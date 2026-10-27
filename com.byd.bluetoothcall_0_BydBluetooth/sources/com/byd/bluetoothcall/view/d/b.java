package com.byd.bluetoothcall.view.d;

import android.content.Context;
import android.content.res.Configuration;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.bluetoothcall.f.f;
import com.bydux.api.R;
import ts.car.service.carplay.data.CallStateInfo;

/* JADX INFO: compiled from: Other_phone_selection_WM.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements View.OnClickListener {

    /* JADX INFO: renamed from: b */
    private static b f3445b;

    /* JADX INFO: renamed from: c */
    private transient Context f3446c;

    /* JADX INFO: renamed from: d */
    private transient View f3447d;

    /* JADX INFO: renamed from: e */
    private transient int f3448e = 1024;
    private transient boolean f = false;
    private transient int g = 0;
    private transient Object h;
    private transient TextView i;
    private transient TextView j;
    private transient LinearLayout k;
    private transient View l;
    private transient TextView m;
    private transient TextView n;
    private transient RelativeLayout o;

    private b(Context context) {
        d(context);
    }

    private void c(View view) {
        this.o = (RelativeLayout) view.findViewById(R.id.phone_type_corver);
        this.i = (TextView) view.findViewById(R.id.phone_type_text);
        this.j = (TextView) view.findViewById(R.id.phone_name_text);
        TextView textView = (TextView) view.findViewById(R.id.dialog_end_and_accept_btn);
        this.m = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) view.findViewById(R.id.dialog_reject_btn);
        this.n = textView2;
        textView2.setOnClickListener(this);
        this.k = (LinearLayout) view.findViewById(R.id.bt_dialog_bg);
        this.l = view.findViewById(R.id.bt_dialog_line);
    }

    private void d(Context context) {
        this.f3446c = context;
    }

    public static b g(Context context) {
        if (f3445b == null) {
            f3445b = new b(context.getApplicationContext());
        }
        return f3445b;
    }

    private void k() {
        RelativeLayout relativeLayout = this.o;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundColor(this.f3446c.getColor(R.color.ts_popover_translucent_background));
        }
        TextView textView = this.i;
        if (textView != null) {
            textView.setTextColor(this.f3446c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView2 = this.j;
        if (textView2 != null) {
            textView2.setTextColor(this.f3446c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView3 = this.m;
        if (textView3 != null) {
            textView3.setTextColor(this.f3446c.getColor(R.color.ts_ff_white_color));
            this.m.setBackgroundDrawable(this.f3446c.getDrawable(R.drawable.denza_dialog_btn_p_shape));
        }
        TextView textView4 = this.n;
        if (textView4 != null) {
            textView4.setTextColor(this.f3446c.getColor(R.color.ts_d9_text_color));
            this.n.setBackgroundDrawable(this.f3446c.getDrawable(R.drawable.denza_dialog_btn_n_shape));
        }
        LinearLayout linearLayout = this.k;
        if (linearLayout != null) {
            linearLayout.setBackgroundDrawable(this.f3446c.getDrawable(R.drawable.denza_contacts_list_wm_bg));
        }
        View view = this.l;
        if (view != null) {
            view.setBackgroundColor(this.f3446c.getColor(R.color.ts_list_line_1));
        }
    }

    private void l() {
        RelativeLayout relativeLayout = this.o;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundColor(this.f3446c.getColor(R.color.popover_translucent_background));
        }
        TextView textView = this.i;
        if (textView != null) {
            textView.setTextColor(this.f3446c.getColor(R.color.ff_text_color));
        }
        TextView textView2 = this.j;
        if (textView2 != null) {
            textView2.setTextColor(this.f3446c.getColor(R.color.ff_text_color));
        }
        TextView textView3 = this.m;
        if (textView3 != null) {
            textView3.setTextColor(this.f3446c.getColor(R.color.ff_white_color));
            this.m.setBackgroundDrawable(this.f3446c.getDrawable(R.drawable.dialog_btn_p_shape));
        }
        TextView textView4 = this.n;
        if (textView4 != null) {
            textView4.setTextColor(this.f3446c.getColor(R.color.d9_text_color));
            this.n.setBackgroundDrawable(this.f3446c.getDrawable(R.drawable.dialog_btn_n_shape));
        }
        LinearLayout linearLayout = this.k;
        if (linearLayout != null) {
            linearLayout.setBackgroundDrawable(this.f3446c.getDrawable(R.drawable.contacts_list_wm_bg));
        }
        View view = this.l;
        if (view != null) {
            view.setBackgroundColor(this.f3446c.getColor(R.color.list_line_1));
        }
    }

    public void a() {
        if (this.f3447d != null) {
            ((WindowManager) this.f3446c.getSystemService("window")).removeViewImmediate(this.f3447d);
            this.f3447d = null;
            this.f = false;
        }
    }

    public void b() {
        com.byd.bluetoothcall.c.a.F().v0(true);
        int i = this.g;
        if (i != 1 || !(this.h instanceof com.byd.bluetoothcall.view.a)) {
            if (i == 2 && (this.h instanceof CallStateInfo)) {
                c.a.b.b.f0().v0();
                c.a.b.b.f0().G0();
                return;
            }
            return;
        }
        for (int i2 = 0; i2 < com.byd.bluetoothcall.c.a.F().Q().size(); i2++) {
            try {
                com.byd.bluetoothcall.c.a.F().C().endCall(0, com.byd.bluetoothcall.c.a.F().Q().get(i2).getCallUuid());
            } catch (RemoteException e2) {
                Log.d("DANDAN", "e = " + e2.toString());
                return;
            }
        }
    }

    public void e() {
        int i = this.g;
        if (i == 1 && (this.h instanceof com.byd.bluetoothcall.view.a)) {
            c.a.b.b.f0().v0();
            c.a.b.b.f0().G0();
        } else if (i == 2 && (this.h instanceof CallStateInfo)) {
            try {
                com.byd.bluetoothcall.c.a.F().C().endCall(0, ((CallStateInfo) this.h).getCallUuid());
            } catch (RemoteException e2) {
                Log.d("DANDAN", "e = " + e2.toString());
            }
        }
        a();
    }

    public void f() {
        if (f.l(this.f3446c) == 1) {
            this.f3446c.setTheme(R.style.ThemeTS);
        } else {
            this.f3446c.setTheme(R.style.ThemeTang);
        }
        if (this.f) {
            return;
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2008, 8, -2);
        WindowManager windowManager = (WindowManager) this.f3446c.getSystemService("window");
        if (this.f3447d == null) {
            View viewInflate = ((LayoutInflater) this.f3446c.getSystemService("layout_inflater")).inflate(R.layout.other_phone_selection_wm, (ViewGroup) null);
            this.f3447d = viewInflate;
            viewInflate.setSystemUiVisibility(this.f3448e);
            c(this.f3447d);
        }
        windowManager.addView(this.f3447d, layoutParams);
        this.f = true;
    }

    public void h(int i, String str, Object obj) {
        this.g = i;
        this.h = obj;
        TextView textView = this.i;
        if (textView != null) {
            textView.setText(str);
        }
        TextView textView2 = this.j;
        if (textView2 != null) {
            Object obj2 = this.h;
            if (obj2 instanceof CallStateInfo) {
                textView2.setText(((CallStateInfo) obj2).getDisplayName());
            } else if (obj2 instanceof com.byd.bluetoothcall.view.a) {
                textView2.setText(((com.byd.bluetoothcall.view.a) obj2).a());
            }
        }
    }

    public boolean i() {
        return this.f;
    }

    public void j(Configuration configuration) {
        if (f.l(this.f3446c) == 1) {
            k();
        } else {
            l();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_end_and_accept_btn /* 2131296487 */:
                b();
                break;
            case R.id.dialog_reject_btn /* 2131296488 */:
                e();
                break;
        }
    }
}
