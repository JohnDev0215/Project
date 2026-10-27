package cerence.ark.assistant.api.notification;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.JsonKey;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AsrResultUpdated extends AbsNotification {
    protected boolean mIsFinal;
    protected Subtype mSubtype;
    protected String mUtterance;

    public AsrResultUpdated(String str, String str2) {
        super(str, str2);
    }

    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.mUtterance = jSONObject.optString(JsonKey.NOTIFICATION_UTTERANCE);
            this.mIsFinal = jSONObject.optBoolean(JsonKey.NOTIFICATION_IS_FINAL);
            this.mSubtype = getSubtypeEnum(jSONObject.optString(JsonKey.NOTIFICATION_SUBTYPE));
        } catch (JSONException e) {
            Logger.error(TAG, "createAsrUpdated: parse json exception", e);
        }
    }

    public String getUtterance() {
        return this.mUtterance;
    }

    public boolean isFinal() {
        return this.mIsFinal;
    }

    public Subtype getSubtype() {
        return this.mSubtype;
    }

    private static Subtype getSubtypeEnum(String str) {
        if (Subtype.WUW_SDS.getValue().equals(str)) {
            return Subtype.WUW_SDS;
        }
        if (Subtype.WUW_CUSTOMIZED.getValue().equals(str)) {
            return Subtype.WUW_CUSTOMIZED;
        }
        if (Subtype.WUW_SHORTCUT.getValue().equals(str)) {
            return Subtype.WUW_SHORTCUT;
        }
        if (Subtype.COMMAND.getValue().equals(str)) {
            return Subtype.COMMAND;
        }
        Logger.error(TAG, "getSubtypeEnum: unknown subtype: " + str);
        return null;
    }

    public enum Subtype {
        WUW_SDS(JsonKey.WUW_TYPE_SDS),
        WUW_CUSTOMIZED(JsonKey.WUW_TYP_CUSTOMIZED),
        WUW_SHORTCUT(JsonKey.WUW_TYP_SHORTCUT),
        COMMAND("command");

        private final String mValue;

        Subtype(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }
}
