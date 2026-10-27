package com.byd.dilink51.app;

import android.annotation.SuppressLint;
import android.app.BYDAuthListener;
import android.app.BYDAuthManager;
import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.Constants;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBYDAuthListener;
import com.byd.dilink.app.IDiLinkBYDAuthManager;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkBYDAuthManager.class, singleton = true)
public class DiLinkBYDAuthManager implements IDiLinkBYDAuthManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final BYDAuthManager f1509a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink5_1_BYDAuthListeners f1510b = new DiLink5_1_BYDAuthListeners();

    public static class DiLink5_1_BYDAuthListeners extends DiLinkCallbackHolders<IDiLinkBYDAuthListener, BYDAuthListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BYDAuthListener createInnerCallback(final IDiLinkBYDAuthListener iDiLinkBYDAuthListener) {
            return new BYDAuthListener() { // from class: com.byd.dilink51.app.DiLinkBYDAuthManager.DiLink5_1_BYDAuthListeners.1
                @Keep
                public void callback(int i, int i2, String str) {
                    iDiLinkBYDAuthListener.authCallback(i, i2, str);
                }
            };
        }
    }

    @SuppressLint({"WrongConstant"})
    public DiLinkBYDAuthManager(Context context) {
        this.f1509a = (BYDAuthManager) context.getSystemService(Constants.BYD_AUTH_SERVICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, BYDAuthListener bYDAuthListener) {
        this.f1509a.requestAuth(i, bYDAuthListener);
    }

    @Override // com.byd.dilink.app.IDiLinkBYDAuthManager
    public void requestAuth(final int i, @NonNull IDiLinkBYDAuthListener iDiLinkBYDAuthListener) {
        this.f1510b.addCallbackIgnoreRemoteException(iDiLinkBYDAuthListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.app.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1513a.a(i, (BYDAuthListener) obj);
            }
        });
    }
}
