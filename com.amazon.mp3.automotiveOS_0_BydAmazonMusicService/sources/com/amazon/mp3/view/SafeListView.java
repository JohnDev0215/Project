package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ListView;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class SafeListView extends ListView {
    private static final String TAG = SafeListView.class.getSimpleName();

    public SafeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        if (gainFocus && getAdapter() == null) {
            Log.debug(TAG, "onFocusChanged: null adapter, reverting to hack");
        } else {
            super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        }
    }
}
