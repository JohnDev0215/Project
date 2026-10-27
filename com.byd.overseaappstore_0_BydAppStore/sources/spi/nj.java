package spi;

import android.content.Context;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import spi.dk;

/* JADX INFO: loaded from: classes.dex */
public final class nj implements ll {
    public final ll<Context> a;
    public final ll<z3> b;

    public nj(ll<Context> llVar, ll<z3> llVar2) {
        this.a = llVar;
        this.b = llVar2;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public static Retrofit a(Context context, z3 configRepository) {
        Objects.requireNonNull(kj.a);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        Retrofit.Builder builderBaseUrl = new Retrofit.Builder().baseUrl(qj.a.a());
        dk.a aVar = new dk.a(new dk());
        ht interceptor = new ht(context, configRepository);
        Intrinsics.checkNotNullParameter(interceptor, "interceptor");
        aVar.c.add(interceptor);
        Retrofit retrofitBuild = builderBaseUrl.client(new dk(aVar)).addConverterFactory(GsonConverterFactory.create()).build();
        Intrinsics.checkNotNullExpressionValue(retrofitBuild, "Builder()\n            .b…e())\n            .build()");
        Objects.requireNonNull(retrofitBuild, "Cannot return null from a non-@Nullable @Provides method");
        return retrofitBuild;
    }

    @Override // spi.ll
    public final Object get() {
        return a(this.a.get(), this.b.get());
    }
}
