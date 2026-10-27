package k2;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import h2.AbstractC1343A;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* JADX INFO: renamed from: k2.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1604e extends AbstractC1602c {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ContentResolver f17651p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Uri f17652q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public AssetFileDescriptor f17653r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public io.sentry.instrumentation.file.e f17654s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f17655t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f17656u;

    public C1604e(Context context) {
        super(false);
        this.f17651p = context.getContentResolver();
    }

    @Override // k2.h
    public final void close() {
        this.f17652q = null;
        try {
            try {
                io.sentry.instrumentation.file.e eVar = this.f17654s;
                if (eVar != null) {
                    eVar.close();
                }
                this.f17654s = null;
                try {
                    try {
                        AssetFileDescriptor assetFileDescriptor = this.f17653r;
                        if (assetFileDescriptor != null) {
                            assetFileDescriptor.close();
                        }
                    } catch (IOException e10) {
                        throw new C1603d(e10, 2000);
                    }
                } finally {
                    this.f17653r = null;
                    if (this.f17656u) {
                        this.f17656u = false;
                        p();
                    }
                }
            } catch (IOException e11) {
                throw new C1603d(e11, 2000);
            }
        } catch (Throwable th) {
            this.f17654s = null;
            try {
                try {
                    AssetFileDescriptor assetFileDescriptor2 = this.f17653r;
                    if (assetFileDescriptor2 != null) {
                        assetFileDescriptor2.close();
                    }
                    this.f17653r = null;
                    if (this.f17656u) {
                        this.f17656u = false;
                        p();
                    }
                    throw th;
                } catch (IOException e12) {
                    throw new C1603d(e12, 2000);
                }
            } finally {
                this.f17653r = null;
                if (this.f17656u) {
                    this.f17656u = false;
                    p();
                }
            }
        }
    }

    @Override // k2.h
    public final long g(m mVar) throws C1603d {
        AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor;
        try {
            Uri uriNormalizeScheme = mVar.f17675a.normalizeScheme();
            this.f17652q = uriNormalizeScheme;
            q();
            boolean zEquals = "content".equals(uriNormalizeScheme.getScheme());
            ContentResolver contentResolver = this.f17651p;
            if (zEquals) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("android.provider.extra.ACCEPT_ORIGINAL_MEDIA_FORMAT", true);
                assetFileDescriptorOpenAssetFileDescriptor = contentResolver.openTypedAssetFileDescriptor(uriNormalizeScheme, "*/*", bundle);
            } else {
                assetFileDescriptorOpenAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uriNormalizeScheme, "r");
            }
            this.f17653r = assetFileDescriptorOpenAssetFileDescriptor;
            if (assetFileDescriptorOpenAssetFileDescriptor == null) {
                throw new C1603d(new IOException("Could not open file descriptor for: " + uriNormalizeScheme), 2000);
            }
            long length = assetFileDescriptorOpenAssetFileDescriptor.getLength();
            FileDescriptor fileDescriptor = assetFileDescriptorOpenAssetFileDescriptor.getFileDescriptor();
            io.sentry.instrumentation.file.e eVarI = h6.j.i(new FileInputStream(fileDescriptor), fileDescriptor);
            this.f17654s = eVarI;
            long j3 = mVar.f17678f;
            if (length != -1 && j3 > length) {
                throw new C1603d(null, 2008);
            }
            long startOffset = assetFileDescriptorOpenAssetFileDescriptor.getStartOffset();
            long jSkip = eVarI.skip(startOffset + j3) - startOffset;
            if (jSkip != j3) {
                throw new C1603d(null, 2008);
            }
            if (length == -1) {
                FileChannel channel = eVarI.getChannel();
                long size = channel.size();
                if (size == 0) {
                    this.f17655t = -1L;
                } else {
                    long jPosition = size - channel.position();
                    this.f17655t = jPosition;
                    if (jPosition < 0) {
                        throw new C1603d(null, 2008);
                    }
                }
            } else {
                long j10 = length - jSkip;
                this.f17655t = j10;
                if (j10 < 0) {
                    throw new C1603d(null, 2008);
                }
            }
            long j11 = mVar.f17679g;
            if (j11 != -1) {
                long j12 = this.f17655t;
                this.f17655t = j12 == -1 ? j11 : Math.min(j12, j11);
            }
            this.f17656u = true;
            r(mVar);
            return j11 != -1 ? j11 : this.f17655t;
        } catch (C1603d e10) {
            throw e10;
        } catch (IOException e11) {
            throw new C1603d(e11, e11 instanceof FileNotFoundException ? 2005 : 2000);
        }
    }

    @Override // k2.h
    public final Uri j() {
        return this.f17652q;
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) throws C1603d {
        if (i10 == 0) {
            return 0;
        }
        long j3 = this.f17655t;
        if (j3 == 0) {
            return -1;
        }
        if (j3 != -1) {
            try {
                i10 = (int) Math.min(j3, i10);
            } catch (IOException e10) {
                throw new C1603d(e10, 2000);
            }
        }
        io.sentry.instrumentation.file.e eVar = this.f17654s;
        int i11 = AbstractC1343A.f15195a;
        int i12 = eVar.read(bArr, i7, i10);
        if (i12 == -1) {
            return -1;
        }
        long j10 = this.f17655t;
        if (j10 != -1) {
            this.f17655t = j10 - ((long) i12);
        }
        e(i12);
        return i12;
    }
}
