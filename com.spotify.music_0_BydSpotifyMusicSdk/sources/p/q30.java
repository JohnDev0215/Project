package p;

import android.R;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.WeakHashMap;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.channels.BufferOverflow;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public abstract class q30 {
    public static final eo a = new eo();
    public static final ke2 b = new ke2(1);
    public static final int[] c = {R.attr.state_enabled, R.attr.state_pressed};
    public static final ke2 d = new ke2(17);
    public static boolean e = false;
    public static Method f = null;
    public static boolean g = false;
    public static Field h;

    public q30(Class cls) {
    }

    public static kotlinx.coroutines.channels.a a(int i, BufferOverflow bufferOverflow, int i2) {
        kotlinx.coroutines.channels.a lxVar;
        if ((i2 & 1) != 0) {
            i = 0;
        }
        int i3 = i2 & 2;
        BufferOverflow bufferOverflow2 = BufferOverflow.c;
        if (i3 != 0) {
            bufferOverflow = bufferOverflow2;
        }
        if (i != -2) {
            if (i == -1) {
                if (bufferOverflow == bufferOverflow2) {
                    return new lx(1, BufferOverflow.e, null);
                }
                throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
            }
            if (i != 0) {
                return i != Integer.MAX_VALUE ? bufferOverflow == bufferOverflow2 ? new kotlinx.coroutines.channels.a(i, null) : new lx(i, bufferOverflow, null) : new kotlinx.coroutines.channels.a(Integer.MAX_VALUE, null);
            }
            lxVar = bufferOverflow == bufferOverflow2 ? new kotlinx.coroutines.channels.a(0, null) : new lx(1, bufferOverflow, null);
        } else if (bufferOverflow == bufferOverflow2) {
            yq.a.getClass();
            lxVar = new kotlinx.coroutines.channels.a(xq.b, null);
        } else {
            lxVar = new lx(1, bufferOverflow, null);
        }
        return lxVar;
    }

    public static final yz b(y10 y10Var) {
        if (y10Var.E0(qo.o) == null) {
            y10Var = y10Var.o0(zu.a());
        }
        return new yz(y10Var);
    }

    public static final boolean c(int i, int i2, int i3, byte[] bArr, byte[] bArr2) {
        v03.j(bArr, "a");
        v03.j(bArr2, "b");
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i4 + i] != bArr2[i4 + i2]) {
                return false;
            }
        }
        return true;
    }

    public static String d(Object obj) {
        String string = obj != null ? obj.toString() : null;
        return string == null ? "" : string;
    }

    public static void e(yz yzVar) {
        p61 p61Var = (p61) yzVar.c.E0(qo.o);
        if (p61Var != null) {
            p61Var.i(null);
        } else {
            throw new IllegalStateException(("Scope cannot be cancelled because it does not have a job: " + yzVar).toString());
        }
    }

    public static final void i(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException("size=" + j + " offset=" + j2 + " byteCount=" + j3);
        }
    }

    public static boolean k(View view, KeyEvent keyEvent) {
        WeakReference weakReference;
        ArrayList arrayList;
        int size;
        int iIndexOfKey;
        WeakHashMap weakHashMap = zt2.a;
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList2 = yt2.d;
        yt2 yt2Var = (yt2) view.getTag(com.spotify.music.R.id.tag_unhandled_key_event_manager);
        if (yt2Var == null) {
            yt2Var = new yt2();
            yt2Var.a = null;
            yt2Var.b = null;
            yt2Var.c = null;
            view.setTag(com.spotify.music.R.id.tag_unhandled_key_event_manager, yt2Var);
        }
        WeakReference weakReference2 = yt2Var.c;
        if (weakReference2 != null && weakReference2.get() == keyEvent) {
            return false;
        }
        yt2Var.c = new WeakReference(keyEvent);
        if (yt2Var.b == null) {
            yt2Var.b = new SparseArray();
        }
        SparseArray sparseArray = yt2Var.b;
        if (keyEvent.getAction() != 1 || (iIndexOfKey = sparseArray.indexOfKey(keyEvent.getKeyCode())) < 0) {
            weakReference = null;
        } else {
            weakReference = (WeakReference) sparseArray.valueAt(iIndexOfKey);
            sparseArray.removeAt(iIndexOfKey);
        }
        if (weakReference == null) {
            weakReference = (WeakReference) sparseArray.get(keyEvent.getKeyCode());
        }
        if (weakReference == null) {
            return false;
        }
        View view2 = (View) weakReference.get();
        if (view2 == null || !lt2.b(view2) || (arrayList = (ArrayList) view2.getTag(com.spotify.music.R.id.tag_unhandled_key_listeners)) == null || (size = arrayList.size() - 1) < 0) {
            return true;
        }
        u4.z(arrayList.get(size));
        throw null;
    }

    public static boolean l(m81 m81Var, View view, Window.Callback callback, KeyEvent keyEvent) {
        DialogInterface.OnKeyListener onKeyListener;
        if (m81Var == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return m81Var.d(keyEvent);
        }
        if (callback instanceof Activity) {
            Activity activity = (Activity) callback;
            activity.onUserInteraction();
            Window window = activity.getWindow();
            if (window.hasFeature(8)) {
                ActionBar actionBar = activity.getActionBar();
                if (keyEvent.getKeyCode() == 82 && actionBar != null) {
                    if (!e) {
                        try {
                            f = actionBar.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
                        } catch (NoSuchMethodException unused) {
                        }
                        e = true;
                    }
                    Method method = f;
                    if (method != null) {
                        try {
                            Object objInvoke = method.invoke(actionBar, keyEvent);
                            if (objInvoke != null) {
                                if (((Boolean) objInvoke).booleanValue()) {
                                    return true;
                                }
                            }
                        } catch (IllegalAccessException | InvocationTargetException unused2) {
                        }
                    }
                }
            }
            if (window.superDispatchKeyEvent(keyEvent)) {
                return true;
            }
            View decorView = window.getDecorView();
            if (zt2.b(decorView, keyEvent)) {
                return true;
            }
            return keyEvent.dispatch(activity, decorView != null ? decorView.getKeyDispatcherState() : null, activity);
        }
        if (!(callback instanceof Dialog)) {
            return (view != null && zt2.b(view, keyEvent)) || m81Var.d(keyEvent);
        }
        Dialog dialog = (Dialog) callback;
        if (!g) {
            try {
                Field declaredField = Dialog.class.getDeclaredField("mOnKeyListener");
                h = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused3) {
            }
            g = true;
        }
        Field field = h;
        if (field != null) {
            try {
                onKeyListener = (DialogInterface.OnKeyListener) field.get(dialog);
            } catch (IllegalAccessException unused4) {
                onKeyListener = null;
            }
        } else {
            onKeyListener = null;
        }
        if (onKeyListener != null && onKeyListener.onKey(dialog, keyEvent.getKeyCode(), keyEvent)) {
            return true;
        }
        Window window2 = dialog.getWindow();
        if (window2.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View decorView2 = window2.getDecorView();
        if (zt2.b(decorView2, keyEvent)) {
            return true;
        }
        return keyEvent.dispatch(dialog, decorView2 != null ? decorView2.getKeyDispatcherState() : null, dialog);
    }

    public static /* synthetic */ oa0 m(p61 p61Var, boolean z, t61 t61Var, int i) {
        if ((i & 1) != 0) {
            z = false;
        }
        return ((kotlinx.coroutines.g) p61Var).M(z, (i & 2) != 0, t61Var);
    }

    public static final Object o(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(obj2);
        return arrayList;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:14:0x0035. Please report as an issue. */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0105  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List r(p.u71 r13) {
        /*
            Method dump skipped, instruction units count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.q30.r(p.u71):java.util.List");
    }

    public static final int s(int i, ByteString byteString) {
        v03.j(byteString, "<this>");
        return i == -1234567890 ? byteString.e() : i;
    }

    public static ColorStateList t(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return ColorStateList.valueOf(0);
        }
        if (Build.VERSION.SDK_INT <= 27 && Color.alpha(colorStateList.getDefaultColor()) == 0) {
            Color.alpha(colorStateList.getColorForState(c, 0));
        }
        return colorStateList;
    }

    public static boolean u(int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        for (int i : iArr) {
            if (i == 16842910) {
                z = true;
            } else if (i == 16842908 || i == 16842919 || i == 16843623) {
                z2 = true;
            }
        }
        return z && z2;
    }

    public static final Object v(o72 o72Var, o72 o72Var2, fz0 fz0Var) throws Throwable {
        Object hvVar;
        Object objO;
        try {
            he1.d(2, fz0Var);
            hvVar = fz0Var.g(o72Var2, o72Var);
        } catch (Throwable th) {
            hvVar = new hv(th, false);
        }
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        if (hvVar == coroutineSingletons || (objO = o72Var.O(hvVar)) == y61.b) {
            return coroutineSingletons;
        }
        if (objO instanceof hv) {
            throw ((hv) objO).a;
        }
        return y61.a(objO);
    }

    public abstract boolean f(g2 g2Var, c2 c2Var, c2 c2Var2);

    public abstract boolean g(g2 g2Var, Object obj, Object obj2);

    public abstract boolean h(g2 g2Var, f2 f2Var, f2 f2Var2);

    public abstract Date j(Date date);

    public abstract Object n(Intent intent, int i);

    public abstract void p(f2 f2Var, f2 f2Var2);

    public abstract void q(f2 f2Var, Thread thread);
}
