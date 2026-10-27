package com.amazon.music.play.queue;

import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public interface MusicPlayQueueServiceConfig {
    URL getMusicPlayQueueServiceUrl();

    RequestInterceptor getRequestInterceptor();
}
