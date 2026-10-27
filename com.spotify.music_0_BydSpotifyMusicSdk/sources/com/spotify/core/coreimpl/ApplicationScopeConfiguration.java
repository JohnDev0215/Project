package com.spotify.core.coreimpl;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ApplicationScopeConfiguration {
    private String cachePath;
    private boolean enablePerformanceTracing;
    private String performanceTracingLogsPath;
    private String settingsPath;

    public ApplicationScopeConfiguration() {
        this(null, null, false, null, 15, null);
    }

    public static /* synthetic */ ApplicationScopeConfiguration copy$default(ApplicationScopeConfiguration applicationScopeConfiguration, String str, String str2, boolean z, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = applicationScopeConfiguration.cachePath;
        }
        if ((i & 2) != 0) {
            str2 = applicationScopeConfiguration.settingsPath;
        }
        if ((i & 4) != 0) {
            z = applicationScopeConfiguration.enablePerformanceTracing;
        }
        if ((i & 8) != 0) {
            str3 = applicationScopeConfiguration.performanceTracingLogsPath;
        }
        return applicationScopeConfiguration.copy(str, str2, z, str3);
    }

    public final String component1() {
        return this.cachePath;
    }

    public final String component2() {
        return this.settingsPath;
    }

    public final boolean component3() {
        return this.enablePerformanceTracing;
    }

    public final String component4() {
        return this.performanceTracingLogsPath;
    }

    public final ApplicationScopeConfiguration copy(String str, String str2, boolean z, String str3) {
        return new ApplicationScopeConfiguration(str, str2, z, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ApplicationScopeConfiguration)) {
            return false;
        }
        ApplicationScopeConfiguration applicationScopeConfiguration = (ApplicationScopeConfiguration) obj;
        return v03.b(this.cachePath, applicationScopeConfiguration.cachePath) && v03.b(this.settingsPath, applicationScopeConfiguration.settingsPath) && this.enablePerformanceTracing == applicationScopeConfiguration.enablePerformanceTracing && v03.b(this.performanceTracingLogsPath, applicationScopeConfiguration.performanceTracingLogsPath);
    }

    public final String getCachePath() {
        return this.cachePath;
    }

    public final boolean getEnablePerformanceTracing() {
        return this.enablePerformanceTracing;
    }

    public final String getPerformanceTracingLogsPath() {
        return this.performanceTracingLogsPath;
    }

    public final String getSettingsPath() {
        return this.settingsPath;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [int] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v8 */
    public int hashCode() {
        String str = this.cachePath;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.settingsPath;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        boolean z = this.enablePerformanceTracing;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode2 + r2) * 31;
        String str3 = this.performanceTracingLogsPath;
        return i + (str3 != null ? str3.hashCode() : 0);
    }

    public final void setCachePath(String str) {
        this.cachePath = str;
    }

    public final void setEnablePerformanceTracing(boolean z) {
        this.enablePerformanceTracing = z;
    }

    public final void setPerformanceTracingLogsPath(String str) {
        this.performanceTracingLogsPath = str;
    }

    public final void setSettingsPath(String str) {
        this.settingsPath = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ApplicationScopeConfiguration(cachePath=");
        sb.append(this.cachePath);
        sb.append(", settingsPath=");
        sb.append(this.settingsPath);
        sb.append(", enablePerformanceTracing=");
        sb.append(this.enablePerformanceTracing);
        sb.append(", performanceTracingLogsPath=");
        return hf2.d(sb, this.performanceTracingLogsPath, ')');
    }

    public ApplicationScopeConfiguration(String str, String str2, boolean z, String str3) {
        this.cachePath = str;
        this.settingsPath = str2;
        this.enablePerformanceTracing = z;
        this.performanceTracingLogsPath = str3;
    }

    public /* synthetic */ ApplicationScopeConfiguration(String str, String str2, boolean z, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : str3);
    }
}
