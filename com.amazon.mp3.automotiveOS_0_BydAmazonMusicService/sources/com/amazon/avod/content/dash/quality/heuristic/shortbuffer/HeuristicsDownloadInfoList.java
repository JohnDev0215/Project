package com.amazon.avod.content.dash.quality.heuristic.shortbuffer;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class HeuristicsDownloadInfoList implements Iterable<HeuristicsDownloadInfo> {
    private final List<HeuristicsDownloadInfo> mHeuristicsDownloadInfoList = Lists.newArrayList();

    HeuristicsDownloadInfoList() {
    }

    void add(HeuristicsDownloadInfo heuristicsDownloadInfo) {
        this.mHeuristicsDownloadInfoList.add(heuristicsDownloadInfo);
    }

    @Nullable
    HeuristicsDownloadInfo getByDownloadHandle(long j) {
        for (HeuristicsDownloadInfo heuristicsDownloadInfo : this.mHeuristicsDownloadInfoList) {
            if (heuristicsDownloadInfo.getDownloadHandle() == j) {
                return heuristicsDownloadInfo;
            }
        }
        return null;
    }

    boolean contains(int i) {
        Iterator<HeuristicsDownloadInfo> it = this.mHeuristicsDownloadInfoList.iterator();
        while (it.hasNext()) {
            if (it.next().getFragmentIndex() == i) {
                return true;
            }
        }
        return false;
    }

    int size() {
        return this.mHeuristicsDownloadInfoList.size();
    }

    HeuristicsDownloadInfo remove(long j) {
        Iterator<HeuristicsDownloadInfo> it = this.mHeuristicsDownloadInfoList.iterator();
        while (it.hasNext()) {
            HeuristicsDownloadInfo next = it.next();
            if (next.getDownloadHandle() == j) {
                it.remove();
                return next;
            }
        }
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator<HeuristicsDownloadInfo> iterator() {
        return this.mHeuristicsDownloadInfoList.iterator();
    }
}
