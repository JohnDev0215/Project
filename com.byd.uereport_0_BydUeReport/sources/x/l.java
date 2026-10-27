package x;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: KeyTrigger.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f8869g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f8870h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f8871i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f8872j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f8873k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f8874l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8875m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View f8876n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f8877o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f8878p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f8879q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f8880r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f8881s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Method f8882t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Method f8883u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Method f8884v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f8885w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f8886x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public RectF f8887y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public RectF f8888z;

    /* JADX INFO: compiled from: KeyTrigger.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f8889a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f8889a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyTrigger_framePosition, 8);
            f8889a.append(R$styleable.KeyTrigger_onCross, 4);
            f8889a.append(R$styleable.KeyTrigger_onNegativeCross, 1);
            f8889a.append(R$styleable.KeyTrigger_onPositiveCross, 2);
            f8889a.append(R$styleable.KeyTrigger_motionTarget, 7);
            f8889a.append(R$styleable.KeyTrigger_triggerId, 6);
            f8889a.append(R$styleable.KeyTrigger_triggerSlack, 5);
            f8889a.append(R$styleable.KeyTrigger_motion_triggerOnCollision, 9);
            f8889a.append(R$styleable.KeyTrigger_motion_postLayoutCollision, 10);
            f8889a.append(R$styleable.KeyTrigger_triggerReceiver, 11);
        }

        public static void a(l lVar, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArray.getIndex(i8);
                switch (f8889a.get(index)) {
                    case 1:
                        lVar.f8872j = typedArray.getString(index);
                        continue;
                        break;
                    case 2:
                        lVar.f8873k = typedArray.getString(index);
                        continue;
                        break;
                    case 4:
                        lVar.f8870h = typedArray.getString(index);
                        continue;
                        break;
                    case 5:
                        lVar.f8877o = typedArray.getFloat(index, lVar.f8877o);
                        continue;
                        break;
                    case 6:
                        lVar.f8874l = typedArray.getResourceId(index, lVar.f8874l);
                        continue;
                        break;
                    case 7:
                        if (MotionLayout.A) {
                            int resourceId = typedArray.getResourceId(index, lVar.f8766c);
                            lVar.f8766c = resourceId;
                            if (resourceId == -1) {
                                lVar.f8767d = typedArray.getString(index);
                            } else {
                                continue;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            lVar.f8767d = typedArray.getString(index);
                        } else {
                            lVar.f8766c = typedArray.getResourceId(index, lVar.f8766c);
                        }
                        break;
                    case 8:
                        int integer = typedArray.getInteger(index, lVar.f8765b);
                        lVar.f8765b = integer;
                        lVar.f8881s = (integer + 0.5f) / 100.0f;
                        continue;
                        break;
                    case 9:
                        lVar.f8875m = typedArray.getResourceId(index, lVar.f8875m);
                        continue;
                        break;
                    case 10:
                        lVar.f8886x = typedArray.getBoolean(index, lVar.f8886x);
                        continue;
                        break;
                    case 11:
                        lVar.f8871i = typedArray.getResourceId(index, lVar.f8871i);
                        break;
                }
                Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + f8889a.get(index));
            }
        }
    }

    public l() {
        int i8 = c.f8764a;
        this.f8871i = i8;
        this.f8872j = null;
        this.f8873k = null;
        this.f8874l = i8;
        this.f8875m = i8;
        this.f8876n = null;
        this.f8877o = 0.1f;
        this.f8878p = true;
        this.f8879q = true;
        this.f8880r = true;
        this.f8881s = Float.NaN;
        this.f8886x = false;
        this.f8887y = new RectF();
        this.f8888z = new RectF();
        this.f8768e = 5;
        this.f8769f = new HashMap<>();
    }

    @Override // x.c
    public void a(HashMap<String, r> map) {
    }

    @Override // x.c
    public void b(HashSet<String> hashSet) {
    }

    @Override // x.c
    public void c(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R$styleable.KeyTrigger), context);
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
        throw new UnsupportedOperationException("Method not decompiled: x.l.r(float, android.view.View):void");
    }

    public final void s(RectF rectF, View view, boolean z7) {
        rectF.top = view.getTop();
        rectF.bottom = view.getBottom();
        rectF.left = view.getLeft();
        rectF.right = view.getRight();
        if (z7) {
            view.getMatrix().mapRect(rectF);
        }
    }
}
