package b.e.a.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f1251g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1252h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1253i = -1;
    public float j = Float.NaN;
    public float k = 0.0f;
    public float l = Float.NaN;
    public int m = -1;
    public float n = Float.NaN;
    public float o = Float.NaN;
    public float p = Float.NaN;
    public float q = Float.NaN;
    public float r = Float.NaN;
    public float s = Float.NaN;
    public float t = Float.NaN;
    public float u = Float.NaN;
    public float v = Float.NaN;
    public float w = Float.NaN;
    public float x = Float.NaN;

    /* JADX INFO: compiled from: KeyCycle.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f1254a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1254a = sparseIntArray;
            sparseIntArray.append(b.e.c.e.h4, 1);
            f1254a.append(b.e.c.e.f4, 2);
            f1254a.append(b.e.c.e.i4, 3);
            f1254a.append(b.e.c.e.e4, 4);
            f1254a.append(b.e.c.e.m4, 5);
            f1254a.append(b.e.c.e.l4, 6);
            f1254a.append(b.e.c.e.k4, 7);
            f1254a.append(b.e.c.e.n4, 8);
            f1254a.append(b.e.c.e.U3, 9);
            f1254a.append(b.e.c.e.d4, 10);
            f1254a.append(b.e.c.e.Z3, 11);
            f1254a.append(b.e.c.e.a4, 12);
            f1254a.append(b.e.c.e.b4, 13);
            f1254a.append(b.e.c.e.j4, 14);
            f1254a.append(b.e.c.e.X3, 15);
            f1254a.append(b.e.c.e.Y3, 16);
            f1254a.append(b.e.c.e.V3, 17);
            f1254a.append(b.e.c.e.W3, 18);
            f1254a.append(b.e.c.e.c4, 19);
            f1254a.append(b.e.c.e.g4, 20);
        }

        public static void b(f fVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f1254a.get(index)) {
                    case 1:
                        if (MotionLayout.W3) {
                            int resourceId = typedArray.getResourceId(index, fVar.f1242c);
                            fVar.f1242c = resourceId;
                            if (resourceId == -1) {
                                fVar.f1243d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            fVar.f1243d = typedArray.getString(index);
                        } else {
                            fVar.f1242c = typedArray.getResourceId(index, fVar.f1242c);
                        }
                        break;
                    case 2:
                        fVar.f1241b = typedArray.getInt(index, fVar.f1241b);
                        break;
                    case 3:
                        fVar.f1251g = typedArray.getString(index);
                        break;
                    case 4:
                        fVar.f1252h = typedArray.getInteger(index, fVar.f1252h);
                        break;
                    case 5:
                        fVar.f1253i = typedArray.getInt(index, fVar.f1253i);
                        break;
                    case 6:
                        fVar.j = typedArray.getFloat(index, fVar.j);
                        break;
                    case 7:
                        if (typedArray.peekValue(index).type == 5) {
                            fVar.k = typedArray.getDimension(index, fVar.k);
                        } else {
                            fVar.k = typedArray.getFloat(index, fVar.k);
                        }
                        break;
                    case 8:
                        fVar.m = typedArray.getInt(index, fVar.m);
                        break;
                    case 9:
                        fVar.n = typedArray.getFloat(index, fVar.n);
                        break;
                    case 10:
                        fVar.o = typedArray.getDimension(index, fVar.o);
                        break;
                    case 11:
                        fVar.p = typedArray.getFloat(index, fVar.p);
                        break;
                    case 12:
                        fVar.r = typedArray.getFloat(index, fVar.r);
                        break;
                    case 13:
                        fVar.s = typedArray.getFloat(index, fVar.s);
                        break;
                    case 14:
                        fVar.q = typedArray.getFloat(index, fVar.q);
                        break;
                    case 15:
                        fVar.t = typedArray.getFloat(index, fVar.t);
                        break;
                    case 16:
                        fVar.u = typedArray.getFloat(index, fVar.u);
                        break;
                    case 17:
                        fVar.v = typedArray.getDimension(index, fVar.v);
                        break;
                    case 18:
                        fVar.w = typedArray.getDimension(index, fVar.w);
                        break;
                    case 19:
                        if (Build.VERSION.SDK_INT >= 21) {
                            fVar.x = typedArray.getDimension(index, fVar.x);
                        }
                        break;
                    case 20:
                        fVar.l = typedArray.getFloat(index, fVar.l);
                        break;
                    default:
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1254a.get(index));
                        break;
                }
            }
        }
    }

    public f() {
        this.f1244e = 4;
        this.f1245f = new HashMap<>();
    }

    public void O(HashMap<String, g> map) {
        for (String str : map.keySet()) {
            if (str.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute = this.f1245f.get(str.substring(7));
                if (constraintAttribute != null && constraintAttribute.c() == ConstraintAttribute.AttributeType.FLOAT_TYPE) {
                    map.get(str).e(this.f1241b, this.f1253i, this.m, this.j, this.k, constraintAttribute.d(), constraintAttribute);
                }
            }
            float fP = P(str);
            if (!Float.isNaN(fP)) {
                map.get(str).d(this.f1241b, this.f1253i, this.m, this.j, this.k, fP);
            }
        }
    }

    public float P(String str) {
        str.hashCode();
        switch (str) {
            case "rotationX":
                return this.r;
            case "rotationY":
                return this.s;
            case "translationX":
                return this.v;
            case "translationY":
                return this.w;
            case "translationZ":
                return this.x;
            case "progress":
                return this.l;
            case "scaleX":
                return this.t;
            case "scaleY":
                return this.u;
            case "rotation":
                return this.p;
            case "elevation":
                return this.o;
            case "transitionPathRotate":
                return this.q;
            case "alpha":
                return this.n;
            case "waveOffset":
                return this.k;
            default:
                Log.v("WARNING! KeyCycle", "  UNKNOWN  " + str);
                return Float.NaN;
        }
    }

    @Override // b.e.a.b.c
    public void a(HashMap<String, r> map) {
        r rVar;
        b.e.a.b.a.e("KeyCycle", "add " + map.size() + " values", 2);
        for (String str : map.keySet()) {
            rVar = map.get(str);
            str.hashCode();
            switch (str) {
                case "rotationX":
                    rVar.e(this.f1241b, this.r);
                    break;
                case "rotationY":
                    rVar.e(this.f1241b, this.s);
                    break;
                case "translationX":
                    rVar.e(this.f1241b, this.v);
                    break;
                case "translationY":
                    rVar.e(this.f1241b, this.w);
                    break;
                case "translationZ":
                    rVar.e(this.f1241b, this.x);
                    break;
                case "progress":
                    rVar.e(this.f1241b, this.l);
                    break;
                case "scaleX":
                    rVar.e(this.f1241b, this.t);
                    break;
                case "scaleY":
                    rVar.e(this.f1241b, this.u);
                    break;
                case "rotation":
                    rVar.e(this.f1241b, this.p);
                    break;
                case "elevation":
                    rVar.e(this.f1241b, this.o);
                    break;
                case "transitionPathRotate":
                    rVar.e(this.f1241b, this.q);
                    break;
                case "alpha":
                    rVar.e(this.f1241b, this.n);
                    break;
                case "waveOffset":
                    rVar.e(this.f1241b, this.k);
                    break;
                default:
                    Log.v("WARNING KeyCycle", "  UNKNOWN  " + str);
                    break;
            }
        }
    }

    @Override // b.e.a.b.c
    public void b(HashSet<String> hashSet) {
        if (!Float.isNaN(this.n)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.u)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.v)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.w)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.x)) {
            hashSet.add("translationZ");
        }
        if (this.f1245f.size() > 0) {
            Iterator<String> it = this.f1245f.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // b.e.a.b.c
    public void c(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, b.e.c.e.T3));
    }
}
