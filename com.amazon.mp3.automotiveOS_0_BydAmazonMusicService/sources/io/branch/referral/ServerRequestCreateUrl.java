package io.branch.referral;

import android.content.Context;
import com.google.android.gms.common.internal.ImagesContract;
import io.branch.referral.Branch;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
class ServerRequestCreateUrl extends ServerRequest {
    private Branch.BranchLinkCreateListener callback_;
    private boolean defaultToLongUrl_;
    private boolean isAsync_;
    private boolean isReqStartedFromBranchShareSheet_;
    private BranchLinkData linkPost_;

    @Override // io.branch.referral.ServerRequest
    public boolean isGetRequest() {
        return false;
    }

    @Override // io.branch.referral.ServerRequest
    boolean isPersistable() {
        return false;
    }

    @Override // io.branch.referral.ServerRequest
    protected boolean prepareExecuteWithoutTracking() {
        return true;
    }

    public ServerRequestCreateUrl(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
        this.isAsync_ = true;
        this.defaultToLongUrl_ = true;
    }

    public BranchLinkData getLinkPost() {
        return this.linkPost_;
    }

    @Override // io.branch.referral.ServerRequest
    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Branch.BranchLinkCreateListener branchLinkCreateListener = this.callback_;
        if (branchLinkCreateListener == null) {
            return true;
        }
        branchLinkCreateListener.onLinkCreate(null, new BranchError("Trouble creating a URL.", -102));
        return true;
    }

    @Override // io.branch.referral.ServerRequest
    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        try {
            String string = serverResponse.getObject().getString(ImagesContract.URL);
            Branch.BranchLinkCreateListener branchLinkCreateListener = this.callback_;
            if (branchLinkCreateListener != null) {
                branchLinkCreateListener.onLinkCreate(string, null);
            }
            updateShareEventToFabric(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // io.branch.referral.ServerRequest
    public void handleFailure(int i, String str) {
        if (this.callback_ != null) {
            String longUrl = this.defaultToLongUrl_ ? getLongUrl() : null;
            this.callback_.onLinkCreate(longUrl, new BranchError("Trouble creating a URL. " + str, i));
        }
    }

    public String getLongUrl() {
        if (!this.prefHelper_.getUserURL().equals("bnc_no_value")) {
            return generateLongUrlWithParams(this.prefHelper_.getUserURL());
        }
        return generateLongUrlWithParams("https://bnc.lt/a/" + this.prefHelper_.getBranchKey());
    }

    public void handleDuplicateURLError() {
        Branch.BranchLinkCreateListener branchLinkCreateListener = this.callback_;
        if (branchLinkCreateListener != null) {
            branchLinkCreateListener.onLinkCreate(null, new BranchError("Trouble creating a URL.", -105));
        }
    }

    @Override // io.branch.referral.ServerRequest
    public void clearCallbacks() {
        this.callback_ = null;
    }

    private String generateLongUrlWithParams(String str) {
        try {
            if (Branch.getInstance().isTrackingDisabled() && !str.contains("https://bnc.lt/a/")) {
                str = str.replace(new URL(str).getQuery(), "");
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str.contains("?") ? "" : "?");
            String string = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string);
            sb2.append(string.endsWith("?") ? "" : "&");
            String string2 = sb2.toString();
            Collection<String> tags = this.linkPost_.getTags();
            if (tags != null) {
                for (String str2 : tags) {
                    if (str2 != null && str2.length() > 0) {
                        string2 = string2 + Defines$LinkParam.Tags + "=" + URLEncoder.encode(str2, "UTF8") + "&";
                    }
                }
            }
            String alias = this.linkPost_.getAlias();
            if (alias != null && alias.length() > 0) {
                string2 = string2 + Defines$LinkParam.Alias + "=" + URLEncoder.encode(alias, "UTF8") + "&";
            }
            String channel = this.linkPost_.getChannel();
            if (channel != null && channel.length() > 0) {
                string2 = string2 + Defines$LinkParam.Channel + "=" + URLEncoder.encode(channel, "UTF8") + "&";
            }
            String feature = this.linkPost_.getFeature();
            if (feature != null && feature.length() > 0) {
                string2 = string2 + Defines$LinkParam.Feature + "=" + URLEncoder.encode(feature, "UTF8") + "&";
            }
            String stage = this.linkPost_.getStage();
            if (stage != null && stage.length() > 0) {
                string2 = string2 + Defines$LinkParam.Stage + "=" + URLEncoder.encode(stage, "UTF8") + "&";
            }
            String campaign = this.linkPost_.getCampaign();
            if (campaign != null && campaign.length() > 0) {
                string2 = string2 + Defines$LinkParam.Campaign + "=" + URLEncoder.encode(campaign, "UTF8") + "&";
            }
            String str3 = (string2 + Defines$LinkParam.Type + "=" + this.linkPost_.getType() + "&") + Defines$LinkParam.Duration + "=" + this.linkPost_.getDuration();
            String string3 = this.linkPost_.getParams().toString();
            if (string3 == null || string3.length() <= 0) {
                return str3;
            }
            return str3 + "&source=android&data=" + URLEncoder.encode(Base64.encodeToString(string3.getBytes(), 2), "UTF8");
        } catch (Exception unused) {
            this.callback_.onLinkCreate(null, new BranchError("Trouble creating a URL.", -116));
            return str;
        }
    }

    boolean isReqStartedFromBranchShareSheet() {
        return this.isReqStartedFromBranchShareSheet_;
    }

    private void updateShareEventToFabric(String str) {
        JSONObject linkDataJsonObject = this.linkPost_.getLinkDataJsonObject();
        if (!isReqStartedFromBranchShareSheet() || linkDataJsonObject == null) {
            return;
        }
        new ExtendedAnswerProvider().provideData("Branch Share", linkDataJsonObject, this.prefHelper_.getIdentityID());
    }
}
