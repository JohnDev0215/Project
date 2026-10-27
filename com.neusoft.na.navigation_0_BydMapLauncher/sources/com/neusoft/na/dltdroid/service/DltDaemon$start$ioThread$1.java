package com.neusoft.na.dltdroid.service;

import android.util.Log;
import com.neusoft.na.dltdroid.service.DltDaemon;
import java.io.IOException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.FuncN;

/* JADX INFO: compiled from: DltDaemon.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u000b\u001a\u00020\u0004H\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\u0006\u0010\u000e\u001a\u00020\rJ\b\u0010\u000f\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"com/neusoft/na/dltdroid/service/DltDaemon$start$ioThread$1", "Ljava/lang/Runnable;", "Lcom/neusoft/na/dltdroid/service/DltDaemon$IServerThread;", "mRunning", "", "mSubscriptions", "Ljava/util/HashSet;", "Lrx/Subscription;", "Lkotlin/collections/HashSet;", "mThread", "Ljava/lang/Thread;", "isRunning", "run", "", "start", "stop", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class DltDaemon$start$ioThread$1 implements Runnable, DltDaemon.IServerThread {
    private boolean mRunning;
    private final HashSet<Subscription> mSubscriptions = new HashSet<>();
    private Thread mThread;
    final /* synthetic */ DltDaemon this$0;

    DltDaemon$start$ioThread$1(DltDaemon dltDaemon) {
        this.this$0 = dltDaemon;
        Thread thread = new Thread(this);
        this.mThread = thread;
        thread.setName("DltDaemonIoThread");
    }

    @Override // java.lang.Runnable
    public void run() {
        HashSet<Subscription> hashSet = this.mSubscriptions;
        ArrayList arrayList = this.this$0.mLogSources;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((ILogSource) it.next()).getClientCountObservable());
        }
        Observable observableCombineLatest = Observable.combineLatest((List) arrayList2, new FuncN() { // from class: com.neusoft.na.dltdroid.service.DltDaemon$start$ioThread$1$$ExternalSyntheticLambda0
            @Override // rx.functions.FuncN
            public final Object call(Object[] objArr) {
                return DltDaemon$start$ioThread$1.run$lambda$2(objArr);
            }
        });
        final DltDaemon dltDaemon = this.this$0;
        final Function1<Integer, Unit> function1 = new Function1<Integer, Unit>() { // from class: com.neusoft.na.dltdroid.service.DltDaemon$start$ioThread$1$run$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke2(num);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Integer num) {
                dltDaemon.mClientCountSubject.onNext(num);
            }
        };
        hashSet.add(observableCombineLatest.subscribe(new Action1() { // from class: com.neusoft.na.dltdroid.service.DltDaemon$start$ioThread$1$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DltDaemon$start$ioThread$1.run$lambda$3(function1, obj);
            }
        }));
        this.this$0.mStatus.onNext(DaemonState.RUNNING);
        Iterator it2 = this.this$0.mLogSinks.iterator();
        while (it2.hasNext()) {
            ((ILogSink) it2.next()).start();
        }
        ArrayList<ILogSource> arrayList3 = this.this$0.mLogSources;
        final DltDaemon dltDaemon2 = this.this$0;
        for (ILogSource iLogSource : arrayList3) {
            HashSet<Subscription> hashSet2 = this.mSubscriptions;
            Observable<DltMessage> messageObservable = iLogSource.getMessageObservable();
            final Function1<DltMessage, Unit> function12 = new Function1<DltMessage, Unit>() { // from class: com.neusoft.na.dltdroid.service.DltDaemon$start$ioThread$1$run$5$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DltMessage dltMessage) {
                    invoke2(dltMessage);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DltMessage msg) {
                    Long lValueOf = Long.valueOf(dltDaemon2.getBytesLogged());
                    DltDaemon dltDaemon3 = dltDaemon2;
                    synchronized (lValueOf) {
                        dltDaemon3.bytesLogged = dltDaemon3.getBytesLogged() + ((long) msg.getLength());
                        Unit unit = Unit.INSTANCE;
                    }
                    Iterator it3 = dltDaemon2.mLogSinks.iterator();
                    Intrinsics.checkNotNullExpressionValue(it3, "mLogSinks.iterator()");
                    while (it3.hasNext()) {
                        Object next = it3.next();
                        Intrinsics.checkNotNullExpressionValue(next, "logSinksIter.next()");
                        ILogSink iLogSink = (ILogSink) next;
                        try {
                            Intrinsics.checkNotNullExpressionValue(msg, "msg");
                            iLogSink.logMessage(msg);
                            if (!dltDaemon2.crashFlag) {
                                dltDaemon2.crashFlag = true;
                            }
                        } catch (Throwable th) {
                            if (dltDaemon2.crashFlag) {
                                dltDaemon2.crashFlag = false;
                                Log.w(DltDaemon.INSTANCE.getLOGTAG(), "failed to log message", th);
                            }
                        }
                    }
                }
            };
            hashSet2.add(messageObservable.subscribe(new Action1() { // from class: com.neusoft.na.dltdroid.service.DltDaemon$start$ioThread$1$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    DltDaemon$start$ioThread$1.run$lambda$6$lambda$5(function12, obj);
                }
            }));
            iLogSource.start();
        }
        while (this.mRunning) {
            try {
                this.this$0.mSelector.select();
                try {
                    Iterator<SelectionKey> it3 = this.this$0.mSelector.selectedKeys().iterator();
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
        Iterator it4 = this.this$0.mLogSources.iterator();
        while (it4.hasNext()) {
            ((ILogSource) it4.next()).stop();
        }
        Iterator it5 = this.this$0.mLogSinks.iterator();
        while (it5.hasNext()) {
            ((ILogSink) it5.next()).stop();
        }
        this.mRunning = false;
        Iterator it6 = this.this$0.mLogSources.iterator();
        while (it6.hasNext()) {
            ((ILogSource) it6.next()).stop();
        }
        Iterator it7 = this.this$0.mLogSinks.iterator();
        while (it7.hasNext()) {
            ((ILogSink) it7.next()).stop();
        }
        Iterator<T> it8 = this.mSubscriptions.iterator();
        while (it8.hasNext()) {
            ((Subscription) it8.next()).unsubscribe();
        }
        this.this$0.mClientCountSubject.onNext(0);
        this.this$0.mStatus.onNext(DaemonState.OFF);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer run$lambda$2(Object[] args) {
        Intrinsics.checkNotNullExpressionValue(args, "args");
        int iIntValue = 0;
        for (Object obj : args) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            iIntValue += ((Integer) obj).intValue();
        }
        return Integer.valueOf(iIntValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void run$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void run$lambda$6$lambda$5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void start() {
        this.mRunning = true;
        this.mThread.start();
    }

    @Override // com.neusoft.na.dltdroid.service.DltDaemon.IServerThread
    public void stop() throws InterruptedException {
        if (this.mRunning) {
            this.mRunning = false;
            this.this$0.mSelector.wakeup();
            this.mThread.join();
        }
    }

    @Override // com.neusoft.na.dltdroid.service.DltDaemon.IServerThread
    /* JADX INFO: renamed from: isRunning, reason: from getter */
    public boolean getMRunning() {
        return this.mRunning;
    }
}
