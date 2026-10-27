package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import t.d;

/* JADX INFO: compiled from: KeyAttributes.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f1355g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f1356h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f1357i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private float f1358j = Float.NaN;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private float f1359k = Float.NaN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private float f1360l = Float.NaN;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private float f1361m = Float.NaN;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float f1362n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private float f1363o = Float.NaN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private float f1364p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private float f1365q = Float.NaN;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private float f1366r = Float.NaN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private float f1367s = Float.NaN;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private float f1368t = Float.NaN;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private float f1369u = Float.NaN;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private float f1370v = Float.NaN;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private float f1371w = Float.NaN;

    /* JADX INFO: compiled from: KeyAttributes.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1372a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1372a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.i.KeyAttribute_android_alpha, 1);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_android_elevation, 2);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_android_rotation, 4);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_android_rotationX, 5);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_android_rotationY, 6);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_android_transformPivotX, 19);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_android_transformPivotY, 20);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_android_scaleX, 7);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_transitionPathRotate, 8);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_transitionEasing, 9);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_motionTarget, 10);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_framePosition, 12);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_curveFit, 13);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_android_scaleY, 14);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_android_translationX, 15);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_android_translationY, 16);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_android_translationZ, 17);
            f1372a.append(androidx.constraintlayout.widget.i.KeyAttribute_motionProgress, 18);
        }

        public static void a(e eVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArray.getIndex(i7);
                switch (f1372a.get(index)) {
                    case 1:
                        eVar.f1358j = typedArray.getFloat(index, eVar.f1358j);
                        break;
                    case 2:
                        eVar.f1359k = typedArray.getDimension(index, eVar.f1359k);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1372a.get(index));
                        break;
                    case 4:
                        eVar.f1360l = typedArray.getFloat(index, eVar.f1360l);
                        break;
                    case 5:
                        eVar.f1361m = typedArray.getFloat(index, eVar.f1361m);
                        break;
                    case 6:
                        eVar.f1362n = typedArray.getFloat(index, eVar.f1362n);
                        break;
                    case 7:
                        eVar.f1366r = typedArray.getFloat(index, eVar.f1366r);
                        break;
                    case 8:
                        eVar.f1365q = typedArray.getFloat(index, eVar.f1365q);
                        break;
                    case 9:
                        eVar.f1355g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.f1260h1) {
                            int resourceId = typedArray.getResourceId(index, eVar.f1351b);
                            eVar.f1351b = resourceId;
                            if (resourceId == -1) {
                                eVar.f1352c = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            eVar.f1352c = typedArray.getString(index);
                        } else {
                            eVar.f1351b = typedArray.getResourceId(index, eVar.f1351b);
                        }
                        break;
                    case 12:
                        eVar.f1350a = typedArray.getInt(index, eVar.f1350a);
                        break;
                    case 13:
                        eVar.f1356h = typedArray.getInteger(index, eVar.f1356h);
                        break;
                    case 14:
                        eVar.f1367s = typedArray.getFloat(index, eVar.f1367s);
                        break;
                    case 15:
                        eVar.f1368t = typedArray.getDimension(index, eVar.f1368t);
                        break;
                    case 16:
                        eVar.f1369u = typedArray.getDimension(index, eVar.f1369u);
                        break;
                    case 17:
                        eVar.f1370v = typedArray.getDimension(index, eVar.f1370v);
                        break;
                    case 18:
                        eVar.f1371w = typedArray.getFloat(index, eVar.f1371w);
                        break;
                    case 19:
                        eVar.f1363o = typedArray.getDimension(index, eVar.f1363o);
                        break;
                    case 20:
                        eVar.f1364p = typedArray.getDimension(index, eVar.f1364p);
                        break;
                }
            }
        }
    }

    public e() {
        this.f1353d = 1;
        this.f1354e = new HashMap<>();
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void a(HashMap<String, t.d> map) {
        for (String str : map.keySet()) {
            t.d dVar = map.get(str);
            if (dVar != null) {
                if (!str.startsWith("CUSTOM")) {
                    switch (str) {
                        case "rotationX":
                            if (Float.isNaN(this.f1361m)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1361m);
                                break;
                            }
                            break;
                        case "rotationY":
                            if (Float.isNaN(this.f1362n)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1362n);
                                break;
                            }
                            break;
                        case "translationX":
                            if (Float.isNaN(this.f1368t)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1368t);
                                break;
                            }
                            break;
                        case "translationY":
                            if (Float.isNaN(this.f1369u)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1369u);
                                break;
                            }
                            break;
                        case "translationZ":
                            if (Float.isNaN(this.f1370v)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1370v);
                                break;
                            }
                            break;
                        case "progress":
                            if (Float.isNaN(this.f1371w)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1371w);
                                break;
                            }
                            break;
                        case "scaleX":
                            if (Float.isNaN(this.f1366r)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1366r);
                                break;
                            }
                            break;
                        case "scaleY":
                            if (Float.isNaN(this.f1367s)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1367s);
                                break;
                            }
                            break;
                        case "transformPivotX":
                            if (Float.isNaN(this.f1361m)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1363o);
                                break;
                            }
                            break;
                        case "transformPivotY":
                            if (Float.isNaN(this.f1362n)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1364p);
                                break;
                            }
                            break;
                        case "rotation":
                            if (Float.isNaN(this.f1360l)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1360l);
                                break;
                            }
                            break;
                        case "elevation":
                            if (Float.isNaN(this.f1359k)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1359k);
                                break;
                            }
                            break;
                        case "transitionPathRotate":
                            if (Float.isNaN(this.f1365q)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1365q);
                                break;
                            }
                            break;
                        case "alpha":
                            if (Float.isNaN(this.f1358j)) {
                                break;
                            } else {
                                dVar.b(this.f1350a, this.f1358j);
                                break;
                            }
                            break;
                    }
                } else {
                    androidx.constraintlayout.widget.a aVar = this.f1354e.get(str.substring(7));
                    if (aVar != null) {
                        ((d.b) dVar).h(this.f1350a, aVar);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.d
    /* JADX INFO: renamed from: b */
    public d clone() {
        return new e().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public d c(d dVar) {
        super.c(dVar);
        e eVar = (e) dVar;
        this.f1356h = eVar.f1356h;
        this.f1357i = eVar.f1357i;
        this.f1358j = eVar.f1358j;
        this.f1359k = eVar.f1359k;
        this.f1360l = eVar.f1360l;
        this.f1361m = eVar.f1361m;
        this.f1362n = eVar.f1362n;
        this.f1363o = eVar.f1363o;
        this.f1364p = eVar.f1364p;
        this.f1365q = eVar.f1365q;
        this.f1366r = eVar.f1366r;
        this.f1367s = eVar.f1367s;
        this.f1368t = eVar.f1368t;
        this.f1369u = eVar.f1369u;
        this.f1370v = eVar.f1370v;
        this.f1371w = eVar.f1371w;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void d(HashSet<String> hashSet) {
        if (!Float.isNaN(this.f1358j)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f1359k)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f1360l)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.f1361m)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.f1362n)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.f1363o)) {
            hashSet.add("transformPivotX");
        }
        if (!Float.isNaN(this.f1364p)) {
            hashSet.add("transformPivotY");
        }
        if (!Float.isNaN(this.f1368t)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.f1369u)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.f1370v)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.f1365q)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.f1366r)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.f1367s)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.f1371w)) {
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
        a.a(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.i.KeyAttribute));
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void g(HashMap<String, Integer> map) {
        if (this.f1356h == -1) {
            return;
        }
        if (!Float.isNaN(this.f1358j)) {
            map.put("alpha", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1359k)) {
            map.put("elevation", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1360l)) {
            map.put("rotation", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1361m)) {
            map.put("rotationX", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1362n)) {
            map.put("rotationY", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1363o)) {
            map.put("transformPivotX", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1364p)) {
            map.put("transformPivotY", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1368t)) {
            map.put("translationX", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1369u)) {
            map.put("translationY", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1370v)) {
            map.put("translationZ", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1365q)) {
            map.put("transitionPathRotate", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1366r)) {
            map.put("scaleX", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1367s)) {
            map.put("scaleY", Integer.valueOf(this.f1356h));
        }
        if (!Float.isNaN(this.f1371w)) {
            map.put("progress", Integer.valueOf(this.f1356h));
        }
        if (this.f1354e.size() > 0) {
            Iterator<String> it = this.f1354e.keySet().iterator();
            while (it.hasNext()) {
                map.put("CUSTOM," + it.next(), Integer.valueOf(this.f1356h));
            }
        }
    }
}
