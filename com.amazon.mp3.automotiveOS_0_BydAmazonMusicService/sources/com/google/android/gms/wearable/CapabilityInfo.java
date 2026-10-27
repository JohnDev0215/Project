package com.google.android.gms.wearable;

import androidx.annotation.RecentlyNonNull;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface CapabilityInfo {
    @RecentlyNonNull
    String getName();

    @RecentlyNonNull
    Set<Node> getNodes();
}
