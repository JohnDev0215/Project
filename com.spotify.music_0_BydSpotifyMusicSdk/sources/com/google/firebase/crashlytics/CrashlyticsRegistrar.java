package com.google.firebase.crashlytics;

import com.google.firebase.components.ComponentRegistrar;
import com.spotify.automotive.auth.a;
import java.util.Arrays;
import java.util.List;
import p.d90;
import p.j30;
import p.lv;
import p.n6;
import p.ru0;
import p.ux2;
import p.wu0;

/* JADX INFO: loaded from: classes.dex */
public class CrashlyticsRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        lv lvVar = new lv(FirebaseCrashlytics.class, new Class[0]);
        lvVar.a = "fire-cls";
        lvVar.a(d90.a(ru0.class));
        lvVar.a(d90.a(wu0.class));
        lvVar.a(new d90(0, 2, j30.class));
        lvVar.a(new d90(0, 2, n6.class));
        lvVar.f = new a(0, this);
        if (lvVar.d != 0) {
            throw new IllegalStateException("Instantiation type has already been set.");
        }
        lvVar.d = 2;
        return Arrays.asList(lvVar.b(), ux2.g("fire-cls", "18.3.6"));
    }
}
