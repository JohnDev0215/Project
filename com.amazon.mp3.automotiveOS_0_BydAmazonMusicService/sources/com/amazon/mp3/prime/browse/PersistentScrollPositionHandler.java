package com.amazon.mp3.prime.browse;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes4.dex */
public class PersistentScrollPositionHandler {
    private final Context mContext;
    private final String mFragmentTag;
    private final String mScrollPositionKey;

    public PersistentScrollPositionHandler(final Context context, final String fragmentTag) {
        this.mContext = context;
        this.mFragmentTag = fragmentTag;
        this.mScrollPositionKey = fragmentTag + "_key_scroll_position";
    }

    public int getLastScrollPosition() {
        return this.mContext.getSharedPreferences(this.mFragmentTag, 0).getInt(this.mScrollPositionKey, 0);
    }

    public void setLastScrollPosition(int scrollPosition) {
        SharedPreferences.Editor editorEdit = this.mContext.getSharedPreferences(this.mFragmentTag, 0).edit();
        editorEdit.putInt(this.mScrollPositionKey, scrollPosition);
        editorEdit.apply();
    }
}
