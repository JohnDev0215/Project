package x;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R$styleable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import x.s;

/* JADX INFO: compiled from: KeyTimeCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f8851g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f8852h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f8853i = Float.NaN;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f8854j = Float.NaN;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f8855k = Float.NaN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f8856l = Float.NaN;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f8857m = Float.NaN;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f8858n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f8859o = Float.NaN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f8860p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f8861q = Float.NaN;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f8862r = Float.NaN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f8863s = Float.NaN;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f8864t = Float.NaN;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f8865u = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f8866v = Float.NaN;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f8867w = 0.0f;

    /* JADX INFO: compiled from: KeyTimeCycle.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f8868a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f8868a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_alpha, 1);
            f8868a.append(R$styleable.KeyTimeCycle_android_elevation, 2);
            f8868a.append(R$styleable.KeyTimeCycle_android_rotation, 4);
            f8868a.append(R$styleable.KeyTimeCycle_android_rotationX, 5);
            f8868a.append(R$styleable.KeyTimeCycle_android_rotationY, 6);
            f8868a.append(R$styleable.KeyTimeCycle_android_scaleX, 7);
            f8868a.append(R$styleable.KeyTimeCycle_transitionPathRotate, 8);
            f8868a.append(R$styleable.KeyTimeCycle_transitionEasing, 9);
            f8868a.append(R$styleable.KeyTimeCycle_motionTarget, 10);
            f8868a.append(R$styleable.KeyTimeCycle_framePosition, 12);
            f8868a.append(R$styleable.KeyTimeCycle_curveFit, 13);
            f8868a.append(R$styleable.KeyTimeCycle_android_scaleY, 14);
            f8868a.append(R$styleable.KeyTimeCycle_android_translationX, 15);
            f8868a.append(R$styleable.KeyTimeCycle_android_translationY, 16);
            f8868a.append(R$styleable.KeyTimeCycle_android_translationZ, 17);
            f8868a.append(R$styleable.KeyTimeCycle_motionProgress, 18);
            f8868a.append(R$styleable.KeyTimeCycle_wavePeriod, 20);
            f8868a.append(R$styleable.KeyTimeCycle_waveOffset, 21);
            f8868a.append(R$styleable.KeyTimeCycle_waveShape, 19);
        }

        public static void a(k kVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArray.getIndex(i8);
                switch (f8868a.get(index)) {
                    case 1:
                        kVar.f8853i = typedArray.getFloat(index, kVar.f8853i);
                        break;
                    case 2:
                        kVar.f8854j = typedArray.getDimension(index, kVar.f8854j);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f8868a.get(index));
                        break;
                    case 4:
                        kVar.f8855k = typedArray.getFloat(index, kVar.f8855k);
                        break;
                    case 5:
                        kVar.f8856l = typedArray.getFloat(index, kVar.f8856l);
                        break;
                    case 6:
                        kVar.f8857m = typedArray.getFloat(index, kVar.f8857m);
                        break;
                    case 7:
                        kVar.f8859o = typedArray.getFloat(index, kVar.f8859o);
                        break;
                    case 8:
                        kVar.f8858n = typedArray.getFloat(index, kVar.f8858n);
                        break;
                    case 9:
                        kVar.f8851g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.A) {
                            int resourceId = typedArray.getResourceId(index, kVar.f8766c);
                            kVar.f8766c = resourceId;
                            if (resourceId == -1) {
                                kVar.f8767d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            kVar.f8767d = typedArray.getString(index);
                        } else {
                            kVar.f8766c = typedArray.getResourceId(index, kVar.f8766c);
                        }
                        break;
                    case 12:
                        kVar.f8765b = typedArray.getInt(index, kVar.f8765b);
                        break;
                    case 13:
                        kVar.f8852h = typedArray.getInteger(index, kVar.f8852h);
                        break;
                    case 14:
                        kVar.f8860p = typedArray.getFloat(index, kVar.f8860p);
                        break;
                    case 15:
                        kVar.f8861q = typedArray.getDimension(index, kVar.f8861q);
                        break;
                    case 16:
                        kVar.f8862r = typedArray.getDimension(index, kVar.f8862r);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            kVar.f8863s = typedArray.getDimension(index, kVar.f8863s);
                        }
                        break;
                    case 18:
                        kVar.f8864t = typedArray.getFloat(index, kVar.f8864t);
                        break;
                    case 19:
                        kVar.f8865u = typedArray.getInt(index, kVar.f8865u);
                        break;
                    case 20:
                        kVar.f8866v = typedArray.getFloat(index, kVar.f8866v);
                        break;
                    case 21:
                        if (typedArray.peekValue(index).type == 5) {
                            kVar.f8867w = typedArray.getDimension(index, kVar.f8867w);
                        } else {
                            kVar.f8867w = typedArray.getFloat(index, kVar.f8867w);
                        }
                        break;
                }
            }
        }
    }

    public k() {
        this.f8768e = 3;
        this.f8769f = new HashMap<>();
    }

    public void M(HashMap<String, s> map) {
        for (String str : map.keySet()) {
            s sVar = map.get(str);
            if (!str.startsWith("CUSTOM")) {
                switch (str) {
                    case "rotationX":
                        if (Float.isNaN(this.f8856l)) {
                            break;
                        } else {
                            sVar.e(this.f8765b, this.f8856l, this.f8866v, this.f8865u, this.f8867w);
                            break;
                        }
                        break;
                    case "rotationY":
                        if (Float.isNaN(this.f8857m)) {
                            break;
                        } else {
                            sVar.e(this.f8765b, this.f8857m, this.f8866v, this.f8865u, this.f8867w);
                            break;
                        }
                        break;
                    case "translationX":
                        if (Float.isNaN(this.f8861q)) {
                            break;
                        } else {
                            sVar.e(this.f8765b, this.f8861q, this.f8866v, this.f8865u, this.f8867w);
                            break;
                        }
                        break;
                    case "translationY":
                        if (Float.isNaN(this.f8862r)) {
                            break;
                        } else {
                            sVar.e(this.f8765b, this.f8862r, this.f8866v, this.f8865u, this.f8867w);
                            break;
                        }
                        break;
                    case "translationZ":
                        if (Float.isNaN(this.f8863s)) {
                            break;
                        } else {
                            sVar.e(this.f8765b, this.f8863s, this.f8866v, this.f8865u, this.f8867w);
                            break;
                        }
                        break;
                    case "progress":
                        if (Float.isNaN(this.f8864t)) {
                            break;
                        } else {
                            sVar.e(this.f8765b, this.f8864t, this.f8866v, this.f8865u, this.f8867w);
                            break;
                        }
                        break;
                    case "scaleX":
                        if (Float.isNaN(this.f8859o)) {
                            break;
                        } else {
                            sVar.e(this.f8765b, this.f8859o, this.f8866v, this.f8865u, this.f8867w);
                            break;
                        }
                        break;
                    case "scaleY":
                        if (Float.isNaN(this.f8860p)) {
                            break;
                        } else {
                            sVar.e(this.f8765b, this.f8860p, this.f8866v, this.f8865u, this.f8867w);
                            break;
                        }
                        break;
                    case "rotation":
                        if (Float.isNaN(this.f8855k)) {
                            break;
                        } else {
                            sVar.e(this.f8765b, this.f8855k, this.f8866v, this.f8865u, this.f8867w);
                            break;
                        }
                        break;
                    case "elevation":
                        if (Float.isNaN(this.f8854j)) {
                            break;
                        } else {
                            sVar.e(this.f8765b, this.f8854j, this.f8866v, this.f8865u, this.f8867w);
                            break;
                        }
                        break;
                    case "transitionPathRotate":
                        if (Float.isNaN(this.f8858n)) {
                            break;
                        } else {
                            sVar.e(this.f8765b, this.f8858n, this.f8866v, this.f8865u, this.f8867w);
                            break;
                        }
                        break;
                    case "alpha":
                        if (Float.isNaN(this.f8853i)) {
                            break;
                        } else {
                            sVar.e(this.f8765b, this.f8853i, this.f8866v, this.f8865u, this.f8867w);
                            break;
                        }
                        break;
                    default:
                        Log.e("KeyTimeCycles", "UNKNOWN addValues \"" + str + "\"");
                        break;
                }
            } else {
                ConstraintAttribute constraintAttribute = this.f8769f.get(str.substring(7));
                if (constraintAttribute != null) {
                    ((s.b) sVar).j(this.f8765b, constraintAttribute, this.f8866v, this.f8865u, this.f8867w);
                }
            }
        }
    }

    @Override // x.c
    public void a(HashMap<String, r> map) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    @Override // x.c
    public void b(HashSet<String> hashSet) {
        if (!Float.isNaN(this.f8853i)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f8854j)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f8855k)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.f8856l)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.f8857m)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.f8861q)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.f8862r)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.f8863s)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.f8858n)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.f8859o)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.f8860p)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.f8864t)) {
            hashSet.add("progress");
        }
        if (this.f8769f.size() > 0) {
            Iterator<String> it = this.f8769f.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // x.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyTimeCycle));
    }

    @Override // x.c
    public void e(HashMap<String, Integer> map) {
        if (this.f8852h == -1) {
            return;
        }
        if (!Float.isNaN(this.f8853i)) {
            map.put("alpha", Integer.valueOf(this.f8852h));
        }
        if (!Float.isNaN(this.f8854j)) {
            map.put("elevation", Integer.valueOf(this.f8852h));
        }
        if (!Float.isNaN(this.f8855k)) {
            map.put("rotation", Integer.valueOf(this.f8852h));
        }
        if (!Float.isNaN(this.f8856l)) {
            map.put("rotationX", Integer.valueOf(this.f8852h));
        }
        if (!Float.isNaN(this.f8857m)) {
            map.put("rotationY", Integer.valueOf(this.f8852h));
        }
        if (!Float.isNaN(this.f8861q)) {
            map.put("translationX", Integer.valueOf(this.f8852h));
        }
        if (!Float.isNaN(this.f8862r)) {
            map.put("translationY", Integer.valueOf(this.f8852h));
        }
        if (!Float.isNaN(this.f8863s)) {
            map.put("translationZ", Integer.valueOf(this.f8852h));
        }
        if (!Float.isNaN(this.f8858n)) {
            map.put("transitionPathRotate", Integer.valueOf(this.f8852h));
        }
        if (!Float.isNaN(this.f8859o)) {
            map.put("scaleX", Integer.valueOf(this.f8852h));
        }
        if (!Float.isNaN(this.f8859o)) {
            map.put("scaleY", Integer.valueOf(this.f8852h));
        }
        if (!Float.isNaN(this.f8864t)) {
            map.put("progress", Integer.valueOf(this.f8852h));
        }
        if (this.f8769f.size() > 0) {
            Iterator<String> it = this.f8769f.keySet().iterator();
            while (it.hasNext()) {
                map.put("CUSTOM," + it.next(), Integer.valueOf(this.f8852h));
            }
        }
    }
}
