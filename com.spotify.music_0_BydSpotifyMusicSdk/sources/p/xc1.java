package p;

import com.spotify.player.model.command.options.LoggingParams;

/* JADX INFO: loaded from: classes.dex */
public final class xc1 implements oz0 {
    public final /* synthetic */ yc1 c;

    public xc1(yc1 yc1Var) {
        this.c = yc1Var;
    }

    @Override // p.oz0
    public final Object apply(Object obj) {
        LoggingParams loggingParams = (LoggingParams) obj;
        v03.i(loggingParams, "it");
        yc1 yc1Var = this.c;
        yc1Var.getClass();
        LoggingParams.Builder builderF = loggingParams.f();
        yc1Var.a.getClass();
        return builderF.b(Long.valueOf(System.currentTimeMillis())).a();
    }
}
