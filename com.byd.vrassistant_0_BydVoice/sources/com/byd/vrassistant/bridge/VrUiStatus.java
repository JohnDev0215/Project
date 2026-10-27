package com.byd.vrassistant.bridge;

import com.byd.vrassistant.bean.ViewBlewType;
import com.byd.vrassistant.bean.VoiceBarType;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface VrUiStatus {
    void onBackPress();

    void onPickListDataShow(List list);

    void onPickListPageChanged();

    void onViewBlewDismiss(ViewBlewType viewBlewType);

    void onViewBlewShow(ViewBlewType viewBlewType);

    void onVrDismissEnd(VoiceBarType voiceBarType);

    void onVrDismissStart(VoiceBarType voiceBarType);

    void onVrShowEnd(VoiceBarType voiceBarType);

    void onVrShowStart(VoiceBarType voiceBarType);
}
