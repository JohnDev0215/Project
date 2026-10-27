package com.amazon.music.station;

import UpsellInterface.v1_0.UpsellType;
import com.amazon.music.ContentAccessType;
import com.amazon.musicensembleservice.ParentalControls;
import com.amazon.musicensembleservice.StationSubscriptionTierDetail;
import com.amazon.musicplayqueueservice.client.common.casting.Track;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: loaded from: classes4.dex */
public class StationItem {
    private final Map<ContentAccessType, Boolean> explicitMap;
    private String identifier;
    private String identifierType;
    private List<Track> identifiers;
    private final String imageUrl;
    private final String key;
    private String nextToken;
    private final String seedId;
    private final String seedType;
    private final String sfaSeed;
    private final Set<ContentAccessType> supportedTiers;
    private final String title;

    public StationItem(Set<ContentAccessType> set, Map<ContentAccessType, Boolean> map, String str, String str2, String str3, String str4, String str5, String str6) {
        this.supportedTiers = set;
        this.explicitMap = map;
        this.title = str;
        this.imageUrl = str2;
        this.seedId = str3;
        this.seedType = str4;
        this.key = str5;
        this.sfaSeed = str6;
    }

    public StationItem(Set<ContentAccessType> set, Map<ContentAccessType, Boolean> map, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this(set, map, str, str2, str3, str4, str5, str6);
        this.identifierType = str7;
        this.identifier = str8;
    }

    public StationItem(Set<ContentAccessType> set, Map<ContentAccessType, Boolean> map, String str, String str2, String str3, String str4, String str5, String str6, String str7, List<Track> list) {
        this(set, map, str, str2, str3, str4, str5, str6);
        this.identifierType = str7;
        this.identifiers = list;
    }

    @Deprecated
    public StationItem(boolean z, boolean z2, boolean z3, boolean z4, String str, String str2, String str3, String str4, String str5) {
        this(buildSupportedTier(z, z2, false, false, false), buildExplicitMap(z4, z3, false), str, str2, str3, str4, str5, null);
    }

    static StationItem from(com.amazon.musicensembleservice.stations.Station station, String str) {
        return new StationItem(buildSupportedTier(station.isIsMusicSubscription(), station.isIsPrime(), station.isIsSonicRush(), station.isIsFree(), false), buildExplicitMap(getExplicitValueFromStationSubscriptionTierDetail(station.getSubscriptionTierToDetails(), "MUSIC_SUBSCRIPTION"), getExplicitValueFromStationSubscriptionTierDetail(station.getSubscriptionTierToDetails(), UpsellType.PRIME), getExplicitValueFromStationSubscriptionTierDetail(station.getSubscriptionTierToDetails(), UpsellType.FREE)), station.getStationTitle(), station.getStationImageUrl(), station.getSeedId(), station.getSeedType(), str, null);
    }

    private static boolean getExplicitValueFromStationSubscriptionTierDetail(Map<String, StationSubscriptionTierDetail> map, String str) {
        StationSubscriptionTierDetail stationSubscriptionTierDetail;
        ParentalControls parentalControls;
        if (map == null || (stationSubscriptionTierDetail = map.get(str)) == null || (parentalControls = stationSubscriptionTierDetail.getParentalControls()) == null) {
            return false;
        }
        return BooleanUtils.isTrue(parentalControls.isHasExplicitLanguage());
    }

    public boolean isExplicit(ContentAccessType contentAccessType) {
        if (ContentAccessType.PRIME.equals(contentAccessType)) {
            return hasPrimeExplicitContent();
        }
        if (ContentAccessType.HAWKFIRE.equals(contentAccessType)) {
            return hasUnlimitedExplicitContent();
        }
        if (ContentAccessType.NIGHTWING.equals(contentAccessType)) {
            return hasNightwingExplicitContent();
        }
        return false;
    }

    public boolean hasNightwingExplicitContent() {
        Map<ContentAccessType, Boolean> map = this.explicitMap;
        ContentAccessType contentAccessType = ContentAccessType.NIGHTWING;
        return map.get(contentAccessType) != null && this.explicitMap.get(contentAccessType).booleanValue();
    }

    public boolean hasPrimeExplicitContent() {
        Map<ContentAccessType, Boolean> map = this.explicitMap;
        ContentAccessType contentAccessType = ContentAccessType.PRIME;
        return map.get(contentAccessType) != null && this.explicitMap.get(contentAccessType).booleanValue();
    }

    public boolean hasUnlimitedExplicitContent() {
        Map<ContentAccessType, Boolean> map = this.explicitMap;
        ContentAccessType contentAccessType = ContentAccessType.HAWKFIRE;
        return map.get(contentAccessType) != null && this.explicitMap.get(contentAccessType).booleanValue();
    }

    public String getKey() {
        return this.key;
    }

    public Boolean isMusicSubscription() {
        return Boolean.valueOf(this.supportedTiers.contains(ContentAccessType.HAWKFIRE));
    }

    public Boolean isPrime() {
        return Boolean.valueOf(this.supportedTiers.contains(ContentAccessType.PRIME));
    }

    public String getTitle() {
        return this.title;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getSeedId() {
        return this.seedId;
    }

    public String getSeedType() {
        return this.seedType;
    }

    public String getSFASeed() {
        return this.sfaSeed;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public Set<ContentAccessType> getSupportedTiers() {
        return this.supportedTiers;
    }

    public Map<ContentAccessType, Boolean> getExplicitMap() {
        return this.explicitMap;
    }

    public String getIdentifierType() {
        return this.identifierType;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public List<Track> getIdentifiers() {
        return this.identifiers;
    }

    public static Map<ContentAccessType, Boolean> buildExplicitMap(boolean z) {
        return buildExplicitMap(z, z, z);
    }

    static Map<ContentAccessType, Boolean> buildExplicitMap(boolean z, boolean z2, boolean z3) {
        HashMap map = new HashMap();
        if (z) {
            map.put(ContentAccessType.HAWKFIRE, Boolean.TRUE);
        }
        if (z2) {
            map.put(ContentAccessType.PRIME, Boolean.TRUE);
        }
        if (z3) {
            map.put(ContentAccessType.NIGHTWING, Boolean.TRUE);
        }
        return map;
    }

    static Set<ContentAccessType> buildSupportedTier(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        HashSet hashSet = new HashSet();
        if (z) {
            hashSet.add(ContentAccessType.HAWKFIRE);
        }
        if (z3) {
            hashSet.add(ContentAccessType.SONIC_RUSH);
        }
        if (z2) {
            hashSet.add(ContentAccessType.PRIME);
        }
        if (z4) {
            hashSet.add(ContentAccessType.NIGHTWING);
        }
        if (z5) {
            hashSet.add(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE);
        }
        return hashSet;
    }
}
