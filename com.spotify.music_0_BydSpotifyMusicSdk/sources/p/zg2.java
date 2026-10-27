package p;

import com.spotify.base.java.logging.Logger;
import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class zg2 {
    public final LinkedHashSet a = new LinkedHashSet();

    public final String a() {
        List listN0 = ou.n0(this.a);
        StringBuilder sb = new StringBuilder();
        sb.append("#Active subscriptions: " + listN0.size());
        ArrayList arrayList = new ArrayList(lu.W(listN0));
        Iterator it = listN0.iterator();
        while (it.hasNext()) {
            arrayList.add("\n" + ((mn2) it.next()).c.a);
        }
        sb.append(arrayList);
        String string = sb.toString();
        v03.i(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final Observable b(String str, Observable observable) {
        v03.j(str, "tag");
        v03.j(observable, "source");
        Observable observableCreate = Observable.create(new yg2(this, str, observable));
        v03.i(observableCreate, "fun track(tag: String, s…        )\n        }\n    }");
        return observableCreate;
    }

    public final synchronized List c() {
        try {
            List listN0 = ou.n0(this.a);
            if (listN0.isEmpty()) {
                return EmptyList.c;
            }
            this.a.size();
            Logger.b("Found active subscribers:\n".concat(a()), new Object[0]);
            ArrayList arrayList = new ArrayList();
            for (Object obj : listN0) {
                mn2 mn2Var = (mn2) obj;
                Emitter emitter = mn2Var.a;
                if (!(emitter instanceof ObservableEmitter)) {
                    throw new IllegalArgumentException("Must be either ObservableEmitter or FlowableEmitter");
                }
                if (!((ObservableEmitter) emitter).isDisposed()) {
                    mn2Var.b.dispose();
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList(lu.W(arrayList));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((mn2) it.next()).c);
            }
            this.a.clear();
            this.a.size();
            return arrayList2;
        } catch (Throwable th) {
            throw th;
        }
    }
}
