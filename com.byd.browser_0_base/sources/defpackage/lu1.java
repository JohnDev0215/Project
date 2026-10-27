package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.components.page_info.PageInfoCookiesPreference;

/* JADX INFO: compiled from: PageInfoCookiesPreference.java */
/* JADX INFO: loaded from: classes3.dex */
public final class lu1 extends ph1 {
    public final /* synthetic */ String f;
    public final /* synthetic */ PageInfoCookiesPreference g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public lu1(PageInfoCookiesPreference pageInfoCookiesPreference, mb1 mb1Var, String str) {
        super(mb1Var);
        this.g = pageInfoCookiesPreference;
        this.f = str;
    }

    @Override // defpackage.mb1
    public final boolean r(Preference preference) {
        ((kr) this.g.h0).getClass();
        return N.MmfN78Q9(this.f);
    }
}
