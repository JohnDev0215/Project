package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Wearable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class MessageClient extends GoogleApi<Wearable.WearableOptions> {

    @RecentlyNonNull
    public static final String ACTION_MESSAGE_RECEIVED = "com.google.android.gms.wearable.MESSAGE_RECEIVED";
    public static final int FILTER_LITERAL = 0;
    public static final int FILTER_PREFIX = 1;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface FilterType {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    public interface OnMessageReceivedListener extends MessageApi.MessageListener {
        @Override // com.google.android.gms.wearable.MessageApi.MessageListener
        void onMessageReceived(@RecentlyNonNull MessageEvent messageEvent);
    }

    public MessageClient(@RecentlyNonNull Activity activity, @RecentlyNonNull GoogleApi.Settings settings) {
        super(activity, Wearable.API, Wearable.WearableOptions.zza, settings);
    }

    @RecentlyNonNull
    public abstract Task<Void> addListener(@RecentlyNonNull OnMessageReceivedListener onMessageReceivedListener);

    @RecentlyNonNull
    public abstract Task<Void> addListener(@RecentlyNonNull OnMessageReceivedListener onMessageReceivedListener, @RecentlyNonNull Uri uri, int i);

    @RecentlyNonNull
    public abstract Task<Boolean> removeListener(@RecentlyNonNull OnMessageReceivedListener onMessageReceivedListener);

    @RecentlyNonNull
    public abstract Task<Integer> sendMessage(@RecentlyNonNull String str, @RecentlyNonNull String str2, @Nullable byte[] bArr);

    @RecentlyNonNull
    public abstract Task<Integer> sendMessage(@RecentlyNonNull String str, @RecentlyNonNull String str2, @Nullable byte[] bArr, @RecentlyNonNull MessageOptions messageOptions);

    public MessageClient(@RecentlyNonNull Context context, @RecentlyNonNull GoogleApi.Settings settings) {
        super(context, Wearable.API, Wearable.WearableOptions.zza, settings);
    }
}
