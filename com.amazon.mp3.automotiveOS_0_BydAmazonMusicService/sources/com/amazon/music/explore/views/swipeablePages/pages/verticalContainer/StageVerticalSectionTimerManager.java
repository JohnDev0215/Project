package com.amazon.music.explore.views.swipeablePages.pages.verticalContainer;

import androidx.core.app.NotificationCompat;
import com.amazon.music.explore.views.swipeablePages.TouchEventHandler;
import com.amazon.music.explore.views.swipeablePages.pages.StageXrayFullScreenProgressBar;
import com.amazon.music.skyfire.ui.managers.CountDownTimerManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: StageVerticalSctionTimerManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 '2\u00020\u0001:\u0001'B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020!J\u000e\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\fJ\u0006\u0010%\u001a\u00020!J\u0006\u0010&\u001a\u00020!R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006("}, d2 = {"Lcom/amazon/music/explore/views/swipeablePages/pages/verticalContainer/StageVerticalSectionTimerManager;", "", "isFullScreen", "", "(Z)V", "containerDuration", "", "getContainerDuration", "()J", "setContainerDuration", "(J)V", "currentSection", "", "getCurrentSection", "()I", "setCurrentSection", "(I)V", "progressBars", "", "Lcom/amazon/music/explore/views/swipeablePages/pages/StageXrayFullScreenProgressBar;", "getProgressBars", "()Ljava/util/List;", "setProgressBars", "(Ljava/util/List;)V", "timerManager", "Lcom/amazon/music/skyfire/ui/managers/CountDownTimerManager;", "touchEventHandler", "Lcom/amazon/music/explore/views/swipeablePages/TouchEventHandler;", "getTouchEventHandler", "()Lcom/amazon/music/explore/views/swipeablePages/TouchEventHandler;", "setTouchEventHandler", "(Lcom/amazon/music/explore/views/swipeablePages/TouchEventHandler;)V", "initializeTimerManager", "", "restartTimer", "setCurrentSectionProgress", NotificationCompat.CATEGORY_PROGRESS, "startTimer", "stopTimer", "Companion", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StageVerticalSectionTimerManager {
    private static final long CLOCK_TICK_INTERVAL_MILLIS = 10;
    private static final long STAGE_FULL_SCREEN_CONTAINER_DURATION_MS = 8000;
    private static final long STAGE_PREVIEW_CONTAINER_DURATION_MS = 8000;
    private long containerDuration;
    private int currentSection;
    private final boolean isFullScreen;
    private List<StageXrayFullScreenProgressBar> progressBars;
    private CountDownTimerManager timerManager;
    private TouchEventHandler touchEventHandler;

    public StageVerticalSectionTimerManager() {
        this(false, 1, null);
    }

    public StageVerticalSectionTimerManager(boolean z) {
        this.isFullScreen = z;
        this.containerDuration = 8000L;
        if (z) {
            this.containerDuration = 8000L;
        }
    }

    public /* synthetic */ StageVerticalSectionTimerManager(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public final int getCurrentSection() {
        return this.currentSection;
    }

    public final void setCurrentSection(int i) {
        this.currentSection = i;
    }

    public final List<StageXrayFullScreenProgressBar> getProgressBars() {
        return this.progressBars;
    }

    public final void setProgressBars(List<StageXrayFullScreenProgressBar> list) {
        this.progressBars = list;
    }

    public final TouchEventHandler getTouchEventHandler() {
        return this.touchEventHandler;
    }

    public final void setTouchEventHandler(TouchEventHandler touchEventHandler) {
        this.touchEventHandler = touchEventHandler;
    }

    public final long getContainerDuration() {
        return this.containerDuration;
    }

    public final void setContainerDuration(long j) {
        this.containerDuration = j;
    }

    public final void initializeTimerManager() {
        this.timerManager = new CountDownTimerManager(this.containerDuration) { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.StageVerticalSectionTimerManager.initializeTimerManager.1
            @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
            public void onTicked(long msRemaining) {
                List<StageXrayFullScreenProgressBar> progressBars = StageVerticalSectionTimerManager.this.getProgressBars();
                if (progressBars == null) {
                    return;
                }
                StageVerticalSectionTimerManager stageVerticalSectionTimerManager = StageVerticalSectionTimerManager.this;
                if (stageVerticalSectionTimerManager.getCurrentSection() < progressBars.size()) {
                    progressBars.get(stageVerticalSectionTimerManager.getCurrentSection()).setCurrentHeightFromProgress(stageVerticalSectionTimerManager.getContainerDuration() - msRemaining, stageVerticalSectionTimerManager.getContainerDuration());
                }
            }

            @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
            public void onFinished() {
                TouchEventHandler touchEventHandler = StageVerticalSectionTimerManager.this.getTouchEventHandler();
                if (touchEventHandler == null) {
                    return;
                }
                touchEventHandler.onSwipeLeft();
            }
        };
    }

    public final void setCurrentSectionProgress(int progress) {
        List<StageXrayFullScreenProgressBar> list = this.progressBars;
        if (list != null && getCurrentSection() < list.size()) {
            list.get(getCurrentSection()).setCurrentHeightFromProgress(progress, getContainerDuration());
        }
    }

    public final void startTimer() {
        CountDownTimerManager countDownTimerManager = this.timerManager;
        if (countDownTimerManager == null) {
            return;
        }
        countDownTimerManager.start();
    }

    public final void restartTimer() {
        CountDownTimerManager countDownTimerManager = this.timerManager;
        if (countDownTimerManager == null) {
            return;
        }
        countDownTimerManager.restart(this.containerDuration);
    }

    public final void stopTimer() {
        CountDownTimerManager countDownTimerManager = this.timerManager;
        if (countDownTimerManager == null) {
            return;
        }
        countDownTimerManager.stop();
    }
}
