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
public class f extends c {
    private String g = null;
    private int h = 0;
    private int i = -1;
    private float j = Float.NaN;
    private float k = 0.0f;
    private float l = Float.NaN;
    private int m = -1;
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
    private float x = Float.NaN;

    /* JADX INFO: compiled from: KeyCycle.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f553a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f553a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.h4, 1);
            f553a.append(androidx.constraintlayout.widget.e.f4, 2);
            f553a.append(androidx.constraintlayout.widget.e.i4, 3);
            f553a.append(androidx.constraintlayout.widget.e.e4, 4);
            f553a.append(androidx.constraintlayout.widget.e.m4, 5);
            f553a.append(androidx.constraintlayout.widget.e.l4, 6);
            f553a.append(androidx.constraintlayout.widget.e.k4, 7);
            f553a.append(androidx.constraintlayout.widget.e.n4, 8);
            f553a.append(androidx.constraintlayout.widget.e.U3, 9);
            f553a.append(androidx.constraintlayout.widget.e.d4, 10);
            f553a.append(androidx.constraintlayout.widget.e.Z3, 11);
            f553a.append(androidx.constraintlayout.widget.e.a4, 12);
            f553a.append(androidx.constraintlayout.widget.e.b4, 13);
            f553a.append(androidx.constraintlayout.widget.e.j4, 14);
            f553a.append(androidx.constraintlayout.widget.e.X3, 15);
            f553a.append(androidx.constraintlayout.widget.e.Y3, 16);
            f553a.append(androidx.constraintlayout.widget.e.V3, 17);
            f553a.append(androidx.constraintlayout.widget.e.W3, 18);
            f553a.append(androidx.constraintlayout.widget.e.c4, 19);
            f553a.append(androidx.constraintlayout.widget.e.g4, 20);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(f fVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f553a.get(index)) {
                    case 1:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, fVar.f549c);
                            fVar.f549c = resourceId;
                            if (resourceId == -1) {
                                fVar.f550d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            fVar.f550d = typedArray.getString(index);
                        } else {
                            fVar.f549c = typedArray.getResourceId(index, fVar.f549c);
                        }
                        break;
                    case 2:
                        fVar.f548b = typedArray.getInt(index, fVar.f548b);
                        break;
                    case 3:
                        fVar.g = typedArray.getString(index);
                        break;
                    case 4:
                        fVar.h = typedArray.getInteger(index, fVar.h);
                        break;
                    case 5:
                        fVar.i = typedArray.getInt(index, fVar.i);
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
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f553a.get(index));
                        break;
                }
            }
        }
    }

    public f() {
        this.e = 4;
        this.f = new HashMap<>();
    }

    public void O(HashMap<String, g> map) {
        for (String str : map.keySet()) {
            if (str.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute = this.f.get(str.substring(7));
                if (constraintAttribute != null && constraintAttribute.c() == ConstraintAttribute.AttributeType.FLOAT_TYPE) {
                    map.get(str).e(this.f548b, this.i, this.m, this.j, this.k, constraintAttribute.d(), constraintAttribute);
                }
            }
            float fP = P(str);
            if (!Float.isNaN(fP)) {
                map.get(str).d(this.f548b, this.i, this.m, this.j, this.k, fP);
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

    @Override // androidx.constraintlayout.motion.widget.c
    public void a(HashMap<String, r> map) {
        r rVar;
        androidx.constraintlayout.motion.widget.a.e("KeyCycle", "add " + map.size() + " values", 2);
        for (String str : map.keySet()) {
            rVar = map.get(str);
            str.hashCode();
            switch (str) {
                case "rotationX":
                    rVar.e(this.f548b, this.r);
                    break;
                case "rotationY":
                    rVar.e(this.f548b, this.s);
                    break;
                case "translationX":
                    rVar.e(this.f548b, this.v);
                    break;
                case "translationY":
                    rVar.e(this.f548b, this.w);
                    break;
                case "translationZ":
                    rVar.e(this.f548b, this.x);
                    break;
                case "progress":
                    rVar.e(this.f548b, this.l);
                    break;
                case "scaleX":
                    rVar.e(this.f548b, this.t);
                    break;
                case "scaleY":
                    rVar.e(this.f548b, this.u);
                    break;
                case "rotation":
                    rVar.e(this.f548b, this.p);
                    break;
                case "elevation":
                    rVar.e(this.f548b, this.o);
                    break;
                case "transitionPathRotate":
                    rVar.e(this.f548b, this.q);
                    break;
                case "alpha":
                    rVar.e(this.f548b, this.n);
                    break;
                case "waveOffset":
                    rVar.e(this.f548b, this.k);
                    break;
                default:
                    Log.v("WARNING KeyCycle", "  UNKNOWN  " + str);
                    break;
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.c
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
        if (this.f.size() > 0) {
            Iterator<String> it = this.f.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.c
    public void c(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.T3));
    }
}
