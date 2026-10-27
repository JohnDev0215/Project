package com.amazon.mp3.configuration;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.configuration.managers.EndPointManager;
import com.amazon.mp3.configuration.managers.MarketPlaceManager;
import com.amazon.mp3.configuration.maps.MarketPlaceMap;
import com.amazon.mp3.configuration.models.EndPoint;
import com.amazon.mp3.configuration.models.MarketPlace;
import com.amazon.mp3.configuration.parsers.EnvironmentParser;
import com.amazon.mp3.util.JsonUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonPointer;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public enum IntlConfiguration {
    INSTANCE;

    public static final String AUTHOR = "custombuildby";
    public static final EndPointManager ENDPOINT;
    public static final String ENDPOINT_T = "endpoint";
    private static final EnvironmentParser ENVIRONMENT_HELPER;
    public static final MarketPlaceManager MARKET;
    public static final String MARKETPLACE = "marketplace";

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amazon.mp3.configuration.parsers.EnvironmentParser] */
    static {
        final Context context = AmazonApplication.getContext();
        ?? r0 = new Object(context) { // from class: com.amazon.mp3.configuration.parsers.EnvironmentParser
            private static final String TAG = "com.amazon.mp3.configuration.parsers.EnvironmentParser";
            private HashMap<String, EndPoint> mEndPoints;
            private JSONObject mEnvironment;
            private MarketPlaceMap mMarketPlaces;

            {
                loadEnvironmentFile(context);
            }

            public String getCustomBuildInfo() {
                try {
                    return this.mEnvironment.getString(IntlConfiguration.AUTHOR);
                } catch (JSONException unused) {
                    throw new RuntimeException("custombuildbyneeds to be declared.");
                }
            }

            public MarketPlaceMap getMarketPlaces() {
                if (this.mMarketPlaces == null) {
                    this.mMarketPlaces = parseMarketPlaces();
                }
                return this.mMarketPlaces;
            }

            public HashMap<String, EndPoint> getEndPoints() {
                if (this.mEndPoints == null) {
                    this.mEndPoints = parseEndPoints();
                }
                return this.mEndPoints;
            }

            private MarketPlaceMap parseMarketPlaces() {
                Log.verbose(TAG, "*** Marketplaces loading...");
                MarketPlaceMap marketPlaceMap = new MarketPlaceMap();
                JSONArray jSONArrayLoadRootJSONArray = loadRootJSONArray(IntlConfiguration.MARKETPLACE);
                for (int i = 0; i < jSONArrayLoadRootJSONArray.length(); i++) {
                    MarketPlace marketPlace = parseMarketPlace(getJSONObject(jSONArrayLoadRootJSONArray, i));
                    marketPlaceMap.put(marketPlace);
                    Log.debug(TAG, "     (%s/%s/%s)", marketPlace.countryCode(), marketPlace.id(), marketPlace.obfuscatedId());
                }
                Log.verbose(TAG, "*** Marketplaces done.");
                return marketPlaceMap;
            }

            private MarketPlace parseMarketPlace(JSONObject marketPlace) {
                return new MarketPlace(getValue(marketPlace, "countryCode"), getValue(marketPlace, "id"), getValue(marketPlace, "obfuscatedId"), parseLocales(marketPlace));
            }

            private String[] parseLocales(JSONObject marketPlace) {
                return isArray(marketPlace, Splash.PARAMS_LOCALE) ? getValuesArray(marketPlace, Splash.PARAMS_LOCALE) : new String[]{getValue(marketPlace, Splash.PARAMS_LOCALE)};
            }

            private HashMap<String, EndPoint> parseEndPoints() {
                Log.verbose(TAG, "*** EndpointsProvider loading...");
                HashMap<String, EndPoint> map = new HashMap<>();
                JSONArray jSONArrayLoadRootJSONArray = loadRootJSONArray(IntlConfiguration.ENDPOINT_T);
                for (int i = 0; i < jSONArrayLoadRootJSONArray.length(); i++) {
                    EndPoint endPoint = parseEndPoint(getJSONObject(jSONArrayLoadRootJSONArray, i));
                    map.put(endPoint.id(), endPoint);
                }
                Log.verbose(TAG, "*** EndpointsProvider done.");
                return map;
            }

            private EndPoint parseEndPoint(JSONObject endPoint) {
                String value = getValue(endPoint, "id");
                String str = TAG;
                Log.verbose(str, "     $" + value + " loading...");
                HashMap<String, HashMap<String, String>> values = getValues(endPoint, "domainName", "subdomainPrefix", "port", "protocol");
                HashMap<String, String> map = values.get("domainName");
                HashMap<String, String> map2 = values.get("subdomainPrefix");
                HashMap<String, String> map3 = values.get("port");
                HashMap<String, String> map4 = values.get("protocol");
                HashMap<String, String> values2 = getValues(endPoint, "path", false);
                HashMap<String, String> values3 = getValues(endPoint, "url_params", false);
                HashMap<String, String> values4 = getValues(endPoint, "assochandle", false);
                Log.verbose(str, "     $" + value + " loaded.");
                return new EndPoint(value, map, values4, map2, map3, map4, values2, values3);
            }

            private void loadEnvironmentFile(Context context2) {
                if (AmazonApplication.getDevModeCapabilities().isDeveloperToolEnabled()) {
                    File file = new File(String.format(Locale.US, "%s/%s", "mnt/sdcard/Music/.dev", "environment"));
                    if (file.exists()) {
                        this.mEnvironment = JsonUtil.loadFromFile(file);
                    } else {
                        Log.debug(TAG, "custom environment file not found, using the default file");
                    }
                }
                if (this.mEnvironment == null) {
                    this.mEnvironment = JsonUtil.loadFromResource(context2, R.raw.environment);
                }
                if (this.mEnvironment == null) {
                    throw new RuntimeException("Unable to load environment file.");
                }
                Log.debug(TAG, "***************** Environment file loaded (Build: " + getCustomBuildInfo() + "). Ready for parsing...");
            }

            private JSONArray loadRootJSONArray(String key) {
                try {
                    return this.mEnvironment.getJSONArray(key);
                } catch (JSONException unused) {
                    throw new RuntimeException(key + " is not declared.");
                }
            }

            private JSONObject getJSONObject(JSONArray jsonArray, int index) {
                try {
                    return jsonArray.getJSONObject(index);
                } catch (JSONException unused) {
                    throw new RuntimeException("There is no JSONObject at " + index);
                }
            }

            private String getValue(JSONObject jsonObject, String name) {
                try {
                    return jsonObject.getString(name);
                } catch (JSONException unused) {
                    throw new RuntimeException(name + " is not correctly declared");
                }
            }

            private String[] getValuesArray(JSONObject jsonObject, String name) {
                try {
                    JSONArray jSONArray = jsonObject.getJSONArray(name);
                    String[] strArr = new String[jSONArray.length()];
                    for (int i = 0; i < jSONArray.length(); i++) {
                        strArr[i] = jSONArray.getString(i);
                    }
                    return strArr;
                } catch (JSONException unused) {
                    throw new RuntimeException("Unable to parse array for " + name);
                }
            }

            private HashMap<String, String> getValues(JSONObject jsonObject, String name) {
                return getValues(jsonObject, name, true);
            }

            private HashMap<String, String> getValues(JSONObject jsonObject, String name, boolean mandatory) {
                HashMap<String, String> map = new HashMap<>();
                try {
                    JSONObject jSONObject = jsonObject.getJSONObject(name);
                    JSONArray jSONArrayNames = jSONObject.names();
                    if (jSONArrayNames != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("         " + name + ": ");
                        for (int i = 0; i < jSONArrayNames.length(); i++) {
                            String string = jSONArrayNames.getString(i);
                            String string2 = jSONObject.getString(string);
                            sb.append('(');
                            sb.append(string);
                            sb.append(JsonPointer.SEPARATOR);
                            sb.append(string2);
                            sb.append(')');
                            map.put(string, string2);
                        }
                        Log.verbose(TAG, sb.toString());
                    }
                } catch (JSONException unused) {
                    if (mandatory) {
                        throw new RuntimeException(name + " is not correctly declared");
                    }
                    Log.warning(TAG, "         " + name + " not declared.");
                }
                return map;
            }

            private HashMap<String, HashMap<String, String>> getValues(JSONObject jsonObject, String... names) {
                HashMap<String, HashMap<String, String>> map = new HashMap<>(names.length);
                ArrayList<String> arrayList = new ArrayList();
                HashMap<String, Integer> map2 = new HashMap<>();
                for (String str : names) {
                    if (isDictionnary(jsonObject, str)) {
                        HashMap<String, String> values = getValues(jsonObject, str);
                        populateKeys(values.keySet(), map2);
                        map.put(str, values);
                    } else {
                        arrayList.add(str);
                    }
                }
                for (String str2 : arrayList) {
                    if (isString(jsonObject, str2)) {
                        map.put(str2, getDefaultValues(str2, map2.keySet(), getValue(jsonObject, str2)));
                    } else {
                        throw new RuntimeException(str2 + " is not correctly declared.");
                    }
                }
                return map;
            }

            private HashMap<String, String> getDefaultValues(String name, Set<String> keys, String defaultValue) {
                StringBuilder sb = new StringBuilder();
                sb.append("         " + name + ": ");
                HashMap<String, String> map = new HashMap<>();
                for (String str : keys) {
                    sb.append('(');
                    sb.append(str);
                    sb.append(JsonPointer.SEPARATOR);
                    sb.append(defaultValue);
                    sb.append(')');
                    map.put(str, defaultValue);
                }
                Log.verbose(TAG, sb.toString());
                return map;
            }

            private boolean isDictionnary(JSONObject jsonObject, String name) {
                return jsonObject.optJSONObject(name) != null;
            }

            private boolean isString(JSONObject jsonObject, String name) {
                return jsonObject.optString(name) != null;
            }

            private boolean isArray(JSONObject jsonObject, String name) {
                return jsonObject.optJSONArray(name) != null;
            }

            private void populateKeys(Set<String> keys, HashMap<String, Integer> counter) {
                int iValueOf;
                for (String str : keys) {
                    Integer num = counter.get(str);
                    if (num != null) {
                        iValueOf = Integer.valueOf(num.intValue() + 1);
                    } else {
                        iValueOf = 1;
                    }
                    counter.put(str, iValueOf);
                }
                keysVerified(counter.values());
            }

            private void keysVerified(Collection<Integer> counter) {
                Integer num = null;
                for (Integer num2 : counter) {
                    if (!(num == null || num.equals(num2))) {
                        throw new RuntimeException("Invalid number of keys. Mismatch between the list of keys.");
                    }
                    num = num2;
                }
            }
        };
        ENVIRONMENT_HELPER = r0;
        MARKET = new MarketPlaceManager(r0.getMarketPlaces());
        ENDPOINT = new EndPointManager(r0.getEndPoints());
    }
}
