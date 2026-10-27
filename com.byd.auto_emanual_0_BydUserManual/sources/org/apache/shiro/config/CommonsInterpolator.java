package org.apache.shiro.config;

import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;
import org.apache.commons.configuration2.interpol.ConstantLookup;
import org.apache.commons.configuration2.interpol.EnvironmentLookup;
import org.apache.commons.configuration2.interpol.SystemPropertiesLookup;

/* JADX INFO: loaded from: classes.dex */
public class CommonsInterpolator implements Interpolator {
    private final ConfigurationInterpolator interpolator;

    public CommonsInterpolator() {
        ConfigurationInterpolator configurationInterpolator = new ConfigurationInterpolator();
        this.interpolator = configurationInterpolator;
        configurationInterpolator.registerLookup("const", new ConstantLookup());
        configurationInterpolator.addDefaultLookup(new SystemPropertiesLookup());
        configurationInterpolator.addDefaultLookup(new EnvironmentLookup());
    }

    @Override // org.apache.shiro.config.Interpolator
    public String interpolate(String str) {
        return (String) this.interpolator.interpolate(str);
    }

    public ConfigurationInterpolator getConfigurationInterpolator() {
        return this.interpolator;
    }
}
