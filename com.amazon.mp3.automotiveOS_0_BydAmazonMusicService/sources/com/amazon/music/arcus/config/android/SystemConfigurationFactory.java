package com.amazon.music.arcus.config.android;

import android.content.Context;
import com.amazon.music.arcus.ConfigurationManager;
import com.amazon.music.arcus.RemoteConfigRequest;
import com.amazon.music.arcus.SegmentRuleKey;
import com.amazon.music.marketplace.Marketplace;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class SystemConfigurationFactory {
    public static Map<String, Object> getSegmentAttributes(RemoteConfigRequest remoteConfigRequest) {
        HashMap map = new HashMap(2);
        Marketplace marketplace = remoteConfigRequest.getMarketplace();
        if (marketplace != null) {
            map.put(SegmentRuleKey.MARKETPLACE.getKey(), marketplace.getId());
        }
        String territory = remoteConfigRequest.getTerritory();
        if (territory != null) {
            map.put(SegmentRuleKey.TERRITORY.getKey(), territory);
        }
        return map;
    }

    public static ConfigurationManager createConfigurationManager(Context context, RemoteConfigRequest remoteConfigRequest, Map<String, Object> map) {
        return new ConfigurationManager(context, map, getConfigurationId(remoteConfigRequest));
    }

    private static String getConfigurationId(RemoteConfigRequest remoteConfigRequest) {
        return remoteConfigRequest.isTestEnvironment() ? "arn:aws:remote-config:us-west-2:959793350288:appConfig:a7bh1kdm" : "arn:aws:remote-config:us-west-2:959793350288:appConfig:auswstej";
    }
}
