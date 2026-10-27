package com.amazon.mp3.util;

import android.widget.AbsListView;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes4.dex */
public class ListViewUtil {
    public static void stopScroll(@NonNull AbsListView listView) {
        if (listView != null) {
            listView.smoothScrollBy(0, 0);
        }
    }
}
