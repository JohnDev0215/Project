package com.byd.auto_emanual.js;

import android.webkit.JavascriptInterface;

/* JADX INFO: loaded from: classes.dex */
public class DiLinkDev {
    public static final String JS_INTERFACE_NAME = "diLinkDev";
    private static final String TAG = "DiLinkDev";
    private String href;
    String language;
    int orientation;
    private int theme;

    @JavascriptInterface
    public int getTheme() {
        return this.theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    @JavascriptInterface
    public int getOrientation() {
        return this.orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @JavascriptInterface
    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @JavascriptInterface
    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
