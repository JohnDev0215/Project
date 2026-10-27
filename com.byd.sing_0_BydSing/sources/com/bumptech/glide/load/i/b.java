package com.bumptech.glide.load.i;

import android.content.res.AssetManager;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.i.d;
import java.io.IOException;

/* JADX INFO: compiled from: AssetPathFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<T> implements d<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2919b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final AssetManager f2920c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private T f2921d;

    public b(AssetManager assetManager, String str) {
        this.f2920c = assetManager;
        this.f2919b = str;
    }

    @Override // com.bumptech.glide.load.i.d
    public void b() {
        T t = this.f2921d;
        if (t == null) {
            return;
        }
        try {
            c(t);
        } catch (IOException unused) {
        }
    }

    protected abstract void c(T t) throws IOException;

    @Override // com.bumptech.glide.load.i.d
    public void cancel() {
    }

    protected abstract T d(AssetManager assetManager, String str) throws IOException;

    @Override // com.bumptech.glide.load.i.d
    public DataSource e() {
        return DataSource.LOCAL;
    }

    @Override // com.bumptech.glide.load.i.d
    public void f(Priority priority, d.a<? super T> aVar) {
        try {
            T tD = d(this.f2920c, this.f2919b);
            this.f2921d = tD;
            aVar.d(tD);
        } catch (IOException e) {
            if (Log.isLoggable("AssetPathFetcher", 3)) {
                Log.d("AssetPathFetcher", "Failed to load data from asset manager", e);
            }
            aVar.c(e);
        }
    }
}
