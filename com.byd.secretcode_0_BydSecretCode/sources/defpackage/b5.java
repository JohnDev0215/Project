package defpackage;

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
public class b5 extends s4 {
    public int g = -1;
    public String h = null;
    public int i;
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
        public static SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.KeyTrigger_framePosition, 8);
            a.append(R$styleable.KeyTrigger_onCross, 4);
            a.append(R$styleable.KeyTrigger_onNegativeCross, 1);
            a.append(R$styleable.KeyTrigger_onPositiveCross, 2);
            a.append(R$styleable.KeyTrigger_motionTarget, 7);
            a.append(R$styleable.KeyTrigger_triggerId, 6);
            a.append(R$styleable.KeyTrigger_triggerSlack, 5);
            a.append(R$styleable.KeyTrigger_motion_triggerOnCollision, 9);
            a.append(R$styleable.KeyTrigger_motion_postLayoutCollision, 10);
            a.append(R$styleable.KeyTrigger_triggerReceiver, 11);
        }

        public static void a(b5 b5Var, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (a.get(index)) {
                    case 1:
                        b5Var.j = typedArray.getString(index);
                        continue;
                        break;
                    case 2:
                        b5Var.k = typedArray.getString(index);
                        continue;
                        break;
                    case 4:
                        b5Var.h = typedArray.getString(index);
                        continue;
                        break;
                    case 5:
                        b5Var.o = typedArray.getFloat(index, b5Var.o);
                        continue;
                        break;
                    case 6:
                        b5Var.l = typedArray.getResourceId(index, b5Var.l);
                        continue;
                        break;
                    case 7:
                        if (MotionLayout.y) {
                            int resourceId = typedArray.getResourceId(index, b5Var.c);
                            b5Var.c = resourceId;
                            if (resourceId == -1) {
                                b5Var.d = typedArray.getString(index);
                            } else {
                                continue;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            b5Var.d = typedArray.getString(index);
                        } else {
                            b5Var.c = typedArray.getResourceId(index, b5Var.c);
                        }
                        break;
                    case 8:
                        int integer = typedArray.getInteger(index, b5Var.b);
                        b5Var.b = integer;
                        b5Var.s = (integer + 0.5f) / 100.0f;
                        continue;
                        break;
                    case 9:
                        b5Var.m = typedArray.getResourceId(index, b5Var.m);
                        continue;
                        break;
                    case 10:
                        b5Var.x = typedArray.getBoolean(index, b5Var.x);
                        continue;
                        break;
                    case 11:
                        b5Var.i = typedArray.getResourceId(index, b5Var.i);
                        break;
                }
                Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + a.get(index));
            }
        }
    }

    public b5() {
        int i = s4.a;
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
        this.e = 5;
        this.f = new HashMap<>();
    }

    @Override // defpackage.s4
    public void a(HashMap<String, h5> map) {
    }

    @Override // defpackage.s4
    public void b(HashSet<String> hashSet) {
    }

    @Override // defpackage.s4
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.b5.r(float, android.view.View):void");
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
