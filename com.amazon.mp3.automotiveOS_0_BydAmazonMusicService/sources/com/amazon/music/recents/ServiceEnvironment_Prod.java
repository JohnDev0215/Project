package com.amazon.music.recents;

import com.amazon.music.marketplace.Marketplace;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class ServiceEnvironment_Prod extends ServiceEnvironment {
    public ServiceEnvironment_Prod() {
        super("Nimbly_Prod");
    }

    @Override // com.amazon.music.recents.ServiceEnvironment
    protected Map<Marketplace, String> getContent() {
        HashMap map = new HashMap();
        map.put(Marketplace.USA, "https://music.amazon.com/api/nimbly/");
        map.put(Marketplace.UK, "https://music.amazon.co.uk/api/nimbly/");
        map.put(Marketplace.GERMANY, "https://music.amazon.de/api/nimbly/");
        map.put(Marketplace.SPAIN, "https://music.amazon.es/api/nimbly/");
        map.put(Marketplace.FRANCE, "https://music.amazon.fr/api/nimbly/");
        map.put(Marketplace.ITALY, "https://music.amazon.it/api/nimbly/");
        map.put(Marketplace.JAPAN, "https://music.amazon.co.jp/api/nimbly/");
        map.put(Marketplace.ROW_NA, "https://music.amazon.com/NA/api/nimbly/");
        map.put(Marketplace.ROE_EU, "https://music.amazon.com/EU/api/nimbly/");
        map.put(Marketplace.ROW_FE, "https://music.amazon.com/FE/api/nimbly/");
        return map;
    }
}
