package com.google.firebase.concurrent;

import android.annotation.SuppressLint;
import com.google.firebase.components.ComponentRegistrar;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import p.dz1;
import p.em;
import p.eq2;
import p.fw;
import p.lj;
import p.mv;
import p.w81;
import p.w91;
import p.wk;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ThreadPoolCreation"})
public class ExecutorsRegistrar implements ComponentRegistrar {
    public static final w81 a = new w81(new fw(2));
    public static final w81 b = new w81(new fw(3));
    public static final w81 c = new w81(new fw(4));
    public static final w81 d = new w81(new fw(5));

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        dz1 dz1Var = new dz1(wk.class, ScheduledExecutorService.class);
        dz1[] dz1VarArr = {new dz1(wk.class, ExecutorService.class), new dz1(wk.class, Executor.class)};
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        hashSet.add(dz1Var);
        for (dz1 dz1Var2 : dz1VarArr) {
            if (dz1Var2 == null) {
                throw new NullPointerException("Null interface");
            }
        }
        Collections.addAll(hashSet, dz1VarArr);
        mv mvVar = new mv(null, new HashSet(hashSet), new HashSet(hashSet2), 0, 0, new lj(0), hashSet3);
        dz1 dz1Var3 = new dz1(em.class, ScheduledExecutorService.class);
        dz1[] dz1VarArr2 = {new dz1(em.class, ExecutorService.class), new dz1(em.class, Executor.class)};
        HashSet hashSet4 = new HashSet();
        HashSet hashSet5 = new HashSet();
        HashSet hashSet6 = new HashSet();
        hashSet4.add(dz1Var3);
        for (dz1 dz1Var4 : dz1VarArr2) {
            if (dz1Var4 == null) {
                throw new NullPointerException("Null interface");
            }
        }
        Collections.addAll(hashSet4, dz1VarArr2);
        mv mvVar2 = new mv(null, new HashSet(hashSet4), new HashSet(hashSet5), 0, 0, new lj(1), hashSet6);
        dz1 dz1Var5 = new dz1(w91.class, ScheduledExecutorService.class);
        dz1[] dz1VarArr3 = {new dz1(w91.class, ExecutorService.class), new dz1(w91.class, Executor.class)};
        HashSet hashSet7 = new HashSet();
        HashSet hashSet8 = new HashSet();
        HashSet hashSet9 = new HashSet();
        hashSet7.add(dz1Var5);
        for (dz1 dz1Var6 : dz1VarArr3) {
            if (dz1Var6 == null) {
                throw new NullPointerException("Null interface");
            }
        }
        Collections.addAll(hashSet7, dz1VarArr3);
        mv mvVar3 = new mv(null, new HashSet(hashSet7), new HashSet(hashSet8), 0, 0, new lj(2), hashSet9);
        dz1 dz1Var7 = new dz1(eq2.class, Executor.class);
        HashSet hashSet10 = new HashSet();
        HashSet hashSet11 = new HashSet();
        HashSet hashSet12 = new HashSet();
        hashSet10.add(dz1Var7);
        Collections.addAll(hashSet10, new dz1[0]);
        return Arrays.asList(mvVar, mvVar2, mvVar3, new mv(null, new HashSet(hashSet10), new HashSet(hashSet11), 0, 0, new lj(3), hashSet12));
    }
}
