package com.neusoft.na.nodejs;

import android.util.Log;
import com.neusoft.na.nodejs.NodeJs;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public class NodeInstance {
    public static final int SIGHUP = 1;
    public static final int SIGINT = 2;
    public static final int SIGKILL = 9;
    public static final int SIGTERM = 15;
    private static final AtomicInteger mInstanceCount;
    private static Map<Integer, String> sSignalNames;
    private final JsContext mContext;
    private final Thread mMainThread;
    private long mNativePeer;
    private final NodeJs.Impl mOwner;
    private final Map<String, JsCallback> mCallbackMap = new HashMap();
    private final AtomicReference<Integer> mExitCode = new AtomicReference<>(null);
    private final Queue<JsContextRunnable> mScheduled = new LinkedList();

    @Retention(RetentionPolicy.SOURCE)
    public @interface Signal {
    }

    private native long createNativePeer(String[] strArr, String str, boolean z, boolean z2, boolean z3);

    private static native void destroyNativePeer(long j);

    private static native String exec(long j, String str);

    private static native void kill(long j);

    private static native void notifyQueue(long j);

    private native int run(long j);

    static {
        HashMap map = new HashMap();
        sSignalNames = map;
        map.put(1, "SIGHUP");
        sSignalNames.put(2, "SIGINT");
        sSignalNames.put(9, "SIGKILL");
        sSignalNames.put(15, "SIGTERM");
        mInstanceCount = new AtomicInteger(0);
    }

    protected NodeInstance(NodeJs.Impl impl, InstanceOptions instanceOptions, JsContextRunnable jsContextRunnable) {
        this.mOwner = impl;
        final String str = instanceOptions.logtag != null ? instanceOptions.logtag : "owner-mobile[" + mInstanceCount.incrementAndGet() + "]";
        this.mNativePeer = createNativePeer((String[]) instanceOptions.args.toArray(new String[0]), str, instanceOptions.interactive, instanceOptions.captureConsole, instanceOptions.runFromArgs);
        this.mContext = new JsContext(this);
        if (jsContextRunnable != null) {
            sync(jsContextRunnable);
        }
        Thread thread = new Thread(new Runnable() { // from class: com.neusoft.na.nodejs.NodeInstance$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m193lambda$new$0$comneusoftnanodejsNodeInstance(str);
            }
        }, str);
        this.mMainThread = thread;
        thread.start();
    }

    /* JADX INFO: renamed from: lambda$new$0$com-neusoft-na-nodejs-NodeInstance, reason: not valid java name */
    /* synthetic */ void m193lambda$new$0$comneusoftnanodejsNodeInstance(String str) {
        Thread.currentThread().setName(str);
        this.mExitCode.set(Integer.valueOf(run(this.mNativePeer)));
        synchronized (this) {
            destroyNativePeer(this.mNativePeer);
            this.mNativePeer = 0L;
        }
        this.mOwner.removeInstance(this);
    }

    public void sync(JsContextRunnable jsContextRunnable) {
        if (Thread.currentThread() == this.mMainThread) {
            jsContextRunnable.apply(this.mContext);
            return;
        }
        synchronized (this.mScheduled) {
            this.mScheduled.add(jsContextRunnable);
        }
        long j = this.mNativePeer;
        if (j != 0) {
            notifyQueue(j);
        }
    }

    protected Object exec(String str) {
        long j = this.mNativePeer;
        if (j != 0) {
            try {
                return Json.parse(exec(j, str));
            } catch (JSONException unused) {
                Log.w("NodeInstance", "failed to parse JSON");
            }
        }
        return null;
    }

    protected void set(String str, JsCallback jsCallback) {
        synchronized (this.mCallbackMap) {
            if (jsCallback == null) {
                this.mCallbackMap.remove(str);
                exec("delete " + str + ";");
            } else {
                this.mCallbackMap.put(str, jsCallback);
                exec(str + "=function(...a){return JSON.parse(_njsm.sendMessage(\"" + str + "\", JSON.stringify(a)));}");
            }
        }
    }

    public void sendSignal(final int i) {
        if (9 == i) {
            kill();
            return;
        }
        final String str = sSignalNames.get(Integer.valueOf(i));
        if (str == null) {
            throw new RuntimeException("unsupported signal: " + i);
        }
        sync(new JsContextRunnable() { // from class: com.neusoft.na.nodejs.NodeInstance$$ExternalSyntheticLambda0
            @Override // com.neusoft.na.nodejs.JsContextRunnable
            public final void apply(JsContext jsContext) {
                this.f$0.m194lambda$sendSignal$1$comneusoftnanodejsNodeInstance(str, i, jsContext);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$sendSignal$1$com-neusoft-na-nodejs-NodeInstance, reason: not valid java name */
    /* synthetic */ void m194lambda$sendSignal$1$comneusoftnanodejsNodeInstance(String str, int i, JsContext jsContext) {
        long j = this.mNativePeer;
        if (j != 0) {
            exec(j, "if (!process.emit('" + str + "')) { process.exit(" + (i + 128) + "); };");
        }
    }

    public synchronized void kill() {
        sync(new JsContextRunnable() { // from class: com.neusoft.na.nodejs.NodeInstance$$ExternalSyntheticLambda1
            @Override // com.neusoft.na.nodejs.JsContextRunnable
            public final void apply(JsContext jsContext) {
                this.f$0.m192lambda$kill$2$comneusoftnanodejsNodeInstance(jsContext);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$kill$2$com-neusoft-na-nodejs-NodeInstance, reason: not valid java name */
    /* synthetic */ void m192lambda$kill$2$comneusoftnanodejsNodeInstance(JsContext jsContext) {
        long j = this.mNativePeer;
        if (j != 0) {
            kill(j);
        }
    }

    public void terminate() {
        sendSignal(15);
    }

    public Integer getExitCode() {
        return this.mExitCode.get();
    }

    public int join() throws InterruptedException {
        Thread threadCurrentThread = Thread.currentThread();
        Thread thread = this.mMainThread;
        if (threadCurrentThread == thread) {
            throw new RuntimeException("Must not be called from the nodejs main thread.");
        }
        thread.join();
        return this.mExitCode.get().intValue();
    }

    public Integer join(long j) throws InterruptedException {
        Thread threadCurrentThread = Thread.currentThread();
        Thread thread = this.mMainThread;
        if (threadCurrentThread == thread) {
            throw new RuntimeException("Must not be called from the nodejs main thread.");
        }
        thread.join(j);
        return this.mExitCode.get();
    }

    private String runCallback(String str, String str2) {
        JsCallback jsCallback;
        synchronized (this.mCallbackMap) {
            jsCallback = this.mCallbackMap.get(str);
        }
        if (jsCallback == null) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str2);
            int length = jSONArray.length();
            Object[] objArr = new Object[length];
            for (int i = 0; i < length; i++) {
                objArr[i] = jSONArray.get(i);
            }
            return Json.toString(jsCallback.apply(objArr));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void processScheduled() {
        JsContextRunnable jsContextRunnableRemove;
        synchronized (this.mScheduled) {
            jsContextRunnableRemove = this.mScheduled.remove();
        }
        if (jsContextRunnableRemove != null) {
            jsContextRunnableRemove.apply(this.mContext);
        }
    }
}
