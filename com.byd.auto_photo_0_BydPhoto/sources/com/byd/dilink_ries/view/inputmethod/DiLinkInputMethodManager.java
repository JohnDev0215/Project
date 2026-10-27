package com.byd.dilink_ries.view.inputmethod;

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
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkInputMethodManager.class, singleton = true)
public class DiLinkInputMethodManager implements IDiLinkInputMethodManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final InputMethodManager f2674a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink_ries_InputMethodListeners f2675b = new DiLink_ries_InputMethodListeners();

    public static class DiLink_ries_InputMethodListeners extends DiLinkCallbackHolders<IDiLinkIInputMethodListener, IInputMethodListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IInputMethodListener createInnerCallback(final IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
            return new IInputMethodListener.Stub() { // from class: com.byd.dilink_ries.view.inputmethod.DiLinkInputMethodManager.DiLink_ries_InputMethodListeners.1
                @Keep
                public void onShownStateChange(boolean z, int i) {
                    iDiLinkIInputMethodListener.onShownStateChange(z, i);
                }
            };
        }
    }

    public DiLinkInputMethodManager(Context context) {
        this.f2674a = (InputMethodManager) context.getSystemService("input_method");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IInputMethodListener iInputMethodListener) {
        this.f2674a.removeListener(iInputMethodListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(IInputMethodListener iInputMethodListener) {
        this.f2674a.addListener(iInputMethodListener);
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public void addListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
        this.f2675b.addCallbackIgnoreRemoteException(iDiLinkIInputMethodListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.view.inputmethod.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2678a.d((IInputMethodListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public int getCurTokenDisplayId() {
        return this.f2674a.getCurTokenDisplayId();
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public boolean getInputShownState() {
        return this.f2674a.getInputShownState();
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public void removeListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
        this.f2675b.removeCallbackIgnoreRemoteException(iDiLinkIInputMethodListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.view.inputmethod.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2679a.a((IInputMethodListener) obj);
            }
        });
    }
}
