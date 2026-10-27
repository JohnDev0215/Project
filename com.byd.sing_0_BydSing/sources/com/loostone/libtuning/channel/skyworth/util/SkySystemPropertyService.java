package com.loostone.libtuning.channel.skyworth.util;

import OooO00o.OooO00o.OooO0O0.OooO0o0.c;
import com.loostone.libtuning.channel.skyworth.bean.SkySystemProperty;
import com.loostone.libtuning.channel.skyworth.inf.ISkySystemProperty;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.a;
import kotlin.d;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class SkySystemPropertyService {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "MicMuteService";
    private SystemPropertyThread listenThread;
    private boolean startListen = true;
    private final a callBackList$delegate = d.b(new kotlin.jvm.b.a<LinkedList<ISkySystemProperty>>() { // from class: com.loostone.libtuning.channel.skyworth.util.SkySystemPropertyService$callBackList$2
        @Override // kotlin.jvm.b.a
        public final LinkedList<ISkySystemProperty> invoke() {
            return new LinkedList<>();
        }
    });
    private final a systemProperties$delegate = d.b(new kotlin.jvm.b.a<ArrayList<SkySystemProperty>>() { // from class: com.loostone.libtuning.channel.skyworth.util.SkySystemPropertyService$systemProperties$2
        @Override // kotlin.jvm.b.a
        public final ArrayList<SkySystemProperty> invoke() {
            ArrayList<SkySystemProperty> arrayList = new ArrayList<>();
            arrayList.add(new SkySystemProperty());
            return arrayList;
        }
    });

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    public final class SystemPropertyThread extends Thread {
        private final Object lock;
        private boolean pause;
        public final /* synthetic */ SkySystemPropertyService this$0;

        public SystemPropertyThread(SkySystemPropertyService skySystemPropertyService) {
            h.d(skySystemPropertyService, "this$0");
            this.this$0 = skySystemPropertyService;
            this.lock = new Object();
        }

        private final void onPause() {
            synchronized (this.lock) {
                try {
                    this.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public final void pauseThread() {
            this.pause = true;
        }

        public final void resumeThread() {
            this.pause = false;
            synchronized (this.lock) {
                this.lock.notifyAll();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.this$0.startListen) {
                try {
                    while (this.pause) {
                        onPause();
                    }
                    this.this$0.dealWithMute();
                    Thread.sleep(1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dealWithMute() {
        int iA = c.f35a.a("third.get.aistandbymode", 0);
        boolean z = iA != 0;
        if (getSystemProperties().get(0).getLastState() != iA) {
            try {
                Iterator<ISkySystemProperty> it = getCallBackList().iterator();
                while (it.hasNext()) {
                    it.next().onDormancy(z);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        getSystemProperties().get(0).setLastState(iA);
    }

    private final LinkedList<ISkySystemProperty> getCallBackList() {
        return (LinkedList) this.callBackList$delegate.getValue();
    }

    private final ArrayList<SkySystemProperty> getSystemProperties() {
        return (ArrayList) this.systemProperties$delegate.getValue();
    }

    public final void pause() {
        SystemPropertyThread systemPropertyThread = this.listenThread;
        if (systemPropertyThread == null) {
            return;
        }
        systemPropertyThread.pauseThread();
    }

    public final void registerCallback(ISkySystemProperty iSkySystemProperty) {
        h.d(iSkySystemProperty, "listener");
        getCallBackList().add(iSkySystemProperty);
    }

    public final void resume() {
        SystemPropertyThread systemPropertyThread = this.listenThread;
        if (systemPropertyThread == null) {
            return;
        }
        systemPropertyThread.resumeThread();
    }

    public final void start() {
        this.startListen = true;
        SystemPropertyThread systemPropertyThread = new SystemPropertyThread(this);
        this.listenThread = systemPropertyThread;
        systemPropertyThread.start();
    }

    public final void stop() {
        this.startListen = false;
        this.listenThread = null;
    }

    public final void unRegisterCallback(ISkySystemProperty iSkySystemProperty) {
        h.d(iSkySystemProperty, "listener");
        getCallBackList().remove(iSkySystemProperty);
    }
}
