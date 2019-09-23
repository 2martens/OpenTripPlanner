package org.opentripplanner.netex;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Multimap;
import org.junit.Test;
import org.opentripplanner.model.Agency;
import org.opentripplanner.model.FeedScopedId;
import org.opentripplanner.model.Notice;
import org.opentripplanner.model.OtpTransitService;
import org.opentripplanner.model.Stop;
import org.opentripplanner.model.StopTimeId;
import org.opentripplanner.model.Trip;
import org.opentripplanner.model.calendar.CalendarServiceData;
import org.opentripplanner.model.calendar.ServiceDate;
import org.opentripplanner.model.impl.OtpTransitServiceBuilder;
import org.opentripplanner.netex.configure.NetexConfig;
import org.opentripplanner.netex.loader.NetexBundle;
import org.opentripplanner.routing.edgetype.TripPattern;
import org.opentripplanner.routing.trippattern.Deduplicator;
import org.opentripplanner.standalone.GraphBuilderParameters;
import org.opentripplanner.standalone.config.OTPConfiguration;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Load a small NeTEx file set without failing. This is just a smoke test
 * and should be excluded from line coverage. The focus of this test is
 * to test that the different parts of the NeTEx works together.
 */
public class NetexLoaderSmokeTest {

    private static final String NETEX_DIR = "src/test/resources/netex";
    private static final String NETEX_FILENAME = "netex_minimal.zip";

    @Test
    public void smokeTestOfNetexLoadData() {
        // Given
        GraphBuilderParameters builderParameters = createBuilderParameters();
        NetexBundle netexBundle = createNetexBundle(builderParameters);

        // Run the check to make sure it does not throw an exception
        netexBundle.checkInputs();

        // When
        OtpTransitServiceBuilder transitBuilder = netexBundle.loadBundle(new Deduplicator());

        // Then - smoke test model
        OtpTransitService otpModel = transitBuilder.build();
        assertAgencies(otpModel.getAllAgencies());
        assertStops(otpModel.getAllStops());
        assertTripPatterns(otpModel.getTripPatterns());
        assertTrips(otpModel.getAllTrips());
        assertServiceIds(otpModel.getAllServiceIds());
        assertNoticeAssignments(otpModel.getNoticeAssignments());

        // And then - smoke test service calendar
        assetServiceCalendar(transitBuilder.buildCalendarServiceData());
    }


    /* private methods */

    private void assertAgencies(Collection<Agency> agencies) {
        Agency a = list(agencies).get(0);
        assertEquals("RUT:Authority:RUT", a.getId());
        assertEquals("RUT", a.getName());
        assertNull( a.getUrl());
        assertEquals("Europe/Oslo", a.getTimezone());
        assertNull(a.getLang());
        assertNull(a.getPhone());
        assertNull( a.getFareUrl());
        assertNull( a.getBrandingUrl());
        assertEquals(1, agencies.size());
    }

    private void assertStops(Collection<Stop> stops) {
        Map<FeedScopedId, Stop> map = stops.stream().collect(Collectors.toMap(Stop::getId, s -> s));

        Stop quay = map.get(fId("NSR:Quay:122003"));
        assertEquals("N/A", quay.getName());
        assertEquals(59.909803, quay.getLat(), 0.000001);
        assertEquals(10.748062, quay.getLon(), 0.000001);
        assertEquals("NSR:StopPlace:3995", quay.getParentStation());
        assertEquals(0, quay.getLocationType());
        assertEquals("L", quay.getPlatformCode());

        Stop station = map.get(fId("NSR:StopPlace:58243"));
        assertEquals("Bergkrystallen", station.getName());
        assertEquals(59.866603, station.getLat(), 0.000001);
        assertEquals(10.821614, station.getLon(), 0.000001);
        assertNull(station.getParentStation());
        assertEquals(1, station.getLocationType());

        assertEquals(24, stops.size());
    }

    private void assertTripPatterns(Collection<TripPattern> patterns) {
        Map<FeedScopedId, TripPattern> map = patterns.stream().collect(Collectors.toMap(TripPattern::getId, s -> s));
        TripPattern p = map.get(fId("RUT:JourneyPattern:12-1"));
        assertEquals("Jernbanetorget", p.getDirection());
        assertEquals("RB", p.getFeedId());
        assertEquals("[<Stop RB_NSR:Quay:7203>, <Stop RB_NSR:Quay:8027>]", p.getStops().toString());
        assertEquals("[<Trip RB_RUT:ServiceJourney:12-101375-1000>]", p.getTrips().toString());
        // TODO OTP2 - Why?
        assertNull(p.getServices());
        assertEquals(4, patterns.size());
    }

