package org.eclipse.paho.client.mqttv3;

import com.google.firebase.perf.plugin.util.AsmString;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class ScheduledExecutorPingSender implements MqttPingSender {
    private static final String CLASS_NAME = "org.eclipse.paho.client.mqttv3.ScheduledExecutorPingSender";
    private String clientid;
    private ClientComms comms;
    private ScheduledExecutorService executorService;
    private final Logger log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    private ScheduledFuture scheduledFuture;

    public ScheduledExecutorPingSender(ScheduledExecutorService scheduledExecutorService) {
        if (scheduledExecutorService == null) {
            throw new IllegalArgumentException("ExecutorService cannot be null.");
        }
        this.executorService = scheduledExecutorService;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void init(ClientComms clientComms) {
        if (clientComms == null) {
            throw new IllegalArgumentException("ClientComms cannot be null.");
        }
        this.comms = clientComms;
        this.clientid = clientComms.getClient().getClientId();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void start() {
        this.log.fine(CLASS_NAME, AsmString.METHOD_FIREBASE_PERF_TRACE_START, "659", new Object[]{this.clientid});
        schedule(this.comms.getKeepAlive());
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void stop() {
        this.log.fine(CLASS_NAME, AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "661", null);
        ScheduledFuture scheduledFuture = this.scheduledFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void schedule(long j) {
        this.scheduledFuture = this.executorService.schedule(new PingRunnable(this, null), j, TimeUnit.MILLISECONDS);
    }

    private class PingRunnable implements Runnable {
        private static final String methodName = "PingTask.run";

        private PingRunnable() {
        }

        /* synthetic */ PingRunnable(ScheduledExecutorPingSender scheduledExecutorPingSender, PingRunnable pingRunnable) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            String name = Thread.currentThread().getName();
            Thread.currentThread().setName("MQTT Ping: " + ScheduledExecutorPingSender.this.clientid);
            ScheduledExecutorPingSender.this.log.fine(ScheduledExecutorPingSender.CLASS_NAME, methodName, "660", new Object[]{Long.valueOf(System.nanoTime())});
            ScheduledExecutorPingSender.this.comms.checkForActivity();
            Thread.currentThread().setName(name);
        }
    }
}
