package com.amazon.minerva.client.thirdparty.metric;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class AggregatedLong {
    private List<Long> mCounts;
    private List<Long> mValues;

    public AggregatedLong(List<Long> list, List<Long> list2) {
        Objects.requireNonNull(list, "Values array cannot be null");
        Objects.requireNonNull(list2, "Counts array cannot be null");
        if (list.isEmpty() || list2.isEmpty()) {
            throw new IllegalArgumentException("Values and Counts cannot be empty!");
        }
        if (list.size() != list2.size()) {
            throw new IllegalArgumentException("Values and Counts must be the same length!");
        }
        this.mValues = list;
        this.mCounts = list2;
    }

    public boolean isEmpty() {
        List<Long> list = this.mValues;
        return list == null || this.mCounts == null || list.isEmpty() || this.mCounts.isEmpty();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("values");
        sb.append(":");
        for (int i = 0; i < this.mValues.size(); i++) {
            sb.append(this.mValues.get(i));
            if (i != this.mValues.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(";");
        sb.append("counts");
        sb.append(":");
        for (int i2 = 0; i2 < this.mCounts.size(); i2++) {
            sb.append(this.mCounts.get(i2));
            if (i2 != this.mCounts.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static AggregatedLong parseAggregatedLong(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String[] strArrSplit = str.split(";");
        for (String str2 : strArrSplit[0].split(":")[1].split(",")) {
            arrayList.add(Long.valueOf(Long.parseLong(str2)));
        }
        for (String str3 : strArrSplit[1].split(":")[1].split(",")) {
            arrayList2.add(Long.valueOf(Long.parseLong(str3)));
        }
        return new AggregatedLong(arrayList, arrayList2);
    }

    public int getSizeInBytes() {
        return (this.mValues.size() * 8) + (this.mCounts.size() * 8) + (this.mValues.size() - 1) + (this.mCounts.size() - 1) + 1 + 20;
    }

    public List<Long> getValues() {
        return this.mValues;
    }

    public List<Long> getCounts() {
        return this.mCounts;
    }
}
