package com.amazon.music.curate.provider;

import androidx.lifecycle.MutableLiveData;
import com.amazon.music.views.library.styles.StyleSheet;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public interface StyleSheetProvider {
    @Deprecated
    MutableLiveData<StyleSheet> getStyleSheet();

    Observable<StyleSheet> getStyleSheetObservable();
}
