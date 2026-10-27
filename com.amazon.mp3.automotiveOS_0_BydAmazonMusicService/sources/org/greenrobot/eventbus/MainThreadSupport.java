package org.greenrobot.eventbus;

/* JADX INFO: loaded from: classes6.dex */
public interface MainThreadSupport {
    Poster createPoster(EventBus eventBus);

    boolean isMainThread();
}
