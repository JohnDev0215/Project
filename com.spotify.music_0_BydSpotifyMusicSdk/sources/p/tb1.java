package p;

import io.reactivex.rxjava3.core.Single;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public interface tb1 {
    default Single a(if2 if2Var, String str, Locale locale, ub1 ub1Var, tx1 tx1Var) {
        return Single.error(new UnsupportedOperationException());
    }

    default Single b(if2 if2Var, String str, Integer num, Locale locale, tx1 tx1Var) {
        return Single.error(new UnsupportedOperationException());
    }

    default Single c(Locale locale, ub1 ub1Var, tx1 tx1Var) {
        return Single.error(new UnsupportedOperationException());
    }

    default dg1 d() {
        jh1 jh1Var = new jh1();
        jh1Var.a = "empty-id";
        return new dg1(jh1Var, 1);
    }
}
