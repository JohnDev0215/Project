package spi;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class ie {

    @JvmField
    @NotNull
    public static final ByteString d;

    @JvmField
    @NotNull
    public static final ByteString e;

    @JvmField
    @NotNull
    public static final ByteString f;

    @JvmField
    @NotNull
    public static final ByteString g;

    @JvmField
    @NotNull
    public static final ByteString h;

    @JvmField
    @NotNull
    public static final ByteString i;

    @JvmField
    public final int a;

    @JvmField
    @NotNull
    public final ByteString b;

    @JvmField
    @NotNull
    public final ByteString c;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        new a(null);
        ByteString.Companion companion = ByteString.INSTANCE;
        d = companion.d(":");
        e = companion.d(":status");
        f = companion.d(":method");
        g = companion.d(":path");
        h = companion.d(":scheme");
        i = companion.d(":authority");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ie(@NotNull String name, @NotNull String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        ByteString.Companion companion = ByteString.INSTANCE;
        this(companion.d(name), companion.d(value));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ie(@NotNull ByteString name, @NotNull String value) {
        this(name, ByteString.INSTANCE.d(value));
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
    }

    public ie(@NotNull ByteString name, @NotNull ByteString value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        this.b = name;
        this.c = value;
        this.a = value.size() + name.size() + 32;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ie)) {
            return false;
        }
        ie ieVar = (ie) obj;
        return Intrinsics.areEqual(this.b, ieVar.b) && Intrinsics.areEqual(this.c, ieVar.c);
    }

    public final int hashCode() {
        ByteString byteString = this.b;
        int iHashCode = (byteString != null ? byteString.hashCode() : 0) * 31;
        ByteString byteString2 = this.c;
        return iHashCode + (byteString2 != null ? byteString2.hashCode() : 0);
    }

    @NotNull
    public final String toString() {
        return this.b.utf8() + ": " + this.c.utf8();
    }
}
