package com.bumptech.glide;

import A4.q;
import java.util.HashSet;
import java.util.Set;
import t4.InterfaceC2435a;

/* JADX INFO: loaded from: classes.dex */
public final class k implements InterfaceC2435a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t4.e f12708a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l f12709b;

    public k(l lVar, t4.e eVar) {
        this.f12709b = lVar;
        this.f12708a = eVar;
    }

    @Override // t4.InterfaceC2435a
    public final void a(boolean z6) {
        if (z6) {
            synchronized (this.f12709b) {
                t4.e eVar = this.f12708a;
                for (w4.c cVar : q.e((Set) eVar.f22463n)) {
                    if (!cVar.h() && !cVar.d()) {
                        cVar.clear();
                        if (eVar.f22462m) {
                            ((HashSet) eVar.o).add(cVar);
                        } else {
                            cVar.g();
                        }
                    }
                }
            }
        }
    }
}
