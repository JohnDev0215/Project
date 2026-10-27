package com.airbnb.lottie.r.b;

import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CompoundTrimPathContent.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<s> f3058a = new ArrayList();

    void a(s sVar) {
        this.f3058a.add(sVar);
    }

    public void b(Path path) {
        for (int size = this.f3058a.size() - 1; size >= 0; size--) {
            com.airbnb.lottie.u.h.b(path, this.f3058a.get(size));
        }
    }
}
