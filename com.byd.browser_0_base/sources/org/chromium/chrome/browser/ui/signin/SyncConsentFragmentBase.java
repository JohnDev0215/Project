package org.chromium.chrome.browser.ui.signin;

import J.N;
import android.accounts.Account;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import defpackage.b1;
import defpackage.bi1;
import defpackage.bq;
import defpackage.bz;
import defpackage.ci1;
import defpackage.e1;
import defpackage.h62;
import defpackage.iq2;
import defpackage.lx;
import defpackage.n3;
import defpackage.nx2;
import defpackage.oj2;
import defpackage.p5;
import defpackage.px2;
import defpackage.qx2;
import defpackage.rx2;
import defpackage.sk;
import defpackage.sx2;
import defpackage.t5;
import defpackage.uh3;
import defpackage.un1;
import defpackage.z1;
import defpackage.z7;
import java.util.HashMap;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.R$dimen;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.b;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.signin.identitymanager.IdentityManager;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SyncConsentFragmentBase extends Fragment implements b1.a, z1, SigninManager.b {
    public static final /* synthetic */ int r0 = 0;
    public boolean a0;
    public SigninView b0;
    public SyncConsentView c0;
    public lx d0;
    public String f0;
    public h62 g0;
    public boolean h0;
    public boolean i0;
    public boolean j0;
    public boolean l0;
    public boolean m0;
    public int n0;
    public bi1 o0;
    public f p0;
    public e1 q0;
    public final AccountManagerFacade Z = AccountManagerFacadeProvider.getInstance();
    public final nx2 e0 = new h62.b() { // from class: nx2
        @Override // h62.b
        public final void n(String str) {
            int i = SyncConsentFragmentBase.r0;
            this.e.Q0(str);
        }
    };
    public final boolean k0 = true;

    public static void H0(SyncConsentFragmentBase syncConsentFragmentBase) {
        SyncConsentView syncConsentView = syncConsentFragmentBase.c0;
        if (syncConsentView == null) {
            syncConsentFragmentBase.b0.o.setVisibility(0);
            syncConsentFragmentBase.b0.q.setVisibility(8);
            syncConsentFragmentBase.b0.e.setScrolledToBottomObserver(null);
        } else {
            syncConsentView.i.setVisibility(0);
            syncConsentFragmentBase.c0.k.setVisibility(0);
            syncConsentFragmentBase.c0.j.setVisibility(8);
            syncConsentFragmentBase.c0.e.setScrolledToBottomObserver(null);
        }
    }

    public static Bundle K0(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("SyncConsentFragmentBase.SigninFlowType", 0);
        bundle.putInt("SyncConsentFragmentBase.AccessPoint", i);
        bundle.putString("SyncConsentFragmentBase.AccountName", str);
        return bundle;
    }

    @Override // defpackage.z1
    public final void F() {
        this.Z.b().g(new qx2(this, 0));
    }

    public final boolean I0() {
        if (this.e >= 7) {
            FragmentManager fragmentManager = this.v;
            if (!(fragmentManager == null ? false : fragmentManager.L()) && !this.h0 && this.k0) {
                return true;
            }
        }
        return false;
    }

    public abstract void J0(boolean z);

    public abstract void L0(Runnable runnable, String str, boolean z);

    public abstract void M0();

    public final void N0(boolean z) {
        int i = 2;
        if (z) {
            this.b0.g.setVisibility(this.m0 || (this.n0 == 0 && this.a0) ? 8 : 0);
            this.d0.b(this.b0.o, R$string.signin_accept_button);
            this.b0.o.setOnClickListener(new rx2(this, i));
        } else {
            this.b0.g.setVisibility(8);
            this.d0.b(this.b0.o, R$string.signin_add_account);
            this.b0.o.setOnClickListener(new sx2(this, i));
        }
        R0(z);
    }

    public final void O0(final Runnable runnable, final String str, final boolean z) {
        AccountManagerFacadeProvider.getInstance().b().g(new Callback() { // from class: ox2
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i = SyncConsentFragmentBase.r0;
                SyncConsentFragmentBase syncConsentFragmentBase = this.e;
                syncConsentFragmentBase.getClass();
                Account accountD = b.d(str, (List) obj);
                Runnable runnable2 = runnable;
                if (accountD == null) {
                    runnable2.run();
                    return;
                }
                z7 z7VarM = z7.m();
                Profile profileD = Profile.d();
                z7VarM.getClass();
                z7.D(profileD).d(syncConsentFragmentBase.n0, accountD, new vx2(syncConsentFragmentBase, z, runnable2));
            }
        });
    }

    public void P0(List<Account> list) {
        if ((this.e >= 7) && this.k0) {
            if (this.c0 != null) {
                if (this.j0) {
                    return;
                }
                String str = this.f0;
                if (!((str == null || org.chromium.components.signin.b.d(str, list) == null) ? false : true)) {
                    M0();
                    return;
                }
                String str2 = this.f0;
                this.f0 = str2;
                Q0(str2);
                return;
            }
            if (list.isEmpty()) {
                this.f0 = null;
                N0(false);
                return;
            }
            N0(true);
            String str3 = list.get(0).name;
            if (this.m0) {
                return;
            }
            String str4 = this.f0;
            if (str4 != null && org.chromium.components.signin.b.d(str4, list) != null) {
                String str5 = this.f0;
                this.f0 = str5;
                Q0(str5);
                return;
            }
            f fVar = this.p0;
            if (fVar != null) {
                fVar.a(false);
                this.p0 = null;
            }
            if (this.f0 != null && K() != null) {
                this.q0 = new e1(x0(), this, this.o0);
            }
            this.f0 = str3;
            Q0(str3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Q0(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = r8.f0
            boolean r9 = android.text.TextUtils.equals(r9, r0)
            if (r9 != 0) goto L9
            return
        L9:
            h62 r9 = r8.g0
            java.lang.String r0 = r8.f0
            fb0 r9 = r9.d(r0)
            org.chromium.chrome.browser.ui.signin.SyncConsentView r0 = r8.c0
            android.graphics.drawable.Drawable r1 = r9.b
            if (r0 == 0) goto L1d
            android.widget.ImageView r8 = r0.f
            r8.setImageDrawable(r1)
            return
        L1d:
            org.chromium.chrome.browser.ui.signin.SigninView r0 = r8.b0
            android.widget.ImageView r0 = r0.h
            r0.setImageDrawable(r1)
            boolean r0 = r9.e
            r1 = 0
            if (r0 != 0) goto L36
            sk r0 = defpackage.bq.a
            java.lang.String r0 = "HideNonDisplayableAccountEmail"
            boolean r0 = J.N.M09VlOh_(r0)
            if (r0 != 0) goto L34
            goto L36
        L34:
            r0 = r1
            goto L37
        L36:
            r0 = 1
        L37:
            lx r2 = r8.d0
            org.chromium.chrome.browser.ui.signin.SigninView r3 = r8.b0
            android.widget.TextView r3 = r3.i
            java.lang.String r4 = r9.c
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            java.lang.String r9 = r9.a
            if (r5 != 0) goto L49
            r5 = r4
            goto L57
        L49:
            if (r0 == 0) goto L4d
            r5 = r9
            goto L57
        L4d:
            int r5 = org.chromium.chrome.R$string.default_google_account_username
            android.content.res.Resources r6 = r8.N()
            java.lang.CharSequence r5 = r6.getText(r5)
        L57:
            r2.getClass()
            java.lang.String r6 = ""
            if (r5 == 0) goto L5f
            goto L60
        L5f:
            r5 = r6
        L60:
            r3.setText(r5)
            java.util.HashMap r2 = r2.b
            lx$a r7 = new lx$a
            java.lang.String r5 = r5.toString()
            r7.<init>(r5, r1)
            r2.put(r3, r7)
            r2 = 8
            if (r0 == 0) goto La5
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L7c
            goto L7d
        L7c:
            r2 = r1
        L7d:
            if (r2 != 0) goto L9d
            lx r0 = r8.d0
            org.chromium.chrome.browser.ui.signin.SigninView r3 = r8.b0
            android.widget.TextView r3 = r3.j
            r0.getClass()
            if (r9 == 0) goto L8b
            goto L8c
        L8b:
            r9 = r6
        L8c:
            r3.setText(r9)
            java.util.HashMap r0 = r0.b
            lx$a r4 = new lx$a
            java.lang.String r9 = r9.toString()
            r4.<init>(r9, r1)
            r0.put(r3, r4)
        L9d:
            org.chromium.chrome.browser.ui.signin.SigninView r8 = r8.b0
            android.widget.TextView r8 = r8.j
            r8.setVisibility(r2)
            goto Lac
        La5:
            org.chromium.chrome.browser.ui.signin.SigninView r8 = r8.b0
            android.widget.TextView r8 = r8.j
            r8.setVisibility(r2)
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase.Q0(java.lang.String):void");
    }

    public final void R0(boolean z) {
        iq2.a aVar = new iq2.a(z ? new un1(K(), new px2(this, 1)) : null, "<LINK1>", "</LINK1>");
        SyncConsentView syncConsentView = this.c0;
        if (syncConsentView != null) {
            lx lxVar = this.d0;
            TextView textView = syncConsentView.l;
            int i = R$string.sync_consent_details_description;
            SpannableString spannableStringA = iq2.a(lxVar.a.getText(i).toString(), aVar);
            textView.setText(spannableStringA);
            lxVar.b.put(textView, new lx.a(spannableStringA.toString(), i));
            return;
        }
        lx lxVar2 = this.d0;
        TextView textView2 = this.b0.n;
        int i2 = R$string.signin_details_description;
        SpannableString spannableStringA2 = iq2.a(lxVar2.a.getText(i2).toString(), aVar);
        textView2.setText(spannableStringA2);
        lxVar2.b.put(textView2, new lx.a(spannableStringA2.toString(), i2));
    }

    @Override // androidx.fragment.app.Fragment
    public final void Z(int i, int i2, Intent intent) {
        if (i == 1) {
            if (i2 != -1 || intent == null) {
                oj2.L(4);
            } else {
                oj2.L(2);
                String stringExtra = intent.getStringExtra("authAccount");
                if (stringExtra != null) {
                    this.f0 = stringExtra;
                } else {
                    oj2.L(5);
                }
            }
            int i3 = 0;
            if (this.i0) {
                this.j0 = false;
            }
            this.Z.b().g(new px2(this, i3));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void a0(Context context) {
        super.a0(context);
        this.o0 = ((ci1) D()).P0();
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.b
    public final void c() {
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        IdentityManager identityManagerV = z7.v(profileD);
        boolean z = false;
        CoreAccountInfo coreAccountInfoA = identityManagerV.a(0);
        if (this.n0 == 0 && coreAccountInfoA != null) {
            z = true;
        }
        this.m0 = z;
        if (z) {
            this.f0 = coreAccountInfoA.getEmail();
            this.Z.b().g(new qx2(this, 1));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void c0(Bundle bundle) {
        h62 h62VarC;
        super.c0(bundle);
        Bundle bundle2 = this.j;
        this.n0 = bundle2.getInt("SyncConsentFragmentBase.AccessPoint", 46);
        this.f0 = bundle2.getString("SyncConsentFragmentBase.AccountName", null);
        if (bundle2.containsKey("SyncConsentFragmentBase.ChildAccountStatus")) {
            this.a0 = bundle2.getBoolean("SyncConsentFragmentBase.ChildAccountStatus");
        } else {
            Profile profileD = Profile.d();
            this.a0 = N.MBL3czGJ(profileD.b, profileD);
        }
        int i = bundle2.getInt("SyncConsentFragmentBase.SigninFlowType", 0);
        boolean z = this.j.getBoolean("SyncConsentFragment.ShowTangibleSyncConsentView", false);
        this.i0 = z;
        if (bundle == null) {
            if (i == 1) {
                if (!z) {
                    this.q0 = new e1(x0(), this, this.o0);
                }
            } else if (i == 2) {
                u();
            }
        }
        this.d0 = new lx(N());
        if (this.a0) {
            Context contextX0 = x0();
            h62VarC = new h62(contextX0, contextX0.getResources().getDimensionPixelSize(R$dimen.user_picture_size), new h62.a(contextX0, R$drawable.ic_account_child_20dp));
        } else {
            h62VarC = h62.c(x0());
        }
        this.g0 = h62VarC;
        h62VarC.b(this.e0);
        z7 z7VarM = z7.m();
        Profile profileD2 = Profile.d();
        z7VarM.getClass();
        z7.D(profileD2).i(this);
        this.l0 = true;
        oj2.U(this.n0, 46, "Signin.SigninStartedAccessPoint");
        int i2 = this.n0;
        if (i2 != 38) {
            N.M3s_IHxy(i2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Drawable drawableF;
        int i;
        int i2;
        final int i3 = 1;
        final int i4 = 0;
        if (this.i0) {
            SyncConsentView syncConsentView = (SyncConsentView) layoutInflater.inflate(R$layout.sync_consent_view, viewGroup, false);
            this.c0 = syncConsentView;
            syncConsentView.i.setOnClickListener(new rx2(this, i3));
            this.c0.i.setVisibility(8);
            this.c0.k.setOnClickListener(new sx2(this, i3));
            this.c0.k.setVisibility(8);
            this.c0.j.setVisibility(0);
            this.c0.j.setOnClickListener(new View.OnClickListener(this) { // from class: org.chromium.chrome.browser.ui.signin.j
                public final /* synthetic */ SyncConsentFragmentBase f;

                {
                    this.f = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i5 = i3;
                    SyncConsentFragmentBase syncConsentFragmentBase = this.f;
                    switch (i5) {
                        case 0:
                            SigninScrollView signinScrollView = syncConsentFragmentBase.b0.e;
                            signinScrollView.smoothScrollBy(0, signinScrollView.getHeight());
                            n3.X0("Signin_MoreButton_Shown");
                            break;
                        default:
                            SigninScrollView signinScrollView2 = syncConsentFragmentBase.c0.e;
                            signinScrollView2.smoothScrollBy(0, signinScrollView2.getHeight());
                            n3.X0("Signin_MoreButton_Shown");
                            break;
                    }
                }
            });
            this.c0.e.setScrolledToBottomObserver(new Runnable(this) { // from class: tx2
                public final /* synthetic */ SyncConsentFragmentBase f;

                {
                    this.f = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i5 = i3;
                    SyncConsentFragmentBase syncConsentFragmentBase = this.f;
                    switch (i5) {
                        case 0:
                            SyncConsentFragmentBase.H0(syncConsentFragmentBase);
                            break;
                        default:
                            SyncConsentFragmentBase.H0(syncConsentFragmentBase);
                            break;
                    }
                }
            });
            this.c0.l.setMovementMethod(LinkMovementMethod.getInstance());
            R0(true);
        } else {
            SigninView signinView = (SigninView) layoutInflater.inflate(R$layout.signin_view, viewGroup, false);
            this.b0 = signinView;
            signinView.g.setOnClickListener(new rx2(this, i4));
            this.b0.p.setOnClickListener(new sx2(this, i4));
            this.b0.o.setVisibility(8);
            this.b0.q.setVisibility(0);
            this.b0.q.setOnClickListener(new View.OnClickListener(this) { // from class: org.chromium.chrome.browser.ui.signin.j
                public final /* synthetic */ SyncConsentFragmentBase f;

                {
                    this.f = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i5 = i4;
                    SyncConsentFragmentBase syncConsentFragmentBase = this.f;
                    switch (i5) {
                        case 0:
                            SigninScrollView signinScrollView = syncConsentFragmentBase.b0.e;
                            signinScrollView.smoothScrollBy(0, signinScrollView.getHeight());
                            n3.X0("Signin_MoreButton_Shown");
                            break;
                        default:
                            SigninScrollView signinScrollView2 = syncConsentFragmentBase.c0.e;
                            signinScrollView2.smoothScrollBy(0, signinScrollView2.getHeight());
                            n3.X0("Signin_MoreButton_Shown");
                            break;
                    }
                }
            });
            this.b0.e.setScrolledToBottomObserver(new Runnable(this) { // from class: tx2
                public final /* synthetic */ SyncConsentFragmentBase f;

                {
                    this.f = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i5 = i4;
                    SyncConsentFragmentBase syncConsentFragmentBase = this.f;
                    switch (i5) {
                        case 0:
                            SyncConsentFragmentBase.H0(syncConsentFragmentBase);
                            break;
                        default:
                            SyncConsentFragmentBase.H0(syncConsentFragmentBase);
                            break;
                    }
                }
            });
            this.b0.n.setMovementMethod(LinkMovementMethod.getInstance());
            if (this.a0) {
                drawableF = AppCompatResources.a(K(), org.chromium.components.browser_ui.widget.R$drawable.ic_check_googblue_24dp);
            } else {
                Context contextK = K();
                int i5 = R$drawable.ic_expand_more_black_24dp;
                int i6 = R$color.default_icon_color_tint_list;
                HashMap map = uh3.a;
                drawableF = uh3.f(contextK, i5, bz.b(contextK, i6));
            }
            this.b0.k.setImageDrawable(drawableF);
            N0(true);
        }
        SyncConsentView syncConsentView2 = this.c0;
        if (syncConsentView2 != null) {
            int i7 = R$string.no_thanks;
            lx lxVar = this.d0;
            TextView textView = syncConsentView2.g;
            sk skVar = bq.a;
            switch (N.M37SqSAy("TangibleSync", "group_id", 1)) {
                case 1:
                    i = R$string.history_sync_consent_title_a;
                    break;
                case 2:
                    i = R$string.history_sync_consent_title_b;
                    break;
                case 3:
                    i = R$string.history_sync_consent_title_c;
                    break;
                case 4:
                    i = R$string.history_sync_consent_title_d;
                    break;
                case 5:
                    i = R$string.history_sync_consent_title_e;
                    break;
                case 6:
                    i = R$string.signin_title;
                    break;
                default:
                    throw new IllegalStateException("Invalid group id");
            }
            lxVar.b(textView, i);
            lx lxVar2 = this.d0;
            TextView textView2 = this.c0.h;
            switch (N.M37SqSAy("TangibleSync", "group_id", 1)) {
                case 1:
                case 2:
                    i2 = R$string.history_sync_consent_subtitle_a;
                    break;
                case 3:
                    i2 = R$string.history_sync_consent_subtitle_c;
                    break;
                case 4:
                    i2 = R$string.history_sync_consent_subtitle_d;
                    break;
                case 5:
                    i2 = R$string.history_sync_consent_subtitle_e;
                    break;
                case 6:
                    i2 = R$string.signin_sync_title;
                    break;
                default:
                    throw new IllegalStateException("Invalid group id");
            }
            lxVar2.b(textView2, i2);
            this.d0.b(this.c0.i, i7);
            this.d0.b(this.c0.k, R$string.signin_accept_button);
            this.d0.b(this.c0.j, R$string.more);
        } else {
            int i8 = this.n0;
            int i9 = (i8 == 15 || i8 == 0) ? R$string.no_thanks : R$string.cancel;
            this.d0.b(this.b0.f, R$string.signin_title);
            this.d0.b(this.b0.l, R$string.signin_sync_title);
            this.d0.b(this.b0.m, R$string.signin_sync_description);
            this.d0.b(this.b0.p, i9);
            this.d0.b(this.b0.q, R$string.more);
        }
        SyncConsentView syncConsentView3 = this.c0;
        return syncConsentView3 != null ? syncConsentView3 : this.b0;
    }

    @Override // androidx.fragment.app.Fragment
    public final void f0() {
        this.H = true;
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        z7.D(profileD).n(this);
        this.g0.e(this.e0);
        f fVar = this.p0;
        if (fVar != null) {
            fVar.a(true);
            this.p0 = null;
        }
        this.o0.a();
        if (this.l0) {
            n3.X0("Signin_Undo_Signin");
        }
    }

    @Override // b1.a
    public final void l(String str) {
        this.f0 = str;
        Q0(str);
        e1 e1Var = this.q0;
        e1Var.b.b(3, e1Var.c);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public final void l0() {
        t5.a aVar;
        this.H = true;
        this.Z.j(this);
        SigninView signinView = this.b0;
        if (signinView != null) {
            t5 t5Var = signinView.r;
            if (t5Var.d) {
                Animatable animatable = t5Var.b;
                Drawable drawable = (Drawable) animatable;
                int i = p5.k;
                if (drawable != null && (aVar = t5Var.c) != null && (drawable instanceof Animatable)) {
                    ((AnimatedVectorDrawable) drawable).unregisterAnimationCallback(aVar.a());
                }
                animatable.stop();
                t5Var.d = false;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        t5.a aVar;
        this.H = true;
        AccountManagerFacade accountManagerFacade = this.Z;
        accountManagerFacade.a(this);
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        IdentityManager identityManagerV = z7.v(profileD);
        boolean z = false;
        CoreAccountInfo coreAccountInfoA = identityManagerV.a(0);
        if (this.n0 == 0 && coreAccountInfoA != null) {
            z = true;
        }
        this.m0 = z;
        if (z) {
            this.f0 = coreAccountInfoA.getEmail();
        }
        String str = this.f0;
        if (str != null) {
            Q0(str);
        }
        P0(org.chromium.components.signin.b.e(accountManagerFacade.b()));
        SigninView signinView = this.b0;
        if (signinView != null) {
            t5 t5Var = signinView.r;
            t5Var.getClass();
            if (ValueAnimator.areAnimatorsEnabled()) {
                Animatable animatable = t5Var.b;
                Drawable drawable = (Drawable) animatable;
                int i = p5.k;
                if (drawable != null && (aVar = t5Var.c) != null && (drawable instanceof Animatable)) {
                    ((AnimatedVectorDrawable) drawable).registerAnimationCallback(aVar.a());
                }
                animatable.start();
                t5Var.d = true;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        this.b0.o.setEnabled(false);
    }

    @Override // b1.a
    public final void u() {
        this.j0 = true;
        oj2.L(0);
        this.Z.k();
        e1 e1Var = this.q0;
        if (e1Var != null) {
            e1Var.b.b(3, e1Var.c);
        }
    }
}
