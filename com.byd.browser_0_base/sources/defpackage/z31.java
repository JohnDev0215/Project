package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.rn3;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyAttributes.java */
/* JADX INFO: loaded from: classes.dex */
public final class z31 extends y31 {
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
    public float r = Float.NaN;
    public float s = Float.NaN;

    /* JADX INFO: compiled from: KeyAttributes.java */
    public static class a {
        public static final SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyAttribute_android_alpha, 1);
            sparseIntArray.append(R$styleable.KeyAttribute_android_elevation, 2);
            sparseIntArray.append(R$styleable.KeyAttribute_android_rotation, 4);
            sparseIntArray.append(R$styleable.KeyAttribute_android_rotationX, 5);
            sparseIntArray.append(R$styleable.KeyAttribute_android_rotationY, 6);
            sparseIntArray.append(R$styleable.KeyAttribute_android_transformPivotX, 19);
            sparseIntArray.append(R$styleable.KeyAttribute_android_transformPivotY, 20);
            sparseIntArray.append(R$styleable.KeyAttribute_android_scaleX, 7);
            sparseIntArray.append(R$styleable.KeyAttribute_transitionPathRotate, 8);
            sparseIntArray.append(R$styleable.KeyAttribute_transitionEasing, 9);
            sparseIntArray.append(R$styleable.KeyAttribute_motionTarget, 10);
            sparseIntArray.append(R$styleable.KeyAttribute_framePosition, 12);
            sparseIntArray.append(R$styleable.KeyAttribute_curveFit, 13);
            sparseIntArray.append(R$styleable.KeyAttribute_android_scaleY, 14);
            sparseIntArray.append(R$styleable.KeyAttribute_android_translationX, 15);
            sparseIntArray.append(R$styleable.KeyAttribute_android_translationY, 16);
            sparseIntArray.append(R$styleable.KeyAttribute_android_translationZ, 17);
            sparseIntArray.append(R$styleable.KeyAttribute_motionProgress, 18);
        }
    }

    public z31() {
        this.d = new HashMap<>();
    }

    @Override // defpackage.y31
    public final void a(HashMap<String, rn3> map) {
        for (String str : map.keySet()) {
            rn3 rn3Var = map.get(str);
            if (rn3Var != null) {
                if (!str.startsWith("CUSTOM")) {
                    switch (str) {
                        case "rotationX":
                            if (Float.isNaN(this.i)) {
                                break;
                            } else {
                                rn3Var.b(this.i, this.a);
                                break;
                            }
                            break;
                        case "rotationY":
                            if (Float.isNaN(this.j)) {
                                break;
                            } else {
                                rn3Var.b(this.j, this.a);
                                break;
                            }
                            break;
                        case "translationX":
                            if (Float.isNaN(this.p)) {
                                break;
                            } else {
                                rn3Var.b(this.p, this.a);
                                break;
                            }
                            break;
                        case "translationY":
                            if (Float.isNaN(this.q)) {
                                break;
                            } else {
                                rn3Var.b(this.q, this.a);
                                break;
                            }
                            break;
                        case "translationZ":
                            if (Float.isNaN(this.r)) {
                                break;
                            } else {
                                rn3Var.b(this.r, this.a);
                                break;
                            }
                            break;
                        case "progress":
                            if (Float.isNaN(this.s)) {
                                break;
                            } else {
                                rn3Var.b(this.s, this.a);
                                break;
                            }
                            break;
                        case "scaleX":
                            if (Float.isNaN(this.n)) {
                                break;
                            } else {
                                rn3Var.b(this.n, this.a);
                                break;
                            }
                            break;
                        case "scaleY":
                            if (Float.isNaN(this.o)) {
                                break;
                            } else {
                                rn3Var.b(this.o, this.a);
                                break;
                            }
                            break;
                        case "transformPivotX":
                            if (Float.isNaN(this.i)) {
                                break;
                            } else {
                                rn3Var.b(this.k, this.a);
                                break;
                            }
                            break;
                        case "transformPivotY":
                            if (Float.isNaN(this.j)) {
                                break;
                            } else {
                                rn3Var.b(this.l, this.a);
                                break;
                            }
                            break;
                        case "rotation":
                            if (Float.isNaN(this.h)) {
                                break;
                            } else {
                                rn3Var.b(this.h, this.a);
                                break;
                            }
                            break;
                        case "elevation":
                            if (Float.isNaN(this.g)) {
                                break;
                            } else {
                                rn3Var.b(this.g, this.a);
                                break;
                            }
                            break;
                        case "transitionPathRotate":
                            if (Float.isNaN(this.m)) {
                                break;
                            } else {
                                rn3Var.b(this.m, this.a);
                                break;
                            }
                            break;
                        case "alpha":
                            if (Float.isNaN(this.f)) {
                                break;
                            } else {
                                rn3Var.b(this.f, this.a);
                                break;
                            }
                            break;
                    }
                } else {
                    ox oxVar = this.d.get(str.substring(7));
                    if (oxVar != null) {
                        ((rn3.b) rn3Var).f.append(this.a, oxVar);
                    }
                }
            }
        }
    }

    @Override // defpackage.y31
    /* JADX INFO: renamed from: b */
    public final y31 clone() {
        z31 z31Var = new z31();
        super.c(this);
        z31Var.e = this.e;
        z31Var.f = this.f;
        z31Var.g = this.g;
        z31Var.h = this.h;
        z31Var.i = this.i;
        z31Var.j = this.j;
        z31Var.k = this.k;
        z31Var.l = this.l;
        z31Var.m = this.m;
        z31Var.n = this.n;
        z31Var.o = this.o;
        z31Var.p = this.p;
        z31Var.q = this.q;
        z31Var.r = this.r;
        z31Var.s = this.s;
        return z31Var;
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
        if (!Float.isNaN(this.k)) {
            hashSet.add("transformPivotX");
        }
        if (!Float.isNaN(this.l)) {
            hashSet.add("transformPivotY");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.m)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.s)) {
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
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.KeyAttribute);
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
                    Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray2.get(index));
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
                    this.n = typedArrayObtainStyledAttributes.getFloat(index, this.n);
                    break;
                case 8:
                    this.m = typedArrayObtainStyledAttributes.getFloat(index, this.m);
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
                    this.o = typedArrayObtainStyledAttributes.getFloat(index, this.o);
                    break;
                case 15:
                    this.p = typedArrayObtainStyledAttributes.getDimension(index, this.p);
                    break;
                case 16:
                    this.q = typedArrayObtainStyledAttributes.getDimension(index, this.q);
                    break;
                case 17:
                    this.r = typedArrayObtainStyledAttributes.getDimension(index, this.r);
                    break;
                case 18:
                    this.s = typedArrayObtainStyledAttributes.getFloat(index, this.s);
                    break;
                case 19:
                    this.k = typedArrayObtainStyledAttributes.getDimension(index, this.k);
                    break;
                case 20:
                    this.l = typedArrayObtainStyledAttributes.getDimension(index, this.l);
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
        if (!Float.isNaN(this.k)) {
            map.put("transformPivotX", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.l)) {
            map.put("transformPivotY", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.p)) {
            map.put("translationX", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.q)) {
            map.put("translationY", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.r)) {
            map.put("translationZ", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.m)) {
            map.put("transitionPathRotate", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.n)) {
            map.put("scaleX", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.o)) {
            map.put("scaleY", Integer.valueOf(this.e));
        }
        if (!Float.isNaN(this.s)) {
            map.put("progress", Integer.valueOf(this.e));
        }
        if (this.d.size() > 0) {
            Iterator<String> it = this.d.keySet().iterator();
            while (it.hasNext()) {
                map.put(C0175b.j("CUSTOM,", it.next()), Integer.valueOf(this.e));
            }
        }
    }

    public final void h(Object obj, String str) {
        switch (str) {
            case "motionProgress":
                this.s = y31.g((Number) obj);
                break;
            case "transitionEasing":
                obj.toString();
                break;
            case "rotationX":
                this.i = y31.g((Number) obj);
                break;
            case "rotationY":
                this.j = y31.g((Number) obj);
                break;
            case "translationX":
                this.p = y31.g((Number) obj);
                break;
            case "translationY":
                this.q = y31.g((Number) obj);
                break;
            case "translationZ":
                this.r = y31.g((Number) obj);
                break;
            case "scaleX":
                this.n = y31.g((Number) obj);
                break;
            case "scaleY":
                this.o = y31.g((Number) obj);
                break;
            case "transformPivotX":
                this.k = y31.g((Number) obj);
                break;
            case "transformPivotY":
                this.l = y31.g((Number) obj);
                break;
            case "rotation":
                this.h = y31.g((Number) obj);
                break;
            case "elevation":
                this.g = y31.g((Number) obj);
                break;
            case "transitionPathRotate":
                this.m = y31.g((Number) obj);
                break;
            case "alpha":
                this.f = y31.g((Number) obj);
                break;
            case "curveFit":
                Number number = (Number) obj;
                this.e = number instanceof Integer ? ((Integer) number).intValue() : Integer.parseInt(number.toString());
                break;
            case "visibility":
                if (!(obj instanceof Boolean)) {
                    Boolean.parseBoolean(obj.toString());
                    break;
                } else {
                    ((Boolean) obj).booleanValue();
                    break;
                }
                break;
        }
    }
}
