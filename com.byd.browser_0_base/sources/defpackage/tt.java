package defpackage;

import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: ClearDataDialogResultRecorder.java */
/* JADX INFO: loaded from: classes.dex */
public final class tt {
    public final p61<SharedPreferencesManager> a;
    public final sp b;
    public final jg3 c;

    public tt(p61<SharedPreferencesManager> p61Var, sp spVar, jg3 jg3Var) {
        this.a = p61Var;
        this.b = spVar;
        this.c = jg3Var;
    }

    public final void a(String str, boolean z) {
        p61<SharedPreferencesManager> p61Var = this.a;
        int iD = p61Var.get().d(0, str);
        for (int i = 0; i < iD; i++) {
            this.c.getClass();
            oj2.O(z ? "TrustedWebActivity.ClearDataDialogOnUninstallAccepted" : "TrustedWebActivity.ClearDataDialogOnClearAppDataAccepted", false);
        }
        p61Var.get().removeKey(str);
    }
}
