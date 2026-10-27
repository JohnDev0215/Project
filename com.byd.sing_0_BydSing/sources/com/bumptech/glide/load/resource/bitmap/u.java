package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.d;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: compiled from: VideoDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class u<T> implements com.bumptech.glide.load.f<T, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.bumptech.glide.load.d<Long> f3156a = com.bumptech.glide.load.d.a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new a());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.bumptech.glide.load.d<Integer> f3157b = com.bumptech.glide.load.d.a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new b());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final e f3158c = new e();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final f<T> f3159d;
    private final com.bumptech.glide.load.engine.x.e e;
    private final e f;

    /* JADX INFO: compiled from: VideoDecoder.java */
    class a implements d.b<Long> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ByteBuffer f3160a = ByteBuffer.allocate(8);

        a() {
        }

        @Override // com.bumptech.glide.load.d.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(byte[] bArr, Long l, MessageDigest messageDigest) {
            messageDigest.update(bArr);
            synchronized (this.f3160a) {
                this.f3160a.position(0);
                messageDigest.update(this.f3160a.putLong(l.longValue()).array());
            }
        }
    }

    /* JADX INFO: compiled from: VideoDecoder.java */
    class b implements d.b<Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ByteBuffer f3161a = ByteBuffer.allocate(4);

        b() {
        }

        @Override // com.bumptech.glide.load.d.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(byte[] bArr, Integer num, MessageDigest messageDigest) {
            if (num == null) {
                return;
            }
            messageDigest.update(bArr);
            synchronized (this.f3161a) {
                this.f3161a.position(0);
                messageDigest.update(this.f3161a.putInt(num.intValue()).array());
            }
        }
    }

    /* JADX INFO: compiled from: VideoDecoder.java */
    private static final class c implements f<AssetFileDescriptor> {
        private c() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.u.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(MediaMetadataRetriever mediaMetadataRetriever, AssetFileDescriptor assetFileDescriptor) {
            mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        }

        /* synthetic */ c(a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: VideoDecoder.java */
    static final class d implements f<ByteBuffer> {

        /* JADX INFO: compiled from: VideoDecoder.java */
        class a extends MediaDataSource {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ByteBuffer f3162b;

            a(ByteBuffer byteBuffer) {
                this.f3162b = byteBuffer;
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // android.media.MediaDataSource
            public long getSize() {
                return this.f3162b.limit();
            }

            @Override // android.media.MediaDataSource
            public int readAt(long j, byte[] bArr, int i, int i2) {
                if (j >= this.f3162b.limit()) {
                    return -1;
                }
                this.f3162b.position((int) j);
                int iMin = Math.min(i2, this.f3162b.remaining());
                this.f3162b.get(bArr, i, iMin);
                return iMin;
            }
        }

        d() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.u.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(MediaMetadataRetriever mediaMetadataRetriever, ByteBuffer byteBuffer) {
            mediaMetadataRetriever.setDataSource(new a(byteBuffer));
        }
    }

    /* JADX INFO: compiled from: VideoDecoder.java */
    static class e {
        e() {
        }

        public MediaMetadataRetriever a() {
            return new MediaMetadataRetriever();
        }
    }

    /* JADX INFO: compiled from: VideoDecoder.java */
    interface f<T> {
        void a(MediaMetadataRetriever mediaMetadataRetriever, T t);
    }

    /* JADX INFO: compiled from: VideoDecoder.java */
    static final class g implements f<ParcelFileDescriptor> {
        g() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.u.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(MediaMetadataRetriever mediaMetadataRetriever, ParcelFileDescriptor parcelFileDescriptor) {
            mediaMetadataRetriever.setDataSource(parcelFileDescriptor.getFileDescriptor());
        }
    }

    u(com.bumptech.glide.load.engine.x.e eVar, f<T> fVar) {
        this(eVar, fVar, f3158c);
    }

    public static com.bumptech.glide.load.f<AssetFileDescriptor, Bitmap> c(com.bumptech.glide.load.engine.x.e eVar) {
        return new u(eVar, new c(null));
    }

    public static com.bumptech.glide.load.f<ByteBuffer, Bitmap> d(com.bumptech.glide.load.engine.x.e eVar) {
        return new u(eVar, new d());
    }

    private static Bitmap e(MediaMetadataRetriever mediaMetadataRetriever, long j, int i, int i2, int i3, DownsampleStrategy downsampleStrategy) {
        Bitmap bitmapG = (Build.VERSION.SDK_INT < 27 || i2 == Integer.MIN_VALUE || i3 == Integer.MIN_VALUE || downsampleStrategy == DownsampleStrategy.f) ? null : g(mediaMetadataRetriever, j, i, i2, i3, downsampleStrategy);
        return bitmapG == null ? f(mediaMetadataRetriever, j, i) : bitmapG;
    }

    private static Bitmap f(MediaMetadataRetriever mediaMetadataRetriever, long j, int i) {
        return mediaMetadataRetriever.getFrameAtTime(j, i);
    }

    @TargetApi(27)
    private static Bitmap g(MediaMetadataRetriever mediaMetadataRetriever, long j, int i, int i2, int i3, DownsampleStrategy downsampleStrategy) {
        try {
            int i4 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            int i5 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
            int i6 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
            if (i6 == 90 || i6 == 270) {
                i5 = i4;
                i4 = i5;
            }
            float fB = downsampleStrategy.b(i4, i5, i2, i3);
            return mediaMetadataRetriever.getScaledFrameAtTime(j, i, Math.round(i4 * fB), Math.round(fB * i5));
        } catch (Throwable th) {
            if (!Log.isLoggable("VideoDecoder", 3)) {
                return null;
            }
            Log.d("VideoDecoder", "Exception trying to decode frame on oreo+", th);
            return null;
        }
    }

    public static com.bumptech.glide.load.f<ParcelFileDescriptor, Bitmap> h(com.bumptech.glide.load.engine.x.e eVar) {
        return new u(eVar, new g());
    }

    @Override // com.bumptech.glide.load.f
    public com.bumptech.glide.load.engine.s<Bitmap> a(T t, int i, int i2, com.bumptech.glide.load.e eVar) throws IOException {
        long jLongValue = ((Long) eVar.c(f3156a)).longValue();
        if (jLongValue < 0 && jLongValue != -1) {
            throw new IllegalArgumentException("Requested frame must be non-negative, or DEFAULT_FRAME, given: " + jLongValue);
        }
        Integer num = (Integer) eVar.c(f3157b);
        if (num == null) {
            num = 2;
        }
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) eVar.c(DownsampleStrategy.h);
        if (downsampleStrategy == null) {
            downsampleStrategy = DownsampleStrategy.g;
        }
        DownsampleStrategy downsampleStrategy2 = downsampleStrategy;
        MediaMetadataRetriever mediaMetadataRetrieverA = this.f.a();
        try {
            try {
                this.f3159d.a(mediaMetadataRetrieverA, t);
                Bitmap bitmapE = e(mediaMetadataRetrieverA, jLongValue, num.intValue(), i, i2, downsampleStrategy2);
                mediaMetadataRetrieverA.release();
                return com.bumptech.glide.load.resource.bitmap.e.e(bitmapE, this.e);
            } catch (RuntimeException e2) {
                throw new IOException(e2);
            }
        } catch (Throwable th) {
            mediaMetadataRetrieverA.release();
            throw th;
        }
    }

    @Override // com.bumptech.glide.load.f
    public boolean b(T t, com.bumptech.glide.load.e eVar) {
        return true;
    }

    u(com.bumptech.glide.load.engine.x.e eVar, f<T> fVar, e eVar2) {
        this.e = eVar;
        this.f3159d = fVar;
        this.f = eVar2;
    }
}
