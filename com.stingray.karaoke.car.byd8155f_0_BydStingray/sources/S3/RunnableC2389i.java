package s3;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: s3.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC2389i implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f22291m;

    public /* synthetic */ RunnableC2389i(Object obj, int i7) {
        this.l = i7;
        this.f22291m = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7;
        Object obj = this.f22291m;
        switch (this.l) {
            case 0:
                C2392l c2392l = (C2392l) obj;
                int i10 = c2392l.f22297A;
                ValueAnimator valueAnimator = c2392l.f22319z;
                if (i10 != 1) {
                    i7 = 2;
                    if (i10 != 2) {
                    }
                } else {
                    i7 = 2;
                    valueAnimator.cancel();
                }
                c2392l.f22297A = 3;
                float[] fArr = new float[i7];
                fArr[0] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                fArr[1] = 0.0f;
                valueAnimator.setFloatValues(fArr);
                valueAnimator.setDuration(500);
                valueAnimator.start();
                break;
            case 1:
                RecyclerView recyclerView = (RecyclerView) obj;
                AbstractC2364C abstractC2364C = recyclerView.f12121U;
                if (abstractC2364C != null) {
                    C2388h c2388h = (C2388h) abstractC2364C;
                    ArrayList arrayList = c2388h.f22282h;
                    boolean zIsEmpty = arrayList.isEmpty();
                    ArrayList arrayList2 = c2388h.f22284j;
                    boolean zIsEmpty2 = arrayList2.isEmpty();
                    ArrayList arrayList3 = c2388h.f22285k;
                    boolean zIsEmpty3 = arrayList3.isEmpty();
                    ArrayList arrayList4 = c2388h.f22283i;
                    boolean zIsEmpty4 = arrayList4.isEmpty();
                    if (!zIsEmpty || !zIsEmpty2 || !zIsEmpty4 || !zIsEmpty3) {
                        Iterator it = arrayList.iterator();
                        while (true) {
                            boolean zHasNext = it.hasNext();
                            long j3 = c2388h.d;
                            if (zHasNext) {
                                U u4 = (U) it.next();
                                View view = u4.f22219a;
                                ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                                c2388h.f22289q.add(u4);
                                viewPropertyAnimatorAnimate.setDuration(j3).alpha(0.0f).setListener(new C2383c(c2388h, u4, viewPropertyAnimatorAnimate, view)).start();
                                it = it;
                            } else {
                                arrayList.clear();
                                if (!zIsEmpty2) {
                                    ArrayList arrayList5 = new ArrayList();
                                    arrayList5.addAll(arrayList2);
                                    c2388h.f22286m.add(arrayList5);
                                    arrayList2.clear();
                                    RunnableC2382b runnableC2382b = new RunnableC2382b(c2388h, arrayList5, 0);
                                    if (zIsEmpty) {
                                        runnableC2382b.run();
                                    } else {
                                        View view2 = ((C2387g) arrayList5.get(0)).f22277a.f22219a;
                                        int[] iArr = F1.H.f2303a;
                                        view2.postOnAnimationDelayed(runnableC2382b, j3);
                                    }
                                }
                                if (!zIsEmpty3) {
                                    ArrayList arrayList6 = new ArrayList();
                                    arrayList6.addAll(arrayList3);
                                    c2388h.f22287n.add(arrayList6);
                                    arrayList3.clear();
                                    RunnableC2382b runnableC2382b2 = new RunnableC2382b(c2388h, arrayList6, 1);
                                    if (zIsEmpty) {
                                        runnableC2382b2.run();
                                    } else {
                                        View view3 = ((C2386f) arrayList6.get(0)).f22273a.f22219a;
                                        int[] iArr2 = F1.H.f2303a;
                                        view3.postOnAnimationDelayed(runnableC2382b2, j3);
                                    }
                                }
                                if (!zIsEmpty4) {
                                    ArrayList arrayList7 = new ArrayList();
                                    arrayList7.addAll(arrayList4);
                                    c2388h.l.add(arrayList7);
                                    arrayList4.clear();
                                    RunnableC2382b runnableC2382b3 = new RunnableC2382b(c2388h, arrayList7, 2);
                                    if (zIsEmpty && zIsEmpty2 && zIsEmpty3) {
                                        runnableC2382b3.run();
                                    } else {
                                        if (zIsEmpty) {
                                            j3 = 0;
                                        }
                                        long jMax = Math.max(!zIsEmpty2 ? c2388h.f22173e : 0L, zIsEmpty3 ? 0L : c2388h.f22174f) + j3;
                                        View view4 = ((U) arrayList7.get(0)).f22219a;
                                        int[] iArr3 = F1.H.f2303a;
                                        view4.postOnAnimationDelayed(runnableC2382b3, jMax);
                                    }
                                }
                            }
                        }
                    }
                }
                recyclerView.f12152u0 = false;
                break;
            case 2:
                ((StaggeredGridLayoutManager) obj).x0();
                break;
            default:
                u2.T t10 = (u2.T) obj;
                for (u2.Z z6 : t10.f22712F) {
                    z6.l(true);
                    q2.h hVar = z6.f22774h;
                    if (hVar != null) {
                        hVar.d(z6.f22771e);
                        z6.f22774h = null;
                        z6.f22773g = null;
                    }
                }
                h3.y yVar = t10.f22745y;
                B2.r rVar = (B2.r) yVar.f15500n;
                if (rVar != null) {
                    rVar.a();
                    yVar.f15500n = null;
                }
                yVar.o = null;
                break;
        }
    }
}
