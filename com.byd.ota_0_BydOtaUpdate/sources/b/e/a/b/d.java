package b.e.a.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import b.e.a.b.r;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyAttributes.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f1246g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1247h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1248i = false;
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

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f1249a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1249a = sparseIntArray;
            sparseIntArray.append(b.e.c.e.B3, 1);
            f1249a.append(b.e.c.e.M3, 2);
            f1249a.append(b.e.c.e.I3, 4);
            f1249a.append(b.e.c.e.J3, 5);
            f1249a.append(b.e.c.e.K3, 6);
            f1249a.append(b.e.c.e.C3, 19);
            f1249a.append(b.e.c.e.D3, 20);
            f1249a.append(b.e.c.e.G3, 7);
            f1249a.append(b.e.c.e.S3, 8);
            f1249a.append(b.e.c.e.R3, 9);
            f1249a.append(b.e.c.e.Q3, 10);
            f1249a.append(b.e.c.e.O3, 12);
            f1249a.append(b.e.c.e.N3, 13);
            f1249a.append(b.e.c.e.H3, 14);
            f1249a.append(b.e.c.e.E3, 15);
            f1249a.append(b.e.c.e.F3, 16);
            f1249a.append(b.e.c.e.L3, 17);
            f1249a.append(b.e.c.e.P3, 18);
        }

        public static void a(d dVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f1249a.get(index)) {
                    case 1:
                        dVar.j = typedArray.getFloat(index, dVar.j);
                        break;
                    case 2:
                        dVar.k = typedArray.getDimension(index, dVar.k);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1249a.get(index));
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
                        dVar.f1246g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.W3) {
                            int resourceId = typedArray.getResourceId(index, dVar.f1242c);
                            dVar.f1242c = resourceId;
                            if (resourceId == -1) {
                                dVar.f1243d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            dVar.f1243d = typedArray.getString(index);
                        } else {
                            dVar.f1242c = typedArray.getResourceId(index, dVar.f1242c);
                        }
                        break;
                    case 12:
                        dVar.f1241b = typedArray.getInt(index, dVar.f1241b);
                        break;
                    case 13:
                        dVar.f1247h = typedArray.getInteger(index, dVar.f1247h);
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
        this.f1244e = 1;
        this.f1245f = new HashMap<>();
    }

    @Override // b.e.a.b.c
    public void a(HashMap<String, r> map) {
        for (String str : map.keySet()) {
            r rVar = map.get(str);
            if (!str.startsWith("CUSTOM")) {
                switch (str) {
                    case "rotationX":
                        if (Float.isNaN(this.m)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.m);
                            break;
                        }
                        break;
                    case "rotationY":
                        if (Float.isNaN(this.n)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.n);
                            break;
                        }
                        break;
                    case "translationX":
                        if (Float.isNaN(this.t)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.t);
                            break;
                        }
                        break;
                    case "translationY":
                        if (Float.isNaN(this.u)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.u);
                            break;
                        }
                        break;
                    case "translationZ":
                        if (Float.isNaN(this.v)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.v);
                            break;
                        }
                        break;
                    case "progress":
                        if (Float.isNaN(this.w)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.w);
                            break;
                        }
                        break;
                    case "scaleX":
                        if (Float.isNaN(this.r)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.r);
                            break;
                        }
                        break;
                    case "scaleY":
                        if (Float.isNaN(this.s)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.s);
                            break;
                        }
                        break;
                    case "transformPivotX":
                        if (Float.isNaN(this.m)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.o);
                            break;
                        }
                        break;
                    case "transformPivotY":
                        if (Float.isNaN(this.n)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.p);
                            break;
                        }
                        break;
                    case "rotation":
                        if (Float.isNaN(this.l)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.l);
                            break;
                        }
                        break;
                    case "elevation":
                        if (Float.isNaN(this.k)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.k);
                            break;
                        }
                        break;
                    case "transitionPathRotate":
                        if (Float.isNaN(this.q)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.q);
                            break;
                        }
                        break;
                    case "alpha":
                        if (Float.isNaN(this.j)) {
                            break;
                        } else {
                            rVar.e(this.f1241b, this.j);
                            break;
                        }
                        break;
                    default:
                        Log.v("KeyAttributes", "UNKNOWN addValues \"" + str + "\"");
                        break;
                }
            } else {
                ConstraintAttribute constraintAttribute = this.f1245f.get(str.substring(7));
                if (constraintAttribute != null) {
                    ((r.b) rVar).i(this.f1241b, constraintAttribute);
                }
            }
        }
    }

    @Override // b.e.a.b.c
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
        if (this.f1245f.size() > 0) {
            Iterator<String> it = this.f1245f.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // b.e.a.b.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, b.e.c.e.A3));
    }

    @Override // b.e.a.b.c
    public void e(HashMap<String, Integer> map) {
        if (this.f1247h == -1) {
            return;
        }
        if (!Float.isNaN(this.j)) {
            map.put("alpha", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.k)) {
            map.put("elevation", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.l)) {
            map.put("rotation", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.m)) {
            map.put("rotationX", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.n)) {
            map.put("rotationY", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.o)) {
            map.put("transformPivotX", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.p)) {
            map.put("transformPivotY", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.t)) {
            map.put("translationX", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.u)) {
            map.put("translationY", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.v)) {
            map.put("translationZ", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.q)) {
            map.put("transitionPathRotate", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.r)) {
            map.put("scaleX", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.s)) {
            map.put("scaleY", Integer.valueOf(this.f1247h));
        }
        if (!Float.isNaN(this.w)) {
            map.put("progress", Integer.valueOf(this.f1247h));
        }
        if (this.f1245f.size() > 0) {
            Iterator<String> it = this.f1245f.keySet().iterator();
            while (it.hasNext()) {
                map.put("CUSTOM," + it.next(), Integer.valueOf(this.f1247h));
            }
        }
    }
}
