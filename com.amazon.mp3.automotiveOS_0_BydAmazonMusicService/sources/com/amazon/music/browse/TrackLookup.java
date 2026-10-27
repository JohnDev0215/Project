package com.amazon.music.browse;

import com.amazon.music.browse.util.ParentalControlsUtil;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.storeservice.model.GeneralMetadata;
import com.amazon.music.storeservice.model.LookupRequest;
import com.amazon.music.storeservice.model.LookupResponse;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class TrackLookup {
    private static final String[] FEATURES = {"ownership", "expandTracklist", "hasLyrics"};
    private final BrowseService browseService;

    public TrackLookup(BrowseService browseService) {
        this.browseService = (BrowseService) Validate.notNull(browseService, "browseService can't be null", new Object[0]);
    }

    public Response get(Request request) throws BrowseException {
        return get(request, true);
    }

    public Response get(Request request, boolean z) throws BrowseException {
        try {
            LookupRequest lookupRequest = new LookupRequest();
            lookupRequest.setAsins(request.trackAsins);
            ArrayList arrayList = new ArrayList(Arrays.asList(FEATURES));
            if (z) {
                if (request.withVideo) {
                    arrayList.add("includeVideo");
                }
                if (request.requestAudioVideo) {
                    arrayList.add("requestAudioVideo");
                }
                lookupRequest.setFeatures(arrayList);
            }
            lookupRequest.setRequestedContent(request.requestedContent);
            lookupRequest.setDeviceId(request.deviceId);
            if (!StringUtils.isEmpty(request.musicTerritory)) {
                lookupRequest.setMusicTerritory(request.musicTerritory);
            } else {
                Marketplace marketplace = request.marketplace;
                if (marketplace != null) {
                    lookupRequest.setMarketplaceId(marketplace.getObfuscatedId());
                }
            }
            lookupRequest.setDeviceType(request.deviceType);
            Locale localeResolveLocale = LocaleResolver.resolveLocale(request.locale);
            lookupRequest.setLang(localeResolveLocale == null ? null : localeResolveLocale.toString());
            lookupRequest.setAllowedParentalControls(ParentalControlsUtil.getParentalControls(request.isExplicitFilterEnabled));
            lookupRequest.setMusicRequestIdentityContextToken(request.musicRequestIdentityContextToken);
            LookupResponse lookupResponseLookup = this.browseService.lookup(lookupRequest);
            List<TrackItem> trackList = lookupResponseLookup.getTrackList();
            if (trackList != null && trackList.size() > 0) {
                return new Response(trackList, lookupResponseLookup.getMetadata());
            }
            throw new IllegalStateException("TrackLookup could not fetch track information");
        } catch (Exception e) {
            throw new BrowseException(e);
        }
    }

    public static final class Request {
        public final String deviceId;
        public final String deviceType;
        public final boolean isExplicitFilterEnabled;
        public final Locale locale;
        public final Marketplace marketplace;
        public final String musicRequestIdentityContextToken;
        public final String musicTerritory;
        public final boolean requestAudioVideo;
        public final String requestedContent;
        public final List<String> trackAsins;
        public final boolean withVideo;

        public Request(String str, String str2, BrowseContentSelector browseContentSelector, String str3, String str4, Locale locale, boolean z, boolean z2, String str5) {
            this(Arrays.asList((String) Validate.notEmpty(str, "asin can't be null", new Object[0])), null, str2, browseContentSelector, str3, str4, locale, z, false, z2, str5);
        }

        public Request(List<String> list, String str, BrowseContentSelector browseContentSelector, String str2, String str3, Locale locale, boolean z, boolean z2, boolean z3, String str4) {
            this(list, null, str, browseContentSelector, str2, str3, locale, z, z2, z3, str4);
        }

        public Request(List<String> list, Marketplace marketplace, String str, BrowseContentSelector browseContentSelector, String str2, String str3, Locale locale, boolean z, boolean z2, boolean z3, String str4) {
            this.trackAsins = list;
            if (marketplace == null && StringUtils.isEmpty(str)) {
                throw new NullPointerException("Either marketplace or musicTerritory can't be null");
            }
            this.marketplace = marketplace;
            this.musicTerritory = str;
            this.requestedContent = (String) Validate.notEmpty(browseContentSelector.toString(), "contentSelector can't be empty", new Object[0]);
            this.deviceId = (String) Validate.notEmpty(str2, "deviceId can't be empty", new Object[0]);
            this.deviceType = (String) Validate.notEmpty(str3, "deviceType can't be empty", new Object[0]);
            this.locale = locale;
            this.withVideo = z;
            this.requestAudioVideo = z2;
            this.isExplicitFilterEnabled = z3;
            this.musicRequestIdentityContextToken = str4;
        }
    }

    public static final class Response {
        public final List<Map<String, String>> contentInfo;
        public final List<TrackItem> tracks;

        public Response(List<TrackItem> list, GeneralMetadata generalMetadata) {
            ArrayList arrayList = new ArrayList();
            this.tracks = arrayList;
            arrayList.clear();
            arrayList.addAll(list);
            if (generalMetadata != null) {
                this.contentInfo = Collections.singletonList(BrowsePagerIterator.createContentInfoMap(generalMetadata.getTrackList()));
            } else {
                this.contentInfo = null;
            }
        }
    }
}
