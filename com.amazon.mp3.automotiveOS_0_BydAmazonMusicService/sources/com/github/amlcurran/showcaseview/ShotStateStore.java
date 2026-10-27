package com.github.amlcurran.showcaseview;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes6.dex */
class ShotStateStore {
    private final Context context;
    long shotId = -1;

    public ShotStateStore(Context context) {
        this.context = context;
    }

    boolean hasShot() {
        if (!isSingleShot()) {
            return false;
        }
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("showcase_internal", 0);
        StringBuilder sb = new StringBuilder();
        sb.append("hasShot");
        sb.append(this.shotId);
        return sharedPreferences.getBoolean(sb.toString(), false);
    }

    boolean isSingleShot() {
        return this.shotId != -1;
    }

    void storeShot() {
        if (isSingleShot()) {
            this.context.getSharedPreferences("showcase_internal", 0).edit().putBoolean("hasShot" + this.shotId, true).apply();
        }
    }

    void setSingleShot(long j) {
        this.shotId = j;
    }
}
