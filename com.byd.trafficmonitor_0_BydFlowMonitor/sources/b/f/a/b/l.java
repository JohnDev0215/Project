package b.f.a.b;

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
    public int f2086g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f2087h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2088i;
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
        public static SparseIntArray f2089a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2089a = sparseIntArray;
            sparseIntArray.append(b.f.c.e.KeyTrigger_framePosition, 8);
            f2089a.append(b.f.c.e.KeyTrigger_onCross, 4);
            f2089a.append(b.f.c.e.KeyTrigger_onNegativeCross, 1);
            f2089a.append(b.f.c.e.KeyTrigger_onPositiveCross, 2);
            f2089a.append(b.f.c.e.KeyTrigger_motionTarget, 7);
            f2089a.append(b.f.c.e.KeyTrigger_triggerId, 6);
            f2089a.append(b.f.c.e.KeyTrigger_triggerSlack, 5);
            f2089a.append(b.f.c.e.KeyTrigger_motion_triggerOnCollision, 9);
            f2089a.append(b.f.c.e.KeyTrigger_motion_postLayoutCollision, 10);
            f2089a.append(b.f.c.e.KeyTrigger_triggerReceiver, 11);
        }

        public static void a(l lVar, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f2089a.get(index)) {
                    case 1:
                        lVar.j = typedArray.getString(index);
                        continue;
                        break;
                    case 2:
                        lVar.k = typedArray.getString(index);
                        continue;
                        break;
                    case 4:
                        lVar.f2087h = typedArray.getString(index);
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
                            int resourceId = typedArray.getResourceId(index, lVar.f2040c);
                            lVar.f2040c = resourceId;
                            if (resourceId == -1) {
                                lVar.f2041d = typedArray.getString(index);
                            } else {
                                continue;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            lVar.f2041d = typedArray.getString(index);
                        } else {
                            lVar.f2040c = typedArray.getResourceId(index, lVar.f2040c);
                        }
                        break;
                    case 8:
                        int integer = typedArray.getInteger(index, lVar.f2039b);
                        lVar.f2039b = integer;
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
                        lVar.f2088i = typedArray.getResourceId(index, lVar.f2088i);
                        break;
                }
                Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + f2089a.get(index));
            }
        }
    }

    public l() {
        int i2 = c.f2038a;
        this.f2088i = i2;
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
        this.f2042e = 5;
        this.f2043f = new HashMap<>();
    }

    @Override // b.f.a.b.c
    public void a(HashMap<String, r> map) {
    }

    @Override // b.f.a.b.c
    public void b(HashSet<String> hashSet) {
    }

    @Override // b.f.a.b.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, b.f.c.e.KeyTrigger), context);
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
        throw new UnsupportedOperationException("Method not decompiled: b.f.a.b.l.r(float, android.view.View):void");
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
