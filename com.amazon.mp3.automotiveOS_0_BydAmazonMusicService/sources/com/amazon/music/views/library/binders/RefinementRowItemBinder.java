package com.amazon.music.views.library.binders;

import com.amazon.music.views.library.models.RefinementRowItemModel;
import com.amazon.music.views.library.views.RefinementRowItemView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementRowItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u0003*\u000e\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00010\u00052\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\u0006B\u0005¢\u0006\u0002\u0010\u0007J\u001d\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u00012\u0006\u0010\u000b\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/binders/RefinementRowItemBinder;", "T", "V", "Lcom/amazon/music/views/library/views/RefinementRowItemView;", "M", "Lcom/amazon/music/views/library/models/RefinementRowItemModel;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "()V", "bind", "", "view", "model", "(Lcom/amazon/music/views/library/views/RefinementRowItemView;Lcom/amazon/music/views/library/models/RefinementRowItemModel;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class RefinementRowItemBinder<T, V extends RefinementRowItemView, M extends RefinementRowItemModel<T>> implements UniversalBinder<V, M> {
    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(V view, M model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.setTitle(model.getTitle());
        view.setSubTitle(model.getSubTitle());
        view.setIsSelected(model.isSelected());
    }
}
