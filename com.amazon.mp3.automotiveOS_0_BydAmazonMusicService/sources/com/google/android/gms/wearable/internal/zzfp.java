package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.NodeApi;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzfp implements NodeApi {
    @Override // com.google.android.gms.wearable.NodeApi
    public final PendingResult<NodeApi.GetConnectedNodesResult> getConnectedNodes(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzfm(this, googleApiClient));
    }

    @Override // com.google.android.gms.wearable.NodeApi
    public final PendingResult<NodeApi.GetLocalNodeResult> getLocalNode(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzfl(this, googleApiClient));
    }
}
