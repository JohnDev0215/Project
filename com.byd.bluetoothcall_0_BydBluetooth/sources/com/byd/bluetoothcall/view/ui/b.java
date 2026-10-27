package com.byd.bluetoothcall.view.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.bluetoothcall.f.e;
import com.byd.bluetoothcall.f.f;
import com.byd.bluetoothcall.view.ui.MyCallView;
import com.byd.constant.StateConstant$SCO_STATE;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: compiled from: BT_Call_View.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements View.OnClickListener {

    /* JADX INFO: renamed from: b */
    public static b f3485b;
    private transient TextView A;
    private transient TextView A0;
    private transient TextView B;
    private transient TextView B0;
    private transient View C;
    private transient TextView C0;
    private transient View D;
    private transient TextView D0;
    private transient ImageView E;
    private transient TextView E0;
    private transient ImageView F;
    private transient TextView F0;
    private transient ImageView G;
    private transient TextView G0;
    private transient TextView H;
    private transient TextView H0;
    private transient TextView I;
    private transient TextView I0;
    private transient EditText J;
    private transient LinearLayout K;
    private transient LottieAnimationView L;
    private transient WindowManager L0;
    private transient ImageView M;
    private transient MyCallView M0;
    private transient TextView N;
    private transient WindowManager.LayoutParams N0;
    private transient TextView O;
    private transient View O0;
    private transient ImageView P;
    private transient View P0;
    private transient ImageView Q;
    private transient View Q0;
    private transient TextView R;
    private transient View R0;
    private transient TextView S;
    private transient View S0;
    private transient TextView T;
    private transient TextView U;
    private transient TextView V;
    private transient TextView W;
    private transient TextView X;
    private transient TextView Y;
    private transient TextView Z;
    private transient TextView a0;
    private transient TextView b0;

    /* JADX INFO: renamed from: c */
    private transient Context f3486c;
    private transient TextView c0;

    /* JADX INFO: renamed from: d */
    private transient Editable f3487d;
    private transient TextView d0;

    /* JADX INFO: renamed from: e */
    private transient int f3488e;
    private transient TextView e0;
    private transient TextView f0;
    private transient TextView g0;
    private transient Editable h;
    private transient TextView h0;
    private transient int i;
    private transient TextView i0;
    private transient TextView j0;
    private transient TextView k0;
    private transient TextView l0;
    private transient RelativeLayout m;
    private transient TextView m0;
    private transient RelativeLayout.LayoutParams n;
    private transient TextView n0;
    private transient ImageView o;
    private transient TextView o0;
    private transient MyCallView p;
    private transient TextView p0;
    private transient LinearLayout q;
    private transient TextView q0;
    private transient EditText r;
    private transient TextView r0;
    private transient ImageView s;
    private transient TextView s0;
    private transient View t;
    private transient TextView t0;
    private transient View u;
    private transient TextView u0;
    private transient TextView v;
    private transient TextView v0;
    private transient TextView w;
    private transient TextView w0;
    private transient TextView x;
    private transient TextView x0;
    private transient View y;
    private transient TextView y0;
    private transient View z;
    private transient TextView z0;
    private transient boolean f = false;
    private transient boolean g = false;
    private transient boolean j = false;
    private transient String k = "";
    private transient String l = "";
    private transient TextView[] J0 = new TextView[24];
    private transient TextView[] K0 = new TextView[18];
    private transient float T0 = 0.0f;
    private transient float U0 = 0.0f;

    /* JADX INFO: compiled from: BT_Call_View.java */
    class a implements MyCallView.a {
        a() {
        }

        @Override // com.byd.bluetoothcall.view.ui.MyCallView.a
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() != 4 || keyEvent.getAction() == 1) {
                return false;
            }
            BydLogHelper.logD("DANDAN", "KEYCODE_BACK: ");
            if (b.this.o()) {
                c.l(b.this.f3486c).t();
                b.this.m();
            }
            return true;
        }
    }

    /* JADX INFO: renamed from: com.byd.bluetoothcall.view.ui.b$b */
    /* JADX INFO: compiled from: BT_Call_View.java */
    class ViewOnTouchListenerC0088b implements View.OnTouchListener {
        ViewOnTouchListenerC0088b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                b.this.T0 = motionEvent.getX();
                b.this.U0 = motionEvent.getY();
                return false;
            }
            if (motionEvent.getAction() != 2 || b.this.U0 - motionEvent.getY() <= 200.0f) {
                return false;
            }
            c.l(b.this.f3486c).t();
            b.this.m();
            return false;
        }
    }

    private b(Context context) {
        this.f3486c = context;
        i();
    }

    private void h() {
        WindowManager.LayoutParams layoutParams;
        MyCallView myCallView = this.M0;
        if (myCallView == null || (layoutParams = this.N0) == null) {
            return;
        }
        try {
            this.L0.addView(myCallView, layoutParams);
            this.g = true;
        } catch (Exception unused) {
        }
    }

    private void i() {
        if (f.l(this.f3486c) == 1) {
            this.f3486c.setTheme(R.style.ThemeTS);
        } else {
            this.f3486c.setTheme(R.style.ThemeTang);
        }
        if (this.M0 == null) {
            if (f.k() == 1) {
                this.M0 = (MyCallView) LayoutInflater.from(this.f3486c).inflate(R.layout.right_driver_bt_call_view, (ViewGroup) null);
            } else {
                this.M0 = (MyCallView) LayoutInflater.from(this.f3486c).inflate(R.layout.bt_call_view, (ViewGroup) null);
            }
            this.M0.setDispatchKeyEventListener(new a());
        }
        k(this.M0);
        this.L0 = (WindowManager) this.f3486c.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.N0 = layoutParams;
        layoutParams.flags = 263424;
        layoutParams.format = -3;
        layoutParams.type = 2008;
        layoutParams.width = -1;
        layoutParams.height = -1;
    }

    private void k(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.bt_call_wm_layout);
        this.m = relativeLayout;
        relativeLayout.setOnTouchListener(new ViewOnTouchListenerC0088b());
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.m.getLayoutParams();
        this.n = layoutParams;
        layoutParams.addRule(13);
        MyCallView myCallView = (MyCallView) view.findViewById(R.id.bt_call_view_click);
        this.p = myCallView;
        myCallView.setOnClickListener(this);
        this.q = (LinearLayout) view.findViewById(R.id.call_ing_keyboard_layout_left);
        this.r = (EditText) view.findViewById(R.id.call_ing_keyboard_input_text_left);
        this.t = view.findViewById(R.id.out_dimens_32);
        this.u = view.findViewById(R.id.out_dimens_30);
        View viewFindViewById = view.findViewById(R.id.bt_call_out_layout);
        this.R0 = viewFindViewById;
        viewFindViewById.findViewById(R.id.call_out_hugup_btn).setOnClickListener(this);
        ImageView imageView = (ImageView) this.R0.findViewById(R.id.call_out_changevoice_btn);
        this.s = imageView;
        imageView.setOnClickListener(this);
        this.v = (TextView) this.R0.findViewById(R.id.call_out_name_text);
        this.x = (TextView) this.R0.findViewById(R.id.dialing_text);
        this.w = (TextView) this.R0.findViewById(R.id.call_out_location_text);
        this.y = view.findViewById(R.id.in_dimens_32);
        this.z = view.findViewById(R.id.in_dimens_34);
        View viewFindViewById2 = view.findViewById(R.id.bt_call_in_layout);
        this.P0 = viewFindViewById2;
        viewFindViewById2.findViewById(R.id.call_in_hugup_btn).setOnClickListener(this);
        this.P0.findViewById(R.id.call_in_anser_btn).setOnClickListener(this);
        this.A = (TextView) this.P0.findViewById(R.id.call_in_name_text);
        this.B = (TextView) this.P0.findViewById(R.id.call_in_location_text);
        this.C = view.findViewById(R.id.ing_dimens_26);
        this.D = view.findViewById(R.id.ing_dimens_32);
        View viewFindViewById3 = view.findViewById(R.id.bt_call_ing_layout);
        this.Q0 = viewFindViewById3;
        ImageView imageView2 = (ImageView) viewFindViewById3.findViewById(R.id.call_ing_keyboard_btn);
        this.E = imageView2;
        imageView2.setOnClickListener(this);
        ImageView imageView3 = (ImageView) this.Q0.findViewById(R.id.call_ing_changevoice_btn);
        this.F = imageView3;
        imageView3.setOnClickListener(this);
        ImageView imageView4 = (ImageView) this.Q0.findViewById(R.id.call_ing_micswitch_btn);
        this.G = imageView4;
        imageView4.setOnClickListener(this);
        this.Q0.findViewById(R.id.call_ing_hugup_btn).setOnClickListener(this);
        this.H = (TextView) this.Q0.findViewById(R.id.call_ing_name_text);
        this.I = (TextView) this.Q0.findViewById(R.id.call_ing_time_text);
        this.J = (EditText) this.Q0.findViewById(R.id.call_ing_keyboard_input_text);
        this.K = (LinearLayout) this.Q0.findViewById(R.id.call_ing_keyboard_layout);
        View viewFindViewById4 = view.findViewById(R.id.network_call_layout);
        this.S0 = viewFindViewById4;
        ImageView imageView5 = (ImageView) viewFindViewById4.findViewById(R.id.network_call_changevoice_btn);
        this.P = imageView5;
        imageView5.setOnClickListener(this);
        ImageView imageView6 = (ImageView) this.S0.findViewById(R.id.network_call_micswitch_btn);
        this.Q = imageView6;
        imageView6.setOnClickListener(this);
        this.R = (TextView) this.S0.findViewById(R.id.network_call_name_text);
        this.S = (TextView) this.S0.findViewById(R.id.network_call_time_text);
        View viewFindViewById5 = view.findViewById(R.id.third_call_layout);
        this.O0 = viewFindViewById5;
        this.L = (LottieAnimationView) viewFindViewById5.findViewById(R.id.switch_call_image);
        if (com.byd.bluetoothcall.c.a.F().K()) {
            LottieAnimationView lottieAnimationView = this.L;
            if (lottieAnimationView != null) {
                lottieAnimationView.setAnimation("icon_toggle.json");
            }
        } else {
            LottieAnimationView lottieAnimationView2 = this.L;
            if (lottieAnimationView2 != null) {
                lottieAnimationView2.setAnimation("icon_toggle_light.json");
            }
        }
        this.L.setOnClickListener(this);
        ImageView imageView7 = (ImageView) this.O0.findViewById(R.id.hugup_third_image);
        this.M = imageView7;
        imageView7.setOnClickListener(this);
        this.N = (TextView) this.O0.findViewById(R.id.third_call_name);
        this.O = (TextView) this.O0.findViewById(R.id.third_call_time);
        TextView textView = (TextView) view.findViewById(R.id.keyboard_number_0_text);
        this.x0 = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) view.findViewById(R.id.keyboard_number_1_text);
        this.o0 = textView2;
        textView2.setOnClickListener(this);
        TextView textView3 = (TextView) view.findViewById(R.id.keyboard_number_2_text);
        this.p0 = textView3;
        textView3.setOnClickListener(this);
        TextView textView4 = (TextView) view.findViewById(R.id.keyboard_number_3_text);
        this.q0 = textView4;
        textView4.setOnClickListener(this);
        TextView textView5 = (TextView) view.findViewById(R.id.keyboard_number_4_text);
        this.r0 = textView5;
        textView5.setOnClickListener(this);
        TextView textView6 = (TextView) view.findViewById(R.id.keyboard_number_5_text);
        this.s0 = textView6;
        textView6.setOnClickListener(this);
        TextView textView7 = (TextView) view.findViewById(R.id.keyboard_number_6_text);
        this.t0 = textView7;
        textView7.setOnClickListener(this);
        TextView textView8 = (TextView) view.findViewById(R.id.keyboard_number_7_text);
        this.u0 = textView8;
        textView8.setOnClickListener(this);
        TextView textView9 = (TextView) view.findViewById(R.id.keyboard_number_8_text);
        this.v0 = textView9;
        textView9.setOnClickListener(this);
        TextView textView10 = (TextView) view.findViewById(R.id.keyboard_number_9_text);
        this.w0 = textView10;
        textView10.setOnClickListener(this);
        TextView textView11 = (TextView) view.findViewById(R.id.keyboard_number_x_text);
        this.y0 = textView11;
        textView11.setOnClickListener(this);
        TextView textView12 = (TextView) view.findViewById(R.id.keyboard_number_j_text);
        this.z0 = textView12;
        textView12.setOnClickListener(this);
        this.A0 = (TextView) view.findViewById(R.id.keyboard_abc_text);
        this.B0 = (TextView) view.findViewById(R.id.keyboard_def_text);
        this.C0 = (TextView) view.findViewById(R.id.keyboard_ghi_text);
        this.D0 = (TextView) view.findViewById(R.id.keyboard_jkl_text);
        this.E0 = (TextView) view.findViewById(R.id.keyboard_mno_text);
        this.F0 = (TextView) view.findViewById(R.id.keyboard_pqrs_text);
        this.G0 = (TextView) view.findViewById(R.id.keyboard_tuv_text);
        this.H0 = (TextView) view.findViewById(R.id.keyboard_wxyz_text);
        this.I0 = (TextView) view.findViewById(R.id.keyboard_jia_text);
        TextView textView13 = (TextView) this.Q0.findViewById(R.id.keyboard_number_0_text);
        this.c0 = textView13;
        textView13.setOnClickListener(this);
        TextView textView14 = (TextView) this.Q0.findViewById(R.id.keyboard_number_1_text);
        this.T = textView14;
        textView14.setOnClickListener(this);
        TextView textView15 = (TextView) this.Q0.findViewById(R.id.keyboard_number_2_text);
        this.U = textView15;
        textView15.setOnClickListener(this);
        TextView textView16 = (TextView) this.Q0.findViewById(R.id.keyboard_number_3_text);
        this.V = textView16;
        textView16.setOnClickListener(this);
        TextView textView17 = (TextView) this.Q0.findViewById(R.id.keyboard_number_4_text);
        this.W = textView17;
        textView17.setOnClickListener(this);
        TextView textView18 = (TextView) this.Q0.findViewById(R.id.keyboard_number_5_text);
        this.X = textView18;
        textView18.setOnClickListener(this);
        TextView textView19 = (TextView) this.Q0.findViewById(R.id.keyboard_number_6_text);
        this.Y = textView19;
        textView19.setOnClickListener(this);
        TextView textView20 = (TextView) this.Q0.findViewById(R.id.keyboard_number_7_text);
        this.Z = textView20;
        textView20.setOnClickListener(this);
        TextView textView21 = (TextView) this.Q0.findViewById(R.id.keyboard_number_8_text);
        this.a0 = textView21;
        textView21.setOnClickListener(this);
        TextView textView22 = (TextView) this.Q0.findViewById(R.id.keyboard_number_9_text);
        this.b0 = textView22;
        textView22.setOnClickListener(this);
        TextView textView23 = (TextView) this.Q0.findViewById(R.id.keyboard_number_x_text);
        this.d0 = textView23;
        textView23.setOnClickListener(this);
        TextView textView24 = (TextView) this.Q0.findViewById(R.id.keyboard_number_j_text);
        this.e0 = textView24;
        textView24.setOnClickListener(this);
        this.f0 = (TextView) this.Q0.findViewById(R.id.keyboard_abc_text);
        this.g0 = (TextView) this.Q0.findViewById(R.id.keyboard_def_text);
        this.h0 = (TextView) this.Q0.findViewById(R.id.keyboard_ghi_text);
        this.i0 = (TextView) this.Q0.findViewById(R.id.keyboard_jkl_text);
        this.j0 = (TextView) this.Q0.findViewById(R.id.keyboard_mno_text);
        this.k0 = (TextView) this.Q0.findViewById(R.id.keyboard_pqrs_text);
        this.l0 = (TextView) this.Q0.findViewById(R.id.keyboard_tuv_text);
        this.m0 = (TextView) this.Q0.findViewById(R.id.keyboard_wxyz_text);
        TextView textView25 = (TextView) this.Q0.findViewById(R.id.keyboard_jia_text);
        this.n0 = textView25;
        TextView[] textViewArr = this.J0;
        textViewArr[0] = this.x0;
        textViewArr[1] = this.o0;
        textViewArr[2] = this.p0;
        textViewArr[3] = this.q0;
        textViewArr[4] = this.r0;
        textViewArr[5] = this.s0;
        textViewArr[6] = this.t0;
        textViewArr[7] = this.u0;
        textViewArr[8] = this.v0;
        textViewArr[9] = this.w0;
        textViewArr[10] = this.y0;
        textViewArr[11] = this.z0;
        textViewArr[12] = this.c0;
        textViewArr[13] = this.T;
        textViewArr[14] = this.U;
        textViewArr[15] = this.V;
        textViewArr[16] = this.W;
        textViewArr[17] = this.X;
        textViewArr[18] = this.Y;
        textViewArr[19] = this.Z;
        textViewArr[20] = this.a0;
        textViewArr[21] = this.b0;
        textViewArr[22] = this.d0;
        textViewArr[23] = this.e0;
        TextView[] textViewArr2 = this.K0;
        textViewArr2[0] = this.A0;
        textViewArr2[1] = this.B0;
        textViewArr2[2] = this.C0;
        textViewArr2[3] = this.D0;
        textViewArr2[4] = this.E0;
        textViewArr2[5] = this.F0;
        textViewArr2[6] = this.G0;
        textViewArr2[7] = this.H0;
        textViewArr2[8] = this.I0;
        textViewArr2[9] = this.f0;
        textViewArr2[10] = this.g0;
        textViewArr2[11] = this.h0;
        textViewArr2[12] = this.i0;
        textViewArr2[13] = this.j0;
        textViewArr2[14] = this.k0;
        textViewArr2[15] = this.l0;
        textViewArr2[16] = this.m0;
        textViewArr2[17] = textView25;
        ImageView imageView8 = (ImageView) view.findViewById(R.id.bt_call_small_icon);
        this.o = imageView8;
        imageView8.setOnClickListener(this);
    }

    public static b l(Context context) {
        synchronized (b.class) {
            if (f3485b == null) {
                f3485b = new b(context.getApplicationContext());
            }
        }
        return f3485b;
    }

    private void n() {
        b(c.a.b.b.f0().b0());
    }

    private void p(String str) {
        this.f3488e = this.J.getSelectionStart();
        Editable text = this.J.getText();
        this.f3487d = text;
        text.insert(this.f3488e, str);
        this.i = this.r.getSelectionStart();
        Editable text2 = this.r.getText();
        this.h = text2;
        text2.insert(this.i, str);
        c.a.b.b.f0().k(str);
    }

    private void s() {
        this.n.width = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_500);
        this.n.height = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_344);
        this.m.setLayoutParams(this.n);
        if (this.K.getVisibility() == 0) {
            this.K.setVisibility(8);
        }
        if (this.q.getVisibility() == 0) {
            this.q.setVisibility(8);
        }
        this.E.setSelected(false);
        TextView textView = this.B;
        if (textView != null) {
            textView.setText("");
        }
        TextView textView2 = this.I;
        if (textView2 != null) {
            textView2.setText("");
        }
        TextView textView3 = this.S;
        if (textView3 != null) {
            textView3.setText("");
        }
    }

    private void x() {
        for (int i = 0; i < 24; i++) {
            TextView[] textViewArr = this.J0;
            if (textViewArr[i] != null) {
                textViewArr[i].setBackground(this.f3486c.getDrawable(R.drawable.denza_contacts_info_list_bg));
                this.J0[i].setTextColor(this.f3486c.getColor(R.color.ts_ff_text_color));
            }
        }
        for (int i2 = 0; i2 < 18; i2++) {
            TextView[] textViewArr2 = this.K0;
            if (textViewArr2[i2] != null) {
                textViewArr2[i2].setTextColor(this.f3486c.getColor(R.color.ts_b3_text_color));
            }
        }
        MyCallView myCallView = this.p;
        if (myCallView != null) {
            myCallView.setBackgroundColor(this.f3486c.getColor(R.color.ts_popover_translucent_background));
        }
        RelativeLayout relativeLayout = this.m;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundDrawable(this.f3486c.getDrawable(R.drawable.denza_bt_dialog_bg));
        }
        ImageView imageView = this.o;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.denza_bt_call_small_icon);
        }
        EditText editText = this.r;
        if (editText != null) {
            editText.setTextColor(this.f3486c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView = this.N;
        if (textView != null) {
            textView.setTextColor(this.f3486c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView2 = this.O;
        if (textView2 != null) {
            textView2.setTextColor(this.f3486c.getColor(R.color.ts_b3_text_color));
        }
        if (com.byd.bluetoothcall.c.a.F().K()) {
            LottieAnimationView lottieAnimationView = this.L;
            if (lottieAnimationView != null) {
                if (lottieAnimationView.m()) {
                    this.L.g();
                    this.L.setAnimation("icon_toggle.json");
                    this.L.o();
                } else {
                    this.L.setAnimation("icon_toggle.json");
                }
            }
        } else {
            LottieAnimationView lottieAnimationView2 = this.L;
            if (lottieAnimationView2 != null) {
                if (lottieAnimationView2.m()) {
                    this.L.g();
                    this.L.setAnimation("icon_toggle_light.json");
                    this.L.o();
                } else {
                    this.L.setAnimation("icon_toggle_light.json");
                }
            }
        }
        TextView textView3 = this.v;
        if (textView3 != null) {
            textView3.setTextColor(this.f3486c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView4 = this.w;
        if (textView4 != null) {
            textView4.setTextColor(this.f3486c.getColor(R.color.ts_b3_text_color));
        }
        TextView textView5 = this.x;
        if (textView5 != null) {
            textView5.setTextColor(this.f3486c.getColor(R.color.ts_b3_text_color));
            if (this.x.getText().toString().equals(this.f3486c.getString(R.string.end_call_text))) {
                this.x.setText(R.string.end_call_text);
            } else {
                this.x.setText(R.string.call_out_text);
            }
        }
        ImageView imageView2 = this.s;
        if (imageView2 != null) {
            imageView2.setImageResource(R.drawable.denza_bt_calling_voice2phone_btn);
        }
        TextView textView6 = this.A;
        if (textView6 != null) {
            textView6.setTextColor(this.f3486c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView7 = this.B;
        if (textView7 != null) {
            textView7.setTextColor(this.f3486c.getColor(R.color.ts_b3_text_color));
        }
        TextView textView8 = this.H;
        if (textView8 != null) {
            textView8.setTextColor(this.f3486c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView9 = this.I;
        if (textView9 != null) {
            textView9.setTextColor(this.f3486c.getColor(R.color.ts_b3_text_color));
        }
        ImageView imageView3 = this.E;
        if (imageView3 != null) {
            imageView3.setImageResource(R.drawable.denza_bt_calling_show_keyboard_btn);
        }
        ImageView imageView4 = this.F;
        if (imageView4 != null) {
            imageView4.setImageResource(R.drawable.denza_bt_calling_voice2phone_btn);
        }
        ImageView imageView5 = this.G;
        if (imageView5 != null) {
            imageView5.setImageResource(R.drawable.denza_bt_calling_mic_mute_btn);
        }
        ImageView imageView6 = this.Q;
        if (imageView6 != null) {
            imageView6.setImageResource(R.drawable.denza_bt_calling_mic_mute_btn);
        }
        ImageView imageView7 = this.P;
        if (imageView7 != null) {
            imageView7.setImageResource(R.drawable.denza_bt_calling_voice2phone_btn);
        }
        TextView textView10 = this.R;
        if (textView10 != null) {
            textView10.setTextColor(this.f3486c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView11 = this.S;
        if (textView11 != null) {
            textView11.setTextColor(this.f3486c.getColor(R.color.ts_b3_text_color));
        }
    }

    private void y() {
        for (int i = 0; i < 24; i++) {
            TextView[] textViewArr = this.J0;
            if (textViewArr[i] != null) {
                textViewArr[i].setBackground(this.f3486c.getDrawable(R.drawable.contacts_info_list_bg));
                this.J0[i].setTextColor(this.f3486c.getColor(R.color.ff_text_color));
            }
        }
        for (int i2 = 0; i2 < 18; i2++) {
            TextView[] textViewArr2 = this.K0;
            if (textViewArr2[i2] != null) {
                textViewArr2[i2].setTextColor(this.f3486c.getColor(R.color.b3_text_color));
            }
        }
        MyCallView myCallView = this.p;
        if (myCallView != null) {
            myCallView.setBackgroundColor(this.f3486c.getColor(R.color.popover_translucent_background));
        }
        RelativeLayout relativeLayout = this.m;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundDrawable(this.f3486c.getDrawable(R.drawable.bt_dialog_bg));
        }
        ImageView imageView = this.o;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.bt_call_small_icon);
        }
        EditText editText = this.r;
        if (editText != null) {
            editText.setTextColor(this.f3486c.getColor(R.color.ff_text_color));
        }
        TextView textView = this.N;
        if (textView != null) {
            textView.setTextColor(this.f3486c.getColor(R.color.ff_text_color));
        }
        TextView textView2 = this.O;
        if (textView2 != null) {
            textView2.setTextColor(this.f3486c.getColor(R.color.b3_text_color));
        }
        if (com.byd.bluetoothcall.c.a.F().K()) {
            LottieAnimationView lottieAnimationView = this.L;
            if (lottieAnimationView != null) {
                if (lottieAnimationView.m()) {
                    this.L.g();
                    this.L.setAnimation("icon_toggle.json");
                    this.L.o();
                } else {
                    this.L.setAnimation("icon_toggle.json");
                }
            }
        } else {
            LottieAnimationView lottieAnimationView2 = this.L;
            if (lottieAnimationView2 != null) {
                if (lottieAnimationView2.m()) {
                    this.L.g();
                    this.L.setAnimation("icon_toggle_light.json");
                    this.L.o();
                } else {
                    this.L.setAnimation("icon_toggle_light.json");
                }
            }
        }
        TextView textView3 = this.v;
        if (textView3 != null) {
            textView3.setTextColor(this.f3486c.getColor(R.color.ff_text_color));
        }
        TextView textView4 = this.w;
        if (textView4 != null) {
            textView4.setTextColor(this.f3486c.getColor(R.color.b3_text_color));
        }
        TextView textView5 = this.x;
        if (textView5 != null) {
            textView5.setTextColor(this.f3486c.getColor(R.color.b3_text_color));
            if (this.x.getText().toString().equals(this.f3486c.getString(R.string.end_call_text))) {
                this.x.setText(R.string.end_call_text);
            } else {
                this.x.setText(R.string.call_out_text);
            }
        }
        ImageView imageView2 = this.s;
        if (imageView2 != null) {
            imageView2.setImageResource(R.drawable.bt_calling_voice2phone_btn);
        }
        TextView textView6 = this.A;
        if (textView6 != null) {
            textView6.setTextColor(this.f3486c.getColor(R.color.ff_text_color));
        }
        TextView textView7 = this.B;
        if (textView7 != null) {
            textView7.setTextColor(this.f3486c.getColor(R.color.b3_text_color));
        }
        TextView textView8 = this.H;
        if (textView8 != null) {
            textView8.setTextColor(this.f3486c.getColor(R.color.ff_text_color));
        }
        TextView textView9 = this.I;
        if (textView9 != null) {
            textView9.setTextColor(this.f3486c.getColor(R.color.b3_text_color));
        }
        ImageView imageView3 = this.E;
        if (imageView3 != null) {
            imageView3.setImageResource(R.drawable.bt_calling_show_keyboard_btn);
        }
        ImageView imageView4 = this.F;
        if (imageView4 != null) {
            imageView4.setImageResource(R.drawable.bt_calling_voice2phone_btn);
        }
        ImageView imageView5 = this.G;
        if (imageView5 != null) {
            imageView5.setImageResource(R.drawable.bt_calling_mic_mute_btn);
        }
        ImageView imageView6 = this.Q;
        if (imageView6 != null) {
            imageView6.setImageResource(R.drawable.bt_calling_mic_mute_btn);
        }
        ImageView imageView7 = this.P;
        if (imageView7 != null) {
            imageView7.setImageResource(R.drawable.bt_calling_voice2phone_btn);
        }
        TextView textView10 = this.R;
        if (textView10 != null) {
            textView10.setTextColor(this.f3486c.getColor(R.color.ff_text_color));
        }
        TextView textView11 = this.S;
        if (textView11 != null) {
            textView11.setTextColor(this.f3486c.getColor(R.color.b3_text_color));
        }
    }

    public void A(String str) {
        TextView textView = this.O;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void a(String str) {
        if (!this.j || str.equals(this.N.getText().toString())) {
            TextView textView = this.N;
            if (textView != null) {
                textView.setText(str);
                return;
            }
            return;
        }
        TextView textView2 = this.N;
        if (textView2 != null) {
            textView2.setText(str);
        }
    }

    public void b(StateConstant$SCO_STATE stateConstant$SCO_STATE) {
        BydLogHelper.logD("HZFR", "sco_state = " + stateConstant$SCO_STATE);
        if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_BT) {
            ImageView imageView = this.G;
            if (imageView != null) {
                imageView.setSelected(com.byd.bluetoothcall.c.a.F().G());
                this.G.setEnabled(true);
            }
            ImageView imageView2 = this.Q;
            if (imageView2 != null) {
                imageView2.setSelected(com.byd.bluetoothcall.c.a.F().G());
                this.Q.setEnabled(true);
            }
            ImageView imageView3 = this.P;
            if (imageView3 != null) {
                imageView3.setSelected(false);
            }
            ImageView imageView4 = this.s;
            if (imageView4 != null) {
                imageView4.setSelected(false);
            }
            ImageView imageView5 = this.F;
            if (imageView5 != null) {
                imageView5.setSelected(false);
                return;
            }
            return;
        }
        if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_PHONE) {
            ImageView imageView6 = this.G;
            if (imageView6 != null) {
                imageView6.setSelected(false);
                this.G.setEnabled(false);
            }
            ImageView imageView7 = this.Q;
            if (imageView7 != null) {
                imageView7.setSelected(false);
                this.Q.setEnabled(false);
            }
            ImageView imageView8 = this.P;
            if (imageView8 != null) {
                imageView8.setSelected(true);
            }
            ImageView imageView9 = this.s;
            if (imageView9 != null) {
                imageView9.setSelected(true);
            }
            ImageView imageView10 = this.F;
            if (imageView10 != null) {
                imageView10.setSelected(true);
            }
        }
    }

    public void c(boolean z) {
        this.j = z;
        View view = this.O0;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
        this.t.setVisibility(z ? 8 : 0);
        this.u.setVisibility(z ? 8 : 0);
        this.y.setVisibility(z ? 8 : 0);
        this.z.setVisibility(z ? 8 : 0);
        this.C.setVisibility(z ? 8 : 0);
        this.D.setVisibility(z ? 8 : 0);
    }

    public void j() {
        TextView textView = this.B;
        if (textView != null) {
            textView.setText(R.string.end_call_text);
        }
        TextView textView2 = this.x;
        if (textView2 != null) {
            textView2.setText(R.string.end_call_text);
        }
        TextView textView3 = this.I;
        if (textView3 != null) {
            textView3.setText(R.string.end_call_text);
        }
        EditText editText = this.J;
        if (editText != null) {
            editText.setText("");
        }
        EditText editText2 = this.r;
        if (editText2 != null) {
            editText2.setText("");
        }
    }

    public void m() {
        MyCallView myCallView;
        if (this.L0 == null || (myCallView = this.M0) == null || !myCallView.isShown()) {
            return;
        }
        this.M0.setVisibility(8);
        this.f = false;
        s();
    }

    public boolean o() {
        return this.f;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_call_small_icon /* 2131296364 */:
            case R.id.bt_call_view_click /* 2131296365 */:
                c.l(this.f3486c).t();
                m();
                break;
            case R.id.call_in_anser_btn /* 2131296391 */:
                if (!this.j) {
                    c.a.b.b.f0().O();
                } else {
                    c.a.b.b.f0().P();
                }
                break;
            case R.id.call_in_hugup_btn /* 2131296392 */:
                c.a.b.b.f0().G0();
                break;
            case R.id.call_ing_changevoice_btn /* 2131296395 */:
            case R.id.call_out_changevoice_btn /* 2131296411 */:
            case R.id.network_call_changevoice_btn /* 2131296680 */:
                if (TextUtils.isEmpty(c.a.e.b.a("ts.vendor.audioon", ""))) {
                    c.a.e.b.c("ts.vendor.audioon", "audio_on");
                }
                if (!f.o(800)) {
                    com.byd.bluetoothcall.c.a.F().a();
                } else {
                    Log.d("DANDAN", "CLICL TOO FAST");
                }
                break;
            case R.id.call_ing_hugup_btn /* 2131296396 */:
                Log.d("QWERT", "isExsitThidCall = " + this.j + " == mHugupNumber_DOWN = " + f.d(this.l));
                if (!this.j) {
                    c.a.b.b.f0().v0();
                } else {
                    c.a.b.b.f0().y0(this.l);
                }
                break;
            case R.id.call_ing_keyboard_btn /* 2131296397 */:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.m.getLayoutParams();
                layoutParams.addRule(13);
                if (com.byd.bluetoothcall.c.a.F().T() != 2) {
                    if (this.K.getVisibility() == 0) {
                        layoutParams.width = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_500);
                        layoutParams.height = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_344);
                        this.m.setLayoutParams(layoutParams);
                        this.K.setVisibility(8);
                        this.E.setSelected(false);
                    } else {
                        layoutParams.width = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_500);
                        layoutParams.height = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_628);
                        this.m.setLayoutParams(layoutParams);
                        this.K.setVisibility(0);
                        this.E.setSelected(true);
                    }
                } else if (this.q.getVisibility() == 0) {
                    layoutParams.width = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_500);
                    layoutParams.height = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_344);
                    this.m.setLayoutParams(layoutParams);
                    this.q.setVisibility(8);
                    this.E.setSelected(false);
                } else {
                    layoutParams.width = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_862);
                    layoutParams.height = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_344);
                    this.m.setLayoutParams(layoutParams);
                    this.q.setVisibility(0);
                    this.E.setSelected(true);
                }
                break;
            case R.id.call_ing_micswitch_btn /* 2131296402 */:
            case R.id.network_call_micswitch_btn /* 2131296682 */:
                com.byd.bluetoothcall.c.a.F().s0();
                break;
            case R.id.call_out_hugup_btn /* 2131296412 */:
                c.a.b.b.f0().v0();
                break;
            case R.id.hugup_third_image /* 2131296550 */:
                c.a.b.b.f0().y0(this.k);
                break;
            case R.id.keyboard_number_0_text /* 2131296586 */:
                p("0");
                break;
            case R.id.keyboard_number_1_text /* 2131296588 */:
                p("1");
                break;
            case R.id.keyboard_number_2_text /* 2131296590 */:
                p("2");
                break;
            case R.id.keyboard_number_3_text /* 2131296592 */:
                p("3");
                break;
            case R.id.keyboard_number_4_text /* 2131296594 */:
                p("4");
                break;
            case R.id.keyboard_number_5_text /* 2131296596 */:
                p("5");
                break;
            case R.id.keyboard_number_6_text /* 2131296598 */:
                p("6");
                break;
            case R.id.keyboard_number_7_text /* 2131296600 */:
                p("7");
                break;
            case R.id.keyboard_number_8_text /* 2131296602 */:
                p("8");
                break;
            case R.id.keyboard_number_9_text /* 2131296604 */:
                p("9");
                break;
            case R.id.keyboard_number_j_text /* 2131296606 */:
                p("#");
                break;
            case R.id.keyboard_number_x_text /* 2131296608 */:
                p("*");
                break;
            case R.id.switch_call_image /* 2131296829 */:
                if (!f.n(500)) {
                    LottieAnimationView lottieAnimationView = this.L;
                    if (lottieAnimationView != null) {
                        lottieAnimationView.o();
                    }
                    c.a.b.b.f0().z0();
                } else {
                    Log.d("DANDAN", "CLICL TOO FAST");
                }
                break;
        }
    }

    public void q(int i) {
        if (c.a.b.b.f0().b0() == StateConstant$SCO_STATE.CHANNEL_PHONE) {
            ImageView imageView = this.G;
            if (imageView != null) {
                imageView.setSelected(false);
                this.G.setEnabled(false);
            }
            ImageView imageView2 = this.Q;
            if (imageView2 != null) {
                imageView2.setSelected(false);
                this.Q.setEnabled(false);
                return;
            }
            return;
        }
        ImageView imageView3 = this.G;
        if (imageView3 != null) {
            imageView3.setEnabled(true);
            this.G.setSelected(i == 1);
        }
        ImageView imageView4 = this.Q;
        if (imageView4 != null) {
            imageView4.setEnabled(true);
            this.Q.setSelected(i == 1);
        }
    }

    public void r(Configuration configuration) {
        if (f.l(this.f3486c) == 1) {
            x();
        } else {
            y();
        }
        if (configuration.orientation == 2) {
            if (this.K.getVisibility() == 0) {
                this.n.width = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_862);
                this.n.height = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_344);
                this.m.setLayoutParams(this.n);
                this.K.setVisibility(8);
                this.q.setVisibility(0);
                return;
            }
            return;
        }
        if (this.q.getVisibility() == 0) {
            this.n.width = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_500);
            this.n.height = (int) this.f3486c.getResources().getDimension(R.dimen.dimens_628);
            this.m.setLayoutParams(this.n);
            this.q.setVisibility(8);
            this.K.setVisibility(0);
        }
    }

    public void t() {
        MyCallView myCallView;
        try {
            WindowManager windowManager = this.L0;
            if (windowManager != null && (myCallView = this.M0) != null) {
                windowManager.removeViewImmediate(myCallView);
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            this.M0 = null;
            this.L0 = null;
            f3485b = null;
            this.g = false;
            throw th;
        }
        this.M0 = null;
        this.L0 = null;
        f3485b = null;
        this.g = false;
    }

    public void u() {
        MyCallView myCallView;
        if (c.a.b.b.f0().B0()) {
            if (!this.g) {
                h();
            }
            if (this.L0 != null && (myCallView = this.M0) != null && !myCallView.isShown()) {
                this.M0.setVisibility(0);
            }
            this.f = true;
            n();
        }
    }

    public void v(String str, String str2, int i, String str3, boolean z, boolean z2) {
        this.l = str2;
        com.byd.bluetoothcall.c.a.F().r0(this.l);
        TextView textView = this.v;
        if (textView != null) {
            textView.setText(e.a(str));
        }
        TextView textView2 = this.A;
        if (textView2 != null) {
            textView2.setText(e.a(str));
        }
        if (this.j && !str.equals(this.H.getText().toString())) {
            TextView textView3 = this.H;
            if (textView3 != null) {
                textView3.setText(str);
            }
        } else if (this.H != null && !e.a(str).equals(this.H.getText().toString())) {
            this.H.setText(e.a(str));
        }
        TextView textView4 = this.R;
        if (textView4 != null) {
            textView4.setText(e.a(str));
        }
        TextView textView5 = this.B;
        if (textView5 != null) {
            textView5.setText(str3);
        }
        TextView textView6 = this.w;
        if (textView6 != null) {
            textView6.setText(str3);
        }
        if (c.a.b.b.f0().b0() == StateConstant$SCO_STATE.CHANNEL_PHONE) {
            ImageView imageView = this.G;
            if (imageView != null) {
                imageView.setSelected(false);
                this.G.setEnabled(false);
            }
            ImageView imageView2 = this.Q;
            if (imageView2 != null) {
                imageView2.setSelected(false);
                this.Q.setEnabled(false);
            }
        } else {
            ImageView imageView3 = this.G;
            if (imageView3 != null) {
                imageView3.setEnabled(true);
                this.G.setSelected(z);
            }
            ImageView imageView4 = this.Q;
            if (imageView4 != null) {
                imageView4.setEnabled(true);
                this.Q.setSelected(z);
            }
        }
        if (i == 0 || i == 1) {
            this.R0.setVisibility(8);
            if (z2) {
                this.S0.setVisibility(0);
                this.Q0.setVisibility(8);
            } else {
                this.Q0.setVisibility(0);
                this.S0.setVisibility(8);
            }
            this.P0.setVisibility(8);
            return;
        }
        if (i == 2 || i == 3) {
            this.R0.setVisibility(0);
            this.x.setText(R.string.call_out_text);
            this.Q0.setVisibility(8);
            this.P0.setVisibility(8);
            if (z2) {
                this.S0.setVisibility(0);
                this.R0.setVisibility(8);
                return;
            } else {
                this.R0.setVisibility(0);
                this.S0.setVisibility(8);
                return;
            }
        }
        if (i != 4 && i != 5) {
            if (i != 7) {
                return;
            }
            this.l = "";
            com.byd.bluetoothcall.c.a.F().r0(this.l);
            return;
        }
        this.R0.setVisibility(8);
        this.Q0.setVisibility(8);
        this.P0.setVisibility(0);
        if (z2) {
            this.S0.setVisibility(0);
            this.P0.setVisibility(8);
        } else {
            this.P0.setVisibility(0);
            this.S0.setVisibility(8);
        }
    }

    public void w(String str) {
        TextView textView = this.I;
        if (textView != null) {
            textView.setText(str);
        }
        TextView textView2 = this.S;
        if (textView2 != null) {
            textView2.setText(str);
        }
    }

    public void z(List<com.byd.bluetoothcall.view.a> list) {
        if (list == null || list.size() <= 1) {
            return;
        }
        this.k = list.get(0).b();
        if ((list.get(0).c() == 0 && list.get(1).c() == 1) || (list.get(0).c() == 1 && list.get(1).c() == 0)) {
            this.L.setVisibility(0);
            this.M.setVisibility(8);
        } else if (list.get(0).c() == 0 && (list.get(1).c() == 4 || list.get(1).c() == 5)) {
            this.L.setVisibility(8);
            this.M.setVisibility(0);
        }
        if (list.get(0).c() != 7) {
            return;
        }
        this.k = "";
    }
}
