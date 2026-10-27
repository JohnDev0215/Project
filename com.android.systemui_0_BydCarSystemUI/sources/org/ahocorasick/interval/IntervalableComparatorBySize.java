package org.ahocorasick.interval;

import java.util.Comparator;

/* JADX INFO: loaded from: classes2.dex */
public class IntervalableComparatorBySize implements Comparator<Intervalable> {
    @Override // java.util.Comparator
    public int compare(Intervalable intervalable, Intervalable intervalable2) {
        int size = intervalable2.size() - intervalable.size();
        return size == 0 ? intervalable.getStart() - intervalable2.getStart() : size;
    }
}
