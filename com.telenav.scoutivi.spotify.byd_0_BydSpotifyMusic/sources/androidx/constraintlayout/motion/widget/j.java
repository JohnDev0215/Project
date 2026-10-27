package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import t.f;

/* JADX INFO: compiled from: KeyTimeCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f1410g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f1411h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float f1412i = Float.NaN;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private float f1413j = Float.NaN;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private float f1414k = Float.NaN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private float f1415l = Float.NaN;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private float f1416m = Float.NaN;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float f1417n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private float f1418o = Float.NaN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private float f1419p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private float f1420q = Float.NaN;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private float f1421r = Float.NaN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private float f1422s = Float.NaN;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private float f1423t = Float.NaN;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private int f1424u = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private String f1425v = null;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private float f1426w = Float.NaN;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private float f1427x = 0.0f;

    /* JADX INFO: compiled from: KeyTimeCycle.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1428a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1428a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.i.KeyTimeCycle_android_alpha, 1);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_android_elevation, 2);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_android_rotation, 4);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_android_rotationX, 5);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_android_rotationY, 6);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_android_scaleX, 7);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_transitionPathRotate, 8);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_transitionEasing, 9);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_motionTarget, 10);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_framePosition, 12);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_curveFit, 13);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_android_scaleY, 14);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_android_translationX, 15);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_android_translationY, 16);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_android_translationZ, 17);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_motionProgress, 18);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_wavePeriod, 20);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_waveOffset, 21);
            f1428a.append(androidx.constraintlayout.widget.i.KeyTimeCycle_waveShape, 19);
        }

        public static void a(j jVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArray.getIndex(i7);
                switch (f1428a.get(index)) {
                    case 1:
                        jVar.f1412i = typedArray.getFloat(index, jVar.f1412i);
                        break;
                    case 2:
                        jVar.f1413j = typedArray.getDimension(index, jVar.f1413j);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1428a.get(index));
                        break;
                    case 4:
                        jVar.f1414k = typedArray.getFloat(index, jVar.f1414k);
                        break;
                    case 5:
                        jVar.f1415l = typedArray.getFloat(index, jVar.f1415l);
                        break;
                    case 6:
                        jVar.f1416m = typedArray.getFloat(index, jVar.f1416m);
                        break;
                    case 7:
                        jVar.f1418o = typedArray.getFloat(index, jVar.f1418o);
                        break;
                    case 8:
                        jVar.f1417n = typedArray.getFloat(index, jVar.f1417n);
                        break;
                    case 9:
                        jVar.f1410g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.f1260h1) {
                            int resourceId = typedArray.getResourceId(index, jVar.f1351b);
                            jVar.f1351b = resourceId;
                            if (resourceId == -1) {
                                jVar.f1352c = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            jVar.f1352c = typedArray.getString(index);
                        } else {
                            jVar.f1351b = typedArray.getResourceId(index, jVar.f1351b);
                        }
                        break;
                    case 12:
                        jVar.f1350a = typedArray.getInt(index, jVar.f1350a);
                        break;
                    case 13:
                        jVar.f1411h = typedArray.getInteger(index, jVar.f1411h);
                        break;
                    case 14:
                        jVar.f1419p = typedArray.getFloat(index, jVar.f1419p);
                        break;
                    case 15:
                        jVar.f1420q = typedArray.getDimension(index, jVar.f1420q);
                        break;
                    case 16:
                        jVar.f1421r = typedArray.getDimension(index, jVar.f1421r);
                        break;
                    case 17:
                        jVar.f1422s = typedArray.getDimension(index, jVar.f1422s);
                        break;
                    case 18:
                        jVar.f1423t = typedArray.getFloat(index, jVar.f1423t);
                        break;
                    case 19:
                        if (typedArray.peekValue(index).type == 3) {
                            jVar.f1425v = typedArray.getString(index);
                            jVar.f1424u = 7;
                        } else {
                            jVar.f1424u = typedArray.getInt(index, jVar.f1424u);
                        }
                        break;
                    case 20:
                        jVar.f1426w = typedArray.getFloat(index, jVar.f1426w);
                        break;
                    case 21:
                        if (typedArray.peekValue(index).type == 5) {
                            jVar.f1427x = typedArray.getDimension(index, jVar.f1427x);
                        } else {
                            jVar.f1427x = typedArray.getFloat(index, jVar.f1427x);
                        }
                        break;
                }
            }
        }
    }

    public j() {
        this.f1353d = 3;
        this.f1354e = new HashMap<>();
    }

    public void Q(HashMap<String, t.f> map) {
        for (String str : map.keySet()) {
            t.f fVar = map.get(str);
            if (fVar != null) {
                if (!str.startsWith("CUSTOM")) {
                    switch (str) {
                        case "rotationX":
                            if (Float.isNaN(this.f1415l)) {
                                break;
                            } else {
                                fVar.b(this.f1350a, this.f1415l, this.f1426w, this.f1424u, this.f1427x);
                                break;
                            }
                            break;
                        case "rotationY":
                            if (Float.isNaN(this.f1416m)) {
                                break;
                            } else {
                                fVar.b(this.f1350a, this.f1416m, this.f1426w, this.f1424u, this.f1427x);
                                break;
                            }
                            break;
                        case "translationX":
                            if (Float.isNaN(this.f1420q)) {
                                break;
                            } else {
                                fVar.b(this.f1350a, this.f1420q, this.f1426w, this.f1424u, this.f1427x);
                                break;
                            }
                            break;
                        case "translationY":
                            if (Float.isNaN(this.f1421r)) {
                                break;
                            } else {
                                fVar.b(this.f1350a, this.f1421r, this.f1426w, this.f1424u, this.f1427x);
                                break;
                            }
                            break;
                        case "translationZ":
                            if (Float.isNaN(this.f1422s)) {
                                break;
                            } else {
                                fVar.b(this.f1350a, this.f1422s, this.f1426w, this.f1424u, this.f1427x);
                                break;
                            }
                            break;
                        case "progress":
                            if (Float.isNaN(this.f1423t)) {
                                break;
                            } else {
                                fVar.b(this.f1350a, this.f1423t, this.f1426w, this.f1424u, this.f1427x);
                                break;
                            }
                            break;
                        case "scaleX":
                            if (Float.isNaN(this.f1418o)) {
                                break;
                            } else {
                                fVar.b(this.f1350a, this.f1418o, this.f1426w, this.f1424u, this.f1427x);
                                break;
                            }
                            break;
                        case "scaleY":
                            if (Float.isNaN(this.f1419p)) {
                                break;
                            } else {
                                fVar.b(this.f1350a, this.f1419p, this.f1426w, this.f1424u, this.f1427x);
                                break;
                            }
                            break;
                        case "rotation":
                            if (Float.isNaN(this.f1414k)) {
                                break;
                            } else {
                                fVar.b(this.f1350a, this.f1414k, this.f1426w, this.f1424u, this.f1427x);
                                break;
                            }
                            break;
                        case "elevation":
                            if (Float.isNaN(this.f1413j)) {
                                break;
                            } else {
                                fVar.b(this.f1350a, this.f1413j, this.f1426w, this.f1424u, this.f1427x);
                                break;
                            }
                            break;
                        case "transitionPathRotate":
                            if (Float.isNaN(this.f1417n)) {
                                break;
                            } else {
                                fVar.b(this.f1350a, this.f1417n, this.f1426w, this.f1424u, this.f1427x);
                                break;
                            }
                            break;
                        case "alpha":
                            if (Float.isNaN(this.f1412i)) {
                                break;
                            } else {
                                fVar.b(this.f1350a, this.f1412i, this.f1426w, this.f1424u, this.f1427x);
                                break;
                            }
                            break;
                        default:
                            Log.e("KeyTimeCycles", "UNKNOWN addValues \"" + str + "\"");
                            break;
                    }
                } else {
                    androidx.constraintlayout.widget.a aVar = this.f1354e.get(str.substring(7));
                    if (aVar != null) {
                        ((f.b) fVar).j(this.f1350a, aVar, this.f1426w, this.f1424u, this.f1427x);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void a(HashMap<String, t.d> map) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    @Override // androidx.constraintlayout.motion.widget.d
    /* JADX INFO: renamed from: b */
    public d clone() {
        return new j().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public d c(d dVar) {
        super.c(dVar);
        j jVar = (j) dVar;
        this.f1410g = jVar.f1410g;
        this.f1411h = jVar.f1411h;
        this.f1424u = jVar.f1424u;
        this.f1426w = jVar.f1426w;
        this.f1427x = jVar.f1427x;
        this.f1423t = jVar.f1423t;
        this.f1412i = jVar.f1412i;
        this.f1413j = jVar.f1413j;
        this.f1414k = jVar.f1414k;
        this.f1417n = jVar.f1417n;
        this.f1415l = jVar.f1415l;
        this.f1416m = jVar.f1416m;
        this.f1418o = jVar.f1418o;
        this.f1419p = jVar.f1419p;
        this.f1420q = jVar.f1420q;
        this.f1421r = jVar.f1421r;
        this.f1422s = jVar.f1422s;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void d(HashSet<String> hashSet) {
        if (!Float.isNaN(this.f1412i)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f1413j)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f1414k)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.f1415l)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.f1416m)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.f1420q)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.f1421r)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.f1422s)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.f1417n)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.f1418o)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.f1419p)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.f1423t)) {
            hashSet.add("progress");
        }
        if (this.f1354e.size() > 0) {
            Iterator<String> it = this.f1354e.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void e(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.i.KeyTimeCycle));
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void g(HashMap<String, Integer> map) {
        if (this.f1411h == -1) {
            return;
        }
        if (!Float.isNaN(this.f1412i)) {
            map.put("alpha", Integer.valueOf(this.f1411h));
        }
        if (!Float.isNaN(this.f1413j)) {
            map.put("elevation", Integer.valueOf(this.f1411h));
        }
        if (!Float.isNaN(this.f1414k)) {
            map.put("rotation", Integer.valueOf(this.f1411h));
        }
        if (!Float.isNaN(this.f1415l)) {
            map.put("rotationX", Integer.valueOf(this.f1411h));
        }
        if (!Float.isNaN(this.f1416m)) {
            map.put("rotationY", Integer.valueOf(this.f1411h));
        }
        if (!Float.isNaN(this.f1420q)) {
            map.put("translationX", Integer.valueOf(this.f1411h));
        }
        if (!Float.isNaN(this.f1421r)) {
            map.put("translationY", Integer.valueOf(this.f1411h));
        }
        if (!Float.isNaN(this.f1422s)) {
            map.put("translationZ", Integer.valueOf(this.f1411h));
        }
        if (!Float.isNaN(this.f1417n)) {
            map.put("transitionPathRotate", Integer.valueOf(this.f1411h));
        }
        if (!Float.isNaN(this.f1418o)) {
            map.put("scaleX", Integer.valueOf(this.f1411h));
        }
        if (!Float.isNaN(this.f1418o)) {
            map.put("scaleY", Integer.valueOf(this.f1411h));
        }
        if (!Float.isNaN(this.f1423t)) {
            map.put("progress", Integer.valueOf(this.f1411h));
        }
        if (this.f1354e.size() > 0) {
            Iterator<String> it = this.f1354e.keySet().iterator();
            while (it.hasNext()) {
                map.put("CUSTOM," + it.next(), Integer.valueOf(this.f1411h));
            }
        }
    }
}
