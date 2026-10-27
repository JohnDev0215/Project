package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import amazon.android.config.ConfigurationValue;

/* JADX INFO: loaded from: classes.dex */
public class FloatConfigurationValue extends ConfigurationValue<Float> {
    public FloatConfigurationValue(String str, Float f, ConfigEditor configEditor) {
        super(str, f, Float.class, configEditor);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // amazon.android.config.ConfigurationValue
    public Float getValue() {
        return Float.valueOf(this.mConfigEditor.getFloatConfig(this.mKey, ((Float) this.mDefaultValue).floatValue()));
    }

    @Override // amazon.android.config.ConfigurationValue
    public Float getValue(Float f) {
        return Float.valueOf(this.mConfigEditor.getFloatConfig(this.mKey, f.floatValue()));
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValueString(String str) {
        this.mConfigEditor.setFloatConfig(this.mKey, str);
    }

    @Override // amazon.android.config.ConfigurationValue
    public void updateValue(Float f) {
        this.mConfigEditor.setFloatConfig(this.mKey, f.toString());
    }
}
