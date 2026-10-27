package org.chromium.content.browser;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import org.chromium.base.Log;

/* JADX INFO: loaded from: classes3.dex */
public class ContentViewClient {
    private static final String TAG = "cr.ContentViewClient";
    private static final int UNSPECIFIED_MEASURE_SPEC = View.MeasureSpec.makeMeasureSpec(0, 0);

    public static boolean shouldPropagateKey(int i) {
        return (i == 82 || i == 3 || i == 4 || i == 5 || i == 6 || i == 26 || i == 79 || i == 27 || i == 80 || i == 25 || i == 164 || i == 24) ? false : true;
    }

    public boolean doesPerformProcessText() {
        return false;
    }

    public boolean doesPerformWebSearch() {
        return false;
    }

    public ContentVideoViewEmbedder getContentVideoViewEmbedder() {
        return null;
    }

    public String getProductVersion() {
        return "";
    }

    public int getSystemWindowInsetBottom() {
        return 0;
    }

    public int getSystemWindowInsetLeft() {
        return 0;
    }

    public int getSystemWindowInsetRight() {
        return 0;
    }

    public int getSystemWindowInsetTop() {
        return 0;
    }

    public boolean isSelectActionModeAllowed(int i) {
        return true;
    }

    public void onBackgroundColorChanged(int i) {
    }

    public void onContextualActionBarHidden() {
    }

    public void onContextualActionBarShown() {
    }

    public void onFocusedNodeEditabilityChanged(boolean z) {
    }

    public void onImeEvent() {
    }

    public void onOffsetsForFullscreenChanged(float f, float f2) {
    }

    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
    }

    public void onUpdateTitle(String str) {
    }

    public void performWebSearch(String str) {
    }

    public boolean shouldBlockMediaRequest(String str) {
        return false;
    }

    public void startProcessTextIntent(Intent intent) {
    }

    public boolean shouldOverrideKeyEvent(KeyEvent keyEvent) {
        return !shouldPropagateKey(keyEvent.getKeyCode());
    }

    public void onStartContentIntent(Context context, String str, boolean z) {
        try {
            Intent uri = Intent.parseUri(str, 1);
            uri.addFlags(268435456);
            try {
                context.startActivity(uri);
            } catch (ActivityNotFoundException unused) {
                Log.w(TAG, "No application can handle %s", str);
            }
        } catch (Exception e) {
            Log.w(TAG, "Bad URI %s", str, e);
        }
    }

    public int getDesiredWidthMeasureSpec() {
        return UNSPECIFIED_MEASURE_SPEC;
    }

    public int getDesiredHeightMeasureSpec() {
        return UNSPECIFIED_MEASURE_SPEC;
    }
}
