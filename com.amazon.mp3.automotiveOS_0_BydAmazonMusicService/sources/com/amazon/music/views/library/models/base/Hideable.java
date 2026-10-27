package com.amazon.music.views.library.models.base;

import kotlin.Metadata;

/* JADX INFO: compiled from: Hideable.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&R\u001c\u0010\u0003\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/views/library/models/base/Hideable;", "", "", "hidden", "", "updateVisibility", "getHidden", "()Z", "setHidden", "(Z)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public interface Hideable {
    boolean getHidden();

    void updateVisibility(boolean hidden);
}
