package b.f.a.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.util.HashMap;

/* JADX INFO: compiled from: KeyPosition.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends j {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f2078h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2079i = c.f2038a;
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

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f2080a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2080a = sparseIntArray;
            sparseIntArray.append(b.f.c.e.KeyPosition_motionTarget, 1);
            f2080a.append(b.f.c.e.KeyPosition_framePosition, 2);
            f2080a.append(b.f.c.e.KeyPosition_transitionEasing, 3);
            f2080a.append(b.f.c.e.KeyPosition_curveFit, 4);
            f2080a.append(b.f.c.e.KeyPosition_drawPath, 5);
            f2080a.append(b.f.c.e.KeyPosition_percentX, 6);
            f2080a.append(b.f.c.e.KeyPosition_percentY, 7);
            f2080a.append(b.f.c.e.KeyPosition_keyPositionType, 9);
            f2080a.append(b.f.c.e.KeyPosition_sizePercent, 8);
            f2080a.append(b.f.c.e.KeyPosition_percentWidth, 11);
            f2080a.append(b.f.c.e.KeyPosition_percentHeight, 12);
            f2080a.append(b.f.c.e.KeyPosition_pathMotionArc, 10);
        }

        public static void b(i iVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f2080a.get(index)) {
                    case 1:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, iVar.f2040c);
                            iVar.f2040c = resourceId;
                            if (resourceId == -1) {
                                iVar.f2041d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            iVar.f2041d = typedArray.getString(index);
                        } else {
                            iVar.f2040c = typedArray.getResourceId(index, iVar.f2040c);
                        }
                        break;
                    case 2:
                        iVar.f2039b = typedArray.getInt(index, iVar.f2039b);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            iVar.f2078h = typedArray.getString(index);
                        } else {
                            iVar.f2078h = b.f.a.a.c.f1996b[typedArray.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        iVar.f2081g = typedArray.getInteger(index, iVar.f2081g);
                        break;
                    case 5:
                        iVar.j = typedArray.getInt(index, iVar.j);
                        break;
                    case 6:
                        iVar.m = typedArray.getFloat(index, iVar.m);
                        break;
                    case 7:
                        iVar.n = typedArray.getFloat(index, iVar.n);
                        break;
                    case 8:
                        float f2 = typedArray.getFloat(index, iVar.l);
                        iVar.k = f2;
                        iVar.l = f2;
                        break;
                    case 9:
                        iVar.q = typedArray.getInt(index, iVar.q);
                        break;
                    case 10:
                        iVar.f2079i = typedArray.getInt(index, iVar.f2079i);
                        break;
                    case 11:
                        iVar.k = typedArray.getFloat(index, iVar.k);
                        break;
                    case 12:
                        iVar.l = typedArray.getFloat(index, iVar.l);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + f2080a.get(index));
                        break;
                }
            }
            if (iVar.f2039b == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public i() {
        this.f2042e = 2;
    }

    @Override // b.f.a.b.c
    public void a(HashMap<String, r> map) {
    }

    @Override // b.f.a.b.c
    public void c(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, b.f.c.e.KeyPosition));
    }
}
