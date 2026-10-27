package com.blankj.utilcode.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class UiMessageUtils implements Handler.Callback {
    private static final boolean DEBUG = AppUtils.isAppDebug();
    private static final String TAG = "UiMessageUtils";
    private final List<UiMessageCallback> mDefensiveCopyList;
    private final Handler mHandler;
    private final SparseArray<List<UiMessageCallback>> mListenersSpecific;
    private final List<UiMessageCallback> mListenersUniversal;
    private final UiMessage mMessage;

    public static final class UiMessage {
        private Message mMessage;

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessage(Message message) {
            this.mMessage = message;
        }

        public int getId() {
            return this.mMessage.what;
        }

        public Object getObject() {
            return this.mMessage.obj;
        }

        public String toString() {
            StringBuilder sbK = spi.c.k("{ id=");
            sbK.append(getId());
            sbK.append(", obj=");
            sbK.append(getObject());
            sbK.append(" }");
            return sbK.toString();
        }

        private UiMessage(Message message) {
            this.mMessage = message;
        }
    }

    public interface UiMessageCallback {
        void handleMessage(@NonNull UiMessage uiMessage);
    }

    public static final class b {
        public static final UiMessageUtils a = new UiMessageUtils();

        private b() {
        }
    }

    public static UiMessageUtils getInstance() {
        return b.a;
    }

    private void logMessageHandling(@NonNull UiMessage uiMessage) {
        List<UiMessageCallback> list = this.mListenersSpecific.get(uiMessage.getId());
        if ((list == null || list.size() == 0) && this.mListenersUniversal.size() == 0) {
            StringBuilder sbK = spi.c.k("Delivering FAILED for message ID ");
            sbK.append(uiMessage.getId());
            sbK.append(". No listeners. ");
            sbK.append(uiMessage.toString());
            Log.w(TAG, sbK.toString());
            return;
        }
        StringBuilder sbK2 = spi.c.k("Delivering message ID ");
        sbK2.append(uiMessage.getId());
        sbK2.append(", Specific listeners: ");
        if (list == null || list.size() == 0) {
            sbK2.append(0);
        } else {
            sbK2.append(list.size());
            sbK2.append(" [");
            for (int i = 0; i < list.size(); i++) {
                sbK2.append(list.get(i).getClass().getSimpleName());
                if (i < list.size() - 1) {
                    sbK2.append(",");
                }
            }
            sbK2.append("]");
        }
        sbK2.append(", Universal listeners: ");
        synchronized (this.mListenersUniversal) {
            if (this.mListenersUniversal.size() == 0) {
                sbK2.append(0);
            } else {
                sbK2.append(this.mListenersUniversal.size());
                sbK2.append(" [");
                for (int i2 = 0; i2 < this.mListenersUniversal.size(); i2++) {
                    sbK2.append(this.mListenersUniversal.get(i2).getClass().getSimpleName());
                    if (i2 < this.mListenersUniversal.size() - 1) {
                        sbK2.append(",");
                    }
                }
                sbK2.append("], Message: ");
            }
        }
        sbK2.append(uiMessage.toString());
        Log.v(TAG, sbK2.toString());
    }

    public void addListener(int i, @NonNull UiMessageCallback uiMessageCallback) {
        synchronized (this.mListenersSpecific) {
            List<UiMessageCallback> arrayList = this.mListenersSpecific.get(i);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.mListenersSpecific.put(i, arrayList);
            }
            if (!arrayList.contains(uiMessageCallback)) {
                arrayList.add(uiMessageCallback);
            }
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        this.mMessage.setMessage(message);
        if (DEBUG) {
            logMessageHandling(this.mMessage);
        }
        synchronized (this.mListenersSpecific) {
            List<UiMessageCallback> list = this.mListenersSpecific.get(message.what);
            if (list != null) {
                if (list.size() == 0) {
                    this.mListenersSpecific.remove(message.what);
                } else {
                    this.mDefensiveCopyList.addAll(list);
                    Iterator<UiMessageCallback> it = this.mDefensiveCopyList.iterator();
                    while (it.hasNext()) {
                        it.next().handleMessage(this.mMessage);
                    }
                    this.mDefensiveCopyList.clear();
                }
            }
        }
        synchronized (this.mListenersUniversal) {
            if (this.mListenersUniversal.size() > 0) {
                this.mDefensiveCopyList.addAll(this.mListenersUniversal);
                Iterator<UiMessageCallback> it2 = this.mDefensiveCopyList.iterator();
                while (it2.hasNext()) {
                    it2.next().handleMessage(this.mMessage);
                }
                this.mDefensiveCopyList.clear();
            }
        }
        this.mMessage.setMessage(null);
        return true;
    }

    public void removeListener(@NonNull UiMessageCallback uiMessageCallback) {
        synchronized (this.mListenersUniversal) {
            if (DEBUG && !this.mListenersUniversal.contains(uiMessageCallback)) {
                Log.w(TAG, "Trying to remove a listener that is not registered. " + uiMessageCallback.toString());
            }
            this.mListenersUniversal.remove(uiMessageCallback);
        }
    }

    public void removeListeners(int i) {
        List<UiMessageCallback> list;
        if (DEBUG && ((list = this.mListenersSpecific.get(i)) == null || list.size() == 0)) {
            spi.c.A("Trying to remove specific listeners that are not registered. ID ", i, TAG);
        }
        synchronized (this.mListenersSpecific) {
            this.mListenersSpecific.delete(i);
        }
    }

    public final void send(int i) {
        this.mHandler.sendEmptyMessage(i);
    }

    private UiMessageUtils() {
        this.mHandler = new Handler(Looper.getMainLooper(), this);
        this.mMessage = new UiMessage(null);
        this.mListenersSpecific = new SparseArray<>();
        this.mListenersUniversal = new ArrayList();
        this.mDefensiveCopyList = new ArrayList();
    }

    public final void send(int i, @NonNull Object obj) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(i, obj));
    }

    public void removeListener(int i, @NonNull UiMessageCallback uiMessageCallback) {
        synchronized (this.mListenersSpecific) {
            List<UiMessageCallback> list = this.mListenersSpecific.get(i);
            if (list != null && !list.isEmpty()) {
                if (DEBUG && !list.contains(uiMessageCallback)) {
                    Log.w(TAG, "Trying to remove specific listener that is not registered. ID " + i + ", " + uiMessageCallback);
                    return;
                }
                list.remove(uiMessageCallback);
            } else if (DEBUG) {
                Log.w(TAG, "Trying to remove specific listener that is not registered. ID " + i + ", " + uiMessageCallback);
            }
        }
    }

    public void addListener(@NonNull UiMessageCallback uiMessageCallback) {
        synchronized (this.mListenersUniversal) {
            if (!this.mListenersUniversal.contains(uiMessageCallback)) {
                this.mListenersUniversal.add(uiMessageCallback);
            } else if (DEBUG) {
                Log.w(TAG, "Listener is already added. " + uiMessageCallback.toString());
            }
        }
    }
}
