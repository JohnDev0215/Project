package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarouselModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\n¢\u0006\u0004\b\f\u0010\rR$\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/views/library/models/CarouselModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/FeatureBarkerModel;", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "setViewId", "(Ljava/lang/String;)V", "", "models", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CarouselModel extends BaseViewContainerModel<FeatureBarkerModel> {
    private String viewId;

    public final String getViewId() {
        return this.viewId;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarouselModel(String str, List<FeatureBarkerModel> models) {
        super(str, models, 0, null, 12, null);
        Intrinsics.checkNotNullParameter(models, "models");
        this.viewId = str;
    }
}
