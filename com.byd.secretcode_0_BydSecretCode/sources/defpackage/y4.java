package defpackage;

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
public class y4 extends z4 {
    public String h = null;
    public int i = s4.a;
    public int j = 0;
    public float k = Float.NaN;
    public float l = Float.NaN;
    public float m = Float.NaN;
    public float n = Float.NaN;
    public float o = Float.NaN;
    public float p = Float.NaN;
    public int q = 0;
    public float r = Float.NaN;
    public float s = Float.NaN;

    /* JADX INFO: compiled from: KeyPosition.java */
    public static class a {
        public static SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyPosition_motionTarget, 1);
            a.append(R$styleable.KeyPosition_framePosition, 2);
            a.append(R$styleable.KeyPosition_transitionEasing, 3);
            a.append(R$styleable.KeyPosition_curveFit, 4);
            a.append(R$styleable.KeyPosition_drawPath, 5);
            a.append(R$styleable.KeyPosition_percentX, 6);
            a.append(R$styleable.KeyPosition_percentY, 7);
            a.append(R$styleable.KeyPosition_keyPositionType, 9);
            a.append(R$styleable.KeyPosition_sizePercent, 8);
            a.append(R$styleable.KeyPosition_percentWidth, 11);
            a.append(R$styleable.KeyPosition_percentHeight, 12);
            a.append(R$styleable.KeyPosition_pathMotionArc, 10);
        }

        public static void b(y4 y4Var, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (a.get(index)) {
                    case 1:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, y4Var.c);
                            y4Var.c = resourceId;
                            if (resourceId == -1) {
                                y4Var.d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            y4Var.d = typedArray.getString(index);
                        } else {
                            y4Var.c = typedArray.getResourceId(index, y4Var.c);
                        }
                        break;
                    case 2:
                        y4Var.b = typedArray.getInt(index, y4Var.b);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            y4Var.h = typedArray.getString(index);
                        } else {
                            y4Var.h = k4.b[typedArray.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        y4Var.g = typedArray.getInteger(index, y4Var.g);
                        break;
                    case 5:
                        y4Var.j = typedArray.getInt(index, y4Var.j);
                        break;
                    case 6:
                        y4Var.m = typedArray.getFloat(index, y4Var.m);
                        break;
                    case 7:
                        y4Var.n = typedArray.getFloat(index, y4Var.n);
                        break;
                    case 8:
                        float f = typedArray.getFloat(index, y4Var.l);
                        y4Var.k = f;
                        y4Var.l = f;
                        break;
                    case 9:
                        y4Var.q = typedArray.getInt(index, y4Var.q);
                        break;
                    case 10:
                        y4Var.i = typedArray.getInt(index, y4Var.i);
                        break;
                    case 11:
                        y4Var.k = typedArray.getFloat(index, y4Var.k);
                        break;
                    case 12:
                        y4Var.l = typedArray.getFloat(index, y4Var.l);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + a.get(index));
                        break;
                }
            }
            if (y4Var.b == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public y4() {
        this.e = 2;
    }

    @Override // defpackage.s4
    public void a(HashMap<String, h5> map) {
    }

    @Override // defpackage.s4
    public void c(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyPosition));
    }
}
