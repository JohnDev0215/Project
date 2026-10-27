package com.byd.overseaappstore.module.ability.data.upper;

import com.byd.car.locale.ccTLD;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/upper/Banner;", "", ccTLD.INDONESIA, "", "avatar", "", "(ILjava/lang/String;)V", "getAvatar", "()Ljava/lang/String;", "getId", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class Banner {

    @NotNull
    private final String avatar;
    private final int id;

    public Banner() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    public Banner(int i, @NotNull String avatar) {
        Intrinsics.checkNotNullParameter(avatar, "avatar");
        this.id = i;
        this.avatar = avatar;
    }

    public /* synthetic */ Banner(int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str);
    }

    public static /* synthetic */ Banner copy$default(Banner banner, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = banner.id;
        }
        if ((i2 & 2) != 0) {
            str = banner.avatar;
        }
        return banner.copy(i, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAvatar() {
        return this.avatar;
    }

    @NotNull
    public final Banner copy(int id, @NotNull String avatar) {
        Intrinsics.checkNotNullParameter(avatar, "avatar");
        return new Banner(id, avatar);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Banner)) {
            return false;
        }
        Banner banner = (Banner) other;
        return this.id == banner.id && Intrinsics.areEqual(this.avatar, banner.avatar);
    }

    @NotNull
    public final String getAvatar() {
        return this.avatar;
    }

    public final int getId() {
        return this.id;
    }

    public int hashCode() {
        return this.avatar.hashCode() + (Integer.hashCode(this.id) * 31);
    }

    @NotNull
    public String toString() {
        return "Banner(id=" + this.id + ", avatar=" + this.avatar + ")";
    }
}
