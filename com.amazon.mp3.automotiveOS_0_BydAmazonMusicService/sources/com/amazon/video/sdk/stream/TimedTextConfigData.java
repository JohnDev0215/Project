package com.amazon.video.sdk.stream;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimedTextConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u001e\u0012\b\b\u0002\u0010#\u001a\u00020\n\u0012\b\b\u0002\u0010%\u001a\u00020\n\u0012\b\b\u0002\u0010(\u001a\u00020'¢\u0006\u0004\b,\u0010-J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\u00020\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001a\u001a\u00020\u00198\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001f\u001a\u00020\u001e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b#\u0010\f\u001a\u0004\b$\u0010\u000eR\u001a\u0010%\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b%\u0010\f\u001a\u0004\b&\u0010\u000eR\u001a\u0010(\u001a\u00020'8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+¨\u0006."}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextConfigData;", "Lcom/amazon/video/sdk/stream/TimedTextConfig;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/stream/TimedTextColor;", "textColor", "Lcom/amazon/video/sdk/stream/TimedTextColor;", "getTextColor", "()Lcom/amazon/video/sdk/stream/TimedTextColor;", "Lcom/amazon/video/sdk/stream/TimedTextOpacity;", "textOpacity", "Lcom/amazon/video/sdk/stream/TimedTextOpacity;", "getTextOpacity", "()Lcom/amazon/video/sdk/stream/TimedTextOpacity;", "Lcom/amazon/video/sdk/stream/TimedTextFontSize;", "fontSize", "Lcom/amazon/video/sdk/stream/TimedTextFontSize;", "getFontSize", "()Lcom/amazon/video/sdk/stream/TimedTextFontSize;", "Lcom/amazon/video/sdk/stream/TimedTextCharacterEdgeType;", "characterEdgeType", "Lcom/amazon/video/sdk/stream/TimedTextCharacterEdgeType;", "getCharacterEdgeType", "()Lcom/amazon/video/sdk/stream/TimedTextCharacterEdgeType;", "Lcom/amazon/video/sdk/stream/TimedTextFontStyle;", "fontStyle", "Lcom/amazon/video/sdk/stream/TimedTextFontStyle;", "getFontStyle", "()Lcom/amazon/video/sdk/stream/TimedTextFontStyle;", "textBackgroundColor", "getTextBackgroundColor", "textWindowColor", "getTextWindowColor", "Lcom/amazon/video/sdk/stream/TimedTextTransitionMode;", "textTransitionMode", "Lcom/amazon/video/sdk/stream/TimedTextTransitionMode;", "getTextTransitionMode", "()Lcom/amazon/video/sdk/stream/TimedTextTransitionMode;", "<init>", "(Lcom/amazon/video/sdk/stream/TimedTextColor;Lcom/amazon/video/sdk/stream/TimedTextOpacity;Lcom/amazon/video/sdk/stream/TimedTextFontSize;Lcom/amazon/video/sdk/stream/TimedTextCharacterEdgeType;Lcom/amazon/video/sdk/stream/TimedTextFontStyle;Lcom/amazon/video/sdk/stream/TimedTextColor;Lcom/amazon/video/sdk/stream/TimedTextColor;Lcom/amazon/video/sdk/stream/TimedTextTransitionMode;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TimedTextConfigData implements TimedTextConfig {
    private final TimedTextCharacterEdgeType characterEdgeType;
    private final TimedTextFontSize fontSize;
    private final TimedTextFontStyle fontStyle;
    private final TimedTextColor textBackgroundColor;
    private final TimedTextColor textColor;
    private final TimedTextOpacity textOpacity;
    private final TimedTextTransitionMode textTransitionMode;
    private final TimedTextColor textWindowColor;

    public TimedTextConfigData() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimedTextConfigData)) {
            return false;
        }
        TimedTextConfigData timedTextConfigData = (TimedTextConfigData) other;
        return getTextColor() == timedTextConfigData.getTextColor() && getTextOpacity() == timedTextConfigData.getTextOpacity() && getFontSize() == timedTextConfigData.getFontSize() && getCharacterEdgeType() == timedTextConfigData.getCharacterEdgeType() && getFontStyle() == timedTextConfigData.getFontStyle() && getTextBackgroundColor() == timedTextConfigData.getTextBackgroundColor() && getTextWindowColor() == timedTextConfigData.getTextWindowColor() && getTextTransitionMode() == timedTextConfigData.getTextTransitionMode();
    }

    public int hashCode() {
        return (((((((((((((getTextColor().hashCode() * 31) + getTextOpacity().hashCode()) * 31) + getFontSize().hashCode()) * 31) + getCharacterEdgeType().hashCode()) * 31) + getFontStyle().hashCode()) * 31) + getTextBackgroundColor().hashCode()) * 31) + getTextWindowColor().hashCode()) * 31) + getTextTransitionMode().hashCode();
    }

    public String toString() {
        return "TimedTextConfigData(textColor=" + getTextColor() + ", textOpacity=" + getTextOpacity() + ", fontSize=" + getFontSize() + ", characterEdgeType=" + getCharacterEdgeType() + ", fontStyle=" + getFontStyle() + ", textBackgroundColor=" + getTextBackgroundColor() + ", textWindowColor=" + getTextWindowColor() + ", textTransitionMode=" + getTextTransitionMode() + ')';
    }

    public TimedTextConfigData(TimedTextColor textColor, TimedTextOpacity textOpacity, TimedTextFontSize fontSize, TimedTextCharacterEdgeType characterEdgeType, TimedTextFontStyle fontStyle, TimedTextColor textBackgroundColor, TimedTextColor textWindowColor, TimedTextTransitionMode textTransitionMode) {
        Intrinsics.checkNotNullParameter(textColor, "textColor");
        Intrinsics.checkNotNullParameter(textOpacity, "textOpacity");
        Intrinsics.checkNotNullParameter(fontSize, "fontSize");
        Intrinsics.checkNotNullParameter(characterEdgeType, "characterEdgeType");
        Intrinsics.checkNotNullParameter(fontStyle, "fontStyle");
        Intrinsics.checkNotNullParameter(textBackgroundColor, "textBackgroundColor");
        Intrinsics.checkNotNullParameter(textWindowColor, "textWindowColor");
        Intrinsics.checkNotNullParameter(textTransitionMode, "textTransitionMode");
        this.textColor = textColor;
        this.textOpacity = textOpacity;
        this.fontSize = fontSize;
        this.characterEdgeType = characterEdgeType;
        this.fontStyle = fontStyle;
        this.textBackgroundColor = textBackgroundColor;
        this.textWindowColor = textWindowColor;
        this.textTransitionMode = textTransitionMode;
    }

    public /* synthetic */ TimedTextConfigData(TimedTextColor timedTextColor, TimedTextOpacity timedTextOpacity, TimedTextFontSize timedTextFontSize, TimedTextCharacterEdgeType timedTextCharacterEdgeType, TimedTextFontStyle timedTextFontStyle, TimedTextColor timedTextColor2, TimedTextColor timedTextColor3, TimedTextTransitionMode timedTextTransitionMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? TimedTextColor.WHITE : timedTextColor, (i & 2) != 0 ? TimedTextOpacity.NORMAL : timedTextOpacity, (i & 4) != 0 ? TimedTextFontSize.MEDIUM : timedTextFontSize, (i & 8) != 0 ? TimedTextCharacterEdgeType.NONE : timedTextCharacterEdgeType, (i & 16) != 0 ? TimedTextFontStyle.DEFAULT : timedTextFontStyle, (i & 32) != 0 ? TimedTextColor.BLACK : timedTextColor2, (i & 64) != 0 ? TimedTextColor.BLACK : timedTextColor3, (i & 128) != 0 ? TimedTextTransitionMode.NONE : timedTextTransitionMode);
    }

    @Override // com.amazon.video.sdk.stream.TimedTextConfig
    public TimedTextColor getTextColor() {
        return this.textColor;
    }

    public TimedTextOpacity getTextOpacity() {
        return this.textOpacity;
    }

    @Override // com.amazon.video.sdk.stream.TimedTextConfig
    public TimedTextFontSize getFontSize() {
        return this.fontSize;
    }

    @Override // com.amazon.video.sdk.stream.TimedTextConfig
    public TimedTextCharacterEdgeType getCharacterEdgeType() {
        return this.characterEdgeType;
    }

    @Override // com.amazon.video.sdk.stream.TimedTextConfig
    public TimedTextFontStyle getFontStyle() {
        return this.fontStyle;
    }

    @Override // com.amazon.video.sdk.stream.TimedTextConfig
    public TimedTextColor getTextBackgroundColor() {
        return this.textBackgroundColor;
    }

    @Override // com.amazon.video.sdk.stream.TimedTextConfig
    public TimedTextColor getTextWindowColor() {
        return this.textWindowColor;
    }

    public TimedTextTransitionMode getTextTransitionMode() {
        return this.textTransitionMode;
    }
}
