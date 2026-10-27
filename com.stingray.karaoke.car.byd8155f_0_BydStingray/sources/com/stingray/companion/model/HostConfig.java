package com.stingray.companion.model;

import A7.m;
import M5.b;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b#\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\t\u0010'\u001a\u00020\tHÆ\u0003J\u0011\u0010(\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0003JN\u0010)\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010*J\u0013\u0010+\u001a\u00020\u00072\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020\u0003HÖ\u0001J\t\u0010.\u001a\u00020\tHÖ\u0001R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0006\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006/"}, d2 = {"Lcom/stingray/companion/model/HostConfig;", "", "songQueueLimit", "", "companionAppMode", "Lcom/stingray/companion/model/CompanionAppMode;", "isParentalControlActive", "", "karaokeApiVersion", "", "features", "", "Lcom/stingray/companion/model/CompanionFeature;", "<init>", "(Ljava/lang/Integer;Lcom/stingray/companion/model/CompanionAppMode;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;)V", "getSongQueueLimit", "()Ljava/lang/Integer;", "setSongQueueLimit", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCompanionAppMode", "()Lcom/stingray/companion/model/CompanionAppMode;", "setCompanionAppMode", "(Lcom/stingray/companion/model/CompanionAppMode;)V", "()Ljava/lang/Boolean;", "setParentalControlActive", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getKaraokeApiVersion", "()Ljava/lang/String;", "setKaraokeApiVersion", "(Ljava/lang/String;)V", "getFeatures", "()Ljava/util/List;", "setFeatures", "(Ljava/util/List;)V", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Lcom/stingray/companion/model/CompanionAppMode;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;)Lcom/stingray/companion/model/HostConfig;", "equals", "other", "hashCode", "toString", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class HostConfig {
    private CompanionAppMode companionAppMode;
    private List<? extends CompanionFeature> features;
    private Boolean isParentalControlActive;
    private String karaokeApiVersion;
    private Integer songQueueLimit;

    public HostConfig(Integer num, CompanionAppMode companionAppMode, Boolean bool, String str, List<? extends CompanionFeature> list) {
        m.f(str, "karaokeApiVersion");
        this.songQueueLimit = num;
        this.companionAppMode = companionAppMode;
        this.isParentalControlActive = bool;
        this.karaokeApiVersion = str;
        this.features = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HostConfig copy$default(HostConfig hostConfig, Integer num, CompanionAppMode companionAppMode, Boolean bool, String str, List list, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            num = hostConfig.songQueueLimit;
        }
        if ((i7 & 2) != 0) {
            companionAppMode = hostConfig.companionAppMode;
        }
        CompanionAppMode companionAppMode2 = companionAppMode;
        if ((i7 & 4) != 0) {
            bool = hostConfig.isParentalControlActive;
        }
        Boolean bool2 = bool;
        if ((i7 & 8) != 0) {
            str = hostConfig.karaokeApiVersion;
        }
        String str2 = str;
        if ((i7 & 16) != 0) {
            list = hostConfig.features;
        }
        return hostConfig.copy(num, companionAppMode2, bool2, str2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getSongQueueLimit() {
        return this.songQueueLimit;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CompanionAppMode getCompanionAppMode() {
        return this.companionAppMode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Boolean getIsParentalControlActive() {
        return this.isParentalControlActive;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getKaraokeApiVersion() {
        return this.karaokeApiVersion;
    }

    public final List<CompanionFeature> component5() {
        return this.features;
    }

    public final HostConfig copy(Integer songQueueLimit, CompanionAppMode companionAppMode, Boolean isParentalControlActive, String karaokeApiVersion, List<? extends CompanionFeature> features) {
        m.f(karaokeApiVersion, "karaokeApiVersion");
        return new HostConfig(songQueueLimit, companionAppMode, isParentalControlActive, karaokeApiVersion, features);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HostConfig)) {
            return false;
        }
        HostConfig hostConfig = (HostConfig) other;
        return m.a(this.songQueueLimit, hostConfig.songQueueLimit) && this.companionAppMode == hostConfig.companionAppMode && m.a(this.isParentalControlActive, hostConfig.isParentalControlActive) && m.a(this.karaokeApiVersion, hostConfig.karaokeApiVersion) && m.a(this.features, hostConfig.features);
    }

    public final CompanionAppMode getCompanionAppMode() {
        return this.companionAppMode;
    }

    public final List<CompanionFeature> getFeatures() {
        return this.features;
    }

    public final String getKaraokeApiVersion() {
        return this.karaokeApiVersion;
    }

    public final Integer getSongQueueLimit() {
        return this.songQueueLimit;
    }

    public int hashCode() {
        Integer num = this.songQueueLimit;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        CompanionAppMode companionAppMode = this.companionAppMode;
        int iHashCode2 = (iHashCode + (companionAppMode == null ? 0 : companionAppMode.hashCode())) * 31;
        Boolean bool = this.isParentalControlActive;
        int iC = b.c(this.karaokeApiVersion, (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31, 31);
        List<? extends CompanionFeature> list = this.features;
        return iC + (list != null ? list.hashCode() : 0);
    }

    public final Boolean isParentalControlActive() {
        return this.isParentalControlActive;
    }

    public final void setCompanionAppMode(CompanionAppMode companionAppMode) {
        this.companionAppMode = companionAppMode;
    }

    public final void setFeatures(List<? extends CompanionFeature> list) {
        this.features = list;
    }

    public final void setKaraokeApiVersion(String str) {
        m.f(str, "<set-?>");
        this.karaokeApiVersion = str;
    }

    public final void setParentalControlActive(Boolean bool) {
        this.isParentalControlActive = bool;
    }

    public final void setSongQueueLimit(Integer num) {
        this.songQueueLimit = num;
    }

    public String toString() {
        return "HostConfig(songQueueLimit=" + this.songQueueLimit + ", companionAppMode=" + this.companionAppMode + ", isParentalControlActive=" + this.isParentalControlActive + ", karaokeApiVersion=" + this.karaokeApiVersion + ", features=" + this.features + ")";
    }

    public /* synthetic */ HostConfig(Integer num, CompanionAppMode companionAppMode, Boolean bool, String str, List list, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : num, (i7 & 2) != 0 ? null : companionAppMode, bool, (i7 & 8) != 0 ? "V3" : str, (i7 & 16) != 0 ? null : list);
    }
}
