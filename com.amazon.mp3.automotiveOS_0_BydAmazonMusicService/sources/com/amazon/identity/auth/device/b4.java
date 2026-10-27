package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.features.Feature;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class b4 extends z3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f238a;

    public b4(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.f238a = context;
    }

    @Override // com.amazon.identity.auth.device.z3
    public boolean a(Feature feature) {
        return feature.fetchValue(this.f238a);
    }
}
