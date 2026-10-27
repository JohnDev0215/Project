package android.media.audiopolicy;

import android.annotation.SystemApi;
import android.media.AudioAttributes;
import android.os.Parcel;
import android.util.Log;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class AudioMixingRule {
    public static final int RULE_EXCLUDE_ATTRIBUTE_CAPTURE_PRESET = 32770;
    public static final int RULE_EXCLUDE_ATTRIBUTE_USAGE = 32769;
    public static final int RULE_EXCLUDE_UID = 32772;
    public static final int RULE_EXCLUDE_USERID = 32776;
    private static final int RULE_EXCLUSION_MASK = 32768;
    public static final int RULE_MATCH_ATTRIBUTE_CAPTURE_PRESET = 2;
    public static final int RULE_MATCH_ATTRIBUTE_USAGE = 1;
    public static final int RULE_MATCH_UID = 4;
    public static final int RULE_MATCH_USERID = 8;
    private boolean mAllowPrivilegedPlaybackCapture;
    private final ArrayList<AudioMixMatchCriterion> mCriteria;
    private final int mTargetMixType;
    private boolean mVoiceCommunicationCaptureAllowed;

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isAudioAttributeRule(int i) {
        return i == 1 || i == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPlayerRule(int i) {
        int i2 = i & (-32769);
        return i2 == 1 || i2 == 4 || i2 == 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isValidAttributesSystemApiRule(int i) {
        return i == 1 || i == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isValidRule(int i) {
        int i2 = i & (-32769);
        return i2 == 1 || i2 == 2 || i2 == 4 || i2 == 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isValidSystemApiRule(int i) {
        return i == 1 || i == 2 || i == 4 || i == 8;
    }

    private AudioMixingRule(int i, ArrayList<AudioMixMatchCriterion> arrayList, boolean z, boolean z2) {
        this.mAllowPrivilegedPlaybackCapture = false;
        this.mVoiceCommunicationCaptureAllowed = false;
        this.mCriteria = arrayList;
        this.mTargetMixType = i;
        this.mAllowPrivilegedPlaybackCapture = z;
        this.mVoiceCommunicationCaptureAllowed = z2;
    }

    public static final class AudioMixMatchCriterion {
        final AudioAttributes mAttr;
        final int mIntProp;
        final int mRule;

        AudioMixMatchCriterion(AudioAttributes audioAttributes, int i) {
            this.mAttr = audioAttributes;
            this.mIntProp = Integer.MIN_VALUE;
            this.mRule = i;
        }

        AudioMixMatchCriterion(Integer num, int i) {
            this.mAttr = null;
            this.mIntProp = num.intValue();
            this.mRule = i;
        }

        public int hashCode() {
            return Objects.hash(this.mAttr, Integer.valueOf(this.mIntProp), Integer.valueOf(this.mRule));
        }

        void writeToParcel(Parcel parcel) {
            parcel.writeInt(this.mRule);
            int i = this.mRule & (-32769);
            if (i == 1) {
                parcel.writeInt(this.mAttr.getUsage());
                return;
            }
            if (i == 2) {
                parcel.writeInt(this.mAttr.getCapturePreset());
            } else if (i == 4 || i == 8) {
                parcel.writeInt(this.mIntProp);
            } else {
                Log.e("AudioMixMatchCriterion", "Unknown match rule" + i + " when writing to Parcel");
                parcel.writeInt(-1);
            }
        }

        public AudioAttributes getAudioAttributes() {
            return this.mAttr;
        }

        public int getIntProp() {
            return this.mIntProp;
        }

        public int getRule() {
            return this.mRule;
        }
    }

    boolean isAffectingUsage(int i) {
        for (AudioMixMatchCriterion audioMixMatchCriterion : this.mCriteria) {
            if ((audioMixMatchCriterion.mRule & 1) != 0 && audioMixMatchCriterion.mAttr != null && audioMixMatchCriterion.mAttr.getUsage() == i) {
                return true;
            }
        }
        return false;
    }

    boolean containsMatchAttributeRuleForUsage(int i) {
        for (AudioMixMatchCriterion audioMixMatchCriterion : this.mCriteria) {
            if (audioMixMatchCriterion.mRule == 1 && audioMixMatchCriterion.mAttr != null && audioMixMatchCriterion.mAttr.getUsage() == i) {
                return true;
            }
        }
        return false;
    }

    private static boolean areCriteriaEquivalent(ArrayList<AudioMixMatchCriterion> arrayList, ArrayList<AudioMixMatchCriterion> arrayList2) {
        if (arrayList == null || arrayList2 == null) {
            return false;
        }
        if (arrayList == arrayList2) {
            return true;
        }
        return arrayList.size() == arrayList2.size() && arrayList.hashCode() == arrayList2.hashCode();
    }

    int getTargetMixType() {
        return this.mTargetMixType;
    }

    public ArrayList<AudioMixMatchCriterion> getCriteria() {
        return this.mCriteria;
    }

    public boolean allowPrivilegedPlaybackCapture() {
        return this.mAllowPrivilegedPlaybackCapture;
    }

    public boolean voiceCommunicationCaptureAllowed() {
        return this.mVoiceCommunicationCaptureAllowed;
    }

    public void setVoiceCommunicationCaptureAllowed(boolean z) {
        this.mVoiceCommunicationCaptureAllowed = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AudioMixingRule audioMixingRule = (AudioMixingRule) obj;
        return this.mTargetMixType == audioMixingRule.mTargetMixType && areCriteriaEquivalent(this.mCriteria, audioMixingRule.mCriteria) && this.mAllowPrivilegedPlaybackCapture == audioMixingRule.mAllowPrivilegedPlaybackCapture && this.mVoiceCommunicationCaptureAllowed == audioMixingRule.mVoiceCommunicationCaptureAllowed;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mTargetMixType), this.mCriteria, Boolean.valueOf(this.mAllowPrivilegedPlaybackCapture), Boolean.valueOf(this.mVoiceCommunicationCaptureAllowed));
    }

    public static class Builder {
        private int mTargetMixType = -1;
        private boolean mAllowPrivilegedPlaybackCapture = false;
        private boolean mVoiceCommunicationCaptureAllowed = false;
        private ArrayList<AudioMixMatchCriterion> mCriteria = new ArrayList<>();

        public Builder addRule(AudioAttributes audioAttributes, int i) throws IllegalArgumentException {
            if (!AudioMixingRule.isValidAttributesSystemApiRule(i)) {
                throw new IllegalArgumentException("Illegal rule value " + i);
            }
            return checkAddRuleObjInternal(i, audioAttributes);
        }

        public Builder excludeRule(AudioAttributes audioAttributes, int i) throws IllegalArgumentException {
            if (!AudioMixingRule.isValidAttributesSystemApiRule(i)) {
                throw new IllegalArgumentException("Illegal rule value " + i);
            }
            return checkAddRuleObjInternal(i | 32768, audioAttributes);
        }

        public Builder addMixRule(int i, Object obj) throws IllegalArgumentException {
            if (!AudioMixingRule.isValidSystemApiRule(i)) {
                throw new IllegalArgumentException("Illegal rule value " + i);
            }
            return checkAddRuleObjInternal(i, obj);
        }

        public Builder excludeMixRule(int i, Object obj) throws IllegalArgumentException {
            if (!AudioMixingRule.isValidSystemApiRule(i)) {
                throw new IllegalArgumentException("Illegal rule value " + i);
            }
            return checkAddRuleObjInternal(i | 32768, obj);
        }

        public Builder allowPrivilegedPlaybackCapture(boolean z) {
            this.mAllowPrivilegedPlaybackCapture = z;
            return this;
        }

        public Builder voiceCommunicationCaptureAllowed(boolean z) {
            this.mVoiceCommunicationCaptureAllowed = z;
            return this;
        }

        private Builder checkAddRuleObjInternal(int i, Object obj) throws IllegalArgumentException {
            if (obj != null) {
                if (AudioMixingRule.isValidRule(i)) {
                    if (AudioMixingRule.isAudioAttributeRule((-32769) & i)) {
                        if (!(obj instanceof AudioAttributes)) {
                            throw new IllegalArgumentException("Invalid AudioAttributes argument");
                        }
                        return addRuleInternal((AudioAttributes) obj, null, i);
                    }
                    if (!(obj instanceof Integer)) {
                        throw new IllegalArgumentException("Invalid Integer argument");
                    }
                    return addRuleInternal(null, (Integer) obj, i);
                }
                throw new IllegalArgumentException("Illegal rule value " + i);
            }
            throw new IllegalArgumentException("Illegal null argument for mixing rule");
        }

        private Builder addRuleInternal(AudioAttributes audioAttributes, Integer num, int i) throws IllegalArgumentException {
            int i2 = this.mTargetMixType;
            if (i2 == -1) {
                if (AudioMixingRule.isPlayerRule(i)) {
                    this.mTargetMixType = 0;
                } else {
                    this.mTargetMixType = 1;
                }
            } else if ((i2 == 0 && !AudioMixingRule.isPlayerRule(i)) || (this.mTargetMixType == 1 && AudioMixingRule.isPlayerRule(i))) {
                throw new IllegalArgumentException("Incompatible rule for mix");
            }
            synchronized (this.mCriteria) {
                int i3 = i & (-32769);
                for (AudioMixMatchCriterion audioMixMatchCriterion : this.mCriteria) {
                    if ((audioMixMatchCriterion.mRule & (-32769)) == i3) {
                        if (i3 == 1) {
                            if (audioMixMatchCriterion.mAttr.getUsage() == audioAttributes.getUsage()) {
                                if (audioMixMatchCriterion.mRule == i) {
                                    return this;
                                }
                                throw new IllegalArgumentException("Contradictory rule exists for " + audioAttributes);
                            }
                        } else if (i3 == 2) {
                            if (audioMixMatchCriterion.mAttr.getCapturePreset() == audioAttributes.getCapturePreset()) {
                                if (audioMixMatchCriterion.mRule == i) {
                                    return this;
                                }
                                throw new IllegalArgumentException("Contradictory rule exists for " + audioAttributes);
                            }
                        } else if (i3 == 4) {
                            if (audioMixMatchCriterion.mIntProp == num.intValue()) {
                                if (audioMixMatchCriterion.mRule == i) {
                                    return this;
                                }
                                throw new IllegalArgumentException("Contradictory rule exists for UID " + num);
                            }
                        } else if (i3 == 8 && audioMixMatchCriterion.mIntProp == num.intValue()) {
                            if (audioMixMatchCriterion.mRule == i) {
                                return this;
                            }
                            throw new IllegalArgumentException("Contradictory rule exists for userId " + num);
                        }
                    }
                }
                if (i3 == 1 || i3 == 2) {
                    this.mCriteria.add(new AudioMixMatchCriterion(audioAttributes, i));
                } else {
                    if (i3 != 4 && i3 != 8) {
                        throw new IllegalStateException("Unreachable code in addRuleInternal()");
                    }
                    this.mCriteria.add(new AudioMixMatchCriterion(num, i));
                }
                return this;
            }
        }

        Builder addRuleFromParcel(Parcel parcel) throws IllegalArgumentException {
            AudioAttributes audioAttributesBuild;
            Integer num;
            int i = parcel.readInt();
            int i2 = (-32769) & i;
            AudioAttributes audioAttributes = null;
            if (i2 == 1) {
                audioAttributesBuild = new AudioAttributes.Builder().setUsage(parcel.readInt()).build();
            } else {
                if (i2 != 2) {
                    if (i2 == 4 || i2 == 8) {
                        num = new Integer(parcel.readInt());
                        return addRuleInternal(audioAttributes, num, i);
                    }
                    parcel.readInt();
                    throw new IllegalArgumentException("Illegal rule value " + i + " in parcel");
                }
                audioAttributesBuild = new AudioAttributes.Builder().setInternalCapturePreset(parcel.readInt()).build();
            }
            num = null;
            audioAttributes = audioAttributesBuild;
            return addRuleInternal(audioAttributes, num, i);
        }

        public AudioMixingRule build() {
            return new AudioMixingRule(this.mTargetMixType, this.mCriteria, this.mAllowPrivilegedPlaybackCapture, this.mVoiceCommunicationCaptureAllowed);
        }
    }
}
