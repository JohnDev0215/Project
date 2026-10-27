package com.android.car.developeroptions.development.gamedriver;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;

/* JADX INFO: loaded from: classes.dex */
public class GameDriverContentObserver extends ContentObserver {
    OnGameDriverContentChangedListener mListener;

    interface OnGameDriverContentChangedListener {
        void onGameDriverContentChanged();
    }

    public GameDriverContentObserver(Handler handler, OnGameDriverContentChangedListener onGameDriverContentChangedListener) {
        super(handler);
        this.mListener = onGameDriverContentChangedListener;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
        this.mListener.onGameDriverContentChanged();
    }

    public void register(ContentResolver contentResolver) {
        contentResolver.registerContentObserver(Settings.Global.getUriFor("game_driver_all_apps"), false, this);
    }

    public void unregister(ContentResolver contentResolver) {
        contentResolver.unregisterContentObserver(this);
    }
}
