package com.byd.overseaappstore.module.ability.data.generator;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.byd.overseaappstore.module.ability.R$string;
import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDtFixInfo;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData;
import com.byd.overseaappstore.module.ability.data.rsp.BannerAppRsp;
import com.byd.overseaappstore.module.ability.data.rsp.BannerRsp;
import com.byd.overseaappstore.module.ability.data.rsp.FeatureTopicGroupRsp;
import com.byd.overseaappstore.module.ability.data.rsp.FeatureTopicSoftwareRsp;
import com.byd.overseaappstore.module.ability.data.rsp.ImagePathRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDetailRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareIntermediaryRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareThemRsp;
import com.byd.overseaappstore.module.ability.data.rsp.TopicGroupTitleRsp;
import com.byd.overseaappstore.module.ability.data.uistate.BannerUiState;
import com.byd.overseaappstore.module.ability.data.uistate.DownloadItemUiState;
import com.byd.overseaappstore.module.ability.data.uistate.FeatureThemGroupUiState;
import com.byd.overseaappstore.module.ability.data.uistate.FooterHeaderUiState;
import com.byd.overseaappstore.module.ability.data.uistate.InstalledItemUiState;
import com.byd.overseaappstore.module.ability.data.uistate.SearchResultItemUiState;
import com.byd.overseaappstore.module.ability.data.uistate.SoftwareDetailUiState;
import com.byd.overseaappstore.module.ability.data.uistate.SoftwareThemeUiState;
import com.byd.overseaappstore.module.ability.data.uistate.ToastUiState;
import com.byd.overseaappstore.module.ability.data.uistate.TopicDetailUiState;
import com.byd.overseaappstore.module.ability.data.uistate.TopicTitleUiState;
import com.byd.overseaappstore.module.ability.data.uistate.UpgradeUiState;
import com.byd.overseaappstore.module.ability.data.upper.Banner;
import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import com.byd.overseaappstore.module.ability.data.upper.ImagePath;
import com.byd.overseaappstore.module.ability.data.upper.SearchResult;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareDetail;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareInstalled;
import com.byd.overseaappstore.module.ability.data.upper.TopicGroupTitle;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareExtParam;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.d;
import spi.hi;
import spi.o5;
import spi.oa;
import spi.pq;
import spi.qq;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B3\b\u0007\u0012\b\b\u0001\u0010K\u001a\u00020J\u0012\u0006\u0010N\u001a\u00020M\u0012\u0006\u0010W\u001a\u00020V\u0012\u0006\u0010Q\u001a\u00020P\u0012\u0006\u0010T\u001a\u00020S¢\u0006\u0004\bX\u0010YJ2\u0010\n\u001a\u00020\t2\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004J \u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\tJ \u0010\u0013\u001a\u00020\u00122\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00040\u00030\u0002J\u001a\u0010\u0016\u001a\u00020\u00152\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00030\u0002J\u0016\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0015J2\u0010\u001c\u001a\u00020\u001b2\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00040\u00030\u00022\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004J\u001e\u0010\u001c\u001a\u00020\u001b2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u00042\u0006\u0010\u001f\u001a\u00020\u001bJ \u0010 \u001a\u0004\u0018\u00010\u001b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u001bJ \u0010#\u001a\u00020\"2\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00040\u00030\u0002J\u000e\u0010&\u001a\u00020%2\u0006\u0010$\u001a\u00020\u000bJ\u001a\u0010)\u001a\u00020(2\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0\u00040\u0002J\u001e\u0010)\u001a\u00020(2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010\u00042\u0006\u0010,\u001a\u00020(J\u001a\u0010/\u001a\u00020.2\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u00040\u0002J\u001c\u0010/\u001a\u00020.2\f\u00101\u001a\b\u0012\u0004\u0012\u0002000\u00042\u0006\u00102\u001a\u00020.J\u001a\u00104\u001a\u0002032\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u00040\u0002J\u001a\u00106\u001a\u0002052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u00040\u0002J\u001c\u00106\u001a\u0002052\f\u00101\u001a\b\u0012\u0004\u0012\u0002000\u00042\u0006\u00107\u001a\u000205J\u001a\u00108\u001a\u0002032\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u00040\u0002J\u0016\u0010=\u001a\u00020;2\u0006\u0010:\u001a\u0002092\u0006\u0010<\u001a\u00020;J\u001a\u0010@\u001a\u00020?\"\u0004\b\u0000\u0010>2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002JB\u0010@\u001a\u00020?\"\u0004\b\u0000\u0010>\"\u0004\b\u0001\u0010A\"\u0004\b\u0002\u0010B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00010\u00022\f\u0010E\u001a\b\u0012\u0004\u0012\u00028\u00020\u0002JB\u0010H\u001a\u00020G\"\u0004\b\u0000\u0010>\"\u0004\b\u0001\u0010A\"\u0004\b\u0002\u0010F2\f\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00010\u00022\f\u0010E\u001a\b\u0012\u0004\u0012\u00028\u00020\u0002J\u001a\u0010I\u001a\u00020G\"\u0004\b\u0000\u0010>2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002R\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010LR\u0014\u0010N\u001a\u00020M8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010OR\u0014\u0010Q\u001a\u00020P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010RR\u0014\u0010T\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010U¨\u0006Z"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;", "", "Lcom/byd/overseaappstore/module/ability/data/SourceDataBody;", "Lcom/byd/overseaappstore/module/ability/data/NetRspDataBody;", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareThemRsp;", "sourceDataBody", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;", "deviceInstalledSoftwareList", "Lcom/byd/overseaappstore/module/ability/data/uistate/SoftwareThemeUiState;", "generateSoftWareThemUiState", "", "pkn", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareStateData;", "softwareStateData", "curSoftwareThemeUiState", "generateSoftWareThemUiStateForPT", "Lcom/byd/overseaappstore/module/ability/data/rsp/BannerRsp;", "Lcom/byd/overseaappstore/module/ability/data/uistate/BannerUiState;", "generateBannerUiState", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDetailRsp;", "Lcom/byd/overseaappstore/module/ability/data/uistate/SoftwareDetailUiState;", "generateSoftwareDetailUiState", "Lcom/byd/overseaappstore/module/ability/data/upper/SoftwareDetail;", "softwareDetail", "curCoftwareDetailUiState", "Lcom/byd/overseaappstore/module/ability/data/rsp/FeatureTopicGroupRsp;", "Lcom/byd/overseaappstore/module/ability/data/uistate/FeatureThemGroupUiState;", "generateFeatureThemGroupUiState", "Lcom/byd/overseaappstore/module/ability/data/upper/FeatureThemTopicGroup;", "featureThemTopicGroupList", "curFeatureThemGroupUiState", "generateFeatureThemGroupUiStateForPT", "Lcom/byd/overseaappstore/module/ability/data/rsp/FeatureTopicSoftwareRsp;", "Lcom/byd/overseaappstore/module/ability/data/uistate/TopicDetailUiState;", "generateTopicDetailUiState", "topicTitle", "Lcom/byd/overseaappstore/module/ability/data/uistate/TopicTitleUiState;", "generateTopicTitleUiState", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareIntermediaryRsp;", "Lcom/byd/overseaappstore/module/ability/data/uistate/InstalledItemUiState;", "generateInstalledItemUiState", "Lcom/byd/overseaappstore/module/ability/data/upper/SoftwareInstalled;", "softwareInstalledList", "curInstalledItemUiState", "Lspi/qq;", "Lcom/byd/overseaappstore/module/ability/data/uistate/DownloadItemUiState;", "generateDownloadItemUiState", "Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "softwareList", "curDownloadItemUiState", "", "generateStartAllPauseAllUiState", "Lcom/byd/overseaappstore/module/ability/data/uistate/UpgradeUiState;", "generateUpgradeSoftwareUiState", "curUpgradeUiState", "generateUpgradeSoftwareExistUiState", "Lcom/byd/overseaappstore/module/ability/data/upper/SearchResult;", "searchResult", "Lcom/byd/overseaappstore/module/ability/data/uistate/SearchResultItemUiState;", "curCoSearchResultUiState", "generateSearchResultUiState", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/byd/overseaappstore/module/ability/data/uistate/ToastUiState;", "generateToastUiState", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "sourceDataBody1", "sourceDataBody2", "sourceDataBody3", ExifInterface.LONGITUDE_EAST, "Lcom/byd/overseaappstore/module/ability/data/uistate/FooterHeaderUiState;", "generatePullDownUiState", "generatePullUpUiState", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;", "softwareRepository", "Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareStateDataParserGenerator;", "softwareStateDataParserGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareStateDataParserGenerator;", "Lspi/oa;", "executorSoftwareStateMgr", "<init>", "(Landroid/content/Context;Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;Lspi/oa;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareStateDataParserGenerator;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SoftwareUiStateGenerator {

    @NotNull
    private final Context context;

    @NotNull
    private final DataGenerator dataGenerator;

    @NotNull
    private final oa executorSoftwareStateMgr;

    @NotNull
    private final SoftwareRepository softwareRepository;

    @NotNull
    private final SoftwareStateDataParserGenerator softwareStateDataParserGenerator;

    public SoftwareUiStateGenerator(@NotNull Context context, @NotNull SoftwareRepository softwareRepository, @NotNull oa executorSoftwareStateMgr, @NotNull DataGenerator dataGenerator, @NotNull SoftwareStateDataParserGenerator softwareStateDataParserGenerator) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(softwareRepository, "softwareRepository");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(softwareStateDataParserGenerator, "softwareStateDataParserGenerator");
        this.context = context;
        this.softwareRepository = softwareRepository;
        this.executorSoftwareStateMgr = executorSoftwareStateMgr;
        this.dataGenerator = dataGenerator;
        this.softwareStateDataParserGenerator = softwareStateDataParserGenerator;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FeatureThemGroupUiState generateFeatureThemGroupUiState$default(SoftwareUiStateGenerator softwareUiStateGenerator, SourceDataBody sourceDataBody, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = null;
        }
        return softwareUiStateGenerator.generateFeatureThemGroupUiState((SourceDataBody<NetRspDataBody<List<FeatureTopicGroupRsp>>>) sourceDataBody, (List<SoftwareDeviceInstalledRsp>) list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SoftwareThemeUiState generateSoftWareThemUiState$default(SoftwareUiStateGenerator softwareUiStateGenerator, SourceDataBody sourceDataBody, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = null;
        }
        return softwareUiStateGenerator.generateSoftWareThemUiState(sourceDataBody, list);
    }

    @NotNull
    public final BannerUiState generateBannerUiState(@NotNull SourceDataBody<NetRspDataBody<List<BannerRsp>>> sourceDataBody) {
        BannerUiState bannerUiState;
        List<BannerRsp> data;
        BannerAppRsp bannerAppRsp;
        Integer id;
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        if (sourceDataBody.getRet() == 0) {
            NetRspDataBody<List<BannerRsp>> apiData = sourceDataBody.getApiData();
            ArrayList arrayList = null;
            List<BannerRsp> data2 = apiData != null ? apiData.getData() : null;
            if (data2 == null || data2.isEmpty()) {
                bannerUiState = new BannerUiState(true, null, false, 2, null);
            } else {
                NetRspDataBody<List<BannerRsp>> apiData2 = sourceDataBody.getApiData();
                if (apiData2 != null && (data = apiData2.getData()) != null) {
                    arrayList = new ArrayList(CollectionsKt.b(data));
                    for (BannerRsp bannerRsp : data) {
                        List<BannerAppRsp> appInfos = bannerRsp.getAppInfos();
                        int iIntValue = (appInfos == null || (bannerAppRsp = appInfos.get(0)) == null || (id = bannerAppRsp.getId()) == null) ? 0 : id.intValue();
                        String pictureUrl = bannerRsp.getPictureUrl();
                        if (pictureUrl == null) {
                            pictureUrl = "";
                        }
                        arrayList.add(new Banner(iIntValue, pictureUrl));
                    }
                }
                bannerUiState = new BannerUiState(true, arrayList, true);
            }
        } else {
            bannerUiState = new BannerUiState(false, null, false, 2, null);
        }
        ct.a(hi.e("repo-vm generateBannerUiState visible:", bannerUiState.getVisible()), new Object[0]);
        return bannerUiState;
    }

    @NotNull
    public final DownloadItemUiState generateDownloadItemUiState(@NotNull SourceDataBody<List<qq>> sourceDataBody) {
        SoftwareStateData value;
        SoftwareStateData value2;
        SoftwareUiStateGenerator softwareUiStateGenerator = this;
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        int i = 0;
        if (sourceDataBody.getRet() != 0) {
            return new DownloadItemUiState(null, false, 1, null);
        }
        List<qq> apiData = sourceDataBody.getApiData();
        if (apiData == null || apiData.isEmpty()) {
            return new DownloadItemUiState(null, false, 1, null);
        }
        HashMap<String, MutableStateFlow<SoftwareStateData>> map = softwareUiStateGenerator.executorSoftwareStateMgr.b;
        List<qq> apiData2 = sourceDataBody.getApiData();
        ArrayList<qq> arrayList = new ArrayList();
        for (Object obj : apiData2) {
            if (((qq) obj).a.k != 6) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.b(arrayList));
        for (qq qqVar : arrayList) {
            pq pqVar = qqVar.a;
            List<SoftwareExtParam> toSoftwareExtParamList = softwareUiStateGenerator.dataGenerator.parseToSoftwareExtParamList(qqVar.b);
            int i2 = pqVar.b;
            String str = pqVar.c;
            String str2 = pqVar.g;
            String str3 = pqVar.a;
            int i3 = pqVar.h;
            String strGenerateAPPClassifyName = softwareUiStateGenerator.dataGenerator.generateAPPClassifyName(Integer.valueOf(i3), softwareUiStateGenerator.context);
            long j = pqVar.e;
            MutableStateFlow<SoftwareStateData> mutableStateFlow = map.get(pqVar.a);
            int percentCount = (mutableStateFlow == null || (value2 = mutableStateFlow.getValue()) == null) ? i : value2.getPercentCount();
            MutableStateFlow<SoftwareStateData> mutableStateFlow2 = map.get(pqVar.a);
            int state = (mutableStateFlow2 == null || (value = mutableStateFlow2.getValue()) == null) ? i : value.getState();
            String strA = o5.a.a(Long.valueOf(pqVar.e));
            String str4 = pqVar.f;
            String str5 = pqVar.l;
            int i4 = pqVar.j;
            arrayList2.add(new Software(i2, str, str2, str3, i3, strGenerateAPPClassifyName, j, percentCount, strA, i4, null, null, null, str4, str5, state, pqVar.m, toSoftwareExtParamList, new SoftwareDtFixInfo(str4, pqVar.e, i4), 7168, null));
            softwareUiStateGenerator = this;
            map = map;
            i = 0;
        }
        return new DownloadItemUiState(arrayList2, true);
    }

    @NotNull
    public final DownloadItemUiState generateDownloadItemUiState(@NotNull List<? extends Software> softwareList, @NotNull DownloadItemUiState curDownloadItemUiState) {
        Intrinsics.checkNotNullParameter(softwareList, "softwareList");
        Intrinsics.checkNotNullParameter(curDownloadItemUiState, "curDownloadItemUiState");
        return new DownloadItemUiState(softwareList, curDownloadItemUiState.getVisible());
    }

    @NotNull
    public final FeatureThemGroupUiState generateFeatureThemGroupUiState(@NotNull SourceDataBody<NetRspDataBody<List<FeatureTopicGroupRsp>>> sourceDataBody, @Nullable List<SoftwareDeviceInstalledRsp> deviceInstalledSoftwareList) {
        FeatureThemGroupUiState featureThemGroupUiState;
        ArrayList arrayList;
        List<FeatureTopicGroupRsp> data;
        List<SoftwareDeviceInstalledRsp> list;
        ArrayList arrayList2;
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        if (sourceDataBody.getRet() == 0) {
            NetRspDataBody<List<FeatureTopicGroupRsp>> apiData = sourceDataBody.getApiData();
            ArrayList arrayList3 = null;
            List<FeatureTopicGroupRsp> data2 = apiData != null ? apiData.getData() : null;
            if (data2 == null || data2.isEmpty()) {
                featureThemGroupUiState = new FeatureThemGroupUiState(true, null, false, 2, null);
            } else {
                List<SoftwareDeviceInstalledRsp> list2 = deviceInstalledSoftwareList == null || deviceInstalledSoftwareList.isEmpty() ? (List) BuildersKt__BuildersKt.runBlocking$default(null, new SoftwareUiStateGenerator$generateFeatureThemGroupUiState$deviceInstalledList$1(this, null), 1, null) : deviceInstalledSoftwareList;
                NetRspDataBody<List<FeatureTopicGroupRsp>> apiData2 = sourceDataBody.getApiData();
                if (apiData2 == null || (data = apiData2.getData()) == null) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(CollectionsKt.b(data));
                    for (FeatureTopicGroupRsp featureTopicGroupRsp : data) {
                        Integer id = featureTopicGroupRsp.getId();
                        int iIntValue = id != null ? id.intValue() : 0;
                        String title = featureTopicGroupRsp.getTitle();
                        String str = title == null ? "" : title;
                        String pictureUrl = featureTopicGroupRsp.getPictureUrl();
                        String str2 = pictureUrl == null ? "" : pictureUrl;
                        List<TopicGroupTitleRsp> topicTitleList = featureTopicGroupRsp.getTopicTitleList();
                        if (topicTitleList != null) {
                            arrayList3 = new ArrayList(CollectionsKt.b(topicTitleList));
                            for (TopicGroupTitleRsp topicGroupTitleRsp : topicTitleList) {
                                arrayList3.add(new TopicGroupTitle(topicGroupTitleRsp.getTip(), topicGroupTitleRsp.getLanguage()));
                            }
                        }
                        ArrayList arrayList4 = arrayList3;
                        List<FeatureTopicSoftwareRsp> appInfoList = featureTopicGroupRsp.getAppInfoList();
                        if (appInfoList != null) {
                            ArrayList arrayList5 = new ArrayList(CollectionsKt.b(appInfoList));
                            Iterator<T> it = appInfoList.iterator();
                            while (it.hasNext()) {
                                SoftwareIntermediaryRsp toSoftwareIntermediaryRsp = this.dataGenerator.parseToSoftwareIntermediaryRsp((FeatureTopicSoftwareRsp) it.next());
                                SoftwareInterceptorRetData softwareInterceptorRetDataSoftwareStateDataComprehensiveParse = this.softwareStateDataParserGenerator.softwareStateDataComprehensiveParse(toSoftwareIntermediaryRsp, list2);
                                arrayList5.add(new Software(toSoftwareIntermediaryRsp.getId(), toSoftwareIntermediaryRsp.getName(), toSoftwareIntermediaryRsp.getIcon(), toSoftwareIntermediaryRsp.getPackageName(), toSoftwareIntermediaryRsp.getClassifyId(), this.dataGenerator.generateAPPClassifyName(Integer.valueOf(toSoftwareIntermediaryRsp.getClassifyId()), this.context), toSoftwareIntermediaryRsp.getTotalSize(), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getSoftwareStateData().getPercentCount(), o5.a.a(Long.valueOf(toSoftwareIntermediaryRsp.getTotalSize())), toSoftwareIntermediaryRsp.getVersionCode(), toSoftwareIntermediaryRsp.getVersionTip(), this.dataGenerator.generateVersionTip(toSoftwareIntermediaryRsp.getVersionTip(), this.context), null, toSoftwareIntermediaryRsp.getDownloadUrl(), toSoftwareIntermediaryRsp.getSignature(), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getSoftwareStateData().getState(), toSoftwareIntermediaryRsp.getSourceType(), this.dataGenerator.parseToSoftwareExtParamList(toSoftwareIntermediaryRsp.getPackageName(), toSoftwareIntermediaryRsp.getExtra()), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getDtFixInfo(), 4096, null));
                                list2 = list2;
                            }
                            list = list2;
                            arrayList2 = arrayList5;
                        } else {
                            list = list2;
                            arrayList2 = null;
                        }
                        arrayList.add(new FeatureThemTopicGroup(iIntValue, str, str2, arrayList4, arrayList2));
                        arrayList3 = null;
                        list2 = list;
                    }
                }
                featureThemGroupUiState = new FeatureThemGroupUiState(true, arrayList, true);
            }
        } else {
            featureThemGroupUiState = new FeatureThemGroupUiState(false, null, false, 2, null);
        }
        ct.a(hi.e("repo-vm generateFeatureThemGroupUiState visible:", featureThemGroupUiState.getVisible()), new Object[0]);
        return featureThemGroupUiState;
    }

    @NotNull
    public final FeatureThemGroupUiState generateFeatureThemGroupUiState(@Nullable List<FeatureThemTopicGroup> featureThemTopicGroupList, @NotNull FeatureThemGroupUiState curFeatureThemGroupUiState) {
        Intrinsics.checkNotNullParameter(curFeatureThemGroupUiState, "curFeatureThemGroupUiState");
        Boolean ret = curFeatureThemGroupUiState.getRet();
        return new FeatureThemGroupUiState(ret != null ? ret.booleanValue() : false, featureThemTopicGroupList, curFeatureThemGroupUiState.getVisible());
    }

    @Nullable
    public final FeatureThemGroupUiState generateFeatureThemGroupUiStateForPT(@NotNull String pkn, @NotNull SoftwareStateData softwareStateData, @NotNull FeatureThemGroupUiState curFeatureThemGroupUiState) {
        Intrinsics.checkNotNullParameter(pkn, "pkn");
        Intrinsics.checkNotNullParameter(softwareStateData, "softwareStateData");
        Intrinsics.checkNotNullParameter(curFeatureThemGroupUiState, "curFeatureThemGroupUiState");
        return null;
    }

    @NotNull
    public final InstalledItemUiState generateInstalledItemUiState(@NotNull SourceDataBody<List<SoftwareIntermediaryRsp>> sourceDataBody) {
        SoftwareUiStateGenerator softwareUiStateGenerator = this;
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        if (sourceDataBody.getRet() != 0) {
            return new InstalledItemUiState(null, false, 1, null);
        }
        List<SoftwareIntermediaryRsp> apiData = sourceDataBody.getApiData();
        if (apiData == null || apiData.isEmpty()) {
            return new InstalledItemUiState(null, false, 1, null);
        }
        List<SoftwareIntermediaryRsp> apiData2 = sourceDataBody.getApiData();
        ArrayList arrayList = new ArrayList(CollectionsKt.b(apiData2));
        for (Iterator it = apiData2.iterator(); it.hasNext(); it = it) {
            SoftwareIntermediaryRsp softwareIntermediaryRsp = (SoftwareIntermediaryRsp) it.next();
            arrayList.add(new SoftwareInstalled(softwareIntermediaryRsp.getId(), softwareIntermediaryRsp.getName(), softwareIntermediaryRsp.getIcon(), softwareIntermediaryRsp.getIconDrawable(), softwareIntermediaryRsp.getPackageName(), softwareIntermediaryRsp.getClassifyId(), softwareUiStateGenerator.dataGenerator.generateAPPClassifyName(Integer.valueOf(softwareIntermediaryRsp.getClassifyId()), softwareUiStateGenerator.context), softwareIntermediaryRsp.getTotalSize(), 0, o5.a.a(Long.valueOf(softwareIntermediaryRsp.getTotalSize())), softwareIntermediaryRsp.getVersionCode(), softwareIntermediaryRsp.getVersionTip(), softwareUiStateGenerator.dataGenerator.generateVersionTip(softwareIntermediaryRsp.getVersionTip(), softwareUiStateGenerator.context), softwareIntermediaryRsp.getIntroduction(), softwareIntermediaryRsp.getDownloadUrl(), null, 0, softwareIntermediaryRsp.getSourceType(), softwareUiStateGenerator.dataGenerator.parseToSoftwareExtParamList(softwareIntermediaryRsp.getPackageName(), softwareIntermediaryRsp.getExtra()), new SoftwareDtFixInfo(softwareIntermediaryRsp.getDownloadUrl(), softwareIntermediaryRsp.getTotalSize(), softwareIntermediaryRsp.getVersionCode()), 98304, null));
            softwareUiStateGenerator = this;
        }
        return new InstalledItemUiState(arrayList, true);
    }

    @NotNull
    public final InstalledItemUiState generateInstalledItemUiState(@Nullable List<SoftwareInstalled> softwareInstalledList, @NotNull InstalledItemUiState curInstalledItemUiState) {
        Intrinsics.checkNotNullParameter(curInstalledItemUiState, "curInstalledItemUiState");
        return new InstalledItemUiState(softwareInstalledList, curInstalledItemUiState.getVisible());
    }

    @NotNull
    public final <T, K, E> FooterHeaderUiState generatePullDownUiState(@NotNull SourceDataBody<T> sourceDataBody1, @NotNull SourceDataBody<K> sourceDataBody2, @NotNull SourceDataBody<E> sourceDataBody3) {
        Intrinsics.checkNotNullParameter(sourceDataBody1, "sourceDataBody1");
        Intrinsics.checkNotNullParameter(sourceDataBody2, "sourceDataBody2");
        Intrinsics.checkNotNullParameter(sourceDataBody3, "sourceDataBody3");
        return (sourceDataBody1.getRet() == 0 || sourceDataBody2.getRet() == 0 || sourceDataBody3.getRet() == 0) ? new FooterHeaderUiState(true, 1) : (sourceDataBody1.getRet() == 1 || sourceDataBody2.getRet() == 1 || sourceDataBody3.getRet() == 1) ? new FooterHeaderUiState(true, 3) : (sourceDataBody1.getRet() == 2 || sourceDataBody2.getRet() == 2 || sourceDataBody3.getRet() == 2) ? new FooterHeaderUiState(true, 4) : (sourceDataBody1.getRet() == 3 || sourceDataBody2.getRet() == 3 || sourceDataBody3.getRet() == 3) ? new FooterHeaderUiState(true, 2) : new FooterHeaderUiState(true, 2);
    }

    @NotNull
    public final <T> FooterHeaderUiState generatePullUpUiState(@NotNull SourceDataBody<T> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        int ret = sourceDataBody.getRet();
        return ret != 0 ? ret != 1 ? ret != 2 ? ret != 3 ? new FooterHeaderUiState(true, 17) : new FooterHeaderUiState(true, 18) : new FooterHeaderUiState(true, 20) : new FooterHeaderUiState(true, 19) : new FooterHeaderUiState(true, 17);
    }

    @NotNull
    public final SearchResultItemUiState generateSearchResultUiState(@NotNull SearchResult searchResult, @NotNull SearchResultItemUiState curCoSearchResultUiState) {
        Intrinsics.checkNotNullParameter(searchResult, "searchResult");
        Intrinsics.checkNotNullParameter(curCoSearchResultUiState, "curCoSearchResultUiState");
        return new SearchResultItemUiState(curCoSearchResultUiState.getVisible(), searchResult);
    }

    @NotNull
    public final SoftwareThemeUiState generateSoftWareThemUiState(@NotNull SourceDataBody<NetRspDataBody<List<SoftwareThemRsp>>> sourceDataBody, @Nullable List<SoftwareDeviceInstalledRsp> deviceInstalledSoftwareList) {
        SoftwareThemeUiState softwareThemeUiState;
        List<SoftwareThemRsp> data;
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        if (sourceDataBody.getRet() == 0) {
            NetRspDataBody<List<SoftwareThemRsp>> apiData = sourceDataBody.getApiData();
            ArrayList arrayList = null;
            List<SoftwareThemRsp> data2 = apiData != null ? apiData.getData() : null;
            if (data2 == null || data2.isEmpty()) {
                softwareThemeUiState = new SoftwareThemeUiState(true, null, false, 2, null);
            } else {
                List<SoftwareDeviceInstalledRsp> list = deviceInstalledSoftwareList == null || deviceInstalledSoftwareList.isEmpty() ? (List) BuildersKt__BuildersKt.runBlocking$default(null, new SoftwareUiStateGenerator$generateSoftWareThemUiState$deviceInstalledList$1(this, null), 1, null) : deviceInstalledSoftwareList;
                NetRspDataBody<List<SoftwareThemRsp>> apiData2 = sourceDataBody.getApiData();
                if (apiData2 != null && (data = apiData2.getData()) != null) {
                    arrayList = new ArrayList(CollectionsKt.b(data));
                    Iterator<T> it = data.iterator();
                    while (it.hasNext()) {
                        SoftwareIntermediaryRsp toSoftwareIntermediaryRsp = this.dataGenerator.parseToSoftwareIntermediaryRsp((SoftwareThemRsp) it.next());
                        SoftwareInterceptorRetData softwareInterceptorRetDataSoftwareStateDataComprehensiveParse = this.softwareStateDataParserGenerator.softwareStateDataComprehensiveParse(toSoftwareIntermediaryRsp, list);
                        arrayList.add(new Software(toSoftwareIntermediaryRsp.getId(), toSoftwareIntermediaryRsp.getName(), toSoftwareIntermediaryRsp.getIcon(), toSoftwareIntermediaryRsp.getPackageName(), toSoftwareIntermediaryRsp.getClassifyId(), this.dataGenerator.generateAPPClassifyName(Integer.valueOf(toSoftwareIntermediaryRsp.getClassifyId()), this.context), toSoftwareIntermediaryRsp.getTotalSize(), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getSoftwareStateData().getPercentCount(), o5.a.a(Long.valueOf(toSoftwareIntermediaryRsp.getTotalSize())), toSoftwareIntermediaryRsp.getVersionCode(), toSoftwareIntermediaryRsp.getVersionTip(), this.dataGenerator.generateVersionTip(toSoftwareIntermediaryRsp.getVersionTip(), this.context), null, toSoftwareIntermediaryRsp.getDownloadUrl(), toSoftwareIntermediaryRsp.getSignature(), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getSoftwareStateData().getState(), toSoftwareIntermediaryRsp.getSourceType(), this.dataGenerator.parseToSoftwareExtParamList(toSoftwareIntermediaryRsp.getPackageName(), toSoftwareIntermediaryRsp.getExtra()), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getDtFixInfo(), 4096, null));
                    }
                }
                softwareThemeUiState = new SoftwareThemeUiState(true, arrayList, true);
            }
        } else {
            softwareThemeUiState = new SoftwareThemeUiState(false, null, false, 2, null);
        }
        ct.a(hi.e("repo-vm generateSoftWareThemUiState visible:", softwareThemeUiState.getVisible()), new Object[0]);
        return softwareThemeUiState;
    }

    @Nullable
    public final SoftwareThemeUiState generateSoftWareThemUiStateForPT(@NotNull String pkn, @NotNull SoftwareStateData softwareStateData, @NotNull SoftwareThemeUiState curSoftwareThemeUiState) {
        Intrinsics.checkNotNullParameter(pkn, "pkn");
        Intrinsics.checkNotNullParameter(softwareStateData, "softwareStateData");
        Intrinsics.checkNotNullParameter(curSoftwareThemeUiState, "curSoftwareThemeUiState");
        List<? extends Software> data = curSoftwareThemeUiState.getData();
        List mutableList = data != null ? CollectionsKt.toMutableList((Collection) data) : null;
        if (mutableList == null || mutableList.isEmpty()) {
            ct.a("generateSoftWareThemUiStateForPT curThemeList.isNullOrEmpty", new Object[0]);
            return null;
        }
        int i = 0;
        for (Object obj : mutableList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Software software = (Software) obj;
            if (Intrinsics.areEqual(software.getPackageName(), pkn)) {
                mutableList.set(i, software.newSoftwareDownloaded(softwareStateData.getPercentCount(), softwareStateData.getState()));
            }
            i = i2;
        }
        Boolean ret = curSoftwareThemeUiState.getRet();
        return new SoftwareThemeUiState(ret != null ? ret.booleanValue() : false, mutableList, curSoftwareThemeUiState.getVisible());
    }

    @NotNull
    public final SoftwareDetailUiState generateSoftwareDetailUiState(@NotNull SourceDataBody<NetRspDataBody<SoftwareDetailRsp>> sourceDataBody) {
        SoftwareDetailUiState softwareDetailUiState;
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        int i = 0;
        ArrayList arrayList = null;
        if (sourceDataBody.getRet() == 0) {
            NetRspDataBody<SoftwareDetailRsp> apiData = sourceDataBody.getApiData();
            SoftwareDetailRsp data = apiData != null ? apiData.getData() : null;
            if (data == null) {
                softwareDetailUiState = new SoftwareDetailUiState(null, false, 1, null);
            } else {
                List<SoftwareDeviceInstalledRsp> list = (List) BuildersKt__BuildersKt.runBlocking$default(null, new SoftwareUiStateGenerator$generateSoftwareDetailUiState$deviceInstalledList$1(this, null), 1, null);
                SoftwareIntermediaryRsp toSoftwareIntermediaryRsp = this.dataGenerator.parseToSoftwareIntermediaryRsp(data);
                SoftwareInterceptorRetData softwareInterceptorRetDataSoftwareStateDataComprehensiveParse = this.softwareStateDataParserGenerator.softwareStateDataComprehensiveParse(toSoftwareIntermediaryRsp, list);
                List<SoftwareExtParam> toSoftwareExtParamList = this.dataGenerator.parseToSoftwareExtParamList(toSoftwareIntermediaryRsp.getPackageName(), toSoftwareIntermediaryRsp.getExtra());
                long updateTime = toSoftwareIntermediaryRsp.getUpdateTime();
                String strGenerateUpdateTimeTip = this.dataGenerator.generateUpdateTimeTip(toSoftwareIntermediaryRsp.getUpdateTime(), this.context);
                int screenCompatibility = toSoftwareIntermediaryRsp.getScreenCompatibility();
                String strGenerateScreenCompatibilityTip = this.dataGenerator.generateScreenCompatibilityTip(toSoftwareIntermediaryRsp.getScreenCompatibility(), this.context);
                String introduction = toSoftwareIntermediaryRsp.getIntroduction();
                List<ImagePathRsp> images = data.getImages();
                if (images != null) {
                    arrayList = new ArrayList(CollectionsKt.b(images));
                    Iterator<T> it = images.iterator();
                    while (it.hasNext()) {
                        String image_path = ((ImagePathRsp) it.next()).getImage_path();
                        if (image_path == null) {
                            image_path = "";
                        }
                        arrayList.add(new ImagePath(image_path));
                    }
                }
                softwareDetailUiState = new SoftwareDetailUiState(new SoftwareDetail(new Software(toSoftwareIntermediaryRsp.getId(), toSoftwareIntermediaryRsp.getName(), toSoftwareIntermediaryRsp.getIcon(), toSoftwareIntermediaryRsp.getPackageName(), toSoftwareIntermediaryRsp.getClassifyId(), this.dataGenerator.generateAPPClassifyName(Integer.valueOf(toSoftwareIntermediaryRsp.getClassifyId()), this.context), toSoftwareIntermediaryRsp.getTotalSize(), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getSoftwareStateData().getPercentCount(), o5.a.a(Long.valueOf(toSoftwareIntermediaryRsp.getTotalSize())), toSoftwareIntermediaryRsp.getVersionCode(), toSoftwareIntermediaryRsp.getVersionTip(), this.dataGenerator.generateVersionTip(toSoftwareIntermediaryRsp.getVersionTip(), this.context), toSoftwareIntermediaryRsp.getIntroduction(), toSoftwareIntermediaryRsp.getDownloadUrl(), toSoftwareIntermediaryRsp.getSignature(), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getSoftwareStateData().getState(), toSoftwareIntermediaryRsp.getSourceType(), toSoftwareExtParamList, softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getDtFixInfo()), updateTime, strGenerateUpdateTimeTip, introduction, screenCompatibility, strGenerateScreenCompatibilityTip, arrayList, toSoftwareIntermediaryRsp.getVersionIntroduction()), true);
                i = 0;
            }
        } else {
            softwareDetailUiState = new SoftwareDetailUiState(null, false, 1, null);
            i = 0;
        }
        ct.a(hi.e("repo-vm generateSoftwareDetailUiState visible:", softwareDetailUiState.getVisible()), new Object[i]);
        return softwareDetailUiState;
    }

    @NotNull
    public final SoftwareDetailUiState generateSoftwareDetailUiState(@NotNull SoftwareDetail softwareDetail, @NotNull SoftwareDetailUiState curCoftwareDetailUiState) {
        Intrinsics.checkNotNullParameter(softwareDetail, "softwareDetail");
        Intrinsics.checkNotNullParameter(curCoftwareDetailUiState, "curCoftwareDetailUiState");
        return new SoftwareDetailUiState(softwareDetail, curCoftwareDetailUiState.getVisible());
    }

    public final boolean generateStartAllPauseAllUiState(@NotNull SourceDataBody<List<qq>> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        if (sourceDataBody.getRet() == 0) {
            List<qq> apiData = sourceDataBody.getApiData();
            if (!(apiData == null || apiData.isEmpty())) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public final <T> ToastUiState generateToastUiState(@NotNull SourceDataBody<T> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        int ret = sourceDataBody.getRet();
        if (ret != 0) {
            return ret != 1 ? ret != 2 ? ret != 3 ? new ToastUiState(false, R$string.net_no_connect_toast) : new ToastUiState(true, R$string.unknown_error) : new ToastUiState(true, R$string.net_no_connect_toast) : new ToastUiState(true, R$string.net_connect_out_time_toast);
        }
        if (!(sourceDataBody.getApiData() != null)) {
            return new ToastUiState(true, R$string.no_data);
        }
        if ((sourceDataBody.getApiData() instanceof List) && ((List) sourceDataBody.getApiData()).isEmpty()) {
            return new ToastUiState(true, R$string.no_data);
        }
        return new ToastUiState(false, 0, 3, null);
    }

    @NotNull
    public final <T, K, V> ToastUiState generateToastUiState(@NotNull SourceDataBody<T> sourceDataBody1, @NotNull SourceDataBody<K> sourceDataBody2, @NotNull SourceDataBody<V> sourceDataBody3) {
        Intrinsics.checkNotNullParameter(sourceDataBody1, "sourceDataBody1");
        Intrinsics.checkNotNullParameter(sourceDataBody2, "sourceDataBody2");
        Intrinsics.checkNotNullParameter(sourceDataBody3, "sourceDataBody3");
        return (sourceDataBody1.getRet() == 0 || sourceDataBody2.getRet() == 0 || sourceDataBody3.getRet() == 0) ? new ToastUiState(false, 0, 3, null) : (sourceDataBody1.getRet() == 1 || sourceDataBody2.getRet() == 1 || sourceDataBody3.getRet() == 1) ? new ToastUiState(true, R$string.net_connect_out_time_toast) : (sourceDataBody1.getRet() == 2 || sourceDataBody2.getRet() == 2 || sourceDataBody3.getRet() == 2) ? new ToastUiState(true, R$string.net_no_connect_toast) : (sourceDataBody1.getRet() == 3 || sourceDataBody2.getRet() == 3 || sourceDataBody3.getRet() == 3) ? new ToastUiState(true, R$string.unknown_error) : new ToastUiState(false, 0, 3, null);
    }

    @NotNull
    public final TopicDetailUiState generateTopicDetailUiState(@NotNull SourceDataBody<NetRspDataBody<List<FeatureTopicSoftwareRsp>>> sourceDataBody) {
        TopicDetailUiState topicDetailUiState;
        List<FeatureTopicSoftwareRsp> data;
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        ArrayList arrayList = null;
        if (sourceDataBody.getRet() == 0) {
            NetRspDataBody<List<FeatureTopicSoftwareRsp>> apiData = sourceDataBody.getApiData();
            List<FeatureTopicSoftwareRsp> data2 = apiData != null ? apiData.getData() : null;
            if (data2 == null || data2.isEmpty()) {
                topicDetailUiState = new TopicDetailUiState(null, false, 1, null);
            } else {
                List<SoftwareDeviceInstalledRsp> list = (List) BuildersKt__BuildersKt.runBlocking$default(null, new SoftwareUiStateGenerator$generateTopicDetailUiState$deviceInstalledList$1(this, null), 1, null);
                NetRspDataBody<List<FeatureTopicSoftwareRsp>> apiData2 = sourceDataBody.getApiData();
                if (apiData2 != null && (data = apiData2.getData()) != null) {
                    arrayList = new ArrayList(CollectionsKt.b(data));
                    Iterator<T> it = data.iterator();
                    while (it.hasNext()) {
                        SoftwareIntermediaryRsp toSoftwareIntermediaryRsp = this.dataGenerator.parseToSoftwareIntermediaryRsp((FeatureTopicSoftwareRsp) it.next());
                        SoftwareInterceptorRetData softwareInterceptorRetDataSoftwareStateDataComprehensiveParse = this.softwareStateDataParserGenerator.softwareStateDataComprehensiveParse(toSoftwareIntermediaryRsp, list);
                        arrayList.add(new Software(toSoftwareIntermediaryRsp.getId(), toSoftwareIntermediaryRsp.getName(), toSoftwareIntermediaryRsp.getIcon(), toSoftwareIntermediaryRsp.getPackageName(), toSoftwareIntermediaryRsp.getClassifyId(), this.dataGenerator.generateAPPClassifyName(Integer.valueOf(toSoftwareIntermediaryRsp.getClassifyId()), this.context), toSoftwareIntermediaryRsp.getTotalSize(), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getSoftwareStateData().getPercentCount(), o5.a.a(Long.valueOf(toSoftwareIntermediaryRsp.getTotalSize())), toSoftwareIntermediaryRsp.getVersionCode(), toSoftwareIntermediaryRsp.getVersionTip(), this.dataGenerator.generateVersionTip(toSoftwareIntermediaryRsp.getVersionTip(), this.context), null, toSoftwareIntermediaryRsp.getDownloadUrl(), toSoftwareIntermediaryRsp.getSignature(), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getSoftwareStateData().getState(), toSoftwareIntermediaryRsp.getSourceType(), this.dataGenerator.parseToSoftwareExtParamList(toSoftwareIntermediaryRsp.getPackageName(), toSoftwareIntermediaryRsp.getExtra()), softwareInterceptorRetDataSoftwareStateDataComprehensiveParse.getDtFixInfo(), 4096, null));
                    }
                }
                topicDetailUiState = new TopicDetailUiState(arrayList, true);
            }
        } else {
            topicDetailUiState = new TopicDetailUiState(null, false, 1, null);
        }
        ct.a(hi.e("repo-vm generateTopicDetailUiState visible:", topicDetailUiState.getVisible()), new Object[0]);
        return topicDetailUiState;
    }

    @NotNull
    public final TopicTitleUiState generateTopicTitleUiState(@NotNull String topicTitle) {
        Intrinsics.checkNotNullParameter(topicTitle, "topicTitle");
        return new TopicTitleUiState(topicTitle, true);
    }

    public final boolean generateUpgradeSoftwareExistUiState(@NotNull SourceDataBody<List<qq>> sourceDataBody) {
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        List<qq> apiData = sourceDataBody.getApiData();
        return !(apiData == null || apiData.isEmpty());
    }

    @NotNull
    public final UpgradeUiState generateUpgradeSoftwareUiState(@NotNull SourceDataBody<List<qq>> sourceDataBody) {
        SoftwareStateData value;
        SoftwareStateData value2;
        SoftwareStateData value3;
        SoftwareStateData value4;
        SoftwareUiStateGenerator softwareUiStateGenerator = this;
        Intrinsics.checkNotNullParameter(sourceDataBody, "sourceDataBody");
        int state = 0;
        Integer numValueOf = null;
        if (sourceDataBody.getRet() != 0) {
            return new UpgradeUiState(null, false, 1, null);
        }
        List<qq> apiData = sourceDataBody.getApiData();
        if (apiData == null || apiData.isEmpty()) {
            return new UpgradeUiState(null, false, 1, null);
        }
        HashMap<String, MutableStateFlow<SoftwareStateData>> map = softwareUiStateGenerator.executorSoftwareStateMgr.b;
        List<qq> apiData2 = sourceDataBody.getApiData();
        ArrayList<qq> arrayList = new ArrayList();
        for (Object obj : apiData2) {
            MutableStateFlow<SoftwareStateData> mutableStateFlow = map.get(((qq) obj).a.a);
            if (!((mutableStateFlow == null || (value4 = mutableStateFlow.getValue()) == null || value4.getState() != 6) ? false : true)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.b(arrayList));
        for (qq qqVar : arrayList) {
            pq pqVar = qqVar.a;
            String str = pqVar.a;
            int i = pqVar.k;
            MutableStateFlow<SoftwareStateData> mutableStateFlow2 = map.get(str);
            if (mutableStateFlow2 != null && (value3 = mutableStateFlow2.getValue()) != null) {
                numValueOf = Integer.valueOf(value3.getState());
            }
            StringBuilder sbK = d.k("temp fetchUpgradeList iuu INSTALLED & UNINSTALLED pkn：", str, " state：", i, " state2: ");
            sbK.append(numValueOf);
            ct.a(sbK.toString(), new Object[state]);
            List<SoftwareExtParam> toSoftwareExtParamList = softwareUiStateGenerator.dataGenerator.parseToSoftwareExtParamList(qqVar.b);
            int i2 = pqVar.b;
            String str2 = pqVar.c;
            String str3 = pqVar.g;
            String str4 = pqVar.a;
            int i3 = pqVar.h;
            String strGenerateAPPClassifyName = softwareUiStateGenerator.dataGenerator.generateAPPClassifyName(Integer.valueOf(i3), softwareUiStateGenerator.context);
            long j = pqVar.e;
            MutableStateFlow<SoftwareStateData> mutableStateFlow3 = map.get(pqVar.a);
            int percentCount = (mutableStateFlow3 == null || (value2 = mutableStateFlow3.getValue()) == null) ? state : value2.getPercentCount();
            MutableStateFlow<SoftwareStateData> mutableStateFlow4 = map.get(pqVar.a);
            if (mutableStateFlow4 != null && (value = mutableStateFlow4.getValue()) != null) {
                state = value.getState();
            }
            HashMap<String, MutableStateFlow<SoftwareStateData>> map2 = map;
            String strA = o5.a.a(Long.valueOf(pqVar.e));
            String str5 = pqVar.f;
            String str6 = pqVar.l;
            int i4 = pqVar.j;
            arrayList2.add(new Software(i2, str2, str3, str4, i3, strGenerateAPPClassifyName, j, percentCount, strA, i4, null, null, null, str5, str6, state, pqVar.m, toSoftwareExtParamList, new SoftwareDtFixInfo(str5, pqVar.e, i4), 7168, null));
            state = 0;
            numValueOf = null;
            softwareUiStateGenerator = this;
            map = map2;
        }
        return new UpgradeUiState(arrayList2, true);
    }

    @NotNull
    public final UpgradeUiState generateUpgradeSoftwareUiState(@NotNull List<? extends Software> softwareList, @NotNull UpgradeUiState curUpgradeUiState) {
        Intrinsics.checkNotNullParameter(softwareList, "softwareList");
        Intrinsics.checkNotNullParameter(curUpgradeUiState, "curUpgradeUiState");
        return new UpgradeUiState(softwareList, curUpgradeUiState.getVisible());
    }
}
