package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.i5;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyTimeCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class a5 extends s4 {
    public String g;
    public int h = -1;
    public float i = Float.NaN;
    public float j = Float.NaN;
    public float k = Float.NaN;
    public float l = Float.NaN;
    public float m = Float.NaN;
    public float n = Float.NaN;
    public float o = Float.NaN;
    public float p = Float.NaN;
    public float q = Float.NaN;
    public float r = Float.NaN;
    public float s = Float.NaN;
    public float t = Float.NaN;
    public int u = 0;
    public float v = Float.NaN;
    public float w = 0.0f;

    /* JADX INFO: compiled from: KeyTimeCycle.java */
    public static class a {
        public static SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_alpha, 1);
            a.append(R$styleable.KeyTimeCycle_android_elevation, 2);
            a.append(R$styleable.KeyTimeCycle_android_rotation, 4);
            a.append(R$styleable.KeyTimeCycle_android_rotationX, 5);
            a.append(R$styleable.KeyTimeCycle_android_rotationY, 6);
            a.append(R$styleable.KeyTimeCycle_android_scaleX, 7);
            a.append(R$styleable.KeyTimeCycle_transitionPathRotate, 8);
            a.append(R$styleable.KeyTimeCycle_transitionEasing, 9);
            a.append(R$styleable.KeyTimeCycle_motionTarget, 10);
            a.append(R$styleable.KeyTimeCycle_framePosition, 12);
            a.append(R$styleable.KeyTimeCycle_curveFit, 13);
            a.append(R$styleable.KeyTimeCycle_android_scaleY, 14);
            a.append(R$styleable.KeyTimeCycle_android_translationX, 15);
            a.append(R$styleable.KeyTimeCycle_android_translationY, 16);
            a.append(R$styleable.KeyTimeCycle_android_translationZ, 17);
            a.append(R$styleable.KeyTimeCycle_motionProgress, 18);
            a.append(R$styleable.KeyTimeCycle_wavePeriod, 20);
            a.append(R$styleable.KeyTimeCycle_waveOffset, 21);
            a.append(R$styleable.KeyTimeCycle_waveShape, 19);
        }

        public static void a(a5 a5Var, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (a.get(index)) {
                    case 1:
                        a5Var.i = typedArray.getFloat(index, a5Var.i);
                        break;
                    case 2:
                        a5Var.j = typedArray.getDimension(index, a5Var.j);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + a.get(index));
                        break;
                    case 4:
                        a5Var.k = typedArray.getFloat(index, a5Var.k);
                        break;
                    case 5:
                        a5Var.l = typedArray.getFloat(index, a5Var.l);
                        break;
                    case 6:
                        a5Var.m = typedArray.getFloat(index, a5Var.m);
                        break;
                    case 7:
                        a5Var.o = typedArray.getFloat(index, a5Var.o);
                        break;
                    case 8:
                        a5Var.n = typedArray.getFloat(index, a5Var.n);
                        break;
                    case 9:
                        a5Var.g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, a5Var.c);
                            a5Var.c = resourceId;
                            if (resourceId == -1) {
                                a5Var.d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            a5Var.d = typedArray.getString(index);
                        } else {
                            a5Var.c = typedArray.getResourceId(index, a5Var.c);
                        }
                        break;
                    case 12:
                        a5Var.b = typedArray.getInt(index, a5Var.b);
                        break;
                    case 13:
                        a5Var.h = typedArray.getInteger(index, a5Var.h);
                        break;
                    case 14:
                        a5Var.p = typedArray.getFloat(index, a5Var.p);
                        break;
                    case 15:
                        a5Var.q = typedArray.getDimension(index, a5Var.q);
                        break;
                    case 16:
                        a5Var.r = typedArray.getDimension(index, a5Var.r);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            a5Var.s = typedArray.getDimension(index, a5Var.s);
                        }
                        break;
                    case 18:
                        a5Var.t = typedArray.getFloat(index, a5Var.t);
                        break;
                    case 19:
                        a5Var.u = typedArray.getInt(index, a5Var.u);
                        break;
                    case 20:
                        a5Var.v = typedArray.getFloat(index, a5Var.v);
                        break;
                    case 21:
                        if (typedArray.peekValue(index).type == 5) {
                            a5Var.w = typedArray.getDimension(index, a5Var.w);
                        } else {
                            a5Var.w = typedArray.getFloat(index, a5Var.w);
                        }
                        break;
                }
            }
        }
    }

    public a5() {
        this.e = 3;
        this.f = new HashMap<>();
    }

    public void M(HashMap<String, i5> map) {
        for (String str : map.keySet()) {
            i5 i5Var = map.get(str);
            if (!str.startsWith("CUSTOM")) {
                switch (str) {
                    case "rotationX":
                        if (Float.isNaN(this.l)) {
                            break;
                        } else {
                            i5Var.e(this.b, this.l, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "rotationY":
                        if (Float.isNaN(this.m)) {
                            break;
                        } else {
                            i5Var.e(this.b, this.m, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "translationX":
                        if (Float.isNaN(this.q)) {
                            break;
                        } else {
                            i5Var.e(this.b, this.q, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "translationY":
                        if (Float.isNaN(this.r)) {
                            break;
                        } else {
                            i5Var.e(this.b, this.r, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "translationZ":
                        if (Float.isNaN(this.s)) {
                            break;
                        } else {
                            i5Var.e(this.b, this.s, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "progress":
                        if (Float.isNaN(this.t)) {
                            break;
                        } else {
                            i5Var.e(this.b, this.t, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "scaleX":
                        if (Float.isNaN(this.o)) {
                            break;
                        } else {
                            i5Var.e(this.b, this.o, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "scaleY":
                        if (Float.isNaN(this.p)) {
                            break;
                        } else {
                            i5Var.e(this.b, this.p, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "rotation":
                        if (Float.isNaN(this.k)) {
                            break;
                        } else {
                            i5Var.e(this.b, this.k, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "elevation":
                        if (Float.isNaN(this.j)) {
                            break;
                        } else {
                            i5Var.e(this.b, this.j, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "transitionPathRotate":
                        if (Float.isNaN(this.n)) {
                            break;
                        } else {
                            i5Var.e(this.b, this.n, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "alpha":
                        if (Float.isNaN(this.i)) {
                            break;
                        } else {
                            i5Var.e(this.b, this.i, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    default:
                        Log.e("KeyTimeCycles", "UNKNOWN addValues \"" + str + "\"");
                        break;
                }
            } else {
                ConstraintAttribute constraintAttribute = this.f.get(str.substring(7));
                if (constraintAttribute != null) {
                    ((i5.b) i5Var).j(this.b, constraintAttribute, this.v, this.u, this.w);
                }
            }
        }
    }

    @Override // defpackage.s4
    public void a(HashMap<String, h5> map) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    @Override // defpackage.s4
    public void b(HashSet<String> hashSet) {
        if (!Float.isNaN(this.i)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.j)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.k)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.l)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.m)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("progress");
        }
        if (this.f.size() > 0) {
            Iterator<String> it = this.f.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // defpackage.s4
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyTimeCycle));
    }

    @Override // defpackage.s4
    public void e(HashMap<String, Integer> map) {
        if (this.h == -1) {
            return;
        }
        if (!Float.isNaN(this.i)) {
            map.put("alpha", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.j)) {
            map.put("elevation", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.k)) {
            map.put("rotation", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.l)) {
            map.put("rotationX", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.m)) {
            map.put("rotationY", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.q)) {
            map.put("translationX", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.r)) {
            map.put("translationY", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.s)) {
            map.put("translationZ", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.n)) {
            map.put("transitionPathRotate", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.o)) {
            map.put("scaleX", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.o)) {
            map.put("scaleY", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.t)) {
            map.put("progress", Integer.valueOf(this.h));
        }
        if (this.f.size() > 0) {
            Iterator<String> it = this.f.keySet().iterator();
            while (it.hasNext()) {
                map.put("CUSTOM," + it.next(), Integer.valueOf(this.h));
            }
        }
    }
}
