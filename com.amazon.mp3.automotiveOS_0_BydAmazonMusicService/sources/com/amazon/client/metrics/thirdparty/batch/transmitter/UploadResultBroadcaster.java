package com.amazon.client.metrics.thirdparty.batch.transmitter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class UploadResultBroadcaster {
    private Context mContext;

    public UploadResultBroadcaster(Context context) {
        this.mContext = context;
    }

    public void broadcastResult(int i, int i2, String str) {
        Log.d("UploadResultBroadcaster", "broadcastResult");
        this.mContext.sendBroadcast(new Intent("com.amazon.intent.action.METRICS_UPLOAD_RESULT").putExtra("NUM_BATCHES_SENT", i2).putExtra("RESULT_CODE", i).putExtra("QUEUE_NAME", str));
    }
}
