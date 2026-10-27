package android.net.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class VersionedBroadcastListener {
    private final Consumer<Intent> mCallback;
    private final Context mContext;
    private final IntentFilter mFilter;
    private final AtomicInteger mGenerationNumber = new AtomicInteger(0);
    private final Handler mHandler;
    private BroadcastReceiver mReceiver;
    private final String mTag;

    public VersionedBroadcastListener(String str, Context context, Handler handler, IntentFilter intentFilter, Consumer<Intent> consumer) {
        this.mTag = str;
        this.mContext = context;
        this.mHandler = handler;
        this.mFilter = intentFilter;
        this.mCallback = consumer;
    }

    public void startListening() {
        if (this.mReceiver != null) {
            return;
        }
        Receiver receiver = new Receiver(this.mTag, this.mGenerationNumber, this.mCallback);
        this.mReceiver = receiver;
        this.mContext.registerReceiver(receiver, this.mFilter, null, this.mHandler);
    }

    private static class Receiver extends BroadcastReceiver {
        public final AtomicInteger atomicGenerationNumber;
        public final Consumer<Intent> callback;
        public final int generationNumber;

        Receiver(String str, AtomicInteger atomicInteger, Consumer<Intent> consumer) {
            this.atomicGenerationNumber = atomicInteger;
            this.callback = consumer;
            this.generationNumber = atomicInteger.incrementAndGet();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (this.generationNumber != this.atomicGenerationNumber.get()) {
                return;
            }
            this.callback.accept(intent);
        }
    }
}
