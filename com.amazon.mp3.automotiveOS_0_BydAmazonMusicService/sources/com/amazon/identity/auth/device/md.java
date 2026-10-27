package com.amazon.identity.auth.device;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class md {
    public static final String d = "com.amazon.identity.auth.device.md";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, String> f436a = new HashMap();
    public final List<String> b = new ArrayList();
    public long c = 200;

    public void a(String str, String str2) {
        if (str == null || str2 == null) {
            Log.e(ga.a(d), "WebResponseHeaders: addHeader: Header cannot be added. Name or value was null.");
        } else {
            this.b.add(str);
            this.f436a.put(str.toLowerCase(Locale.US), str2);
        }
    }
}
