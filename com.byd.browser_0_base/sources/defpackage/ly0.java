package defpackage;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.g63;
import org.chromium.chrome.browser.tasks.tab_management.p1;
import org.chromium.components.browser_ui.settings.SettingsLauncher;

/* JADX INFO: compiled from: IncognitoReauthCoordinatorFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class ly0 {
    public final Context a;
    public final y13 b;
    public final bi1 c;
    public final ny0 d;
    public final SettingsLauncher e;
    public final boolean f;
    public final xy0 g;
    public p1 h;
    public final z51 i;
    public final Intent j;

    public ly0(AppCompatActivity appCompatActivity, y13 y13Var, bi1 bi1Var, ny0 ny0Var, n3 n3Var, g63.c cVar, h61 h61Var, Intent intent, boolean z) {
        this.a = appCompatActivity;
        this.b = y13Var;
        this.c = bi1Var;
        this.d = ny0Var;
        this.e = n3Var;
        this.g = cVar;
        this.i = h61Var;
        this.j = intent;
        this.f = z;
    }

    public final Runnable a() {
        return this.f ? new qm0(7, this) : new zc0(10, this);
    }
}
