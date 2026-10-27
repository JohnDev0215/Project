package com.bumptech.glide.load.data;

import android.content.res.AssetManager;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.d;
import java.io.IOException;

/* JADX INFO: compiled from: AssetPathFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<T> implements d<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2934b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final AssetManager f2935c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private T f2936d;

    public b(AssetManager assetManager, String str) {
        this.f2935c = assetManager;
        this.f2934b = str;
    }

    @Override // com.bumptech.glide.load.data.d
    public void b() {
        T t = this.f2936d;
        if (t == null) {
            return;
        }
        try {
            d(t);
        } catch (IOException unused) {
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
    public void e(Priority priority, d.a<? super T> aVar) {
        try {
            T tF = f(this.f2935c, this.f2934b);
            this.f2936d = tF;
            aVar.f(tF);
        } catch (IOException e2) {
            if (Log.isLoggable("AssetPathFetcher", 3)) {
                Log.d("AssetPathFetcher", "Failed to load data from asset manager", e2);
            }
            aVar.d(e2);
        }
    }

    protected abstract T f(AssetManager assetManager, String str) throws IOException;
}
