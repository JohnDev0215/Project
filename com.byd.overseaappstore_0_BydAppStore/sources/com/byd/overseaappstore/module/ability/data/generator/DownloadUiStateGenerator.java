package com.byd.overseaappstore.module.ability.data.generator;

import android.content.Context;
import com.byd.overseaappstore.module.ability.R$string;
import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.rsp.DownloadUrlRefreshRsp;
import com.byd.overseaappstore.module.ability.data.uistate.DownloadUrlUiState;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\u00020\b2\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b0\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/DownloadUiStateGenerator;", "", "context", "Landroid/content/Context;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "(Landroid/content/Context;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;)V", "generateDownloadUrlUiState", "Lcom/byd/overseaappstore/module/ability/data/uistate/DownloadUrlUiState;", "sourceDataBody", "Lcom/byd/overseaappstore/module/ability/data/SourceDataBody;", "Lcom/byd/overseaappstore/module/ability/data/NetRspDataBody;", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/DownloadUrlRefreshRsp;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadUiStateGenerator {

    @NotNull
    private final Context context;

    @NotNull
    private final DataGenerator dataGenerator;

    public DownloadUiStateGenerator(@NotNull Context context, @NotNull DataGenerator dataGenerator) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        this.context = context;
        this.dataGenerator = dataGenerator;
    }

    @NotNull
    public final DownloadUrlUiState generateDownloadUrlUiState(@NotNull SourceDataBody<NetRspDataBody<List<DownloadUrlRefreshRsp>>> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        int ret = sourceDataBody.getRet();
        boolean z = true;
        if (ret != 0) {
            return ret != 1 ? ret != 2 ? ret != 3 ? new DownloadUrlUiState(null, vm.a.e(this.context, R$string.refresh_url_error), true, 1, null) : new DownloadUrlUiState(null, vm.a.e(this.context, R$string.unknown_error), true, 1, null) : new DownloadUrlUiState(null, vm.a.e(this.context, R$string.net_no_connect_toast), true, 1, null) : new DownloadUrlUiState(null, vm.a.e(this.context, R$string.net_connect_out_time_toast), true, 1, null);
        }
        NetRspDataBody<List<DownloadUrlRefreshRsp>> apiData = sourceDataBody.getApiData();
        if (apiData == null) {
            return new DownloadUrlUiState(null, vm.a.e(this.context, R$string.no_data), true, 1, null);
        }
        int code = apiData.getCode();
        List<DownloadUrlRefreshRsp> data = apiData.getData();
        if (code != 0) {
            return new DownloadUrlUiState(null, vm.a.e(this.context, R$string.refresh_url_error), true, 1, null);
        }
        if (data != null && !data.isEmpty()) {
            z = false;
        }
        return z ? new DownloadUrlUiState(null, vm.a.e(this.context, R$string.refresh_url_error), true, 1, null) : new DownloadUrlUiState(this.dataGenerator.parseToDownloadUrlInfoList(data), null, false, 2, null);
    }
}
