package h6;

import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import T.S;
import k6.C1705m;
import kotlin.Metadata;
import n7.z;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lh6/l;", "Lb6/f;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class l extends b6.f {
    public final C1705m l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f15582m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f15583n;
    public final C0738e0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(C1705m c1705m, W5.e eVar) {
        super(c1705m, eVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(eVar, "appNavigationManager");
        this.l = c1705m;
        this.f15582m = "https://legal.stingray.com/%s/markdown";
        this.f15583n = z.s(new m7.i("de", "de/datenschutzrichtlinie"), new m7.i("en", "en/privacy-policy"), new m7.i("es", "es/politica-de-privacidad"), new m7.i("fi", "fi/tietosuojakaytanto"), new m7.i("fr", "fr/politique-de-confidentialite"), new m7.i("hu", "hu/adatvedelmi-szabalyzata"), new m7.i("nb", "nb/personvernerklaering"), new m7.i("nl", "nl/privacybeleid"), new m7.i("pt", "pt/politica-de-privacidade"));
        this.o = C0735d.O("", S.f8224q);
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        AbstractC0727y.w(this.f12236f, null, null, new k(this, null), 3);
        return m7.q.f19267a;
    }

    @Override // b6.f
    public final void g() {
        i(true);
    }
}
