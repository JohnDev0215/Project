package com.amazon.avod.content.urlvending;

import com.amazon.avod.util.DLog;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FailoverManager {
    private final Set<String> mAvailableOrigins;
    private final String mDefaultUrlSetId;
    private final Map<String, ContentUrl> mUrlSetIdToContentUrlMap;
    private final Map<String, Map<FailoverType, FailoverInfo>> mUrlSetIdToFailoverInfoMap;

    @JsonCreator
    public FailoverManager(@JsonProperty("defaultUrlSetId") @Nonnull String str, @JsonProperty("urlSetIdToContentUrlMap") @Nonnull Map<String, ContentUrl> map, @JsonProperty("urlSetIdToFailoverInfoMap") @Nonnull Map<String, Map<FailoverType, FailoverInfo>> map2, @JsonProperty("availableOrigins") @Nonnull Set<String> set) {
        String str2 = (String) Preconditions.checkNotNull(str, "defaultUrlSetId");
        this.mDefaultUrlSetId = str2;
        Map<String, ContentUrl> map3 = (Map) Preconditions.checkNotNull(map, "urlSetIdToContentUrlMap");
        this.mUrlSetIdToContentUrlMap = map3;
        this.mUrlSetIdToFailoverInfoMap = (Map) Preconditions.checkNotNull(map2, "urlSetIdToContentUrlMap");
        this.mAvailableOrigins = (Set) Preconditions.checkNotNull(set, "availableOrigins");
        Preconditions.checkNotNull(map3.get(str2), "Default url set id should have a content url associated with it");
        logFailoverMap();
    }

    private void logFailoverMap() {
        DLog.devf("Printing failover map for PlaybackUrls response");
        for (Map.Entry<String, ContentUrl> entry : this.mUrlSetIdToContentUrlMap.entrySet()) {
            DLog.devf("Map for URL %s", entry.getValue());
            DLog.devf("TEVS endpoint is %s", entry.getValue().getAdsTrackingEndpoint());
            for (Map.Entry<FailoverType, FailoverInfo> entry2 : this.mUrlSetIdToFailoverInfoMap.get(entry.getKey()).entrySet()) {
                DLog.devf("Mapping %s to url %s", entry2.getKey(), this.mUrlSetIdToContentUrlMap.get(entry2.getValue().getUrlSetId()));
            }
        }
    }

    @Nullable
    public ContentUrl getContentUrlForUrlSetId(@Nonnull String str) {
        return this.mUrlSetIdToContentUrlMap.get(Preconditions.checkNotNull(str, "urlSetId"));
    }

    @JsonIgnore
    @Nonnull
    public ContentUrl getDefaultContentUrl() {
        return getContentUrlForUrlSetId(this.mDefaultUrlSetId);
    }

    @Nullable
    public FailoverInfo getFailoverInfoForFailoverType(@Nonnull String str, @Nonnull FailoverType failoverType, @Nonnull List<String> list) {
        String urlSetId;
        ContentUrl contentUrl;
        Preconditions.checkNotNull(str, "urlSetId");
        Preconditions.checkNotNull(failoverType, "failoverType");
        Map<FailoverType, FailoverInfo> map = this.mUrlSetIdToFailoverInfoMap.get(str);
        if (map == null) {
            return null;
        }
        if (failoverType != FailoverType.ORIGIN || !areAnyOriginsHealthy(list)) {
            return map.get(failoverType);
        }
        HashSet hashSetNewHashSet = Sets.newHashSet();
        hashSetNewHashSet.add(str);
        while (true) {
            if (map != null) {
                FailoverInfo failoverInfo = map.get(failoverType);
                if (failoverInfo == null || (contentUrl = this.mUrlSetIdToContentUrlMap.get((urlSetId = failoverInfo.getUrlSetId()))) == null) {
                    break;
                }
                if (hashSetNewHashSet.contains(urlSetId)) {
                    DLog.warnf("There is a loop containing urlSetId %s , not failing over", urlSetId);
                    break;
                }
                hashSetNewHashSet.add(urlSetId);
                if (!list.contains(contentUrl.getLivePlaybackStreamId())) {
                    return failoverInfo;
                }
                map = this.mUrlSetIdToFailoverInfoMap.get(urlSetId);
            } else {
                break;
            }
        }
        return null;
    }

    boolean areAnyOriginsHealthy(@Nonnull List<String> list) {
        Iterator<String> it = this.mAvailableOrigins.iterator();
        while (it.hasNext()) {
            if (!list.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    @JsonIgnore
    @Nonnull
    public ImmutableList<ContentUrl> getExhaustiveContentUrlList() {
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.addAll((Iterable) this.mUrlSetIdToContentUrlMap.values());
        return builder.build();
    }

    @JsonIgnore
    @Nonnull
    public ImmutableList<ContentUrl> getOrderedContentUrlListForSeamlessFailovers() {
        ContentUrl contentUrl = (ContentUrl) Preconditions.checkNotNull(this.mUrlSetIdToContentUrlMap.get(this.mDefaultUrlSetId), "defaultContentUrl");
        HashSet hashSet = new HashSet();
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.add(contentUrl);
        hashSet.add(contentUrl);
        ContentUrl contentUrlForSeamlessFailover = getContentUrlForSeamlessFailover(this.mDefaultUrlSetId);
        while (contentUrlForSeamlessFailover != null && !hashSet.contains(contentUrlForSeamlessFailover)) {
            builder.add(contentUrlForSeamlessFailover);
            hashSet.add(contentUrlForSeamlessFailover);
            contentUrlForSeamlessFailover = getContentUrlForSeamlessFailover(contentUrlForSeamlessFailover.getSessionId());
        }
        return builder.build();
    }

    @Nullable
    private ContentUrl getContentUrlForSeamlessFailover(@Nonnull String str) {
        FailoverInfo failoverInfoForFailoverType = getFailoverInfoForFailoverType(str, FailoverType.CDN, ImmutableList.of());
        if (failoverInfoForFailoverType != null && failoverInfoForFailoverType.getFailoverMode().equals(FailoverMode.SEAMLESS)) {
            return this.mUrlSetIdToContentUrlMap.get(failoverInfoForFailoverType.getUrlSetId());
        }
        FailoverInfo failoverInfoForFailoverType2 = getFailoverInfoForFailoverType(str, FailoverType.DEFAULT, ImmutableList.of());
        if (failoverInfoForFailoverType2 == null || !failoverInfoForFailoverType2.getFailoverMode().equals(FailoverMode.SEAMLESS)) {
            return null;
        }
        return this.mUrlSetIdToContentUrlMap.get(failoverInfoForFailoverType2.getUrlSetId());
    }
}
