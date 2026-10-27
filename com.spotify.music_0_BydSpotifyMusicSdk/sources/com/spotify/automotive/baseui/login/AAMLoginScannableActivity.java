package com.spotify.automotive.baseui.login;

import android.accounts.AccountAuthenticatorResponse;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.spotify.automotive.baseui.login.AAMLoginPasswordActivity;
import com.spotify.automotive.baseui.login.AAMLoginPinActivity;
import com.spotify.automotive.baseui.login.AAMLoginScannableActivity;
import com.spotify.automotive.ubi.LoginUbiLogger$Page;
import com.spotify.automotive.ubi.LoginUbiLogger$Target;
import com.spotify.eventsender.eventsender.b;
import com.spotify.music.R;
import com.spotify.pinpairing.pinpairingimpl.c;
import java.util.WeakHashMap;
import p.ak;
import p.ay2;
import p.md1;
import p.ot2;
import p.p01;
import p.p41;
import p.pz;
import p.qd1;
import p.r50;
import p.sz;
import p.xi0;
import p.zb0;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class AAMLoginScannableActivity extends r50 implements md1 {
    public static final /* synthetic */ int W = 0;
    public qd1 I;
    public ak K;
    public View L;
    public TextView M;
    public TextView N;
    public TextView O;
    public View P;
    public View Q;
    public View R;
    public ProgressBar S;
    public ImageView T;
    public p01 U;
    public xi0 V;
    public final ay2 H = new ay2(13);
    public final zb0 J = new zb0();

    @Override // p.md1
    public final void c(boolean z, boolean z2) {
        this.L.setVisibility(8);
        if (!z) {
            this.S.setVisibility(8);
            this.O.setVisibility(0);
            this.T.setVisibility(0);
            this.P.setVisibility(0);
            this.Q.setVisibility(0);
            this.R.setVisibility(0);
            return;
        }
        this.S.setVisibility(0);
        this.O.setVisibility(8);
        this.T.setVisibility(8);
        if (z2) {
            this.P.setVisibility(0);
            this.Q.setVisibility(0);
            this.R.setVisibility(0);
        } else {
            this.P.setVisibility(8);
            this.Q.setVisibility(8);
            this.R.setVisibility(8);
        }
    }

    @Override // p.md1
    public final void e(int i, boolean z) {
        this.S.setVisibility(8);
        this.O.setVisibility(8);
        this.T.setVisibility(8);
        this.N.setText(i);
        this.M.setVisibility(z ? 0 : 8);
        this.L.setVisibility(0);
        this.P.setVisibility(0);
        this.Q.setVisibility(0);
    }

    @Override // android.app.Activity, p.mu0
    public final void finish() {
        ay2 ay2Var = this.H;
        AccountAuthenticatorResponse accountAuthenticatorResponse = (AccountAuthenticatorResponse) ay2Var.c;
        if (accountAuthenticatorResponse != null) {
            accountAuthenticatorResponse.onResult((Bundle) ay2Var.e);
            ay2Var.c = null;
        }
        setResult(-1, getIntent());
        super.finish();
    }

    @Override // p.md1
    public final void h(String str, String str2, String str3) {
        ImageView imageView = this.T;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.settings_social_connect_scannable_size);
        Object obj = sz.a;
        int iA = pz.a(this, R.color.scannable_foreground);
        int iA2 = pz.a(this, R.color.scannable_background);
        this.J.getClass();
        imageView.setImageBitmap(zb0.v(dimensionPixelSize, iA, iA2, str3));
        c(false, true);
    }

    @Override // p.r50, p.zw0, androidx.activity.a, p.wv, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login_scannable);
        Intent intent = getIntent();
        ay2 ay2Var = this.H;
        ay2Var.getClass();
        if (intent != null) {
            ay2Var.c = (AccountAuthenticatorResponse) intent.getParcelableExtra("accountAuthenticatorResponse");
            ay2Var.e = intent.getExtras();
            AccountAuthenticatorResponse accountAuthenticatorResponse = (AccountAuthenticatorResponse) ay2Var.c;
            if (accountAuthenticatorResponse != null) {
                accountAuthenticatorResponse.onRequestContinued();
            }
        }
        ak akVar = new ak(getApplicationContext(), new p01(23, this));
        this.K = akVar;
        akVar.a();
        this.L = findViewById(R.id.error_container);
        this.M = (TextView) findViewById(R.id.error_retry_button);
        this.N = (TextView) findViewById(R.id.error_text);
        this.O = (TextView) findViewById(R.id.scannable_instructions);
        this.P = findViewById(R.id.login_password_button);
        this.Q = findViewById(R.id.login_pin_button);
        this.R = findViewById(R.id.login_text_need_help);
        this.S = (ProgressBar) findViewById(R.id.progressbar);
        this.T = (ImageView) findViewById(R.id.scannable);
        final int i = 0;
        this.M.setOnClickListener(new View.OnClickListener(this) { // from class: p.z
            public final /* synthetic */ AAMLoginScannableActivity e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginUbiLogger$Page loginUbiLogger$Page = LoginUbiLogger$Page.QR_CODE;
                int i2 = i;
                AAMLoginScannableActivity aAMLoginScannableActivity = this.e;
                switch (i2) {
                    case 0:
                        qd1 qd1Var = aAMLoginScannableActivity.I;
                        qd1Var.g.w(qd1Var.h, LoginUbiLogger$Target.m);
                        qd1Var.c();
                        break;
                    case 1:
                        qd1 qd1Var2 = aAMLoginScannableActivity.I;
                        qd1Var2.j.w(loginUbiLogger$Page, LoginUbiLogger$Target.l);
                        ((l0) qd1Var2.d).a(R.string.login_help_scannable_title, R.string.login_help_scannable_text);
                        break;
                    case 2:
                        qd1 qd1Var3 = aAMLoginScannableActivity.I;
                        qd1Var3.g.w(qd1Var3.h, LoginUbiLogger$Target.k);
                        l0 l0Var = (l0) qd1Var3.d;
                        l0Var.getClass();
                        Context context = l0Var.a;
                        Intent intent2 = new Intent(context, (Class<?>) AAMLoginPasswordActivity.class);
                        intent2.addFlags(67108864);
                        context.startActivity(intent2);
                        qd1Var3.a.finish();
                        break;
                    case 3:
                        qd1 qd1Var4 = aAMLoginScannableActivity.I;
                        qd1Var4.j.w(loginUbiLogger$Page, LoginUbiLogger$Target.e);
                        l0 l0Var2 = (l0) qd1Var4.d;
                        l0Var2.getClass();
                        Context context2 = l0Var2.a;
                        Intent intent3 = new Intent(context2, (Class<?>) AAMLoginPinActivity.class);
                        intent3.setFlags(67108864);
                        context2.startActivity(intent3);
                        qd1Var4.a.finish();
                        break;
                    default:
                        int i3 = AAMLoginScannableActivity.W;
                        aAMLoginScannableActivity.finish();
                        break;
                }
            }
        });
        final int i2 = 1;
        this.R.setOnClickListener(new View.OnClickListener(this) { // from class: p.z
            public final /* synthetic */ AAMLoginScannableActivity e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginUbiLogger$Page loginUbiLogger$Page = LoginUbiLogger$Page.QR_CODE;
                int i22 = i2;
                AAMLoginScannableActivity aAMLoginScannableActivity = this.e;
                switch (i22) {
                    case 0:
                        qd1 qd1Var = aAMLoginScannableActivity.I;
                        qd1Var.g.w(qd1Var.h, LoginUbiLogger$Target.m);
                        qd1Var.c();
                        break;
                    case 1:
                        qd1 qd1Var2 = aAMLoginScannableActivity.I;
                        qd1Var2.j.w(loginUbiLogger$Page, LoginUbiLogger$Target.l);
                        ((l0) qd1Var2.d).a(R.string.login_help_scannable_title, R.string.login_help_scannable_text);
                        break;
                    case 2:
                        qd1 qd1Var3 = aAMLoginScannableActivity.I;
                        qd1Var3.g.w(qd1Var3.h, LoginUbiLogger$Target.k);
                        l0 l0Var = (l0) qd1Var3.d;
                        l0Var.getClass();
                        Context context = l0Var.a;
                        Intent intent2 = new Intent(context, (Class<?>) AAMLoginPasswordActivity.class);
                        intent2.addFlags(67108864);
                        context.startActivity(intent2);
                        qd1Var3.a.finish();
                        break;
                    case 3:
                        qd1 qd1Var4 = aAMLoginScannableActivity.I;
                        qd1Var4.j.w(loginUbiLogger$Page, LoginUbiLogger$Target.e);
                        l0 l0Var2 = (l0) qd1Var4.d;
                        l0Var2.getClass();
                        Context context2 = l0Var2.a;
                        Intent intent3 = new Intent(context2, (Class<?>) AAMLoginPinActivity.class);
                        intent3.setFlags(67108864);
                        context2.startActivity(intent3);
                        qd1Var4.a.finish();
                        break;
                    default:
                        int i3 = AAMLoginScannableActivity.W;
                        aAMLoginScannableActivity.finish();
                        break;
                }
            }
        });
        final int i3 = 2;
        this.P.setOnClickListener(new View.OnClickListener(this) { // from class: p.z
            public final /* synthetic */ AAMLoginScannableActivity e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginUbiLogger$Page loginUbiLogger$Page = LoginUbiLogger$Page.QR_CODE;
                int i22 = i3;
                AAMLoginScannableActivity aAMLoginScannableActivity = this.e;
                switch (i22) {
                    case 0:
                        qd1 qd1Var = aAMLoginScannableActivity.I;
                        qd1Var.g.w(qd1Var.h, LoginUbiLogger$Target.m);
                        qd1Var.c();
                        break;
                    case 1:
                        qd1 qd1Var2 = aAMLoginScannableActivity.I;
                        qd1Var2.j.w(loginUbiLogger$Page, LoginUbiLogger$Target.l);
                        ((l0) qd1Var2.d).a(R.string.login_help_scannable_title, R.string.login_help_scannable_text);
                        break;
                    case 2:
                        qd1 qd1Var3 = aAMLoginScannableActivity.I;
                        qd1Var3.g.w(qd1Var3.h, LoginUbiLogger$Target.k);
                        l0 l0Var = (l0) qd1Var3.d;
                        l0Var.getClass();
                        Context context = l0Var.a;
                        Intent intent2 = new Intent(context, (Class<?>) AAMLoginPasswordActivity.class);
                        intent2.addFlags(67108864);
                        context.startActivity(intent2);
                        qd1Var3.a.finish();
                        break;
                    case 3:
                        qd1 qd1Var4 = aAMLoginScannableActivity.I;
                        qd1Var4.j.w(loginUbiLogger$Page, LoginUbiLogger$Target.e);
                        l0 l0Var2 = (l0) qd1Var4.d;
                        l0Var2.getClass();
                        Context context2 = l0Var2.a;
                        Intent intent3 = new Intent(context2, (Class<?>) AAMLoginPinActivity.class);
                        intent3.setFlags(67108864);
                        context2.startActivity(intent3);
                        qd1Var4.a.finish();
                        break;
                    default:
                        int i32 = AAMLoginScannableActivity.W;
                        aAMLoginScannableActivity.finish();
                        break;
                }
            }
        });
        final int i4 = 3;
        this.Q.setOnClickListener(new View.OnClickListener(this) { // from class: p.z
            public final /* synthetic */ AAMLoginScannableActivity e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginUbiLogger$Page loginUbiLogger$Page = LoginUbiLogger$Page.QR_CODE;
                int i22 = i4;
                AAMLoginScannableActivity aAMLoginScannableActivity = this.e;
                switch (i22) {
                    case 0:
                        qd1 qd1Var = aAMLoginScannableActivity.I;
                        qd1Var.g.w(qd1Var.h, LoginUbiLogger$Target.m);
                        qd1Var.c();
                        break;
                    case 1:
                        qd1 qd1Var2 = aAMLoginScannableActivity.I;
                        qd1Var2.j.w(loginUbiLogger$Page, LoginUbiLogger$Target.l);
                        ((l0) qd1Var2.d).a(R.string.login_help_scannable_title, R.string.login_help_scannable_text);
                        break;
                    case 2:
                        qd1 qd1Var3 = aAMLoginScannableActivity.I;
                        qd1Var3.g.w(qd1Var3.h, LoginUbiLogger$Target.k);
                        l0 l0Var = (l0) qd1Var3.d;
                        l0Var.getClass();
                        Context context = l0Var.a;
                        Intent intent2 = new Intent(context, (Class<?>) AAMLoginPasswordActivity.class);
                        intent2.addFlags(67108864);
                        context.startActivity(intent2);
                        qd1Var3.a.finish();
                        break;
                    case 3:
                        qd1 qd1Var4 = aAMLoginScannableActivity.I;
                        qd1Var4.j.w(loginUbiLogger$Page, LoginUbiLogger$Target.e);
                        l0 l0Var2 = (l0) qd1Var4.d;
                        l0Var2.getClass();
                        Context context2 = l0Var2.a;
                        Intent intent3 = new Intent(context2, (Class<?>) AAMLoginPinActivity.class);
                        intent3.setFlags(67108864);
                        context2.startActivity(intent3);
                        qd1Var4.a.finish();
                        break;
                    default:
                        int i32 = AAMLoginScannableActivity.W;
                        aAMLoginScannableActivity.finish();
                        break;
                }
            }
        });
        final int i5 = 4;
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener(this) { // from class: p.z
            public final /* synthetic */ AAMLoginScannableActivity e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginUbiLogger$Page loginUbiLogger$Page = LoginUbiLogger$Page.QR_CODE;
                int i22 = i5;
                AAMLoginScannableActivity aAMLoginScannableActivity = this.e;
                switch (i22) {
                    case 0:
                        qd1 qd1Var = aAMLoginScannableActivity.I;
                        qd1Var.g.w(qd1Var.h, LoginUbiLogger$Target.m);
                        qd1Var.c();
                        break;
                    case 1:
                        qd1 qd1Var2 = aAMLoginScannableActivity.I;
                        qd1Var2.j.w(loginUbiLogger$Page, LoginUbiLogger$Target.l);
                        ((l0) qd1Var2.d).a(R.string.login_help_scannable_title, R.string.login_help_scannable_text);
                        break;
                    case 2:
                        qd1 qd1Var3 = aAMLoginScannableActivity.I;
                        qd1Var3.g.w(qd1Var3.h, LoginUbiLogger$Target.k);
                        l0 l0Var = (l0) qd1Var3.d;
                        l0Var.getClass();
                        Context context = l0Var.a;
                        Intent intent2 = new Intent(context, (Class<?>) AAMLoginPasswordActivity.class);
                        intent2.addFlags(67108864);
                        context.startActivity(intent2);
                        qd1Var3.a.finish();
                        break;
                    case 3:
                        qd1 qd1Var4 = aAMLoginScannableActivity.I;
                        qd1Var4.j.w(loginUbiLogger$Page, LoginUbiLogger$Target.e);
                        l0 l0Var2 = (l0) qd1Var4.d;
                        l0Var2.getClass();
                        Context context2 = l0Var2.a;
                        Intent intent3 = new Intent(context2, (Class<?>) AAMLoginPinActivity.class);
                        intent3.setFlags(67108864);
                        context2.startActivity(intent3);
                        qd1Var4.a.finish();
                        break;
                    default:
                        int i32 = AAMLoginScannableActivity.W;
                        aAMLoginScannableActivity.finish();
                        break;
                }
            }
        });
        View viewFindViewById = findViewById(R.id.logo);
        View decorView = getWindow().getDecorView();
        p41 p41Var = new p41(viewFindViewById);
        WeakHashMap weakHashMap = zt2.a;
        ot2.u(decorView, p41Var);
        ((b) this.V).a();
    }

    @Override // p.t7, p.zw0, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        this.K.b();
    }

    @Override // p.t7, p.zw0, android.app.Activity
    public final void onStart() {
        super.onStart();
        qd1 qd1Var = this.I;
        if (qd1Var != null) {
            qd1Var.c();
        }
    }

    @Override // p.t7, p.zw0, android.app.Activity
    public final void onStop() {
        super.onStop();
        qd1 qd1Var = this.I;
        if (qd1Var != null) {
            ((c) qd1Var.e).d.d();
        }
    }
}
