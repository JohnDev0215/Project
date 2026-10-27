package android.media;

import android.icu.util.ULocale;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioPresentation {
    public static final int MASTERED_FOR_3D = 3;
    public static final int MASTERED_FOR_HEADPHONE = 4;
    public static final int MASTERED_FOR_STEREO = 1;
    public static final int MASTERED_FOR_SURROUND = 2;
    public static final int MASTERING_NOT_INDICATED = 0;
    private static final int UNKNOWN_ID = -1;
    private final boolean mAudioDescriptionAvailable;
    private final boolean mDialogueEnhancementAvailable;
    private final Map<ULocale, CharSequence> mLabels;
    private final ULocale mLanguage;
    private final int mMasteringIndication;
    private final int mPresentationId;
    private final int mProgramId;
    private final boolean mSpokenSubtitlesAvailable;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MasteringIndicationType {
    }

    private AudioPresentation(int i, int i2, ULocale uLocale, int i3, boolean z, boolean z2, boolean z3, Map<ULocale, CharSequence> map) {
        this.mPresentationId = i;
        this.mProgramId = i2;
        this.mLanguage = uLocale;
        this.mMasteringIndication = i3;
        this.mAudioDescriptionAvailable = z;
        this.mSpokenSubtitlesAvailable = z2;
        this.mDialogueEnhancementAvailable = z3;
        this.mLabels = new HashMap(map);
    }

    public int getPresentationId() {
        return this.mPresentationId;
    }

    public int getProgramId() {
        return this.mProgramId;
    }

    public Map<Locale, String> getLabels() {
        HashMap map = new HashMap(this.mLabels.size());
        for (Map.Entry<ULocale, CharSequence> entry : this.mLabels.entrySet()) {
            map.put(entry.getKey().toLocale(), entry.getValue().toString());
        }
        return map;
    }

    private Map<ULocale, CharSequence> getULabels() {
        return this.mLabels;
    }

    public Locale getLocale() {
        return this.mLanguage.toLocale();
    }

    private ULocale getULocale() {
        return this.mLanguage;
    }

    public int getMasteringIndication() {
        return this.mMasteringIndication;
    }

    public boolean hasAudioDescription() {
        return this.mAudioDescriptionAvailable;
    }

    public boolean hasSpokenSubtitles() {
        return this.mSpokenSubtitlesAvailable;
    }

    public boolean hasDialogueEnhancement() {
        return this.mDialogueEnhancementAvailable;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioPresentation)) {
            return false;
        }
        AudioPresentation audioPresentation = (AudioPresentation) obj;
        return this.mPresentationId == audioPresentation.getPresentationId() && this.mProgramId == audioPresentation.getProgramId() && this.mLanguage.equals(audioPresentation.getULocale()) && this.mMasteringIndication == audioPresentation.getMasteringIndication() && this.mAudioDescriptionAvailable == audioPresentation.hasAudioDescription() && this.mSpokenSubtitlesAvailable == audioPresentation.hasSpokenSubtitles() && this.mDialogueEnhancementAvailable == audioPresentation.hasDialogueEnhancement() && this.mLabels.equals(audioPresentation.getULabels());
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mPresentationId), Integer.valueOf(this.mProgramId), Integer.valueOf(this.mLanguage.hashCode()), Integer.valueOf(this.mMasteringIndication), Boolean.valueOf(this.mAudioDescriptionAvailable), Boolean.valueOf(this.mSpokenSubtitlesAvailable), Boolean.valueOf(this.mDialogueEnhancementAvailable), Integer.valueOf(this.mLabels.hashCode()));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName() + " ");
        sb.append("{ presentation id=" + this.mPresentationId);
        sb.append(", program id=" + this.mProgramId);
        sb.append(", language=" + this.mLanguage);
        sb.append(", labels=" + this.mLabels);
        sb.append(", mastering indication=" + this.mMasteringIndication);
        sb.append(", audio description=" + this.mAudioDescriptionAvailable);
        sb.append(", spoken subtitles=" + this.mSpokenSubtitlesAvailable);
        sb.append(", dialogue enhancement=" + this.mDialogueEnhancementAvailable);
        sb.append(" }");
        return sb.toString();
    }

    public static final class Builder {
        private final int mPresentationId;
        private int mProgramId = -1;
        private ULocale mLanguage = new ULocale("");
        private int mMasteringIndication = 0;
        private boolean mAudioDescriptionAvailable = false;
        private boolean mSpokenSubtitlesAvailable = false;
        private boolean mDialogueEnhancementAvailable = false;
        private Map<ULocale, CharSequence> mLabels = new HashMap();

        public Builder(int i) {
            this.mPresentationId = i;
        }

        public Builder setProgramId(int i) {
            this.mProgramId = i;
            return this;
        }

        public Builder setLocale(ULocale uLocale) {
            this.mLanguage = uLocale;
            return this;
        }

        public Builder setMasteringIndication(int i) {
            if (i != 0 && i != 1 && i != 2 && i != 3 && i != 4) {
                throw new IllegalArgumentException("Unknown mastering indication: " + i);
            }
            this.mMasteringIndication = i;
            return this;
        }

        public Builder setLabels(Map<ULocale, CharSequence> map) {
            this.mLabels = new HashMap(map);
            return this;
        }

        public Builder setHasAudioDescription(boolean z) {
            this.mAudioDescriptionAvailable = z;
            return this;
        }

        public Builder setHasSpokenSubtitles(boolean z) {
            this.mSpokenSubtitlesAvailable = z;
            return this;
        }

        public Builder setHasDialogueEnhancement(boolean z) {
            this.mDialogueEnhancementAvailable = z;
            return this;
        }

        public AudioPresentation build() {
            return new AudioPresentation(this.mPresentationId, this.mProgramId, this.mLanguage, this.mMasteringIndication, this.mAudioDescriptionAvailable, this.mSpokenSubtitlesAvailable, this.mDialogueEnhancementAvailable, this.mLabels);
        }
    }
}
