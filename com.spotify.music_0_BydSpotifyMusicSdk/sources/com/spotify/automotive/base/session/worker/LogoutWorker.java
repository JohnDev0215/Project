package com.spotify.automotive.base.session.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.spotify.androidx.workmanager.DaggerRxWorker;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.auth.b;
import io.reactivex.rxjava3.internal.operators.completable.n;
import p.ad1;
import p.ob1;
import p.v03;
import p.w50;

/* JADX INFO: loaded from: classes.dex */
public final class LogoutWorker extends DaggerRxWorker {
    public ad1 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogoutWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        v03.j(context, "context");
        v03.j(workerParameters, "workerParams");
    }

    @Override // com.spotify.androidx.workmanager.DaggerRxWorker
    public final n h() {
        ad1 ad1Var = this.o;
        if (ad1Var != null) {
            return ((b) ad1Var).a(LoginState.k).e(new ob1(w50.c));
        }
        v03.Q("loginController");
        throw null;
    }
}
