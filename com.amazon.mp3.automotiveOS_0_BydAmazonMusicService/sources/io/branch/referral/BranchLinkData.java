package io.branch.referral;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
class BranchLinkData extends JSONObject {
    private String alias;
    private String campaign;
    private String channel;
    private int duration;
    private String feature;
    private JSONObject params;
    private String stage;
    private Collection<String> tags;
    private int type;

    public Collection<String> getTags() {
        return this.tags;
    }

    public String getAlias() {
        return this.alias;
    }

    public int getType() {
        return this.type;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getFeature() {
        return this.feature;
    }

    public String getStage() {
        return this.stage;
    }

    public String getCampaign() {
        return this.campaign;
    }

    public JSONObject getParams() {
        return this.params;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BranchLinkData branchLinkData = (BranchLinkData) obj;
        String str = this.alias;
        if (str == null) {
            if (branchLinkData.alias != null) {
                return false;
            }
        } else if (!str.equals(branchLinkData.alias)) {
            return false;
        }
        String str2 = this.channel;
        if (str2 == null) {
            if (branchLinkData.channel != null) {
                return false;
            }
        } else if (!str2.equals(branchLinkData.channel)) {
            return false;
        }
        String str3 = this.feature;
        if (str3 == null) {
            if (branchLinkData.feature != null) {
                return false;
            }
        } else if (!str3.equals(branchLinkData.feature)) {
            return false;
        }
        JSONObject jSONObject = this.params;
        if (jSONObject == null) {
            if (branchLinkData.params != null) {
                return false;
            }
        } else if (!jSONObject.equals(branchLinkData.params)) {
            return false;
        }
        String str4 = this.stage;
        if (str4 == null) {
            if (branchLinkData.stage != null) {
                return false;
            }
        } else if (!str4.equals(branchLinkData.stage)) {
            return false;
        }
        String str5 = this.campaign;
        if (str5 == null) {
            if (branchLinkData.campaign != null) {
                return false;
            }
        } else if (!str5.equals(branchLinkData.campaign)) {
            return false;
        }
        if (this.type != branchLinkData.type || this.duration != branchLinkData.duration) {
            return false;
        }
        Collection<String> collection = this.tags;
        if (collection == null) {
            if (branchLinkData.tags != null) {
                return false;
            }
        } else if (!collection.toString().equals(branchLinkData.tags.toString())) {
            return false;
        }
        return true;
    }

    @SuppressLint({"DefaultLocale"})
    public int hashCode() {
        int i = (this.type + 19) * 19;
        String str = this.alias;
        int iHashCode = (i + (str == null ? 0 : str.toLowerCase().hashCode())) * 19;
        String str2 = this.channel;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.toLowerCase().hashCode())) * 19;
        String str3 = this.feature;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.toLowerCase().hashCode())) * 19;
        String str4 = this.stage;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.toLowerCase().hashCode())) * 19;
        String str5 = this.campaign;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.toLowerCase().hashCode())) * 19;
        JSONObject jSONObject = this.params;
        int iHashCode6 = ((iHashCode5 + (jSONObject != null ? jSONObject.toString().toLowerCase().hashCode() : 0)) * 19) + this.duration;
        Collection<String> collection = this.tags;
        if (collection != null) {
            Iterator<String> it = collection.iterator();
            while (it.hasNext()) {
                iHashCode6 = (iHashCode6 * 19) + it.next().toLowerCase().hashCode();
            }
        }
        return iHashCode6;
    }

    public JSONObject getLinkDataJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.channel)) {
                jSONObject.put("~" + Defines$LinkParam.Channel.getKey(), this.channel);
            }
            if (!TextUtils.isEmpty(this.alias)) {
                jSONObject.put("~" + Defines$LinkParam.Alias.getKey(), this.alias);
            }
            if (!TextUtils.isEmpty(this.feature)) {
                jSONObject.put("~" + Defines$LinkParam.Feature.getKey(), this.feature);
            }
            if (!TextUtils.isEmpty(this.stage)) {
                jSONObject.put("~" + Defines$LinkParam.Stage.getKey(), this.stage);
            }
            if (!TextUtils.isEmpty(this.campaign)) {
                jSONObject.put("~" + Defines$LinkParam.Campaign.getKey(), this.campaign);
            }
            Defines$LinkParam defines$LinkParam = Defines$LinkParam.Tags;
            if (has(defines$LinkParam.getKey())) {
                jSONObject.put(defines$LinkParam.getKey(), getJSONArray(defines$LinkParam.getKey()));
            }
            jSONObject.put("~" + Defines$LinkParam.Type.getKey(), this.type);
            jSONObject.put("~" + Defines$LinkParam.Duration.getKey(), this.duration);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
