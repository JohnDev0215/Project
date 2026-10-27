package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.ConstraintAttribute;
import b.e.a.a.f;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyTimeCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends e {
    private String g;
    private int h = -1;
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
    private String v = null;
    private float w = Float.NaN;
    private float x = 0.0f;

    /* JADX INFO: compiled from: KeyTimeCycle.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f935a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f935a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.d7, 1);
            f935a.append(androidx.constraintlayout.widget.e.m7, 2);
            f935a.append(androidx.constraintlayout.widget.e.i7, 4);
            f935a.append(androidx.constraintlayout.widget.e.j7, 5);
            f935a.append(androidx.constraintlayout.widget.e.k7, 6);
            f935a.append(androidx.constraintlayout.widget.e.g7, 7);
            f935a.append(androidx.constraintlayout.widget.e.s7, 8);
            f935a.append(androidx.constraintlayout.widget.e.r7, 9);
            f935a.append(androidx.constraintlayout.widget.e.q7, 10);
            f935a.append(androidx.constraintlayout.widget.e.o7, 12);
            f935a.append(androidx.constraintlayout.widget.e.n7, 13);
            f935a.append(androidx.constraintlayout.widget.e.h7, 14);
            f935a.append(androidx.constraintlayout.widget.e.e7, 15);
            f935a.append(androidx.constraintlayout.widget.e.f7, 16);
            f935a.append(androidx.constraintlayout.widget.e.l7, 17);
            f935a.append(androidx.constraintlayout.widget.e.p7, 18);
            f935a.append(androidx.constraintlayout.widget.e.u7, 20);
            f935a.append(androidx.constraintlayout.widget.e.t7, 21);
            f935a.append(androidx.constraintlayout.widget.e.v7, 19);
        }

        public static void a(k kVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f935a.get(index)) {
                    case 1:
                        kVar.i = typedArray.getFloat(index, kVar.i);
                        break;
                    case 2:
                        kVar.j = typedArray.getDimension(index, kVar.j);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f935a.get(index));
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
                        kVar.g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.z) {
                            int resourceId = typedArray.getResourceId(index, kVar.f927c);
                            kVar.f927c = resourceId;
                            if (resourceId == -1) {
                                kVar.f928d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            kVar.f928d = typedArray.getString(index);
                        } else {
                            kVar.f927c = typedArray.getResourceId(index, kVar.f927c);
                        }
                        break;
                    case 12:
                        kVar.f926b = typedArray.getInt(index, kVar.f926b);
                        break;
                    case 13:
                        kVar.h = typedArray.getInteger(index, kVar.h);
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
                        if (typedArray.peekValue(index).type == 3) {
                            kVar.v = typedArray.getString(index);
                            kVar.u = 7;
                        } else {
                            kVar.u = typedArray.getInt(index, kVar.u);
                        }
                        break;
                    case 20:
                        kVar.w = typedArray.getFloat(index, kVar.w);
                        break;
                    case 21:
                        if (typedArray.peekValue(index).type == 5) {
                            kVar.x = typedArray.getDimension(index, kVar.x);
                        } else {
                            kVar.x = typedArray.getFloat(index, kVar.x);
                        }
                        break;
                }
            }
        }
    }

    public k() {
        this.f929e = 3;
        this.f = new HashMap<>();
    }

    public void U(HashMap<String, b.e.a.a.f> map) {
        for (String str : map.keySet()) {
            b.e.a.a.f fVar = map.get(str);
            if (fVar != null) {
                if (!str.startsWith("CUSTOM")) {
                    switch (str) {
                        case "rotationX":
                            if (Float.isNaN(this.l)) {
                                break;
                            } else {
                                fVar.b(this.f926b, this.l, this.w, this.u, this.x);
                                break;
                            }
                            break;
                        case "rotationY":
                            if (Float.isNaN(this.m)) {
                                break;
                            } else {
                                fVar.b(this.f926b, this.m, this.w, this.u, this.x);
                                break;
                            }
                            break;
                        case "translationX":
                            if (Float.isNaN(this.q)) {
                                break;
                            } else {
                                fVar.b(this.f926b, this.q, this.w, this.u, this.x);
                                break;
                            }
                            break;
                        case "translationY":
                            if (Float.isNaN(this.r)) {
                                break;
                            } else {
                                fVar.b(this.f926b, this.r, this.w, this.u, this.x);
                                break;
                            }
                            break;
                        case "translationZ":
                            if (Float.isNaN(this.s)) {
                                break;
                            } else {
                                fVar.b(this.f926b, this.s, this.w, this.u, this.x);
                                break;
                            }
                            break;
                        case "progress":
                            if (Float.isNaN(this.t)) {
                                break;
                            } else {
                                fVar.b(this.f926b, this.t, this.w, this.u, this.x);
                                break;
                            }
                            break;
                        case "scaleX":
                            if (Float.isNaN(this.o)) {
                                break;
                            } else {
                                fVar.b(this.f926b, this.o, this.w, this.u, this.x);
                                break;
                            }
                            break;
                        case "scaleY":
                            if (Float.isNaN(this.p)) {
                                break;
                            } else {
                                fVar.b(this.f926b, this.p, this.w, this.u, this.x);
                                break;
                            }
                            break;
                        case "rotation":
                            if (Float.isNaN(this.k)) {
                                break;
                            } else {
                                fVar.b(this.f926b, this.k, this.w, this.u, this.x);
                                break;
                            }
                            break;
                        case "elevation":
                            if (Float.isNaN(this.j)) {
                                break;
                            } else {
                                fVar.b(this.f926b, this.j, this.w, this.u, this.x);
                                break;
                            }
                            break;
                        case "transitionPathRotate":
                            if (Float.isNaN(this.n)) {
                                break;
                            } else {
                                fVar.b(this.f926b, this.n, this.w, this.u, this.x);
                                break;
                            }
                            break;
                        case "alpha":
                            if (Float.isNaN(this.i)) {
                                break;
                            } else {
                                fVar.b(this.f926b, this.i, this.w, this.u, this.x);
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
                        ((f.b) fVar).j(this.f926b, constraintAttribute, this.w, this.u, this.x);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void a(HashMap<String, b.e.a.a.d> map) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    @Override // androidx.constraintlayout.motion.widget.e
    /* JADX INFO: renamed from: b */
    public e clone() {
        return new k().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public e c(e eVar) {
        super.c(eVar);
        k kVar = (k) eVar;
        this.g = kVar.g;
        this.h = kVar.h;
        this.u = kVar.u;
        this.w = kVar.w;
        this.x = kVar.x;
        this.t = kVar.t;
        this.i = kVar.i;
        this.j = kVar.j;
        this.k = kVar.k;
        this.n = kVar.n;
        this.l = kVar.l;
        this.m = kVar.m;
        this.o = kVar.o;
        this.p = kVar.p;
        this.q = kVar.q;
        this.r = kVar.r;
        this.s = kVar.s;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void d(HashSet<String> hashSet) {
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

    @Override // androidx.constraintlayout.motion.widget.e
    public void e(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.c7));
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void h(HashMap<String, Integer> map) {
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
