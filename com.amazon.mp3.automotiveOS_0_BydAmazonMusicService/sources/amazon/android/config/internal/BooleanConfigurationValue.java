package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import amazon.android.config.ConfigurationValue;

/* JADX INFO: loaded from: classes.dex */
public class BooleanConfigurationValue extends ConfigurationValue<Boolean> {
    public BooleanConfigurationValue(String str, Boolean bool, ConfigEditor configEditor) {
        super(str, bool, Boolean.class, configEditor);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // amazon.android.config.ConfigurationValue
    public Boolean getValue() {
        return Boolean.valueOf(this.mConfigEditor.getBooleanConfig(this.mKey, ((Boolean) this.mDefaultValue).booleanValue()));
    }

    @Override // amazon.android.config.ConfigurationValue
    public Boolean getValue(Boolean bool) {
        return Boolean.valueOf(this.mConfigEditor.getBooleanConfig(this.mKey, bool.booleanValue()));
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValueString(String str) {
        this.mConfigEditor.setBooleanConfig(this.mKey, str);
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValue(Boolean bool) {
        this.mConfigEditor.setBooleanConfig(this.mKey, bool.toString());
    }
}
