// THIS IS AN AUTOGENERATED FILE. DO NOT EDIT THIS FILE DIRECTLY.

package org.opentripplanner.ext.legacygraphqlapi.generated;

import org.opentripplanner.model.Agency;
import org.opentripplanner.routing.alertpatch.AlertPatch;
import org.opentripplanner.routing.bike_park.BikePark;
import org.opentripplanner.routing.bike_rental.BikeRentalStation;
import org.locationtech.jts.geom.Coordinate;
import org.opentripplanner.api.resource.DebugOutput;
import org.opentripplanner.common.model.P2;
import java.util.Map;
import org.opentripplanner.routing.core.FareComponent;
import org.opentripplanner.model.FeedInfo;
import org.opentripplanner.util.model.EncodedPolylineBean;
import org.opentripplanner.model.plan.Itinerary;
import org.opentripplanner.model.plan.Leg;
import org.opentripplanner.model.TripPattern;
import org.opentripplanner.model.plan.StopArrival;
import graphql.relay.Connection;
import graphql.relay.Edge;
import org.opentripplanner.routing.api.response.RoutingResponse;
import org.opentripplanner.model.Route;
import org.opentripplanner.model.plan.WalkStep;
import org.opentripplanner.routing.StopFinder;
import graphql.relay.Connection;
import graphql.relay.Edge;
import org.opentripplanner.model.TripTimeShort;
import org.opentripplanner.model.StopTimesInPattern;
import org.opentripplanner.routing.core.FareRuleSet;
import java.util.Map;
import org.opentripplanner.model.Trip;
import graphql.schema.TypeResolver;
import graphql.schema.DataFetcher;

public class LegacyGraphQLDataFetchers {
  /** A public transport agency */
  public interface LegacyGraphQLAgency {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<String> gtfsId();
    public DataFetcher<String> name();
    public DataFetcher<String> url();
    public DataFetcher<String> timezone();
    public DataFetcher<String> lang();
    public DataFetcher<String> phone();
    public DataFetcher<String> fareUrl();
    public DataFetcher<Iterable<Route>> routes();
    public DataFetcher<Iterable<AlertPatch>> alerts();
  }
  
  /** Alert of a current or upcoming disruption in public transportation */
  public interface LegacyGraphQLAlert {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<Integer> alertHash();
    public DataFetcher<String> feed();
    public DataFetcher<Agency> agency();
    public DataFetcher<Route> route();
    public DataFetcher<Trip> trip();
    public DataFetcher<Object> stop();
    public DataFetcher<Iterable<TripPattern>> patterns();
    public DataFetcher<String> alertHeaderText();
    public DataFetcher<Iterable<Map.Entry<String, String>>> alertHeaderTextTranslations();
    public DataFetcher<String> alertDescriptionText();
    public DataFetcher<Iterable<Map.Entry<String, String>>> alertDescriptionTextTranslations();
    public DataFetcher<String> alertUrl();
    public DataFetcher<Iterable<Map.Entry<String, String>>> alertUrlTranslations();
    public DataFetcher<String> alertEffect();
    public DataFetcher<String> alertCause();
    public DataFetcher<String> alertSeverityLevel();
    public DataFetcher<Long> effectiveStartDate();
    public DataFetcher<Long> effectiveEndDate();
  }
  
  /** Bike park represents a location where bicycles can be parked. */
  public interface LegacyGraphQLBikePark {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<String> bikeParkId();
    public DataFetcher<String> name();
    public DataFetcher<Integer> spacesAvailable();
    public DataFetcher<Boolean> realtime();
    public DataFetcher<Double> lon();
    public DataFetcher<Double> lat();
  }
  
  /** Bike rental station represents a location where users can rent bicycles for a fee. */
  public interface LegacyGraphQLBikeRentalStation {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<String> stationId();
    public DataFetcher<String> name();
    public DataFetcher<Integer> bikesAvailable();
    public DataFetcher<Integer> spacesAvailable();
    public DataFetcher<String> state();
    public DataFetcher<Boolean> realtime();
    public DataFetcher<Boolean> allowDropoff();
    public DataFetcher<Iterable<String>> networks();
    public DataFetcher<Double> lon();
    public DataFetcher<Double> lat();
  }
  
