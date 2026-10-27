package com.amazon.mp3.library.item;

import com.amazon.mp3.prime.ContentCatalogStatus;

/* JADX INFO: loaded from: classes3.dex */
public interface CatalogContent {
    ContentCatalogStatus getMaxNonPreviousContentCatalogStatus();

    String getSource();

    boolean isAllOwned();

    boolean isAllPreviouslyCatalog();

    boolean isAnyCatalog();

    boolean isExplicit();
}
