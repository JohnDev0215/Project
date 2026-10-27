package com.byd.overseaappstore.module.ability.data.generator;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.byd.overseaappstore.module.ability.R$drawable;
import com.byd.overseaappstore.module.ability.R$string;
import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData;
import com.byd.overseaappstore.module.ability.data.rsp.HotSearchRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareIntermediaryRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareSearchRsp;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.FooterHeaderUiState;
import com.byd.overseaappstore.module.ability.data.uistate.HotSearchItemUiState;
import com.byd.overseaappstore.module.ability.data.uistate.SearchResultItemUiState;
import com.byd.overseaappstore.module.ability.data.uistate.ToastUiState;
import com.byd.overseaappstore.module.ability.data.upper.HotSearchItem;
import com.byd.overseaappstore.module.ability.data.upper.SearchResult;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.jetbrains.annotations.NotNull;
import spi.o5;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ&\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\r2\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00110\u00100\u000fJ \u0010\u0012\u001a\u00020\u00132\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00110\u00100\u000fJ\u001a\u0010\u0015\u001a\u00020\u0016\"\u0004\b\u0000\u0010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fJ&\u0010\u0017\u001a\u00020\u0016\"\u0004\b\u0000\u0010\r2\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00110\u00100\u000fJ \u0010\u0018\u001a\u00020\u00192\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00110\u00100\u000fJ&\u0010\u001b\u001a\u00020\u001c\"\u0004\b\u0000\u0010\r2\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00110\u00100\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/SearchUiStateGenerator;", "", "context", "Landroid/content/Context;", "softwareRepository", "Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "softwareStateDataParserGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareStateDataParserGenerator;", "(Landroid/content/Context;Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareStateDataParserGenerator;)V", "generateErrorUiState", "Lcom/byd/overseaappstore/module/ability/data/uistate/ErrorEmptyUiState;", ExifInterface.GPS_DIRECTION_TRUE, "sourceDataBody", "Lcom/byd/overseaappstore/module/ability/data/SourceDataBody;", "Lcom/byd/overseaappstore/module/ability/data/NetRspDataBody;", "", "generateHotSearchUiState", "Lcom/byd/overseaappstore/module/ability/data/uistate/HotSearchItemUiState;", "Lcom/byd/overseaappstore/module/ability/data/rsp/HotSearchRsp;", "generatePullDownUiState", "Lcom/byd/overseaappstore/module/ability/data/uistate/FooterHeaderUiState;", "generatePullUpUiState", "generateSearchResultItemUiState", "Lcom/byd/overseaappstore/module/ability/data/uistate/SearchResultItemUiState;", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareSearchRsp;", "generateToastUiState", "Lcom/byd/overseaappstore/module/ability/data/uistate/ToastUiState;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchUiStateGenerator {

    @NotNull
    private final Context context;

    @NotNull
    private final DataGenerator dataGenerator;

    @NotNull
    private final SoftwareRepository softwareRepository;

    @NotNull
    private final SoftwareStateDataParserGenerator softwareStateDataParserGenerator;

    public SearchUiStateGenerator(@NotNull Context context, @NotNull SoftwareRepository softwareRepository, @NotNull DataGenerator dataGenerator, @NotNull SoftwareStateDataParserGenerator softwareStateDataParserGenerator) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(softwareRepository, "softwareRepository");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(softwareStateDataParserGenerator, "softwareStateDataParserGenerator");
        this.context = context;
        this.softwareRepository = softwareRepository;
        this.dataGenerator = dataGenerator;
        this.softwareStateDataParserGenerator = softwareStateDataParserGenerator;
    }

    @NotNull
    public final <T> ErrorEmptyUiState generateErrorUiState(@NotNull SourceDataBody<NetRspDataBody<List<T>>> sourceDataBody) {
        List<T> data;
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        int ret = sourceDataBody.getRet();
        if (ret != 0) {
            return ret != 1 ? ret != 2 ? ret != 3 ? new ErrorEmptyUiState(false, 0, 0, 6, null) : new ErrorEmptyUiState(true, R$drawable.ic_no_search_results, R$string.unknown_error) : new ErrorEmptyUiState(true, R$drawable.ic_net_error, R$string.net_no_connect) : new ErrorEmptyUiState(true, R$drawable.ic_net_error, R$string.net_connect_out_time);
        }
        NetRspDataBody<List<T>> apiData = sourceDataBody.getApiData();
        Boolean boolValueOf = null;
        if ((apiData != null ? apiData.getData() : null) == null) {
            return new ErrorEmptyUiState(true, R$drawable.ic_no_search_results, R$string.search_no_result);
        }
        NetRspDataBody<List<T>> apiData2 = sourceDataBody.getApiData();
        if (apiData2 != null && (data = apiData2.getData()) != null) {
            boolValueOf = Boolean.valueOf(data.isEmpty());
        }
        return Intrinsics.areEqual(boolValueOf, Boolean.TRUE) ? new ErrorEmptyUiState(true, R$drawable.ic_no_search_results, R$string.search_no_result) : new ErrorEmptyUiState(false, 0, 0, 6, null);
    }

    @NotNull
    public final HotSearchItemUiState generateHotSearchUiState(@NotNull SourceDataBody<NetRspDataBody<List<HotSearchRsp>>> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        NetRspDataBody<List<HotSearchRsp>> apiData = sourceDataBody.getApiData();
        List<HotSearchRsp> data = apiData != null ? apiData.getData() : null;
        if (sourceDataBody.getRet() != 0) {
            return new HotSearchItemUiState(null, false, 1, null);
        }
        if (data == null || data.isEmpty()) {
            return new HotSearchItemUiState(null, false, 1, null);
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.b(data));
        for (HotSearchRsp hotSearchRsp : data) {
            Integer id = hotSearchRsp.getId();
            int iIntValue = id != null ? id.intValue() : 0;
            String name = hotSearchRsp.getName();
            if (name == null) {
                name = "";
            }
            arrayList.add(new HotSearchItem(iIntValue, name));
        }
        return new HotSearchItemUiState(arrayList, true);
    }

    @NotNull
    public final <T> FooterHeaderUiState generatePullDownUiState(@NotNull SourceDataBody<T> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        int ret = sourceDataBody.getRet();
        return ret != 0 ? ret != 1 ? ret != 2 ? ret != 3 ? new FooterHeaderUiState(true, 1) : new FooterHeaderUiState(true, 2) : new FooterHeaderUiState(true, 4) : new FooterHeaderUiState(true, 3) : new FooterHeaderUiState(true, 1);
    }

    @NotNull
    public final <T> FooterHeaderUiState generatePullUpUiState(@NotNull SourceDataBody<NetRspDataBody<List<T>>> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        int ret = sourceDataBody.getRet();
        return ret != 0 ? ret != 1 ? ret != 2 ? ret != 3 ? new FooterHeaderUiState(true, 17) : new FooterHeaderUiState(true, 18) : new FooterHeaderUiState(true, 20) : new FooterHeaderUiState(true, 19) : new FooterHeaderUiState(true, 17);
    }

    @NotNull
    public final SearchResultItemUiState generateSearchResultItemUiState(@NotNull SourceDataBody<NetRspDataBody<List<SoftwareSearchRsp>>> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        NetRspDataBody<List<SoftwareSearchRsp>> apiData = sourceDataBody.getApiData();
        List<SoftwareSearchRsp> data = apiData != null ? apiData.getData() : null;
        if (sourceDataBody.getRet() != 0) {
            return new SearchResultItemUiState(false, null, 2, null);
        }
        if (data == null || data.isEmpty()) {
            return new SearchResultItemUiState(false, null, 2, null);
        }
        List<SoftwareDeviceInstalledRsp> list = (List) BuildersKt__BuildersKt.runBlocking$default(null, new SearchUiStateGenerator$generateSearchResultItemUiState$deviceInstalledList$1(this, null), 1, null);
        ArrayList arrayList = new ArrayList(CollectionsKt.b(data));
        Iterator<T> it = data.iterator();
        while (it.hasNext()) {
            SoftwareIntermediaryRsp toSoftwareIntermediaryRsp = this.dataGenerator.parseToSoftwareIntermediaryRsp((SoftwareSearchRsp) it.next());
            SoftwareInterceptorRetData softwareInterceptorRetDataSoftwareStateDataComprehensiveParse = this.softwareStateDataParserGenerator.softwareStateDataComprehensiveParse(toSoftwareIntermediaryRsp, list);
            arrayList.add(new Software(toSoftwareIntermediaryRsp.getId(), toSoftwareIntermediaryRsp.getName(), toSoftwareIntermediaryRsp.getIcon(), toSoftwareIntermediaryRsp.getPackageName(), toSoftwareIntermediaryRsp.getClassifyId(), this.dataGenerator.generateAPPClassifyName(Integer.valueOf(toSoftwareIntermediaryRsp.getClassifyId()), this.context), toSoftwareIntermediaryRsp.getTotalSize(), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getSoftwareStateData().getPercentCount(), o5.a.a(Long.valueOf(toSoftwareIntermediaryRsp.getTotalSize())), toSoftwareIntermediaryRsp.getVersionCode(), toSoftwareIntermediaryRsp.getVersionTip(), this.dataGenerator.generateVersionTip(toSoftwareIntermediaryRsp.getVersionTip(), this.context), toSoftwareIntermediaryRsp.getIntroduction(), toSoftwareIntermediaryRsp.getDownloadUrl(), toSoftwareIntermediaryRsp.getSignature(), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getSoftwareStateData().getState(), toSoftwareIntermediaryRsp.getSourceType(), this.dataGenerator.parseToSoftwareExtParamList(toSoftwareIntermediaryRsp.getPackageName(), toSoftwareIntermediaryRsp.getExtra()), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getDtFixInfo()));
        }
        return new SearchResultItemUiState(true, new SearchResult(arrayList, data.size(), vm.a.f(this.context, R$string.search_init_count, Integer.valueOf(data.size()))));
    }

    @NotNull
    public final <T> ToastUiState generateToastUiState(@NotNull SourceDataBody<NetRspDataBody<List<T>>> sourceDataBody) {
        List<T> data;
        ToastUiState toastUiState;
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        int ret = sourceDataBody.getRet();
        if (ret == 0) {
            NetRspDataBody<List<T>> apiData = sourceDataBody.getApiData();
            return Intrinsics.areEqual((apiData == null || (data = apiData.getData()) == null) ? null : Boolean.valueOf(data.isEmpty()), Boolean.TRUE) ? new ToastUiState(false, 0, 2, null) : new ToastUiState(false, 0, 3, null);
        }
        if (ret == 1) {
            toastUiState = new ToastUiState(true, R$string.net_connect_out_time_toast);
        } else if (ret == 2) {
            toastUiState = new ToastUiState(true, R$string.net_no_connect_toast);
        } else {
            if (ret != 3) {
                return new ToastUiState(false, 0, 2, null);
            }
            toastUiState = new ToastUiState(true, R$string.unknown_error);
        }
        return toastUiState;
    }
}
