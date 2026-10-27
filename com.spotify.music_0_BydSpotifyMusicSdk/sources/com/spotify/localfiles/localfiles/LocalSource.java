package com.spotify.localfiles.localfiles;

import p.c71;
import p.hf2;
import p.u4;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class LocalSource {
    private final boolean enabled;
    private final boolean found;
    private final String id;
    private final String name;
    private final String path;

    public LocalSource(@z61(name = "id") String str, @z61(name = "path") String str2, @z61(name = "name") String str3, @z61(name = "enabled") boolean z, @z61(name = "found") boolean z2) {
        v03.j(str, "id");
        v03.j(str2, "path");
        v03.j(str3, "name");
        this.id = str;
        this.path = str2;
        this.name = str3;
        this.enabled = z;
        this.found = z2;
    }

    public static /* synthetic */ LocalSource copy$default(LocalSource localSource, String str, String str2, String str3, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = localSource.id;
        }
        if ((i & 2) != 0) {
            str2 = localSource.path;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = localSource.name;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            z = localSource.enabled;
        }
        boolean z3 = z;
        if ((i & 16) != 0) {
            z2 = localSource.found;
        }
        return localSource.copy(str, str4, str5, z3, z2);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.path;
    }

    public final String component3() {
        return this.name;
    }

    public final boolean component4() {
        return this.enabled;
    }

    public final boolean component5() {
        return this.found;
    }

    public final LocalSource copy(@z61(name = "id") String str, @z61(name = "path") String str2, @z61(name = "name") String str3, @z61(name = "enabled") boolean z, @z61(name = "found") boolean z2) {
        v03.j(str, "id");
        v03.j(str2, "path");
        v03.j(str3, "name");
        return new LocalSource(str, str2, str3, z, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalSource)) {
            return false;
        }
        LocalSource localSource = (LocalSource) obj;
        return v03.b(this.id, localSource.id) && v03.b(this.path, localSource.path) && v03.b(this.name, localSource.name) && this.enabled == localSource.enabled && this.found == localSource.found;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final boolean getFound() {
        return this.found;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPath() {
        return this.path;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    public int hashCode() {
        int iB = hf2.b(this.name, hf2.b(this.path, this.id.hashCode() * 31, 31), 31);
        boolean z = this.enabled;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iB + r2) * 31;
        boolean z2 = this.found;
        return i + (z2 ? 1 : z2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LocalSource(id=");
        sb.append(this.id);
        sb.append(", path=");
        sb.append(this.path);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", enabled=");
        sb.append(this.enabled);
        sb.append(", found=");
        return u4.n(sb, this.found, ')');
    }
}
