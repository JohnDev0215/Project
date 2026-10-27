package com.stingray.companion.model;

import A7.m;
import h2.AbstractC1347d;
import k5.InterfaceC1688b;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0016JH\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\"\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lcom/stingray/companion/model/Song;", "", "id", "", "title", "artistName", "cover", "hasPitchInfo", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getTitle", "setTitle", "getArtistName", "setArtistName", "getCover", "setCover", "getHasPitchInfo", "()Ljava/lang/Boolean;", "setHasPitchInfo", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/stingray/companion/model/Song;", "equals", "other", "hashCode", "", "toString", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class Song {

    @InterfaceC1688b("artist_name")
    private String artistName;
    private String cover;

    @InterfaceC1688b("has_pitch_info")
    private Boolean hasPitchInfo;
    private String id;
    private String title;

    public Song(String str, String str2, String str3, String str4, Boolean bool) {
        m.f(str, "id");
        this.id = str;
        this.title = str2;
        this.artistName = str3;
        this.cover = str4;
        this.hasPitchInfo = bool;
    }

    public static /* synthetic */ Song copy$default(Song song, String str, String str2, String str3, String str4, Boolean bool, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            str = song.id;
        }
        if ((i7 & 2) != 0) {
            str2 = song.title;
        }
        String str5 = str2;
        if ((i7 & 4) != 0) {
            str3 = song.artistName;
        }
        String str6 = str3;
        if ((i7 & 8) != 0) {
            str4 = song.cover;
        }
        String str7 = str4;
        if ((i7 & 16) != 0) {
            bool = song.hasPitchInfo;
        }
        return song.copy(str, str5, str6, str7, bool);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getArtistName() {
        return this.artistName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCover() {
        return this.cover;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Boolean getHasPitchInfo() {
        return this.hasPitchInfo;
    }

    public final Song copy(String id, String title, String artistName, String cover, Boolean hasPitchInfo) {
        m.f(id, "id");
        return new Song(id, title, artistName, cover, hasPitchInfo);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Song)) {
            return false;
        }
        Song song = (Song) other;
        return m.a(this.id, song.id) && m.a(this.title, song.title) && m.a(this.artistName, song.artistName) && m.a(this.cover, song.cover) && m.a(this.hasPitchInfo, song.hasPitchInfo);
    }

    public final String getArtistName() {
        return this.artistName;
    }

    public final String getCover() {
        return this.cover;
    }

    public final Boolean getHasPitchInfo() {
        return this.hasPitchInfo;
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int iHashCode = this.id.hashCode() * 31;
        String str = this.title;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.artistName;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.cover;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Boolean bool = this.hasPitchInfo;
        return iHashCode4 + (bool != null ? bool.hashCode() : 0);
    }

    public final void setArtistName(String str) {
        this.artistName = str;
    }

    public final void setCover(String str) {
        this.cover = str;
    }

    public final void setHasPitchInfo(Boolean bool) {
        this.hasPitchInfo = bool;
    }

    public final void setId(String str) {
        m.f(str, "<set-?>");
        this.id = str;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.title;
        String str3 = this.artistName;
        String str4 = this.cover;
        Boolean bool = this.hasPitchInfo;
        StringBuilder sb = new StringBuilder("Song(id=");
        sb.append(str);
        sb.append(", title=");
        sb.append(str2);
        sb.append(", artistName=");
        AbstractC1347d.r(sb, str3, ", cover=", str4, ", hasPitchInfo=");
        sb.append(bool);
        sb.append(")");
        return sb.toString();
    }

    public /* synthetic */ Song(String str, String str2, String str3, String str4, Boolean bool, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i7 & 16) != 0 ? null : bool);
    }
}
