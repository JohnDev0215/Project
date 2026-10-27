package com.amazon.music.inappmessaging.external.model;

import android.net.Uri;
import androidx.core.os.EnvironmentCompat;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageEvent {
    private final String event;
    private final DynamicMessageEventType eventType;
    private final boolean isCritical;

    public enum DynamicMessageEventType {
        TRIGGER("trigger"),
        ACTION("action"),
        HTTPS("https"),
        UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);

        private final String name;

        DynamicMessageEventType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public DynamicMessageEvent(final URI uri) {
        this.event = uri.toString();
        this.eventType = parseURIEventType(uri);
        this.isCritical = determineIfCriticalURI(uri);
    }

    public DynamicMessageEvent(final Trigger trigger) {
        this.event = trigger.getName();
        this.eventType = DynamicMessageEventType.TRIGGER;
        this.isCritical = trigger.isCritical();
    }

    public DynamicMessageEventType getEventType() {
        return this.eventType;
    }

    public String getEvent() {
        return this.event;
    }

    public boolean isCritical() {
        return this.isCritical;
    }

    public boolean isAppStartDeeplinkEvent() {
        Uri uri = Uri.parse(this.event);
        return (uri.getQueryParameter(com.amazon.music.inappmessaging.internal.model.Splash.PARAMS_CAMPAIGN_ID) == null && uri.getQueryParameter("category") == null) ? false : true;
    }

    public Map<String, String> getContextualValueMap() {
        HashMap map = new HashMap();
        Uri uri = Uri.parse(this.event);
        if (uri.getQueryParameterNames() != null) {
            for (String str : uri.getQueryParameterNames()) {
                map.put(str, uri.getQueryParameter(str));
            }
        }
        return map;
    }

    private DynamicMessageEventType parseURIEventType(final URI uri) {
        String scheme = uri.getScheme();
        DynamicMessageEventType dynamicMessageEventType = DynamicMessageEventType.ACTION;
        if (scheme.equals(dynamicMessageEventType.getName())) {
            return dynamicMessageEventType;
        }
        String scheme2 = uri.getScheme();
        DynamicMessageEventType dynamicMessageEventType2 = DynamicMessageEventType.TRIGGER;
        if (scheme2.equals(dynamicMessageEventType2.getName())) {
            return dynamicMessageEventType2;
        }
        String scheme3 = uri.getScheme();
        DynamicMessageEventType dynamicMessageEventType3 = DynamicMessageEventType.HTTPS;
        return scheme3.equals(dynamicMessageEventType3.getName()) ? dynamicMessageEventType3 : DynamicMessageEventType.UNKNOWN;
    }

    private boolean determineIfCriticalURI(final URI uri) {
        if (DynamicMessageEventType.TRIGGER.equals(parseURIEventType(uri))) {
            return Trigger.fromName(uri.toString()).isCritical();
        }
        return false;
    }
}
