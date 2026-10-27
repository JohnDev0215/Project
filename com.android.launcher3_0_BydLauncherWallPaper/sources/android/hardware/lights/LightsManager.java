package android.hardware.lights;

import android.annotation.SystemApi;
import android.content.Context;
import android.hardware.lights.ILightsManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.CloseGuard;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.Reference;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class LightsManager {
    public static final int LIGHT_TYPE_MICROPHONE = 8;
    private static final String TAG = "LightsManager";
    private final Context mContext;
    private final ILightsManager mService;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LightType {
    }

    public LightsManager(Context context) throws ServiceManager.ServiceNotFoundException {
        this(context, ILightsManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.LIGHTS_SERVICE)));
    }

    public LightsManager(Context context, ILightsManager iLightsManager) {
        this.mContext = (Context) Preconditions.checkNotNull(context);
        this.mService = (ILightsManager) Preconditions.checkNotNull(iLightsManager);
    }

    public List<Light> getLights() {
        try {
            return this.mService.getLights();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public LightState getLightState(Light light) {
        Preconditions.checkNotNull(light);
        try {
            return this.mService.getLightState(light.getId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public LightsSession openSession() {
        try {
            LightsSession lightsSession = new LightsSession();
            this.mService.openSession(lightsSession.mToken);
            return lightsSession;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public final class LightsSession implements AutoCloseable {
        private final CloseGuard mCloseGuard;
        private boolean mClosed;
        private final IBinder mToken;

        private LightsSession() {
            this.mToken = new Binder();
            CloseGuard closeGuard = new CloseGuard();
            this.mCloseGuard = closeGuard;
            this.mClosed = false;
            closeGuard.open("close");
        }

        public void requestLights(LightsRequest lightsRequest) {
            Preconditions.checkNotNull(lightsRequest);
            if (this.mClosed) {
                return;
            }
            try {
                LightsManager.this.mService.setLightStates(this.mToken, lightsRequest.mLightIds, lightsRequest.mLightStates);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            if (!this.mClosed) {
                try {
                    LightsManager.this.mService.closeSession(this.mToken);
                    this.mClosed = true;
                    this.mCloseGuard.close();
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
            Reference.reachabilityFence(this);
        }

        protected void finalize() throws Throwable {
            try {
                this.mCloseGuard.warnIfOpen();
                close();
            } finally {
                super.finalize();
            }
        }
    }
}
