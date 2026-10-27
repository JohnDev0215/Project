package com.alibaba.android.arouter.core;

import android.content.Context;
import com.alibaba.android.arouter.exception.HandlerException;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.service.InterceptorService;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.thread.CancelableCountDownLatch;
import com.alibaba.android.arouter.utils.MapUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import spi.zc;

/* JADX INFO: loaded from: classes.dex */
@Route(path = "/arouter/service/interceptor")
public class InterceptorServiceImpl implements InterceptorService {
    private static boolean interceptorHasInit;
    private static final Object interceptorInitLock = new Object();

    public class a implements Runnable {
        public final /* synthetic */ Postcard a;
        public final /* synthetic */ InterceptorCallback b;

        public a(InterceptorServiceImpl interceptorServiceImpl, Postcard postcard, InterceptorCallback interceptorCallback) {
            this.a = postcard;
            this.b = interceptorCallback;
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [java.util.ArrayList, java.util.List<com.alibaba.android.arouter.facade.template.IInterceptor>] */
        @Override // java.lang.Runnable
        public final void run() {
            CancelableCountDownLatch cancelableCountDownLatch = new CancelableCountDownLatch(zc.f.size());
            try {
                InterceptorServiceImpl._execute(0, cancelableCountDownLatch, this.a);
                cancelableCountDownLatch.await(this.a.getTimeout(), TimeUnit.SECONDS);
                if (cancelableCountDownLatch.getCount() > 0) {
                    this.b.onInterrupt(new HandlerException("The interceptor processing timed out."));
                } else if (this.a.getTag() != null) {
                    this.b.onInterrupt((Throwable) this.a.getTag());
                } else {
                    this.b.onContinue(this.a);
                }
            } catch (Exception e) {
                this.b.onInterrupt(e);
            }
        }
    }

    public static class b implements InterceptorCallback {
        public final /* synthetic */ CancelableCountDownLatch a;
        public final /* synthetic */ int b;
        public final /* synthetic */ Postcard c;

        public b(CancelableCountDownLatch cancelableCountDownLatch, int i, Postcard postcard) {
            this.a = cancelableCountDownLatch;
            this.b = i;
            this.c = postcard;
        }

        @Override // com.alibaba.android.arouter.facade.callback.InterceptorCallback
        public final void onContinue(Postcard postcard) {
            this.a.countDown();
            InterceptorServiceImpl._execute(this.b + 1, this.a, postcard);
        }

        @Override // com.alibaba.android.arouter.facade.callback.InterceptorCallback
        public final void onInterrupt(Throwable th) {
            Postcard postcard = this.c;
            if (th == null) {
                th = new HandlerException("No message.");
            }
            postcard.setTag(th);
            this.a.cancel();
        }
    }

    public class c implements Runnable {
        public final /* synthetic */ Context a;

        public c(InterceptorServiceImpl interceptorServiceImpl, Context context) {
            this.a = context;
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Map<java.lang.Integer, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IInterceptor>>, java.util.TreeMap] */
        /* JADX WARN: Type inference failed for: r3v3, types: [java.util.ArrayList, java.util.List<com.alibaba.android.arouter.facade.template.IInterceptor>] */
        @Override // java.lang.Runnable
        public final void run() {
            if (MapUtils.isNotEmpty(zc.e)) {
                Iterator it = zc.e.entrySet().iterator();
                while (it.hasNext()) {
                    Class cls = (Class) ((Map.Entry) it.next()).getValue();
                    try {
                        IInterceptor iInterceptor = (IInterceptor) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
                        iInterceptor.init(this.a);
                        zc.f.add(iInterceptor);
                    } catch (Exception e) {
                        StringBuilder sbK = spi.c.k("ARouter::ARouter init interceptor error! name = [");
                        sbK.append(cls.getName());
                        sbK.append("], reason = [");
                        sbK.append(e.getMessage());
                        sbK.append("]");
                        throw new HandlerException(sbK.toString());
                    }
                }
                boolean unused = InterceptorServiceImpl.interceptorHasInit = true;
                ARouter.logger.info("ARouter::", "ARouter interceptors init over.");
                synchronized (InterceptorServiceImpl.interceptorInitLock) {
                    InterceptorServiceImpl.interceptorInitLock.notifyAll();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<com.alibaba.android.arouter.facade.template.IInterceptor>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList, java.util.List<com.alibaba.android.arouter.facade.template.IInterceptor>] */
    public static void _execute(int i, CancelableCountDownLatch cancelableCountDownLatch, Postcard postcard) {
        if (i < zc.f.size()) {
            ((IInterceptor) zc.f.get(i)).process(postcard, new b(cancelableCountDownLatch, i, postcard));
        }
    }

    private static void checkInterceptorsInitStatus() {
        synchronized (interceptorInitLock) {
            while (!interceptorHasInit) {
                try {
                    interceptorInitLock.wait(10000L);
                } catch (InterruptedException e) {
                    throw new HandlerException("ARouter::Interceptor init cost too much time error! reason = [" + e.getMessage() + "]");
                }
            }
        }
    }

    @Override // com.alibaba.android.arouter.facade.service.InterceptorService
    public void doInterceptions(Postcard postcard, InterceptorCallback interceptorCallback) {
        if (!MapUtils.isNotEmpty(zc.e)) {
            interceptorCallback.onContinue(postcard);
            return;
        }
        checkInterceptorsInitStatus();
        if (interceptorHasInit) {
            LogisticsCenter.executor.execute(new a(this, postcard, interceptorCallback));
        } else {
            interceptorCallback.onInterrupt(new HandlerException("Interceptors initialization takes too much time."));
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        LogisticsCenter.executor.execute(new c(this, context));
    }
}
