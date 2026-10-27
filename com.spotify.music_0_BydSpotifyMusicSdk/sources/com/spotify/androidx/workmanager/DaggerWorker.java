package com.spotify.androidx.workmanager;

import android.content.Context;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import p.bk0;
import p.ob1;
import p.v03;
import p.w50;

/* JADX INFO: loaded from: classes.dex */
public abstract class DaggerWorker extends Worker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DaggerWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        v03.j(context, "context");
        v03.j(workerParameters, "workerParams");
    }

    @Override // androidx.work.Worker
    public final ob1 f() {
        bk0.D(this);
        return new ob1(w50.c);
    }
}
