package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.Hideable;
import com.amazon.music.views.library.models.base.Insettable;
import com.amazon.music.views.library.views.TextRowItemView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextRowItemModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003BK\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b'\u0010(J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0013\u0010\n\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\f\u001a\u00020\u000bH\u0016R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0019\u0010\u0017R\u0017\u0010\u001b\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\"\u0010\u0005\u001a\u00020\u00048\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010$\u001a\u00020\u00048\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b$\u0010\u001f\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#¨\u0006)"}, d2 = {"Lcom/amazon/music/views/library/models/TextRowItemModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lcom/amazon/music/views/library/models/base/Insettable;", "Lcom/amazon/music/views/library/models/base/Hideable;", "", "hidden", "", "updateVisibility", "", "other", "equals", "", "hashCode", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "text", "getText", "shouldCenter", "Ljava/lang/Boolean;", "getShouldCenter", "()Ljava/lang/Boolean;", "applyLeftMargin", "getApplyLeftMargin", "Lcom/amazon/music/views/library/views/TextRowItemView$TextStyle;", "textStyle", "Lcom/amazon/music/views/library/views/TextRowItemView$TextStyle;", "getTextStyle", "()Lcom/amazon/music/views/library/views/TextRowItemView$TextStyle;", "Z", "getHidden", "()Z", "setHidden", "(Z)V", "insetHorizontally", "getInsetHorizontally", "setInsetHorizontally", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/music/views/library/views/TextRowItemView$TextStyle;Z)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class TextRowItemModel extends BaseViewModel implements Insettable, Hideable {
    private final Boolean applyLeftMargin;
    private boolean hidden;
    private boolean insetHorizontally;
    private final Boolean shouldCenter;
    private final String text;
    private final TextRowItemView.TextStyle textStyle;
    private final String viewId;

    public TextRowItemModel() {
        this(null, null, null, null, null, false, 63, null);
    }

    public final String getText() {
        return this.text;
    }

    public /* synthetic */ TextRowItemModel(String str, String str2, Boolean bool, Boolean bool2, TextRowItemView.TextStyle textStyle, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? Boolean.FALSE : bool, (i & 8) == 0 ? bool2 : null, (i & 16) != 0 ? TextRowItemView.TextStyle.HEADER : textStyle, (i & 32) != 0 ? false : z);
    }

    public final Boolean getShouldCenter() {
        return this.shouldCenter;
    }

    public final Boolean getApplyLeftMargin() {
        return this.applyLeftMargin;
    }

    public final TextRowItemView.TextStyle getTextStyle() {
        return this.textStyle;
    }

    @Override // com.amazon.music.views.library.models.base.Hideable
    public boolean getHidden() {
        return this.hidden;
    }

    public void setHidden(boolean z) {
        this.hidden = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextRowItemModel(String str, String str2, Boolean bool, Boolean bool2, TextRowItemView.TextStyle textStyle, boolean z) {
        super(str, null, 2, null);
        Intrinsics.checkNotNullParameter(textStyle, "textStyle");
        this.viewId = str;
        this.text = str2;
        this.shouldCenter = bool;
        this.applyLeftMargin = bool2;
        this.textStyle = textStyle;
        this.hidden = z;
    }

    public boolean getInsetHorizontally() {
        return this.insetHorizontally;
    }

    @Override // com.amazon.music.views.library.models.base.Insettable
    public void setInsetHorizontally(boolean z) {
        this.insetHorizontally = z;
    }

    @Override // com.amazon.music.views.library.models.base.Hideable
    public void updateVisibility(boolean hidden) {
        if (getHidden() == hidden) {
            return;
        }
        setHidden(hidden);
        notifyChanged(5);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }
        if (other != null) {
            return getHidden() == ((TextRowItemModel) other).getHidden();
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.TextRowItemModel");
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        return (super.hashCode() * 31) + Boolean.hashCode(getHidden());
    }
}
