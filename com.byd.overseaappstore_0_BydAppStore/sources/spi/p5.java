package spi;

import android.util.Base64;
import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import spi.m5;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class p5<Model, Data> implements ti<Model, Data> {
    public final a<Data> a;

    public interface a<Data> {
    }

    public static final class b<Data> implements m5<Data> {
        public final String a;
        public final a<Data> b;

        /* JADX INFO: Incorrect field signature: TData; */
        public ByteArrayInputStream c;

        public b(String str, a<Data> aVar) {
            this.a = str;
            this.b = aVar;
        }

        @Override // spi.m5
        @NonNull
        public final Class<Data> a() {
            Objects.requireNonNull((c.a) this.b);
            return InputStream.class;
        }

        @Override // spi.m5
        public final void b() {
            try {
                a<Data> aVar = this.b;
                ByteArrayInputStream byteArrayInputStream = this.c;
                Objects.requireNonNull((c.a) aVar);
                byteArrayInputStream.close();
            } catch (IOException unused) {
            }
        }

        @Override // spi.m5
        public final void cancel() {
        }

        @Override // spi.m5
        public final void d(@NonNull Priority priority, @NonNull m5.a<? super Data> aVar) {
            try {
                Object objA = ((c.a) this.b).a(this.a);
                this.c = (ByteArrayInputStream) objA;
                aVar.f(objA);
            } catch (IllegalArgumentException e) {
                aVar.c(e);
            }
        }

        @Override // spi.m5
        @NonNull
        public final DataSource e() {
            return DataSource.LOCAL;
        }
    }

    public static final class c<Model> implements ui<Model, InputStream> {
        public final a<InputStream> a = new a(this);

        public class a implements a<InputStream> {
            public a(c cVar) {
            }

            public final Object a(String str) throws IllegalArgumentException {
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

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<Model, InputStream> c(@NonNull aj ajVar) {
            return new p5(this.a);
        }
    }

    public p5(a<Data> aVar) {
        this.a = aVar;
    }

    @Override // spi.ti
    public final boolean a(@NonNull Model model) {
        return model.toString().startsWith("data:image");
    }

    @Override // spi.ti
    public final ti.a<Data> b(@NonNull Model model, int i, int i2, @NonNull lk lkVar) {
        return new ti.a<>(new wj(model), new b(model.toString(), this.a));
    }
}
