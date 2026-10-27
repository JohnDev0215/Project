package p;

import com.spotify.base.java.logging.Logger;
import com.spotify.shoreline.esperanto.java.impl.NativeShorelineLoggerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class hd2 implements gd2, sb2 {
    public final NativeShorelineLoggerImpl a;

    public hd2(u11 u11Var) {
        this.a = NativeShorelineLoggerImpl.Companion.create(new com.spotify.shoreline.shorelineloggeresperanto.a(u11Var));
    }

    @Override // p.sb2
    public final void shutdown() {
        Logger.a("ShorelineCoreService shutdown called", new Object[0]);
        this.a.destroy();
        Logger.a("ShorelineCoreService shutdown completed", new Object[0]);
    }
}
