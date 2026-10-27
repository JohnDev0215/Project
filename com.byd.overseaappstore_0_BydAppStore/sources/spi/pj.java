package spi;

import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes.dex */
public final class pj implements ll {
    public final ll<Retrofit> a;

    public pj(ll<Retrofit> llVar) {
        this.a = llVar;
    }

    public static bq a(Retrofit retrofit) {
        Objects.requireNonNull(kj.a);
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(bq.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(Software…SourceNetApi::class.java)");
        bq bqVar = (bq) objCreate;
        Objects.requireNonNull(bqVar, "Cannot return null from a non-@Nullable @Provides method");
        return bqVar;
    }

    @Override // spi.ll
    public final Object get() {
        return a(this.a.get());
    }
}
