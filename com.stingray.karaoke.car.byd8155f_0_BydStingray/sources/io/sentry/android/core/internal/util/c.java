package io.sentry.android.core.internal.util;

import h8.AbstractC1386c;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f16211b = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f16212a = new ArrayList();

    public final synchronized ArrayList a() {
        if (!this.f16212a.isEmpty()) {
            return this.f16212a;
        }
        File[] fileArrListFiles = new File("/sys/devices/system/cpu").listFiles();
        if (fileArrListFiles == null) {
            return new ArrayList();
        }
        for (File file : fileArrListFiles) {
            if (file.getName().matches("cpu[0-9]+")) {
                File file2 = new File(file, "cpufreq/cpuinfo_max_freq");
                if (file2.exists() && file2.canRead()) {
                    try {
                        String strN = AbstractC1386c.n(file2);
                        if (strN != null) {
                            this.f16212a.add(Integer.valueOf((int) (Long.parseLong(strN.trim()) / 1000)));
                        }
                    } catch (IOException | NumberFormatException unused) {
                    }
                }
            }
        }
        return this.f16212a;
    }
}
