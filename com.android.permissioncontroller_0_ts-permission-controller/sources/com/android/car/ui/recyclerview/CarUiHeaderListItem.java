package com.android.car.ui.recyclerview;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiHeaderListItem extends CarUiListItem {
    private final CharSequence mBody;
    private final CharSequence mTitle;

    public CarUiHeaderListItem(CharSequence title) {
        this(title, "");
    }

    public CarUiHeaderListItem(CharSequence title, CharSequence body) {
        this.mTitle = title;
        this.mBody = body;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getBody() {
        return this.mBody;
    }
}
