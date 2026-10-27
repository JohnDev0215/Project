package com.bumptech.glide.load.p.d;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.h;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public class x<T> implements com.bumptech.glide.load.k<T, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.bumptech.glide.load.h<Long> f2373a = com.bumptech.glide.load.h.a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new a());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.bumptech.glide.load.h<Integer> f2374b = com.bumptech.glide.load.h.a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new b());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final e f2375c = new e();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final f<T> f2376d;
    private final com.bumptech.glide.load.n.a0.e e;
    private final e f;

    class a implements h.b<Long> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ByteBuffer f2377a = ByteBuffer.allocate(8);

        a() {
        }

        @Override // com.bumptech.glide.load.h.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(byte[] bArr, Long l, MessageDigest messageDigest) {
            messageDigest.update(bArr);
            synchronized (this.f2377a) {
                this.f2377a.position(0);
                messageDigest.update(this.f2377a.putLong(l.longValue()).array());
            }
        }
    }

    class b implements h.b<Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ByteBuffer f2378a = ByteBuffer.allocate(4);

        b() {
        }

        @Override // com.bumptech.glide.load.h.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(byte[] bArr, Integer num, MessageDigest messageDigest) {
            if (num == null) {
                return;
            }
            messageDigest.update(bArr);
            synchronized (this.f2378a) {
                this.f2378a.position(0);
                messageDigest.update(this.f2378a.putInt(num.intValue()).array());
            }
        }
    }

    private static final class c implements f<AssetFileDescriptor> {
        private c() {
        }

        /* synthetic */ c(a aVar) {
            this();
        }

        @Override // com.bumptech.glide.load.p.d.x.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(MediaMetadataRetriever mediaMetadataRetriever, AssetFileDescriptor assetFileDescriptor) {
            mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        }
    }

    static final class d implements f<ByteBuffer> {

        class a extends MediaDataSource {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ByteBuffer f2379b;

            a(ByteBuffer byteBuffer) {
                this.f2379b = byteBuffer;
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // android.media.MediaDataSource
            public long getSize() {
                return this.f2379b.limit();
            }

            @Override // android.media.MediaDataSource
            public int readAt(long j, byte[] bArr, int i, int i2) {
                if (j >= this.f2379b.limit()) {
                    return -1;
                }
                this.f2379b.position((int) j);
                int iMin = Math.min(i2, this.f2379b.remaining());
                this.f2379b.get(bArr, i, iMin);
                return iMin;
            }
        }

        d() {
        }

        @Override // com.bumptech.glide.load.p.d.x.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(MediaMetadataRetriever mediaMetadataRetriever, ByteBuffer byteBuffer) {
            mediaMetadataRetriever.setDataSource(new a(byteBuffer));
        }
    }

    static class e {
        e() {
        }

        public MediaMetadataRetriever a() {
            return new MediaMetadataRetriever();
        }
    }

    interface f<T> {
        void a(MediaMetadataRetriever mediaMetadataRetriever, T t);
    }

    static final class g implements f<ParcelFileDescriptor> {
        g() {
        }

        @Override // com.bumptech.glide.load.p.d.x.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(MediaMetadataRetriever mediaMetadataRetriever, ParcelFileDescriptor parcelFileDescriptor) {
            mediaMetadataRetriever.setDataSource(parcelFileDescriptor.getFileDescriptor());
        }
    }

    x(com.bumptech.glide.load.n.a0.e eVar, f<T> fVar) {
        this(eVar, fVar, f2375c);
    }

    x(com.bumptech.glide.load.n.a0.e eVar, f<T> fVar, e eVar2) {
        this.e = eVar;
        this.f2376d = fVar;
        this.f = eVar2;
    }

    public static com.bumptech.glide.load.k<AssetFileDescriptor, Bitmap> c(com.bumptech.glide.load.n.a0.e eVar) {
        return new x(eVar, new c(null));
    }

    public static com.bumptech.glide.load.k<ByteBuffer, Bitmap> d(com.bumptech.glide.load.n.a0.e eVar) {
        return new x(eVar, new d());
    }

    private static Bitmap e(MediaMetadataRetriever mediaMetadataRetriever, long j, int i, int i2, int i3, i iVar) {
        Bitmap bitmapG = (Build.VERSION.SDK_INT < 27 || i2 == Integer.MIN_VALUE || i3 == Integer.MIN_VALUE || iVar == i.f) ? null : g(mediaMetadataRetriever, j, i, i2, i3, iVar);
        return bitmapG == null ? f(mediaMetadataRetriever, j, i) : bitmapG;
    }

    private static Bitmap f(MediaMetadataRetriever mediaMetadataRetriever, long j, int i) {
        return mediaMetadataRetriever.getFrameAtTime(j, i);
    }

    @TargetApi(27)
    private static Bitmap g(MediaMetadataRetriever mediaMetadataRetriever, long j, int i, int i2, int i3, i iVar) {
        try {
            int i4 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            int i5 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
            int i6 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
            if (i6 == 90 || i6 == 270) {
                i5 = i4;
                i4 = i5;
            }
            float fB = iVar.b(i4, i5, i2, i3);
            return mediaMetadataRetriever.getScaledFrameAtTime(j, i, Math.round(i4 * fB), Math.round(fB * i5));
        } catch (Throwable th) {
            if (!Log.isLoggable("VideoDecoder", 3)) {
                return null;
            }
            Log.d("VideoDecoder", "Exception trying to decode frame on oreo+", th);
            return null;
        }
    }

    public static com.bumptech.glide.load.k<ParcelFileDescriptor, Bitmap> h(com.bumptech.glide.load.n.a0.e eVar) {
        return new x(eVar, new g());
    }

    @Override // com.bumptech.glide.load.k
    public com.bumptech.glide.load.n.v<Bitmap> a(T t, int i, int i2, com.bumptech.glide.load.i iVar) throws IOException {
        long jLongValue = ((Long) iVar.c(f2373a)).longValue();
        if (jLongValue < 0 && jLongValue != -1) {
            throw new IllegalArgumentException("Requested frame must be non-negative, or DEFAULT_FRAME, given: " + jLongValue);
        }
        Integer num = (Integer) iVar.c(f2374b);
        if (num == null) {
            num = 2;
        }
        i iVar2 = (i) iVar.c(i.h);
        if (iVar2 == null) {
            iVar2 = i.g;
        }
        i iVar3 = iVar2;
        MediaMetadataRetriever mediaMetadataRetrieverA = this.f.a();
        try {
            try {
                this.f2376d.a(mediaMetadataRetrieverA, t);
                Bitmap bitmapE = e(mediaMetadataRetrieverA, jLongValue, num.intValue(), i, i2, iVar3);
                mediaMetadataRetrieverA.release();
                return com.bumptech.glide.load.p.d.e.g(bitmapE, this.e);
            } catch (RuntimeException e2) {
                throw new IOException(e2);
            }
        } catch (Throwable th) {
            mediaMetadataRetrieverA.release();
            throw th;
        }
    }

    @Override // com.bumptech.glide.load.k
    public boolean b(T t, com.bumptech.glide.load.i iVar) {
        return true;
    }
}
