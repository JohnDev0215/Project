package com.byd.overseaappstore.module.ability.data.generator;

import androidx.exifinterface.media.ExifInterface;
import com.byd.overseaappstore.module.ability.R$drawable;
import com.byd.overseaappstore.module.ability.R$string;
import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.rsp.BannerRsp;
import com.byd.overseaappstore.module.ability.data.rsp.FeatureTopicGroupRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareThemRsp;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u0004\"\u000e\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00050\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\bJT\u0010\u0003\u001a\u00020\u00042\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00060\b2\u0018\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\n0\u00060\b2\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\n0\u00060\bJ\u001a\u0010\u0010\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\bJ\u001a\u0010\u0011\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\bJ\u001a\u0010\u0012\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\b¨\u0006\u0013"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;", "", "()V", "generateErrorUiState", "Lcom/byd/overseaappstore/module/ability/data/uistate/ErrorEmptyUiState;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/byd/overseaappstore/module/ability/data/NetRspDataBody;", "sourceDataBody", "Lcom/byd/overseaappstore/module/ability/data/SourceDataBody;", "bannerBody", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/BannerRsp;", "softwareThemBody", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareThemRsp;", "topicGroupBody", "Lcom/byd/overseaappstore/module/ability/data/rsp/FeatureTopicGroupRsp;", "generateErrorUiStateForDownloadManager", "generateErrorUiStateForUninstallManager", "generateErrorUiStateForUpdateManager", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ErrorUiStateGenerator {
    @NotNull
    public final <T extends NetRspDataBody<T>> ErrorEmptyUiState generateErrorUiState(@NotNull SourceDataBody<T> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        int ret = sourceDataBody.getRet();
        if (ret != 0) {
            return ret != 1 ? ret != 2 ? ret != 3 ? new ErrorEmptyUiState(false, 0, 0, 6, null) : new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.unknown_error) : new ErrorEmptyUiState(true, R$drawable.ic_net_error, R$string.net_no_connect) : new ErrorEmptyUiState(true, R$drawable.ic_net_error, R$string.net_connect_out_time);
        }
        if (sourceDataBody.getApiData() == null) {
            return new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.no_data);
        }
        boolean z = sourceDataBody.getApiData() instanceof List;
        T apiData = sourceDataBody.getApiData();
        return z ? ((List) apiData).isEmpty() ? new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.no_data) : new ErrorEmptyUiState(false, 0, 0, 6, null) : apiData.getData() == null ? new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.no_data) : new ErrorEmptyUiState(false, 0, 0, 6, null);
    }

    @NotNull
    public final ErrorEmptyUiState generateErrorUiState(@NotNull SourceDataBody<NetRspDataBody<List<BannerRsp>>> bannerBody, @NotNull SourceDataBody<NetRspDataBody<List<SoftwareThemRsp>>> softwareThemBody, @NotNull SourceDataBody<NetRspDataBody<List<FeatureTopicGroupRsp>>> topicGroupBody) {
        Intrinsics.checkNotNullParameter(bannerBody, "bannerBody");
        Intrinsics.checkNotNullParameter(softwareThemBody, "softwareThemBody");
        Intrinsics.checkNotNullParameter(topicGroupBody, "topicGroupBody");
        int ret = bannerBody.getRet();
        int ret2 = softwareThemBody.getRet();
        int ret3 = topicGroupBody.getRet();
        if (ret == 0) {
            NetRspDataBody<List<BannerRsp>> apiData = bannerBody.getApiData();
            List<BannerRsp> data = apiData != null ? apiData.getData() : null;
            if (!(data == null || data.isEmpty())) {
                return new ErrorEmptyUiState(false, 0, 0, 6, null);
            }
        }
        if (ret2 == 0) {
            NetRspDataBody<List<SoftwareThemRsp>> apiData2 = softwareThemBody.getApiData();
            List<SoftwareThemRsp> data2 = apiData2 != null ? apiData2.getData() : null;
            if (!(data2 == null || data2.isEmpty())) {
                return new ErrorEmptyUiState(false, 0, 0, 6, null);
            }
        }
        if (ret3 == 0) {
            NetRspDataBody<List<FeatureTopicGroupRsp>> apiData3 = topicGroupBody.getApiData();
            List<FeatureTopicGroupRsp> data3 = apiData3 != null ? apiData3.getData() : null;
            if (!(data3 == null || data3.isEmpty())) {
                return new ErrorEmptyUiState(false, 0, 0, 6, null);
            }
        }
        return (ret == 1 || ret2 == 1 || ret3 == 1) ? new ErrorEmptyUiState(true, R$drawable.ic_net_error, R$string.net_connect_out_time) : (ret == 2 || ret2 == 2 || ret3 == 2) ? new ErrorEmptyUiState(true, R$drawable.ic_net_error, R$string.net_no_connect) : (ret == 3 || ret2 == 3 || ret3 == 3) ? new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.unknown_error) : new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.no_data);
    }

    @NotNull
    public final <T> ErrorEmptyUiState generateErrorUiStateForDownloadManager(@NotNull SourceDataBody<T> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        if (sourceDataBody.getRet() != 0) {
            return new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.download_list_null);
        }
        boolean z = false;
        if (sourceDataBody.getApiData() == null) {
            return new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.download_list_null);
        }
        if ((sourceDataBody.getApiData() instanceof List) && (!((Collection) sourceDataBody.getApiData()).isEmpty())) {
            z = true;
        }
        return z ? new ErrorEmptyUiState(false, 0, 0, 6, null) : new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.download_list_null);
    }

    @NotNull
    public final <T> ErrorEmptyUiState generateErrorUiStateForUninstallManager(@NotNull SourceDataBody<T> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        if (sourceDataBody.getRet() != 0) {
            return new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.uninstall_list_null);
        }
        boolean z = false;
        if (sourceDataBody.getApiData() == null) {
            return new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.uninstall_list_null);
        }
        if ((sourceDataBody.getApiData() instanceof List) && (!((Collection) sourceDataBody.getApiData()).isEmpty())) {
            z = true;
        }
        return z ? new ErrorEmptyUiState(false, 0, 0, 6, null) : new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.uninstall_list_null);
    }

    @NotNull
    public final <T> ErrorEmptyUiState generateErrorUiStateForUpdateManager(@NotNull SourceDataBody<T> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        if (sourceDataBody.getRet() != 0) {
            return new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.update_list_null);
        }
        boolean z = false;
        if (sourceDataBody.getApiData() == null) {
            return new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.update_list_null);
        }
        if ((sourceDataBody.getApiData() instanceof List) && (!((Collection) sourceDataBody.getApiData()).isEmpty())) {
            z = true;
        }
        return z ? new ErrorEmptyUiState(false, 0, 0, 6, null) : new ErrorEmptyUiState(true, R$drawable.ic_no_data, R$string.update_list_null);
    }
}
