package com.byd.dilink50.view.inputmethod;

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
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkInputMethodManager.class, singleton = true)
public class DiLinkInputMethodManager implements IDiLinkInputMethodManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final InputMethodManager f1489a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink5_0_InputMethodListeners f1490b = new DiLink5_0_InputMethodListeners();

    public static class DiLink5_0_InputMethodListeners extends DiLinkCallbackHolders<IDiLinkIInputMethodListener, IInputMethodListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IInputMethodListener createInnerCallback(final IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
            return new IInputMethodListener.Stub() { // from class: com.byd.dilink50.view.inputmethod.DiLinkInputMethodManager.DiLink5_0_InputMethodListeners.1
                @Keep
                public void onShownStateChange(boolean z, int i) {
                    iDiLinkIInputMethodListener.onShownStateChange(z, i);
                }
            };
        }
    }

    public DiLinkInputMethodManager(Context context) {
        this.f1489a = (InputMethodManager) context.getSystemService("input_method");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IInputMethodListener iInputMethodListener) {
        this.f1489a.addListener(iInputMethodListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IInputMethodListener iInputMethodListener) {
        this.f1489a.removeListener(iInputMethodListener);
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public void addListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
        this.f1490b.addCallbackIgnoreRemoteException(iDiLinkIInputMethodListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.view.inputmethod.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1493a.a((IInputMethodListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public int getCurTokenDisplayId() {
        return this.f1489a.getCurTokenDisplayId();
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public boolean getInputShownState() {
        return this.f1489a.getInputShownState();
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public void removeListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
        this.f1490b.removeCallbackIgnoreRemoteException(iDiLinkIInputMethodListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.view.inputmethod.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1494a.b((IInputMethodListener) obj);
            }
        });
    }
}
