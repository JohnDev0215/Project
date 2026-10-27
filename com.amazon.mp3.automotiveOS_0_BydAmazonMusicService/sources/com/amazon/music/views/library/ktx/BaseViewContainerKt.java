package com.amazon.music.views.library.ktx;

import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.utils.BaseViewContainerModelUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewContainer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u001c\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0000*\u0006\u0012\u0002\b\u00030\u00002\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "", ParserUtil.TAG_QUERY_PARAM_NAME, "findContainerByTag", "DMMViewLibrary_release"}, k = 2, mv = {1, 6, 0})
public final class BaseViewContainerKt {
    public static final BaseViewContainerModel<?> findContainerByTag(BaseViewContainerModel<?> baseViewContainerModel, String tag) {
        Intrinsics.checkNotNullParameter(baseViewContainerModel, "<this>");
        Intrinsics.checkNotNullParameter(tag, "tag");
        return BaseViewContainerModelUtil.INSTANCE.findContainerByTag(baseViewContainerModel, tag);
    }
}
