package com.spotify.automotive.baseui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.spotify.automotive.baseui.login.AAMLoginPinActivity;
import com.spotify.automotive.baseui.login.AAMLoginScannableActivity;
import com.spotify.automotive.ubi.LoginUbiLogger$Target;
import com.spotify.eventsender.eventsender.b;
import com.spotify.music.R;
import com.spotify.pinpairing.pinpairingimpl.c;
import p.ak;
import p.l0;
import p.md1;
import p.oh1;
import p.p01;
import p.pd1;
import p.r50;
import p.xi0;

/* JADX INFO: loaded from: classes.dex */
public class AAMLoginPinActivity extends r50 implements md1 {
    public static final /* synthetic */ int U = 0;
    public pd1 H;
    public ak I;
    public View J;
    public TextView K;
    public TextView L;
    public View M;
    public ProgressBar N;
    public LinearLayout O;
    public TextView P;
    public TextView Q;
    public TextView R;
    public p01 S;
    public xi0 T;

    @Override // p.md1
    public final void c(boolean z, boolean z2) {
        this.J.setVisibility(8);
        if (!z) {
            this.N.setVisibility(8);
            this.O.setVisibility(0);
            this.P.setVisibility(0);
            this.R.setVisibility(0);
            this.Q.setVisibility(0);
            this.M.setVisibility(0);
            return;
        }
        this.N.setVisibility(0);
        this.O.setVisibility(8);
        this.P.setVisibility(8);
        this.R.setVisibility(8);
        this.Q.setVisibility(8);
        if (z2) {
            this.M.setVisibility(0);
        } else {
            this.M.setVisibility(8);
        }
    }

    @Override // p.md1
    public final void e(int i, boolean z) {
        this.N.setVisibility(8);
        this.O.setVisibility(8);
        this.P.setVisibility(8);
        this.R.setVisibility(8);
        this.Q.setVisibility(8);
        this.L.setText(i);
        this.J.setVisibility(0);
        this.K.setVisibility(z ? 0 : 8);
    }

