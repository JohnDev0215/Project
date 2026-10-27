package org.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final List<j> f3310a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Object f3311b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    q f3312c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    j f3313d;

    private j(Object obj, q qVar) {
        this.f3311b = obj;
        this.f3312c = qVar;
    }

    static j a(q qVar, Object obj) {
        List<j> list = f3310a;
        synchronized (list) {
            int size = list.size();
            if (size <= 0) {
                return new j(obj, qVar);
            }
            j jVarRemove = list.remove(size - 1);
            jVarRemove.f3311b = obj;
            jVarRemove.f3312c = qVar;
            jVarRemove.f3313d = null;
            return jVarRemove;
        }
    }

    static void b(j jVar) {
        jVar.f3311b = null;
        jVar.f3312c = null;
        jVar.f3313d = null;
        List<j> list = f3310a;
        synchronized (list) {
            if (list.size() < 10000) {
                list.add(jVar);
            }
        }
    }
}
