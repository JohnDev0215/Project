package spi;

import java.io.IOException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface u0 {

    @JvmField
    @NotNull
    public static final a.C0142a a;

    public static final class a {

        /* JADX INFO: renamed from: spi.u0$a$a, reason: collision with other inner class name */
        public static final class C0142a implements u0 {
            @Override // spi.u0
            @Nullable
            public final im a(@Nullable rn rnVar, @NotNull ln response) {
                Intrinsics.checkNotNullParameter(response, "response");
                return null;
            }
        }

        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        new a(null);
        a = new a.C0142a();
        new eg(0 == true ? 1 : 0, 1, 0 == true ? 1 : 0);
    }

    @Nullable
    im a(@Nullable rn rnVar, @NotNull ln lnVar) throws IOException;
}
