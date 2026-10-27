package p;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class k52 {
    public final boolean a;
    public final Object b;

    public /* synthetic */ k52(Object obj, boolean z) {
        this.b = obj;
        this.a = z;
    }

    public k52() {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        v03.i(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        this.b = new wk0(executorServiceNewSingleThreadExecutor);
    }

    public k52(String str, boolean z) {
        this.a = z;
        this.b = str;
    }
}
