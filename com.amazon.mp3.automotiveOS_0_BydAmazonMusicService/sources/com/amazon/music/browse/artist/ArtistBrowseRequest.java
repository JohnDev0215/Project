package com.amazon.music.browse.artist;

import com.amazon.music.browse.BrowseContentSelector;
import com.amazon.music.browse.DataType;
import com.amazon.music.browse.LocaleResolver;
import com.amazon.musicensembleservice.ArtistDetailsMetadataType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class ArtistBrowseRequest {
    private final String asin;
    private final String deviceId;
    private final String deviceType;
    private final boolean isExplicitFilterEnabled;
    private final String lang;
    private final String requestedContent;
    private final List<ArtistDetailsMetadataType> types;

    private ArtistBrowseRequest(String str, List<ArtistDetailsMetadataType> list, BrowseContentSelector browseContentSelector, String str2, String str3, Locale locale, boolean z) {
        this.asin = str;
        this.types = list;
        this.requestedContent = browseContentSelector.value;
        this.deviceId = str2;
        this.deviceType = str3;
        Locale localeResolveLocale = LocaleResolver.resolveLocale(locale);
        this.lang = localeResolveLocale == null ? null : localeResolveLocale.toString();
        this.isExplicitFilterEnabled = z;
    }

    String getAsin() {
        return this.asin;
    }

    List<ArtistDetailsMetadataType> getTypes() {
        return this.types;
    }

    String getRequestedContent() {
        return this.requestedContent;
    }

    String getDeviceId() {
        return this.deviceId;
    }

    String getDeviceType() {
        return this.deviceType;
    }

    String getLanguage() {
        return this.lang;
    }

    boolean isExplicitFilterEnabled() {
        return this.isExplicitFilterEnabled;
    }

    public static final Builder createBuilder(BrowseContentSelector browseContentSelector, String str, String str2) {
        return new Builder(browseContentSelector, str, str2);
    }

    public static final class Builder {
        private String asin;
        private String deviceId;
        private String deviceType;
        private boolean isExplicitFilterEnabled;
        private Locale locale;
        private BrowseContentSelector requestContent;
        private Set<ArtistDetailsMetadataType> types;

        private Builder(BrowseContentSelector browseContentSelector, String str, String str2) {
            this.types = new HashSet();
            this.requestContent = browseContentSelector;
            this.deviceId = str;
            this.deviceType = str2;
        }

        public Builder withAsin(String str) {
            this.asin = str;
            return this;
        }

        public Builder withTracks(int i, SortBy sortBy) {
            this.types.add(ArtistDetailsRequestHelper.createArtistDetailsMetadataType(DataType.TRACK, null, i, sortBy));
            return this;
        }

        public Builder withExplicitFilterEnabled(boolean z) {
            this.isExplicitFilterEnabled = z;
            return this;
        }

        public ArtistBrowseRequest build() {
            Validate.notEmpty(this.asin, "asin can't be empty or null", new Object[0]);
            Validate.notEmpty(this.types, "request must have at least one item type specified", new Object[0]);
            return new ArtistBrowseRequest(this.asin, new ArrayList(this.types), this.requestContent, this.deviceId, this.deviceType, this.locale, this.isExplicitFilterEnabled);
        }
    }
}
