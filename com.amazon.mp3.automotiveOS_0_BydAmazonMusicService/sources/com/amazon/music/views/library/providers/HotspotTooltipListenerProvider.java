package com.amazon.music.views.library.providers;

import android.view.View;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.HotspotSupportedView;
import com.amazon.music.views.library.views.HotSpotPulse;
import com.amazon.music.views.library.views.HotSpotToolTipDialog;
import kotlin.Metadata;

/* JADX INFO: compiled from: HotspotTooltipListenerProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\"\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u000bH&J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/views/library/providers/HotspotTooltipListenerProvider;", "", "onClickHotspotView", "", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "hotspotTag", "", "hotspot", "Lcom/amazon/music/views/library/views/HotSpotPulse;", "tooltip", "Lcom/amazon/music/views/library/views/HotSpotToolTipDialog;", "targetViewModel", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "onDismissToolTipView", "isToolTipButtonPressed", "", "onShowHotspotView", "onShowToolTipView", "tooltipBackgroundOnClick", "Lcom/amazon/music/views/library/views/HotSpotToolTipDialog$ToolTipViewTouchListener;", "view", "Lcom/amazon/music/views/library/models/base/HotspotSupportedView;", "tooltipButtonOnClick", "Landroid/view/View$OnClickListener;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface HotspotTooltipListenerProvider {
    void onClickHotspotView(PageType pageType, String hotspotTag, HotSpotPulse hotspot, HotSpotToolTipDialog tooltip, BaseViewModel targetViewModel);

    void onDismissToolTipView(boolean isToolTipButtonPressed, PageType pageType, String hotspotTag);

    void onShowHotspotView(PageType pageType, String hotspotTag);

    void onShowToolTipView(PageType pageType, String hotspotTag);

    HotSpotToolTipDialog.ToolTipViewTouchListener tooltipBackgroundOnClick(HotspotSupportedView view, HotSpotToolTipDialog tooltip);

    View.OnClickListener tooltipButtonOnClick(HotspotSupportedView view, HotSpotToolTipDialog tooltip);
}
