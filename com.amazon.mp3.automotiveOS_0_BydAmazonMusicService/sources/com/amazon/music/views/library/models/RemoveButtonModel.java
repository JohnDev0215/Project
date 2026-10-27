package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.ClearableContainer;
import java.lang.ref.WeakReference;
import kotlin.Metadata;

/* JADX INFO: compiled from: RemoveButtonModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bR*\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/amazon/music/views/library/models/RemoveButtonModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/music/views/library/models/base/ClearableContainer;", "clearableContainer", "Ljava/lang/ref/WeakReference;", "getClearableContainer", "()Ljava/lang/ref/WeakReference;", "setClearableContainer", "(Ljava/lang/ref/WeakReference;)V", "<init>", "()V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class RemoveButtonModel extends BaseViewModel {
    private WeakReference<ClearableContainer> clearableContainer;

    public RemoveButtonModel() {
        super(null, null, 2, null);
    }

    public final void setClearableContainer(WeakReference<ClearableContainer> weakReference) {
        this.clearableContainer = weakReference;
    }
}
