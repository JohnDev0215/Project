package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import java.util.HashMap;

/* JADX INFO: compiled from: KeyPosition.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends j {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    String f872h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int f873i = c.f832a;
    int j = 0;
    float k = Float.NaN;
    float l = Float.NaN;
    float m = Float.NaN;
    float n = Float.NaN;
    float o = Float.NaN;
    float p = Float.NaN;
    int q = 0;
    private float r = Float.NaN;
    private float s = Float.NaN;

    /* JADX INFO: compiled from: KeyPosition.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f874a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f874a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.f.KeyPosition_motionTarget, 1);
            f874a.append(androidx.constraintlayout.widget.f.KeyPosition_framePosition, 2);
            f874a.append(androidx.constraintlayout.widget.f.KeyPosition_transitionEasing, 3);
            f874a.append(androidx.constraintlayout.widget.f.KeyPosition_curveFit, 4);
            f874a.append(androidx.constraintlayout.widget.f.KeyPosition_drawPath, 5);
            f874a.append(androidx.constraintlayout.widget.f.KeyPosition_percentX, 6);
            f874a.append(androidx.constraintlayout.widget.f.KeyPosition_percentY, 7);
            f874a.append(androidx.constraintlayout.widget.f.KeyPosition_keyPositionType, 9);
            f874a.append(androidx.constraintlayout.widget.f.KeyPosition_sizePercent, 8);
            f874a.append(androidx.constraintlayout.widget.f.KeyPosition_percentWidth, 11);
            f874a.append(androidx.constraintlayout.widget.f.KeyPosition_percentHeight, 12);
            f874a.append(androidx.constraintlayout.widget.f.KeyPosition_pathMotionArc, 10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(i iVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f874a.get(index)) {
                    case 1:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, iVar.f834c);
                            iVar.f834c = resourceId;
                            if (resourceId == -1) {
                                iVar.f835d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            iVar.f835d = typedArray.getString(index);
                        } else {
                            iVar.f834c = typedArray.getResourceId(index, iVar.f834c);
                        }
                        break;
                    case 2:
                        iVar.f833b = typedArray.getInt(index, iVar.f833b);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            iVar.f872h = typedArray.getString(index);
                        } else {
                            iVar.f872h = b.e.a.a.c.f2316b[typedArray.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        iVar.f875g = typedArray.getInteger(index, iVar.f875g);
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
                        iVar.f873i = typedArray.getInt(index, iVar.f873i);
                        break;
                    case 11:
                        iVar.k = typedArray.getFloat(index, iVar.k);
                        break;
                    case 12:
                        iVar.l = typedArray.getFloat(index, iVar.l);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + f874a.get(index));
                        break;
                }
            }
            if (iVar.f833b == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public i() {
        this.f836e = 2;
    }

    @Override // androidx.constraintlayout.motion.widget.c
    public void a(HashMap<String, r> map) {
    }

    @Override // androidx.constraintlayout.motion.widget.c
    public void c(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.f.KeyPosition));
    }
}
