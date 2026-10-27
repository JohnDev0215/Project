package androidx.core.provider;

import android.util.Base64;
import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.d.e;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f507a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f508b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f509c;
    private final List<List<byte[]>> d;
    private final int e;
    private final String f;

    public a(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<List<byte[]>> list) {
        e.b(str);
        this.f507a = str;
        e.b(str2);
        this.f508b = str2;
        e.b(str3);
        this.f509c = str3;
        e.b(list);
        this.d = list;
        this.e = 0;
        this.f = this.f507a + "-" + this.f508b + "-" + this.f509c;
    }

    @Nullable
    public List<List<byte[]>> a() {
        return this.d;
    }

    @ArrayRes
    public int b() {
        return this.e;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public String c() {
        return this.f;
    }

    @NonNull
    public String d() {
        return this.f507a;
    }

    @NonNull
    public String e() {
        return this.f508b;
    }

    @NonNull
    public String f() {
        return this.f509c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f507a + ", mProviderPackage: " + this.f508b + ", mQuery: " + this.f509c + ", mCertificates:");
        for (int i = 0; i < this.d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.e);
        return sb.toString();
    }
}
