package b.d.a.a.m;

import a.h.k.q;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f2012a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2014c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2015d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2016e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2017f = true;
    public boolean g = true;

    public h(View view) {
        this.f2012a = view;
    }

    public void a() {
        View view = this.f2012a;
        q.I(view, this.f2015d - (view.getTop() - this.f2013b));
        View view2 = this.f2012a;
        view2.offsetLeftAndRight(this.f2016e - (view2.getLeft() - this.f2014c));
    }
}
