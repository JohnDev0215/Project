package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
class ServerRequestGetRewardHistory extends ServerRequest {
    Branch.BranchListResponseListener callback_;

    @Override // io.branch.referral.ServerRequest
    public boolean isGetRequest() {
        return false;
    }

    public ServerRequestGetRewardHistory(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    @Override // io.branch.referral.ServerRequest
    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        Branch.BranchListResponseListener branchListResponseListener = this.callback_;
        if (branchListResponseListener != null) {
            branchListResponseListener.onReceivingResponse(serverResponse.getArray(), null);
        }
    }

    @Override // io.branch.referral.ServerRequest
    public void handleFailure(int i, String str) {
        Branch.BranchListResponseListener branchListResponseListener = this.callback_;
        if (branchListResponseListener != null) {
            branchListResponseListener.onReceivingResponse(null, new BranchError("Trouble retrieving user credit history. " + str, i));
        }
    }

    @Override // io.branch.referral.ServerRequest
    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Branch.BranchListResponseListener branchListResponseListener = this.callback_;
        if (branchListResponseListener == null) {
            return true;
        }
        branchListResponseListener.onReceivingResponse(null, new BranchError("Trouble retrieving user credit history.", -102));
        return true;
    }

    @Override // io.branch.referral.ServerRequest
    public void clearCallbacks() {
        this.callback_ = null;
    }
}
