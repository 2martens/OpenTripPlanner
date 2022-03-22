package org.opentripplanner.ext.legacygraphqlapi;

import graphql.schema.DataFetchingEnvironment;
import java.util.Locale;
import java.util.Map;
import org.opentripplanner.util.I18NString;

public class LegacyGraphQLUtils {

    public static Locale getLocale(DataFetchingEnvironment environment) {
      String argLang = environment.getArgument("language");
      if (argLang != null) {
        return Locale.forLanguageTag(argLang);
      }

      Map<String, ?> localContext = environment.getLocalContext();
      if (localContext != null && localContext.get("locale") != null) {
        return (Locale) localContext.get("locale");
      }

      return environment.getLocale();
    }

    public static String getTranslation(I18NString input, DataFetchingEnvironment environment) {
      if (input == null) { return null; }
      return input.toString(getLocale(environment));
    }
}