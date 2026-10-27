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
    int i = e.f925a;
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
        private static SparseIntArray f934a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f934a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.U6, 1);
            f934a.append(androidx.constraintlayout.widget.e.S6, 2);
            f934a.append(androidx.constraintlayout.widget.e.b7, 3);
            f934a.append(androidx.constraintlayout.widget.e.Q6, 4);
            f934a.append(androidx.constraintlayout.widget.e.R6, 5);
            f934a.append(androidx.constraintlayout.widget.e.Y6, 6);
            f934a.append(androidx.constraintlayout.widget.e.Z6, 7);
            f934a.append(androidx.constraintlayout.widget.e.T6, 9);
            f934a.append(androidx.constraintlayout.widget.e.a7, 8);
            f934a.append(androidx.constraintlayout.widget.e.X6, 11);
            f934a.append(androidx.constraintlayout.widget.e.W6, 12);
            f934a.append(androidx.constraintlayout.widget.e.V6, 10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(i iVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f934a.get(index)) {
                    case 1:
                        if (MotionLayout.z) {
                            int resourceId = typedArray.getResourceId(index, iVar.f927c);
                            iVar.f927c = resourceId;
                            if (resourceId == -1) {
                                iVar.f928d = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            iVar.f928d = typedArray.getString(index);
                        } else {
                            iVar.f927c = typedArray.getResourceId(index, iVar.f927c);
                        }
                        break;
                    case 2:
                        iVar.f926b = typedArray.getInt(index, iVar.f926b);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            iVar.h = typedArray.getString(index);
                        } else {
                            iVar.h = androidx.constraintlayout.core.j.a.c.f746b[typedArray.getInteger(index, 0)];
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
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + f934a.get(index));
                        break;
                }
            }
            if (iVar.f926b == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public i() {
        this.f929e = 2;
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void a(HashMap<String, b.e.a.a.d> map) {
    }

    @Override // androidx.constraintlayout.motion.widget.e
    /* JADX INFO: renamed from: b */
    public e clone() {
        return new i().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public e c(e eVar) {
        super.c(eVar);
        i iVar = (i) eVar;
        this.h = iVar.h;
        this.i = iVar.i;
        this.j = iVar.j;
        this.k = iVar.k;
        this.l = Float.NaN;
        this.m = iVar.m;
        this.n = iVar.n;
        this.o = iVar.o;
        this.p = iVar.p;
        this.r = iVar.r;
        this.s = iVar.s;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.e
    public void e(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.P6));
    }

    public void m(int i) {
        this.q = i;
    }

    public void n(String str, Object obj) {
        str.hashCode();
        switch (str) {
            case "transitionEasing":
                this.h = obj.toString();
                break;
            case "percentWidth":
                this.k = k(obj);
                break;
            case "percentHeight":
                this.l = k(obj);
                break;
            case "drawPath":
                this.j = l(obj);
                break;
            case "sizePercent":
                float fK = k(obj);
                this.k = fK;
                this.l = fK;
                break;
            case "percentX":
                this.m = k(obj);
                break;
            case "percentY":
                this.n = k(obj);
                break;
        }
    }
}
