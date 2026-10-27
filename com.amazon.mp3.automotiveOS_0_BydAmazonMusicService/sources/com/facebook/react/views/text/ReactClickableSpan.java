package com.facebook.react.views.text;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.view.ViewGroupClickEvent;

/* JADX INFO: loaded from: classes6.dex */
class ReactClickableSpan extends ClickableSpan implements ReactSpan {
    private final int mReactTag;

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(@NonNull TextPaint textPaint) {
    }

    ReactClickableSpan(int i) {
        this.mReactTag = i;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(@NonNull View view) {
        ReactContext reactContext = (ReactContext) view.getContext();
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, this.mReactTag);
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new ViewGroupClickEvent(UIManagerHelper.getSurfaceId(reactContext), this.mReactTag));
        }
    }

    public int getReactTag() {
        return this.mReactTag;
    }
}
