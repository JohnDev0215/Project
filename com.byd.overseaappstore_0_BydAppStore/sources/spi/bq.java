package spi;

import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.req.FeatureTopicGroupExpendReq;
import com.byd.overseaappstore.module.ability.data.req.FeatureTopicGroupReq;
import com.byd.overseaappstore.module.ability.data.req.SoftwareDetailReq;
import com.byd.overseaappstore.module.ability.data.req.SoftwareThemReq;
import com.byd.overseaappstore.module.ability.data.req.UpgradeSoftwareReq;
import com.byd.overseaappstore.module.ability.data.rsp.FeatureTopicGroupRsp;
import com.byd.overseaappstore.module.ability.data.rsp.FeatureTopicSoftwareRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDetailRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareThemRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareUpdateRsp;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J+\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u00042\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ%\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00042\b\b\u0001\u0010\n\u001a\u00020\tH§@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ+\u0010\u0011\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u0005\u0018\u00010\u00042\b\b\u0001\u0010\u000f\u001a\u00020\u000eH§@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J+\u0010\u0016\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0005\u0018\u00010\u00042\b\b\u0001\u0010\u0014\u001a\u00020\u0013H§@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J+\u0010\u001b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0005\u0018\u00010\u00042\b\b\u0001\u0010\u0019\u001a\u00020\u0018H§@ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lspi/bq;", "", "Lcom/byd/overseaappstore/module/ability/data/req/SoftwareThemReq;", "softwareThemReq", "Lcom/byd/overseaappstore/module/ability/data/NetRspDataBody;", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareThemRsp;", "c", "(Lcom/byd/overseaappstore/module/ability/data/req/SoftwareThemReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/byd/overseaappstore/module/ability/data/req/SoftwareDetailReq;", "softwareDetailReq", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDetailRsp;", "a", "(Lcom/byd/overseaappstore/module/ability/data/req/SoftwareDetailReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/byd/overseaappstore/module/ability/data/req/FeatureTopicGroupReq;", "featureTopicGroupReq", "Lcom/byd/overseaappstore/module/ability/data/rsp/FeatureTopicGroupRsp;", "b", "(Lcom/byd/overseaappstore/module/ability/data/req/FeatureTopicGroupReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/byd/overseaappstore/module/ability/data/req/FeatureTopicGroupExpendReq;", "featureTopicGroupExpendReq", "Lcom/byd/overseaappstore/module/ability/data/rsp/FeatureTopicSoftwareRsp;", "e", "(Lcom/byd/overseaappstore/module/ability/data/req/FeatureTopicGroupExpendReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/byd/overseaappstore/module/ability/data/req/UpgradeSoftwareReq;", "upgradeSoftwareReq", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareUpdateRsp;", "d", "(Lcom/byd/overseaappstore/module/ability/data/req/UpgradeSoftwareReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public interface bq {
    @POST("software/detailNew")
    @Nullable
    Object a(@Body @NotNull SoftwareDetailReq softwareDetailReq, @NotNull Continuation<? super NetRspDataBody<SoftwareDetailRsp>> continuation);

    @POST("featured/theme/get")
    @Nullable
    Object b(@Body @NotNull FeatureTopicGroupReq featureTopicGroupReq, @NotNull Continuation<? super NetRspDataBody<List<FeatureTopicGroupRsp>>> continuation);

    @POST("theme/softwares")
    @Nullable
    Object c(@Body @NotNull SoftwareThemReq softwareThemReq, @NotNull Continuation<? super NetRspDataBody<List<SoftwareThemRsp>>> continuation);

    @POST("upgrade/software")
    @Nullable
    Object d(@Body @NotNull UpgradeSoftwareReq upgradeSoftwareReq, @NotNull Continuation<? super NetRspDataBody<List<SoftwareUpdateRsp>>> continuation);

    @POST("featured/theme/getDetail")
    @Nullable
    Object e(@Body @NotNull FeatureTopicGroupExpendReq featureTopicGroupExpendReq, @NotNull Continuation<? super NetRspDataBody<List<FeatureTopicSoftwareRsp>>> continuation);
}
