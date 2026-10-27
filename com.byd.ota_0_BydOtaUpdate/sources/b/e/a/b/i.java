package b.e.a.b;

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
    public String f1280h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1281i = c.f1240a;
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
        public static SparseIntArray f1282a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1282a = sparseIntArray;
            sparseIntArray.append(b.e.c.e.t4, 1);
            f1282a.append(b.e.c.e.r4, 2);
            f1282a.append(b.e.c.e.A4, 3);
            f1282a.append(b.e.c.e.p4, 4);
            f1282a.append(b.e.c.e.q4, 5);
            f1282a.append(b.e.c.e.x4, 6);
            f1282a.append(b.e.c.e.y4, 7);
            f1282a.append(b.e.c.e.s4, 9);
            f1282a.append(b.e.c.e.z4, 8);
            f1282a.append(b.e.c.e.w4, 11);
            f1282a.append(b.e.c.e.v4, 12);
            f1282a.append(b.e.c.e.u4, 10);
        }

        public static void b(i iVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f1282a.get(index)) {
                    case 1:
                        if (MotionLayout.W3) {
                            int resourceId = typedArray.getResourceId(index, iVar.f1242c);
                            iVar.f1242c = resourceId;
                            if (resourceId == -1) {
                                iVar.f1243d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            iVar.f1243d = typedArray.getString(index);
                        } else {
                            iVar.f1242c = typedArray.getResourceId(index, iVar.f1242c);
                        }
                        break;
                    case 2:
                        iVar.f1241b = typedArray.getInt(index, iVar.f1241b);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            iVar.f1280h = typedArray.getString(index);
                        } else {
                            iVar.f1280h = b.e.a.a.c.f1198b[typedArray.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        iVar.f1283g = typedArray.getInteger(index, iVar.f1283g);
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
                        iVar.f1281i = typedArray.getInt(index, iVar.f1281i);
                        break;
                    case 11:
                        iVar.k = typedArray.getFloat(index, iVar.k);
                        break;
                    case 12:
                        iVar.l = typedArray.getFloat(index, iVar.l);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1282a.get(index));
                        break;
                }
            }
            if (iVar.f1241b == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public i() {
        this.f1244e = 2;
    }

    @Override // b.e.a.b.c
    public void a(HashMap<String, r> map) {
    }

    @Override // b.e.a.b.c
    public void c(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, b.e.c.e.o4));
    }
}
