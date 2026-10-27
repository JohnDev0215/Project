package spi;

import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes.dex */
public final class lj implements ll {
    public final ll<Retrofit> a;

    public lj(ll<Retrofit> llVar) {
        this.a = llVar;
    }

    public static b1 a(Retrofit retrofit) {
        Objects.requireNonNull(kj.a);
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(b1.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(\n       …SourceNetApi::class.java)");
        b1 b1Var = (b1) objCreate;
        Objects.requireNonNull(b1Var, "Cannot return null from a non-@Nullable @Provides method");
        return b1Var;
    }

    @Override // spi.ll
    public final Object get() {
        return a(this.a.get());
    }
}
