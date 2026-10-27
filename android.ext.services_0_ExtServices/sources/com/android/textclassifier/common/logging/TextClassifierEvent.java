package com.android.textclassifier.common.logging;

import android.os.Bundle;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class TextClassifierEvent {
    private final int[] actionIndices;
    private final String[] entityTypes;
    private final int eventCategory;
    private TextClassificationContext eventContext;
    private final int eventIndex;
    private final int eventType;
    private final Bundle extras;
    private final String modelName;
    private final String resultId;
    private final float[] scores;

    void toString(StringBuilder sb) {
    }

    private TextClassifierEvent(Builder<?> builder) {
        this.eventCategory = ((Builder) builder).eventCategory;
        this.eventType = ((Builder) builder).eventType;
        this.entityTypes = ((Builder) builder).entityTypes;
        this.eventContext = ((Builder) builder).eventContext;
        this.resultId = ((Builder) builder).resultId;
        this.eventIndex = ((Builder) builder).eventIndex;
        this.scores = ((Builder) builder).scores;
        this.modelName = ((Builder) builder).modelName;
        this.actionIndices = ((Builder) builder).actionIndices;
        Locale unused = ((Builder) builder).locale;
        this.extras = ((Builder) builder).extras == null ? Bundle.EMPTY : ((Builder) builder).extras;
    }

    public int getEventCategory() {
        return this.eventCategory;
    }

    public int getEventType() {
        return this.eventType;
    }

    public String[] getEntityTypes() {
        return this.entityTypes;
    }

    public TextClassificationContext getEventContext() {
        return this.eventContext;
    }

    public String getResultId() {
        return this.resultId;
    }

    public int getEventIndex() {
        return this.eventIndex;
    }

    public float[] getScores() {
        return this.scores;
    }

    public String getModelName() {
        return this.modelName;
    }

    public int[] getActionIndices() {
        return this.actionIndices;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append("mEventCategory=");
        sb.append(this.eventCategory);
        sb.append(", mEventType=");
        sb.append(this.eventType);
        sb.append(", mEntityTypes=");
        sb.append(Arrays.toString(this.entityTypes));
        sb.append(", mEventContext=");
        sb.append(this.eventContext);
        sb.append(", mResultId=");
        sb.append(this.resultId);
        sb.append(", mEventIndex=");
        sb.append(this.eventIndex);
        sb.append(", mExtras=");
        sb.append(this.extras);
        sb.append(", mScores=");
        sb.append(Arrays.toString(this.scores));
        sb.append(", mModelName=");
        sb.append(this.modelName);
        sb.append(", mActionIndices=");
        sb.append(Arrays.toString(this.actionIndices));
        toString(sb);
        sb.append("}");
        return sb.toString();
    }

    public static abstract class Builder<T extends Builder<T>> {
        private int[] actionIndices;
        private String[] entityTypes;
        private final int eventCategory;
        private TextClassificationContext eventContext;
        private int eventIndex;
        private final int eventType;
        private Bundle extras;
        private Locale locale;
        private String modelName;
        private String resultId;
        private float[] scores;

        abstract T self();

        private Builder(int i, int i2) {
            this.entityTypes = new String[0];
            this.scores = new float[0];
            this.actionIndices = new int[0];
            this.eventCategory = i;
            this.eventType = i2;
        }

        public T setEntityTypes(String... strArr) {
            Preconditions.checkNotNull(strArr);
            String[] strArr2 = new String[strArr.length];
            this.entityTypes = strArr2;
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            return (T) self();
        }

        public T setEventContext(TextClassificationContext textClassificationContext) {
            this.eventContext = textClassificationContext;
            return (T) self();
        }

        public T setResultId(String str) {
            this.resultId = str;
            return (T) self();
        }

        public T setEventIndex(int i) {
            this.eventIndex = i;
            return (T) self();
        }

        public T setScores(float... fArr) {
            Preconditions.checkNotNull(fArr);
            float[] fArr2 = new float[fArr.length];
            this.scores = fArr2;
            System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
            return (T) self();
        }

        public T setModelName(String str) {
            this.modelName = str;
            return (T) self();
        }

        public T setActionIndices(int... iArr) {
            int[] iArr2 = new int[iArr.length];
            this.actionIndices = iArr2;
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            return (T) self();
        }

        public T setLocale(Locale locale) {
            this.locale = locale;
            return (T) self();
        }

        public T setExtras(Bundle bundle) {
            Preconditions.checkNotNull(bundle);
            this.extras = bundle;
            return (T) self();
        }
    }

    public static final class TextSelectionEvent extends TextClassifierEvent {
        final int relativeSuggestedWordEndIndex;
        final int relativeSuggestedWordStartIndex;
        final int relativeWordEndIndex;
        final int relativeWordStartIndex;

        private TextSelectionEvent(Builder builder) {
            super(builder);
            this.relativeWordStartIndex = builder.relativeWordStartIndex;
            this.relativeWordEndIndex = builder.relativeWordEndIndex;
            this.relativeSuggestedWordStartIndex = builder.relativeSuggestedWordStartIndex;
            this.relativeSuggestedWordEndIndex = builder.relativeSuggestedWordEndIndex;
        }

        public int getRelativeWordStartIndex() {
            return this.relativeWordStartIndex;
        }

        public int getRelativeWordEndIndex() {
            return this.relativeWordEndIndex;
        }

        public int getRelativeSuggestedWordStartIndex() {
            return this.relativeSuggestedWordStartIndex;
        }

        public int getRelativeSuggestedWordEndIndex() {
            return this.relativeSuggestedWordEndIndex;
        }

        @Override // com.android.textclassifier.common.logging.TextClassifierEvent
        void toString(StringBuilder sb) {
            sb.append(", getRelativeWordStartIndex=");
            sb.append(this.relativeWordStartIndex);
            sb.append(", getRelativeWordEndIndex=");
            sb.append(this.relativeWordEndIndex);
            sb.append(", getRelativeSuggestedWordStartIndex=");
            sb.append(this.relativeSuggestedWordStartIndex);
            sb.append(", getRelativeSuggestedWordEndIndex=");
            sb.append(this.relativeSuggestedWordEndIndex);
        }

        public static final class Builder extends Builder<Builder> {
            int relativeSuggestedWordEndIndex;
            int relativeSuggestedWordStartIndex;
            int relativeWordEndIndex;
            int relativeWordStartIndex;

            @Override // com.android.textclassifier.common.logging.TextClassifierEvent.Builder
            Builder self() {
                return this;
            }

            @Override // com.android.textclassifier.common.logging.TextClassifierEvent.Builder
            /* bridge */ /* synthetic */ Builder self() {
                self();
                return this;
            }

            public Builder(int i) {
                super(1, i);
            }

            public Builder setRelativeWordStartIndex(int i) {
                this.relativeWordStartIndex = i;
                return this;
            }

            public Builder setRelativeWordEndIndex(int i) {
                this.relativeWordEndIndex = i;
                return this;
            }

            public Builder setRelativeSuggestedWordStartIndex(int i) {
                this.relativeSuggestedWordStartIndex = i;
                return this;
            }

            public Builder setRelativeSuggestedWordEndIndex(int i) {
                this.relativeSuggestedWordEndIndex = i;
                return this;
            }

            public TextSelectionEvent build() {
                return new TextSelectionEvent(this);
            }
        }
    }

    public static final class TextLinkifyEvent extends TextClassifierEvent {
        private TextLinkifyEvent(Builder builder) {
            super(builder);
        }

        public static final class Builder extends Builder<Builder> {
            @Override // com.android.textclassifier.common.logging.TextClassifierEvent.Builder
            Builder self() {
                return this;
            }

            @Override // com.android.textclassifier.common.logging.TextClassifierEvent.Builder
            /* bridge */ /* synthetic */ Builder self() {
                self();
                return this;
            }

            public Builder(int i) {
                super(2, i);
            }

            public TextLinkifyEvent build() {
                return new TextLinkifyEvent(this);
            }
        }
    }

    public static final class LanguageDetectionEvent extends TextClassifierEvent {
        private LanguageDetectionEvent(Builder builder) {
            super(builder);
        }

        public static final class Builder extends Builder<Builder> {
            @Override // com.android.textclassifier.common.logging.TextClassifierEvent.Builder
            Builder self() {
                return this;
            }

            @Override // com.android.textclassifier.common.logging.TextClassifierEvent.Builder
            /* bridge */ /* synthetic */ Builder self() {
                self();
                return this;
            }

            public Builder(int i) {
                super(4, i);
            }

            public LanguageDetectionEvent build() {
                return new LanguageDetectionEvent(this);
            }
        }
    }

    public static final class ConversationActionsEvent extends TextClassifierEvent {
        private ConversationActionsEvent(Builder builder) {
            super(builder);
        }

        public static final class Builder extends Builder<Builder> {
            @Override // com.android.textclassifier.common.logging.TextClassifierEvent.Builder
            Builder self() {
                return this;
            }

            @Override // com.android.textclassifier.common.logging.TextClassifierEvent.Builder
            /* bridge */ /* synthetic */ Builder self() {
                self();
                return this;
            }

            public Builder(int i) {
                super(3, i);
            }

            public ConversationActionsEvent build() {
                return new ConversationActionsEvent(this);
            }
        }
    }
}
