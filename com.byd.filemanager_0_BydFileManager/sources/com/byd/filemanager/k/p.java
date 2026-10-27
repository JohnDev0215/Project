package com.byd.filemanager.k;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: UserLock.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicBoolean f3640a = new AtomicBoolean(true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f3641b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final AtomicBoolean f3642c = new AtomicBoolean(false);

    public void a() {
        this.f3640a.set(false);
    }
}
