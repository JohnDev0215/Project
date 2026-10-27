package b.e.a.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: KeyTrigger.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1288g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f1289h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1290i;
    public String j;
    public String k;
    public int l;
    public int m;
    public View n;
    public float o;
    public boolean p;
    public boolean q;
    public boolean r;
    public float s;
    public Method t;
    public Method u;
    public Method v;
    public float w;
    public boolean x;
    public RectF y;
    public RectF z;

    /* JADX INFO: compiled from: KeyTrigger.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f1291a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1291a = sparseIntArray;
            sparseIntArray.append(b.e.c.e.W4, 8);
            f1291a.append(b.e.c.e.a5, 4);
            f1291a.append(b.e.c.e.b5, 1);
            f1291a.append(b.e.c.e.c5, 2);
            f1291a.append(b.e.c.e.X4, 7);
            f1291a.append(b.e.c.e.d5, 6);
            f1291a.append(b.e.c.e.f5, 5);
            f1291a.append(b.e.c.e.Z4, 9);
            f1291a.append(b.e.c.e.Y4, 10);
            f1291a.append(b.e.c.e.e5, 11);
        }

        public static void a(l lVar, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f1291a.get(index)) {
                    case 1:
                        lVar.j = typedArray.getString(index);
                        continue;
                        break;
                    case 2:
                        lVar.k = typedArray.getString(index);
                        continue;
                        break;
                    case 4:
                        lVar.f1289h = typedArray.getString(index);
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
                        if (MotionLayout.W3) {
                            int resourceId = typedArray.getResourceId(index, lVar.f1242c);
                            lVar.f1242c = resourceId;
                            if (resourceId == -1) {
                                lVar.f1243d = typedArray.getString(index);
                            } else {
                                continue;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            lVar.f1243d = typedArray.getString(index);
                        } else {
                            lVar.f1242c = typedArray.getResourceId(index, lVar.f1242c);
                        }
                        break;
                    case 8:
                        int integer = typedArray.getInteger(index, lVar.f1241b);
                        lVar.f1241b = integer;
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
                        lVar.f1290i = typedArray.getResourceId(index, lVar.f1290i);
                        break;
                }
                Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1291a.get(index));
            }
        }
    }

    public l() {
        int i2 = c.f1240a;
        this.f1290i = i2;
        this.j = null;
        this.k = null;
        this.l = i2;
        this.m = i2;
        this.n = null;
        this.o = 0.1f;
        this.p = true;
        this.q = true;
        this.r = true;
        this.s = Float.NaN;
        this.x = false;
        this.y = new RectF();
        this.z = new RectF();
        this.f1244e = 5;
        this.f1245f = new HashMap<>();
    }

    @Override // b.e.a.b.c
    public void a(HashMap<String, r> map) {
    }

    @Override // b.e.a.b.c
    public void b(HashSet<String> hashSet) {
    }

    @Override // b.e.a.b.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, b.e.c.e.V4), context);
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
        throw new UnsupportedOperationException("Method not decompiled: b.e.a.b.l.r(float, android.view.View):void");
    }

    public final void s(RectF rectF, View view, boolean z) {
        rectF.top = view.getTop();
        rectF.bottom = view.getBottom();
        rectF.left = view.getLeft();
        rectF.right = view.getRight();
        if (z) {
            view.getMatrix().mapRect(rectF);
        }
    }
}
