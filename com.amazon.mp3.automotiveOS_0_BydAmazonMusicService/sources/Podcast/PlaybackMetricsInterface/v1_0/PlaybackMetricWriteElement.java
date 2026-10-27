package Podcast.PlaybackMetricsInterface.v1_0;

import SOAAppSyncInterface.v1_0.WriteElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackMetricWriteElement extends WriteElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricWriteElement");
    private String audioUri;
    private Float currentPlaybackSpeed;
    private Long currentProgressMilliseconds;
    private String errorException;
    private Long initialPlaybackStartDelayMilliseconds;
    private boolean isMediaDownloaded;
    private String mediaCollectionId;
    private String mediaCollectionType;
    private String mediaId;
    private String mediaSubTitle;
    private String mediaTitle;
    private String metricsPreset;
    private String pageType;
    private String playbackEngineType;
    private String playbackInstanceId;
    private Long playbackRequestedAtTimestampMilliseconds;
    private String playbackSignalType;
    private Long playbackStartOffsetMilliseconds;
    private Long playbackStartedAtTimestampMilliseconds;
    private String podcastEpisodeVariantId;
    private String podcastShowVariantId;
    private Integer rebufferCount;
    private Long rebufferDurationMilliseconds;

    public static class Builder extends WriteElement.Builder {
        protected String audioUri;
        protected Float currentPlaybackSpeed;
        protected Long currentProgressMilliseconds;
        protected String errorException;
        protected Long initialPlaybackStartDelayMilliseconds;
        protected boolean isMediaDownloaded;
        protected String mediaCollectionId;
        protected String mediaCollectionType;
        protected String mediaId;
        protected String mediaSubTitle;
        protected String mediaTitle;
        protected String metricsPreset;
        protected String pageType;
        protected String playbackEngineType;
        protected String playbackInstanceId;
        protected Long playbackRequestedAtTimestampMilliseconds;
        protected String playbackSignalType;
        protected Long playbackStartOffsetMilliseconds;
        protected Long playbackStartedAtTimestampMilliseconds;
        protected String podcastEpisodeVariantId;
        protected String podcastShowVariantId;
        protected Integer rebufferCount;
        protected Long rebufferDurationMilliseconds;

        public PlaybackMetricWriteElement build() {
            PlaybackMetricWriteElement playbackMetricWriteElement = new PlaybackMetricWriteElement();
            populate(playbackMetricWriteElement);
            return playbackMetricWriteElement;
        }

        protected void populate(PlaybackMetricWriteElement playbackMetricWriteElement) {
            super.populate((WriteElement) playbackMetricWriteElement);
            playbackMetricWriteElement.setMediaId(this.mediaId);
            playbackMetricWriteElement.setPlaybackInstanceId(this.playbackInstanceId);
            playbackMetricWriteElement.setPodcastEpisodeVariantId(this.podcastEpisodeVariantId);
            playbackMetricWriteElement.setPlaybackSignalType(this.playbackSignalType);
            playbackMetricWriteElement.setPodcastShowVariantId(this.podcastShowVariantId);
            playbackMetricWriteElement.setInitialPlaybackStartDelayMilliseconds(this.initialPlaybackStartDelayMilliseconds);
            playbackMetricWriteElement.setIsMediaDownloaded(this.isMediaDownloaded);
            playbackMetricWriteElement.setMediaSubTitle(this.mediaSubTitle);
            playbackMetricWriteElement.setMediaTitle(this.mediaTitle);
            playbackMetricWriteElement.setMediaCollectionType(this.mediaCollectionType);
            playbackMetricWriteElement.setPlaybackRequestedAtTimestampMilliseconds(this.playbackRequestedAtTimestampMilliseconds);
            playbackMetricWriteElement.setRebufferCount(this.rebufferCount);
            playbackMetricWriteElement.setRebufferDurationMilliseconds(this.rebufferDurationMilliseconds);
            playbackMetricWriteElement.setCurrentPlaybackSpeed(this.currentPlaybackSpeed);
            playbackMetricWriteElement.setAudioUri(this.audioUri);
            playbackMetricWriteElement.setCurrentProgressMilliseconds(this.currentProgressMilliseconds);
            playbackMetricWriteElement.setPlaybackStartOffsetMilliseconds(this.playbackStartOffsetMilliseconds);
            playbackMetricWriteElement.setPlaybackStartedAtTimestampMilliseconds(this.playbackStartedAtTimestampMilliseconds);
            playbackMetricWriteElement.setMediaCollectionId(this.mediaCollectionId);
            playbackMetricWriteElement.setErrorException(this.errorException);
            playbackMetricWriteElement.setPageType(this.pageType);
            playbackMetricWriteElement.setMetricsPreset(this.metricsPreset);
            playbackMetricWriteElement.setPlaybackEngineType(this.playbackEngineType);
        }

        public Builder withAudioUri(String str) {
            this.audioUri = str;
            return this;
        }

        public Builder withCurrentPlaybackSpeed(Float f) {
            this.currentPlaybackSpeed = f;
            return this;
        }

        public Builder withCurrentProgressMilliseconds(Long l) {
            this.currentProgressMilliseconds = l;
            return this;
        }

        public Builder withErrorException(String str) {
            this.errorException = str;
            return this;
        }

        @Override // SOAAppSyncInterface.v1_0.WriteElement.Builder
        public Builder withId(String str) {
            super.withId(str);
            return this;
        }

        public Builder withInitialPlaybackStartDelayMilliseconds(Long l) {
            this.initialPlaybackStartDelayMilliseconds = l;
            return this;
        }

        public Builder withIsMediaDownloaded(boolean z) {
            this.isMediaDownloaded = z;
            return this;
        }

        public Builder withMediaCollectionId(String str) {
            this.mediaCollectionId = str;
            return this;
        }

        public Builder withMediaCollectionType(String str) {
            this.mediaCollectionType = str;
            return this;
        }

        public Builder withMediaId(String str) {
            this.mediaId = str;
            return this;
        }

        public Builder withMediaSubTitle(String str) {
            this.mediaSubTitle = str;
            return this;
        }

        public Builder withMediaTitle(String str) {
            this.mediaTitle = str;
            return this;
        }

        public Builder withMetricsPreset(String str) {
            this.metricsPreset = str;
            return this;
        }

        public Builder withPageType(String str) {
            this.pageType = str;
            return this;
        }

        public Builder withPlaybackEngineType(String str) {
            this.playbackEngineType = str;
            return this;
        }

        public Builder withPlaybackInstanceId(String str) {
            this.playbackInstanceId = str;
            return this;
        }

        public Builder withPlaybackRequestedAtTimestampMilliseconds(Long l) {
            this.playbackRequestedAtTimestampMilliseconds = l;
            return this;
        }

        public Builder withPlaybackSignalType(String str) {
            this.playbackSignalType = str;
            return this;
        }

        public Builder withPlaybackStartOffsetMilliseconds(Long l) {
            this.playbackStartOffsetMilliseconds = l;
            return this;
        }

        public Builder withPlaybackStartedAtTimestampMilliseconds(Long l) {
            this.playbackStartedAtTimestampMilliseconds = l;
            return this;
        }

        public Builder withPodcastEpisodeVariantId(String str) {
            this.podcastEpisodeVariantId = str;
            return this;
        }

        public Builder withPodcastShowVariantId(String str) {
            this.podcastShowVariantId = str;
            return this;
        }

        public Builder withRebufferCount(Integer num) {
            this.rebufferCount = num;
            return this;
        }

        public Builder withRebufferDurationMilliseconds(Long l) {
            this.rebufferDurationMilliseconds = l;
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
    @Override // SOAAppSyncInterface.v1_0.WriteElement, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated WriteElement writeElement) {
        if (writeElement == null) {
            return -1;
        }
        if (writeElement == this) {
            return 0;
        }
        if (!(writeElement instanceof PlaybackMetricWriteElement)) {
            return 1;
        }
        PlaybackMetricWriteElement playbackMetricWriteElement = (PlaybackMetricWriteElement) writeElement;
        String mediaId = getMediaId();
        String mediaId2 = playbackMetricWriteElement.getMediaId();
        if (mediaId != mediaId2) {
            if (mediaId == null) {
                return -1;
            }
            if (mediaId2 == null) {
                return 1;
            }
            int iCompareTo = mediaId.compareTo(mediaId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String playbackInstanceId = getPlaybackInstanceId();
        String playbackInstanceId2 = playbackMetricWriteElement.getPlaybackInstanceId();
        if (playbackInstanceId != playbackInstanceId2) {
            if (playbackInstanceId == null) {
                return -1;
            }
            if (playbackInstanceId2 == null) {
                return 1;
            }
            int iCompareTo2 = playbackInstanceId.compareTo(playbackInstanceId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String podcastEpisodeVariantId = getPodcastEpisodeVariantId();
        String podcastEpisodeVariantId2 = playbackMetricWriteElement.getPodcastEpisodeVariantId();
        if (podcastEpisodeVariantId != podcastEpisodeVariantId2) {
            if (podcastEpisodeVariantId == null) {
                return -1;
            }
            if (podcastEpisodeVariantId2 == null) {
                return 1;
            }
            int iCompareTo3 = podcastEpisodeVariantId.compareTo(podcastEpisodeVariantId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String playbackSignalType = getPlaybackSignalType();
        String playbackSignalType2 = playbackMetricWriteElement.getPlaybackSignalType();
        if (playbackSignalType != playbackSignalType2) {
            if (playbackSignalType == null) {
                return -1;
            }
            if (playbackSignalType2 == null) {
                return 1;
            }
            int iCompareTo4 = playbackSignalType.compareTo(playbackSignalType2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String podcastShowVariantId = getPodcastShowVariantId();
        String podcastShowVariantId2 = playbackMetricWriteElement.getPodcastShowVariantId();
        if (podcastShowVariantId != podcastShowVariantId2) {
            if (podcastShowVariantId == null) {
                return -1;
            }
            if (podcastShowVariantId2 == null) {
                return 1;
            }
            int iCompareTo5 = podcastShowVariantId.compareTo(podcastShowVariantId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Long initialPlaybackStartDelayMilliseconds = getInitialPlaybackStartDelayMilliseconds();
        Long initialPlaybackStartDelayMilliseconds2 = playbackMetricWriteElement.getInitialPlaybackStartDelayMilliseconds();
        if (initialPlaybackStartDelayMilliseconds != initialPlaybackStartDelayMilliseconds2) {
            if (initialPlaybackStartDelayMilliseconds == null) {
                return -1;
            }
            if (initialPlaybackStartDelayMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo6 = initialPlaybackStartDelayMilliseconds.compareTo(initialPlaybackStartDelayMilliseconds2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        if (!isIsMediaDownloaded() && playbackMetricWriteElement.isIsMediaDownloaded()) {
            return -1;
        }
        if (isIsMediaDownloaded() && !playbackMetricWriteElement.isIsMediaDownloaded()) {
            return 1;
        }
        String mediaSubTitle = getMediaSubTitle();
        String mediaSubTitle2 = playbackMetricWriteElement.getMediaSubTitle();
        if (mediaSubTitle != mediaSubTitle2) {
            if (mediaSubTitle == null) {
                return -1;
            }
            if (mediaSubTitle2 == null) {
                return 1;
            }
            int iCompareTo7 = mediaSubTitle.compareTo(mediaSubTitle2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String mediaTitle = getMediaTitle();
        String mediaTitle2 = playbackMetricWriteElement.getMediaTitle();
        if (mediaTitle != mediaTitle2) {
            if (mediaTitle == null) {
                return -1;
            }
            if (mediaTitle2 == null) {
                return 1;
            }
            int iCompareTo8 = mediaTitle.compareTo(mediaTitle2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String mediaCollectionType = getMediaCollectionType();
        String mediaCollectionType2 = playbackMetricWriteElement.getMediaCollectionType();
        if (mediaCollectionType != mediaCollectionType2) {
            if (mediaCollectionType == null) {
                return -1;
            }
            if (mediaCollectionType2 == null) {
                return 1;
            }
            int iCompareTo9 = mediaCollectionType.compareTo(mediaCollectionType2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Long playbackRequestedAtTimestampMilliseconds = getPlaybackRequestedAtTimestampMilliseconds();
        Long playbackRequestedAtTimestampMilliseconds2 = playbackMetricWriteElement.getPlaybackRequestedAtTimestampMilliseconds();
        if (playbackRequestedAtTimestampMilliseconds != playbackRequestedAtTimestampMilliseconds2) {
            if (playbackRequestedAtTimestampMilliseconds == null) {
                return -1;
            }
            if (playbackRequestedAtTimestampMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo10 = playbackRequestedAtTimestampMilliseconds.compareTo(playbackRequestedAtTimestampMilliseconds2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Integer rebufferCount = getRebufferCount();
        Integer rebufferCount2 = playbackMetricWriteElement.getRebufferCount();
        if (rebufferCount != rebufferCount2) {
            if (rebufferCount == null) {
                return -1;
            }
            if (rebufferCount2 == null) {
                return 1;
            }
            int iCompareTo11 = rebufferCount.compareTo(rebufferCount2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Long rebufferDurationMilliseconds = getRebufferDurationMilliseconds();
        Long rebufferDurationMilliseconds2 = playbackMetricWriteElement.getRebufferDurationMilliseconds();
        if (rebufferDurationMilliseconds != rebufferDurationMilliseconds2) {
            if (rebufferDurationMilliseconds == null) {
                return -1;
            }
            if (rebufferDurationMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo12 = rebufferDurationMilliseconds.compareTo(rebufferDurationMilliseconds2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Float currentPlaybackSpeed = getCurrentPlaybackSpeed();
        Float currentPlaybackSpeed2 = playbackMetricWriteElement.getCurrentPlaybackSpeed();
        if (currentPlaybackSpeed != currentPlaybackSpeed2) {
            if (currentPlaybackSpeed == null) {
                return -1;
            }
            if (currentPlaybackSpeed2 == null) {
                return 1;
            }
            int iCompareTo13 = currentPlaybackSpeed.compareTo(currentPlaybackSpeed2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String audioUri = getAudioUri();
        String audioUri2 = playbackMetricWriteElement.getAudioUri();
        if (audioUri != audioUri2) {
            if (audioUri == null) {
                return -1;
            }
            if (audioUri2 == null) {
                return 1;
            }
            int iCompareTo14 = audioUri.compareTo(audioUri2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        Long currentProgressMilliseconds = getCurrentProgressMilliseconds();
        Long currentProgressMilliseconds2 = playbackMetricWriteElement.getCurrentProgressMilliseconds();
        if (currentProgressMilliseconds != currentProgressMilliseconds2) {
            if (currentProgressMilliseconds == null) {
                return -1;
            }
            if (currentProgressMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo15 = currentProgressMilliseconds.compareTo(currentProgressMilliseconds2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Long playbackStartOffsetMilliseconds = getPlaybackStartOffsetMilliseconds();
        Long playbackStartOffsetMilliseconds2 = playbackMetricWriteElement.getPlaybackStartOffsetMilliseconds();
        if (playbackStartOffsetMilliseconds != playbackStartOffsetMilliseconds2) {
            if (playbackStartOffsetMilliseconds == null) {
                return -1;
            }
            if (playbackStartOffsetMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo16 = playbackStartOffsetMilliseconds.compareTo(playbackStartOffsetMilliseconds2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        Long playbackStartedAtTimestampMilliseconds = getPlaybackStartedAtTimestampMilliseconds();
        Long playbackStartedAtTimestampMilliseconds2 = playbackMetricWriteElement.getPlaybackStartedAtTimestampMilliseconds();
        if (playbackStartedAtTimestampMilliseconds != playbackStartedAtTimestampMilliseconds2) {
            if (playbackStartedAtTimestampMilliseconds == null) {
                return -1;
            }
            if (playbackStartedAtTimestampMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo17 = playbackStartedAtTimestampMilliseconds.compareTo(playbackStartedAtTimestampMilliseconds2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String mediaCollectionId = getMediaCollectionId();
        String mediaCollectionId2 = playbackMetricWriteElement.getMediaCollectionId();
        if (mediaCollectionId != mediaCollectionId2) {
            if (mediaCollectionId == null) {
                return -1;
            }
            if (mediaCollectionId2 == null) {
                return 1;
            }
            int iCompareTo18 = mediaCollectionId.compareTo(mediaCollectionId2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        String errorException = getErrorException();
        String errorException2 = playbackMetricWriteElement.getErrorException();
        if (errorException != errorException2) {
            if (errorException == null) {
                return -1;
            }
            if (errorException2 == null) {
                return 1;
            }
            int iCompareTo19 = errorException.compareTo(errorException2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        String pageType = getPageType();
        String pageType2 = playbackMetricWriteElement.getPageType();
        if (pageType != pageType2) {
            if (pageType == null) {
                return -1;
            }
            if (pageType2 == null) {
                return 1;
            }
            int iCompareTo20 = pageType.compareTo(pageType2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        String metricsPreset = getMetricsPreset();
        String metricsPreset2 = playbackMetricWriteElement.getMetricsPreset();
        if (metricsPreset != metricsPreset2) {
            if (metricsPreset == null) {
                return -1;
            }
            if (metricsPreset2 == null) {
                return 1;
            }
            int iCompareTo21 = metricsPreset.compareTo(metricsPreset2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        String playbackEngineType = getPlaybackEngineType();
        String playbackEngineType2 = playbackMetricWriteElement.getPlaybackEngineType();
        if (playbackEngineType != playbackEngineType2) {
            if (playbackEngineType == null) {
                return -1;
            }
            if (playbackEngineType2 == null) {
                return 1;
            }
            int iCompareTo22 = playbackEngineType.compareTo(playbackEngineType2);
            if (iCompareTo22 != 0) {
                return iCompareTo22;
            }
        }
        return super.compareTo(writeElement);
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public boolean equals(Object obj) {
        if (!(obj instanceof PlaybackMetricWriteElement)) {
            return false;
        }
        PlaybackMetricWriteElement playbackMetricWriteElement = (PlaybackMetricWriteElement) obj;
        return super.equals(obj) && internalEqualityCheck(getMediaId(), playbackMetricWriteElement.getMediaId()) && internalEqualityCheck(getPlaybackInstanceId(), playbackMetricWriteElement.getPlaybackInstanceId()) && internalEqualityCheck(getPodcastEpisodeVariantId(), playbackMetricWriteElement.getPodcastEpisodeVariantId()) && internalEqualityCheck(getPlaybackSignalType(), playbackMetricWriteElement.getPlaybackSignalType()) && internalEqualityCheck(getPodcastShowVariantId(), playbackMetricWriteElement.getPodcastShowVariantId()) && internalEqualityCheck(getInitialPlaybackStartDelayMilliseconds(), playbackMetricWriteElement.getInitialPlaybackStartDelayMilliseconds()) && internalEqualityCheck(Boolean.valueOf(isIsMediaDownloaded()), Boolean.valueOf(playbackMetricWriteElement.isIsMediaDownloaded())) && internalEqualityCheck(getMediaSubTitle(), playbackMetricWriteElement.getMediaSubTitle()) && internalEqualityCheck(getMediaTitle(), playbackMetricWriteElement.getMediaTitle()) && internalEqualityCheck(getMediaCollectionType(), playbackMetricWriteElement.getMediaCollectionType()) && internalEqualityCheck(getPlaybackRequestedAtTimestampMilliseconds(), playbackMetricWriteElement.getPlaybackRequestedAtTimestampMilliseconds()) && internalEqualityCheck(getRebufferCount(), playbackMetricWriteElement.getRebufferCount()) && internalEqualityCheck(getRebufferDurationMilliseconds(), playbackMetricWriteElement.getRebufferDurationMilliseconds()) && internalEqualityCheck(getCurrentPlaybackSpeed(), playbackMetricWriteElement.getCurrentPlaybackSpeed()) && internalEqualityCheck(getAudioUri(), playbackMetricWriteElement.getAudioUri()) && internalEqualityCheck(getCurrentProgressMilliseconds(), playbackMetricWriteElement.getCurrentProgressMilliseconds()) && internalEqualityCheck(getPlaybackStartOffsetMilliseconds(), playbackMetricWriteElement.getPlaybackStartOffsetMilliseconds()) && internalEqualityCheck(getPlaybackStartedAtTimestampMilliseconds(), playbackMetricWriteElement.getPlaybackStartedAtTimestampMilliseconds()) && internalEqualityCheck(getMediaCollectionId(), playbackMetricWriteElement.getMediaCollectionId()) && internalEqualityCheck(getErrorException(), playbackMetricWriteElement.getErrorException()) && internalEqualityCheck(getPageType(), playbackMetricWriteElement.getPageType()) && internalEqualityCheck(getMetricsPreset(), playbackMetricWriteElement.getMetricsPreset()) && internalEqualityCheck(getPlaybackEngineType(), playbackMetricWriteElement.getPlaybackEngineType());
    }

    public String getAudioUri() {
        return this.audioUri;
    }

    public Float getCurrentPlaybackSpeed() {
        return this.currentPlaybackSpeed;
    }

    public Long getCurrentProgressMilliseconds() {
        return this.currentProgressMilliseconds;
    }

    public String getErrorException() {
        return this.errorException;
    }

    public Long getInitialPlaybackStartDelayMilliseconds() {
        return this.initialPlaybackStartDelayMilliseconds;
    }

    public String getMediaCollectionId() {
        return this.mediaCollectionId;
    }

    public String getMediaCollectionType() {
        return this.mediaCollectionType;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public String getMediaSubTitle() {
        return this.mediaSubTitle;
    }

    public String getMediaTitle() {
        return this.mediaTitle;
    }

    public String getMetricsPreset() {
        return this.metricsPreset;
    }

    public String getPageType() {
        return this.pageType;
    }

    public String getPlaybackEngineType() {
        return this.playbackEngineType;
    }

    public String getPlaybackInstanceId() {
        return this.playbackInstanceId;
    }

    public Long getPlaybackRequestedAtTimestampMilliseconds() {
        return this.playbackRequestedAtTimestampMilliseconds;
    }

    public String getPlaybackSignalType() {
        return this.playbackSignalType;
    }

    public Long getPlaybackStartOffsetMilliseconds() {
        return this.playbackStartOffsetMilliseconds;
    }

    public Long getPlaybackStartedAtTimestampMilliseconds() {
        return this.playbackStartedAtTimestampMilliseconds;
    }

    public String getPodcastEpisodeVariantId() {
        return this.podcastEpisodeVariantId;
    }

    public String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }

    public Integer getRebufferCount() {
        return this.rebufferCount;
    }

    public Long getRebufferDurationMilliseconds() {
        return this.rebufferDurationMilliseconds;
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMediaId(), getPlaybackInstanceId(), getPodcastEpisodeVariantId(), getPlaybackSignalType(), getPodcastShowVariantId(), getInitialPlaybackStartDelayMilliseconds(), Boolean.valueOf(isIsMediaDownloaded()), getMediaSubTitle(), getMediaTitle(), getMediaCollectionType(), getPlaybackRequestedAtTimestampMilliseconds(), getRebufferCount(), getRebufferDurationMilliseconds(), getCurrentPlaybackSpeed(), getAudioUri(), getCurrentProgressMilliseconds(), getPlaybackStartOffsetMilliseconds(), getPlaybackStartedAtTimestampMilliseconds(), getMediaCollectionId(), getErrorException(), getPageType(), getMetricsPreset(), getPlaybackEngineType());
    }

    public boolean isIsMediaDownloaded() {
        return this.isMediaDownloaded;
    }

    public void setAudioUri(String str) {
        this.audioUri = str;
    }

    public void setCurrentPlaybackSpeed(Float f) {
        this.currentPlaybackSpeed = f;
    }

    public void setCurrentProgressMilliseconds(Long l) {
        this.currentProgressMilliseconds = l;
    }

    public void setErrorException(String str) {
        this.errorException = str;
    }

    public void setInitialPlaybackStartDelayMilliseconds(Long l) {
        this.initialPlaybackStartDelayMilliseconds = l;
    }

    public void setIsMediaDownloaded(boolean z) {
        this.isMediaDownloaded = z;
    }

    public void setMediaCollectionId(String str) {
        this.mediaCollectionId = str;
    }

    public void setMediaCollectionType(String str) {
        this.mediaCollectionType = str;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public void setMediaSubTitle(String str) {
        this.mediaSubTitle = str;
    }

    public void setMediaTitle(String str) {
        this.mediaTitle = str;
    }

    public void setMetricsPreset(String str) {
        this.metricsPreset = str;
    }

    public void setPageType(String str) {
        this.pageType = str;
    }

    public void setPlaybackEngineType(String str) {
        this.playbackEngineType = str;
    }

    public void setPlaybackInstanceId(String str) {
        this.playbackInstanceId = str;
    }

    public void setPlaybackRequestedAtTimestampMilliseconds(Long l) {
        this.playbackRequestedAtTimestampMilliseconds = l;
    }

    public void setPlaybackSignalType(String str) {
        this.playbackSignalType = str;
    }

    public void setPlaybackStartOffsetMilliseconds(Long l) {
        this.playbackStartOffsetMilliseconds = l;
    }

    public void setPlaybackStartedAtTimestampMilliseconds(Long l) {
        this.playbackStartedAtTimestampMilliseconds = l;
    }

    public void setPodcastEpisodeVariantId(String str) {
        this.podcastEpisodeVariantId = str;
    }

    public void setPodcastShowVariantId(String str) {
        this.podcastShowVariantId = str;
    }

    public void setRebufferCount(Integer num) {
        this.rebufferCount = num;
    }

    public void setRebufferDurationMilliseconds(Long l) {
        this.rebufferDurationMilliseconds = l;
    }
}
