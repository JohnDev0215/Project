package com.google.android.gms.wearable;

import android.net.Uri;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public interface MessageApi {

    @RecentlyNonNull
    public static final String ACTION_MESSAGE_RECEIVED = "com.google.android.gms.wearable.MESSAGE_RECEIVED";
    public static final int FILTER_LITERAL = 0;
    public static final int FILTER_PREFIX = 1;
    public static final int UNKNOWN_REQUEST_ID = -1;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface FilterType {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface MessageListener {
        void onMessageReceived(@RecentlyNonNull MessageEvent messageEvent);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface SendMessageResult extends Result {
        int getRequestId();
    }

    @RecentlyNonNull
    PendingResult<Status> addListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull MessageListener messageListener);

    @RecentlyNonNull
    PendingResult<Status> addListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull MessageListener messageListener, @RecentlyNonNull Uri uri, int i);

    @RecentlyNonNull
    PendingResult<Status> removeListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull MessageListener messageListener);

    @RecentlyNonNull
    PendingResult<SendMessageResult> sendMessage(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull String str, @RecentlyNonNull String str2, @RecentlyNonNull byte[] bArr);
}
