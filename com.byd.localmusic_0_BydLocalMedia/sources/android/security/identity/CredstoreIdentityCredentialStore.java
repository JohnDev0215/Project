package android.security.identity;

import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.security.identity.ICredentialStoreFactory;

/* JADX INFO: loaded from: classes3.dex */
class CredstoreIdentityCredentialStore extends IdentityCredentialStore {
    private static final String TAG = "CredstoreIdentityCredentialStore";
    private static CredstoreIdentityCredentialStore sInstanceDefault;
    private static CredstoreIdentityCredentialStore sInstanceDirectAccess;
    private Context mContext;
    private ICredentialStore mStore;

    private CredstoreIdentityCredentialStore(Context context, ICredentialStore iCredentialStore) {
        this.mContext = null;
        this.mStore = null;
        this.mContext = context;
        this.mStore = iCredentialStore;
    }

    static CredstoreIdentityCredentialStore getInstanceForType(Context context, int i) {
        ICredentialStoreFactory iCredentialStoreFactoryAsInterface = ICredentialStoreFactory.Stub.asInterface(ServiceManager.getService("android.security.identity"));
        if (iCredentialStoreFactoryAsInterface == null) {
            return null;
        }
        try {
            ICredentialStore credentialStore = iCredentialStoreFactoryAsInterface.getCredentialStore(i);
            if (credentialStore == null) {
                return null;
            }
            return new CredstoreIdentityCredentialStore(context, credentialStore);
        } catch (RemoteException e) {
            throw new RuntimeException("Unexpected RemoteException ", e);
        } catch (ServiceSpecificException e2) {
            if (e2.errorCode == 1) {
                return null;
            }
            throw new RuntimeException("Unexpected ServiceSpecificException with code " + e2.errorCode, e2);
        }
    }

    public static IdentityCredentialStore getInstance(Context context) {
        if (sInstanceDefault == null) {
            sInstanceDefault = getInstanceForType(context, 0);
        }
        return sInstanceDefault;
    }

    public static IdentityCredentialStore getDirectAccessInstance(Context context) {
        if (sInstanceDirectAccess == null) {
            sInstanceDirectAccess = getInstanceForType(context, 1);
        }
        return sInstanceDirectAccess;
    }

    @Override // android.security.identity.IdentityCredentialStore
    public String[] getSupportedDocTypes() {
        try {
            return this.mStore.getSecurityHardwareInfo().supportedDocTypes;
        } catch (RemoteException e) {
            throw new RuntimeException("Unexpected RemoteException ", e);
        } catch (ServiceSpecificException e2) {
            throw new RuntimeException("Unexpected ServiceSpecificException with code " + e2.errorCode, e2);
        }
    }

    @Override // android.security.identity.IdentityCredentialStore
    public WritableIdentityCredential createCredential(String str, String str2) throws DocTypeNotSupportedException, AlreadyPersonalizedException {
        try {
            return new CredstoreWritableIdentityCredential(this.mContext, str, str2, this.mStore.createCredential(str, str2));
        } catch (RemoteException e) {
            throw new RuntimeException("Unexpected RemoteException ", e);
        } catch (ServiceSpecificException e2) {
            if (e2.errorCode == 2) {
                throw new AlreadyPersonalizedException(e2.getMessage(), e2);
            }
            if (e2.errorCode == 8) {
                throw new DocTypeNotSupportedException(e2.getMessage(), e2);
            }
            throw new RuntimeException("Unexpected ServiceSpecificException with code " + e2.errorCode, e2);
        }
    }

    @Override // android.security.identity.IdentityCredentialStore
    public IdentityCredential getCredentialByName(String str, int i) throws CipherSuiteNotSupportedException {
        try {
            return new CredstoreIdentityCredential(this.mContext, str, i, this.mStore.getCredentialByName(str, i));
        } catch (RemoteException e) {
            throw new RuntimeException("Unexpected RemoteException ", e);
        } catch (ServiceSpecificException e2) {
            if (e2.errorCode == 3) {
                return null;
            }
            if (e2.errorCode == 4) {
                throw new CipherSuiteNotSupportedException(e2.getMessage(), e2);
            }
            throw new RuntimeException("Unexpected ServiceSpecificException with code " + e2.errorCode, e2);
        }
    }

    @Override // android.security.identity.IdentityCredentialStore
    public byte[] deleteCredentialByName(String str) {
        ICredential credentialByName = null;
        try {
            try {
                credentialByName = this.mStore.getCredentialByName(str, 1);
            } catch (ServiceSpecificException e) {
                try {
                    if (e.errorCode == 3) {
                        return null;
                    }
                } catch (ServiceSpecificException e2) {
                    throw new RuntimeException("Unexpected ServiceSpecificException with code " + e2.errorCode, e2);
                }
            }
            return credentialByName.deleteCredential();
        } catch (RemoteException e3) {
            throw new RuntimeException("Unexpected RemoteException ", e3);
        }
    }
}
