package spi;

import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes.dex */
public final class oj implements ll {
    public final ll<Retrofit> a;

    public oj(ll<Retrofit> llVar) {
        this.a = llVar;
    }

    public static lo a(Retrofit retrofit) {
        Objects.requireNonNull(kj.a);
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(lo.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(SearchDa…SourceNetApi::class.java)");
        lo loVar = (lo) objCreate;
        Objects.requireNonNull(loVar, "Cannot return null from a non-@Nullable @Provides method");
        return loVar;
    }

    @Override // spi.ll
    public final Object get() {
        return a(this.a.get());
    }
}
