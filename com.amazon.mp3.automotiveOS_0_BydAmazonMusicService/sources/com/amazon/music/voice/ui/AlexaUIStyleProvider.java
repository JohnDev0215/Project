package com.amazon.music.voice.ui;

import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;

/* JADX INFO: compiled from: AlexaUIStyleProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0005H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/voice/ui/AlexaUIStyleProvider;", "", "getAlexaTextFontStyle", "Lcom/amazon/ui/foundations/styles/FontStyle;", "getHintButtonStyleBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "getNegativeButtonStyleBuilder", "getPositiveButtonStyleBuilder", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface AlexaUIStyleProvider {
    FontStyle getAlexaTextFontStyle();

    BaseButton.StyleBuilder getHintButtonStyleBuilder();

    BaseButton.StyleBuilder getNegativeButtonStyleBuilder();

    BaseButton.StyleBuilder getPositiveButtonStyleBuilder();
}
