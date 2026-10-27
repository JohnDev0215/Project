package pl.droidsonroids.gif;

import androidx.annotation.NonNull;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class GifIOException extends IOException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    public final d f3171a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3172b;

    GifIOException(int i, String str) {
        this.f3171a = d.b(i);
        this.f3172b = str;
    }

    static GifIOException a(int i) {
        if (i == d.NO_ERROR.x) {
            return null;
        }
        return new GifIOException(i, null);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        if (this.f3172b == null) {
            return this.f3171a.c();
        }
        return this.f3171a.c() + ": " + this.f3172b;
    }
}
