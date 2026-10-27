package com.byd.bluetoothcall.view.d;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.bluetoothcall.f.f;
import com.byd.constant.StateConstant$SCO_STATE;
import com.bydux.api.R;

/* JADX INFO: compiled from: PhoneModeSelect_WM.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements View.OnClickListener {

    /* JADX INFO: renamed from: b */
    private static c f3449b;

    /* JADX INFO: renamed from: c */
    private transient Context f3450c;

    /* JADX INFO: renamed from: d */
    private transient View f3451d;

    /* JADX INFO: renamed from: e */
    private transient int f3452e = 1024;
    private transient boolean f = false;
    private transient LinearLayout g;
    private transient View h;
    private transient TextView i;
    private transient TextView j;
    private transient TextView k;
    private transient TextView l;
    private transient RelativeLayout m;

    private c(Context context) {
        c(context);
    }

    private void b(View view) {
        this.m = (RelativeLayout) view.findViewById(R.id.phone_mode_corver);
        this.g = (LinearLayout) view.findViewById(R.id.phone_mode_bg);
        this.h = view.findViewById(R.id.phone_mode_line);
        TextView textView = (TextView) view.findViewById(R.id.dialog_end_and_accept_btn);
        this.i = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) view.findViewById(R.id.dialog_reject_btn);
        this.j = textView2;
        textView2.setOnClickListener(this);
        this.k = (TextView) view.findViewById(R.id.phone_mode_title);
        this.l = (TextView) view.findViewById(R.id.phone_mode_text);
    }

    private void c(Context context) {
        this.f3450c = context;
    }

    public static c g(Context context) {
        if (f3449b == null) {
            f3449b = new c(context.getApplicationContext());
        }
        return f3449b;
    }

    private void j() {
        RelativeLayout relativeLayout = this.m;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundColor(this.f3450c.getColor(R.color.ts_popover_translucent_background));
        }
        TextView textView = this.k;
        if (textView != null) {
            textView.setTextColor(this.f3450c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView2 = this.l;
        if (textView2 != null) {
            textView2.setTextColor(this.f3450c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView3 = this.i;
        if (textView3 != null) {
            textView3.setTextColor(this.f3450c.getColor(R.color.ts_ff_white_color));
            this.i.setBackgroundDrawable(this.f3450c.getDrawable(R.drawable.denza_dialog_btn_p_shape));
        }
        TextView textView4 = this.j;
        if (textView4 != null) {
            textView4.setTextColor(this.f3450c.getColor(R.color.ts_d9_text_color));
            this.j.setBackgroundDrawable(this.f3450c.getDrawable(R.drawable.denza_dialog_btn_n_shape));
        }
        LinearLayout linearLayout = this.g;
        if (linearLayout != null) {
            linearLayout.setBackgroundDrawable(this.f3450c.getDrawable(R.drawable.denza_contacts_list_wm_bg));
        }
        View view = this.h;
        if (view != null) {
            view.setBackgroundColor(this.f3450c.getColor(R.color.ts_list_line_1));
        }
    }

    private void k() {
        RelativeLayout relativeLayout = this.m;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundColor(this.f3450c.getColor(R.color.popover_translucent_background));
        }
        TextView textView = this.k;
        if (textView != null) {
            textView.setTextColor(this.f3450c.getColor(R.color.ff_text_color));
        }
        TextView textView2 = this.l;
        if (textView2 != null) {
            textView2.setTextColor(this.f3450c.getColor(R.color.ff_text_color));
        }
        TextView textView3 = this.i;
        if (textView3 != null) {
            textView3.setTextColor(this.f3450c.getColor(R.color.ff_white_color));
            this.i.setBackgroundDrawable(this.f3450c.getDrawable(R.drawable.dialog_btn_p_shape));
        }
        TextView textView4 = this.j;
        if (textView4 != null) {
            textView4.setTextColor(this.f3450c.getColor(R.color.d9_text_color));
            this.j.setBackgroundDrawable(this.f3450c.getDrawable(R.drawable.dialog_btn_n_shape));
        }
        LinearLayout linearLayout = this.g;
        if (linearLayout != null) {
            linearLayout.setBackgroundDrawable(this.f3450c.getDrawable(R.drawable.contacts_list_wm_bg));
        }
        View view = this.h;
        if (view != null) {
            view.setBackgroundColor(this.f3450c.getColor(R.color.list_line_1));
        }
    }

    public void a() {
        if (this.f3451d != null) {
            ((WindowManager) this.f3450c.getSystemService("window")).removeViewImmediate(this.f3451d);
            this.f3451d = null;
            this.f = false;
        }
    }

    public void d() {
        a();
    }

    public void e() {
        if (f.l(this.f3450c) == 1) {
            this.f3450c.setTheme(R.style.ThemeTS);
        } else {
            this.f3450c.setTheme(R.style.ThemeTang);
        }
        if (this.f) {
            return;
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2008, 8, -2);
        WindowManager windowManager = (WindowManager) this.f3450c.getSystemService("window");
        if (this.f3451d == null) {
            View viewInflate = ((LayoutInflater) this.f3450c.getSystemService("layout_inflater")).inflate(R.layout.phonemode_select_wm, (ViewGroup) null);
            this.f3451d = viewInflate;
            viewInflate.setSystemUiVisibility(this.f3452e);
            b(this.f3451d);
        }
        windowManager.addView(this.f3451d, layoutParams);
        this.f = true;
    }

    public void f() {
        com.byd.bluetoothcall.c.a.F().n0(false);
        c.a.b.b.f0().l(StateConstant$SCO_STATE.CHANNEL_BT);
        a();
    }

    public boolean h() {
        return this.f;
    }

    public void i(Configuration configuration) {
        if (f.l(this.f3450c) == 1) {
            j();
        } else {
            k();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_end_and_accept_btn /* 2131296487 */:
                f();
                break;
            case R.id.dialog_reject_btn /* 2131296488 */:
                d();
                break;
        }
    }
}
