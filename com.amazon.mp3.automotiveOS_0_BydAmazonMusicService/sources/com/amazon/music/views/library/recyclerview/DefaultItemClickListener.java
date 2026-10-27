package com.amazon.music.views.library.recyclerview;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultItemClickListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/DefaultItemClickListener;", "Landroid/view/View$OnClickListener;", "itemClickListener", "Lcom/amazon/music/views/library/recyclerview/OnItemClickListener;", "position", "", "(Lcom/amazon/music/views/library/recyclerview/OnItemClickListener;I)V", "getPosition", "()I", "onClick", "", "view", "Landroid/view/View;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultItemClickListener implements View.OnClickListener {
    private final OnItemClickListener itemClickListener;
    private final int position;

    public DefaultItemClickListener(OnItemClickListener onItemClickListener, int i) {
        this.itemClickListener = onItemClickListener;
        this.position = i;
    }

    public final int getPosition() {
        return this.position;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        OnItemClickListener onItemClickListener = this.itemClickListener;
        if (onItemClickListener == null) {
            return;
        }
        onItemClickListener.onItemClick(view, this.position);
    }
}
