package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
class ServerRequestRedeemRewards extends ServerRequest {
    int actualNumOfCreditsToRedeem_;
    Branch.BranchReferralStateChangedListener callback_;

    @Override // io.branch.referral.ServerRequest
    public boolean isGetRequest() {
        return false;
    }

    public ServerRequestRedeemRewards(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
        this.actualNumOfCreditsToRedeem_ = 0;
    }

    @Override // io.branch.referral.ServerRequest
    public boolean handleErrors(Context context) {
        if (!super.doesAppHasInternetPermission(context)) {
            Branch.BranchReferralStateChangedListener branchReferralStateChangedListener = this.callback_;
            if (branchReferralStateChangedListener != null) {
                branchReferralStateChangedListener.onStateChanged(false, new BranchError("Trouble redeeming rewards.", -102));
            }
            return true;
        }
        if (this.actualNumOfCreditsToRedeem_ > 0) {
            return false;
        }
        Branch.BranchReferralStateChangedListener branchReferralStateChangedListener2 = this.callback_;
        if (branchReferralStateChangedListener2 != null) {
            branchReferralStateChangedListener2.onStateChanged(false, new BranchError("Trouble redeeming rewards.", -107));
        }
        return true;
    }

    @Override // io.branch.referral.ServerRequest
    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        JSONObject post = getPost();
        if (post != null) {
            Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.Bucket;
            if (post.has(defines$Jsonkey.getKey())) {
                Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.Amount;
                if (post.has(defines$Jsonkey2.getKey())) {
                    try {
                        int i = post.getInt(defines$Jsonkey2.getKey());
                        String string = post.getString(defines$Jsonkey.getKey());
                        z = i > 0;
                        this.prefHelper_.setCreditCount(string, this.prefHelper_.getCreditCount(string) - i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (this.callback_ != null) {
            this.callback_.onStateChanged(z, z ? null : new BranchError("Trouble redeeming rewards.", -107));
        }
    }

    @Override // io.branch.referral.ServerRequest
    public void handleFailure(int i, String str) {
        Branch.BranchReferralStateChangedListener branchReferralStateChangedListener = this.callback_;
        if (branchReferralStateChangedListener != null) {
            branchReferralStateChangedListener.onStateChanged(false, new BranchError("Trouble redeeming rewards. " + str, i));
        }
    }

    @Override // io.branch.referral.ServerRequest
    public void clearCallbacks() {
        this.callback_ = null;
    }
}
