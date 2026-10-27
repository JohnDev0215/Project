package com.android.systemui.qs.tileimpl;

/* JADX INFO: loaded from: classes.dex */
public interface QSFactory {
    QSTile createCarTile(String tileSpec, boolean fromSaved);

    QSTile createSceneTile(String tileSpec, boolean fromSaved);

    QSTile createTile(String tileSpec, boolean fromSaved);
}
