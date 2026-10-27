package spi;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.io.File;
import java.io.InputStream;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class ms<Data> implements ti<String, Data> {
    public final ti<Uri, Data> a;

    public static final class a implements ui<String, AssetFileDescriptor> {
        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        public final ti<String, AssetFileDescriptor> c(@NonNull aj ajVar) {
            return new ms(ajVar.c(Uri.class, AssetFileDescriptor.class));
        }
    }

    public static class b implements ui<String, ParcelFileDescriptor> {
        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<String, ParcelFileDescriptor> c(@NonNull aj ajVar) {
            return new ms(ajVar.c(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public static class c implements ui<String, InputStream> {
        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<String, InputStream> c(@NonNull aj ajVar) {
            return new ms(ajVar.c(Uri.class, InputStream.class));
        }
    }

    public ms(ti<Uri, Data> tiVar) {
        this.a = tiVar;
    }

    @Override // spi.ti
    public final /* bridge */ /* synthetic */ boolean a(@NonNull String str) {
        return true;
    }

    @Override // spi.ti
    public final ti.a b(@NonNull String str, int i, int i2, @NonNull lk lkVar) {
        Uri uriFromFile;
        File file;
        String str2 = str;
        if (TextUtils.isEmpty(str2)) {
            uriFromFile = null;
        } else {
            if (str2.charAt(0) == '/') {
                file = new File(str2);
            } else {
                uriFromFile = Uri.parse(str2);
                if (uriFromFile.getScheme() == null) {
                    file = new File(str2);
                }
            }
            uriFromFile = Uri.fromFile(file);
        }
        if (uriFromFile == null || !this.a.a(uriFromFile)) {
            return null;
        }
        return this.a.b(uriFromFile, i, i2, lkVar);
    }
}
