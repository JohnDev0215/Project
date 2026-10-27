package com.amazon.mp3.voice;

import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.voice.ui.AlexaUIStyleProvider;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: AlexaUIStyleProviderImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\bH\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\bH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/voice/AlexaUIStyleProviderImpl;", "Lcom/amazon/music/voice/ui/AlexaUIStyleProvider;", "()V", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getAlexaTextFontStyle", "Lcom/amazon/ui/foundations/styles/FontStyle;", "getHintButtonStyleBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "getNegativeButtonStyleBuilder", "getPositiveButtonStyleBuilder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaUIStyleProviderImpl implements AlexaUIStyleProvider {
    private StyleSheet styleSheet;

    public AlexaUIStyleProviderImpl() {
        StyleSheetProvider.getStyleSheetObservable().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.voice.AlexaUIStyleProviderImpl$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlexaUIStyleProviderImpl.m1811_init_$lambda0(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1811_init_$lambda0(AlexaUIStyleProviderImpl this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.styleSheet = styleSheet;
    }

    @Override // com.amazon.music.voice.ui.AlexaUIStyleProvider
    public FontStyle getAlexaTextFontStyle() {
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getFontStyle(FontStyleKey.PRIMARY);
    }

    @Override // com.amazon.music.voice.ui.AlexaUIStyleProvider
    public BaseButton.StyleBuilder getHintButtonStyleBuilder() {
        ButtonSize buttonSize;
        StyleSheet styleSheet = this.styleSheet;
        ButtonStyle buttonStyle = styleSheet == null ? null : styleSheet.getButtonStyle(ButtonStyleKey.GLASS);
        StyleSheet styleSheet2 = this.styleSheet;
        ButtonSize buttonSizeCopy = (styleSheet2 == null || (buttonSize = styleSheet2.getButtonSize(ButtonSizeKey.MEDIUM)) == null) ? null : buttonSize.copy((4026 & 1) != 0 ? buttonSize.height : null, (4026 & 2) != 0 ? buttonSize.width : null, (4026 & 4) != 0 ? buttonSize.paddingHorizontal : 0, (4026 & 8) != 0 ? buttonSize.paddingVertical : null, (4026 & 16) != 0 ? buttonSize.minWidth : null, (4026 & 32) != 0 ? buttonSize.cornerRadius : null, (4026 & 64) != 0 ? buttonSize.textSize : null, (4026 & 128) != 0 ? buttonSize.textTypeface : null, (4026 & 256) != 0 ? buttonSize.textAllCaps : Boolean.FALSE, (4026 & 512) != 0 ? buttonSize.iconSize : null, (4026 & 1024) != 0 ? buttonSize.iconPaddingLeft : null, (4026 & 2048) != 0 ? buttonSize.iconPaddingRight : null);
        if (buttonStyle == null || buttonSizeCopy == null) {
            return null;
        }
        return new BaseButton.StyleBuilder().withStyle(buttonStyle).withSize(buttonSizeCopy);
    }

    @Override // com.amazon.music.voice.ui.AlexaUIStyleProvider
    public BaseButton.StyleBuilder getNegativeButtonStyleBuilder() {
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.OUTLINE);
    }

    @Override // com.amazon.music.voice.ui.AlexaUIStyleProvider
    public BaseButton.StyleBuilder getPositiveButtonStyleBuilder() {
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID);
    }
}
