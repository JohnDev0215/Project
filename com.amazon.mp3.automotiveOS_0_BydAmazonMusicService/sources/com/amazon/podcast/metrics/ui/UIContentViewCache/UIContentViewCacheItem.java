package com.amazon.podcast.metrics.ui.UIContentViewCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes5.dex */
public final class UIContentViewCacheItem {
    private int numItemsAccessible = -1;
    private int firstViewableIndex = -1;
    private int lastViewableIndex = -1;
    private Set<Integer> viewedIndices = new HashSet();
    private Set<Integer> reportedIndices = new HashSet();
    private Set<Integer> unreportedIndices = new HashSet();
    private List<Set<Integer>> batchesOfIndicesToReport = new ArrayList();

    protected void setNumItemsAccessible(final int numItemsAccessible) {
        this.numItemsAccessible = numItemsAccessible;
    }

    protected void setViewedIndices(final List<Integer> viewedIndices) {
        this.viewedIndices.addAll(viewedIndices);
        updateUnreportedIndices();
    }

    protected void setReportedIndices(final List<Integer> reportedIndices) {
        this.reportedIndices.addAll(reportedIndices);
        this.unreportedIndices.removeAll(this.reportedIndices);
    }

    public Set<Integer> getFirstBatchOfUnreportedIndices() {
        if (this.batchesOfIndicesToReport.isEmpty()) {
            this.unreportedIndices.clear();
        } else {
            this.unreportedIndices.addAll(this.batchesOfIndicesToReport.get(0));
            this.unreportedIndices.removeAll(this.reportedIndices);
        }
        return this.unreportedIndices;
    }

    public int getNumItemsAccessible() {
        return this.numItemsAccessible;
    }

    public int getFirstViewableIndex() {
        updateUnreportedIndices();
        if (this.unreportedIndices.isEmpty()) {
            return this.firstViewableIndex;
        }
        int iIntValue = ((Integer) Collections.min(this.unreportedIndices)).intValue();
        this.firstViewableIndex = iIntValue;
        return iIntValue;
    }

    public int getLastViewableIndex() {
        updateUnreportedIndices();
        if (this.unreportedIndices.isEmpty()) {
            return this.lastViewableIndex;
        }
        int iIntValue = ((Integer) Collections.max(this.unreportedIndices)).intValue();
        this.lastViewableIndex = iIntValue;
        return iIntValue;
    }

    public List<Set<Integer>> getBatchesOfIndicesToReport() {
        updateUnreportedIndices();
        updateBatchesOfIndicesToReport();
        return this.batchesOfIndicesToReport;
    }

    private void updateBatchesOfIndicesToReport() {
        Set<Integer> sortedIndicesToReport = getSortedIndicesToReport();
        if (sortedIndicesToReport.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i = Integer.MIN_VALUE;
        Iterator<Integer> it = sortedIndicesToReport.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (i == iIntValue - 1) {
                ((Set) arrayList.get(arrayList.size() - 1)).add(Integer.valueOf(iIntValue));
            } else {
                HashSet hashSet = new HashSet();
                hashSet.add(Integer.valueOf(iIntValue));
                arrayList.add(hashSet);
            }
            i = iIntValue;
        }
        this.batchesOfIndicesToReport.clear();
        this.batchesOfIndicesToReport.addAll(arrayList);
    }

    private Set<Integer> getSortedIndicesToReport() {
        TreeSet treeSet = new TreeSet();
        Iterator<Integer> it = this.viewedIndices.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (!this.reportedIndices.contains(Integer.valueOf(iIntValue))) {
                treeSet.add(Integer.valueOf(iIntValue));
            }
        }
        return treeSet;
    }

    private void updateUnreportedIndices() {
        this.unreportedIndices.addAll(this.viewedIndices);
        this.unreportedIndices.removeAll(this.reportedIndices);
    }
}
