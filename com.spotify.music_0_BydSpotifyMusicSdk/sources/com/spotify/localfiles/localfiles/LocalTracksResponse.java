package com.spotify.localfiles.localfiles;

import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.u4;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class LocalTracksResponse {
    private final List<LocalTrack> items;
    private final int lengthInSeconds;
    private final int unfilteredLength;

    public LocalTracksResponse(@z61(name = "rows") List<LocalTrack> list, @z61(name = "unfilteredLength") int i, @z61(name = "lengthInSeconds") int i2) {
        v03.j(list, "items");
        this.items = list;
        this.unfilteredLength = i;
        this.lengthInSeconds = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LocalTracksResponse copy$default(LocalTracksResponse localTracksResponse, List list, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            list = localTracksResponse.items;
        }
        if ((i3 & 2) != 0) {
            i = localTracksResponse.unfilteredLength;
        }
        if ((i3 & 4) != 0) {
            i2 = localTracksResponse.lengthInSeconds;
        }
        return localTracksResponse.copy(list, i, i2);
    }

    public final List<LocalTrack> component1() {
        return this.items;
    }

    public final int component2() {
        return this.unfilteredLength;
    }

    public final int component3() {
        return this.lengthInSeconds;
    }

    public final LocalTracksResponse copy(@z61(name = "rows") List<LocalTrack> list, @z61(name = "unfilteredLength") int i, @z61(name = "lengthInSeconds") int i2) {
        v03.j(list, "items");
        return new LocalTracksResponse(list, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalTracksResponse)) {
            return false;
        }
        LocalTracksResponse localTracksResponse = (LocalTracksResponse) obj;
        return v03.b(this.items, localTracksResponse.items) && this.unfilteredLength == localTracksResponse.unfilteredLength && this.lengthInSeconds == localTracksResponse.lengthInSeconds;
    }

    public final List<LocalTrack> getItems() {
        return this.items;
    }

    public final int getLengthInSeconds() {
        return this.lengthInSeconds;
    }

    public final int getUnfilteredLength() {
        return this.unfilteredLength;
    }

    public int hashCode() {
        return Integer.hashCode(this.lengthInSeconds) + hf2.a(this.unfilteredLength, this.items.hashCode() * 31, 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LocalTracksResponse(items=");
        sb.append(this.items);
        sb.append(", unfilteredLength=");
        sb.append(this.unfilteredLength);
        sb.append(", lengthInSeconds=");
        return u4.k(sb, this.lengthInSeconds, ')');
    }

    public LocalTracksResponse(List list, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? EmptyList.c : list, i, (i3 & 4) != 0 ? 0 : i2);
    }
}
