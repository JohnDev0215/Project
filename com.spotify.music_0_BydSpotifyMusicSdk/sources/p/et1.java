package p;

import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class et1 extends ThreadLocal {
    public final /* synthetic */ int a;

    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        switch (this.a) {
            case 0:
                return new char[1024];
            case 1:
                return new Exception();
            default:
                return new Random();
        }
    }
}
