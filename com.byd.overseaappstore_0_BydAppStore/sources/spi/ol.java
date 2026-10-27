package spi;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public interface ol {

    @JvmField
    @NotNull
    public static final a.C0137a a;

    public static final class a {

        /* JADX INFO: renamed from: spi.ol$a$a, reason: collision with other inner class name */
        public static final class C0137a implements ol {
        }

        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        new a(null);
        a = new a.C0137a();
    }
}
