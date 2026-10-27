package b.f.a.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import b.f.a.b.s;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyTimeCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f2082g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2083h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f2084i = Float.NaN;
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

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f2085a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2085a = sparseIntArray;
            sparseIntArray.append(b.f.c.e.KeyTimeCycle_android_alpha, 1);
            f2085a.append(b.f.c.e.KeyTimeCycle_android_elevation, 2);
            f2085a.append(b.f.c.e.KeyTimeCycle_android_rotation, 4);
            f2085a.append(b.f.c.e.KeyTimeCycle_android_rotationX, 5);
            f2085a.append(b.f.c.e.KeyTimeCycle_android_rotationY, 6);
            f2085a.append(b.f.c.e.KeyTimeCycle_android_scaleX, 7);
            f2085a.append(b.f.c.e.KeyTimeCycle_transitionPathRotate, 8);
            f2085a.append(b.f.c.e.KeyTimeCycle_transitionEasing, 9);
            f2085a.append(b.f.c.e.KeyTimeCycle_motionTarget, 10);
            f2085a.append(b.f.c.e.KeyTimeCycle_framePosition, 12);
            f2085a.append(b.f.c.e.KeyTimeCycle_curveFit, 13);
            f2085a.append(b.f.c.e.KeyTimeCycle_android_scaleY, 14);
            f2085a.append(b.f.c.e.KeyTimeCycle_android_translationX, 15);
            f2085a.append(b.f.c.e.KeyTimeCycle_android_translationY, 16);
            f2085a.append(b.f.c.e.KeyTimeCycle_android_translationZ, 17);
            f2085a.append(b.f.c.e.KeyTimeCycle_motionProgress, 18);
            f2085a.append(b.f.c.e.KeyTimeCycle_wavePeriod, 20);
            f2085a.append(b.f.c.e.KeyTimeCycle_waveOffset, 21);
            f2085a.append(b.f.c.e.KeyTimeCycle_waveShape, 19);
        }

        public static void a(k kVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f2085a.get(index)) {
                    case 1:
                        kVar.f2084i = typedArray.getFloat(index, kVar.f2084i);
                        break;
                    case 2:
                        kVar.j = typedArray.getDimension(index, kVar.j);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f2085a.get(index));
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
                        kVar.f2082g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, kVar.f2040c);
                            kVar.f2040c = resourceId;
                            if (resourceId == -1) {
                                kVar.f2041d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            kVar.f2041d = typedArray.getString(index);
                        } else {
                            kVar.f2040c = typedArray.getResourceId(index, kVar.f2040c);
                        }
                        break;
                    case 12:
                        kVar.f2039b = typedArray.getInt(index, kVar.f2039b);
                        break;
                    case 13:
                        kVar.f2083h = typedArray.getInteger(index, kVar.f2083h);
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
        this.f2042e = 3;
        this.f2043f = new HashMap<>();
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
                            sVar.e(this.f2039b, this.l, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "rotationY":
                        if (Float.isNaN(this.m)) {
                            break;
                        } else {
                            sVar.e(this.f2039b, this.m, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "translationX":
                        if (Float.isNaN(this.q)) {
                            break;
                        } else {
                            sVar.e(this.f2039b, this.q, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "translationY":
                        if (Float.isNaN(this.r)) {
                            break;
                        } else {
                            sVar.e(this.f2039b, this.r, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "translationZ":
                        if (Float.isNaN(this.s)) {
                            break;
                        } else {
                            sVar.e(this.f2039b, this.s, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "progress":
                        if (Float.isNaN(this.t)) {
                            break;
                        } else {
                            sVar.e(this.f2039b, this.t, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "scaleX":
                        if (Float.isNaN(this.o)) {
                            break;
                        } else {
                            sVar.e(this.f2039b, this.o, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "scaleY":
                        if (Float.isNaN(this.p)) {
                            break;
                        } else {
                            sVar.e(this.f2039b, this.p, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "rotation":
                        if (Float.isNaN(this.k)) {
                            break;
                        } else {
                            sVar.e(this.f2039b, this.k, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "elevation":
                        if (Float.isNaN(this.j)) {
                            break;
                        } else {
                            sVar.e(this.f2039b, this.j, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "transitionPathRotate":
                        if (Float.isNaN(this.n)) {
                            break;
                        } else {
                            sVar.e(this.f2039b, this.n, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    case "alpha":
                        if (Float.isNaN(this.f2084i)) {
                            break;
                        } else {
                            sVar.e(this.f2039b, this.f2084i, this.v, this.u, this.w);
                            break;
                        }
                        break;
                    default:
                        Log.e("KeyTimeCycles", "UNKNOWN addValues \"" + str + "\"");
                        break;
                }
            } else {
                ConstraintAttribute constraintAttribute = this.f2043f.get(str.substring(7));
                if (constraintAttribute != null) {
                    ((s.b) sVar).j(this.f2039b, constraintAttribute, this.v, this.u, this.w);
                }
            }
        }
    }

    @Override // b.f.a.b.c
    public void a(HashMap<String, r> map) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    @Override // b.f.a.b.c
    public void b(HashSet<String> hashSet) {
        if (!Float.isNaN(this.f2084i)) {
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
        if (this.f2043f.size() > 0) {
            Iterator<String> it = this.f2043f.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // b.f.a.b.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, b.f.c.e.KeyTimeCycle));
    }

    @Override // b.f.a.b.c
    public void e(HashMap<String, Integer> map) {
        if (this.f2083h == -1) {
            return;
        }
        if (!Float.isNaN(this.f2084i)) {
            map.put("alpha", Integer.valueOf(this.f2083h));
        }
        if (!Float.isNaN(this.j)) {
            map.put("elevation", Integer.valueOf(this.f2083h));
        }
        if (!Float.isNaN(this.k)) {
            map.put("rotation", Integer.valueOf(this.f2083h));
        }
        if (!Float.isNaN(this.l)) {
            map.put("rotationX", Integer.valueOf(this.f2083h));
        }
        if (!Float.isNaN(this.m)) {
            map.put("rotationY", Integer.valueOf(this.f2083h));
        }
        if (!Float.isNaN(this.q)) {
            map.put("translationX", Integer.valueOf(this.f2083h));
        }
        if (!Float.isNaN(this.r)) {
            map.put("translationY", Integer.valueOf(this.f2083h));
        }
        if (!Float.isNaN(this.s)) {
            map.put("translationZ", Integer.valueOf(this.f2083h));
        }
        if (!Float.isNaN(this.n)) {
            map.put("transitionPathRotate", Integer.valueOf(this.f2083h));
        }
        if (!Float.isNaN(this.o)) {
            map.put("scaleX", Integer.valueOf(this.f2083h));
        }
        if (!Float.isNaN(this.o)) {
            map.put("scaleY", Integer.valueOf(this.f2083h));
        }
        if (!Float.isNaN(this.t)) {
            map.put("progress", Integer.valueOf(this.f2083h));
        }
        if (this.f2043f.size() > 0) {
            Iterator<String> it = this.f2043f.keySet().iterator();
            while (it.hasNext()) {
                map.put("CUSTOM," + it.next(), Integer.valueOf(this.f2083h));
            }
        }
    }
}
