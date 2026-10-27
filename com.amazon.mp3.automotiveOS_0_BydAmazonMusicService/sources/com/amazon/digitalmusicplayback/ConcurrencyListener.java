package com.amazon.digitalmusicplayback;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ConcurrencyListener {
    public abstract void inactivityThresholdReceived(long j);

    public abstract void maximumConcurrencyReached(int i, ArrayList<StreamingStatusResponse> arrayList);
}
