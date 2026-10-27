package com.byd.dilink_DiR.view.inputmethod;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.Keep;
import com.android.internal.view.IInputMethodListener;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.view.inputmethod.IDiLinkIInputMethodListener;
import com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkInputMethodManager.class, singleton = true)
public class DiLinkInputMethodManager implements IDiLinkInputMethodManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final InputMethodManager f2329a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLinkDiR_MirrorListeners f2330b = new DiLinkDiR_MirrorListeners();

    public static class DiLinkDiR_MirrorListeners extends DiLinkCallbackHolders<IDiLinkIInputMethodListener, IInputMethodListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IInputMethodListener createInnerCallback(final IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
            return new IInputMethodListener.Stub() { // from class: com.byd.dilink_DiR.view.inputmethod.DiLinkInputMethodManager.DiLinkDiR_MirrorListeners.1
                @Keep
                public void onShownStateChange(boolean z, int i) {
                    iDiLinkIInputMethodListener.onShownStateChange(z, i);
                }
            };
        }
    }

    public DiLinkInputMethodManager(Context context) {
        this.f2329a = (InputMethodManager) context.getSystemService("input_method");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(IInputMethodListener iInputMethodListener) {
        this.f2329a.addListener(iInputMethodListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(IInputMethodListener iInputMethodListener) {
        this.f2329a.removeListener(iInputMethodListener);
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public void addListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
        this.f2330b.addCallbackIgnoreRemoteException(iDiLinkIInputMethodListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.view.inputmethod.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2334a.c((IInputMethodListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public int getCurTokenDisplayId() {
        return this.f2329a.getCurTokenDisplayId();
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public boolean getInputShownState() {
        return this.f2329a.getInputShownState();
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public void removeListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
        this.f2330b.removeCallbackIgnoreRemoteException(iDiLinkIInputMethodListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.view.inputmethod.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2333a.d((IInputMethodListener) obj);
            }
        });
    }
}
