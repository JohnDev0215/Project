package spi;

import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.req.DownloadUrlRefreshReq;
import com.byd.overseaappstore.module.ability.data.rsp.DownloadUrlRefreshRsp;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J'\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0004\u001a\u00020\u0002H§@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J+\u0010\r\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0018\u00010\n2\b\b\u0001\u0010\t\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lspi/x6;", "", "", "downloadUrl", "headTip", "Lspi/mn;", "a", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/byd/overseaappstore/module/ability/data/req/DownloadUrlRefreshReq;", "downloadUrlRefreshReq", "Lcom/byd/overseaappstore/module/ability/data/NetRspDataBody;", "", "Lcom/byd/overseaappstore/module/ability/data/rsp/DownloadUrlRefreshRsp;", "b", "(Lcom/byd/overseaappstore/module/ability/data/req/DownloadUrlRefreshReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public interface x6 {
    @Streaming
    @GET
    @Nullable
    @Headers({"CUSTOM_HEADER_KEY_DOWNLOAD:CUSTOM_HEADER_VALUE_DOWNLOAD"})
    Object a(@Url @NotNull String str, @Header("Range") @NotNull String str2, @NotNull Continuation<? super mn> continuation);

    @POST("getDownloadUrl/refresh")
    @Nullable
    Object b(@Body @NotNull DownloadUrlRefreshReq downloadUrlRefreshReq, @NotNull Continuation<? super NetRspDataBody<List<DownloadUrlRefreshRsp>>> continuation);
}
