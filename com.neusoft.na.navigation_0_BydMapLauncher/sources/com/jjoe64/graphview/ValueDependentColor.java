package com.jjoe64.graphview;

import com.jjoe64.graphview.series.DataPointInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface ValueDependentColor<T extends DataPointInterface> {
    int get(T t);
}
