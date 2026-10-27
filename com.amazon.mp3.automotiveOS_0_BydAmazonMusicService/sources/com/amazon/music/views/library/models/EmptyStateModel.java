package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EmptyStateModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/views/library/models/EmptyStateModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "title", "", "message", "buttonType", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/models/EmptyStateButtonType;)V", "getButtonType", "()Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "getMessage", "()Ljava/lang/String;", "getTitle", "equals", "", "other", "", "hashCode", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EmptyStateModel extends BaseViewModel {
    private final EmptyStateButtonType buttonType;
    private final String message;
    private final String title;

    public final String getTitle() {
        return this.title;
    }

    public final String getMessage() {
        return this.message;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EmptyStateModel(String title, String str, EmptyStateButtonType emptyStateButtonType) {
        super(null, null, 2, null);
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.message = str;
        this.buttonType = emptyStateButtonType;
    }

    public final EmptyStateButtonType getButtonType() {
        return this.buttonType;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(EmptyStateModel.class, other == null ? null : other.getClass()) || !super.equals(other)) {
            return false;
        }
        if (other != null) {
            EmptyStateModel emptyStateModel = (EmptyStateModel) other;
            return Intrinsics.areEqual(this.title, emptyStateModel.title) && Intrinsics.areEqual(this.message, emptyStateModel.message) && Intrinsics.areEqual(this.buttonType, emptyStateModel.buttonType);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.EmptyStateModel");
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        int iHashCode = ((super.hashCode() * 31) + this.title.hashCode()) * 31;
        String str = this.message;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        EmptyStateButtonType emptyStateButtonType = this.buttonType;
        return iHashCode2 + (emptyStateButtonType != null ? emptyStateButtonType.hashCode() : 0);
    }
}
