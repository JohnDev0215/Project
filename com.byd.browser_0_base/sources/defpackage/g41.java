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
public final class g41 extends h41 {
    public String f = null;
    public int g = -1;
    public int h = 0;
    public float i = Float.NaN;
    public float j = Float.NaN;
    public float k = Float.NaN;
    public float l = Float.NaN;
    public float m = Float.NaN;
    public float n = Float.NaN;
    public int o = 0;

    /* JADX INFO: compiled from: KeyPosition.java */
    public static class a {
        public static final SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyPosition_motionTarget, 1);
            sparseIntArray.append(R$styleable.KeyPosition_framePosition, 2);
            sparseIntArray.append(R$styleable.KeyPosition_transitionEasing, 3);
            sparseIntArray.append(R$styleable.KeyPosition_curveFit, 4);
            sparseIntArray.append(R$styleable.KeyPosition_drawPath, 5);
            sparseIntArray.append(R$styleable.KeyPosition_percentX, 6);
            sparseIntArray.append(R$styleable.KeyPosition_percentY, 7);
            sparseIntArray.append(R$styleable.KeyPosition_keyPositionType, 9);
            sparseIntArray.append(R$styleable.KeyPosition_sizePercent, 8);
            sparseIntArray.append(R$styleable.KeyPosition_percentWidth, 11);
            sparseIntArray.append(R$styleable.KeyPosition_percentHeight, 12);
            sparseIntArray.append(R$styleable.KeyPosition_pathMotionArc, 10);
        }
    }

    @Override // defpackage.y31
    /* JADX INFO: renamed from: b */
    public final y31 clone() {
        g41 g41Var = new g41();
        super.c(this);
        g41Var.f = this.f;
        g41Var.g = this.g;
        g41Var.h = this.h;
        g41Var.i = this.i;
        g41Var.j = Float.NaN;
        g41Var.k = this.k;
        g41Var.l = this.l;
        g41Var.m = this.m;
        g41Var.n = this.n;
        return g41Var;
    }

    @Override // defpackage.y31
    public final void e(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.KeyPosition);
        SparseIntArray sparseIntArray = a.a;
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            SparseIntArray sparseIntArray2 = a.a;
            switch (sparseIntArray2.get(index)) {
                case 1:
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
                case 2:
                    this.a = typedArrayObtainStyledAttributes.getInt(index, this.a);
                    break;
                case 3:
                    if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                        this.f = typedArrayObtainStyledAttributes.getString(index);
                    } else {
                        this.f = hf0.c[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                    }
                    break;
                case 4:
                    this.e = typedArrayObtainStyledAttributes.getInteger(index, this.e);
                    break;
                case 5:
                    this.h = typedArrayObtainStyledAttributes.getInt(index, this.h);
                    break;
                case 6:
                    this.k = typedArrayObtainStyledAttributes.getFloat(index, this.k);
                    break;
                case 7:
                    this.l = typedArrayObtainStyledAttributes.getFloat(index, this.l);
                    break;
                case 8:
                    float f = typedArrayObtainStyledAttributes.getFloat(index, this.j);
                    this.i = f;
                    this.j = f;
                    break;
                case 9:
                    this.o = typedArrayObtainStyledAttributes.getInt(index, this.o);
                    break;
                case 10:
                    this.g = typedArrayObtainStyledAttributes.getInt(index, this.g);
                    break;
                case 11:
                    this.i = typedArrayObtainStyledAttributes.getFloat(index, this.i);
                    break;
                case 12:
                    this.j = typedArrayObtainStyledAttributes.getFloat(index, this.j);
                    break;
                default:
                    Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray2.get(index));
                    break;
            }
        }
        if (this.a == -1) {
            Log.e("KeyPosition", "no frame position");
        }
    }

    public final void h(Object obj, String str) {
        switch (str) {
            case "transitionEasing":
                this.f = obj.toString();
                break;
            case "percentWidth":
                this.i = y31.g((Number) obj);
                break;
            case "percentHeight":
                this.j = y31.g((Number) obj);
                break;
            case "drawPath":
                Number number = (Number) obj;
                this.h = number instanceof Integer ? ((Integer) number).intValue() : Integer.parseInt(number.toString());
                break;
            case "sizePercent":
                float fG = y31.g((Number) obj);
                this.i = fG;
                this.j = fG;
                break;
            case "percentX":
                this.k = y31.g((Number) obj);
                break;
            case "percentY":
                this.l = y31.g((Number) obj);
                break;
        }
    }

    @Override // defpackage.y31
    public final void a(HashMap<String, rn3> map) {
    }
}
