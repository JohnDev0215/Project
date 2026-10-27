package defpackage;

import J.N;
import android.accounts.Account;
import android.content.Context;
import android.view.ViewGroup;
import java.util.List;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.signin.PersonalizedSigninPromoView;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.b;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX INFO: compiled from: SyncPromoController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xx2 {
    public fb0 a;
    public px0 b;
    public final int c;
    public final String d;
    public final String e;
    public final int f;
    public final int g;
    public final a8 h;

    /* JADX INFO: compiled from: SyncPromoController.java */
    public interface a {
    }

    public xx2(int i, a8 a8Var) {
        this.c = i;
        this.h = a8Var;
        if (i == 3) {
            this.d = "Signin_Impression_FromSettings";
            this.e = "settings_personalized_signin_promo_dismissed";
            this.f = R$string.sync_promo_title_settings;
            this.g = R$string.sync_promo_description_settings;
            return;
        }
        if (i == 9) {
            this.d = "Signin_Impression_FromBookmarkManager";
            this.e = "signin_promo_bookmarks_declined";
            this.f = R$string.sync_promo_title_bookmarks;
            this.g = R$string.sync_promo_description_bookmarks;
            return;
        }
        if (i == 16) {
            this.d = "Signin_Impression_FromRecentTabs";
            this.e = null;
            this.f = R$string.sync_promo_title_recent_tabs;
            this.g = R$string.sync_promo_description_recent_tabs;
            return;
        }
        if (i != 20) {
            throw new IllegalArgumentException(gq2.e("Unexpected value for access point: ", i));
        }
        this.d = "Signin_Impression_FromNTPContentSuggestions";
        this.e = "ntp.personalized_signin_promo_dismissed";
        this.f = R$string.sync_promo_title_ntp_content_suggestions;
        this.g = R$string.sync_promo_description_ntp_content_suggestions;
    }

    public static boolean a() {
        return SharedPreferencesManager.getInstance().d(0, b(9)) < 20 && !SharedPreferencesManager.getInstance().readBoolean("signin_promo_bookmarks_declined", false);
    }

    public static String b(int i) {
        if (i == 3) {
            return ar.o.b("Settings");
        }
        if (i == 9) {
            return ar.o.b("Bookmarks");
        }
        if (i == 20) {
            return ar.o.b("Ntp");
        }
        throw new IllegalArgumentException(gq2.e("Unexpected value for access point: ", i));
    }

    public static Account c() {
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        Account accountA = CoreAccountInfo.a(z7.v(profileD).a(0));
        AccountManagerFacade accountManagerFacadeProvider = AccountManagerFacadeProvider.getInstance();
        if (accountA != null) {
            return accountA;
        }
        List<Account> listE = b.e(accountManagerFacadeProvider.b());
        if (listE.isEmpty()) {
            return null;
        }
        return listE.get(0);
    }

    public static void f(Context context, PersonalizedSigninPromoView personalizedSigninPromoView, int i) {
        ViewGroup.LayoutParams layoutParams = personalizedSigninPromoView.getImage().getLayoutParams();
        layoutParams.height = context.getResources().getDimensionPixelSize(i);
        layoutParams.width = context.getResources().getDimensionPixelSize(i);
        personalizedSigninPromoView.getImage().setLayoutParams(layoutParams);
    }

    public final void d() {
        int i = this.c;
        if (i == 20) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - SharedPreferencesManager.getInstance().e(0L, "Chrome.SigninPromoNTP.LastShownTime") < 1800000) {
                sk skVar = bq.a;
                if (N.M09VlOh_("SyncAndroidLimitNTPPromoImpressions")) {
                    return;
                }
            }
            if (SharedPreferencesManager.getInstance().f("Chrome.SigninPromoNTP.FirstShownTime") == 0) {
                SharedPreferencesManager.getInstance().n(jCurrentTimeMillis, "Chrome.SigninPromoNTP.FirstShownTime");
            }
            SharedPreferencesManager.getInstance().n(jCurrentTimeMillis, "Chrome.SigninPromoNTP.LastShownTime");
        }
        if (i != 16) {
            SharedPreferencesManager.getInstance().c(b(i));
        }
        SharedPreferencesManager.getInstance().c("Chrome.SyncPromo.TotalShowCount");
        e("Shown");
    }

    public final void e(String str) {
        String str2;
        int i = this.c;
        if (i == 3) {
            str2 = "Settings";
        } else if (i == 9) {
            str2 = "Bookmarks";
        } else if (i == 16) {
            str2 = "RecentTabs";
        } else {
            if (i != 20) {
                throw new IllegalArgumentException(gq2.e("Unexpected value for access point", i));
            }
            str2 = "Ntp";
        }
        oj2.U(SharedPreferencesManager.getInstance().d(0, "Chrome.SyncPromo.TotalShowCount"), 100, "Signin.SyncPromo." + str + ".Count." + str2);
    }

    public final void g(h62 h62Var, PersonalizedSigninPromoView personalizedSigninPromoView, pq2 pq2Var) {
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        z7.v(profileD);
        Account accountC = c();
        if (accountC == null) {
            h(personalizedSigninPromoView, null, pq2Var);
        } else {
            h(personalizedSigninPromoView, h62Var.d(accountC.name), pq2Var);
        }
    }

    public final void h(PersonalizedSigninPromoView personalizedSigninPromoView, fb0 fb0Var, pq2 pq2Var) {
        px0 px0Var = this.b;
        if (px0Var != null) {
            px0Var.a(null);
            this.b = null;
        }
        px0 px0Var2 = new px0(personalizedSigninPromoView);
        this.b = px0Var2;
        px0Var2.a(new ns1(new gc3(this)));
        this.a = fb0Var;
        int i = this.g;
        int i2 = this.f;
        int i3 = 9;
        if (fb0Var == null) {
            Context context = personalizedSigninPromoView.getContext();
            personalizedSigninPromoView.getImage().setImageResource(R$drawable.chrome_sync_logo);
            f(context, personalizedSigninPromoView, R$dimen.signin_promo_cold_state_image_size);
            personalizedSigninPromoView.getTitle().setText(i2);
            personalizedSigninPromoView.getDescription().setText(i);
            personalizedSigninPromoView.getPrimaryButton().setText(R$string.sync_promo_turn_on_sync);
            personalizedSigninPromoView.getPrimaryButton().setOnClickListener(new rf(this, i3, context));
            personalizedSigninPromoView.getSecondaryButton().setVisibility(8);
        } else {
            Context context2 = personalizedSigninPromoView.getContext();
            personalizedSigninPromoView.getImage().setImageDrawable(this.a.b);
            f(context2, personalizedSigninPromoView, R$dimen.sync_promo_account_image_size);
            personalizedSigninPromoView.getTitle().setText(i2);
            personalizedSigninPromoView.getDescription().setText(i);
            personalizedSigninPromoView.getPrimaryButton().setOnClickListener(new c30(this, 9, context2));
            z7 z7VarM = z7.m();
            Profile profileD = Profile.d();
            z7VarM.getClass();
            if (z7.v(profileD).b(0)) {
                personalizedSigninPromoView.getPrimaryButton().setText(R$string.sync_promo_turn_on_sync);
                personalizedSigninPromoView.getSecondaryButton().setVisibility(8);
            } else {
                personalizedSigninPromoView.getPrimaryButton().setText(sm2.a(context2, this.a));
                personalizedSigninPromoView.getSecondaryButton().setText(R$string.signin_promo_choose_another_account);
                personalizedSigninPromoView.getSecondaryButton().setOnClickListener(new n40(this, 10, context2));
                personalizedSigninPromoView.getSecondaryButton().setVisibility(0);
            }
        }
        if (pq2Var == null) {
            personalizedSigninPromoView.getDismissButton().setVisibility(8);
        } else {
            personalizedSigninPromoView.getDismissButton().setVisibility(0);
            personalizedSigninPromoView.getDismissButton().setOnClickListener(new wd2(this, i3, pq2Var));
        }
    }
}
