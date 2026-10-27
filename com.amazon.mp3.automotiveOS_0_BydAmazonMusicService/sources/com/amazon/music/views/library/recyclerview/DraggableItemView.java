package com.amazon.music.views.library.recyclerview;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: DraggableItemView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0018\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/DraggableItemView;", "", "isDraggingEnabled", "", "setOnDraggedListener", "", "onDragged", "Lkotlin/Function0;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface DraggableItemView {
    boolean isDraggingEnabled();

    void setOnDraggedListener(Function0<Unit> onDragged);
}
