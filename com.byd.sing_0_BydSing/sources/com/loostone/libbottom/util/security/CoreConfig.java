package com.loostone.libbottom.util.security;

import androidx.annotation.Keep;
import com.android.volley.BuildConfig;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class CoreConfig {
    public static final CoreConfig INSTANCE = new CoreConfig();
    private static String PUBLIC_KEY = BuildConfig.FLAVOR;
    private static String PRIVATE_KEY = BuildConfig.FLAVOR;

    private CoreConfig() {
    }

    public final String getPRIVATE_KEY() {
        return PRIVATE_KEY;
    }

    public final String getPUBLIC_KEY() {
        return PUBLIC_KEY;
    }

    public final void setPRIVATE_KEY(String str) {
        h.d(str, "<set-?>");
        PRIVATE_KEY = str;
    }

    public final void setPUBLIC_KEY(String str) {
        h.d(str, "<set-?>");
        PUBLIC_KEY = str;
    }
}
