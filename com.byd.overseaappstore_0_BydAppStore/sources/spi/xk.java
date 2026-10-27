package spi;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.resource.bitmap.b;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(21)
public final class xk implements bn<ParcelFileDescriptor, Bitmap> {
    public final com.bumptech.glide.load.resource.bitmap.a a;

    public xk(com.bumptech.glide.load.resource.bitmap.a aVar) {
        this.a = aVar;
    }

    @Override // spi.bn
    public final boolean a(@NonNull ParcelFileDescriptor parcelFileDescriptor, @NonNull lk lkVar) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
        String str = Build.MANUFACTURER;
        if (!(!("HUAWEI".equalsIgnoreCase(str) || "HONOR".equalsIgnoreCase(str)) || parcelFileDescriptor2.getStatSize() <= 536870912)) {
            return false;
        }
        Objects.requireNonNull(this.a);
        return true;
    }

    @Override // spi.bn
    @Nullable
    public final wm<Bitmap> b(@NonNull ParcelFileDescriptor parcelFileDescriptor, int i, int i2, @NonNull lk lkVar) throws IOException {
        com.bumptech.glide.load.resource.bitmap.a aVar = this.a;
        return aVar.a(new b.c(parcelFileDescriptor, aVar.d, aVar.c), i, i2, lkVar, com.bumptech.glide.load.resource.bitmap.a.j);
    }
}
