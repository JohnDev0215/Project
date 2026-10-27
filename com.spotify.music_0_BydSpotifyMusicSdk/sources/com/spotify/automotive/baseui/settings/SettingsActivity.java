package com.spotify.automotive.baseui.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.baseui.settings.LicensesActivity;
import com.spotify.automotive.baseui.settings.SettingsActivity;
import com.spotify.automotive.socialconnect.SocialConnectController;
import com.spotify.base.java.logging.Logger;
import com.spotify.music.R;
import p.ak;
import p.gc2;
import p.gq;
import p.h6;
import p.kk;
import p.m60;
import p.mc2;
import p.mu0;
import p.o23;
import p.oh1;
import p.pz;
import p.r50;
import p.sz;
import p.zb0;

/* JADX INFO: loaded from: classes.dex */
public final class SettingsActivity extends r50 implements mu0, mc2 {
    public static final /* synthetic */ int W = 0;
    public gq H;
    public Spinner I;
    public o23 J;
    public ak K;
    public View L;
    public View M;
    public View N;
    public ImageView O;
    public View P;
    public View Q;
    public TextView R;
    public TextView S;
    public TextView T;
    public m60 U;
    public zb0 V;

    @Override // p.r50, p.zw0, androidx.activity.a, p.wv, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.settings_title);
        final int i = 0;
        toolbar.setNavigationOnClickListener(new View.OnClickListener(this) { // from class: p.dc2
            public final /* synthetic */ SettingsActivity e;

            {
                this.e = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i2 = i;
                int i3 = 0;
                SettingsActivity settingsActivity = this.e;
                switch (i2) {
                    case 0:
                        int i4 = SettingsActivity.W;
                        settingsActivity.finish();
                        break;
                    case 1:
                        final SettingsActivity settingsActivity2 = (SettingsActivity) ((mc2) settingsActivity.J.r);
                        settingsActivity2.getClass();
                        m90 m90Var = new m90(settingsActivity2);
                        m90Var.e = settingsActivity2.getString(R.string.are_you_sure_you_want_to_logout);
                        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: p.ec2
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i5) {
                                o23 o23Var = settingsActivity2.J;
                                o23Var.getClass();
                                Logger.e("User requested to log out.", new Object[0]);
                                ((io.reactivex.rxjava3.disposables.a) o23Var.c).c(((com.spotify.automotive.auth.b) ((ad1) o23Var.e)).a(LoginState.k).subscribe(new ot(4), new ed1(17)));
                                dialogInterface.dismiss();
                            }
                        };
                        m90Var.f = settingsActivity2.getString(R.string.logout);
                        m90Var.g = onClickListener;
                        fc2 fc2Var = new fc2();
                        m90Var.h = settingsActivity2.getString(android.R.string.cancel);
                        m90Var.i = fc2Var;
                        gq gqVar = new gq(settingsActivity2, m90Var);
                        gqVar.setCancelable(m90Var.a);
                        gqVar.setCanceledOnTouchOutside(m90Var.a);
                        gqVar.setOnCancelListener((DialogInterface.OnCancelListener) m90Var.j);
                        gqVar.setOnDismissListener((DialogInterface.OnDismissListener) m90Var.k);
                        settingsActivity2.H = gqVar;
                        gqVar.show();
                        break;
                    case 2:
                        o23 o23Var = settingsActivity.J;
                        o23Var.getClass();
                        Logger.e("Starting social connect session.", new Object[0]);
                        SettingsActivity settingsActivity3 = (SettingsActivity) ((mc2) o23Var.r);
                        settingsActivity3.L.setVisibility(0);
                        settingsActivity3.N.setVisibility(0);
                        settingsActivity3.Q.setVisibility(8);
                        settingsActivity3.M.setVisibility(8);
                        settingsActivity3.O.setVisibility(8);
                        settingsActivity3.P.setVisibility(0);
                        settingsActivity3.T.setVisibility(8);
                        ((io.reactivex.rxjava3.disposables.a) o23Var.c).c(((SocialConnectController) o23Var.o).getOrCreateSession().observeOn(io.reactivex.rxjava3.android.schedulers.c.a()).subscribe(new lc2(o23Var, i3), new lc2(o23Var, 1)));
                        break;
                    case 3:
                        o23 o23Var2 = settingsActivity.J;
                        o23Var2.getClass();
                        Logger.e("Leaving social connect session.", new Object[0]);
                        ((SettingsActivity) ((mc2) o23Var2.r)).w();
                        ((io.reactivex.rxjava3.disposables.a) o23Var2.c).c(((SocialConnectController) o23Var2.o).leaveSession().subscribe(new ot(3), new ed1(16)));
                        break;
                    case 4:
                        l0 l0Var = (l0) ((k0) settingsActivity.J.m);
                        l0Var.getClass();
                        Context context = l0Var.a;
                        context.startActivity(new Intent(context, (Class<?>) LicensesActivity.class));
                        break;
                    default:
                        settingsActivity.J.getClass();
                        break;
                }
            }
        });
        ak akVar = new ak(getApplicationContext(), new oh1(25, this));
        this.K = akVar;
        akVar.a();
        this.L = findViewById(R.id.social_connect_layout);
        this.M = findViewById(R.id.social_connect_button_leave);
        this.N = findViewById(R.id.social_connect_progress);
        this.O = (ImageView) findViewById(R.id.social_connect_scannable);
        this.P = findViewById(R.id.social_connect_scannable_layout);
        this.Q = findViewById(R.id.social_connect_button_start);
        this.R = (TextView) findViewById(R.id.social_connect_subtitle);
        this.S = (TextView) findViewById(R.id.social_connect_title);
        this.T = (TextView) findViewById(R.id.social_connect_error);
        final int i2 = 1;
        findViewById(R.id.logout_button).setOnClickListener(new View.OnClickListener(this) { // from class: p.dc2
            public final /* synthetic */ SettingsActivity e;

            {
                this.e = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i2;
                int i3 = 0;
                SettingsActivity settingsActivity = this.e;
                switch (i22) {
                    case 0:
                        int i4 = SettingsActivity.W;
                        settingsActivity.finish();
                        break;
                    case 1:
                        final SettingsActivity settingsActivity2 = (SettingsActivity) ((mc2) settingsActivity.J.r);
                        settingsActivity2.getClass();
                        m90 m90Var = new m90(settingsActivity2);
                        m90Var.e = settingsActivity2.getString(R.string.are_you_sure_you_want_to_logout);
                        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: p.ec2
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i5) {
                                o23 o23Var = settingsActivity2.J;
                                o23Var.getClass();
                                Logger.e("User requested to log out.", new Object[0]);
                                ((io.reactivex.rxjava3.disposables.a) o23Var.c).c(((com.spotify.automotive.auth.b) ((ad1) o23Var.e)).a(LoginState.k).subscribe(new ot(4), new ed1(17)));
                                dialogInterface.dismiss();
                            }
                        };
                        m90Var.f = settingsActivity2.getString(R.string.logout);
                        m90Var.g = onClickListener;
                        fc2 fc2Var = new fc2();
                        m90Var.h = settingsActivity2.getString(android.R.string.cancel);
                        m90Var.i = fc2Var;
                        gq gqVar = new gq(settingsActivity2, m90Var);
                        gqVar.setCancelable(m90Var.a);
                        gqVar.setCanceledOnTouchOutside(m90Var.a);
                        gqVar.setOnCancelListener((DialogInterface.OnCancelListener) m90Var.j);
                        gqVar.setOnDismissListener((DialogInterface.OnDismissListener) m90Var.k);
                        settingsActivity2.H = gqVar;
                        gqVar.show();
                        break;
                    case 2:
                        o23 o23Var = settingsActivity.J;
                        o23Var.getClass();
                        Logger.e("Starting social connect session.", new Object[0]);
                        SettingsActivity settingsActivity3 = (SettingsActivity) ((mc2) o23Var.r);
                        settingsActivity3.L.setVisibility(0);
                        settingsActivity3.N.setVisibility(0);
                        settingsActivity3.Q.setVisibility(8);
                        settingsActivity3.M.setVisibility(8);
                        settingsActivity3.O.setVisibility(8);
                        settingsActivity3.P.setVisibility(0);
                        settingsActivity3.T.setVisibility(8);
                        ((io.reactivex.rxjava3.disposables.a) o23Var.c).c(((SocialConnectController) o23Var.o).getOrCreateSession().observeOn(io.reactivex.rxjava3.android.schedulers.c.a()).subscribe(new lc2(o23Var, i3), new lc2(o23Var, 1)));
                        break;
                    case 3:
                        o23 o23Var2 = settingsActivity.J;
                        o23Var2.getClass();
                        Logger.e("Leaving social connect session.", new Object[0]);
                        ((SettingsActivity) ((mc2) o23Var2.r)).w();
                        ((io.reactivex.rxjava3.disposables.a) o23Var2.c).c(((SocialConnectController) o23Var2.o).leaveSession().subscribe(new ot(3), new ed1(16)));
                        break;
                    case 4:
                        l0 l0Var = (l0) ((k0) settingsActivity.J.m);
                        l0Var.getClass();
                        Context context = l0Var.a;
                        context.startActivity(new Intent(context, (Class<?>) LicensesActivity.class));
                        break;
                    default:
                        settingsActivity.J.getClass();
                        break;
                }
            }
        });
        final int i3 = 2;
        this.Q.setOnClickListener(new View.OnClickListener(this) { // from class: p.dc2
            public final /* synthetic */ SettingsActivity e;

            {
                this.e = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i3;
                int i32 = 0;
                SettingsActivity settingsActivity = this.e;
                switch (i22) {
                    case 0:
                        int i4 = SettingsActivity.W;
                        settingsActivity.finish();
                        break;
                    case 1:
                        final SettingsActivity settingsActivity2 = (SettingsActivity) ((mc2) settingsActivity.J.r);
                        settingsActivity2.getClass();
                        m90 m90Var = new m90(settingsActivity2);
                        m90Var.e = settingsActivity2.getString(R.string.are_you_sure_you_want_to_logout);
                        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: p.ec2
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i5) {
                                o23 o23Var = settingsActivity2.J;
                                o23Var.getClass();
                                Logger.e("User requested to log out.", new Object[0]);
                                ((io.reactivex.rxjava3.disposables.a) o23Var.c).c(((com.spotify.automotive.auth.b) ((ad1) o23Var.e)).a(LoginState.k).subscribe(new ot(4), new ed1(17)));
                                dialogInterface.dismiss();
                            }
                        };
                        m90Var.f = settingsActivity2.getString(R.string.logout);
                        m90Var.g = onClickListener;
                        fc2 fc2Var = new fc2();
                        m90Var.h = settingsActivity2.getString(android.R.string.cancel);
                        m90Var.i = fc2Var;
                        gq gqVar = new gq(settingsActivity2, m90Var);
                        gqVar.setCancelable(m90Var.a);
                        gqVar.setCanceledOnTouchOutside(m90Var.a);
                        gqVar.setOnCancelListener((DialogInterface.OnCancelListener) m90Var.j);
                        gqVar.setOnDismissListener((DialogInterface.OnDismissListener) m90Var.k);
                        settingsActivity2.H = gqVar;
                        gqVar.show();
                        break;
                    case 2:
                        o23 o23Var = settingsActivity.J;
                        o23Var.getClass();
                        Logger.e("Starting social connect session.", new Object[0]);
                        SettingsActivity settingsActivity3 = (SettingsActivity) ((mc2) o23Var.r);
                        settingsActivity3.L.setVisibility(0);
                        settingsActivity3.N.setVisibility(0);
                        settingsActivity3.Q.setVisibility(8);
                        settingsActivity3.M.setVisibility(8);
                        settingsActivity3.O.setVisibility(8);
                        settingsActivity3.P.setVisibility(0);
                        settingsActivity3.T.setVisibility(8);
                        ((io.reactivex.rxjava3.disposables.a) o23Var.c).c(((SocialConnectController) o23Var.o).getOrCreateSession().observeOn(io.reactivex.rxjava3.android.schedulers.c.a()).subscribe(new lc2(o23Var, i32), new lc2(o23Var, 1)));
                        break;
                    case 3:
                        o23 o23Var2 = settingsActivity.J;
                        o23Var2.getClass();
                        Logger.e("Leaving social connect session.", new Object[0]);
                        ((SettingsActivity) ((mc2) o23Var2.r)).w();
                        ((io.reactivex.rxjava3.disposables.a) o23Var2.c).c(((SocialConnectController) o23Var2.o).leaveSession().subscribe(new ot(3), new ed1(16)));
                        break;
                    case 4:
                        l0 l0Var = (l0) ((k0) settingsActivity.J.m);
                        l0Var.getClass();
                        Context context = l0Var.a;
                        context.startActivity(new Intent(context, (Class<?>) LicensesActivity.class));
                        break;
                    default:
                        settingsActivity.J.getClass();
                        break;
                }
            }
        });
        final int i4 = 3;
        this.M.setOnClickListener(new View.OnClickListener(this) { // from class: p.dc2
            public final /* synthetic */ SettingsActivity e;

            {
                this.e = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i4;
                int i32 = 0;
                SettingsActivity settingsActivity = this.e;
                switch (i22) {
                    case 0:
                        int i42 = SettingsActivity.W;
                        settingsActivity.finish();
                        break;
                    case 1:
                        final SettingsActivity settingsActivity2 = (SettingsActivity) ((mc2) settingsActivity.J.r);
                        settingsActivity2.getClass();
                        m90 m90Var = new m90(settingsActivity2);
                        m90Var.e = settingsActivity2.getString(R.string.are_you_sure_you_want_to_logout);
                        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: p.ec2
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i5) {
                                o23 o23Var = settingsActivity2.J;
                                o23Var.getClass();
                                Logger.e("User requested to log out.", new Object[0]);
                                ((io.reactivex.rxjava3.disposables.a) o23Var.c).c(((com.spotify.automotive.auth.b) ((ad1) o23Var.e)).a(LoginState.k).subscribe(new ot(4), new ed1(17)));
                                dialogInterface.dismiss();
                            }
                        };
                        m90Var.f = settingsActivity2.getString(R.string.logout);
                        m90Var.g = onClickListener;
                        fc2 fc2Var = new fc2();
                        m90Var.h = settingsActivity2.getString(android.R.string.cancel);
                        m90Var.i = fc2Var;
                        gq gqVar = new gq(settingsActivity2, m90Var);
                        gqVar.setCancelable(m90Var.a);
                        gqVar.setCanceledOnTouchOutside(m90Var.a);
                        gqVar.setOnCancelListener((DialogInterface.OnCancelListener) m90Var.j);
                        gqVar.setOnDismissListener((DialogInterface.OnDismissListener) m90Var.k);
                        settingsActivity2.H = gqVar;
                        gqVar.show();
                        break;
                    case 2:
                        o23 o23Var = settingsActivity.J;
                        o23Var.getClass();
                        Logger.e("Starting social connect session.", new Object[0]);
                        SettingsActivity settingsActivity3 = (SettingsActivity) ((mc2) o23Var.r);
                        settingsActivity3.L.setVisibility(0);
                        settingsActivity3.N.setVisibility(0);
                        settingsActivity3.Q.setVisibility(8);
                        settingsActivity3.M.setVisibility(8);
                        settingsActivity3.O.setVisibility(8);
                        settingsActivity3.P.setVisibility(0);
                        settingsActivity3.T.setVisibility(8);
                        ((io.reactivex.rxjava3.disposables.a) o23Var.c).c(((SocialConnectController) o23Var.o).getOrCreateSession().observeOn(io.reactivex.rxjava3.android.schedulers.c.a()).subscribe(new lc2(o23Var, i32), new lc2(o23Var, 1)));
                        break;
                    case 3:
                        o23 o23Var2 = settingsActivity.J;
                        o23Var2.getClass();
                        Logger.e("Leaving social connect session.", new Object[0]);
                        ((SettingsActivity) ((mc2) o23Var2.r)).w();
                        ((io.reactivex.rxjava3.disposables.a) o23Var2.c).c(((SocialConnectController) o23Var2.o).leaveSession().subscribe(new ot(3), new ed1(16)));
                        break;
                    case 4:
                        l0 l0Var = (l0) ((k0) settingsActivity.J.m);
                        l0Var.getClass();
                        Context context = l0Var.a;
                        context.startActivity(new Intent(context, (Class<?>) LicensesActivity.class));
                        break;
                    default:
                        settingsActivity.J.getClass();
                        break;
                }
            }
        });
        final int i5 = 4;
        findViewById(R.id.third_party_licenses_button).setOnClickListener(new View.OnClickListener(this) { // from class: p.dc2
            public final /* synthetic */ SettingsActivity e;

            {
                this.e = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i5;
                int i32 = 0;
                SettingsActivity settingsActivity = this.e;
                switch (i22) {
                    case 0:
                        int i42 = SettingsActivity.W;
                        settingsActivity.finish();
                        break;
                    case 1:
                        final SettingsActivity settingsActivity2 = (SettingsActivity) ((mc2) settingsActivity.J.r);
                        settingsActivity2.getClass();
                        m90 m90Var = new m90(settingsActivity2);
                        m90Var.e = settingsActivity2.getString(R.string.are_you_sure_you_want_to_logout);
                        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: p.ec2
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i52) {
                                o23 o23Var = settingsActivity2.J;
                                o23Var.getClass();
                                Logger.e("User requested to log out.", new Object[0]);
                                ((io.reactivex.rxjava3.disposables.a) o23Var.c).c(((com.spotify.automotive.auth.b) ((ad1) o23Var.e)).a(LoginState.k).subscribe(new ot(4), new ed1(17)));
                                dialogInterface.dismiss();
                            }
                        };
                        m90Var.f = settingsActivity2.getString(R.string.logout);
                        m90Var.g = onClickListener;
                        fc2 fc2Var = new fc2();
                        m90Var.h = settingsActivity2.getString(android.R.string.cancel);
                        m90Var.i = fc2Var;
                        gq gqVar = new gq(settingsActivity2, m90Var);
                        gqVar.setCancelable(m90Var.a);
                        gqVar.setCanceledOnTouchOutside(m90Var.a);
                        gqVar.setOnCancelListener((DialogInterface.OnCancelListener) m90Var.j);
                        gqVar.setOnDismissListener((DialogInterface.OnDismissListener) m90Var.k);
                        settingsActivity2.H = gqVar;
                        gqVar.show();
                        break;
                    case 2:
                        o23 o23Var = settingsActivity.J;
                        o23Var.getClass();
                        Logger.e("Starting social connect session.", new Object[0]);
                        SettingsActivity settingsActivity3 = (SettingsActivity) ((mc2) o23Var.r);
                        settingsActivity3.L.setVisibility(0);
                        settingsActivity3.N.setVisibility(0);
                        settingsActivity3.Q.setVisibility(8);
                        settingsActivity3.M.setVisibility(8);
                        settingsActivity3.O.setVisibility(8);
                        settingsActivity3.P.setVisibility(0);
                        settingsActivity3.T.setVisibility(8);
                        ((io.reactivex.rxjava3.disposables.a) o23Var.c).c(((SocialConnectController) o23Var.o).getOrCreateSession().observeOn(io.reactivex.rxjava3.android.schedulers.c.a()).subscribe(new lc2(o23Var, i32), new lc2(o23Var, 1)));
                        break;
                    case 3:
                        o23 o23Var2 = settingsActivity.J;
                        o23Var2.getClass();
                        Logger.e("Leaving social connect session.", new Object[0]);
                        ((SettingsActivity) ((mc2) o23Var2.r)).w();
                        ((io.reactivex.rxjava3.disposables.a) o23Var2.c).c(((SocialConnectController) o23Var2.o).leaveSession().subscribe(new ot(3), new ed1(16)));
                        break;
                    case 4:
                        l0 l0Var = (l0) ((k0) settingsActivity.J.m);
                        l0Var.getClass();
                        Context context = l0Var.a;
                        context.startActivity(new Intent(context, (Class<?>) LicensesActivity.class));
                        break;
                    default:
                        settingsActivity.J.getClass();
                        break;
                }
            }
        });
        final int i6 = 5;
        findViewById(R.id.logo).setOnClickListener(new View.OnClickListener(this) { // from class: p.dc2
            public final /* synthetic */ SettingsActivity e;

            {
                this.e = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i6;
                int i32 = 0;
                SettingsActivity settingsActivity = this.e;
                switch (i22) {
                    case 0:
                        int i42 = SettingsActivity.W;
                        settingsActivity.finish();
                        break;
                    case 1:
                        final SettingsActivity settingsActivity2 = (SettingsActivity) ((mc2) settingsActivity.J.r);
                        settingsActivity2.getClass();
                        m90 m90Var = new m90(settingsActivity2);
                        m90Var.e = settingsActivity2.getString(R.string.are_you_sure_you_want_to_logout);
                        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: p.ec2
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i52) {
                                o23 o23Var = settingsActivity2.J;
                                o23Var.getClass();
                                Logger.e("User requested to log out.", new Object[0]);
                                ((io.reactivex.rxjava3.disposables.a) o23Var.c).c(((com.spotify.automotive.auth.b) ((ad1) o23Var.e)).a(LoginState.k).subscribe(new ot(4), new ed1(17)));
                                dialogInterface.dismiss();
                            }
                        };
                        m90Var.f = settingsActivity2.getString(R.string.logout);
                        m90Var.g = onClickListener;
                        fc2 fc2Var = new fc2();
                        m90Var.h = settingsActivity2.getString(android.R.string.cancel);
                        m90Var.i = fc2Var;
                        gq gqVar = new gq(settingsActivity2, m90Var);
                        gqVar.setCancelable(m90Var.a);
                        gqVar.setCanceledOnTouchOutside(m90Var.a);
                        gqVar.setOnCancelListener((DialogInterface.OnCancelListener) m90Var.j);
                        gqVar.setOnDismissListener((DialogInterface.OnDismissListener) m90Var.k);
                        settingsActivity2.H = gqVar;
                        gqVar.show();
                        break;
                    case 2:
                        o23 o23Var = settingsActivity.J;
                        o23Var.getClass();
                        Logger.e("Starting social connect session.", new Object[0]);
                        SettingsActivity settingsActivity3 = (SettingsActivity) ((mc2) o23Var.r);
                        settingsActivity3.L.setVisibility(0);
                        settingsActivity3.N.setVisibility(0);
                        settingsActivity3.Q.setVisibility(8);
                        settingsActivity3.M.setVisibility(8);
                        settingsActivity3.O.setVisibility(8);
                        settingsActivity3.P.setVisibility(0);
                        settingsActivity3.T.setVisibility(8);
                        ((io.reactivex.rxjava3.disposables.a) o23Var.c).c(((SocialConnectController) o23Var.o).getOrCreateSession().observeOn(io.reactivex.rxjava3.android.schedulers.c.a()).subscribe(new lc2(o23Var, i32), new lc2(o23Var, 1)));
                        break;
                    case 3:
                        o23 o23Var2 = settingsActivity.J;
                        o23Var2.getClass();
                        Logger.e("Leaving social connect session.", new Object[0]);
                        ((SettingsActivity) ((mc2) o23Var2.r)).w();
                        ((io.reactivex.rxjava3.disposables.a) o23Var2.c).c(((SocialConnectController) o23Var2.o).leaveSession().subscribe(new ot(3), new ed1(16)));
                        break;
                    case 4:
                        l0 l0Var = (l0) ((k0) settingsActivity.J.m);
                        l0Var.getClass();
                        Context context = l0Var.a;
                        context.startActivity(new Intent(context, (Class<?>) LicensesActivity.class));
                        break;
                    default:
                        settingsActivity.J.getClass();
                        break;
                }
            }
        });
        h6 h6Var = new h6(this);
        Spinner spinner = (Spinner) findViewById(R.id.music_quality_spinner);
        this.I = spinner;
        spinner.setAdapter((SpinnerAdapter) h6Var);
        this.I.setOnItemSelectedListener(new gc2(this, h6Var));
        TextView textView = (TextView) findViewById(R.id.app_version);
        int i7 = kk.a;
        textView.setText("4.4.1.0");
        Logger.b("Spotify AAM Version = 4.4.1.0", new Object[0]);
    }

    @Override // p.t7, p.zw0, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        this.K.b();
        gq gqVar = this.H;
        if (gqVar != null) {
            gqVar.dismiss();
            this.H = null;
        }
    }

    public final void w() {
        this.L.setVisibility(0);
        this.Q.setVisibility(0);
        this.M.setVisibility(8);
        this.P.setVisibility(8);
        this.R.setText(R.string.social_connect_subtitle_inactive);
        this.S.setText(R.string.social_connect_title_inactive);
    }

    public final void x(String str) {
        ImageView imageView = this.O;
        zb0 zb0Var = this.V;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.settings_social_connect_scannable_size);
        Object obj = sz.a;
        int iA = pz.a(this, R.color.scannable_foreground);
        int iA2 = pz.a(this, R.color.scannable_background);
        zb0Var.getClass();
        imageView.setImageBitmap(zb0.v(dimensionPixelSize, iA, iA2, str));
        this.L.setVisibility(0);
        this.N.setVisibility(8);
        this.Q.setVisibility(8);
        this.M.setVisibility(0);
        this.O.setVisibility(0);
        this.P.setVisibility(0);
        this.R.setText(R.string.social_connect_subtitle_active);
        this.S.setText(R.string.social_connect_title_active);
    }
}
