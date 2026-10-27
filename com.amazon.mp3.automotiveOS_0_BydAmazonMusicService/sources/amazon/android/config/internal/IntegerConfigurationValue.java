package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import amazon.android.config.ConfigurationValue;

/* JADX INFO: loaded from: classes.dex */
public class IntegerConfigurationValue extends ConfigurationValue<Integer> {
    public IntegerConfigurationValue(String str, Integer num, ConfigEditor configEditor) {
        super(str, num, Integer.class, configEditor);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // amazon.android.config.ConfigurationValue
    public Integer getValue() {
        return Integer.valueOf(this.mConfigEditor.getIntConfig(this.mKey, ((Integer) this.mDefaultValue).intValue()));
    }

    @Override // amazon.android.config.ConfigurationValue
    public Integer getValue(Integer num) {
        return Integer.valueOf(this.mConfigEditor.getIntConfig(this.mKey, num.intValue()));
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValueString(String str) {
        this.mConfigEditor.setIntConfig(this.mKey, str);
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValue(Integer num) {
        this.mConfigEditor.setIntConfig(this.mKey, num.toString());
    }
}
