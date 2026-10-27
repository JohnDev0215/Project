package com.google.android.gms.cast;

import androidx.annotation.NonNull;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public interface CastRemoteDisplayApi {
    @NonNull
    PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> startRemoteDisplay(@NonNull GoogleApiClient googleApiClient, @NonNull String str);

    @NonNull
    PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> stopRemoteDisplay(@NonNull GoogleApiClient googleApiClient);
}