  /** Car park represents a location where cars can be parked. */
  public interface LegacyGraphQLCarPark {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<String> carParkId();
    public DataFetcher<String> name();
    public DataFetcher<Integer> maxCapacity();
    public DataFetcher<Integer> spacesAvailable();
    public DataFetcher<Boolean> realtime();
    public DataFetcher<Double> lon();
    public DataFetcher<Double> lat();
  }
  
  /** Cluster is a list of stops grouped by name and proximity */
  public interface LegacyGraphQLCluster {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<String> gtfsId();
    public DataFetcher<String> name();
    public DataFetcher<Double> lat();
    public DataFetcher<Double> lon();
    public DataFetcher<Iterable<Object>> stops();
  }
  
  public interface LegacyGraphQLCoordinates {
    public DataFetcher<Double> lat();
    public DataFetcher<Double> lon();
  }
  
  public interface LegacyGraphQLDebugOutput {
    public DataFetcher<Long> totalTime();
    public DataFetcher<Long> pathCalculationTime();
    public DataFetcher<Long> precalculationTime();
    public DataFetcher<Long> renderingTime();
    public DataFetcher<Boolean> timedOut();
  }
  
  /**
   * Departure row is a location, which lists departures of a certain pattern from a
   * stop. Departure rows are identified with the pattern, so querying departure rows
   * will return only departures from one stop per pattern
   */
  public interface LegacyGraphQLDepartureRow {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<Object> stop();
    public DataFetcher<Double> lat();
    public DataFetcher<Double> lon();
    public DataFetcher<TripPattern> pattern();
    public DataFetcher<Iterable<TripTimeShort>> stoptimes();
  }
  
  public interface LegacyGraphQLElevationProfileComponent {
    public DataFetcher<Double> distance();
    public DataFetcher<Double> elevation();
  }
  
  public interface LegacyGraphQLFare {
    public DataFetcher<String> type();
    public DataFetcher<String> currency();
    public DataFetcher<Integer> cents();
    public DataFetcher<Iterable<FareComponent>> components();
  }
  
  /** Component of the fare (i.e. ticket) for a part of the itinerary */
  public interface LegacyGraphQLFareComponent {
    public DataFetcher<String> fareId();
    public DataFetcher<String> currency();
    public DataFetcher<Integer> cents();
    public DataFetcher<Iterable<Route>> routes();
  }
  
  /** A feed provides routing data (stops, routes, timetables, etc.) from one or more public transport agencies. */
  public interface LegacyGraphQLFeed {
    public DataFetcher<String> feedId();
    public DataFetcher<Iterable<Agency>> agencies();
  }
  
  public interface LegacyGraphQLGeometry {
    public DataFetcher<Integer> length();
    public DataFetcher<String> points();
  }
  
  public interface LegacyGraphQLItinerary {
    public DataFetcher<Long> startTime();
    public DataFetcher<Long> endTime();
    public DataFetcher<Long> duration();
    public DataFetcher<Long> waitingTime();
    public DataFetcher<Long> walkTime();
    public DataFetcher<Double> walkDistance();
    public DataFetcher<Iterable<Leg>> legs();
    public DataFetcher<Iterable<Map<String, Object>>> fares();
    public DataFetcher<Double> elevationGained();
    public DataFetcher<Double> elevationLost();
  }
  
  public interface LegacyGraphQLLeg {
    public DataFetcher<Long> startTime();
    public DataFetcher<Long> endTime();
    public DataFetcher<Integer> departureDelay();
    public DataFetcher<Integer> arrivalDelay();
    public DataFetcher<String> mode();
    public DataFetcher<Double> duration();
    public DataFetcher<EncodedPolylineBean> legGeometry();
    public DataFetcher<Agency> agency();
    public DataFetcher<Boolean> realTime();
    public DataFetcher<String> realtimeState();
    public DataFetcher<Double> distance();
    public DataFetcher<Boolean> transitLeg();
    public DataFetcher<Boolean> rentedBike();
    public DataFetcher<StopArrival> from();
    public DataFetcher<StopArrival> to();
    public DataFetcher<Route> route();
    public DataFetcher<Trip> trip();
    public DataFetcher<String> serviceDate();
    public DataFetcher<Iterable<Object>> intermediateStops();
    public DataFetcher<Iterable<StopArrival>> intermediatePlaces();
    public DataFetcher<Boolean> intermediatePlace();
    public DataFetcher<Iterable<WalkStep>> steps();
  }
  
