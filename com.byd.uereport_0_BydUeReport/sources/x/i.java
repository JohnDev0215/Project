package x;

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
    public String f8837h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f8838i = c.f8764a;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f8839j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f8840k = Float.NaN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f8841l = Float.NaN;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f8842m = Float.NaN;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f8843n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f8844o = Float.NaN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f8845p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f8846q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f8847r = Float.NaN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f8848s = Float.NaN;

    /* JADX INFO: compiled from: KeyPosition.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f8849a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f8849a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyPosition_motionTarget, 1);
            f8849a.append(R$styleable.KeyPosition_framePosition, 2);
            f8849a.append(R$styleable.KeyPosition_transitionEasing, 3);
            f8849a.append(R$styleable.KeyPosition_curveFit, 4);
            f8849a.append(R$styleable.KeyPosition_drawPath, 5);
            f8849a.append(R$styleable.KeyPosition_percentX, 6);
            f8849a.append(R$styleable.KeyPosition_percentY, 7);
            f8849a.append(R$styleable.KeyPosition_keyPositionType, 9);
            f8849a.append(R$styleable.KeyPosition_sizePercent, 8);
            f8849a.append(R$styleable.KeyPosition_percentWidth, 11);
            f8849a.append(R$styleable.KeyPosition_percentHeight, 12);
            f8849a.append(R$styleable.KeyPosition_pathMotionArc, 10);
        }

        public static void b(i iVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArray.getIndex(i8);
                switch (f8849a.get(index)) {
                    case 1:
                        if (MotionLayout.A) {
                            int resourceId = typedArray.getResourceId(index, iVar.f8766c);
                            iVar.f8766c = resourceId;
                            if (resourceId == -1) {
                                iVar.f8767d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            iVar.f8767d = typedArray.getString(index);
                        } else {
                            iVar.f8766c = typedArray.getResourceId(index, iVar.f8766c);
                        }
                        break;
                    case 2:
                        iVar.f8765b = typedArray.getInt(index, iVar.f8765b);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            iVar.f8837h = typedArray.getString(index);
                        } else {
                            iVar.f8837h = w.c.f8625b[typedArray.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        iVar.f8850g = typedArray.getInteger(index, iVar.f8850g);
                        break;
                    case 5:
                        iVar.f8839j = typedArray.getInt(index, iVar.f8839j);
                        break;
                    case 6:
                        iVar.f8842m = typedArray.getFloat(index, iVar.f8842m);
                        break;
                    case 7:
                        iVar.f8843n = typedArray.getFloat(index, iVar.f8843n);
                        break;
                    case 8:
                        float f8 = typedArray.getFloat(index, iVar.f8841l);
                        iVar.f8840k = f8;
                        iVar.f8841l = f8;
                        break;
                    case 9:
                        iVar.f8846q = typedArray.getInt(index, iVar.f8846q);
                        break;
                    case 10:
                        iVar.f8838i = typedArray.getInt(index, iVar.f8838i);
                        break;
                    case 11:
                        iVar.f8840k = typedArray.getFloat(index, iVar.f8840k);
                        break;
                    case 12:
                        iVar.f8841l = typedArray.getFloat(index, iVar.f8841l);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + f8849a.get(index));
                        break;
                }
            }
            if (iVar.f8765b == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public i() {
        this.f8768e = 2;
    }

    @Override // x.c
    public void a(HashMap<String, r> map) {
    }

    @Override // x.c
    public void c(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyPosition));
    }
}
