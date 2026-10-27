package u1;

import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import o1.d;
import u1.n;

/* JADX INFO: compiled from: DataUrlLoader.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<Model, Data> implements n<Model, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a<Data> f9574a;

    /* JADX INFO: compiled from: DataUrlLoader.java */
    public interface a<Data> {
        Class<Data> a();

        void b(Data data);

        Data c(String str);
    }

    /* JADX INFO: compiled from: DataUrlLoader.java */
    private static final class b<Data> implements o1.d<Data> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final String f9575e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final a<Data> f9576g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private Data f9577h;

        b(String str, a<Data> aVar) {
            this.f9575e = str;
            this.f9576g = aVar;
        }

        @Override // o1.d
        public Class<Data> a() {
            return this.f9576g.a();
        }

        @Override // o1.d
        public void b() {
            try {
                this.f9576g.b(this.f9577h);
            } catch (IOException unused) {
            }
        }

        /* JADX WARN: Type inference failed for: r2v3, types: [Data, java.lang.Object] */
        @Override // o1.d
        public void c(com.bumptech.glide.g gVar, d.a<? super Data> aVar) {
            try {
                Data dataC = this.f9576g.c(this.f9575e);
                this.f9577h = dataC;
                aVar.f(dataC);
            } catch (IllegalArgumentException e7) {
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

    /* JADX INFO: compiled from: DataUrlLoader.java */
    public static final class c<Model> implements o<Model, InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final a<InputStream> f9578a = new a();

        /* JADX INFO: compiled from: DataUrlLoader.java */
        class a implements a<InputStream> {
            a() {
            }

            @Override // u1.e.a
            public Class<InputStream> a() {
                return InputStream.class;
            }

            @Override // u1.e.a
            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public void b(InputStream inputStream) throws IOException {
                inputStream.close();
            }

            @Override // u1.e.a
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public InputStream c(String str) {
                if (!str.startsWith("data:image")) {
                    throw new IllegalArgumentException("Not a valid image data URL.");
                }
                int iIndexOf = str.indexOf(44);
                if (iIndexOf == -1) {
                    throw new IllegalArgumentException("Missing comma in data URL.");
                }
                if (str.substring(0, iIndexOf).endsWith(";base64")) {
                    return new ByteArrayInputStream(Base64.decode(str.substring(iIndexOf + 1), 0));
                }
                throw new IllegalArgumentException("Not a base64 image data URL.");
            }
        }

        @Override // u1.o
        public void b() {
        }

        @Override // u1.o
        public n<Model, InputStream> c(r rVar) {
            return new e(this.f9578a);
        }
    }

    public e(a<Data> aVar) {
        this.f9574a = aVar;
    }

    @Override // u1.n
    public boolean a(Model model) {
        return model.toString().startsWith("data:image");
    }

    @Override // u1.n
    public n.a<Data> b(Model model, int i7, int i8, n1.i iVar) {
        return new n.a<>(new j2.d(model), new b(model.toString(), this.f9574a));
    }
}
