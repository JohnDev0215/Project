package com.amazon.avod.content.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.classification.ClassifierType;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ClassifierConfig extends MediaConfigBase {
    public static final ClassifierConfig INSTANCE = new ClassifierConfig();
    private final ConfigurationValue<ClassifierType> mClassifierType = newEnumConfigValue("classifierParameters_type", ClassifierType.EXPONENTIAL_SMOOTHING, ClassifierType.class);
    private final ConfigurationValue<Integer> mWindowSize = newIntConfigValue("classifierParameters_windowSize", 10);
    private final ConfigurationValue<Float> mClassifierAlpha = newFloatConfigValue("classifierParameters_alpha", 0.7f);

    private ClassifierConfig() {
    }

    @Nonnull
    public ClassifierType getClassifierType() {
        return this.mClassifierType.getValue();
    }

    public int getWindowSize() {
        return this.mWindowSize.getValue().intValue();
    }

    public float getClassifierAlpha() {
        return this.mClassifierAlpha.getValue().floatValue();
    }
}
