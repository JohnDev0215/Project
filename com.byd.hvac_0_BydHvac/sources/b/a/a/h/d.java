package b.a.a.h;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class d extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static d f241a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<a> f242b;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        boolean a(Message message);
    }

    public static synchronized d b() {
        if (f241a == null) {
            f241a = new d();
        }
        return f241a;
    }

    public void a(a aVar) {
        if (this.f242b == null) {
            this.f242b = new ArrayList();
        }
        if (this.f242b.contains(aVar)) {
            return;
        }
        this.f242b.add(aVar);
    }

    public void c(a aVar) {
        List<a> list = this.f242b;
        if (list != null) {
            list.remove(aVar);
        }
    }

    @Override // android.os.Handler
    public void handleMessage(@NonNull Message message) {
        try {
            List<a> list = this.f242b;
            int size = list != null ? list.size() : 0;
            for (int i = 0; i < size; i++) {
                if (this.f242b.get(i).a(message)) {
                    return;
                }
            }
        } catch (Throwable th) {
            BydLogHelper.logD(" , handleMessage = " + message.what + ListUtils.DEFAULT_JOIN_SEPARATOR + Log.getStackTraceString(th));
        }
    }
}
