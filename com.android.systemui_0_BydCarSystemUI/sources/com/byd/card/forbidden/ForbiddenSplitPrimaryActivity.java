package com.byd.card.forbidden;

import android.util.Log;
import com.android.systemui.stackdivider.DividerUtils;

/* JADX INFO: loaded from: classes.dex */
public class ForbiddenSplitPrimaryActivity extends BaseForbiddenActivity {
    private static final String TAG = "ForbiddenSplitPrimaryActivity";
    public static boolean sIsTopShowing;

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        sIsTopShowing = true;
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        sIsTopShowing = false;
    }

    @Override // com.byd.card.forbidden.BaseForbiddenActivity
    protected void handleQuit() {
        boolean zIsInMultiWindowMode = isInMultiWindowMode();
        Log.d(TAG, "handleQuit isInMultiWindowMode " + zIsInMultiWindowMode);
        if (zIsInMultiWindowMode) {
            DividerUtils.getInstance().startSplitScreenListActivity(this, true);
        } else {
            sendHomeKey();
        }
    }
}
