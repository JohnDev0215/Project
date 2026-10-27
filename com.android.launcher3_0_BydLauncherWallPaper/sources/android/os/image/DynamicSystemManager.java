package android.os.image;

import android.gsi.AvbPublicKey;
import android.gsi.GsiProgress;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicSystemManager {
    private static final String TAG = "DynamicSystemManager";
    private final IDynamicSystemService mService;

    public DynamicSystemManager(IDynamicSystemService iDynamicSystemService) {
        this.mService = iDynamicSystemService;
    }

    public class Session {
        private Session() {
        }

        public boolean setAshmem(ParcelFileDescriptor parcelFileDescriptor, long j) {
            try {
                return DynamicSystemManager.this.mService.setAshmem(parcelFileDescriptor, j);
            } catch (RemoteException e) {
                throw new RuntimeException(e.toString());
            }
        }

        public boolean submitFromAshmem(int i) {
            try {
                return DynamicSystemManager.this.mService.submitFromAshmem(i);
            } catch (RemoteException e) {
                throw new RuntimeException(e.toString());
            }
        }

        public boolean getAvbPublicKey(AvbPublicKey avbPublicKey) {
            try {
                return DynamicSystemManager.this.mService.getAvbPublicKey(avbPublicKey);
            } catch (RemoteException e) {
                throw new RuntimeException(e.toString());
            }
        }

        public boolean commit() {
            try {
                return DynamicSystemManager.this.mService.setEnable(true, true);
            } catch (RemoteException e) {
                throw new RuntimeException(e.toString());
            }
        }
    }

    public boolean startInstallation(String str) {
        try {
            return this.mService.startInstallation(str);
        } catch (RemoteException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public Session createPartition(String str, long j, boolean z) {
        try {
            boolean zCreatePartition = this.mService.createPartition(str, j, z);
            if (zCreatePartition) {
                return new Session();
            }
            return null;
        } catch (RemoteException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public boolean finishInstallation() {
        try {
            return this.mService.finishInstallation();
        } catch (RemoteException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public GsiProgress getInstallationProgress() {
        try {
            return this.mService.getInstallationProgress();
        } catch (RemoteException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public boolean abort() {
        try {
            return this.mService.abort();
        } catch (RemoteException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public boolean isInUse() {
        try {
            return this.mService.isInUse();
        } catch (RemoteException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public boolean isInstalled() {
        try {
            return this.mService.isInstalled();
        } catch (RemoteException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public boolean isEnabled() {
        try {
            return this.mService.isEnabled();
        } catch (RemoteException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public boolean remove() {
        try {
            return this.mService.remove();
        } catch (RemoteException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public boolean setEnable(boolean z, boolean z2) {
        try {
            return this.mService.setEnable(z, z2);
        } catch (RemoteException e) {
            throw new RuntimeException(e.toString());
        }
    }
}
