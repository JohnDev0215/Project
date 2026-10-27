package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.r;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyAttributes.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends c {
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
        private static SparseIntArray f624a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f624a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.B3, 1);
            f624a.append(androidx.constraintlayout.widget.e.M3, 2);
            f624a.append(androidx.constraintlayout.widget.e.I3, 4);
            f624a.append(androidx.constraintlayout.widget.e.J3, 5);
            f624a.append(androidx.constraintlayout.widget.e.K3, 6);
            f624a.append(androidx.constraintlayout.widget.e.C3, 19);
            f624a.append(androidx.constraintlayout.widget.e.D3, 20);
            f624a.append(androidx.constraintlayout.widget.e.G3, 7);
            f624a.append(androidx.constraintlayout.widget.e.S3, 8);
            f624a.append(androidx.constraintlayout.widget.e.R3, 9);
            f624a.append(androidx.constraintlayout.widget.e.Q3, 10);
            f624a.append(androidx.constraintlayout.widget.e.O3, 12);
            f624a.append(androidx.constraintlayout.widget.e.N3, 13);
            f624a.append(androidx.constraintlayout.widget.e.H3, 14);
            f624a.append(androidx.constraintlayout.widget.e.E3, 15);
            f624a.append(androidx.constraintlayout.widget.e.F3, 16);
            f624a.append(androidx.constraintlayout.widget.e.L3, 17);
            f624a.append(androidx.constraintlayout.widget.e.P3, 18);
        }

        public static void a(d dVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f624a.get(index)) {
                    case 1:
                        dVar.j = typedArray.getFloat(index, dVar.j);
                        break;
                    case 2:
                        dVar.k = typedArray.getDimension(index, dVar.k);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + f624a.get(index));
                        break;
                    case 4:
                        dVar.l = typedArray.getFloat(index, dVar.l);
                        break;
                    case 5:
                        dVar.m = typedArray.getFloat(index, dVar.m);
                        break;
                    case 6:
                        dVar.n = typedArray.getFloat(index, dVar.n);
                        break;
                    case 7:
                        dVar.r = typedArray.getFloat(index, dVar.r);
                        break;
                    case 8:
                        dVar.q = typedArray.getFloat(index, dVar.q);
                        break;
                    case 9:
                        dVar.g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, dVar.f621c);
                            dVar.f621c = resourceId;
                            if (resourceId == -1) {
                                dVar.f622d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            dVar.f622d = typedArray.getString(index);
                        } else {
                            dVar.f621c = typedArray.getResourceId(index, dVar.f621c);
                        }
                        break;
                    case 12:
                        dVar.f620b = typedArray.getInt(index, dVar.f620b);
                        break;
                    case 13:
                        dVar.h = typedArray.getInteger(index, dVar.h);
                        break;
                    case 14:
                        dVar.s = typedArray.getFloat(index, dVar.s);
                        break;
                    case 15:
                        dVar.t = typedArray.getDimension(index, dVar.t);
                        break;
                    case 16:
                        dVar.u = typedArray.getDimension(index, dVar.u);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            dVar.v = typedArray.getDimension(index, dVar.v);
                        }
                        break;
                    case 18:
                        dVar.w = typedArray.getFloat(index, dVar.w);
                        break;
                    case 19:
                        dVar.o = typedArray.getDimension(index, dVar.o);
                        break;
                    case 20:
                        dVar.p = typedArray.getDimension(index, dVar.p);
                        break;
                }
            }
        }
    }

    public d() {
        this.f623e = 1;
        this.f = new HashMap<>();
    }

    @Override // androidx.constraintlayout.motion.widget.c
    public void a(HashMap<String, r> map) {
        for (String str : map.keySet()) {
            r rVar = map.get(str);
            if (!str.startsWith("CUSTOM")) {
                switch (str) {
                    case "rotationX":
                        if (Float.isNaN(this.m)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.m);
                            break;
                        }
                        break;
                    case "rotationY":
                        if (Float.isNaN(this.n)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.n);
                            break;
                        }
                        break;
                    case "translationX":
                        if (Float.isNaN(this.t)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.t);
                            break;
                        }
                        break;
                    case "translationY":
                        if (Float.isNaN(this.u)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.u);
                            break;
                        }
                        break;
                    case "translationZ":
                        if (Float.isNaN(this.v)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.v);
                            break;
                        }
                        break;
                    case "progress":
                        if (Float.isNaN(this.w)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.w);
                            break;
                        }
                        break;
                    case "scaleX":
                        if (Float.isNaN(this.r)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.r);
                            break;
                        }
                        break;
                    case "scaleY":
                        if (Float.isNaN(this.s)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.s);
                            break;
                        }
                        break;
                    case "transformPivotX":
                        if (Float.isNaN(this.m)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.o);
                            break;
                        }
                        break;
                    case "transformPivotY":
                        if (Float.isNaN(this.n)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.p);
                            break;
                        }
                        break;
                    case "rotation":
                        if (Float.isNaN(this.l)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.l);
                            break;
                        }
                        break;
                    case "elevation":
                        if (Float.isNaN(this.k)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.k);
                            break;
                        }
                        break;
                    case "transitionPathRotate":
                        if (Float.isNaN(this.q)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.q);
                            break;
                        }
                        break;
                    case "alpha":
                        if (Float.isNaN(this.j)) {
                            break;
                        } else {
                            rVar.e(this.f620b, this.j);
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
                    ((r.b) rVar).i(this.f620b, constraintAttribute);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.c
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

    @Override // androidx.constraintlayout.motion.widget.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.A3));
    }

    @Override // androidx.constraintlayout.motion.widget.c
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
