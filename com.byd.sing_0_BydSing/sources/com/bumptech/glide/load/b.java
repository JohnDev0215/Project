package com.bumptech.glide.load;

import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.i.m;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: compiled from: ImageHeaderParserUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: compiled from: ImageHeaderParserUtils.java */
    class a implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ InputStream f2725a;

        a(InputStream inputStream) {
            this.f2725a = inputStream;
        }

        @Override // com.bumptech.glide.load.b.g
        public ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws IOException {
            try {
                return imageHeaderParser.c(this.f2725a);
            } finally {
                this.f2725a.reset();
            }
        }
    }

    /* JADX INFO: renamed from: com.bumptech.glide.load.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ImageHeaderParserUtils.java */
    class C0085b implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ByteBuffer f2726a;

        C0085b(ByteBuffer byteBuffer) {
            this.f2726a = byteBuffer;
        }

        @Override // com.bumptech.glide.load.b.g
        public ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws IOException {
            return imageHeaderParser.a(this.f2726a);
        }
    }

    /* JADX INFO: compiled from: ImageHeaderParserUtils.java */
    class c implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ m f2727a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.bumptech.glide.load.engine.x.b f2728b;

        c(m mVar, com.bumptech.glide.load.engine.x.b bVar) {
            this.f2727a = mVar;
            this.f2728b = bVar;
        }

        @Override // com.bumptech.glide.load.b.g
        public ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws Throwable {
            RecyclableBufferedInputStream recyclableBufferedInputStream;
            RecyclableBufferedInputStream recyclableBufferedInputStream2 = null;
            try {
                recyclableBufferedInputStream = new RecyclableBufferedInputStream(new FileInputStream(this.f2727a.a().getFileDescriptor()), this.f2728b);
            } catch (Throwable th) {
                th = th;
            }
            try {
                ImageHeaderParser.ImageType imageTypeC = imageHeaderParser.c(recyclableBufferedInputStream);
                try {
                    recyclableBufferedInputStream.close();
                } catch (IOException unused) {
                }
                this.f2727a.a();
                return imageTypeC;
            } catch (Throwable th2) {
                th = th2;
                recyclableBufferedInputStream2 = recyclableBufferedInputStream;
                if (recyclableBufferedInputStream2 != null) {
                    try {
                        recyclableBufferedInputStream2.close();
                    } catch (IOException unused2) {
                    }
                }
                this.f2727a.a();
                throw th;
            }
        }
    }

    /* JADX INFO: compiled from: ImageHeaderParserUtils.java */
    class d implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ InputStream f2729a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.bumptech.glide.load.engine.x.b f2730b;

        d(InputStream inputStream, com.bumptech.glide.load.engine.x.b bVar) {
            this.f2729a = inputStream;
            this.f2730b = bVar;
        }

        @Override // com.bumptech.glide.load.b.f
        public int a(ImageHeaderParser imageHeaderParser) throws IOException {
            try {
                return imageHeaderParser.b(this.f2729a, this.f2730b);
            } finally {
                this.f2729a.reset();
            }
        }
    }

    /* JADX INFO: compiled from: ImageHeaderParserUtils.java */
    class e implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ m f2731a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.bumptech.glide.load.engine.x.b f2732b;

        e(m mVar, com.bumptech.glide.load.engine.x.b bVar) {
            this.f2731a = mVar;
            this.f2732b = bVar;
        }

        @Override // com.bumptech.glide.load.b.f
        public int a(ImageHeaderParser imageHeaderParser) throws Throwable {
            RecyclableBufferedInputStream recyclableBufferedInputStream;
            RecyclableBufferedInputStream recyclableBufferedInputStream2 = null;
            try {
                recyclableBufferedInputStream = new RecyclableBufferedInputStream(new FileInputStream(this.f2731a.a().getFileDescriptor()), this.f2732b);
            } catch (Throwable th) {
                th = th;
            }
            try {
                int iB = imageHeaderParser.b(recyclableBufferedInputStream, this.f2732b);
                try {
                    recyclableBufferedInputStream.close();
                } catch (IOException unused) {
                }
                this.f2731a.a();
                return iB;
            } catch (Throwable th2) {
                th = th2;
                recyclableBufferedInputStream2 = recyclableBufferedInputStream;
                if (recyclableBufferedInputStream2 != null) {
                    try {
                        recyclableBufferedInputStream2.close();
                    } catch (IOException unused2) {
                    }
                }
                this.f2731a.a();
                throw th;
            }
        }
    }

    /* JADX INFO: compiled from: ImageHeaderParserUtils.java */
    private interface f {
        int a(ImageHeaderParser imageHeaderParser) throws IOException;
    }

    /* JADX INFO: compiled from: ImageHeaderParserUtils.java */
    private interface g {
        ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws IOException;
    }

    public static int a(List<ImageHeaderParser> list, m mVar, com.bumptech.glide.load.engine.x.b bVar) throws IOException {
        return c(list, new e(mVar, bVar));
    }

    public static int b(List<ImageHeaderParser> list, InputStream inputStream, com.bumptech.glide.load.engine.x.b bVar) throws IOException {
        if (inputStream == null) {
            return -1;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, bVar);
        }
        inputStream.mark(5242880);
        return c(list, new d(inputStream, bVar));
    }

    private static int c(List<ImageHeaderParser> list, f fVar) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int iA = fVar.a(list.get(i));
            if (iA != -1) {
                return iA;
            }
        }
        return -1;
    }

    public static ImageHeaderParser.ImageType d(List<ImageHeaderParser> list, m mVar, com.bumptech.glide.load.engine.x.b bVar) throws IOException {
        return g(list, new c(mVar, bVar));
    }

    public static ImageHeaderParser.ImageType e(List<ImageHeaderParser> list, InputStream inputStream, com.bumptech.glide.load.engine.x.b bVar) throws IOException {
        if (inputStream == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, bVar);
        }
        inputStream.mark(5242880);
        return g(list, new a(inputStream));
    }

    public static ImageHeaderParser.ImageType f(List<ImageHeaderParser> list, ByteBuffer byteBuffer) throws IOException {
        return byteBuffer == null ? ImageHeaderParser.ImageType.UNKNOWN : g(list, new C0085b(byteBuffer));
    }

    private static ImageHeaderParser.ImageType g(List<ImageHeaderParser> list, g gVar) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ImageHeaderParser.ImageType imageTypeA = gVar.a(list.get(i));
            if (imageTypeA != ImageHeaderParser.ImageType.UNKNOWN) {
                return imageTypeA;
            }
        }
        return ImageHeaderParser.ImageType.UNKNOWN;
    }
}
