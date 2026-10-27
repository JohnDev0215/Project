package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSScreenStackHeaderSubviewManagerInterface;

/* JADX INFO: loaded from: classes6.dex */
public class RNSScreenStackHeaderSubviewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSScreenStackHeaderSubviewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSScreenStackHeaderSubviewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        str.hashCode();
        if (str.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
            ((RNSScreenStackHeaderSubviewManagerInterface) this.mViewManager).setType(t, (String) obj);
        } else {
            super.setProperty(t, str, obj);
        }
    }
}
