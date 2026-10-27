package defpackage;

import android.content.Context;
import defpackage.ny0;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tasks.tab_management.a;

/* JADX INFO: compiled from: IncognitoReauthPromoMessageService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ty0 implements ny0.a {
    public final /* synthetic */ a a;

    public ty0(a aVar) {
        this.a = aVar;
    }

    @Override // ny0.a
    public final void a() {
        a aVar = this.a;
        aVar.getClass();
        oj2.k(Profile.d()).e("incognito.incognito_reauthentication", true);
        oj2.U(0, 2, "Android.IncognitoReauth.PromoAcceptedOrDismissed");
        aVar.i();
        int i = R$string.incognito_reauth_snackbar_text;
        Context context = aVar.j;
        tp2 tp2VarA = tp2.a(context.getString(i), null, 1, 53);
        tp2VarA.g = context.getResources().getColor(R$color.snackbar_background_color_baseline_dark);
        tp2VarA.h = R$style.TextAppearance_TextMedium_Secondary_Baseline_Light;
        tp2VarA.i = false;
        aVar.l.c(tp2VarA);
    }

    @Override // ny0.a
    public final void b() {
    }

    @Override // ny0.a
    public final void c() {
    }
}
