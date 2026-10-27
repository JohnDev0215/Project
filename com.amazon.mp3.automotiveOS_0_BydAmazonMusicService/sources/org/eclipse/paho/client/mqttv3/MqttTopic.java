package org.eclipse.paho.client.mqttv3;

import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.util.Strings;

/* JADX INFO: loaded from: classes6.dex */
public class MqttTopic {
    private static final int MAX_TOPIC_LEN = 65535;
    private static final int MIN_TOPIC_LEN = 1;
    public static final String MULTI_LEVEL_WILDCARD = "#";
    public static final String MULTI_LEVEL_WILDCARD_PATTERN = "/#";
    private static final char NUL = 0;
    public static final String SINGLE_LEVEL_WILDCARD = "+";
    public static final String TOPIC_LEVEL_SEPARATOR = "/";
    public static final String TOPIC_WILDCARDS = "#+";
    private ClientComms comms;
    private String name;

    public MqttTopic(String str, ClientComms clientComms) {
        this.comms = clientComms;
        this.name = str;
    }

    public MqttDeliveryToken publish(byte[] bArr, int i, boolean z) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        return publish(mqttMessage);
    }

    public MqttDeliveryToken publish(MqttMessage mqttMessage) throws MqttException {
        MqttDeliveryToken mqttDeliveryToken = new MqttDeliveryToken(this.comms.getClient().getClientId());
        mqttDeliveryToken.setMessage(mqttMessage);
        this.comms.sendNoWait(createPublish(mqttMessage), mqttDeliveryToken);
        mqttDeliveryToken.internalTok.waitUntilSent();
        return mqttDeliveryToken;
    }

    public String getName() {
        return this.name;
    }

    private MqttPublish createPublish(MqttMessage mqttMessage) {
        return new MqttPublish(getName(), mqttMessage);
    }

    public String toString() {
        return getName();
    }

    public static void validate(String str, boolean z) throws IllegalArgumentException {
        try {
            int length = str.getBytes(ParserUtil.UTF_8).length;
            if (length < 1 || length > 65535) {
                throw new IllegalArgumentException(String.format("Invalid topic length, should be in range[%d, %d]!", 1, 65535));
            }
            if (z) {
                if (Strings.equalsAny(str, new String[]{"#", SINGLE_LEVEL_WILDCARD})) {
                    return;
                }
                if (Strings.countMatches(str, "#") > 1 || (str.contains("#") && !str.endsWith(MULTI_LEVEL_WILDCARD_PATTERN))) {
                    throw new IllegalArgumentException("Invalid usage of multi-level wildcard in topic string: " + str);
                }
                validateSingleLevelWildcard(str);
                return;
            }
            if (Strings.containsAny(str, TOPIC_WILDCARDS)) {
                throw new IllegalArgumentException("The topic name MUST NOT contain any wildcard characters (#+)");
            }
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private static void validateSingleLevelWildcard(String str) {
        char cCharAt = SINGLE_LEVEL_WILDCARD.charAt(0);
        char cCharAt2 = "/".charAt(0);
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        while (i < length) {
            int i2 = i - 1;
            char c = i2 >= 0 ? charArray[i2] : (char) 0;
            int i3 = i + 1;
            char c2 = i3 < length ? charArray[i3] : (char) 0;
            if (charArray[i] == cCharAt && ((c != cCharAt2 && c != 0) || (c2 != cCharAt2 && c2 != 0))) {
                throw new IllegalArgumentException(String.format("Invalid usage of single-level wildcard in topic string '%s'!", str));
            }
            i = i3;
        }
    }

    public static boolean isMatched(String str, String str2) throws IllegalArgumentException {
        int length = str2.length();
        int length2 = str.length();
        validate(str, true);
        validate(str2, false);
        if (str.equals(str2)) {
            return true;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i < length2 && i2 < length) {
                if (str.charAt(i) != '#') {
                    if ((str2.charAt(i2) == '/' && str.charAt(i) != '/') || (str.charAt(i) != '+' && str.charAt(i) != '#' && str.charAt(i) != str2.charAt(i2))) {
                        break;
                    }
                    if (str.charAt(i) == '+') {
                        while (true) {
                            int i3 = i2 + 1;
                            if (i3 >= length || str2.charAt(i3) == '/') {
                                break;
                            }
                            i2++;
                        }
                    }
                    i++;
                    i2++;
                } else {
                    i2 = length;
                    i = length2;
                    break;
                }
            } else {
                break;
            }
        }
        if (i2 == length && i == length2) {
            return true;
        }
        if (str.length() - i > 0 && i2 == length) {
            if (str2.charAt(i2 - 1) == '/' && str.charAt(i) == '#') {
                return true;
            }
            if (str.length() - i > 1 && str.substring(i, i + 2).equals(MULTI_LEVEL_WILDCARD_PATTERN)) {
                return true;
            }
        }
        return false;
    }
}
