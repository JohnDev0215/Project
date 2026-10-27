package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: compiled from: ImageReader.java */
/* JADX INFO: loaded from: classes.dex */
interface o {

    /* JADX INFO: compiled from: ImageReader.java */
    public static final class a implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final com.bumptech.glide.load.data.k f3373a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.load.engine.x.b f3374b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<ImageHeaderParser> f3375c;

        a(InputStream inputStream, List<ImageHeaderParser> list, com.bumptech.glide.load.engine.x.b bVar) {
            this.f3374b = (com.bumptech.glide.load.engine.x.b) com.bumptech.glide.o.j.d(bVar);
            this.f3375c = (List) com.bumptech.glide.o.j.d(list);
            this.f3373a = new com.bumptech.glide.load.data.k(inputStream, bVar);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.o
        public Bitmap a(BitmapFactory.Options options) throws IOException {
            return BitmapFactory.decodeStream(this.f3373a.a(), null, options);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.o
        public ImageHeaderParser.ImageType b() throws IOException {
            return com.bumptech.glide.load.b.e(this.f3375c, this.f3373a.a(), this.f3374b);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.o
        public void c() {
            this.f3373a.c();
        }

        @Override // com.bumptech.glide.load.resource.bitmap.o
        public int d() throws IOException {
            return com.bumptech.glide.load.b.b(this.f3375c, this.f3373a.a(), this.f3374b);
        }
    }

    /* JADX INFO: compiled from: ImageReader.java */
    public static final class b implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final com.bumptech.glide.load.engine.x.b f3376a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<ImageHeaderParser> f3377b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ParcelFileDescriptorRewinder f3378c;

        b(ParcelFileDescriptor parcelFileDescriptor, List<ImageHeaderParser> list, com.bumptech.glide.load.engine.x.b bVar) {
            this.f3376a = (com.bumptech.glide.load.engine.x.b) com.bumptech.glide.o.j.d(bVar);
            this.f3377b = (List) com.bumptech.glide.o.j.d(list);
            this.f3378c = new ParcelFileDescriptorRewinder(parcelFileDescriptor);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.o
        public Bitmap a(BitmapFactory.Options options) throws IOException {
            return BitmapFactory.decodeFileDescriptor(this.f3378c.a().getFileDescriptor(), null, options);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.o
        public ImageHeaderParser.ImageType b() throws IOException {
            return com.bumptech.glide.load.b.d(this.f3377b, this.f3378c, this.f3376a);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.o
        public void c() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.o
        public int d() throws IOException {
            return com.bumptech.glide.load.b.a(this.f3377b, this.f3378c, this.f3376a);
        }
    }

    Bitmap a(BitmapFactory.Options options) throws IOException;

    ImageHeaderParser.ImageType b() throws IOException;

    void c();

    int d() throws IOException;
}
