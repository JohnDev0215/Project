package spi;

import com.byd.overseaappstore.module.ability.data.ApiDataBody;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public interface aq {
    @NotNull
    ApiDataBody<List<SoftwareDeviceInstalledRsp>> a();

    @NotNull
    ApiDataBody<List<SoftwareDeviceInstalledRsp>> b();
}
