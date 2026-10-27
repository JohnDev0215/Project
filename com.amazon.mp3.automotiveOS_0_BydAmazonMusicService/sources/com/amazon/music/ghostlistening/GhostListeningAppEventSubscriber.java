package com.amazon.music.ghostlistening;

import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import org.greenrobot.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class GhostListeningAppEventSubscriber {
    private final Logger logger = LoggerFactory.getLogger(GhostListeningAppEventSubscriber.class.getSimpleName());
    private final InactivityTimer timer;

    public GhostListeningAppEventSubscriber(InactivityTimer inactivityTimer) {
        this.timer = inactivityTimer;
    }

    @Subscribe
    public void onEvent(UserInteractionAppEvent userInteractionAppEvent) {
        this.logger.trace("UI app event received by Ghost Listening component.");
        this.timer.resetTimerToDefaultState();
    }

    @Subscribe
    public void onEvent(NavigationAppEvent navigationAppEvent) {
        this.logger.trace("Navigation app event received by Ghost Listening component.");
        this.timer.resetTimerToDefaultState();
    }
}
