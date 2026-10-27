package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.d;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: compiled from: LocalUriFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l<T> implements d<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Uri f2954b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ContentResolver f2955c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private T f2956d;

    public l(ContentResolver contentResolver, Uri uri) {
        this.f2955c = contentResolver;
        this.f2954b = uri;
    }

    @Override // com.bumptech.glide.load.data.d
    public void b() {
        T t = this.f2956d;
        if (t != null) {
            try {
                d(t);
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.bumptech.glide.load.data.d
    public DataSource c() {
        return DataSource.LOCAL;
    }

    @Override // com.bumptech.glide.load.data.d
    public void cancel() {
    }

    protected abstract void d(T t) throws IOException;

    @Override // com.bumptech.glide.load.data.d
    public final void e(Priority priority, d.a<? super T> aVar) {
        try {
            T tF = f(this.f2954b, this.f2955c);
            this.f2956d = tF;
            aVar.f(tF);
        } catch (FileNotFoundException e2) {
            if (Log.isLoggable("LocalUriFetcher", 3)) {
                Log.d("LocalUriFetcher", "Failed to open Uri", e2);
            }
            aVar.d(e2);
        }
    }

    protected abstract T f(Uri uri, ContentResolver contentResolver) throws FileNotFoundException;
}
