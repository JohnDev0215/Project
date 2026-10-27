package com.neusoft.na.nodejs;

import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import com.neusoft.na.nodejs.InstanceOptions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class NodeJs {
    private static Impl globalInstance;

    protected static class Impl {
        private final Set<NodeInstance> mInstances = new HashSet();

        private native boolean deinitImpl();

        private native boolean initImpl(String[] strArr);

        static {
            System.loadLibrary("nodejs-android");
        }

        Impl(GlobalOptions globalOptions, String[] strArr) {
            if (globalOptions != null && globalOptions.nodePath != null) {
                try {
                    Os.setenv("NODE_PATH", globalOptions.nodePath, true);
                } catch (ErrnoException unused) {
                    Log.w("nodejs", "Failed to set up environment variables.");
                }
            }
            String[] strArr2 = new String[strArr.length + 1];
            strArr2[0] = "node";
            System.arraycopy(strArr, 0, strArr2, 1, strArr.length);
            if (!initImpl(strArr2)) {
                throw new RuntimeException("failed to initialize nodejs");
            }
        }

        void deinit() {
            ArrayList arrayList = new ArrayList();
            synchronized (this) {
                Log.i("NodeJs", "killing all instances");
                for (NodeInstance nodeInstance : this.mInstances) {
                    nodeInstance.kill();
                    arrayList.add(nodeInstance);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                try {
                    ((NodeInstance) it.next()).join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!deinitImpl()) {
                throw new RuntimeException("failed to deinitialize nodejs");
            }
        }

        synchronized NodeInstance launch(InstanceOptions instanceOptions, JsContextRunnable jsContextRunnable) {
            NodeInstance nodeInstance;
            if (instanceOptions == null) {
                instanceOptions = new InstanceOptions.Builder().build();
            }
            nodeInstance = new NodeInstance(this, instanceOptions, jsContextRunnable);
            this.mInstances.add(nodeInstance);
            return nodeInstance;
        }

        synchronized void removeInstance(NodeInstance nodeInstance) {
            this.mInstances.remove(nodeInstance);
        }

        protected void finalize() throws Throwable {
            deinit();
            super.finalize();
        }
    }

    public static synchronized void init(String... strArr) {
        init(null, strArr);
    }

    public static synchronized void init(GlobalOptions globalOptions, String... strArr) {
        if (globalInstance == null) {
            globalInstance = new Impl(globalOptions, strArr);
        } else {
            throw new RuntimeException("NodeJs already initialized in this process.");
        }
    }

    public static synchronized void deinit() {
        Impl impl = globalInstance;
        if (impl != null) {
            impl.deinit();
            globalInstance = null;
        } else {
            throw new RuntimeException("NodeJs not initialized in this process.");
        }
    }

    public static synchronized NodeInstance launch(InstanceOptions instanceOptions, JsContextRunnable jsContextRunnable) {
        Impl impl;
        impl = globalInstance;
        if (impl != null) {
        } else {
            throw new RuntimeException("NodeJs not initialized in this process.");
        }
        return impl.launch(instanceOptions, jsContextRunnable);
    }

    public static NodeInstance launch(InstanceOptions instanceOptions) {
        return launch(instanceOptions, null);
    }

    public static NodeInstance launch(JsContextRunnable jsContextRunnable) {
        return launch(null, jsContextRunnable);
    }

    public static NodeInstance launch() {
        return launch(null, null);
    }
}
