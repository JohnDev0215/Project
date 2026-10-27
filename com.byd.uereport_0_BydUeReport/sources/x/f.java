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

/* JADX INFO: compiled from: KeyCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f8789g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f8790h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f8791i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f8792j = Float.NaN;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f8793k = 0.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f8794l = Float.NaN;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8795m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f8796n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f8797o = Float.NaN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f8798p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f8799q = Float.NaN;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f8800r = Float.NaN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f8801s = Float.NaN;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f8802t = Float.NaN;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f8803u = Float.NaN;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f8804v = Float.NaN;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f8805w = Float.NaN;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f8806x = Float.NaN;

    /* JADX INFO: compiled from: KeyCycle.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f8807a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f8807a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyCycle_motionTarget, 1);
            f8807a.append(R$styleable.KeyCycle_framePosition, 2);
            f8807a.append(R$styleable.KeyCycle_transitionEasing, 3);
            f8807a.append(R$styleable.KeyCycle_curveFit, 4);
            f8807a.append(R$styleable.KeyCycle_waveShape, 5);
            f8807a.append(R$styleable.KeyCycle_wavePeriod, 6);
            f8807a.append(R$styleable.KeyCycle_waveOffset, 7);
            f8807a.append(R$styleable.KeyCycle_waveVariesBy, 8);
            f8807a.append(R$styleable.KeyCycle_android_alpha, 9);
            f8807a.append(R$styleable.KeyCycle_android_elevation, 10);
            f8807a.append(R$styleable.KeyCycle_android_rotation, 11);
            f8807a.append(R$styleable.KeyCycle_android_rotationX, 12);
            f8807a.append(R$styleable.KeyCycle_android_rotationY, 13);
            f8807a.append(R$styleable.KeyCycle_transitionPathRotate, 14);
            f8807a.append(R$styleable.KeyCycle_android_scaleX, 15);
            f8807a.append(R$styleable.KeyCycle_android_scaleY, 16);
            f8807a.append(R$styleable.KeyCycle_android_translationX, 17);
            f8807a.append(R$styleable.KeyCycle_android_translationY, 18);
            f8807a.append(R$styleable.KeyCycle_android_translationZ, 19);
            f8807a.append(R$styleable.KeyCycle_motionProgress, 20);
        }

        public static void b(f fVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArray.getIndex(i8);
                switch (f8807a.get(index)) {
                    case 1:
                        if (MotionLayout.A) {
                            int resourceId = typedArray.getResourceId(index, fVar.f8766c);
                            fVar.f8766c = resourceId;
                            if (resourceId == -1) {
                                fVar.f8767d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            fVar.f8767d = typedArray.getString(index);
                        } else {
                            fVar.f8766c = typedArray.getResourceId(index, fVar.f8766c);
                        }
                        break;
                    case 2:
                        fVar.f8765b = typedArray.getInt(index, fVar.f8765b);
                        break;
                    case 3:
                        fVar.f8789g = typedArray.getString(index);
                        break;
                    case 4:
                        fVar.f8790h = typedArray.getInteger(index, fVar.f8790h);
                        break;
                    case 5:
                        fVar.f8791i = typedArray.getInt(index, fVar.f8791i);
                        break;
                    case 6:
                        fVar.f8792j = typedArray.getFloat(index, fVar.f8792j);
                        break;
                    case 7:
                        if (typedArray.peekValue(index).type == 5) {
                            fVar.f8793k = typedArray.getDimension(index, fVar.f8793k);
                        } else {
                            fVar.f8793k = typedArray.getFloat(index, fVar.f8793k);
                        }
                        break;
                    case 8:
                        fVar.f8795m = typedArray.getInt(index, fVar.f8795m);
                        break;
                    case 9:
                        fVar.f8796n = typedArray.getFloat(index, fVar.f8796n);
                        break;
                    case 10:
                        fVar.f8797o = typedArray.getDimension(index, fVar.f8797o);
                        break;
                    case 11:
                        fVar.f8798p = typedArray.getFloat(index, fVar.f8798p);
                        break;
                    case 12:
                        fVar.f8800r = typedArray.getFloat(index, fVar.f8800r);
                        break;
                    case 13:
                        fVar.f8801s = typedArray.getFloat(index, fVar.f8801s);
                        break;
                    case 14:
                        fVar.f8799q = typedArray.getFloat(index, fVar.f8799q);
                        break;
                    case 15:
                        fVar.f8802t = typedArray.getFloat(index, fVar.f8802t);
                        break;
                    case 16:
                        fVar.f8803u = typedArray.getFloat(index, fVar.f8803u);
                        break;
                    case 17:
                        fVar.f8804v = typedArray.getDimension(index, fVar.f8804v);
                        break;
                    case 18:
                        fVar.f8805w = typedArray.getDimension(index, fVar.f8805w);
                        break;
                    case 19:
                        if (Build.VERSION.SDK_INT >= 21) {
                            fVar.f8806x = typedArray.getDimension(index, fVar.f8806x);
                        }
                        break;
                    case 20:
                        fVar.f8794l = typedArray.getFloat(index, fVar.f8794l);
                        break;
                    default:
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f8807a.get(index));
                        break;
                }
            }
        }
    }

    public f() {
        this.f8768e = 4;
        this.f8769f = new HashMap<>();
    }

    public void O(HashMap<String, g> map) {
        for (String str : map.keySet()) {
            if (str.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute = this.f8769f.get(str.substring(7));
                if (constraintAttribute != null && constraintAttribute.c() == ConstraintAttribute.AttributeType.FLOAT_TYPE) {
                    map.get(str).e(this.f8765b, this.f8791i, this.f8795m, this.f8792j, this.f8793k, constraintAttribute.d(), constraintAttribute);
                }
            }
            float fP = P(str);
            if (!Float.isNaN(fP)) {
                map.get(str).d(this.f8765b, this.f8791i, this.f8795m, this.f8792j, this.f8793k, fP);
            }
        }
    }

    public float P(String str) {
        str.hashCode();
        switch (str) {
            case "rotationX":
                return this.f8800r;
            case "rotationY":
                return this.f8801s;
            case "translationX":
                return this.f8804v;
            case "translationY":
                return this.f8805w;
            case "translationZ":
                return this.f8806x;
            case "progress":
                return this.f8794l;
            case "scaleX":
                return this.f8802t;
            case "scaleY":
                return this.f8803u;
            case "rotation":
                return this.f8798p;
            case "elevation":
                return this.f8797o;
            case "transitionPathRotate":
                return this.f8799q;
            case "alpha":
                return this.f8796n;
            case "waveOffset":
                return this.f8793k;
            default:
                Log.v("WARNING! KeyCycle", "  UNKNOWN  " + str);
                return Float.NaN;
        }
    }

    @Override // x.c
    public void a(HashMap<String, r> map) {
        r rVar;
        x.a.e("KeyCycle", "add " + map.size() + " values", 2);
        for (String str : map.keySet()) {
            rVar = map.get(str);
            str.hashCode();
            switch (str) {
                case "rotationX":
                    rVar.e(this.f8765b, this.f8800r);
                    break;
                case "rotationY":
                    rVar.e(this.f8765b, this.f8801s);
                    break;
                case "translationX":
                    rVar.e(this.f8765b, this.f8804v);
                    break;
                case "translationY":
                    rVar.e(this.f8765b, this.f8805w);
                    break;
                case "translationZ":
                    rVar.e(this.f8765b, this.f8806x);
                    break;
                case "progress":
                    rVar.e(this.f8765b, this.f8794l);
                    break;
                case "scaleX":
                    rVar.e(this.f8765b, this.f8802t);
                    break;
                case "scaleY":
                    rVar.e(this.f8765b, this.f8803u);
                    break;
                case "rotation":
                    rVar.e(this.f8765b, this.f8798p);
                    break;
                case "elevation":
                    rVar.e(this.f8765b, this.f8797o);
                    break;
                case "transitionPathRotate":
                    rVar.e(this.f8765b, this.f8799q);
                    break;
                case "alpha":
                    rVar.e(this.f8765b, this.f8796n);
                    break;
                case "waveOffset":
                    rVar.e(this.f8765b, this.f8793k);
                    break;
                default:
                    Log.v("WARNING KeyCycle", "  UNKNOWN  " + str);
                    break;
            }
        }
    }

    @Override // x.c
    public void b(HashSet<String> hashSet) {
        if (!Float.isNaN(this.f8796n)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f8797o)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f8798p)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.f8800r)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.f8801s)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.f8802t)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.f8803u)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.f8799q)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.f8804v)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.f8805w)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.f8806x)) {
            hashSet.add("translationZ");
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
        a.b(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyCycle));
    }
}
