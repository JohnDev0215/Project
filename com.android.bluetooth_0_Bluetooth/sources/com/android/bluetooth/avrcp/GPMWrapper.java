package com.android.bluetooth.avrcp;

import android.media.session.MediaSession;
import android.os.Looper;
import android.util.Log;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class GPMWrapper extends MediaPlayerWrapper {
    private static final boolean DEBUG = true;
    private static final String TAG = "AvrcpGPMWrapper";

    GPMWrapper(MediaController controller, Looper looper) {
        super(controller, looper);
    }

    @Override // com.android.bluetooth.avrcp.MediaPlayerWrapper
    boolean isMetadataSynced() {
        if (getQueue() == null) {
            return false;
        }
        MediaSession.QueueItem currItem = null;
        Iterator<MediaSession.QueueItem> it = getQueue().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            MediaSession.QueueItem item = it.next();
            if (item.getQueueId() == getActiveQueueID()) {
                currItem = item;
                break;
            }
        }
        Metadata qitem = Util.toMetadata(currItem);
        Metadata mdata = Util.toMetadata(getMetadata());
        if (currItem == null || !qitem.equals(mdata)) {
            Log.d(TAG, "Metadata currently out of sync for Google Play Music");
            Log.d(TAG, "  └ Current queueItem: " + qitem);
            Log.d(TAG, "  └ Current metadata : " + mdata);
            return false;
        }
        return true;
    }
}
