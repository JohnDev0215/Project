package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import amazon.android.config.ConfigurationValue;

/* JADX INFO: loaded from: classes.dex */
public class StringConfigurationValue extends ConfigurationValue<String> {
    public StringConfigurationValue(String str, String str2, ConfigEditor configEditor) {
        super(str, str2, String.class, configEditor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // amazon.android.config.ConfigurationValue
    public String getValue() {
        return this.mConfigEditor.getStringConfig(this.mKey, (String) this.mDefaultValue);
    }

    @Override // amazon.android.config.ConfigurationValue
    public String getValue(String str) {
        return this.mConfigEditor.getStringConfig(this.mKey, str);
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValueString(String str) {
        this.mConfigEditor.setStringConfig(this.mKey, str);
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValue(String str) {
        updateValueString(str);
    }
}
