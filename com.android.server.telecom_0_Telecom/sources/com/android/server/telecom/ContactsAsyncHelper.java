package com.android.server.telecom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.telecom.Log;
import java.io.FileNotFoundException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class ContactsAsyncHelper {
    private static final String LOG_TAG = "ContactsAsyncHelper";
    private final ContentResolverAdapter mContentResolverAdapter;
    private Handler mThreadHandler;

    public interface ContentResolverAdapter {
        InputStream openInputStream(Context context, Uri uri) throws FileNotFoundException;
    }

    public interface OnImageLoadCompleteListener {
        void onImageLoadComplete(int i, Drawable drawable, Bitmap bitmap, Object obj);
    }

    public static class Factory {
        public ContactsAsyncHelper create(ContentResolverAdapter contentResolverAdapter) {
            return new ContactsAsyncHelper(contentResolverAdapter);
        }
    }

    public ContactsAsyncHelper(ContentResolverAdapter contentResolverAdapter) {
        this.mContentResolverAdapter = contentResolverAdapter;
    }

    private static final class WorkerArgs {
        public Context context;
        public Object cookie;
        public Uri displayPhotoUri;
        public OnImageLoadCompleteListener listener;
        public Drawable photo;
        public Bitmap photoIcon;

        private WorkerArgs() {
        }
    }

    private class WorkerHandler extends Handler {
        public WorkerHandler(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x00e7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r9) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 242
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.ContactsAsyncHelper.WorkerHandler.handleMessage(android.os.Message):void");
        }

        private Bitmap getPhotoIconWhenAppropriate(Context context, Drawable drawable) {
            if (!(drawable instanceof BitmapDrawable)) {
                return null;
            }
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.notification_icon_size);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i = width > height ? width : height;
            if (i <= dimensionPixelSize) {
                return bitmap;
            }
            float f = i / dimensionPixelSize;
            int i2 = (int) (width / f);
            int i3 = (int) (height / f);
            if (i2 <= 0 || i3 <= 0) {
                Log.w(this, "Photo icon's width or height become 0.", new Object[0]);
                return null;
            }
            return Bitmap.createScaledBitmap(bitmap, i2, i3, true);
        }
    }

    public void startObtainPhotoAsync(int i, Context context, Uri uri, OnImageLoadCompleteListener onImageLoadCompleteListener, Object obj) {
        ensureAsyncHandlerStarted();
        if (uri == null) {
            Log.wtf(LOG_TAG, "Uri is missing", new Object[0]);
            return;
        }
        WorkerArgs workerArgs = new WorkerArgs();
        workerArgs.cookie = obj;
        workerArgs.context = context;
        workerArgs.displayPhotoUri = uri;
        workerArgs.listener = onImageLoadCompleteListener;
        Message messageObtainMessage = this.mThreadHandler.obtainMessage(i);
        messageObtainMessage.arg1 = 1;
        messageObtainMessage.obj = workerArgs;
        Log.d(LOG_TAG, "Begin loading image: " + workerArgs.displayPhotoUri + ", displaying default image for now.", new Object[0]);
        this.mThreadHandler.sendMessage(messageObtainMessage);
    }

    private void ensureAsyncHandlerStarted() {
        if (this.mThreadHandler == null) {
            HandlerThread handlerThread = new HandlerThread("ContactsAsyncWorker");
            handlerThread.start();
            this.mThreadHandler = new WorkerHandler(handlerThread.getLooper());
        }
    }
}
