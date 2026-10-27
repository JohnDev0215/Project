package com.amazon.music.browse;

import com.amazon.music.browse.util.ParentalControlsUtil;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.storeservice.model.AlbumItem;
import com.amazon.music.storeservice.model.GeneralMetadata;
import com.amazon.music.storeservice.model.LookupRequest;
import com.amazon.music.storeservice.model.LookupResponse;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public final class AlbumLookup {
    private final BrowseService service;

    public AlbumLookup(BrowseService browseService) {
        this.service = browseService;
    }

    public Response get(Request request) throws BrowseException {
        try {
            LookupRequest lookupRequest = new LookupRequest();
            lookupRequest.setAsins(Arrays.asList(request.asin));
            lookupRequest.setFeatures(Arrays.asList("popularity", "ownership", "expandTracklist", "hasLyrics"));
            if (!StringUtils.isEmpty(request.musicTerritory)) {
                lookupRequest.setMusicTerritory(request.musicTerritory);
            } else {
                Marketplace marketplace = request.marketplace;
                if (marketplace != null) {
                    lookupRequest.setMarketplaceId(marketplace.getObfuscatedId());
                }
            }
            lookupRequest.setRequestedContent(request.requestedContent);
            lookupRequest.setDeviceId(request.deviceId);
            lookupRequest.setDeviceType(request.deviceType);
            lookupRequest.setAllowedParentalControls(ParentalControlsUtil.getParentalControls(request.isExplicitFilterEnabled));
            lookupRequest.setMusicRequestIdentityContextToken(request.musicRequestIdentityContextToken);
            Locale localeResolveLocale = LocaleResolver.resolveLocale(request.locale);
            lookupRequest.setLang(localeResolveLocale == null ? null : localeResolveLocale.toString());
            LookupResponse lookupResponseLookup = this.service.lookup(lookupRequest);
            List<AlbumItem> albumList = lookupResponseLookup.getAlbumList();
            if (albumList != null && albumList.size() > 0) {
                return new Response(albumList.get(0), lookupResponseLookup.getMetadata());
            }
            throw new IllegalStateException("AlbumLookup could not fetch Album information");
        } catch (Exception e) {
            throw new BrowseException(e);
        }
    }

    public static final class Request {
        public final String asin;
        public final String deviceId;
        public final String deviceType;
        public final boolean isExplicitFilterEnabled;
        public final Locale locale;
        public final Marketplace marketplace;
        public final String musicRequestIdentityContextToken;
        public final String musicTerritory;
        public final String requestedContent;

        public Request(String str, String str2, BrowseContentSelector browseContentSelector, String str3, String str4, Locale locale, boolean z, String str5) {
            this(str, null, str2, browseContentSelector, str3, str4, locale, z, str5);
        }

        public Request(String str, Marketplace marketplace, String str2, BrowseContentSelector browseContentSelector, String str3, String str4, Locale locale, boolean z, String str5) {
            this.asin = (String) Validate.notEmpty(str, "asin can't be null", new Object[0]);
            if (marketplace == null && StringUtils.isEmpty(str2)) {
                throw new NullPointerException("Either marketplace or musicTerritory can't be null");
            }
            this.marketplace = marketplace;
            this.musicTerritory = str2;
            this.requestedContent = (String) Validate.notEmpty(browseContentSelector.toString(), "contentSelector can't be empty", new Object[0]);
            this.deviceId = (String) Validate.notEmpty(str3, "deviceId can't be empty", new Object[0]);
            this.deviceType = (String) Validate.notEmpty(str4, "deviceType can't be empty", new Object[0]);
            this.locale = locale;
            this.musicRequestIdentityContextToken = str5;
            this.isExplicitFilterEnabled = z;
        }
    }

    public static final class Response {
        public final AlbumItem album;
        public final Map<String, String> contentInfo;

        public Response(AlbumItem albumItem, GeneralMetadata generalMetadata) {
            this.album = (AlbumItem) Validate.notNull(albumItem, "album can't be null", new Object[0]);
            if (albumItem.getTracks() != null && albumItem.getArtist() != null) {
                for (TrackItem trackItem : albumItem.getTracks()) {
                    trackItem.setAlbumArtistAsin(albumItem.getArtist().getAsin());
                    trackItem.setAlbumArtistName(albumItem.getArtist().getName());
                }
            }
            if (generalMetadata != null) {
                this.contentInfo = BrowsePagerIterator.createContentInfoMap(generalMetadata.getAlbumList());
            } else {
                this.contentInfo = null;
            }
        }
    }
}
