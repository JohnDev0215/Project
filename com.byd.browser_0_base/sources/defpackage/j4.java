package defpackage;

import java.util.Comparator;
import java.util.Locale;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j4 implements Comparator {
    public final /* synthetic */ int e;

    public /* synthetic */ j4(int i) {
        this.e = i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        String strB;
        String strB2;
        switch (this.e) {
            case 0:
                s10 s10Var = (s10) obj;
                s10 s10Var2 = (s10) obj2;
                if (s10Var.e) {
                    strB = s10Var.f.toLowerCase(Locale.ENGLISH);
                } else {
                    strB = bk3.b(s10Var.d, false);
                }
                if (s10Var2.e) {
                    strB2 = s10Var2.f.toLowerCase(Locale.ENGLISH);
                } else {
                    strB2 = bk3.b(s10Var2.d, false);
                }
                return strB.compareTo(strB2);
            case 1:
                return ((f51) obj).b.compareTo(((f51) obj2).b);
            case 2:
                long[] jArr = d43.p;
                return Integer.compare(((v33) obj).b, ((v33) obj2).b);
            default:
                return ((Toast) obj).c - ((Toast) obj2).c;
        }
    }
}
