package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.ClearableContainer;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.models.enums.ViewType;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PillCellModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/views/library/models/PillCellModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "viewId", "", "title", "target", "targetType", "Lcom/amazon/music/views/library/models/enums/TargetType;", "viewType", "Lcom/amazon/music/views/library/models/enums/ViewType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/models/enums/TargetType;Lcom/amazon/music/views/library/models/enums/ViewType;)V", "clearableContainer", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/music/views/library/models/base/ClearableContainer;", "getClearableContainer", "()Ljava/lang/ref/WeakReference;", "setClearableContainer", "(Ljava/lang/ref/WeakReference;)V", "getTarget", "()Ljava/lang/String;", "getTargetType", "()Lcom/amazon/music/views/library/models/enums/TargetType;", "getTitle", "getViewId", "getViewType", "()Lcom/amazon/music/views/library/models/enums/ViewType;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PillCellModel extends BaseViewModel {
    private WeakReference<ClearableContainer> clearableContainer;
    private final String target;
    private final TargetType targetType;
    private final String title;
    private final String viewId;
    private final ViewType viewType;

    public final String getViewId() {
        return this.viewId;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getTarget() {
        return this.target;
    }

    public final TargetType getTargetType() {
        return this.targetType;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PillCellModel(String str, String title, String target, TargetType targetType, ViewType viewType) {
        super(str, null, 2, null);
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(targetType, "targetType");
        Intrinsics.checkNotNullParameter(viewType, "viewType");
        this.viewId = str;
        this.title = title;
        this.target = target;
        this.targetType = targetType;
        this.viewType = viewType;
    }

    public /* synthetic */ PillCellModel(String str, String str2, String str3, TargetType targetType, ViewType viewType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, str2, str3, targetType, (i & 16) != 0 ? ViewType.GENERIC : viewType);
    }

    public final ViewType getViewType() {
        return this.viewType;
    }

    public final WeakReference<ClearableContainer> getClearableContainer() {
        return this.clearableContainer;
    }

    public final void setClearableContainer(WeakReference<ClearableContainer> weakReference) {
        this.clearableContainer = weakReference;
    }
}
