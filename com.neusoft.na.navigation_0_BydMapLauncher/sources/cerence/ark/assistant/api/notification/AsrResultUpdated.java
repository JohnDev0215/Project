package cerence.ark.assistant.api.notification;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AsrResultUpdated extends AbsNotification {
    private static final String SOURCE_OFFBOARD = "offboard";
    private static final String SOURCE_ONBOARD = "onboard";
    protected boolean mIsFinal;
    protected Source mSource;
    protected Subtype mSubtype;
    protected String mUtterance;
    protected String mZone;

    public enum Source {
        OFFBOARD,
        ONBOARD
    }

    public AsrResultUpdated(String str) {
        super(str);
        Logger.debug(TAG, Utils.format("AsrResultUpdated: %s", str));
    }

    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.mUtterance = jSONObject.optString(JsonKey.NOTIFICATION_UTTERANCE);
        this.mIsFinal = jSONObject.optBoolean(JsonKey.NOTIFICATION_IS_FINAL);
        this.mZone = jSONObject.optString(JsonKey.NOTIFICATION_ZONE);
        this.mSource = getSourceEnum(jSONObject.optString("source"));
        this.mSubtype = getSubtypeEnum(jSONObject.optString(JsonKey.NOTIFICATION_SUBTYPE));
    }

    public String getUtterance() {
        return this.mUtterance;
    }

    public boolean isFinal() {
        return this.mIsFinal;
    }

    public Source getSource() {
        return this.mSource;
    }

    public String getZone() {
        return this.mZone;
    }

    public Subtype getSubtype() {
        return this.mSubtype;
    }

    private static Source getSourceEnum(String str) {
        if (SOURCE_ONBOARD.equals(str)) {
            return Source.ONBOARD;
        }
        if (SOURCE_OFFBOARD.equals(str)) {
            return Source.OFFBOARD;
        }
        Logger.error(TAG, "getSourceEnum: unknown asr source: " + str);
        return null;
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
        if (Subtype.WUW_VPA.getValue().equals(str)) {
            return Subtype.WUW_VPA;
        }
        if (Subtype.WUW_GATE.getValue().equals(str)) {
            return Subtype.WUW_GATE;
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
        WUW_VPA(JsonKey.WUW_TYP_VPA),
        WUW_GATE("wuw_gate"),
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
