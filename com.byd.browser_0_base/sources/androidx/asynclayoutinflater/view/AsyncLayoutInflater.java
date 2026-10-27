package androidx.asynclayoutinflater.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public final class AsyncLayoutInflater {

    public class a implements Handler.Callback {
        public a(AsyncLayoutInflater asyncLayoutInflater) {
        }

        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            ((c) message.obj).getClass();
            throw null;
        }
    }

    public static class b extends LayoutInflater {
        public static final String[] a = {"android.widget.", "android.webkit.", "android.app."};

        public b(Context context) {
            super(context);
        }

        @Override // android.view.LayoutInflater
        public final LayoutInflater cloneInContext(Context context) {
            return new b(context);
        }

        @Override // android.view.LayoutInflater
        public final View onCreateView(String str, AttributeSet attributeSet) {
            View viewCreateView;
            String[] strArr = a;
            for (int i = 0; i < 3; i++) {
                try {
                    viewCreateView = createView(str, strArr[i], attributeSet);
                } catch (ClassNotFoundException unused) {
                }
                if (viewCreateView != null) {
                    return viewCreateView;
                }
            }
            return super.onCreateView(str, attributeSet);
        }
    }

    public static class c {
    }

    public static class d extends Thread {
        public static final d f;
        public final ArrayBlockingQueue<c> e = new ArrayBlockingQueue<>(10);

        static {
            d dVar = new d();
            f = dVar;
            dVar.setName("AsyncLayoutInflator");
            dVar.start();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            while (true) {
                try {
                    c cVarTake = this.e.take();
                    try {
                        cVarTake.getClass();
                        throw null;
                    } catch (RuntimeException e) {
                        Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", e);
                        cVarTake.getClass();
                        Message.obtain(null, 0, cVarTake).sendToTarget();
                    }
                } catch (InterruptedException e2) {
                    Log.w("AsyncLayoutInflater", e2);
                }
            }
        }
    }

    public AsyncLayoutInflater(Context context) {
        a aVar = new a(this);
        new b(context);
        new Handler(Looper.myLooper(), aVar);
        d dVar = d.f;
    }
}
