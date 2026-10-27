package com.bydux.api.common.views.skin.listener;

/* JADX INFO: loaded from: classes5.dex */
public interface ISkinLoader {
    void attach(ISkinUpdate observer);

    void detach(ISkinUpdate observer);

    void notifySkinUpdate();
}
