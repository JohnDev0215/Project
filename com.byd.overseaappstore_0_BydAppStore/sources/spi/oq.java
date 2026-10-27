package spi;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Entity
public final class oq {

    @PrimaryKey
    @NotNull
    public final String a;

    @NotNull
    public final String b;

    @NotNull
    public final String c;
    public final int d;

    @NotNull
    public final String e;

    public oq(@NotNull String packageName, @NotNull String primePackageName, @NotNull String signature, int i, @NotNull String versionName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(primePackageName, "primePackageName");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        this.a = packageName;
        this.b = primePackageName;
        this.c = signature;
        this.d = i;
        this.e = versionName;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof oq)) {
            return false;
        }
        oq oqVar = (oq) obj;
        return Intrinsics.areEqual(this.a, oqVar.a) && Intrinsics.areEqual(this.b, oqVar.b) && Intrinsics.areEqual(this.c, oqVar.c) && this.d == oqVar.d && Intrinsics.areEqual(this.e, oqVar.e);
    }

    public final int hashCode() {
        return this.e.hashCode() + ((Integer.hashCode(this.d) + xi.b(this.c, xi.b(this.b, this.a.hashCode() * 31, 31), 31)) * 31);
    }

    @NotNull
    public final String toString() {
        String str = this.a;
        String str2 = this.b;
        String str3 = this.c;
        int i = this.d;
        String str4 = this.e;
        StringBuilder sbL = d.l("SoftwareExtraLocal(packageName=", str, ", primePackageName=", str2, ", signature=");
        sbL.append(str3);
        sbL.append(", versionCode=");
        sbL.append(i);
        sbL.append(", versionName=");
        return hi.f(sbL, str4, ")");
    }
}
