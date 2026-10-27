package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.ConstraintAttribute;
import b.e.a.a.d;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyAttributes.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends e {
    private String g;
    private int h = -1;
    private boolean i = false;
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
    private float u = Float.NaN;
    private float v = Float.NaN;
    private float w = Float.NaN;

    /* JADX INFO: compiled from: KeyAttributes.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f930a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f930a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.Y5, 1);
            f930a.append(androidx.constraintlayout.widget.e.j6, 2);
            f930a.append(androidx.constraintlayout.widget.e.f6, 4);
            f930a.append(androidx.constraintlayout.widget.e.g6, 5);
            f930a.append(androidx.constraintlayout.widget.e.h6, 6);
            f930a.append(androidx.constraintlayout.widget.e.Z5, 19);
            f930a.append(androidx.constraintlayout.widget.e.a6, 20);
            f930a.append(androidx.constraintlayout.widget.e.d6, 7);
            f930a.append(androidx.constraintlayout.widget.e.p6, 8);
            f930a.append(androidx.constraintlayout.widget.e.o6, 9);
            f930a.append(androidx.constraintlayout.widget.e.n6, 10);
            f930a.append(androidx.constraintlayout.widget.e.l6, 12);
            f930a.append(androidx.constraintlayout.widget.e.k6, 13);
            f930a.append(androidx.constraintlayout.widget.e.e6, 14);
            f930a.append(androidx.constraintlayout.widget.e.b6, 15);
            f930a.append(androidx.constraintlayout.widget.e.c6, 16);
            f930a.append(androidx.constraintlayout.widget.e.i6, 17);
            f930a.append(androidx.constraintlayout.widget.e.m6, 18);
        }

        public static void a(f fVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f930a.get(index)) {
                    case 1:
                        fVar.j = typedArray.getFloat(index, fVar.j);
                        break;
                    case 2:
                        fVar.k = typedArray.getDimension(index, fVar.k);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + f930a.get(index));
                        break;
                    case 4:
                        fVar.l = typedArray.getFloat(index, fVar.l);
                        break;
                    case 5:
                        fVar.m = typedArray.getFloat(index, fVar.m);
                        break;
                    case 6:
                        fVar.n = typedArray.getFloat(index, fVar.n);
                        break;
                    case 7:
                        fVar.r = typedArray.getFloat(index, fVar.r);
                        break;
                    case 8:
                        fVar.q = typedArray.getFloat(index, fVar.q);
                        break;
                    case 9:
                        fVar.g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.z) {
                            int resourceId = typedArray.getResourceId(index, fVar.f927c);
                            fVar.f927c = resourceId;
                            if (resourceId == -1) {
                                fVar.f928d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            fVar.f928d = typedArray.getString(index);
                        } else {
                            fVar.f927c = typedArray.getResourceId(index, fVar.f927c);
                        }
                        break;
                    case 12:
                        fVar.f926b = typedArray.getInt(index, fVar.f926b);
                        break;
                    case 13:
                        fVar.h = typedArray.getInteger(index, fVar.h);
                        break;
                    case 14:
                        fVar.s = typedArray.getFloat(index, fVar.s);
                        break;
                    case 15:
                        fVar.t = typedArray.getDimension(index, fVar.t);
                        break;
                    case 16:
                        fVar.u = typedArray.getDimension(index, fVar.u);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            fVar.v = typedArray.getDimension(index, fVar.v);
                        }
                        break;
                    case 18:
                        fVar.w = typedArray.getFloat(index, fVar.w);
                        break;
                    case 19:
                        fVar.o = typedArray.getDimension(index, fVar.o);
                        break;
                    case 20:
                        fVar.p = typedArray.getDimension(index, fVar.p);
                        break;
                }
            }
        }
    }

    public f() {
        this.f929e = 1;
        this.f = new HashMap<>();
    }

    public void R(String str, Object obj) {
        str.hashCode();
        switch (str) {
            case "motionProgress":
                this.w = k(obj);
                break;
            case "transitionEasing":
                this.g = obj.toString();
                break;
            case "rotationX":
                this.m = k(obj);
                break;
            case "rotationY":
                this.n = k(obj);
                break;
            case "translationX":
                this.t = k(obj);
                break;
            case "translationY":
                this.u = k(obj);
                break;
            case "translationZ":
                this.v = k(obj);
                break;
            case "scaleX":
                this.r = k(obj);
                break;
            case "scaleY":
                this.s = k(obj);
                break;
            case "transformPivotX":
                this.o = k(obj);
                break;
            case "transformPivotY":
                this.p = k(obj);
                break;
            case "rotation":
                this.l = k(obj);
                break;
            case "elevation":
                this.k = k(obj);
                break;
            case "transitionPathRotate":
                this.q = k(obj);
                break;
            case "alpha":
                this.j = k(obj);
                break;
            case "curveFit":
                this.h = l(obj);
                break;
            case "visibility":
                this.i = j(obj);
                break;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void a(HashMap<String, b.e.a.a.d> map) {
        for (String str : map.keySet()) {
            b.e.a.a.d dVar = map.get(str);
            if (dVar != null) {
                if (!str.startsWith("CUSTOM")) {
                    switch (str) {
                        case "rotationX":
                            if (Float.isNaN(this.m)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.m);
                                break;
                            }
                            break;
                        case "rotationY":
                            if (Float.isNaN(this.n)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.n);
                                break;
                            }
                            break;
                        case "translationX":
                            if (Float.isNaN(this.t)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.t);
                                break;
                            }
                            break;
                        case "translationY":
                            if (Float.isNaN(this.u)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.u);
                                break;
                            }
                            break;
                        case "translationZ":
                            if (Float.isNaN(this.v)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.v);
                                break;
                            }
                            break;
                        case "progress":
                            if (Float.isNaN(this.w)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.w);
                                break;
                            }
                            break;
                        case "scaleX":
                            if (Float.isNaN(this.r)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.r);
                                break;
                            }
                            break;
                        case "scaleY":
                            if (Float.isNaN(this.s)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.s);
                                break;
                            }
                            break;
                        case "transformPivotX":
                            if (Float.isNaN(this.m)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.o);
                                break;
                            }
                            break;
                        case "transformPivotY":
                            if (Float.isNaN(this.n)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.p);
                                break;
                            }
                            break;
                        case "rotation":
                            if (Float.isNaN(this.l)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.l);
                                break;
                            }
                            break;
                        case "elevation":
                            if (Float.isNaN(this.k)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.k);
                                break;
                            }
                            break;
                        case "transitionPathRotate":
                            if (Float.isNaN(this.q)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.q);
                                break;
                            }
                            break;
                        case "alpha":
                            if (Float.isNaN(this.j)) {
                                break;
                            } else {
                                dVar.c(this.f926b, this.j);
                                break;
                            }
                            break;
                    }
                } else {
                    ConstraintAttribute constraintAttribute = this.f.get(str.substring(7));
                    if (constraintAttribute != null) {
                        ((d.b) dVar).i(this.f926b, constraintAttribute);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.e
    /* JADX INFO: renamed from: b */
    public e clone() {
        return new f().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public e c(e eVar) {
        super.c(eVar);
        f fVar = (f) eVar;
        this.h = fVar.h;
        this.i = fVar.i;
        this.j = fVar.j;
        this.k = fVar.k;
        this.l = fVar.l;
        this.m = fVar.m;
        this.n = fVar.n;
        this.o = fVar.o;
        this.p = fVar.p;
        this.q = fVar.q;
        this.r = fVar.r;
        this.s = fVar.s;
        this.t = fVar.t;
        this.u = fVar.u;
        this.v = fVar.v;
        this.w = fVar.w;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void d(HashSet<String> hashSet) {
        if (!Float.isNaN(this.j)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.k)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.l)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.m)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("transformPivotX");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("transformPivotY");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.u)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.v)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.w)) {
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
        a.a(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.X5));
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void h(HashMap<String, Integer> map) {
        if (this.h == -1) {
            return;
        }
        if (!Float.isNaN(this.j)) {
            map.put("alpha", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.k)) {
            map.put("elevation", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.l)) {
            map.put("rotation", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.m)) {
            map.put("rotationX", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.n)) {
            map.put("rotationY", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.o)) {
            map.put("transformPivotX", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.p)) {
            map.put("transformPivotY", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.t)) {
            map.put("translationX", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.u)) {
            map.put("translationY", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.v)) {
            map.put("translationZ", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.q)) {
            map.put("transitionPathRotate", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.r)) {
            map.put("scaleX", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.s)) {
            map.put("scaleY", Integer.valueOf(this.h));
        }
        if (!Float.isNaN(this.w)) {
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
