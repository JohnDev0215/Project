package com.byd.logtools.tools;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.byd.logtools.R;
import com.byd.logtools.application.BydLogApplication;

/* JADX INFO: compiled from: BYDCustomToast.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a f3216a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f3217b = BydLogApplication.d().getApplicationContext();

    public static a a() {
        if (f3216a == null) {
            f3216a = new a();
        }
        return f3216a;
    }

    public void b(int i) {
        LinearLayout.LayoutParams layoutParams;
        Display defaultDisplay = ((WindowManager) this.f3217b.getApplicationContext().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i2 = point.x;
        int i3 = point.y;
        View viewInflate = LayoutInflater.from(this.f3217b).inflate(R.layout.layout_bottom_toast, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.ll_root);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_msg);
        Toast toast = new Toast(this.f3217b);
        toast.setDuration(0);
        if (this.f3217b.getResources().getConfiguration().orientation == 2) {
            layoutParams = new LinearLayout.LayoutParams((i2 * 480) / 1280, (i3 * 55) / 720);
            toast.setGravity(7, 0, (i3 * 212) / 720);
        } else {
            layoutParams = new LinearLayout.LayoutParams((i2 * 480) / 720, (i3 * 55) / 1280);
            toast.setGravity(7, 0, (i3 * 270) / 1280);
        }
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackground(androidx.core.content.c.f.a(this.f3217b.getResources(), R.drawable.toast_shape, null));
        textView.setText(i);
        toast.setView(viewInflate);
        toast.show();
        toast.setView(null);
    }

    public void c(String str) {
        LinearLayout.LayoutParams layoutParams;
        Display defaultDisplay = ((WindowManager) this.f3217b.getApplicationContext().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i = point.x;
        int i2 = point.y;
        View viewInflate = LayoutInflater.from(this.f3217b).inflate(R.layout.layout_bottom_toast, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.ll_root);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_msg);
        Toast toast = new Toast(this.f3217b);
        toast.setDuration(0);
        if (this.f3217b.getResources().getConfiguration().orientation == 2) {
            layoutParams = new LinearLayout.LayoutParams((i * 480) / 1280, (i2 * 55) / 720);
            toast.setGravity(7, 0, (i2 * 212) / 720);
        } else {
            layoutParams = new LinearLayout.LayoutParams((i * 480) / 720, (i2 * 55) / 1280);
            toast.setGravity(7, 0, (i2 * 270) / 1280);
        }
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackground(androidx.core.content.c.f.a(this.f3217b.getResources(), R.drawable.toast_shape, null));
        textView.setText(str);
        toast.setView(viewInflate);
        toast.show();
        toast.setView(null);
    }
}
