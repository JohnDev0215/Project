package com.google.firebase.crashlytics.ndk;

import android.content.Context;
import com.google.firebase.components.ComponentRegistrar;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import p.d90;
import p.dz1;
import p.gu0;
import p.j30;
import p.jm2;
import p.l40;
import p.mv;
import p.tu0;
import p.ux2;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public class CrashlyticsNdkRegistrar implements ComponentRegistrar {
    public static tu0 a(CrashlyticsNdkRegistrar crashlyticsNdkRegistrar, jm2 jm2Var) {
        crashlyticsNdkRegistrar.getClass();
        Context context = (Context) jm2Var.a(Context.class);
        return new tu0(new l40(context, new JniNativeApi(context), new gu0(context)), !(zu.i0(context, "com.google.firebase.crashlytics.unity_version", "string") != 0));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        hashSet.add(dz1.a(j30.class));
        for (Class cls : new Class[0]) {
            if (cls == null) {
                throw new NullPointerException("Null interface");
            }
            hashSet.add(dz1.a(cls));
        }
        d90 d90VarA = d90.a(Context.class);
        if (!(!hashSet.contains(d90VarA.a))) {
            throw new IllegalArgumentException("Components are not allowed to depend on interfaces they themselves provide.");
        }
        hashSet2.add(d90VarA);
        return Arrays.asList(new mv("fire-cls-ndk", new HashSet(hashSet), new HashSet(hashSet2), 2, 0, new com.spotify.automotive.auth.a(1, this), hashSet3), ux2.g("fire-cls-ndk", "18.3.6"));
    }
}
