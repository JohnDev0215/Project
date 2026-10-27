package defpackage;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import java.util.function.BooleanSupplier;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.toolbar.top.ToggleTabStackButton;

/* JADX INFO: compiled from: ToggleTabStackButtonCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ib3 {
    public final ll a;
    public final Context b;
    public final ToggleTabStackButton c;
    public final pk3 d;
    public final BooleanSupplier e;
    public final qs1<Boolean> f;
    public final Callback<Boolean> g;
    public final i20 h;
    public l61 i;
    public hb3 j;
    public boolean k;

    public ib3(AppCompatActivity appCompatActivity, ToggleTabStackButton toggleTabStackButton, pk3 pk3Var, cp cpVar, qs1 qs1Var, qs1 qs1Var2, fc3 fc3Var, z2 z2Var) {
        ll llVar = new ll();
        this.a = llVar;
        this.b = appCompatActivity;
        this.c = toggleTabStackButton;
        this.d = pk3Var;
        this.e = cpVar;
        this.f = qs1Var;
        this.g = fc3Var;
        qs1Var2.h(llVar.b(new dr(18, this)));
        this.h = new i20(z2Var, new gb3(this), null);
    }
}
