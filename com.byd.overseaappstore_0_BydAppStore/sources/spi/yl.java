package spi;

import java.security.cert.Certificate;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes2.dex */
public final class yl extends Lambda implements Function0<List<? extends Certificate>> {
    public final /* synthetic */ m3 a;
    public final /* synthetic */ ge b;
    public final /* synthetic */ t c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public yl(m3 m3Var, ge geVar, t tVar) {
        super(0);
        this.a = m3Var;
        this.b = geVar;
        this.c = tVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends Certificate> invoke() {
        l3 l3Var = this.a.b;
        Intrinsics.checkNotNull(l3Var);
        return l3Var.a(this.b.b(), this.c.a.e);
    }
}
