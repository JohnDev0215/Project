package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements e {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f12719m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Comparable f12720n;
    public final Object o;

    public /* synthetic */ b(int i7, Comparable comparable, Object obj) {
        this.l = i7;
        this.o = obj;
        this.f12720n = comparable;
    }

    private final void c() {
    }

    private final void d() {
    }

    @Override // com.bumptech.glide.load.data.e
    public final void b() {
        switch (this.l) {
            case 0:
                Object obj = this.f12719m;
                if (obj != null) {
                    try {
                        g(obj);
                    } catch (IOException unused) {
                        return;
                    }
                    break;
                }
                break;
            default:
                Object obj2 = this.f12719m;
                if (obj2 != null) {
                    try {
                        g(obj2);
                    } catch (IOException unused2) {
                        return;
                    }
                }
                break;
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final void cancel() {
        int i7 = this.l;
    }

    @Override // com.bumptech.glide.load.data.e
    public final int e() {
        switch (this.l) {
        }
        return 1;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void f(com.bumptech.glide.f fVar, d dVar) {
        switch (this.l) {
            case 0:
                try {
                    Object objH = h((AssetManager) this.o, (String) this.f12720n);
                    this.f12719m = objH;
                    dVar.d(objH);
                } catch (IOException e10) {
                    if (Log.isLoggable("AssetPathFetcher", 3)) {
                        Log.d("AssetPathFetcher", "Failed to load data from asset manager", e10);
                    }
                    dVar.c(e10);
                    return;
                }
                break;
            default:
                try {
                    Object objI = i((Uri) this.f12720n, (ContentResolver) this.o);
                    this.f12719m = objI;
                    dVar.d(objI);
                } catch (FileNotFoundException e11) {
                    if (Log.isLoggable("LocalUriFetcher", 3)) {
                        Log.d("LocalUriFetcher", "Failed to open Uri", e11);
                    }
                    dVar.c(e11);
                }
                break;
        }
    }

    public abstract void g(Object obj);

    public abstract Object h(AssetManager assetManager, String str);

    public abstract Object i(Uri uri, ContentResolver contentResolver);
}
