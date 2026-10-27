package com.byd.carsettings.db.model;

import b.b.h.f.f.a;
import b.b.h.f.f.b;

/* JADX INFO: loaded from: classes2.dex */
@b(name = "SearchKeyword")
public class SearchKeyword extends BaseDBModel implements Comparable<SearchKeyword> {

    @a(isId = true, name = "id")
    private long id;

    @a(name = "keyword")
    private String keyword;

    @a(name = "langTag")
    private String langTag;

    @a(name = "searchCount")
    private int searchCount;

    @a(name = "searchTime")
    private long searchTime;

    public SearchKeyword() {
    }

    public long getId() {
        return this.id;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getLangTag() {
        return this.langTag;
    }

    public int getSearchCount() {
        return this.searchCount;
    }

    public long getSearchTime() {
        return this.searchTime;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    public void setLangTag(String str) {
        this.langTag = str;
    }

    public void setSearchCount(int i) {
        this.searchCount = i;
    }

    public void setSearchTime(long j) {
        this.searchTime = j;
    }

    public String toString() {
        return "SearchKeyword{id=" + this.id + ", keyword='" + this.keyword + "', langTag='" + this.langTag + "', searchCount=" + this.searchCount + ", searchTime=" + this.searchTime + '}';
    }

    public SearchKeyword(String str) {
        this.keyword = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(SearchKeyword searchKeyword) {
        if (this.searchTime > searchKeyword.getSearchTime()) {
            return -1;
        }
        return this.searchTime < searchKeyword.getSearchTime() ? 1 : 0;
    }
}
