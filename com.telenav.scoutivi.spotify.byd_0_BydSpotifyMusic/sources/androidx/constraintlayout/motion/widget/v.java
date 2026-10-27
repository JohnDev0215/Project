package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.motion.widget.u;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.j;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewTransitionController.java */
/* JADX INFO: loaded from: classes.dex */
public class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final MotionLayout f1636a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashSet<View> f1638c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ArrayList<u.b> f1640e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ArrayList<u> f1637b = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f1639d = "ViewTransitionController";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    ArrayList<u.b> f1641f = new ArrayList<>();

    /* JADX INFO: compiled from: ViewTransitionController.java */
    class a implements j.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ u f1642a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1643b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1644c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f1645d;

        a(u uVar, int i7, boolean z6, int i8) {
            this.f1642a = uVar;
            this.f1643b = i7;
            this.f1644c = z6;
            this.f1645d = i8;
        }
    }

    public v(MotionLayout motionLayout) {
        this.f1636a = motionLayout;
    }

    private void e(u uVar, boolean z6) {
        ConstraintLayout.getSharedValues().a(uVar.h(), new a(uVar, uVar.h(), z6, uVar.g()));
    }

    private void i(u uVar, View... viewArr) {
        int currentState = this.f1636a.getCurrentState();
        if (uVar.f1601e == 2) {
            uVar.c(this, this.f1636a, currentState, null, viewArr);
            return;
        }
        if (currentState != -1) {
            androidx.constraintlayout.widget.d dVarM0 = this.f1636a.m0(currentState);
            if (dVarM0 == null) {
                return;
            }
            uVar.c(this, this.f1636a, currentState, dVarM0, viewArr);
            return;
        }
        Log.w(this.f1639d, "No support for ViewTransition within transition yet. Currently: " + this.f1636a.toString());
    }

    public void a(u uVar) {
        this.f1637b.add(uVar);
        this.f1638c = null;
        if (uVar.i() == 4) {
            e(uVar, true);
        } else if (uVar.i() == 5) {
            e(uVar, false);
        }
    }

    void b(u.b bVar) {
        if (this.f1640e == null) {
            this.f1640e = new ArrayList<>();
        }
        this.f1640e.add(bVar);
    }

    void c() {
        ArrayList<u.b> arrayList = this.f1640e;
        if (arrayList == null) {
            return;
        }
        Iterator<u.b> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f1640e.removeAll(this.f1641f);
        this.f1641f.clear();
        if (this.f1640e.isEmpty()) {
            this.f1640e = null;
        }
    }

    void d() {
        this.f1636a.invalidate();
    }

    void f(u.b bVar) {
        this.f1641f.add(bVar);
    }

    void g(MotionEvent motionEvent) {
        u uVar;
        int currentState = this.f1636a.getCurrentState();
        if (currentState == -1) {
            return;
        }
        if (this.f1638c == null) {
            this.f1638c = new HashSet<>();
            for (u uVar2 : this.f1637b) {
                int childCount = this.f1636a.getChildCount();
                for (int i7 = 0; i7 < childCount; i7++) {
                    View childAt = this.f1636a.getChildAt(i7);
                    if (uVar2.k(childAt)) {
                        childAt.getId();
                        this.f1638c.add(childAt);
                    }
                }
            }
        }
        float x6 = motionEvent.getX();
        float y6 = motionEvent.getY();
        Rect rect = new Rect();
        int action = motionEvent.getAction();
        ArrayList<u.b> arrayList = this.f1640e;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<u.b> it = this.f1640e.iterator();
            while (it.hasNext()) {
                it.next().d(action, x6, y6);
            }
        }
        if (action == 0 || action == 1) {
            androidx.constraintlayout.widget.d dVarM0 = this.f1636a.m0(currentState);
            Iterator<u> it2 = this.f1637b.iterator();
            while (it2.hasNext()) {
                u next = it2.next();
                if (next.m(action)) {
                    for (View view : this.f1638c) {
                        if (next.k(view)) {
                            view.getHitRect(rect);
                            if (rect.contains((int) x6, (int) y6)) {
                                uVar = next;
                                next.c(this, this.f1636a, currentState, dVarM0, view);
                            } else {
                                uVar = next;
                            }
                            next = uVar;
                        }
                    }
                }
            }
        }
    }

    void h(int i7, View... viewArr) {
        ArrayList arrayList = new ArrayList();
        u uVar = null;
        for (u uVar2 : this.f1637b) {
            if (uVar2.e() == i7) {
                for (View view : viewArr) {
                    if (uVar2.d(view)) {
                        arrayList.add(view);
                    }
                }
                if (!arrayList.isEmpty()) {
                    i(uVar2, (View[]) arrayList.toArray(new View[0]));
                    arrayList.clear();
                }
                uVar = uVar2;
            }
        }
        if (uVar == null) {
            Log.e(this.f1639d, " Could not find ViewTransition");
        }
    }
}
