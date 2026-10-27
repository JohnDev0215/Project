package com.spotify.automotive.baseui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.spotify.automotive.baseui.login.AAMLoginPasswordActivity;
import com.spotify.automotive.baseui.login.AAMLoginScannableActivity;
import com.spotify.automotive.ubi.LoginUbiLogger$Page;
import com.spotify.automotive.ubi.LoginUbiLogger$Target;
import com.spotify.eventsender.eventsender.b;
import com.spotify.music.R;
import p.ak;
import p.hk0;
import p.kd1;
import p.l0;
import p.ld1;
import p.p01;
import p.r50;
import p.xi0;

/* JADX INFO: loaded from: classes.dex */
public class AAMLoginPasswordActivity extends r50 implements ld1 {
    public static final /* synthetic */ int R = 0;
    public kd1 H;
    public ak I;
    public TextView J;
    public ProgressBar K;
    public TextView L;
    public TextView M;
    public TextView N;
    public View O;
    public p01 P;
    public xi0 Q;

    @Override // androidx.activity.a, android.app.Activity
    public final void onBackPressed() {
        kd1 kd1Var = this.H;
        if (kd1Var == null) {
            super.onBackPressed();
            return;
        }
        kd1Var.f.w(LoginUbiLogger$Page.PASSWORD, LoginUbiLogger$Target.c);
        l0 l0Var = (l0) kd1Var.d;
        l0Var.getClass();
        Context context = l0Var.a;
        Intent intent = new Intent(context, (Class<?>) AAMLoginScannableActivity.class);
        intent.addFlags(67108864);
        context.startActivity(intent);
        kd1Var.a.finish();
    }

