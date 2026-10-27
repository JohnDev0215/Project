package android.media.tv.tunerresourcemanager;

import android.media.tv.tunerresourcemanager.IResourcesReclaimListener;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class TunerResourceManager {
    public static final int INVALID_OWNER_ID = -1;
    public static final int INVALID_RESOURCE_HANDLE = -1;
    public static final int TUNER_RESOURCE_TYPE_CAS_SESSION = 4;
    public static final int TUNER_RESOURCE_TYPE_DEMUX = 1;
    public static final int TUNER_RESOURCE_TYPE_DESCRAMBLER = 2;
    public static final int TUNER_RESOURCE_TYPE_FRONTEND = 0;
    public static final int TUNER_RESOURCE_TYPE_LNB = 3;
    public static final int TUNER_RESOURCE_TYPE_MAX = 5;
    private final ITunerResourceManager mService;
    private final int mUserId;
    private static final String TAG = "TunerResourceManager";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);

    public static abstract class ResourcesReclaimListener {
        public abstract void onReclaimResources();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TunerResourceType {
    }

    public TunerResourceManager(ITunerResourceManager iTunerResourceManager, int i) {
        this.mService = iTunerResourceManager;
        this.mUserId = i;
    }

    /* JADX INFO: renamed from: android.media.tv.tunerresourcemanager.TunerResourceManager$1, reason: invalid class name */
    class AnonymousClass1 extends IResourcesReclaimListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ ResourcesReclaimListener val$listener;

        AnonymousClass1(Executor executor, ResourcesReclaimListener resourcesReclaimListener) {
            this.val$executor = executor;
            this.val$listener = resourcesReclaimListener;
        }

        @Override // android.media.tv.tunerresourcemanager.IResourcesReclaimListener
        public void onReclaimResources() {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final ResourcesReclaimListener resourcesReclaimListener = this.val$listener;
                executor.execute(new Runnable() { // from class: android.media.tv.tunerresourcemanager.-$$Lambda$TunerResourceManager$1$GH95nzrFtw_d9fH9Hia82564Yrs
                    @Override // java.lang.Runnable
                    public final void run() {
                        resourcesReclaimListener.onReclaimResources();
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void registerClientProfile(ResourceClientProfile resourceClientProfile, Executor executor, ResourcesReclaimListener resourcesReclaimListener, int[] iArr) {
        try {
            this.mService.registerClientProfile(resourceClientProfile, new AnonymousClass1(executor, resourcesReclaimListener), iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unregisterClientProfile(int i) {
        try {
            this.mService.unregisterClientProfile(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean updateClientPriority(int i, int i2, int i3) {
        try {
            return this.mService.updateClientPriority(i, i2, i3);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setFrontendInfoList(TunerFrontendInfo[] tunerFrontendInfoArr) {
        try {
            this.mService.setFrontendInfoList(tunerFrontendInfoArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void updateCasInfo(int i, int i2) {
        try {
            this.mService.updateCasInfo(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setLnbInfoList(int[] iArr) {
        try {
            this.mService.setLnbInfoList(iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean requestFrontend(TunerFrontendRequest tunerFrontendRequest, int[] iArr) {
        try {
            return this.mService.requestFrontend(tunerFrontendRequest, iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void shareFrontend(int i, int i2) {
        try {
            this.mService.shareFrontend(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean requestDemux(TunerDemuxRequest tunerDemuxRequest, int[] iArr) {
        try {
            return this.mService.requestDemux(tunerDemuxRequest, iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean requestDescrambler(TunerDescramblerRequest tunerDescramblerRequest, int[] iArr) {
        try {
            return this.mService.requestDescrambler(tunerDescramblerRequest, iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean requestCasSession(CasSessionRequest casSessionRequest, int[] iArr) {
        try {
            return this.mService.requestCasSession(casSessionRequest, iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean requestLnb(TunerLnbRequest tunerLnbRequest, int[] iArr) {
        try {
            return this.mService.requestLnb(tunerLnbRequest, iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void releaseFrontend(int i, int i2) {
        try {
            this.mService.releaseFrontend(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void releaseDemux(int i, int i2) {
        try {
            this.mService.releaseDemux(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void releaseDescrambler(int i, int i2) {
        try {
            this.mService.releaseDescrambler(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void releaseCasSession(int i, int i2) {
        try {
            this.mService.releaseCasSession(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void releaseLnb(int i, int i2) {
        try {
            this.mService.releaseLnb(i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isHigherPriority(ResourceClientProfile resourceClientProfile, ResourceClientProfile resourceClientProfile2) {
        try {
            return this.mService.isHigherPriority(resourceClientProfile, resourceClientProfile2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
