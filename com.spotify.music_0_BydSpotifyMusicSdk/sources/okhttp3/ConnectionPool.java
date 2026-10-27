package okhttp3;

import java.util.concurrent.TimeUnit;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealConnectionPool;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionPool {
    public final RealConnectionPool a;

    public ConnectionPool() {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        v03.j(timeUnit, "timeUnit");
        this.a = new RealConnectionPool(TaskRunner.i, timeUnit);
    }
}
