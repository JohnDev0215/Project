package com.amazon.music.alps.liveevent.viewmodel;

import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: LineupViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001BK\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\u0018\b\u0002\u0010\u0015\u001a\u0012\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000e\u0018\u00010\u000e¢\u0006\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001f\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R'\u0010\u0015\u001a\u0012\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000e\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0011\u001a\u0004\b\u0016\u0010\u0013¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/alps/liveevent/viewmodel/LineupViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "", "selectedIndex", "Ljava/lang/Integer;", "getSelectedIndex", "()Ljava/lang/Integer;", "setSelectedIndex", "(Ljava/lang/Integer;)V", "", "Lcom/amazon/music/alps/liveevent/viewmodel/SelectItemViewModel;", "lineupItemList", "Ljava/util/List;", "getLineupItemList", "()Ljava/util/List;", "Lcom/amazon/music/views/library/models/HorizontalTileModel;", "lineupDetailList", "getLineupDetailList", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class LineupViewModel extends BaseViewModel {
    private final List<List<HorizontalTileModel>> lineupDetailList;
    private final List<SelectItemViewModel> lineupItemList;
    private Integer selectedIndex;
    private final String viewId;

    public LineupViewModel() {
        this(null, null, null, null, 15, null);
    }

    public /* synthetic */ LineupViewModel(String str, Integer num, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? 0 : num, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : list2);
    }

    public final Integer getSelectedIndex() {
        return this.selectedIndex;
    }

    public final void setSelectedIndex(Integer num) {
        this.selectedIndex = num;
    }

    public final List<SelectItemViewModel> getLineupItemList() {
        return this.lineupItemList;
    }

    public final List<List<HorizontalTileModel>> getLineupDetailList() {
        return this.lineupDetailList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LineupViewModel(String str, Integer num, List<SelectItemViewModel> list, List<? extends List<HorizontalTileModel>> list2) {
        super(str, null, 2, null);
        this.viewId = str;
        this.selectedIndex = num;
        this.lineupItemList = list;
        this.lineupDetailList = list2;
    }
}
