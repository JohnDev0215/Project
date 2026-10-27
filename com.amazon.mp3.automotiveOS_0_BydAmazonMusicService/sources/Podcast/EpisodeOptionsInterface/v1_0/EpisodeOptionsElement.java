package Podcast.EpisodeOptionsInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeOptionsElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement");
    private CarPresetElement carPreset;
    private CompletedElement completed;
    private DownloadElement download;
    private FollowElement follow;
    private GoToPodcastElement goToPodcast;
    private String image;
    private List<Method> onEpisodeOptionsDismiss;
    private List<Method> onEpisodeOptionsShow;
    private PlayEpisodeElement playEpisode;
    private ReportElement report;
    private SaveElement save;
    private ShareElement share;
    private String subTitle;
    private String title;

    public static class Builder extends TemplateElement.Builder {
        protected CarPresetElement carPreset;
        protected CompletedElement completed;
        protected DownloadElement download;
        protected FollowElement follow;
        protected GoToPodcastElement goToPodcast;
        protected String image;
        protected List<Method> onEpisodeOptionsDismiss;
        protected List<Method> onEpisodeOptionsShow;
        protected PlayEpisodeElement playEpisode;
        protected ReportElement report;
        protected SaveElement save;
        protected ShareElement share;
        protected String subTitle;
        protected String title;

        public EpisodeOptionsElement build() {
            EpisodeOptionsElement episodeOptionsElement = new EpisodeOptionsElement();
            populate(episodeOptionsElement);
            return episodeOptionsElement;
        }

        protected void populate(EpisodeOptionsElement episodeOptionsElement) {
            super.populate((TemplateElement) episodeOptionsElement);
            episodeOptionsElement.setShare(this.share);
            episodeOptionsElement.setPlayEpisode(this.playEpisode);
            episodeOptionsElement.setFollow(this.follow);
            episodeOptionsElement.setSubTitle(this.subTitle);
            episodeOptionsElement.setGoToPodcast(this.goToPodcast);
            episodeOptionsElement.setImage(this.image);
            episodeOptionsElement.setDownload(this.download);
            episodeOptionsElement.setTitle(this.title);
            episodeOptionsElement.setCarPreset(this.carPreset);
            episodeOptionsElement.setOnEpisodeOptionsShow(this.onEpisodeOptionsShow);
            episodeOptionsElement.setCompleted(this.completed);
            episodeOptionsElement.setOnEpisodeOptionsDismiss(this.onEpisodeOptionsDismiss);
            episodeOptionsElement.setSave(this.save);
            episodeOptionsElement.setReport(this.report);
        }

        public Builder withCarPreset(CarPresetElement carPresetElement) {
            this.carPreset = carPresetElement;
            return this;
        }

        public Builder withCompleted(CompletedElement completedElement) {
            this.completed = completedElement;
            return this;
        }

        public Builder withDownload(DownloadElement downloadElement) {
            this.download = downloadElement;
            return this;
        }

        public Builder withFollow(FollowElement followElement) {
            this.follow = followElement;
            return this;
        }

        public Builder withGoToPodcast(GoToPodcastElement goToPodcastElement) {
            this.goToPodcast = goToPodcastElement;
            return this;
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withOnEpisodeOptionsDismiss(List<Method> list) {
            this.onEpisodeOptionsDismiss = list;
            return this;
        }

        public Builder withOnEpisodeOptionsShow(List<Method> list) {
            this.onEpisodeOptionsShow = list;
            return this;
        }

        public Builder withPlayEpisode(PlayEpisodeElement playEpisodeElement) {
            this.playEpisode = playEpisodeElement;
            return this;
        }

        public Builder withReport(ReportElement reportElement) {
            this.report = reportElement;
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

        public Builder withSubTitle(String str) {
            this.subTitle = str;
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
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
    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof EpisodeOptionsElement)) {
            return 1;
        }
        EpisodeOptionsElement episodeOptionsElement = (EpisodeOptionsElement) sOAObject;
        ShareElement share = getShare();
        ShareElement share2 = episodeOptionsElement.getShare();
        if (share != share2) {
            if (share == null) {
                return -1;
            }
            if (share2 == null) {
                return 1;
            }
            int iCompareTo = share.compareTo(share2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        PlayEpisodeElement playEpisode = getPlayEpisode();
        PlayEpisodeElement playEpisode2 = episodeOptionsElement.getPlayEpisode();
        if (playEpisode != playEpisode2) {
            if (playEpisode == null) {
                return -1;
            }
            if (playEpisode2 == null) {
                return 1;
            }
            int iCompareTo2 = playEpisode.compareTo(playEpisode2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        FollowElement follow = getFollow();
        FollowElement follow2 = episodeOptionsElement.getFollow();
        if (follow != follow2) {
            if (follow == null) {
                return -1;
            }
            if (follow2 == null) {
                return 1;
            }
            int iCompareTo3 = follow.compareTo(follow2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String subTitle = getSubTitle();
        String subTitle2 = episodeOptionsElement.getSubTitle();
        if (subTitle != subTitle2) {
            if (subTitle == null) {
                return -1;
            }
            if (subTitle2 == null) {
                return 1;
            }
            int iCompareTo4 = subTitle.compareTo(subTitle2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        GoToPodcastElement goToPodcast = getGoToPodcast();
        GoToPodcastElement goToPodcast2 = episodeOptionsElement.getGoToPodcast();
        if (goToPodcast != goToPodcast2) {
            if (goToPodcast == null) {
                return -1;
            }
            if (goToPodcast2 == null) {
                return 1;
            }
            int iCompareTo5 = goToPodcast.compareTo(goToPodcast2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String image = getImage();
        String image2 = episodeOptionsElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo6 = image.compareTo(image2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        DownloadElement download = getDownload();
        DownloadElement download2 = episodeOptionsElement.getDownload();
        if (download != download2) {
            if (download == null) {
                return -1;
            }
            if (download2 == null) {
                return 1;
            }
            int iCompareTo7 = download.compareTo(download2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String title = getTitle();
        String title2 = episodeOptionsElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo8 = title.compareTo(title2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        CarPresetElement carPreset = getCarPreset();
        CarPresetElement carPreset2 = episodeOptionsElement.getCarPreset();
        if (carPreset != carPreset2) {
            if (carPreset == null) {
                return -1;
            }
            if (carPreset2 == null) {
                return 1;
            }
            int iCompareTo9 = carPreset.compareTo(carPreset2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        List<Method> onEpisodeOptionsShow = getOnEpisodeOptionsShow();
        List<Method> onEpisodeOptionsShow2 = episodeOptionsElement.getOnEpisodeOptionsShow();
        if (onEpisodeOptionsShow != onEpisodeOptionsShow2) {
            if (onEpisodeOptionsShow == null) {
                return -1;
            }
            if (onEpisodeOptionsShow2 == null) {
                return 1;
            }
            if (onEpisodeOptionsShow instanceof Comparable) {
                int iCompareTo10 = ((Comparable) onEpisodeOptionsShow).compareTo(onEpisodeOptionsShow2);
                if (iCompareTo10 != 0) {
                    return iCompareTo10;
                }
            } else if (!onEpisodeOptionsShow.equals(onEpisodeOptionsShow2)) {
                int iHashCode = onEpisodeOptionsShow.hashCode();
                int iHashCode2 = onEpisodeOptionsShow2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        CompletedElement completed = getCompleted();
        CompletedElement completed2 = episodeOptionsElement.getCompleted();
        if (completed != completed2) {
            if (completed == null) {
                return -1;
            }
            if (completed2 == null) {
                return 1;
            }
            int iCompareTo11 = completed.compareTo(completed2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        List<Method> onEpisodeOptionsDismiss = getOnEpisodeOptionsDismiss();
        List<Method> onEpisodeOptionsDismiss2 = episodeOptionsElement.getOnEpisodeOptionsDismiss();
        if (onEpisodeOptionsDismiss != onEpisodeOptionsDismiss2) {
            if (onEpisodeOptionsDismiss == null) {
                return -1;
            }
            if (onEpisodeOptionsDismiss2 == null) {
                return 1;
            }
            if (onEpisodeOptionsDismiss instanceof Comparable) {
                int iCompareTo12 = ((Comparable) onEpisodeOptionsDismiss).compareTo(onEpisodeOptionsDismiss2);
                if (iCompareTo12 != 0) {
                    return iCompareTo12;
                }
            } else if (!onEpisodeOptionsDismiss.equals(onEpisodeOptionsDismiss2)) {
                int iHashCode3 = onEpisodeOptionsDismiss.hashCode();
                int iHashCode4 = onEpisodeOptionsDismiss2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        SaveElement save = getSave();
        SaveElement save2 = episodeOptionsElement.getSave();
        if (save != save2) {
            if (save == null) {
                return -1;
            }
            if (save2 == null) {
                return 1;
            }
            int iCompareTo13 = save.compareTo(save2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        ReportElement report = getReport();
        ReportElement report2 = episodeOptionsElement.getReport();
        if (report != report2) {
            if (report == null) {
                return -1;
            }
            if (report2 == null) {
                return 1;
            }
            int iCompareTo14 = report.compareTo(report2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof EpisodeOptionsElement)) {
            return false;
        }
        EpisodeOptionsElement episodeOptionsElement = (EpisodeOptionsElement) obj;
        return super.equals(obj) && internalEqualityCheck(getShare(), episodeOptionsElement.getShare()) && internalEqualityCheck(getPlayEpisode(), episodeOptionsElement.getPlayEpisode()) && internalEqualityCheck(getFollow(), episodeOptionsElement.getFollow()) && internalEqualityCheck(getSubTitle(), episodeOptionsElement.getSubTitle()) && internalEqualityCheck(getGoToPodcast(), episodeOptionsElement.getGoToPodcast()) && internalEqualityCheck(getImage(), episodeOptionsElement.getImage()) && internalEqualityCheck(getDownload(), episodeOptionsElement.getDownload()) && internalEqualityCheck(getTitle(), episodeOptionsElement.getTitle()) && internalEqualityCheck(getCarPreset(), episodeOptionsElement.getCarPreset()) && internalEqualityCheck(getOnEpisodeOptionsShow(), episodeOptionsElement.getOnEpisodeOptionsShow()) && internalEqualityCheck(getCompleted(), episodeOptionsElement.getCompleted()) && internalEqualityCheck(getOnEpisodeOptionsDismiss(), episodeOptionsElement.getOnEpisodeOptionsDismiss()) && internalEqualityCheck(getSave(), episodeOptionsElement.getSave()) && internalEqualityCheck(getReport(), episodeOptionsElement.getReport());
    }

    public CarPresetElement getCarPreset() {
        return this.carPreset;
    }

    public CompletedElement getCompleted() {
        return this.completed;
    }

    public DownloadElement getDownload() {
        return this.download;
    }

    public FollowElement getFollow() {
        return this.follow;
    }

    public GoToPodcastElement getGoToPodcast() {
        return this.goToPodcast;
    }

    public String getImage() {
        return this.image;
    }

    public List<Method> getOnEpisodeOptionsDismiss() {
        return this.onEpisodeOptionsDismiss;
    }

    public List<Method> getOnEpisodeOptionsShow() {
        return this.onEpisodeOptionsShow;
    }

    public PlayEpisodeElement getPlayEpisode() {
        return this.playEpisode;
    }

    public ReportElement getReport() {
        return this.report;
    }

    public SaveElement getSave() {
        return this.save;
    }

    public ShareElement getShare() {
        return this.share;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getShare(), getPlayEpisode(), getFollow(), getSubTitle(), getGoToPodcast(), getImage(), getDownload(), getTitle(), getCarPreset(), getOnEpisodeOptionsShow(), getCompleted(), getOnEpisodeOptionsDismiss(), getSave(), getReport());
    }

    public void setCarPreset(CarPresetElement carPresetElement) {
        this.carPreset = carPresetElement;
    }

    public void setCompleted(CompletedElement completedElement) {
        this.completed = completedElement;
    }

    public void setDownload(DownloadElement downloadElement) {
        this.download = downloadElement;
    }

    public void setFollow(FollowElement followElement) {
        this.follow = followElement;
    }

    public void setGoToPodcast(GoToPodcastElement goToPodcastElement) {
        this.goToPodcast = goToPodcastElement;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setOnEpisodeOptionsDismiss(List<Method> list) {
        this.onEpisodeOptionsDismiss = list;
    }

    public void setOnEpisodeOptionsShow(List<Method> list) {
        this.onEpisodeOptionsShow = list;
    }

    public void setPlayEpisode(PlayEpisodeElement playEpisodeElement) {
        this.playEpisode = playEpisodeElement;
    }

    public void setReport(ReportElement reportElement) {
        this.report = reportElement;
    }

    public void setSave(SaveElement saveElement) {
        this.save = saveElement;
    }

    public void setShare(ShareElement shareElement) {
        this.share = shareElement;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