  /** An object with an ID */
  public interface LegacyGraphQLNode extends TypeResolver {
    default public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id() { return null; }
  }
  
  /** Information about pagination in a connection. */
  public interface LegacyGraphQLPageInfo {
    public DataFetcher<Boolean> hasNextPage();
    public DataFetcher<Boolean> hasPreviousPage();
    public DataFetcher<String> startCursor();
    public DataFetcher<String> endCursor();
  }
  
  /**
   * Pattern is sequence of stops used by trips on a specific direction and variant
   * of a route. Most routes have only two patterns: one for outbound trips and one
   * for inbound trips
   */
  public interface LegacyGraphQLPattern {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<Route> route();
    public DataFetcher<Integer> directionId();
    public DataFetcher<String> name();
    public DataFetcher<String> code();
    public DataFetcher<String> headsign();
    public DataFetcher<Iterable<Trip>> trips();
    public DataFetcher<Iterable<Trip>> tripsForDate();
    public DataFetcher<Iterable<Object>> stops();
    public DataFetcher<Iterable<Coordinate>> geometry();
    public DataFetcher<EncodedPolylineBean> patternGeometry();
    public DataFetcher<String> semanticHash();
    public DataFetcher<Iterable<AlertPatch>> alerts();
  }
  
  public interface LegacyGraphQLPlace {
    public DataFetcher<String> name();
    public DataFetcher<String> vertexType();
    public DataFetcher<Double> lat();
    public DataFetcher<Double> lon();
    public DataFetcher<Long> arrivalTime();
    public DataFetcher<Long> departureTime();
    public DataFetcher<Object> stop();
    public DataFetcher<BikeRentalStation> bikeRentalStation();
    public DataFetcher<BikePark> bikePark();
    public DataFetcher<Object> carPark();
  }
  
  public interface LegacyGraphQLPlaceAtDistance {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<Object> place();
    public DataFetcher<Integer> distance();
  }
  
  /** A connection to a list of items. */
  public interface LegacyGraphQLPlaceAtDistanceConnection {
    public DataFetcher<Iterable<Edge<Object>>> edges();
    public DataFetcher<Object> pageInfo();
  }
  
  /** An edge in a connection. */
  public interface LegacyGraphQLPlaceAtDistanceEdge {
    public DataFetcher<Object> node();
    public DataFetcher<String> cursor();
  }
  
  /** Interface for places, e.g. stops, stations, parking areas.. */
  public interface LegacyGraphQLPlaceInterface extends TypeResolver {
    default public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id() { return null; }
    default public DataFetcher<Double> lat() { return null; }
    default public DataFetcher<Double> lon() { return null; }
  }
  
  public interface LegacyGraphQLPlan {
    public DataFetcher<Long> date();
    public DataFetcher<StopArrival> from();
    public DataFetcher<StopArrival> to();
    public DataFetcher<Iterable<Itinerary>> itineraries();
    public DataFetcher<Iterable<String>> messageEnums();
    public DataFetcher<Iterable<String>> messageStrings();
    public DataFetcher<DebugOutput> debugOutput();
  }
  
