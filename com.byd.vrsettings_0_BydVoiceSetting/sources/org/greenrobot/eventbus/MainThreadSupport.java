package org.greenrobot.eventbus;

/* JADX INFO: loaded from: classes.dex */
public interface MainThreadSupport {
    Poster createPoster(EventBus eventBus);

    boolean isMainThread();
}
