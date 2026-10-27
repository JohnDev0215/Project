package a.l.d;

import a.l.d.g0;
import a.l.d.r;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Animation.AnimationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f965a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Fragment f966b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ g0.a f967c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ a.h.g.a f968d;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (g.this.f966b.i() != null) {
                g.this.f966b.H(null);
                g gVar = g.this;
                ((r.b) gVar.f967c).a(gVar.f966b, gVar.f968d);
            }
        }
    }

    public g(ViewGroup viewGroup, Fragment fragment, g0.a aVar, a.h.g.a aVar2) {
        this.f965a = viewGroup;
        this.f966b = fragment;
        this.f967c = aVar;
        this.f968d = aVar2;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.f965a.post(new a());
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
    }
}
