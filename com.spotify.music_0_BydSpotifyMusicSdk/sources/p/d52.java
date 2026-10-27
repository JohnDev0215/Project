package p;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class d52 {
    public final ht1 a;
    public Call.Factory b;
    public HttpUrl c;
    public final ArrayList d;
    public final ArrayList e;
    public final Executor f;
    public final boolean g;

    public d52() {
        ht1 ht1Var = ht1.c;
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.a = ht1Var;
    }

    public final void a(a62 a62Var) {
        this.e.add(a62Var);
    }

    public final void b(g00 g00Var) {
        Objects.requireNonNull(g00Var, "factory == null");
        this.d.add(g00Var);
    }

    public final void c(String str) {
        Objects.requireNonNull(str, "baseUrl == null");
        HttpUrl.k.getClass();
        d(HttpUrl.Companion.c(str));
    }

    public final void d(HttpUrl httpUrl) {
        Objects.requireNonNull(httpUrl, "baseUrl == null");
        if ("".equals(httpUrl.f.get(r0.size() - 1))) {
            this.c = httpUrl;
        } else {
            throw new IllegalArgumentException("baseUrl must end in /: " + httpUrl);
        }
    }

    public final e52 e() {
        if (this.c == null) {
            throw new IllegalStateException("Base URL required.");
        }
        Call.Factory okHttpClient = this.b;
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        Call.Factory factory = okHttpClient;
        Executor executorA = this.f;
        ht1 ht1Var = this.a;
        if (executorA == null) {
            executorA = ht1Var.a();
        }
        Executor executor = executorA;
        ArrayList arrayList = new ArrayList(this.e);
        ht1Var.getClass();
        l60 l60Var = new l60(executor);
        boolean z = ht1Var.a;
        arrayList.addAll(z ? Arrays.asList(ev.a, l60Var) : Collections.singletonList(l60Var));
        ArrayList arrayList2 = this.d;
        ArrayList arrayList3 = new ArrayList(arrayList2.size() + 1 + (z ? 1 : 0));
        to toVar = new to();
        toVar.a = true;
        arrayList3.add(toVar);
        arrayList3.addAll(arrayList2);
        arrayList3.addAll(z ? Collections.singletonList(yq1.a) : Collections.emptyList());
        return new e52(factory, this.c, Collections.unmodifiableList(arrayList3), Collections.unmodifiableList(arrayList), executor, this.g);
    }

    public d52(e52 e52Var) {
        this.d = new ArrayList();
        this.e = new ArrayList();
        ht1 ht1Var = ht1.c;
        this.a = ht1Var;
        this.b = e52Var.b;
        this.c = e52Var.c;
        List list = e52Var.d;
        int size = list.size() - (ht1Var.a ? 1 : 0);
        for (int i = 1; i < size; i++) {
            this.d.add((g00) list.get(i));
        }
        List list2 = e52Var.e;
        int size2 = list2.size() - (this.a.a ? 2 : 1);
        for (int i2 = 0; i2 < size2; i2++) {
            this.e.add((kp) list2.get(i2));
        }
        this.f = e52Var.f;
        this.g = e52Var.g;
    }
}
