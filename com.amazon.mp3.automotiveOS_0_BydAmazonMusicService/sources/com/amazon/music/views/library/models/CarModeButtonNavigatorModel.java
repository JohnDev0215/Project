package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeButtonNavigatorModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0012\u001a\u00020\b¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\rR\"\u0010\u0012\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0012\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/views/library/models/CarModeButtonNavigatorModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "toString", "", "hashCode", "", "other", "", "equals", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "text", "getText", "target", "getTarget", "isLastItem", "Z", "()Z", "setLastItem", "(Z)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class CarModeButtonNavigatorModel extends BaseViewModel {
    private boolean isLastItem;
    private final String target;
    private final String text;
    private final String viewId;

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CarModeButtonNavigatorModel)) {
            return false;
        }
        CarModeButtonNavigatorModel carModeButtonNavigatorModel = (CarModeButtonNavigatorModel) other;
        return Intrinsics.areEqual(this.viewId, carModeButtonNavigatorModel.viewId) && Intrinsics.areEqual(this.text, carModeButtonNavigatorModel.text) && Intrinsics.areEqual(this.target, carModeButtonNavigatorModel.target) && this.isLastItem == carModeButtonNavigatorModel.isLastItem;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        String str = this.viewId;
        int iHashCode = (((((str == null ? 0 : str.hashCode()) * 31) + this.text.hashCode()) * 31) + this.target.hashCode()) * 31;
        boolean z = this.isLastItem;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "CarModeButtonNavigatorModel(viewId=" + ((Object) this.viewId) + ", text=" + this.text + ", target=" + this.target + ", isLastItem=" + this.isLastItem + ')';
    }

    public /* synthetic */ CarModeButtonNavigatorModel(String str, String str2, String str3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, str2, str3, (i & 8) != 0 ? false : z);
    }

    public final String getText() {
        return this.text;
    }

    public final String getTarget() {
        return this.target;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModeButtonNavigatorModel(String str, String text, String target, boolean z) {
        super(str, null, 2, null);
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(target, "target");
        this.viewId = str;
        this.text = text;
        this.target = target;
        this.isLastItem = z;
    }

    /* JADX INFO: renamed from: isLastItem, reason: from getter */
    public final boolean getIsLastItem() {
        return this.isLastItem;
    }

    public final void setLastItem(boolean z) {
        this.isLastItem = z;
    }
}
