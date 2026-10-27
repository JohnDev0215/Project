package com.amazon.mp3.account.auth;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.station.StationException;

/* JADX INFO: loaded from: classes3.dex */
public class AuthExceptionMetricsLogger {
    private static final String TAG = "AuthExceptionMetricsLogger";
    private boolean mIsTesting = false;
    private final SharedPreferences mSharedPreferences;

    public AuthExceptionMetricsLogger(final Context context) {
        this.mSharedPreferences = context.getSharedPreferences("com.amazon.authenticationExceptionPreferences", 0);
    }

    public synchronized void exceptionThrown(final Exception exception) {
        if (isAuthenticationException(exception)) {
            reportAuthenticationException();
        }
    }

    @VisibleForTesting
    boolean isAuthenticationException(final Exception exception) {
        if (exception instanceof BrowseException) {
            return BrowseException.Reason.AUTHENTICATION_ERROR.equals(((BrowseException) exception).getReason());
        }
        if (exception instanceof StationException) {
            return StationException.Reason.AUTHENTICATION_ERROR.equals(((StationException) exception).getReason());
        }
        return false;
    }

    private synchronized void reportAuthenticationException() {
        long j = this.mSharedPreferences.getLong("auth_exception_counter", 0L) + 1;
        storeAuthenticationException(j);
        if (shouldSendMetrics()) {
            MetricsLogger.sendEvent(FlexEvent.builder("museAuthenticationException").build());
        }
        Log.info(TAG, "AuthenticationException occurrence count: " + j);
    }

    private boolean shouldSendMetrics() {
        return !this.mIsTesting;
    }

    public synchronized void successfulCall() {
        if (authenticationExceptionOccurred()) {
            reportSuccessfulCallAfterException();
        }
    }

    private void reportSuccessfulCallAfterException() {
        long j = this.mSharedPreferences.getLong("auth_exception_counter", 0L);
        if (shouldSendMetrics()) {
            MetricsLogger.sendEvent(FlexEvent.builder("museSuccessAfterException").withFlexNum1(new Float(j)).build());
        }
        Log.info(TAG, "MuSE first successful call after " + j + " AuthenticationExceptions");
        clearAuthenticationException();
    }

    @VisibleForTesting
    boolean authenticationExceptionOccurred() {
        return getAuthExceptionCount() >= 1;
    }

    @VisibleForTesting
    long getAuthExceptionCount() {
        return this.mSharedPreferences.getLong("auth_exception_counter", 0L);
    }

    private void storeAuthenticationException(final long exceptionCounter) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putLong("auth_exception_counter", exceptionCounter);
        editorEdit.apply();
    }

    private void clearAuthenticationException() {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.remove("auth_exception_counter");
        editorEdit.apply();
    }
}
