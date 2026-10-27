package spi;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.ImageHeaderParser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(28)
public final class d0 {
    public final List<ImageHeaderParser> a;
    public final m0 b;

    public static final class a implements wm<Drawable> {
        public final AnimatedImageDrawable a;

        public a(AnimatedImageDrawable animatedImageDrawable) {
            this.a = animatedImageDrawable;
        }

        @Override // spi.wm
        public final int a() {
            return tu.d(Bitmap.Config.ARGB_8888) * this.a.getIntrinsicHeight() * this.a.getIntrinsicWidth() * 2;
        }

        @Override // spi.wm
        @NonNull
        public final Class<Drawable> c() {
            return Drawable.class;
        }

        @Override // spi.wm
        @NonNull
        public final Drawable get() {
            return this.a;
        }

        @Override // spi.wm
        public final void recycle() {
            this.a.stop();
            this.a.clearAnimationCallbacks();
        }
    }

    public static final class b implements bn<ByteBuffer, Drawable> {
        public final d0 a;

        public b(d0 d0Var) {
            this.a = d0Var;
        }

        @Override // spi.bn
        public final boolean a(@NonNull ByteBuffer byteBuffer, @NonNull lk lkVar) throws IOException {
            return com.bumptech.glide.load.a.d(this.a.a, byteBuffer) == ImageHeaderParser.ImageType.ANIMATED_WEBP;
        }

        @Override // spi.bn
        public final wm<Drawable> b(@NonNull ByteBuffer byteBuffer, int i, int i2, @NonNull lk lkVar) throws IOException {
            return this.a.b(ImageDecoder.createSource(byteBuffer), i, i2, lkVar);
        }
    }

    public static final class c implements bn<InputStream, Drawable> {
        public final d0 a;

        public c(d0 d0Var) {
            this.a = d0Var;
        }

        @Override // spi.bn
        public final boolean a(@NonNull InputStream inputStream, @NonNull lk lkVar) throws IOException {
            d0 d0Var = this.a;
            return com.bumptech.glide.load.a.c(d0Var.a, inputStream, d0Var.b) == ImageHeaderParser.ImageType.ANIMATED_WEBP;
        }

        @Override // spi.bn
        public final wm<Drawable> b(@NonNull InputStream inputStream, int i, int i2, @NonNull lk lkVar) throws IOException {
            return this.a.b(ImageDecoder.createSource(u2.b(inputStream)), i, i2, lkVar);
        }
    }

    private d0(List<ImageHeaderParser> list, m0 m0Var) {
        this.a = list;
        this.b = m0Var;
    }

    public static bn<ByteBuffer, Drawable> a(List<ImageHeaderParser> list, m0 m0Var) {
        return new b(new d0(list, m0Var));
    }

    public static bn<InputStream, Drawable> c(List<ImageHeaderParser> list, m0 m0Var) {
        return new c(new d0(list, m0Var));
    }

    public final wm<Drawable> b(@NonNull ImageDecoder.Source source, int i, int i2, @NonNull lk lkVar) throws IOException {
        Drawable drawableDecodeDrawable = ImageDecoder.decodeDrawable(source, new a6(i, i2, lkVar));
        if (drawableDecodeDrawable instanceof AnimatedImageDrawable) {
            return new a((AnimatedImageDrawable) drawableDecodeDrawable);
        }
        throw new IOException("Received unexpected drawable type for animated webp, failing: " + drawableDecodeDrawable);
    }
}
