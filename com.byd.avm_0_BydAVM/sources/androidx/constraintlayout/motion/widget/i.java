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
    String h = null;
    int i = c.f557a;
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
        private static SparseIntArray f579a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f579a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.t4, 1);
            f579a.append(androidx.constraintlayout.widget.e.r4, 2);
            f579a.append(androidx.constraintlayout.widget.e.A4, 3);
            f579a.append(androidx.constraintlayout.widget.e.p4, 4);
            f579a.append(androidx.constraintlayout.widget.e.q4, 5);
            f579a.append(androidx.constraintlayout.widget.e.x4, 6);
            f579a.append(androidx.constraintlayout.widget.e.y4, 7);
            f579a.append(androidx.constraintlayout.widget.e.s4, 9);
            f579a.append(androidx.constraintlayout.widget.e.z4, 8);
            f579a.append(androidx.constraintlayout.widget.e.w4, 11);
            f579a.append(androidx.constraintlayout.widget.e.v4, 12);
            f579a.append(androidx.constraintlayout.widget.e.u4, 10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(i iVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f579a.get(index)) {
                    case 1:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, iVar.f559c);
                            iVar.f559c = resourceId;
                            if (resourceId == -1) {
                                iVar.f560d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            iVar.f560d = typedArray.getString(index);
                        } else {
                            iVar.f559c = typedArray.getResourceId(index, iVar.f559c);
                        }
                        break;
                    case 2:
                        iVar.f558b = typedArray.getInt(index, iVar.f558b);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            iVar.h = typedArray.getString(index);
                        } else {
                            iVar.h = b.e.a.a.c.f1569b[typedArray.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        iVar.g = typedArray.getInteger(index, iVar.g);
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
                        float f = typedArray.getFloat(index, iVar.l);
                        iVar.k = f;
                        iVar.l = f;
                        break;
                    case 9:
                        iVar.q = typedArray.getInt(index, iVar.q);
                        break;
                    case 10:
                        iVar.i = typedArray.getInt(index, iVar.i);
                        break;
                    case 11:
                        iVar.k = typedArray.getFloat(index, iVar.k);
                        break;
                    case 12:
                        iVar.l = typedArray.getFloat(index, iVar.l);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + f579a.get(index));
                        break;
                }
            }
            if (iVar.f558b == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public i() {
        this.e = 2;
    }

    @Override // androidx.constraintlayout.motion.widget.c
    public void a(HashMap<String, r> map) {
    }

    @Override // androidx.constraintlayout.motion.widget.c
    public void c(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.o4));
    }
}
