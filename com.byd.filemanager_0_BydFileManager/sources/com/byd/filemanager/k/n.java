package com.byd.filemanager.k;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.byd.filemanager.FileApplication;
import com.byd.filemanager.R;

/* JADX INFO: compiled from: ToastManager.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Toast f3639a;

    public static void a(int i) {
        b(FileApplication.a().getString(i));
    }

    public static void b(String str) {
        c(str, 0, 0);
    }

    private static void c(String str, int i, int i2) {
        FileApplication fileApplicationA = FileApplication.a();
        View viewInflate = com.byd.filemanager.f.h().v() ? LayoutInflater.from(fileApplicationA).inflate(R.layout.layout_toast, (ViewGroup) null) : LayoutInflater.from(fileApplicationA).inflate(R.layout.layout_toast_denza, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_message_normal);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_icon);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_message);
        if (i2 == 0) {
            textView.setTextColor(fileApplicationA.getResources().getColor(R.color.text_toast));
            textView.setText(str);
            imageView.setVisibility(8);
            textView2.setVisibility(8);
        } else {
            textView.setVisibility(8);
            imageView.setImageResource(i2 == 1 ? R.drawable.icon_public_green_cherk_center_48 : R.drawable.icon_public_redx_center_48);
            textView2.setText(str);
            textView2.setTextColor(i2 != -1 ? fileApplicationA.getResources().getColor(R.color.text_toast) : fileApplicationA.getResources().getColor(R.color.text_warning));
        }
        Toast toast = f3639a;
        if (toast != null) {
            toast.cancel();
        }
        Toast toast2 = new Toast(fileApplicationA);
        f3639a = toast2;
        toast2.setGravity(49, 0, fileApplicationA.getResources().getDimensionPixelSize(R.dimen.dp_72));
        f3639a.setDuration(i);
        f3639a.setView(viewInflate);
        f3639a.show();
    }

    public static void d(boolean z, int i, int i2) {
        if (z) {
            g(i);
        } else {
            e(i2);
        }
    }

    public static void e(int i) {
        f(FileApplication.a().getString(i));
    }

    public static void f(String str) {
        c(str, 0, -1);
    }

    public static void g(int i) {
        h(FileApplication.a().getString(i));
    }

    public static void h(String str) {
        c(str, 0, 1);
    }
}
