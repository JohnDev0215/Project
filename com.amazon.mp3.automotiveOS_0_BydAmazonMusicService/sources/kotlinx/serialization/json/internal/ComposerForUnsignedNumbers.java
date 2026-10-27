package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Composers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000bH\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\fH\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/serialization/json/internal/ComposerForUnsignedNumbers;", "Lkotlinx/serialization/json/internal/Composer;", "writer", "Lkotlinx/serialization/json/internal/JsonWriter;", "forceQuoting", "", "(Lkotlinx/serialization/json/internal/JsonWriter;Z)V", "print", "", "v", "", "", "", "", "kotlinx-serialization-json"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ComposerForUnsignedNumbers extends Composer {
    private final boolean forceQuoting;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerForUnsignedNumbers(JsonWriter writer, boolean z) {
        super(writer);
        Intrinsics.checkNotNullParameter(writer, "writer");
        this.forceQuoting = z;
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(int v) {
        boolean z = this.forceQuoting;
        int iM2904constructorimpl = UInt.m2904constructorimpl(v);
        if (z) {
            printQuoted(Long.toString(((long) iM2904constructorimpl) & 4294967295L, 10));
        } else {
            print(Long.toString(((long) iM2904constructorimpl) & 4294967295L, 10));
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(long v) {
        boolean z = this.forceQuoting;
        long jM2982constructorimpl = ULong.m2982constructorimpl(v);
        if (z) {
            printQuoted(ComposerForUnsignedNumbers$$ExternalSyntheticBackport5.m(jM2982constructorimpl, 10));
        } else {
            print(ComposerForUnsignedNumbers$$ExternalSyntheticBackport4.m(jM2982constructorimpl, 10));
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(byte v) {
        boolean z = this.forceQuoting;
        String strM2871toStringimpl = UByte.m2871toStringimpl(UByte.m2828constructorimpl(v));
        if (z) {
            printQuoted(strM2871toStringimpl);
        } else {
            print(strM2871toStringimpl);
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(short v) {
        boolean z = this.forceQuoting;
        String strM3131toStringimpl = UShort.m3131toStringimpl(UShort.m3088constructorimpl(v));
        if (z) {
            printQuoted(strM3131toStringimpl);
        } else {
            print(strM3131toStringimpl);
        }
    }
}
