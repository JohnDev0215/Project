package com.amazon.music.browse;

import com.amazon.music.browse.util.ParentalControlsUtil;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.pager.PagerIterator;
import com.amazon.music.storeservice.model.GeneralMetadata;
import com.amazon.musicensembleservice.ContentInfo;
import com.amazon.musicensembleservice.GetTopMusicEntitiesRequest;
import com.amazon.musicensembleservice.GetTopMusicEntitiesResponse;
import com.android.volley.VolleyError;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public final class BrowsePagerIterator<T> implements PagerIterator<List<T>> {
    private final BrowseService browseService;
    private final DataType dataType;
    private final Marketplace marketplace;
    private final String musicTerritory;
    private final BrowseItemRequest request;
    private boolean hasNext = true;
    private Map<String, String> nextTokenMap = null;
    private Map<String, String> contentInfo = null;

    public BrowsePagerIterator(BrowseService browseService, Marketplace marketplace, String str, BrowseItemRequest browseItemRequest, DataType dataType) {
        Validate.notNull(browseService, "browseService can't be null", new Object[0]);
        Validate.notNull(browseItemRequest, "request can't be null", new Object[0]);
        Validate.notNull(dataType, "datatype can't be null", new Object[0]);
        if (marketplace == null && StringUtils.isEmpty(str)) {
            throw new NullPointerException("Either marketplace or musicTerritory can't be null");
        }
        this.browseService = browseService;
        this.marketplace = marketplace;
        this.musicTerritory = str;
        this.request = browseItemRequest;
        this.dataType = dataType;
    }

    @Override // com.amazon.music.pager.PagerIterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // com.amazon.music.pager.PagerIterator
    public List<T> next() throws ExecutionException, NoSuchElementException {
        List<T> list;
        GetTopMusicEntitiesRequest getTopMusicEntitiesRequest = new GetTopMusicEntitiesRequest();
        if (!StringUtils.isEmpty(this.musicTerritory)) {
            getTopMusicEntitiesRequest.setMusicTerritory(this.musicTerritory);
        } else {
            Marketplace marketplace = this.marketplace;
            if (marketplace != null) {
                getTopMusicEntitiesRequest.setMarketplaceId(marketplace.getObfuscatedId());
            }
        }
        getTopMusicEntitiesRequest.setRankType(this.request.getRankType().getValue());
        boolean z = true;
        getTopMusicEntitiesRequest.setTypes(Arrays.asList(this.dataType.getValue()));
        getTopMusicEntitiesRequest.setRequestedContent(this.request.getRequestedContent());
        getTopMusicEntitiesRequest.setMaxCount(Integer.valueOf(this.request.getItemsPerPage()));
        Map<String, String> map = this.nextTokenMap;
        if (map != null && !map.isEmpty()) {
            getTopMusicEntitiesRequest.setNextTokenMap(this.nextTokenMap);
        } else {
            getTopMusicEntitiesRequest.setNextTokenMap(getEmptyNextTokenMap());
        }
        getTopMusicEntitiesRequest.setBrowseId(this.request.getRefinement());
        getTopMusicEntitiesRequest.setDeviceId(this.request.getDeviceId());
        getTopMusicEntitiesRequest.setDeviceType(this.request.getDeviceType());
        getTopMusicEntitiesRequest.setLang(this.request.getLang());
        List<String> features = this.request.getFeatures();
        if (features != null && features.size() > 0) {
            getTopMusicEntitiesRequest.setFeatures(features);
        }
        getTopMusicEntitiesRequest.setAllowedParentalControls(ParentalControlsUtil.getParentalControls(this.request.isExplicitFilterEnabled()));
        getTopMusicEntitiesRequest.setMusicRequestIdentityContextToken(this.request.getMusicRequestIdentityContextToken());
        try {
            GetTopMusicEntitiesResponse topMusicEntities = this.browseService.getTopMusicEntities(getTopMusicEntitiesRequest);
            GeneralMetadata metadata = topMusicEntities.getMetadata();
            int i = AnonymousClass1.$SwitchMap$com$amazon$music$browse$DataType[this.dataType.ordinal()];
            if (i == 1) {
                list = (List<T>) topMusicEntities.getTrackList();
                if (metadata != null) {
                    this.contentInfo = createContentInfoMap(topMusicEntities.getMetadata().getTrackList());
                }
            } else if (i == 2) {
                list = (List<T>) topMusicEntities.getAlbumList();
                if (metadata != null) {
                    this.contentInfo = createContentInfoMap(metadata.getAlbumList());
                }
            } else if (i == 3) {
                list = (List<T>) topMusicEntities.getPlaylistList();
                if (metadata != null) {
                    this.contentInfo = createContentInfoMap(topMusicEntities.getMetadata().getPlaylistList());
                }
            } else {
                throw new IllegalArgumentException("Provided DataType is not supported");
            }
            this.nextTokenMap = topMusicEntities.getNextTokenMap();
            String value = this.dataType.getValue();
            Map<String, String> map2 = this.nextTokenMap;
            if (map2 == null || !map2.containsKey(value) || StringUtils.isEmpty(this.nextTokenMap.get(value))) {
                z = false;
            }
            this.hasNext = z;
            return list;
        } catch (VolleyError e) {
            throw new ExecutionException(new BrowseException(e));
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.browse.BrowsePagerIterator$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$browse$DataType;

        static {
            int[] iArr = new int[DataType.values().length];
            $SwitchMap$com$amazon$music$browse$DataType = iArr;
            try {
                iArr[DataType.TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$browse$DataType[DataType.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$browse$DataType[DataType.PLAYLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    Map<String, String> getEmptyNextTokenMap() {
        HashMap map = new HashMap();
        map.put(this.dataType.getValue(), "");
        return map;
    }

    static Map<String, String> createContentInfoMap(ContentInfo contentInfo) {
        if (contentInfo == null) {
            return null;
        }
        HashMap map = new HashMap();
        map.put(ContextMappingConstants.CONTENT_SRC, contentInfo.getContentSrc());
        map.put("contentInfoInstId", contentInfo.getContentInfoInstId());
        return map;
    }
}
