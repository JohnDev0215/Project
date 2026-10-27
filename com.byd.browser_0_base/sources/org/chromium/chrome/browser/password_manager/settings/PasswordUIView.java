package org.chromium.chrome.browser.password_manager.settings;

import J.N;
import defpackage.ox1;
import defpackage.px1;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public final class PasswordUIView {
    public long a = N.Mx3ZU1Lr(this);
    public final ox1 b;

    public PasswordUIView(px1 px1Var) {
        this.b = px1Var;
    }

    @CalledByNative
    public static SavedPasswordEntry createSavedPasswordEntry(String str, String str2, String str3) {
        return new SavedPasswordEntry(str, str2, str3);
    }

    @CalledByNative
    public final void passwordExceptionListAvailable(int i) {
        this.b.z(i);
    }

    @CalledByNative
    public final void passwordListAvailable(int i) {
        this.b.x(i);
    }
}
