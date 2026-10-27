package com.byd.overseaappstore.module.ability.data.rsp;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/rsp/ImagePathRsp;", "", "image_path", "", "(Ljava/lang/String;)V", "getImage_path", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class ImagePathRsp {

    @Nullable
    private final String image_path;

    public ImagePathRsp() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ImagePathRsp(@Nullable String str) {
        this.image_path = str;
    }

    public /* synthetic */ ImagePathRsp(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    public static /* synthetic */ ImagePathRsp copy$default(ImagePathRsp imagePathRsp, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = imagePathRsp.image_path;
        }
        return imagePathRsp.copy(str);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getImage_path() {
        return this.image_path;
    }

    @NotNull
    public final ImagePathRsp copy(@Nullable String image_path) {
        return new ImagePathRsp(image_path);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ImagePathRsp) && Intrinsics.areEqual(this.image_path, ((ImagePathRsp) other).image_path);
    }

    @Nullable
    public final String getImage_path() {
        return this.image_path;
    }

    public int hashCode() {
        String str = this.image_path;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    @NotNull
    public String toString() {
        return e.d("ImagePathRsp(image_path=", this.image_path, ")");
    }
}
