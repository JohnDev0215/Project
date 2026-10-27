package com.byd.overseaappstore.module.ability.util.ipc;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0001¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\bJ\u000e\u0010\f\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\bJ.\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00002\b\b\u0002\u0010\u0005\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/ipc/EnterAppData;", ExifInterface.GPS_DIRECTION_TRUE, "K", "", "data", "state", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getState", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/byd/overseaappstore/module/ability/util/ipc/EnterAppData;", "equals", "", "other", "hashCode", "", "toString", "", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class EnterAppData<T, K> {
    private final T data;
    private final K state;

    public EnterAppData(T t, K k) {
        this.data = t;
        this.state = k;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EnterAppData copy$default(EnterAppData enterAppData, Object obj, Object obj2, int i, Object obj3) {
        if ((i & 1) != 0) {
            obj = enterAppData.data;
        }
        if ((i & 2) != 0) {
            obj2 = enterAppData.state;
        }
        return enterAppData.copy(obj, obj2);
    }

    public final T component1() {
        return this.data;
    }

    public final K component2() {
        return this.state;
    }

    @NotNull
    public final EnterAppData<T, K> copy(T data, K state) {
        return new EnterAppData<>(data, state);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EnterAppData)) {
            return false;
        }
        EnterAppData enterAppData = (EnterAppData) other;
        return Intrinsics.areEqual(this.data, enterAppData.data) && Intrinsics.areEqual(this.state, enterAppData.state);
    }

    public final T getData() {
        return this.data;
    }

    public final K getState() {
        return this.state;
    }

    public int hashCode() {
        T t = this.data;
        int iHashCode = (t == null ? 0 : t.hashCode()) * 31;
        K k = this.state;
        return iHashCode + (k != null ? k.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "EnterAppData(data=" + this.data + ", state=" + this.state + ")";
    }
}
