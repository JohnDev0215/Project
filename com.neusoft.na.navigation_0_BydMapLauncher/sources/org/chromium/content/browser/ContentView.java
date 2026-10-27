package org.chromium.content.browser;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.ImagesContract;
import org.bouncycastle.i18n.MessageBundle;
import org.bouncycastle.i18n.TextBundle;
import org.chromium.base.Log;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.ContentViewCore;

/* JADX INFO: loaded from: classes3.dex */
public class ContentView extends FrameLayout implements ContentViewCore.InternalAccessDelegate, SmartClipProvider {
    private static final String TAG = "cr.ContentView";
    protected final ContentViewCore mContentViewCore;

    @Override // android.view.View
    public boolean performLongClick() {
        return false;
    }

    public static ContentView createContentView(Context context, ContentViewCore contentViewCore) {
        return new ContentViewApi23(context, contentViewCore);
    }

    public ContentView(Context context, ContentViewCore contentViewCore) {
        super(context, null, R.attr.webViewStyle);
        if (getScrollBarStyle() == 0) {
            setHorizontalScrollBarEnabled(false);
            setVerticalScrollBarEnabled(false);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.mContentViewCore = contentViewCore;
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        if (this.mContentViewCore.supportsAccessibilityAction(i)) {
            return this.mContentViewCore.performAccessibilityAction(i, bundle);
        }
        return super.performAccessibilityAction(i, bundle);
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        AccessibilityNodeProvider accessibilityNodeProvider = this.mContentViewCore.getAccessibilityNodeProvider();
        return accessibilityNodeProvider != null ? accessibilityNodeProvider : super.getAccessibilityNodeProvider();
    }

    @Override // android.view.View, org.chromium.content.browser.ContentViewCore.InternalAccessDelegate
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        try {
            TraceEvent.begin("ContentView.onSizeChanged");
            super.onSizeChanged(i, i2, i3, i4);
            this.mContentViewCore.onSizeChanged(i, i2, i3, i4);
        } finally {
            TraceEvent.end("ContentView.onSizeChanged");
        }
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return this.mContentViewCore.onCreateInputConnection(editorInfo);
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        return this.mContentViewCore.onCheckIsTextEditor();
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        try {
            TraceEvent.begin("ContentView.onFocusChanged");
            super.onFocusChanged(z, i, rect);
            this.mContentViewCore.onFocusChanged(z);
        } finally {
            TraceEvent.end("ContentView.onFocusChanged");
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.mContentViewCore.onWindowFocusChanged(z);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mContentViewCore.onKeyUp(i, keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (isFocused()) {
            return this.mContentViewCore.dispatchKeyEvent(keyEvent);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mContentViewCore.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        boolean zOnHoverEvent = this.mContentViewCore.onHoverEvent(motionEvent);
        if (!this.mContentViewCore.isTouchExplorationEnabled()) {
            super.onHoverEvent(motionEvent);
        }
        return zOnHoverEvent;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return this.mContentViewCore.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        this.mContentViewCore.onConfigurationChanged(configuration);
    }

    @Override // android.view.View
    public void scrollBy(int i, int i2) {
        this.mContentViewCore.scrollBy(i, i2, false);
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        this.mContentViewCore.scrollTo(i, i2);
    }

    @Override // android.view.View
    protected int computeHorizontalScrollExtent() {
        return this.mContentViewCore.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    protected int computeHorizontalScrollOffset() {
        return this.mContentViewCore.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    protected int computeHorizontalScrollRange() {
        return this.mContentViewCore.computeHorizontalScrollRange();
    }

    @Override // android.view.View
    protected int computeVerticalScrollExtent() {
        return this.mContentViewCore.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    protected int computeVerticalScrollOffset() {
        return this.mContentViewCore.computeVerticalScrollOffset();
    }

    @Override // android.view.View
    protected int computeVerticalScrollRange() {
        return this.mContentViewCore.computeVerticalScrollRange();
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        ContentViewClient contentViewClient = this.mContentViewCore.getContentViewClient();
        int desiredWidthMeasureSpec = contentViewClient.getDesiredWidthMeasureSpec();
        if (View.MeasureSpec.getMode(desiredWidthMeasureSpec) != 0) {
            i = desiredWidthMeasureSpec;
        }
        int desiredHeightMeasureSpec = contentViewClient.getDesiredHeightMeasureSpec();
        if (View.MeasureSpec.getMode(desiredHeightMeasureSpec) != 0) {
            i2 = desiredHeightMeasureSpec;
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public boolean awakenScrollBars(int i, boolean z) {
        return this.mContentViewCore.awakenScrollBars(i, z);
    }

    @Override // android.view.View, org.chromium.content.browser.ContentViewCore.InternalAccessDelegate
    public boolean awakenScrollBars() {
        return super.awakenScrollBars();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mContentViewCore.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mContentViewCore.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.mContentViewCore.onVisibilityChanged(view, i);
    }

    @Override // org.chromium.content.browser.SmartClipProvider
    public void extractSmartClipData(int i, int i2, int i3, int i4) {
        this.mContentViewCore.extractSmartClipData(i, i2, i3, i4);
    }

    @Override // org.chromium.content.browser.SmartClipProvider
    public void setSmartClipResultHandler(final Handler handler) {
        if (handler == null) {
            this.mContentViewCore.setSmartClipDataListener(null);
        } else {
            this.mContentViewCore.setSmartClipDataListener(new ContentViewCore.SmartClipDataListener() { // from class: org.chromium.content.browser.ContentView.1
                @Override // org.chromium.content.browser.ContentViewCore.SmartClipDataListener
                public void onSmartClipDataExtracted(String str, String str2, Rect rect) {
                    Bundle bundle = new Bundle();
                    bundle.putString(ImagesContract.URL, ContentView.this.mContentViewCore.getWebContents().getVisibleUrl());
                    bundle.putString(MessageBundle.TITLE_ENTRY, ContentView.this.mContentViewCore.getWebContents().getTitle());
                    bundle.putParcelable("rect", rect);
                    bundle.putString(TextBundle.TEXT_ENTRY, str);
                    bundle.putString("html", str2);
                    try {
                        Message messageObtain = Message.obtain(handler, 0);
                        messageObtain.setData(bundle);
                        messageObtain.sendToTarget();
                    } catch (Exception e) {
                        Log.e(ContentView.TAG, "Error calling handler for smart clip data: ", e);
                    }
                }
            });
        }
    }

    @Override // org.chromium.content.browser.ContentViewCore.InternalAccessDelegate
    public boolean super_onKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    @Override // org.chromium.content.browser.ContentViewCore.InternalAccessDelegate
    public boolean super_dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // org.chromium.content.browser.ContentViewCore.InternalAccessDelegate
    public boolean super_onGenericMotionEvent(MotionEvent motionEvent) {
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // org.chromium.content.browser.ContentViewCore.InternalAccessDelegate
    public void super_onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // org.chromium.content.browser.ContentViewCore.InternalAccessDelegate
    public boolean super_awakenScrollBars(int i, boolean z) {
        return super.awakenScrollBars(i, z);
    }

    private static class ContentViewApi23 extends ContentView {
        public ContentViewApi23(Context context, ContentViewCore contentViewCore) {
            super(context, contentViewCore);
        }

        @Override // android.view.View
        public void onProvideVirtualStructure(ViewStructure viewStructure) {
            this.mContentViewCore.onProvideVirtualStructure(viewStructure, false);
        }
    }
}
