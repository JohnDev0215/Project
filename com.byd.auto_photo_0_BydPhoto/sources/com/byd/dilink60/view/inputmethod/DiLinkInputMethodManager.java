package com.byd.dilink60.view.inputmethod;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.Keep;
import com.android.internal.view.IInputMethodListener;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.view.inputmethod.IDiLinkIInputMethodListener;
import com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkInputMethodManager.class, singleton = true)
public class DiLinkInputMethodManager implements IDiLinkInputMethodManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final InputMethodManager f2130a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink6_0_MirrorListeners f2131b = new DiLink6_0_MirrorListeners();

    public static class DiLink6_0_MirrorListeners extends DiLinkCallbackHolders<IDiLinkIInputMethodListener, IInputMethodListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IInputMethodListener createInnerCallback(final IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
            return new IInputMethodListener.Stub() { // from class: com.byd.dilink60.view.inputmethod.DiLinkInputMethodManager.DiLink6_0_MirrorListeners.1
                @Keep
                public void onShownStateChange(boolean z, int i) {
                    iDiLinkIInputMethodListener.onShownStateChange(z, i);
                }
            };
        }
    }

    public DiLinkInputMethodManager(Context context) {
        this.f2130a = (InputMethodManager) context.getSystemService("input_method");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IInputMethodListener iInputMethodListener) {
        this.f2130a.addListener(iInputMethodListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IInputMethodListener iInputMethodListener) {
        this.f2130a.removeListener(iInputMethodListener);
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public void addListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
        this.f2131b.addCallbackIgnoreRemoteException(iDiLinkIInputMethodListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.view.inputmethod.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2134a.a((IInputMethodListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public int getCurTokenDisplayId() {
        return this.f2130a.getCurTokenDisplayId();
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public boolean getInputShownState() {
        return this.f2130a.getInputShownState();
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public void removeListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
        this.f2131b.removeCallbackIgnoreRemoteException(iDiLinkIInputMethodListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.view.inputmethod.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2135a.b((IInputMethodListener) obj);
            }
        });
    }
}
