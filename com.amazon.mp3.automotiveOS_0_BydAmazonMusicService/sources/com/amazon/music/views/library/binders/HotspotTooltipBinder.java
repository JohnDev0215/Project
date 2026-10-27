package com.amazon.music.views.library.binders;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.models.HotspotTooltipModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.HotspotSupportedView;
import com.amazon.music.views.library.providers.HotspotTooltipListenerProvider;
import com.amazon.music.views.library.views.HotSpotPulse;
import com.amazon.music.views.library.views.HotSpotToolTipDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HotspotTooltipBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/binders/HotspotTooltipBinder;", "", "hotspotTooltipListenerProvider", "Lcom/amazon/music/views/library/providers/HotspotTooltipListenerProvider;", "(Lcom/amazon/music/views/library/providers/HotspotTooltipListenerProvider;)V", "bind", "", "view", "Landroidx/constraintlayout/widget/ConstraintLayout;", "hotspotModel", "Lcom/amazon/music/views/library/models/HotspotTooltipModel;", "targetViewModel", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HotspotTooltipBinder {
    private final HotspotTooltipListenerProvider hotspotTooltipListenerProvider;

    public HotspotTooltipBinder() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public HotspotTooltipBinder(HotspotTooltipListenerProvider hotspotTooltipListenerProvider) {
        this.hotspotTooltipListenerProvider = hotspotTooltipListenerProvider;
    }

    public /* synthetic */ HotspotTooltipBinder(HotspotTooltipListenerProvider hotspotTooltipListenerProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : hotspotTooltipListenerProvider);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void bind(ConstraintLayout view, final HotspotTooltipModel hotspotModel, final BaseViewModel targetViewModel) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(hotspotModel, "hotspotModel");
        Intrinsics.checkNotNullParameter(targetViewModel, "targetViewModel");
        if (!(view instanceof HotspotSupportedView) || this.hotspotTooltipListenerProvider == null) {
            return;
        }
        HotspotSupportedView hotspotSupportedView = (HotspotSupportedView) view;
        final HotSpotPulse hotspotView = hotspotSupportedView.getHotspotView();
        final HotSpotToolTipDialog hotSpotToolTipDialogCreateTooltipView = hotspotSupportedView.createTooltipView();
        View.OnClickListener onClickListener = this.hotspotTooltipListenerProvider.tooltipButtonOnClick(hotspotSupportedView, hotSpotToolTipDialogCreateTooltipView);
        HotSpotToolTipDialog.ToolTipViewTouchListener toolTipViewTouchListener = this.hotspotTooltipListenerProvider.tooltipBackgroundOnClick(hotspotSupportedView, hotSpotToolTipDialogCreateTooltipView);
        hotSpotToolTipDialogCreateTooltipView.setTitle(hotspotModel.getTitle());
        hotSpotToolTipDialogCreateTooltipView.setText(hotspotModel.getText());
        hotSpotToolTipDialogCreateTooltipView.setButton(hotspotModel.getButtonText(), onClickListener);
        hotSpotToolTipDialogCreateTooltipView.setTag(hotspotModel.getHotspotTag());
        hotSpotToolTipDialogCreateTooltipView.setHotspotPageType(hotspotModel.getPageType());
        hotSpotToolTipDialogCreateTooltipView.setOnTouchToolTipViewListener(toolTipViewTouchListener);
        hotSpotToolTipDialogCreateTooltipView.setTooltipListenerProvider(this.hotspotTooltipListenerProvider);
        hotspotView.requestLayout();
        hotspotView.setIsPulsing(true);
        this.hotspotTooltipListenerProvider.onShowHotspotView(hotspotModel.getPageType(), hotspotModel.getHotspotTag());
        hotspotView.setPulseClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.HotspotTooltipBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HotspotTooltipBinder.m2324bind$lambda0(this.f$0, hotspotModel, hotspotView, hotSpotToolTipDialogCreateTooltipView, targetViewModel, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final void m2324bind$lambda0(HotspotTooltipBinder this$0, HotspotTooltipModel hotspotModel, HotSpotPulse hotspot, HotSpotToolTipDialog tooltip, BaseViewModel targetViewModel, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(hotspotModel, "$hotspotModel");
        Intrinsics.checkNotNullParameter(hotspot, "$hotspot");
        Intrinsics.checkNotNullParameter(tooltip, "$tooltip");
        Intrinsics.checkNotNullParameter(targetViewModel, "$targetViewModel");
        this$0.hotspotTooltipListenerProvider.onClickHotspotView(hotspotModel.getPageType(), hotspotModel.getHotspotTag(), hotspot, tooltip, targetViewModel);
    }
}
