package com.google.android.gms.wearable;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public interface NodeApi {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface GetConnectedNodesResult extends Result {
        @RecentlyNonNull
        List<Node> getNodes();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    @Deprecated
    public interface GetLocalNodeResult extends Result {
        @RecentlyNonNull
        Node getNode();
    }

    @RecentlyNonNull
    PendingResult<GetConnectedNodesResult> getConnectedNodes(@RecentlyNonNull GoogleApiClient googleApiClient);

    @RecentlyNonNull
    PendingResult<GetLocalNodeResult> getLocalNode(@RecentlyNonNull GoogleApiClient googleApiClient);
}
