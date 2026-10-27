package com.bumptech.glide.load.data;

import android.content.res.AssetManager;
import android.util.Log;
import com.bumptech.glide.load.data.d;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class b<T> implements d<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f1971b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final AssetManager f1972c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private T f1973d;

    public b(AssetManager assetManager, String str) {
        this.f1972c = assetManager;
        this.f1971b = str;
    }

    @Override // com.bumptech.glide.load.data.d
    public void b() {
        T t = this.f1973d;
        if (t == null) {
            return;
        }
        try {
            d(t);
        } catch (IOException unused) {
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
    public void e(com.bumptech.glide.g gVar, d.a<? super T> aVar) {
        try {
            T tF = f(this.f1972c, this.f1971b);
            this.f1973d = tF;
            aVar.f(tF);
        } catch (IOException e) {
            if (Log.isLoggable("AssetPathFetcher", 3)) {
                Log.d("AssetPathFetcher", "Failed to load data from asset manager", e);
            }
            aVar.d(e);
        }
    }

    protected abstract T f(AssetManager assetManager, String str);
}
