package com.amazon.music.skyfire.ui.ktx;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: ViewGroup.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\tH\u0096\u0002J\t\u0010\n\u001a\u00020\u0002H\u0096\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/skyfire/ui/ktx/ViewGroupIterator;", "", "Landroid/view/View;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "index", "", "hasNext", "", "next", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
final class ViewGroupIterator implements Iterator<View>, KMappedMarker {
    private int index;
    private final ViewGroup parent;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public ViewGroupIterator(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.parent = parent;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.parent.getChildCount();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public View next() {
        ViewGroup viewGroup = this.parent;
        int i = this.index;
        this.index = i + 1;
        View childAt = viewGroup.getChildAt(i);
        Intrinsics.checkNotNullExpressionValue(childAt, "parent.getChildAt(index++)");
        return childAt;
    }
}
