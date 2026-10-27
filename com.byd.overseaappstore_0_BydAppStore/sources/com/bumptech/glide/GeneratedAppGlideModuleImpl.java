package com.bumptech.glide;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.byd.overseaappstore.module.ability.util.net.MyAppGlideModule;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import spi.rm;
import spi.yc;

/* JADX INFO: loaded from: classes.dex */
final class GeneratedAppGlideModuleImpl extends GeneratedAppGlideModule {
    public final MyAppGlideModule a = new MyAppGlideModule();

    public GeneratedAppGlideModuleImpl(Context context) {
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Discovered AppGlideModule from annotation: com.byd.overseaappstore.module.ability.util.net.MyAppGlideModule");
            Log.d("Glide", "Discovered LibraryGlideModule from annotation: com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule");
        }
    }

    @Override // spi.f0, spi.k0
    public final void a(@NonNull Context context, @NonNull b bVar) {
        this.a.a(context, bVar);
    }

    @Override // spi.rg, spi.em
    public final void b(@NonNull Context context, @NonNull a aVar, @NonNull Registry registry) {
        new com.bumptech.glide.integration.okhttp3.a().b(context, aVar, registry);
        this.a.b(context, aVar, registry);
    }

    @Override // spi.f0
    public final void c() {
        Objects.requireNonNull(this.a);
    }

    @Override // com.bumptech.glide.GeneratedAppGlideModule
    @NonNull
    public final Set<Class<?>> d() {
        return Collections.emptySet();
    }

    @Override // com.bumptech.glide.GeneratedAppGlideModule
    @NonNull
    public final rm.b e() {
        return new yc();
    }
}
