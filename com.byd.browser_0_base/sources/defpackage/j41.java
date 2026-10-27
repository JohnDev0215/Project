package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyTimeCycle.java */
/* JADX INFO: loaded from: classes.dex */
public final class j41 extends y31 {
    public int e = -1;
    public float f = Float.NaN;
    public float g = Float.NaN;
    public float h = Float.NaN;
    public float i = Float.NaN;
    public float j = Float.NaN;
    public float k = Float.NaN;
    public float l = Float.NaN;
    public float m = Float.NaN;
    public float n = Float.NaN;
    public float o = Float.NaN;
    public float p = Float.NaN;
    public float q = Float.NaN;
    public int r = 0;
    public float s = Float.NaN;
    public float t = 0.0f;

    /* JADX INFO: compiled from: KeyTimeCycle.java */
    public static class a {
        public static final SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_alpha, 1);
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_elevation, 2);
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_rotation, 4);
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_rotationX, 5);
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_rotationY, 6);
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_scaleX, 7);
            sparseIntArray.append(R$styleable.KeyTimeCycle_transitionPathRotate, 8);
            sparseIntArray.append(R$styleable.KeyTimeCycle_transitionEasing, 9);
            sparseIntArray.append(R$styleable.KeyTimeCycle_motionTarget, 10);
            sparseIntArray.append(R$styleable.KeyTimeCycle_framePosition, 12);
            sparseIntArray.append(R$styleable.KeyTimeCycle_curveFit, 13);
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_scaleY, 14);
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_translationX, 15);
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_translationY, 16);
            sparseIntArray.append(R$styleable.KeyTimeCycle_android_translationZ, 17);
            sparseIntArray.append(R$styleable.KeyTimeCycle_motionProgress, 18);
            sparseIntArray.append(R$styleable.KeyTimeCycle_wavePeriod, 20);
            sparseIntArray.append(R$styleable.KeyTimeCycle_waveOffset, 21);
            sparseIntArray.append(R$styleable.KeyTimeCycle_waveShape, 19);
        }
    }

    public j41() {
        this.d = new HashMap<>();
    }

    @Override // defpackage.y31
    public final void a(HashMap<String, rn3> map) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    @Override // defpackage.y31
    /* JADX INFO: renamed from: b */
    public final y31 clone() {
        j41 j41Var = new j41();
        super.c(this);
        j41Var.e = this.e;
        j41Var.r = this.r;
        j41Var.s = this.s;
        j41Var.t = this.t;
        j41Var.q = this.q;
        j41Var.f = this.f;
        j41Var.g = this.g;
        j41Var.h = this.h;
        j41Var.k = this.k;
        j41Var.i = this.i;
        j41Var.j = this.j;
        j41Var.l = this.l;
        j41Var.m = this.m;
        j41Var.n = this.n;
        j41Var.o = this.o;
        j41Var.p = this.p;
        return j41Var;
    }

    @Override // defpackage.y31
    public final void d(HashSet<String> hashSet) {
        if (!Float.isNaN(this.f)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.g)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.h)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.i)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.j)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.k)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.l)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.m)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("progress");
        }
        if (this.d.size() > 0) {
            Iterator<String> it = this.d.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // defpackage.y31
    public final void e(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.KeyTimeCycle);
        SparseIntArray sparseIntArray = a.a;
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            SparseIntArray sparseIntArray2 = a.a;
            switch (sparseIntArray2.get(index)) {
                case 1:
                    this.f = typedArrayObtainStyledAttributes.getFloat(index, this.f);
                    break;
                case 2:
                    this.g = typedArrayObtainStyledAttributes.getDimension(index, this.g);
                    break;
                case 3:
                case 11:
                default:
                    Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray2.get(index));
                    break;
                case 4:
                    this.h = typedArrayObtainStyledAttributes.getFloat(index, this.h);
                    break;
                case 5:
                    this.i = typedArrayObtainStyledAttributes.getFloat(index, this.i);
                    break;
                case 6:
                    this.j = typedArrayObtainStyledAttributes.getFloat(index, this.j);
                    break;
                case 7:
                    this.l = typedArrayObtainStyledAttributes.getFloat(index, this.l);
                    break;
                case 8:
                    this.k = typedArrayObtainStyledAttributes.getFloat(index, this.k);
                    break;
                case 9:
                    typedArrayObtainStyledAttributes.getString(index);
                    break;
                case 10:
                    if (MotionLayout.J0) {
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.b);
                        this.b = resourceId;
                        if (resourceId == -1) {
                            this.c = typedArrayObtainStyledAttributes.getString(index);
                        }
                    } else if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                        this.c = typedArrayObtainStyledAttributes.getString(index);
                    } else {
                        this.b = typedArrayObtainStyledAttributes.getResourceId(index, this.b);
                    }
                    break;
                case 12:
                    this.a = typedArrayObtainStyledAttributes.getInt(index, this.a);
                    break;
                case 13:
                    this.e = typedArrayObtainStyledAttributes.getInteger(index, this.e);
                    break;
                case 14:
                    this.m = typedArrayObtainStyledAttributes.getFloat(index, this.m);
                    break;
                case 15:
                    this.n = typedArrayObtainStyledAttributes.getDimension(index, this.n);
                    break;
                case 16:
                    this.o = typedArrayObtainStyledAttributes.getDimension(index, this.o);
                    break;
                case 17:
                    this.p = typedArrayObtainStyledAttributes.getDimension(index, this.p);
                    break;
                case 18:
                    this.q = typedArrayObtainStyledAttributes.getFloat(index, this.q);
                    break;
                case 19:
                    if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                        typedArrayObtainStyledAttributes.getString(index);
                        this.r = 7;
                    } else {
                        this.r = typedArrayObtainStyledAttributes.getInt(index, this.r);
                    }
                    break;
                case 20:
                    this.s = typedArrayObtainStyledAttributes.getFloat(index, this.s);
                    break;
                case 21:
                    if (typedArrayObtainStyledAttributes.peekValue(index).type == 5) {
                        this.t = typedArrayObtainStyledAttributes.getDimension(index, this.t);
                    } else {
                        this.t = typedArrayObtainStyledAttributes.getFloat(index, this.t);
                    }
                    break;
            }
        }
    }

    @Override // defpackage.y31
    public final void f(HashMap<String, Integer> map) {
        if (this.e == -1) {
            return;
        }
        if (!Float.isNaN(this.f)) {
            map.put("alpha", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.g)) {
            map.put("elevation", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.h)) {
            map.put("rotation", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.i)) {
            map.put("rotationX", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.j)) {
            map.put("rotationY", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.n)) {
            map.put("translationX", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.o)) {
            map.put("translationY", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.p)) {
            map.put("translationZ", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.k)) {
            map.put("transitionPathRotate", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.l)) {
            map.put("scaleX", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.l)) {
            map.put("scaleY", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.q)) {
            map.put("progress", Integer.valueOf(this.e));
        }
        if (this.d.size() > 0) {
            Iterator<String> it = this.d.keySet().iterator();
            while (it.hasNext()) {
                map.put(C0175b.j("CUSTOM,", it.next()), Integer.valueOf(this.e));
            }
        }
    }
}
