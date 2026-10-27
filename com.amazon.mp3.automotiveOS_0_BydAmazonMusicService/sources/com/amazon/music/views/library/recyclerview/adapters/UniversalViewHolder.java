package com.amazon.music.views.library.recyclerview.adapters;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UniversalViewHolder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R(\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/adapters/UniversalViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "<set-?>", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "model", "getModel", "()Lcom/amazon/music/views/library/models/base/BaseViewModel;", "setModel$DMMViewLibrary_release", "(Lcom/amazon/music/views/library/models/base/BaseViewModel;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UniversalViewHolder extends RecyclerView.ViewHolder {
    private BaseViewModel model;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UniversalViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkNotNullParameter(itemView, "itemView");
    }

    public final BaseViewModel getModel() {
        return this.model;
    }

    public final void setModel$DMMViewLibrary_release(BaseViewModel baseViewModel) {
        this.model = baseViewModel;
    }
}
