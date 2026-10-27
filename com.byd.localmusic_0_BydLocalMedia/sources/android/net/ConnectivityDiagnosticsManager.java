package android.net;

import android.content.Context;
import android.net.IConnectivityDiagnosticsCallback;
import android.os.Binder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.RemoteException;
import com.android.internal.util.FunctionalUtils;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class ConnectivityDiagnosticsManager {
    public static final Map<ConnectivityDiagnosticsCallback, ConnectivityDiagnosticsBinder> sCallbacks = new ConcurrentHashMap();
    private final Context mContext;
    private final IConnectivityManager mService;

    public static abstract class ConnectivityDiagnosticsCallback {
        public void onConnectivityReportAvailable(ConnectivityReport connectivityReport) {
        }

        public void onDataStallSuspected(DataStallReport dataStallReport) {
        }

        public void onNetworkConnectivityReported(Network network, boolean z) {
        }
    }

    public ConnectivityDiagnosticsManager(Context context, IConnectivityManager iConnectivityManager) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "missing context");
        this.mService = (IConnectivityManager) Preconditions.checkNotNull(iConnectivityManager, "missing IConnectivityManager");
    }

    public static boolean persistableBundleEquals(PersistableBundle persistableBundle, PersistableBundle persistableBundle2) {
        if (persistableBundle == persistableBundle2) {
            return true;
        }
        if (persistableBundle == null || persistableBundle2 == null || !Objects.equals(persistableBundle.keySet(), persistableBundle2.keySet())) {
            return false;
        }
        for (String str : persistableBundle.keySet()) {
            if (!Objects.equals(persistableBundle.get(str), persistableBundle2.get(str))) {
                return false;
            }
        }
        return true;
    }

    public static final class ConnectivityReport implements Parcelable {
        public static final Parcelable.Creator<ConnectivityReport> CREATOR = new Parcelable.Creator<ConnectivityReport>() { // from class: android.net.ConnectivityDiagnosticsManager.ConnectivityReport.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ConnectivityReport createFromParcel(Parcel parcel) {
                return new ConnectivityReport((Network) parcel.readParcelable(null), parcel.readLong(), (LinkProperties) parcel.readParcelable(null), (NetworkCapabilities) parcel.readParcelable(null), (PersistableBundle) parcel.readParcelable(null));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ConnectivityReport[] newArray(int i) {
                return new ConnectivityReport[i];
            }
        };
        public static final String KEY_NETWORK_PROBES_ATTEMPTED_BITMASK = "networkProbesAttempted";
        public static final String KEY_NETWORK_PROBES_SUCCEEDED_BITMASK = "networkProbesSucceeded";
        public static final String KEY_NETWORK_VALIDATION_RESULT = "networkValidationResult";
        public static final int NETWORK_PROBE_DNS = 4;
        public static final int NETWORK_PROBE_FALLBACK = 32;
        public static final int NETWORK_PROBE_HTTP = 8;
        public static final int NETWORK_PROBE_HTTPS = 16;
        public static final int NETWORK_PROBE_PRIVATE_DNS = 64;
        public static final int NETWORK_VALIDATION_RESULT_INVALID = 0;
        public static final int NETWORK_VALIDATION_RESULT_PARTIALLY_VALID = 2;
        public static final int NETWORK_VALIDATION_RESULT_SKIPPED = 3;
        public static final int NETWORK_VALIDATION_RESULT_VALID = 1;
        private final PersistableBundle mAdditionalInfo;
        private final LinkProperties mLinkProperties;
        private final Network mNetwork;
        private final NetworkCapabilities mNetworkCapabilities;
        private final long mReportTimestamp;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ConnectivityReportBundleKeys {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface NetworkProbe {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface NetworkValidationResult {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public ConnectivityReport(Network network, long j, LinkProperties linkProperties, NetworkCapabilities networkCapabilities, PersistableBundle persistableBundle) {
            this.mNetwork = network;
            this.mReportTimestamp = j;
            this.mLinkProperties = new LinkProperties(linkProperties);
            this.mNetworkCapabilities = new NetworkCapabilities(networkCapabilities);
            this.mAdditionalInfo = persistableBundle;
        }

        public Network getNetwork() {
            return this.mNetwork;
        }

        public long getReportTimestamp() {
            return this.mReportTimestamp;
        }

        public LinkProperties getLinkProperties() {
            return new LinkProperties(this.mLinkProperties);
        }

        public NetworkCapabilities getNetworkCapabilities() {
            return new NetworkCapabilities(this.mNetworkCapabilities);
        }

        public PersistableBundle getAdditionalInfo() {
            return new PersistableBundle(this.mAdditionalInfo);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ConnectivityReport)) {
                return false;
            }
            ConnectivityReport connectivityReport = (ConnectivityReport) obj;
            return this.mReportTimestamp == connectivityReport.mReportTimestamp && this.mNetwork.equals(connectivityReport.mNetwork) && this.mLinkProperties.equals(connectivityReport.mLinkProperties) && this.mNetworkCapabilities.equals(connectivityReport.mNetworkCapabilities) && ConnectivityDiagnosticsManager.persistableBundleEquals(this.mAdditionalInfo, connectivityReport.mAdditionalInfo);
        }

        public int hashCode() {
            return Objects.hash(this.mNetwork, Long.valueOf(this.mReportTimestamp), this.mLinkProperties, this.mNetworkCapabilities, this.mAdditionalInfo);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mNetwork, i);
            parcel.writeLong(this.mReportTimestamp);
            parcel.writeParcelable(this.mLinkProperties, i);
            parcel.writeParcelable(this.mNetworkCapabilities, i);
            parcel.writeParcelable(this.mAdditionalInfo, i);
        }
    }

    public static final class DataStallReport implements Parcelable {
        public static final Parcelable.Creator<DataStallReport> CREATOR = new Parcelable.Creator<DataStallReport>() { // from class: android.net.ConnectivityDiagnosticsManager.DataStallReport.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public DataStallReport createFromParcel(Parcel parcel) {
                return new DataStallReport((Network) parcel.readParcelable(null), parcel.readLong(), parcel.readInt(), (LinkProperties) parcel.readParcelable(null), (NetworkCapabilities) parcel.readParcelable(null), (PersistableBundle) parcel.readParcelable(null));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public DataStallReport[] newArray(int i) {
                return new DataStallReport[i];
            }
        };
        public static final int DETECTION_METHOD_DNS_EVENTS = 1;
        public static final int DETECTION_METHOD_TCP_METRICS = 2;
        public static final String KEY_DNS_CONSECUTIVE_TIMEOUTS = "dnsConsecutiveTimeouts";
        public static final String KEY_TCP_METRICS_COLLECTION_PERIOD_MILLIS = "tcpMetricsCollectionPeriodMillis";
        public static final String KEY_TCP_PACKET_FAIL_RATE = "tcpPacketFailRate";
        private final int mDetectionMethod;
        private final LinkProperties mLinkProperties;
        private final Network mNetwork;
        private final NetworkCapabilities mNetworkCapabilities;
        private long mReportTimestamp;
        private final PersistableBundle mStallDetails;

        @Retention(RetentionPolicy.SOURCE)
        public @interface DataStallReportBundleKeys {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface DetectionMethod {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public DataStallReport(Network network, long j, int i, LinkProperties linkProperties, NetworkCapabilities networkCapabilities, PersistableBundle persistableBundle) {
            this.mNetwork = network;
            this.mReportTimestamp = j;
            this.mDetectionMethod = i;
            this.mLinkProperties = new LinkProperties(linkProperties);
            this.mNetworkCapabilities = new NetworkCapabilities(networkCapabilities);
            this.mStallDetails = persistableBundle;
        }

        public Network getNetwork() {
            return this.mNetwork;
        }

        public long getReportTimestamp() {
            return this.mReportTimestamp;
        }

        public int getDetectionMethod() {
            return this.mDetectionMethod;
        }

        public LinkProperties getLinkProperties() {
            return new LinkProperties(this.mLinkProperties);
        }

        public NetworkCapabilities getNetworkCapabilities() {
            return new NetworkCapabilities(this.mNetworkCapabilities);
        }

        public PersistableBundle getStallDetails() {
            return new PersistableBundle(this.mStallDetails);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DataStallReport)) {
                return false;
            }
            DataStallReport dataStallReport = (DataStallReport) obj;
            return this.mReportTimestamp == dataStallReport.mReportTimestamp && this.mDetectionMethod == dataStallReport.mDetectionMethod && this.mNetwork.equals(dataStallReport.mNetwork) && this.mLinkProperties.equals(dataStallReport.mLinkProperties) && this.mNetworkCapabilities.equals(dataStallReport.mNetworkCapabilities) && ConnectivityDiagnosticsManager.persistableBundleEquals(this.mStallDetails, dataStallReport.mStallDetails);
        }

        public int hashCode() {
            return Objects.hash(this.mNetwork, Long.valueOf(this.mReportTimestamp), Integer.valueOf(this.mDetectionMethod), this.mLinkProperties, this.mNetworkCapabilities, this.mStallDetails);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mNetwork, i);
            parcel.writeLong(this.mReportTimestamp);
            parcel.writeInt(this.mDetectionMethod);
            parcel.writeParcelable(this.mLinkProperties, i);
            parcel.writeParcelable(this.mNetworkCapabilities, i);
            parcel.writeParcelable(this.mStallDetails, i);
        }
    }

    public static class ConnectivityDiagnosticsBinder extends IConnectivityDiagnosticsCallback.Stub {
        private final ConnectivityDiagnosticsCallback mCb;
        private final Executor mExecutor;

        public ConnectivityDiagnosticsBinder(ConnectivityDiagnosticsCallback connectivityDiagnosticsCallback, Executor executor) {
            this.mCb = connectivityDiagnosticsCallback;
            this.mExecutor = executor;
        }

        @Override // android.net.IConnectivityDiagnosticsCallback
        public void onConnectivityReportAvailable(final ConnectivityReport connectivityReport) {
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.-$$Lambda$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder$FGvmuK56MYkuwwlBPZ9M93t2UqY
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onConnectivityReportAvailable$1$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder(connectivityReport);
                }
            });
        }

        public /* synthetic */ void lambda$onConnectivityReportAvailable$1$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder(final ConnectivityReport connectivityReport) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.net.-$$Lambda$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder$lWpuEpaS9Asp8olPn0ZHvDXKBsY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onConnectivityReportAvailable$0$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder(connectivityReport);
                }
            });
        }

        public /* synthetic */ void lambda$onConnectivityReportAvailable$0$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder(ConnectivityReport connectivityReport) {
            this.mCb.onConnectivityReportAvailable(connectivityReport);
        }

        @Override // android.net.IConnectivityDiagnosticsCallback
        public void onDataStallSuspected(final DataStallReport dataStallReport) {
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.-$$Lambda$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder$oQ8vg4N13Yt69vO4YyGcJmSjA-U
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onDataStallSuspected$3$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder(dataStallReport);
                }
            });
        }

        public /* synthetic */ void lambda$onDataStallSuspected$3$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder(final DataStallReport dataStallReport) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.net.-$$Lambda$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder$Z0TUq7QTgpwW5qFuK7Z3dk74jB0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDataStallSuspected$2$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder(dataStallReport);
                }
            });
        }

        public /* synthetic */ void lambda$onDataStallSuspected$2$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder(DataStallReport dataStallReport) {
            this.mCb.onDataStallSuspected(dataStallReport);
        }

        @Override // android.net.IConnectivityDiagnosticsCallback
        public void onNetworkConnectivityReported(final Network network, final boolean z) {
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.-$$Lambda$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder$-i7Xc4PglBiVdW9VXLaiY3i_ufk
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onNetworkConnectivityReported$5$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder(network, z);
                }
            });
        }

        public /* synthetic */ void lambda$onNetworkConnectivityReported$5$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder(final Network network, final boolean z) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.net.-$$Lambda$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder$5egkMKdBjwQ1BLrH78YD5OHumY0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onNetworkConnectivityReported$4$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder(network, z);
                }
            });
        }

        public /* synthetic */ void lambda$onNetworkConnectivityReported$4$ConnectivityDiagnosticsManager$ConnectivityDiagnosticsBinder(Network network, boolean z) {
            this.mCb.onNetworkConnectivityReported(network, z);
        }
    }

    public void registerConnectivityDiagnosticsCallback(NetworkRequest networkRequest, Executor executor, ConnectivityDiagnosticsCallback connectivityDiagnosticsCallback) {
        ConnectivityDiagnosticsBinder connectivityDiagnosticsBinder = new ConnectivityDiagnosticsBinder(connectivityDiagnosticsCallback, executor);
        if (sCallbacks.putIfAbsent(connectivityDiagnosticsCallback, connectivityDiagnosticsBinder) != null) {
            throw new IllegalArgumentException("Callback is currently registered");
        }
        try {
            this.mService.registerConnectivityDiagnosticsCallback(connectivityDiagnosticsBinder, networkRequest, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void unregisterConnectivityDiagnosticsCallback(ConnectivityDiagnosticsCallback connectivityDiagnosticsCallback) {
        ConnectivityDiagnosticsBinder connectivityDiagnosticsBinderRemove = sCallbacks.remove(connectivityDiagnosticsCallback);
        if (connectivityDiagnosticsBinderRemove == null) {
            return;
        }
        try {
            this.mService.unregisterConnectivityDiagnosticsCallback(connectivityDiagnosticsBinderRemove);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }
}
