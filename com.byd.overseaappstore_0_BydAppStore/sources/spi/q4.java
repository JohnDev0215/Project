package spi;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public interface q4 {

    @JvmField
    @NotNull
    public static final a.C0138a a;

    public static final class a {

        /* JADX INFO: renamed from: spi.q4$a$a, reason: collision with other inner class name */
        public static final class C0138a implements q4 {
            @Override // spi.q4
            public final void a(@NotNull hf url, @NotNull List<p4> cookies) {
                Intrinsics.checkNotNullParameter(url, "url");
                Intrinsics.checkNotNullParameter(cookies, "cookies");
            }

            @Override // spi.q4
            @NotNull
            public final List<p4> b(@NotNull hf url) {
                Intrinsics.checkNotNullParameter(url, "url");
                return CollectionsKt.emptyList();
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
        a = new a.C0138a();
    }

    void a(@NotNull hf hfVar, @NotNull List<p4> list);

    @NotNull
    List<p4> b(@NotNull hf hfVar);
}
