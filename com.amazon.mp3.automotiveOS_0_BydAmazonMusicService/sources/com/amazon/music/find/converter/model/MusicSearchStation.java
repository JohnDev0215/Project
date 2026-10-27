package com.amazon.music.find.converter.model;

import com.amazon.musicensembleservice.brush.Station;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: MusicSearchStation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/find/converter/model/MusicSearchStation;", "Lcom/amazon/musicensembleservice/brush/Station;", "enableOrdinal", "", "showOrdinal", "(ZZ)V", "getEnableOrdinal", "()Z", "getShowOrdinal", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class MusicSearchStation extends Station {
    private final boolean enableOrdinal;
    private final boolean showOrdinal;

    /* JADX WARN: Illegal instructions before constructor call */
    public MusicSearchStation() {
        boolean z = false;
        this(z, z, 3, null);
    }

    public static /* synthetic */ MusicSearchStation copy$default(MusicSearchStation musicSearchStation, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = musicSearchStation.enableOrdinal;
        }
        if ((i & 2) != 0) {
            z2 = musicSearchStation.showOrdinal;
        }
        return musicSearchStation.copy(z, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public final MusicSearchStation copy(boolean enableOrdinal, boolean showOrdinal) {
        return new MusicSearchStation(enableOrdinal, showOrdinal);
    }

    @Override // com.amazon.musicensembleservice.brush.Station, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MusicSearchStation)) {
            return false;
        }
        MusicSearchStation musicSearchStation = (MusicSearchStation) other;
        return this.enableOrdinal == musicSearchStation.enableOrdinal && this.showOrdinal == musicSearchStation.showOrdinal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    @Override // com.amazon.musicensembleservice.brush.Station, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        boolean z = this.enableOrdinal;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.showOrdinal;
        return i + (z2 ? 1 : z2);
    }

    public String toString() {
        return "MusicSearchStation(enableOrdinal=" + this.enableOrdinal + ", showOrdinal=" + this.showOrdinal + ')';
    }

    public /* synthetic */ MusicSearchStation(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? false : z2);
    }

    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public MusicSearchStation(boolean z, boolean z2) {
        this.enableOrdinal = z;
        this.showOrdinal = z2;
    }
}
