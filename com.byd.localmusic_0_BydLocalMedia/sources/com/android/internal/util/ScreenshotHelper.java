package com.android.internal.util;

import android.app.job.JobInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Insets;
import android.graphics.Rect;
import android.media.audio.common.AudioFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import com.android.internal.R;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public class ScreenshotHelper {
    public static final int SCREENSHOT_MSG_PROCESS_COMPLETE = 2;
    public static final int SCREENSHOT_MSG_URI = 1;
    private static final String TAG = "ScreenshotHelper";
    private final Context mContext;
    private final int SCREENSHOT_TIMEOUT_MS = 10000;
    private final Object mScreenshotLock = new Object();
    private IBinder mScreenshotService = null;
    private ServiceConnection mScreenshotConnection = null;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.internal.util.ScreenshotHelper.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            synchronized (ScreenshotHelper.this.mScreenshotLock) {
                if (Intent.ACTION_USER_SWITCHED.equals(intent.getAction())) {
                    ScreenshotHelper.this.resetConnection();
                }
            }
        }
    };

    public static class ScreenshotRequest implements Parcelable {
        public static final Parcelable.Creator<ScreenshotRequest> CREATOR = new Parcelable.Creator<ScreenshotRequest>() { // from class: com.android.internal.util.ScreenshotHelper.ScreenshotRequest.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ScreenshotRequest createFromParcel(Parcel parcel) {
                return new ScreenshotRequest(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ScreenshotRequest[] newArray(int i) {
                return new ScreenshotRequest[i];
            }
        };
        private Bundle mBitmapBundle;
        private Rect mBoundsInScreen;
        private boolean mHasNavBar;
        private boolean mHasStatusBar;
        private Insets mInsets;
        private int mSource;
        private int mTaskId;
        private ComponentName mTopComponent;
        private int mUserId;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        ScreenshotRequest(int i, boolean z, boolean z2) {
            this.mSource = i;
            this.mHasStatusBar = z;
            this.mHasNavBar = z2;
        }

        ScreenshotRequest(int i, Bundle bundle, Rect rect, Insets insets, int i2, int i3, ComponentName componentName) {
            this.mSource = i;
            this.mBitmapBundle = bundle;
            this.mBoundsInScreen = rect;
            this.mInsets = insets;
            this.mTaskId = i2;
            this.mUserId = i3;
            this.mTopComponent = componentName;
        }

        ScreenshotRequest(Parcel parcel) {
            this.mSource = parcel.readInt();
            this.mHasStatusBar = parcel.readBoolean();
            this.mHasNavBar = parcel.readBoolean();
            if (parcel.readInt() == 1) {
                this.mBitmapBundle = parcel.readBundle(getClass().getClassLoader());
                this.mBoundsInScreen = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
                this.mInsets = (Insets) parcel.readParcelable(Insets.class.getClassLoader());
                this.mTaskId = parcel.readInt();
                this.mUserId = parcel.readInt();
                this.mTopComponent = (ComponentName) parcel.readParcelable(ComponentName.class.getClassLoader());
            }
        }

        public int getSource() {
            return this.mSource;
        }

        public boolean getHasStatusBar() {
            return this.mHasStatusBar;
        }

        public boolean getHasNavBar() {
            return this.mHasNavBar;
        }

        public Bundle getBitmapBundle() {
            return this.mBitmapBundle;
        }

        public Rect getBoundsInScreen() {
            return this.mBoundsInScreen;
        }

        public Insets getInsets() {
            return this.mInsets;
        }

        public int getTaskId() {
            return this.mTaskId;
        }

        public int getUserId() {
            return this.mUserId;
        }

        public ComponentName getTopComponent() {
            return this.mTopComponent;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mSource);
            parcel.writeBoolean(this.mHasStatusBar);
            parcel.writeBoolean(this.mHasNavBar);
            if (this.mBitmapBundle == null) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcel.writeBundle(this.mBitmapBundle);
            parcel.writeParcelable(this.mBoundsInScreen, 0);
            parcel.writeParcelable(this.mInsets, 0);
            parcel.writeInt(this.mTaskId);
            parcel.writeInt(this.mUserId);
            parcel.writeParcelable(this.mTopComponent, 0);
        }
    }

    public ScreenshotHelper(Context context) {
        this.mContext = context;
        this.mContext.registerReceiver(this.mBroadcastReceiver, new IntentFilter(Intent.ACTION_USER_SWITCHED));
    }

    public void takeScreenshot(int i, boolean z, boolean z2, int i2, Handler handler, Consumer<Uri> consumer) {
        takeScreenshot(i, JobInfo.MIN_BACKOFF_MILLIS, handler, new ScreenshotRequest(i2, z, z2), consumer);
    }

    public void takeScreenshot(int i, boolean z, boolean z2, Handler handler, Consumer<Uri> consumer) {
        takeScreenshot(i, z, z2, 10000, handler, consumer);
    }

    public void takeScreenshot(int i, boolean z, boolean z2, long j, Handler handler, Consumer<Uri> consumer) {
        takeScreenshot(i, j, handler, new ScreenshotRequest(5, z, z2), consumer);
    }

    public void provideScreenshot(Bundle bundle, Rect rect, Insets insets, int i, int i2, ComponentName componentName, int i3, Handler handler, Consumer<Uri> consumer) {
        takeScreenshot(3, JobInfo.MIN_BACKOFF_MILLIS, handler, new ScreenshotRequest(i3, bundle, rect, insets, i, i2, componentName), consumer);
    }

    private void takeScreenshot(int i, long j, final Handler handler, ScreenshotRequest screenshotRequest, final Consumer<Uri> consumer) {
        synchronized (this.mScreenshotLock) {
            final Runnable runnable = new Runnable() { // from class: com.android.internal.util.-$$Lambda$ScreenshotHelper$r9rp933Jt-vm9lU4BZ-gj9VY0YE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$takeScreenshot$0$ScreenshotHelper(consumer);
                }
            };
            final Message messageObtain = Message.obtain(null, i, screenshotRequest);
            messageObtain.replyTo = new Messenger(new Handler(handler.getLooper()) { // from class: com.android.internal.util.ScreenshotHelper.2
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    int i2 = message.what;
                    if (i2 == 1) {
                        Consumer consumer2 = consumer;
                        if (consumer2 != null) {
                            consumer2.accept((Uri) message.obj);
                        }
                        handler.removeCallbacks(runnable);
                        return;
                    }
                    if (i2 != 2) {
                        return;
                    }
                    synchronized (ScreenshotHelper.this.mScreenshotLock) {
                        ScreenshotHelper.this.resetConnection();
                    }
                }
            });
            if (this.mScreenshotConnection == null || this.mScreenshotService == null) {
                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(this.mContext.getResources().getString(R.string.config_screenshotServiceComponent));
                Intent intent = new Intent();
                intent.setComponent(componentNameUnflattenFromString);
                ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.android.internal.util.ScreenshotHelper.3
                    @Override // android.content.ServiceConnection
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        synchronized (ScreenshotHelper.this.mScreenshotLock) {
                            if (ScreenshotHelper.this.mScreenshotConnection != this) {
                                return;
                            }
                            ScreenshotHelper.this.mScreenshotService = iBinder;
                            try {
                                new Messenger(ScreenshotHelper.this.mScreenshotService).send(messageObtain);
                            } catch (RemoteException e) {
                                Log.e(ScreenshotHelper.TAG, "Couldn't take screenshot: " + e);
                                if (consumer != null) {
                                    consumer.accept(null);
                                }
                            }
                        }
                    }

                    @Override // android.content.ServiceConnection
                    public void onServiceDisconnected(ComponentName componentName) {
                        synchronized (ScreenshotHelper.this.mScreenshotLock) {
                            if (ScreenshotHelper.this.mScreenshotConnection != null) {
                                ScreenshotHelper.this.resetConnection();
                                if (handler.hasCallbacks(runnable)) {
                                    handler.removeCallbacks(runnable);
                                    ScreenshotHelper.this.notifyScreenshotError();
                                }
                            }
                        }
                    }
                };
                if (this.mContext.bindServiceAsUser(intent, serviceConnection, 67108865, UserHandle.CURRENT)) {
                    this.mScreenshotConnection = serviceConnection;
                    handler.postDelayed(runnable, j);
                }
            } else {
                try {
                    new Messenger(this.mScreenshotService).send(messageObtain);
                } catch (RemoteException e) {
                    Log.e(TAG, "Couldn't take screenshot: " + e);
                    if (consumer != null) {
                        consumer.accept(null);
                    }
                }
                handler.postDelayed(runnable, j);
            }
        }
    }

    public /* synthetic */ void lambda$takeScreenshot$0$ScreenshotHelper(Consumer consumer) {
        synchronized (this.mScreenshotLock) {
            if (this.mScreenshotConnection != null) {
                Log.e(TAG, "Timed out before getting screenshot capture response");
                resetConnection();
                notifyScreenshotError();
            }
        }
        if (consumer != null) {
            consumer.accept(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetConnection() {
        ServiceConnection serviceConnection = this.mScreenshotConnection;
        if (serviceConnection != null) {
            this.mContext.unbindService(serviceConnection);
            this.mScreenshotConnection = null;
            this.mScreenshotService = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyScreenshotError() {
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(this.mContext.getResources().getString(R.string.config_screenshotErrorReceiverComponent));
        Intent intent = new Intent(Intent.ACTION_USER_PRESENT);
        intent.setComponent(componentNameUnflattenFromString);
        intent.addFlags(AudioFormat.AAC_ADIF);
        this.mContext.sendBroadcastAsUser(intent, UserHandle.CURRENT);
    }
}
