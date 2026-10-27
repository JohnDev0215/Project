package io.sentry.config;

import io.sentry.util.i;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
public final class e implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f16510a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Properties f16511b;

    public e(String str, Properties properties) {
        this.f16510a = str;
        h6.e.q(properties, "properties are required");
        this.f16511b = properties;
    }

    @Override // io.sentry.config.d
    public final Map a() {
        String strL = android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(), this.f16510a, "tags.");
        HashMap map = new HashMap();
        for (Map.Entry entry : this.f16511b.entrySet()) {
            if ((entry.getKey() instanceof String) && (entry.getValue() instanceof String)) {
                String str = (String) entry.getKey();
                if (str.startsWith(strL)) {
                    map.put(str.substring(strL.length()), i.c((String) entry.getValue()));
                }
            }
        }
        return map;
    }

    @Override // io.sentry.config.d
    public final String getProperty(String str) {
        return i.c(this.f16511b.getProperty(android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(), this.f16510a, str)));
    }

    public e(Properties properties) {
        this("", properties);
    }
}
