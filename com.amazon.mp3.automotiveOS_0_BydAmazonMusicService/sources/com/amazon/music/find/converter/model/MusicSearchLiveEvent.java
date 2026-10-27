package com.amazon.music.find.converter.model;

import com.amazon.musicensembleservice.brush.LiveEvent;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicSearchLiveEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/find/converter/model/MusicSearchLiveEvent;", "Lcom/amazon/musicensembleservice/brush/LiveEvent;", MediaTrack.ROLE_SUBTITLE, "", "enableOrdinal", "", "showOrdinal", "(Ljava/lang/String;ZZ)V", "getEnableOrdinal", "()Z", "getShowOrdinal", "getSubtitle", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class MusicSearchLiveEvent extends LiveEvent {
    private final boolean enableOrdinal;
    private final boolean showOrdinal;
    private final String subtitle;

    public static /* synthetic */ MusicSearchLiveEvent copy$default(MusicSearchLiveEvent musicSearchLiveEvent, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = musicSearchLiveEvent.subtitle;
        }
        if ((i & 2) != 0) {
            z = musicSearchLiveEvent.enableOrdinal;
        }
        if ((i & 4) != 0) {
            z2 = musicSearchLiveEvent.showOrdinal;
        }
        return musicSearchLiveEvent.copy(str, z, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSubtitle() {
        return this.subtitle;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public final MusicSearchLiveEvent copy(String subtitle, boolean enableOrdinal, boolean showOrdinal) {
        return new MusicSearchLiveEvent(subtitle, enableOrdinal, showOrdinal);
    }

    @Override // com.amazon.musicensembleservice.brush.LiveEvent, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MusicSearchLiveEvent)) {
            return false;
        }
        MusicSearchLiveEvent musicSearchLiveEvent = (MusicSearchLiveEvent) other;
        return Intrinsics.areEqual(this.subtitle, musicSearchLiveEvent.subtitle) && this.enableOrdinal == musicSearchLiveEvent.enableOrdinal && this.showOrdinal == musicSearchLiveEvent.showOrdinal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v2 */
    @Override // com.amazon.musicensembleservice.brush.LiveEvent, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        String str = this.subtitle;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        boolean z = this.enableOrdinal;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode + r1) * 31;
        boolean z2 = this.showOrdinal;
        return i + (z2 ? 1 : z2);
    }

    public String toString() {
        return "MusicSearchLiveEvent(subtitle=" + ((Object) this.subtitle) + ", enableOrdinal=" + this.enableOrdinal + ", showOrdinal=" + this.showOrdinal + ')';
    }

    public /* synthetic */ MusicSearchLiveEvent(String str, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? true : z, (i & 4) != 0 ? false : z2);
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public MusicSearchLiveEvent(String str, boolean z, boolean z2) {
        this.subtitle = str;
        this.enableOrdinal = z;
        this.showOrdinal = z2;
    }
}
