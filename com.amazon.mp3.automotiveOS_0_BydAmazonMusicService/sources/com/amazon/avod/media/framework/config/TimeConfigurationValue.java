package com.amazon.avod.media.framework.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class TimeConfigurationValue {
    private final ConfigurationValue<Long> mLongValue;
    private final TimeUnit mTimeUnit;

    public TimeConfigurationValue(ConfigurationValue<Long> configurationValue, TimeUnit timeUnit) {
        this.mTimeUnit = timeUnit;
        this.mLongValue = configurationValue;
    }

    public TimeSpan getValue() {
        return new TimeSpan(TimeUnit.NANOSECONDS.convert(this.mLongValue.getValue().longValue(), this.mTimeUnit));
    }

    public void updateValue(@Nonnull TimeSpan timeSpan) {
        Preconditions.checkNotNull(timeSpan, "TimeSpan(newValue)");
        this.mLongValue.updateValue(Long.valueOf(this.mTimeUnit.convert(timeSpan.getTotalNanoSeconds(), TimeUnit.NANOSECONDS)));
    }
}
