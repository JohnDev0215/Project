package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class SeedContextInput {
    private final SeedContextTypeEnum contextType;
    private final String contextTypeEnumValue;
    private final String contextValue;

    public interface BuildStep {
        SeedContextInput build();

        BuildStep contextTypeEnumValue(String str);

        BuildStep contextValue(String str);
    }

    public interface ContextTypeStep {
        BuildStep contextType(SeedContextTypeEnum seedContextTypeEnum);
    }

    public SeedContextTypeEnum getContextType() {
        return this.contextType;
    }

    public String getContextTypeEnumValue() {
        return this.contextTypeEnumValue;
    }

    public String getContextValue() {
        return this.contextValue;
    }

    private SeedContextInput(SeedContextTypeEnum seedContextTypeEnum, String str, String str2) {
        this.contextType = seedContextTypeEnum;
        this.contextTypeEnumValue = str;
        this.contextValue = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SeedContextInput.class != obj.getClass()) {
            return false;
        }
        SeedContextInput seedContextInput = (SeedContextInput) obj;
        return ObjectsCompat.equals(getContextType(), seedContextInput.getContextType()) && ObjectsCompat.equals(getContextTypeEnumValue(), seedContextInput.getContextTypeEnumValue()) && ObjectsCompat.equals(getContextValue(), seedContextInput.getContextValue());
    }

    public int hashCode() {
        return (getContextType() + getContextTypeEnumValue() + getContextValue()).hashCode();
    }

    public static ContextTypeStep builder() {
        return new Builder();
    }

    public static class Builder implements ContextTypeStep, BuildStep {
        private SeedContextTypeEnum contextType;
        private String contextTypeEnumValue;
        private String contextValue;

        @Override // com.amazon.mp3.amplifyqueue.model.SeedContextInput.BuildStep
        public SeedContextInput build() {
            return new SeedContextInput(this.contextType, this.contextTypeEnumValue, this.contextValue);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.SeedContextInput.ContextTypeStep
        public BuildStep contextType(SeedContextTypeEnum seedContextTypeEnum) {
            Objects.requireNonNull(seedContextTypeEnum);
            this.contextType = seedContextTypeEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.SeedContextInput.BuildStep
        public BuildStep contextTypeEnumValue(String str) {
            this.contextTypeEnumValue = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.SeedContextInput.BuildStep
        public BuildStep contextValue(String str) {
            this.contextValue = str;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.SeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.SeedContextInput.ContextTypeStep
        public CopyOfBuilder contextType(SeedContextTypeEnum seedContextTypeEnum) {
            return (CopyOfBuilder) super.contextType(seedContextTypeEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.SeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.SeedContextInput.BuildStep
        public CopyOfBuilder contextTypeEnumValue(String str) {
            return (CopyOfBuilder) super.contextTypeEnumValue(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.SeedContextInput.Builder, com.amazon.mp3.amplifyqueue.model.SeedContextInput.BuildStep
        public CopyOfBuilder contextValue(String str) {
            return (CopyOfBuilder) super.contextValue(str);
        }
    }
}
