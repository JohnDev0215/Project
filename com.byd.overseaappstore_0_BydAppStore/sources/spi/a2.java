package spi;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class a2 {
    public final byte a;
    public final byte b;
    public byte c;

    public a2() {
        this((byte) 0, (byte) 0, (byte) 0, 7, null);
    }

    public a2(byte b, byte b2, byte b3) {
        this.a = b;
        this.b = b2;
        this.c = b3;
    }

    public /* synthetic */ a2(byte b, byte b2, byte b3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (byte) 0 : b, (i & 2) != 0 ? (byte) 0 : b2, (i & 4) != 0 ? (byte) 0 : b3);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a2)) {
            return false;
        }
        a2 a2Var = (a2) obj;
        return this.a == a2Var.a && this.b == a2Var.b && this.c == a2Var.c;
    }

    public final int hashCode() {
        return Byte.hashCode(this.c) + ((Byte.hashCode(this.b) + (Byte.hashCode(this.a) * 31)) * 31);
    }

    @NotNull
    public final String toString() {
        byte b = this.a;
        byte b2 = this.b;
        return d.h(e.l("BizSrc(channel=", b, ", bizType=", b2, ", bizWay="), this.c, ")");
    }
}
