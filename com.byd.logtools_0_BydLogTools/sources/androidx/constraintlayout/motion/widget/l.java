package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: KeyTrigger.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f832g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f833h = null;
    private int i;
    private String j;
    private String k;
    private int l;
    private int m;
    private View n;
    float o;
    private boolean p;
    private boolean q;
    private boolean r;
    private float s;
    private Method t;
    private Method u;
    private Method v;
    private float w;
    private boolean x;
    RectF y;
    RectF z;

    /* JADX INFO: compiled from: KeyTrigger.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f834a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f834a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.W4, 8);
            f834a.append(androidx.constraintlayout.widget.e.a5, 4);
            f834a.append(androidx.constraintlayout.widget.e.b5, 1);
            f834a.append(androidx.constraintlayout.widget.e.c5, 2);
            f834a.append(androidx.constraintlayout.widget.e.X4, 7);
            f834a.append(androidx.constraintlayout.widget.e.d5, 6);
            f834a.append(androidx.constraintlayout.widget.e.f5, 5);
            f834a.append(androidx.constraintlayout.widget.e.Z4, 9);
            f834a.append(androidx.constraintlayout.widget.e.Y4, 10);
            f834a.append(androidx.constraintlayout.widget.e.e5, 11);
        }

        public static void a(l lVar, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f834a.get(index)) {
                    case 1:
                        lVar.j = typedArray.getString(index);
                        continue;
                        break;
                    case 2:
                        lVar.k = typedArray.getString(index);
                        continue;
                        break;
                    case 4:
                        lVar.f833h = typedArray.getString(index);
                        continue;
                        break;
                    case 5:
                        lVar.o = typedArray.getFloat(index, lVar.o);
                        continue;
                        break;
                    case 6:
                        lVar.l = typedArray.getResourceId(index, lVar.l);
                        continue;
                        break;
                    case 7:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, lVar.f791c);
                            lVar.f791c = resourceId;
                            if (resourceId == -1) {
                                lVar.f792d = typedArray.getString(index);
                            } else {
                                continue;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            lVar.f792d = typedArray.getString(index);
                        } else {
                            lVar.f791c = typedArray.getResourceId(index, lVar.f791c);
                        }
                        break;
                    case 8:
                        int integer = typedArray.getInteger(index, lVar.f790b);
                        lVar.f790b = integer;
                        lVar.s = (integer + 0.5f) / 100.0f;
                        continue;
                        break;
                    case 9:
                        lVar.m = typedArray.getResourceId(index, lVar.m);
                        continue;
                        break;
                    case 10:
                        lVar.x = typedArray.getBoolean(index, lVar.x);
                        continue;
                        break;
                    case 11:
                        lVar.i = typedArray.getResourceId(index, lVar.i);
                        break;
                }
                Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + f834a.get(index));
            }
        }
    }

    public l() {
        int i = c.f789a;
        this.i = i;
        this.j = null;
        this.k = null;
        this.l = i;
        this.m = i;
        this.n = null;
        this.o = 0.1f;
        this.p = true;
        this.q = true;
        this.r = true;
        this.s = Float.NaN;
        this.x = false;
        this.y = new RectF();
        this.z = new RectF();
        this.f793e = 5;
        this.f794f = new HashMap<>();
    }

    private void s(RectF rectF, View view, boolean z) {
        rectF.top = view.getTop();
        rectF.bottom = view.getBottom();
        rectF.left = view.getLeft();
        rectF.right = view.getRight();
        if (z) {
            view.getMatrix().mapRect(rectF);
        }
    }

    @Override // androidx.constraintlayout.motion.widget.c
    public void a(HashMap<String, r> map) {
    }

    @Override // androidx.constraintlayout.motion.widget.c
    public void b(HashSet<String> hashSet) {
    }

    @Override // androidx.constraintlayout.motion.widget.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.V4), context);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void r(float r11, android.view.View r12) {
        /*
            Method dump skipped, instruction units count: 631
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.l.r(float, android.view.View):void");
    }
}
