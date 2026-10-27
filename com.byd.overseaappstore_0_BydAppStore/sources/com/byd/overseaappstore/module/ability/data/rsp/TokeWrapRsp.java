package com.byd.overseaappstore.module.ability.data.rsp;

import androidx.core.app.NotificationCompat;
import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/rsp/TokeWrapRsp;", "Lcom/byd/overseaappstore/module/ability/data/NetRspDataBody;", "Lcom/byd/overseaappstore/module/ability/data/rsp/TokenRsp;", "code", "", NotificationCompat.CATEGORY_MESSAGE, "", "data", "(ILjava/lang/String;Lcom/byd/overseaappstore/module/ability/data/rsp/TokenRsp;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TokeWrapRsp extends NetRspDataBody<TokenRsp> {
    public TokeWrapRsp() {
        this(0, null, null, 7, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TokeWrapRsp(int i, @NotNull String msg, @Nullable TokenRsp tokenRsp) {
        super(i, msg, tokenRsp);
        Intrinsics.checkNotNullParameter(msg, "msg");
    }

    public /* synthetic */ TokeWrapRsp(int i, String str, TokenRsp tokenRsp, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? null : tokenRsp);
    }
}
