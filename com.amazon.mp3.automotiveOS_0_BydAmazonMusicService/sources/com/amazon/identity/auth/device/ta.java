package com.amazon.identity.auth.device;

import java.util.Timer;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ta {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Timer f584a;
    public boolean b;
    public boolean c;

    public ta() {
        this(new Timer());
    }

    public ta(Timer timer) {
        this.f584a = timer;
        this.b = false;
        this.c = false;
    }
}
