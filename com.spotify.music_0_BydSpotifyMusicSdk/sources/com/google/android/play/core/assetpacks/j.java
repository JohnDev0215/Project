package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import p.f13;
import p.g13;
import p.o13;
import p.q13;
import p.tz2;

/* JADX INFO: loaded from: classes.dex */
public final class j {
    public static final tz2 f = new tz2("ExtractorSessionStoreView", 0);
    public final c a;
    public final f13 b;
    public final g13 c;
    public final HashMap d = new HashMap();
    public final ReentrantLock e = new ReentrantLock();

    public j(c cVar, f13 f13Var, g13 g13Var) {
        this.a = cVar;
        this.b = f13Var;
        this.c = g13Var;
    }

    public static String d(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        if (stringArrayList == null || stringArrayList.isEmpty()) {
            throw new zzck("Session without pack received.");
        }
        return stringArrayList.get(0);
    }

    public final void a() {
        this.e.unlock();
    }

    public final o13 b(int i) {
        HashMap map = this.d;
        Integer numValueOf = Integer.valueOf(i);
        o13 o13Var = (o13) map.get(numValueOf);
        if (o13Var != null) {
            return o13Var;
        }
        throw new zzck(String.format("Could not find session %d while trying to get it", numValueOf), i);
    }

    public final Object c(q13 q13Var) {
        ReentrantLock reentrantLock = this.e;
        try {
            reentrantLock.lock();
            return q13Var.a();
        } finally {
            reentrantLock.unlock();
        }
    }
}
