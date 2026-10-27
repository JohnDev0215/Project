package com.byd.filemanager.h;

import android.app.Application;
import android.util.Log;
import com.byd.filemanager.service.b;

/* JADX INFO: compiled from: MainViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends androidx.lifecycle.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f3568d;

    public a(Application application) {
        super(application);
        this.f3568d = "MainViewModel";
    }

    @Override // androidx.lifecycle.o
    protected void c() {
        Log.i("MainViewModel", "onCleared is begin");
        b.c().g();
    }
}
