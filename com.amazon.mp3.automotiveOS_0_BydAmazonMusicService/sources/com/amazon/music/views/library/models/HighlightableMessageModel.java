package com.amazon.music.views.library.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HighlightableMessageModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0013\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/views/library/models/HighlightableMessageModel;", "Lcom/amazon/music/views/library/models/MessageViewModel;", "message", "", "buttonType", "Lcom/amazon/music/views/library/models/ButtonType;", "stringToHighlight", "isEmpty", "", "(Ljava/lang/String;Lcom/amazon/music/views/library/models/ButtonType;Ljava/lang/String;Z)V", "getButtonType", "()Lcom/amazon/music/views/library/models/ButtonType;", "()Z", "getStringToHighlight", "()Ljava/lang/String;", "equals", "other", "", "hashCode", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HighlightableMessageModel extends MessageViewModel {
    private final ButtonType buttonType;
    private final boolean isEmpty;
    private final String stringToHighlight;

    public HighlightableMessageModel() {
        this(null, null, null, false, 15, null);
    }

    public /* synthetic */ HighlightableMessageModel(String str, ButtonType buttonType, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : buttonType, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? false : z);
    }

    public final ButtonType getButtonType() {
        return this.buttonType;
    }

    public final String getStringToHighlight() {
        return this.stringToHighlight;
    }

    public HighlightableMessageModel(String str, ButtonType buttonType, String str2, boolean z) {
        super(null, str, 1, null);
        this.buttonType = buttonType;
        this.stringToHighlight = str2;
        this.isEmpty = z;
    }

    /* JADX INFO: renamed from: isEmpty, reason: from getter */
    public final boolean getIsEmpty() {
        return this.isEmpty;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(HighlightableMessageModel.class, other == null ? null : other.getClass()) || !super.equals(other)) {
            return false;
        }
        if (other != null) {
            HighlightableMessageModel highlightableMessageModel = (HighlightableMessageModel) other;
            return Intrinsics.areEqual(this.stringToHighlight, highlightableMessageModel.stringToHighlight) && Intrinsics.areEqual(getMessage(), highlightableMessageModel.getMessage()) && Intrinsics.areEqual(this.buttonType, highlightableMessageModel.buttonType);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.HighlightableMessageModel");
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        String message = getMessage();
        int iHashCode2 = (iHashCode + (message == null ? 0 : message.hashCode())) * 31;
        ButtonType buttonType = this.buttonType;
        int iHashCode3 = (iHashCode2 + (buttonType == null ? 0 : buttonType.hashCode())) * 31;
        String str = this.stringToHighlight;
        return iHashCode3 + (str != null ? str.hashCode() : 0);
    }
}
