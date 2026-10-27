package com.byd.engineermode.ui.dialog;

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
import androidx.core.content.c.f;
import butterknife.R;

/* JADX INFO: compiled from: BYDCustomToast.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a f2368a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f2369b;

    public a(Context context) {
        this.f2369b = context.getApplicationContext();
    }

    public static a a(Context context) {
        a aVar = new a(context);
        f2368a = aVar;
        return aVar;
    }

    public void b(int i) {
        LinearLayout.LayoutParams layoutParams;
        Display defaultDisplay = ((WindowManager) this.f2369b.getApplicationContext().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i2 = point.x;
        int i3 = point.y;
        View viewInflate = LayoutInflater.from(this.f2369b).inflate(R.layout.layout_bottom_toast, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.ll_root);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_msg);
        Toast toast = new Toast(this.f2369b);
        toast.setDuration(0);
        if (this.f2369b.getResources().getConfiguration().orientation == 2) {
            layoutParams = new LinearLayout.LayoutParams((i2 * 480) / 1280, (i3 * 55) / 720);
            toast.setGravity(55, 0, (int) this.f2369b.getResources().getDimension(R.dimen.dp_72));
        } else {
            layoutParams = new LinearLayout.LayoutParams((i2 * 480) / 720, (i3 * 55) / 1280);
            toast.setGravity(55, 0, (int) this.f2369b.getResources().getDimension(R.dimen.dp_190));
        }
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackground(f.a(this.f2369b.getResources(), R.drawable.toast_shape, null));
        textView.setText(i);
        toast.setView(viewInflate);
        toast.show();
        toast.setView(null);
    }
}
