package com.amazon.music.curate.model;

import com.amazon.music.ContentAccessType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: com.amazon.music.curate.model.Station, reason: from toString */
/* JADX INFO: compiled from: Station.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\r¢\u0006\u0002\u0010\u000fJ\b\u0010\u0019\u001a\u00020\u0003H\u0016R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\r¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\t\u0010\u0014R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\n\u0010\u0014R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\b\u0010\u0014R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0006\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/curate/model/Station;", "", "stationKey", "", "title", "imageUrl", "isPrime", "", "isMusicUnlimited", "isFree", "isFreeOnDemandPlayable", "token", "explicitMap", "", "Lcom/amazon/music/ContentAccessType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/Map;)V", "getExplicitMap", "()Ljava/util/Map;", "getImageUrl", "()Ljava/lang/String;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getStationKey", "getTitle", "getToken", "toString", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class station {
    private final Map<ContentAccessType, Boolean> explicitMap;
    private final String imageUrl;
    private final Boolean isFree;
    private final Boolean isFreeOnDemandPlayable;
    private final Boolean isMusicUnlimited;
    private final Boolean isPrime;
    private final String stationKey;
    private final String title;
    private final String token;

    public station(String stationKey, String title, String imageUrl, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, String str, Map<ContentAccessType, Boolean> explicitMap) {
        Intrinsics.checkNotNullParameter(stationKey, "stationKey");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        Intrinsics.checkNotNullParameter(explicitMap, "explicitMap");
        this.stationKey = stationKey;
        this.title = title;
        this.imageUrl = imageUrl;
        this.isPrime = bool;
        this.isMusicUnlimited = bool2;
        this.isFree = bool3;
        this.isFreeOnDemandPlayable = bool4;
        this.token = str;
        this.explicitMap = explicitMap;
    }

    public final String getStationKey() {
        return this.stationKey;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public /* synthetic */ station(String str, String str2, String str3, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, String str4, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? Boolean.FALSE : bool, (i & 16) != 0 ? Boolean.FALSE : bool2, (i & 32) != 0 ? Boolean.FALSE : bool3, (i & 64) != 0 ? Boolean.FALSE : bool4, (i & 128) != 0 ? null : str4, map);
    }

    /* JADX INFO: renamed from: isPrime, reason: from getter */
    public final Boolean getIsPrime() {
        return this.isPrime;
    }

    /* JADX INFO: renamed from: isMusicUnlimited, reason: from getter */
    public final Boolean getIsMusicUnlimited() {
        return this.isMusicUnlimited;
    }

    /* JADX INFO: renamed from: isFree, reason: from getter */
    public final Boolean getIsFree() {
        return this.isFree;
    }

    /* JADX INFO: renamed from: isFreeOnDemandPlayable, reason: from getter */
    public final Boolean getIsFreeOnDemandPlayable() {
        return this.isFreeOnDemandPlayable;
    }

    public final String getToken() {
        return this.token;
    }

    public final Map<ContentAccessType, Boolean> getExplicitMap() {
        return this.explicitMap;
    }

    public String toString() {
        return "station(stationKey='" + this.stationKey + "', title='" + this.title + "', imageUrl='" + this.imageUrl + "', isPrime=" + this.isPrime + ", isMusicUnlimited=" + this.isMusicUnlimited + ", isFree=" + this.isFree + ", isFreeOnDemandPlayable=" + this.isFreeOnDemandPlayable + ')';
    }
}
