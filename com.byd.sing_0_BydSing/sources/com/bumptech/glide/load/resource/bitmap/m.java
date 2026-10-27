package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.ImageHeaderParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: compiled from: ImageReader.java */
/* JADX INFO: loaded from: classes.dex */
interface m {

    /* JADX INFO: compiled from: ImageReader.java */
    public static final class a implements m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final com.bumptech.glide.load.i.k f3135a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.load.engine.x.b f3136b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<ImageHeaderParser> f3137c;

        a(InputStream inputStream, List<ImageHeaderParser> list, com.bumptech.glide.load.engine.x.b bVar) {
            this.f3136b = (com.bumptech.glide.load.engine.x.b) com.bumptech.glide.m.j.d(bVar);
            this.f3137c = (List) com.bumptech.glide.m.j.d(list);
            this.f3135a = new com.bumptech.glide.load.i.k(inputStream, bVar);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.m
        public Bitmap a(BitmapFactory.Options options) throws IOException {
            return BitmapFactory.decodeStream(this.f3135a.a(), null, options);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.m
        public void b() {
            this.f3135a.c();
        }

        @Override // com.bumptech.glide.load.resource.bitmap.m
        public int c() throws IOException {
            return com.bumptech.glide.load.b.b(this.f3137c, this.f3135a.a(), this.f3136b);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.m
        public ImageHeaderParser.ImageType d() throws IOException {
            return com.bumptech.glide.load.b.e(this.f3137c, this.f3135a.a(), this.f3136b);
        }
    }

    /* JADX INFO: compiled from: ImageReader.java */
    public static final class b implements m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final com.bumptech.glide.load.engine.x.b f3138a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<ImageHeaderParser> f3139b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final com.bumptech.glide.load.i.m f3140c;

        b(ParcelFileDescriptor parcelFileDescriptor, List<ImageHeaderParser> list, com.bumptech.glide.load.engine.x.b bVar) {
            this.f3138a = (com.bumptech.glide.load.engine.x.b) com.bumptech.glide.m.j.d(bVar);
            this.f3139b = (List) com.bumptech.glide.m.j.d(list);
            this.f3140c = new com.bumptech.glide.load.i.m(parcelFileDescriptor);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.m
        public Bitmap a(BitmapFactory.Options options) throws IOException {
            return BitmapFactory.decodeFileDescriptor(this.f3140c.a().getFileDescriptor(), null, options);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.m
        public void b() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.m
        public int c() throws IOException {
            return com.bumptech.glide.load.b.a(this.f3139b, this.f3140c, this.f3138a);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.m
        public ImageHeaderParser.ImageType d() throws IOException {
            return com.bumptech.glide.load.b.d(this.f3139b, this.f3140c, this.f3138a);
        }
    }

    Bitmap a(BitmapFactory.Options options) throws IOException;

    void b();

    int c() throws IOException;

    ImageHeaderParser.ImageType d() throws IOException;
}
