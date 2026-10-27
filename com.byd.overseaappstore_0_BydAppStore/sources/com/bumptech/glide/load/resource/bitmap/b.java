package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.ParcelFileDescriptor;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;
import spi.dm;
import spi.m0;
import spi.u2;

/* JADX INFO: loaded from: classes.dex */
public interface b {

    public static final class a implements b {
        public final ByteBuffer a;
        public final List<ImageHeaderParser> b;
        public final m0 c;

        public a(ByteBuffer byteBuffer, List<ImageHeaderParser> list, m0 m0Var) {
            this.a = byteBuffer;
            this.b = list;
            this.c = m0Var;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.b
        public final int a() throws IOException {
            List<ImageHeaderParser> list = this.b;
            ByteBuffer byteBufferC = u2.c(this.a);
            m0 m0Var = this.c;
            if (byteBufferC == null) {
                return -1;
            }
            return com.bumptech.glide.load.a.b(list, new com.bumptech.glide.load.c(byteBufferC, m0Var));
        }

        @Override // com.bumptech.glide.load.resource.bitmap.b
        @Nullable
        public final Bitmap b(BitmapFactory.Options options) {
            return BitmapFactory.decodeStream(new u2.a(u2.c(this.a)), null, options);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.b
        public final void c() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.b
        public final ImageHeaderParser.ImageType d() throws IOException {
            return com.bumptech.glide.load.a.d(this.b, u2.c(this.a));
        }
    }

    /* JADX INFO: renamed from: com.bumptech.glide.load.resource.bitmap.b$b, reason: collision with other inner class name */
    public static final class C0019b implements b {
        public final com.bumptech.glide.load.data.c a;
        public final m0 b;
        public final List<ImageHeaderParser> c;

        public C0019b(InputStream inputStream, List<ImageHeaderParser> list, m0 m0Var) {
            Objects.requireNonNull(m0Var, "Argument must not be null");
            this.b = m0Var;
            Objects.requireNonNull(list, "Argument must not be null");
            this.c = list;
            this.a = new com.bumptech.glide.load.data.c(inputStream, m0Var);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.b
        public final int a() throws IOException {
            return com.bumptech.glide.load.a.a(this.c, this.a.c(), this.b);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.b
        @Nullable
        public final Bitmap b(BitmapFactory.Options options) throws IOException {
            return BitmapFactory.decodeStream(this.a.c(), null, options);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.b
        public final void c() {
            dm dmVar = this.a.a;
            synchronized (dmVar) {
                dmVar.c = dmVar.a.length;
            }
        }

        @Override // com.bumptech.glide.load.resource.bitmap.b
        public final ImageHeaderParser.ImageType d() throws IOException {
            return com.bumptech.glide.load.a.c(this.c, this.a.c(), this.b);
        }
    }

    @RequiresApi(21)
    public static final class c implements b {
        public final m0 a;
        public final List<ImageHeaderParser> b;
        public final ParcelFileDescriptorRewinder c;

        public c(ParcelFileDescriptor parcelFileDescriptor, List<ImageHeaderParser> list, m0 m0Var) {
            Objects.requireNonNull(m0Var, "Argument must not be null");
            this.a = m0Var;
            Objects.requireNonNull(list, "Argument must not be null");
            this.b = list;
            this.c = new ParcelFileDescriptorRewinder(parcelFileDescriptor);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.b
        public final int a() throws IOException {
            return com.bumptech.glide.load.a.b(this.b, new d(this.c, this.a));
        }

        @Override // com.bumptech.glide.load.resource.bitmap.b
        @Nullable
        public final Bitmap b(BitmapFactory.Options options) throws IOException {
            return BitmapFactory.decodeFileDescriptor(this.c.c().getFileDescriptor(), null, options);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.b
        public final void c() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.b
        public final ImageHeaderParser.ImageType d() throws IOException {
            return com.bumptech.glide.load.a.e(this.b, new com.bumptech.glide.load.b(this.c, this.a));
        }
    }

    int a() throws IOException;

    @Nullable
    Bitmap b(BitmapFactory.Options options) throws IOException;

    void c();

    ImageHeaderParser.ImageType d() throws IOException;
}
