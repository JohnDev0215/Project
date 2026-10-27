package com.amazon.music.views.library.recyclerview.indexer;

import android.content.Context;
import android.view.View;
import kotlin.Metadata;

/* JADX INFO: compiled from: ScrollerViewProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&J\n\u0010\b\u001a\u0004\u0018\u00010\u0006H&R(\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u00028\u0004@BX\u0084\u000e¢\u0006\f\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/indexer/ScrollerViewProvider;", "", "Lcom/amazon/music/views/library/recyclerview/indexer/FastScroller;", "scroller", "", "setFastScroller", "Landroid/view/View;", "provideHandleView", "provideScrollBar", "<set-?>", "Lcom/amazon/music/views/library/recyclerview/indexer/FastScroller;", "getScroller", "()Lcom/amazon/music/views/library/recyclerview/indexer/FastScroller;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context", "<init>", "()V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public abstract class ScrollerViewProvider {
    private FastScroller scroller;

    public abstract View provideHandleView();

    public abstract View provideScrollBar();

    public final void setFastScroller(FastScroller scroller) {
        this.scroller = scroller;
    }

    protected final Context getContext() {
        FastScroller fastScroller = this.scroller;
        if (fastScroller == null) {
            return null;
        }
        return fastScroller.getContext();
    }
}
