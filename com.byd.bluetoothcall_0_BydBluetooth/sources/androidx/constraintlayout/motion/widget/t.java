package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.motion.widget.s;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.f;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewTransitionController.java */
/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final MotionLayout f985a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashSet<View> f987c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ArrayList<s.b> f989e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ArrayList<s> f986b = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f988d = "ViewTransitionController";
    ArrayList<s.b> f = new ArrayList<>();

    /* JADX INFO: compiled from: ViewTransitionController.java */
    class a implements f.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ s f990b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f991c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f992d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ int f993e;

        a(s sVar, int i, boolean z, int i2) {
            this.f990b = sVar;
            this.f991c = i;
            this.f992d = z;
            this.f993e = i2;
        }
    }

    public t(MotionLayout motionLayout) {
        this.f985a = motionLayout;
    }

    private void f(s sVar, boolean z) {
        ConstraintLayout.getSharedValues().a(sVar.g(), new a(sVar, sVar.g(), z, sVar.f()));
    }

    private void j(s sVar, View... viewArr) {
        int currentState = this.f985a.getCurrentState();
        if (sVar.f == 2) {
            sVar.b(this, this.f985a, currentState, null, viewArr);
            return;
        }
        if (currentState != -1) {
            androidx.constraintlayout.widget.b bVarL0 = this.f985a.l0(currentState);
            if (bVarL0 == null) {
                return;
            }
            sVar.b(this, this.f985a, currentState, bVarL0, viewArr);
            return;
        }
        Log.w(this.f988d, "No support for ViewTransition within transition yet. Currently: " + this.f985a.toString());
    }

    public void a(s sVar) {
        this.f986b.add(sVar);
        this.f987c = null;
        if (sVar.h() == 4) {
            f(sVar, true);
        } else if (sVar.h() == 5) {
            f(sVar, false);
        }
    }

    void b(s.b bVar) {
        if (this.f989e == null) {
            this.f989e = new ArrayList<>();
        }
        this.f989e.add(bVar);
    }

    void c() {
        ArrayList<s.b> arrayList = this.f989e;
        if (arrayList == null) {
            return;
        }
        Iterator<s.b> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f989e.removeAll(this.f);
        this.f.clear();
        if (this.f989e.isEmpty()) {
            this.f989e = null;
        }
    }

    boolean d(int i, n nVar) {
        for (s sVar : this.f986b) {
            if (sVar.d() == i) {
                sVar.g.a(nVar);
                return true;
            }
        }
        return false;
    }

    void e() {
        this.f985a.invalidate();
    }

    void g(s.b bVar) {
        this.f.add(bVar);
    }

    void h(MotionEvent motionEvent) {
        s sVar;
        int currentState = this.f985a.getCurrentState();
        if (currentState == -1) {
            return;
        }
        if (this.f987c == null) {
            this.f987c = new HashSet<>();
            for (s sVar2 : this.f986b) {
                int childCount = this.f985a.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = this.f985a.getChildAt(i);
                    if (sVar2.k(childAt)) {
                        childAt.getId();
                        this.f987c.add(childAt);
                    }
                }
            }
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        Rect rect = new Rect();
        int action = motionEvent.getAction();
        ArrayList<s.b> arrayList = this.f989e;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<s.b> it = this.f989e.iterator();
            while (it.hasNext()) {
                it.next().d(action, x, y);
            }
        }
        if (action == 0 || action == 1) {
            androidx.constraintlayout.widget.b bVarL0 = this.f985a.l0(currentState);
            Iterator<s> it2 = this.f986b.iterator();
            while (it2.hasNext()) {
                s next = it2.next();
                if (next.m(action)) {
                    for (View view : this.f987c) {
                        if (next.k(view)) {
                            view.getHitRect(rect);
                            if (rect.contains((int) x, (int) y)) {
                                sVar = next;
                                next.b(this, this.f985a, currentState, bVarL0, view);
                            } else {
                                sVar = next;
                            }
                            next = sVar;
                        }
                    }
                }
            }
        }
    }

    void i(int i, View... viewArr) {
        ArrayList arrayList = new ArrayList();
        s sVar = null;
        for (s sVar2 : this.f986b) {
            if (sVar2.d() == i) {
                for (View view : viewArr) {
                    if (sVar2.c(view)) {
                        arrayList.add(view);
                    }
                }
                if (!arrayList.isEmpty()) {
                    j(sVar2, (View[]) arrayList.toArray(new View[0]));
                    arrayList.clear();
                }
                sVar = sVar2;
            }
        }
        if (sVar == null) {
            Log.e(this.f988d, " Could not find ViewTransition");
        }
    }
}
