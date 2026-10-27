package spi;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class x1 implements Executor {
    public static final /* synthetic */ x1 b = new x1(0);
    public static final /* synthetic */ x1 c = new x1(1);
    public static final /* synthetic */ x1 d = new x1(2);
    public final /* synthetic */ int a;

    public /* synthetic */ x1(int i) {
        this.a = i;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.a) {
            case 0:
                runnable.run();
                break;
            case 1:
                runnable.run();
                break;
            default:
                runnable.run();
                break;
        }
    }
}
