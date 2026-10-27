package spi;

import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class dd implements en<GifDrawable> {
    @Override // spi.en
    @NonNull
    public final EncodeStrategy a(@NonNull lk lkVar) {
        return EncodeStrategy.SOURCE;
    }

    @Override // spi.f8
    public final boolean b(@NonNull Object obj, @NonNull File file, @NonNull lk lkVar) throws Throwable {
        try {
            u2.d(((GifDrawable) ((wm) obj).get()).a.a.a.getData().asReadOnlyBuffer(), file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable("GifEncoder", 5)) {
                Log.w("GifEncoder", "Failed to encode GIF drawable data", e);
            }
            return false;
        }
    }
}
