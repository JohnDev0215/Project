package com.android.inputmethod.dictionarypack;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
final class LogProblemReporter implements ProblemReporter {
    private final String TAG;

    public LogProblemReporter(final String tag) {
        this.TAG = tag;
    }

    @Override // com.android.inputmethod.dictionarypack.ProblemReporter
    public void report(final Exception e) {
        Log.e(this.TAG, "Reporting problem", e);
    }
}
