package com.amazon.music.views.library.converter;

import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: BaseContainerModelConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J/\u0010\u0003\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00028\u00002\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bH&¢\u0006\u0002\u0010\tJ\u001d\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "From", "Lcom/amazon/music/views/library/converter/BaseModelConverter;", "convert", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "data", "children", "", "(Ljava/lang/Object;Ljava/util/List;)Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "getChildrenModels", "(Ljava/lang/Object;)Ljava/util/List;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface BaseContainerModelConverter<From> extends BaseModelConverter {
    BaseViewContainerModel<? extends BaseViewModel> convert(From data, List<? extends BaseViewModel> children);

    List<BaseViewModel> getChildrenModels(From data);
}
