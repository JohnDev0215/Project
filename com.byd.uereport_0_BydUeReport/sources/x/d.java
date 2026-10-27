package x;

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
import x.r;

/* JADX INFO: compiled from: KeyAttributes.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f8770g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f8771h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f8772i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f8773j = Float.NaN;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f8774k = Float.NaN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f8775l = Float.NaN;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f8776m = Float.NaN;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f8777n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f8778o = Float.NaN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f8779p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f8780q = Float.NaN;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f8781r = Float.NaN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f8782s = Float.NaN;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f8783t = Float.NaN;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f8784u = Float.NaN;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f8785v = Float.NaN;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f8786w = Float.NaN;

    /* JADX INFO: compiled from: KeyAttributes.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f8787a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f8787a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyAttribute_android_alpha, 1);
            f8787a.append(R$styleable.KeyAttribute_android_elevation, 2);
            f8787a.append(R$styleable.KeyAttribute_android_rotation, 4);
            f8787a.append(R$styleable.KeyAttribute_android_rotationX, 5);
            f8787a.append(R$styleable.KeyAttribute_android_rotationY, 6);
            f8787a.append(R$styleable.KeyAttribute_android_transformPivotX, 19);
            f8787a.append(R$styleable.KeyAttribute_android_transformPivotY, 20);
            f8787a.append(R$styleable.KeyAttribute_android_scaleX, 7);
            f8787a.append(R$styleable.KeyAttribute_transitionPathRotate, 8);
            f8787a.append(R$styleable.KeyAttribute_transitionEasing, 9);
            f8787a.append(R$styleable.KeyAttribute_motionTarget, 10);
            f8787a.append(R$styleable.KeyAttribute_framePosition, 12);
            f8787a.append(R$styleable.KeyAttribute_curveFit, 13);
            f8787a.append(R$styleable.KeyAttribute_android_scaleY, 14);
            f8787a.append(R$styleable.KeyAttribute_android_translationX, 15);
            f8787a.append(R$styleable.KeyAttribute_android_translationY, 16);
            f8787a.append(R$styleable.KeyAttribute_android_translationZ, 17);
            f8787a.append(R$styleable.KeyAttribute_motionProgress, 18);
        }

        public static void a(d dVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArray.getIndex(i8);
                switch (f8787a.get(index)) {
                    case 1:
                        dVar.f8773j = typedArray.getFloat(index, dVar.f8773j);
                        break;
                    case 2:
                        dVar.f8774k = typedArray.getDimension(index, dVar.f8774k);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + f8787a.get(index));
                        break;
                    case 4:
                        dVar.f8775l = typedArray.getFloat(index, dVar.f8775l);
                        break;
                    case 5:
                        dVar.f8776m = typedArray.getFloat(index, dVar.f8776m);
                        break;
                    case 6:
                        dVar.f8777n = typedArray.getFloat(index, dVar.f8777n);
                        break;
                    case 7:
                        dVar.f8781r = typedArray.getFloat(index, dVar.f8781r);
                        break;
                    case 8:
                        dVar.f8780q = typedArray.getFloat(index, dVar.f8780q);
                        break;
                    case 9:
                        dVar.f8770g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.A) {
                            int resourceId = typedArray.getResourceId(index, dVar.f8766c);
                            dVar.f8766c = resourceId;
                            if (resourceId == -1) {
                                dVar.f8767d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            dVar.f8767d = typedArray.getString(index);
                        } else {
                            dVar.f8766c = typedArray.getResourceId(index, dVar.f8766c);
                        }
                        break;
                    case 12:
                        dVar.f8765b = typedArray.getInt(index, dVar.f8765b);
                        break;
                    case 13:
                        dVar.f8771h = typedArray.getInteger(index, dVar.f8771h);
                        break;
                    case 14:
                        dVar.f8782s = typedArray.getFloat(index, dVar.f8782s);
                        break;
                    case 15:
                        dVar.f8783t = typedArray.getDimension(index, dVar.f8783t);
                        break;
                    case 16:
                        dVar.f8784u = typedArray.getDimension(index, dVar.f8784u);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            dVar.f8785v = typedArray.getDimension(index, dVar.f8785v);
                        }
                        break;
                    case 18:
                        dVar.f8786w = typedArray.getFloat(index, dVar.f8786w);
                        break;
                    case 19:
                        dVar.f8778o = typedArray.getDimension(index, dVar.f8778o);
                        break;
                    case 20:
                        dVar.f8779p = typedArray.getDimension(index, dVar.f8779p);
                        break;
                }
            }
        }
    }

    public d() {
        this.f8768e = 1;
        this.f8769f = new HashMap<>();
    }

    @Override // x.c
    public void a(HashMap<String, r> map) {
        for (String str : map.keySet()) {
            r rVar = map.get(str);
            if (!str.startsWith("CUSTOM")) {
                switch (str) {
                    case "rotationX":
                        if (Float.isNaN(this.f8776m)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8776m);
                            break;
                        }
                        break;
                    case "rotationY":
                        if (Float.isNaN(this.f8777n)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8777n);
                            break;
                        }
                        break;
                    case "translationX":
                        if (Float.isNaN(this.f8783t)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8783t);
                            break;
                        }
                        break;
                    case "translationY":
                        if (Float.isNaN(this.f8784u)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8784u);
                            break;
                        }
                        break;
                    case "translationZ":
                        if (Float.isNaN(this.f8785v)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8785v);
                            break;
                        }
                        break;
                    case "progress":
                        if (Float.isNaN(this.f8786w)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8786w);
                            break;
                        }
                        break;
                    case "scaleX":
                        if (Float.isNaN(this.f8781r)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8781r);
                            break;
                        }
                        break;
                    case "scaleY":
                        if (Float.isNaN(this.f8782s)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8782s);
                            break;
                        }
                        break;
                    case "transformPivotX":
                        if (Float.isNaN(this.f8776m)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8778o);
                            break;
                        }
                        break;
                    case "transformPivotY":
                        if (Float.isNaN(this.f8777n)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8779p);
                            break;
                        }
                        break;
                    case "rotation":
                        if (Float.isNaN(this.f8775l)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8775l);
                            break;
                        }
                        break;
                    case "elevation":
                        if (Float.isNaN(this.f8774k)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8774k);
                            break;
                        }
                        break;
                    case "transitionPathRotate":
                        if (Float.isNaN(this.f8780q)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8780q);
                            break;
                        }
                        break;
                    case "alpha":
                        if (Float.isNaN(this.f8773j)) {
                            break;
                        } else {
                            rVar.e(this.f8765b, this.f8773j);
                            break;
                        }
                        break;
                    default:
                        Log.v("KeyAttributes", "UNKNOWN addValues \"" + str + "\"");
                        break;
                }
            } else {
                ConstraintAttribute constraintAttribute = this.f8769f.get(str.substring(7));
                if (constraintAttribute != null) {
                    ((r.b) rVar).i(this.f8765b, constraintAttribute);
                }
            }
        }
    }

    @Override // x.c
    public void b(HashSet<String> hashSet) {
        if (!Float.isNaN(this.f8773j)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f8774k)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f8775l)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.f8776m)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.f8777n)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.f8778o)) {
            hashSet.add("transformPivotX");
        }
        if (!Float.isNaN(this.f8779p)) {
            hashSet.add("transformPivotY");
        }
        if (!Float.isNaN(this.f8783t)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.f8784u)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.f8785v)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.f8780q)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.f8781r)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.f8781r)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.f8786w)) {
            hashSet.add("progress");
        }
        if (this.f8769f.size() > 0) {
            Iterator<String> it = this.f8769f.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // x.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyAttribute));
    }

    @Override // x.c
    public void e(HashMap<String, Integer> map) {
        if (this.f8771h == -1) {
            return;
        }
        if (!Float.isNaN(this.f8773j)) {
            map.put("alpha", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8774k)) {
            map.put("elevation", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8775l)) {
            map.put("rotation", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8776m)) {
            map.put("rotationX", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8777n)) {
            map.put("rotationY", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8778o)) {
            map.put("transformPivotX", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8779p)) {
            map.put("transformPivotY", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8783t)) {
            map.put("translationX", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8784u)) {
            map.put("translationY", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8785v)) {
            map.put("translationZ", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8780q)) {
            map.put("transitionPathRotate", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8781r)) {
            map.put("scaleX", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8782s)) {
            map.put("scaleY", Integer.valueOf(this.f8771h));
        }
        if (!Float.isNaN(this.f8786w)) {
            map.put("progress", Integer.valueOf(this.f8771h));
        }
        if (this.f8769f.size() > 0) {
            Iterator<String> it = this.f8769f.keySet().iterator();
            while (it.hasNext()) {
                map.put("CUSTOM," + it.next(), Integer.valueOf(this.f8771h));
            }
        }
    }
}
