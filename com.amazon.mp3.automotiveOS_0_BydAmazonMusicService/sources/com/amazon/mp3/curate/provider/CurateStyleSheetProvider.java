package com.amazon.mp3.curate.provider;

import androidx.lifecycle.MutableLiveData;
import com.amazon.music.curate.provider.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import rx.Observable;

/* JADX INFO: compiled from: CurateStyleSheetProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateStyleSheetProvider;", "Lcom/amazon/music/curate/provider/StyleSheetProvider;", "()V", "getStyleSheet", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheetObservable", "Lrx/Observable;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateStyleSheetProvider implements StyleSheetProvider {
    @Override // com.amazon.music.curate.provider.StyleSheetProvider
    public MutableLiveData<StyleSheet> getStyleSheet() {
        return com.amazon.mp3.styles.StyleSheetProvider.getStyleSheet();
    }

    @Override // com.amazon.music.curate.provider.StyleSheetProvider
    public Observable<StyleSheet> getStyleSheetObservable() {
        return com.amazon.mp3.styles.StyleSheetProvider.getStyleSheetObservable();
    }
}
