package com.amazon.music.views.library.recyclerview.adapters;

import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseModelChange.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003B\u001f\u0012\u0006\u0010\u000b\u001a\u00028\u0000\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0014\u001a\u00020\u0006¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0003HÖ\u0003R\u0017\u0010\u000b\u001a\u00028\u00008\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/adapters/BaseModelChange;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "T", "", "", "toString", "", "hashCode", "other", "", "equals", "model", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "getModel", "()Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "adapter", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "getAdapter", "()Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "payload", "I", "getPayload", "()I", "<init>", "(Lcom/amazon/music/views/library/models/base/BaseViewModel;Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;I)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class BaseModelChange<T extends BaseViewModel> {
    private final UniversalAdapter adapter;
    private final T model;
    private final int payload;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BaseModelChange)) {
            return false;
        }
        BaseModelChange baseModelChange = (BaseModelChange) other;
        return Intrinsics.areEqual(this.model, baseModelChange.model) && Intrinsics.areEqual(this.adapter, baseModelChange.adapter) && this.payload == baseModelChange.payload;
    }

    public int hashCode() {
        return (((this.model.hashCode() * 31) + this.adapter.hashCode()) * 31) + Integer.hashCode(this.payload);
    }

    public String toString() {
        return "BaseModelChange(model=" + this.model + ", adapter=" + this.adapter + ", payload=" + this.payload + ')';
    }

    public BaseModelChange(T model, UniversalAdapter adapter, int i) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        this.model = model;
        this.adapter = adapter;
        this.payload = i;
    }

    public final T getModel() {
        return this.model;
    }

    public final UniversalAdapter getAdapter() {
        return this.adapter;
    }

    public final int getPayload() {
        return this.payload;
    }
}
