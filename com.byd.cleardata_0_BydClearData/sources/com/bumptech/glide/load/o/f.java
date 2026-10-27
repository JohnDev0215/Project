package com.bumptech.glide.load.o;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.o.n;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class f<Data> implements n<File, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d<Data> f2223a;

    public static class a<Data> implements o<File, Data> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d<Data> f2224a;

        public a(d<Data> dVar) {
            this.f2224a = dVar;
        }

        @Override // com.bumptech.glide.load.o.o
        public final n<File, Data> b(r rVar) {
            return new f(this.f2224a);
        }
    }

    public static class b extends a<ParcelFileDescriptor> {

        class a implements d<ParcelFileDescriptor> {
            a() {
            }

            @Override // com.bumptech.glide.load.o.f.d
            public Class<ParcelFileDescriptor> a() {
                return ParcelFileDescriptor.class;
            }

            @Override // com.bumptech.glide.load.o.f.d
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void b(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
                parcelFileDescriptor.close();
            }

            @Override // com.bumptech.glide.load.o.f.d
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public ParcelFileDescriptor c(File file) {
                return ParcelFileDescriptor.open(file, 268435456);
            }
        }

        public b() {
            super(new a());
        }
    }

    private static final class c<Data> implements com.bumptech.glide.load.data.d<Data> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final File f2225b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final d<Data> f2226c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Data f2227d;

        c(File file, d<Data> dVar) {
            this.f2225b = file;
            this.f2226c = dVar;
        }

        @Override // com.bumptech.glide.load.data.d
        public Class<Data> a() {
            return this.f2226c.a();
        }

        @Override // com.bumptech.glide.load.data.d
        public void b() {
            Data data = this.f2227d;
            if (data != null) {
                try {
                    this.f2226c.b(data);
                } catch (IOException unused) {
                }
            }
        }

        @Override // com.bumptech.glide.load.data.d
        public com.bumptech.glide.load.a c() {
            return com.bumptech.glide.load.a.LOCAL;
        }

        @Override // com.bumptech.glide.load.data.d
        public void cancel() {
        }

        /* JADX WARN: Type inference failed for: r3v3, types: [Data, java.lang.Object] */
        @Override // com.bumptech.glide.load.data.d
        public void e(com.bumptech.glide.g gVar, d.a<? super Data> aVar) {
            try {
                Data dataC = this.f2226c.c(this.f2225b);
                this.f2227d = dataC;
                aVar.f(dataC);
            } catch (FileNotFoundException e) {
                if (Log.isLoggable("FileLoader", 3)) {
                    Log.d("FileLoader", "Failed to open file", e);
                }
                aVar.d(e);
            }
        }
    }

    public interface d<Data> {
        Class<Data> a();

        void b(Data data);

        Data c(File file);
    }

    public static class e extends a<InputStream> {

        class a implements d<InputStream> {
            a() {
            }

            @Override // com.bumptech.glide.load.o.f.d
            public Class<InputStream> a() {
                return InputStream.class;
            }

            @Override // com.bumptech.glide.load.o.f.d
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void b(InputStream inputStream) throws IOException {
                inputStream.close();
            }

            @Override // com.bumptech.glide.load.o.f.d
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public InputStream c(File file) {
                return new FileInputStream(file);
            }
        }

        public e() {
            super(new a());
        }
    }

    public f(d<Data> dVar) {
        this.f2223a = dVar;
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(File file, int i, int i2, com.bumptech.glide.load.i iVar) {
        return new n.a<>(new com.bumptech.glide.s.b(file), new c(file, this.f2223a));
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(File file) {
        return true;
    }
}
