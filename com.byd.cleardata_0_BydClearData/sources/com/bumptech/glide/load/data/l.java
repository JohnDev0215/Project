package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.load.data.d;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class l<T> implements d<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Uri f1988b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ContentResolver f1989c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private T f1990d;

    public l(ContentResolver contentResolver, Uri uri) {
        this.f1989c = contentResolver;
        this.f1988b = uri;
    }

    @Override // com.bumptech.glide.load.data.d
    public void b() {
        T t = this.f1990d;
        if (t != null) {
            try {
                d(t);
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.bumptech.glide.load.data.d
    public com.bumptech.glide.load.a c() {
        return com.bumptech.glide.load.a.LOCAL;
    }

    @Override // com.bumptech.glide.load.data.d
    public void cancel() {
    }

    protected abstract void d(T t);

    @Override // com.bumptech.glide.load.data.d
    public final void e(com.bumptech.glide.g gVar, d.a<? super T> aVar) {
        try {
            T tF = f(this.f1988b, this.f1989c);
            this.f1990d = tF;
            aVar.f(tF);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable("LocalUriFetcher", 3)) {
                Log.d("LocalUriFetcher", "Failed to open Uri", e);
            }
            aVar.d(e);
        }
    }

    protected abstract T f(Uri uri, ContentResolver contentResolver);
}
