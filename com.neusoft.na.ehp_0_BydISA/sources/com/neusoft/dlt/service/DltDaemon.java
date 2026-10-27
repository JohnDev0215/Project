package com.neusoft.dlt.service;

import android.util.Log;
import java.io.IOException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

/* JADX INFO: loaded from: classes.dex */
public class DltDaemon {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String LOGTAG = "DltDaemon";
    private Selector mSelector;
    private IServerThread mIoThread = null;
    private ArrayList<ILogSource> mLogSources = new ArrayList<>();
    private ArrayList<ILogSink> mLogSinks = new ArrayList<>();
    private BehaviorSubject<Integer> mClientCountSubject = BehaviorSubject.create(0);
    private BehaviorSubject<DaemonState> mStatus = BehaviorSubject.create(DaemonState.OFF);
    private Integer bytesLogged = 0;

    public enum DaemonState {
        OFF,
        RUNNING,
        ERROR
    }

    private interface IServerThread {
        boolean isRunning();

        void stop();
    }

    public DltDaemon() {
        try {
            this.mSelector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getBytesLogged() {
        return this.bytesLogged.intValue();
    }

    public int getNrOfClients() {
        return this.mClientCountSubject.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    class IoThread implements Runnable, IServerThread {
        private boolean mRunning;
        private HashSet<Subscription> mSubscriptions;
        private Thread mThread;

        private IoThread() {
            this.mSubscriptions = new HashSet<>();
            this.mThread = new Thread(this, "DltDaemonIoThread");
            this.mRunning = false;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = DltDaemon.this.mLogSources.iterator();
            while (it.hasNext()) {
                this.mSubscriptions.add(((ILogSource) it.next()).getClientCountObservable().subscribe(new Action1() { // from class: com.neusoft.dlt.service.DltDaemon$IoThread$$ExternalSyntheticLambda1
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        this.f$0.m37lambda$run$0$comneusoftdltserviceDltDaemon$IoThread((Integer) obj);
                    }
                }));
            }
            DltDaemon.this.mStatus.onNext(DaemonState.RUNNING);
            Iterator it2 = DltDaemon.this.mLogSinks.iterator();
            while (it2.hasNext()) {
                ((ILogSink) it2.next()).start();
            }
            for (ILogSource iLogSource : DltDaemon.this.mLogSources) {
                this.mSubscriptions.add(iLogSource.getMessageObservable().subscribe(new Action1() { // from class: com.neusoft.dlt.service.DltDaemon$IoThread$$ExternalSyntheticLambda0
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        this.f$0.m38lambda$run$1$comneusoftdltserviceDltDaemon$IoThread((DltMessage) obj);
                    }
                }));
                iLogSource.start();
            }
            while (this.mRunning) {
                try {
                    DltDaemon.this.mSelector.select();
                    try {
                        Iterator<SelectionKey> it3 = DltDaemon.this.mSelector.selectedKeys().iterator();
                        while (it3.hasNext()) {
                            Object objAttachment = it3.next().attachment();
                            if (objAttachment != null && (objAttachment instanceof Runnable)) {
                                ((Runnable) objAttachment).run();
                            }
                            it3.remove();
                        }
                    } catch (ClosedSelectorException unused) {
                    }
                } catch (IOException unused2) {
                } catch (Throwable th) {
                    this.mRunning = false;
                    throw th;
                }
            }
            DltDaemon dltDaemon = DltDaemon.this;
            dltDaemon.stopILogSource(dltDaemon.mLogSources);
            DltDaemon dltDaemon2 = DltDaemon.this;
            dltDaemon2.stopILogSink(dltDaemon2.mLogSinks);
            this.mRunning = false;
            DltDaemon dltDaemon3 = DltDaemon.this;
            dltDaemon3.stopILogSource(dltDaemon3.mLogSources);
            DltDaemon dltDaemon4 = DltDaemon.this;
            dltDaemon4.stopILogSink(dltDaemon4.mLogSinks);
            DltDaemon.this.stopSubscription(this.mSubscriptions);
            DltDaemon.this.mClientCountSubject.onNext(0);
            DltDaemon.this.mStatus.onNext(DaemonState.OFF);
        }

        /* JADX INFO: renamed from: lambda$run$0$com-neusoft-dlt-service-DltDaemon$IoThread, reason: not valid java name */
        /* synthetic */ void m37lambda$run$0$comneusoftdltserviceDltDaemon$IoThread(Integer num) {
            DltDaemon.this.mClientCountSubject.onNext(num);
        }

        /* JADX INFO: renamed from: lambda$run$1$com-neusoft-dlt-service-DltDaemon$IoThread, reason: not valid java name */
        /* synthetic */ void m38lambda$run$1$comneusoftdltserviceDltDaemon$IoThread(DltMessage dltMessage) {
            synchronized (DltDaemon.this.bytesLogged) {
                DltDaemon dltDaemon = DltDaemon.this;
                dltDaemon.bytesLogged = Integer.valueOf(dltDaemon.bytesLogged.intValue() + dltMessage.getLength());
            }
            Iterator it = DltDaemon.this.mLogSinks.iterator();
            while (it.hasNext()) {
                try {
                    ((ILogSink) it.next()).logMessage(dltMessage);
                } catch (Throwable th) {
                    Log.w(DltDaemon.LOGTAG, "failed to log message", th);
                    DltDaemon.this.mStatus.onNext(DaemonState.ERROR);
                    it.remove();
                }
            }
        }

        public void start() {
            this.mRunning = true;
            this.mThread.start();
        }

        @Override // com.neusoft.dlt.service.DltDaemon.IServerThread
        public void stop() {
            if (this.mRunning) {
                this.mRunning = false;
                DltDaemon.this.mSelector.wakeup();
                try {
                    this.mThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override // com.neusoft.dlt.service.DltDaemon.IServerThread
        public boolean isRunning() {
            return this.mRunning;
        }
    }

    public synchronized void start() {
        if (this.mIoThread == null) {
            IoThread ioThread = new IoThread();
            this.mIoThread = ioThread;
            ioThread.start();
        }
    }

    public synchronized void stop() {
        this.mIoThread.stop();
        this.mIoThread = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopILogSink(List<ILogSink> list) {
        Iterator<ILogSink> it = list.iterator();
        while (it.hasNext()) {
            it.next().stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopILogSource(List<ILogSource> list) {
        Iterator<ILogSource> it = list.iterator();
        while (it.hasNext()) {
            it.next().stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopSubscription(HashSet<Subscription> hashSet) {
        Iterator<Subscription> it = hashSet.iterator();
        while (it.hasNext()) {
            it.next().unsubscribe();
        }
    }

    public void addLogSink(ILogSink iLogSink) {
        this.mLogSinks.add(iLogSink);
        iLogSink.register(this.mSelector);
    }

    public void addLogSource(ILogSource iLogSource) {
        this.mLogSources.add(iLogSource);
        iLogSource.register(this.mSelector);
    }

    public Observable<Integer> getConnectedClientsObservable() {
        return this.mClientCountSubject.asObservable();
    }

    public Observable<DaemonState> getStatusObservable() {
        return this.mStatus.asObservable();
    }
}
