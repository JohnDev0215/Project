package com.android.settingslib.applications;

import android.content.res.Configuration;
import android.content.res.Resources;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Configuration f1335a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f1336b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1337c;

    public d() {
        this(-2147482876);
    }

    public boolean a(Resources resources) {
        Configuration configuration = this.f1335a;
        int iUpdateFrom = configuration.updateFrom(Configuration.generateDelta(configuration, resources.getConfiguration()));
        if (!(this.f1337c != resources.getDisplayMetrics().densityDpi) && (iUpdateFrom & this.f1336b) == 0) {
            return false;
        }
        this.f1337c = resources.getDisplayMetrics().densityDpi;
        return true;
    }

    public d(int i) {
        this.f1335a = new Configuration();
        this.f1336b = i;
    }
}
