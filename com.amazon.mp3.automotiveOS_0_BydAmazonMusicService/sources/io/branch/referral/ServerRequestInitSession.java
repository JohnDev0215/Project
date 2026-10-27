package io.branch.referral;

import android.app.Activity;
import android.content.Context;
import io.branch.indexing.ContentDiscoverer;
import io.branch.indexing.ContentDiscoveryManifest;
import io.branch.referral.Branch;
import io.branch.referral.validators.DeepLinkRoutingValidator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
abstract class ServerRequestInitSession extends ServerRequest {
    private final ContentDiscoveryManifest contentDiscoveryManifest_;
    private final Context context_;

    public abstract String getRequestActionName();

    public abstract boolean hasCallBack();

    @Override // io.branch.referral.ServerRequest
    protected boolean shouldUpdateLimitFacebookTracking() {
        return true;
    }

    ServerRequestInitSession(Context context, String str) {
        super(context, str);
        this.context_ = context;
        this.contentDiscoveryManifest_ = ContentDiscoveryManifest.getInstance(context);
    }

    ServerRequestInitSession(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
        this.context_ = context;
        this.contentDiscoveryManifest_ = ContentDiscoveryManifest.getInstance(context);
    }

    @Override // io.branch.referral.ServerRequest
    protected void setPost(JSONObject jSONObject) throws JSONException {
        super.setPost(jSONObject);
        String appVersion = DeviceInfo.getInstance().getAppVersion();
        if (!DeviceInfo.isNullOrEmptyOrBlank(appVersion)) {
            jSONObject.put(Defines$Jsonkey.AppVersion.getKey(), appVersion);
        }
        jSONObject.put(Defines$Jsonkey.FaceBookAppLinkChecked.getKey(), this.prefHelper_.getIsAppLinkTriggeredInit());
        jSONObject.put(Defines$Jsonkey.IsReferrable.getKey(), this.prefHelper_.getIsReferrable());
        jSONObject.put(Defines$Jsonkey.Debug.getKey(), BranchUtil.isDebugEnabled());
        updateInstallStateAndTimestamps(jSONObject);
        updateEnvironment(this.context_, jSONObject);
    }

