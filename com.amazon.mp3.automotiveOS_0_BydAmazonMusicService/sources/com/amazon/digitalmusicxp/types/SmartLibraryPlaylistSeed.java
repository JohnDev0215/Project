package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.enums.SmartLibraryPlaylistIdentifierEnum;
import kotlin.Metadata;

/* JADX INFO: compiled from: SmartLibraryPlaylistSeed.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/digitalmusicxp/types/SmartLibraryPlaylistSeed;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/SmartLibraryPlaylistIdentifierEnum;", "id", "Lcom/amazon/digitalmusicxp/enums/SmartLibraryPlaylistIdentifierEnum;", "getId", "()Lcom/amazon/digitalmusicxp/enums/SmartLibraryPlaylistIdentifierEnum;", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SmartLibraryPlaylistSeed {
    private final SmartLibraryPlaylistIdentifierEnum id;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SmartLibraryPlaylistSeed) && this.id == ((SmartLibraryPlaylistSeed) other).id;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public String toString() {
        return "SmartLibraryPlaylistSeed(id=" + this.id + ')';
    }
}
