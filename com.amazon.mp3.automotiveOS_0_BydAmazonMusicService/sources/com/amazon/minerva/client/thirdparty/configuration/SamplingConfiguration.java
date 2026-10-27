package com.amazon.minerva.client.thirdparty.configuration;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class SamplingConfiguration {
    private static final String TAG = "SamplingConfiguration";
    private int mDefaultSamplingRate;

    public SamplingConfiguration(int i) {
        if (!isValidInput(i)) {
            this.mDefaultSamplingRate = 100;
        } else {
            this.mDefaultSamplingRate = i;
        }
    }

    private boolean isValidInput(int i) {
        if (i >= 0 && i <= 100) {
            return true;
        }
        Log.e(TAG, String.format("Invalid defaultSamplingRate: %d.", Integer.valueOf(i)));
        return false;
    }

    public int getDefaultSamplingRate() {
        return this.mDefaultSamplingRate;
    }
}
