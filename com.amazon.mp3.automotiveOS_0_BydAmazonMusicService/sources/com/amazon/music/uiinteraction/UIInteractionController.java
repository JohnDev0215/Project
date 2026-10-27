package com.amazon.music.uiinteraction;

import com.amazon.music.uiinteraction.subscribers.AppEventSubscriber;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import org.greenrobot.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class UIInteractionController {
    private final Logger LOG;
    private final AtomicBoolean isInitialized;

    private UIInteractionController() {
        this.LOG = LoggerFactory.getLogger(UIInteractionController.class.getSimpleName());
        this.isInitialized = new AtomicBoolean(false);
    }

    public static UIInteractionController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initialize(ObjectMapper objectMapper, InputStream inputStream) {
        if (this.isInitialized.getAndSet(true)) {
            throw new IllegalStateException("Must not invoke initialize() more than once");
        }
        EventBus.getDefault().register(new AppEventSubscriber(objectMapper, inputStream));
        this.LOG.debug("Registered a subscriber with the event bus");
    }

    private static class SingletonHolder {
        static final UIInteractionController INSTANCE = new UIInteractionController();

        private SingletonHolder() {
        }
    }
}
