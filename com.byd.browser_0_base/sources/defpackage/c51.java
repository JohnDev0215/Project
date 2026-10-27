package defpackage;

import java.util.Comparator;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: LanguageAskPrompt.java */
/* JADX INFO: loaded from: classes.dex */
public final class c51 implements Comparator<f51> {
    public final /* synthetic */ LinkedHashSet e;
    public final /* synthetic */ e51 f;

    public c51(e51 e51Var, LinkedHashSet linkedHashSet) {
        this.f = e51Var;
        this.e = linkedHashSet;
    }

    @Override // java.util.Comparator
    public final int compare(f51 f51Var, f51 f51Var2) {
        f51 f51Var3 = f51Var;
        f51 f51Var4 = f51Var2;
        String str = f51Var3.a;
        LinkedHashSet linkedHashSet = this.e;
        boolean zContains = linkedHashSet.contains(str);
        int i = -1;
        e51 e51Var = this.f;
        int i2 = zContains ? -2 : e51Var.g.contains(f51Var3.a) ? -1 : 0;
        if (linkedHashSet.contains(f51Var4.a)) {
            i = -2;
        } else if (!e51Var.g.contains(f51Var4.a)) {
            i = 0;
        }
        return i2 - i;
    }
}
