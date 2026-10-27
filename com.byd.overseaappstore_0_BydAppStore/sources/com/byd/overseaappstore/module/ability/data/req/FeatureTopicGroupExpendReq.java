package com.byd.overseaappstore.module.ability.data.req;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.d;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/req/FeatureTopicGroupExpendReq;", "", "featuredThemeId", "", TypedValues.CycleType.S_WAVE_OFFSET, "pageSize", "(III)V", "getFeaturedThemeId", "()I", "getOffset", "getPageSize", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class FeatureTopicGroupExpendReq {
    private final int featuredThemeId;
    private final int offset;
    private final int pageSize;

    public FeatureTopicGroupExpendReq(int i, int i2, int i3) {
        this.featuredThemeId = i;
        this.offset = i2;
        this.pageSize = i3;
    }

    public /* synthetic */ FeatureTopicGroupExpendReq(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 20 : i3);
    }

    public static /* synthetic */ FeatureTopicGroupExpendReq copy$default(FeatureTopicGroupExpendReq featureTopicGroupExpendReq, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = featureTopicGroupExpendReq.featuredThemeId;
        }
        if ((i4 & 2) != 0) {
            i2 = featureTopicGroupExpendReq.offset;
        }
        if ((i4 & 4) != 0) {
            i3 = featureTopicGroupExpendReq.pageSize;
        }
        return featureTopicGroupExpendReq.copy(i, i2, i3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getFeaturedThemeId() {
        return this.featuredThemeId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getOffset() {
        return this.offset;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getPageSize() {
        return this.pageSize;
    }

    @NotNull
    public final FeatureTopicGroupExpendReq copy(int featuredThemeId, int offset, int pageSize) {
        return new FeatureTopicGroupExpendReq(featuredThemeId, offset, pageSize);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeatureTopicGroupExpendReq)) {
            return false;
        }
        FeatureTopicGroupExpendReq featureTopicGroupExpendReq = (FeatureTopicGroupExpendReq) other;
        return this.featuredThemeId == featureTopicGroupExpendReq.featuredThemeId && this.offset == featureTopicGroupExpendReq.offset && this.pageSize == featureTopicGroupExpendReq.pageSize;
    }

    public final int getFeaturedThemeId() {
        return this.featuredThemeId;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getPageSize() {
        return this.pageSize;
    }

    public int hashCode() {
        return Integer.hashCode(this.pageSize) + ((Integer.hashCode(this.offset) + (Integer.hashCode(this.featuredThemeId) * 31)) * 31);
    }

    @NotNull
    public String toString() {
        int i = this.featuredThemeId;
        int i2 = this.offset;
        return d.h(e.l("FeatureTopicGroupExpendReq(featuredThemeId=", i, ", offset=", i2, ", pageSize="), this.pageSize, ")");
    }
}
