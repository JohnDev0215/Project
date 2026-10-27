package com.amazon.avod.qos;

import amazon.android.config.ConfigurationValue;
import android.util.Log;
import com.amazon.avod.media.error.DrmErrorCode;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.ServiceErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.error.MediaException;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorReportingConfiguration {
    private static final ImmutableMap<MediaErrorCode, Integer> SEVERITY_OVERRIDES = ImmutableMap.builder().put(StandardErrorCode.DATA_CONNECTION_UNAVAILABLE, 4).put(StandardErrorCode.DISK_FULL, 3).put(ServiceErrorCode.NO_AVAILABLE_DOWNLOAD_RIGHTS, 3).put(ServiceErrorCode.NO_AVAILABLE_ONLINE_STREAMS, 3).put(ServiceErrorCode.INVALID_GEO_IP, 3).put(DrmErrorCode.LICENSE_EXPIRED, 3).put(DrmErrorCode.LICENSE_EXPIRED_PLAYBACK_CLOCK, 3).put(DrmErrorCode.LICENSE_EXPIRED_LICENSE_CLOCK, 3).put(DrmErrorCode.PLAYREADY_HDCPFAIL, 3).build();

    public int getSeverityFor(MediaException mediaException, boolean z, int i) {
        Integer num;
        if (ErrorReportingConfig.getInstance().getNonFatalHttpCodes().contains(Integer.valueOf(i))) {
            return 3;
        }
        ImmutableMap<MediaErrorCode, Integer> immutableMap = SEVERITY_OVERRIDES;
        Integer num2 = immutableMap.get(mediaException.getErrorCode().getExternalCode());
        if (num2 != null) {
            return num2.intValue();
        }
        Throwable cause = mediaException.getCause();
        if (cause == null || !(cause instanceof MediaException) || (num = immutableMap.get(((MediaException) cause).getErrorCode().getExternalCode())) == null) {
            return z ? 3 : 2;
        }
        return num.intValue();
    }

    static class ErrorReportingConfig extends MediaConfigBase {
        private Set<Integer> mCachedNonFatalHttpCodes;
        private final ConfigurationValue<Set<String>> mNonFatalHttpCodes = newSemicolonDelimitedStringSetConfigurationValue("playback_nonFatalHttpCodes", new String[]{"260"});
        private final ConfigurationValue<Boolean> mShouldDedupeFatalErrorEvents = newBooleanConfigValue("playback_dedupeFatalQosErrorEvents", true);

        static ErrorReportingConfig getInstance() {
            return SingletonHolder.INSTANCE;
        }

        private static class SingletonHolder {
            private static ErrorReportingConfig INSTANCE = new ErrorReportingConfig();

            private SingletonHolder() {
            }
        }

        @Nonnull
        public Set<Integer> getNonFatalHttpCodes() {
            if (this.mCachedNonFatalHttpCodes == null) {
                Set<String> value = this.mNonFatalHttpCodes.getValue();
                HashSet hashSetNewHashSet = Sets.newHashSet();
                try {
                    Iterator<String> it = value.iterator();
                    while (it.hasNext()) {
                        hashSetNewHashSet.add(Integer.valueOf(Integer.parseInt(it.next())));
                    }
                } catch (NumberFormatException e) {
                    Log.wtf("ErrorReportingConfig", e);
                }
                this.mCachedNonFatalHttpCodes = hashSetNewHashSet;
            }
            return this.mCachedNonFatalHttpCodes;
        }

        public boolean shouldDedupeFatalErrorEvents() {
            return this.mShouldDedupeFatalErrorEvents.getValue().booleanValue();
        }
    }
}
