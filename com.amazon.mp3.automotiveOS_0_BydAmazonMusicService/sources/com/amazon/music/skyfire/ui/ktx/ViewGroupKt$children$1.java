package com.amazon.music.skyfire.ui.ktx;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: ViewGroup.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0004H\u0096\u0002¨\u0006\u0005"}, d2 = {"com/amazon/music/skyfire/ui/ktx/ViewGroupKt$children$1", "", "Landroid/view/View;", "iterator", "Lcom/amazon/music/skyfire/ui/ktx/ViewGroupIterator;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ViewGroupKt$children$1 implements Iterable<View>, KMappedMarker {
    final /* synthetic */ ViewGroup $this_children;

    ViewGroupKt$children$1(ViewGroup viewGroup) {
        this.$this_children = viewGroup;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: iterator, reason: merged with bridge method [inline-methods] */
    public Iterator<View> iterator2() {
        return new ViewGroupIterator(this.$this_children);
    }
}
