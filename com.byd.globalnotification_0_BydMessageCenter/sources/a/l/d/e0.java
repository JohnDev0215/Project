package a.l.d;

import android.graphics.Rect;
import android.view.View;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public final class e0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Fragment f952a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Fragment f953b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f954c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ a.e.a f955d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ View f956e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ l0 f957f;
    public final /* synthetic */ Rect g;

    public e0(Fragment fragment, Fragment fragment2, boolean z, a.e.a aVar, View view, l0 l0Var, Rect rect) {
        this.f952a = fragment;
        this.f953b = fragment2;
        this.f954c = z;
        this.f955d = aVar;
        this.f956e = view;
        this.f957f = l0Var;
        this.g = rect;
    }

    @Override // java.lang.Runnable
    public void run() {
        g0.c(this.f952a, this.f953b, this.f954c, this.f955d, false);
        View view = this.f956e;
        if (view != null) {
            this.f957f.j(view, this.g);
        }
    }
}
