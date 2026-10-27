package com.amazon.avod.ads.parser.vast;

import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastVideoClicks {
    private final VastVideoClickLink mClickThrough;
    private final List<VastVideoClickLink> mClickTracking;
    private final List<VastVideoClickLink> mCustomClick;

    public VastVideoClicks(@Nullable VastVideoClickLink vastVideoClickLink, @Nullable List<VastVideoClickLink> list, @Nullable List<VastVideoClickLink> list2) {
        this.mClickThrough = vastVideoClickLink;
        this.mClickTracking = list;
        this.mCustomClick = list2;
    }

    @Nullable
    public VastVideoClickLink getClickThrough() {
        return this.mClickThrough;
    }
}
