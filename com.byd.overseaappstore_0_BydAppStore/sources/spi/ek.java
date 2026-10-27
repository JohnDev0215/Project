package spi;

import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import spi.c3;
import spi.im;
import spi.m5;

/* JADX INFO: loaded from: classes.dex */
public final class ek implements m5<InputStream>, e3 {
    public final c3.a a;
    public final sd b;
    public l4 c;
    public mn d;
    public m5.a<? super InputStream> e;
    public volatile c3 f;

    public ek(c3.a aVar, sd sdVar) {
        this.a = aVar;
        this.b = sdVar;
    }

    @Override // spi.m5
    @NonNull
    public final Class<InputStream> a() {
        return InputStream.class;
    }

    @Override // spi.m5
    public final void b() {
        try {
            l4 l4Var = this.c;
            if (l4Var != null) {
                l4Var.close();
            }
        } catch (IOException unused) {
        }
        mn mnVar = this.d;
        if (mnVar != null) {
            mnVar.close();
        }
        this.e = null;
    }

    @Override // spi.m5
    public final void cancel() {
        c3 c3Var = this.f;
        if (c3Var != null) {
            c3Var.cancel();
        }
    }

    @Override // spi.m5
    public final void d(@NonNull Priority priority, @NonNull m5.a<? super InputStream> aVar) {
        im.a aVar2 = new im.a();
        aVar2.g(this.b.d());
        for (Map.Entry<String, String> entry : this.b.b.a().entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            aVar2.c.a(name, value);
        }
        im imVarA = aVar2.a();
        this.e = aVar;
        this.f = this.a.a(imVarA);
        this.f.e(this);
    }

    @Override // spi.m5
    @NonNull
    public final DataSource e() {
        return DataSource.REMOTE;
    }

    @Override // spi.e3
    public final void onFailure(@NonNull c3 c3Var, @NonNull IOException iOException) {
        if (Log.isLoggable("OkHttpFetcher", 3)) {
            Log.d("OkHttpFetcher", "OkHttp failed to obtain result", iOException);
        }
        this.e.c(iOException);
    }

    @Override // spi.e3
    public final void onResponse(@NonNull c3 c3Var, @NonNull ln lnVar) {
        this.d = lnVar.h;
        if (!lnVar.C()) {
            this.e.c(new HttpException(lnVar.d, lnVar.e));
            return;
        }
        mn mnVar = this.d;
        Objects.requireNonNull(mnVar, "Argument must not be null");
        InputStream inputStreamB = l4.B(this.d.byteStream(), mnVar.contentLength());
        this.c = (l4) inputStreamB;
        this.e.f(inputStreamB);
    }
}
