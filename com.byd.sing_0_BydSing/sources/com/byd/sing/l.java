package com.byd.sing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/* JADX INFO: compiled from: ToastManager.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Toast f3257a;

    public static void a(int i) {
        b(SingApplication.a().getString(i));
    }

    public static void b(String str) {
        c(str, 0, 0);
    }

    private static void c(String str, int i, int i2) {
        SingApplication singApplicationA = SingApplication.a();
        View viewInflate = LayoutInflater.from(singApplicationA).inflate(R.layout.layout_toast, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_message_normal);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_icon);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_message);
        textView.setTextColor(singApplicationA.getResources().getColor(R.color.text_toast));
        textView.setText(str);
        imageView.setVisibility(8);
        textView2.setVisibility(8);
        Toast toast = f3257a;
        if (toast != null) {
            toast.cancel();
        }
        Toast toast2 = new Toast(singApplicationA);
        f3257a = toast2;
        toast2.setGravity(49, 0, singApplicationA.getResources().getDimensionPixelSize(R.dimen.dp_72));
        f3257a.setDuration(i);
        f3257a.setView(viewInflate);
        f3257a.show();
    }
}
