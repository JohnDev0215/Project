package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;

/* JADX INFO: loaded from: classes6.dex */
public class RNSScreenStackManagerDelegate<T extends View, U extends BaseViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSScreenStackManagerDelegate(U u) {
        super(u);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        super.setProperty(t, str, obj);
    }
}
