package org.chromium.components.content_creation.notes.models;

/* JADX INFO: loaded from: classes2.dex */
public final class NoteTemplate {
    public final int a;
    public final String b;
    public final Background c;
    public final TextStyle d;
    public final FooterStyle e;
    public final Background f;

    public NoteTemplate(int i, String str, Background background, Background background2, TextStyle textStyle, FooterStyle footerStyle) {
        this.a = i;
        this.b = str;
        this.c = background;
        this.f = background2;
        this.d = textStyle;
        this.e = footerStyle;
    }
}
