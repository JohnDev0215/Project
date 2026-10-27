package com.amazon.mp3.environment.url;

import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.environment.url.CloudQueueAuthEndpointURLFactory;
import com.amazon.mp3.feed.FeedEndpointFactory;
import com.amazon.mp3.hawkfire.upsell.UnsupportedMarketplaceException;
import com.amazon.mp3.util.Log;
import com.amazon.music.endpoint.EndpointProvider;
import com.amazon.music.endpoint.EndpointURLRequest;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ConfigurationProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class EndpointsProvider {
    private static final HashMap<Marketplace, String> ACCOUNT_WEBVIEW_MARKETPLACE_MAP;
    private static final HashMap<String, String> ACCOUNT_WEBVIEW_TERRITORY_MAP;
    private static final HashMap<Marketplace, String> AD_PREFS_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> ALEXA_HELP_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> ALEXA_HINT_SERVICE_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> AVS_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> BMWV_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> CANTILEVER_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> CLIENTBUDDY_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> CLOUDPLAYER_ENDPOint_MAP;
    private static final HashMap<Marketplace, String> CUSTOMER_LOOKUP_RESULTS_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> CUSTOMER_LOOKUP_SUGGESTIONS_ENDPOINT_MAP;
    private static final HashMap<String, String> DOMAIN_MAP;
    private static final HashMap<String, String> DOMAIN_TERRITORY_MAP;
    private static final HashMap<Marketplace, String> EARLY_USE_NOTIFICAION_ATC_ENDPOINT_MAP;
    private static final HashMap<String, String> EARLY_USE_NOTIFICAION_ATC_MUSICTERRITORY_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> EARLY_USE_NOTIFICAION_FTU_ENDPOINT_MAP;
    private static final HashMap<String, String> EARLY_USE_NOTIFICAION_FTU_MUSICTERRITORY_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> LIBRARY_HELP_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> LYRICS_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> MPQS_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> MPQS_V2_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> MUSIC_IDENTITY_SERVICE_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> MUSIC_PROFILE_SERVICE_MAP;
    private static final HashMap<Marketplace, String> PROFILE_AVATAR_SERVICE_MAP;
    private static final HashMap<String, String> PROFILE_AVATAR_SERVICE_TERRITORY_MAP;
    private static final HashMap<Marketplace, String> RESOV2_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> SOPRANOS_SERVICE_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> STATION_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> STORE_ENDPOINT_MAP;
    private static final String TAG = "EndpointsProvider";
    private static final HashMap<Marketplace, String> WEBVIEW_ENDPOINT_MAP;
    private static final HashMap<Marketplace, String> WEBVIEW_MUSIC_ENDPOINT_MAP;
    private static final HashMap<String, String> WEBVIEW_ROW_LOCALIZED_ENDPOINT_MAP;
    private static final HashMap<String, String> WEBVIEW_ROW_MUSIC_ENDPOINT_MAP;
    private static final HashMap<String, String> WEBVIEW_ROW_PRIME_ONLY_ENDPOINT_MAP;
    private static volatile EndpointsProvider mEndpoints;
    private EndpointProvider mComponentEndpointProvider = new EndpointProvider();
    private FeedEndpointFactory feedEndpointFactory = new FeedEndpointFactory();

    public String getRowMusicDomain() {
        return "https://music.amazon.com:443/";
    }

    static {
        HashMap<Marketplace, String> map = new HashMap<>();
        CLOUDPLAYER_ENDPOint_MAP = map;
        HashMap<Marketplace, String> map2 = new HashMap<>();
        CLIENTBUDDY_ENDPOINT_MAP = map2;
        HashMap<Marketplace, String> map3 = new HashMap<>();
        WEBVIEW_ENDPOINT_MAP = map3;
        HashMap<Marketplace, String> map4 = new HashMap<>();
        WEBVIEW_MUSIC_ENDPOINT_MAP = map4;
        HashMap<String, String> map5 = new HashMap<>();
        WEBVIEW_ROW_PRIME_ONLY_ENDPOINT_MAP = map5;
        HashMap<String, String> map6 = new HashMap<>();
        WEBVIEW_ROW_LOCALIZED_ENDPOINT_MAP = map6;
        HashMap<String, String> map7 = new HashMap<>();
        WEBVIEW_ROW_MUSIC_ENDPOINT_MAP = map7;
        HashMap<Marketplace, String> map8 = new HashMap<>();
        STORE_ENDPOINT_MAP = map8;
        HashMap<Marketplace, String> map9 = new HashMap<>();
        STATION_ENDPOINT_MAP = map9;
        HashMap<Marketplace, String> map10 = new HashMap<>();
        LYRICS_ENDPOINT_MAP = map10;
        HashMap<Marketplace, String> map11 = new HashMap<>();
        CANTILEVER_ENDPOINT_MAP = map11;
        HashMap<Marketplace, String> map12 = new HashMap<>();
        AVS_ENDPOINT_MAP = map12;
        HashMap<Marketplace, String> map13 = new HashMap<>();
        MPQS_ENDPOINT_MAP = map13;
        HashMap<Marketplace, String> map14 = new HashMap<>();
        MPQS_V2_ENDPOINT_MAP = map14;
        HashMap<String, String> map15 = new HashMap<>();
        DOMAIN_MAP = map15;
        HashMap<String, String> map16 = new HashMap<>();
        DOMAIN_TERRITORY_MAP = map16;
        HashMap<Marketplace, String> map17 = new HashMap<>();
        BMWV_ENDPOINT_MAP = map17;
        HashMap<Marketplace, String> map18 = new HashMap<>();
        ACCOUNT_WEBVIEW_MARKETPLACE_MAP = map18;
        HashMap<String, String> map19 = new HashMap<>();
        ACCOUNT_WEBVIEW_TERRITORY_MAP = map19;
        HashMap<Marketplace, String> map20 = new HashMap<>();
        RESOV2_ENDPOINT_MAP = map20;
        HashMap<Marketplace, String> map21 = new HashMap<>();
        PROFILE_AVATAR_SERVICE_MAP = map21;
        HashMap<String, String> map22 = new HashMap<>();
        PROFILE_AVATAR_SERVICE_TERRITORY_MAP = map22;
        HashMap<Marketplace, String> map23 = new HashMap<>();
        MUSIC_PROFILE_SERVICE_MAP = map23;
        HashMap<Marketplace, String> map24 = new HashMap<>();
        AD_PREFS_ENDPOINT_MAP = map24;
        HashMap<Marketplace, String> map25 = new HashMap<>();
        CUSTOMER_LOOKUP_RESULTS_ENDPOINT_MAP = map25;
        HashMap<Marketplace, String> map26 = new HashMap<>();
        CUSTOMER_LOOKUP_SUGGESTIONS_ENDPOINT_MAP = map26;
        HashMap<Marketplace, String> map27 = new HashMap<>();
        LIBRARY_HELP_ENDPOINT_MAP = map27;
        HashMap<Marketplace, String> map28 = new HashMap<>();
        ALEXA_HINT_SERVICE_ENDPOINT_MAP = map28;
        HashMap<Marketplace, String> map29 = new HashMap<>();
        ALEXA_HELP_ENDPOINT_MAP = map29;
        HashMap<Marketplace, String> map30 = new HashMap<>();
        MUSIC_IDENTITY_SERVICE_ENDPOINT_MAP = map30;
        HashMap<Marketplace, String> map31 = new HashMap<>();
        SOPRANOS_SERVICE_ENDPOINT_MAP = map31;
        EARLY_USE_NOTIFICAION_FTU_ENDPOINT_MAP = new HashMap<>();
        EARLY_USE_NOTIFICAION_FTU_MUSICTERRITORY_ENDPOINT_MAP = new HashMap<>();
        EARLY_USE_NOTIFICAION_ATC_ENDPOINT_MAP = new HashMap<>();
        EARLY_USE_NOTIFICAION_ATC_MUSICTERRITORY_ENDPOINT_MAP = new HashMap<>();
        Marketplace marketplace = Marketplace.USA;
        map15.put(marketplace.getObfuscatedId(), "amazon.com");
        Marketplace marketplace2 = Marketplace.UK;
        map15.put(marketplace2.getObfuscatedId(), "amazon.co.uk");
        Marketplace marketplace3 = Marketplace.GERMANY;
        map15.put(marketplace3.getObfuscatedId(), "amazon.de");
        Marketplace marketplace4 = Marketplace.FRANCE;
        map15.put(marketplace4.getObfuscatedId(), "amazon.fr");
        Marketplace marketplace5 = Marketplace.ITALY;
        map15.put(marketplace5.getObfuscatedId(), "amazon.it");
        Marketplace marketplace6 = Marketplace.SPAIN;
        map15.put(marketplace6.getObfuscatedId(), "amazon.es");
        Marketplace marketplace7 = Marketplace.JAPAN;
        map15.put(marketplace7.getObfuscatedId(), "amazon.co.jp");
        Marketplace marketplace8 = Marketplace.ROW_NA;
        map15.put(marketplace8.getObfuscatedId(), "amazon.com");
        Marketplace marketplace9 = Marketplace.ROE_EU;
        map15.put(marketplace9.getObfuscatedId(), "amazon.com");
        Marketplace marketplace10 = Marketplace.ROW_FE;
        map15.put(marketplace10.getObfuscatedId(), "amazon.com");
        MusicTerritory musicTerritory = MusicTerritory.INDIA;
        map16.put(musicTerritory.value, "amazon.in");
        MusicTerritory musicTerritory2 = MusicTerritory.CANADA;
        map16.put(musicTerritory2.value, "amazon.ca");
        MusicTerritory musicTerritory3 = MusicTerritory.AUSTRALIA;
        map16.put(musicTerritory3.value, "amazon.com.au");
        MusicTerritory musicTerritory4 = MusicTerritory.NEW_ZEALAND;
        map16.put(musicTerritory4.value, "amazon.com.au");
        map16.put(MusicTerritory.US.value, "amazon.com");
        map16.put(MusicTerritory.UK.value, "amazon.co.uk");
        map16.put(MusicTerritory.GERMANY.value, "amazon.de");
        map16.put(MusicTerritory.FRANCE.value, "amazon.fr");
        map16.put(MusicTerritory.ITALY.value, "amazon.it");
        map16.put(MusicTerritory.SPAIN.value, "amazon.es");
        map16.put(MusicTerritory.JAPAN.value, "amazon.co.jp");
        MusicTerritory musicTerritory5 = MusicTerritory.MEXICO;
        map16.put(musicTerritory5.value, "amazon.com.mx");
        MusicTerritory musicTerritory6 = MusicTerritory.BRAZIL;
        map16.put(musicTerritory6.value, "amazon.com.br");
        map.put(marketplace, "https://music.amazon.com:443/NA/api/cirrus/");
        map.put(marketplace2, "https://music.amazon.com:443/EU/api/cirrus/");
        map.put(marketplace3, "https://music.amazon.com:443/EU/api/cirrus/");
        map.put(marketplace4, "https://music.amazon.com:443/EU/api/cirrus/");
        map.put(marketplace5, "https://music.amazon.com:443/EU/api/cirrus/");
        map.put(marketplace6, "https://music.amazon.com:443/EU/api/cirrus/");
        map.put(marketplace7, "https://music.amazon.com:443/FE/api/cirrus");
        map.put(marketplace8, "https://music.amazon.com:443/NA/api/cirrus/");
        map.put(marketplace9, "https://music.amazon.com:443/EU/api/cirrus/");
        map.put(marketplace10, "https://music.amazon.com:443/FE/api/cirrus/");
        map2.put(marketplace, "https://www.amazon.com:443/clientbuddy/");
        map2.put(marketplace2, "https://www.amazon.co.uk:443/clientbuddy/");
        map2.put(marketplace3, "https://www.amazon.de:443/clientbuddy/");
        map2.put(marketplace4, "https://www.amazon.fr:443/clientbuddy/");
        map2.put(marketplace5, "https://www.amazon.it:443/clientbuddy/");
        map2.put(marketplace6, "https://www.amazon.es:443/clientbuddy/");
        map2.put(marketplace7, "https://www.amazon.co.jp:443/clientbuddy/");
        map2.put(marketplace8, "https://www.amazon.com:443/clientbuddy/");
        map2.put(marketplace9, "https://www.amazon.co.uk:443/clientbuddy/");
        map2.put(marketplace10, "https://www.amazon.co.jp:443/clientbuddy/");
        map3.put(marketplace, "https://www.amazon.com:443/");
        map3.put(marketplace2, "https://www.amazon.co.uk:443/");
        map3.put(marketplace3, "https://www.amazon.de:443/");
        map3.put(marketplace4, "https://www.amazon.fr:443/");
        map3.put(marketplace5, "https://www.amazon.it:443/");
        map3.put(marketplace6, "https://www.amazon.es:443/");
        map3.put(marketplace7, "https://www.amazon.co.jp:443/");
        map3.put(marketplace8, "https://music.amazon.com:443/");
        map3.put(marketplace9, "https://music.amazon.com:443/");
        map3.put(marketplace10, "https://music.amazon.com:443/");
        map4.put(marketplace, "https://music.amazon.com:443/");
        map4.put(marketplace2, "https://music.amazon.co.uk:443/");
        map4.put(marketplace3, "https://music.amazon.de:443/");
        map4.put(marketplace4, "https://music.amazon.fr:443/");
        map4.put(marketplace5, "https://music.amazon.it:443/");
        map4.put(marketplace6, "https://music.amazon.es:443/");
        map4.put(marketplace7, "https://music.amazon.co.jp:443/");
        map4.put(marketplace8, "https://music.amazon.com:443/");
        map4.put(marketplace9, "https://music.amazon.com:443/");
        map4.put(marketplace10, "https://music.amazon.com:443/");
        map5.put(musicTerritory2.value, "https://www.amazon.ca:443/");
        map5.put(musicTerritory.value, "https://www.amazon.in:443/");
        map5.put(musicTerritory5.value, "https://music.amazon.com.mx:443/");
        map5.put(musicTerritory3.value, "https://music.amazon.com.au:443/");
        map5.put(musicTerritory4.value, "https://music.amazon.com.au:443/");
        map5.put(musicTerritory6.value, "https://music.amazon.com.br:443/");
        map7.put(musicTerritory2.value, "https://music.amazon.ca:443/");
        map7.put(musicTerritory.value, "https://music.amazon.in:443/");
        map7.put(musicTerritory3.value, "https://music.amazon.com.au:443/");
        map7.put(musicTerritory4.value, "https://music.amazon.com.au:443/");
        map7.put(musicTerritory5.value, "https://music.amazon.com.mx:443/");
        map7.put(musicTerritory6.value, "https://music.amazon.com.br:443/");
        map6.put(musicTerritory3.value, "https://www.amazon.com.au:443/");
        map6.put(musicTerritory4.value, "https://www.amazon.com.au:443/");
        map6.put(musicTerritory2.value, "https://www.amazon.ca:443/");
        map6.put(musicTerritory5.value, "https://www.amazon.com.mx:443/");
        map6.put(musicTerritory6.value, "https://www.amazon.com.br:443/");
        map8.put(marketplace, "https://www.amazon.com:443/");
        map8.put(marketplace2, "https://www.amazon.co.uk:443/");
        map8.put(marketplace3, "https://www.amazon.de:443/");
        map8.put(marketplace4, "https://www.amazon.fr:443/");
        map8.put(marketplace5, "https://www.amazon.it:443/");
        map8.put(marketplace6, "https://www.amazon.es:443/");
        map8.put(marketplace7, "https://www.amazon.co.jp:443/");
        map8.put(marketplace8, "https://www.amazon.com:443/");
        map8.put(marketplace9, "https://www.amazon.co.uk:443/");
        map8.put(marketplace10, "https://www.amazon.co.jp:443/");
        map9.put(marketplace, "https://music.amazon.com:443/NA/api/mpqs/nonvoiceenabled/");
        map9.put(marketplace2, "https://music.amazon.com:443/EU/api/mpqs/nonvoiceenabled/");
        map9.put(marketplace3, "https://music.amazon.com:443/EU/api/mpqs/nonvoiceenabled/");
        map9.put(marketplace4, "https://music.amazon.com:443/EU/api/mpqs/nonvoiceenabled/");
        map9.put(marketplace5, "https://music.amazon.com:443/EU/api/mpqs/nonvoiceenabled/");
        map9.put(marketplace6, "https://music.amazon.com:443/EU/api/mpqs/nonvoiceenabled/");
        map9.put(marketplace7, "https://music.amazon.com:443/FE/api/mpqs/nonvoiceenabled/");
        map9.put(marketplace8, "https://music.amazon.com:443/NA/api/mpqs/nonvoiceenabled/");
        map9.put(marketplace9, "https://music.amazon.com:443/EU/api/mpqs/nonvoiceenabled/");
        map9.put(marketplace10, "https://music.amazon.com:443/FE/api/mpqs/nonvoiceenabled/");
        map10.put(marketplace, "music-xray-service.amazon.com");
        map10.put(marketplace2, "music-xray-service.amazon.eu");
        map10.put(marketplace3, "music-xray-service.amazon.eu");
        map10.put(marketplace4, "music-xray-service.amazon.eu");
        map10.put(marketplace5, "music-xray-service.amazon.eu");
        map10.put(marketplace6, "music-xray-service.amazon.eu");
        map10.put(marketplace7, "music-xray-service.amazon.co.jp");
        map10.put(marketplace8, "music-xray-service.amazon.com");
        map10.put(marketplace9, "music-xray-service.amazon.eu");
        map10.put(marketplace10, "music-xray-service.amazon.co.jp");
        map11.put(marketplace, "https://digprjsurvey.amazon.com/");
        map11.put(marketplace2, "https://digprjsurvey.amazon.co.uk/");
        map11.put(marketplace3, "https://digprjsurvey.amazon.co.uk/");
        map11.put(marketplace4, "https://digprjsurvey.amazon.co.uk/");
        map11.put(marketplace5, "https://digprjsurvey.amazon.co.uk/");
        map11.put(marketplace6, "https://digprjsurvey.amazon.co.uk/");
        map11.put(marketplace7, "https://digprjsurvey.amazon.co.jp/");
        map11.put(marketplace8, "https://digprjsurvey.amazon.com/");
        map11.put(marketplace9, "https://digprjsurvey.amazon.co.uk/");
        map11.put(marketplace10, "https://digprjsurvey.amazon.co.jp/");
        map13.put(marketplace, "https://music.amazon.com/NA/api/mpqs/voiceenabled/");
        map13.put(marketplace2, "https://music.amazon.com/EU/api/mpqs/voiceenabled/");
        map13.put(marketplace3, "https://music.amazon.com/EU/api/mpqs/voiceenabled/");
        map13.put(marketplace7, "https://music.amazon.com/FE/api/mpqs/voiceenabled/");
        map13.put(marketplace4, "https://music.amazon.com/EU/api/mpqs/voiceenabled/");
        map13.put(marketplace5, "https://music.amazon.com/EU/api/mpqs/voiceenabled/");
        map13.put(marketplace6, "https://music.amazon.com/EU/api/mpqs/voiceenabled/");
        map13.put(marketplace8, "https://music.amazon.com/NA/api/mpqs/voiceenabled/");
        map13.put(marketplace9, "https://music.amazon.com/EU/api/mpqs/voiceenabled/");
        map13.put(marketplace10, "https://music.amazon.com/FE/api/mpqs/voiceenabled/");
        map14.put(marketplace, "https://music.amazon.com/NA/api/mpqs/v2/voiceenabled/");
        map14.put(marketplace2, "https://music.amazon.com/EU/api/mpqs/v2/voiceenabled/");
        map14.put(marketplace3, "https://music.amazon.com/EU/api/mpqs/v2/voiceenabled/");
        map14.put(marketplace7, "https://music.amazon.com/FE/api/mpqs/v2/voiceenabled/");
        map14.put(marketplace4, "https://music.amazon.com/EU/api/mpqs/v2/voiceenabled/");
        map14.put(marketplace5, "https://music.amazon.com/EU/api/mpqs/v2/voiceenabled/");
        map14.put(marketplace6, "https://music.amazon.com/EU/api/mpqs/v2/voiceenabled/");
        map14.put(marketplace8, "https://music.amazon.com/NA/api/mpqs/v2/voiceenabled/");
        map14.put(marketplace9, "https://music.amazon.com/EU/api/mpqs/v2/voiceenabled/");
        map14.put(marketplace10, "https://music.amazon.com/FE/api/mpqs/v2/voiceenabled/");
        map12.put(marketplace, "https://avs.na.amazonalexa.com");
        map12.put(marketplace2, "https://avs.eu.amazonalexa.com");
        map12.put(marketplace3, "https://avs.eu.amazonalexa.com");
        map12.put(marketplace4, "https://avs.eu.amazonalexa.com");
        map12.put(marketplace5, "https://avs.eu.amazonalexa.com");
        map12.put(marketplace6, "https://avs.eu.amazonalexa.com");
        map12.put(marketplace7, "https://avs.fe.amazonalexa.com");
        map12.put(marketplace8, "https://avs.na.amazonalexa.com");
        map12.put(marketplace9, "https://avs.eu.amazonalexa.com");
        map12.put(marketplace10, "https://avs.fe.amazonalexa.com");
        map17.put(marketplace, "https://www.amazon.com:443/");
        map17.put(marketplace2, "https://www.amazon.co.uk:443/");
        map17.put(marketplace3, "https://www.amazon.de:443/");
        map17.put(marketplace4, "https://www.amazon.fr:443/");
        map17.put(marketplace5, "https://www.amazon.it:443/");
        map17.put(marketplace6, "https://www.amazon.es:443/");
        map17.put(marketplace7, "https://www.amazon.co.jp:443/");
        map17.put(marketplace8, "https://music.amazon.com:443/NA/BMWV/");
        map17.put(marketplace9, "https://music.amazon.com:443/EU/BMWV/");
        map17.put(marketplace10, "https://music.amazon.com:443/FE/BMWV/");
        map18.put(marketplace, "https://music.amazon.com/account/manage");
        map18.put(marketplace2, "https://music.amazon.co.uk/account/manage");
        map18.put(marketplace3, "https://music.amazon.de/account/manage");
        map18.put(marketplace4, "https://music.amazon.fr/account/manage");
        map18.put(marketplace5, "https://music.amazon.it/account/manage");
        map18.put(marketplace6, "https://music.amazon.es/account/manage");
        map18.put(marketplace7, "https://music.amazon.co.jp/account/manage");
        map18.put(marketplace8, "https://music.amazon.com/NA/account/manage");
        map18.put(marketplace9, "https://music.amazon.com/EU/account/manage");
        map18.put(marketplace10, "https://music.amazon.com/FE/account/manage");
        map19.put(musicTerritory.value, "https://music.amazon.in/account/manage");
        map19.put(musicTerritory2.value, "https://music.amazon.ca/account/manage");
        map19.put(musicTerritory3.value, "https://music.amazon.com.au/account/manage");
        map19.put(musicTerritory4.value, "https://music.amazon.com.au/account/manage");
        map19.put(musicTerritory5.value, "https://music.amazon.com.mx/account/manage");
        map19.put(musicTerritory6.value, "https://music.amazon.com.br/account/manage");
        map20.put(marketplace, "https://music.amazon.com/NA/api/msos/");
        map20.put(marketplace2, "https://music.amazon.com/EU/api/msos/");
        map20.put(marketplace4, "https://music.amazon.com/EU/api/msos/");
        map20.put(marketplace5, "https://music.amazon.com/EU/api/msos/");
        map20.put(marketplace6, "https://music.amazon.com/EU/api/msos/");
        map20.put(marketplace3, "https://music.amazon.com/EU/api/msos/");
        map20.put(marketplace7, "https://music.amazon.com/FE/api/msos/");
        map20.put(marketplace8, "https://music.amazon.com/NA/api/msos/");
        map20.put(marketplace10, "https://music.amazon.com/FE/api/msos/");
        map21.put(marketplace, "https://www.amazon.com/avatar/default/");
        map21.put(marketplace2, "https://www.amazon.co.uk/avatar/default/");
        map21.put(marketplace3, "https://www.amazon.de/avatar/default/");
        map21.put(marketplace4, "https://www.amazon.fr/avatar/default/");
        map21.put(marketplace5, "https://www.amazon.it/avatar/default/");
        map21.put(marketplace6, "https://www.amazon.es/avatar/default/");
        map21.put(marketplace7, "https://www.amazon.co.jp/avatar/default/");
        map21.put(marketplace8, "https://www.amazon.com/avatar/default/");
        map21.put(marketplace9, "https://www.amazon.co.uk/avatar/default/");
        map21.put(marketplace10, "https://www.amazon.com.au/avatar/default/");
        map22.put(musicTerritory.value, "https://www.amazon.in/avatar/default");
        map22.put(musicTerritory2.value, "https://www.amazon.ca/avatar/default");
        map22.put(musicTerritory3.value, "https://www.amazon.com.au/avatar/default");
        map22.put(musicTerritory4.value, "https://www.amazon.com.au/avatar/default");
        map22.put(musicTerritory5.value, "https://www.amazon.com.mx/avatar/default");
        map22.put(musicTerritory6.value, "https://www.amazon.com.br/avatar/default");
        map23.put(marketplace, "https://music.amazon.com/NA/api/mps/");
        map23.put(marketplace2, "https://music.amazon.com/EU/api/mps/");
        map23.put(marketplace3, "https://music.amazon.de/EU/api/mps/");
        map23.put(marketplace4, "https://music.amazon.fr/EU/api/mps/");
        map23.put(marketplace5, "https://music.amazon.it/EU/api/mps/");
        map23.put(marketplace6, "https://music.amazon.es/EU/api/mps/");
        map23.put(marketplace7, "https://music.amazon.co.jp/FE/api/mps/");
        map23.put(marketplace8, "https://music.amazon.com/NA/api/mps/");
        map23.put(marketplace9, "https://music.amazon.com/EU/api/mps/");
        map23.put(marketplace10, "https://music.amazon.com/FE/api/mps/");
        map24.put(marketplace, "amazon.com/adprefs");
        map24.put(marketplace2, "amazon.co.uk/adprefs");
        map24.put(marketplace3, "amazon.de/werbeeinstellungen");
        map24.put(marketplace4, "amazon.fr/PreferencesPublicites");
        map24.put(marketplace5, "amazon.it/anpref");
        map24.put(marketplace6, "amazon.es/PreferenciasdePublicidad");
        map25.put(marketplace, "https://am4mso2bjk.execute-api.us-west-2.amazonaws.com/live/results");
        map26.put(marketplace, "https://am4mso2bjk.execute-api.us-west-2.amazonaws.com/live/suggestions");
        map28.put(marketplace, "https://api.amazonalexa.com");
        map28.put(marketplace2, "https://api.eu.amazonalexa.com");
        map28.put(marketplace3, "https://api.eu.amazonalexa.com");
        map28.put(marketplace4, "https://api.eu.amazonalexa.com");
        map28.put(marketplace7, "https://api.fe.amazonalexa.com");
        map28.put(marketplace6, "https://api.eu.amazonalexa.com");
        map28.put(marketplace5, "https://api.eu.amazonalexa.com");
        map28.put(marketplace8, "https://api.amazonalexa.com");
        map28.put(marketplace10, "https://api.fe.amazonalexa.com");
        map28.put(marketplace9, "https://api.eu.amazonalexa.com");
        Marketplace marketplace11 = Marketplace.NONE;
        map28.put(marketplace11, "https://api.amazonalexa.com");
        map27.put(marketplace, "https://www.amazon.com/gp/help/customer/display.html?ie=UTF8&nodeId=201650670&pop-up=1");
        map27.put(marketplace2, "https://www.amazon.co.uk/gp/help/customer/display.html?ie=UTF8&nodeId=201650670&pop-up=1");
        map27.put(marketplace3, "https://www.amazon.de/gp/help/customer/display.html?ie=UTF8&nodeId=201650670&pop-up=1");
        map27.put(marketplace4, "https://www.amazon.fr/gp/help/customer/display.html?ie=UTF8&nodeId=201650670&pop-up=1");
        map27.put(marketplace7, "https://www.amazon.co.jp/gp/help/customer/display.html?ie=UTF8&nodeId=201650670&pop-up=1");
        map27.put(marketplace6, "https://www.amazon.es/gp/help/customer/display.html?ie=UTF8&nodeId=201650670&pop-up=1");
        map27.put(marketplace5, "https://www.amazon.it/gp/help/customer/display.html?ie=UTF8&nodeId=201650670&pop-up=1");
        map27.put(marketplace11, "https://www.amazon.com/gp/help/customer/display.html?ie=UTF8&nodeId=201650670&pop-up=1");
        map29.put(marketplace7, "https://www.amazon.co.jp/b?ie=UTF8&node=5503743051");
        map30.put(marketplace, "https://music.amazon.com:443/NA/api/mis/");
        map30.put(marketplace2, "https://music.amazon.com:443/EU/api/mis/");
        map30.put(marketplace9, "https://music.amazon.com:443/EU/api/mis/");
        map30.put(marketplace10, "https://music.amazon.com:443/FE/api/mis/");
        map30.put(marketplace8, "https://music.amazon.com:443/NA/api/mis/");
        map31.put(marketplace, "https://music.amazon.com:443/NA/api/soprano/");
        map31.put(marketplace2, "https://music.amazon.com:443/EU/api/soprano/");
        map31.put(marketplace9, "https://music.amazon.com:443/EU/api/soprano/");
        map31.put(marketplace10, "https://music.amazon.com:443/FE/api/soprano/");
        map31.put(marketplace8, "https://music.amazon.com:443/NA/api/soprano/");
    }

    public static EndpointsProvider get() {
        if (mEndpoints == null) {
            mEndpoints = new EndpointsProvider();
        }
        return mEndpoints;
    }

    private EndpointsProvider() {
    }

    public List<String> getSupportedDomains() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(DOMAIN_TERRITORY_MAP.values());
        hashSet.addAll(DOMAIN_MAP.values());
        ArrayList arrayList = new ArrayList(hashSet.size());
        arrayList.addAll(hashSet);
        return arrayList;
    }

    public String getDomain() {
        String domainForMusicTerritory = getDomainForMusicTerritory(AccountDetailUtil.getMusicTerritoryOfResidence());
        if (domainForMusicTerritory != null) {
            return domainForMusicTerritory;
        }
        return DOMAIN_MAP.get(AccountDetailUtil.get().getHomeMarketPlace().getObfuscatedId());
    }

    public String getDomainForMusicTerritory(final String musicTerritory) {
        return DOMAIN_TERRITORY_MAP.get(musicTerritory);
    }

    public String getDefaultDomain() {
        return DOMAIN_MAP.get(Marketplace.USA.getObfuscatedId());
    }

    public String getMusicDomain() {
        String domain = getDomain();
        if (domain == null) {
            domain = getDefaultDomain();
        }
        return ("music." + domain).toLowerCase();
    }

    public URL getCirrusEndpoint() throws Exception {
        return this.mComponentEndpointProvider.getCirrusEndpoint(new EndpointURLRequest.Builder(AccountDetailUtil.get().getHomeMarketPlace()).build());
    }

    public URL getPlaylistEndpoint() throws Exception {
        return this.mComponentEndpointProvider.getPlaylistEndpoint(new EndpointURLRequest.Builder(AccountDetailUtil.get().getHomeMarketPlace()).build());
    }

    public URL getMuseEndpoint() throws Exception {
        return this.mComponentEndpointProvider.getMuseEndpoint(new EndpointURLRequest.Builder(AccountDetailUtil.get().getHomeMarketPlace()).build());
    }

    public URL getMuseBrushEndpoint() throws Exception {
        return this.mComponentEndpointProvider.getMuseBrushEndpoint(new EndpointURLRequest.Builder(AccountDetailUtil.get().getHomeMarketPlace()).build());
    }

    public URL getStratusEndpoint() throws Exception {
        return this.mComponentEndpointProvider.getStratusEndpoint(new EndpointURLRequest.Builder(AccountDetailUtil.get().getHomeMarketPlace()).build());
    }

    public URL getMusicIdentityServiceEndpoint() throws Exception {
        return new URL(MUSIC_IDENTITY_SERVICE_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace()));
    }

    public URL getSopranosServiceEndpoint() throws Exception {
        return new URL(SOPRANOS_SERVICE_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace()));
    }

    public URL getDefaultStratusEndpoint() throws Exception {
        return this.mComponentEndpointProvider.getStratusEndpoint(new EndpointURLRequest.Builder(Marketplace.USA).build());
    }

    public URL getDMLSEndpoint() throws Exception {
        return this.mComponentEndpointProvider.getDMLSEndpoint(new EndpointURLRequest.Builder(AccountDetailUtil.get().getHomeMarketPlace()).build());
    }

    public URL getTenzingEndpoint() throws Exception {
        return this.mComponentEndpointProvider.getTenzingTextSearchEndpoint(new EndpointURLRequest.Builder(AccountDetailUtil.get().getHomeMarketPlace()).build());
    }

    public URL getTenzingSuggestionEndpoint() throws Exception {
        return this.mComponentEndpointProvider.getTenzingSuggestEndpoint(new EndpointURLRequest.Builder(AccountDetailUtil.get().getHomeMarketPlace()).build());
    }

    public URL getTenzingInstantSearchEndpoint() throws Exception {
        return this.mComponentEndpointProvider.getTenzingInstantSearchEndpoint(new EndpointURLRequest.Builder(AccountDetailUtil.get().getHomeMarketPlace()).build());
    }

    public URL getNautilusEndpoint() throws Exception {
        return this.mComponentEndpointProvider.getNautilusEndpoint(new EndpointURLRequest.Builder(AccountDetailUtil.get().getHomeMarketPlace()).build());
    }

    public URL getIAMEndpointUrl() throws Exception {
        return this.mComponentEndpointProvider.getIAMEndpoint(new EndpointURLRequest.Builder(AccountDetailUtil.get().getHomeMarketPlace()).build());
    }

    public URL getCloudplayerEndpoint() throws Exception {
        return new URL(CLOUDPLAYER_ENDPOint_MAP.get(AccountDetailUtil.get().getHomeMarketPlace()));
    }

    public URL getClientbuddyEndpoint() throws MalformedURLException {
        return new URL(CLIENTBUDDY_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace()));
    }

    public URL getRESOV2Endpoint() throws UnsupportedMarketplaceException, MalformedURLException {
        Marketplace homeMarketPlace = AccountDetailUtil.get().getHomeMarketPlace();
        String str = RESOV2_ENDPOINT_MAP.get(homeMarketPlace);
        if (str == null) {
            throw new UnsupportedMarketplaceException(String.format("Marketplace: %s not supported.", homeMarketPlace.getId()));
        }
        return new URL(str);
    }

    public URL getKatanaLearnMoreEndpoint() throws UnsupportedMarketplaceException, MalformedURLException {
        URL value = ConfigurableURLs.MUSIC_HD_LEARN_MORE_URL.getValue();
        if (value != null) {
            return value;
        }
        throw new UnsupportedMarketplaceException("Current marketplace/music territory has no URL endpoint");
    }

    public URL getIvyLearnMoreEndpoint() throws UnsupportedMarketplaceException {
        URL value = ConfigurableURLs.IVY_LEARN_MORE_URL.getValue();
        if (value != null) {
            return value;
        }
        throw new UnsupportedMarketplaceException("Current marketplace/music territory has no URL endpoint");
    }

    public URL getConfigurableURLEndPoint(ConfigurableURLs urlEnum) throws UnsupportedMarketplaceException {
        URL value = urlEnum.getValue();
        Marketplace homeMarketPlace = AccountDetailUtil.get().getHomeMarketPlace();
        if (value != null) {
            return value;
        }
        throw new UnsupportedMarketplaceException(String.format("Marketplace: %s not supported for %s url.", homeMarketPlace.getId(), urlEnum.name()));
    }

    public URL getWebviewEndpoint() throws MalformedURLException {
        String webviewRowPrimeOnlyEndpoint = getWebviewRowPrimeOnlyEndpoint();
        if (webviewRowPrimeOnlyEndpoint == null) {
            webviewRowPrimeOnlyEndpoint = getWebviewRowLocalizedEndpoint();
        }
        if (webviewRowPrimeOnlyEndpoint == null) {
            webviewRowPrimeOnlyEndpoint = WEBVIEW_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace());
        }
        try {
            return new URL(webviewRowPrimeOnlyEndpoint);
        } catch (MalformedURLException e) {
            Log.error(TAG, "The url passed to java.net.URL is invalid. Url was: %s ", webviewRowPrimeOnlyEndpoint);
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getWebviewRowPrimeOnlyEndpoint() {
        return WEBVIEW_ROW_PRIME_ONLY_ENDPOINT_MAP.get(AccountDetailUtil.getMusicTerritoryOfResidence());
    }

    public String getWebviewRowLocalizedEndpoint() {
        return WEBVIEW_ROW_LOCALIZED_ENDPOINT_MAP.get(AccountDetailUtil.getMusicTerritoryOfResidence());
    }

    public String getApexRowMusicEndpoint() {
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        HashMap<String, String> map = WEBVIEW_ROW_MUSIC_ENDPOINT_MAP;
        return map.containsKey(musicTerritoryOfResidence) ? map.get(musicTerritoryOfResidence) : "https://music.amazon.com:443/";
    }

    public String getMusicEndpoint() {
        Marketplace homeMarketPlace = AccountDetailUtil.get().getHomeMarketPlace();
        HashMap<Marketplace, String> map = WEBVIEW_MUSIC_ENDPOINT_MAP;
        return map.containsKey(homeMarketPlace) ? map.get(homeMarketPlace) : "https://music.amazon.com:443/";
    }

    public URL getStoreEndpoint() throws MalformedURLException {
        return new URL(STORE_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace()));
    }

    public URL getStationEndpoint() throws MalformedURLException {
        return new URL(STATION_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace()));
    }

    public String getLyricsEndpoint() {
        return LYRICS_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace());
    }

    public String getCantileverEndpoint() {
        return CANTILEVER_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace());
    }

    public URL getMusicPlayqueueServiceUrl() throws MalformedURLException {
        return new URL(MPQS_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace()));
    }

    public URL getMusicPlayqueueServiceV2Url() throws MalformedURLException {
        return new URL(MPQS_V2_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace()));
    }

    public URL getAlexaVoiceServiceUrl() throws MalformedURLException {
        return new URL(AVS_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace()));
    }

    public URL getBMWVEndpoint() throws MalformedURLException {
        return new URL(BMWV_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace()));
    }

    public String getAccountWebViewEndpoint() {
        String str = ACCOUNT_WEBVIEW_TERRITORY_MAP.get(AccountDetailUtil.getMusicTerritoryOfResidence());
        if (str != null) {
            return str;
        }
        return ACCOUNT_WEBVIEW_MARKETPLACE_MAP.get(AccountDetailUtil.get().getHomeMarketPlace());
    }

    public String getProfileAvatarServiceEndpoint() {
        String str = PROFILE_AVATAR_SERVICE_TERRITORY_MAP.get(AccountDetailUtil.getMusicTerritoryOfResidence());
        if (str != null) {
            return str;
        }
        return PROFILE_AVATAR_SERVICE_MAP.get(AccountDetailUtil.get().getHomeMarketPlace());
    }

    public String getActivityFeedApiEndpoint() {
        return this.feedEndpointFactory.getCurrentEndpoint(AccountDetailUtil.get().getHomeMarketPlace());
    }

    public String getCustomerLookupResultsApiEndpoint() {
        return CUSTOMER_LOOKUP_RESULTS_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace());
    }

    public String getCustomerLookupSuggestionsApiEndpoint() {
        return CUSTOMER_LOOKUP_SUGGESTIONS_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace());
    }

    public String getCloudQueueAuthApiEndpoint() {
        Configuration configuration = (Configuration) Providers.INSTANCE.get(ConfigurationProvider.class);
        String strString = configuration != null ? configuration.string("cloud_queue_auth_url") : null;
        if (strString == null) {
            Log.error(TAG, "config cloud_queue_auth_url not found");
            strString = "https://iad.prod.auth.cloud-queue.music.amazon.dev";
        }
        String currentOverrideId = Environment.CLOUD_QUEUE_AUTH.getCurrentOverrideId();
        return CloudQueueAuthEndpointURLFactory.STAGE.GAMMA.getValue().equals(currentOverrideId) ? strString.replace(CloudQueueAuthEndpointURLFactory.STAGE.PROD.getValue(), currentOverrideId) : strString;
    }

    public String getLibraryHelpEndPoint() {
        return LIBRARY_HELP_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace());
    }

    public String getAlexaHintServiceEndpoint() {
        return ALEXA_HINT_SERVICE_ENDPOINT_MAP.get(AccountDetailUtil.get().getHomeMarketPlace());
    }

    public String getEarlyUseNotificationFTUDeeplinkEndpoint() {
        return getMusicDomain() + "/tastes?ref=dm_early_use_ftu_push";
    }

    public String getEarlyUseNotificationATCDeeplinkEndpoint() {
        return getMusicDomain() + "?ref=dm_early_use_atc_push";
    }

    public String getPlaylistImportEndpoint() {
        Configuration configuration = (Configuration) Providers.INSTANCE.get(ConfigurationProvider.class);
        return configuration == null ? "https://www.amazon.com/gp/help/customer/display.html?nodeId=GSC84ZE725QPVRBU&view-type=content-only" : configuration.string("playlist_import_endpoint_url");
    }
}
