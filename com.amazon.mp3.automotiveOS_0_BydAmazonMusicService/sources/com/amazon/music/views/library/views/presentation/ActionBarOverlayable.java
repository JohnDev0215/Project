package com.amazon.music.views.library.views.presentation;

import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;

/* JADX INFO: compiled from: ActionBarOverlayable.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/views/presentation/ActionBarOverlayable;", "", "isActionBarOverlay", "", "()Z", "setActionBarOverlay", "(Z)V", "setContentOffset", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "offset", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ActionBarOverlayable {
    boolean isActionBarOverlay();

    void setActionBarOverlay(boolean z);

    RecyclerView.ItemDecoration setContentOffset(RecyclerView recyclerView, int offset);
}
