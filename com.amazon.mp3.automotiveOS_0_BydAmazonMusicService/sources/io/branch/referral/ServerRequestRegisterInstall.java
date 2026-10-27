package io.branch.referral;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPActorManager;
import io.branch.referral.Branch;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
class ServerRequestRegisterInstall extends ServerRequestInitSession {
    Branch.BranchReferralInitListener callback_;

    @Override // io.branch.referral.ServerRequestInitSession
    public String getRequestActionName() {
        return "install";
    }

    @Override // io.branch.referral.ServerRequest
    public boolean isGetRequest() {
        return false;
    }

    ServerRequestRegisterInstall(Context context, Branch.BranchReferralInitListener branchReferralInitListener, String str) {
        super(context, Defines$RequestPath.RegisterInstall.getPath());
        this.callback_ = branchReferralInitListener;
        JSONObject jSONObject = new JSONObject();
        try {
            if (!str.equals("bnc_no_value")) {
                jSONObject.put(Defines$Jsonkey.LinkClickID.getKey(), str);
            }
            setPost(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.constructError_ = true;
        }
    }

    ServerRequestRegisterInstall(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    @Override // io.branch.referral.ServerRequestInitSession, io.branch.referral.ServerRequest
    public void onPreExecute() {
        super.onPreExecute();
        long j = this.prefHelper_.getLong("bnc_referrer_click_ts");
        long j2 = this.prefHelper_.getLong("bnc_install_begin_ts");
        if (j > 0) {
            try {
                getPost().put(Defines$Jsonkey.ClickedReferrerTimeStamp.getKey(), j);
            } catch (JSONException unused) {
                return;
            }
        }
        if (j2 > 0) {
            getPost().put(Defines$Jsonkey.InstallBeginTimeStamp.getKey(), j2);
        }
    }

    @Override // io.branch.referral.ServerRequestInitSession
    public boolean hasCallBack() {
        return this.callback_ != null;
    }

    @Override // io.branch.referral.ServerRequestInitSession, io.branch.referral.ServerRequest
    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        super.onRequestSucceeded(serverResponse, branch);
        try {
            this.prefHelper_.setUserURL(serverResponse.getObject().getString(Defines$Jsonkey.Link.getKey()));
            JSONObject object = serverResponse.getObject();
            Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.Data;
            if (object.has(defines$Jsonkey.getKey())) {
                JSONObject jSONObject = new JSONObject(serverResponse.getObject().getString(defines$Jsonkey.getKey()));
                Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.Clicked_Branch_Link;
                if (jSONObject.has(defines$Jsonkey2.getKey()) && jSONObject.getBoolean(defines$Jsonkey2.getKey()) && this.prefHelper_.getInstallParams().equals("bnc_no_value") && this.prefHelper_.getIsReferrable() == 1) {
                    this.prefHelper_.setInstallParams(serverResponse.getObject().getString(defines$Jsonkey.getKey()));
                }
            }
            JSONObject object2 = serverResponse.getObject();
            Defines$Jsonkey defines$Jsonkey3 = Defines$Jsonkey.LinkClickID;
            if (object2.has(defines$Jsonkey3.getKey())) {
                this.prefHelper_.setLinkClickID(serverResponse.getObject().getString(defines$Jsonkey3.getKey()));
            } else {
                this.prefHelper_.setLinkClickID("bnc_no_value");
            }
            if (serverResponse.getObject().has(defines$Jsonkey.getKey())) {
                this.prefHelper_.setSessionParams(serverResponse.getObject().getString(defines$Jsonkey.getKey()));
            } else {
                this.prefHelper_.setSessionParams("bnc_no_value");
            }
            Branch.BranchReferralInitListener branchReferralInitListener = this.callback_;
            if (branchReferralInitListener != null && !branch.isInitReportedThroughCallBack) {
                branchReferralInitListener.onInitFinished(branch.getLatestReferringParams(), null);
            }
            this.prefHelper_.setAppVersion(DeviceInfo.getInstance().getAppVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
        onInitSessionCompleted(serverResponse, branch);
    }

    void setInitFinishedCallback(Branch.BranchReferralInitListener branchReferralInitListener) {
        if (branchReferralInitListener != null) {
            this.callback_ = branchReferralInitListener;
        }
    }

    @Override // io.branch.referral.ServerRequest
    public void handleFailure(int i, String str) {
        if (this.callback_ != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(MAPActorManager.KEY_ERROR_MESSAGE, "Trouble reaching server. Please try again in a few minutes");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.callback_.onInitFinished(jSONObject, new BranchError("Trouble initializing Branch. " + str, i));
        }
    }

    @Override // io.branch.referral.ServerRequest
    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Branch.BranchReferralInitListener branchReferralInitListener = this.callback_;
        if (branchReferralInitListener == null) {
            return true;
        }
        branchReferralInitListener.onInitFinished(null, new BranchError("Trouble initializing Branch.", -102));
        return true;
    }

    @Override // io.branch.referral.ServerRequest
    public void clearCallbacks() {
        this.callback_ = null;
    }
}
