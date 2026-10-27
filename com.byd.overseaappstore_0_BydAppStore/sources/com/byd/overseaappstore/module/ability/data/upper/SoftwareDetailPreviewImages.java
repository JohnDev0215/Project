package com.byd.overseaappstore.module.ability.data.upper;

import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/upper/SoftwareDetailPreviewImages;", "Ljava/io/Serializable;", "previewImages", "", "Lcom/byd/overseaappstore/module/ability/data/upper/ImagePath;", "(Ljava/util/List;)V", "getPreviewImages", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SoftwareDetailPreviewImages implements Serializable {

    @NotNull
    private final List<ImagePath> previewImages;

    public SoftwareDetailPreviewImages(@NotNull List<ImagePath> previewImages) {
        Intrinsics.checkNotNullParameter(previewImages, "previewImages");
        this.previewImages = previewImages;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SoftwareDetailPreviewImages copy$default(SoftwareDetailPreviewImages softwareDetailPreviewImages, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = softwareDetailPreviewImages.previewImages;
        }
        return softwareDetailPreviewImages.copy(list);
    }

    @NotNull
    public final List<ImagePath> component1() {
        return this.previewImages;
    }

    @NotNull
    public final SoftwareDetailPreviewImages copy(@NotNull List<ImagePath> previewImages) {
        Intrinsics.checkNotNullParameter(previewImages, "previewImages");
        return new SoftwareDetailPreviewImages(previewImages);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SoftwareDetailPreviewImages) && Intrinsics.areEqual(this.previewImages, ((SoftwareDetailPreviewImages) other).previewImages);
    }

    @NotNull
    public final List<ImagePath> getPreviewImages() {
        return this.previewImages;
    }

    public int hashCode() {
        return this.previewImages.hashCode();
    }

    @NotNull
    public String toString() {
        return "SoftwareDetailPreviewImages(previewImages=" + this.previewImages + ")";
    }
}
