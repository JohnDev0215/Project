package com.amazon.music.push.sonarpush;

import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.marketplace.MusicTerritory;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public class SonarEndpointsProvider {
    private static final HashMap<String, String> DOMAIN_TERRITORY_MAP;
    private static final HashMap<String, String> ENDPOINT_MAP;
    private static final HashMap<String, String> ROW_ID_REALM_MAP;
    private static final HashMap<String, String> ROW_MARKETPLACE_ID_MAP;
    private static volatile SonarEndpointsProvider mEndpoints;

    static {
        HashMap<String, String> map = new HashMap<>();
        ENDPOINT_MAP = map;
        HashMap<String, String> map2 = new HashMap<>();
        ROW_MARKETPLACE_ID_MAP = map2;
        HashMap<String, String> map3 = new HashMap<>();
        DOMAIN_TERRITORY_MAP = map3;
        HashMap<String, String> map4 = new HashMap<>();
        ROW_ID_REALM_MAP = map4;
        map.put(Marketplace.USA.getObfuscatedId(), "amazon.com");
        map.put(Marketplace.UK.getObfuscatedId(), "amazon.co.uk");
        map.put(Marketplace.GERMANY.getObfuscatedId(), "amazon.de");
        map.put(Marketplace.FRANCE.getObfuscatedId(), "amazon.fr");
        map.put(Marketplace.ITALY.getObfuscatedId(), "amazon.it");
        map.put(Marketplace.SPAIN.getObfuscatedId(), "amazon.es");
        map.put(Marketplace.JAPAN.getObfuscatedId(), "amazon.co.jp");
        map.put(Marketplace.ROW_NA.getObfuscatedId(), "amazon.com");
        map.put(Marketplace.ROE_EU.getObfuscatedId(), "amazon.co.uk");
        map.put(Marketplace.ROW_FE.getObfuscatedId(), "amazon.co.jp");
        MusicTerritory musicTerritory = MusicTerritory.INDIA;
        map3.put(musicTerritory.value, "amazon.in");
        MusicTerritory musicTerritory2 = MusicTerritory.CANADA;
        map3.put(musicTerritory2.value, "amazon.ca");
        MusicTerritory musicTerritory3 = MusicTerritory.AUSTRALIA;
        map3.put(musicTerritory3.value, "amazon.co.jp");
        MusicTerritory musicTerritory4 = MusicTerritory.NEW_ZEALAND;
        map3.put(musicTerritory4.value, "amazon.co.jp");
        map3.put(MusicTerritory.US.value, "amazon.com");
        map3.put(MusicTerritory.UK.value, "amazon.co.uk");
        map3.put(MusicTerritory.GERMANY.value, "amazon.de");
        map3.put(MusicTerritory.FRANCE.value, "amazon.fr");
        map3.put(MusicTerritory.ITALY.value, "amazon.it");
        map3.put(MusicTerritory.SPAIN.value, "amazon.es");
        map3.put(MusicTerritory.JAPAN.value, "amazon.co.jp");
        MusicTerritory musicTerritory5 = MusicTerritory.MEXICO;
        map3.put(musicTerritory5.value, "amazon.com.mx");
        MusicTerritory musicTerritory6 = MusicTerritory.BRAZIL;
        map3.put(musicTerritory6.value, "amazon.com.br");
        map2.put(musicTerritory3.value, "A39IBJ37TRP1C6");
        map2.put(musicTerritory6.value, "A2Q3Y263D00KWC");
        map2.put(musicTerritory2.value, "A2EUQ1WTGCTBG2");
        map2.put(musicTerritory.value, "A21TJRUUN4KGV");
        map2.put(musicTerritory5.value, "A1AM78C64UM0Y8");
        map2.put(musicTerritory4.value, "A39IBJ37TRP1C6");
        map4.put("A2EUQ1WTGCTBG2", "NA");
        map4.put("A1AM78C64UM0Y8", "NA");
        map4.put("A2Q3Y263D00KWC", "NA");
        map4.put("A21TJRUUN4KGV", "EU");
        map4.put("A39IBJ37TRP1C6", "FE");
    }

    public static SonarEndpointsProvider get() {
        if (mEndpoints == null) {
            mEndpoints = new SonarEndpointsProvider();
        }
        return mEndpoints;
    }

    public String getDomain() {
        String domainForMusicTerritory = getDomainForMusicTerritory(AccountManagerSingleton.get().getMusicTerritory());
        return domainForMusicTerritory != null ? domainForMusicTerritory : ENDPOINT_MAP.get(NotificationUtil.getMarketplaceId());
    }

    public String getDomainForMusicTerritory(String str) {
        return DOMAIN_TERRITORY_MAP.get(str);
    }

    public String getROWMarketPlaceId(String str) {
        return ROW_MARKETPLACE_ID_MAP.get(str);
    }

    public boolean isROWTerritory(String str) {
        return ROW_MARKETPLACE_ID_MAP.containsKey(str);
    }

    public boolean isROWMarketplace(String str) {
        return ROW_ID_REALM_MAP.containsKey(str);
    }

    public String getRealmForROWMarketplace(String str) {
        return ROW_ID_REALM_MAP.get(str);
    }
}
