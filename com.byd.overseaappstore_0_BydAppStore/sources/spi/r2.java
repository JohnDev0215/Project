package spi;

import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import spi.m5;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class r2 implements ti<File, ByteBuffer> {

    public static final class a implements m5<ByteBuffer> {
        public final File a;

        public a(File file) {
            this.a = file;
        }

        @Override // spi.m5
        @NonNull
        public final Class<ByteBuffer> a() {
            return ByteBuffer.class;
        }

        @Override // spi.m5
        public final void b() {
        }

        @Override // spi.m5
        public final void cancel() {
        }

        @Override // spi.m5
        public final void d(@NonNull Priority priority, @NonNull m5.a<? super ByteBuffer> aVar) {
            try {
                aVar.f(u2.a(this.a));
            } catch (IOException e) {
                if (Log.isLoggable("ByteBufferFileLoader", 3)) {
                    Log.d("ByteBufferFileLoader", "Failed to obtain ByteBuffer for file", e);
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

    public static class b implements ui<File, ByteBuffer> {
        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<File, ByteBuffer> c(@NonNull aj ajVar) {
            return new r2();
        }
    }

    @Override // spi.ti
    public final /* bridge */ /* synthetic */ boolean a(@NonNull File file) {
        return true;
    }

    @Override // spi.ti
    public final ti.a<ByteBuffer> b(@NonNull File file, int i, int i2, @NonNull lk lkVar) {
        File file2 = file;
        return new ti.a<>(new wj(file2), new a(file2));
    }
}
