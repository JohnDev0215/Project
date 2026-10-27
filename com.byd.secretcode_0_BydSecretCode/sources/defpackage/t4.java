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
import defpackage.h5;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyAttributes.java */
/* JADX INFO: loaded from: classes.dex */
public class t4 extends s4 {
    public String g;
    public int h = -1;
    public boolean i = false;
    public float j = Float.NaN;
    public float k = Float.NaN;
    public float l = Float.NaN;
    public float m = Float.NaN;
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

    /* JADX INFO: compiled from: KeyAttributes.java */
    public static class a {
        public static SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyAttribute_android_alpha, 1);
            a.append(R$styleable.KeyAttribute_android_elevation, 2);
            a.append(R$styleable.KeyAttribute_android_rotation, 4);
            a.append(R$styleable.KeyAttribute_android_rotationX, 5);
            a.append(R$styleable.KeyAttribute_android_rotationY, 6);
            a.append(R$styleable.KeyAttribute_android_transformPivotX, 19);
            a.append(R$styleable.KeyAttribute_android_transformPivotY, 20);
            a.append(R$styleable.KeyAttribute_android_scaleX, 7);
            a.append(R$styleable.KeyAttribute_transitionPathRotate, 8);
            a.append(R$styleable.KeyAttribute_transitionEasing, 9);
            a.append(R$styleable.KeyAttribute_motionTarget, 10);
            a.append(R$styleable.KeyAttribute_framePosition, 12);
            a.append(R$styleable.KeyAttribute_curveFit, 13);
            a.append(R$styleable.KeyAttribute_android_scaleY, 14);
            a.append(R$styleable.KeyAttribute_android_translationX, 15);
            a.append(R$styleable.KeyAttribute_android_translationY, 16);
            a.append(R$styleable.KeyAttribute_android_translationZ, 17);
            a.append(R$styleable.KeyAttribute_motionProgress, 18);
        }

        public static void a(t4 t4Var, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (a.get(index)) {
                    case 1:
                        t4Var.j = typedArray.getFloat(index, t4Var.j);
                        break;
                    case 2:
                        t4Var.k = typedArray.getDimension(index, t4Var.k);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + a.get(index));
                        break;
                    case 4:
                        t4Var.l = typedArray.getFloat(index, t4Var.l);
                        break;
                    case 5:
                        t4Var.m = typedArray.getFloat(index, t4Var.m);
                        break;
                    case 6:
                        t4Var.n = typedArray.getFloat(index, t4Var.n);
                        break;
                    case 7:
                        t4Var.r = typedArray.getFloat(index, t4Var.r);
                        break;
                    case 8:
                        t4Var.q = typedArray.getFloat(index, t4Var.q);
                        break;
                    case 9:
                        t4Var.g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, t4Var.c);
                            t4Var.c = resourceId;
                            if (resourceId == -1) {
                                t4Var.d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            t4Var.d = typedArray.getString(index);
                        } else {
                            t4Var.c = typedArray.getResourceId(index, t4Var.c);
                        }
                        break;
                    case 12:
                        t4Var.b = typedArray.getInt(index, t4Var.b);
                        break;
                    case 13:
                        t4Var.h = typedArray.getInteger(index, t4Var.h);
                        break;
                    case 14:
                        t4Var.s = typedArray.getFloat(index, t4Var.s);
                        break;
                    case 15:
                        t4Var.t = typedArray.getDimension(index, t4Var.t);
                        break;
                    case 16:
                        t4Var.u = typedArray.getDimension(index, t4Var.u);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            t4Var.v = typedArray.getDimension(index, t4Var.v);
                        }
                        break;
                    case 18:
                        t4Var.w = typedArray.getFloat(index, t4Var.w);
                        break;
                    case 19:
                        t4Var.o = typedArray.getDimension(index, t4Var.o);
                        break;
                    case 20:
                        t4Var.p = typedArray.getDimension(index, t4Var.p);
                        break;
                }
            }
        }
    }

    public t4() {
        this.e = 1;
        this.f = new HashMap<>();
    }

    @Override // defpackage.s4
    public void a(HashMap<String, h5> map) {
        for (String str : map.keySet()) {
            h5 h5Var = map.get(str);
            if (!str.startsWith("CUSTOM")) {
                switch (str) {
                    case "rotationX":
                        if (Float.isNaN(this.m)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.m);
                            break;
                        }
                        break;
                    case "rotationY":
                        if (Float.isNaN(this.n)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.n);
                            break;
                        }
                        break;
                    case "translationX":
                        if (Float.isNaN(this.t)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.t);
                            break;
                        }
                        break;
                    case "translationY":
                        if (Float.isNaN(this.u)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.u);
                            break;
                        }
                        break;
                    case "translationZ":
                        if (Float.isNaN(this.v)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.v);
                            break;
                        }
                        break;
                    case "progress":
                        if (Float.isNaN(this.w)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.w);
                            break;
                        }
                        break;
                    case "scaleX":
                        if (Float.isNaN(this.r)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.r);
                            break;
                        }
                        break;
                    case "scaleY":
                        if (Float.isNaN(this.s)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.s);
                            break;
                        }
                        break;
                    case "transformPivotX":
                        if (Float.isNaN(this.m)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.o);
                            break;
                        }
                        break;
                    case "transformPivotY":
                        if (Float.isNaN(this.n)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.p);
                            break;
                        }
                        break;
                    case "rotation":
                        if (Float.isNaN(this.l)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.l);
                            break;
                        }
                        break;
                    case "elevation":
                        if (Float.isNaN(this.k)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.k);
                            break;
                        }
                        break;
                    case "transitionPathRotate":
                        if (Float.isNaN(this.q)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.q);
                            break;
                        }
                        break;
                    case "alpha":
                        if (Float.isNaN(this.j)) {
                            break;
                        } else {
                            h5Var.e(this.b, this.j);
                            break;
                        }
                        break;
                    default:
                        Log.v("KeyAttributes", "UNKNOWN addValues \"" + str + "\"");
                        break;
                }
            } else {
                ConstraintAttribute constraintAttribute = this.f.get(str.substring(7));
                if (constraintAttribute != null) {
                    ((h5.b) h5Var).i(this.b, constraintAttribute);
                }
            }
        }
    }

    @Override // defpackage.s4
    public void b(HashSet<String> hashSet) {
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
        if (!Float.isNaN(this.r)) {
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

    @Override // defpackage.s4
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyAttribute));
    }

    @Override // defpackage.s4
    public void e(HashMap<String, Integer> map) {
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
