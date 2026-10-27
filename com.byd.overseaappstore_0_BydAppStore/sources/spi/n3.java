package spi;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes2.dex */
public final class n3 extends Lambda implements Function0<List<? extends X509Certificate>> {
    public final /* synthetic */ m3 a;
    public final /* synthetic */ List b;
    public final /* synthetic */ String c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n3(m3 m3Var, List list, String str) {
        super(0);
        this.a = m3Var;
        this.b = list;
        this.c = str;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends X509Certificate> invoke() {
        List<Certificate> listA;
        l3 l3Var = this.a.b;
        if (l3Var == null || (listA = l3Var.a(this.b, this.c)) == null) {
            listA = this.b;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.b(listA));
        for (Certificate certificate : listA) {
            Objects.requireNonNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            arrayList.add((X509Certificate) certificate);
        }
        return arrayList;
    }
}
