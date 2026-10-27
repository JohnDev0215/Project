package com.byd.overseaappstore.ui.view.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareDetailPreviewImages;
import java.io.Serializable;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\bB\u001d\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/SoftwareDetailPreviewImageFragmentArgs;", "Landroidx/navigation/NavArgs;", "", "imagePosition", "Lcom/byd/overseaappstore/module/ability/data/upper/SoftwareDetailPreviewImages;", "imagesPath", "<init>", "(ILcom/byd/overseaappstore/module/ability/data/upper/SoftwareDetailPreviewImages;)V", "a", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class SoftwareDetailPreviewImageFragmentArgs implements NavArgs {

    @NotNull
    public static final a c = new a(null);

    /* JADX INFO: renamed from: a, reason: from toString */
    public final int imagePosition;

    /* JADX INFO: renamed from: b, reason: from toString */
    @Nullable
    public final SoftwareDetailPreviewImages imagesPath;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SoftwareDetailPreviewImageFragmentArgs() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    public SoftwareDetailPreviewImageFragmentArgs(int i, @Nullable SoftwareDetailPreviewImages softwareDetailPreviewImages) {
        this.imagePosition = i;
        this.imagesPath = softwareDetailPreviewImages;
    }

    public /* synthetic */ SoftwareDetailPreviewImageFragmentArgs(int i, SoftwareDetailPreviewImages softwareDetailPreviewImages, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? null : softwareDetailPreviewImages);
    }

    @JvmStatic
    @NotNull
    public static final SoftwareDetailPreviewImageFragmentArgs fromBundle(@NotNull Bundle bundle) {
        SoftwareDetailPreviewImages softwareDetailPreviewImages;
        Objects.requireNonNull(c);
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        bundle.setClassLoader(SoftwareDetailPreviewImageFragmentArgs.class.getClassLoader());
        int i = bundle.containsKey("image_position") ? bundle.getInt("image_position") : 0;
        if (!bundle.containsKey("images_path")) {
            softwareDetailPreviewImages = null;
        } else {
            if (!Parcelable.class.isAssignableFrom(SoftwareDetailPreviewImages.class) && !Serializable.class.isAssignableFrom(SoftwareDetailPreviewImages.class)) {
                throw new UnsupportedOperationException(d.f(SoftwareDetailPreviewImages.class.getName(), " must implement Parcelable or Serializable or must be an Enum."));
            }
            softwareDetailPreviewImages = (SoftwareDetailPreviewImages) bundle.get("images_path");
        }
        return new SoftwareDetailPreviewImageFragmentArgs(i, softwareDetailPreviewImages);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SoftwareDetailPreviewImageFragmentArgs)) {
            return false;
        }
        SoftwareDetailPreviewImageFragmentArgs softwareDetailPreviewImageFragmentArgs = (SoftwareDetailPreviewImageFragmentArgs) obj;
        return this.imagePosition == softwareDetailPreviewImageFragmentArgs.imagePosition && Intrinsics.areEqual(this.imagesPath, softwareDetailPreviewImageFragmentArgs.imagesPath);
    }

    public final int hashCode() {
        int iHashCode = Integer.hashCode(this.imagePosition) * 31;
        SoftwareDetailPreviewImages softwareDetailPreviewImages = this.imagesPath;
        return iHashCode + (softwareDetailPreviewImages == null ? 0 : softwareDetailPreviewImages.hashCode());
    }

    @NotNull
    public final String toString() {
        return "SoftwareDetailPreviewImageFragmentArgs(imagePosition=" + this.imagePosition + ", imagesPath=" + this.imagesPath + ")";
    }
}
