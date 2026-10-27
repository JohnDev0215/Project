package x1;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import n1.h;

/* JADX INFO: compiled from: VideoDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class a0<T> implements n1.k<T, Bitmap> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final n1.h<Long> f9739d = n1.h.a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new a());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final n1.h<Integer> f9740e = n1.h.a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new b());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final d f9741f = new d();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e<T> f9742a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final r1.d f9743b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final d f9744c;

    /* JADX INFO: compiled from: VideoDecoder.java */
    class a implements h.b<Long> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ByteBuffer f9745a = ByteBuffer.allocate(8);

        a() {
        }

        @Override // n1.h.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(byte[] bArr, Long l7, MessageDigest messageDigest) {
            messageDigest.update(bArr);
            synchronized (this.f9745a) {
                this.f9745a.position(0);
                messageDigest.update(this.f9745a.putLong(l7.longValue()).array());
            }
        }
    }

    /* JADX INFO: compiled from: VideoDecoder.java */
    class b implements h.b<Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ByteBuffer f9746a = ByteBuffer.allocate(4);

        b() {
        }

        @Override // n1.h.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(byte[] bArr, Integer num, MessageDigest messageDigest) {
            if (num == null) {
                return;
            }
            messageDigest.update(bArr);
            synchronized (this.f9746a) {
                this.f9746a.position(0);
                messageDigest.update(this.f9746a.putInt(num.intValue()).array());
            }
        }
    }

    /* JADX INFO: compiled from: VideoDecoder.java */
    private static final class c implements e<AssetFileDescriptor> {
        private c() {
        }

        /* synthetic */ c(a aVar) {
            this();
        }

        @Override // x1.a0.e
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(MediaMetadataRetriever mediaMetadataRetriever, AssetFileDescriptor assetFileDescriptor) {
            mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        }
    }

    /* JADX INFO: compiled from: VideoDecoder.java */
    static class d {
        d() {
        }

        public MediaMetadataRetriever a() {
            return new MediaMetadataRetriever();
        }
    }

    /* JADX INFO: compiled from: VideoDecoder.java */
    interface e<T> {
        void a(MediaMetadataRetriever mediaMetadataRetriever, T t6);
    }

    /* JADX INFO: compiled from: VideoDecoder.java */
    static final class f implements e<ParcelFileDescriptor> {
        f() {
        }

        @Override // x1.a0.e
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(MediaMetadataRetriever mediaMetadataRetriever, ParcelFileDescriptor parcelFileDescriptor) {
            mediaMetadataRetriever.setDataSource(parcelFileDescriptor.getFileDescriptor());
        }
    }

    a0(r1.d dVar, e<T> eVar) {
        this(dVar, eVar, f9741f);
    }

    public static n1.k<AssetFileDescriptor, Bitmap> c(r1.d dVar) {
        return new a0(dVar, new c(null));
    }

    private static Bitmap d(MediaMetadataRetriever mediaMetadataRetriever, long j7, int i7, int i8, int i9, l lVar) {
        Bitmap bitmapF = (i8 == Integer.MIN_VALUE || i9 == Integer.MIN_VALUE || lVar == l.f9769f) ? null : f(mediaMetadataRetriever, j7, i7, i8, i9, lVar);
        return bitmapF == null ? e(mediaMetadataRetriever, j7, i7) : bitmapF;
    }

    private static Bitmap e(MediaMetadataRetriever mediaMetadataRetriever, long j7, int i7) {
        return mediaMetadataRetriever.getFrameAtTime(j7, i7);
    }

    @TargetApi(27)
    private static Bitmap f(MediaMetadataRetriever mediaMetadataRetriever, long j7, int i7, int i8, int i9, l lVar) {
        try {
            int i10 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            int i11 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
            int i12 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
            if (i12 == 90 || i12 == 270) {
                i11 = i10;
                i10 = i11;
            }
            float fB = lVar.b(i10, i11, i8, i9);
            return mediaMetadataRetriever.getScaledFrameAtTime(j7, i7, Math.round(i10 * fB), Math.round(fB * i11));
        } catch (Throwable th) {
            if (!Log.isLoggable("VideoDecoder", 3)) {
                return null;
            }
            Log.d("VideoDecoder", "Exception trying to decode frame on oreo+", th);
            return null;
        }
    }

    public static n1.k<ParcelFileDescriptor, Bitmap> g(r1.d dVar) {
        return new a0(dVar, new f());
    }

    @Override // n1.k
    public boolean a(T t6, n1.i iVar) {
        return true;
    }

    @Override // n1.k
    public q1.v<Bitmap> b(T t6, int i7, int i8, n1.i iVar) throws IOException {
        long jLongValue = ((Long) iVar.c(f9739d)).longValue();
        if (jLongValue < 0 && jLongValue != -1) {
            throw new IllegalArgumentException("Requested frame must be non-negative, or DEFAULT_FRAME, given: " + jLongValue);
        }
        Integer num = (Integer) iVar.c(f9740e);
        if (num == null) {
            num = 2;
        }
        l lVar = (l) iVar.c(l.f9771h);
        if (lVar == null) {
            lVar = l.f9770g;
        }
        l lVar2 = lVar;
        MediaMetadataRetriever mediaMetadataRetrieverA = this.f9744c.a();
        try {
            try {
                this.f9742a.a(mediaMetadataRetrieverA, t6);
                Bitmap bitmapD = d(mediaMetadataRetrieverA, jLongValue, num.intValue(), i7, i8, lVar2);
                mediaMetadataRetrieverA.release();
                return x1.e.f(bitmapD, this.f9743b);
            } catch (RuntimeException e7) {
                throw new IOException(e7);
            }
        } catch (Throwable th) {
            mediaMetadataRetrieverA.release();
            throw th;
        }
    }

    a0(r1.d dVar, e<T> eVar, d dVar2) {
        this.f9743b = dVar;
        this.f9742a = eVar;
        this.f9744c = dVar2;
    }
}
