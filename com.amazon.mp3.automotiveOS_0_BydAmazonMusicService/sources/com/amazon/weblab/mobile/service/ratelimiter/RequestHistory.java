package com.amazon.weblab.mobile.service.ratelimiter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class RequestHistory {
    private final LinkedList<RequestEntry> mEntries = new LinkedList<>();
    private final int mMaxSize;

    RequestHistory(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Size is negative");
        }
        this.mMaxSize = i;
    }

    List<RequestEntry> getHistoryEntries() {
        return Collections.unmodifiableList(this.mEntries);
    }

    void addEntry(RequestEntry requestEntry) {
        this.mEntries.addLast(requestEntry);
        if (this.mEntries.size() > this.mMaxSize) {
            this.mEntries.removeFirst();
        }
    }

    int getMaxSize() {
        return this.mMaxSize;
    }
}
