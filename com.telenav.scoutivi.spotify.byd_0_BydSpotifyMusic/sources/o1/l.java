package o1;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import o1.d;

/* JADX INFO: compiled from: LocalUriFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l<T> implements d<T> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Uri f8664e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final ContentResolver f8665g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private T f8666h;

    public l(ContentResolver contentResolver, Uri uri) {
        this.f8665g = contentResolver;
        this.f8664e = uri;
    }

    @Override // o1.d
    public void b() {
        T t6 = this.f8666h;
        if (t6 != null) {
            try {
                d(t6);
            } catch (IOException unused) {
            }
        }
    }

    @Override // o1.d
    public final void c(com.bumptech.glide.g gVar, d.a<? super T> aVar) {
        try {
            T tF = f(this.f8664e, this.f8665g);
            this.f8666h = tF;
            aVar.f(tF);
        } catch (FileNotFoundException e7) {
            if (Log.isLoggable("LocalUriFetcher", 3)) {
                Log.d("LocalUriFetcher", "Failed to open Uri", e7);
            }
            aVar.d(e7);
        }
    }

    @Override // o1.d
    public void cancel() {
    }

    protected abstract void d(T t6);

    @Override // o1.d
    public n1.a e() {
        return n1.a.LOCAL;
    }

    protected abstract T f(Uri uri, ContentResolver contentResolver);
}
