package com.bumptech.glide.load.p.d;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
interface o {

    public static final class a implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final com.bumptech.glide.load.data.k f2349a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.load.n.a0.b f2350b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<ImageHeaderParser> f2351c;

        a(InputStream inputStream, List<ImageHeaderParser> list, com.bumptech.glide.load.n.a0.b bVar) {
            this.f2350b = (com.bumptech.glide.load.n.a0.b) com.bumptech.glide.t.j.d(bVar);
            this.f2351c = (List) com.bumptech.glide.t.j.d(list);
            this.f2349a = new com.bumptech.glide.load.data.k(inputStream, bVar);
        }

        @Override // com.bumptech.glide.load.p.d.o
        public Bitmap a(BitmapFactory.Options options) {
            return BitmapFactory.decodeStream(this.f2349a.a(), null, options);
        }

        @Override // com.bumptech.glide.load.p.d.o
        public ImageHeaderParser.ImageType b() {
            return com.bumptech.glide.load.f.e(this.f2351c, this.f2349a.a(), this.f2350b);
        }

        @Override // com.bumptech.glide.load.p.d.o
        public void c() {
            this.f2349a.c();
        }

        @Override // com.bumptech.glide.load.p.d.o
        public int d() {
            return com.bumptech.glide.load.f.b(this.f2351c, this.f2349a.a(), this.f2350b);
        }
    }

    public static final class b implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final com.bumptech.glide.load.n.a0.b f2352a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<ImageHeaderParser> f2353b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ParcelFileDescriptorRewinder f2354c;

        b(ParcelFileDescriptor parcelFileDescriptor, List<ImageHeaderParser> list, com.bumptech.glide.load.n.a0.b bVar) {
            this.f2352a = (com.bumptech.glide.load.n.a0.b) com.bumptech.glide.t.j.d(bVar);
            this.f2353b = (List) com.bumptech.glide.t.j.d(list);
            this.f2354c = new ParcelFileDescriptorRewinder(parcelFileDescriptor);
        }

        @Override // com.bumptech.glide.load.p.d.o
        public Bitmap a(BitmapFactory.Options options) {
            return BitmapFactory.decodeFileDescriptor(this.f2354c.a().getFileDescriptor(), null, options);
        }

        @Override // com.bumptech.glide.load.p.d.o
        public ImageHeaderParser.ImageType b() {
            return com.bumptech.glide.load.f.d(this.f2353b, this.f2354c, this.f2352a);
        }

        @Override // com.bumptech.glide.load.p.d.o
        public void c() {
        }

        @Override // com.bumptech.glide.load.p.d.o
        public int d() {
            return com.bumptech.glide.load.f.a(this.f2353b, this.f2354c, this.f2352a);
        }
    }

    Bitmap a(BitmapFactory.Options options);

    ImageHeaderParser.ImageType b();

    void c();

    int d();
}
