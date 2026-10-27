package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import java.util.HashMap;

/* JADX INFO: compiled from: KeyPosition.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends i {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    String f1396h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int f1397i = d.f1349f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    int f1398j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    float f1399k = Float.NaN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    float f1400l = Float.NaN;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    float f1401m = Float.NaN;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    float f1402n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    float f1403o = Float.NaN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    float f1404p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    int f1405q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private float f1406r = Float.NaN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private float f1407s = Float.NaN;

    /* JADX INFO: compiled from: KeyPosition.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1408a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1408a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.i.KeyPosition_motionTarget, 1);
            f1408a.append(androidx.constraintlayout.widget.i.KeyPosition_framePosition, 2);
            f1408a.append(androidx.constraintlayout.widget.i.KeyPosition_transitionEasing, 3);
            f1408a.append(androidx.constraintlayout.widget.i.KeyPosition_curveFit, 4);
            f1408a.append(androidx.constraintlayout.widget.i.KeyPosition_drawPath, 5);
            f1408a.append(androidx.constraintlayout.widget.i.KeyPosition_percentX, 6);
            f1408a.append(androidx.constraintlayout.widget.i.KeyPosition_percentY, 7);
            f1408a.append(androidx.constraintlayout.widget.i.KeyPosition_keyPositionType, 9);
            f1408a.append(androidx.constraintlayout.widget.i.KeyPosition_sizePercent, 8);
            f1408a.append(androidx.constraintlayout.widget.i.KeyPosition_percentWidth, 11);
            f1408a.append(androidx.constraintlayout.widget.i.KeyPosition_percentHeight, 12);
            f1408a.append(androidx.constraintlayout.widget.i.KeyPosition_pathMotionArc, 10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(h hVar, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArray.getIndex(i7);
                switch (f1408a.get(index)) {
                    case 1:
                        if (MotionLayout.f1260h1) {
                            int resourceId = typedArray.getResourceId(index, hVar.f1351b);
                            hVar.f1351b = resourceId;
                            if (resourceId == -1) {
                                hVar.f1352c = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            hVar.f1352c = typedArray.getString(index);
                        } else {
                            hVar.f1351b = typedArray.getResourceId(index, hVar.f1351b);
                        }
                        break;
                    case 2:
                        hVar.f1350a = typedArray.getInt(index, hVar.f1350a);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            hVar.f1396h = typedArray.getString(index);
                        } else {
                            hVar.f1396h = p.c.f8706c[typedArray.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        hVar.f1409g = typedArray.getInteger(index, hVar.f1409g);
                        break;
                    case 5:
                        hVar.f1398j = typedArray.getInt(index, hVar.f1398j);
                        break;
                    case 6:
                        hVar.f1401m = typedArray.getFloat(index, hVar.f1401m);
                        break;
                    case 7:
                        hVar.f1402n = typedArray.getFloat(index, hVar.f1402n);
                        break;
                    case 8:
                        float f7 = typedArray.getFloat(index, hVar.f1400l);
                        hVar.f1399k = f7;
                        hVar.f1400l = f7;
                        break;
                    case 9:
                        hVar.f1405q = typedArray.getInt(index, hVar.f1405q);
                        break;
                    case 10:
                        hVar.f1397i = typedArray.getInt(index, hVar.f1397i);
                        break;
                    case 11:
                        hVar.f1399k = typedArray.getFloat(index, hVar.f1399k);
                        break;
                    case 12:
                        hVar.f1400l = typedArray.getFloat(index, hVar.f1400l);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1408a.get(index));
                        break;
                }
            }
            if (hVar.f1350a == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public h() {
        this.f1353d = 2;
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void a(HashMap<String, t.d> map) {
    }

    @Override // androidx.constraintlayout.motion.widget.d
    /* JADX INFO: renamed from: b */
    public d clone() {
        return new h().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public d c(d dVar) {
        super.c(dVar);
        h hVar = (h) dVar;
        this.f1396h = hVar.f1396h;
        this.f1397i = hVar.f1397i;
        this.f1398j = hVar.f1398j;
        this.f1399k = hVar.f1399k;
        this.f1400l = Float.NaN;
        this.f1401m = hVar.f1401m;
        this.f1402n = hVar.f1402n;
        this.f1403o = hVar.f1403o;
        this.f1404p = hVar.f1404p;
        this.f1406r = hVar.f1406r;
        this.f1407s = hVar.f1407s;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void e(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.i.KeyPosition));
    }
}
