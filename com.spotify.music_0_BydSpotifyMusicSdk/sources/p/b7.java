package p;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class b7 implements sk2 {
    public final zk1 a;
    public final ExecutorService b;
    public volatile pl2 c;
    public final HashSet d;

    public b7() {
        zk1 zk1Var = new zk1(new u6());
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        v03.i(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        this.a = zk1Var;
        this.b = executorServiceNewSingleThreadExecutor;
        this.d = new HashSet(50);
    }

    public final c7 a(String str) {
        v03.j(str, "category");
        return new c7(str, this.a);
    }

    public final void b(vk2 vk2Var) {
        this.b.execute(new z13(this, vk2Var, 2));
    }
}
