package com.byd.bluetoothcall.view.ui;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.bydux.api.R;

/* JADX INFO: compiled from: BT_Call_StautsBar_WM.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements View.OnClickListener {

    /* JADX INFO: renamed from: b */
    private static a f3481b;

    /* JADX INFO: renamed from: c */
    private Context f3482c;

    /* JADX INFO: renamed from: e */
    private View f3484e;
    private WindowManager.LayoutParams g;
    private TextView i;
    private ImageView j;
    private WindowManager m;

    /* JADX INFO: renamed from: d */
    private int f3483d = 1024;
    private int f = 0;
    private boolean h = false;
    private String k = "";
    private int l = 4;
    private boolean n = false;

    private a(Context context) {
        this.f3482c = context;
        c();
    }

    private void a(View view) {
        view.findViewById(R.id.btcall_wm_click).setOnClickListener(this);
        this.i = (TextView) view.findViewById(R.id.bt_call_wm_text);
        this.j = (ImageView) view.findViewById(R.id.bt_call_wm_state);
    }

    private void b() {
        WindowManager.LayoutParams layoutParams;
        View view = this.f3484e;
        if (view == null || (layoutParams = this.g) == null) {
            return;
        }
        try {
            this.m.addView(view, layoutParams);
            this.n = true;
        } catch (Exception unused) {
        }
    }

    private void c() {
        if (this.f3484e == null) {
            View viewInflate = ((LayoutInflater) this.f3482c.getSystemService("layout_inflater")).inflate(R.layout.bt_stausbar_call_wm_layout, (ViewGroup) null);
            this.f3484e = viewInflate;
            viewInflate.setSystemUiVisibility(this.f3483d);
        }
        int dimension = (int) this.f3482c.getResources().getDimension(R.dimen.dimens_32);
        int dimension2 = (int) this.f3482c.getResources().getDimension(R.dimen.dimens_12);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, dimension, 2010, 8, -2);
        this.g = layoutParams;
        layoutParams.y = dimension2;
        layoutParams.gravity = 49;
        layoutParams.flags = 1320;
        a(this.f3484e);
        WindowManager windowManager = (WindowManager) this.f3482c.getSystemService("window");
        this.m = windowManager;
        windowManager.addView(this.f3484e, this.g);
        this.h = true;
    }

    public static a d(Context context) {
        if (f3481b == null) {
            f3481b = new a(context.getApplicationContext());
        }
        return f3481b;
    }

    public void e() {
        View view;
        if (this.m == null || (view = this.f3484e) == null || !view.isShown()) {
            return;
        }
        this.f3484e.setVisibility(8);
        this.h = false;
    }

    public void f(int i, String str, int i2) {
        this.f = i;
        this.k = str;
        this.l = i2;
        i(str, i2);
    }

    public boolean g() {
        return this.h;
    }

    public void h() {
        View view;
        if (!this.n) {
            b();
        }
        if (this.m != null && (view = this.f3484e) != null && !view.isShown()) {
            this.f3484e.setVisibility(0);
        }
        this.h = true;
    }

    public void i(String str, int i) {
        ImageView imageView;
        TextView textView = this.i;
        if (textView != null) {
            textView.setText(str);
        }
        if (i == 1) {
            ImageView imageView2 = this.j;
            if (imageView2 != null) {
                imageView2.setImageResource(R.drawable.bt_stausbar_callin_icon);
                return;
            }
            return;
        }
        if (i == 2) {
            ImageView imageView3 = this.j;
            if (imageView3 != null) {
                imageView3.setImageResource(R.drawable.bt_stausbar_callout_icon);
                return;
            }
            return;
        }
        if (i != 3 || (imageView = this.j) == null) {
            return;
        }
        imageView.setImageResource(R.drawable.bt_stausbar_calling_icon);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btcall_wm_click) {
            return;
        }
        Log.d("DANDAN", "btcall_wm_click ============================");
        if (this.f == 2) {
            try {
                com.byd.bluetoothcall.c.a.F().C().requestUi(3);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }
}
