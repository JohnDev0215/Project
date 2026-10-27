package com.neusoft.slf4j.dlt;

import android.content.res.XmlResourceParser;
import android.util.Log;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class LogConfig {
    private static final String LOGTAG = "LogConfig";
    private static LogConfig instance = new LogConfig();
    private static boolean isInitialized = false;
    final Map<String, SlfLogLevel> map = new HashMap();

    private LogConfig() {
    }

    private LogConfig(XmlResourceParser xmlResourceParser) {
        try {
            int eventType = xmlResourceParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2 && "Setting".equals(xmlResourceParser.getName())) {
                    String attributeValue = xmlResourceParser.getAttributeValue(null, "name");
                    if (attributeValue == null) {
                        throw new IllegalArgumentException("Attribute 'name' missing in tag 'Setting");
                    }
                    String attributeValue2 = xmlResourceParser.getAttributeValue(null, "level");
                    if (attributeValue2 == null) {
                        throw new IllegalArgumentException("Attribute 'level' missing in tag 'Setting");
                    }
                    SlfLogLevel slfLogLevelValueOf = SlfLogLevel.valueOf(attributeValue2);
                    if (this.map.containsKey(attributeValue)) {
                        throw new IllegalArgumentException(String.format("Duplicate setting for '%s'", attributeValue));
                    }
                    this.map.put(attributeValue, slfLogLevelValueOf);
                }
                eventType = xmlResourceParser.next();
            }
        } catch (IOException | IllegalArgumentException | XmlPullParserException e) {
            Log.w(LOGTAG, e);
        }
    }

    static LogConfig getInstance() {
        if (!isInitialized) {
            Log.w(LOGTAG, "Request to uninitialized instance of LogConfig");
        }
        return instance;
    }

    public static synchronized void init(XmlResourceParser xmlResourceParser) {
        if (!isInitialized) {
            instance = new LogConfig(xmlResourceParser);
            isInitialized = true;
        }
    }
}
