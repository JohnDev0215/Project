package p;

import android.os.Looper;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class w6 implements ce1 {
    @Override // p.ce1
    public String a() {
        return "For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used";
    }

    @Override // p.ce1
    public be1 b(List list) {
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null) {
            return new kotlinx.coroutines.android.a(k11.a(mainLooper));
        }
        throw new IllegalStateException("The main looper is not available");
    }

    @Override // p.ce1
    public int c() {
        return 1073741823;
    }
}
