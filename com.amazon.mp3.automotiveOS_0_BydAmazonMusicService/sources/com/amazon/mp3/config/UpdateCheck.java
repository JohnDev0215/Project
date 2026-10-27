package com.amazon.mp3.config;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.music.config.Configuration;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class UpdateCheck {

    public static class Result {
        private final Version mClientVersion;
        private final Version mCriticalVersion;
        private final Version mLatestVersion;
        private final String mMarketUrl;

        public Result(final Context context, final Version latest, final Version critical, final String marketUrl) {
            this.mLatestVersion = latest;
            this.mCriticalVersion = critical;
            this.mMarketUrl = marketUrl;
            this.mClientVersion = Version.parse(ApplicationAttributes.getInstance(context).getVersion());
        }

        public Version getLatestVersion() {
            return this.mLatestVersion;
        }

        public Version getCriticalVersion() {
            return this.mCriticalVersion;
        }

        public String getMarketUrl() {
            return this.mMarketUrl;
        }

        public Version getClientVersion() {
            return this.mClientVersion;
        }
    }

    public static class Version {
        private static final Pattern VERSION_PATTERN = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)");
        private final int mMajor;
        private final int mMinor;
        private final int mPatch;

        public Version(int major, int minor, int patch) {
            this.mMajor = major;
            this.mMinor = minor;
            this.mPatch = patch;
        }

        public int getMajor() {
            return this.mMajor;
        }

        public int getMinor() {
            return this.mMinor;
        }

        public int getPatch() {
            return this.mPatch;
        }

        public static Version parse(String string) {
            Matcher matcher = VERSION_PATTERN.matcher(string);
            if (matcher.find()) {
                return new Version(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
            }
            return null;
        }

        public boolean greaterThan(Version version) {
            int i = this.mMajor;
            int i2 = version.mMajor;
            if (i > i2) {
                return true;
            }
            if (i == i2) {
                int i3 = this.mMinor;
                int i4 = version.mMinor;
                if (i3 > i4) {
                    return true;
                }
                return i3 == i4 && this.mPatch > version.mPatch;
            }
            return false;
        }

        public boolean equalTo(Version version) {
            return this.mMajor == version.mMajor && this.mMinor == version.mMinor && this.mPatch == version.mPatch;
        }

        public boolean lessThan(Version version) {
            return (greaterThan(version) || equalTo(version)) ? false : true;
        }

        public String toString() {
            return String.format(Locale.US, "%d.%d.%d", Integer.valueOf(this.mMajor), Integer.valueOf(this.mMinor), Integer.valueOf(this.mPatch));
        }
    }

    public static Result check(Context context) {
        Configuration configuration = AmazonApplication.getConfiguration(context);
        ApplicationAttributes applicationAttributes = ApplicationAttributes.getInstance(context);
        if (!configuration.isRemoteEndpointLoaded()) {
            return null;
        }
        String version = applicationAttributes.getVersion();
        Version version2 = Version.parse(configuration.getString("update_check_latest_version_v2", version));
        Version version3 = Version.parse(configuration.getString("update_check_critical_version_v2", version));
        if (version2 == null || version3 == null) {
            return null;
        }
        return new Result(context, version2, version3, "market://details?id=com.amazon.mp3");
    }
}
