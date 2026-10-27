package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.net.Uri;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
interface TrackMigrator {
    Uri migrateTrack(final PreUnityTrack preUnityTrack) throws IOException;
}
