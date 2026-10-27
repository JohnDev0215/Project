package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
class ServerRequestGetRewards extends ServerRequest {
    Branch.BranchReferralStateChangedListener callback_;

    @Override // io.branch.referral.ServerRequest
    public boolean isGetRequest() {
        return true;
    }

    public ServerRequestGetRewards(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    @Override // io.branch.referral.ServerRequest
    public String getRequestUrl() {
        return super.getRequestUrl() + this.prefHelper_.getIdentityID();
    }

    @Override // io.branch.referral.ServerRequest
    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        Iterator<String> itKeys = serverResponse.getObject().keys();
        boolean z = false;
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                int i = serverResponse.getObject().getInt(next);
                if (i != this.prefHelper_.getCreditCount(next)) {
                    z = true;
                }
                this.prefHelper_.setCreditCount(next, i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Branch.BranchReferralStateChangedListener branchReferralStateChangedListener = this.callback_;
        if (branchReferralStateChangedListener != null) {
            branchReferralStateChangedListener.onStateChanged(z, null);
        }
    }

    @Override // io.branch.referral.ServerRequest
    public void handleFailure(int i, String str) {
        Branch.BranchReferralStateChangedListener branchReferralStateChangedListener = this.callback_;
        if (branchReferralStateChangedListener != null) {
            branchReferralStateChangedListener.onStateChanged(false, new BranchError("Trouble retrieving user credits. " + str, i));
        }
    }

    @Override // io.branch.referral.ServerRequest
    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Branch.BranchReferralStateChangedListener branchReferralStateChangedListener = this.callback_;
        if (branchReferralStateChangedListener == null) {
            return true;
        }
        branchReferralStateChangedListener.onStateChanged(false, new BranchError("Trouble retrieving user credits.", -102));
        return true;
    }

    @Override // io.branch.referral.ServerRequest
    public void clearCallbacks() {
        this.callback_ = null;
    }
}
