package com.byd.dilink30.app;

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
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkBYDAuthManager.class, singleton = true)
public class DiLinkBYDAuthManager implements IDiLinkBYDAuthManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final BYDAuthManager f1185a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink3_0_BYDAuthListeners f1186b = new DiLink3_0_BYDAuthListeners();

    public static class DiLink3_0_BYDAuthListeners extends DiLinkCallbackHolders<IDiLinkBYDAuthListener, BYDAuthListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BYDAuthListener createInnerCallback(final IDiLinkBYDAuthListener iDiLinkBYDAuthListener) {
            return new BYDAuthListener() { // from class: com.byd.dilink30.app.DiLinkBYDAuthManager.DiLink3_0_BYDAuthListeners.1
                @Keep
                public void callback(int i, int i2, String str) {
                    iDiLinkBYDAuthListener.authCallback(i, i2, str);
                }
            };
        }
    }

    @SuppressLint({"WrongConstant"})
    public DiLinkBYDAuthManager(Context context) {
        this.f1185a = (BYDAuthManager) context.getSystemService(Constants.BYD_AUTH_SERVICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, BYDAuthListener bYDAuthListener) {
        this.f1185a.requestAuth(i, bYDAuthListener);
    }

    @Override // com.byd.dilink.app.IDiLinkBYDAuthManager
    public void requestAuth(final int i, @NonNull IDiLinkBYDAuthListener iDiLinkBYDAuthListener) {
        this.f1186b.addCallbackIgnoreRemoteException(iDiLinkBYDAuthListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.app.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1189a.a(i, (BYDAuthListener) obj);
            }
        });
    }
}
