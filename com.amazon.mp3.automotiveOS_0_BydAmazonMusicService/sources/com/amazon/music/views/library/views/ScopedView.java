package com.amazon.music.views.library.views;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ScopedViews.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&R\u001c\u0010\t\u001a\u00020\u00048&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/amazon/music/views/library/views/ScopedView;", "", "", "clear", "Lkotlinx/coroutines/CoroutineScope;", "getLimitedScope", "()Lkotlinx/coroutines/CoroutineScope;", "setLimitedScope", "(Lkotlinx/coroutines/CoroutineScope;)V", "limitedScope", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public interface ScopedView {
    void clear();

    CoroutineScope getLimitedScope();
}
