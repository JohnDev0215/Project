package com.byd.dilink_rse.app;

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

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkBYDAuthManager.class, singleton = true)
public class DiLinkBYDAuthManager implements IDiLinkBYDAuthManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final BYDAuthManager f2686a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink_rse_BYDAuthListeners f2687b = new DiLink_rse_BYDAuthListeners();

    public static class DiLink_rse_BYDAuthListeners extends DiLinkCallbackHolders<IDiLinkBYDAuthListener, BYDAuthListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BYDAuthListener createInnerCallback(final IDiLinkBYDAuthListener iDiLinkBYDAuthListener) {
            return new BYDAuthListener() { // from class: com.byd.dilink_rse.app.DiLinkBYDAuthManager.DiLink_rse_BYDAuthListeners.1
                @Keep
                public void callback(int i, int i2, String str) {
                    iDiLinkBYDAuthListener.authCallback(i, i2, str);
                }
            };
        }
    }

    @SuppressLint({"WrongConstant"})
    public DiLinkBYDAuthManager(Context context) {
        this.f2686a = (BYDAuthManager) context.getSystemService(Constants.BYD_AUTH_SERVICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, BYDAuthListener bYDAuthListener) {
        this.f2686a.requestAuth(i, bYDAuthListener);
    }

    @Override // com.byd.dilink.app.IDiLinkBYDAuthManager
    public void requestAuth(final int i, @NonNull IDiLinkBYDAuthListener iDiLinkBYDAuthListener) {
        this.f2687b.addCallbackIgnoreRemoteException(iDiLinkBYDAuthListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rse.app.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2690a.a(i, (BYDAuthListener) obj);
            }
        });
    }
}
