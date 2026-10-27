package com.amazon.mp3.dialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.LeadingMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes3.dex */
public enum TryOurWidgetDialogManager {
    INSTANCE;

    public static final String ACTION_TRY_OUR_WIDGET_DIALOG_REQUIRED = "com.amazon.mp3.action.TRY_OUR_WIDGET_DIALOG_REQUIRED";
    private static final String BROADCAST_TIME_PREF_NAME = "broadcast_time_pref_name";
    private static final long DELAY_BEFORE_SHOWING_DIALOG = 300000;
    private static final String DIALOG_ALREADY_SHOWED_PREF_NAME = "dialog_already_showed";
    private static final String DIALOG_NAME = "TryOurWidgetDialog";
    private static final String PLAYED_TRACKS_COUNT_PREF_NAME = "played_tracks_count";
    public static final String SHARED_PREF_NAME = "com.amazon.mp3.try_our_widget";
    private static final String TAG = TryOurWidgetDialogManager.class.getSimpleName();
    private static final int THRESHOLD_TRACK_PLAYED = 2;
    public static final String WIDGET_ALREADY_ENABLED = "widget_already_enabled";

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldShowDialog() {
        return false;
    }

    private SharedPreferences getPreferences() {
        return AmazonApplication.getContext().getSharedPreferences(SHARED_PREF_NAME, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDialogAlreadyShowed() {
        SharedPreferences.Editor editorEdit = getPreferences().edit();
        editorEdit.putBoolean(DIALOG_ALREADY_SHOWED_PREF_NAME, true);
        editorEdit.apply();
    }

    private boolean isTracksPlayedReachedThreshold() {
        return getPreferences().getInt(PLAYED_TRACKS_COUNT_PREF_NAME, 0) >= 2;
    }

    private void setBroadcastTimeToNowInNs() {
        Log.debug(TAG, "Set broadcast time");
        SharedPreferences.Editor editorEdit = getPreferences().edit();
        editorEdit.putLong(BROADCAST_TIME_PREF_NAME, System.nanoTime());
        editorEdit.apply();
    }

    private long getBroadcastTime() {
        return getPreferences().getLong(BROADCAST_TIME_PREF_NAME, -1L);
    }

    private void broadcastTryOurDialogReceiver() {
        Log.debug(TAG, "Dialog required / Scheduling broadcast");
        setBroadcastTimeToNowInNs();
        new Timer().schedule(new TimerTask() { // from class: com.amazon.mp3.dialog.TryOurWidgetDialogManager.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Log.debug(TryOurWidgetDialogManager.TAG, "Sending broadcast");
                AmazonApplication.getContext().sendBroadcast(new Intent(TryOurWidgetDialogManager.ACTION_TRY_OUR_WIDGET_DIALOG_REQUIRED));
            }
        }, 300000L);
    }

    private boolean isWidgetAlreadyEnabled() {
        return getPreferences().getBoolean(WIDGET_ALREADY_ENABLED, false);
    }

    public boolean wasDialogAlreadyShowed() {
        return getPreferences().getBoolean(DIALOG_ALREADY_SHOWED_PREF_NAME, false);
    }

    public void broadcastTryOurDialogReceiverIfMissed() {
        long broadcastTime = getBroadcastTime();
        if (broadcastTime > 0) {
            boolean z = (System.nanoTime() - broadcastTime) / 1000000 > 300000;
            if (shouldShowDialog() && z) {
                Log.debug(TAG, "Previous broadcast was missed");
                broadcastTryOurDialogReceiver();
            }
        }
    }

    public synchronized void incrementTracksPlayed() {
        if (!wasDialogAlreadyShowed() && !isTracksPlayedReachedThreshold()) {
            SharedPreferences preferences = getPreferences();
            int i = preferences.getInt(PLAYED_TRACKS_COUNT_PREF_NAME, 0);
            SharedPreferences.Editor editorEdit = preferences.edit();
            int i2 = i + 1;
            editorEdit.putInt(PLAYED_TRACKS_COUNT_PREF_NAME, i2);
            editorEdit.apply();
            Log.debug(TAG, "Tracks played count updated: %s / Checking if dialog is required...", Integer.valueOf(i2));
            if (shouldShowDialog()) {
                broadcastTryOurDialogReceiver();
            }
        }
    }

    public static class TryOurWidgetBroadcastReceiver extends BroadcastReceiver {
        private static final String DEBUG_TAG = TryOurWidgetBroadcastReceiver.class.getSimpleName();
        private FragmentManager mFragmentManager;

        public TryOurWidgetBroadcastReceiver(FragmentManager fm) {
            Log.debug(DEBUG_TAG, "TryOurWidgetBroadcastReceiver initialized");
            this.mFragmentManager = fm;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            TryOurWidgetDialogManager tryOurWidgetDialogManager = TryOurWidgetDialogManager.INSTANCE;
            if (tryOurWidgetDialogManager.shouldShowDialog()) {
                Log.debug(DEBUG_TAG, "TryOurWidgetBroadcastReceiver received / Showing the dialog...");
                new TryOurWidgetDialog().show(this.mFragmentManager, TryOurWidgetDialogManager.DIALOG_NAME);
                tryOurWidgetDialogManager.setDialogAlreadyShowed();
            }
        }
    }

    public static class TryOurWidgetDialog extends DialogFragment {
        private static final String DEBUG_TAG = TryOurWidgetBroadcastReceiver.class.getSimpleName();

        @Override // androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Log.verbose(DEBUG_TAG, "Inflating the layout");
            getDialog().getWindow().requestFeature(1);
            View viewInflate = inflater.inflate(R.layout.try_our_widget_dialog, container, false);
            View viewFindViewById = viewInflate.findViewById(R.id.try_our_widget_dialog_button);
            TextView textView = (TextView) viewInflate.findViewById(R.id.try_our_widget_dialog_second_text);
            SpannableString spannableString = new SpannableString(getString(R.string.try_our_widget_dialog_second_text));
            spannableString.setSpan(new StepsLeadingMarginSpan(), 0, spannableString.length(), 0);
            textView.setText(spannableString);
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.dialog.TryOurWidgetDialogManager.TryOurWidgetDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Log.verbose(TryOurWidgetDialog.DEBUG_TAG, "Button clicked / Dialog dismissed");
                    TryOurWidgetDialog.this.getDialog().dismiss();
                }
            });
            viewInflate.findViewById(R.id.try_our_widget_close_button).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.dialog.TryOurWidgetDialogManager.TryOurWidgetDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View arg0) {
                    Log.verbose(TryOurWidgetDialog.DEBUG_TAG, "Button clicked / Dialog dismissed");
                    TryOurWidgetDialog.this.getDialog().dismiss();
                }
            });
            return viewInflate;
        }
    }

    private static class StepsLeadingMarginSpan implements LeadingMarginSpan {
        private final int mOutdent;

        @Override // android.text.style.LeadingMarginSpan
        public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
        }

        private StepsLeadingMarginSpan() {
            this.mOutdent = AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.step_outdent);
        }

        @Override // android.text.style.LeadingMarginSpan
        public int getLeadingMargin(boolean first) {
            if (first) {
                return 0;
            }
            return this.mOutdent;
        }
    }
}
