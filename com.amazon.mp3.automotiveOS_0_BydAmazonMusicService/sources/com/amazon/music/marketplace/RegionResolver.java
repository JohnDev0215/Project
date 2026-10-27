package com.amazon.music.marketplace;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
final class RegionResolver {
    private static final Map<String, String> map;

    RegionResolver() {
    }

    static {
        HashMap map2 = new HashMap();
        map = map2;
        map2.put("ATVPDKIKX0DER", "NA");
        map2.put("ART4WZ8MWBX2Y", "NA");
        map2.put("A1F83G8C2ARO7P", "EU");
        map2.put("A1PA6795UKMFR9", "EU");
        map2.put("A13V1IB3VIYZZH", "EU");
        map2.put("APJ6JRA9NG5V4", "EU");
        map2.put("A1RKKUPIHCS9HS", "EU");
        map2.put("A3K6Y4MI8GDYMT", "EU");
        map2.put("A1VC38T7YXB528", "FE");
        map2.put("A15PK738MTQHSO", "FE");
    }

    static String get(String str) {
        return map.get(str);
    }
}
