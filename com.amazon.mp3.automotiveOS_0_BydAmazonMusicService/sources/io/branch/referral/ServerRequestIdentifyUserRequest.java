package io.branch.referral;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPActorManager;
import io.branch.referral.Branch;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
class ServerRequestIdentifyUserRequest extends ServerRequest {
    Branch.BranchReferralInitListener callback_;
    String userId_;

    @Override // io.branch.referral.ServerRequest
    public boolean isGetRequest() {
        return false;
    }

    @Override // io.branch.referral.ServerRequest
    public boolean shouldRetryOnFail() {
        return true;
    }

    public ServerRequestIdentifyUserRequest(Context context, Branch.BranchReferralInitListener branchReferralInitListener, String str) {
        super(context, Defines$RequestPath.IdentifyUser.getPath());
        this.callback_ = branchReferralInitListener;
        this.userId_ = str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Defines$Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
            jSONObject.put(Defines$Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
            jSONObject.put(Defines$Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
            if (!this.prefHelper_.getLinkClickID().equals("bnc_no_value")) {
                jSONObject.put(Defines$Jsonkey.LinkClickID.getKey(), this.prefHelper_.getLinkClickID());
            }
            jSONObject.put(Defines$Jsonkey.Identity.getKey(), str);
            setPost(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.constructError_ = true;
        }
    }

    public ServerRequestIdentifyUserRequest(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
        this.userId_ = null;
    }

    @Override // io.branch.referral.ServerRequest
    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        try {
            if (getPost() != null) {
                JSONObject post = getPost();
                Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.Identity;
                if (post.has(defines$Jsonkey.getKey())) {
                    this.prefHelper_.setIdentity(getPost().getString(defines$Jsonkey.getKey()));
                }
            }
            this.prefHelper_.setIdentityID(serverResponse.getObject().getString(Defines$Jsonkey.IdentityID.getKey()));
            this.prefHelper_.setUserURL(serverResponse.getObject().getString(Defines$Jsonkey.Link.getKey()));
            JSONObject object = serverResponse.getObject();
            Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.ReferringData;
            if (object.has(defines$Jsonkey2.getKey())) {
                this.prefHelper_.setInstallParams(serverResponse.getObject().getString(defines$Jsonkey2.getKey()));
            }
            Branch.BranchReferralInitListener branchReferralInitListener = this.callback_;
            if (branchReferralInitListener != null) {
                branchReferralInitListener.onInitFinished(branch.getFirstReferringParams(), null);
            }
        } catch (JSONException e) {
            e.printStackTrace();
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
            this.callback_.onInitFinished(jSONObject, new BranchError("Trouble setting the user alias. " + str, i));
        }
    }

    @Override // io.branch.referral.ServerRequest
    public boolean handleErrors(Context context) {
        if (!super.doesAppHasInternetPermission(context)) {
            Branch.BranchReferralInitListener branchReferralInitListener = this.callback_;
            if (branchReferralInitListener != null) {
                branchReferralInitListener.onInitFinished(null, new BranchError("Trouble setting the user alias.", -102));
            }
            return true;
        }
        try {
            String string = getPost().getString(Defines$Jsonkey.Identity.getKey());
            if (string != null && string.length() != 0) {
                if (!string.equals(this.prefHelper_.getIdentity())) {
                    return false;
                }
            }
        } catch (JSONException unused) {
        }
        return true;
    }

    public boolean isExistingID() {
        try {
            String string = getPost().getString(Defines$Jsonkey.Identity.getKey());
            if (string != null) {
                return string.equals(this.prefHelper_.getIdentity());
            }
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void handleUserExist(Branch branch) {
        Branch.BranchReferralInitListener branchReferralInitListener = this.callback_;
        if (branchReferralInitListener != null) {
            branchReferralInitListener.onInitFinished(branch.getFirstReferringParams(), null);
        }
    }

    @Override // io.branch.referral.ServerRequest
    public void clearCallbacks() {
        this.callback_ = null;
    }
}
