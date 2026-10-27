package defpackage;

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

/* JADX INFO: compiled from: KeyCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class v4 extends s4 {
    public String g = null;
    public int h = 0;
    public int i = -1;
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
        public static SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyCycle_motionTarget, 1);
            a.append(R$styleable.KeyCycle_framePosition, 2);
            a.append(R$styleable.KeyCycle_transitionEasing, 3);
            a.append(R$styleable.KeyCycle_curveFit, 4);
            a.append(R$styleable.KeyCycle_waveShape, 5);
            a.append(R$styleable.KeyCycle_wavePeriod, 6);
            a.append(R$styleable.KeyCycle_waveOffset, 7);
            a.append(R$styleable.KeyCycle_waveVariesBy, 8);
            a.append(R$styleable.KeyCycle_android_alpha, 9);
            a.append(R$styleable.KeyCycle_android_elevation, 10);
            a.append(R$styleable.KeyCycle_android_rotation, 11);
            a.append(R$styleable.KeyCycle_android_rotationX, 12);
            a.append(R$styleable.KeyCycle_android_rotationY, 13);
            a.append(R$styleable.KeyCycle_transitionPathRotate, 14);
            a.append(R$styleable.KeyCycle_android_scaleX, 15);
            a.append(R$styleable.KeyCycle_android_scaleY, 16);
            a.append(R$styleable.KeyCycle_android_translationX, 17);
            a.append(R$styleable.KeyCycle_android_translationY, 18);
            a.append(R$styleable.KeyCycle_android_translationZ, 19);
            a.append(R$styleable.KeyCycle_motionProgress, 20);
        }

        public static void b(v4 v4Var, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (a.get(index)) {
                    case 1:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, v4Var.c);
                            v4Var.c = resourceId;
                            if (resourceId == -1) {
                                v4Var.d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            v4Var.d = typedArray.getString(index);
                        } else {
                            v4Var.c = typedArray.getResourceId(index, v4Var.c);
                        }
                        break;
                    case 2:
                        v4Var.b = typedArray.getInt(index, v4Var.b);
                        break;
                    case 3:
                        v4Var.g = typedArray.getString(index);
                        break;
                    case 4:
                        v4Var.h = typedArray.getInteger(index, v4Var.h);
                        break;
                    case 5:
                        v4Var.i = typedArray.getInt(index, v4Var.i);
                        break;
                    case 6:
                        v4Var.j = typedArray.getFloat(index, v4Var.j);
                        break;
                    case 7:
                        if (typedArray.peekValue(index).type == 5) {
                            v4Var.k = typedArray.getDimension(index, v4Var.k);
                        } else {
                            v4Var.k = typedArray.getFloat(index, v4Var.k);
                        }
                        break;
                    case 8:
                        v4Var.m = typedArray.getInt(index, v4Var.m);
                        break;
                    case 9:
                        v4Var.n = typedArray.getFloat(index, v4Var.n);
                        break;
                    case 10:
                        v4Var.o = typedArray.getDimension(index, v4Var.o);
                        break;
                    case 11:
                        v4Var.p = typedArray.getFloat(index, v4Var.p);
                        break;
                    case 12:
                        v4Var.r = typedArray.getFloat(index, v4Var.r);
                        break;
                    case 13:
                        v4Var.s = typedArray.getFloat(index, v4Var.s);
                        break;
                    case 14:
                        v4Var.q = typedArray.getFloat(index, v4Var.q);
                        break;
                    case 15:
                        v4Var.t = typedArray.getFloat(index, v4Var.t);
                        break;
                    case 16:
                        v4Var.u = typedArray.getFloat(index, v4Var.u);
                        break;
                    case 17:
                        v4Var.v = typedArray.getDimension(index, v4Var.v);
                        break;
                    case 18:
                        v4Var.w = typedArray.getDimension(index, v4Var.w);
                        break;
                    case 19:
                        if (Build.VERSION.SDK_INT >= 21) {
                            v4Var.x = typedArray.getDimension(index, v4Var.x);
                        }
                        break;
                    case 20:
                        v4Var.l = typedArray.getFloat(index, v4Var.l);
                        break;
                    default:
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + a.get(index));
                        break;
                }
            }
        }
    }

    public v4() {
        this.e = 4;
        this.f = new HashMap<>();
    }

    public void O(HashMap<String, w4> map) {
        for (String str : map.keySet()) {
            if (str.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute = this.f.get(str.substring(7));
                if (constraintAttribute != null && constraintAttribute.c() == ConstraintAttribute.AttributeType.FLOAT_TYPE) {
                    map.get(str).e(this.b, this.i, this.m, this.j, this.k, constraintAttribute.d(), constraintAttribute);
                }
            }
            float fP = P(str);
            if (!Float.isNaN(fP)) {
                map.get(str).d(this.b, this.i, this.m, this.j, this.k, fP);
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

    @Override // defpackage.s4
    public void a(HashMap<String, h5> map) {
        h5 h5Var;
        q4.e("KeyCycle", "add " + map.size() + " values", 2);
        for (String str : map.keySet()) {
            h5Var = map.get(str);
            str.hashCode();
            switch (str) {
                case "rotationX":
                    h5Var.e(this.b, this.r);
                    break;
                case "rotationY":
                    h5Var.e(this.b, this.s);
                    break;
                case "translationX":
                    h5Var.e(this.b, this.v);
                    break;
                case "translationY":
                    h5Var.e(this.b, this.w);
                    break;
                case "translationZ":
                    h5Var.e(this.b, this.x);
                    break;
                case "progress":
                    h5Var.e(this.b, this.l);
                    break;
                case "scaleX":
                    h5Var.e(this.b, this.t);
                    break;
                case "scaleY":
                    h5Var.e(this.b, this.u);
                    break;
                case "rotation":
                    h5Var.e(this.b, this.p);
                    break;
                case "elevation":
                    h5Var.e(this.b, this.o);
                    break;
                case "transitionPathRotate":
                    h5Var.e(this.b, this.q);
                    break;
                case "alpha":
                    h5Var.e(this.b, this.n);
                    break;
                case "waveOffset":
                    h5Var.e(this.b, this.k);
                    break;
                default:
                    Log.v("WARNING KeyCycle", "  UNKNOWN  " + str);
                    break;
            }
        }
    }

    @Override // defpackage.s4
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

    @Override // defpackage.s4
    public void c(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyCycle));
    }
}
