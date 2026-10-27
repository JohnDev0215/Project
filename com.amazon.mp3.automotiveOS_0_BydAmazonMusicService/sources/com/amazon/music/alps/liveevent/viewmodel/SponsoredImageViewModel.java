package com.amazon.music.alps.liveevent.viewmodel;

import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SponsoredImageViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001f\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/alps/liveevent/viewmodel/SponsoredImageViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "", "Lcom/amazon/music/alps/liveevent/viewmodel/SponsoredImageItemViewModel;", "sponsoredImageItemList", "Ljava/util/List;", "getSponsoredImageItemList", "()Ljava/util/List;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class SponsoredImageViewModel extends BaseViewModel {
    private final List<SponsoredImageItemViewModel> sponsoredImageItemList;
    private final String viewId;

    public SponsoredImageViewModel() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public /* synthetic */ SponsoredImageViewModel(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : list);
    }

    public final List<SponsoredImageItemViewModel> getSponsoredImageItemList() {
        return this.sponsoredImageItemList;
    }

    public SponsoredImageViewModel(String str, List<SponsoredImageItemViewModel> list) {
        super(str, null, 2, null);
        this.viewId = str;
        this.sponsoredImageItemList = list;
    }
}
