package p;

import android.os.Handler;
import com.google.common.base.Optional;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class qj {
    public final oj a;
    public final bi1 b;
    public final st1 c;
    public final p0 d;
    public final j0 e;
    public final Optional f;
    public final ei1 g;
    public final v h;
    public final l i;
    public final t0 j;
    public final jf0 k;
    public final yf2 l;
    public final Disposable m;

    public qj(rd1 rd1Var, st1 st1Var, jf0 jf0Var, bi1 bi1Var, oj ojVar, p0 p0Var, j0 j0Var, Optional optional, ei1 ei1Var, v vVar, l lVar, t0 t0Var, ix ixVar, yf2 yf2Var) {
        this.c = st1Var;
        this.k = jf0Var;
        this.b = bi1Var;
        this.a = ojVar;
        this.d = p0Var;
        this.e = j0Var;
        this.f = optional;
        this.g = ei1Var;
        this.h = vVar;
        this.i = lVar;
        this.j = t0Var;
        this.l = yf2Var;
        wh1 wh1Var = bi1Var.a;
        if (ojVar == null) {
            wh1Var.g(null, null);
        } else {
            wh1Var.g(ojVar, new Handler());
        }
        bi1Var.a.a.setFlags(7);
        Observable observableDistinctUntilChanged = ((sd1) rd1Var).a.distinctUntilChanged().distinctUntilChanged();
        ixVar.getClass();
        int i = 0;
        this.m = Observable.combineLatest(observableDistinctUntilChanged, Observable.create(new com.spotify.automotive.auth.a(i, ixVar)), new lj(i)).doOnNext(new pj(this, 1)).switchMap(new pj(this, i)).subscribeOn(io.reactivex.rxjava3.schedulers.e.c).subscribe(new pj(this, i), new ed1(8));
    }
}
