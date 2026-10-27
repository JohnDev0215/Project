package com.ts.carplay.adapter.location;

import com.ts.carplay.adapter.common.IAdapter;

/* JADX INFO: loaded from: classes.dex */
public interface ILocationAdapter extends IAdapter {
    void registerListener(ILocationAdapterListener iLocationAdapterListener);

    void unregisterListener(ILocationAdapterListener iLocationAdapterListener);

    default void setMapCoordinateSystem(int gprmcData) {
    }
}
