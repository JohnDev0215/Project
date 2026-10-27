package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.device.features.Feature;
import java.util.EnumMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class a4 extends z3 {
    public static final String c = "com.amazon.identity.auth.device.a4";
    public static a4 d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z3 f170a;
    public final EnumMap<Feature, Boolean> b = new EnumMap<>(Feature.class);

    public a4(z3 z3Var) {
        if (z3Var == null) {
            throw new IllegalArgumentException("delegateFeatureSet is null");
        }
        this.f170a = z3Var;
    }

    public static synchronized a4 a(z3 z3Var) {
        if (d == null) {
            d = new a4(z3Var);
        }
        return d;
    }

    @Override // com.amazon.identity.auth.device.z3
    public synchronized boolean a(Feature feature) {
        Boolean bool = this.b.get(feature);
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zA = this.f170a.a(feature);
        this.b.put(feature, Boolean.valueOf(zA));
        Log.i(ga.a(c), String.format("Caching feature %s as %s", feature.toString(), Boolean.valueOf(zA)));
        return zA;
    }

    public synchronized void a(Feature feature, Context context) {
        boolean zFetchValue = feature.fetchValue(context);
        this.b.put(feature, Boolean.valueOf(zFetchValue));
        Log.i(ga.a(c), String.format("Resetting feature cache %s as %s", feature.toString(), Boolean.valueOf(zFetchValue)));
    }
}
