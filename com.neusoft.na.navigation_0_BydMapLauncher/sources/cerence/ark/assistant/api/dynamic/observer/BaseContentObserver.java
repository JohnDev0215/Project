package cerence.ark.assistant.api.dynamic.observer;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseContentObserver extends BaseObserver {
    private static final long DEFAULT_DELAY_TIME = 3000;
    private static final int FLAG_DCC_CHANGED = 20;
    private static final String TAG = "BaseContentObserver";
    private final String mContentType;
    protected Handler mHandler;
    protected ContentObserver mObserver;

    protected long getDelayTime() {
        return DEFAULT_DELAY_TIME;
    }

    protected abstract Uri getUri();

    protected BaseContentObserver(String str) {
        this.mContentType = str;
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        Uri uri = getUri();
        if (uri == null) {
            Logger.error(TAG, "uri is null");
            return;
        }
        Logger.info(TAG, "activate: " + this.mContentType);
        onContentChanged(this.mContentType);
        HandlerThread handlerThread = new HandlerThread(Utils.format("ca_%s_observer", this.mContentType));
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: cerence.ark.assistant.api.dynamic.observer.BaseContentObserver.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 20) {
                    BaseContentObserver baseContentObserver = BaseContentObserver.this;
                    baseContentObserver.onContentChanged(baseContentObserver.mContentType);
                }
            }
        };
        this.mObserver = new ContentObserver(null) { // from class: cerence.ark.assistant.api.dynamic.observer.BaseContentObserver.2
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                BaseContentObserver.this.mHandler.removeMessages(20);
                BaseContentObserver.this.mHandler.sendEmptyMessageDelayed(20, BaseContentObserver.this.getDelayTime());
            }
        };
        Utils.getContext().getContentResolver().registerContentObserver(uri, false, this.mObserver);
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
        if (this.mObserver != null) {
            Utils.getContext().getContentResolver().unregisterContentObserver(this.mObserver);
        }
        if (this.mHandler != null) {
            Logger.info(TAG, Utils.format("unregisterObserver: destroy %s observer thread", this.mContentType));
            this.mHandler.getLooper().quit();
        }
    }
}
