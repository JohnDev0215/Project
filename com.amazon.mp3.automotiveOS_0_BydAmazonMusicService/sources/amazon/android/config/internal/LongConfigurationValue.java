package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import amazon.android.config.ConfigurationValue;

/* JADX INFO: loaded from: classes.dex */
public class LongConfigurationValue extends ConfigurationValue<Long> {
    public LongConfigurationValue(String str, Long l, ConfigEditor configEditor) {
        super(str, l, Long.class, configEditor);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // amazon.android.config.ConfigurationValue
    public Long getValue() {
        return Long.valueOf(this.mConfigEditor.getLongConfig(this.mKey, ((Long) this.mDefaultValue).longValue()));
    }

    @Override // amazon.android.config.ConfigurationValue
    public Long getValue(Long l) {
        return Long.valueOf(this.mConfigEditor.getLongConfig(this.mKey, l.longValue()));
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValueString(String str) {
        this.mConfigEditor.setLongConfig(this.mKey, str);
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValue(Long l) {
        this.mConfigEditor.setLongConfig(this.mKey, l.toString());
    }
}
