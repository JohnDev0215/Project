package com.google.android.gms.wearable;

import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public interface ChannelApi {

    @RecentlyNonNull
    public static final String ACTION_CHANNEL_EVENT = "com.google.android.gms.wearable.CHANNEL_EVENT";

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface ChannelListener {
        public static final int CLOSE_REASON_CONNECTION_TIMEOUT = 4;
        public static final int CLOSE_REASON_DISCONNECTED = 1;
        public static final int CLOSE_REASON_LOCAL_CLOSE = 3;
        public static final int CLOSE_REASON_NORMAL = 0;
        public static final int CLOSE_REASON_REMOTE_CLOSE = 2;

        void onChannelClosed(@RecentlyNonNull Channel channel, int i, int i2);

        void onChannelOpened(@RecentlyNonNull Channel channel);

        void onInputClosed(@RecentlyNonNull Channel channel, int i, int i2);

        void onOutputClosed(@RecentlyNonNull Channel channel, int i, int i2);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface CloseReason {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface OpenChannelResult extends Result {
        @RecentlyNullable
        Channel getChannel();
    }

    @RecentlyNonNull
    PendingResult<Status> addListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull ChannelListener channelListener);

    @RecentlyNonNull
    PendingResult<OpenChannelResult> openChannel(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull String str, @RecentlyNonNull String str2);

    @RecentlyNonNull
    PendingResult<Status> removeListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull ChannelListener channelListener);
}
