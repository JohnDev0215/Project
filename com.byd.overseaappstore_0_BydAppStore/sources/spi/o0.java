package spi;

import android.content.res.AssetManager;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.IOException;
import spi.m5;

/* JADX INFO: loaded from: classes.dex */
public abstract class o0<T> implements m5<T> {
    public final String a;
    public final AssetManager b;
    public T c;

    public o0(AssetManager assetManager, String str) {
        this.b = assetManager;
        this.a = str;
    }

    @Override // spi.m5
    public final void b() {
        T t = this.c;
        if (t == null) {
            return;
        }
        try {
            c(t);
        } catch (IOException unused) {
        }
    }

    public abstract void c(T t) throws IOException;

    @Override // spi.m5
    public final void cancel() {
    }

    @Override // spi.m5
    public final void d(@NonNull Priority priority, @NonNull m5.a<? super T> aVar) {
        try {
            T tF = f(this.b, this.a);
            this.c = tF;
            aVar.f(tF);
        } catch (IOException e) {
            if (Log.isLoggable("AssetPathFetcher", 3)) {
                Log.d("AssetPathFetcher", "Failed to load data from asset manager", e);
            }
            aVar.c(e);
        }
    }

    @Override // spi.m5
    @NonNull
    public final DataSource e() {
        return DataSource.LOCAL;
    }

    public abstract T f(AssetManager assetManager, String str) throws IOException;
}
