package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.util.Xml;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.motion.widget.a;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$id;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.a;
import defpackage.a41;
import defpackage.b70;
import defpackage.bj1;
import defpackage.ej1;
import defpackage.f41;
import defpackage.hf0;
import defpackage.tn3;
import defpackage.y31;
import defpackage.z8;
import defpackage.zi1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewTransition.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public int a;
    public int e;
    public final f41 f;
    public final a.C0004a g;
    public int j;
    public String k;
    public final Context o;
    public int b = -1;
    public boolean c = false;
    public int d = 0;
    public int h = -1;
    public int i = -1;
    public int l = 0;
    public String m = null;
    public int n = -1;
    public int p = -1;
    public int q = -1;
    public int r = -1;
    public int s = -1;
    public int t = -1;
    public int u = -1;

    /* JADX INFO: compiled from: ViewTransition.java */
    public static class a {
        public final int a;
        public final int b;
        public final bj1 c;
        public final int d;
        public final d f;
        public final Interpolator g;
        public float i;
        public float j;
        public final boolean m;
        public final a41 e = new a41();
        public boolean h = false;
        public final Rect l = new Rect();
        public long k = System.nanoTime();

        public a(d dVar, bj1 bj1Var, int i, int i2, int i3, Interpolator interpolator, int i4, int i5) {
            this.m = false;
            this.f = dVar;
            this.c = bj1Var;
            this.d = i2;
            if (dVar.e == null) {
                dVar.e = new ArrayList<>();
            }
            dVar.e.add(this);
            this.g = interpolator;
            this.a = i4;
            this.b = i5;
            if (i3 == 3) {
                this.m = true;
            }
            this.j = i == 0 ? Float.MAX_VALUE : 1.0f / i;
            a();
        }

        public final void a() {
            boolean z = this.h;
            int i = this.b;
            int i2 = this.a;
            d dVar = this.f;
            Interpolator interpolator = this.g;
            bj1 bj1Var = this.c;
            if (z) {
                long jNanoTime = System.nanoTime();
                long j = jNanoTime - this.k;
                this.k = jNanoTime;
                float f = this.i - (((float) (j * 1.0E-6d)) * this.j);
                this.i = f;
                if (f < 0.0f) {
                    this.i = 0.0f;
                }
                boolean zE = bj1Var.e(interpolator == null ? this.i : interpolator.getInterpolation(this.i), jNanoTime, bj1Var.b, this.e);
                if (this.i <= 0.0f) {
                    if (i2 != -1) {
                        bj1Var.b.setTag(i2, Long.valueOf(System.nanoTime()));
                    }
                    if (i != -1) {
                        bj1Var.b.setTag(i, null);
                    }
                    dVar.f.add(this);
                }
                if (this.i > 0.0f || zE) {
                    dVar.a.invalidate();
                    return;
                }
                return;
            }
            long jNanoTime2 = System.nanoTime();
            long j2 = jNanoTime2 - this.k;
            this.k = jNanoTime2;
            float f2 = (((float) (j2 * 1.0E-6d)) * this.j) + this.i;
            this.i = f2;
            if (f2 >= 1.0f) {
                this.i = 1.0f;
            }
            boolean zE2 = bj1Var.e(interpolator == null ? this.i : interpolator.getInterpolation(this.i), jNanoTime2, bj1Var.b, this.e);
            if (this.i >= 1.0f) {
                if (i2 != -1) {
                    bj1Var.b.setTag(i2, Long.valueOf(System.nanoTime()));
                }
                if (i != -1) {
                    bj1Var.b.setTag(i, null);
                }
                if (!this.m) {
                    dVar.f.add(this);
                }
            }
            if (this.i < 1.0f || zE2) {
                dVar.a.invalidate();
            }
        }

        public final void b() {
            this.h = true;
            int i = this.d;
            if (i != -1) {
                this.j = i == 0 ? Float.MAX_VALUE : 1.0f / i;
            }
            this.f.a.invalidate();
            this.k = System.nanoTime();
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public c(android.content.Context r11, android.content.res.XmlResourceParser r12) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.c.<init>(android.content.Context, android.content.res.XmlResourceParser):void");
    }

    public final void a(d dVar, MotionLayout motionLayout, int i, androidx.constraintlayout.widget.a aVar, View... viewArr) {
        Interpolator interpolatorLoadInterpolator;
        Interpolator tn3Var;
        if (this.c) {
            return;
        }
        int i2 = this.e;
        int i3 = 0;
        int i4 = 1;
        f41 f41Var = this.f;
        if (i2 == 2) {
            View view = viewArr[0];
            bj1 bj1Var = new bj1(view);
            ej1 ej1Var = bj1Var.f;
            ej1Var.g = 0.0f;
            ej1Var.h = 0.0f;
            bj1Var.H = true;
            ej1Var.g(view.getX(), view.getY(), view.getWidth(), view.getHeight());
            bj1Var.g.g(view.getX(), view.getY(), view.getWidth(), view.getHeight());
            zi1 zi1Var = bj1Var.h;
            zi1Var.getClass();
            view.getX();
            view.getY();
            view.getWidth();
            view.getHeight();
            zi1Var.e(view);
            zi1 zi1Var2 = bj1Var.i;
            zi1Var2.getClass();
            view.getX();
            view.getY();
            view.getWidth();
            view.getHeight();
            zi1Var2.e(view);
            ArrayList<y31> arrayList = f41Var.a.get(-1);
            if (arrayList != null) {
                bj1Var.w.addAll(arrayList);
            }
            bj1Var.h(motionLayout.getWidth(), motionLayout.getHeight(), System.nanoTime());
            int i5 = this.h;
            int i6 = this.i;
            int i7 = this.b;
            Context context = motionLayout.getContext();
            int i8 = this.l;
            if (i8 == -2) {
                interpolatorLoadInterpolator = AnimationUtils.loadInterpolator(context, this.n);
            } else {
                if (i8 == -1) {
                    tn3Var = new tn3(hf0.c(this.m));
                    new a(dVar, bj1Var, i5, i6, i7, tn3Var, this.p, this.q);
                    return;
                }
                interpolatorLoadInterpolator = i8 != 0 ? i8 != 1 ? i8 != 2 ? i8 != 4 ? i8 != 5 ? i8 != 6 ? null : new AnticipateInterpolator() : new OvershootInterpolator() : new BounceInterpolator() : new DecelerateInterpolator() : new AccelerateInterpolator() : new AccelerateDecelerateInterpolator();
            }
            tn3Var = interpolatorLoadInterpolator;
            new a(dVar, bj1Var, i5, i6, i7, tn3Var, this.p, this.q);
            return;
        }
        a.C0004a c0004a = this.g;
        if (i2 == 1) {
            int[] constraintSetIds = motionLayout.getConstraintSetIds();
            int i9 = 0;
            while (i9 < constraintSetIds.length) {
                int i10 = constraintSetIds[i9];
                if (i10 != i) {
                    androidx.constraintlayout.motion.widget.a aVar2 = motionLayout.u;
                    androidx.constraintlayout.widget.a aVarB = aVar2 == null ? null : aVar2.b(i10);
                    int length = viewArr.length;
                    for (int i11 = i3; i11 < length; i11++) {
                        a.C0004a c0004aI = aVarB.i(viewArr[i11].getId());
                        if (c0004a != null) {
                            a.C0004a.C0005a c0005a = c0004a.h;
                            if (c0005a != null) {
                                c0005a.e(c0004aI);
                            }
                            c0004aI.g.putAll(c0004a.g);
                        }
                    }
                }
                i9++;
                i3 = 0;
            }
        }
        androidx.constraintlayout.widget.a aVar3 = new androidx.constraintlayout.widget.a();
        HashMap<Integer, a.C0004a> map = aVar3.g;
        map.clear();
        for (Integer num : aVar.g.keySet()) {
            a.C0004a c0004a2 = aVar.g.get(num);
            if (c0004a2 != null) {
                map.put(num, c0004a2.clone());
            }
        }
        for (View view2 : viewArr) {
            a.C0004a c0004aI2 = aVar3.i(view2.getId());
            if (c0004a != null) {
                a.C0004a.C0005a c0005a2 = c0004a.h;
                if (c0005a2 != null) {
                    c0005a2.e(c0004aI2);
                }
                c0004aI2.g.putAll(c0004a.g);
            }
        }
        motionLayout.J(i, aVar3);
        int i12 = R$id.view_transition;
        motionLayout.J(i12, aVar);
        motionLayout.F(i12);
        a.b bVar = new a.b(motionLayout.u, i12, i);
        for (View view3 : viewArr) {
            int i13 = this.h;
            if (i13 != -1) {
                bVar.h = Math.max(i13, 8);
            }
            bVar.p = this.d;
            int i14 = this.l;
            String str = this.m;
            int i15 = this.n;
            bVar.e = i14;
            bVar.f = str;
            bVar.g = i15;
            int id = view3.getId();
            if (f41Var != null) {
                ArrayList<y31> arrayList2 = f41Var.a.get(-1);
                f41 f41Var2 = new f41();
                Iterator<y31> it = arrayList2.iterator();
                while (it.hasNext()) {
                    y31 y31VarClone = it.next().clone();
                    y31VarClone.b = id;
                    f41Var2.b(y31VarClone);
                }
                bVar.k.add(f41Var2);
            }
        }
        motionLayout.setTransition(bVar);
        z8 z8Var = new z8(this, i4, viewArr);
        motionLayout.t(1.0f);
        motionLayout.z0 = z8Var;
    }

    public final boolean b(View view) {
        int i = this.r;
        boolean z = i == -1 || view.getTag(i) != null;
        int i2 = this.s;
        return z && (i2 == -1 || view.getTag(i2) == null);
    }

    public final boolean c(View view) {
        String str;
        if (view == null) {
            return false;
        }
        if ((this.j == -1 && this.k == null) || !b(view)) {
            return false;
        }
        if (view.getId() == this.j) {
            return true;
        }
        return this.k != null && (view.getLayoutParams() instanceof ConstraintLayout.LayoutParams) && (str = ((ConstraintLayout.LayoutParams) view.getLayoutParams()).Y) != null && str.matches(this.k);
    }

    public final void d(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.ViewTransition);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R$styleable.ViewTransition_android_id) {
                this.a = typedArrayObtainStyledAttributes.getResourceId(index, this.a);
            } else if (index == R$styleable.ViewTransition_motionTarget) {
                if (MotionLayout.J0) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.j);
                    this.j = resourceId;
                    if (resourceId == -1) {
                        this.k = typedArrayObtainStyledAttributes.getString(index);
                    }
                } else if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                    this.k = typedArrayObtainStyledAttributes.getString(index);
                } else {
                    this.j = typedArrayObtainStyledAttributes.getResourceId(index, this.j);
                }
            } else if (index == R$styleable.ViewTransition_onStateTransition) {
                this.b = typedArrayObtainStyledAttributes.getInt(index, this.b);
            } else if (index == R$styleable.ViewTransition_transitionDisable) {
                this.c = typedArrayObtainStyledAttributes.getBoolean(index, this.c);
            } else if (index == R$styleable.ViewTransition_pathMotionArc) {
                this.d = typedArrayObtainStyledAttributes.getInt(index, this.d);
            } else if (index == R$styleable.ViewTransition_duration) {
                this.h = typedArrayObtainStyledAttributes.getInt(index, this.h);
            } else if (index == R$styleable.ViewTransition_upDuration) {
                this.i = typedArrayObtainStyledAttributes.getInt(index, this.i);
            } else if (index == R$styleable.ViewTransition_viewTransitionMode) {
                this.e = typedArrayObtainStyledAttributes.getInt(index, this.e);
            } else if (index == R$styleable.ViewTransition_motionInterpolator) {
                int i2 = typedArrayObtainStyledAttributes.peekValue(index).type;
                if (i2 == 1) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                    this.n = resourceId2;
                    if (resourceId2 != -1) {
                        this.l = -2;
                    }
                } else if (i2 == 3) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.m = string;
                    if (string == null || string.indexOf("/") <= 0) {
                        this.l = -1;
                    } else {
                        this.n = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                        this.l = -2;
                    }
                } else {
                    this.l = typedArrayObtainStyledAttributes.getInteger(index, this.l);
                }
            } else if (index == R$styleable.ViewTransition_setsTag) {
                this.p = typedArrayObtainStyledAttributes.getResourceId(index, this.p);
            } else if (index == R$styleable.ViewTransition_clearsTag) {
                this.q = typedArrayObtainStyledAttributes.getResourceId(index, this.q);
            } else if (index == R$styleable.ViewTransition_ifTagSet) {
                this.r = typedArrayObtainStyledAttributes.getResourceId(index, this.r);
            } else if (index == R$styleable.ViewTransition_ifTagNotSet) {
                this.s = typedArrayObtainStyledAttributes.getResourceId(index, this.s);
            } else if (index == R$styleable.ViewTransition_SharedValueId) {
                this.u = typedArrayObtainStyledAttributes.getResourceId(index, this.u);
            } else if (index == R$styleable.ViewTransition_SharedValue) {
                this.t = typedArrayObtainStyledAttributes.getInteger(index, this.t);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final String toString() {
        return "ViewTransition(" + b70.c(this.o, this.a) + ")";
    }
}
