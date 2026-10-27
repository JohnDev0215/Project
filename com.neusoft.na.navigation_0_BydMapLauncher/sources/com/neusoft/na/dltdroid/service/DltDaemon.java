package com.neusoft.na.dltdroid.service;

import java.nio.channels.Selector;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/* JADX INFO: compiled from: DltDaemon.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 &2\u00020\u0001:\u0002&'B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0012J\u000e\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0014J\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\f0\"J\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00180\"J\u0006\u0010$\u001a\u00020\u001dJ\u0006\u0010%\u001a\u00020\u001dR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\n\u001a&\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f \r*\u0012\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n \r*\u0004\u0018\u00010\u00160\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u0017\u001a&\u0012\f\u0012\n \r*\u0004\u0018\u00010\u00180\u0018 \r*\u0012\u0012\f\u0012\n \r*\u0004\u0018\u00010\u00180\u0018\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0019\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f8F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006("}, d2 = {"Lcom/neusoft/na/dltdroid/service/DltDaemon;", "", "()V", "<set-?>", "", "bytesLogged", "getBytesLogged", "()J", "crashFlag", "", "mClientCountSubject", "Lrx/subjects/BehaviorSubject;", "", "kotlin.jvm.PlatformType", "mIoThread", "Lcom/neusoft/na/dltdroid/service/DltDaemon$IServerThread;", "mLogSinks", "Ljava/util/ArrayList;", "Lcom/neusoft/na/dltdroid/service/ILogSink;", "mLogSources", "Lcom/neusoft/na/dltdroid/service/ILogSource;", "mSelector", "Ljava/nio/channels/Selector;", "mStatus", "Lcom/neusoft/na/dltdroid/service/DaemonState;", "nrOfClients", "getNrOfClients", "()I", "addLogSink", "", "logSink", "addLogSource", "logSource", "getConnectedClientsObservable", "Lrx/Observable;", "getStatusObservable", "start", "stop", "Companion", "IServerThread", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class DltDaemon {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String LOGTAG = "DltDaemon";
    private long bytesLogged;
    private IServerThread mIoThread;
    private int nrOfClients;
    private final Selector mSelector = Selector.open();
    private final ArrayList<ILogSource> mLogSources = new ArrayList<>();
    private final ArrayList<ILogSink> mLogSinks = new ArrayList<>();
    private final BehaviorSubject<Integer> mClientCountSubject = BehaviorSubject.create(0);
    private final BehaviorSubject<DaemonState> mStatus = BehaviorSubject.create(DaemonState.OFF);
    private boolean crashFlag = true;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DltDaemon.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\bb\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/neusoft/na/dltdroid/service/DltDaemon$IServerThread;", "", "isRunning", "", "stop", "", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    interface IServerThread {
        boolean isRunning();

        void stop();
    }

    public final long getBytesLogged() {
        return this.bytesLogged;
    }

    public final int getNrOfClients() {
        Integer value = this.mClientCountSubject.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "mClientCountSubject.value");
        return value.intValue();
    }

    public final synchronized void start() {
        if (this.mIoThread == null) {
            DltDaemon$start$ioThread$1 dltDaemon$start$ioThread$1 = new DltDaemon$start$ioThread$1(this);
            this.mIoThread = dltDaemon$start$ioThread$1;
            dltDaemon$start$ioThread$1.start();
        }
    }

    public final synchronized void stop() {
        IServerThread iServerThread = this.mIoThread;
        if (iServerThread != null) {
            iServerThread.stop();
        }
        this.mIoThread = null;
    }

    public final void addLogSink(ILogSink logSink) {
        Intrinsics.checkNotNullParameter(logSink, "logSink");
        this.mLogSinks.add(logSink);
        Selector mSelector = this.mSelector;
        Intrinsics.checkNotNullExpressionValue(mSelector, "mSelector");
        logSink.register(mSelector);
    }

    public final void addLogSource(ILogSource logSource) {
        Intrinsics.checkNotNullParameter(logSource, "logSource");
        this.mLogSources.add(logSource);
        Selector mSelector = this.mSelector;
        Intrinsics.checkNotNullExpressionValue(mSelector, "mSelector");
        logSource.register(mSelector);
    }

    public final Observable<Integer> getConnectedClientsObservable() {
        Observable<Integer> observableAsObservable = this.mClientCountSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "mClientCountSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<DaemonState> getStatusObservable() {
        Observable<DaemonState> observableAsObservable = this.mStatus.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "mStatus.asObservable()");
        return observableAsObservable;
    }

    /* JADX INFO: compiled from: DltDaemon.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/neusoft/na/dltdroid/service/DltDaemon$Companion;", "", "()V", "LOGTAG", "", "getLOGTAG", "()Ljava/lang/String;", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getLOGTAG() {
            return DltDaemon.LOGTAG;
        }
    }
}
