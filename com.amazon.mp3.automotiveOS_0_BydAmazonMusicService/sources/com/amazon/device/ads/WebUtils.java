package com.amazon.device.ads;

import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.IllegalCharsetNameException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* JADX INFO: loaded from: classes2.dex */
class WebUtils {
    private static final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(WebUtils.class.getSimpleName());

    WebUtils() {
    }

    public static final String getURLEncodedString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLEncoder.encode(str, ParserUtil.UTF_8).replace(MqttTopic.SINGLE_LEVEL_WILDCARD, "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            logger.d("getURLEncodedString threw: %s", e);
            return str;
        } catch (IllegalCharsetNameException e2) {
            logger.d("getURLEncodedString threw: %s", e2);
            return str;
        }
    }

    public static final String getURLDecodedString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLDecoder.decode(str, ParserUtil.UTF_8);
        } catch (UnsupportedEncodingException e) {
            logger.d("getURLDecodedString threw: %s", e);
            return str;
        }
    }
}
