package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

/* JADX INFO: compiled from: KeyTrigger.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends e {
    HashMap<String, Method> A;
    private int g = -1;
    private String h = null;
    private int i;
    private String j;
    private String k;
    private int l;
    private int m;
    private View n;
    float o;
    private boolean p;
    private boolean q;
    private boolean r;
    private float s;
    private float t;
    private boolean u;
    int v;
    int w;
    int x;
    RectF y;
    RectF z;

    /* JADX INFO: compiled from: KeyTrigger.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f936a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f936a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.x7, 8);
            f936a.append(androidx.constraintlayout.widget.e.B7, 4);
            f936a.append(androidx.constraintlayout.widget.e.C7, 1);
            f936a.append(androidx.constraintlayout.widget.e.D7, 2);
            f936a.append(androidx.constraintlayout.widget.e.y7, 7);
            f936a.append(androidx.constraintlayout.widget.e.E7, 6);
            f936a.append(androidx.constraintlayout.widget.e.G7, 5);
            f936a.append(androidx.constraintlayout.widget.e.A7, 9);
            f936a.append(androidx.constraintlayout.widget.e.z7, 10);
            f936a.append(androidx.constraintlayout.widget.e.F7, 11);
            f936a.append(androidx.constraintlayout.widget.e.H7, 12);
            f936a.append(androidx.constraintlayout.widget.e.I7, 13);
            f936a.append(androidx.constraintlayout.widget.e.J7, 14);
        }

        public static void a(l lVar, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f936a.get(index)) {
                    case 1:
                        lVar.j = typedArray.getString(index);
                        break;
                    case 2:
                        lVar.k = typedArray.getString(index);
                        break;
                    case 3:
                    default:
                        Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + f936a.get(index));
                        break;
                    case 4:
                        lVar.h = typedArray.getString(index);
                        break;
                    case 5:
                        lVar.o = typedArray.getFloat(index, lVar.o);
                        break;
                    case 6:
                        lVar.l = typedArray.getResourceId(index, lVar.l);
                        break;
                    case 7:
                        if (MotionLayout.z) {
                            int resourceId = typedArray.getResourceId(index, lVar.f927c);
                            lVar.f927c = resourceId;
                            if (resourceId == -1) {
                                lVar.f928d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            lVar.f928d = typedArray.getString(index);
                        } else {
                            lVar.f927c = typedArray.getResourceId(index, lVar.f927c);
                        }
                        break;
                    case 8:
                        int integer = typedArray.getInteger(index, lVar.f926b);
                        lVar.f926b = integer;
                        lVar.s = (integer + 0.5f) / 100.0f;
                        break;
                    case 9:
                        lVar.m = typedArray.getResourceId(index, lVar.m);
                        break;
                    case 10:
                        lVar.u = typedArray.getBoolean(index, lVar.u);
                        break;
                    case 11:
                        lVar.i = typedArray.getResourceId(index, lVar.i);
                        break;
                    case 12:
                        lVar.x = typedArray.getResourceId(index, lVar.x);
                        break;
                    case 13:
                        lVar.v = typedArray.getResourceId(index, lVar.v);
                        break;
                    case 14:
                        lVar.w = typedArray.getResourceId(index, lVar.w);
                        break;
                }
            }
        }
    }

    public l() {
        int i = e.f925a;
        this.i = i;
        this.j = null;
        this.k = null;
        this.l = i;
        this.m = i;
        this.n = null;
        this.o = 0.1f;
        this.p = true;
        this.q = true;
        this.r = true;
        this.s = Float.NaN;
        this.u = false;
        this.v = i;
        this.w = i;
        this.x = i;
        this.y = new RectF();
        this.z = new RectF();
        this.A = new HashMap<>();
        this.f929e = 5;
        this.f = new HashMap<>();
    }

    private void A(String str, View view) {
        boolean z = str.length() == 1;
        if (!z) {
            str = str.substring(1).toLowerCase(Locale.ROOT);
        }
        for (String str2 : this.f.keySet()) {
            String lowerCase = str2.toLowerCase(Locale.ROOT);
            if (z || lowerCase.matches(str)) {
                ConstraintAttribute constraintAttribute = this.f.get(str2);
                if (constraintAttribute != null) {
                    constraintAttribute.a(view);
                }
            }
        }
    }

    private void B(RectF rectF, View view, boolean z) {
        rectF.top = view.getTop();
        rectF.bottom = view.getBottom();
        rectF.left = view.getLeft();
        rectF.right = view.getRight();
        if (z) {
            view.getMatrix().mapRect(rectF);
        }
    }

    private void z(String str, View view) {
        Method method;
        if (str == null) {
            return;
        }
        if (str.startsWith(".")) {
            A(str, view);
            return;
        }
        if (this.A.containsKey(str)) {
            method = this.A.get(str);
            if (method == null) {
                return;
            }
        } else {
            method = null;
        }
        if (method == null) {
            try {
                method = view.getClass().getMethod(str, new Class[0]);
                this.A.put(str, method);
            } catch (NoSuchMethodException unused) {
                this.A.put(str, null);
                Log.e("KeyTrigger", "Could not find method \"" + str + "\"on class " + view.getClass().getSimpleName() + " " + b.d(view));
                return;
            }
        }
        try {
            method.invoke(view, new Object[0]);
        } catch (Exception unused2) {
            Log.e("KeyTrigger", "Exception in call \"" + this.h + "\"on class " + view.getClass().getSimpleName() + " " + b.d(view));
        }
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void a(HashMap<String, b.e.a.a.d> map) {
    }

    @Override // androidx.constraintlayout.motion.widget.e
    /* JADX INFO: renamed from: b */
    public e clone() {
        return new l().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public e c(e eVar) {
        super.c(eVar);
        l lVar = (l) eVar;
        this.g = lVar.g;
        this.h = lVar.h;
        this.i = lVar.i;
        this.j = lVar.j;
        this.k = lVar.k;
        this.l = lVar.l;
        this.m = lVar.m;
        this.n = lVar.n;
        this.o = lVar.o;
        this.p = lVar.p;
        this.q = lVar.q;
        this.r = lVar.r;
        this.s = lVar.s;
        this.t = lVar.t;
        this.u = lVar.u;
        this.y = lVar.y;
        this.z = lVar.z;
        this.A = lVar.A;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void d(HashSet<String> hashSet) {
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void e(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.w7), context);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void y(float r10, android.view.View r11) {
        /*
            Method dump skipped, instruction units count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.l.y(float, android.view.View):void");
    }
}
