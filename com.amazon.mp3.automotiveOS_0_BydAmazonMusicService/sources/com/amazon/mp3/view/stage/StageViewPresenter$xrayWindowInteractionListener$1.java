package com.amazon.mp3.view.stage;

import Touch.SwipeablePagesTemplateInterface.v1_0.ImpressionEndReason;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.stage.StageXRayManager;
import com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import kotlin.Metadata;

/* JADX INFO: compiled from: StageViewPresenter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0016¨\u0006\u0004"}, d2 = {"com/amazon/mp3/view/stage/StageViewPresenter$xrayWindowInteractionListener$1", "Lcom/amazon/mp3/view/stage/StageXRayManager$XrayWindowInteractionListener;", "", "onXrayWindowClicked", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StageViewPresenter$xrayWindowInteractionListener$1 implements StageXRayManager.XrayWindowInteractionListener {
    final /* synthetic */ StageViewPresenter this$0;

    StageViewPresenter$xrayWindowInteractionListener$1(StageViewPresenter stageViewPresenter) {
        this.this$0 = stageViewPresenter;
    }

    @Override // com.amazon.mp3.view.stage.StageXRayManager.XrayWindowInteractionListener
    public void onXrayWindowClicked() {
        StageSwipeablePagesView xrayContainer;
        StageSwipeablePagesView xrayContainer2;
        if (ScreenUtil.isPortrait()) {
            StageXRayManager stageXrayManager = this.this$0.getStageXrayManager();
            if ((stageXrayManager == null ? null : stageXrayManager.getXrayContainer()) == null) {
                return;
            }
            StageXRayManager stageXrayManager2 = this.this$0.getStageXrayManager();
            if (stageXrayManager2 != null && (xrayContainer2 = stageXrayManager2.getXrayContainer()) != null) {
                xrayContainer2.impressionEnded(ImpressionEndReason.SWITCH_TO_FULLSCREEN, false);
            }
            StageXRayManager stageXrayManager3 = this.this$0.getStageXrayManager();
            if (stageXrayManager3 != null && (xrayContainer = stageXrayManager3.getXrayContainer()) != null) {
                xrayContainer.onPreviewClicked();
            }
            StageViewPresenter stageViewPresenter = this.this$0;
            StageXRayManager stageXrayManager4 = stageViewPresenter.getStageXrayManager();
            StageSwipeablePagesView xrayContainer3 = stageXrayManager4 == null ? null : stageXrayManager4.getXrayContainer();
            if (xrayContainer3 != null) {
                stageViewPresenter.showXrayFullScreenOverlay(xrayContainer3);
                StageViewModel.sendUiClickEvent$default(this.this$0.getStageViewModel(), ActionType.EXPAND_XRAY_FULL, null, 2, null);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.view.View");
        }
        this.this$0.handleLandscapePlayAreaTap();
    }
}
