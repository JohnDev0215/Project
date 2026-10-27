package com.amazon.mp3.platform.providers;

import com.amazon.mp3.platform.providers.DispatcherProvider;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;

/* JADX INFO: compiled from: DispatcherProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/platform/providers/DefaultDispatcherProvider;", "Lcom/amazon/mp3/platform/providers/DispatcherProvider;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultDispatcherProvider implements DispatcherProvider {
    public static final DefaultDispatcherProvider INSTANCE = new DefaultDispatcherProvider();

    private DefaultDispatcherProvider() {
    }

    @Override // com.amazon.mp3.platform.providers.DispatcherProvider
    public CoroutineDispatcher io() {
        return DispatcherProvider.DefaultImpls.io(this);
    }
}
