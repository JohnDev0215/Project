package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends e {
    private String g = null;
    private int h = 0;
    private int i = -1;
    private String j = null;
    private float k = Float.NaN;
    private float l = 0.0f;
    private float m = 0.0f;
    private float n = Float.NaN;
    private int o = -1;
    private float p = Float.NaN;
    private float q = Float.NaN;
    private float r = Float.NaN;
    private float s = Float.NaN;
    private float t = Float.NaN;
    private float u = Float.NaN;
    private float v = Float.NaN;
    private float w = Float.NaN;
    private float x = Float.NaN;
    private float y = Float.NaN;
    private float z = Float.NaN;

    /* JADX INFO: compiled from: KeyCycle.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f931a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f931a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.E6, 1);
            f931a.append(androidx.constraintlayout.widget.e.C6, 2);
            f931a.append(androidx.constraintlayout.widget.e.F6, 3);
            f931a.append(androidx.constraintlayout.widget.e.B6, 4);
            f931a.append(androidx.constraintlayout.widget.e.K6, 5);
            f931a.append(androidx.constraintlayout.widget.e.I6, 6);
            f931a.append(androidx.constraintlayout.widget.e.H6, 7);
            f931a.append(androidx.constraintlayout.widget.e.L6, 8);
            f931a.append(androidx.constraintlayout.widget.e.r6, 9);
            f931a.append(androidx.constraintlayout.widget.e.A6, 10);
            f931a.append(androidx.constraintlayout.widget.e.w6, 11);
            f931a.append(androidx.constraintlayout.widget.e.x6, 12);
            f931a.append(androidx.constraintlayout.widget.e.y6, 13);
            f931a.append(androidx.constraintlayout.widget.e.G6, 14);
            f931a.append(androidx.constraintlayout.widget.e.u6, 15);
            f931a.append(androidx.constraintlayout.widget.e.v6, 16);
            f931a.append(androidx.constraintlayout.widget.e.s6, 17);
            f931a.append(androidx.constraintlayout.widget.e.t6, 18);
            f931a.append(androidx.constraintlayout.widget.e.z6, 19);
            f931a.append(androidx.constraintlayout.widget.e.D6, 20);
            f931a.append(androidx.constraintlayout.widget.e.J6, 21);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(g gVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f931a.get(index)) {
                    case 1:
                        if (MotionLayout.z) {
                            int resourceId = typedArray.getResourceId(index, gVar.f927c);
                            gVar.f927c = resourceId;
                            if (resourceId == -1) {
                                gVar.f928d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            gVar.f928d = typedArray.getString(index);
                        } else {
                            gVar.f927c = typedArray.getResourceId(index, gVar.f927c);
                        }
                        break;
                    case 2:
                        gVar.f926b = typedArray.getInt(index, gVar.f926b);
                        break;
                    case 3:
                        gVar.g = typedArray.getString(index);
                        break;
                    case 4:
                        gVar.h = typedArray.getInteger(index, gVar.h);
                        break;
                    case 5:
                        if (typedArray.peekValue(index).type == 3) {
                            gVar.j = typedArray.getString(index);
                            gVar.i = 7;
                        } else {
                            gVar.i = typedArray.getInt(index, gVar.i);
                        }
                        break;
                    case 6:
                        gVar.k = typedArray.getFloat(index, gVar.k);
                        break;
                    case 7:
                        if (typedArray.peekValue(index).type == 5) {
                            gVar.l = typedArray.getDimension(index, gVar.l);
                        } else {
                            gVar.l = typedArray.getFloat(index, gVar.l);
                        }
                        break;
                    case 8:
                        gVar.o = typedArray.getInt(index, gVar.o);
                        break;
                    case 9:
                        gVar.p = typedArray.getFloat(index, gVar.p);
                        break;
                    case 10:
                        gVar.q = typedArray.getDimension(index, gVar.q);
                        break;
                    case 11:
                        gVar.r = typedArray.getFloat(index, gVar.r);
                        break;
                    case 12:
                        gVar.t = typedArray.getFloat(index, gVar.t);
                        break;
                    case 13:
                        gVar.u = typedArray.getFloat(index, gVar.u);
                        break;
                    case 14:
                        gVar.s = typedArray.getFloat(index, gVar.s);
                        break;
                    case 15:
                        gVar.v = typedArray.getFloat(index, gVar.v);
                        break;
                    case 16:
                        gVar.w = typedArray.getFloat(index, gVar.w);
                        break;
                    case 17:
                        gVar.x = typedArray.getDimension(index, gVar.x);
                        break;
                    case 18:
                        gVar.y = typedArray.getDimension(index, gVar.y);
                        break;
                    case 19:
                        if (Build.VERSION.SDK_INT >= 21) {
                            gVar.z = typedArray.getDimension(index, gVar.z);
                        }
                        break;
                    case 20:
                        gVar.n = typedArray.getFloat(index, gVar.n);
                        break;
                    case 21:
                        gVar.m = typedArray.getFloat(index, gVar.m) / 360.0f;
                        break;
                    default:
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f931a.get(index));
                        break;
                }
            }
        }
    }

    public g() {
        this.f929e = 4;
        this.f = new HashMap<>();
    }

    public void Y(HashMap<String, b.e.a.a.c> map) {
        b.e.a.a.c cVar;
        b.e.a.a.c cVar2;
        for (String str : map.keySet()) {
            if (str.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute = this.f.get(str.substring(7));
                if (constraintAttribute != null && constraintAttribute.d() == ConstraintAttribute.AttributeType.FLOAT_TYPE && (cVar = map.get(str)) != null) {
                    cVar.e(this.f926b, this.i, this.j, this.o, this.k, this.l, this.m, constraintAttribute.e(), constraintAttribute);
                }
            } else {
                float fZ = Z(str);
                if (!Float.isNaN(fZ) && (cVar2 = map.get(str)) != null) {
                    cVar2.d(this.f926b, this.i, this.j, this.o, this.k, this.l, this.m, fZ);
                }
            }
        }
    }

    public float Z(String str) {
        str.hashCode();
        switch (str) {
            case "rotationX":
                return this.t;
            case "rotationY":
                return this.u;
            case "translationX":
                return this.x;
            case "translationY":
                return this.y;
            case "translationZ":
                return this.z;
            case "progress":
                return this.n;
            case "scaleX":
                return this.v;
            case "scaleY":
                return this.w;
            case "rotation":
                return this.r;
            case "elevation":
                return this.q;
            case "transitionPathRotate":
                return this.s;
            case "alpha":
                return this.p;
            case "waveOffset":
                return this.l;
            case "wavePhase":
                return this.m;
            default:
                if (str.startsWith("CUSTOM")) {
                    return Float.NaN;
                }
                Log.v("WARNING! KeyCycle", "  UNKNOWN  " + str);
                return Float.NaN;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void a(HashMap<String, b.e.a.a.d> map) {
        b.g("KeyCycle", "add " + map.size() + " values", 2);
        for (String str : map.keySet()) {
            b.e.a.a.d dVar = map.get(str);
            if (dVar != null) {
                str.hashCode();
                switch (str) {
                    case "rotationX":
                        dVar.c(this.f926b, this.t);
                        break;
                    case "rotationY":
                        dVar.c(this.f926b, this.u);
                        break;
                    case "translationX":
                        dVar.c(this.f926b, this.x);
                        break;
                    case "translationY":
                        dVar.c(this.f926b, this.y);
                        break;
                    case "translationZ":
                        dVar.c(this.f926b, this.z);
                        break;
                    case "progress":
                        dVar.c(this.f926b, this.n);
                        break;
                    case "scaleX":
                        dVar.c(this.f926b, this.v);
                        break;
                    case "scaleY":
                        dVar.c(this.f926b, this.w);
                        break;
                    case "rotation":
                        dVar.c(this.f926b, this.r);
                        break;
                    case "elevation":
                        dVar.c(this.f926b, this.q);
                        break;
                    case "transitionPathRotate":
                        dVar.c(this.f926b, this.s);
                        break;
                    case "alpha":
                        dVar.c(this.f926b, this.p);
                        break;
                    case "waveOffset":
                        dVar.c(this.f926b, this.l);
                        break;
                    case "wavePhase":
                        dVar.c(this.f926b, this.m);
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

    @Override // androidx.constraintlayout.motion.widget.e
    /* JADX INFO: renamed from: b */
    public e clone() {
        return new g().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public e c(e eVar) {
        super.c(eVar);
        g gVar = (g) eVar;
        this.g = gVar.g;
        this.h = gVar.h;
        this.i = gVar.i;
        this.j = gVar.j;
        this.k = gVar.k;
        this.l = gVar.l;
        this.m = gVar.m;
        this.n = gVar.n;
        this.o = gVar.o;
        this.p = gVar.p;
        this.q = gVar.q;
        this.r = gVar.r;
        this.s = gVar.s;
        this.t = gVar.t;
        this.u = gVar.u;
        this.v = gVar.v;
        this.w = gVar.w;
        this.x = gVar.x;
        this.y = gVar.y;
        this.z = gVar.z;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void d(HashSet<String> hashSet) {
        if (!Float.isNaN(this.p)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.u)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.v)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.w)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.x)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.y)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.z)) {
            hashSet.add("translationZ");
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
        a.b(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.q6));
    }
}
