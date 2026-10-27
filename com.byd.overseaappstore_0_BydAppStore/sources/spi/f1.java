package spi;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.HttpException;

/* JADX INFO: loaded from: classes.dex */
public abstract class f1 implements i7 {

    @NotNull
    public final a2 a;

    public f1(@NotNull a2 bizSrc) {
        Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
        this.a = bizSrc;
    }

    public final void b(@NotNull Throwable throwable, @NotNull SoftwareOptParam softwareOptParam) {
        int i;
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        String simpleName = getClass().getSimpleName();
        String message = throwable.getMessage();
        String simpleName2 = throwable.getClass().getSimpleName();
        String packageName = softwareOptParam.getPackageName();
        int versionCode = softwareOptParam.getVersionCode();
        long totalSize = softwareOptParam.getTotalSize();
        a2 a2Var = this.a;
        StringBuilder sbL = d.l("dTask download ", simpleName, " onCatch error msg:", message, " class:");
        sbL.append(simpleName2);
        sbL.append(" pkn:");
        sbL.append(packageName);
        sbL.append(" version:");
        d.s(sbL, versionCode, " totalSize:", totalSize);
        ct.b(e.i(sbL, " bizSrc:", a2Var), new Object[0]);
        if (throwable instanceof SocketException ? true : throwable instanceof SocketTimeoutException ? true : throwable instanceof UnknownHostException ? true : throwable instanceof ConnectException ? true : throwable instanceof HttpException ? true : throwable instanceof SSLException) {
            i = 3;
        } else {
            cancel();
            i = 2;
        }
        a(i);
    }
}
