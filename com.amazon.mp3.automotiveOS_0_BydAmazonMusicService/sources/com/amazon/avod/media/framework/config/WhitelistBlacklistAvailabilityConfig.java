package com.amazon.avod.media.framework.config;

import amazon.android.config.ConfigurationValue;
import android.os.Build;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class WhitelistBlacklistAvailabilityConfig extends MediaConfigBase {
    private final ConfigurationValue<Boolean> mDeviceTypeDefaultAvailability;
    private final ConfigurationValue<Set<String>> mManufacturerBlacklist;
    private final ConfigurationValue<Set<String>> mManufacturerWhitelist;
    private final ConfigurationValue<Set<String>> mModelBlacklist;
    private final ConfigurationValue<Set<String>> mModelWhitelist;
    protected final String mPrefix;

    enum ListingResult {
        WHITE_LISTED,
        BLACK_LISTED,
        NOT_LISTED
    }

    public WhitelistBlacklistAvailabilityConfig(@Nonnull String str, boolean z) {
        this.mPrefix = (String) Preconditions.checkNotNull(str, "prefix");
        this.mModelBlacklist = newSemicolonDelimitedStringSetConfigurationValue(str + "_modelBlacklist", new String[0]);
        this.mModelWhitelist = newSemicolonDelimitedStringSetConfigurationValue(str + "_modelWhitelist", new String[0]);
        this.mManufacturerBlacklist = newSemicolonDelimitedStringSetConfigurationValue(str + "_manufacturerBlacklist", new String[0]);
        this.mManufacturerWhitelist = newSemicolonDelimitedStringSetConfigurationValue(str + "_manufacturerWhitelist", new String[0]);
        this.mDeviceTypeDefaultAvailability = newBooleanConfigValue(str, z);
    }

    public boolean isAvailableForDevice() {
        ListingResult listingResultIsWhiteOrBlackListed = isWhiteOrBlackListed(this.mModelBlacklist, this.mModelWhitelist, this.mManufacturerBlacklist, this.mManufacturerWhitelist);
        if (listingResultIsWhiteOrBlackListed == ListingResult.BLACK_LISTED) {
            return false;
        }
        return listingResultIsWhiteOrBlackListed == ListingResult.WHITE_LISTED || this.mDeviceTypeDefaultAvailability.getValue().booleanValue();
    }

    static ListingResult isWhiteOrBlackListed(@Nonnull ConfigurationValue<Set<String>> configurationValue, @Nonnull ConfigurationValue<Set<String>> configurationValue2, @Nonnull ConfigurationValue<Set<String>> configurationValue3, @Nonnull ConfigurationValue<Set<String>> configurationValue4) {
        String str = Build.MANUFACTURER;
        String str2 = str + " " + Build.MODEL;
        if (fuzzyContains(configurationValue, str2)) {
            return ListingResult.BLACK_LISTED;
        }
        if (fuzzyContains(configurationValue2, str2)) {
            return ListingResult.WHITE_LISTED;
        }
        if (fuzzyContains(configurationValue3, str)) {
            return ListingResult.BLACK_LISTED;
        }
        if (fuzzyContains(configurationValue4, str)) {
            return ListingResult.WHITE_LISTED;
        }
        return ListingResult.NOT_LISTED;
    }

    static boolean fuzzyContains(@Nonnull ConfigurationValue<Set<String>> configurationValue, @Nonnull String str) {
        Iterator<String> it = configurationValue.getValue().iterator();
        while (it.hasNext()) {
            if (str.equalsIgnoreCase(it.next().trim())) {
                return true;
            }
        }
        return false;
    }
}
