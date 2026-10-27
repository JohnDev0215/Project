package org.chromium.content_public.browser;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes3.dex */
public class NavigationEntry {
    private Bitmap mFavicon;
    private final int mIndex;
    private final String mOriginalUrl;
    private final String mTitle;
    private int mTransition;
    private final String mUrl;
    private final String mVirtualUrl;

    public NavigationEntry(int i, String str, String str2, String str3, String str4, Bitmap bitmap, int i2) {
        this.mIndex = i;
        this.mUrl = str;
        this.mVirtualUrl = str2;
        this.mOriginalUrl = str3;
        this.mTitle = str4;
        this.mFavicon = bitmap;
        this.mTransition = i2;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getVirtualUrl() {
        return this.mVirtualUrl;
    }

    public String getOriginalUrl() {
        return this.mOriginalUrl;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public Bitmap getFavicon() {
        return this.mFavicon;
    }

    public void updateFavicon(Bitmap bitmap) {
        this.mFavicon = bitmap;
    }

    public int getTransition() {
        return this.mTransition;
    }
}
