package a.a.a.a.a;

import java.util.Arrays;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @JvmField
    public final b f8a;

    @JvmField
    public final byte[] b;

    @JvmField
    public final long c;

    public a(b bVar, byte[] bArr, long j) {
        this.f8a = bVar;
        this.b = bArr;
        this.c = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return Intrinsics.areEqual(this.f8a, aVar.f8a) && Intrinsics.areEqual(this.b, aVar.b) && this.c == aVar.c;
    }

    public int hashCode() {
        b bVar = this.f8a;
        int iHashCode = (bVar != null ? bVar.hashCode() : 0) * 31;
        byte[] bArr = this.b;
        int iHashCode2 = (iHashCode + (bArr != null ? Arrays.hashCode(bArr) : 0)) * 31;
        long j = this.c;
        return iHashCode2 + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "AudioData(format=" + this.f8a + ", buffer=" + Arrays.toString(this.b) + ", presentationTimeUs=" + this.c + ")";
    }
}
