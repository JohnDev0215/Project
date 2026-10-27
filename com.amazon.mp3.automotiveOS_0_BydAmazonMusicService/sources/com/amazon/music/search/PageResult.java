package com.amazon.music.search;

import com.amazon.music.search.ResultItem;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class PageResult<TResultItem extends ResultItem> {
    private final List<TResultItem> mPage;
    private final String mPageToken;
    private final long mTotalHits;

    public PageResult(List<TResultItem> list, long j) {
        this(list, j, null);
    }

    public PageResult(List<TResultItem> list, long j, String str) {
        this.mPage = list;
        this.mTotalHits = j;
        this.mPageToken = str;
    }

    public List<TResultItem> getPage() {
        return this.mPage;
    }

    public String getPageToken() {
        return this.mPageToken;
    }
}
