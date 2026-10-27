package defpackage;

import defpackage.fl1;
import java.util.function.Consumer;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabbedSheetDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class j63 implements fl1.a {
    public final Tab a;
    public final Consumer<Tab> b;
    public final String c;

    public j63(Tab tab, d63 d63Var, String str) {
        this.a = tab;
        this.b = d63Var;
        this.c = str;
    }
}
