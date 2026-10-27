package com.byd.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.byd.common.R$id;
import com.byd.common.R$layout;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Toast f3011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static WeakReference<Context> f3012b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f3013c;
    public static int d;

    private static void a(Context context, Toast toast, String str, int i, int i2) {
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.layout_custom_toast, (ViewGroup) null);
        ((ImageView) viewInflate.findViewById(R$id.iv_toast_icon)).setImageResource(i2);
        ((TextView) viewInflate.findViewById(R$id.tv_toast_msg)).setText(str);
        viewInflate.setTag("custom");
        toast.setView(viewInflate);
        toast.setDuration(i);
    }

    private static void b(Toast toast) {
        int i = f3013c;
        if (i == 0 && d == 0) {
            return;
        }
        toast.setGravity(49, i, d);
    }

    public static void c(String str) {
        d("m_tag_c", str);
    }

    public static void d(String str, String str2) {
        Log.d(str, str2);
    }

    public static void e(Activity activity, int i) {
        f(activity, i, 0);
    }

    public static void f(Activity activity, int i, int i2) {
        h(activity, activity.getResources().getString(i), i2);
    }

    public static void g(Activity activity, String str) {
        i(activity, str, 0, 0);
    }

    public static void h(Activity activity, String str, int i) {
        i(activity, str, i, 0);
    }

    public static void i(Activity activity, String str, int i, int i2) {
        Toast toastMakeText;
        String str2;
        if (activity == null) {
            str2 = "toast fail , activity is null";
        } else {
            if (i2 != 0) {
                toastMakeText = new Toast(activity);
                a(activity, toastMakeText, str, i, i2);
            } else {
                toastMakeText = Toast.makeText(activity, str, i);
            }
            b(toastMakeText);
            toastMakeText.show();
            str2 = "activity toast show : " + str;
        }
        c(str2);
    }

    public static void j(Context context, int i) {
        k(context, i, 0);
    }

    public static void k(Context context, int i, int i2) {
        if (context == null) {
            c("toast fail , no context");
            return;
        }
        WeakReference<Context> weakReference = f3012b;
        if (weakReference == null || weakReference.get() == null) {
            f3012b = new WeakReference<>(context.getApplicationContext());
        }
        if (f3012b.get() == null) {
            c("toast fail , contextRef is null");
        } else {
            m(f3012b.get(), f3012b.get().getResources().getString(i), i2, 0);
        }
    }

    public static void l(Context context, int i, int i2, int i3) {
        if (context == null) {
            c("toast fail , no context");
            return;
        }
        WeakReference<Context> weakReference = f3012b;
        if (weakReference == null || weakReference.get() == null) {
            f3012b = new WeakReference<>(context.getApplicationContext());
        }
        m(f3012b.get(), f3012b.get().getResources().getString(i), i2, i3);
    }

    public static void m(Context context, String str, int i, int i2) {
        String str2;
        if (context == null) {
            str2 = "toast fail , context is null";
        } else {
            WeakReference<Context> weakReference = f3012b;
            if (weakReference == null || weakReference.get() == null) {
                f3012b = new WeakReference<>(context.getApplicationContext());
            }
            Toast toast = f3011a;
            if (toast == null) {
                if (i2 != 0) {
                    f3011a = new Toast(f3012b.get());
                    a(f3012b.get(), f3011a, str, i, i2);
                }
                f3011a = Toast.makeText(f3012b.get(), str, i);
            } else if (i2 != 0) {
                a(f3012b.get(), f3011a, str, i, i2);
            } else {
                View view = toast.getView();
                if (view == null || "custom".equals(view.getTag())) {
                    f3011a = Toast.makeText(f3012b.get(), str, i);
                } else {
                    f3011a.setDuration(i);
                    f3011a.setText(str);
                }
            }
            b(f3011a);
            f3011a.show();
            str2 = "toast show : " + str;
        }
        c(str2);
    }
}
