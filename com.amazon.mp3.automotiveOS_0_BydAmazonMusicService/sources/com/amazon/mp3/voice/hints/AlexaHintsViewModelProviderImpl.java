package com.amazon.mp3.voice.hints;

import android.content.Context;
import com.amazon.mp3.voice.hints.cache.AlexaHintsCacheManagerImpl;
import com.amazon.mp3.voice.hints.guicontext.GUIContextProviderImpl;
import com.amazon.mp3.voice.hints.network.RemoteAlexaHintsProviderImpl;
import com.amazon.music.platform.aom.hints.AlexaHintsRepository;
import com.amazon.music.voice.ui.AlexaHintsViewModel;
import com.amazon.music.voice.ui.AlexaHintsViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaHintsViewModelProviderImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/voice/hints/AlexaHintsViewModelProviderImpl;", "Lcom/amazon/music/voice/ui/AlexaHintsViewModelProvider;", "()V", "getAlexaHintsViewModel", "Lcom/amazon/music/voice/ui/AlexaHintsViewModel;", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaHintsViewModelProviderImpl implements AlexaHintsViewModelProvider {
    @Override // com.amazon.music.voice.ui.AlexaHintsViewModelProvider
    public AlexaHintsViewModel getAlexaHintsViewModel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new AlexaHintsViewModelImpl(new AlexaHintsRepository(new AlexaHintsCacheManagerImpl(context), new RemoteAlexaHintsProviderImpl()), new GUIContextProviderImpl());
    }
}
