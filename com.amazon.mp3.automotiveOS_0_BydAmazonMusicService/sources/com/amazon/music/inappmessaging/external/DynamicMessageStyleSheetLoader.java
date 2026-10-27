package com.amazon.music.inappmessaging.external;

import com.amazon.music.views.library.styles.StyleSheet;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageStyleSheetLoader {
    private static DynamicMessageStyleSheetLoader sInstance;
    private StyleSheet mStyleSheet;

    public static synchronized DynamicMessageStyleSheetLoader getInstance() {
        if (sInstance == null) {
            sInstance = new DynamicMessageStyleSheetLoader();
        }
        return sInstance;
    }

    public void setStyleSheet(StyleSheet styleSheet) {
        this.mStyleSheet = styleSheet;
    }

    public StyleSheet getStyleSheet() {
        return this.mStyleSheet;
    }
}
