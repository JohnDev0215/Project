package p;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class h02 {
    public final i02 a;
    public final wc1 b;
    public final LinkedHashMap c;

    public h02(k02 k02Var, wc1 wc1Var) {
        v03.j(wc1Var, "logger");
        this.a = k02Var;
        this.b = wc1Var;
        this.c = new LinkedHashMap();
        io.reactivex.rxjava3.disposables.a aVar = new io.reactivex.rxjava3.disposables.a();
        io.reactivex.rxjava3.subjects.c cVar = new io.reactivex.rxjava3.subjects.c();
        int i = 2;
        Single map = Single.fromCallable(new h30(i, k02Var)).map(j02.c);
        v03.i(map, "override fun loadReport(…}.toMap()\n        }\n    }");
        Disposable disposableSubscribe = map.subscribeOn(io.reactivex.rxjava3.schedulers.e.c).subscribe(new g02(this, 0), new g02(this, 1));
        v03.i(disposableSubscribe, "private fun setCurrentSt…    }\n            )\n    }");
        aVar.c(disposableSubscribe);
        Disposable disposableSubscribe2 = cVar.concatMap(new ux1(9, this)).subscribe(new g02(this, i), new g02(this, 3));
        v03.i(disposableSubscribe2, "private fun startListeni…        }\n        )\n    }");
        aVar.c(disposableSubscribe2);
    }
}
