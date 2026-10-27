package spi;

import kotlin.collections.ArraysKt;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class vo {

    @JvmField
    @NotNull
    public final byte[] a;

    @JvmField
    public int b;

    @JvmField
    public int c;

    @JvmField
    public boolean d;

    @JvmField
    public boolean e;

    @JvmField
    @Nullable
    public vo f;

    @JvmField
    @Nullable
    public vo g;

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

    public vo() {
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    public vo(@NotNull byte[] data, int i, int i2, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.a = data;
        this.b = i;
        this.c = i2;
        this.d = z;
        this.e = z2;
    }

    @Nullable
    public final vo a() {
        vo voVar = this.f;
        if (voVar == this) {
            voVar = null;
        }
        vo voVar2 = this.g;
        Intrinsics.checkNotNull(voVar2);
        voVar2.f = this.f;
        vo voVar3 = this.f;
        Intrinsics.checkNotNull(voVar3);
        voVar3.g = this.g;
        this.f = null;
        this.g = null;
        return voVar;
    }

    @NotNull
    public final vo b(@NotNull vo segment) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        segment.g = this;
        segment.f = this.f;
        vo voVar = this.f;
        Intrinsics.checkNotNull(voVar);
        voVar.g = segment;
        this.f = segment;
        return segment;
    }

    @NotNull
    public final vo c() {
        this.d = true;
        return new vo(this.a, this.b, this.c, true, false);
    }

    public final void d(@NotNull vo sink, int i) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (!sink.e) {
            throw new IllegalStateException("only owner can write".toString());
        }
        int i2 = sink.c;
        int i3 = i2 + i;
        if (i3 > 8192) {
            if (sink.d) {
                throw new IllegalArgumentException();
            }
            int i4 = sink.b;
            if (i3 - i4 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = sink.a;
            ArraysKt___ArraysJvmKt.copyInto$default(bArr, bArr, 0, i4, i2, 2, (Object) null);
            sink.c -= sink.b;
            sink.b = 0;
        }
        byte[] bArr2 = this.a;
        byte[] bArr3 = sink.a;
        int i5 = sink.c;
        int i6 = this.b;
        ArraysKt.copyInto(bArr2, bArr3, i5, i6, i6 + i);
        sink.c += i;
        this.b += i;
    }
}
