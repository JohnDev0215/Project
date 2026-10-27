package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SimpleShovelerModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Bm\b\u0007\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0016\u0010\u0017R$\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\n\u0010\u0005\u001a\u0004\b\u000b\u0010\u0007R\u0019\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010\u0005\u001a\u0004\b\r\u0010\u0007R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u0005\u001a\u0004\b\u000f\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/views/library/models/SimpleShovelerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "moreText", "getMoreText", "nextPageToken", "getNextPageToken", "moreTarget", "getMoreTarget", "viewId", "", "models", "", "tileType", "tags", "<init>", "(Ljava/lang/String;Ljava/util/List;ILjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class SimpleShovelerModel extends BaseViewContainerModel<BaseViewModel> {
    private final String moreTarget;
    private final String moreText;
    private final String nextPageToken;
    private String title;

    public final String getTitle() {
        return this.title;
    }

    public final String getNextPageToken() {
        return this.nextPageToken;
    }

    public final String getMoreTarget() {
        return this.moreTarget;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SimpleShovelerModel(String str, List<? extends BaseViewModel> models, int i, List<String> list, String str2, String str3, String str4, String str5) {
        super(str, models, i, list);
        Intrinsics.checkNotNullParameter(models, "models");
        this.title = str2;
        this.moreText = str3;
        this.nextPageToken = str4;
        this.moreTarget = str5;
    }
}
