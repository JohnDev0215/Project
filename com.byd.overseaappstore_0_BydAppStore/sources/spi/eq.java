package spi;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavDirections;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareDetailPreviewImages;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class eq {

    @NotNull
    public static final b a = new b(null);

    public static final class a implements NavDirections {
        public final int a;

        @Nullable
        public final SoftwareDetailPreviewImages b;
        public final int c;

        public a() {
            this(0, null, 3, 0 == true ? 1 : 0);
        }

        public a(int i, @Nullable SoftwareDetailPreviewImages softwareDetailPreviewImages) {
            this.a = i;
            this.b = softwareDetailPreviewImages;
            this.c = R.id.action_software_detail_to_software_detail_preview;
        }

        public /* synthetic */ a(int i, SoftwareDetailPreviewImages softwareDetailPreviewImages, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? null : softwareDetailPreviewImages);
        }

        public final boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && Intrinsics.areEqual(this.b, aVar.b);
        }

        @Override // androidx.navigation.NavDirections
        public final int getActionId() {
            return this.c;
        }

        @Override // androidx.navigation.NavDirections
        @NotNull
        public final Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putInt("image_position", this.a);
            if (Parcelable.class.isAssignableFrom(SoftwareDetailPreviewImages.class)) {
                bundle.putParcelable("images_path", (Parcelable) this.b);
            } else if (Serializable.class.isAssignableFrom(SoftwareDetailPreviewImages.class)) {
                bundle.putSerializable("images_path", this.b);
            }
            return bundle;
        }

        public final int hashCode() {
            int iHashCode = Integer.hashCode(this.a) * 31;
            SoftwareDetailPreviewImages softwareDetailPreviewImages = this.b;
            return iHashCode + (softwareDetailPreviewImages == null ? 0 : softwareDetailPreviewImages.hashCode());
        }

        @NotNull
        public final String toString() {
            return "ActionSoftwareDetailToSoftwareDetailPreview(imagePosition=" + this.a + ", imagesPath=" + this.b + ")";
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private eq() {
    }
}
