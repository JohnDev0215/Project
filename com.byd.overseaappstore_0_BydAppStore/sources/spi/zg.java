package spi;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import spi.m5;

/* JADX INFO: loaded from: classes.dex */
public abstract class zg<T> implements m5<T> {
    public final Uri a;
    public final ContentResolver b;
    public T c;

    public zg(ContentResolver contentResolver, Uri uri) {
        this.b = contentResolver;
        this.a = uri;
    }

    @Override // spi.m5
    public final void b() {
        T t = this.c;
        if (t != null) {
            try {
                c(t);
            } catch (IOException unused) {
            }
        }
    }

    public abstract void c(T t) throws IOException;

    @Override // spi.m5
    public final void cancel() {
    }

    @Override // spi.m5
    public final void d(@NonNull Priority priority, @NonNull m5.a<? super T> aVar) {
        try {
            T tF = f(this.a, this.b);
            this.c = tF;
            aVar.f(tF);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable("LocalUriFetcher", 3)) {
                Log.d("LocalUriFetcher", "Failed to open Uri", e);
            }
            aVar.c(e);
        }
    }

    @Override // spi.m5
    @NonNull
    public final DataSource e() {
        return DataSource.LOCAL;
    }

    public abstract T f(Uri uri, ContentResolver contentResolver) throws FileNotFoundException;
}
