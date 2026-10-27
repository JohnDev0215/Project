package com.amazon.music.find.converter.model;

import com.amazon.musicensembleservice.brush.Album;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicSearchAlbum.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0003J/\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/find/converter/model/MusicSearchAlbum;", "Lcom/amazon/musicensembleservice/brush/Album;", "enableOrdinal", "", "showOrdinal", "languageOfPerformances", "", "", "(ZZLjava/util/List;)V", "getEnableOrdinal", "()Z", "getLanguageOfPerformances", "()Ljava/util/List;", "getShowOrdinal", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class MusicSearchAlbum extends Album {
    private final boolean enableOrdinal;
    private final List<String> languageOfPerformances;
    private final boolean showOrdinal;

    public MusicSearchAlbum() {
        this(false, false, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MusicSearchAlbum copy$default(MusicSearchAlbum musicSearchAlbum, boolean z, boolean z2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            z = musicSearchAlbum.enableOrdinal;
        }
        if ((i & 2) != 0) {
            z2 = musicSearchAlbum.showOrdinal;
        }
        if ((i & 4) != 0) {
            list = musicSearchAlbum.languageOfPerformances;
        }
        return musicSearchAlbum.copy(z, z2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public final List<String> component3() {
        return this.languageOfPerformances;
    }

    public final MusicSearchAlbum copy(boolean enableOrdinal, boolean showOrdinal, List<String> languageOfPerformances) {
        return new MusicSearchAlbum(enableOrdinal, showOrdinal, languageOfPerformances);
    }

    @Override // com.amazon.musicensembleservice.brush.Album, com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MusicSearchAlbum)) {
            return false;
        }
        MusicSearchAlbum musicSearchAlbum = (MusicSearchAlbum) other;
        return this.enableOrdinal == musicSearchAlbum.enableOrdinal && this.showOrdinal == musicSearchAlbum.showOrdinal && Intrinsics.areEqual(this.languageOfPerformances, musicSearchAlbum.languageOfPerformances);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    @Override // com.amazon.musicensembleservice.brush.Album, com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        boolean z = this.enableOrdinal;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.showOrdinal;
        int i2 = (i + (z2 ? 1 : z2)) * 31;
        List<String> list = this.languageOfPerformances;
        return i2 + (list == null ? 0 : list.hashCode());
    }

    public String toString() {
        return "MusicSearchAlbum(enableOrdinal=" + this.enableOrdinal + ", showOrdinal=" + this.showOrdinal + ", languageOfPerformances=" + this.languageOfPerformances + ')';
    }

    public /* synthetic */ MusicSearchAlbum(boolean z, boolean z2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? null : list);
    }

    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public final List<String> getLanguageOfPerformances() {
        return this.languageOfPerformances;
    }

    public MusicSearchAlbum(boolean z, boolean z2, List<String> list) {
        this.enableOrdinal = z;
        this.showOrdinal = z2;
        this.languageOfPerformances = list;
    }
}
