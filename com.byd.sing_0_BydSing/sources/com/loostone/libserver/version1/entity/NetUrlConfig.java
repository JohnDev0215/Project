package com.loostone.libserver.version1.entity;

import com.android.volley.BuildConfig;
import com.google.gson.Gson;

/* JADX INFO: loaded from: classes.dex */
public class NetUrlConfig {
    private static final String TAG = "NetUrlConfig";
    private static NetAddress netAddress;

    public interface TYPE {
        public static final int APP_UPDATE = 1;
        public static final int CACHE_REFRESH = 5;
        public static final int COMMUNICATION_KERNEL = 6;
        public static final int KARAOKE_VOICE_APP = 7;
        public static final int KARAOKE_VOLUME = 8;
        public static final int MIC_COMM_INFO = 4;
        public static final int MIC_LIMIT_INFO = 2;
        public static final int STATISTICAL_MIC_INFO = 3;
    }

    public static String getAddress(int i) {
        NetAddress netAddress2 = netAddress;
        if (netAddress2 == null) {
            return BuildConfig.FLAVOR;
        }
        switch (i) {
        }
        return BuildConfig.FLAVOR;
    }

    public static void init(String str) {
        if (netAddress == null) {
            netAddress = (NetAddress) new Gson().fromJson(str, NetAddress.class);
        }
    }
}
