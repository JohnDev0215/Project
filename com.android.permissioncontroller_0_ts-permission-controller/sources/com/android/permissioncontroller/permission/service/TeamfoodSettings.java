package com.android.permissioncontroller.permission.service;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.android.permissioncontroller.DumpableLog;
import com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AutoRevokePermissions.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0011\u0010\u0011\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lcom/android/permissioncontroller/permission/service/TeamfoodSettings;", "", "enabledForPreRApps", "", "unusedThresholdMs", "", "checkFrequencyMs", "(ZJJ)V", "getCheckFrequencyMs", "()J", "getEnabledForPreRApps", "()Z", "getUnusedThresholdMs", "component1", "component2", "component3", "copy", "dump", "Lcom/android/permissioncontroller/permission/service/AutoRevokePermissionsProto$TeamFoodSettingsProto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "equals", "other", "hashCode", "", "toString", "", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
final /* data */ class TeamfoodSettings {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static TeamfoodSettings cached;
    private final long checkFrequencyMs;
    private final boolean enabledForPreRApps;
    private final long unusedThresholdMs;

    public static /* synthetic */ TeamfoodSettings copy$default(TeamfoodSettings teamfoodSettings, boolean z, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = teamfoodSettings.enabledForPreRApps;
        }
        if ((i & 2) != 0) {
            j = teamfoodSettings.unusedThresholdMs;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = teamfoodSettings.checkFrequencyMs;
        }
        return teamfoodSettings.copy(z, j3, j2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getEnabledForPreRApps() {
        return this.enabledForPreRApps;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getUnusedThresholdMs() {
        return this.unusedThresholdMs;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getCheckFrequencyMs() {
        return this.checkFrequencyMs;
    }

    public final TeamfoodSettings copy(boolean enabledForPreRApps, long unusedThresholdMs, long checkFrequencyMs) {
        return new TeamfoodSettings(enabledForPreRApps, unusedThresholdMs, checkFrequencyMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TeamfoodSettings)) {
            return false;
        }
        TeamfoodSettings teamfoodSettings = (TeamfoodSettings) other;
        return this.enabledForPreRApps == teamfoodSettings.enabledForPreRApps && this.unusedThresholdMs == teamfoodSettings.unusedThresholdMs && this.checkFrequencyMs == teamfoodSettings.checkFrequencyMs;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public int hashCode() {
        boolean z = this.enabledForPreRApps;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (((r0 * 31) + Long.hashCode(this.unusedThresholdMs)) * 31) + Long.hashCode(this.checkFrequencyMs);
    }

    public String toString() {
        return "TeamfoodSettings(enabledForPreRApps=" + this.enabledForPreRApps + ", unusedThresholdMs=" + this.unusedThresholdMs + ", checkFrequencyMs=" + this.checkFrequencyMs + ")";
    }

    public TeamfoodSettings(boolean enabledForPreRApps, long unusedThresholdMs, long checkFrequencyMs) {
        this.enabledForPreRApps = enabledForPreRApps;
        this.unusedThresholdMs = unusedThresholdMs;
        this.checkFrequencyMs = checkFrequencyMs;
    }

    public final boolean getEnabledForPreRApps() {
        return this.enabledForPreRApps;
    }

    public final long getUnusedThresholdMs() {
        return this.unusedThresholdMs;
    }

    public final long getCheckFrequencyMs() {
        return this.checkFrequencyMs;
    }

    /* JADX INFO: compiled from: AutoRevokePermissions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/android/permissioncontroller/permission/service/TeamfoodSettings$Companion;", "", "()V", "cached", "Lcom/android/permissioncontroller/permission/service/TeamfoodSettings;", "get", "context", "Landroid/content/Context;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final TeamfoodSettings get(Context context) {
            TeamfoodSettings teamfoodSettings;
            Long longOrNull;
            Long longOrNull2;
            Intrinsics.checkParameterIsNotNull(context, "context");
            if (TeamfoodSettings.cached != null) {
                return TeamfoodSettings.cached;
            }
            String str = Settings.Global.getString(context.getContentResolver(), "auto_revoke_parameters");
            if (str != null) {
                DumpableLog.i$default(DumpableLog.INSTANCE, "AutoRevokePermissions", "Parsing teamfood setting value: " + str, null, 4, null);
                Iterable $this$mapNotNull$iv = StringsKt.split$default((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                    List keyValue = StringsKt.split$default((CharSequence) element$iv$iv$iv, new String[]{"="}, false, 0, 6, (Object) null);
                    String str2 = str;
                    String key = (String) CollectionsKt.getOrNull(keyValue, 0);
                    Pair pair = key != null ? TuplesKt.to(key, CollectionsKt.getOrNull(keyValue, 1)) : null;
                    if (pair != null) {
                        destination$iv$iv.add(pair);
                    }
                    str = str2;
                }
                Map pairs = MapsKt.toMap((List) destination$iv$iv);
                boolean zAreEqual = Intrinsics.areEqual((String) pairs.get("enabledForPreRApps"), "true");
                String str3 = (String) pairs.get("unusedThresholdMs");
                long jLongValue = (str3 == null || (longOrNull2 = StringsKt.toLongOrNull(str3)) == null) ? AutoRevokePermissions.DEFAULT_UNUSED_THRESHOLD_MS : longOrNull2.longValue();
                String str4 = (String) pairs.get("checkFrequencyMs");
                teamfoodSettings = new TeamfoodSettings(zAreEqual, jLongValue, (str4 == null || (longOrNull = StringsKt.toLongOrNull(str4)) == null) ? AutoRevokePermissions.DEFAULT_CHECK_FREQUENCY_MS : longOrNull.longValue());
            } else {
                teamfoodSettings = null;
            }
            TeamfoodSettings it = teamfoodSettings;
            TeamfoodSettings.cached = it;
            Log.i("AutoRevokePermissions", "Parsed teamfood setting value: " + it);
            return teamfoodSettings;
        }
    }

    public final Object dump(Continuation<? super AutoRevokePermissionsProto.TeamFoodSettingsProto> continuation) {
        AutoRevokePermissionsProto.TeamFoodSettingsProto teamFoodSettingsProtoBuild = AutoRevokePermissionsProto.TeamFoodSettingsProto.newBuilder().setEnabledForPreRApps(this.enabledForPreRApps).setUnusedThresholdMillis(this.unusedThresholdMs).setCheckFrequencyMillis(this.checkFrequencyMs).build();
        Intrinsics.checkExpressionValueIsNotNull(teamFoodSettingsProtoBuild, "TeamFoodSettingsProto.ne…\n                .build()");
        return teamFoodSettingsProtoBuild;
    }
}
