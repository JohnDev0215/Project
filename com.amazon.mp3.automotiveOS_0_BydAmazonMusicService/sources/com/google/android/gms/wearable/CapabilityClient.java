package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.Wearable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class CapabilityClient extends GoogleApi<Wearable.WearableOptions> {

    @RecentlyNonNull
    public static final String ACTION_CAPABILITY_CHANGED = "com.google.android.gms.wearable.CAPABILITY_CHANGED";
    public static final int FILTER_ALL = 0;
    public static final int FILTER_LITERAL = 0;
    public static final int FILTER_PREFIX = 1;
    public static final int FILTER_REACHABLE = 1;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface CapabilityFilterType {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface NodeFilterType {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    public interface OnCapabilityChangedListener extends CapabilityApi.CapabilityListener {
        @Override // com.google.android.gms.wearable.CapabilityApi.CapabilityListener
        void onCapabilityChanged(@RecentlyNonNull CapabilityInfo capabilityInfo);
    }

    public CapabilityClient(@RecentlyNonNull Activity activity, @RecentlyNonNull GoogleApi.Settings settings) {
        super(activity, Wearable.API, Wearable.WearableOptions.zza, settings);
    }

    @RecentlyNonNull
    public abstract Task<Void> addListener(@RecentlyNonNull OnCapabilityChangedListener onCapabilityChangedListener, @RecentlyNonNull Uri uri, int i);

    @RecentlyNonNull
    public abstract Task<Void> addListener(@RecentlyNonNull OnCapabilityChangedListener onCapabilityChangedListener, @RecentlyNonNull String str);

    @RecentlyNonNull
    public abstract Task<Void> addLocalCapability(@RecentlyNonNull String str);

    @RecentlyNonNull
    public abstract Task<Map<String, CapabilityInfo>> getAllCapabilities(int i);

    @RecentlyNonNull
    public abstract Task<CapabilityInfo> getCapability(@RecentlyNonNull String str, int i);

    @RecentlyNonNull
    public abstract Task<Boolean> removeListener(@RecentlyNonNull OnCapabilityChangedListener onCapabilityChangedListener);

    @RecentlyNonNull
    public abstract Task<Boolean> removeListener(@RecentlyNonNull OnCapabilityChangedListener onCapabilityChangedListener, @RecentlyNonNull String str);

    @RecentlyNonNull
    public abstract Task<Void> removeLocalCapability(@RecentlyNonNull String str);

    public CapabilityClient(@RecentlyNonNull Context context, @RecentlyNonNull GoogleApi.Settings settings) {
        super(context, Wearable.API, Wearable.WearableOptions.zza, settings);
    }
}
