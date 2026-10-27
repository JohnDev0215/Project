package jViVV55i.jViVV55i.jViVV55i.Iji.jViVV55i;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.view.inputmethod.IDiLinkIInputMethodListener;
import com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkInputMethodManager.class, singleton = true)
public class jViVV55i implements IDiLinkInputMethodManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final InputMethodManager f3052a;

    public jViVV55i(Context context) {
        this.f3052a = (InputMethodManager) context.getSystemService("input_method");
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public void addListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 addListener 方法！");
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public int getCurTokenDisplayId() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 getCurTokenDisplayId 方法！");
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public boolean getInputShownState() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 getInputShownState 方法！");
    }

    @Override // com.byd.dilink.view.inputmethod.IDiLinkInputMethodManager
    public void removeListener(IDiLinkIInputMethodListener iDiLinkIInputMethodListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 removeListener 方法！");
    }
}
