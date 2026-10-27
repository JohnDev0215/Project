package com.amazon.music.media.playback;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class ControlSource {
    private final String name;
    private static final Map<String, ControlSource> controlSources = new HashMap();
    public static final ControlSource APP_UI = new ControlSource("app_ui");
    public static final ControlSource EXTERNAL = new ControlSource("external");
    public static final ControlSource NOTIFICATION = new ControlSource("notification");
    public static final ControlSource ALEXA = new ControlSource("alexa");
    public static final ControlSource GA = new ControlSource("google_assistant");
    public static final ControlSource WIDGET = new ControlSource("widget");
    public static final ControlSource NONE = new ControlSource("none");

    public static ControlSource getControlSource(@NonNull String str) {
        ControlSource controlSource;
        Map<String, ControlSource> map = controlSources;
        synchronized (map) {
            controlSource = map.get(str);
            if (controlSource == null) {
                controlSource = new ControlSource(str);
            }
        }
        return controlSource;
    }

    private ControlSource(String str) {
        this.name = (String) Validate.notEmpty(str, "ControlSource name cannot be empty", new Object[0]);
        controlSources.put(str, this);
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "ControlSource{name='" + this.name + "'}";
    }
}