    static boolean isInitSessionAction(String str) {
        if (str != null) {
            return str.equalsIgnoreCase("open") || str.equalsIgnoreCase("install");
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean handleBranchViewIfAvailable(ServerResponse serverResponse) {
        if (serverResponse != null && serverResponse.getObject() != null) {
            JSONObject object = serverResponse.getObject();
            Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.BranchViewData;
            if (object.has(defines$Jsonkey.getKey())) {
                try {
                    JSONObject jSONObject = serverResponse.getObject().getJSONObject(defines$Jsonkey.getKey());
                    String requestActionName = getRequestActionName();
                    if (Branch.getInstance().currentActivityReference_ != null && Branch.getInstance().currentActivityReference_.get() != null) {
                        Activity activity = Branch.getInstance().currentActivityReference_.get();
                        if (activity instanceof Branch.IBranchViewControl ? true ^ ((Branch.IBranchViewControl) activity).skipBranchViewsOnThisActivity() : true) {
                            return BranchViewHandler.getInstance().showBranchView(jSONObject, requestActionName, activity, Branch.getInstance());
                        }
                        return BranchViewHandler.getInstance().markInstallOrOpenBranchViewPending(jSONObject, requestActionName);
                    }
                    return BranchViewHandler.getInstance().markInstallOrOpenBranchViewPending(jSONObject, requestActionName);
                } catch (JSONException unused) {
                }
            }
        }
        return false;
    }

    @Override // io.branch.referral.ServerRequest
    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        try {
            this.prefHelper_.setLinkClickIdentifier("bnc_no_value");
            this.prefHelper_.setGoogleSearchInstallIdentifier("bnc_no_value");
            this.prefHelper_.setGooglePlayReferrer("bnc_no_value");
            this.prefHelper_.setExternalIntentUri("bnc_no_value");
            this.prefHelper_.setExternalIntentExtra("bnc_no_value");
            this.prefHelper_.setAppLink("bnc_no_value");
            this.prefHelper_.setPushIdentifier("bnc_no_value");
            this.prefHelper_.setIsAppLinkTriggeredInit(Boolean.FALSE);
            this.prefHelper_.setInstallReferrerParams("bnc_no_value");
            this.prefHelper_.setIsFullAppConversion(false);
            if (serverResponse.getObject() != null) {
                JSONObject object = serverResponse.getObject();
                Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.Data;
                if (object.has(defines$Jsonkey.getKey())) {
                    JSONObject jSONObject = new JSONObject(serverResponse.getObject().getString(defines$Jsonkey.getKey()));
                    if (jSONObject.optBoolean(Defines$Jsonkey.Clicked_Branch_Link.getKey())) {
                        new ExtendedAnswerProvider().provideData(this instanceof ServerRequestRegisterInstall ? "Branch Install" : "Branch Open", jSONObject, this.prefHelper_.getIdentityID());
                    }
                }
            }
        } catch (JSONException unused) {
        }
        if (this.prefHelper_.getLong("bnc_previous_update_time") == 0) {
            PrefHelper prefHelper = this.prefHelper_;
            prefHelper.setLong("bnc_previous_update_time", prefHelper.getLong("bnc_last_known_update_time"));
        }
    }

    void onInitSessionCompleted(ServerResponse serverResponse, Branch branch) {
        ContentDiscoveryManifest contentDiscoveryManifest = this.contentDiscoveryManifest_;
        if (contentDiscoveryManifest != null) {
            contentDiscoveryManifest.onBranchInitialised(serverResponse.getObject());
            if (branch.currentActivityReference_ != null) {
                try {
                    ContentDiscoverer.getInstance().onSessionStarted(branch.currentActivityReference_.get(), branch.getSessionReferredLink());
                } catch (Exception unused) {
                }
            }
        }
        DeepLinkRoutingValidator.validate(branch.currentActivityReference_);
        branch.updateSkipURLFormats();
    }

    void updateLinkReferrerParams() {
        String linkClickIdentifier = this.prefHelper_.getLinkClickIdentifier();
        if (!linkClickIdentifier.equals("bnc_no_value")) {
            try {
                getPost().put(Defines$Jsonkey.LinkIdentifier.getKey(), linkClickIdentifier);
                getPost().put(Defines$Jsonkey.FaceBookAppLinkChecked.getKey(), this.prefHelper_.getIsAppLinkTriggeredInit());
            } catch (JSONException unused) {
            }
        }
        String googleSearchInstallIdentifier = this.prefHelper_.getGoogleSearchInstallIdentifier();
        if (!googleSearchInstallIdentifier.equals("bnc_no_value")) {
            try {
                getPost().put(Defines$Jsonkey.GoogleSearchInstallReferrer.getKey(), googleSearchInstallIdentifier);
            } catch (JSONException unused2) {
            }
        }
        String googlePlayReferrer = this.prefHelper_.getGooglePlayReferrer();
        if (!googlePlayReferrer.equals("bnc_no_value")) {
            try {
                getPost().put(Defines$Jsonkey.GooglePlayInstallReferrer.getKey(), googlePlayReferrer);
            } catch (JSONException unused3) {
            }
        }
        if (this.prefHelper_.isFullAppConversion()) {
            try {
                getPost().put(Defines$Jsonkey.AndroidAppLinkURL.getKey(), this.prefHelper_.getAppLink());
                getPost().put(Defines$Jsonkey.IsFullAppConv.getKey(), true);
            } catch (JSONException unused4) {
            }
        }
    }

    @Override // io.branch.referral.ServerRequest
    public void onPreExecute() {
        JSONObject post = getPost();
        try {
            if (!this.prefHelper_.getAppLink().equals("bnc_no_value")) {
                post.put(Defines$Jsonkey.AndroidAppLinkURL.getKey(), this.prefHelper_.getAppLink());
            }
            if (!this.prefHelper_.getPushIdentifier().equals("bnc_no_value")) {
                post.put(Defines$Jsonkey.AndroidPushIdentifier.getKey(), this.prefHelper_.getPushIdentifier());
            }
            if (!this.prefHelper_.getExternalIntentUri().equals("bnc_no_value")) {
                post.put(Defines$Jsonkey.External_Intent_URI.getKey(), this.prefHelper_.getExternalIntentUri());
            }
            if (!this.prefHelper_.getExternalIntentExtra().equals("bnc_no_value")) {
                post.put(Defines$Jsonkey.External_Intent_Extra.getKey(), this.prefHelper_.getExternalIntentExtra());
            }
            if (this.contentDiscoveryManifest_ != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("mv", this.contentDiscoveryManifest_.getManifestVersion());
                jSONObject.put("pn", this.context_.getPackageName());
                post.put("cd", jSONObject);
            }
        } catch (JSONException unused) {
        }
    }

    private void updateInstallStateAndTimestamps(JSONObject jSONObject) throws JSONException {
        String appVersion = DeviceInfo.getInstance().getAppVersion();
        long firstInstallTime = DeviceInfo.getInstance().getFirstInstallTime();
        long lastUpdateTime = DeviceInfo.getInstance().getLastUpdateTime();
        int i = 2;
        if ("bnc_no_value".equals(this.prefHelper_.getAppVersion())) {
            if (lastUpdateTime - firstInstallTime < 86400000) {
                i = 0;
            }
        } else if (this.prefHelper_.getAppVersion().equals(appVersion)) {
            i = 1;
        }
        jSONObject.put(Defines$Jsonkey.Update.getKey(), i);
        jSONObject.put(Defines$Jsonkey.FirstInstallTime.getKey(), firstInstallTime);
        jSONObject.put(Defines$Jsonkey.LastUpdateTime.getKey(), lastUpdateTime);
        long j = this.prefHelper_.getLong("bnc_original_install_time");
        if (j == 0) {
            this.prefHelper_.setLong("bnc_original_install_time", firstInstallTime);
        } else {
            firstInstallTime = j;
        }
        jSONObject.put(Defines$Jsonkey.OriginalInstallTime.getKey(), firstInstallTime);
        long j2 = this.prefHelper_.getLong("bnc_last_known_update_time");
        if (j2 < lastUpdateTime) {
            this.prefHelper_.setLong("bnc_previous_update_time", j2);
            this.prefHelper_.setLong("bnc_last_known_update_time", lastUpdateTime);
        }
        jSONObject.put(Defines$Jsonkey.PreviousUpdateTime.getKey(), this.prefHelper_.getLong("bnc_previous_update_time"));
    }

    @Override // io.branch.referral.ServerRequest
    protected boolean prepareExecuteWithoutTracking() {
        JSONObject post = getPost();
        if (post.has(Defines$Jsonkey.AndroidAppLinkURL.getKey()) || post.has(Defines$Jsonkey.AndroidPushIdentifier.getKey()) || post.has(Defines$Jsonkey.LinkIdentifier.getKey())) {
            post.remove(Defines$Jsonkey.DeviceFingerprintID.getKey());
            post.remove(Defines$Jsonkey.IdentityID.getKey());
            post.remove(Defines$Jsonkey.FaceBookAppLinkChecked.getKey());
            post.remove(Defines$Jsonkey.External_Intent_Extra.getKey());
            post.remove(Defines$Jsonkey.External_Intent_URI.getKey());
            post.remove(Defines$Jsonkey.FirstInstallTime.getKey());
            post.remove(Defines$Jsonkey.LastUpdateTime.getKey());
            post.remove(Defines$Jsonkey.OriginalInstallTime.getKey());
            post.remove(Defines$Jsonkey.PreviousUpdateTime.getKey());
            post.remove(Defines$Jsonkey.InstallBeginTimeStamp.getKey());
            post.remove(Defines$Jsonkey.ClickedReferrerTimeStamp.getKey());
            post.remove(Defines$Jsonkey.HardwareID.getKey());
            post.remove(Defines$Jsonkey.IsHardwareIDReal.getKey());
            post.remove(Defines$Jsonkey.LocalIP.getKey());
            try {
                post.put(Defines$Jsonkey.TrackingDisabled.getKey(), true);
            } catch (JSONException unused) {
            }
            return true;
        }
        return super.prepareExecuteWithoutTracking();
    }
}
