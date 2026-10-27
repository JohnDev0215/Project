package com.byd.vrassistant.bridge;

import android.util.Log;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import com.byd.vrassistant.bean.BeanTransform;
import com.byd.vrassistant.bean.ViewBlewType;
import com.byd.vrassistant.bean.VoiceBarType;
import com.byd.vrassistant.conversation.NaviHelper;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.utils.ExecutorUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class VrUiStatusImpl implements VrUiStatus {
    private static final String TAG = "NEU_VrUiStatusImpl";

    @Override // com.byd.vrassistant.bridge.VrUiStatus
    public void onBackPress() {
        reqDismissPoiDetail();
    }

    @Override // com.byd.vrassistant.bridge.VrUiStatus
    public void onPickListPageChanged() {
        reqDismissPoiDetail();
    }

    @Override // com.byd.vrassistant.bridge.VrUiStatus
    public void onPickListDataShow(List list) {
        boolean z = false;
        boolean z2 = NaviHelper.getInstance().checkNaviGuide() == 1;
        if (!list.isEmpty() && (list.get(0) instanceof PlaceInfo)) {
            z = true;
        }
        if (z2 || !z) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        list.forEach(new Consumer() { // from class: com.byd.vrassistant.bridge.VrUiStatusImpl$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add(BeanTransform.getWithPlaceInfo((PlaceInfo) obj));
            }
        });
        NaviHelper.getInstance().reqShowPlaceMark(arrayList);
    }

    private void reqDismissPoiDetail() {
        ExecutorUtils.execute(new Runnable() { // from class: com.byd.vrassistant.bridge.VrUiStatusImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VrUiStatusImpl.lambda$reqDismissPoiDetail$1();
            }
        });
    }

    static /* synthetic */ void lambda$reqDismissPoiDetail$1() {
        int pOIDetailStatus = NaviHelper.getInstance().getPOIDetailStatus();
        if (pOIDetailStatus == 1 || pOIDetailStatus == 2) {
            NaviHelper.getInstance().reqClosePOIDetail();
        }
    }

    @Override // com.byd.vrassistant.bridge.VrUiStatus
    public void onVrShowStart(VoiceBarType voiceBarType) {
        Log.i(TAG, "onVrShowStart: ");
        SystemSettingManager.get().setVrAssistantStatus(true);
    }

    @Override // com.byd.vrassistant.bridge.VrUiStatus
    public void onVrShowEnd(VoiceBarType voiceBarType) {
        Log.i(TAG, "onVrShowEnd: ");
    }

    @Override // com.byd.vrassistant.bridge.VrUiStatus
    public void onVrDismissStart(VoiceBarType voiceBarType) {
        Log.i(TAG, "onVrDismissStart: ");
    }

    @Override // com.byd.vrassistant.bridge.VrUiStatus
    public void onVrDismissEnd(VoiceBarType voiceBarType) {
        Log.i(TAG, "onVrDismissEnd: ");
        SystemSettingManager.get().setVrAssistantStatus(false);
    }

    @Override // com.byd.vrassistant.bridge.VrUiStatus
    public void onViewBlewShow(ViewBlewType viewBlewType) {
        Log.i(TAG, "onViewBlewShow: ");
    }

    @Override // com.byd.vrassistant.bridge.VrUiStatus
    public void onViewBlewDismiss(ViewBlewType viewBlewType) {
        Log.i(TAG, "onViewBlewDismiss: ");
        if (viewBlewType == ViewBlewType.UDE_POI || viewBlewType == ViewBlewType.UDE_RECENT) {
            NaviHelper.getInstance().reqHidePlaceMark();
        }
    }
}
