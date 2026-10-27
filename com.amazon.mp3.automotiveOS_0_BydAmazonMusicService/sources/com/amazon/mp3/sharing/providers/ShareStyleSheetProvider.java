package com.amazon.mp3.sharing.providers;

import com.amazon.music.share.menu.providers.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShareStyleSheetProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/sharing/providers/ShareStyleSheetProvider;", "Lcom/amazon/music/share/menu/providers/StyleSheetProvider;", "()V", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ShareStyleSheetProvider implements StyleSheetProvider {
    @Override // com.amazon.music.share.menu.providers.StyleSheetProvider
    public StyleSheet getStyleSheet() {
        StyleSheet value = com.amazon.mp3.styles.StyleSheetProvider.getStyleSheet().getValue();
        Intrinsics.checkNotNull(value);
        Intrinsics.checkNotNullExpressionValue(value, "getStyleSheet().value!!");
        return value;
    }
}
