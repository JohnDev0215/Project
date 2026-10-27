package com.amazon.music.casting;

import com.amazon.music.marketplace.Marketplace;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
final class ServiceEnvironment_Prod extends ServiceEnvironment {
    public ServiceEnvironment_Prod() {
        super("Casting_Prod");
    }

    @Override // com.amazon.music.casting.ServiceEnvironment
    protected Map<Marketplace, String> getContent() {
        HashMap map = new HashMap();
        map.put(Marketplace.USA, "https://music.amazon.com/NA/api/mpqs/voiceenabled/");
        map.put(Marketplace.UK, "https://music.amazon.com/EU/api/mpqs/voiceenabled/");
        map.put(Marketplace.GERMANY, "https://music.amazon.com/EU/api/mpqs/voiceenabled/");
        map.put(Marketplace.JAPAN, "https://music.amazon.com/FE/api/mpqs/voiceenabled/");
        map.put(Marketplace.FRANCE, "https://music.amazon.com/EU/api/mpqs/voiceenabled/");
        map.put(Marketplace.ITALY, "https://music.amazon.com/EU/api/mpqs/voiceenabled/");
        map.put(Marketplace.SPAIN, "https://music.amazon.com/EU/api/mpqs/voiceenabled/");
        map.put(Marketplace.ROW_NA, "https://music.amazon.com/NA/api/mpqs/voiceenabled/");
        map.put(Marketplace.ROE_EU, "https://music.amazon.com/EU/api/mpqs/voiceenabled/");
        map.put(Marketplace.ROW_FE, "https://music.amazon.com/FE/api/mpqs/voiceenabled/");
        return map;
    }
}