    @Override // p.md1
    public final void h(String str, String str2, String str3) {
        this.R.setText(str2);
        this.O.removeAllViews();
        int i = 0;
        while (i < str.length()) {
            TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.pin_code_char, (ViewGroup) this.O, false);
            int i2 = i + 1;
            textView.setText(str.substring(i, i2));
            this.O.addView(textView);
            i = i2;
        }
        this.O.setVisibility(0);
        this.P.setVisibility(0);
        c(false, true);
    }

    @Override // androidx.activity.a, android.app.Activity
    public final void onBackPressed() {
        pd1 pd1Var = this.H;
        if (pd1Var == null) {
            super.onBackPressed();
            return;
        }
        pd1Var.g.w(pd1Var.h, LoginUbiLogger$Target.c);
        l0 l0Var = (l0) pd1Var.d;
        l0Var.getClass();
        Context context = l0Var.a;
        Intent intent = new Intent(context, (Class<?>) AAMLoginScannableActivity.class);
        intent.addFlags(67108864);
        context.startActivity(intent);
        pd1Var.a.finish();
    }

    @Override // p.r50, p.zw0, androidx.activity.a, p.wv, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login_pin);
        ak akVar = new ak(getApplicationContext(), new oh1(24, this));
        this.I = akVar;
        akVar.a();
        this.J = findViewById(R.id.error_container);
        this.K = (TextView) findViewById(R.id.error_retry_button);
        this.L = (TextView) findViewById(R.id.error_text);
        this.M = findViewById(R.id.login_text_need_help);
        this.P = (TextView) findViewById(R.id.pin_url_instructions);
        this.Q = (TextView) findViewById(R.id.pin_instructions);
        this.O = (LinearLayout) findViewById(R.id.pin_code);
        this.N = (ProgressBar) findViewById(R.id.progressbar);
        this.R = (TextView) findViewById(R.id.pin_url);
        final int i = 0;
        this.K.setOnClickListener(new View.OnClickListener(this) { // from class: p.y
            public final /* synthetic */ AAMLoginPinActivity e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i2 = i;
                AAMLoginPinActivity aAMLoginPinActivity = this.e;
                switch (i2) {
                    case 0:
                        pd1 pd1Var = aAMLoginPinActivity.H;
                        pd1Var.g.w(pd1Var.h, LoginUbiLogger$Target.m);
                        pd1Var.c();
                        break;
                    case 1:
                        pd1 pd1Var2 = aAMLoginPinActivity.H;
                        pd1Var2.g.w(pd1Var2.h, LoginUbiLogger$Target.l);
                        ((l0) pd1Var2.d).a(R.string.login_help_pin_title, R.string.login_help_pin_text);
                        break;
                    default:
                        pd1 pd1Var3 = aAMLoginPinActivity.H;
                        pd1Var3.g.w(pd1Var3.h, LoginUbiLogger$Target.c);
                        l0 l0Var = (l0) pd1Var3.d;
                        l0Var.getClass();
                        Context context = l0Var.a;
                        Intent intent = new Intent(context, (Class<?>) AAMLoginScannableActivity.class);
                        intent.addFlags(67108864);
                        context.startActivity(intent);
                        pd1Var3.a.finish();
                        break;
                }
            }
        });
        final int i2 = 1;
        this.M.setOnClickListener(new View.OnClickListener(this) { // from class: p.y
            public final /* synthetic */ AAMLoginPinActivity e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i2;
                AAMLoginPinActivity aAMLoginPinActivity = this.e;
                switch (i22) {
                    case 0:
                        pd1 pd1Var = aAMLoginPinActivity.H;
                        pd1Var.g.w(pd1Var.h, LoginUbiLogger$Target.m);
                        pd1Var.c();
                        break;
                    case 1:
                        pd1 pd1Var2 = aAMLoginPinActivity.H;
                        pd1Var2.g.w(pd1Var2.h, LoginUbiLogger$Target.l);
                        ((l0) pd1Var2.d).a(R.string.login_help_pin_title, R.string.login_help_pin_text);
                        break;
                    default:
                        pd1 pd1Var3 = aAMLoginPinActivity.H;
                        pd1Var3.g.w(pd1Var3.h, LoginUbiLogger$Target.c);
                        l0 l0Var = (l0) pd1Var3.d;
                        l0Var.getClass();
                        Context context = l0Var.a;
                        Intent intent = new Intent(context, (Class<?>) AAMLoginScannableActivity.class);
                        intent.addFlags(67108864);
                        context.startActivity(intent);
                        pd1Var3.a.finish();
                        break;
                }
            }
        });
        final int i3 = 2;
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener(this) { // from class: p.y
            public final /* synthetic */ AAMLoginPinActivity e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i3;
                AAMLoginPinActivity aAMLoginPinActivity = this.e;
                switch (i22) {
                    case 0:
                        pd1 pd1Var = aAMLoginPinActivity.H;
                        pd1Var.g.w(pd1Var.h, LoginUbiLogger$Target.m);
                        pd1Var.c();
                        break;
                    case 1:
                        pd1 pd1Var2 = aAMLoginPinActivity.H;
                        pd1Var2.g.w(pd1Var2.h, LoginUbiLogger$Target.l);
                        ((l0) pd1Var2.d).a(R.string.login_help_pin_title, R.string.login_help_pin_text);
                        break;
                    default:
                        pd1 pd1Var3 = aAMLoginPinActivity.H;
                        pd1Var3.g.w(pd1Var3.h, LoginUbiLogger$Target.c);
                        l0 l0Var = (l0) pd1Var3.d;
                        l0Var.getClass();
                        Context context = l0Var.a;
                        Intent intent = new Intent(context, (Class<?>) AAMLoginScannableActivity.class);
                        intent.addFlags(67108864);
                        context.startActivity(intent);
                        pd1Var3.a.finish();
                        break;
                }
            }
        });
        ((b) this.T).a();
    }

    @Override // p.t7, p.zw0, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        this.I.b();
    }

    @Override // p.t7, p.zw0, android.app.Activity
    public final void onStart() {
        super.onStart();
        pd1 pd1Var = this.H;
        if (pd1Var != null) {
            pd1Var.c();
        }
    }

    @Override // p.t7, p.zw0, android.app.Activity
    public final void onStop() {
        super.onStop();
        pd1 pd1Var = this.H;
        if (pd1Var != null) {
            ((c) pd1Var.e).d.d();
        }
    }
}
