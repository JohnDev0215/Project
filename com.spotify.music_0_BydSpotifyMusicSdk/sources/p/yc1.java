package p;

import com.google.common.base.Optional;
import com.spotify.player.model.command.options.LoggingParams;

/* JADX INFO: loaded from: classes.dex */
public final class yc1 {
    public final u6 a;

    public yc1(u6 u6Var) {
        v03.j(u6Var, "clock");
        this.a = u6Var;
    }

    public final LoggingParams a(Optional optional) {
        Optional optionalI = optional.i(new xc1(this));
        LoggingParams loggingParams = LoggingParams.a;
        v03.i(loggingParams, "EMPTY");
        LoggingParams.Builder builderF = loggingParams.f();
        this.a.getClass();
        return (LoggingParams) optionalI.g(builderF.b(Long.valueOf(System.currentTimeMillis())).a());
    }
}
