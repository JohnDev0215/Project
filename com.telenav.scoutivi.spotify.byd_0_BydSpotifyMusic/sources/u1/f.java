package u1;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import o1.d;
import u1.n;

/* JADX INFO: compiled from: FileLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class f<Data> implements n<File, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d<Data> f9580a;

    /* JADX INFO: compiled from: FileLoader.java */
    public static class a<Data> implements o<File, Data> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d<Data> f9581a;

        public a(d<Data> dVar) {
            this.f9581a = dVar;
        }

        @Override // u1.o
        public final void b() {
        }

        @Override // u1.o
        public final n<File, Data> c(r rVar) {
            return new f(this.f9581a);
        }
    }

    /* JADX INFO: compiled from: FileLoader.java */
    public static class b extends a<ParcelFileDescriptor> {

        /* JADX INFO: compiled from: FileLoader.java */
        class a implements d<ParcelFileDescriptor> {
            a() {
            }

            @Override // u1.f.d
            public Class<ParcelFileDescriptor> a() {
                return ParcelFileDescriptor.class;
            }

            @Override // u1.f.d
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void b(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
                parcelFileDescriptor.close();
            }

            @Override // u1.f.d
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public ParcelFileDescriptor c(File file) {
                return ParcelFileDescriptor.open(file, 268435456);
            }
        }

        public b() {
            super(new a());
        }
    }

    /* JADX INFO: compiled from: FileLoader.java */
    private static final class c<Data> implements o1.d<Data> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final File f9582e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final d<Data> f9583g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private Data f9584h;

        c(File file, d<Data> dVar) {
            this.f9582e = file;
            this.f9583g = dVar;
        }

        @Override // o1.d
        public Class<Data> a() {
            return this.f9583g.a();
        }

        @Override // o1.d
        public void b() {
            Data data = this.f9584h;
            if (data != null) {
                try {
                    this.f9583g.b(data);
                } catch (IOException unused) {
                }
            }
        }

        /* JADX WARN: Type inference failed for: r3v3, types: [Data, java.lang.Object] */
        @Override // o1.d
        public void c(com.bumptech.glide.g gVar, d.a<? super Data> aVar) {
            try {
                Data dataC = this.f9583g.c(this.f9582e);
                this.f9584h = dataC;
                aVar.f(dataC);
            } catch (FileNotFoundException e7) {
                if (Log.isLoggable("FileLoader", 3)) {
                    Log.d("FileLoader", "Failed to open file", e7);
                }
                aVar.d(e7);
            }
        }

        @Override // o1.d
        public void cancel() {
        }

        @Override // o1.d
        public n1.a e() {
            return n1.a.LOCAL;
        }
    }

    /* JADX INFO: compiled from: FileLoader.java */
    public interface d<Data> {
        Class<Data> a();

        void b(Data data);

        Data c(File file);
    }

    /* JADX INFO: compiled from: FileLoader.java */
    public static class e extends a<InputStream> {

        /* JADX INFO: compiled from: FileLoader.java */
        class a implements d<InputStream> {
            a() {
            }

            @Override // u1.f.d
            public Class<InputStream> a() {
                return InputStream.class;
            }

            @Override // u1.f.d
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void b(InputStream inputStream) throws IOException {
                inputStream.close();
            }

            @Override // u1.f.d
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
        this.f9580a = dVar;
    }

    @Override // u1.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Data> b(File file, int i7, int i8, n1.i iVar) {
        return new n.a<>(new j2.d(file), new c(file, this.f9580a));
    }

    @Override // u1.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(File file) {
        return true;
    }
}
