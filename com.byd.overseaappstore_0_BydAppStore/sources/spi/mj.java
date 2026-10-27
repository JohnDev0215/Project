package spi;

import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes.dex */
public final class mj implements ll {
    public final ll<Retrofit> a;

    public mj(ll<Retrofit> llVar) {
        this.a = llVar;
    }

    public static x6 a(Retrofit retrofit) {
        Objects.requireNonNull(kj.a);
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(x6.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(Download…SourceNetApi::class.java)");
        x6 x6Var = (x6) objCreate;
        Objects.requireNonNull(x6Var, "Cannot return null from a non-@Nullable @Provides method");
        return x6Var;
    }

    @Override // spi.ll
    public final Object get() {
        return a(this.a.get());
    }
}
