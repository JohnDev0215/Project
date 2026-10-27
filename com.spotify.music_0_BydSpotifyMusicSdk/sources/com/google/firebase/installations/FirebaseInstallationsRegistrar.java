package com.google.firebase.installations;

import androidx.annotation.Keep;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.concurrent.b;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import p.d90;
import p.dz1;
import p.em;
import p.kv;
import p.lj;
import p.lv;
import p.mv;
import p.ru0;
import p.u11;
import p.ux2;
import p.v11;
import p.wk;
import p.wu0;
import p.xv;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class FirebaseInstallationsRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-installations";

    /* JADX INFO: Access modifiers changed from: private */
    public static wu0 lambda$getComponents$0(xv xvVar) {
        return new a((ru0) xvVar.a(ru0.class), xvVar.d(v11.class), (ExecutorService) xvVar.f(new dz1(wk.class, ExecutorService.class)), new b((Executor) xvVar.f(new dz1(em.class, Executor.class))));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<mv> getComponents() {
        lv lvVar = new lv(wu0.class, new Class[0]);
        lvVar.a = LIBRARY_NAME;
        lvVar.a(d90.a(ru0.class));
        lvVar.a(new d90(0, 1, v11.class));
        lvVar.a(new d90(new dz1(wk.class, ExecutorService.class), 1, 0));
        lvVar.a(new d90(new dz1(em.class, Executor.class), 1, 0));
        lvVar.f = new lj(4);
        mv mvVarB = lvVar.b();
        u11 u11Var = new u11();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        hashSet.add(dz1.a(u11.class));
        return Arrays.asList(mvVarB, new mv(null, new HashSet(hashSet), new HashSet(hashSet2), 0, 1, new kv(1, u11Var), hashSet3), ux2.g(LIBRARY_NAME, "17.1.3"));
    }
}
