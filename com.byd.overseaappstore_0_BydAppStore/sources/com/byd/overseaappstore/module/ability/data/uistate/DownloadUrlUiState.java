package com.byd.overseaappstore.module.ability.data.uistate;

import androidx.core.app.NotificationCompat;
import com.byd.overseaappstore.module.ability.data.UiState;
import com.byd.overseaappstore.module.ability.data.req.DownloadUrlInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B-\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/uistate/DownloadUrlUiState;", "Lcom/byd/overseaappstore/module/ability/data/UiState;", "", "Lcom/byd/overseaappstore/module/ability/data/req/DownloadUrlInfo;", "data", NotificationCompat.CATEGORY_MESSAGE, "", "visible", "", "(Ljava/util/List;Ljava/lang/String;Z)V", "getMsg", "()Ljava/lang/String;", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadUrlUiState extends UiState<List<? extends DownloadUrlInfo>> {

    @Nullable
    private final String msg;

    public DownloadUrlUiState() {
        this(null, null, false, 7, null);
    }

    public DownloadUrlUiState(@Nullable List<? extends DownloadUrlInfo> list, @Nullable String str, boolean z) {
        super(list, z);
        this.msg = str;
    }

    public /* synthetic */ DownloadUrlUiState(List list, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : str, (i & 4) != 0 ? false : z);
    }

    @Nullable
    public final String getMsg() {
        return this.msg;
    }
}
