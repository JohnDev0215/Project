package com.bumptech.glide.load;

import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.p.d.s;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    class a implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ InputStream f2003a;

        a(InputStream inputStream) {
            this.f2003a = inputStream;
        }

        @Override // com.bumptech.glide.load.f.g
        public ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws IOException {
            try {
                return imageHeaderParser.c(this.f2003a);
            } finally {
                this.f2003a.reset();
            }
        }
    }

    class b implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ByteBuffer f2004a;

        b(ByteBuffer byteBuffer) {
            this.f2004a = byteBuffer;
        }

        @Override // com.bumptech.glide.load.f.g
        public ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) {
            return imageHeaderParser.a(this.f2004a);
        }
    }

    class c implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ParcelFileDescriptorRewinder f2005a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.bumptech.glide.load.n.a0.b f2006b;

        c(ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, com.bumptech.glide.load.n.a0.b bVar) {
            this.f2005a = parcelFileDescriptorRewinder;
            this.f2006b = bVar;
        }

        @Override // com.bumptech.glide.load.f.g
        public ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws Throwable {
            s sVar = null;
            try {
                s sVar2 = new s(new FileInputStream(this.f2005a.a().getFileDescriptor()), this.f2006b);
                try {
                    ImageHeaderParser.ImageType imageTypeC = imageHeaderParser.c(sVar2);
                    try {
                        sVar2.close();
                    } catch (IOException unused) {
                    }
                    this.f2005a.a();
                    return imageTypeC;
                } catch (Throwable th) {
                    th = th;
                    sVar = sVar2;
                    if (sVar != null) {
                        try {
                            sVar.close();
                        } catch (IOException unused2) {
                        }
                    }
                    this.f2005a.a();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    class d implements InterfaceC0064f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ InputStream f2007a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.bumptech.glide.load.n.a0.b f2008b;

        d(InputStream inputStream, com.bumptech.glide.load.n.a0.b bVar) {
            this.f2007a = inputStream;
            this.f2008b = bVar;
        }

        @Override // com.bumptech.glide.load.f.InterfaceC0064f
        public int a(ImageHeaderParser imageHeaderParser) throws IOException {
            try {
                return imageHeaderParser.b(this.f2007a, this.f2008b);
            } finally {
                this.f2007a.reset();
            }
        }
    }

    class e implements InterfaceC0064f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ParcelFileDescriptorRewinder f2009a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.bumptech.glide.load.n.a0.b f2010b;

        e(ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, com.bumptech.glide.load.n.a0.b bVar) {
            this.f2009a = parcelFileDescriptorRewinder;
            this.f2010b = bVar;
        }

        @Override // com.bumptech.glide.load.f.InterfaceC0064f
        public int a(ImageHeaderParser imageHeaderParser) throws Throwable {
            s sVar = null;
            try {
                s sVar2 = new s(new FileInputStream(this.f2009a.a().getFileDescriptor()), this.f2010b);
                try {
                    int iB = imageHeaderParser.b(sVar2, this.f2010b);
                    try {
                        sVar2.close();
                    } catch (IOException unused) {
                    }
                    this.f2009a.a();
                    return iB;
                } catch (Throwable th) {
                    th = th;
                    sVar = sVar2;
                    if (sVar != null) {
                        try {
                            sVar.close();
                        } catch (IOException unused2) {
                        }
                    }
                    this.f2009a.a();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX INFO: renamed from: com.bumptech.glide.load.f$f, reason: collision with other inner class name */
    private interface InterfaceC0064f {
        int a(ImageHeaderParser imageHeaderParser);
    }

    private interface g {
        ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser);
    }

    public static int a(List<ImageHeaderParser> list, ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, com.bumptech.glide.load.n.a0.b bVar) {
        return c(list, new e(parcelFileDescriptorRewinder, bVar));
    }

    public static int b(List<ImageHeaderParser> list, InputStream inputStream, com.bumptech.glide.load.n.a0.b bVar) {
        if (inputStream == null) {
            return -1;
        }
        if (!inputStream.markSupported()) {
            inputStream = new s(inputStream, bVar);
        }
        inputStream.mark(5242880);
        return c(list, new d(inputStream, bVar));
    }

    private static int c(List<ImageHeaderParser> list, InterfaceC0064f interfaceC0064f) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int iA = interfaceC0064f.a(list.get(i));
            if (iA != -1) {
                return iA;
            }
        }
        return -1;
    }

    public static ImageHeaderParser.ImageType d(List<ImageHeaderParser> list, ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, com.bumptech.glide.load.n.a0.b bVar) {
        return g(list, new c(parcelFileDescriptorRewinder, bVar));
    }

    public static ImageHeaderParser.ImageType e(List<ImageHeaderParser> list, InputStream inputStream, com.bumptech.glide.load.n.a0.b bVar) {
        if (inputStream == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        if (!inputStream.markSupported()) {
            inputStream = new s(inputStream, bVar);
        }
        inputStream.mark(5242880);
        return g(list, new a(inputStream));
    }

    public static ImageHeaderParser.ImageType f(List<ImageHeaderParser> list, ByteBuffer byteBuffer) {
        return byteBuffer == null ? ImageHeaderParser.ImageType.UNKNOWN : g(list, new b(byteBuffer));
    }

    private static ImageHeaderParser.ImageType g(List<ImageHeaderParser> list, g gVar) {
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
