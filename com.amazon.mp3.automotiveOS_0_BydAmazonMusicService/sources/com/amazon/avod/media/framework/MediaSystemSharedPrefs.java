package com.amazon.avod.media.framework;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class MediaSystemSharedPrefs {
    private SharedPreferences mSharedPreferences;

    static class SingletonHolder {
        private static final MediaSystemSharedPrefs INSTANCE = new MediaSystemSharedPrefs();

        SingletonHolder() {
        }
    }

    private MediaSystemSharedPrefs() {
    }

    @Nonnull
    public static MediaSystemSharedPrefs getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initialize(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "context");
        this.mSharedPreferences = (SharedPreferences) Preconditions.checkNotNull(context.getSharedPreferences("MediaSystemSharedPrefs", 0), "sharedPreferences");
    }

    @Nullable
    public synchronized String readFromPrefs(@Nonnull String str) {
        Preconditions.checkState(this.mSharedPreferences != null, "Cannot call readFromPrefs() until initialize() is called.");
        Preconditions.checkNotNull(str, "key");
        return this.mSharedPreferences.getString(str, null);
    }

    public synchronized void writeToPrefs(@Nonnull String str, @Nonnull String str2) {
        Preconditions.checkState(this.mSharedPreferences != null, "Cannot call writeToPrefs() until initialize() is called.");
        Preconditions.checkNotNull(str, "key");
        Preconditions.checkNotNull(str2, "value");
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }
}
