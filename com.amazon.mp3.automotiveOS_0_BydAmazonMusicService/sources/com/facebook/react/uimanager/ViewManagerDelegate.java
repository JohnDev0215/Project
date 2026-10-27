package com.facebook.react.uimanager;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;

/* JADX INFO: loaded from: classes6.dex */
public interface ViewManagerDelegate<T extends View> {
    void receiveCommand(T t, String str, ReadableArray readableArray);

    void setProperty(T t, String str, @Nullable Object obj);
}
