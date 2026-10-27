package com.byd.overseaappstore.module.ability.data;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u00032\u00020\u0004B!\u0012\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tR\u0018\u0010\u0005\u001a\u0004\u0018\u00018\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/RetUiState;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/byd/overseaappstore/module/ability/data/DataUiState;", "Lcom/byd/overseaappstore/module/ability/data/ViewUiState;", "Lcom/byd/overseaappstore/module/ability/data/RetState;", "data", "ret", "", "visible", "(Ljava/lang/Object;Ljava/lang/Boolean;Z)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getRet", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getVisible", "()Z", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class RetUiState<T> implements DataUiState<T>, ViewUiState, RetState {

    @Nullable
    private final T data;

    @Nullable
    private final Boolean ret;
    private final boolean visible;

    public RetUiState(@Nullable T t, @Nullable Boolean bool, boolean z) {
        this.data = t;
        this.ret = bool;
        this.visible = z;
    }

    @Override // com.byd.overseaappstore.module.ability.data.DataUiState
    @Nullable
    public T getData() {
        return this.data;
    }

    @Override // com.byd.overseaappstore.module.ability.data.RetState
    @Nullable
    public Boolean getRet() {
        return this.ret;
    }

    @Override // com.byd.overseaappstore.module.ability.data.ViewUiState
    public boolean getVisible() {
        return this.visible;
    }
}
