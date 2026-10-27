package com.bumptech.glide.integration.okhttp3;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.b;
import java.io.InputStream;
import spi.rg;

/* JADX INFO: loaded from: classes.dex */
public final class a extends rg {
    @Override // spi.rg, spi.em
    public final void b(@NonNull Context context, @NonNull com.bumptech.glide.a aVar, @NonNull Registry registry) {
        registry.j(InputStream.class, new b.a());
    }
}
