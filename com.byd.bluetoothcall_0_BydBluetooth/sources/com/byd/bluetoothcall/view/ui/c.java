package com.byd.bluetoothcall.view.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.bluetoothcall.f.e;
import com.byd.bluetoothcall.f.f;
import com.byd.constant.StateConstant$SCO_STATE;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: compiled from: BT_Small_Call_View.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements View.OnClickListener {

    /* JADX INFO: renamed from: b */
    public static c f3491b;
    private transient View A;
    private transient View B;
    private transient WindowManager C;
    private transient MyView D;
    private transient WindowManager.LayoutParams E;
    private transient View F;
    private transient View G;
    private transient View H;
    private transient View I;
    private transient View J;

    /* JADX INFO: renamed from: c */
    private transient Context f3492c;
    private transient RelativeLayout h;
    private transient ImageView i;
    private transient TextView j;
    private transient TextView k;
    private transient TextView l;
    private transient TextView m;
    private transient TextView n;
    private transient ImageView o;
    private transient ImageView p;
    private transient TextView q;
    private transient TextView r;
    private transient ImageView s;
    private transient ImageView t;
    private transient TextView u;
    private transient TextView v;
    private transient LottieAnimationView w;
    private transient ImageView x;
    private transient TextView y;
    private transient TextView z;

    /* JADX INFO: renamed from: d */
    private transient boolean f3493d = false;

    /* JADX INFO: renamed from: e */
    private transient boolean f3494e = false;
    private transient String f = "";
    private transient String g = "";
    final transient float[] K = {0.0f};
    final transient float[] L = {0.0f};

    /* JADX INFO: compiled from: BT_Small_Call_View.java */
    class a implements View.OnTouchListener {
        a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                BydLogHelper.logD("DANDAN", "MotionEvent.ACTION_DOWN ===");
            } else if (action == 1) {
                c cVar = c.this;
                cVar.K[0] = 0.0f;
                cVar.L[0] = 0.0f;
            } else if (action == 2) {
                c cVar2 = c.this;
                float[] fArr = cVar2.K;
                if (fArr[0] == 0.0f || cVar2.L[0] == 0.0f) {
                    fArr[0] = motionEvent.getRawX();
                    c.this.L[0] = motionEvent.getRawY();
                }
                Float fValueOf = Float.valueOf(motionEvent.getRawX());
                Float fValueOf2 = Float.valueOf(motionEvent.getRawY());
                int iFloatValue = (int) (fValueOf.floatValue() - c.this.K[0]);
                float fFloatValue = fValueOf2.floatValue();
                c cVar3 = c.this;
                int i = (int) (fFloatValue - cVar3.L[0]);
                cVar3.E.x += iFloatValue;
                c.this.E.y += i;
                c.this.C.updateViewLayout(c.this.D, c.this.E);
                c.this.K[0] = fValueOf.floatValue();
                c.this.L[0] = fValueOf2.floatValue();
            }
            return true;
        }
    }

    private c(Context context) {
        this.f3492c = context;
        i();
    }

    private void a() {
        RelativeLayout relativeLayout = this.h;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundDrawable(this.f3492c.getDrawable(R.drawable.denza_bt_call_small_view_bg));
        }
        TextView textView = this.y;
        if (textView != null) {
            textView.setTextColor(this.f3492c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView2 = this.z;
        if (textView2 != null) {
            textView2.setTextColor(this.f3492c.getColor(R.color.ts_b3_text_color));
        }
        if (com.byd.bluetoothcall.c.a.F().K()) {
            LottieAnimationView lottieAnimationView = this.w;
            if (lottieAnimationView != null) {
                if (lottieAnimationView.m()) {
                    this.w.g();
                    this.w.setAnimation("icon_toggle.json");
                    this.w.o();
                } else {
                    this.w.setAnimation("icon_toggle.json");
                }
            }
        } else if (this.w.m()) {
            this.w.g();
            this.w.setAnimation("icon_toggle_light.json");
            this.w.o();
        } else {
            this.w.setAnimation("icon_toggle_light.json");
        }
        TextView textView3 = this.j;
        if (textView3 != null) {
            textView3.setTextColor(this.f3492c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView4 = this.k;
        if (textView4 != null) {
            textView4.setTextColor(this.f3492c.getColor(R.color.ts_b3_text_color));
        }
        TextView textView5 = this.l;
        if (textView5 != null) {
            textView5.setTextColor(this.f3492c.getColor(R.color.ts_b3_text_color));
            if (this.l.getText().toString().equals(this.f3492c.getString(R.string.end_call_text))) {
                this.l.setText(R.string.end_call_text);
            } else {
                this.l.setText(R.string.call_out_text);
            }
        }
        ImageView imageView = this.i;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.denza_special_calling_voice2phone_btn);
        }
        TextView textView6 = this.m;
        if (textView6 != null) {
            textView6.setTextColor(this.f3492c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView7 = this.n;
        if (textView7 != null) {
            textView7.setTextColor(this.f3492c.getColor(R.color.ts_b3_text_color));
        }
        TextView textView8 = this.q;
        if (textView8 != null) {
            textView8.setTextColor(this.f3492c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView9 = this.r;
        if (textView9 != null) {
            textView9.setTextColor(this.f3492c.getColor(R.color.ts_b3_text_color));
        }
        ImageView imageView2 = this.o;
        if (imageView2 != null) {
            imageView2.setImageResource(R.drawable.denza_special_calling_voice2phone_btn);
        }
        ImageView imageView3 = this.p;
        if (imageView3 != null) {
            imageView3.setImageResource(R.drawable.denza_special_calling_mic_mute_btn);
        }
        TextView textView10 = this.u;
        if (textView10 != null) {
            textView10.setTextColor(this.f3492c.getColor(R.color.ts_ff_text_color));
        }
        TextView textView11 = this.v;
        if (textView11 != null) {
            textView11.setTextColor(this.f3492c.getColor(R.color.ts_b3_text_color));
        }
        ImageView imageView4 = this.s;
        if (imageView4 != null) {
            imageView4.setImageResource(R.drawable.denza_special_calling_voice2phone_btn);
        }
        ImageView imageView5 = this.t;
        if (imageView5 != null) {
            imageView5.setImageResource(R.drawable.denza_special_calling_mic_mute_btn);
        }
    }

    private void b() {
        RelativeLayout relativeLayout = this.h;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundDrawable(this.f3492c.getDrawable(R.drawable.bt_call_small_view_bg));
        }
        TextView textView = this.y;
        if (textView != null) {
            textView.setTextColor(this.f3492c.getColor(R.color.ff_text_color));
        }
        TextView textView2 = this.z;
        if (textView2 != null) {
            textView2.setTextColor(this.f3492c.getColor(R.color.b3_text_color));
        }
        if (com.byd.bluetoothcall.c.a.F().K()) {
            LottieAnimationView lottieAnimationView = this.w;
            if (lottieAnimationView != null) {
                if (lottieAnimationView.m()) {
                    this.w.g();
                    this.w.setAnimation("icon_toggle.json");
                    this.w.o();
                } else {
                    this.w.setAnimation("icon_toggle.json");
                }
            }
        } else {
            LottieAnimationView lottieAnimationView2 = this.w;
            if (lottieAnimationView2 != null) {
                if (lottieAnimationView2.m()) {
                    this.w.g();
                    this.w.setAnimation("icon_toggle_light.json");
                    this.w.o();
                } else {
                    this.w.setAnimation("icon_toggle_light.json");
                }
            }
        }
        TextView textView3 = this.j;
        if (textView3 != null) {
            textView3.setTextColor(this.f3492c.getColor(R.color.ff_text_color));
        }
        TextView textView4 = this.k;
        if (textView4 != null) {
            textView4.setTextColor(this.f3492c.getColor(R.color.b3_text_color));
        }
        TextView textView5 = this.l;
        if (textView5 != null) {
            textView5.setTextColor(this.f3492c.getColor(R.color.b3_text_color));
            if (this.l.getText().toString().equals(this.f3492c.getString(R.string.end_call_text))) {
                this.l.setText(R.string.end_call_text);
            } else {
                this.l.setText(R.string.call_out_text);
            }
        }
        ImageView imageView = this.i;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.special_calling_voice2phone_btn);
        }
        TextView textView6 = this.m;
        if (textView6 != null) {
            textView6.setTextColor(this.f3492c.getColor(R.color.ff_text_color));
        }
        TextView textView7 = this.n;
        if (textView7 != null) {
            textView7.setTextColor(this.f3492c.getColor(R.color.b3_text_color));
        }
        TextView textView8 = this.q;
        if (textView8 != null) {
            textView8.setTextColor(this.f3492c.getColor(R.color.ff_text_color));
        }
        TextView textView9 = this.r;
        if (textView9 != null) {
            textView9.setTextColor(this.f3492c.getColor(R.color.b3_text_color));
        }
        ImageView imageView2 = this.o;
        if (imageView2 != null) {
            imageView2.setImageResource(R.drawable.special_calling_voice2phone_btn);
        }
        ImageView imageView3 = this.p;
        if (imageView3 != null) {
            imageView3.setImageResource(R.drawable.special_calling_mic_mute_btn);
        }
        TextView textView10 = this.u;
        if (textView10 != null) {
            textView10.setTextColor(this.f3492c.getColor(R.color.ff_text_color));
        }
        TextView textView11 = this.v;
        if (textView11 != null) {
            textView11.setTextColor(this.f3492c.getColor(R.color.b3_text_color));
        }
        ImageView imageView4 = this.s;
        if (imageView4 != null) {
            imageView4.setImageResource(R.drawable.special_calling_voice2phone_btn);
        }
        ImageView imageView5 = this.t;
        if (imageView5 != null) {
            imageView5.setImageResource(R.drawable.special_calling_mic_mute_btn);
        }
    }

    private void i() {
        if (f.l(this.f3492c) == 1) {
            this.f3492c.setTheme(R.style.ThemeTS);
        } else {
            this.f3492c.setTheme(R.style.ThemeTang);
        }
        if (this.D == null) {
            this.D = (MyView) LayoutInflater.from(this.f3492c).inflate(R.layout.bt_special_scenario_call_view, (ViewGroup) null);
        }
        k(this.D);
        this.C = (WindowManager) this.f3492c.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.E = layoutParams;
        layoutParams.flags = 40;
        layoutParams.format = -3;
        layoutParams.type = 2008;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.gravity = 49;
        layoutParams.x = 0;
        layoutParams.y = 0;
        this.D.setBackgroundColor(0);
        this.D.setVisibility(8);
        this.D.setOnTouchListener(new a());
        try {
            this.C.addView(this.D, this.E);
        } catch (Exception unused) {
        }
    }

    private void k(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.bt_special_call_view);
        this.h = relativeLayout;
        relativeLayout.setOnClickListener(this);
        View viewFindViewById = view.findViewById(R.id.bt_call_out_layout);
        this.I = viewFindViewById;
        viewFindViewById.findViewById(R.id.call_out_hugup_btn).setOnClickListener(this);
        ImageView imageView = (ImageView) this.I.findViewById(R.id.call_out_changevoice_btn);
        this.i = imageView;
        imageView.setOnClickListener(this);
        this.j = (TextView) this.I.findViewById(R.id.call_out_name_text);
        this.k = (TextView) this.I.findViewById(R.id.call_out_location_text);
        this.l = (TextView) this.I.findViewById(R.id.dialing_text);
        View viewFindViewById2 = view.findViewById(R.id.bt_call_in_layout);
        this.G = viewFindViewById2;
        viewFindViewById2.findViewById(R.id.call_in_hugup_btn).setOnClickListener(this);
        this.G.findViewById(R.id.call_in_anser_btn).setOnClickListener(this);
        this.m = (TextView) this.G.findViewById(R.id.call_in_name_text);
        this.n = (TextView) this.G.findViewById(R.id.call_in_location_text);
        View viewFindViewById3 = view.findViewById(R.id.bt_call_ing_layout);
        this.H = viewFindViewById3;
        ImageView imageView2 = (ImageView) viewFindViewById3.findViewById(R.id.call_ing_micswitch_btn);
        this.p = imageView2;
        imageView2.setOnClickListener(this);
        this.H.findViewById(R.id.call_ing_hugup_btn).setOnClickListener(this);
        this.q = (TextView) this.H.findViewById(R.id.call_ing_name_text);
        this.r = (TextView) this.H.findViewById(R.id.call_ing_time_text);
        View viewFindViewById4 = view.findViewById(R.id.special_networkcall_layout);
        this.J = viewFindViewById4;
        ImageView imageView3 = (ImageView) viewFindViewById4.findViewById(R.id.special_networkcall_voice2phone_btn);
        this.s = imageView3;
        imageView3.setOnClickListener(this);
        ImageView imageView4 = (ImageView) this.J.findViewById(R.id.networkcall_micswitch_btn);
        this.t = imageView4;
        imageView4.setOnClickListener(this);
        this.u = (TextView) this.J.findViewById(R.id.networkcall_name_text);
        this.v = (TextView) this.J.findViewById(R.id.networkcall_time_text);
        View viewFindViewById5 = view.findViewById(R.id.third_call_layout);
        this.F = viewFindViewById5;
        this.w = (LottieAnimationView) viewFindViewById5.findViewById(R.id.switch_call_image);
        if (com.byd.bluetoothcall.c.a.F().K()) {
            LottieAnimationView lottieAnimationView = this.w;
            if (lottieAnimationView != null) {
                lottieAnimationView.setAnimation("icon_toggle.json");
            }
        } else {
            LottieAnimationView lottieAnimationView2 = this.w;
            if (lottieAnimationView2 != null) {
                lottieAnimationView2.setAnimation("icon_toggle_light.json");
            }
        }
        this.w.setOnClickListener(this);
        ImageView imageView5 = (ImageView) this.F.findViewById(R.id.hugup_third_image);
        this.x = imageView5;
        imageView5.setOnClickListener(this);
        this.y = (TextView) this.F.findViewById(R.id.third_call_name);
        this.z = (TextView) this.F.findViewById(R.id.third_call_time);
        this.A = view.findViewById(R.id.view_up_6dp);
        this.B = view.findViewById(R.id.view_down_6dp);
    }

    public static c l(Context context) {
        synchronized (c.class) {
            if (f3491b == null) {
                f3491b = new c(context.getApplicationContext());
            }
        }
        return f3491b;
    }

    private void n() {
        d(c.a.b.b.f0().b0());
    }

    private void r() {
        WindowManager.LayoutParams layoutParams = this.E;
        layoutParams.x = 0;
        layoutParams.y = 0;
        WindowManager windowManager = this.C;
        if (windowManager != null) {
            windowManager.updateViewLayout(this.D, layoutParams);
        }
        TextView textView = this.n;
        if (textView != null) {
            textView.setText("");
        }
        TextView textView2 = this.r;
        if (textView2 != null) {
            textView2.setText("");
        }
    }

    public void c(String str) {
        if (!this.f3494e || str.equals(this.y.getText().toString())) {
            TextView textView = this.y;
            if (textView != null) {
                textView.setText(str);
                return;
            }
            return;
        }
        TextView textView2 = this.y;
        if (textView2 != null) {
            textView2.setText(str);
        }
    }

    public void d(StateConstant$SCO_STATE stateConstant$SCO_STATE) {
        BydLogHelper.logD("HZFR", "sco_state = " + stateConstant$SCO_STATE);
        if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_BT) {
            ImageView imageView = this.p;
            if (imageView != null) {
                imageView.setSelected(com.byd.bluetoothcall.c.a.F().G());
                this.p.setEnabled(true);
            }
            ImageView imageView2 = this.t;
            if (imageView2 != null) {
                imageView2.setSelected(com.byd.bluetoothcall.c.a.F().G());
                this.t.setEnabled(true);
            }
            ImageView imageView3 = this.i;
            if (imageView3 != null) {
                imageView3.setSelected(false);
            }
            ImageView imageView4 = this.s;
            if (imageView4 != null) {
                imageView4.setSelected(false);
                return;
            }
            return;
        }
        if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_PHONE) {
            ImageView imageView5 = this.p;
            if (imageView5 != null) {
                imageView5.setSelected(false);
                this.p.setEnabled(false);
            }
            ImageView imageView6 = this.t;
            if (imageView6 != null) {
                imageView6.setSelected(false);
                this.t.setEnabled(false);
            }
            ImageView imageView7 = this.i;
            if (imageView7 != null) {
                imageView7.setSelected(true);
            }
            ImageView imageView8 = this.s;
            if (imageView8 != null) {
                imageView8.setSelected(true);
            }
        }
    }

    public void e(boolean z) {
        this.f3494e = z;
        View view = this.F;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
        RelativeLayout relativeLayout = this.h;
        if (relativeLayout != null) {
            ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
            if (z) {
                this.A.setVisibility(8);
                this.B.setVisibility(8);
                layoutParams.height = (int) this.f3492c.getResources().getDimension(R.dimen.dimens_165);
            } else {
                this.A.setVisibility(0);
                this.B.setVisibility(0);
                layoutParams.height = (int) this.f3492c.getResources().getDimension(R.dimen.dimens_132);
            }
            this.h.setLayoutParams(layoutParams);
        }
    }

    public void j() {
        TextView textView = this.n;
        if (textView != null) {
            textView.setText(R.string.end_call_text);
        }
        TextView textView2 = this.l;
        if (textView2 != null) {
            textView2.setText(R.string.end_call_text);
        }
        TextView textView3 = this.r;
        if (textView3 != null) {
            textView3.setText(R.string.end_call_text);
        }
    }

    public void m() {
        MyView myView;
        if (this.C == null || (myView = this.D) == null || !myView.isShown()) {
            return;
        }
        this.D.setVisibility(8);
        this.f3493d = false;
        r();
    }

    public boolean o() {
        return this.f3493d;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_special_call_view /* 2131296374 */:
                if (com.byd.bluetoothcall.c.a.F().f0()) {
                    b.l(this.f3492c).u();
                    m();
                }
                break;
            case R.id.call_in_anser_btn /* 2131296391 */:
                if (!this.f3494e) {
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
            case R.id.special_networkcall_voice2phone_btn /* 2131296807 */:
                if (!f.o(800)) {
                    com.byd.bluetoothcall.c.a.F().a();
                } else {
                    Log.d("DANDAN", "CLICL TOO FAST");
                }
                break;
            case R.id.call_ing_hugup_btn /* 2131296396 */:
                if (!this.f3494e) {
                    c.a.b.b.f0().v0();
                } else {
                    c.a.b.b.f0().y0(this.g);
                }
                break;
            case R.id.call_ing_micswitch_btn /* 2131296402 */:
            case R.id.networkcall_micswitch_btn /* 2131296685 */:
                com.byd.bluetoothcall.c.a.F().s0();
                break;
            case R.id.call_out_hugup_btn /* 2131296412 */:
                c.a.b.b.f0().v0();
                break;
            case R.id.hugup_third_image /* 2131296550 */:
                c.a.b.b.f0().y0(this.f);
                break;
            case R.id.switch_call_image /* 2131296829 */:
                if (!f.n(500)) {
                    LottieAnimationView lottieAnimationView = this.w;
                    if (lottieAnimationView != null) {
                        lottieAnimationView.o();
                    }
                    c.a.b.b.f0().P();
                }
                break;
        }
    }

    public void p(int i) {
        if (c.a.b.b.f0().b0() == StateConstant$SCO_STATE.CHANNEL_PHONE) {
            ImageView imageView = this.p;
            if (imageView != null) {
                imageView.setSelected(false);
                this.p.setEnabled(false);
            }
            ImageView imageView2 = this.t;
            if (imageView2 != null) {
                imageView2.setSelected(false);
                this.t.setEnabled(false);
                return;
            }
            return;
        }
        ImageView imageView3 = this.p;
        if (imageView3 != null) {
            imageView3.setEnabled(true);
            this.p.setSelected(i == 1);
        }
        ImageView imageView4 = this.t;
        if (imageView4 != null) {
            imageView4.setEnabled(true);
            this.t.setSelected(i == 1);
        }
    }

    public void q(Configuration configuration) {
        if (f.l(this.f3492c) == 1) {
            a();
        } else {
            b();
        }
    }

    public void s() {
        MyView myView;
        WindowManager windowManager = this.C;
        if (windowManager == null || (myView = this.D) == null) {
            return;
        }
        windowManager.removeViewImmediate(myView);
        this.D = null;
        this.C = null;
        f3491b = null;
    }

    public void t() {
        MyView myView;
        if (c.a.b.b.f0().B0()) {
            if (this.C != null && (myView = this.D) != null && !myView.isShown()) {
                this.D.setVisibility(0);
                this.f3493d = true;
            }
            n();
        }
    }

    public void u(String str, String str2, int i, String str3, boolean z, boolean z2) {
        this.g = str2;
        TextView textView = this.j;
        if (textView != null) {
            textView.setText(e.a(str));
        }
        TextView textView2 = this.m;
        if (textView2 != null) {
            textView2.setText(e.a(str));
        }
        if (this.f3494e && !str.equals(this.q.getText().toString())) {
            TextView textView3 = this.q;
            if (textView3 != null) {
                textView3.setText(str);
            }
        } else if (this.q != null && !e.a(str).equals(this.q.getText().toString())) {
            this.q.setText(e.a(str));
        }
        TextView textView4 = this.u;
        if (textView4 != null) {
            textView4.setText(e.a(str));
        }
        TextView textView5 = this.n;
        if (textView5 != null) {
            textView5.setText(str3);
        }
        TextView textView6 = this.k;
        if (textView6 != null) {
            textView6.setText(str3);
        }
        if (c.a.b.b.f0().b0() == StateConstant$SCO_STATE.CHANNEL_PHONE) {
            ImageView imageView = this.p;
            if (imageView != null) {
                imageView.setSelected(false);
                this.p.setEnabled(false);
            }
            ImageView imageView2 = this.t;
            if (imageView2 != null) {
                imageView2.setSelected(false);
                this.t.setEnabled(false);
            }
        } else {
            ImageView imageView3 = this.p;
            if (imageView3 != null) {
                imageView3.setEnabled(true);
                this.p.setSelected(z);
            }
            ImageView imageView4 = this.t;
            if (imageView4 != null) {
                imageView4.setEnabled(true);
                this.t.setSelected(z);
            }
        }
        if (i == 0 || i == 1) {
            this.I.setVisibility(8);
            if (z2) {
                this.H.setVisibility(8);
                this.J.setVisibility(0);
            } else {
                this.H.setVisibility(0);
                this.J.setVisibility(8);
            }
            this.G.setVisibility(8);
            return;
        }
        if (i == 2 || i == 3) {
            this.l.setText(R.string.call_out_text);
            this.H.setVisibility(8);
            this.G.setVisibility(8);
            if (z2) {
                this.I.setVisibility(8);
                this.J.setVisibility(0);
                return;
            } else {
                this.I.setVisibility(0);
                this.J.setVisibility(8);
                return;
            }
        }
        if (i != 4 && i != 5) {
            if (i != 7) {
                return;
            }
            this.g = "";
            return;
        }
        this.I.setVisibility(8);
        this.H.setVisibility(8);
        if (z2) {
            this.G.setVisibility(8);
            this.J.setVisibility(0);
        } else {
            this.G.setVisibility(0);
            this.J.setVisibility(8);
        }
    }

    public void v(String str) {
        TextView textView = this.r;
        if (textView != null) {
            textView.setText(str);
        }
        TextView textView2 = this.v;
        if (textView2 != null) {
            textView2.setText(str);
        }
    }

    public void w(List<com.byd.bluetoothcall.view.a> list) {
        if (list == null || list.size() <= 1) {
            return;
        }
        this.f = list.get(0).b();
        if ((list.get(0).c() == 0 && list.get(1).c() == 1) || (list.get(0).c() == 1 && list.get(1).c() == 0)) {
            this.w.setVisibility(0);
            this.x.setVisibility(8);
        } else if (list.get(0).c() == 0 && (list.get(1).c() == 4 || list.get(1).c() == 5)) {
            this.w.setVisibility(8);
            this.x.setVisibility(0);
        }
        if (list.get(0).c() != 7) {
            return;
        }
        this.f = "";
    }

    public void x(String str) {
        TextView textView = this.z;
        if (textView != null) {
            textView.setText(str);
        }
    }
}
