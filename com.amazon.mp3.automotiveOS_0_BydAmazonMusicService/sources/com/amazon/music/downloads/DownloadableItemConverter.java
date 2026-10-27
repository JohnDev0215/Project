package com.amazon.music.downloads;

import com.amazon.music.downloads.Item;
import kotlin.Metadata;

/* JADX INFO: compiled from: DownloadableItemConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/downloads/DownloadableItemConverter;", "T", "Lcom/amazon/music/downloads/Item;", "", "convert", "Lcom/amazon/music/downloads/DownloadableItem;", "item", "(Lcom/amazon/music/downloads/Item;)Lcom/amazon/music/downloads/DownloadableItem;", "DMMDownloadManager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface DownloadableItemConverter<T extends Item> {
    DownloadableItem convert(T item);
}
