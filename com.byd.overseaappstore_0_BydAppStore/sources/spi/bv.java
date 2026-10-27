package spi;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Objects;
import spi.jk;

/* JADX INFO: loaded from: classes.dex */
public final class bv<T> implements bn<T, Bitmap> {
    public static final jk<Long> d = jk.a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new a());
    public static final jk<Integer> e = jk.a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new b());
    public static final e f = new e();
    public final f<T> a;
    public final w1 b;
    public final e c;

    public class a implements jk.b<Long> {
        public final ByteBuffer a = ByteBuffer.allocate(8);

        @Override // spi.jk.b
        public final void a(@NonNull byte[] bArr, @NonNull Long l, @NonNull MessageDigest messageDigest) {
            Long l2 = l;
            messageDigest.update(bArr);
            synchronized (this.a) {
                this.a.position(0);
                messageDigest.update(this.a.putLong(l2.longValue()).array());
            }
        }
    }

    public class b implements jk.b<Integer> {
        public final ByteBuffer a = ByteBuffer.allocate(4);

        @Override // spi.jk.b
        public final void a(@NonNull byte[] bArr, @NonNull Integer num, @NonNull MessageDigest messageDigest) {
            Integer num2 = num;
            if (num2 == null) {
                return;
            }
            messageDigest.update(bArr);
            synchronized (this.a) {
                this.a.position(0);
                messageDigest.update(this.a.putInt(num2.intValue()).array());
            }
        }
    }

    public static final class c implements f<AssetFileDescriptor> {
        private c() {
        }

        public /* synthetic */ c(a aVar) {
            this();
        }

        @Override // spi.bv.f
        public final void a(MediaMetadataRetriever mediaMetadataRetriever, AssetFileDescriptor assetFileDescriptor) {
            AssetFileDescriptor assetFileDescriptor2 = assetFileDescriptor;
            mediaMetadataRetriever.setDataSource(assetFileDescriptor2.getFileDescriptor(), assetFileDescriptor2.getStartOffset(), assetFileDescriptor2.getLength());
        }
    }

    @RequiresApi(23)
    public static final class d implements f<ByteBuffer> {
        @Override // spi.bv.f
        public final void a(MediaMetadataRetriever mediaMetadataRetriever, ByteBuffer byteBuffer) {
            mediaMetadataRetriever.setDataSource(new cv(this, byteBuffer));
        }
    }

    @VisibleForTesting
    public static class e {
    }

    @VisibleForTesting
    public interface f<T> {
        void a(MediaMetadataRetriever mediaMetadataRetriever, T t);
    }

    public static final class g implements f<ParcelFileDescriptor> {
        @Override // spi.bv.f
        public final void a(MediaMetadataRetriever mediaMetadataRetriever, ParcelFileDescriptor parcelFileDescriptor) {
            mediaMetadataRetriever.setDataSource(parcelFileDescriptor.getFileDescriptor());
        }
    }

    public static final class h extends RuntimeException {
        private static final long serialVersionUID = -2556382523004027815L;

        public h() {
            super("MediaMetadataRetriever failed to retrieve a frame without throwing, check the adb logs for .*MetadataRetriever.* prior to this exception for details");
        }
    }

    public bv(w1 w1Var, f<T> fVar) {
        this(w1Var, fVar, f);
    }

    @VisibleForTesting
    public bv(w1 w1Var, f<T> fVar, e eVar) {
        this.b = w1Var;
        this.a = fVar;
        this.c = eVar;
    }

    @Nullable
    public static Bitmap c(MediaMetadataRetriever mediaMetadataRetriever, long j, int i, int i2, int i3, DownsampleStrategy downsampleStrategy) {
        Bitmap frameAtTime;
        if (i2 == Integer.MIN_VALUE || i3 == Integer.MIN_VALUE || downsampleStrategy == DownsampleStrategy.d) {
            frameAtTime = null;
        } else {
            try {
                int i4 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                int i5 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                int i6 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
                if (i6 == 90 || i6 == 270) {
                    i5 = i4;
                    i4 = i5;
                }
                float fB = downsampleStrategy.b(i4, i5, i2, i3);
                frameAtTime = mediaMetadataRetriever.getScaledFrameAtTime(j, i, Math.round(i4 * fB), Math.round(fB * i5));
            } catch (Throwable th) {
                if (Log.isLoggable("VideoDecoder", 3)) {
                    Log.d("VideoDecoder", "Exception trying to decode a scaled frame on oreo+, falling back to a fullsize frame", th);
                }
                frameAtTime = null;
            }
        }
        if (frameAtTime == null) {
            frameAtTime = mediaMetadataRetriever.getFrameAtTime(j, i);
        }
        if (frameAtTime != null) {
            return frameAtTime;
        }
        throw new h();
    }

    @Override // spi.bn
    public final boolean a(@NonNull T t, @NonNull lk lkVar) {
        return true;
    }

    @Override // spi.bn
    public final wm<Bitmap> b(@NonNull T t, int i, int i2, @NonNull lk lkVar) throws IOException {
        long jLongValue = ((Long) lkVar.c(d)).longValue();
        if (jLongValue < 0 && jLongValue != -1) {
            throw new IllegalArgumentException(spi.d.d("Requested frame must be non-negative, or DEFAULT_FRAME, given: ", jLongValue));
        }
        Integer num = (Integer) lkVar.c(e);
        if (num == null) {
            num = 2;
        }
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) lkVar.c(DownsampleStrategy.f);
        if (downsampleStrategy == null) {
            downsampleStrategy = DownsampleStrategy.e;
        }
        DownsampleStrategy downsampleStrategy2 = downsampleStrategy;
        Objects.requireNonNull(this.c);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            this.a.a(mediaMetadataRetriever, t);
            return y1.b(c(mediaMetadataRetriever, jLongValue, num.intValue(), i, i2, downsampleStrategy2), this.b);
        } finally {
            if (Build.VERSION.SDK_INT >= 29) {
                mediaMetadataRetriever.close();
            } else {
                mediaMetadataRetriever.release();
            }
        }
    }
}
