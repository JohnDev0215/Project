package com.amazon.mp3.dmsfcore.providers;

import androidx.lifecycle.MutableLiveData;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;

/* JADX INFO: compiled from: DMSFStyleSheetProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFStyleSheetProvider;", "Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "()V", "styleSheet", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Landroidx/lifecycle/MutableLiveData;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DMSFStyleSheetProvider implements StyleSheetProvider {
    @Override // com.amazon.music.skyfire.ui.providers.StyleSheetProvider
    public MutableLiveData<StyleSheet> getStyleSheet() {
        return com.amazon.mp3.styles.StyleSheetProvider.getStyleSheet();
    }
}
