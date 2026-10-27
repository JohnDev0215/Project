package com.amazon.music.uicontentview;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public interface ContentViewedListener {
    void onItemRendered(String str, Map<String, String> map);

    void onItemRenderedInSequence(String str, int i, Map<String, String> map);
}
