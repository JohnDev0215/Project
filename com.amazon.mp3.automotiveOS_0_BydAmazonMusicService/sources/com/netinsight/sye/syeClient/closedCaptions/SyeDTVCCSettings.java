package com.netinsight.sye.syeClient.closedCaptions;

import com.amazon.music.find.utils.PageUriUtils;
import com.netinsight.sye.syeClient.generated.enums.cEA708.AspectRatio;
import com.netinsight.sye.syeClient.generated.enums.cEA708.EdgeStyle;
import com.netinsight.sye.syeClient.generated.enums.cEA708.FontStyle;
import com.netinsight.sye.syeClient.generated.enums.cEA708.Opacity;
import com.netinsight.sye.syeClient.generated.enums.cEA708.PenSize;
import com.netinsight.sye.syeClient.generated.enums.cEA708.SafeTitleArea;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b{\u0010|B\u0013\b\u0016\u0012\b\u0010}\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b{\u0010~J\r\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0004R*\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00068\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\"\u0010\u0012\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0017\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\"\u0010\u001a\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\"\u0010\u001d\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0011\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R*\u0010&\u001a\u00020\u001f2\u0006\u0010\u0007\u001a\u00020\u001f8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R*\u0010.\u001a\u00020'2\u0006\u0010\u0007\u001a\u00020'8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R*\u00102\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00068\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b/\u0010\t\u001a\u0004\b0\u0010\u000b\"\u0004\b1\u0010\rR*\u0010:\u001a\u0002032\u0006\u0010\u0007\u001a\u0002038\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\"\u0010<\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b;\u0010\u0011\u001a\u0004\b<\u0010\u0013\"\u0004\b=\u0010\u0015R*\u0010E\u001a\u00020>2\u0006\u0010\u0007\u001a\u00020>8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR*\u0010I\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00068\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bF\u0010\t\u001a\u0004\bG\u0010\u000b\"\u0004\bH\u0010\rR*\u0010Q\u001a\u00020J2\u0006\u0010\u0007\u001a\u00020J8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bK\u0010L\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR*\u0010U\u001a\u00020J2\u0006\u0010\u0007\u001a\u00020J8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bR\u0010L\u001a\u0004\bS\u0010N\"\u0004\bT\u0010PR\"\u0010W\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bV\u0010\u0011\u001a\u0004\bW\u0010\u0013\"\u0004\bX\u0010\u0015R\"\u0010Z\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bY\u0010\u0011\u001a\u0004\bZ\u0010\u0013\"\u0004\b[\u0010\u0015R\"\u0010]\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\\\u0010\u0011\u001a\u0004\b]\u0010\u0013\"\u0004\b^\u0010\u0015R\"\u0010`\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b_\u0010\u0011\u001a\u0004\b`\u0010\u0013\"\u0004\ba\u0010\u0015R*\u0010e\u001a\u00020J2\u0006\u0010\u0007\u001a\u00020J8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bb\u0010L\u001a\u0004\bc\u0010N\"\u0004\bd\u0010PR\"\u0010g\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bf\u0010\u0011\u001a\u0004\bg\u0010\u0013\"\u0004\bh\u0010\u0015R\"\u0010j\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bi\u0010\u0011\u001a\u0004\bj\u0010\u0013\"\u0004\bk\u0010\u0015R\"\u0010m\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bl\u0010\u0011\u001a\u0004\bm\u0010\u0013\"\u0004\bn\u0010\u0015R*\u0010v\u001a\u00020o2\u0006\u0010\u0007\u001a\u00020o8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bp\u0010q\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR*\u0010z\u001a\u00020J2\u0006\u0010\u0007\u001a\u00020J8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bw\u0010L\u001a\u0004\bx\u0010N\"\u0004\by\u0010P¨\u0006\u007f"}, d2 = {"Lcom/netinsight/sye/syeClient/closedCaptions/SyeDTVCCSettings;", "", "", "toJson", "()Ljava/lang/String;", "toStringValues", "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/Opacity;", "value", "k", "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/Opacity;", "getTextOpacity", "()Lcom/netinsight/sye/syeClient/generated/enums/cEA708/Opacity;", "setTextOpacity", "(Lcom/netinsight/sye/syeClient/generated/enums/cEA708/Opacity;)V", "textOpacity", "", "b", "Z", "isWindowFillColorOverrided", "()Z", "setWindowFillColorOverrided", "(Z)V", "p", "isTextEdgeStyleOverrided", "setTextEdgeStyleOverrided", "h", "isTextBackgroundOpacityOverrided", "setTextBackgroundOpacityOverrided", "x", "isAspectRatioOverrided", "setAspectRatioOverrided", "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/EdgeStyle;", "o", "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/EdgeStyle;", "getTextEdgeStyle", "()Lcom/netinsight/sye/syeClient/generated/enums/cEA708/EdgeStyle;", "setTextEdgeStyle", "(Lcom/netinsight/sye/syeClient/generated/enums/cEA708/EdgeStyle;)V", "textEdgeStyle", "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/AspectRatio;", "w", "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/AspectRatio;", "getAspectRatio", "()Lcom/netinsight/sye/syeClient/generated/enums/cEA708/AspectRatio;", "setAspectRatio", "(Lcom/netinsight/sye/syeClient/generated/enums/cEA708/AspectRatio;)V", "aspectRatio", "g", "getTextBackgroundOpacity", "setTextBackgroundOpacity", "textBackgroundOpacity", "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/SafeTitleArea;", "u", "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/SafeTitleArea;", "getSafeTitleArea", "()Lcom/netinsight/sye/syeClient/generated/enums/cEA708/SafeTitleArea;", "setSafeTitleArea", "(Lcom/netinsight/sye/syeClient/generated/enums/cEA708/SafeTitleArea;)V", "safeTitleArea", "f", "isTextBackgroundColorOverrided", "setTextBackgroundColorOverrided", "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/FontStyle;", PageUriUtils.SEARCH_TERM_QUERY_PARAM, "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/FontStyle;", "getFontStyle", "()Lcom/netinsight/sye/syeClient/generated/enums/cEA708/FontStyle;", "setFontStyle", "(Lcom/netinsight/sye/syeClient/generated/enums/cEA708/FontStyle;)V", "fontStyle", "c", "getWindowOpacity", "setWindowOpacity", "windowOpacity", "", "e", "I", "getTextBackgroundColor", "()I", "setTextBackgroundColor", "(I)V", "textBackgroundColor", "i", "getTextColor", "setTextColor", "textColor", "j", "isTextColorOverrided", "setTextColorOverrided", "l", "isTextOpacityOverrided", "setTextOpacityOverrided", "r", "isFontStyleOverrided", "setFontStyleOverrided", "t", "isPenSizeOverrided", "setPenSizeOverrided", "m", "getTextEdgeColor", "setTextEdgeColor", "textEdgeColor", "d", "isWindowOpacityOverrided", "setWindowOpacityOverrided", "v", "isSafeTitleAreaOverrided", "setSafeTitleAreaOverrided", "n", "isTextEdgeColorOverrided", "setTextEdgeColorOverrided", "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/PenSize;", "s", "Lcom/netinsight/sye/syeClient/generated/enums/cEA708/PenSize;", "getPenSize", "()Lcom/netinsight/sye/syeClient/generated/enums/cEA708/PenSize;", "setPenSize", "(Lcom/netinsight/sye/syeClient/generated/enums/cEA708/PenSize;)V", "penSize", "a", "getWindowFillColor", "setWindowFillColor", "windowFillColor", "<init>", "()V", "copyThis", "(Lcom/netinsight/sye/syeClient/closedCaptions/SyeDTVCCSettings;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeDTVCCSettings {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public int windowFillColor;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public boolean isWindowFillColorOverrided;

    /* JADX INFO: renamed from: c, reason: from kotlin metadata */
    public Opacity windowOpacity;

    /* JADX INFO: renamed from: d, reason: from kotlin metadata */
    public boolean isWindowOpacityOverrided;

    /* JADX INFO: renamed from: e, reason: from kotlin metadata */
    public int textBackgroundColor;

    /* JADX INFO: renamed from: f, reason: from kotlin metadata */
    public boolean isTextBackgroundColorOverrided;

    /* JADX INFO: renamed from: g, reason: from kotlin metadata */
    public Opacity textBackgroundOpacity;

    /* JADX INFO: renamed from: h, reason: from kotlin metadata */
    public boolean isTextBackgroundOpacityOverrided;

    /* JADX INFO: renamed from: i, reason: from kotlin metadata */
    public int textColor;

    /* JADX INFO: renamed from: j, reason: from kotlin metadata */
    public boolean isTextColorOverrided;

    /* JADX INFO: renamed from: k, reason: from kotlin metadata */
    public Opacity textOpacity;

    /* JADX INFO: renamed from: l, reason: from kotlin metadata */
    public boolean isTextOpacityOverrided;

    /* JADX INFO: renamed from: m, reason: from kotlin metadata */
    public int textEdgeColor;

    /* JADX INFO: renamed from: n, reason: from kotlin metadata */
    public boolean isTextEdgeColorOverrided;

    /* JADX INFO: renamed from: o, reason: from kotlin metadata */
    public EdgeStyle textEdgeStyle;

    /* JADX INFO: renamed from: p, reason: from kotlin metadata */
    public boolean isTextEdgeStyleOverrided;

    /* JADX INFO: renamed from: q, reason: from kotlin metadata */
    public FontStyle fontStyle;

    /* JADX INFO: renamed from: r, reason: from kotlin metadata */
    public boolean isFontStyleOverrided;

    /* JADX INFO: renamed from: s, reason: from kotlin metadata */
    public PenSize penSize;

    /* JADX INFO: renamed from: t, reason: from kotlin metadata */
    public boolean isPenSizeOverrided;

    /* JADX INFO: renamed from: u, reason: from kotlin metadata */
    public SafeTitleArea safeTitleArea;

    /* JADX INFO: renamed from: v, reason: from kotlin metadata */
    public boolean isSafeTitleAreaOverrided;

    /* JADX INFO: renamed from: w, reason: from kotlin metadata */
    public AspectRatio aspectRatio;

    /* JADX INFO: renamed from: x, reason: from kotlin metadata */
    public boolean isAspectRatioOverrided;

    public SyeDTVCCSettings() {
        Opacity opacity = Opacity.Transparent;
        this.windowOpacity = opacity;
        this.textBackgroundOpacity = opacity;
        this.textOpacity = Opacity.Solid;
        this.textEdgeStyle = EdgeStyle.None;
        this.fontStyle = FontStyle.Default;
        this.penSize = PenSize.Standard;
        this.safeTitleArea = SafeTitleArea.Percent_80;
        this.aspectRatio = AspectRatio.Aspect_4_3;
    }

    public SyeDTVCCSettings(SyeDTVCCSettings syeDTVCCSettings) {
        Opacity opacity = Opacity.Transparent;
        this.windowOpacity = opacity;
        this.textBackgroundOpacity = opacity;
        this.textOpacity = Opacity.Solid;
        this.textEdgeStyle = EdgeStyle.None;
        this.fontStyle = FontStyle.Default;
        this.penSize = PenSize.Standard;
        this.safeTitleArea = SafeTitleArea.Percent_80;
        this.aspectRatio = AspectRatio.Aspect_4_3;
        if (syeDTVCCSettings != null) {
            setWindowFillColor(syeDTVCCSettings.windowFillColor);
            this.isWindowFillColorOverrided = syeDTVCCSettings.isWindowFillColorOverrided;
            setWindowOpacity(syeDTVCCSettings.windowOpacity);
            this.isWindowOpacityOverrided = syeDTVCCSettings.isWindowOpacityOverrided;
            setTextBackgroundColor(syeDTVCCSettings.textBackgroundColor);
            this.isTextBackgroundColorOverrided = syeDTVCCSettings.isTextBackgroundColorOverrided;
            setTextBackgroundOpacity(syeDTVCCSettings.textBackgroundOpacity);
            this.isTextBackgroundOpacityOverrided = syeDTVCCSettings.isTextBackgroundOpacityOverrided;
            setTextColor(syeDTVCCSettings.textColor);
            this.isTextColorOverrided = syeDTVCCSettings.isTextColorOverrided;
            setTextOpacity(syeDTVCCSettings.textOpacity);
            this.isTextOpacityOverrided = syeDTVCCSettings.isTextOpacityOverrided;
            setTextEdgeColor(syeDTVCCSettings.textEdgeColor);
            this.isTextEdgeColorOverrided = syeDTVCCSettings.isTextEdgeColorOverrided;
            setTextEdgeStyle(syeDTVCCSettings.textEdgeStyle);
            this.isTextEdgeStyleOverrided = syeDTVCCSettings.isTextEdgeStyleOverrided;
            setFontStyle(syeDTVCCSettings.fontStyle);
            this.isFontStyleOverrided = syeDTVCCSettings.isFontStyleOverrided;
            setPenSize(syeDTVCCSettings.penSize);
            this.isPenSizeOverrided = syeDTVCCSettings.isPenSizeOverrided;
            setSafeTitleArea(syeDTVCCSettings.safeTitleArea);
            this.isSafeTitleAreaOverrided = syeDTVCCSettings.isSafeTitleAreaOverrided;
            setAspectRatio(syeDTVCCSettings.aspectRatio);
            this.isAspectRatioOverrided = syeDTVCCSettings.isAspectRatioOverrided;
        }
    }

    public final AspectRatio getAspectRatio() {
        return this.aspectRatio;
    }

    public final FontStyle getFontStyle() {
        return this.fontStyle;
    }

    public final PenSize getPenSize() {
        return this.penSize;
    }

    public final SafeTitleArea getSafeTitleArea() {
        return this.safeTitleArea;
    }

    public final int getTextBackgroundColor() {
        return this.textBackgroundColor;
    }

    public final Opacity getTextBackgroundOpacity() {
        return this.textBackgroundOpacity;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final int getTextEdgeColor() {
        return this.textEdgeColor;
    }

    public final EdgeStyle getTextEdgeStyle() {
        return this.textEdgeStyle;
    }

    public final Opacity getTextOpacity() {
        return this.textOpacity;
    }

    public final int getWindowFillColor() {
        return this.windowFillColor;
    }

    public final Opacity getWindowOpacity() {
        return this.windowOpacity;
    }

    /* JADX INFO: renamed from: isAspectRatioOverrided, reason: from getter */
    public final boolean getIsAspectRatioOverrided() {
        return this.isAspectRatioOverrided;
    }

    /* JADX INFO: renamed from: isFontStyleOverrided, reason: from getter */
    public final boolean getIsFontStyleOverrided() {
        return this.isFontStyleOverrided;
    }

    /* JADX INFO: renamed from: isPenSizeOverrided, reason: from getter */
    public final boolean getIsPenSizeOverrided() {
        return this.isPenSizeOverrided;
    }

    /* JADX INFO: renamed from: isSafeTitleAreaOverrided, reason: from getter */
    public final boolean getIsSafeTitleAreaOverrided() {
        return this.isSafeTitleAreaOverrided;
    }

    /* JADX INFO: renamed from: isTextBackgroundColorOverrided, reason: from getter */
    public final boolean getIsTextBackgroundColorOverrided() {
        return this.isTextBackgroundColorOverrided;
    }

    /* JADX INFO: renamed from: isTextBackgroundOpacityOverrided, reason: from getter */
    public final boolean getIsTextBackgroundOpacityOverrided() {
        return this.isTextBackgroundOpacityOverrided;
    }

    /* JADX INFO: renamed from: isTextColorOverrided, reason: from getter */
    public final boolean getIsTextColorOverrided() {
        return this.isTextColorOverrided;
    }

    /* JADX INFO: renamed from: isTextEdgeColorOverrided, reason: from getter */
    public final boolean getIsTextEdgeColorOverrided() {
        return this.isTextEdgeColorOverrided;
    }

    /* JADX INFO: renamed from: isTextEdgeStyleOverrided, reason: from getter */
    public final boolean getIsTextEdgeStyleOverrided() {
        return this.isTextEdgeStyleOverrided;
    }

    /* JADX INFO: renamed from: isTextOpacityOverrided, reason: from getter */
    public final boolean getIsTextOpacityOverrided() {
        return this.isTextOpacityOverrided;
    }

    /* JADX INFO: renamed from: isWindowFillColorOverrided, reason: from getter */
    public final boolean getIsWindowFillColorOverrided() {
        return this.isWindowFillColorOverrided;
    }

    /* JADX INFO: renamed from: isWindowOpacityOverrided, reason: from getter */
    public final boolean getIsWindowOpacityOverrided() {
        return this.isWindowOpacityOverrided;
    }

    public final void setAspectRatio(AspectRatio aspectRatio) {
        this.aspectRatio = aspectRatio;
        this.isAspectRatioOverrided = true;
    }

    public final void setAspectRatioOverrided(boolean z) {
        this.isAspectRatioOverrided = z;
    }

    public final void setFontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
        this.isFontStyleOverrided = true;
    }

    public final void setFontStyleOverrided(boolean z) {
        this.isFontStyleOverrided = z;
    }

    public final void setPenSize(PenSize penSize) {
        this.penSize = penSize;
        this.isPenSizeOverrided = true;
    }

    public final void setPenSizeOverrided(boolean z) {
        this.isPenSizeOverrided = z;
    }

    public final void setSafeTitleArea(SafeTitleArea safeTitleArea) {
        this.safeTitleArea = safeTitleArea;
        this.isSafeTitleAreaOverrided = true;
    }

    public final void setSafeTitleAreaOverrided(boolean z) {
        this.isSafeTitleAreaOverrided = z;
    }

    public final void setTextBackgroundColor(int i) {
        this.textBackgroundColor = i;
        this.isTextBackgroundColorOverrided = true;
    }

    public final void setTextBackgroundColorOverrided(boolean z) {
        this.isTextBackgroundColorOverrided = z;
    }

    public final void setTextBackgroundOpacity(Opacity opacity) {
        this.textBackgroundOpacity = opacity;
        this.isTextBackgroundOpacityOverrided = false;
    }

    public final void setTextBackgroundOpacityOverrided(boolean z) {
        this.isTextBackgroundOpacityOverrided = z;
    }

    public final void setTextColor(int i) {
        this.textColor = i;
        this.isTextColorOverrided = false;
    }

    public final void setTextColorOverrided(boolean z) {
        this.isTextColorOverrided = z;
    }

    public final void setTextEdgeColor(int i) {
        this.textEdgeColor = i;
        this.isTextEdgeColorOverrided = true;
    }

    public final void setTextEdgeColorOverrided(boolean z) {
        this.isTextEdgeColorOverrided = z;
    }

    public final void setTextEdgeStyle(EdgeStyle edgeStyle) {
        this.textEdgeStyle = edgeStyle;
        this.isTextEdgeStyleOverrided = true;
    }

    public final void setTextEdgeStyleOverrided(boolean z) {
        this.isTextEdgeStyleOverrided = z;
    }

    public final void setTextOpacity(Opacity opacity) {
        this.textOpacity = opacity;
        this.isTextOpacityOverrided = false;
    }

    public final void setTextOpacityOverrided(boolean z) {
        this.isTextOpacityOverrided = z;
    }

    public final void setWindowFillColor(int i) {
        this.windowFillColor = i;
        this.isWindowFillColorOverrided = true;
    }

    public final void setWindowFillColorOverrided(boolean z) {
        this.isWindowFillColorOverrided = z;
    }

    public final void setWindowOpacity(Opacity opacity) {
        this.windowOpacity = opacity;
        this.isWindowOpacityOverrided = true;
    }

    public final void setWindowOpacityOverrided(boolean z) {
        this.isWindowOpacityOverrided = z;
    }

    public final String toJson() {
        JSONObject jSONObject = new JSONObject();
        if (this.isWindowFillColorOverrided) {
            jSONObject.put("windowFillColor", this.windowFillColor);
        }
        if (this.isWindowOpacityOverrided) {
            jSONObject.put("windowOpacity", this.windowOpacity.getValue());
        }
        if (this.isTextBackgroundColorOverrided) {
            jSONObject.put("textBackgroundColor", this.textBackgroundColor);
        }
        if (this.isTextBackgroundOpacityOverrided) {
            jSONObject.put("textBackgroundOpacity", this.textBackgroundOpacity.getValue());
        }
        if (this.isTextColorOverrided) {
            jSONObject.put("textColor", this.textColor);
        }
        if (this.isTextOpacityOverrided) {
            jSONObject.put("textOpacity", this.textOpacity.getValue());
        }
        if (this.isTextEdgeColorOverrided) {
            jSONObject.put("textEdgeColor", this.textEdgeColor);
        }
        if (this.isTextEdgeStyleOverrided) {
            jSONObject.put("textEdgeStyle", this.textEdgeStyle.getValue());
        }
        if (this.isFontStyleOverrided) {
            jSONObject.put("fontStyle", this.fontStyle.getValue());
        }
        if (this.isPenSizeOverrided) {
            jSONObject.put("penSize", this.penSize.getValue());
        }
        if (this.isSafeTitleAreaOverrided) {
            jSONObject.put("safeTitleArea", this.safeTitleArea.getValue());
        }
        if (this.isAspectRatioOverrided) {
            jSONObject.put("aspectRatio", this.aspectRatio.getValue());
        }
        String string = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "JSONObject().apply {\n   …   }\n        }.toString()");
        return string;
    }

    public final String toStringValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.windowFillColor);
        sb.append(":");
        sb.append(this.isWindowFillColorOverrided ? "1" : 0);
        sb.append(",");
        sb.append(this.windowOpacity.getValue());
        sb.append(":");
        sb.append(this.isWindowOpacityOverrided ? "1" : 0);
        sb.append(",");
        sb.append(this.textBackgroundColor);
        sb.append(":");
        sb.append(this.isTextBackgroundColorOverrided ? "1" : 0);
        sb.append(",");
        sb.append(this.textBackgroundOpacity.getValue());
        sb.append(":");
        sb.append(this.isTextBackgroundOpacityOverrided ? "1" : 0);
        sb.append(",");
        sb.append(this.textColor);
        sb.append(":");
        sb.append(this.isTextColorOverrided ? "1" : 0);
        sb.append(",");
        sb.append(this.textOpacity.getValue());
        sb.append(":");
        sb.append(this.isTextOpacityOverrided ? "1" : 0);
        sb.append(",");
        sb.append(this.textEdgeColor);
        sb.append(":");
        sb.append(this.isTextEdgeColorOverrided ? "1" : 0);
        sb.append(",");
        sb.append(this.textEdgeStyle.getValue());
        sb.append(":");
        sb.append(this.isTextEdgeStyleOverrided ? "1" : 0);
        sb.append(",");
        sb.append(this.fontStyle.getValue());
        sb.append(":");
        sb.append(this.isFontStyleOverrided ? "1" : 0);
        sb.append(",");
        sb.append(this.penSize.getValue());
        sb.append(":");
        sb.append(this.isPenSizeOverrided ? "1" : 0);
        sb.append(",");
        sb.append(this.safeTitleArea.getValue());
        sb.append(":");
        sb.append(this.isSafeTitleAreaOverrided ? "1" : 0);
        sb.append(",");
        sb.append(this.aspectRatio.getValue());
        sb.append(":");
        sb.append(this.isAspectRatioOverrided ? "1" : 0);
        sb.append(",");
        String string = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "StringBuilder().apply {\n…\",\")\n        }.toString()");
        return string;
    }
}
