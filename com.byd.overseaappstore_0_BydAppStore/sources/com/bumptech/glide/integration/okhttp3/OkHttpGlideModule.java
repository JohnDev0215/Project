package com.bumptech.glide.integration.okhttp3;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.b;
import java.io.InputStream;
import spi.md;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class OkHttpGlideModule implements md {
    @Override // spi.k0
    public final void a(@NonNull Context context, @NonNull com.bumptech.glide.b bVar) {
    }

    @Override // spi.em
    public final void b(Context context, com.bumptech.glide.a aVar, Registry registry) {
        registry.j(InputStream.class, new b.a());
    }
}
