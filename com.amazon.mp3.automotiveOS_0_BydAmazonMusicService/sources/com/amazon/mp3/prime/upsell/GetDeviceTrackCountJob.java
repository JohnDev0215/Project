package com.amazon.mp3.prime.upsell;

import android.database.Cursor;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.library.provider.source.local.LocalMediaSource;
import com.amazon.mp3.library.service.sync.LocalTrackSyncOperation;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class GetDeviceTrackCountJob extends Job {
    private static final String TAG = "GetDeviceTrackCountJob";
    private volatile int mDeviceTrackCount = -1;

    @Override // com.amazon.mp3.service.job.Job
    public int run() {
        if (GrantPermissionActivity.areAllGranted(getContext(), LocalTrackSyncOperation.PERMISSIONS_FOR_LOCAL_SYNC)) {
            Cursor cursorQuery = getContext().getContentResolver().query(LocalMediaSource.LOCAL_TRACKS_URI, new String[]{"_id"}, null, null, null);
            if (cursorQuery == null) {
                return 1;
            }
            this.mDeviceTrackCount = cursorQuery.getCount();
            cursorQuery.close();
            return 1;
        }
        this.mDeviceTrackCount = 0;
        Log.info(TAG, "Do not have permission to count tracks on device");
        return 1;
    }

    public int getDeviceTrackCount() {
        return this.mDeviceTrackCount;
    }
}
