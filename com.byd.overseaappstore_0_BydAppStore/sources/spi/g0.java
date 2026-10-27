package spi;

import com.byd.overseaappstore.Hilt_OverseaAppStoreApp;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class g0 implements xc<Object> {
    public volatile g5 a;
    public final Object b = new Object();
    public final w3 c;

    public g0(w3 w3Var) {
        this.c = w3Var;
    }

    @Override // spi.xc
    public final Object a() {
        if (this.a == null) {
            synchronized (this.b) {
                if (this.a == null) {
                    Hilt_OverseaAppStoreApp.a aVar = (Hilt_OverseaAppStoreApp.a) this.c;
                    Objects.requireNonNull(aVar);
                    new b5(null);
                    this.a = new g5(new h0(Hilt_OverseaAppStoreApp.this), null);
                }
            }
        }
        return this.a;
    }
}
