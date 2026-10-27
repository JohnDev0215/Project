package com.amazon.mp3.catalog.fragment.viewmodel;

import kotlin.Metadata;

/* JADX INFO: compiled from: BaseAndroidViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageRequest;", "", "", "toString", "", "hashCode", "other", "", "equals", "index", "I", "getIndex", "()I", "size", "getSize", "count", "getCount", "<init>", "(III)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
final /* data */ class PageRequest {
    private final int count;
    private final int index;
    private final int size;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PageRequest)) {
            return false;
        }
        PageRequest pageRequest = (PageRequest) other;
        return this.index == pageRequest.index && this.size == pageRequest.size && this.count == pageRequest.count;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.index) * 31) + Integer.hashCode(this.size)) * 31) + Integer.hashCode(this.count);
    }

    public String toString() {
        return "PageRequest(index=" + this.index + ", size=" + this.size + ", count=" + this.count + ')';
    }

    public PageRequest(int i, int i2, int i3) {
        this.index = i;
        this.size = i2;
        this.count = i3;
    }

    public final int getCount() {
        return this.count;
    }

    public final int getIndex() {
        return this.index;
    }

    public final int getSize() {
        return this.size;
    }
}
