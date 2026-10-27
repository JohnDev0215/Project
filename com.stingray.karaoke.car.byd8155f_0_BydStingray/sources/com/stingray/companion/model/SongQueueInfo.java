package com.stingray.companion.model;

import A7.m;
import M5.b;
import java.util.Arrays;
import k5.InterfaceC1688b;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u0016\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J<\u0010\u001c\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\bHÖ\u0001J\t\u0010\"\u001a\u00020\u0004HÖ\u0001R$\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR(\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006#"}, d2 = {"Lcom/stingray/companion/model/SongQueueInfo;", "", "songIds", "", "", "songs", "Lcom/stingray/companion/model/Song;", "currentIndex", "", "<init>", "([Ljava/lang/String;[Lcom/stingray/companion/model/Song;I)V", "getSongIds", "()[Ljava/lang/String;", "setSongIds", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "getSongs", "()[Lcom/stingray/companion/model/Song;", "setSongs", "([Lcom/stingray/companion/model/Song;)V", "[Lcom/stingray/companion/model/Song;", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "component1", "component2", "component3", "copy", "([Ljava/lang/String;[Lcom/stingray/companion/model/Song;I)Lcom/stingray/companion/model/SongQueueInfo;", "equals", "", "other", "hashCode", "toString", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class SongQueueInfo {
    private int currentIndex;
    private String[] songIds;

    @InterfaceC1688b("songQueue")
    private Song[] songs;

    public SongQueueInfo(String[] strArr, Song[] songArr, int i7) {
        this.songIds = strArr;
        this.songs = songArr;
        this.currentIndex = i7;
    }

    public static /* synthetic */ SongQueueInfo copy$default(SongQueueInfo songQueueInfo, String[] strArr, Song[] songArr, int i7, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            strArr = songQueueInfo.songIds;
        }
        if ((i10 & 2) != 0) {
            songArr = songQueueInfo.songs;
        }
        if ((i10 & 4) != 0) {
            i7 = songQueueInfo.currentIndex;
        }
        return songQueueInfo.copy(strArr, songArr, i7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String[] getSongIds() {
        return this.songIds;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Song[] getSongs() {
        return this.songs;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    public final SongQueueInfo copy(String[] songIds, Song[] songs, int currentIndex) {
        return new SongQueueInfo(songIds, songs, currentIndex);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SongQueueInfo)) {
            return false;
        }
        SongQueueInfo songQueueInfo = (SongQueueInfo) other;
        return m.a(this.songIds, songQueueInfo.songIds) && m.a(this.songs, songQueueInfo.songs) && this.currentIndex == songQueueInfo.currentIndex;
    }

    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    public final String[] getSongIds() {
        return this.songIds;
    }

    public final Song[] getSongs() {
        return this.songs;
    }

    public int hashCode() {
        String[] strArr = this.songIds;
        int iHashCode = (strArr == null ? 0 : Arrays.hashCode(strArr)) * 31;
        Song[] songArr = this.songs;
        return Integer.hashCode(this.currentIndex) + ((iHashCode + (songArr != null ? Arrays.hashCode(songArr) : 0)) * 31);
    }

    public final void setCurrentIndex(int i7) {
        this.currentIndex = i7;
    }

    public final void setSongIds(String[] strArr) {
        this.songIds = strArr;
    }

    public final void setSongs(Song[] songArr) {
        this.songs = songArr;
    }

    public String toString() {
        String string = Arrays.toString(this.songIds);
        String string2 = Arrays.toString(this.songs);
        int i7 = this.currentIndex;
        StringBuilder sb = new StringBuilder("SongQueueInfo(songIds=");
        sb.append(string);
        sb.append(", songs=");
        sb.append(string2);
        sb.append(", currentIndex=");
        return b.h(sb, i7, ")");
    }

    public /* synthetic */ SongQueueInfo(String[] strArr, Song[] songArr, int i7, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? null : strArr, (i10 & 2) != 0 ? null : songArr, i7);
    }
}
