package k2;

import android.net.Uri;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.text.TextUtils;
import h2.AbstractC1343A;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes.dex */
public final class t extends AbstractC1602c {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public RandomAccessFile f17703p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Uri f17704q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f17705r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f17706s;

    @Override // k2.h
    public final void close() {
        this.f17704q = null;
        try {
            try {
                RandomAccessFile randomAccessFile = this.f17703p;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e10) {
                throw new s(e10, 2000);
            }
        } finally {
            this.f17703p = null;
            if (this.f17706s) {
                this.f17706s = false;
                p();
            }
        }
    }

    @Override // k2.h
    public final long g(m mVar) throws s {
        Uri uri = mVar.f17675a;
        long j3 = mVar.f17678f;
        this.f17704q = uri;
        q();
        try {
            String path = uri.getPath();
            path.getClass();
            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
            this.f17703p = randomAccessFile;
            try {
                randomAccessFile.seek(j3);
                long length = mVar.f17679g;
                if (length == -1) {
                    length = this.f17703p.length() - j3;
                }
                this.f17705r = length;
                if (length < 0) {
                    throw new s(null, null, 2008);
                }
                this.f17706s = true;
                r(mVar);
                return this.f17705r;
            } catch (IOException e10) {
                throw new s(e10, 2000);
            }
        } catch (FileNotFoundException e11) {
            if (TextUtils.isEmpty(uri.getQuery()) && TextUtils.isEmpty(uri.getFragment())) {
                throw new s(e11, ((e11.getCause() instanceof ErrnoException) && ((ErrnoException) e11.getCause()).errno == OsConstants.EACCES) ? 2006 : 2005);
            }
            throw new s("uri has query and/or fragment, which are not supported. Did you call Uri.parse() on a string containing '?' or '#'? Use Uri.fromFile(new File(path)) to avoid this. path=" + uri.getPath() + ",query=" + uri.getQuery() + ",fragment=" + uri.getFragment(), e11, 1004);
        } catch (SecurityException e12) {
            throw new s(e12, 2006);
        } catch (RuntimeException e13) {
            throw new s(e13, 2000);
        }
    }

    @Override // k2.h
    public final Uri j() {
        return this.f17704q;
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) throws s {
        if (i10 == 0) {
            return 0;
        }
        long j3 = this.f17705r;
        if (j3 == 0) {
            return -1;
        }
        try {
            RandomAccessFile randomAccessFile = this.f17703p;
            int i11 = AbstractC1343A.f15195a;
            int i12 = randomAccessFile.read(bArr, i7, (int) Math.min(j3, i10));
            if (i12 > 0) {
                this.f17705r -= (long) i12;
                e(i12);
            }
            return i12;
        } catch (IOException e10) {
            throw new s(e10, 2000);
        }
    }
}
