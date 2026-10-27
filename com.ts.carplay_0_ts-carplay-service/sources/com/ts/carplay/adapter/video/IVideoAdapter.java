package com.ts.carplay.adapter.video;

import com.ts.carplay.adapter.common.IAdapter;

/* JADX INFO: loaded from: classes.dex */
public interface IVideoAdapter extends IAdapter {
    void registerListener(IVideoAdapterListener iVideoAdapterListener);

    void unregisterListener(IVideoAdapterListener iVideoAdapterListener);
}
