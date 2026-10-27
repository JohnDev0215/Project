package com.android.car.storagemonitoring;

import android.car.storagemonitoring.UidIoRecord;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes3.dex */
public interface UidIoStatsProvider {
    SparseArray<UidIoRecord> load();
}
