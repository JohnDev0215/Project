package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyCycle.java */
/* JADX INFO: loaded from: classes.dex */
public final class b41 extends y31 {
    public int e = 0;
    public int f = -1;
    public String g = null;
    public float h = Float.NaN;
    public float i = 0.0f;
    public float j = 0.0f;
    public float k = Float.NaN;
    public int l = -1;
    public float m = Float.NaN;
    public float n = Float.NaN;
    public float o = Float.NaN;
    public float p = Float.NaN;
    public float q = Float.NaN;
    public float r = Float.NaN;
    public float s = Float.NaN;
    public float t = Float.NaN;
    public float u = Float.NaN;
    public float v = Float.NaN;
    public float w = Float.NaN;

    /* JADX INFO: compiled from: KeyCycle.java */
    public static class a {
        public static final SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyCycle_motionTarget, 1);
            sparseIntArray.append(R$styleable.KeyCycle_framePosition, 2);
            sparseIntArray.append(R$styleable.KeyCycle_transitionEasing, 3);
            sparseIntArray.append(R$styleable.KeyCycle_curveFit, 4);
            sparseIntArray.append(R$styleable.KeyCycle_waveShape, 5);
            sparseIntArray.append(R$styleable.KeyCycle_wavePeriod, 6);
            sparseIntArray.append(R$styleable.KeyCycle_waveOffset, 7);
            sparseIntArray.append(R$styleable.KeyCycle_waveVariesBy, 8);
            sparseIntArray.append(R$styleable.KeyCycle_android_alpha, 9);
            sparseIntArray.append(R$styleable.KeyCycle_android_elevation, 10);
            sparseIntArray.append(R$styleable.KeyCycle_android_rotation, 11);
            sparseIntArray.append(R$styleable.KeyCycle_android_rotationX, 12);
            sparseIntArray.append(R$styleable.KeyCycle_android_rotationY, 13);
            sparseIntArray.append(R$styleable.KeyCycle_transitionPathRotate, 14);
            sparseIntArray.append(R$styleable.KeyCycle_android_scaleX, 15);
            sparseIntArray.append(R$styleable.KeyCycle_android_scaleY, 16);
            sparseIntArray.append(R$styleable.KeyCycle_android_translationX, 17);
            sparseIntArray.append(R$styleable.KeyCycle_android_translationY, 18);
            sparseIntArray.append(R$styleable.KeyCycle_android_translationZ, 19);
            sparseIntArray.append(R$styleable.KeyCycle_motionProgress, 20);
            sparseIntArray.append(R$styleable.KeyCycle_wavePhase, 21);
        }
    }

    public b41() {
        this.d = new HashMap<>();
    }

    @Override // defpackage.y31
    public final void a(HashMap<String, rn3> map) {
        map.size();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int iMin = Math.min(2, stackTrace.length - 1);
        String str = " ";
        for (int i = 1; i <= iMin; i++) {
            stackTrace[i].getFileName();
            stackTrace[i].getLineNumber();
            stackTrace[i].getMethodName();
            str = str + " ";
        }
        for (String str2 : map.keySet()) {
            rn3 rn3Var = map.get(str2);
            if (rn3Var != null) {
                str2.getClass();
                switch (str2) {
                    case "rotationX":
                        rn3Var.b(this.q, this.a);
                        break;
                    case "rotationY":
                        rn3Var.b(this.r, this.a);
                        break;
                    case "translationX":
                        rn3Var.b(this.u, this.a);
                        break;
                    case "translationY":
                        rn3Var.b(this.v, this.a);
                        break;
                    case "translationZ":
                        rn3Var.b(this.w, this.a);
                        break;
                    case "progress":
                        rn3Var.b(this.k, this.a);
                        break;
                    case "scaleX":
                        rn3Var.b(this.s, this.a);
                        break;
                    case "scaleY":
                        rn3Var.b(this.t, this.a);
                        break;
                    case "rotation":
                        rn3Var.b(this.o, this.a);
                        break;
                    case "elevation":
                        rn3Var.b(this.n, this.a);
                        break;
                    case "transitionPathRotate":
                        rn3Var.b(this.p, this.a);
                        break;
                    case "alpha":
                        rn3Var.b(this.m, this.a);
                        break;
                    case "waveOffset":
                        rn3Var.b(this.i, this.a);
                        break;
                    case "wavePhase":
                        rn3Var.b(this.j, this.a);
                        break;
                    default:
                        str2.startsWith("CUSTOM");
                        break;
                }
            }
        }
    }

    @Override // defpackage.y31
    /* JADX INFO: renamed from: b */
    public final y31 clone() {
        b41 b41Var = new b41();
        super.c(this);
        b41Var.e = this.e;
        b41Var.f = this.f;
        b41Var.g = this.g;
        b41Var.h = this.h;
        b41Var.i = this.i;
        b41Var.j = this.j;
        b41Var.k = this.k;
        b41Var.l = this.l;
        b41Var.m = this.m;
        b41Var.n = this.n;
        b41Var.o = this.o;
        b41Var.p = this.p;
        b41Var.q = this.q;
        b41Var.r = this.r;
        b41Var.s = this.s;
        b41Var.t = this.t;
        b41Var.u = this.u;
        b41Var.v = this.v;
        b41Var.w = this.w;
        return b41Var;
    }

    @Override // defpackage.y31
    public final void d(HashSet<String> hashSet) {
        if (!Float.isNaN(this.m)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.u)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.v)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.w)) {
            hashSet.add("translationZ");
        }
        if (this.d.size() > 0) {
            Iterator<String> it = this.d.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // defpackage.y31
    public final void e(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.KeyCycle);
        SparseIntArray sparseIntArray = a.a;
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            SparseIntArray sparseIntArray2 = a.a;
            switch (sparseIntArray2.get(index)) {
                case 1:
                    if (MotionLayout.J0) {
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.b);
                        this.b = resourceId;
                        if (resourceId == -1) {
                            this.c = typedArrayObtainStyledAttributes.getString(index);
                        }
                    } else if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                        this.c = typedArrayObtainStyledAttributes.getString(index);
                    } else {
                        this.b = typedArrayObtainStyledAttributes.getResourceId(index, this.b);
                    }
                    break;
                case 2:
                    this.a = typedArrayObtainStyledAttributes.getInt(index, this.a);
                    break;
                case 3:
                    typedArrayObtainStyledAttributes.getString(index);
                    break;
                case 4:
                    this.e = typedArrayObtainStyledAttributes.getInteger(index, this.e);
                    break;
                case 5:
                    if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                        this.g = typedArrayObtainStyledAttributes.getString(index);
                        this.f = 7;
                    } else {
                        this.f = typedArrayObtainStyledAttributes.getInt(index, this.f);
                    }
                    break;
                case 6:
                    this.h = typedArrayObtainStyledAttributes.getFloat(index, this.h);
                    break;
                case 7:
                    if (typedArrayObtainStyledAttributes.peekValue(index).type == 5) {
                        this.i = typedArrayObtainStyledAttributes.getDimension(index, this.i);
                    } else {
                        this.i = typedArrayObtainStyledAttributes.getFloat(index, this.i);
                    }
                    break;
                case 8:
                    this.l = typedArrayObtainStyledAttributes.getInt(index, this.l);
                    break;
                case 9:
                    this.m = typedArrayObtainStyledAttributes.getFloat(index, this.m);
                    break;
                case 10:
                    this.n = typedArrayObtainStyledAttributes.getDimension(index, this.n);
                    break;
                case 11:
                    this.o = typedArrayObtainStyledAttributes.getFloat(index, this.o);
                    break;
                case 12:
                    this.q = typedArrayObtainStyledAttributes.getFloat(index, this.q);
                    break;
                case 13:
                    this.r = typedArrayObtainStyledAttributes.getFloat(index, this.r);
                    break;
                case 14:
                    this.p = typedArrayObtainStyledAttributes.getFloat(index, this.p);
                    break;
                case 15:
                    this.s = typedArrayObtainStyledAttributes.getFloat(index, this.s);
                    break;
                case 16:
                    this.t = typedArrayObtainStyledAttributes.getFloat(index, this.t);
                    break;
                case 17:
                    this.u = typedArrayObtainStyledAttributes.getDimension(index, this.u);
                    break;
                case 18:
                    this.v = typedArrayObtainStyledAttributes.getDimension(index, this.v);
                    break;
                case 19:
                    this.w = typedArrayObtainStyledAttributes.getDimension(index, this.w);
                    break;
                case 20:
                    this.k = typedArrayObtainStyledAttributes.getFloat(index, this.k);
                    break;
                case 21:
                    this.j = typedArrayObtainStyledAttributes.getFloat(index, this.j) / 360.0f;
                    break;
                default:
                    Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray2.get(index));
                    break;
            }
        }
    }
}
