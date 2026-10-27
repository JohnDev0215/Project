package android.security;

import android.content.Context;
import android.os.RemoteException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

/* JADX INFO: loaded from: classes2.dex */
public final class FileIntegrityManager {
    private final Context mContext;
    private final IFileIntegrityService mService;

    public FileIntegrityManager(Context context, IFileIntegrityService iFileIntegrityService) {
        this.mContext = context;
        this.mService = iFileIntegrityService;
    }

    public boolean isApkVeritySupported() {
        try {
            return this.mService.isApkVeritySupported();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isAppSourceCertificateTrusted(X509Certificate x509Certificate) throws CertificateEncodingException {
        try {
            return this.mService.isAppSourceCertificateTrusted(x509Certificate.getEncoded(), this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
