package com.android.car.audio;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
final class FocusInteraction {
    static final int INTERACTION_CONCURRENT = 2;
    static final int INTERACTION_EXCLUSIVE = 1;
    static final int INTERACTION_REJECT = 0;
    private final CarAudioSettings mCarAudioFocusSettings;
    private ContentObserver mContentObserver;
    private final int[][] mInteractionMatrix;
    private final Object mLock = new Object();
    private int mUserId;
    private static final String TAG = FocusInteraction.class.getSimpleName();
    static final Uri AUDIO_FOCUS_NAVIGATION_REJECTED_DURING_CALL_URI = Settings.Secure.getUriFor("android.car.KEY_AUDIO_FOCUS_NAVIGATION_REJECTED_DURING_CALL");
    private static final int[][] sInteractionMatrix = {new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0}, new int[]{0, 1, 2, 1, 1, 1, 1, 2, 2, 1, 2, 2, 1}, new int[]{0, 2, 2, 1, 2, 1, 2, 2, 2, 1, 2, 2, 2}, new int[]{0, 2, 0, 2, 1, 1, 0, 0, 0, 1, 2, 2, 0}, new int[]{0, 0, 2, 2, 2, 2, 0, 0, 2, 1, 2, 2, 0}, new int[]{0, 0, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 0}, new int[]{0, 2, 2, 1, 1, 1, 2, 2, 2, 1, 2, 2, 0}, new int[]{0, 2, 2, 1, 1, 1, 2, 2, 2, 1, 2, 2, 2}, new int[]{0, 2, 2, 1, 1, 1, 2, 2, 2, 1, 2, 2, 2}, new int[]{0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 2, 0, 0}, new int[]{0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, new int[]{0, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2}, new int[]{0, 1, 2, 1, 1, 1, 1, 2, 2, 1, 2, 2, 1}};

    FocusInteraction(CarAudioSettings carAudioSettings) {
        Objects.requireNonNull(carAudioSettings);
        this.mCarAudioFocusSettings = carAudioSettings;
        this.mInteractionMatrix = cloneInteractionMatrix(sInteractionMatrix);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void navigationOnCallSettingChanged() {
        synchronized (this.mLock) {
            if (this.mUserId != -10000) {
                setRejectNavigationOnCallLocked(isRejectNavigationOnCallEnabledInSettings(this.mUserId));
            }
        }
    }

    public void setRejectNavigationOnCallLocked(boolean navigationRejectedWithCall) {
        this.mInteractionMatrix[5][2] = navigationRejectedWithCall ? 0 : sInteractionMatrix[5][2];
    }

    public int evaluateRequest(int requestedContext, FocusEntry focusHolder, List<FocusEntry> focusLosers, boolean allowDucking, boolean allowsDelayedFocus) {
        int holderContext = focusHolder.getAudioContext();
        Preconditions.checkArgumentInRange(holderContext, 0, this.mInteractionMatrix.length - 1, "holderContext");
        synchronized (this.mLock) {
            int[] holderRow = this.mInteractionMatrix[holderContext];
            Preconditions.checkArgumentInRange(requestedContext, 0, holderRow.length - 1, "requestedContext");
            int i = holderRow[requestedContext];
            if (i == 0) {
                return allowsDelayedFocus ? 2 : 0;
            }
            if (i == 1) {
                focusLosers.add(focusHolder);
                return 1;
            }
            if (i == 2) {
                if (!allowDucking || focusHolder.wantsPauseInsteadOfDucking() || focusHolder.receivesDuckEvents()) {
                    focusLosers.add(focusHolder);
                }
                return 1;
            }
            Log.e(TAG, String.format("Unsupported CarAudioContext %d - rejecting request", Integer.valueOf(holderRow[requestedContext])));
            return 0;
        }
    }

    void setUserIdForSettings(int userId) {
        synchronized (this.mLock) {
            this.mUserId = userId;
            if (this.mContentObserver != null) {
                this.mCarAudioFocusSettings.getContentResolver().unregisterContentObserver(this.mContentObserver);
                this.mContentObserver = null;
            }
            if (this.mUserId == -10000) {
                setRejectNavigationOnCallLocked(false);
                return;
            }
            this.mContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.car.audio.FocusInteraction.1
                @Override // android.database.ContentObserver
                public void onChange(boolean selfChange, Uri uri) {
                    if (uri.equals(FocusInteraction.AUDIO_FOCUS_NAVIGATION_REJECTED_DURING_CALL_URI)) {
                        FocusInteraction.this.navigationOnCallSettingChanged();
                    }
                }
            };
            this.mCarAudioFocusSettings.getContentResolver().registerContentObserver(AUDIO_FOCUS_NAVIGATION_REJECTED_DURING_CALL_URI, false, this.mContentObserver, userId);
            setRejectNavigationOnCallLocked(isRejectNavigationOnCallEnabledInSettings(this.mUserId));
        }
    }

    private boolean isRejectNavigationOnCallEnabledInSettings(int userId) {
        return this.mCarAudioFocusSettings.isRejectNavigationOnCallEnabledInSettings(userId);
    }

    int[][] getInteractionMatrix() {
        return cloneInteractionMatrix(this.mInteractionMatrix);
    }

    private static int[][] cloneInteractionMatrix(int[][] matrixToClone) {
        int[][] interactionMatrixClone = (int[][]) Array.newInstance((Class<?>) int.class, matrixToClone.length, matrixToClone.length);
        for (int audioContext = 0; audioContext < matrixToClone.length; audioContext++) {
            System.arraycopy(matrixToClone[audioContext], 0, interactionMatrixClone[audioContext], 0, matrixToClone.length);
        }
        return interactionMatrixClone;
    }

    public void dump(String indent, PrintWriter writer) {
        boolean rejectNavigationOnCall = this.mInteractionMatrix[5][2] == 0;
        writer.printf("%sReject Navigation on Call: %b\n", indent, Boolean.valueOf(rejectNavigationOnCall));
    }
}
