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

/* JADX INFO: compiled from: KeyCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f5075g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5076h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5077i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f5078j = Float.NaN;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f5079k = 0.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f5080l = Float.NaN;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5081m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f5082n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f5083o = Float.NaN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f5084p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f5085q = Float.NaN;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f5086r = Float.NaN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f5087s = Float.NaN;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f5088t = Float.NaN;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f5089u = Float.NaN;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f5090v = Float.NaN;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f5091w = Float.NaN;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f5092x = Float.NaN;

    /* JADX INFO: compiled from: KeyCycle.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f5093a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f5093a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyCycle_motionTarget, 1);
            f5093a.append(R$styleable.KeyCycle_framePosition, 2);
            f5093a.append(R$styleable.KeyCycle_transitionEasing, 3);
            f5093a.append(R$styleable.KeyCycle_curveFit, 4);
            f5093a.append(R$styleable.KeyCycle_waveShape, 5);
            f5093a.append(R$styleable.KeyCycle_wavePeriod, 6);
            f5093a.append(R$styleable.KeyCycle_waveOffset, 7);
            f5093a.append(R$styleable.KeyCycle_waveVariesBy, 8);
            f5093a.append(R$styleable.KeyCycle_android_alpha, 9);
            f5093a.append(R$styleable.KeyCycle_android_elevation, 10);
            f5093a.append(R$styleable.KeyCycle_android_rotation, 11);
            f5093a.append(R$styleable.KeyCycle_android_rotationX, 12);
            f5093a.append(R$styleable.KeyCycle_android_rotationY, 13);
            f5093a.append(R$styleable.KeyCycle_transitionPathRotate, 14);
            f5093a.append(R$styleable.KeyCycle_android_scaleX, 15);
            f5093a.append(R$styleable.KeyCycle_android_scaleY, 16);
            f5093a.append(R$styleable.KeyCycle_android_translationX, 17);
            f5093a.append(R$styleable.KeyCycle_android_translationY, 18);
            f5093a.append(R$styleable.KeyCycle_android_translationZ, 19);
            f5093a.append(R$styleable.KeyCycle_motionProgress, 20);
        }

        public static void b(f fVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArray.getIndex(i3);
                switch (f5093a.get(index)) {
                    case 1:
                        if (MotionLayout.f736y) {
                            int resourceId = typedArray.getResourceId(index, fVar.f5052c);
                            fVar.f5052c = resourceId;
                            if (resourceId == -1) {
                                fVar.f5053d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            fVar.f5053d = typedArray.getString(index);
                        } else {
                            fVar.f5052c = typedArray.getResourceId(index, fVar.f5052c);
                        }
                        break;
                    case 2:
                        fVar.f5051b = typedArray.getInt(index, fVar.f5051b);
                        break;
                    case 3:
                        fVar.f5075g = typedArray.getString(index);
                        break;
                    case 4:
                        fVar.f5076h = typedArray.getInteger(index, fVar.f5076h);
                        break;
                    case 5:
                        fVar.f5077i = typedArray.getInt(index, fVar.f5077i);
                        break;
                    case 6:
                        fVar.f5078j = typedArray.getFloat(index, fVar.f5078j);
                        break;
                    case 7:
                        if (typedArray.peekValue(index).type == 5) {
                            fVar.f5079k = typedArray.getDimension(index, fVar.f5079k);
                        } else {
                            fVar.f5079k = typedArray.getFloat(index, fVar.f5079k);
                        }
                        break;
                    case 8:
                        fVar.f5081m = typedArray.getInt(index, fVar.f5081m);
                        break;
                    case 9:
                        fVar.f5082n = typedArray.getFloat(index, fVar.f5082n);
                        break;
                    case 10:
                        fVar.f5083o = typedArray.getDimension(index, fVar.f5083o);
                        break;
                    case 11:
                        fVar.f5084p = typedArray.getFloat(index, fVar.f5084p);
                        break;
                    case 12:
                        fVar.f5086r = typedArray.getFloat(index, fVar.f5086r);
                        break;
                    case 13:
                        fVar.f5087s = typedArray.getFloat(index, fVar.f5087s);
                        break;
                    case 14:
                        fVar.f5085q = typedArray.getFloat(index, fVar.f5085q);
                        break;
                    case 15:
                        fVar.f5088t = typedArray.getFloat(index, fVar.f5088t);
                        break;
                    case 16:
                        fVar.f5089u = typedArray.getFloat(index, fVar.f5089u);
                        break;
                    case 17:
                        fVar.f5090v = typedArray.getDimension(index, fVar.f5090v);
                        break;
                    case 18:
                        fVar.f5091w = typedArray.getDimension(index, fVar.f5091w);
                        break;
                    case 19:
                        if (Build.VERSION.SDK_INT >= 21) {
                            fVar.f5092x = typedArray.getDimension(index, fVar.f5092x);
                        }
                        break;
                    case 20:
                        fVar.f5080l = typedArray.getFloat(index, fVar.f5080l);
                        break;
                    default:
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f5093a.get(index));
                        break;
                }
            }
        }
    }

    public f() {
        this.f5054e = 4;
        this.f5055f = new HashMap<>();
    }

    public void O(HashMap<String, g> map) {
        for (String str : map.keySet()) {
            if (str.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute = this.f5055f.get(str.substring(7));
                if (constraintAttribute != null && constraintAttribute.c() == ConstraintAttribute.AttributeType.FLOAT_TYPE) {
                    map.get(str).e(this.f5051b, this.f5077i, this.f5081m, this.f5078j, this.f5079k, constraintAttribute.d(), constraintAttribute);
                }
            }
            float fP = P(str);
            if (!Float.isNaN(fP)) {
                map.get(str).d(this.f5051b, this.f5077i, this.f5081m, this.f5078j, this.f5079k, fP);
            }
        }
    }

    public float P(String str) {
        str.hashCode();
        switch (str) {
            case "rotationX":
                return this.f5086r;
            case "rotationY":
                return this.f5087s;
            case "translationX":
                return this.f5090v;
            case "translationY":
                return this.f5091w;
            case "translationZ":
                return this.f5092x;
            case "progress":
                return this.f5080l;
            case "scaleX":
                return this.f5088t;
            case "scaleY":
                return this.f5089u;
            case "rotation":
                return this.f5084p;
            case "elevation":
                return this.f5083o;
            case "transitionPathRotate":
                return this.f5085q;
            case "alpha":
                return this.f5082n;
            case "waveOffset":
                return this.f5079k;
            default:
                Log.v("WARNING! KeyCycle", "  UNKNOWN  " + str);
                return Float.NaN;
        }
    }

    @Override // u.c
    public void a(HashMap<String, r> map) {
        r rVar;
        u.a.e("KeyCycle", "add " + map.size() + " values", 2);
        for (String str : map.keySet()) {
            rVar = map.get(str);
            str.hashCode();
            switch (str) {
                case "rotationX":
                    rVar.e(this.f5051b, this.f5086r);
                    break;
                case "rotationY":
                    rVar.e(this.f5051b, this.f5087s);
                    break;
                case "translationX":
                    rVar.e(this.f5051b, this.f5090v);
                    break;
                case "translationY":
                    rVar.e(this.f5051b, this.f5091w);
                    break;
                case "translationZ":
                    rVar.e(this.f5051b, this.f5092x);
                    break;
                case "progress":
                    rVar.e(this.f5051b, this.f5080l);
                    break;
                case "scaleX":
                    rVar.e(this.f5051b, this.f5088t);
                    break;
                case "scaleY":
                    rVar.e(this.f5051b, this.f5089u);
                    break;
                case "rotation":
                    rVar.e(this.f5051b, this.f5084p);
                    break;
                case "elevation":
                    rVar.e(this.f5051b, this.f5083o);
                    break;
                case "transitionPathRotate":
                    rVar.e(this.f5051b, this.f5085q);
                    break;
                case "alpha":
                    rVar.e(this.f5051b, this.f5082n);
                    break;
                case "waveOffset":
                    rVar.e(this.f5051b, this.f5079k);
                    break;
                default:
                    Log.v("WARNING KeyCycle", "  UNKNOWN  " + str);
                    break;
            }
        }
    }

    @Override // u.c
    public void b(HashSet<String> hashSet) {
        if (!Float.isNaN(this.f5082n)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f5083o)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f5084p)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.f5086r)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.f5087s)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.f5088t)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.f5089u)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.f5085q)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.f5090v)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.f5091w)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.f5092x)) {
            hashSet.add("translationZ");
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
        a.b(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyCycle));
    }
}
