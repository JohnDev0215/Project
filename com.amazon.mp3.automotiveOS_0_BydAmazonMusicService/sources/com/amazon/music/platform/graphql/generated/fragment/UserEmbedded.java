package com.amazon.music.platform.graphql.generated.fragment;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserEmbedded.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/UserEmbedded;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "name", "getName", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class UserEmbedded {
    private final String id;
    private final String name;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserEmbedded)) {
            return false;
        }
        UserEmbedded userEmbedded = (UserEmbedded) other;
        return Intrinsics.areEqual(this.id, userEmbedded.id) && Intrinsics.areEqual(this.name, userEmbedded.name);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "UserEmbedded(id=" + this.id + ", name=" + this.name + ')';
    }

    public UserEmbedded(String str, String str2) {
        this.id = str;
        this.name = str2;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }
}
