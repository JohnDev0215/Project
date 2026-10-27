package spi;

import java.io.IOException;
import kotlin.Unit;
import spi.l6;

/* JADX INFO: loaded from: classes2.dex */
public final class m6 extends ic {
    public boolean a;
    public final /* synthetic */ l6.e b;
    public final /* synthetic */ vr c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m6(l6.e eVar, vr vrVar, vr vrVar2) {
        super(vrVar2);
        this.b = eVar;
        this.c = vrVar;
    }

    @Override // spi.ic, spi.vr, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        super.close();
        if (this.a) {
            return;
        }
        this.a = true;
        synchronized (this.b.j) {
            l6.e eVar = this.b;
            int i = eVar.g - 1;
            eVar.g = i;
            if (i == 0 && eVar.e) {
                eVar.j.L(eVar);
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
