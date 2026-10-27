package com.amazon.mp3.recentlyplayed;

import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.prime.ContentUnavailableReason;

/* JADX INFO: loaded from: classes4.dex */
public interface RecentItem extends CatalogContent {
    void setContentUnavailableReason(ContentUnavailableReason contentUnavailableReason);
}
