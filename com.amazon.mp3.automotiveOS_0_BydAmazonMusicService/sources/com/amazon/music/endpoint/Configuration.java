package com.amazon.music.endpoint;

import com.amazon.music.marketplace.Marketplace;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class Configuration {
    private static final Map<Marketplace, String> DOMAINS;
    private static Map<Marketplace, Map<EndpointType, String>> ENDPOINTS;
    private static final Set<Marketplace> EU_MARKETPLACES;
    private static final Set<Marketplace> FE_MARKETPLACES;
    private static final Set<Marketplace> NA_MARKETPLACES;
    private static final Set<Marketplace> ROW_MARKETPLACES;

    public static Map<Marketplace, Map<EndpointType, String>> get() {
        return ENDPOINTS;
    }

    static {
        HashSet hashSet = new HashSet();
        NA_MARKETPLACES = hashSet;
        Marketplace marketplace = Marketplace.USA;
        hashSet.add(marketplace);
        Marketplace marketplace2 = Marketplace.ROW_NA;
        hashSet.add(marketplace2);
        HashSet hashSet2 = new HashSet();
        EU_MARKETPLACES = hashSet2;
        Marketplace marketplace3 = Marketplace.UK;
        hashSet2.add(marketplace3);
        Marketplace marketplace4 = Marketplace.GERMANY;
        hashSet2.add(marketplace4);
        Marketplace marketplace5 = Marketplace.FRANCE;
        hashSet2.add(marketplace5);
        Marketplace marketplace6 = Marketplace.SPAIN;
        hashSet2.add(marketplace6);
        Marketplace marketplace7 = Marketplace.ITALY;
        hashSet2.add(marketplace7);
        Marketplace marketplace8 = Marketplace.ROE_EU;
        hashSet2.add(marketplace8);
        HashSet hashSet3 = new HashSet();
        FE_MARKETPLACES = hashSet3;
        Marketplace marketplace9 = Marketplace.JAPAN;
        hashSet3.add(marketplace9);
        Marketplace marketplace10 = Marketplace.ROW_FE;
        hashSet3.add(marketplace10);
        HashSet hashSet4 = new HashSet();
        ROW_MARKETPLACES = hashSet4;
        hashSet4.add(marketplace8);
        hashSet4.add(marketplace10);
        hashSet4.add(marketplace2);
        HashMap map = new HashMap();
        DOMAINS = map;
        map.put(marketplace, "com");
        map.put(marketplace3, "co.uk");
        map.put(marketplace4, "de");
        map.put(marketplace5, "fr");
        map.put(marketplace7, "it");
        map.put(marketplace6, "es");
        map.put(marketplace9, "co.jp");
        Map<EndpointType, String> mapConstructConfigurationForMarketplace = constructConfigurationForMarketplace(marketplace);
        Map<EndpointType, String> mapConstructConfigurationForMarketplace2 = constructConfigurationForMarketplace(marketplace2);
        Map<EndpointType, String> mapConstructConfigurationForMarketplace3 = constructConfigurationForMarketplace(marketplace3);
        Map<EndpointType, String> mapConstructConfigurationForMarketplace4 = constructConfigurationForMarketplace(marketplace4);
        Map<EndpointType, String> mapConstructConfigurationForMarketplace5 = constructConfigurationForMarketplace(marketplace5);
        Map<EndpointType, String> mapConstructConfigurationForMarketplace6 = constructConfigurationForMarketplace(marketplace6);
        Map<EndpointType, String> mapConstructConfigurationForMarketplace7 = constructConfigurationForMarketplace(marketplace7);
        Map<EndpointType, String> mapConstructConfigurationForMarketplace8 = constructConfigurationForMarketplace(marketplace8);
        Map<EndpointType, String> mapConstructConfigurationForMarketplace9 = constructConfigurationForMarketplace(marketplace9);
        Map<EndpointType, String> mapConstructConfigurationForMarketplace10 = constructConfigurationForMarketplace(marketplace10);
        HashMap map2 = new HashMap();
        ENDPOINTS = map2;
        map2.put(marketplace, mapConstructConfigurationForMarketplace);
        ENDPOINTS.put(marketplace3, mapConstructConfigurationForMarketplace3);
        ENDPOINTS.put(marketplace4, mapConstructConfigurationForMarketplace4);
        ENDPOINTS.put(marketplace5, mapConstructConfigurationForMarketplace5);
        ENDPOINTS.put(marketplace9, mapConstructConfigurationForMarketplace9);
        ENDPOINTS.put(marketplace6, mapConstructConfigurationForMarketplace6);
        ENDPOINTS.put(marketplace7, mapConstructConfigurationForMarketplace7);
        ENDPOINTS.put(marketplace2, mapConstructConfigurationForMarketplace2);
        ENDPOINTS.put(marketplace8, mapConstructConfigurationForMarketplace8);
        ENDPOINTS.put(marketplace10, mapConstructConfigurationForMarketplace10);
    }

    private static Map<EndpointType, String> constructConfigurationForMarketplace(Marketplace marketplace) {
        String regionString = getRegionString(marketplace);
        HashMap map = new HashMap();
        map.put(EndpointType.CIRRUS, String.format("https://music.amazon.com:443/%s/api/cirrus/v3/", regionString));
        map.put(EndpointType.DMLS, String.format("https://music.amazon.com:443/%s/api/dmls/", regionString));
        map.put(EndpointType.MPQS_NON_VOICE_ENABLED, String.format("https://music.amazon.com:443/%s/api/mpqs/nonvoiceenabled/", regionString));
        map.put(EndpointType.MPQS_VOICE_ENABLED, String.format("https://music.amazon.com:443/%s/api/mpqs/voiceenabled/", regionString));
        map.put(EndpointType.MUSE, String.format("https://music.amazon.com:443/%s/api/muse/", regionString));
        map.put(EndpointType.MUSE_BRUSH, String.format("https://music.amazon.com:443/%s/api/musepage/", regionString));
        map.put(EndpointType.NAUTILUS, String.format("https://music.amazon.com:443/%s/api/mss/", regionString));
        map.put(EndpointType.NIMBLY, String.format("https://music.amazon.com:443/%s/api/nimbly/", regionString));
        map.put(EndpointType.PLAYLIST, String.format("https://music.amazon.com:443/%s/api/playlists/", regionString));
        map.put(EndpointType.STRATUS, String.format("https://music.amazon.com:443/%s/api/stratus/", regionString));
        map.put(EndpointType.TENZING_TEXT_SEARCH, String.format("https://music.amazon.com/%s/api/textsearch/search/v1_1/", regionString));
        map.put(EndpointType.TENZING_TEXT_SUGGEST, String.format("https://music.amazon.com/%s/api/textsearch/suggest/v1_1/", regionString));
        map.put(EndpointType.TENZING_TEXT_INSTANT_SEARCH, String.format("https://music.amazon.com/%s/api/textsearch/instantsearch/v1_1/", regionString));
        map.put(EndpointType.IAM, "https://268mhmtxhj.execute-api.us-east-1.amazonaws.com/prod/v1/inappmessages");
        addLegalUrl(map, marketplace);
        addHelpUrl(map, marketplace);
        return map;
    }

    private static void addHelpUrl(Map<EndpointType, String> map, Marketplace marketplace) {
        map.put(EndpointType.HELP, ROW_MARKETPLACES.contains(marketplace) ? "https://music.amazon.com/help?nodeId=202196550 " : String.format("https://www.amazon.%s/gp/help/customer/display.html?nodeId=201650670", DOMAINS.get(marketplace)));
    }

    private static void addLegalUrl(Map<EndpointType, String> map, Marketplace marketplace) {
        String str;
        if (ROW_MARKETPLACES.contains(marketplace)) {
            str = "https://music.amazon.com/help?nodeId=202196530";
        } else {
            str = marketplace.equals(Marketplace.USA) ? "https://www.amazon.com/gp/help/customer/display.html?nodeId=201482960" : String.format("https://www.amazon.%s/gp/help/customer/display.html?nodeId=201377350", DOMAINS.get(marketplace));
        }
        map.put(EndpointType.LEGAL, str);
    }

    private static String getRegionString(Marketplace marketplace) {
        return EU_MARKETPLACES.contains(marketplace) ? "EU" : FE_MARKETPLACES.contains(marketplace) ? "FE" : "NA";
    }
}
