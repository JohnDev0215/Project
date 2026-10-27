package defpackage;

import J.N;
import android.content.Context;
import androidx.preference.Preference;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.prefs.PrefService;

/* JADX INFO: compiled from: ChromeSiteSettingsDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kr implements zn2 {
    public final Context a;
    public final Profile b;
    public a c;
    public i52 d;
    public LargeIconBridge e;

    /* JADX INFO: compiled from: ChromeSiteSettingsDelegate.java */
    public class a implements gq {
        @Override // defpackage.mb1
        public final boolean r(Preference preference) {
            return false;
        }
    }

    public kr(Context context, Profile profile) {
        this.a = context;
        this.b = profile;
    }

    public final mb1 a() {
        if (this.c == null) {
            this.c = new a();
        }
        return this.c;
    }

    public final boolean b() {
        sk skVar = bq.a;
        return N.M09VlOh_("PrivacySandboxFirstPartySetsUI");
    }

    public final boolean c() {
        sk skVar = bq.a;
        return N.M09VlOh_("PrivacySandboxSettings4");
    }

    public final void d() {
        if (this.d == null) {
            return;
        }
        if (c()) {
            PrefService prefServiceK = oj2.k(Profile.d());
            if (!(prefServiceK.a("privacy_sandbox.m1.topics_enabled") || prefServiceK.a("privacy_sandbox.m1.ad_measurement_enabled") || prefServiceK.a("privacy_sandbox.m1.fledge_enabled"))) {
                return;
            }
        } else if (!N.MhaiireD()) {
            return;
        }
        if (N.MewRKkCC()) {
            return;
        }
        i52 i52Var = this.d;
        i52Var.getClass();
        n3.X0("Settings.PrivacySandbox.Block3PCookies");
        wp2 wp2Var = i52Var.g;
        wp2Var.a(i52Var);
        int i = R$string.privacy_sandbox_snackbar_message;
        Context context = i52Var.e;
        tp2 tp2VarA = tp2.a(context.getString(i), i52Var, 2, 38);
        tp2VarA.d = context.getString(R$string.more);
        tp2VarA.e = null;
        tp2VarA.i = false;
        wp2Var.c(tp2VarA);
    }
}
