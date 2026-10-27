package com.byd.bluetoothcall.view.c;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.m;
import androidx.lifecycle.p;
import androidx.lifecycle.q;

/* JADX INFO: compiled from: PageViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private m<Integer> f3410c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private LiveData<String> f3411d;

    /* JADX INFO: compiled from: PageViewModel.java */
    class a implements b.b.a.c.a<Integer, String> {
        a() {
        }

        @Override // b.b.a.c.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public String apply(Integer num) {
            return "Hello world from section: " + num;
        }
    }

    public f() {
        m<Integer> mVar = new m<>();
        this.f3410c = mVar;
        this.f3411d = p.a(mVar, new a());
    }

    public void d(int i) {
        this.f3410c.k(Integer.valueOf(i));
    }
}
