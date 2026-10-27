package android.service.watchdog;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.service.watchdog.IExplicitHealthCheckService;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public abstract class ExplicitHealthCheckService extends Service {
    public static final String BIND_PERMISSION = "android.permission.BIND_EXPLICIT_HEALTH_CHECK_SERVICE";
    public static final String EXTRA_HEALTH_CHECK_PASSED_PACKAGE = "android.service.watchdog.extra.health_check_passed_package";
    public static final String EXTRA_REQUESTED_PACKAGES = "android.service.watchdog.extra.requested_packages";
    public static final String EXTRA_SUPPORTED_PACKAGES = "android.service.watchdog.extra.supported_packages";
    public static final String SERVICE_INTERFACE = "android.service.watchdog.ExplicitHealthCheckService";
    private static final String TAG = "ExplicitHealthCheckService";
    private RemoteCallback mCallback;
    private final ExplicitHealthCheckServiceWrapper mWrapper = new ExplicitHealthCheckServiceWrapper();
    private final Handler mHandler = new Handler(Looper.getMainLooper(), null, true);

    public abstract void onCancelHealthCheck(String str);

    public abstract List<String> onGetRequestedPackages();

    public abstract List<PackageConfig> onGetSupportedPackages();

    public abstract void onRequestHealthCheck(String str);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.mWrapper;
    }

    public void setCallback(RemoteCallback remoteCallback) {
        this.mCallback = remoteCallback;
    }

    public final void notifyHealthCheckPassed(final String str) {
        this.mHandler.post(new Runnable() { // from class: android.service.watchdog.-$$Lambda$ExplicitHealthCheckService$hA6CKZY5TeGn8GEmvyggUkjDQVg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$notifyHealthCheckPassed$0$ExplicitHealthCheckService(str);
            }
        });
    }

    public /* synthetic */ void lambda$notifyHealthCheckPassed$0$ExplicitHealthCheckService(String str) {
        if (this.mCallback != null) {
            Objects.requireNonNull(str, "Package passing explicit health check must be non-null");
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_HEALTH_CHECK_PASSED_PACKAGE, str);
            this.mCallback.sendResult(bundle);
            return;
        }
        Log.wtf(TAG, "System missed explicit health check result for " + str);
    }

    @SystemApi
    public static final class PackageConfig implements Parcelable {
        private final long mHealthCheckTimeoutMillis;
        private final String mPackageName;
        private static final long DEFAULT_HEALTH_CHECK_TIMEOUT_MILLIS = TimeUnit.HOURS.toMillis(1);
        public static final Parcelable.Creator<PackageConfig> CREATOR = new Parcelable.Creator<PackageConfig>() { // from class: android.service.watchdog.ExplicitHealthCheckService.PackageConfig.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PackageConfig createFromParcel(Parcel parcel) {
                return new PackageConfig(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PackageConfig[] newArray(int i) {
                return new PackageConfig[i];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public PackageConfig(String str, long j) {
            this.mPackageName = (String) Preconditions.checkNotNull(str);
            if (j == 0) {
                this.mHealthCheckTimeoutMillis = DEFAULT_HEALTH_CHECK_TIMEOUT_MILLIS;
            } else {
                this.mHealthCheckTimeoutMillis = Preconditions.checkArgumentNonnegative(j);
            }
        }

        private PackageConfig(Parcel parcel) {
            this.mPackageName = parcel.readString();
            this.mHealthCheckTimeoutMillis = parcel.readLong();
        }

        public String getPackageName() {
            return this.mPackageName;
        }

        public long getHealthCheckTimeoutMillis() {
            return this.mHealthCheckTimeoutMillis;
        }

        public String toString() {
            return "PackageConfig{" + this.mPackageName + ", " + this.mHealthCheckTimeoutMillis + "}";
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PackageConfig)) {
                return false;
            }
            PackageConfig packageConfig = (PackageConfig) obj;
            return Objects.equals(Long.valueOf(packageConfig.getHealthCheckTimeoutMillis()), Long.valueOf(this.mHealthCheckTimeoutMillis)) && Objects.equals(packageConfig.getPackageName(), this.mPackageName);
        }

        public int hashCode() {
            return Objects.hash(this.mPackageName, Long.valueOf(this.mHealthCheckTimeoutMillis));
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mPackageName);
            parcel.writeLong(this.mHealthCheckTimeoutMillis);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ExplicitHealthCheckServiceWrapper extends IExplicitHealthCheckService.Stub {
        private ExplicitHealthCheckServiceWrapper() {
        }

        @Override // android.service.watchdog.IExplicitHealthCheckService
        public void setCallback(final RemoteCallback remoteCallback) throws RemoteException {
            ExplicitHealthCheckService.this.mHandler.post(new Runnable() { // from class: android.service.watchdog.-$$Lambda$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper$1I1lIc2oq2p0dd_9Xb2hD3PzZrE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setCallback$0$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper(remoteCallback);
                }
            });
        }

        public /* synthetic */ void lambda$setCallback$0$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper(RemoteCallback remoteCallback) {
            ExplicitHealthCheckService.this.mCallback = remoteCallback;
        }

        public /* synthetic */ void lambda$request$1$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper(String str) {
            ExplicitHealthCheckService.this.onRequestHealthCheck(str);
        }

        @Override // android.service.watchdog.IExplicitHealthCheckService
        public void request(final String str) throws RemoteException {
            ExplicitHealthCheckService.this.mHandler.post(new Runnable() { // from class: android.service.watchdog.-$$Lambda$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper$GNEFbwBylisP4MO0Shec806zeXg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$request$1$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper(str);
                }
            });
        }

        @Override // android.service.watchdog.IExplicitHealthCheckService
        public void cancel(final String str) throws RemoteException {
            ExplicitHealthCheckService.this.mHandler.post(new Runnable() { // from class: android.service.watchdog.-$$Lambda$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper$g8isM74RFlj499VUU_MmyaYNoZI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$cancel$2$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper(str);
                }
            });
        }

        public /* synthetic */ void lambda$cancel$2$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper(String str) {
            ExplicitHealthCheckService.this.onCancelHealthCheck(str);
        }

        @Override // android.service.watchdog.IExplicitHealthCheckService
        public void getSupportedPackages(final RemoteCallback remoteCallback) throws RemoteException {
            ExplicitHealthCheckService.this.mHandler.post(new Runnable() { // from class: android.service.watchdog.-$$Lambda$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper$Y26yt8vnmC1Sv1GPU2BpPfyW98M
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$getSupportedPackages$3$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper(remoteCallback);
                }
            });
        }

        public /* synthetic */ void lambda$getSupportedPackages$3$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper(RemoteCallback remoteCallback) {
            List<PackageConfig> listOnGetSupportedPackages = ExplicitHealthCheckService.this.onGetSupportedPackages();
            Objects.requireNonNull(listOnGetSupportedPackages, "Supported package list must be non-null");
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(ExplicitHealthCheckService.EXTRA_SUPPORTED_PACKAGES, new ArrayList<>(listOnGetSupportedPackages));
            remoteCallback.sendResult(bundle);
        }

        @Override // android.service.watchdog.IExplicitHealthCheckService
        public void getRequestedPackages(final RemoteCallback remoteCallback) throws RemoteException {
            ExplicitHealthCheckService.this.mHandler.post(new Runnable() { // from class: android.service.watchdog.-$$Lambda$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper$QofR_6i7pIQGOe_WsBPKue1JdDI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$getRequestedPackages$4$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper(remoteCallback);
                }
            });
        }

        public /* synthetic */ void lambda$getRequestedPackages$4$ExplicitHealthCheckService$ExplicitHealthCheckServiceWrapper(RemoteCallback remoteCallback) {
            List<String> listOnGetRequestedPackages = ExplicitHealthCheckService.this.onGetRequestedPackages();
            Objects.requireNonNull(listOnGetRequestedPackages, "Requested  package list must be non-null");
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(ExplicitHealthCheckService.EXTRA_REQUESTED_PACKAGES, new ArrayList<>(listOnGetRequestedPackages));
            remoteCallback.sendResult(bundle);
        }
    }
}
