package com.amazon.music.platform;

import com.google.firebase.perf.plugin.util.AsmString;
import java.util.Calendar;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlatformProviderUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0017\u0010\u0001\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00028\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"T", "freeze", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "", "getCurrentEpochTimeMillis", "", "getFireFlyApiKey", "MusicPlatformCore_release"}, k = 2, mv = {1, 7, 1})
public final class PlatformProviderUtilKt {
    public static final <T> T freeze(T t) {
        return t;
    }

    public static final String getFireFlyApiKey() {
        return "amzn1.application.d5f54ca32dbe412b8adca604f36814f9";
    }

    public static final long getCurrentEpochTimeMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
