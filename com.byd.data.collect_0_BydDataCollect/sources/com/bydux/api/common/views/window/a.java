package com.bydux.api.common.views.window;

import android.content.Context;
import android.content.res.Resources;
import android.widget.TextView;
import android.widget.Toast;
import d.a.a.b;
import d.a.a.c;
import d.a.a.d;

/* JADX INFO: compiled from: ToastUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Toast f3633a;

    public static void a(Context context, String str) {
        if (context == null) {
            return;
        }
        Toast toast = f3633a;
        if (toast != null) {
            toast.cancel();
            f3633a = null;
        }
        f3633a = new Toast(context);
        TextView textView = new TextView(context);
        textView.setScaleX(0.9f);
        textView.setScaleY(0.9f);
        textView.setAlpha(0.0f);
        textView.setBackground(androidx.core.content.a.d(context, d.toast_background));
        textView.setTextColor(androidx.core.content.a.b(context, b.toast_textcolor));
        textView.setTextSize(0, context.getResources().getDimension(c.sp_18));
        textView.setText(str);
        textView.setGravity(17);
        textView.setMaxWidth(context.getResources().getDimensionPixelSize(c.dp_480));
        textView.setMinWidth(context.getResources().getDimensionPixelSize(c.dp_200));
        Resources resources = context.getResources();
        int i2 = c.dp_24;
        int dimensionPixelSize = resources.getDimensionPixelSize(i2);
        Resources resources2 = context.getResources();
        int i3 = c.dp_15;
        textView.setPadding(dimensionPixelSize, resources2.getDimensionPixelSize(i3), context.getResources().getDimensionPixelSize(i2), context.getResources().getDimensionPixelSize(i3));
        f3633a.setGravity(49, 0, context.getResources().getDimensionPixelSize(context.getResources().getConfiguration().orientation == 1 ? c.dp_190 : c.dp_72));
        f3633a.setDuration(1);
        textView.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(d.a.a.f.b.c.b.a()).setDuration(200L).start();
        textView.animate().alpha(1.0f).setInterpolator(d.a.a.f.b.c.b.c()).setDuration(200L).start();
        f3633a.setView(textView);
        f3633a.show();
    }
}
