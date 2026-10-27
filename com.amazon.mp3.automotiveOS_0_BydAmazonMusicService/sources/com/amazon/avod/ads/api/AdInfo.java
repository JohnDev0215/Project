package com.amazon.avod.ads.api;

import com.amazon.avod.ads.api.internal.AdNode;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface AdInfo extends AdNode {
    @Nullable
    String getAdId();

    @Nullable
    String getAdServer();

    List<AdInfo> getDisplayableAds();

    @Nullable
    AdSkipInfo getParentSkipInfo();
}
