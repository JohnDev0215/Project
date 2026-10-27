package com.airbnb.lottie.r.b;

import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<s> f731a = new ArrayList();

    void a(s sVar) {
        this.f731a.add(sVar);
    }

    public void b(Path path) {
        for (int size = this.f731a.size() - 1; size >= 0; size--) {
            com.airbnb.lottie.w.h.b(path, this.f731a.get(size));
        }
    }
}
