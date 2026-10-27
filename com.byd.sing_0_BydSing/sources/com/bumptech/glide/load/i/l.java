package com.bumptech.glide.load.i;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.i.d;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: compiled from: LocalUriFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l<T> implements d<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Uri f2936b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ContentResolver f2937c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private T f2938d;

    public l(ContentResolver contentResolver, Uri uri) {
        this.f2937c = contentResolver;
        this.f2936b = uri;
    }

    @Override // com.bumptech.glide.load.i.d
    public void b() {
        T t = this.f2938d;
        if (t != null) {
            try {
                c(t);
            } catch (IOException unused) {
            }
        }
    }

    protected abstract void c(T t) throws IOException;

    @Override // com.bumptech.glide.load.i.d
    public void cancel() {
    }

    protected abstract T d(Uri uri, ContentResolver contentResolver) throws FileNotFoundException;

    @Override // com.bumptech.glide.load.i.d
    public DataSource e() {
        return DataSource.LOCAL;
    }

    @Override // com.bumptech.glide.load.i.d
    public final void f(Priority priority, d.a<? super T> aVar) {
        try {
            T tD = d(this.f2936b, this.f2937c);
            this.f2938d = tD;
            aVar.d(tD);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable("LocalUriFetcher", 3)) {
                Log.d("LocalUriFetcher", "Failed to open Uri", e);
            }
            aVar.c(e);
        }
    }
}
