package com.amazon.ui.foundations.views;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.TextView;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.LinkStyle;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazon.ui.foundations.utils.LinkTouchMovementMethod;
import com.amazon.ui.foundations.utils.TouchableSpan;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: Link.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001$B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0019\u001a\u00020\u0011H\u0002J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!H\u0002J\u001e\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R6\u0010\r\u001a*\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000ej\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010`\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/ui/foundations/views/Link;", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "fontStyle", "Lcom/amazon/ui/foundations/styles/FontStyle;", "linkStyle", "Lcom/amazon/ui/foundations/styles/LinkStyle;", "linkTextMap", "Ljava/util/LinkedHashMap;", "Landroid/text/SpannableStringBuilder;", "Lkotlin/Function0;", "", "Lkotlin/collections/LinkedHashMap;", "spannableStringBuilder", "addLinkText", "text", "", "linkClick", "addText", "create", "setEnabled", "enabled", "", "setFontStyle", "setLinkStyle", "setSpan", "touchableSpan", "Lcom/amazon/ui/foundations/utils/TouchableSpan;", "setTouchableSpan", "spannableString", "LinkStringBuilder", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Link extends TextView {
    private FontStyle fontStyle;
    private LinkStyle linkStyle;
    private LinkedHashMap<SpannableStringBuilder, Function0<Unit>> linkTextMap;
    private SpannableStringBuilder spannableStringBuilder;

    public /* synthetic */ Link(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public Link(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.spannableStringBuilder = new SpannableStringBuilder();
        this.linkTextMap = new LinkedHashMap<>();
        setMovementMethod(LinkTouchMovementMethod.INSTANCE.getInstance());
        setHighlightColor(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
        setTextSize(0, fontStyle.getFontSize().getTextSize());
        setTextColor(fontStyle.getColor());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLinkStyle(LinkStyle linkStyle) {
        this.linkStyle = linkStyle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addText(String text) {
        this.spannableStringBuilder.append((CharSequence) text);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addLinkText(String text, Function0<Unit> linkClick) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        setTouchableSpan(spannableStringBuilder, linkClick);
        this.spannableStringBuilder.append((CharSequence) spannableStringBuilder);
        this.linkTextMap.put(spannableStringBuilder, linkClick);
    }

    private final void setTouchableSpan(SpannableStringBuilder spannableString, Function0<Unit> linkClick) {
        LinkStyle linkStyle = this.linkStyle;
        StatefulStyle statefulStyle = linkStyle == null ? null : linkStyle.getStatefulStyle();
        if (isEnabled()) {
            Integer color = statefulStyle == null ? null : statefulStyle.getColor();
            Integer pressedColor = statefulStyle == null ? null : statefulStyle.getPressedColor();
            LinkStyle linkStyle2 = this.linkStyle;
            Boolean showUnderlineWhenClick = linkStyle2 != null ? linkStyle2.getShowUnderlineWhenClick() : null;
            if (color != null) {
                setSpan(spannableString, new TouchableSpan(color.intValue(), pressedColor, showUnderlineWhenClick, linkClick));
                return;
            }
            return;
        }
        Integer disabledColor = statefulStyle != null ? statefulStyle.getDisabledColor() : null;
        if (disabledColor != null) {
            setSpan(spannableString, new TouchableSpan(disabledColor.intValue(), null, null, null, 14, null));
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setEnabled(enabled);
        for (Map.Entry<SpannableStringBuilder, Function0<Unit>> entry : this.linkTextMap.entrySet()) {
            setTouchableSpan(entry.getKey(), entry.getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void create() {
        setText(this.spannableStringBuilder, TextView.BufferType.SPANNABLE);
    }

    private final void setSpan(SpannableStringBuilder spannableStringBuilder, TouchableSpan touchableSpan) {
        spannableStringBuilder.setSpan(touchableSpan, 0, spannableStringBuilder.length(), 33);
    }

    /* JADX INFO: compiled from: Link.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\tJ\u001c\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J$\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ,\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0004J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R:\u0010\u0007\u001a.\u0012\u0004\u0012\u00020\t\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\bj\u0016\u0012\u0004\u0012\u00020\t\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n`\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/ui/foundations/views/Link$LinkStringBuilder;", "", "()V", "builderFontStyle", "Lcom/amazon/ui/foundations/styles/FontStyle;", "builderLinkStyle", "Lcom/amazon/ui/foundations/styles/LinkStyle;", "builderTextMap", "Ljava/util/LinkedHashMap;", "", "Lkotlin/Function0;", "", "Lkotlin/collections/LinkedHashMap;", "append", "text", "appendLink", "linkText", "onClick", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/ui/foundations/views/Link;", "context", "Landroid/content/Context;", "setLinkSubstring", "linkSubstring", "setLinkSubstringWithIndex", "linkStart", "", "linkEnd", "withFontStyle", "fontStyle", "withLinkStyle", "linkStyle", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class LinkStringBuilder {
        private FontStyle builderFontStyle;
        private LinkStyle builderLinkStyle;
        private LinkedHashMap<String, Function0<Unit>> builderTextMap = new LinkedHashMap<>();

        public final LinkStringBuilder withFontStyle(FontStyle fontStyle) {
            Intrinsics.checkNotNullParameter(fontStyle, "fontStyle");
            this.builderFontStyle = fontStyle;
            return this;
        }

        public final LinkStringBuilder withLinkStyle(LinkStyle linkStyle) {
            Intrinsics.checkNotNullParameter(linkStyle, "linkStyle");
            this.builderLinkStyle = linkStyle;
            return this;
        }

        public final LinkStringBuilder append(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.builderTextMap.put(text, null);
            return this;
        }

        public final LinkStringBuilder appendLink(String linkText, Function0<Unit> onClick) {
            Intrinsics.checkNotNullParameter(linkText, "linkText");
            Intrinsics.checkNotNullParameter(onClick, "onClick");
            this.builderTextMap.put(linkText, onClick);
            return this;
        }

        public final LinkStringBuilder setLinkSubstring(String text, String linkSubstring, Function0<Unit> onClick) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(linkSubstring, "linkSubstring");
            Intrinsics.checkNotNullParameter(onClick, "onClick");
            int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) text, linkSubstring, 0, false, 6, (Object) null);
            return iIndexOf$default == -1 ? this : setLinkSubstringWithIndex(text, iIndexOf$default, linkSubstring.length() + iIndexOf$default, onClick);
        }

        public final LinkStringBuilder setLinkSubstringWithIndex(String text, int linkStart, int linkEnd, Function0<Unit> onClick) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(onClick, "onClick");
            if (linkStart < 0 || linkEnd > text.length()) {
                throw new IllegalArgumentException("Should use a valid link start and end index");
            }
            String strSubstring = text.substring(0, linkStart);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            append(strSubstring);
            String strSubstring2 = text.substring(linkStart, linkEnd);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
            appendLink(strSubstring2, onClick);
            String strSubstring3 = text.substring(linkEnd, text.length());
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
            append(strSubstring3);
            return this;
        }

        public final Link build(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Link link = new Link(context, null, 0, 6, null);
            FontStyle fontStyle = this.builderFontStyle;
            if (fontStyle != null) {
                link.setFontStyle(fontStyle);
            }
            LinkStyle linkStyle = this.builderLinkStyle;
            if (linkStyle != null) {
                link.setLinkStyle(linkStyle);
            }
            for (Map.Entry<String, Function0<Unit>> entry : this.builderTextMap.entrySet()) {
                String key = entry.getKey();
                Function0<Unit> value = entry.getValue();
                if (value == null) {
                    link.addText(key);
                } else {
                    link.addLinkText(key, value);
                }
            }
            link.create();
            return link;
        }
    }
}
