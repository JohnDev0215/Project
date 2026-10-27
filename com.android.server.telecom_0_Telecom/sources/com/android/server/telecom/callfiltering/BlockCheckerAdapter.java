package com.android.server.telecom.callfiltering;

import android.content.Context;
import android.os.Bundle;
import android.provider.BlockedNumberContract;
import android.telecom.Log;

/* JADX INFO: loaded from: classes.dex */
public class BlockCheckerAdapter {
    private static final String TAG = "BlockCheckerAdapter";

    public int getBlockStatus(Context context, String str, Bundle bundle) {
        int iShouldSystemBlockNumber;
        long jNanoTime = System.nanoTime();
        try {
            iShouldSystemBlockNumber = BlockedNumberContract.SystemContract.shouldSystemBlockNumber(context, str, bundle);
            if (iShouldSystemBlockNumber != 0) {
                try {
                    Log.d(TAG, str + " is blocked.", new Object[0]);
                } catch (Exception e) {
                    e = e;
                    Log.e(TAG, e, "Exception checking for blocked number", new Object[0]);
                }
            }
        } catch (Exception e2) {
            e = e2;
            iShouldSystemBlockNumber = 0;
        }
        int iNanoTime = (int) ((System.nanoTime() - jNanoTime) / 1000000);
        if (iNanoTime > 500 || Log.isLoggable(3)) {
            Log.d(TAG, "Blocked number lookup took: " + iNanoTime + " ms.", new Object[0]);
        }
        return iShouldSystemBlockNumber;
    }
}