    @Override // p.r50, p.zw0, androidx.activity.a, p.wv, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login_password);
        ak akVar = new ak(getApplicationContext(), new hk0(25, this));
        this.I = akVar;
        akVar.a();
        TextView textView = (TextView) findViewById(R.id.login_text_need_help);
        this.O = findViewById(R.id.login_layout);
        this.N = (TextView) findViewById(R.id.login_username);
        this.M = (TextView) findViewById(R.id.login_password);
        this.J = (TextView) findViewById(R.id.login_error_text);
        this.K = (ProgressBar) findViewById(R.id.progressbar);
        this.L = (TextView) findViewById(R.id.login_button);
        this.M.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: p.w
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView2, int i, KeyEvent keyEvent) {
                int i2 = AAMLoginPasswordActivity.R;
                AAMLoginPasswordActivity aAMLoginPasswordActivity = this.a;
                aAMLoginPasswordActivity.getClass();
                if ((keyEvent != null && keyEvent.getKeyCode() == 66) || i == 6) {
                    InputMethodManager inputMethodManager = (InputMethodManager) aAMLoginPasswordActivity.getSystemService("input_method");
                    if (inputMethodManager != null) {
                        inputMethodManager.hideSoftInputFromWindow(textView2.getWindowToken(), 0);
                    }
                    aAMLoginPasswordActivity.H.a();
                }
                return false;
            }
        });
        final int i = 0;
        this.L.setOnClickListener(new View.OnClickListener(this) { // from class: p.x
            public final /* synthetic */ AAMLoginPasswordActivity e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginUbiLogger$Page loginUbiLogger$Page = LoginUbiLogger$Page.PASSWORD;
                int i2 = i;
                AAMLoginPasswordActivity aAMLoginPasswordActivity = this.e;
                switch (i2) {
                    case 0:
                        TextView textView2 = aAMLoginPasswordActivity.L;
                        InputMethodManager inputMethodManager = (InputMethodManager) aAMLoginPasswordActivity.getSystemService("input_method");
                        if (inputMethodManager != null) {
                            inputMethodManager.hideSoftInputFromWindow(textView2.getWindowToken(), 0);
                        }
                        aAMLoginPasswordActivity.H.a();
                        break;
                    case 1:
                        kd1 kd1Var = aAMLoginPasswordActivity.H;
                        kd1Var.f.w(loginUbiLogger$Page, LoginUbiLogger$Target.c);
                        l0 l0Var = (l0) kd1Var.d;
                        l0Var.getClass();
                        Context context = l0Var.a;
                        Intent intent = new Intent(context, (Class<?>) AAMLoginScannableActivity.class);
                        intent.addFlags(67108864);
                        context.startActivity(intent);
                        kd1Var.a.finish();
                        break;
                    default:
                        kd1 kd1Var2 = aAMLoginPasswordActivity.H;
                        kd1Var2.f.w(loginUbiLogger$Page, LoginUbiLogger$Target.l);
                        ((l0) kd1Var2.d).a(R.string.login_help_password_title, R.string.login_help_password_text);
                        break;
                }
            }
        });
        final int i2 = 1;
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener(this) { // from class: p.x
            public final /* synthetic */ AAMLoginPasswordActivity e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginUbiLogger$Page loginUbiLogger$Page = LoginUbiLogger$Page.PASSWORD;
                int i22 = i2;
                AAMLoginPasswordActivity aAMLoginPasswordActivity = this.e;
                switch (i22) {
                    case 0:
                        TextView textView2 = aAMLoginPasswordActivity.L;
                        InputMethodManager inputMethodManager = (InputMethodManager) aAMLoginPasswordActivity.getSystemService("input_method");
                        if (inputMethodManager != null) {
                            inputMethodManager.hideSoftInputFromWindow(textView2.getWindowToken(), 0);
                        }
                        aAMLoginPasswordActivity.H.a();
                        break;
                    case 1:
                        kd1 kd1Var = aAMLoginPasswordActivity.H;
                        kd1Var.f.w(loginUbiLogger$Page, LoginUbiLogger$Target.c);
                        l0 l0Var = (l0) kd1Var.d;
                        l0Var.getClass();
                        Context context = l0Var.a;
                        Intent intent = new Intent(context, (Class<?>) AAMLoginScannableActivity.class);
                        intent.addFlags(67108864);
                        context.startActivity(intent);
                        kd1Var.a.finish();
                        break;
                    default:
                        kd1 kd1Var2 = aAMLoginPasswordActivity.H;
                        kd1Var2.f.w(loginUbiLogger$Page, LoginUbiLogger$Target.l);
                        ((l0) kd1Var2.d).a(R.string.login_help_password_title, R.string.login_help_password_text);
                        break;
                }
            }
        });
        final int i3 = 2;
        textView.setOnClickListener(new View.OnClickListener(this) { // from class: p.x
            public final /* synthetic */ AAMLoginPasswordActivity e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginUbiLogger$Page loginUbiLogger$Page = LoginUbiLogger$Page.PASSWORD;
                int i22 = i3;
                AAMLoginPasswordActivity aAMLoginPasswordActivity = this.e;
                switch (i22) {
                    case 0:
                        TextView textView2 = aAMLoginPasswordActivity.L;
                        InputMethodManager inputMethodManager = (InputMethodManager) aAMLoginPasswordActivity.getSystemService("input_method");
                        if (inputMethodManager != null) {
                            inputMethodManager.hideSoftInputFromWindow(textView2.getWindowToken(), 0);
                        }
                        aAMLoginPasswordActivity.H.a();
                        break;
                    case 1:
                        kd1 kd1Var = aAMLoginPasswordActivity.H;
                        kd1Var.f.w(loginUbiLogger$Page, LoginUbiLogger$Target.c);
                        l0 l0Var = (l0) kd1Var.d;
                        l0Var.getClass();
                        Context context = l0Var.a;
                        Intent intent = new Intent(context, (Class<?>) AAMLoginScannableActivity.class);
                        intent.addFlags(67108864);
                        context.startActivity(intent);
                        kd1Var.a.finish();
                        break;
                    default:
                        kd1 kd1Var2 = aAMLoginPasswordActivity.H;
                        kd1Var2.f.w(loginUbiLogger$Page, LoginUbiLogger$Target.l);
                        ((l0) kd1Var2.d).a(R.string.login_help_password_title, R.string.login_help_password_text);
                        break;
                }
            }
        });
        ((b) this.Q).a();
    }

    @Override // p.t7, p.zw0, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        this.I.b();
    }
}