    private void assertTrips(Collection<Trip> trips) {
        Map<FeedScopedId, Trip> map = trips.stream().collect(Collectors.toMap(Trip::getId, t -> t));
        Trip t = map.get(fId("RUT:ServiceJourney:12-101375-1001"));

        assertEquals("Jernbanetorget", t.getTripHeadsign());
        assertNull(t.getTripShortName());
        assertEquals("RUT:DayType:6-101468", t.getServiceId().getId());
        assertEquals(0, t.getBikesAllowed());
        assertEquals(0, t.getWheelchairAccessible());
        assertEquals(4, trips.size());
    }

    private void assertNoticeAssignments(Multimap<Serializable, Notice> map) {
        assertNote(map, fId("RUT:ServiceJourney:4-101468-583"),"045", "Notice on ServiceJourney");
        assertNote(map, stId("RUT:ServiceJourney:4-101468-583", 0), "035", "Notice on TimetabledPassingTime");
        assertNote(map, fId("RUT:Line:4"), "075", "Notice on Line");
        assertNote(map, stId("RUT:ServiceJourney:4-101493-1098", 1), "090", "Notice on Journeypattern");
        assertEquals(4, map.size());
    }

    private void assertNote(Multimap<Serializable, Notice> map, Serializable key, String code, String text) {
        List<Notice> list = list(map.get(key));
        if(list.size() == 0) fail("Notice not found: " + key + " -> <Notice " + code + ", " + text + ">\n\t" + map);
        Notice n = list.get(0);
        assertTrue(n.getId().toString().startsWith("RB_RUT:Notice:"));
        assertEquals(code, n.getPublicCode());
        assertEquals(text, n.getText());
        assertEquals(1, list.size());
    }

    private void assertServiceIds(Collection<FeedScopedId> serviceIds) {
        List<String> sIds = serviceIds.stream().map(FeedScopedId::getId).sorted().collect(Collectors.toList());
        assertEquals(
                "[RUT:DayType:0-105025+RUT:DayType:0-105026+RUT:DayType:6-101468, RUT:DayType:6-101468]",
                sIds.toString()
        );
    }

    private void assetServiceCalendar(CalendarServiceData cal) {
        assertEquals("[RUT:Authority:RUT]", cal.getAgencyIds().toString());
        assertEquals("Europe/Oslo", cal.getTimeZoneForAgencyId("RUT:Authority:RUT").toZoneId().toString());
        assertEquals(
                "[RUT:DayType:0-105025+RUT:DayType:0-105026+RUT:DayType:6-101468, RUT:DayType:6-101468]",
                cal.getServiceIds().stream().map(FeedScopedId::getId).sorted().collect(Collectors.toList()).toString()
        );
        assertEquals(
                "[2017-12-21, 2017-12-22, 2017-12-25, 2017-12-26, 2017-12-27, 2017-12-28, 2017-12-29, 2018-01-02, 2018-01-03, 2018-01-04]",
                cal.getServiceDatesForServiceId(fId("RUT:DayType:6-101468")).toString()
        );
        ServiceDate DEC_29 = new ServiceDate(2017, 12, 29);
        assertEquals(
                "RUT:DayType:0-105025+RUT:DayType:0-105026+RUT:DayType:6-101468, RUT:DayType:6-101468",
                cal.getServiceIdsForDate(DEC_29).stream().map(FeedScopedId::getId).sorted().collect(Collectors.joining(", "))
        );
        assertEquals(2, cal.getServiceIds().size());
        assertEquals(1, cal.getAgencyIds().size());
    }

    private static <T> List<T> list(Collection<T> collection) { return new ArrayList<>(collection);}

    private static StopTimeId stId(String id, int stopSequenceNr) {
        return new StopTimeId(fId(id), stopSequenceNr);
    }

    private static FeedScopedId fId(String id) {
        return new FeedScopedId("RB", id);
    }

    private static GraphBuilderParameters createBuilderParameters() {
        JsonNode buildConfig = new OTPConfiguration(null)
                .getGraphConfig(new File(NETEX_DIR))
                .builderConfig();

        return new GraphBuilderParameters(buildConfig);
    }

    private static NetexBundle createNetexBundle(GraphBuilderParameters builderParameters) {
        return NetexConfig.netexBundleForTest(
                builderParameters,
                new File(NETEX_DIR, NETEX_FILENAME)
        );
    }
}