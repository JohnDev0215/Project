package com.amazon.avod.userdownload.internal.database;

import com.amazon.avod.db.ADatabaseInstance;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadsDatabase extends ADatabaseInstance {
    private static final ADatabaseInstance.Scope DATABASE_SCOPE = ADatabaseInstance.Scope.GLOBAL;

    public PlaybackDownloadsDatabase() {
        super("playback_downloads", 31, DATABASE_SCOPE);
        addTable(new DrmTable());
        addTable(new PlaybackDownloadsTable());
    }
}
