package Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0;

import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElement;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeDetailTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.EpisodeDetailTemplate");
    private String backgroundImage;
    private BookmarkElement bookmark;
    private String description;
    private DownloadElement download;
    private EpisodeOptionsElement episodeOptions;
    private HeaderElement header;
    private TemplateMetadataElement metadata;
    private SaveElement save;
    private ShareElement share;

    public static class Builder extends Template.Builder {
        protected String backgroundImage;
        protected BookmarkElement bookmark;
        protected String description;
        protected DownloadElement download;
        protected EpisodeOptionsElement episodeOptions;
        protected HeaderElement header;
        protected TemplateMetadataElement metadata;
        protected SaveElement save;
        protected ShareElement share;

        public EpisodeDetailTemplate build() {
            EpisodeDetailTemplate episodeDetailTemplate = new EpisodeDetailTemplate();
            populate(episodeDetailTemplate);
            return episodeDetailTemplate;
        }

        protected void populate(EpisodeDetailTemplate episodeDetailTemplate) {
            super.populate((Template) episodeDetailTemplate);
            episodeDetailTemplate.setSave(this.save);
            episodeDetailTemplate.setHeader(this.header);
            episodeDetailTemplate.setMetadata(this.metadata);
            episodeDetailTemplate.setBackgroundImage(this.backgroundImage);
            episodeDetailTemplate.setShare(this.share);
            episodeDetailTemplate.setDescription(this.description);
            episodeDetailTemplate.setBookmark(this.bookmark);
            episodeDetailTemplate.setDownload(this.download);
            episodeDetailTemplate.setEpisodeOptions(this.episodeOptions);
        }

        public Builder withBackgroundImage(String str) {
            this.backgroundImage = str;
            return this;
        }

        public Builder withBookmark(BookmarkElement bookmarkElement) {
            this.bookmark = bookmarkElement;
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

        public Builder withMetadata(TemplateMetadataElement templateMetadataElement) {
            this.metadata = templateMetadataElement;
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

        public Builder withSave(SaveElement saveElement) {
            this.save = saveElement;
            return this;
        }

        public Builder withShare(ShareElement shareElement) {
            this.share = shareElement;
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
        if (!(sOAObject instanceof EpisodeDetailTemplate)) {
            return 1;
        }
        EpisodeDetailTemplate episodeDetailTemplate = (EpisodeDetailTemplate) sOAObject;
        SaveElement save = getSave();
        SaveElement save2 = episodeDetailTemplate.getSave();
        if (save != save2) {
            if (save == null) {
                return -1;
            }
            if (save2 == null) {
                return 1;
            }
            int iCompareTo = save.compareTo(save2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        HeaderElement header = getHeader();
        HeaderElement header2 = episodeDetailTemplate.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo2 = header.compareTo(header2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        TemplateMetadataElement metadata = getMetadata();
        TemplateMetadataElement metadata2 = episodeDetailTemplate.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo3 = metadata.compareTo(metadata2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String backgroundImage = getBackgroundImage();
        String backgroundImage2 = episodeDetailTemplate.getBackgroundImage();
        if (backgroundImage != backgroundImage2) {
            if (backgroundImage == null) {
                return -1;
            }
            if (backgroundImage2 == null) {
                return 1;
            }
            int iCompareTo4 = backgroundImage.compareTo(backgroundImage2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        ShareElement share = getShare();
        ShareElement share2 = episodeDetailTemplate.getShare();
        if (share != share2) {
            if (share == null) {
                return -1;
            }
            if (share2 == null) {
                return 1;
            }
            int iCompareTo5 = share.compareTo(share2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String description = getDescription();
        String description2 = episodeDetailTemplate.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo6 = description.compareTo(description2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        BookmarkElement bookmark = getBookmark();
        BookmarkElement bookmark2 = episodeDetailTemplate.getBookmark();
        if (bookmark != bookmark2) {
            if (bookmark == null) {
                return -1;
            }
            if (bookmark2 == null) {
                return 1;
            }
            int iCompareTo7 = bookmark.compareTo(bookmark2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        DownloadElement download = getDownload();
        DownloadElement download2 = episodeDetailTemplate.getDownload();
        if (download != download2) {
            if (download == null) {
                return -1;
            }
            if (download2 == null) {
                return 1;
            }
            int iCompareTo8 = download.compareTo(download2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        EpisodeOptionsElement episodeOptions = getEpisodeOptions();
        EpisodeOptionsElement episodeOptions2 = episodeDetailTemplate.getEpisodeOptions();
        if (episodeOptions != episodeOptions2) {
            if (episodeOptions == null) {
                return -1;
            }
            if (episodeOptions2 == null) {
                return 1;
            }
            int iCompareTo9 = episodeOptions.compareTo(episodeOptions2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof EpisodeDetailTemplate)) {
            return false;
        }
        EpisodeDetailTemplate episodeDetailTemplate = (EpisodeDetailTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getSave(), episodeDetailTemplate.getSave()) && internalEqualityCheck(getHeader(), episodeDetailTemplate.getHeader()) && internalEqualityCheck(getMetadata(), episodeDetailTemplate.getMetadata()) && internalEqualityCheck(getBackgroundImage(), episodeDetailTemplate.getBackgroundImage()) && internalEqualityCheck(getShare(), episodeDetailTemplate.getShare()) && internalEqualityCheck(getDescription(), episodeDetailTemplate.getDescription()) && internalEqualityCheck(getBookmark(), episodeDetailTemplate.getBookmark()) && internalEqualityCheck(getDownload(), episodeDetailTemplate.getDownload()) && internalEqualityCheck(getEpisodeOptions(), episodeDetailTemplate.getEpisodeOptions());
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public BookmarkElement getBookmark() {
        return this.bookmark;
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

    public TemplateMetadataElement getMetadata() {
        return this.metadata;
    }

    public SaveElement getSave() {
        return this.save;
    }

    public ShareElement getShare() {
        return this.share;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSave(), getHeader(), getMetadata(), getBackgroundImage(), getShare(), getDescription(), getBookmark(), getDownload(), getEpisodeOptions());
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setBookmark(BookmarkElement bookmarkElement) {
        this.bookmark = bookmarkElement;
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

    public void setMetadata(TemplateMetadataElement templateMetadataElement) {
        this.metadata = templateMetadataElement;
    }

    public void setSave(SaveElement saveElement) {
        this.save = saveElement;
    }

    public void setShare(ShareElement shareElement) {
        this.share = shareElement;
    }
}
