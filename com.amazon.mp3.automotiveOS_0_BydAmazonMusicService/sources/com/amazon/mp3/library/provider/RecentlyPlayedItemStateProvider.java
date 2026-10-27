package com.amazon.mp3.library.provider;

import android.net.Uri;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;

/* JADX INFO: loaded from: classes3.dex */
public interface RecentlyPlayedItemStateProvider extends StateProvider<RecentlyPlayedItem> {
    void setDownloadState(final Uri contentUri, final int downloadState);
}
