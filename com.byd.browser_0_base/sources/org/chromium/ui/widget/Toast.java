package org.chromium.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import defpackage.cb3;
import defpackage.eb3;
import defpackage.wh3;
import defpackage.ya0;
import java.util.Iterator;
import java.util.PriorityQueue;
import org.chromium.base.SysUtils;
import org.chromium.ui.R$layout;

/* JADX INFO: loaded from: classes3.dex */
public final class Toast {
    public static int e;
    public final android.widget.Toast a;
    public final FrameLayout b;
    public int c;
    public CharSequence d;

    public static class Builder {
        public final Context a;
        public CharSequence b;
        public View c;
        public Integer d;
        public Integer e;
        public int f = 0;
        public int g = 1;

        public Builder(Context context) {
            this.a = context;
        }

        public final Toast a() {
            Context context = this.a;
            TextView textView = (TextView) LayoutInflater.from(context).inflate(R$layout.custom_toast_layout, (ViewGroup) null);
            CharSequence charSequence = this.b;
            if (charSequence != null) {
                textView.setText(charSequence);
                textView.announceForAccessibility(this.b);
            }
            if (this.d != null) {
                textView.getBackground().setTint(this.d.intValue());
            }
            Integer num = this.e;
            if (num != null) {
                textView.setTextAppearance(num.intValue());
            }
            Toast toast = new Toast(context, textView);
            View view = this.c;
            if (view != null) {
                Point point = ya0.a(context).c;
                int i = point.x;
                int i2 = point.y;
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                int width = view.getWidth();
                int height = view.getHeight();
                int i3 = iArr[0];
                boolean z = i3 < i / 2;
                int i4 = z ? 3 : 5;
                int i5 = z ? (width / 2) + i3 : (i - i3) - (width / 2);
                int i6 = iArr[1];
                toast.d(i4 | 48, i5, i6 < i2 / 2 ? (height / 2) + i6 : i6 - ((height * 3) / 2));
            }
            toast.a.setDuration(this.f);
            toast.c = this.g;
            toast.d = this.b;
            return toast;
        }
    }

    public Toast(Context context, TextView textView) {
        if (SysUtils.isLowEndDevice()) {
            this.b = new FrameLayout(new cb3(context));
        }
        if (wh3.a == null) {
            wh3.a = new wh3();
        }
        wh3.a.getClass();
        android.widget.Toast toast = new android.widget.Toast(context);
        this.a = toast;
        FrameLayout frameLayout = this.b;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
            if (textView != null) {
                frameLayout.addView(textView, -2, -2);
                toast.setView(frameLayout);
            } else {
                toast.setView(null);
            }
        } else {
            toast.setView(textView);
        }
        toast.setGravity(toast.getGravity(), toast.getXOffset(), toast.getYOffset() + e);
    }

    @SuppressLint({"ShowToast"})
    public static Toast b(int i, Context context, CharSequence charSequence) {
        Builder builder = new Builder(context);
        builder.b = charSequence;
        builder.f = i;
        return builder.a();
    }

    public static Toast c(Context context, int i, int i2) {
        Builder builder = new Builder(context);
        builder.b = builder.a.getResources().getText(i);
        builder.f = i2;
        return builder.a();
    }

    public static boolean f(Context context, View view, CharSequence charSequence) {
        Builder builder = new Builder(context);
        builder.c = view;
        builder.b = charSequence;
        if (charSequence == null) {
            return false;
        }
        builder.a().e();
        return true;
    }

    public final void a() {
        if (!eb3.a()) {
            this.a.cancel();
            return;
        }
        if (eb3.e == null) {
            eb3.e = new eb3();
        }
        eb3 eb3Var = eb3.e;
        Toast toast = eb3Var.c;
        Toast toast2 = null;
        if (this == toast) {
            toast.a.cancel();
            eb3Var.c = null;
            eb3Var.b.onCancel();
            return;
        }
        PriorityQueue<Toast> priorityQueue = eb3Var.a;
        Iterator<Toast> it = priorityQueue.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Toast next = it.next();
            if (TextUtils.equals(next.d, this.d)) {
                toast2 = next;
                break;
            }
        }
        if (toast2 != null) {
            priorityQueue.remove(toast2);
        }
    }

    public final void d(int i, int i2, int i3) {
        this.a.setGravity(i, i2, i3);
    }

    public final void e() {
        boolean z;
        if (!eb3.a()) {
            this.a.show();
            return;
        }
        if (eb3.e == null) {
            eb3.e = new eb3();
        }
        eb3 eb3Var = eb3.e;
        Toast toast = eb3Var.c;
        PriorityQueue<Toast> priorityQueue = eb3Var.a;
        if (toast != null && (toast == this || TextUtils.equals(toast.d, this.d))) {
            z = true;
            break;
        }
        for (Toast toast2 : priorityQueue) {
            if (toast2 == this || TextUtils.equals(toast2.d, this.d)) {
                z = true;
                break;
            }
        }
        z = false;
        if (z) {
            return;
        }
        priorityQueue.add(this);
        if (eb3Var.c == null) {
            eb3Var.b();
        }
    }
}
