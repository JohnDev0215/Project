package com.amazon.mp3.playback.streaming.concurrency;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.net.dmls.ContentId;
import com.amazon.mp3.net.dmls.DMLSExceptions;
import com.amazon.mp3.net.dmls.IdentifierType;
import com.amazon.mp3.net.dmls.StreamingStatus;
import com.amazon.mp3.util.Log;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public final class StreamingConcurrencyCheckReceiver extends BroadcastReceiver {
    public static final String TAG = StreamingConcurrencyCheckReceiver.class.getSimpleName();

    private enum Policy {
        DO_NOT_HANDLE,
        USE_NOTIFICATION,
        USE_DIALOG
    }

    public static Intent getBroadcastIntentForHandleCustomerDecision(int customerActionCode, ContentId contentId, long trackDuration, long streamingBeganTimeEpochMs) {
        Intent intent = new Intent();
        intent.setAction("com.amazon.mp3.streaming.concurrency.HandleCustomerResponse");
        intent.putExtra("com.amazon.mp3.streaming.concurrency.CustomerAction", customerActionCode);
        intent.putExtra("com.amazon.mp3.streaming.concurrency.TrackId", contentId.getId());
        intent.putExtra("com.amazon.mp3.streaming.concurrency.TrackIdType", contentId.getType());
        intent.putExtra("com.amazon.mp3.streaming.concurrency.TrackDuration", trackDuration);
        intent.putExtra("com.amazon.mp3.streaming.concurrency.TrackStartedTime", streamingBeganTimeEpochMs);
        return intent;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (determinePolicy(context) == Policy.DO_NOT_HANDLE) {
            return;
        }
        if (intent.getAction().equals("com.amazon.mp3.streaming.concurrency.UpdateConcurrencyForTrack")) {
            handleUpdateIntent(context, intent);
        } else if (intent.getAction().equals("com.amazon.mp3.streaming.concurrency.HandleCustomerResponse")) {
            handleCustomerResponseIntent(context, intent);
        }
    }

    private void handleUpdateIntent(Context context, Intent intent) {
        try {
            ContentId contentId = new ContentId(IdentifierType.fromString(intent.getStringExtra("com.amazon.mp3.streaming.concurrency.TrackIdType")), intent.getStringExtra("com.amazon.mp3.streaming.concurrency.TrackId"));
            StreamingStatus streamingStatusFromString = StreamingStatus.fromString(intent.getStringExtra("com.amazon.mp3.streaming.concurrency.StreamingStatus"));
            long longExtra = intent.getLongExtra("com.amazon.mp3.streaming.concurrency.TrackDuration", 0L);
            long time = new Date().getTime();
            long longExtra2 = (time - intent.getLongExtra("com.amazon.mp3.streaming.concurrency.TrackStartedTime", time)) / 1000;
            boolean booleanExtra = intent.getBooleanExtra("com.amazon.mp3.streaming.concurrency.ForceUpdate", false);
            String stringExtra = intent.getStringExtra("com.amazon.mp3.streaming.concurrency.OldDeviceName");
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = "";
            }
            String str = stringExtra;
            Log.debug(TAG, "Got an UPDATE intent! contentId = {%s}, status = {%s}, track duration = %d seconds, track elapsed = %d seconds, force = %s, old device name = {%s}, policy = %s", contentId.toString(), streamingStatusFromString.toString(), Long.valueOf(longExtra), Long.valueOf(longExtra2), String.valueOf(booleanExtra), str, determinePolicy(context).toString());
            doUpdate(contentId, streamingStatusFromString, longExtra, longExtra2, str, booleanExtra);
        } catch (DMLSExceptions.InvalidContentIdException e) {
            Log.warning(TAG, "Unable to update Streaming Status due to invalid ContentId", e);
        }
    }

    private void doUpdate(ContentId contentId, StreamingStatus status, long trackDuration, long trackElapsed, String oldDeviceName, boolean forceUpdate) {
        if (status != StreamingStatus.PLAY) {
            return;
        }
        if (!forceUpdate) {
            StreamingConcurrencyStatusConnector.getInstance(AmazonApplication.getContext()).updateStreamingStatus(contentId, status, trackDuration, trackElapsed);
        } else {
            StreamingConcurrencyStatusConnector.getInstance(AmazonApplication.getContext()).terminateAndUpdateStreamingStatus(contentId, status, trackDuration, trackElapsed, oldDeviceName);
        }
    }

    private void handleCustomerResponseIntent(Context context, Intent intent) {
        ContentId contentId;
        int intExtra = intent.getIntExtra("com.amazon.mp3.streaming.concurrency.CustomerAction", 2);
        try {
            contentId = new ContentId((IdentifierType) intent.getSerializableExtra("com.amazon.mp3.streaming.concurrency.TrackIdType"), intent.getStringExtra("com.amazon.mp3.streaming.concurrency.TrackId"));
        } catch (DMLSExceptions.InvalidContentIdException unused) {
            Log.warning(TAG, "Unable to create ContentId for Customer Response");
            contentId = new ContentId();
        }
        ContentId contentId2 = contentId;
        long longExtra = intent.getLongExtra("com.amazon.mp3.streaming.concurrency.TrackDuration", 0L);
        long time = new Date().getTime();
        long longExtra2 = (time - intent.getLongExtra("com.amazon.mp3.streaming.concurrency.TrackStartedTime", time)) / 1000;
        String stringExtra = intent.getStringExtra("com.amazon.mp3.streaming.concurrency.OldDeviceName");
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = "";
        }
        String str = stringExtra;
        Log.debug(TAG, "Got a CUSTOMER_ACTION intent! action code = %d, contentId = {%s}, trackDuration = %d, elapsedTime = %d", Integer.valueOf(intExtra), contentId2, Long.valueOf(longExtra), Long.valueOf(longExtra2));
        if (intExtra == 1) {
            doUpdate(contentId2, StreamingStatus.PLAY, longExtra, longExtra2, str, true);
        }
    }

    private Policy determinePolicy(Context context) {
        return AmazonApplication.getApplication().getPauseState() ? Policy.USE_NOTIFICATION : Policy.USE_DIALOG;
    }
}
