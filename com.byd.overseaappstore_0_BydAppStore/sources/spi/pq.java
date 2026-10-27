package spi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Entity
public final class pq {

    @PrimaryKey
    @NotNull
    public final String a;
    public final int b;

    @NotNull
    public final String c;

    @NotNull
    public final String d;
    public final long e;

    @NotNull
    public String f;

    @NotNull
    public final String g;
    public final int h;

    @NotNull
    public final String i;
    public final int j;
    public final int k;

    @NotNull
    public final String l;

    @ColumnInfo(defaultValue = "1")
    public final short m;

    public pq(@NotNull String packageName, int i, @NotNull String name, @NotNull String sourceDir, long j, @NotNull String downloadUrl, @NotNull String icon, int i2, @NotNull String classifyName, int i3, int i4, @NotNull String signature, short s) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(sourceDir, "sourceDir");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(classifyName, "classifyName");
        Intrinsics.checkNotNullParameter(signature, "signature");
        this.a = packageName;
        this.b = i;
        this.c = name;
        this.d = sourceDir;
        this.e = j;
        this.f = downloadUrl;
        this.g = icon;
        this.h = i2;
        this.i = classifyName;
        this.j = i3;
        this.k = i4;
        this.l = signature;
        this.m = s;
    }

    public /* synthetic */ pq(String str, int i, String str2, String str3, long j, String str4, String str5, int i2, String str6, int i3, int i4, String str7, short s, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, str3, j, str4, (i5 & 64) != 0 ? "" : str5, (i5 & 128) != 0 ? 0 : i2, (i5 & 256) != 0 ? "" : str6, i3, i4, str7, s);
    }

    public final void a(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pq)) {
            return false;
        }
        pq pqVar = (pq) obj;
        return Intrinsics.areEqual(this.a, pqVar.a) && this.b == pqVar.b && Intrinsics.areEqual(this.c, pqVar.c) && Intrinsics.areEqual(this.d, pqVar.d) && this.e == pqVar.e && Intrinsics.areEqual(this.f, pqVar.f) && Intrinsics.areEqual(this.g, pqVar.g) && this.h == pqVar.h && Intrinsics.areEqual(this.i, pqVar.i) && this.j == pqVar.j && this.k == pqVar.k && Intrinsics.areEqual(this.l, pqVar.l) && this.m == pqVar.m;
    }

    public final int hashCode() {
        return Short.hashCode(this.m) + xi.b(this.l, (Integer.hashCode(this.k) + ((Integer.hashCode(this.j) + xi.b(this.i, (Integer.hashCode(this.h) + xi.b(this.g, xi.b(this.f, (Long.hashCode(this.e) + xi.b(this.d, xi.b(this.c, (Integer.hashCode(this.b) + (this.a.hashCode() * 31)) * 31, 31), 31)) * 31, 31), 31)) * 31, 31)) * 31)) * 31, 31);
    }

    @NotNull
    public final String toString() {
        String str = this.a;
        int i = this.b;
        String str2 = this.c;
        String str3 = this.d;
        long j = this.e;
        String str4 = this.f;
        String str5 = this.g;
        int i2 = this.h;
        String str6 = this.i;
        int i3 = this.j;
        int i4 = this.k;
        String str7 = this.l;
        short s = this.m;
        StringBuilder sbK = d.k("SoftwareLocal(packageName=", str, ", id=", i, ", name=");
        sbK.append(str2);
        sbK.append(", sourceDir=");
        sbK.append(str3);
        sbK.append(", totalSize=");
        sbK.append(j);
        sbK.append(", downloadUrl=");
        sbK.append(str4);
        sbK.append(", icon=");
        sbK.append(str5);
        sbK.append(", classifyId=");
        sbK.append(i2);
        sbK.append(", classifyName=");
        sbK.append(str6);
        sbK.append(", versionCode=");
        sbK.append(i3);
        sbK.append(", state=");
        sbK.append(i4);
        sbK.append(", signature=");
        sbK.append(str7);
        sbK.append(", sourceType=");
        sbK.append((int) s);
        sbK.append(")");
        return sbK.toString();
    }
}
