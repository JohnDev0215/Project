package com.amazon.digitalmusicplayback.internal;

import android.util.Log;
import com.amazon.digitalmusicplayback.HttpClient;
import com.amazon.digitalmusicplayback.HttpClientFactory;
import com.amazon.digitalmusicplayback.HttpMethod;
import com.amazon.digitalmusicplayback.HttpRequest;
import com.amazon.digitalmusicplayback.HttpResponseHandlerWrapper;
import com.amazon.digitalmusicplayback.internal.LoggingFacade;
import com.google.firebase.perf.FirebasePerformance;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class HttpClientFactoryImpl extends HttpClientFactory {
    private LoggingFacade.Logger mLogger;

    public HttpClientFactoryImpl(LoggingFacade loggingFacade) {
        this.mLogger = loggingFacade.currentSourceFileLog();
    }

    @Override // com.amazon.digitalmusicplayback.HttpClientFactory
    public HttpClient createClient() {
        return new HttpUrlConnectionClient();
    }

    private class HttpUrlConnectionClient extends HttpClient {
        private Set<String> mCancelledRequests;
        private ThreadPoolExecutor mExecutor;
        private final AtomicBoolean mIsShutDown = new AtomicBoolean(false);
        private Map<String, Future> mPendingRequests;
        private int mTimeout;

        private class HttpRejectedExecutionHandler implements RejectedExecutionHandler {
            private HttpRejectedExecutionHandler() {
            }

            /* synthetic */ HttpRejectedExecutionHandler(HttpUrlConnectionClient httpUrlConnectionClient, AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                HttpClientFactoryImpl.this.mLogger.warning("Dropping http request", new Object[0]);
            }
        }

        private class ExtendThreadPoolExecutor extends ThreadPoolExecutor {
            public ExtendThreadPoolExecutor() {
                super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue());
            }

            @Override // java.util.concurrent.ThreadPoolExecutor
            protected void afterExecute(Runnable runnable, Throwable th) {
                super.afterExecute(runnable, th);
                if (th == null && (runnable instanceof Future)) {
                    try {
                        Future future = (Future) runnable;
                        if (future.isDone()) {
                            future.get(1L, TimeUnit.SECONDS);
                        }
                    } catch (Exception e) {
                        th = e.getCause();
                    }
                }
                if (th != null) {
                    HttpClientFactoryImpl.this.mLogger.warning("Uncaught exception from the java network thread: %s\n%s", th.getMessage(), Log.getStackTraceString(th));
                }
            }
        }

        HttpUrlConnectionClient() {
            ExtendThreadPoolExecutor extendThreadPoolExecutor = new ExtendThreadPoolExecutor();
            this.mExecutor = extendThreadPoolExecutor;
            extendThreadPoolExecutor.setRejectedExecutionHandler(new HttpRejectedExecutionHandler(this, null));
            this.mPendingRequests = new HashMap();
            this.mCancelledRequests = new LimitedHashSet();
        }

        @Override // com.amazon.digitalmusicplayback.HttpClient
        public void sendRequest(final HttpRequest httpRequest, final HttpResponseHandlerWrapper httpResponseHandlerWrapper) {
            this.mPendingRequests.put(httpRequest.getId(), this.mExecutor.submit(new Runnable() { // from class: com.amazon.digitalmusicplayback.internal.HttpClientFactoryImpl.HttpUrlConnectionClient.1
                private boolean shouldCancel() {
                    if (!HttpUrlConnectionClient.this.mIsShutDown.get()) {
                        return false;
                    }
                    if (!HttpUrlConnectionClient.this.mCancelledRequests.contains(httpRequest.getId())) {
                        return true;
                    }
                    httpResponseHandlerWrapper.onError(HttpResponseHandlerWrapper.CANCELLED);
                    return true;
                }

                /* JADX WARN: Removed duplicated region for block: B:157:0x02b3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:159:0x01f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:161:0x0227 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:165:0x027e A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:174:0x0294 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:176:0x0211 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:178:0x02c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:190:0x01da A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:208:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:210:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:214:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:216:? A[RETURN, SYNTHETIC] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void run() throws java.lang.Throwable {
                    /*
                        Method dump skipped, instruction units count: 780
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.digitalmusicplayback.internal.HttpClientFactoryImpl.HttpUrlConnectionClient.AnonymousClass1.run():void");
                }
            }));
        }

        @Override // com.amazon.digitalmusicplayback.HttpClient
        public void cancel(String str) {
            this.mCancelledRequests.add(str);
            if (this.mPendingRequests.containsKey(str)) {
                this.mPendingRequests.get(str).cancel(true);
                this.mPendingRequests.remove(str);
            }
        }

        @Override // com.amazon.digitalmusicplayback.HttpClient
        public void setTimeout(int i) {
            this.mTimeout = i;
        }

        @Override // com.amazon.digitalmusicplayback.HttpClient
        public void shutdown(int i) {
            this.mIsShutDown.set(true);
            this.mExecutor.shutdownNow();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String methodDescription(HttpMethod httpMethod) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$digitalmusicplayback$HttpMethod[httpMethod.ordinal()];
            if (i == 1) {
                return FirebasePerformance.HttpMethod.GET;
            }
            if (i != 2) {
                return null;
            }
            return FirebasePerformance.HttpMethod.POST;
        }
    }

    /* JADX INFO: renamed from: com.amazon.digitalmusicplayback.internal.HttpClientFactoryImpl$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$digitalmusicplayback$HttpMethod;

        static {
            int[] iArr = new int[HttpMethod.values().length];
            $SwitchMap$com$amazon$digitalmusicplayback$HttpMethod = iArr;
            try {
                iArr[HttpMethod.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$HttpMethod[HttpMethod.POST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
