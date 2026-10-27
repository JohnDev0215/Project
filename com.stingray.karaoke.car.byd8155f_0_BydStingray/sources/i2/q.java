package i2;

import F1.A;
import F1.E;
import F1.H;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import h2.AbstractC1343A;
import h2.t;
import h3.y;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import n.AbstractC1850a;
import r.AbstractC2225M;
import r.C2258q;
import u2.n0;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f15716a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f15717b;
    public final Object c;
    public Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f15718e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f15719f;

    public q(View view) {
        C2258q c2258q;
        this.f15716a = -1;
        this.f15717b = view;
        PorterDuff.Mode mode = C2258q.f21546b;
        synchronized (C2258q.class) {
            try {
                if (C2258q.c == null) {
                    C2258q.c();
                }
                c2258q = C2258q.c;
            } catch (Throwable th) {
                throw th;
            }
        }
        this.c = c2258q;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
    
        if (r8 < r0.f15715m) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(long r8, h2.t r10) {
        /*
            r7 = this;
            int r0 = r7.f15716a
            if (r0 == 0) goto La1
            java.lang.Object r1 = r7.f15718e
            java.util.PriorityQueue r1 = (java.util.PriorityQueue) r1
            r2 = -1
            if (r0 == r2) goto L23
            int r0 = r1.size()
            int r3 = r7.f15716a
            if (r0 < r3) goto L23
            java.lang.Object r0 = r1.peek()
            i2.o r0 = (i2.o) r0
            int r3 = h2.AbstractC1343A.f15195a
            long r3 = r0.f15715m
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 >= 0) goto L23
            goto La1
        L23:
            java.lang.Object r0 = r7.c
            java.util.ArrayDeque r0 = (java.util.ArrayDeque) r0
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L33
            h2.t r0 = new h2.t
            r0.<init>()
            goto L39
        L33:
            java.lang.Object r0 = r0.pop()
            h2.t r0 = (h2.t) r0
        L39:
            int r3 = r10.a()
            r0.E(r3)
            byte[] r3 = r10.f15241a
            int r10 = r10.f15242b
            byte[] r4 = r0.f15241a
            int r5 = r0.a()
            r6 = 0
            java.lang.System.arraycopy(r3, r10, r4, r6, r5)
            java.lang.Object r10 = r7.f15719f
            i2.o r10 = (i2.o) r10
            if (r10 == 0) goto L60
            long r3 = r10.f15715m
            int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r3 != 0) goto L60
            java.util.ArrayList r7 = r10.l
            r7.add(r0)
            return
        L60:
            java.lang.Object r10 = r7.d
            java.util.ArrayDeque r10 = (java.util.ArrayDeque) r10
            boolean r3 = r10.isEmpty()
            if (r3 == 0) goto L70
            i2.o r10 = new i2.o
            r10.<init>()
            goto L76
        L70:
            java.lang.Object r10 = r10.pop()
            i2.o r10 = (i2.o) r10
        L76:
            r10.getClass()
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r3 == 0) goto L83
            r6 = 1
        L83:
            h2.AbstractC1346c.c(r6)
            java.util.ArrayList r3 = r10.l
            boolean r4 = r3.isEmpty()
            h2.AbstractC1346c.j(r4)
            r10.f15715m = r8
            r3.add(r0)
            r1.add(r10)
            r7.f15719f = r10
            int r8 = r7.f15716a
            if (r8 == r2) goto La0
            r7.c(r8)
        La0:
            return
        La1:
            java.lang.Object r7 = r7.f15717b
            i2.p r7 = (i2.p) r7
            r7.d(r8, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: i2.q.a(long, h2.t):void");
    }

    public void b() {
        View view = (View) this.f15717b;
        Drawable background = view.getBackground();
        if (background != null) {
            if (((c9.m) this.d) != null) {
                if (((c9.m) this.f15719f) == null) {
                    this.f15719f = new c9.m();
                }
                c9.m mVar = (c9.m) this.f15719f;
                mVar.c = null;
                mVar.f12588b = false;
                mVar.d = null;
                mVar.f12587a = false;
                int[] iArr = H.f2303a;
                ColorStateList colorStateListC = A.c(view);
                if (colorStateListC != null) {
                    mVar.f12588b = true;
                    mVar.c = colorStateListC;
                }
                PorterDuff.Mode modeD = A.d(view);
                if (modeD != null) {
                    mVar.f12587a = true;
                    mVar.d = modeD;
                }
                if (mVar.f12588b || mVar.f12587a) {
                    C2258q.d(background, mVar, view.getDrawableState());
                    return;
                }
            }
            c9.m mVar2 = (c9.m) this.f15718e;
            if (mVar2 != null) {
                C2258q.d(background, mVar2, view.getDrawableState());
                return;
            }
            c9.m mVar3 = (c9.m) this.d;
            if (mVar3 != null) {
                C2258q.d(background, mVar3, view.getDrawableState());
            }
        }
    }

    public void c(int i7) {
        ArrayList arrayList;
        while (true) {
            PriorityQueue priorityQueue = (PriorityQueue) this.f15718e;
            if (priorityQueue.size() <= i7) {
                return;
            }
            o oVar = (o) priorityQueue.poll();
            int i10 = AbstractC1343A.f15195a;
            int i11 = 0;
            while (true) {
                int size = oVar.l.size();
                arrayList = oVar.l;
                if (i11 >= size) {
                    break;
                }
                ((p) this.f15717b).d(oVar.f15715m, (t) arrayList.get(i11));
                ((ArrayDeque) this.c).push((t) arrayList.get(i11));
                i11++;
            }
            arrayList.clear();
            o oVar2 = (o) this.f15719f;
            if (oVar2 != null && oVar2.f15715m == oVar.f15715m) {
                this.f15719f = null;
            }
            ((ArrayDeque) this.d).push(oVar);
        }
    }

    public ColorStateList d() {
        c9.m mVar = (c9.m) this.f15718e;
        if (mVar != null) {
            return (ColorStateList) mVar.c;
        }
        return null;
    }

    public PorterDuff.Mode e() {
        c9.m mVar = (c9.m) this.f15718e;
        if (mVar != null) {
            return (PorterDuff.Mode) mVar.d;
        }
        return null;
    }

    public void f(AttributeSet attributeSet, int i7) {
        ColorStateList colorStateListF;
        View view = (View) this.f15717b;
        Context context = view.getContext();
        int[] iArr = AbstractC1850a.f19367t;
        y yVarF = y.F(context, attributeSet, iArr, i7);
        TypedArray typedArray = (TypedArray) yVarF.f15500n;
        View view2 = (View) this.f15717b;
        Context context2 = view2.getContext();
        int[] iArr2 = H.f2303a;
        E.b(view2, context2, iArr, attributeSet, (TypedArray) yVarF.f15500n, i7, 0);
        try {
            if (typedArray.hasValue(0)) {
                this.f15716a = typedArray.getResourceId(0, -1);
                C2258q c2258q = (C2258q) this.c;
                Context context3 = view.getContext();
                int i10 = this.f15716a;
                synchronized (c2258q) {
                    colorStateListF = c2258q.f21547a.f(context3, i10);
                }
                if (colorStateListF != null) {
                    i(colorStateListF);
                }
            }
            if (typedArray.hasValue(1)) {
                A.h(view, yVarF.p(1));
            }
            if (typedArray.hasValue(2)) {
                A.i(view, AbstractC2225M.b(typedArray.getInt(2, -1), null));
            }
        } finally {
            yVarF.G();
        }
    }

    public void g() {
        this.f15716a = -1;
        i(null);
        b();
    }

    public void h(int i7) {
        ColorStateList colorStateListF;
        this.f15716a = i7;
        C2258q c2258q = (C2258q) this.c;
        if (c2258q != null) {
            Context context = ((View) this.f15717b).getContext();
            synchronized (c2258q) {
                colorStateListF = c2258q.f21547a.f(context, i7);
            }
        } else {
            colorStateListF = null;
        }
        i(colorStateListF);
        b();
    }

    public void i(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (((c9.m) this.d) == null) {
                this.d = new c9.m();
            }
            c9.m mVar = (c9.m) this.d;
            mVar.c = colorStateList;
            mVar.f12588b = true;
        } else {
            this.d = null;
        }
        b();
    }

    public void j(ColorStateList colorStateList) {
        if (((c9.m) this.f15718e) == null) {
            this.f15718e = new c9.m();
        }
        c9.m mVar = (c9.m) this.f15718e;
        mVar.c = colorStateList;
        mVar.f12588b = true;
        b();
    }

    public void k(PorterDuff.Mode mode) {
        if (((c9.m) this.f15718e) == null) {
            this.f15718e = new c9.m();
        }
        c9.m mVar = (c9.m) this.f15718e;
        mVar.d = mode;
        mVar.f12587a = true;
        b();
    }

    public q(p pVar) {
        this.f15717b = pVar;
        this.c = new ArrayDeque();
        this.d = new ArrayDeque();
        this.f15718e = new PriorityQueue();
        this.f15716a = -1;
    }

    public q(int[] iArr, n0[] n0VarArr, int[] iArr2, int[][][] iArr3, n0 n0Var) {
        this.f15717b = iArr;
        this.c = n0VarArr;
        this.f15718e = iArr3;
        this.d = iArr2;
        this.f15719f = n0Var;
        this.f15716a = iArr.length;
    }
}
