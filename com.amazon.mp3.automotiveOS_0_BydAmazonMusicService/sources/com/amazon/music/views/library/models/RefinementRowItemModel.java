package com.amazon.music.views.library.models;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: RefinementRowItemModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0011\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u00038&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/views/library/models/RefinementRowItemModel;", "T", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "isSelected", "()Z", "", "getTitle", "()Ljava/lang/String;", "title", "getSubTitle", "subTitle", ContentUtils.KEY_VIEW_REFERENCE_ID, "<init>", "(Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public abstract class RefinementRowItemModel<T> extends BaseViewModel {
    public abstract String getSubTitle();

    public abstract String getTitle();

    public abstract boolean isSelected();

    public RefinementRowItemModel(String str) {
        super(str, null, 2, 0 == true ? 1 : 0);
    }
}
