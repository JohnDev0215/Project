package spi;

import android.content.res.AssetManager;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class gs extends o0<InputStream> {
    public gs(AssetManager assetManager, String str) {
        super(assetManager, str);
    }

    @Override // spi.m5
    @NonNull
    public final Class<InputStream> a() {
        return InputStream.class;
    }

    @Override // spi.o0
    public final void c(InputStream inputStream) throws IOException {
        inputStream.close();
    }

    @Override // spi.o0
    public final InputStream f(AssetManager assetManager, String str) throws IOException {
        return assetManager.open(str);
    }
}
