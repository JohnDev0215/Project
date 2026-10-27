package com.amazon.music.browse.page;

import androidx.annotation.Nullable;
import com.amazon.music.browse.BrowseContentSelector;
import com.amazon.music.browse.LocaleResolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class PageRequest {
    private final BrowseContentSelector browseContentSelector;
    private final String browseId;
    private final List<String> contentFeatures;
    private final int count;
    private final String deviceId;
    private final String deviceType;
    private final boolean isExplicitFilterEnabled;
    private final boolean isStub;
    private final String locale;
    private final String musicRequestIdentityContextToken;
    private final String musicTerritory;
    private final String nextToken;
    private final String upsellContent;
    private final String uri;

    private PageRequest(Builder builder) {
        this.browseContentSelector = builder.browseContentSelector;
        Locale localeResolveLocale = LocaleResolver.resolveLocale(builder.locale);
        this.locale = localeResolveLocale == null ? null : localeResolveLocale.toString();
        this.deviceId = builder.deviceId;
        this.deviceType = builder.deviceType;
        this.isStub = builder.isStub;
        this.isExplicitFilterEnabled = builder.isExplicitFilterEnabled;
        this.count = builder.count;
        this.nextToken = builder.nextToken;
        this.uri = builder.uri;
        this.musicTerritory = builder.musicTerritory;
        this.contentFeatures = builder.contentFeatures;
        this.upsellContent = builder.upsellContent;
        this.browseId = builder.browseId;
        this.musicRequestIdentityContextToken = builder.musicRequestIdentityContextToken;
    }

    public BrowseContentSelector getRequestedContent() {
        return this.browseContentSelector;
    }

    @Nullable
    public String getLocale() {
        return this.locale;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public List<String> getContentFeatures() {
        return this.contentFeatures;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public boolean isStub() {
        return this.isStub;
    }

    public boolean isExplicitFilterEnabled() {
        return this.isExplicitFilterEnabled;
    }

    public int getCount() {
        return this.count;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getUri() {
        return this.uri;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public String getUpsellContent() {
        return this.upsellContent;
    }

    public String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }

    public String getBrowseId() {
        return this.browseId;
    }

    public static final class Builder {
        private final BrowseContentSelector browseContentSelector;
        private String browseId;
        private List<String> contentFeatures = new ArrayList();
        private int count;
        private final String deviceId;
        private final String deviceType;
        private boolean isExplicitFilterEnabled;
        private boolean isStub;
        private Locale locale;
        private String musicRequestIdentityContextToken;
        private String musicTerritory;
        private String nextToken;
        private String upsellContent;
        private String uri;

        public Builder(BrowseContentSelector browseContentSelector, String str, String str2, String str3, String str4) {
            Validate.notEmpty(str, "deviceId must be non-empty", new Object[0]);
            Validate.notEmpty(str2, "deviceType must be non-empty", new Object[0]);
            Validate.notEmpty(str4, "uri must be non-empty", new Object[0]);
            Validate.notEmpty(str3, "musicTerritory must be non-empty", new Object[0]);
            this.browseContentSelector = browseContentSelector;
            this.deviceId = str;
            this.deviceType = str2;
            this.uri = str4;
            this.musicTerritory = str3;
        }

        public Builder withLocale(Locale locale) {
            Validate.notNull(locale);
            Validate.notEmpty(locale.getLanguage(), "Language must be specified in Locale", new Object[0]);
            this.locale = locale;
            return this;
        }

        public Builder isStub(boolean z) {
            this.isStub = z;
            return this;
        }

        public Builder setIsExplicitFilterEnabled(boolean z) {
            this.isExplicitFilterEnabled = z;
            return this;
        }

        public Builder withCount(int i) {
            this.count = i;
            return this;
        }

        public Builder withNextToken(String str) {
            this.nextToken = str;
            return this;
        }

        public Builder withPodcastContent(boolean z) {
            if (z && !this.contentFeatures.contains("podcast")) {
                this.contentFeatures.add("podcast");
            }
            return this;
        }

        public Builder withPodcastUserContent(boolean z) {
            if (z && !this.contentFeatures.contains("includePodcastUserContent")) {
                this.contentFeatures.add("includePodcastUserContent");
            }
            return this;
        }

        public Builder withLiveEvent(boolean z) {
            if (z && !this.contentFeatures.contains("includeLiveEvent")) {
                this.contentFeatures.add("includeLiveEvent");
            }
            return this;
        }

        public Builder withPodcastCuratedContent(boolean z) {
            if (z && !this.contentFeatures.contains("includePodcastCuratedContent")) {
                this.contentFeatures.add("includePodcastCuratedContent");
            }
            return this;
        }

        public Builder withBundesligaContent(boolean z) {
            if (z && !this.contentFeatures.contains("bundesliga")) {
                this.contentFeatures.add("bundesliga");
            }
            return this;
        }

        public Builder withFeaturedPlay(boolean z, boolean z2, boolean z3) {
            boolean z4 = (!(!this.contentFeatures.contains("requestFeaturedPlayV3") && !this.contentFeatures.contains("requestFeaturedPlayV4Sub1")) || this.contentFeatures.contains("requestFeaturedPlayV6") || this.contentFeatures.contains("requestFeaturedPlayV6NoPodcasts")) ? false : true;
            if (z && z4) {
                if (z2) {
                    if (z3) {
                        this.contentFeatures.add("requestFeaturedPlayV6");
                    } else {
                        this.contentFeatures.add("requestFeaturedPlayV4Sub1");
                    }
                } else if (z3) {
                    this.contentFeatures.add("requestFeaturedPlayV6NoPodcasts");
                } else {
                    this.contentFeatures.add("requestFeaturedPlayV3");
                }
            }
            return this;
        }

        public Builder withMerch(boolean z) {
            toggleFeature("includeMerch", z);
            return this;
        }

        public Builder withVerticalItemBarkers(boolean z) {
            if (z && !this.contentFeatures.contains("allowVerticalItemBarkers")) {
                this.contentFeatures.add("allowVerticalItemBarkers");
            }
            return this;
        }

        public Builder withPodcastEpisodeDescriptiveShoveler() {
            if (!this.contentFeatures.contains("includePodcastEpisodeDescriptiveShoveler")) {
                this.contentFeatures.add("includePodcastEpisodeDescriptiveShoveler");
            }
            return this;
        }

        public Builder withPodcastSonicRush() {
            if (!this.contentFeatures.contains("podcastSonicRush")) {
                this.contentFeatures.add("podcastSonicRush");
            }
            return this;
        }

        public Builder withLiveStreamContent(boolean z) {
            if (z && !this.contentFeatures.contains("includeLiveStream")) {
                this.contentFeatures.add("includeLiveStream");
            }
            return this;
        }

        public Builder withVideo(boolean z) {
            if (z && !this.contentFeatures.contains("includeVideo")) {
                this.contentFeatures.add("includeVideo");
            }
            return this;
        }

        public Builder withVideoStory(boolean z) {
            if (z && !this.contentFeatures.contains("includeVideoStory")) {
                this.contentFeatures.add("includeVideoStory");
            }
            return this;
        }

        public Builder withMusicShowContent(boolean z) {
            if (z && !this.contentFeatures.contains("audioShow")) {
                this.contentFeatures.add("audioShow");
            }
            return this;
        }

        public Builder withRecentlyPlayed(boolean z) {
            if (z && !this.contentFeatures.contains("populateRecentlyPlayed")) {
                this.contentFeatures.add("populateRecentlyPlayed");
            }
            return this;
        }

        public Builder withArtistUpsellWidget(boolean z) {
            if (z && !this.contentFeatures.contains("includeUpsellWidgets")) {
                this.contentFeatures.add("includeUpsellWidgets");
            }
            return this;
        }

        public Builder withUpsellContent(String str) {
            if ("MUSIC_SUBSCRIPTION".equalsIgnoreCase(str)) {
                this.upsellContent = "MUSIC_SUBSCRIPTION";
            }
            return this;
        }

        public Builder withFollowSuggestedArtistsWidget(boolean z) {
            if (z && !this.contentFeatures.contains("includeFollowArtistsWidget")) {
                this.contentFeatures.add("includeFollowArtistsWidget");
            }
            return this;
        }

        public Builder withMusicRequestIdentityContextToken(String str) {
            this.musicRequestIdentityContextToken = str;
            return this;
        }

        public Builder withDeepLinkURLInWidget(boolean z) {
            if (z && !this.contentFeatures.contains("allowDeepLinkURLInWidget")) {
                this.contentFeatures.add("allowDeepLinkURLInWidget");
            }
            return this;
        }

        public Builder withPodcastExploreBitesContent(boolean z) {
            if (z && !this.contentFeatures.contains("includePodcastExploreBites")) {
                this.contentFeatures.add("includePodcastExploreBites");
            }
            return this;
        }

        public Builder withFacetedNavigation(boolean z) {
            if (z && !this.contentFeatures.contains("includeFacetedNavigation")) {
                this.contentFeatures.add("includeFacetedNavigation");
            }
            return this;
        }

        public Builder withAmpShows(boolean z) {
            if (z && !this.contentFeatures.contains("includeAmpShows")) {
                this.contentFeatures.add("includeAmpShows");
            }
            return this;
        }

        public Builder withPinPodcastsInFacetedNavigation(boolean z) {
            if (z && !this.contentFeatures.contains("pinPodcastsInFacetedNavigation")) {
                this.contentFeatures.add("pinPodcastsInFacetedNavigation");
            }
            return this;
        }

        public Builder withIncludeCommentary(boolean z) {
            return includeBooleanContentFeature(z, "includeCommentary");
        }

        public Builder withIncludeCommentaryFlag(boolean z) {
            return includeBooleanContentFeature(z, "includeCommentaryFlag");
        }

        private Builder includeBooleanContentFeature(boolean z, String str) {
            if (z && !this.contentFeatures.contains(str)) {
                this.contentFeatures.add(str);
            }
            return this;
        }

        public Builder withBrowseId(String str) {
            this.browseId = str;
            return this;
        }

        private void toggleFeature(String str, boolean z) {
            if (z) {
                if (this.contentFeatures.contains(str)) {
                    return;
                }
                this.contentFeatures.add(str);
                return;
            }
            this.contentFeatures.remove(str);
        }

        public PageRequest build() {
            return new PageRequest(this);
        }
    }
}
