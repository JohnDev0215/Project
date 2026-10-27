package com.google.firebase;

import android.content.Context;
import android.os.Build;
import com.google.firebase.components.ComponentRegistrar;
import com.spotify.automotive.auth.a;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import p.d80;
import p.d90;
import p.dz1;
import p.ei;
import p.iy2;
import p.lj;
import p.lv;
import p.mv;
import p.q81;
import p.ru0;
import p.u11;
import p.ux2;
import p.v11;
import p.w11;
import p.wk;
import p.y60;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseCommonRegistrar implements ComponentRegistrar {
    public static String a(String str) {
        return str.replace(' ', '_').replace('/', '_');
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        String str;
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        hashSet.add(dz1.a(d80.class));
        for (Class cls : new Class[0]) {
            if (cls == null) {
                throw new NullPointerException("Null interface");
            }
            hashSet.add(dz1.a(cls));
        }
        int i = 2;
        d90 d90Var = new d90(2, 0, ei.class);
        if (!(!hashSet.contains(d90Var.a))) {
            throw new IllegalArgumentException("Components are not allowed to depend on interfaces they themselves provide.");
        }
        hashSet2.add(d90Var);
        arrayList.add(new mv(null, new HashSet(hashSet), new HashSet(hashSet2), 0, 0, new lj(5), hashSet3));
        dz1 dz1Var = new dz1(wk.class, Executor.class);
        lv lvVar = new lv(y60.class, new Class[]{v11.class, w11.class});
        lvVar.a(d90.a(Context.class));
        lvVar.a(d90.a(ru0.class));
        lvVar.a(new d90(2, 0, u11.class));
        lvVar.a(new d90(1, 1, d80.class));
        lvVar.a(new d90(dz1Var, 1, 0));
        lvVar.f = new a(i, dz1Var);
        arrayList.add(lvVar.b());
        arrayList.add(ux2.g("fire-android", String.valueOf(Build.VERSION.SDK_INT)));
        arrayList.add(ux2.g("fire-core", "20.3.1"));
        arrayList.add(ux2.g("device-name", a(Build.PRODUCT)));
        arrayList.add(ux2.g("device-model", a(Build.DEVICE)));
        arrayList.add(ux2.g("device-brand", a(Build.BRAND)));
        arrayList.add(ux2.j("android-target-sdk", new iy2(12)));
        arrayList.add(ux2.j("android-min-sdk", new iy2(13)));
        arrayList.add(ux2.j("android-platform", new iy2(14)));
        arrayList.add(ux2.j("android-installer", new iy2(15)));
        try {
            q81.e.getClass();
            str = "1.8.22";
        } catch (NoClassDefFoundError unused) {
            str = null;
        }
        if (str != null) {
            arrayList.add(ux2.g("kotlin", str));
        }
        return arrayList;
    }
}
