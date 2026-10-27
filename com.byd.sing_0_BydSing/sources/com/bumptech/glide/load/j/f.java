package com.bumptech.glide.load.j;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.i.d;
import com.bumptech.glide.load.j.n;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: FileLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class f<Data> implements n<File, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d<Data> f2969a;

    /* JADX INFO: compiled from: FileLoader.java */
    public static class a<Data> implements o<File, Data> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d<Data> f2970a;

        public a(d<Data> dVar) {
            this.f2970a = dVar;
        }

        @Override // com.bumptech.glide.load.j.o
        public final n<File, Data> b(r rVar) {
            return new f(this.f2970a);
        }
    }

    /* JADX INFO: compiled from: FileLoader.java */
    public static class b extends a<ParcelFileDescriptor> {

        /* JADX INFO: compiled from: FileLoader.java */
        class a implements d<ParcelFileDescriptor> {
            a() {
            }

            @Override // com.bumptech.glide.load.j.f.d
            public Class<ParcelFileDescriptor> a() {
                return ParcelFileDescriptor.class;
            }

            @Override // com.bumptech.glide.load.j.f.d
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void b(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
                parcelFileDescriptor.close();
            }

            @Override // com.bumptech.glide.load.j.f.d
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public ParcelFileDescriptor c(File file) throws FileNotFoundException {
                return ParcelFileDescriptor.open(file, 268435456);
            }
        }

        public b() {
            super(new a());
        }
    }

    /* JADX INFO: compiled from: FileLoader.java */
    private static final class c<Data> implements com.bumptech.glide.load.i.d<Data> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final File f2971b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final d<Data> f2972c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Data f2973d;

        c(File file, d<Data> dVar) {
            this.f2971b = file;
            this.f2972c = dVar;
        }

        @Override // com.bumptech.glide.load.i.d
        public Class<Data> a() {
            return this.f2972c.a();
        }

        @Override // com.bumptech.glide.load.i.d
        public void b() {
            Data data = this.f2973d;
            if (data != null) {
                try {
                    this.f2972c.b(data);
                } catch (IOException unused) {
                }
            }
        }

        @Override // com.bumptech.glide.load.i.d
        public void cancel() {
        }

        @Override // com.bumptech.glide.load.i.d
        public DataSource e() {
            return DataSource.LOCAL;
        }

        /* JADX WARN: Type inference failed for: r3v3, types: [Data, java.lang.Object] */
        @Override // com.bumptech.glide.load.i.d
        public void f(Priority priority, d.a<? super Data> aVar) {
            try {
                Data dataC = this.f2972c.c(this.f2971b);
                this.f2973d = dataC;
                aVar.d(dataC);
            } catch (FileNotFoundException e) {
                if (Log.isLoggable("FileLoader", 3)) {
                    Log.d("FileLoader", "Failed to open file", e);
                }
                aVar.c(e);
            }
        }
    }

    /* JADX INFO: compiled from: FileLoader.java */
    public interface d<Data> {
        Class<Data> a();

        void b(Data data) throws IOException;

        Data c(File file) throws FileNotFoundException;
    }

    /* JADX INFO: compiled from: FileLoader.java */
    public static class e extends a<InputStream> {

        /* JADX INFO: compiled from: FileLoader.java */
        class a implements d<InputStream> {
            a() {
            }

            @Override // com.bumptech.glide.load.j.f.d
            public Class<InputStream> a() {
                return InputStream.class;
            }

            @Override // com.bumptech.glide.load.j.f.d
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void b(InputStream inputStream) throws IOException {
                inputStream.close();
            }

            @Override // com.bumptech.glide.load.j.f.d
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public InputStream c(File file) throws FileNotFoundException {
                return new FileInputStream(file);
            }
        }

        public e() {
            super(new a());
        }
    }

    public f(d<Data> dVar) {
        this.f2969a = dVar;
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> a(File file, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new n.a<>(new com.bumptech.glide.l.b(file), new c(file, this.f2969a));
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(File file) {
        return true;
    }
}
