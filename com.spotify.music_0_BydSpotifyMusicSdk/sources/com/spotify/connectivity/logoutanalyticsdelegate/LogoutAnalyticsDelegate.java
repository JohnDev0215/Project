package com.spotify.connectivity.logoutanalyticsdelegate;

import com.google.protobuf.i;
import com.spotify.authentication.authtriggerserviceapi.AuthLogoutReason;
import com.spotify.connectivity.logoutanalyticsdelegate.LogoutEvent;
import com.spotify.libs.connect.events.proto.LogoutNonAuth;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;
import p.b7;
import p.c7;
import p.lc;
import p.sk2;
import p.ud1;
import p.v03;
import p.wi0;
import p.wk2;

/* JADX INFO: loaded from: classes.dex */
public final class LogoutAnalyticsDelegate implements AuthAnalyticsDelegate {
    private final wi0 eventPublisher;
    private boolean isEnabled;
    private boolean isQuasarLogoutFlow;
    private AuthLogoutReason logoutReason;
    private boolean offlineUserRemoved;
    private boolean shouldSendEventAlways;
    private boolean storedCredentialsRemoved;
    private final sk2 timeKeeper;
    private final Map<TimeMeasurementCategory, wk2> timeMeasurementBuilders;

    public enum TimeMeasurementCategory {
        TOTAL_LOGOUT_DURATION("auth.logout");

        private final String raw;

        TimeMeasurementCategory(String str) {
            this.raw = str;
        }

        public final String getRaw() {
            return this.raw;
        }
    }

    public LogoutAnalyticsDelegate(wi0 wi0Var, sk2 sk2Var) {
        v03.j(wi0Var, "eventPublisher");
        v03.j(sk2Var, "timeKeeper");
        this.eventPublisher = wi0Var;
        this.timeKeeper = sk2Var;
        this.timeMeasurementBuilders = new LinkedHashMap();
    }

    private final boolean getShouldSendEvent() {
        return this.shouldSendEventAlways || ((double) new SecureRandom().nextInt(Integer.MAX_VALUE)) < 2.147483647E7d;
    }

    public static /* synthetic */ void getShouldSendEventAlways$annotations() {
    }

    private final void initializeEvent(LogoutEvent.WillLogout willLogout) {
        this.logoutReason = willLogout.getReason();
        this.isQuasarLogoutFlow = willLogout.getNewLoginFlow();
        startTrackingTime(TimeMeasurementCategory.TOTAL_LOGOUT_DURATION);
    }

    private final void sendEvent() {
        AuthLogoutReason authLogoutReason = this.logoutReason;
        if (authLogoutReason == null) {
            lc.c("Trying to send a logout event that has not been initialized");
            return;
        }
        if (getShouldSendEvent()) {
            wi0 wi0Var = this.eventPublisher;
            ud1 ud1VarI = LogoutNonAuth.i();
            ud1VarI.f(this.isQuasarLogoutFlow ? "v2" : "v1");
            ud1VarI.d(authLogoutReason.name());
            ud1VarI.g(this.storedCredentialsRemoved);
            ud1VarI.e(this.offlineUserRemoved);
            i iVarBuild = ud1VarI.build();
            v03.i(iVarBuild, "newBuilder()\n           …                 .build()");
            wi0Var.a(iVarBuild);
            stopTrackingTime(TimeMeasurementCategory.TOTAL_LOGOUT_DURATION);
        }
        this.logoutReason = null;
        this.offlineUserRemoved = false;
        this.storedCredentialsRemoved = false;
        this.timeMeasurementBuilders.clear();
    }

    private final void startTrackingTime(TimeMeasurementCategory timeMeasurementCategory) {
        if (this.timeMeasurementBuilders.get(timeMeasurementCategory) != null) {
            lc.d("Trying to start a logout measurement %s that has been initialized", timeMeasurementCategory.getRaw());
            return;
        }
        c7 c7VarA = ((b7) this.timeKeeper).a(timeMeasurementCategory.getRaw());
        c7VarA.h = "android-feature-connectivity";
        c7VarA.g(timeMeasurementCategory.getRaw());
        this.timeMeasurementBuilders.put(timeMeasurementCategory, c7VarA);
    }

    private final void stopTrackingTime(TimeMeasurementCategory timeMeasurementCategory) {
        wk2 wk2Var = this.timeMeasurementBuilders.get(timeMeasurementCategory);
        if (wk2Var == null) {
            lc.d("Trying to complete logout measurement %s that has not been initialized", timeMeasurementCategory.getRaw());
            return;
        }
        c7 c7Var = (c7) wk2Var;
        c7Var.c(timeMeasurementCategory.getRaw());
        ((b7) this.timeKeeper).b(c7Var.b());
        this.timeMeasurementBuilders.remove(timeMeasurementCategory);
    }

    public final boolean getShouldSendEventAlways() {
        return this.shouldSendEventAlways;
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final boolean isQuasarLogoutFlow() {
        return this.isQuasarLogoutFlow;
    }

    @Override // com.spotify.connectivity.logoutanalyticsdelegate.AuthAnalyticsDelegate
    public void logEvent(LogoutEvent logoutEvent) {
        v03.j(logoutEvent, "event");
        if (this.isEnabled) {
            if (logoutEvent instanceof LogoutEvent.WillLogout) {
                initializeEvent((LogoutEvent.WillLogout) logoutEvent);
                return;
            }
            if (logoutEvent instanceof LogoutEvent.StoredCredentialsRemoved) {
                this.storedCredentialsRemoved = true;
            } else if (logoutEvent instanceof LogoutEvent.OfflineUserRemoved) {
                this.offlineUserRemoved = true;
            } else if (logoutEvent instanceof LogoutEvent.DidLogout) {
                sendEvent();
            }
        }
    }

    public final void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public final void setQuasarLogoutFlow(boolean z) {
        this.isQuasarLogoutFlow = z;
    }

    public final void setShouldSendEventAlways(boolean z) {
        this.shouldSendEventAlways = z;
    }
}
