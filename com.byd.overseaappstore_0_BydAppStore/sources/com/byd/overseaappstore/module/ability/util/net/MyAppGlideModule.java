package com.byd.overseaappstore.module.ability.util.net;

import android.content.Context;
import com.bumptech.glide.Registry;
import com.bumptech.glide.a;
import com.bumptech.glide.b;
import com.bumptech.glide.integration.okhttp3.b;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.dk;
import spi.f0;
import spi.ih;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/net/MyAppGlideModule;", "Lspi/f0;", "<init>", "()V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class MyAppGlideModule extends f0 {
    @Override // spi.f0, spi.k0
    public final void a(@NotNull Context context, @NotNull b builder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.f = new ih(134217728);
    }

    @Override // spi.rg, spi.em
    public final void b(@NotNull Context context, @NotNull a glide, @NotNull Registry registry) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(glide, "glide");
        Intrinsics.checkNotNullParameter(registry, "registry");
        registry.j(InputStream.class, new b.a(new dk(new dk.a())));
    }
}
