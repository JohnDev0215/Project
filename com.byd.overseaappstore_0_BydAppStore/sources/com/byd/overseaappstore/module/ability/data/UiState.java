package com.byd.overseaappstore.module.ability.data;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u0017\u0012\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0018\u0010\u0004\u001a\u0004\u0018\u00018\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/UiState;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/byd/overseaappstore/module/ability/data/DataUiState;", "Lcom/byd/overseaappstore/module/ability/data/ViewUiState;", "data", "visible", "", "(Ljava/lang/Object;Z)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getVisible", "()Z", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class UiState<T> implements DataUiState<T>, ViewUiState {

    @Nullable
    private final T data;
    private final boolean visible;

    public UiState(@Nullable T t, boolean z) {
        this.data = t;
        this.visible = z;
    }

    @Override // com.byd.overseaappstore.module.ability.data.DataUiState
    @Nullable
    public T getData() {
        return this.data;
    }

    @Override // com.byd.overseaappstore.module.ability.data.ViewUiState
    public boolean getVisible() {
        return this.visible;
    }
}
