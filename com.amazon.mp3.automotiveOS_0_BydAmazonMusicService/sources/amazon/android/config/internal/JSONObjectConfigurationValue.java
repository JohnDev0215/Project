package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import com.amazon.avod.util.DLog;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class JSONObjectConfigurationValue extends CachingConfigurationValue<JSONObject> {
    public JSONObjectConfigurationValue(String str, JSONObject jSONObject, ConfigEditor configEditor) {
        super(str, jSONObject, JSONObject.class, configEditor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nullable
    public JSONObject parse(@Nonnull String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            DLog.exceptionf(e, "Error when parsing JSON for config %s: could not parse source \"%s\".", this.mKey, str);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.android.config.internal.CachingConfigurationValue
    @Nonnull
    public String asString(@Nonnull JSONObject jSONObject) {
        return jSONObject.toString();
    }
}
