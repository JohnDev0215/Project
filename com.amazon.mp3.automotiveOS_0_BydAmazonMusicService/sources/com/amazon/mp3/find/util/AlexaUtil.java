package com.amazon.mp3.find.util;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.bottomnavbar.events.BottomNavBarEvent;
import com.amazon.mp3.bottomnavbar.events.BottomNavBarEventType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: AlexaUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0003\u001a\u00020\u0002H\u0007J\b\u0010\u0004\u001a\u00020\u0002H\u0007J\b\u0010\u0005\u001a\u00020\u0002H\u0007J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u0002¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/find/util/AlexaUtil;", "", "", "isAlexaSearchFindBrowseEnabled", "isAlexaSearchOrdinalPlaybackEnabled", "isAlexaSearchFindBrowseInCarModeEnabled", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "", "triggerAlexa", "shouldShowAlexaButton", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AlexaUtil {
    public static final AlexaUtil INSTANCE = new AlexaUtil();

    private AlexaUtil() {
    }

    public final void triggerAlexa(PageType pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        EventBus.getDefault().post(new BottomNavBarEvent(BottomNavBarEventType.TRIGGER_ALEXA));
        SearchClickEventUtil.sendClickEvent(ActionType.GO_ALEXA, null, null, null, pageType);
    }

    @JvmStatic
    public static final boolean isAlexaSearchFindBrowseEnabled() {
        return AmazonApplication.getCapabilities().isAlexaSearchFindBrowseEnabled();
    }

    @JvmStatic
    public static final boolean isAlexaSearchOrdinalPlaybackEnabled() {
        return AmazonApplication.getCapabilities().isAlexaSearchOrdinalPlaybackEnabled();
    }

    @JvmStatic
    public static final boolean isAlexaSearchFindBrowseInCarModeEnabled() {
        return AmazonApplication.getCapabilities().isAlexaSearchFindBrowseInCarModeEnabled();
    }

    public final boolean shouldShowAlexaButton() {
        return isAlexaSearchFindBrowseEnabled();
    }
}
