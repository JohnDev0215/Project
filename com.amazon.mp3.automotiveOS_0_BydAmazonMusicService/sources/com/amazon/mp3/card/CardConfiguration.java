package com.amazon.mp3.card;

/* JADX INFO: loaded from: classes3.dex */
public class CardConfiguration {
    private final String headerText;
    private final CardLayout layout;
    private final CardStyle style;
    private final String subHeaderText;

    public CardConfiguration(final String headerText, final String subHeaderText, final CardLayout layout) {
        this(headerText, subHeaderText, layout, null);
    }

    public CardConfiguration(final String headerText, final String subHeaderText, final CardLayout layout, final CardStyle style) {
        this.headerText = headerText;
        this.subHeaderText = subHeaderText;
        this.layout = layout;
        this.style = style;
    }

    public String getHeaderText() {
        return this.headerText;
    }

    public String getSubHeaderText() {
        return this.subHeaderText;
    }

    public CardLayout getLayout() {
        return this.layout;
    }

    public CardStyle getStyle() {
        return this.style;
    }
}
