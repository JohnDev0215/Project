package k2;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import h2.AbstractC1343A;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class B extends AbstractC1602c {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Context f17625p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public m f17626q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public AssetFileDescriptor f17627r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public io.sentry.instrumentation.file.e f17628s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f17629t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f17630u;

    public B(Context context) {
        super(false);
        this.f17625p = context.getApplicationContext();
    }

    @Deprecated
    public static Uri buildRawResourceUri(int i7) {
        return Uri.parse("rawresource:///" + i7);
    }

    @Override // k2.h
    public final void close() {
        this.f17626q = null;
        try {
            try {
                io.sentry.instrumentation.file.e eVar = this.f17628s;
                if (eVar != null) {
                    eVar.close();
                }
                this.f17628s = null;
                try {
                    try {
                        AssetFileDescriptor assetFileDescriptor = this.f17627r;
                        if (assetFileDescriptor != null) {
                            assetFileDescriptor.close();
                        }
                    } catch (IOException e10) {
                        throw new C1599A(null, e10, 2000);
                    }
                } finally {
                    this.f17627r = null;
                    if (this.f17630u) {
                        this.f17630u = false;
                        p();
                    }
                }
            } catch (IOException e11) {
                throw new C1599A(null, e11, 2000);
            }
        } catch (Throwable th) {
            this.f17628s = null;
            try {
                try {
                    AssetFileDescriptor assetFileDescriptor2 = this.f17627r;
                    if (assetFileDescriptor2 != null) {
                        assetFileDescriptor2.close();
                    }
                    this.f17627r = null;
                    if (this.f17630u) {
                        this.f17630u = false;
                        p();
                    }
                    throw th;
                } catch (IOException e12) {
                    throw new C1599A(null, e12, 2000);
                }
            } finally {
                this.f17627r = null;
                if (this.f17630u) {
                    this.f17630u = false;
                    p();
                }
            }
        }
    }

    @Override // k2.h
    public final long g(m mVar) throws C1599A {
        Resources resourcesForApplication;
        int identifier;
        int i7;
        Resources resources;
        this.f17626q = mVar;
        q();
        Uri uriNormalizeScheme = mVar.f17675a.normalizeScheme();
        boolean zEquals = TextUtils.equals("rawresource", uriNormalizeScheme.getScheme());
        Context context = this.f17625p;
        if (zEquals) {
            resources = context.getResources();
            List<String> pathSegments = uriNormalizeScheme.getPathSegments();
            if (pathSegments.size() != 1) {
                throw new C1599A("rawresource:// URI must have exactly one path element, found " + pathSegments.size(), null, 2000);
            }
            try {
                i7 = Integer.parseInt(pathSegments.get(0));
            } catch (NumberFormatException unused) {
                throw new C1599A("Resource identifier must be an integer.", null, 1004);
            }
        } else {
            if (!TextUtils.equals("android.resource", uriNormalizeScheme.getScheme())) {
                throw new C1599A("Unsupported URI scheme (" + uriNormalizeScheme.getScheme() + "). Only android.resource is supported.", null, 1004);
            }
            String path = uriNormalizeScheme.getPath();
            path.getClass();
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            String packageName = TextUtils.isEmpty(uriNormalizeScheme.getHost()) ? context.getPackageName() : uriNormalizeScheme.getHost();
            if (packageName.equals(context.getPackageName())) {
                resourcesForApplication = context.getResources();
            } else {
                try {
                    resourcesForApplication = context.getPackageManager().getResourcesForApplication(packageName);
                } catch (PackageManager.NameNotFoundException e10) {
                    throw new C1599A("Package in android.resource:// URI not found. Check http://g.co/dev/packagevisibility.", e10, 2005);
                }
            }
            if (path.matches("\\d+")) {
                try {
                    identifier = Integer.parseInt(path);
                } catch (NumberFormatException unused2) {
                    throw new C1599A("Resource identifier must be an integer.", null, 1004);
                }
            } else {
                identifier = resourcesForApplication.getIdentifier(packageName + ":" + path, "raw", null);
                if (identifier == 0) {
                    throw new C1599A("Resource not found.", null, 2005);
                }
            }
            i7 = identifier;
            resources = resourcesForApplication;
        }
        try {
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = resources.openRawResourceFd(i7);
            if (assetFileDescriptorOpenRawResourceFd == null) {
                throw new C1599A("Resource is compressed: " + uriNormalizeScheme, null, 2000);
            }
            this.f17627r = assetFileDescriptorOpenRawResourceFd;
            long length = assetFileDescriptorOpenRawResourceFd.getLength();
            FileDescriptor fileDescriptor = this.f17627r.getFileDescriptor();
            io.sentry.instrumentation.file.e eVarI = h6.j.i(new FileInputStream(fileDescriptor), fileDescriptor);
            this.f17628s = eVarI;
            long j3 = mVar.f17678f;
            try {
                if (length != -1 && j3 > length) {
                    throw new C1599A(null, null, 2008);
                }
                long startOffset = this.f17627r.getStartOffset();
                long jSkip = eVarI.skip(startOffset + j3) - startOffset;
                if (jSkip != j3) {
                    throw new C1599A(null, null, 2008);
                }
                if (length == -1) {
                    FileChannel channel = eVarI.getChannel();
                    if (channel.size() == 0) {
                        this.f17629t = -1L;
                    } else {
                        long size = channel.size() - channel.position();
                        this.f17629t = size;
                        if (size < 0) {
                            throw new C1599A(null, null, 2008);
                        }
                    }
                } else {
                    long j10 = length - jSkip;
                    this.f17629t = j10;
                    if (j10 < 0) {
                        throw new j(2008);
                    }
                }
                long j11 = mVar.f17679g;
                if (j11 != -1) {
                    long j12 = this.f17629t;
                    this.f17629t = j12 == -1 ? j11 : Math.min(j12, j11);
                }
                this.f17630u = true;
                r(mVar);
                return j11 != -1 ? j11 : this.f17629t;
            } catch (C1599A e11) {
                throw e11;
            } catch (IOException e12) {
                throw new C1599A(null, e12, 2000);
            }
        } catch (Resources.NotFoundException e13) {
            throw new C1599A(null, e13, 2005);
        }
    }

    @Override // k2.h
    public final Uri j() {
        m mVar = this.f17626q;
        if (mVar != null) {
            return mVar.f17675a;
        }
        return null;
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) throws C1599A {
        if (i10 == 0) {
            return 0;
        }
        long j3 = this.f17629t;
        if (j3 == 0) {
            return -1;
        }
        if (j3 != -1) {
            try {
                i10 = (int) Math.min(j3, i10);
            } catch (IOException e10) {
                throw new C1599A(null, e10, 2000);
            }
        }
        io.sentry.instrumentation.file.e eVar = this.f17628s;
        int i11 = AbstractC1343A.f15195a;
        int i12 = eVar.read(bArr, i7, i10);
        if (i12 == -1) {
            if (this.f17629t == -1) {
                return -1;
            }
            throw new C1599A("End of stream reached having not read sufficient data.", new EOFException(), 2000);
        }
        long j10 = this.f17629t;
        if (j10 != -1) {
            this.f17629t = j10 - ((long) i12);
        }
        e(i12);
        return i12;
    }
}
