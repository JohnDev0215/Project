package k2;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import h2.AbstractC1343A;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: renamed from: k2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1601b extends AbstractC1602c {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final AssetManager f17644p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Uri f17645q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public InputStream f17646r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f17647s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f17648t;

    public C1601b(Context context) {
        super(false);
        this.f17644p = context.getAssets();
    }

    @Override // k2.h
    public final void close() {
        this.f17645q = null;
        try {
            try {
                InputStream inputStream = this.f17646r;
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e10) {
                throw new C1600a(e10, 2000);
            }
        } finally {
            this.f17646r = null;
            if (this.f17648t) {
                this.f17648t = false;
                p();
            }
        }
    }

    @Override // k2.h
    public final long g(m mVar) throws C1600a {
        try {
            Uri uri = mVar.f17675a;
            long j3 = mVar.f17678f;
            this.f17645q = uri;
            String path = uri.getPath();
            path.getClass();
            if (path.startsWith("/android_asset/")) {
                path = path.substring(15);
            } else if (path.startsWith("/")) {
                path = path.substring(1);
            }
            q();
            InputStream inputStreamOpen = this.f17644p.open(path, 1);
            this.f17646r = inputStreamOpen;
            if (inputStreamOpen.skip(j3) < j3) {
                throw new C1600a(null, 2008);
            }
            long j10 = mVar.f17679g;
            if (j10 != -1) {
                this.f17647s = j10;
            } else {
                long jAvailable = this.f17646r.available();
                this.f17647s = jAvailable;
                if (jAvailable == 2147483647L) {
                    this.f17647s = -1L;
                }
            }
            this.f17648t = true;
            r(mVar);
            return this.f17647s;
        } catch (C1600a e10) {
            throw e10;
        } catch (IOException e11) {
            throw new C1600a(e11, e11 instanceof FileNotFoundException ? 2005 : 2000);
        }
    }

    @Override // k2.h
    public final Uri j() {
        return this.f17645q;
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) throws C1600a {
        if (i10 == 0) {
            return 0;
        }
        long j3 = this.f17647s;
        if (j3 == 0) {
            return -1;
        }
        if (j3 != -1) {
            try {
                i10 = (int) Math.min(j3, i10);
            } catch (IOException e10) {
                throw new C1600a(e10, 2000);
            }
        }
        InputStream inputStream = this.f17646r;
        int i11 = AbstractC1343A.f15195a;
        int i12 = inputStream.read(bArr, i7, i10);
        if (i12 == -1) {
            return -1;
        }
        long j10 = this.f17647s;
        if (j10 != -1) {
            this.f17647s = j10 - ((long) i12);
        }
        e(i12);
        return i12;
    }
}
