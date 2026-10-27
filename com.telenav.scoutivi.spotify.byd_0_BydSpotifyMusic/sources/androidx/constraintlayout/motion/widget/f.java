package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.a;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f1373g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f1374h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f1375i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private String f1376j = null;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private float f1377k = Float.NaN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private float f1378l = 0.0f;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private float f1379m = 0.0f;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float f1380n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f1381o = -1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private float f1382p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private float f1383q = Float.NaN;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private float f1384r = Float.NaN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private float f1385s = Float.NaN;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private float f1386t = Float.NaN;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private float f1387u = Float.NaN;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private float f1388v = Float.NaN;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private float f1389w = Float.NaN;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private float f1390x = Float.NaN;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private float f1391y = Float.NaN;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private float f1392z = Float.NaN;

    /* JADX INFO: compiled from: KeyCycle.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1393a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1393a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.i.KeyCycle_motionTarget, 1);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_framePosition, 2);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_transitionEasing, 3);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_curveFit, 4);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_waveShape, 5);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_wavePeriod, 6);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_waveOffset, 7);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_waveVariesBy, 8);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_android_alpha, 9);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_android_elevation, 10);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_android_rotation, 11);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_android_rotationX, 12);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_android_rotationY, 13);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_transitionPathRotate, 14);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_android_scaleX, 15);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_android_scaleY, 16);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_android_translationX, 17);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_android_translationY, 18);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_android_translationZ, 19);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_motionProgress, 20);
            f1393a.append(androidx.constraintlayout.widget.i.KeyCycle_wavePhase, 21);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(f fVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArray.getIndex(i7);
                switch (f1393a.get(index)) {
                    case 1:
                        if (MotionLayout.f1260h1) {
                            int resourceId = typedArray.getResourceId(index, fVar.f1351b);
                            fVar.f1351b = resourceId;
                            if (resourceId == -1) {
                                fVar.f1352c = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            fVar.f1352c = typedArray.getString(index);
                        } else {
                            fVar.f1351b = typedArray.getResourceId(index, fVar.f1351b);
                        }
                        break;
                    case 2:
                        fVar.f1350a = typedArray.getInt(index, fVar.f1350a);
                        break;
                    case 3:
                        fVar.f1373g = typedArray.getString(index);
                        break;
                    case 4:
                        fVar.f1374h = typedArray.getInteger(index, fVar.f1374h);
                        break;
                    case 5:
                        if (typedArray.peekValue(index).type == 3) {
                            fVar.f1376j = typedArray.getString(index);
                            fVar.f1375i = 7;
                        } else {
                            fVar.f1375i = typedArray.getInt(index, fVar.f1375i);
                        }
                        break;
                    case 6:
                        fVar.f1377k = typedArray.getFloat(index, fVar.f1377k);
                        break;
                    case 7:
                        if (typedArray.peekValue(index).type == 5) {
                            fVar.f1378l = typedArray.getDimension(index, fVar.f1378l);
                        } else {
                            fVar.f1378l = typedArray.getFloat(index, fVar.f1378l);
                        }
                        break;
                    case 8:
                        fVar.f1381o = typedArray.getInt(index, fVar.f1381o);
                        break;
                    case 9:
                        fVar.f1382p = typedArray.getFloat(index, fVar.f1382p);
                        break;
                    case 10:
                        fVar.f1383q = typedArray.getDimension(index, fVar.f1383q);
                        break;
                    case 11:
                        fVar.f1384r = typedArray.getFloat(index, fVar.f1384r);
                        break;
                    case 12:
                        fVar.f1386t = typedArray.getFloat(index, fVar.f1386t);
                        break;
                    case 13:
                        fVar.f1387u = typedArray.getFloat(index, fVar.f1387u);
                        break;
                    case 14:
                        fVar.f1385s = typedArray.getFloat(index, fVar.f1385s);
                        break;
                    case 15:
                        fVar.f1388v = typedArray.getFloat(index, fVar.f1388v);
                        break;
                    case 16:
                        fVar.f1389w = typedArray.getFloat(index, fVar.f1389w);
                        break;
                    case 17:
                        fVar.f1390x = typedArray.getDimension(index, fVar.f1390x);
                        break;
                    case 18:
                        fVar.f1391y = typedArray.getDimension(index, fVar.f1391y);
                        break;
                    case 19:
                        fVar.f1392z = typedArray.getDimension(index, fVar.f1392z);
                        break;
                    case 20:
                        fVar.f1380n = typedArray.getFloat(index, fVar.f1380n);
                        break;
                    case 21:
                        fVar.f1379m = typedArray.getFloat(index, fVar.f1379m) / 360.0f;
                        break;
                    default:
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1393a.get(index));
                        break;
                }
            }
        }
    }

    public f() {
        this.f1353d = 4;
        this.f1354e = new HashMap<>();
    }

    public void U(HashMap<String, t.c> map) {
        t.c cVar;
        t.c cVar2;
        for (String str : map.keySet()) {
            if (str.startsWith("CUSTOM")) {
                androidx.constraintlayout.widget.a aVar = this.f1354e.get(str.substring(7));
                if (aVar != null && aVar.d() == a.b.FLOAT_TYPE && (cVar = map.get(str)) != null) {
                    cVar.d(this.f1350a, this.f1375i, this.f1376j, this.f1381o, this.f1377k, this.f1378l, this.f1379m, aVar.e(), aVar);
                }
            } else {
                float fV = V(str);
                if (!Float.isNaN(fV) && (cVar2 = map.get(str)) != null) {
                    cVar2.c(this.f1350a, this.f1375i, this.f1376j, this.f1381o, this.f1377k, this.f1378l, this.f1379m, fV);
                }
            }
        }
    }

    public float V(String str) {
        str.hashCode();
        switch (str) {
            case "rotationX":
                return this.f1386t;
            case "rotationY":
                return this.f1387u;
            case "translationX":
                return this.f1390x;
            case "translationY":
                return this.f1391y;
            case "translationZ":
                return this.f1392z;
            case "progress":
                return this.f1380n;
            case "scaleX":
                return this.f1388v;
            case "scaleY":
                return this.f1389w;
            case "rotation":
                return this.f1384r;
            case "elevation":
                return this.f1383q;
            case "transitionPathRotate":
                return this.f1385s;
            case "alpha":
                return this.f1382p;
            case "waveOffset":
                return this.f1378l;
            case "wavePhase":
                return this.f1379m;
            default:
                if (str.startsWith("CUSTOM")) {
                    return Float.NaN;
                }
                Log.v("WARNING! KeyCycle", "  UNKNOWN  " + str);
                return Float.NaN;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void a(HashMap<String, t.d> map) {
        androidx.constraintlayout.motion.widget.a.g("KeyCycle", "add " + map.size() + " values", 2);
        for (String str : map.keySet()) {
            t.d dVar = map.get(str);
            if (dVar != null) {
                str.hashCode();
                switch (str) {
                    case "rotationX":
                        dVar.b(this.f1350a, this.f1386t);
                        break;
                    case "rotationY":
                        dVar.b(this.f1350a, this.f1387u);
                        break;
                    case "translationX":
                        dVar.b(this.f1350a, this.f1390x);
                        break;
                    case "translationY":
                        dVar.b(this.f1350a, this.f1391y);
                        break;
                    case "translationZ":
                        dVar.b(this.f1350a, this.f1392z);
                        break;
                    case "progress":
                        dVar.b(this.f1350a, this.f1380n);
                        break;
                    case "scaleX":
                        dVar.b(this.f1350a, this.f1388v);
                        break;
                    case "scaleY":
                        dVar.b(this.f1350a, this.f1389w);
                        break;
                    case "rotation":
                        dVar.b(this.f1350a, this.f1384r);
                        break;
                    case "elevation":
                        dVar.b(this.f1350a, this.f1383q);
                        break;
                    case "transitionPathRotate":
                        dVar.b(this.f1350a, this.f1385s);
                        break;
                    case "alpha":
                        dVar.b(this.f1350a, this.f1382p);
                        break;
                    case "waveOffset":
                        dVar.b(this.f1350a, this.f1378l);
                        break;
                    case "wavePhase":
                        dVar.b(this.f1350a, this.f1379m);
                        break;
                    default:
                        if (str.startsWith("CUSTOM")) {
                            break;
                        } else {
                            Log.v("WARNING KeyCycle", "  UNKNOWN  " + str);
                            break;
                        }
                        break;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.d
    /* JADX INFO: renamed from: b */
    public d clone() {
        return new f().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public d c(d dVar) {
        super.c(dVar);
        f fVar = (f) dVar;
        this.f1373g = fVar.f1373g;
        this.f1374h = fVar.f1374h;
        this.f1375i = fVar.f1375i;
        this.f1376j = fVar.f1376j;
        this.f1377k = fVar.f1377k;
        this.f1378l = fVar.f1378l;
        this.f1379m = fVar.f1379m;
        this.f1380n = fVar.f1380n;
        this.f1381o = fVar.f1381o;
        this.f1382p = fVar.f1382p;
        this.f1383q = fVar.f1383q;
        this.f1384r = fVar.f1384r;
        this.f1385s = fVar.f1385s;
        this.f1386t = fVar.f1386t;
        this.f1387u = fVar.f1387u;
        this.f1388v = fVar.f1388v;
        this.f1389w = fVar.f1389w;
        this.f1390x = fVar.f1390x;
        this.f1391y = fVar.f1391y;
        this.f1392z = fVar.f1392z;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void d(HashSet<String> hashSet) {
        if (!Float.isNaN(this.f1382p)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f1383q)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f1384r)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.f1386t)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.f1387u)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.f1388v)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.f1389w)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.f1385s)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.f1390x)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.f1391y)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.f1392z)) {
            hashSet.add("translationZ");
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
        a.b(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.i.KeyCycle));
    }
}
