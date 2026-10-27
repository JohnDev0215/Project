package com.amazon.alexa.voice.core.processor.superbowl.directives;

import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.processor.superbowl.Content;
import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.google.android.gms.common.internal.ImagesContract;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioPlayDirective extends SuperbowlDirective {
    private final AudioItem audioItem;
    private final PlayBehavior playBehavior;

    AudioPlayDirective(Builder builder) {
        super(builder);
        this.playBehavior = builder.playBehavior;
        this.audioItem = builder.audioItem;
    }

    public AudioItem getAudioItem() {
        return this.audioItem;
    }

    public PlayBehavior getPlayBehavior() {
        return this.playBehavior;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AudioPlayDirective.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        AudioPlayDirective audioPlayDirective = (AudioPlayDirective) obj;
        if (this.playBehavior.equals(audioPlayDirective.playBehavior)) {
            return this.audioItem.equals(audioPlayDirective.audioItem);
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective
    public int hashCode() {
        return (((super.hashCode() * 31) + this.playBehavior.hashCode()) * 31) + this.audioItem.hashCode();
    }

    public String toString() {
        return "AudioPlayDirective{playBehavior='" + this.playBehavior + "', audioItem=" + this.audioItem + '}';
    }

    public static final class AudioItem {
        private final String id;
        private final Stream stream;

        public AudioItem(String str, Stream stream) {
            this.id = str;
            this.stream = stream;
        }

        public Stream getStream() {
            return this.stream;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || AudioItem.class != obj.getClass()) {
                return false;
            }
            AudioItem audioItem = (AudioItem) obj;
            if (this.id.equals(audioItem.id)) {
                return this.stream.equals(audioItem.stream);
            }
            return false;
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + this.stream.hashCode();
        }

        public String toString() {
            return "AudioItem{id='" + this.id + "', stream=" + this.stream + '}';
        }
    }

    public static final class Stream {
        private final AudioSource audioSource;
        private final String expectedPreviousToken;
        private final String expiryTime;
        private final long offsetInMilliseconds;
        private final ProgressReport progressReport;
        private final String token;
        private final String url;

        public Stream(String str, String str2, String str3, String str4, long j, AudioSource audioSource, ProgressReport progressReport) {
            this.expiryTime = str;
            this.url = str2;
            this.token = str3;
            this.expectedPreviousToken = str4;
            this.offsetInMilliseconds = j;
            this.audioSource = audioSource;
            this.progressReport = progressReport;
        }

        public boolean hasAttachedAudio() {
            return this.audioSource != null;
        }

        public String getUrl() {
            return this.url;
        }

        public long getOffsetInMilliseconds() {
            return this.offsetInMilliseconds;
        }

        public AudioSource getAudioSource() {
            return this.audioSource;
        }

        public ProgressReport getProgressReport() {
            return this.progressReport;
        }

        public String getToken() {
            return this.token;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Stream.class != obj.getClass()) {
                return false;
            }
            Stream stream = (Stream) obj;
            if (this.offsetInMilliseconds != stream.offsetInMilliseconds) {
                return false;
            }
            String str = this.expiryTime;
            if (str == null ? stream.expiryTime != null : !str.equals(stream.expiryTime)) {
                return false;
            }
            if (!this.url.equals(stream.url) || !this.token.equals(stream.token)) {
                return false;
            }
            String str2 = this.expectedPreviousToken;
            if (str2 == null ? stream.expectedPreviousToken != null : !str2.equals(stream.expiryTime)) {
                return false;
            }
            AudioSource audioSource = this.audioSource;
            if (audioSource == null ? stream.audioSource == null : audioSource.equals(stream.audioSource)) {
                return this.progressReport.equals(stream.progressReport);
            }
            return false;
        }

        public int hashCode() {
            int iHashCode = this.url.hashCode() * 31;
            String str = this.expiryTime;
            int iHashCode2 = (((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + this.token.hashCode()) * 31;
            String str2 = this.expectedPreviousToken;
            int iHashCode3 = str2 != null ? str2.hashCode() : 0;
            long j = this.offsetInMilliseconds;
            int i = (((iHashCode2 + iHashCode3) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
            AudioSource audioSource = this.audioSource;
            return ((i + (audioSource != null ? audioSource.hashCode() : 0)) * 31) + this.progressReport.hashCode();
        }

        public String toString() {
            return "Stream{expiryTime='" + this.expiryTime + "', url='" + this.url + "', token='" + this.token + "', expectedPreviousToken='" + this.expectedPreviousToken + "', offsetInMilliseconds=" + this.offsetInMilliseconds + ", audioSource=" + this.audioSource + ", progressReport=" + this.progressReport + '}';
        }
    }

    public static final class ProgressReport {
        private final long delayInMilliseconds;
        private final long intervalInMilliseconds;

        public ProgressReport(long j, long j2) {
            this.delayInMilliseconds = j;
            this.intervalInMilliseconds = j2;
        }

        public long getDelayInMilliseconds() {
            return this.delayInMilliseconds;
        }

        public long getIntervalInMilliseconds() {
            return this.intervalInMilliseconds;
        }

        public String toString() {
            return "ProgressReport{delayInMilliseconds=" + this.delayInMilliseconds + ", intervalInMilliseconds=" + this.intervalInMilliseconds + '}';
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ProgressReport.class != obj.getClass()) {
                return false;
            }
            ProgressReport progressReport = (ProgressReport) obj;
            return this.delayInMilliseconds == progressReport.delayInMilliseconds && this.intervalInMilliseconds == progressReport.intervalInMilliseconds;
        }

        public int hashCode() {
            long j = this.delayInMilliseconds;
            int i = ((int) (j ^ (j >>> 32))) * 31;
            long j2 = this.intervalInMilliseconds;
            return i + ((int) ((j2 >>> 32) ^ j2));
        }
    }

    public static final class Builder extends SuperbowlDirective.Builder<AudioPlayDirective> {
        AudioItem audioItem;
        PlayBehavior playBehavior;

        public Builder() {
            super("AudioPlayer", "Play");
        }

        public Builder audioItem(AudioItem audioItem) {
            this.audioItem = audioItem;
            return this;
        }

        public Builder playBehavior(PlayBehavior playBehavior) {
            this.playBehavior = playBehavior;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public AudioPlayDirective build() {
            return new AudioPlayDirective(this);
        }
    }

    public static final class Factory extends DirectiveBaseFactory<AudioPlayDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public AudioPlayDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            return (AudioPlayDirective) new Builder().playBehavior(PlayBehavior.parse(jSONObject.getJSONObject("payload").getString("playBehavior"))).audioItem(audioItem(jSONObject.getJSONObject("payload").getJSONObject("audioItem"), contentProvider)).dialogRequestId(getDialogRequestId(jSONObject)).messageId(getMessageId(jSONObject)).build();
        }

        private AudioItem audioItem(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            return new AudioItem(jSONObject.getString("audioItemId"), stream(jSONObject.getJSONObject("stream"), contentProvider));
        }

        private Stream stream(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
            AudioSource audioSource;
            ContentUrl contentUrl = new ContentUrl(jSONObject.getString(ImagesContract.URL));
            if (contentUrl.isAttachedContent()) {
                Content content = contentProvider.get(contentUrl.getContentId());
                if (content == null) {
                    throw new IllegalArgumentException("No content attached " + contentUrl.getContentId());
                }
                audioSource = content.audioSource();
            } else {
                audioSource = null;
            }
            return new Stream(jSONObject.optString("expiryTime", null), contentUrl.getUrl(), jSONObject.getString("token"), jSONObject.optString("expectedPreviousToken", null), jSONObject.optLong("offsetInMilliseconds"), audioSource, progressReport(jSONObject.optJSONObject("progressReport")));
        }

        private ProgressReport progressReport(JSONObject jSONObject) {
            if (jSONObject == null) {
                return new ProgressReport(0L, 0L);
            }
            return new ProgressReport(jSONObject.optLong("progressReportDelayInMilliseconds"), jSONObject.optLong("progressReportIntervalInMilliseconds"));
        }
    }

    public enum PlayBehavior {
        REPLACE_ALL("REPLACE_ALL"),
        ENQUEUE("ENQUEUE"),
        REPLACE_ENQUEUED("REPLACE_ENQUEUED"),
        UNKNOWN("UNKNOWN");

        private final String id;

        PlayBehavior(String str) {
            this.id = str;
        }

        public String getId() {
            return this.id;
        }

        public static PlayBehavior parse(String str) {
            for (PlayBehavior playBehavior : values()) {
                if (playBehavior.id.equalsIgnoreCase(str)) {
                    return playBehavior;
                }
            }
            return UNKNOWN;
        }
    }
}
