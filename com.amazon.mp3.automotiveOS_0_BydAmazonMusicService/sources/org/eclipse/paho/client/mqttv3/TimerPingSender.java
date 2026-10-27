package org.eclipse.paho.client.mqttv3;

import com.google.firebase.perf.plugin.util.AsmString;
import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class TimerPingSender implements MqttPingSender {
    private static final String CLASS_NAME = "org.eclipse.paho.client.mqttv3.TimerPingSender";
    private String clientid;
    private ClientComms comms;
    private Logger log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    private Timer timer;

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void init(ClientComms clientComms) {
        if (clientComms == null) {
            throw new IllegalArgumentException("ClientComms cannot be null.");
        }
        this.comms = clientComms;
        String clientId = clientComms.getClient().getClientId();
        this.clientid = clientId;
        this.log.setResourceName(clientId);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void start() {
        this.log.fine(CLASS_NAME, AsmString.METHOD_FIREBASE_PERF_TRACE_START, "659", new Object[]{this.clientid});
        Timer timer = new Timer("MQTT Ping: " + this.clientid);
        this.timer = timer;
        timer.schedule(new PingTask(this, null), this.comms.getKeepAlive());
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void stop() {
        this.log.fine(CLASS_NAME, AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "661", null);
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void schedule(long j) {
        this.timer.schedule(new PingTask(this, null), j);
    }

    private class PingTask extends TimerTask {
        private static final String methodName = "PingTask.run";

        private PingTask() {
        }

        /* synthetic */ PingTask(TimerPingSender timerPingSender, PingTask pingTask) {
            this();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            TimerPingSender.this.log.fine(TimerPingSender.CLASS_NAME, methodName, "660", new Object[]{Long.valueOf(System.nanoTime())});
            TimerPingSender.this.comms.checkForActivity();
        }
    }
}