  public interface LegacyGraphQLQueryType {
    public DataFetcher<Object> node();
    public DataFetcher<Iterable<FeedInfo>> feeds();
    public DataFetcher<Iterable<Agency>> agencies();
    public DataFetcher<Iterable<FareRuleSet>> ticketTypes();
    public DataFetcher<Agency> agency();
    public DataFetcher<Iterable<Object>> stops();
    public DataFetcher<Iterable<Object>> stopsByBbox();
    public DataFetcher<Connection<StopFinder.StopAndDistance>> stopsByRadius();
    public DataFetcher<Connection<Object>> nearest();
    public DataFetcher<Object> departureRow();
    public DataFetcher<Object> stop();
    public DataFetcher<Object> station();
    public DataFetcher<Iterable<Object>> stations();
    public DataFetcher<Iterable<Route>> routes();
    public DataFetcher<Route> route();
    public DataFetcher<Iterable<Trip>> trips();
    public DataFetcher<Trip> trip();
    public DataFetcher<Trip> fuzzyTrip();
    public DataFetcher<Iterable<TripTimeShort>> cancelledTripTimes();
    public DataFetcher<Iterable<TripPattern>> patterns();
    public DataFetcher<TripPattern> pattern();
    public DataFetcher<Iterable<Object>> clusters();
    public DataFetcher<Object> cluster();
    public DataFetcher<Iterable<AlertPatch>> alerts();
    public DataFetcher<Object> serviceTimeRange();
    public DataFetcher<Iterable<BikeRentalStation>> bikeRentalStations();
    public DataFetcher<BikeRentalStation> bikeRentalStation();
    public DataFetcher<Iterable<BikePark>> bikeParks();
    public DataFetcher<BikePark> bikePark();
    public DataFetcher<Iterable<Object>> carParks();
    public DataFetcher<Object> carPark();
    public DataFetcher<Object> viewer();
    public DataFetcher<RoutingResponse> plan();
  }
  
  /**
   * Route represents a public transportation service, usually from point A to point
   * B and *back*, shown to customers under a single name, e.g. bus 550. Routes
   * contain patterns (see field `patterns`), which describe different variants of
   * the route, e.g. outbound pattern from point A to point B and inbound pattern
   * from point B to point A.
   */
  public interface LegacyGraphQLRoute {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<String> gtfsId();
    public DataFetcher<Agency> agency();
    public DataFetcher<String> shortName();
    public DataFetcher<String> longName();
    public DataFetcher<String> mode();
    public DataFetcher<Integer> type();
    public DataFetcher<String> desc();
    public DataFetcher<String> url();
    public DataFetcher<String> color();
    public DataFetcher<String> textColor();
    public DataFetcher<String> bikesAllowed();
    public DataFetcher<Iterable<TripPattern>> patterns();
    public DataFetcher<Iterable<Object>> stops();
    public DataFetcher<Iterable<Trip>> trips();
    public DataFetcher<Iterable<AlertPatch>> alerts();
  }
  
  /** Time range for which the API has data available */
  public interface LegacyGraphQLServiceTimeRange {
    public DataFetcher<Long> start();
    public DataFetcher<Long> end();
  }
  
  public interface LegacyGraphQLStep {
    public DataFetcher<Double> distance();
    public DataFetcher<Double> lon();
    public DataFetcher<Double> lat();
    public DataFetcher<Iterable<P2<Double>>> elevationProfile();
  }
  
  /**
   * Stop can represent either a single public transport stop, where passengers can
   * board and/or disembark vehicles, or a station, which contains multiple stops.
   * See field `locationType`.
   */
  public interface LegacyGraphQLStop {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<Iterable<TripTimeShort>> stopTimesForPattern();
    public DataFetcher<String> gtfsId();
    public DataFetcher<String> name();
    public DataFetcher<Double> lat();
    public DataFetcher<Double> lon();
    public DataFetcher<String> code();
    public DataFetcher<String> desc();
    public DataFetcher<String> zoneId();
    public DataFetcher<String> url();
    public DataFetcher<Object> locationType();
    public DataFetcher<Object> parentStation();
    public DataFetcher<Object> wheelchairBoarding();
    public DataFetcher<String> direction();
    public DataFetcher<String> timezone();
    public DataFetcher<Integer> vehicleType();
    public DataFetcher<String> vehicleMode();
    public DataFetcher<String> platformCode();
    public DataFetcher<Object> cluster();
    public DataFetcher<Iterable<Object>> stops();
    public DataFetcher<Iterable<Route>> routes();
    public DataFetcher<Iterable<TripPattern>> patterns();
    public DataFetcher<Iterable<StopFinder.StopAndDistance>> transfers();
    public DataFetcher<Iterable<StopTimesInPattern>> stoptimesForServiceDate();
    public DataFetcher<Iterable<StopTimesInPattern>> stoptimesForPatterns();
    public DataFetcher<Iterable<TripTimeShort>> stoptimesWithoutPatterns();
    public DataFetcher<Iterable<AlertPatch>> alerts();
  }
  
