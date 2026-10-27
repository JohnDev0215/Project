package org.chromium.chrome.browser.privacy_guide;

import J.N;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.e;
import defpackage.by1;
import defpackage.gs;
import defpackage.ie0;
import defpackage.ji;
import defpackage.n3;
import defpackage.oj2;
import defpackage.pq2;
import defpackage.r42;
import defpackage.s42;
import defpackage.sk1;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$menu;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.privacy_guide.PrivacyGuideFragment;
import org.chromium.chrome.browser.privacy_guide.a;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacyGuideFragment extends Fragment {
    public static final /* synthetic */ int n0 = 0;
    public ji Z;
    public a.InterfaceC0110a a0;
    public SettingsLauncher b0;
    public s42 c0;
    public View d0;
    public ViewPager2 e0;
    public TabLayout f0;
    public ButtonCompat g0;
    public ButtonCompat h0;
    public ButtonCompat i0;
    public ButtonCompat j0;
    public ButtonCompat k0;
    public r42 l0;
    public sk1 m0;

    public final void H0() {
        int currentItem = this.e0.getCurrentItem();
        int i = currentItem + 1;
        this.e0.setCurrentItem(i);
        J0();
        I0(currentItem, i);
    }

    public final void I0(int i, int i2) {
        int i3;
        int i4;
        if (i > i2) {
            int iK = this.c0.K(i);
            if (iK == 2) {
                n3.X0("Settings.PrivacyGuide.BackClickHistorySync");
            } else if (iK == 3) {
                n3.X0("Settings.PrivacyGuide.BackClickSafeBrowsing");
            } else if (iK == 4) {
                n3.X0("Settings.PrivacyGuide.BackClickCookies");
            }
        } else {
            r42 r42Var = this.l0;
            int iK2 = this.c0.K(i);
            r42Var.getClass();
            if (iK2 == 0) {
                n3.X0("Settings.PrivacyGuide.NextClickWelcome");
                oj2.U(0, 9, "Settings.PrivacyGuide.NextNavigation");
            } else if (iK2 == 1) {
                boolean zMfmn09fr = N.Mfmn09fr(Profile.d());
                oj2.U((r42Var.a.booleanValue() && zMfmn09fr) ? 0 : (!r42Var.a.booleanValue() || zMfmn09fr) ? (r42Var.a.booleanValue() || !zMfmn09fr) ? 3 : 2 : 1, 15, "Settings.PrivacyGuide.SettingsStates");
                n3.X0("Settings.PrivacyGuide.NextClickMSBB");
                oj2.U(1, 9, "Settings.PrivacyGuide.NextNavigation");
            } else if (iK2 == 2) {
                r42Var.b.booleanValue();
                if (r42Var.b.booleanValue()) {
                    i3 = 9;
                } else {
                    r42Var.b.booleanValue();
                    i3 = 11;
                }
                oj2.U(i3, 15, "Settings.PrivacyGuide.SettingsStates");
                n3.X0("Settings.PrivacyGuide.NextClickHistorySync");
                oj2.U(2, 9, "Settings.PrivacyGuide.NextNavigation");
            } else if (iK2 == 3) {
                int iMdyQjr8h = N.MdyQjr8h();
                boolean z = r42Var.c.intValue() == 2;
                i4 = iMdyQjr8h == 2 ? 1 : 0;
                oj2.U((!z || i4 == 0) ? (z && i4 == 0) ? 13 : (z || i4 == 0) ? 15 : 14 : 12, 15, "Settings.PrivacyGuide.SettingsStates");
                n3.X0("Settings.PrivacyGuide.NextClickSafeBrowsing");
                oj2.U(3, 9, "Settings.PrivacyGuide.NextNavigation");
            } else if (iK2 == 4) {
                int iA = a.a();
                boolean z2 = r42Var.d.intValue() == 2;
                i4 = iA == 2 ? 1 : 0;
                oj2.U((!z2 || i4 == 0) ? (z2 && i4 == 0) ? 5 : (z2 || i4 == 0) ? 7 : 6 : 4, 15, "Settings.PrivacyGuide.SettingsStates");
                n3.X0("Settings.PrivacyGuide.NextClickCookies");
                oj2.U(4, 9, "Settings.PrivacyGuide.NextNavigation");
            }
        }
        r42 r42Var2 = this.l0;
        int iK3 = this.c0.K(i2);
        r42Var2.getClass();
        if (iK3 != 0) {
            if (iK3 == 1) {
                r42Var2.a = Boolean.valueOf(N.Mfmn09fr(Profile.d()));
                return;
            }
            if (iK3 == 2) {
                r42Var2.b = Boolean.FALSE;
            } else if (iK3 == 3) {
                r42Var2.c = Integer.valueOf(N.MdyQjr8h());
            } else {
                if (iK3 != 4) {
                    return;
                }
                r42Var2.d = Integer.valueOf(a.a());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void J0() {
        /*
            r7 = this;
            androidx.viewpager2.widget.ViewPager2 r0 = r7.e0
            int r0 = r0.getCurrentItem()
            org.chromium.ui.widget.ButtonCompat r1 = r7.g0
            sk1 r2 = r7.m0
            r2.getClass()
            r2 = 1
            r3 = 0
            if (r0 != 0) goto L13
            r4 = r2
            goto L14
        L13:
            r4 = r3
        L14:
            r5 = 8
            if (r4 == 0) goto L1a
            r4 = r3
            goto L1b
        L1a:
            r4 = r5
        L1b:
            r1.setVisibility(r4)
            org.chromium.ui.widget.ButtonCompat r1 = r7.h0
            sk1 r4 = r7.m0
            if (r0 <= 0) goto L2b
            int r6 = r4.a
            int r6 = r6 - r2
            if (r0 >= r6) goto L2e
            r6 = r2
            goto L2f
        L2b:
            r4.getClass()
        L2e:
            r6 = r3
        L2f:
            if (r6 == 0) goto L3e
            int r4 = r4.a
            int r4 = r4 + (-2)
            if (r0 != r4) goto L39
            r4 = r2
            goto L3a
        L39:
            r4 = r3
        L3a:
            if (r4 != 0) goto L3e
            r4 = r3
            goto L3f
        L3e:
            r4 = r5
        L3f:
            r1.setVisibility(r4)
            org.chromium.ui.widget.ButtonCompat r1 = r7.i0
            sk1 r4 = r7.m0
            if (r0 <= 0) goto L4f
            int r4 = r4.a
            int r4 = r4 - r2
            if (r0 >= r4) goto L52
            r4 = r2
            goto L53
        L4f:
            r4.getClass()
        L52:
            r4 = r3
        L53:
            if (r4 == 0) goto L60
            if (r0 != r2) goto L59
            r4 = r2
            goto L5a
        L59:
            r4 = r3
        L5a:
            if (r4 == 0) goto L5e
            r4 = 4
            goto L61
        L5e:
            r4 = r3
            goto L61
        L60:
            r4 = r5
        L61:
            r1.setVisibility(r4)
            org.chromium.ui.widget.ButtonCompat r1 = r7.j0
            sk1 r4 = r7.m0
            int r4 = r4.a
            int r4 = r4 + (-2)
            if (r0 != r4) goto L70
            r4 = r2
            goto L71
        L70:
            r4 = r3
        L71:
            if (r4 == 0) goto L75
            r4 = r3
            goto L76
        L75:
            r4 = r5
        L76:
            r1.setVisibility(r4)
            org.chromium.ui.widget.ButtonCompat r1 = r7.k0
            sk1 r4 = r7.m0
            int r4 = r4.a
            int r4 = r4 - r2
            if (r0 != r4) goto L84
            r4 = r2
            goto L85
        L84:
            r4 = r3
        L85:
            if (r4 == 0) goto L89
            r4 = r3
            goto L8a
        L89:
            r4 = r5
        L8a:
            r1.setVisibility(r4)
            com.google.android.material.tabs.TabLayout r1 = r7.f0
            sk1 r7 = r7.m0
            if (r0 <= 0) goto L99
            int r7 = r7.a
            int r7 = r7 - r2
            if (r0 >= r7) goto L9c
            goto L9d
        L99:
            r7.getClass()
        L9c:
            r2 = r3
        L9d:
            if (r2 == 0) goto La0
            goto La1
        La0:
            r3 = r5
        La1:
            r1.setVisibility(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.privacy_guide.PrivacyGuideFragment.J0():void");
    }

    @Override // androidx.fragment.app.Fragment
    public final void b0(Fragment fragment) {
        if (fragment instanceof SafeBrowsingFragment) {
            ((SafeBrowsingFragment) fragment).b0 = this.Z;
        }
        if (fragment instanceof DoneFragment) {
            DoneFragment doneFragment = (DoneFragment) fragment;
            doneFragment.Z = this.a0;
            doneFragment.a0 = this.b0;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        D0();
        this.l0 = new r42();
    }

    @Override // androidx.fragment.app.Fragment
    public final void d0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R$menu.privacy_guide_toolbar_menu, menu);
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) D();
        appCompatActivity.setTitle(R$string.prefs_privacy_guide_title);
        final int i = 0;
        appCompatActivity.B1().t(false);
        View viewInflate = layoutInflater.inflate(R$layout.privacy_guide_steps, viewGroup, false);
        this.d0 = viewInflate;
        this.e0 = (ViewPager2) viewInflate.findViewById(R$id.review_viewpager);
        s42 s42Var = new s42(this, new n3(0));
        this.c0 = s42Var;
        this.m0 = new sk1(s42Var.k());
        this.e0.setAdapter(this.c0);
        this.e0.setUserInputEnabled(false);
        TabLayout tabLayout = (TabLayout) this.d0.findViewById(R$id.tab_layout);
        this.f0 = tabLayout;
        new e(tabLayout, this.e0, new pq2(24, this)).a();
        ButtonCompat buttonCompat = (ButtonCompat) this.d0.findViewById(R$id.start_button);
        this.g0 = buttonCompat;
        buttonCompat.setOnClickListener(new View.OnClickListener(this) { // from class: q42
            public final /* synthetic */ PrivacyGuideFragment f;

            {
                this.f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i2 = i;
                PrivacyGuideFragment privacyGuideFragment = this.f;
                switch (i2) {
                    case 0:
                        int i3 = PrivacyGuideFragment.n0;
                        privacyGuideFragment.H0();
                        break;
                    default:
                        int i4 = PrivacyGuideFragment.n0;
                        privacyGuideFragment.getClass();
                        n3.X0("Settings.PrivacyGuide.NextClickCompletion");
                        oj2.U(5, 9, "Settings.PrivacyGuide.NextNavigation");
                        privacyGuideFragment.D().onBackPressed();
                        break;
                }
            }
        });
        ButtonCompat buttonCompat2 = (ButtonCompat) this.d0.findViewById(R$id.next_button);
        this.h0 = buttonCompat2;
        buttonCompat2.setOnClickListener(new gs(6, this));
        ButtonCompat buttonCompat3 = (ButtonCompat) this.d0.findViewById(R$id.back_button);
        this.i0 = buttonCompat3;
        buttonCompat3.setOnClickListener(new ie0(17, this));
        ButtonCompat buttonCompat4 = (ButtonCompat) this.d0.findViewById(R$id.finish_button);
        this.j0 = buttonCompat4;
        buttonCompat4.setOnClickListener(new by1(17, this));
        ButtonCompat buttonCompat5 = (ButtonCompat) this.d0.findViewById(R$id.done_button);
        this.k0 = buttonCompat5;
        final int i2 = 1;
        buttonCompat5.setOnClickListener(new View.OnClickListener(this) { // from class: q42
            public final /* synthetic */ PrivacyGuideFragment f;

            {
                this.f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i2;
                PrivacyGuideFragment privacyGuideFragment = this.f;
                switch (i22) {
                    case 0:
                        int i3 = PrivacyGuideFragment.n0;
                        privacyGuideFragment.H0();
                        break;
                    default:
                        int i4 = PrivacyGuideFragment.n0;
                        privacyGuideFragment.getClass();
                        n3.X0("Settings.PrivacyGuide.NextClickCompletion");
                        oj2.U(5, 9, "Settings.PrivacyGuide.NextNavigation");
                        privacyGuideFragment.D().onBackPressed();
                        break;
                }
            }
        });
        return this.d0;
    }

    @Override // androidx.fragment.app.Fragment
    public final boolean k0(MenuItem menuItem) {
        if (menuItem.getItemId() != R$id.close_menu_id) {
            return false;
        }
        D().onBackPressed();
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        J0();
    }
}
