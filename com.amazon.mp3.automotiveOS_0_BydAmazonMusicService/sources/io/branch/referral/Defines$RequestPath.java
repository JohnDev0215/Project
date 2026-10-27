package io.branch.referral;

/* JADX INFO: loaded from: classes7.dex */
public enum Defines$RequestPath {
    RedeemRewards("v1/redeem"),
    GetURL("v1/url"),
    GetApp("v1/app-link-settings"),
    RegisterInstall("v1/install"),
    RegisterClose("v1/close"),
    RegisterOpen("v1/open"),
    RegisterView("v1/register-view"),
    GetCredits("v1/credits/"),
    GetCreditHistory("v1/credithistory"),
    CompletedAction("v1/event"),
    IdentifyUser("v1/profile"),
    Logout("v1/logout"),
    GetReferralCode("v1/referralcode"),
    ValidateReferralCode("v1/referralcode/"),
    ApplyReferralCode("v1/applycode/"),
    DebugConnect("v1/debug/connect"),
    ContentEvent("v1/content-events"),
    TrackStandardEvent("v2/event/standard"),
    TrackCustomEvent("v2/event/custom"),
    GetCPID("v1/cpid"),
    GetLATD("v1/cpid/latd");

    private String key;

    Defines$RequestPath(String str) {
        this.key = str;
    }

    public String getPath() {
        return this.key;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.key;
    }
}
