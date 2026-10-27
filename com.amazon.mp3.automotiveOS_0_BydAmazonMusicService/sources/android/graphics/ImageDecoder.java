package android.graphics;

import android.annotation.NonNull;
import android.graphics.drawable.Drawable;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ImageDecoder implements AutoCloseable {

    public /* synthetic */ interface OnHeaderDecodedListener {
        static {
            throw new NoClassDefFoundError();
        }
    }

    static {
        throw new NoClassDefFoundError();
    }

    @NonNull
    public static native /* synthetic */ Bitmap decodeBitmap(@NonNull Source source, @NonNull OnHeaderDecodedListener onHeaderDecodedListener) throws IOException;

    @NonNull
    public static native /* synthetic */ Drawable decodeDrawable(@NonNull Source source, @NonNull OnHeaderDecodedListener onHeaderDecodedListener) throws IOException;
}
