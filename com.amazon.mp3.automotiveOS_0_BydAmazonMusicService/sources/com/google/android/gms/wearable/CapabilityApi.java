package com.google.android.gms.wearable;

import android.net.Uri;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public interface CapabilityApi {

    @RecentlyNonNull
    public static final String ACTION_CAPABILITY_CHANGED = "com.google.android.gms.wearable.CAPABILITY_CHANGED";
    public static final int FILTER_ALL = 0;
    public static final int FILTER_LITERAL = 0;
    public static final int FILTER_PREFIX = 1;
    public static final int FILTER_REACHABLE = 1;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface AddLocalCapabilityResult extends Result {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface CapabilityFilterType {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface CapabilityListener {
        void onCapabilityChanged(@RecentlyNonNull CapabilityInfo capabilityInfo);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface GetAllCapabilitiesResult extends Result {
        @RecentlyNonNull
        Map<String, CapabilityInfo> getAllCapabilities();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface GetCapabilityResult extends Result {
        @RecentlyNonNull
        CapabilityInfo getCapability();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface NodeFilterType {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface RemoveLocalCapabilityResult extends Result {
    }

    @RecentlyNonNull
    PendingResult<Status> addCapabilityListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull CapabilityListener capabilityListener, @RecentlyNonNull String str);

    @RecentlyNonNull
    PendingResult<Status> addListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull CapabilityListener capabilityListener, @RecentlyNonNull Uri uri, int i);

    @RecentlyNonNull
    PendingResult<AddLocalCapabilityResult> addLocalCapability(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull String str);

    @RecentlyNonNull
    PendingResult<GetAllCapabilitiesResult> getAllCapabilities(@RecentlyNonNull GoogleApiClient googleApiClient, int i);

    @RecentlyNonNull
    PendingResult<GetCapabilityResult> getCapability(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull String str, int i);

    @RecentlyNonNull
    PendingResult<Status> removeCapabilityListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull CapabilityListener capabilityListener, @RecentlyNonNull String str);

    @RecentlyNonNull
    PendingResult<Status> removeListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull CapabilityListener capabilityListener);

    @RecentlyNonNull
    PendingResult<RemoveLocalCapabilityResult> removeLocalCapability(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull String str);
}