  public interface LegacyGraphQLStopAtDistance {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<Object> stop();
    public DataFetcher<Integer> distance();
  }
  
  /** A connection to a list of items. */
  public interface LegacyGraphQLStopAtDistanceConnection {
    public DataFetcher<Iterable<Edge<StopFinder.StopAndDistance>>> edges();
    public DataFetcher<Object> pageInfo();
  }
  
  /** An edge in a connection. */
  public interface LegacyGraphQLStopAtDistanceEdge {
    public DataFetcher<StopFinder.StopAndDistance> node();
    public DataFetcher<String> cursor();
  }
  
  /** Stoptime represents the time when a specific trip arrives to or departs from a specific stop. */
  public interface LegacyGraphQLStoptime {
    public DataFetcher<Object> stop();
    public DataFetcher<Integer> scheduledArrival();
    public DataFetcher<Integer> realtimeArrival();
    public DataFetcher<Integer> arrivalDelay();
    public DataFetcher<Integer> scheduledDeparture();
    public DataFetcher<Integer> realtimeDeparture();
    public DataFetcher<Integer> departureDelay();
    public DataFetcher<Boolean> timepoint();
    public DataFetcher<Boolean> realtime();
    public DataFetcher<String> realtimeState();
    public DataFetcher<String> pickupType();
    public DataFetcher<String> dropoffType();
    public DataFetcher<Long> serviceDay();
    public DataFetcher<Trip> trip();
    public DataFetcher<String> headsign();
  }
  
  /** Stoptimes grouped by pattern */
  public interface LegacyGraphQLStoptimesInPattern {
    public DataFetcher<TripPattern> pattern();
    public DataFetcher<Iterable<TripTimeShort>> stoptimes();
  }
  
  /** Describes ticket type */
  public interface LegacyGraphQLTicketType {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> fareId();
    public DataFetcher<Double> price();
    public DataFetcher<String> currency();
    public DataFetcher<Iterable<String>> zones();
  }
  
  /** Text with language */
  public interface LegacyGraphQLTranslatedString {
    public DataFetcher<String> text();
    public DataFetcher<String> language();
  }
  
  /** Trip is a specific occurance of a pattern, usually identified by route, direction on the route and exact departure time. */
  public interface LegacyGraphQLTrip {
    public DataFetcher<graphql.relay.Relay.ResolvedGlobalId> id();
    public DataFetcher<String> gtfsId();
    public DataFetcher<Route> route();
    public DataFetcher<String> serviceId();
    public DataFetcher<Iterable<String>> activeDates();
    public DataFetcher<String> tripShortName();
    public DataFetcher<String> tripHeadsign();
    public DataFetcher<String> routeShortName();
    public DataFetcher<String> directionId();
    public DataFetcher<String> blockId();
    public DataFetcher<String> shapeId();
    public DataFetcher<Object> wheelchairAccessible();
    public DataFetcher<String> bikesAllowed();
    public DataFetcher<TripPattern> pattern();
    public DataFetcher<Iterable<Object>> stops();
    public DataFetcher<String> semanticHash();
    public DataFetcher<Iterable<TripTimeShort>> stoptimes();
    public DataFetcher<TripTimeShort> departureStoptime();
    public DataFetcher<TripTimeShort> arrivalStoptime();
    public DataFetcher<Iterable<TripTimeShort>> stoptimesForDate();
    public DataFetcher<Iterable<Iterable<Double>>> geometry();
    public DataFetcher<EncodedPolylineBean> tripGeometry();
    public DataFetcher<Iterable<AlertPatch>> alerts();
  }
  
}
