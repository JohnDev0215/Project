package com.amazon.music.curate.skyfire;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateEndpoints {
    private static Map<String, String> euEndpoints;
    private static Map<String, String> feEndpoints;
    private static final Map<String, Realm> marketplaceToRealmMap;
    private static Map<String, String> naEndpoints;

    public enum Realm {
        NA,
        EU,
        FE
    }

    public enum Stage {
        LOCAL,
        DEV,
        QA,
        PROD
    }

    public enum Marketplaces {
        US("ATVPDKIKX0DER"),
        UK("A1F83G8C2ARO7P"),
        DE("A1PA6795UKMFR9"),
        FR("A13V1IB3VIYZZH"),
        JP("A1VC38T7YXB528"),
        ES("A1RKKUPIHCS9HS"),
        IT("APJ6JRA9NG5V4"),
        ROW_NA("ART4WZ8MWBX2Y"),
        ROW_EU("A3K6Y4MI8GDYMT"),
        ROW_FE("A15PK738MTQHSO");

        private final String maketplaceId;

        Marketplaces(final String id) {
            this.maketplaceId = id;
        }
    }

    static {
        HashMap map = new HashMap();
        marketplaceToRealmMap = map;
        String str = Marketplaces.US.maketplaceId;
        Realm realm = Realm.NA;
        map.put(str, realm);
        map.put(Marketplaces.ROW_NA.maketplaceId, realm);
        String str2 = Marketplaces.UK.maketplaceId;
        Realm realm2 = Realm.EU;
        map.put(str2, realm2);
        map.put(Marketplaces.DE.maketplaceId, realm2);
        map.put(Marketplaces.FR.maketplaceId, realm2);
        map.put(Marketplaces.IT.maketplaceId, realm2);
        map.put(Marketplaces.ES.maketplaceId, realm2);
        map.put(Marketplaces.ROW_EU.maketplaceId, realm2);
        String str3 = Marketplaces.JP.maketplaceId;
        Realm realm3 = Realm.FE;
        map.put(str3, realm3);
        map.put(Marketplaces.ROW_FE.maketplaceId, realm3);
        naEndpoints = new HashMap();
        euEndpoints = new HashMap();
        feEndpoints = new HashMap();
        Map<String, String> map2 = naEndpoints;
        Stage stage = Stage.LOCAL;
        map2.put(stage.name(), "http://10.0.2.2:8080/api");
        Map<String, String> map3 = naEndpoints;
        Stage stage2 = Stage.PROD;
        map3.put(stage2.name(), "https://na.curate.skill.music.a2z.com/api");
        Map<String, String> map4 = naEndpoints;
        Stage stage3 = Stage.DEV;
        map4.put(stage3.name(), "https://dev-na.curate.skill.music.a2z.com/api");
        Map<String, String> map5 = naEndpoints;
        Stage stage4 = Stage.QA;
        map5.put(stage4.name(), "https://qa-na.curate.skill.music.a2z.com/api");
        euEndpoints.put(stage.name(), "http://10.0.2.2:8080/api");
        euEndpoints.put(stage2.name(), "https://eu.curate.skill.music.a2z.com/api");
        euEndpoints.put(stage3.name(), "https://dev-eu.curate.skill.music.a2z.com/api");
        euEndpoints.put(stage4.name(), "https://qa-eu.curate.skill.music.a2z.com/api");
        feEndpoints.put(stage.name(), "http://10.0.2.2:8080/api");
        feEndpoints.put(stage2.name(), "https://fe.curate.skill.music.a2z.com/api");
        feEndpoints.put(stage3.name(), "https://dev-fe.curate.skill.music.a2z.com/api");
        feEndpoints.put(stage4.name(), "https://qa-fe.curate.skill.music.a2z.com/api");
    }

    public static String url(final String obfuscatedMarketplaceId, final String stage) {
        String str;
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$curate$skyfire$CurateEndpoints$Realm[getRealmByObfuscatedMarketplaceId(obfuscatedMarketplaceId).ordinal()];
        if (i == 1) {
            str = naEndpoints.get(stage);
        } else if (i == 2) {
            str = euEndpoints.get(stage);
        } else {
            str = i != 3 ? null : feEndpoints.get(stage);
        }
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("Not supported Stage ${stage.name} or Realm ${realm.name} for ${obfuscatedMarketplaceId}");
    }

    /* JADX INFO: renamed from: com.amazon.music.curate.skyfire.CurateEndpoints$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$curate$skyfire$CurateEndpoints$Realm;

        static {
            int[] iArr = new int[Realm.values().length];
            $SwitchMap$com$amazon$music$curate$skyfire$CurateEndpoints$Realm = iArr;
            try {
                iArr[Realm.NA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$skyfire$CurateEndpoints$Realm[Realm.EU.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$skyfire$CurateEndpoints$Realm[Realm.FE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static Realm getRealmByObfuscatedMarketplaceId(final String obfuscatedId) {
        Realm realm = marketplaceToRealmMap.get(obfuscatedId);
        return realm != null ? realm : Realm.NA;
    }
}
