package defpackage;

import android.util.Pair;
import defpackage.xf0;
import java.text.Collator;
import java.util.Comparator;

/* JADX INFO: compiled from: AutofillProfileBridge.java */
/* JADX INFO: loaded from: classes.dex */
public final class fb implements Comparator<xf0.a> {
    public final /* synthetic */ Collator e;

    public fb(Collator collator) {
        this.e = collator;
    }

    @Override // java.util.Comparator
    public final int compare(xf0.a aVar, xf0.a aVar2) {
        return this.e.compare((CharSequence) ((Pair) aVar).second, (CharSequence) ((Pair) aVar2).second);
    }
}
