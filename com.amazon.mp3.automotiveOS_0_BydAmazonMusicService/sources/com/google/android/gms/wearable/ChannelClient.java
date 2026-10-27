package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.Wearable;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class ChannelClient extends GoogleApi<Wearable.WearableOptions> {

    @RecentlyNonNull
    public static final String ACTION_CHANNEL_EVENT = "com.google.android.gms.wearable.CHANNEL_EVENT";

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    public interface Channel extends Parcelable {
        @RecentlyNonNull
        String getNodeId();

        @RecentlyNonNull
        String getPath();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    public static abstract class ChannelCallback {
        public static final int CLOSE_REASON_DISCONNECTED = 1;
        public static final int CLOSE_REASON_LOCAL_CLOSE = 3;
        public static final int CLOSE_REASON_NORMAL = 0;
        public static final int CLOSE_REASON_REMOTE_CLOSE = 2;

        public void onChannelClosed(@RecentlyNonNull Channel channel, int i, int i2) {
        }

        public void onChannelOpened(@RecentlyNonNull Channel channel) {
        }

        public void onInputClosed(@RecentlyNonNull Channel channel, int i, int i2) {
        }

        public void onOutputClosed(@RecentlyNonNull Channel channel, int i, int i2) {
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface CloseReason {
    }

    public ChannelClient(@RecentlyNonNull Activity activity, @RecentlyNonNull GoogleApi.Settings settings) {
        super(activity, Wearable.API, Wearable.WearableOptions.zza, settings);
    }

    @RecentlyNonNull
    public abstract Task<Void> close(@RecentlyNonNull Channel channel);

    @RecentlyNonNull
    public abstract Task<Void> close(@RecentlyNonNull Channel channel, int i);

    @RecentlyNonNull
    public abstract Task<InputStream> getInputStream(@RecentlyNonNull Channel channel);

    @RecentlyNonNull
    public abstract Task<OutputStream> getOutputStream(@RecentlyNonNull Channel channel);

    @RecentlyNonNull
    public abstract Task<Channel> openChannel(@RecentlyNonNull String str, @RecentlyNonNull String str2);

    @RecentlyNonNull
    public abstract Task<Void> receiveFile(@RecentlyNonNull Channel channel, @RecentlyNonNull Uri uri, boolean z);

    @RecentlyNonNull
    public abstract Task<Void> registerChannelCallback(@RecentlyNonNull Channel channel, @RecentlyNonNull ChannelCallback channelCallback);

    @RecentlyNonNull
    public abstract Task<Void> registerChannelCallback(@RecentlyNonNull ChannelCallback channelCallback);

    @RecentlyNonNull
    public abstract Task<Void> sendFile(@RecentlyNonNull Channel channel, @RecentlyNonNull Uri uri);

    @RecentlyNonNull
    public abstract Task<Void> sendFile(@RecentlyNonNull Channel channel, @RecentlyNonNull Uri uri, long j, long j2);

    @RecentlyNonNull
    public abstract Task<Boolean> unregisterChannelCallback(@RecentlyNonNull Channel channel, @RecentlyNonNull ChannelCallback channelCallback);

    @RecentlyNonNull
    public abstract Task<Boolean> unregisterChannelCallback(@RecentlyNonNull ChannelCallback channelCallback);

    public ChannelClient(@RecentlyNonNull Context context, @RecentlyNonNull GoogleApi.Settings settings) {
        super(context, Wearable.API, Wearable.WearableOptions.zza, settings);
    }
}
