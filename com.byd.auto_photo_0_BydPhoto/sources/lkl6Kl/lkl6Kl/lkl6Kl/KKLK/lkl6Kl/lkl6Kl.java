package lkl6Kl.lkl6Kl.lkl6Kl.KKLK.lkl6Kl;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.view.inputmethod.IDiLinkIInputMethodListener;
import com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkInputMethodManager.class, singleton = true)
public class lkl6Kl implements IDiLinkInputMethodManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final InputMethodManager f3105a;

    public lkl6Kl(Context context) {
        this.f3105a = (InputMethodManager) context.getSystemService("input_method");
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public void addListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 addListener！");
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public int getCurTokenDisplayId() {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 getCurTokenDisplayId！");
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public boolean getInputShownState() {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 getInputShownState！");
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public void removeListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 removeListener！");
    }
}
