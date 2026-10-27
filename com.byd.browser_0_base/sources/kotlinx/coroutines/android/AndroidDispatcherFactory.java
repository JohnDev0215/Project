package kotlinx.coroutines.android;

import android.os.Looper;
import defpackage.cb1;
import defpackage.db1;
import defpackage.wt0;
import defpackage.yt0;
import java.util.List;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: compiled from: HandlerDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidDispatcherFactory implements db1 {
    @Override // defpackage.db1
    public cb1 createDispatcher(List<? extends db1> list) {
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null) {
            return new wt0(yt0.a(mainLooper));
        }
        throw new IllegalStateException("The main looper is not available");
    }

    @Override // defpackage.db1
    public int getLoadPriority() {
        return 1073741823;
    }

    @Override // defpackage.db1
    public String hintOnError() {
        return "For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used";
    }
}
