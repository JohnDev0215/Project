package com.spotify.androidx.workmanager;

import android.content.Context;
import androidx.work.WorkerParameters;
import androidx.work.rxjava3.RxWorker;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.internal.operators.completable.n;
import p.bk0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class DaggerRxWorker extends RxWorker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DaggerRxWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        v03.j(context, "appContext");
        v03.j(workerParameters, "workerParams");
    }

    @Override // androidx.work.rxjava3.RxWorker
    public final Single g() {
        bk0.D(this);
        return h();
    }

    public abstract n h();
}
