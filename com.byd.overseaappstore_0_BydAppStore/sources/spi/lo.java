package spi;

import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.req.SearchSoftwareReq;
import com.byd.overseaappstore.module.ability.data.rsp.HotSearchRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareSearchRsp;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J+\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u00042\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\n\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0005\u0018\u00010\u0004H§@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lspi/lo;", "", "Lcom/byd/overseaappstore/module/ability/data/req/SearchSoftwareReq;", "searchSoftwareReq", "Lcom/byd/overseaappstore/module/ability/data/NetRspDataBody;", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareSearchRsp;", "b", "(Lcom/byd/overseaappstore/module/ability/data/req/SearchSoftwareReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/byd/overseaappstore/module/ability/data/rsp/HotSearchRsp;", "a", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public interface lo {
    @POST("appHotSearch/get")
    @Nullable
    Object a(@NotNull Continuation<? super NetRspDataBody<List<HotSearchRsp>>> continuation);

    @POST("search/software")
    @Nullable
    Object b(@Body @NotNull SearchSoftwareReq searchSoftwareReq, @NotNull Continuation<? super NetRspDataBody<List<SoftwareSearchRsp>>> continuation);
}
