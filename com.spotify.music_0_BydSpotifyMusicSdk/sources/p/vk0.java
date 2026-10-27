package p;

import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class vk0 implements vl0 {
    public final /* synthetic */ int a;

    public /* synthetic */ vk0(int i) {
        this.a = i;
    }

    @Override // p.xy1
    public final Object get() {
        int i = 1;
        int i2 = this.a;
        switch (i2) {
            case 0:
                return new xx2(i, Executors.newSingleThreadExecutor());
            case 1:
                return "com.google.android.datatransport.events";
            case 2:
                return Integer.valueOf(n72.l);
            case 3:
                bi biVar = bi.f;
                if (biVar != null) {
                    return biVar;
                }
                throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
            case 4:
                switch (i2) {
                    case 4:
                        return new lr2(1);
                    default:
                        return new lr2(0);
                }
            default:
                switch (i2) {
                    case 4:
                        return new lr2(1);
                    default:
                        return new lr2(0);
                }
        }
    }
}
