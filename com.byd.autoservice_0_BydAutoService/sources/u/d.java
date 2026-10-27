package u;

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
import u.r;

/* JADX INFO: compiled from: KeyAttributes.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f5056g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5057h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5058i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f5059j = Float.NaN;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f5060k = Float.NaN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f5061l = Float.NaN;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f5062m = Float.NaN;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f5063n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f5064o = Float.NaN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f5065p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f5066q = Float.NaN;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f5067r = Float.NaN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f5068s = Float.NaN;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f5069t = Float.NaN;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f5070u = Float.NaN;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f5071v = Float.NaN;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f5072w = Float.NaN;

    /* JADX INFO: compiled from: KeyAttributes.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f5073a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f5073a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyAttribute_android_alpha, 1);
            f5073a.append(R$styleable.KeyAttribute_android_elevation, 2);
            f5073a.append(R$styleable.KeyAttribute_android_rotation, 4);
            f5073a.append(R$styleable.KeyAttribute_android_rotationX, 5);
            f5073a.append(R$styleable.KeyAttribute_android_rotationY, 6);
            f5073a.append(R$styleable.KeyAttribute_android_transformPivotX, 19);
            f5073a.append(R$styleable.KeyAttribute_android_transformPivotY, 20);
            f5073a.append(R$styleable.KeyAttribute_android_scaleX, 7);
            f5073a.append(R$styleable.KeyAttribute_transitionPathRotate, 8);
            f5073a.append(R$styleable.KeyAttribute_transitionEasing, 9);
            f5073a.append(R$styleable.KeyAttribute_motionTarget, 10);
            f5073a.append(R$styleable.KeyAttribute_framePosition, 12);
            f5073a.append(R$styleable.KeyAttribute_curveFit, 13);
            f5073a.append(R$styleable.KeyAttribute_android_scaleY, 14);
            f5073a.append(R$styleable.KeyAttribute_android_translationX, 15);
            f5073a.append(R$styleable.KeyAttribute_android_translationY, 16);
            f5073a.append(R$styleable.KeyAttribute_android_translationZ, 17);
            f5073a.append(R$styleable.KeyAttribute_motionProgress, 18);
        }

        public static void a(d dVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArray.getIndex(i3);
                switch (f5073a.get(index)) {
                    case 1:
                        dVar.f5059j = typedArray.getFloat(index, dVar.f5059j);
                        break;
                    case 2:
                        dVar.f5060k = typedArray.getDimension(index, dVar.f5060k);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + f5073a.get(index));
                        break;
                    case 4:
                        dVar.f5061l = typedArray.getFloat(index, dVar.f5061l);
                        break;
                    case 5:
                        dVar.f5062m = typedArray.getFloat(index, dVar.f5062m);
                        break;
                    case 6:
                        dVar.f5063n = typedArray.getFloat(index, dVar.f5063n);
                        break;
                    case 7:
                        dVar.f5067r = typedArray.getFloat(index, dVar.f5067r);
                        break;
                    case 8:
                        dVar.f5066q = typedArray.getFloat(index, dVar.f5066q);
                        break;
                    case 9:
                        dVar.f5056g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.f736y) {
                            int resourceId = typedArray.getResourceId(index, dVar.f5052c);
                            dVar.f5052c = resourceId;
                            if (resourceId == -1) {
                                dVar.f5053d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            dVar.f5053d = typedArray.getString(index);
                        } else {
                            dVar.f5052c = typedArray.getResourceId(index, dVar.f5052c);
                        }
                        break;
                    case 12:
                        dVar.f5051b = typedArray.getInt(index, dVar.f5051b);
                        break;
                    case 13:
                        dVar.f5057h = typedArray.getInteger(index, dVar.f5057h);
                        break;
                    case 14:
                        dVar.f5068s = typedArray.getFloat(index, dVar.f5068s);
                        break;
                    case 15:
                        dVar.f5069t = typedArray.getDimension(index, dVar.f5069t);
                        break;
                    case 16:
                        dVar.f5070u = typedArray.getDimension(index, dVar.f5070u);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            dVar.f5071v = typedArray.getDimension(index, dVar.f5071v);
                        }
                        break;
                    case 18:
                        dVar.f5072w = typedArray.getFloat(index, dVar.f5072w);
                        break;
                    case 19:
                        dVar.f5064o = typedArray.getDimension(index, dVar.f5064o);
                        break;
                    case 20:
                        dVar.f5065p = typedArray.getDimension(index, dVar.f5065p);
                        break;
                }
            }
        }
    }

    public d() {
        this.f5054e = 1;
        this.f5055f = new HashMap<>();
    }

    @Override // u.c
    public void a(HashMap<String, r> map) {
        for (String str : map.keySet()) {
            r rVar = map.get(str);
            if (!str.startsWith("CUSTOM")) {
                switch (str) {
                    case "rotationX":
                        if (Float.isNaN(this.f5062m)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5062m);
                            break;
                        }
                        break;
                    case "rotationY":
                        if (Float.isNaN(this.f5063n)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5063n);
                            break;
                        }
                        break;
                    case "translationX":
                        if (Float.isNaN(this.f5069t)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5069t);
                            break;
                        }
                        break;
                    case "translationY":
                        if (Float.isNaN(this.f5070u)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5070u);
                            break;
                        }
                        break;
                    case "translationZ":
                        if (Float.isNaN(this.f5071v)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5071v);
                            break;
                        }
                        break;
                    case "progress":
                        if (Float.isNaN(this.f5072w)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5072w);
                            break;
                        }
                        break;
                    case "scaleX":
                        if (Float.isNaN(this.f5067r)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5067r);
                            break;
                        }
                        break;
                    case "scaleY":
                        if (Float.isNaN(this.f5068s)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5068s);
                            break;
                        }
                        break;
                    case "transformPivotX":
                        if (Float.isNaN(this.f5062m)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5064o);
                            break;
                        }
                        break;
                    case "transformPivotY":
                        if (Float.isNaN(this.f5063n)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5065p);
                            break;
                        }
                        break;
                    case "rotation":
                        if (Float.isNaN(this.f5061l)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5061l);
                            break;
                        }
                        break;
                    case "elevation":
                        if (Float.isNaN(this.f5060k)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5060k);
                            break;
                        }
                        break;
                    case "transitionPathRotate":
                        if (Float.isNaN(this.f5066q)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5066q);
                            break;
                        }
                        break;
                    case "alpha":
                        if (Float.isNaN(this.f5059j)) {
                            break;
                        } else {
                            rVar.e(this.f5051b, this.f5059j);
                            break;
                        }
                        break;
                    default:
                        Log.v("KeyAttributes", "UNKNOWN addValues \"" + str + "\"");
                        break;
                }
            } else {
                ConstraintAttribute constraintAttribute = this.f5055f.get(str.substring(7));
                if (constraintAttribute != null) {
                    ((r.b) rVar).i(this.f5051b, constraintAttribute);
                }
            }
        }
    }

    @Override // u.c
    public void b(HashSet<String> hashSet) {
        if (!Float.isNaN(this.f5059j)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f5060k)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f5061l)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.f5062m)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.f5063n)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.f5064o)) {
            hashSet.add("transformPivotX");
        }
        if (!Float.isNaN(this.f5065p)) {
            hashSet.add("transformPivotY");
        }
        if (!Float.isNaN(this.f5069t)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.f5070u)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.f5071v)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.f5066q)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.f5067r)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.f5067r)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.f5072w)) {
            hashSet.add("progress");
        }
        if (this.f5055f.size() > 0) {
            Iterator<String> it = this.f5055f.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // u.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyAttribute));
    }

    @Override // u.c
    public void e(HashMap<String, Integer> map) {
        if (this.f5057h == -1) {
            return;
        }
        if (!Float.isNaN(this.f5059j)) {
            map.put("alpha", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5060k)) {
            map.put("elevation", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5061l)) {
            map.put("rotation", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5062m)) {
            map.put("rotationX", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5063n)) {
            map.put("rotationY", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5064o)) {
            map.put("transformPivotX", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5065p)) {
            map.put("transformPivotY", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5069t)) {
            map.put("translationX", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5070u)) {
            map.put("translationY", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5071v)) {
            map.put("translationZ", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5066q)) {
            map.put("transitionPathRotate", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5067r)) {
            map.put("scaleX", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5068s)) {
            map.put("scaleY", Integer.valueOf(this.f5057h));
        }
        if (!Float.isNaN(this.f5072w)) {
            map.put("progress", Integer.valueOf(this.f5057h));
        }
        if (this.f5055f.size() > 0) {
            Iterator<String> it = this.f5055f.keySet().iterator();
            while (it.hasNext()) {
                map.put("CUSTOM," + it.next(), Integer.valueOf(this.f5057h));
            }
        }
    }
}
