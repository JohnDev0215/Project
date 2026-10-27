package org.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: PendingPost.java */
/* JADX INFO: loaded from: classes.dex */
final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final List<i> f4699a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Object f4700b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    p f4701c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    i f4702d;

    private i(Object obj, p pVar) {
        this.f4700b = obj;
        this.f4701c = pVar;
    }

    static i a(p pVar, Object obj) {
        List<i> list = f4699a;
        synchronized (list) {
            int size = list.size();
            if (size <= 0) {
                return new i(obj, pVar);
            }
            i iVarRemove = list.remove(size - 1);
            iVarRemove.f4700b = obj;
            iVarRemove.f4701c = pVar;
            iVarRemove.f4702d = null;
            return iVarRemove;
        }
    }

    static void b(i iVar) {
        iVar.f4700b = null;
        iVar.f4701c = null;
        iVar.f4702d = null;
        List<i> list = f4699a;
        synchronized (list) {
            if (list.size() < 10000) {
                list.add(iVar);
            }
        }
    }
}
