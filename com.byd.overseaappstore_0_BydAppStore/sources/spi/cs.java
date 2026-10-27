package spi;

import android.os.Bundle;
import androidx.navigation.NavDirections;
import com.byd.overseaappstore.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class cs {

    @NotNull
    public static final b a = new b(null);

    public static final class a implements NavDirections {

        @NotNull
        public final String a;
        public final int b;

        public a() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public a(@NotNull String protocolType) {
            Intrinsics.checkNotNullParameter(protocolType, "protocolType");
            this.a = protocolType;
            this.b = R.id.action_start_up_to_protocol;
        }

        public /* synthetic */ a(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str);
        }

        public final boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof a) && Intrinsics.areEqual(this.a, ((a) obj).a);
        }

        @Override // androidx.navigation.NavDirections
        public final int getActionId() {
            return this.b;
        }

        @Override // androidx.navigation.NavDirections
        @NotNull
        public final Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("protocol_type", this.a);
            return bundle;
        }

        public final int hashCode() {
            return this.a.hashCode();
        }

        @NotNull
        public final String toString() {
            return e.d("ActionStartUpToProtocol(protocolType=", this.a, ")");
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final NavDirections a() {
            Intrinsics.checkNotNullParameter("PROTOCOL_FRAG_TYPE_STARTUP", "protocolType");
            return new a("PROTOCOL_FRAG_TYPE_STARTUP");
        }
    }

    private cs() {
    }
}
