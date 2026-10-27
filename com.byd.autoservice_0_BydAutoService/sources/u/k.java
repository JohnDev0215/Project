package u;

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
import u.s;

/* JADX INFO: compiled from: KeyTimeCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f5137g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5138h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f5139i = Float.NaN;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f5140j = Float.NaN;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f5141k = Float.NaN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f5142l = Float.NaN;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f5143m = Float.NaN;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f5144n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f5145o = Float.NaN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f5146p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f5147q = Float.NaN;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f5148r = Float.NaN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f5149s = Float.NaN;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f5150t = Float.NaN;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f5151u = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f5152v = Float.NaN;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f5153w = 0.0f;

    /* JADX INFO: compiled from: KeyTimeCycle.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f5154a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f5154a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_alpha, 1);
            f5154a.append(R$styleable.KeyTimeCycle_android_elevation, 2);
            f5154a.append(R$styleable.KeyTimeCycle_android_rotation, 4);
            f5154a.append(R$styleable.KeyTimeCycle_android_rotationX, 5);
            f5154a.append(R$styleable.KeyTimeCycle_android_rotationY, 6);
            f5154a.append(R$styleable.KeyTimeCycle_android_scaleX, 7);
            f5154a.append(R$styleable.KeyTimeCycle_transitionPathRotate, 8);
            f5154a.append(R$styleable.KeyTimeCycle_transitionEasing, 9);
            f5154a.append(R$styleable.KeyTimeCycle_motionTarget, 10);
            f5154a.append(R$styleable.KeyTimeCycle_framePosition, 12);
            f5154a.append(R$styleable.KeyTimeCycle_curveFit, 13);
            f5154a.append(R$styleable.KeyTimeCycle_android_scaleY, 14);
            f5154a.append(R$styleable.KeyTimeCycle_android_translationX, 15);
            f5154a.append(R$styleable.KeyTimeCycle_android_translationY, 16);
            f5154a.append(R$styleable.KeyTimeCycle_android_translationZ, 17);
            f5154a.append(R$styleable.KeyTimeCycle_motionProgress, 18);
            f5154a.append(R$styleable.KeyTimeCycle_wavePeriod, 20);
            f5154a.append(R$styleable.KeyTimeCycle_waveOffset, 21);
            f5154a.append(R$styleable.KeyTimeCycle_waveShape, 19);
        }

        public static void a(k kVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArray.getIndex(i3);
                switch (f5154a.get(index)) {
                    case 1:
                        kVar.f5139i = typedArray.getFloat(index, kVar.f5139i);
                        break;
                    case 2:
                        kVar.f5140j = typedArray.getDimension(index, kVar.f5140j);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f5154a.get(index));
                        break;
                    case 4:
                        kVar.f5141k = typedArray.getFloat(index, kVar.f5141k);
                        break;
                    case 5:
                        kVar.f5142l = typedArray.getFloat(index, kVar.f5142l);
                        break;
                    case 6:
                        kVar.f5143m = typedArray.getFloat(index, kVar.f5143m);
                        break;
                    case 7:
                        kVar.f5145o = typedArray.getFloat(index, kVar.f5145o);
                        break;
                    case 8:
                        kVar.f5144n = typedArray.getFloat(index, kVar.f5144n);
                        break;
                    case 9:
                        kVar.f5137g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.f736y) {
                            int resourceId = typedArray.getResourceId(index, kVar.f5052c);
                            kVar.f5052c = resourceId;
                            if (resourceId == -1) {
                                kVar.f5053d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            kVar.f5053d = typedArray.getString(index);
                        } else {
                            kVar.f5052c = typedArray.getResourceId(index, kVar.f5052c);
                        }
                        break;
                    case 12:
                        kVar.f5051b = typedArray.getInt(index, kVar.f5051b);
                        break;
                    case 13:
                        kVar.f5138h = typedArray.getInteger(index, kVar.f5138h);
                        break;
                    case 14:
                        kVar.f5146p = typedArray.getFloat(index, kVar.f5146p);
                        break;
                    case 15:
                        kVar.f5147q = typedArray.getDimension(index, kVar.f5147q);
                        break;
                    case 16:
                        kVar.f5148r = typedArray.getDimension(index, kVar.f5148r);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            kVar.f5149s = typedArray.getDimension(index, kVar.f5149s);
                        }
                        break;
                    case 18:
                        kVar.f5150t = typedArray.getFloat(index, kVar.f5150t);
                        break;
                    case 19:
                        kVar.f5151u = typedArray.getInt(index, kVar.f5151u);
                        break;
                    case 20:
                        kVar.f5152v = typedArray.getFloat(index, kVar.f5152v);
                        break;
                    case 21:
                        if (typedArray.peekValue(index).type == 5) {
                            kVar.f5153w = typedArray.getDimension(index, kVar.f5153w);
                        } else {
                            kVar.f5153w = typedArray.getFloat(index, kVar.f5153w);
                        }
                        break;
                }
            }
        }
    }

    public k() {
        this.f5054e = 3;
        this.f5055f = new HashMap<>();
    }

    public void M(HashMap<String, s> map) {
        for (String str : map.keySet()) {
            s sVar = map.get(str);
            if (!str.startsWith("CUSTOM")) {
                switch (str) {
                    case "rotationX":
                        if (Float.isNaN(this.f5142l)) {
                            break;
                        } else {
                            sVar.e(this.f5051b, this.f5142l, this.f5152v, this.f5151u, this.f5153w);
                            break;
                        }
                        break;
                    case "rotationY":
                        if (Float.isNaN(this.f5143m)) {
                            break;
                        } else {
                            sVar.e(this.f5051b, this.f5143m, this.f5152v, this.f5151u, this.f5153w);
                            break;
                        }
                        break;
                    case "translationX":
                        if (Float.isNaN(this.f5147q)) {
                            break;
                        } else {
                            sVar.e(this.f5051b, this.f5147q, this.f5152v, this.f5151u, this.f5153w);
                            break;
                        }
                        break;
                    case "translationY":
                        if (Float.isNaN(this.f5148r)) {
                            break;
                        } else {
                            sVar.e(this.f5051b, this.f5148r, this.f5152v, this.f5151u, this.f5153w);
                            break;
                        }
                        break;
                    case "translationZ":
                        if (Float.isNaN(this.f5149s)) {
                            break;
                        } else {
                            sVar.e(this.f5051b, this.f5149s, this.f5152v, this.f5151u, this.f5153w);
                            break;
                        }
                        break;
                    case "progress":
                        if (Float.isNaN(this.f5150t)) {
                            break;
                        } else {
                            sVar.e(this.f5051b, this.f5150t, this.f5152v, this.f5151u, this.f5153w);
                            break;
                        }
                        break;
                    case "scaleX":
                        if (Float.isNaN(this.f5145o)) {
                            break;
                        } else {
                            sVar.e(this.f5051b, this.f5145o, this.f5152v, this.f5151u, this.f5153w);
                            break;
                        }
                        break;
                    case "scaleY":
                        if (Float.isNaN(this.f5146p)) {
                            break;
                        } else {
                            sVar.e(this.f5051b, this.f5146p, this.f5152v, this.f5151u, this.f5153w);
                            break;
                        }
                        break;
                    case "rotation":
                        if (Float.isNaN(this.f5141k)) {
                            break;
                        } else {
                            sVar.e(this.f5051b, this.f5141k, this.f5152v, this.f5151u, this.f5153w);
                            break;
                        }
                        break;
                    case "elevation":
                        if (Float.isNaN(this.f5140j)) {
                            break;
                        } else {
                            sVar.e(this.f5051b, this.f5140j, this.f5152v, this.f5151u, this.f5153w);
                            break;
                        }
                        break;
                    case "transitionPathRotate":
                        if (Float.isNaN(this.f5144n)) {
                            break;
                        } else {
                            sVar.e(this.f5051b, this.f5144n, this.f5152v, this.f5151u, this.f5153w);
                            break;
                        }
                        break;
                    case "alpha":
                        if (Float.isNaN(this.f5139i)) {
                            break;
                        } else {
                            sVar.e(this.f5051b, this.f5139i, this.f5152v, this.f5151u, this.f5153w);
                            break;
                        }
                        break;
                    default:
                        Log.e("KeyTimeCycles", "UNKNOWN addValues \"" + str + "\"");
                        break;
                }
            } else {
                ConstraintAttribute constraintAttribute = this.f5055f.get(str.substring(7));
                if (constraintAttribute != null) {
                    ((s.b) sVar).j(this.f5051b, constraintAttribute, this.f5152v, this.f5151u, this.f5153w);
                }
            }
        }
    }

    @Override // u.c
    public void a(HashMap<String, r> map) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    @Override // u.c
    public void b(HashSet<String> hashSet) {
        if (!Float.isNaN(this.f5139i)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f5140j)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f5141k)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.f5142l)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.f5143m)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.f5147q)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.f5148r)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.f5149s)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.f5144n)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.f5145o)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.f5146p)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.f5150t)) {
            hashSet.add("progress");
        }
        if (this.f5055f.size() > 0) {
            Iterator<String> it = this.f5055f.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // u.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyTimeCycle));
    }

    @Override // u.c
    public void e(HashMap<String, Integer> map) {
        if (this.f5138h == -1) {
            return;
        }
        if (!Float.isNaN(this.f5139i)) {
            map.put("alpha", Integer.valueOf(this.f5138h));
        }
        if (!Float.isNaN(this.f5140j)) {
            map.put("elevation", Integer.valueOf(this.f5138h));
        }
        if (!Float.isNaN(this.f5141k)) {
            map.put("rotation", Integer.valueOf(this.f5138h));
        }
        if (!Float.isNaN(this.f5142l)) {
            map.put("rotationX", Integer.valueOf(this.f5138h));
        }
        if (!Float.isNaN(this.f5143m)) {
            map.put("rotationY", Integer.valueOf(this.f5138h));
        }
        if (!Float.isNaN(this.f5147q)) {
            map.put("translationX", Integer.valueOf(this.f5138h));
        }
        if (!Float.isNaN(this.f5148r)) {
            map.put("translationY", Integer.valueOf(this.f5138h));
        }
        if (!Float.isNaN(this.f5149s)) {
            map.put("translationZ", Integer.valueOf(this.f5138h));
        }
        if (!Float.isNaN(this.f5144n)) {
            map.put("transitionPathRotate", Integer.valueOf(this.f5138h));
        }
        if (!Float.isNaN(this.f5145o)) {
            map.put("scaleX", Integer.valueOf(this.f5138h));
        }
        if (!Float.isNaN(this.f5145o)) {
            map.put("scaleY", Integer.valueOf(this.f5138h));
        }
        if (!Float.isNaN(this.f5150t)) {
            map.put("progress", Integer.valueOf(this.f5138h));
        }
        if (this.f5055f.size() > 0) {
            Iterator<String> it = this.f5055f.keySet().iterator();
            while (it.hasNext()) {
                map.put("CUSTOM," + it.next(), Integer.valueOf(this.f5138h));
            }
        }
    }
}
