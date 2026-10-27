package com.amazon.mp3.environment.url;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class MuseEndPointURLFactory extends BaseEndPointURLFactory {
    private static final String TAG = "MuseEndPointURLFactory";
    private static final Map<String, String> brushv3_stages;
    private static final Map<String, String> muse_endpoints;

    static {
        HashMap map = new HashMap();
        muse_endpoints = map;
        HashMap map2 = new HashMap();
        brushv3_stages = map2;
        map.put("TEST1", "https://music.amazon.com/api/musetest1/");
        map.put("TEST2", "https://music.amazon.com/api/musetest2/");
        map.put("TEST3", "https://music.amazon.com/api/musetest3/");
        map.put("GAMMA", "https://music.amazon.com/NA/api/musepagegamma/");
        map.put("PROD", "https://music.amazon.com/NA/api/musepage/");
        map2.put("BETA", "beta");
        map2.put("PROD", "prod");
    }

    @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
    public EndPointURL get() {
        try {
            return new EndPointURL(EndpointsProvider.get().getMuseEndpoint());
        } catch (Exception e) {
            Log.error(TAG, "Can't get muse endpoint " + e);
            return null;
        }
    }

    public URL getMuseBrushEndpoint() throws Exception {
        if (getCurrentOverrideId(R.string.setting_key_dev_muse_endpoint).equals("PROD")) {
            return EndpointsProvider.get().getMuseBrushEndpoint();
        }
        return new URL(muse_endpoints.get(getCurrentOverrideId(R.string.setting_key_dev_muse_endpoint)));
    }

    public String getBrushV3Stage() {
        return brushv3_stages.get(getCurrentOverrideId(R.string.setting_key_dev_brushv3_endpoint));
    }

    public String getCurrentOverrideId(final int key) {
        return SettingsUtil.EnvironmentPref.getEnvironment(AmazonApplication.getContext(), key, "PROD");
    }

    public String[] overrideEntries(final int key) {
        Set<String> setKeySet = (key == R.string.setting_key_dev_muse_endpoint ? muse_endpoints : brushv3_stages).keySet();
        String[] strArr = new String[setKeySet.size()];
        setKeySet.toArray(strArr);
        Arrays.sort(strArr);
        return strArr;
    }
}
