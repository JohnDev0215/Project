package spi;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import spi.m5;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class vb<Data> implements ti<File, Data> {
    public final d<Data> a;

    public static class a<Data> implements ui<File, Data> {
        public final d<Data> a;

        public a(d<Data> dVar) {
            this.a = dVar;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<File, Data> c(@NonNull aj ajVar) {
            return new vb(this.a);
        }
    }

    public static class b extends a<ParcelFileDescriptor> {

        public class a implements d<ParcelFileDescriptor> {
            @Override // spi.vb.d
            public final Class<ParcelFileDescriptor> a() {
                return ParcelFileDescriptor.class;
            }

            @Override // spi.vb.d
            public final ParcelFileDescriptor b(File file) throws FileNotFoundException {
                return ParcelFileDescriptor.open(file, 268435456);
            }

            @Override // spi.vb.d
            public final void c(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
                parcelFileDescriptor.close();
            }
        }

        public b() {
            super(new a());
        }
    }

    public static final class c<Data> implements m5<Data> {
        public final File a;
        public final d<Data> b;
        public Data c;

        public c(File file, d<Data> dVar) {
            this.a = file;
            this.b = dVar;
        }

        @Override // spi.m5
        @NonNull
        public final Class<Data> a() {
            return this.b.a();
        }

        @Override // spi.m5
        public final void b() {
            Data data = this.c;
            if (data != null) {
                try {
                    this.b.c(data);
                } catch (IOException unused) {
                }
            }
        }

        @Override // spi.m5
        public final void cancel() {
        }

        /* JADX WARN: Type inference failed for: r3v3, types: [Data, java.lang.Object] */
        @Override // spi.m5
        public final void d(@NonNull Priority priority, @NonNull m5.a<? super Data> aVar) {
            try {
                Data dataB = this.b.b(this.a);
                this.c = dataB;
                aVar.f(dataB);
            } catch (FileNotFoundException e) {
                if (Log.isLoggable("FileLoader", 3)) {
                    Log.d("FileLoader", "Failed to open file", e);
                }
                aVar.c(e);
            }
        }

        @Override // spi.m5
        @NonNull
        public final DataSource e() {
            return DataSource.LOCAL;
        }
    }

    public interface d<Data> {
        Class<Data> a();

        Data b(File file) throws FileNotFoundException;

        void c(Data data) throws IOException;
    }

    public static class e extends a<InputStream> {

        public class a implements d<InputStream> {
            @Override // spi.vb.d
            public final Class<InputStream> a() {
                return InputStream.class;
            }

            @Override // spi.vb.d
            public final InputStream b(File file) throws FileNotFoundException {
                return new FileInputStream(file);
            }

            @Override // spi.vb.d
            public final void c(InputStream inputStream) throws IOException {
                inputStream.close();
            }
        }

        public e() {
            super(new a());
        }
    }

    public vb(d<Data> dVar) {
        this.a = dVar;
    }

    @Override // spi.ti
    public final /* bridge */ /* synthetic */ boolean a(@NonNull File file) {
        return true;
    }

    @Override // spi.ti
    public final ti.a b(@NonNull File file, int i, int i2, @NonNull lk lkVar) {
        File file2 = file;
        return new ti.a(new wj(file2), new c(file2, this.a));
    }
}
