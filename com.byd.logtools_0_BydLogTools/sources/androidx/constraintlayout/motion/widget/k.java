package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.s;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyTimeCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f829g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f830h = -1;
    private float i = Float.NaN;
    private float j = Float.NaN;
    private float k = Float.NaN;
    private float l = Float.NaN;
    private float m = Float.NaN;
    private float n = Float.NaN;
    private float o = Float.NaN;
    private float p = Float.NaN;
    private float q = Float.NaN;
    private float r = Float.NaN;
    private float s = Float.NaN;
    private float t = Float.NaN;
    private int u = 0;
    private float v = Float.NaN;
    private float w = 0.0f;

    /* JADX INFO: compiled from: KeyTimeCycle.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f831a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f831a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.C4, 1);
            f831a.append(androidx.constraintlayout.widget.e.L4, 2);
            f831a.append(androidx.constraintlayout.widget.e.H4, 4);
            f831a.append(androidx.constraintlayout.widget.e.I4, 5);
            f831a.append(androidx.constraintlayout.widget.e.J4, 6);
            f831a.append(androidx.constraintlayout.widget.e.F4, 7);
            f831a.append(androidx.constraintlayout.widget.e.R4, 8);
            f831a.append(androidx.constraintlayout.widget.e.Q4, 9);
            f831a.append(androidx.constraintlayout.widget.e.P4, 10);
            f831a.append(androidx.constraintlayout.widget.e.N4, 12);
            f831a.append(androidx.constraintlayout.widget.e.M4, 13);
            f831a.append(androidx.constraintlayout.widget.e.G4, 14);
            f831a.append(androidx.constraintlayout.widget.e.D4, 15);
            f831a.append(androidx.constraintlayout.widget.e.E4, 16);
            f831a.append(androidx.constraintlayout.widget.e.K4, 17);
            f831a.append(androidx.constraintlayout.widget.e.O4, 18);
            f831a.append(androidx.constraintlayout.widget.e.T4, 20);
            f831a.append(androidx.constraintlayout.widget.e.S4, 21);
            f831a.append(androidx.constraintlayout.widget.e.U4, 19);
        }

        public static void a(k kVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f831a.get(index)) {
                    case 1:
                        kVar.i = typedArray.getFloat(index, kVar.i);
                        break;
                    case 2:
                        kVar.j = typedArray.getDimension(index, kVar.j);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f831a.get(index));
                        break;
                    case 4:
                        kVar.k = typedArray.getFloat(index, kVar.k);
                        break;
                    case 5:
                        kVar.l = typedArray.getFloat(index, kVar.l);
                        break;
                    case 6:
                        kVar.m = typedArray.getFloat(index, kVar.m);
                        break;
                    case 7:
                        kVar.o = typedArray.getFloat(index, kVar.o);
                        break;
                    case 8:
                        kVar.n = typedArray.getFloat(index, kVar.n);
                        break;
                    case 9:
                        kVar.f829g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, kVar.f791c);
                            kVar.f791c = resourceId;
                            if (resourceId == -1) {
                                kVar.f792d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            kVar.f792d = typedArray.getString(index);
                        } else {
                            kVar.f791c = typedArray.getResourceId(index, kVar.f791c);
                        }
                        break;
                    case 12:
                        kVar.f790b = typedArray.getInt(index, kVar.f790b);
                        break;
                    case 13:
                        kVar.f830h = typedArray.getInteger(index, kVar.f830h);
                        break;
                    case 14:
                        kVar.p = typedArray.getFloat(index, kVar.p);
                        break;
                    case 15:
                        kVar.q = typedArray.getDimension(index, kVar.q);
                        break;
                    case 16:
                        kVar.r = typedArray.getDimension(index, kVar.r);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            kVar.s = typedArray.getDimension(index, kVar.s);
                        }
                        break;
                    case 18:
                        kVar.t = typedArray.getFloat(index, kVar.t);
                        break;
                    case 19:
                        kVar.u = typedArray.getInt(index, kVar.u);
                        break;
                    case 20:
                        kVar.v = typedArray.getFloat(index, kVar.v);
                        break;
                    case 21:
                        if (typedArray.peekValue(index).type == 5) {
                            kVar.w = typedArray.getDimension(index, kVar.w);
                        } else {
                            kVar.w = typedArray.getFloat(index, kVar.w);
                        }
                        break;
                }
            }
        }
    }

    public k() {
        this.f793e = 3;
        this.f794f = new HashMap<>();
    }

    public void M(HashMap<String, s> map) {
        for (String str : map.keySet()) {
            s sVar = map.get(str);
            if (!str.startsWith("CUSTOM")) {
                switch (str) {
                    case "rotationX":
                        if (Float.isNaN(this.l)) {
                            break;
                        } else {
                            sVar.e(this.f790b, this.l, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "rotationY":
                        if (Float.isNaN(this.m)) {
                            break;
                        } else {
                            sVar.e(this.f790b, this.m, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "translationX":
                        if (Float.isNaN(this.q)) {
                            break;
                        } else {
                            sVar.e(this.f790b, this.q, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "translationY":
                        if (Float.isNaN(this.r)) {
                            break;
                        } else {
                            sVar.e(this.f790b, this.r, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "translationZ":
                        if (Float.isNaN(this.s)) {
                            break;
                        } else {
                            sVar.e(this.f790b, this.s, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "progress":
                        if (Float.isNaN(this.t)) {
                            break;
                        } else {
                            sVar.e(this.f790b, this.t, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "scaleX":
                        if (Float.isNaN(this.o)) {
                            break;
                        } else {
                            sVar.e(this.f790b, this.o, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "scaleY":
                        if (Float.isNaN(this.p)) {
                            break;
                        } else {
                            sVar.e(this.f790b, this.p, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "rotation":
                        if (Float.isNaN(this.k)) {
                            break;
                        } else {
                            sVar.e(this.f790b, this.k, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "elevation":
                        if (Float.isNaN(this.j)) {
                            break;
                        } else {
                            sVar.e(this.f790b, this.j, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "transitionPathRotate":
                        if (Float.isNaN(this.n)) {
                            break;
                        } else {
                            sVar.e(this.f790b, this.n, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "alpha":
                        if (Float.isNaN(this.i)) {
                            break;
                        } else {
                            sVar.e(this.f790b, this.i, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    default:
                        Log.e("KeyTimeCycles", "UNKNOWN addValues \"" + str + "\"");
                        break;
                }
            } else {
                ConstraintAttribute constraintAttribute = this.f794f.get(str.substring(7));
                if (constraintAttribute != null) {
                    ((s.b) sVar).j(this.f790b, constraintAttribute, this.v, this.u, this.w);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.c
    public void a(HashMap<String, r> map) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    @Override // androidx.constraintlayout.motion.widget.c
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
        if (this.f794f.size() > 0) {
            Iterator<String> it = this.f794f.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.B4));
    }

    @Override // androidx.constraintlayout.motion.widget.c
    public void e(HashMap<String, Integer> map) {
        if (this.f830h == -1) {
            return;
        }
        if (!Float.isNaN(this.i)) {
            map.put("alpha", Integer.valueOf(this.f830h));
        }
        if (!Float.isNaN(this.j)) {
            map.put("elevation", Integer.valueOf(this.f830h));
        }
        if (!Float.isNaN(this.k)) {
            map.put("rotation", Integer.valueOf(this.f830h));
        }
        if (!Float.isNaN(this.l)) {
            map.put("rotationX", Integer.valueOf(this.f830h));
        }
        if (!Float.isNaN(this.m)) {
            map.put("rotationY", Integer.valueOf(this.f830h));
        }
        if (!Float.isNaN(this.q)) {
            map.put("translationX", Integer.valueOf(this.f830h));
        }
        if (!Float.isNaN(this.r)) {
            map.put("translationY", Integer.valueOf(this.f830h));
        }
        if (!Float.isNaN(this.s)) {
            map.put("translationZ", Integer.valueOf(this.f830h));
        }
        if (!Float.isNaN(this.n)) {
            map.put("transitionPathRotate", Integer.valueOf(this.f830h));
        }
        if (!Float.isNaN(this.o)) {
            map.put("scaleX", Integer.valueOf(this.f830h));
        }
        if (!Float.isNaN(this.o)) {
            map.put("scaleY", Integer.valueOf(this.f830h));
        }
        if (!Float.isNaN(this.t)) {
            map.put("progress", Integer.valueOf(this.f830h));
        }
        if (this.f794f.size() > 0) {
            Iterator<String> it = this.f794f.keySet().iterator();
            while (it.hasNext()) {
                map.put("CUSTOM," + it.next(), Integer.valueOf(this.f830h));
            }
        }
    }
}
