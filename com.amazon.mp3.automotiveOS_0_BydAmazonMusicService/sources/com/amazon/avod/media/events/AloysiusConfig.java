package com.amazon.avod.media.events;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusConfig extends MediaConfigBase {
    private final ConfigurationValue<Boolean> mAloysiusEnabled;
    private final ConfigurationValue<Boolean> mDeleteOversizeEventDB;
    public final ConfigurationValue<Boolean> mIsLegacyMediaEventsDatabaseCleared;
    private final ConfigurationValue<Boolean> mLiveContentOnly;
    private final ConfigurationValue<Integer> mMaxDBSearchSizeAtBootstrap;
    private final ConfigurationValue<Integer> mMaxEventsToLoadAtBootstrap;
    private final ConfigurationValue<Integer> mMaxEventsToSerializePerCall;
    private final ConfigurationValue<Integer> mMaxRecordsAllowedInDB;
    private final ConfigurationValue<Integer> mMaxRecordsToSendPerDispatch;
    private final ConfigurationValue<Set<String>> mMediaEventTypeBlocklist;
    private final ConfigurationValue<Integer> mMinDiskSpaceMegabytesRequiredForAloysius;
    private final ConfigurationValue<Boolean> mOldNetworkMediaEventSerialization;
    private final ConfigurationValue<Boolean> mReportBootstrapInfoAsDiagnostics;
    private final ConfigurationValue<Boolean> mReportBootstrapInfoAsErrors;
    private final ConfigurationValue<Integer> mSQLiteMaxVariableCount;
    private final ConfigurationValue<Boolean> mSendWeblabEventOncePerSession;
    private final ConfigurationValue<Boolean> mShouldClearLegacyMediaEventsDatabase;
    private final ConfigurationValue<Boolean> mShouldDropAloysiusEventsOnInsufficientDiskSpace;
    private final ConfigurationValue<Boolean> mShouldDropDatabaseOnGiantRow;
    private final ConfigurationValue<Boolean> mShouldDropGiantRowsInsteadOfInserting;
    private final ConfigurationValue<Boolean> mShouldEnableAloysiusEventsByBootstrap;
    private final ConfigurationValue<Boolean> mShouldForceDispatchAloysiusEventsByBootstrap;
    private final ConfigurationValue<Boolean> mShouldReportFragmentInformation;
    private final ConfigurationValue<Boolean> mShouldReportQualityScore;
    private final ConfigurationValue<Boolean> mShouldReportRetriableManifestError;
    private final ConfigurationValue<Boolean> mShouldUnifyMediaEventReporters;
    private final ConfigurationValue<Boolean> mShouldUseInteractionStateLogic;
    private final ConfigurationValue<Boolean> mShouldUseRealDisplayMetricsForReporting;
    private final ConfigurationValue<Boolean> mSuppressSQLiteExceptionsInMediaDAO;

    private static class InstanceHolder {
        private static final AloysiusConfig ALOYSIUS_CONFIG = new AloysiusConfig();

        private InstanceHolder() {
        }
    }

    public static AloysiusConfig getInstance() {
        return InstanceHolder.ALOYSIUS_CONFIG;
    }

    private AloysiusConfig() {
        this.mAloysiusEnabled = newBooleanConfigValue("isAloysiusEnabled", true);
        this.mLiveContentOnly = newBooleanConfigValue("playback.aloysius.live-content-only", true);
        this.mOldNetworkMediaEventSerialization = newBooleanConfigValue("playback.aloysius.old-network-media-event-serialization", false);
        this.mMaxRecordsToSendPerDispatch = newIntConfigValue("playback.aloysius.max-records-to-send-per-dispatch", 3);
        this.mMaxEventsToSerializePerCall = newIntConfigValue("playback.aloysius.max-events-to-serialize-per-call", 200);
        this.mMaxEventsToLoadAtBootstrap = newIntConfigValue("playback.aloysius.max-events-to-load-at-bootstrap", 400);
        this.mDeleteOversizeEventDB = newBooleanConfigValue("playback.aloysius.delete-oversized-event-db2", true);
        this.mMaxRecordsAllowedInDB = newIntConfigValue("playback.aloysius.max-records-allowed-in-db", 100);
        this.mMaxDBSearchSizeAtBootstrap = newIntConfigValue("playback.aloysius.max-db-search-size-at-bootstrap", 10000);
        this.mMediaEventTypeBlocklist = newSemicolonDelimitedStringSetConfigurationValue("playback.aloysius.media-event-type-blacklist", new String[0]);
        this.mSendWeblabEventOncePerSession = newBooleanConfigValue("playback.aloysius.sendWeblabEventOncePerSession", true);
        this.mShouldDropDatabaseOnGiantRow = newBooleanConfigValue("playback.aloysius.shouldDropDatabaseOnGiantRow", true);
        this.mShouldDropGiantRowsInsteadOfInserting = newBooleanConfigValue("playback.aloysius.shouldDropGiantRowsInsteadOfInserting", true);
        this.mShouldUseInteractionStateLogic = newBooleanConfigValue("playback.aloysius.shouldUseInteractionStateLogic", true);
        this.mSQLiteMaxVariableCount = newIntConfigValue("playback.aloysius.mSQLiteMaxVariableCount", 900);
        this.mShouldReportFragmentInformation = newBooleanConfigValue("playback.aloysius.add-fragment-information-to-acquisition-event", true);
        ConfigType configType = ConfigType.SERVER;
        this.mShouldClearLegacyMediaEventsDatabase = newBooleanConfigValue("playback.aloysius.shouldClearLegacyMediaEventsDatabase", true, configType);
        this.mIsLegacyMediaEventsDatabaseCleared = newBooleanConfigValue("playback.aloysius.isLegacyMediaEventsLegacyDatabaseCleared", false, ConfigType.INTERNAL);
        this.mSuppressSQLiteExceptionsInMediaDAO = newBooleanConfigValue("playback.aloysius.suppressSQLiteExceptionsInMediaDAO", true, configType);
        this.mMinDiskSpaceMegabytesRequiredForAloysius = newIntConfigValue("playback.aloysius.minDiskSpaceMegabytesRequiredForAloysius", 50, configType);
        this.mShouldDropAloysiusEventsOnInsufficientDiskSpace = newBooleanConfigValue("playback.aloysius.shouldDropAloysiusEventsOnInsufficientDiskSpace", true, configType);
        this.mReportBootstrapInfoAsErrors = newBooleanConfigValue("playback.aloysius.reportBootstrapInfoAsErrors", true, configType);
        this.mReportBootstrapInfoAsDiagnostics = newBooleanConfigValue("playback.aloysius.reportBootstrapInfoAsDiagnostics", true, configType);
        this.mShouldUnifyMediaEventReporters = newBooleanConfigValue("playback.aloysius.shouldUnifyMediaEventReporters", true, configType);
        this.mShouldReportQualityScore = newBooleanConfigValue("playback.aloysius.shouldReportQualityScore", true, configType);
        this.mShouldReportRetriableManifestError = newBooleanConfigValue("playback.aloysius.shouldReportRetriableManifestError", true, configType);
        this.mShouldUseRealDisplayMetricsForReporting = newBooleanConfigValue("playback.aloysius.shouldUseRealDisplayMetricsForReporting", true, configType);
        this.mShouldEnableAloysiusEventsByBootstrap = newBooleanConfigValue("playback.aloysius.shouldEnableAloysiusEventsByBootstrap", true, configType);
        this.mShouldForceDispatchAloysiusEventsByBootstrap = newBooleanConfigValue("playback.aloysius.shouldForceDispatchAloysiusEventsByBootstrap", true, configType);
    }

    public boolean isAloysiusEnabled() {
        return this.mAloysiusEnabled.getValue().booleanValue();
    }

    public boolean isLiveContentOnlyEnabled() {
        return this.mLiveContentOnly.getValue().booleanValue();
    }

    public boolean isOldNetworkMediaEventSerializationEnabled() {
        return this.mOldNetworkMediaEventSerialization.getValue().booleanValue();
    }

    public int getMaxRecordsToSendPerDispatch() {
        return this.mMaxRecordsToSendPerDispatch.getValue().intValue();
    }

    public int getMaxEventsToSerializePerCall() {
        return this.mMaxEventsToSerializePerCall.getValue().intValue();
    }

    public int getMaxEventsToLoadAtBootstrap() {
        return this.mMaxEventsToLoadAtBootstrap.getValue().intValue();
    }

    public boolean shouldDeleteOversizedEventDB() {
        return this.mDeleteOversizeEventDB.getValue().booleanValue();
    }

    public int getMaxRecordsAllowedInDB() {
        return this.mMaxRecordsAllowedInDB.getValue().intValue();
    }

    public int getMaxDBSearchSizeAtBootstrap() {
        return this.mMaxDBSearchSizeAtBootstrap.getValue().intValue();
    }

    public boolean sendWeblabEventOncePerSession() {
        return this.mSendWeblabEventOncePerSession.getValue().booleanValue();
    }

    @Nonnull
    public Set<MediaEvent.MediaEventType> getMediaEventTypeBlocklist() {
        HashSet hashSetNewHashSet = Sets.newHashSet();
        Iterator<String> it = this.mMediaEventTypeBlocklist.getValue().iterator();
        while (it.hasNext()) {
            try {
                hashSetNewHashSet.add(Enum.valueOf(MediaEvent.MediaEventType.class, it.next()));
            } catch (IllegalArgumentException | NullPointerException unused) {
            }
        }
        return hashSetNewHashSet;
    }

    public boolean shouldDropDatabaseOnGiantRow() {
        return this.mShouldDropDatabaseOnGiantRow.getValue().booleanValue();
    }

    public boolean shouldDropGiantRowsInsteadOfInserting() {
        return this.mShouldDropGiantRowsInsteadOfInserting.getValue().booleanValue();
    }

    public boolean shouldUseInteractionStateLogic() {
        return this.mShouldUseInteractionStateLogic.getValue().booleanValue();
    }

    public int getSQLiteMaxVariableCount() {
        return this.mSQLiteMaxVariableCount.getValue().intValue();
    }

    public boolean shouldReportFragmentInforamtion() {
        return this.mShouldReportFragmentInformation.getValue().booleanValue();
    }

    public boolean shouldClearLegacyMediaEventsDatabase() {
        return this.mShouldClearLegacyMediaEventsDatabase.getValue().booleanValue();
    }

    public boolean shouldSuppressSQLiteExceptionsInMediaDAO() {
        return this.mSuppressSQLiteExceptionsInMediaDAO.getValue().booleanValue();
    }

    public int getMinDiskSpaceMegabytesRequiredForAloysius() {
        return this.mMinDiskSpaceMegabytesRequiredForAloysius.getValue().intValue();
    }

    public boolean shouldDropAloysiusEventsOnInsufficientDiskSpace() {
        return this.mShouldDropAloysiusEventsOnInsufficientDiskSpace.getValue().booleanValue();
    }

    public boolean shouldReportBootstrapInfoAsErrors() {
        return this.mReportBootstrapInfoAsErrors.getValue().booleanValue();
    }

    public boolean shouldReportBootstrapInfoAsDiagnostics() {
        return this.mReportBootstrapInfoAsDiagnostics.getValue().booleanValue();
    }

    public boolean shouldUnifyMediaEventReporters() {
        return this.mShouldUnifyMediaEventReporters.getValue().booleanValue();
    }

    public boolean shouldReportQualityScore() {
        return this.mShouldReportQualityScore.getValue().booleanValue();
    }

    public boolean shouldReportRetriableManifestError() {
        return this.mShouldReportRetriableManifestError.getValue().booleanValue();
    }

    public boolean shouldUseRealDisplayMetricsForReporting() {
        return this.mShouldUseRealDisplayMetricsForReporting.getValue().booleanValue();
    }

    public boolean shouldEnableAloysiusEventsByBootstrap() {
        return this.mShouldEnableAloysiusEventsByBootstrap.getValue().booleanValue();
    }

    public boolean shouldForceDispatchAloysiusEventsByBootstrap() {
        return this.mShouldForceDispatchAloysiusEventsByBootstrap.getValue().booleanValue();
    }
}
