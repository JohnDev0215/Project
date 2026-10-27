package com.byd.util;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class u<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<T> f3007a;

    public void a() {
        if (this.f3007a != null) {
            w.c("==clearSearchResult==");
            this.f3007a.clear();
            this.f3007a = null;
        }
    }

    public List<T> b() {
        return this.f3007a;
    }

    public boolean c() {
        List<T> list = this.f3007a;
        return list == null || list.size() == 0;
    }

    public void d(List<T> list) {
        this.f3007a = list;
    }

    public int e() {
        List<T> list = this.f3007a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
