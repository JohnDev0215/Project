package defpackage;

import android.util.Pair;
import defpackage.xf0;
import java.text.Collator;
import java.util.Comparator;

/* JADX INFO: compiled from: AutofillProfileBridge.java */
/* JADX INFO: loaded from: classes.dex */
public final class eb implements Comparator<xf0.a> {
    public final /* synthetic */ Collator e;

    public eb(Collator collator) {
        this.e = collator;
    }

    @Override // java.util.Comparator
    public final int compare(xf0.a aVar, xf0.a aVar2) {
        xf0.a aVar3 = aVar;
        xf0.a aVar4 = aVar2;
        int iCompare = this.e.compare((CharSequence) ((Pair) aVar3).second, (CharSequence) ((Pair) aVar4).second);
        return iCompare == 0 ? ((String) ((Pair) aVar3).first).compareTo((String) ((Pair) aVar4).first) : iCompare;
    }
}
