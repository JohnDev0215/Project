package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.Wearable;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class NodeClient extends GoogleApi<Wearable.WearableOptions> {
    public NodeClient(@RecentlyNonNull Activity activity, @RecentlyNonNull GoogleApi.Settings settings) {
        super(activity, Wearable.API, Wearable.WearableOptions.zza, settings);
    }

    @RecentlyNonNull
    public abstract Task<String> getCompanionPackageForNode(@RecentlyNonNull String str);

    @RecentlyNonNull
    public abstract Task<List<Node>> getConnectedNodes();

    @RecentlyNonNull
    public abstract Task<Node> getLocalNode();

    public NodeClient(@RecentlyNonNull Context context, @RecentlyNonNull GoogleApi.Settings settings) {
        super(context, Wearable.API, Wearable.WearableOptions.zza, settings);
    }
}
