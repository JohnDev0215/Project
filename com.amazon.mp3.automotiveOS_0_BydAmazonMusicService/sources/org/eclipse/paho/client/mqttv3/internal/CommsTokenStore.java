package org.eclipse.paho.client.mqttv3.internal;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class CommsTokenStore {
    private static final String CLASS_NAME = "org.eclipse.paho.client.mqttv3.internal.CommsTokenStore";
    private MqttException closedResponse;
    private Logger log;
    private String logContext;
    private final Hashtable tokens;

    public CommsTokenStore(String str) {
        String str2 = CLASS_NAME;
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, str2);
        this.log = logger;
        this.closedResponse = null;
        logger.setResourceName(str);
        this.tokens = new Hashtable();
        this.logContext = str;
        this.log.fine(str2, "<Init>", "308");
    }

    public MqttToken getToken(MqttWireMessage mqttWireMessage) {
        return (MqttToken) this.tokens.get(mqttWireMessage.getKey());
    }

    public MqttToken getToken(String str) {
        return (MqttToken) this.tokens.get(str);
    }

    public MqttToken removeToken(MqttWireMessage mqttWireMessage) {
        if (mqttWireMessage != null) {
            return removeToken(mqttWireMessage.getKey());
        }
        return null;
    }

    public MqttToken removeToken(String str) {
        this.log.fine(CLASS_NAME, "removeToken", "306", new Object[]{str});
        if (str != null) {
            return (MqttToken) this.tokens.remove(str);
        }
        return null;
    }

    protected MqttDeliveryToken restoreToken(MqttPublish mqttPublish) {
        MqttDeliveryToken mqttDeliveryToken;
        synchronized (this.tokens) {
            String string = Integer.toString(mqttPublish.getMessageId());
            if (this.tokens.containsKey(string)) {
                mqttDeliveryToken = (MqttDeliveryToken) this.tokens.get(string);
                this.log.fine(CLASS_NAME, "restoreToken", "302", new Object[]{string, mqttPublish, mqttDeliveryToken});
            } else {
                mqttDeliveryToken = new MqttDeliveryToken(this.logContext);
                mqttDeliveryToken.internalTok.setKey(string);
                this.tokens.put(string, mqttDeliveryToken);
                this.log.fine(CLASS_NAME, "restoreToken", "303", new Object[]{string, mqttPublish, mqttDeliveryToken});
            }
        }
        return mqttDeliveryToken;
    }

    protected void saveToken(MqttToken mqttToken, MqttWireMessage mqttWireMessage) throws MqttException {
        synchronized (this.tokens) {
            MqttException mqttException = this.closedResponse;
            if (mqttException == null) {
                String key = mqttWireMessage.getKey();
                this.log.fine(CLASS_NAME, "saveToken", "300", new Object[]{key, mqttWireMessage});
                saveToken(mqttToken, key);
            } else {
                throw mqttException;
            }
        }
    }

    protected void saveToken(MqttToken mqttToken, String str) {
        synchronized (this.tokens) {
            this.log.fine(CLASS_NAME, "saveToken", "307", new Object[]{str, mqttToken.toString()});
            mqttToken.internalTok.setKey(str);
            this.tokens.put(str, mqttToken);
        }
    }

    protected void quiesce(MqttException mqttException) {
        synchronized (this.tokens) {
            this.log.fine(CLASS_NAME, "quiesce", "309", new Object[]{mqttException});
            this.closedResponse = mqttException;
        }
    }

    public void open() {
        synchronized (this.tokens) {
            this.log.fine(CLASS_NAME, "open", "310");
            this.closedResponse = null;
        }
    }

    public MqttDeliveryToken[] getOutstandingDelTokens() {
        MqttDeliveryToken[] mqttDeliveryTokenArr;
        synchronized (this.tokens) {
            this.log.fine(CLASS_NAME, "getOutstandingDelTokens", "311");
            Vector vector = new Vector();
            Enumeration enumerationElements = this.tokens.elements();
            while (enumerationElements.hasMoreElements()) {
                MqttToken mqttToken = (MqttToken) enumerationElements.nextElement();
                if (mqttToken != null && (mqttToken instanceof MqttDeliveryToken) && !mqttToken.internalTok.isNotified()) {
                    vector.addElement(mqttToken);
                }
            }
            mqttDeliveryTokenArr = (MqttDeliveryToken[]) vector.toArray(new MqttDeliveryToken[vector.size()]);
        }
        return mqttDeliveryTokenArr;
    }

    public Vector getOutstandingTokens() {
        Vector vector;
        synchronized (this.tokens) {
            this.log.fine(CLASS_NAME, "getOutstandingTokens", "312");
            vector = new Vector();
            Enumeration enumerationElements = this.tokens.elements();
            while (enumerationElements.hasMoreElements()) {
                MqttToken mqttToken = (MqttToken) enumerationElements.nextElement();
                if (mqttToken != null) {
                    vector.addElement(mqttToken);
                }
            }
        }
        return vector;
    }

    public void clear() {
        this.log.fine(CLASS_NAME, "clear", "305", new Object[]{Integer.valueOf(this.tokens.size())});
        synchronized (this.tokens) {
            this.tokens.clear();
        }
    }

    public int count() {
        int size;
        synchronized (this.tokens) {
            size = this.tokens.size();
        }
        return size;
    }

    public String toString() {
        String string;
        String property = System.getProperty("line.separator", "\n");
        StringBuffer stringBuffer = new StringBuffer();
        synchronized (this.tokens) {
            Enumeration enumerationElements = this.tokens.elements();
            while (enumerationElements.hasMoreElements()) {
                stringBuffer.append("{" + ((MqttToken) enumerationElements.nextElement()).internalTok + "}" + property);
            }
            string = stringBuffer.toString();
        }
        return string;
    }
}
