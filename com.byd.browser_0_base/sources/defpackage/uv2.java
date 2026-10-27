package defpackage;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.inputmethod.InputConnection;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.gfx.mojom.Rect;

/* JADX INFO: compiled from: StylusGestureHandler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class uv2 implements InvocationHandler {
    public final InputConnection a;
    public final Callback<ss1> b;

    public uv2(bt btVar, ur3 ur3Var) {
        this.a = btVar;
        this.b = ur3Var;
    }

    public static void a(int i) {
        oj2.U(i, 11, "InputMethod.StylusHandwriting.Gesture");
    }

    public static Rect b(PointF pointF) {
        Rect rect = new Rect();
        rect.b = Math.round(pointF.x);
        rect.c = Math.round(pointF.y);
        rect.d = 0;
        rect.e = 0;
        return rect;
    }

    public static Rect c(RectF rectF) {
        Rect rect = new Rect();
        rect.b = Math.round(rectF.left);
        rect.c = Math.round(rectF.top);
        rect.d = Math.round(rectF.right - rectF.left);
        rect.e = Math.round(rectF.bottom - rectF.top);
        return rect;
    }

    public static mu2 d(String str) {
        int length = str.length();
        short[] sArr = new short[length];
        for (int i = 0; i < length; i++) {
            sArr[i] = (short) str.charAt(i);
        }
        mu2 mu2Var = new mu2();
        mu2Var.b = sArr;
        return mu2Var;
    }

    public static Rect[] e(RectF rectF) {
        return new Rect[]{b(new PointF(rectF.left, (rectF.top + rectF.bottom) / 2.0f)), b(new PointF(rectF.right, (rectF.top + rectF.bottom) / 2.0f))};
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        xv2 xv2Var;
        xv2 xv2Var2;
        xv2 xv2Var3;
        if (!method.getName().equals("performHandwritingGesture")) {
            return method.invoke(this.a, objArr);
        }
        Object obj2 = objArr[0];
        if (!Class.forName("android.view.inputmethod.SelectGesture").isInstance(obj2)) {
            if (Class.forName("android.view.inputmethod.InsertGesture").isInstance(obj2)) {
                try {
                    Class<?> cls = Class.forName("android.view.inputmethod.InsertGesture");
                    xv2Var3 = new xv2();
                    xv2Var3.b = 1;
                    xv2Var3.c = 0;
                    xv2Var3.f = d((String) cls.getMethod("getFallbackText", new Class[0]).invoke(obj2, new Object[0]));
                    xv2Var3.g = d((String) cls.getMethod("getTextToInsert", new Class[0]).invoke(obj2, new Object[0]));
                    xv2Var3.d = b((PointF) cls.getMethod("getInsertionPoint", new Class[0]).invoke(obj2, new Object[0]));
                } catch (Throwable unused) {
                    Log.e("cr_StylusGestureHandler", "Could not unpack gesture object into InsertGesture");
                    xv2Var3 = null;
                }
                a(5);
            } else if (Class.forName("android.view.inputmethod.DeleteGesture").isInstance(obj2)) {
                try {
                    Class<?> cls2 = Class.forName("android.view.inputmethod.DeleteGesture");
                    xv2Var3 = new xv2();
                    xv2Var3.b = 0;
                    xv2Var3.c = ((Integer) cls2.getMethod("getGranularity", new Class[0]).invoke(obj2, new Object[0])).intValue() == 1 ? 1 : 0;
                    xv2Var3.f = d((String) cls2.getMethod("getFallbackText", new Class[0]).invoke(obj2, new Object[0]));
                    Rect[] rectArrE = e((RectF) cls2.getMethod("getDeletionArea", new Class[0]).invoke(obj2, new Object[0]));
                    xv2Var3.d = rectArrE[0];
                    xv2Var3.e = rectArrE[1];
                } catch (Throwable unused2) {
                    Log.e("cr_StylusGestureHandler", "Could not unpack gesture object into DeleteGesture");
                    xv2Var3 = null;
                }
                a(6);
            } else if (Class.forName("android.view.inputmethod.RemoveSpaceGesture").isInstance(obj2)) {
                try {
                    Class<?> cls3 = Class.forName("android.view.inputmethod.RemoveSpaceGesture");
                    xv2Var3 = new xv2();
                    xv2Var3.b = 2;
                    xv2Var3.c = 0;
                    xv2Var3.f = d((String) cls3.getMethod("getFallbackText", new Class[0]).invoke(obj2, new Object[0]));
                    xv2Var3.d = b((PointF) cls3.getMethod("getStartPoint", new Class[0]).invoke(obj2, new Object[0]));
                    xv2Var3.e = b((PointF) cls3.getMethod("getEndPoint", new Class[0]).invoke(obj2, new Object[0]));
                } catch (Throwable unused3) {
                    Log.e("cr_StylusGestureHandler", "Could not unpack gesture object into RemoveSpaceGesture");
                    xv2Var3 = null;
                }
                a(7);
            } else if (Class.forName("android.view.inputmethod.JoinOrSplitGesture").isInstance(obj2)) {
                try {
                    Class<?> cls4 = Class.forName("android.view.inputmethod.JoinOrSplitGesture");
                    xv2Var3 = new xv2();
                    xv2Var3.b = 3;
                    xv2Var3.c = 0;
                    xv2Var3.f = d((String) cls4.getMethod("getFallbackText", new Class[0]).invoke(obj2, new Object[0]));
                    xv2Var3.d = b((PointF) cls4.getMethod("getJoinOrSplitPoint", new Class[0]).invoke(obj2, new Object[0]));
                } catch (Throwable unused4) {
                    Log.e("cr_StylusGestureHandler", "Could not unpack gesture object into JoinOrSplitGesture");
                    xv2Var3 = null;
                }
                a(8);
            } else if (Class.forName("android.view.inputmethod.SelectRangeGesture").isInstance(obj2)) {
                try {
                    Class<?> cls5 = Class.forName("android.view.inputmethod.SelectRangeGesture");
                    xv2Var = new xv2();
                    xv2Var.b = 4;
                    xv2Var.c = ((Integer) cls5.getMethod("getGranularity", new Class[0]).invoke(obj2, new Object[0])).intValue() == 1 ? 1 : 0;
                    xv2Var.f = d((String) cls5.getMethod("getFallbackText", new Class[0]).invoke(obj2, new Object[0]));
                    xv2Var.d = c((RectF) cls5.getMethod("getSelectionStartArea", new Class[0]).invoke(obj2, new Object[0]));
                    xv2Var.e = c((RectF) cls5.getMethod("getSelectionEndArea", new Class[0]).invoke(obj2, new Object[0]));
                } catch (Throwable unused5) {
                    Log.e("cr_StylusGestureHandler", "Could not unpack gesture object into SelectRangeGesture");
                    xv2Var = null;
                }
                a(9);
            } else {
                if (!Class.forName("android.view.inputmethod.DeleteRangeGesture").isInstance(obj2)) {
                    xv2Var2 = null;
                    PostTask.c(rh3.d, new ci3(this, xv2Var2, (Executor) objArr[1], (IntConsumer) objArr[2], 3));
                    return null;
                }
                try {
                    Class<?> cls6 = Class.forName("android.view.inputmethod.DeleteRangeGesture");
                    xv2Var3 = new xv2();
                    xv2Var3.b = 0;
                    xv2Var3.c = ((Integer) cls6.getMethod("getGranularity", new Class[0]).invoke(obj2, new Object[0])).intValue() == 1 ? 1 : 0;
                    xv2Var3.f = d((String) cls6.getMethod("getFallbackText", new Class[0]).invoke(obj2, new Object[0]));
                    xv2Var3.d = c((RectF) cls6.getMethod("getDeletionStartArea", new Class[0]).invoke(obj2, new Object[0]));
                    xv2Var3.e = c((RectF) cls6.getMethod("getDeletionEndArea", new Class[0]).invoke(obj2, new Object[0]));
                } catch (Throwable unused6) {
                    Log.e("cr_StylusGestureHandler", "Could not unpack gesture object into DeleteRangeGesture");
                    xv2Var3 = null;
                }
                a(10);
            }
            xv2Var2 = xv2Var3;
            PostTask.c(rh3.d, new ci3(this, xv2Var2, (Executor) objArr[1], (IntConsumer) objArr[2], 3));
            return null;
        }
        try {
            Class<?> cls7 = Class.forName("android.view.inputmethod.SelectGesture");
            xv2Var = new xv2();
            xv2Var.b = 4;
            xv2Var.c = ((Integer) cls7.getMethod("getGranularity", new Class[0]).invoke(obj2, new Object[0])).intValue() == 1 ? 1 : 0;
            xv2Var.f = d((String) cls7.getMethod("getFallbackText", new Class[0]).invoke(obj2, new Object[0]));
            Rect[] rectArrE2 = e((RectF) cls7.getMethod("getSelectionArea", new Class[0]).invoke(obj2, new Object[0]));
            xv2Var.d = rectArrE2[0];
            xv2Var.e = rectArrE2[1];
        } catch (Throwable unused7) {
            Log.e("cr_StylusGestureHandler", "Could not unpack gesture object into SelectGesture");
            xv2Var = null;
        }
        a(4);
        xv2Var2 = xv2Var;
        PostTask.c(rh3.d, new ci3(this, xv2Var2, (Executor) objArr[1], (IntConsumer) objArr[2], 3));
        return null;
    }
}
