package spi;

import java.io.IOException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.ke;

/* JADX INFO: loaded from: classes2.dex */
public final class le {
    public long a;

    @NotNull
    public final l2 b;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        new a(null);
    }

    public le(@NotNull l2 source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.b = source;
        this.a = 262144;
    }

    @NotNull
    public final ke a() throws IOException {
        ke.a aVar = new ke.a();
        while (true) {
            String strK = this.b.k(this.a);
            this.a -= (long) strK.length();
            if (strK.length() == 0) {
                return aVar.d();
            }
            aVar.b(strK);
        }
    }
}
