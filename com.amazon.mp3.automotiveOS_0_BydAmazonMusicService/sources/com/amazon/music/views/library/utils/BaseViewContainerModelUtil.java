package com.amazon.music.views.library.utils;

import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewContainerModelUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u0004\u0018\u0001H\u0004\"\b\b\u0000\u0010\u0004*\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00040\t¢\u0006\u0002\u0010\nJ \u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00072\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/views/library/utils/BaseViewContainerModelUtil;", "", "()V", "findChildByType", "T", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "containerModel", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "clazz", "Ljava/lang/Class;", "(Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;Ljava/lang/Class;)Lcom/amazon/music/views/library/models/base/BaseViewModel;", "findContainerByTag", ParserUtil.TAG_QUERY_PARAM_NAME, "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BaseViewContainerModelUtil {
    public static final BaseViewContainerModelUtil INSTANCE = new BaseViewContainerModelUtil();

    private BaseViewContainerModelUtil() {
    }

    public final BaseViewContainerModel<?> findContainerByTag(BaseViewContainerModel<?> containerModel, String tag) {
        BaseViewContainerModel<?> baseViewContainerModelFindContainerByTag;
        Intrinsics.checkNotNullParameter(containerModel, "containerModel");
        Intrinsics.checkNotNullParameter(tag, "tag");
        List<String> tags = containerModel.getTags();
        boolean z = false;
        if (tags != null && tags.contains(tag)) {
            z = true;
        }
        if (z) {
            return containerModel;
        }
        for (BaseViewModel baseViewModel : containerModel.getModels()) {
            if ((baseViewModel instanceof BaseViewContainerModel) && (baseViewContainerModelFindContainerByTag = INSTANCE.findContainerByTag((BaseViewContainerModel) baseViewModel, tag)) != null) {
                return baseViewContainerModelFindContainerByTag;
            }
        }
        return null;
    }

    public final <T extends BaseViewModel> T findChildByType(BaseViewContainerModel<?> containerModel, Class<T> clazz) {
        Intrinsics.checkNotNullParameter(containerModel, "containerModel");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        if (Intrinsics.areEqual(containerModel.getClass(), clazz)) {
            return containerModel;
        }
        for (T t : containerModel.getModels()) {
            if (t instanceof BaseViewContainerModel) {
                T t2 = (T) INSTANCE.findChildByType((BaseViewContainerModel) t, clazz);
                if (t2 != null) {
                    return t2;
                }
            } else if (Intrinsics.areEqual(t.getClass(), clazz)) {
                return t;
            }
        }
        return null;
    }
}
