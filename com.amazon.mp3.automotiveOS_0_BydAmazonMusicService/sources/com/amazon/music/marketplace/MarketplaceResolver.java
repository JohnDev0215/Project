package com.amazon.music.marketplace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class MarketplaceResolver {
    private static final List<Marketplace> ALL_COUNTRIES;
    private static final Map<String, Marketplace> lookupCountryCode;
    private static final Map<String, Marketplace> lookupId;
    private static final Map<String, Marketplace> lookupLocale;
    private static final Map<String, Marketplace> lookupObfuscatedId;

    static {
        ArrayList arrayList = new ArrayList();
        ALL_COUNTRIES = arrayList;
        arrayList.add(Marketplace.USA);
        arrayList.add(Marketplace.UK);
        arrayList.add(Marketplace.GERMANY);
        arrayList.add(Marketplace.FRANCE);
        arrayList.add(Marketplace.JAPAN);
        arrayList.add(Marketplace.SPAIN);
        arrayList.add(Marketplace.ITALY);
        arrayList.add(Marketplace.ROW_NA);
        arrayList.add(Marketplace.ROE_EU);
        arrayList.add(Marketplace.ROW_FE);
        lookupId = new HashMap();
        lookupObfuscatedId = new HashMap();
        lookupCountryCode = new HashMap();
        lookupLocale = new HashMap();
        igniteLookup();
    }

    private static final void igniteLookup() {
        int i = 0;
        while (true) {
            List<Marketplace> list = ALL_COUNTRIES;
            if (i >= list.size()) {
                return;
            }
            Marketplace marketplace = list.get(i);
            lookupId.put(marketplace.getId(), marketplace);
            lookupObfuscatedId.put(marketplace.getObfuscatedId(), marketplace);
            lookupCountryCode.put(marketplace.getCountryCode(), marketplace);
            Iterator<String> it = marketplace.getLocale().iterator();
            while (it.hasNext()) {
                lookupLocale.put(it.next(), marketplace);
            }
            i++;
        }
    }

    public static final Marketplace lookupById(String str) {
        return lookup(lookupId, str);
    }

    public static final Marketplace lookupByObfuscatedId(String str) {
        return lookup(lookupObfuscatedId, str);
    }

    private static final Marketplace lookup(Map<String, Marketplace> map, String str) {
        Marketplace marketplace = Marketplace.NONE;
        return (StringUtils.isEmpty(str) || !map.containsKey(str)) ? marketplace : map.get(str);
    }
}
