package com.bumptech.glide.load.data;

import android.os.ParcelFileDescriptor;
import java.io.InputStream;
import java.util.HashMap;
import n4.w;

/* JADX INFO: loaded from: classes.dex */
public final class i implements g {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final h f12724n = new h(0);
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f12725m;

    public i() {
        this.l = 0;
        this.f12725m = new HashMap();
    }

    private final void c() {
    }

    private final void d() {
    }

    @Override // com.bumptech.glide.load.data.g
    public Object a() {
        switch (this.l) {
            case 1:
                return ((ParcelFileDescriptorRewinder$InternalRewinder) this.f12725m).rewind();
            case 2:
                return this.f12725m;
            default:
                w wVar = (w) this.f12725m;
                wVar.reset();
                return wVar;
        }
    }

    @Override // com.bumptech.glide.load.data.g
    public void b() {
        switch (this.l) {
            case 1:
            case 2:
                break;
            default:
                ((w) this.f12725m).g();
                break;
        }
    }

    public ParcelFileDescriptor e() {
        return ((ParcelFileDescriptorRewinder$InternalRewinder) this.f12725m).rewind();
    }

    public i(InputStream inputStream, F.l lVar) {
        this.l = 3;
        w wVar = new w(inputStream, lVar);
        this.f12725m = wVar;
        wVar.mark(5242880);
    }

    public i(ParcelFileDescriptor parcelFileDescriptor) {
        this.l = 1;
        this.f12725m = new ParcelFileDescriptorRewinder$InternalRewinder(parcelFileDescriptor);
    }

    public i(Object obj) {
        this.l = 2;
        this.f12725m = obj;
    }
}
