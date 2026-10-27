package o1;

import android.content.res.AssetManager;
import android.util.Log;
import java.io.IOException;
import o1.d;

/* JADX INFO: compiled from: AssetPathFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<T> implements d<T> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f8640e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final AssetManager f8641g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private T f8642h;

    public b(AssetManager assetManager, String str) {
        this.f8641g = assetManager;
        this.f8640e = str;
    }

    @Override // o1.d
    public void b() {
        T t6 = this.f8642h;
        if (t6 == null) {
            return;
        }
        try {
            d(t6);
        } catch (IOException unused) {
        }
    }

    @Override // o1.d
    public void c(com.bumptech.glide.g gVar, d.a<? super T> aVar) {
        try {
            T tF = f(this.f8641g, this.f8640e);
            this.f8642h = tF;
            aVar.f(tF);
        } catch (IOException e7) {
            if (Log.isLoggable("AssetPathFetcher", 3)) {
                Log.d("AssetPathFetcher", "Failed to load data from asset manager", e7);
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

    protected abstract T f(AssetManager assetManager, String str);
}
