package s3;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: s3.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC2382b implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ArrayList f22254m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2388h f22255n;

    public /* synthetic */ RunnableC2382b(C2388h c2388h, ArrayList arrayList, int i7) {
        this.l = i7;
        this.f22255n = c2388h;
        this.f22254m = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Iterator it;
        switch (this.l) {
            case 0:
                ArrayList arrayList = this.f22254m;
                Iterator it2 = arrayList.iterator();
                while (true) {
                    boolean zHasNext = it2.hasNext();
                    C2388h c2388h = this.f22255n;
                    if (!zHasNext) {
                        arrayList.clear();
                        c2388h.f22286m.remove(arrayList);
                    } else {
                        C2387g c2387g = (C2387g) it2.next();
                        U u4 = c2387g.f22277a;
                        c2388h.getClass();
                        View view = u4.f22219a;
                        int i7 = c2387g.d - c2387g.f22278b;
                        int i10 = c2387g.f22279e - c2387g.c;
                        if (i7 != 0) {
                            view.animate().translationX(0.0f);
                        }
                        if (i10 != 0) {
                            view.animate().translationY(0.0f);
                        }
                        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                        c2388h.f22288p.add(u4);
                        viewPropertyAnimatorAnimate.setDuration(c2388h.f22173e).setListener(new C2384d(c2388h, u4, i7, view, i10, viewPropertyAnimatorAnimate)).start();
                    }
                    break;
                }
                break;
            case 1:
                ArrayList arrayList2 = this.f22254m;
                Iterator it3 = arrayList2.iterator();
                while (true) {
                    boolean zHasNext2 = it3.hasNext();
                    C2388h c2388h2 = this.f22255n;
                    if (!zHasNext2) {
                        arrayList2.clear();
                        c2388h2.f22287n.remove(arrayList2);
                    } else {
                        C2386f c2386f = (C2386f) it3.next();
                        c2388h2.getClass();
                        U u10 = c2386f.f22273a;
                        View view2 = u10 == null ? null : u10.f22219a;
                        U u11 = c2386f.f22274b;
                        View view3 = u11 != null ? u11.f22219a : null;
                        ArrayList arrayList3 = c2388h2.f22290r;
                        long j3 = c2388h2.f22174f;
                        if (view2 != null) {
                            ViewPropertyAnimator duration = view2.animate().setDuration(j3);
                            arrayList3.add(c2386f.f22273a);
                            duration.translationX(c2386f.f22275e - c2386f.c);
                            duration.translationY(c2386f.f22276f - c2386f.d);
                            it = it3;
                            duration.alpha(0.0f).setListener(new C2385e(c2388h2, c2386f, duration, view2, 0)).start();
                        } else {
                            it = it3;
                        }
                        if (view3 != null) {
                            ViewPropertyAnimator viewPropertyAnimatorAnimate2 = view3.animate();
                            arrayList3.add(c2386f.f22274b);
                            viewPropertyAnimatorAnimate2.translationX(0.0f).translationY(0.0f).setDuration(j3).alpha(1.0f).setListener(new C2385e(c2388h2, c2386f, viewPropertyAnimatorAnimate2, view3, 1)).start();
                        }
                        it3 = it;
                    }
                    break;
                }
                break;
            default:
                ArrayList arrayList4 = this.f22254m;
                Iterator it4 = arrayList4.iterator();
                while (true) {
                    boolean zHasNext3 = it4.hasNext();
                    C2388h c2388h3 = this.f22255n;
                    if (!zHasNext3) {
                        arrayList4.clear();
                        c2388h3.l.remove(arrayList4);
                    } else {
                        U u12 = (U) it4.next();
                        c2388h3.getClass();
                        View view4 = u12.f22219a;
                        ViewPropertyAnimator viewPropertyAnimatorAnimate3 = view4.animate();
                        c2388h3.o.add(u12);
                        viewPropertyAnimatorAnimate3.alpha(1.0f).setDuration(c2388h3.c).setListener(new C2383c(c2388h3, u12, view4, viewPropertyAnimatorAnimate3)).start();
                    }
                    break;
                }
                break;
        }
    }
}
