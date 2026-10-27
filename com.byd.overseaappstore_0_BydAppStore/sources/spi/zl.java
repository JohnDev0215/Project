package spi;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes2.dex */
public final class zl extends Lambda implements Function0<List<? extends X509Certificate>> {
    public final /* synthetic */ wl a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zl(wl wlVar) {
        super(0);
        this.a = wlVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends X509Certificate> invoke() {
        ge geVar = this.a.d;
        Intrinsics.checkNotNull(geVar);
        List<Certificate> listB = geVar.b();
        ArrayList arrayList = new ArrayList(CollectionsKt.b(listB));
        for (Certificate certificate : listB) {
            Objects.requireNonNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            arrayList.add((X509Certificate) certificate);
        }
        return arrayList;
    }
}
