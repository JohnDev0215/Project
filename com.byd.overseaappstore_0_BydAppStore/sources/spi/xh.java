package spi;

import android.os.Bundle;
import androidx.navigation.NavDirections;
import com.byd.overseaappstore.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class xh {

    @NotNull
    public static final b a = new b(null);

    public static final class a implements NavDirections {
        public final int a;
        public final int b;

        public a() {
            this(0, 1, null);
        }

        public a(int i) {
            this.a = i;
            this.b = R.id.action_management_to_software_detail;
        }

        public /* synthetic */ a(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 0 : i);
        }

        public final boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof a) && this.a == ((a) obj).a;
        }

        @Override // androidx.navigation.NavDirections
        public final int getActionId() {
            return this.b;
        }

        @Override // androidx.navigation.NavDirections
        @NotNull
        public final Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putInt("software_id", this.a);
            return bundle;
        }

        public final int hashCode() {
            return Integer.hashCode(this.a);
        }

        @NotNull
        public final String toString() {
            return xi.e("ActionManagementToSoftwareDetail(softwareId=", this.a, ")");
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private xh() {
    }
}
