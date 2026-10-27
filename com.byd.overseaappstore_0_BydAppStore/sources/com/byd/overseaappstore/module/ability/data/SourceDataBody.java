package com.byd.overseaappstore.module.ability.data;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B%\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\nJ4\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0000HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/SourceDataBody;", ExifInterface.GPS_DIRECTION_TRUE, "", "ret", "", NotificationCompat.CATEGORY_MESSAGE, "", "apiData", "(ILjava/lang/String;Ljava/lang/Object;)V", "getApiData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getMsg", "()Ljava/lang/String;", "getRet", "()I", "component1", "component2", "component3", "copy", "(ILjava/lang/String;Ljava/lang/Object;)Lcom/byd/overseaappstore/module/ability/data/SourceDataBody;", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SourceDataBody<T> {

    @Nullable
    private final T apiData;

    @NotNull
    private final String msg;
    private final int ret;

    public SourceDataBody() {
        this(0, null, null, 7, null);
    }

    public SourceDataBody(int i, @NotNull String msg, @Nullable T t) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        this.ret = i;
        this.msg = msg;
        this.apiData = t;
    }

    public /* synthetic */ SourceDataBody(int i, String str, Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? null : obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SourceDataBody copy$default(SourceDataBody sourceDataBody, int i, String str, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = sourceDataBody.ret;
        }
        if ((i2 & 2) != 0) {
            str = sourceDataBody.msg;
        }
        if ((i2 & 4) != 0) {
            obj = sourceDataBody.apiData;
        }
        return sourceDataBody.copy(i, str, obj);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getRet() {
        return this.ret;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    @Nullable
    public final T component3() {
        return this.apiData;
    }

    @NotNull
    public final SourceDataBody<T> copy(int ret, @NotNull String msg, @Nullable T apiData) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        return new SourceDataBody<>(ret, msg, apiData);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourceDataBody)) {
            return false;
        }
        SourceDataBody sourceDataBody = (SourceDataBody) other;
        return this.ret == sourceDataBody.ret && Intrinsics.areEqual(this.msg, sourceDataBody.msg) && Intrinsics.areEqual(this.apiData, sourceDataBody.apiData);
    }

    @Nullable
    public final T getApiData() {
        return this.apiData;
    }

    @NotNull
    public final String getMsg() {
        return this.msg;
    }

    public final int getRet() {
        return this.ret;
    }

    public int hashCode() {
        int iB = xi.b(this.msg, Integer.hashCode(this.ret) * 31, 31);
        T t = this.apiData;
        return iB + (t == null ? 0 : t.hashCode());
    }

    @NotNull
    public String toString() {
        int i = this.ret;
        String str = this.msg;
        T t = this.apiData;
        StringBuilder sbH = xi.h("SourceDataBody(ret=", i, ", msg=", str, ", apiData=");
        sbH.append(t);
        sbH.append(")");
        return sbH.toString();
    }
}
