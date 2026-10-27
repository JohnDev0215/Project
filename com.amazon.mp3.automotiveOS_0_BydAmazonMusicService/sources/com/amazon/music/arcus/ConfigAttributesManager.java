package com.amazon.music.arcus;

import com.amazon.music.arcus.ConfigRefreshGate;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class ConfigAttributesManager implements ConfigRefreshGate {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigAttributesManager.class.getSimpleName());
    private static boolean UPDATING;
    private Map<String, Object> customAttributes;
    private ConfigRefreshGate.ConfigRefreshGateState state = ConfigRefreshGate.ConfigRefreshGateState.REFRESH_ALLOWED;

    @Override // com.amazon.music.arcus.ConfigRefreshGate
    public ConfigRefreshGate.ConfigRefreshGateState getGateState() {
        return this.state;
    }

    public void onConfigRefreshed() {
        this.state = ConfigRefreshGate.ConfigRefreshGateState.REFRESH_ALLOWED;
    }

    public void updateManagerAttributes(RemoteConfigurationManager remoteConfigurationManager, Map<String, Object> map) {
        Validate.notNull(remoteConfigurationManager);
        Validate.notNull(map);
        if (setManagerAttributes(remoteConfigurationManager, map)) {
            this.state = ConfigRefreshGate.ConfigRefreshGateState.REFRESH_NEEDED;
        }
    }

    private boolean setManagerAttributes(RemoteConfigurationManager remoteConfigurationManager, Map<String, Object> map) {
        Validate.notNull(remoteConfigurationManager);
        Validate.notNull(map);
        Map<String, Object> map2 = this.customAttributes;
        boolean z = map2 == null || !map2.equals(map);
        if (z && !isUpdating()) {
            LOG.debug("New attributes are not equal to the existing attributes; updating custom attributes.");
            setUpdating(true);
            this.customAttributes = map;
            updateCustomAttributes(remoteConfigurationManager, map);
        }
        return z && !isUpdating();
    }

    private void updateCustomAttributes(RemoteConfigurationManager remoteConfigurationManager, Map<String, Object> map) {
        Validate.notNull(remoteConfigurationManager);
        Validate.notNull(map);
        Attributes attributesOpenAttributes = remoteConfigurationManager.openAttributes();
        for (String str : attributesOpenAttributes.getAllAttributes().keySet()) {
            if (!str.startsWith(Attributes.PREDEFINED_ATTRIBUTE_PREFIX)) {
                attributesOpenAttributes.remove(str);
            }
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (!StringUtils.isEmpty(key)) {
                if (value.getClass().isAssignableFrom(Long.class)) {
                    attributesOpenAttributes.addAttribute(key, (Long) value);
                } else if (value.getClass().isAssignableFrom(Integer.class)) {
                    attributesOpenAttributes.addAttribute(key, (Integer) value);
                } else if (value.getClass().isAssignableFrom(String.class)) {
                    attributesOpenAttributes.addAttribute(key, (String) value);
                } else if (value.getClass().isAssignableFrom(Boolean.class)) {
                    attributesOpenAttributes.addAttribute(key, (Boolean) value);
                } else if (value.getClass().isAssignableFrom(Double.class)) {
                    attributesOpenAttributes.addAttribute(key, (Double) value);
                }
            }
        }
        setUpdating(false);
    }

    boolean isUpdating() {
        return UPDATING;
    }

    void setUpdating(boolean z) {
        UPDATING = z;
    }
}
