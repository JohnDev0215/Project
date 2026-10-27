package com.amazon.music.alps.liveevent.viewmodel;

import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: NewWaysToWatchViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B1\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u001f\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/alps/liveevent/viewmodel/NewWaysToWatchViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "title", "getTitle", "", "Lcom/amazon/music/alps/liveevent/viewmodel/VerticalIconLinkItemViewModel;", "waysToWatchItems", "Ljava/util/List;", "getWaysToWatchItems", "()Ljava/util/List;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class NewWaysToWatchViewModel extends BaseViewModel {
    private final String title;
    private final String viewId;
    private final List<VerticalIconLinkItemViewModel> waysToWatchItems;

    public NewWaysToWatchViewModel() {
        this(null, null, null, 7, null);
    }

    public /* synthetic */ NewWaysToWatchViewModel(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : list);
    }

    public final String getTitle() {
        return this.title;
    }

    public final List<VerticalIconLinkItemViewModel> getWaysToWatchItems() {
        return this.waysToWatchItems;
    }

    public NewWaysToWatchViewModel(String str, String str2, List<VerticalIconLinkItemViewModel> list) {
        super(str, null, 2, null);
        this.viewId = str;
        this.title = str2;
        this.waysToWatchItems = list;
    }
}
