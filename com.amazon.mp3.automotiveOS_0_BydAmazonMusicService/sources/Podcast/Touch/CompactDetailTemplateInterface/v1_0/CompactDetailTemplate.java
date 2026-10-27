package Podcast.Touch.CompactDetailTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CompactDetailTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.CompactDetailTemplateInterface.v1_0.CompactDetailTemplate");
    private String backgroundImage;
    private BadgeElement badge;
    private BookmarkElement bookmark;
    private ButtonElement button;
    private String description;
    private DownloadElement download;
    private EpisodeOptionsElement episodeOptions;
    private HeaderElement header;
    private String playbackMode;
    private SaveElement save;
    private ShareElement share;
    private String text;

    public static class Builder extends Template.Builder {
        protected String backgroundImage;
        protected BadgeElement badge;
        protected BookmarkElement bookmark;
        protected ButtonElement button;
        protected String description;
        protected DownloadElement download;
        protected EpisodeOptionsElement episodeOptions;
        protected HeaderElement header;
        protected String playbackMode;
        protected SaveElement save;
        protected ShareElement share;
        protected String text;

        public CompactDetailTemplate build() {
            CompactDetailTemplate compactDetailTemplate = new CompactDetailTemplate();
            populate(compactDetailTemplate);
            return compactDetailTemplate;
        }

        protected void populate(CompactDetailTemplate compactDetailTemplate) {
            super.populate((Template) compactDetailTemplate);
            compactDetailTemplate.setEpisodeOptions(this.episodeOptions);
            compactDetailTemplate.setDescription(this.description);
            compactDetailTemplate.setPlaybackMode(this.playbackMode);
            compactDetailTemplate.setShare(this.share);
            compactDetailTemplate.setButton(this.button);
            compactDetailTemplate.setBookmark(this.bookmark);
            compactDetailTemplate.setText(this.text);
            compactDetailTemplate.setSave(this.save);
            compactDetailTemplate.setBadge(this.badge);
            compactDetailTemplate.setDownload(this.download);
            compactDetailTemplate.setHeader(this.header);
            compactDetailTemplate.setBackgroundImage(this.backgroundImage);
        }

        public Builder withBackgroundImage(String str) {
            this.backgroundImage = str;
            return this;
        }

        public Builder withBadge(BadgeElement badgeElement) {
            this.badge = badgeElement;
            return this;
        }

        public Builder withBookmark(BookmarkElement bookmarkElement) {
            this.bookmark = bookmarkElement;
            return this;
        }

        public Builder withButton(ButtonElement buttonElement) {
            this.button = buttonElement;
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withCacheSeconds(Long l) {
            super.withCacheSeconds(l);
            return this;
        }

        public Builder withDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder withDownload(DownloadElement downloadElement) {
            this.download = downloadElement;
            return this;
        }

        public Builder withEpisodeOptions(EpisodeOptionsElement episodeOptionsElement) {
            this.episodeOptions = episodeOptionsElement;
            return this;
        }

        public Builder withHeader(HeaderElement headerElement) {
            this.header = headerElement;
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withLatencyMetricElement(LatencyMetricElement latencyMetricElement) {
            super.withLatencyMetricElement(latencyMetricElement);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withLaunchMode(String str) {
            super.withLaunchMode(str);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnBound(List<Method> list) {
            super.withOnBound(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnBound(List list) {
            return withOnBound((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnCreated(List<Method> list) {
            super.withOnCreated(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnCreated(List list) {
            return withOnCreated((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnDismissed(List<Method> list) {
            super.withOnDismissed(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnDismissed(List list) {
            return withOnDismissed((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        @Deprecated
        public Builder withPageType(String str) {
            super.withPageType(str);
            return this;
        }

        public Builder withPlaybackMode(String str) {
            this.playbackMode = str;
            return this;
        }

        public Builder withSave(SaveElement saveElement) {
            this.save = saveElement;
            return this;
        }

        public Builder withShare(ShareElement shareElement) {
            this.share = shareElement;
            return this;
        }

        public Builder withText(String str) {
            this.text = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof CompactDetailTemplate)) {
            return 1;
        }
        CompactDetailTemplate compactDetailTemplate = (CompactDetailTemplate) sOAObject;
        EpisodeOptionsElement episodeOptions = getEpisodeOptions();
        EpisodeOptionsElement episodeOptions2 = compactDetailTemplate.getEpisodeOptions();
        if (episodeOptions != episodeOptions2) {
            if (episodeOptions == null) {
                return -1;
            }
            if (episodeOptions2 == null) {
                return 1;
            }
            int iCompareTo = episodeOptions.compareTo(episodeOptions2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String description = getDescription();
        String description2 = compactDetailTemplate.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo2 = description.compareTo(description2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String playbackMode = getPlaybackMode();
        String playbackMode2 = compactDetailTemplate.getPlaybackMode();
        if (playbackMode != playbackMode2) {
            if (playbackMode == null) {
                return -1;
            }
            if (playbackMode2 == null) {
                return 1;
            }
            int iCompareTo3 = playbackMode.compareTo(playbackMode2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        ShareElement share = getShare();
        ShareElement share2 = compactDetailTemplate.getShare();
        if (share != share2) {
            if (share == null) {
                return -1;
            }
            if (share2 == null) {
                return 1;
            }
            int iCompareTo4 = share.compareTo(share2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        ButtonElement button = getButton();
        ButtonElement button2 = compactDetailTemplate.getButton();
        if (button != button2) {
            if (button == null) {
                return -1;
            }
            if (button2 == null) {
                return 1;
            }
            int iCompareTo5 = button.compareTo(button2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        BookmarkElement bookmark = getBookmark();
        BookmarkElement bookmark2 = compactDetailTemplate.getBookmark();
        if (bookmark != bookmark2) {
            if (bookmark == null) {
                return -1;
            }
            if (bookmark2 == null) {
                return 1;
            }
            int iCompareTo6 = bookmark.compareTo(bookmark2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String text = getText();
        String text2 = compactDetailTemplate.getText();
        if (text != text2) {
            if (text == null) {
                return -1;
            }
            if (text2 == null) {
                return 1;
            }
            int iCompareTo7 = text.compareTo(text2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        SaveElement save = getSave();
        SaveElement save2 = compactDetailTemplate.getSave();
        if (save != save2) {
            if (save == null) {
                return -1;
            }
            if (save2 == null) {
                return 1;
            }
            int iCompareTo8 = save.compareTo(save2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        BadgeElement badge = getBadge();
        BadgeElement badge2 = compactDetailTemplate.getBadge();
        if (badge != badge2) {
            if (badge == null) {
                return -1;
            }
            if (badge2 == null) {
                return 1;
            }
            int iCompareTo9 = badge.compareTo(badge2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        DownloadElement download = getDownload();
        DownloadElement download2 = compactDetailTemplate.getDownload();
        if (download != download2) {
            if (download == null) {
                return -1;
            }
            if (download2 == null) {
                return 1;
            }
            int iCompareTo10 = download.compareTo(download2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        HeaderElement header = getHeader();
        HeaderElement header2 = compactDetailTemplate.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo11 = header.compareTo(header2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String backgroundImage = getBackgroundImage();
        String backgroundImage2 = compactDetailTemplate.getBackgroundImage();
        if (backgroundImage != backgroundImage2) {
            if (backgroundImage == null) {
                return -1;
            }
            if (backgroundImage2 == null) {
                return 1;
            }
            int iCompareTo12 = backgroundImage.compareTo(backgroundImage2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof CompactDetailTemplate)) {
            return false;
        }
        CompactDetailTemplate compactDetailTemplate = (CompactDetailTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getEpisodeOptions(), compactDetailTemplate.getEpisodeOptions()) && internalEqualityCheck(getDescription(), compactDetailTemplate.getDescription()) && internalEqualityCheck(getPlaybackMode(), compactDetailTemplate.getPlaybackMode()) && internalEqualityCheck(getShare(), compactDetailTemplate.getShare()) && internalEqualityCheck(getButton(), compactDetailTemplate.getButton()) && internalEqualityCheck(getBookmark(), compactDetailTemplate.getBookmark()) && internalEqualityCheck(getText(), compactDetailTemplate.getText()) && internalEqualityCheck(getSave(), compactDetailTemplate.getSave()) && internalEqualityCheck(getBadge(), compactDetailTemplate.getBadge()) && internalEqualityCheck(getDownload(), compactDetailTemplate.getDownload()) && internalEqualityCheck(getHeader(), compactDetailTemplate.getHeader()) && internalEqualityCheck(getBackgroundImage(), compactDetailTemplate.getBackgroundImage());
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public BadgeElement getBadge() {
        return this.badge;
    }

    public BookmarkElement getBookmark() {
        return this.bookmark;
    }

    public ButtonElement getButton() {
        return this.button;
    }

    public String getDescription() {
        return this.description;
    }

    public DownloadElement getDownload() {
        return this.download;
    }

    public EpisodeOptionsElement getEpisodeOptions() {
        return this.episodeOptions;
    }

    public HeaderElement getHeader() {
        return this.header;
    }

    public String getPlaybackMode() {
        return this.playbackMode;
    }

    public SaveElement getSave() {
        return this.save;
    }

    public ShareElement getShare() {
        return this.share;
    }

    public String getText() {
        return this.text;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getEpisodeOptions(), getDescription(), getPlaybackMode(), getShare(), getButton(), getBookmark(), getText(), getSave(), getBadge(), getDownload(), getHeader(), getBackgroundImage());
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setBadge(BadgeElement badgeElement) {
        this.badge = badgeElement;
    }

    public void setBookmark(BookmarkElement bookmarkElement) {
        this.bookmark = bookmarkElement;
    }

    public void setButton(ButtonElement buttonElement) {
        this.button = buttonElement;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDownload(DownloadElement downloadElement) {
        this.download = downloadElement;
    }

    public void setEpisodeOptions(EpisodeOptionsElement episodeOptionsElement) {
        this.episodeOptions = episodeOptionsElement;
    }

    public void setHeader(HeaderElement headerElement) {
        this.header = headerElement;
    }

    public void setPlaybackMode(String str) {
        this.playbackMode = str;
    }

    public void setSave(SaveElement saveElement) {
        this.save = saveElement;
    }

    public void setShare(ShareElement shareElement) {
        this.share = shareElement;
    }

    public void setText(String str) {
        this.text = str;
    }
}
