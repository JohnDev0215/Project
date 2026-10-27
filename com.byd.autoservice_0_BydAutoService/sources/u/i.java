package u;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;
import java.util.HashMap;

/* JADX INFO: compiled from: KeyPosition.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends j {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f5123h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5124i = c.f5050a;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f5125j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f5126k = Float.NaN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f5127l = Float.NaN;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f5128m = Float.NaN;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f5129n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f5130o = Float.NaN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f5131p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f5132q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f5133r = Float.NaN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f5134s = Float.NaN;

    /* JADX INFO: compiled from: KeyPosition.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f5135a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f5135a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyPosition_motionTarget, 1);
            f5135a.append(R$styleable.KeyPosition_framePosition, 2);
            f5135a.append(R$styleable.KeyPosition_transitionEasing, 3);
            f5135a.append(R$styleable.KeyPosition_curveFit, 4);
            f5135a.append(R$styleable.KeyPosition_drawPath, 5);
            f5135a.append(R$styleable.KeyPosition_percentX, 6);
            f5135a.append(R$styleable.KeyPosition_percentY, 7);
            f5135a.append(R$styleable.KeyPosition_keyPositionType, 9);
            f5135a.append(R$styleable.KeyPosition_sizePercent, 8);
            f5135a.append(R$styleable.KeyPosition_percentWidth, 11);
            f5135a.append(R$styleable.KeyPosition_percentHeight, 12);
            f5135a.append(R$styleable.KeyPosition_pathMotionArc, 10);
        }

        public static void b(i iVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArray.getIndex(i3);
                switch (f5135a.get(index)) {
                    case 1:
                        if (MotionLayout.f736y) {
                            int resourceId = typedArray.getResourceId(index, iVar.f5052c);
                            iVar.f5052c = resourceId;
                            if (resourceId == -1) {
                                iVar.f5053d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            iVar.f5053d = typedArray.getString(index);
                        } else {
                            iVar.f5052c = typedArray.getResourceId(index, iVar.f5052c);
                        }
                        break;
                    case 2:
                        iVar.f5051b = typedArray.getInt(index, iVar.f5051b);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            iVar.f5123h = typedArray.getString(index);
                        } else {
                            iVar.f5123h = t.c.f4950b[typedArray.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        iVar.f5136g = typedArray.getInteger(index, iVar.f5136g);
                        break;
                    case 5:
                        iVar.f5125j = typedArray.getInt(index, iVar.f5125j);
                        break;
                    case 6:
                        iVar.f5128m = typedArray.getFloat(index, iVar.f5128m);
                        break;
                    case 7:
                        iVar.f5129n = typedArray.getFloat(index, iVar.f5129n);
                        break;
                    case 8:
                        float f3 = typedArray.getFloat(index, iVar.f5127l);
                        iVar.f5126k = f3;
                        iVar.f5127l = f3;
                        break;
                    case 9:
                        iVar.f5132q = typedArray.getInt(index, iVar.f5132q);
                        break;
                    case 10:
                        iVar.f5124i = typedArray.getInt(index, iVar.f5124i);
                        break;
                    case 11:
                        iVar.f5126k = typedArray.getFloat(index, iVar.f5126k);
                        break;
                    case 12:
                        iVar.f5127l = typedArray.getFloat(index, iVar.f5127l);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + f5135a.get(index));
                        break;
                }
            }
            if (iVar.f5051b == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public i() {
        this.f5054e = 2;
    }

    @Override // u.c
    public void a(HashMap<String, r> map) {
    }

    @Override // u.c
    public void c(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyPosition));
    }
}
