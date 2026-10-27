package android.hardware.display;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import java.time.LocalTime;

/* JADX INFO: loaded from: classes2.dex */
public class NightDisplayListener {
    private Callback mCallback;
    private final ContentObserver mContentObserver;
    private final Context mContext;
    private final Handler mHandler;
    private final ColorDisplayManager mManager;
    private final int mUserId;

    public interface Callback {
        default void onActivated(boolean z) {
        }

        default void onAutoModeChanged(int i) {
        }

        default void onColorTemperatureChanged(int i) {
        }

        default void onCustomEndTimeChanged(LocalTime localTime) {
        }

        default void onCustomStartTimeChanged(LocalTime localTime) {
        }
    }

    public NightDisplayListener(Context context) {
        this(context, ActivityManager.getCurrentUser(), new Handler(Looper.getMainLooper()));
    }

    public NightDisplayListener(Context context, Handler handler) {
        this(context, ActivityManager.getCurrentUser(), handler);
    }

    public NightDisplayListener(Context context, int i, Handler handler) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mManager = (ColorDisplayManager) applicationContext.getSystemService(ColorDisplayManager.class);
        this.mUserId = i;
        this.mHandler = handler;
        this.mContentObserver = new ContentObserver(this.mHandler) { // from class: android.hardware.display.NightDisplayListener.1
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                super.onChange(z, uri);
                String lastPathSegment = uri == null ? null : uri.getLastPathSegment();
                if (lastPathSegment == null || NightDisplayListener.this.mCallback == null) {
                    return;
                }
                byte b = -1;
                switch (lastPathSegment.hashCode()) {
                    case -2038150513:
                        if (lastPathSegment.equals(Settings.Secure.NIGHT_DISPLAY_AUTO_MODE)) {
                            b = 1;
                        }
                        break;
                    case -1761668069:
                        if (lastPathSegment.equals(Settings.Secure.NIGHT_DISPLAY_CUSTOM_END_TIME)) {
                            b = 3;
                        }
                        break;
                    case -969458956:
                        if (lastPathSegment.equals(Settings.Secure.NIGHT_DISPLAY_COLOR_TEMPERATURE)) {
                            b = 4;
                        }
                        break;
                    case 800115245:
                        if (lastPathSegment.equals(Settings.Secure.NIGHT_DISPLAY_ACTIVATED)) {
                            b = 0;
                        }
                        break;
                    case 1578271348:
                        if (lastPathSegment.equals(Settings.Secure.NIGHT_DISPLAY_CUSTOM_START_TIME)) {
                            b = 2;
                        }
                        break;
                }
                if (b == 0) {
                    NightDisplayListener.this.mCallback.onActivated(NightDisplayListener.this.mManager.isNightDisplayActivated());
                    return;
                }
                if (b == 1) {
                    NightDisplayListener.this.mCallback.onAutoModeChanged(NightDisplayListener.this.mManager.getNightDisplayAutoMode());
                    return;
                }
                if (b == 2) {
                    NightDisplayListener.this.mCallback.onCustomStartTimeChanged(NightDisplayListener.this.mManager.getNightDisplayCustomStartTime());
                } else if (b == 3) {
                    NightDisplayListener.this.mCallback.onCustomEndTimeChanged(NightDisplayListener.this.mManager.getNightDisplayCustomEndTime());
                } else {
                    if (b != 4) {
                        return;
                    }
                    NightDisplayListener.this.mCallback.onColorTemperatureChanged(NightDisplayListener.this.mManager.getNightDisplayColorTemperature());
                }
            }
        };
    }

    public void setCallback(final Callback callback) {
        if (Looper.myLooper() != this.mHandler.getLooper()) {
            this.mHandler.post(new Runnable() { // from class: android.hardware.display.-$$Lambda$NightDisplayListener$sOK1HmSbMnFLzc4SdDD1WpVWJiI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setCallback$0$NightDisplayListener(callback);
                }
            });
        }
        lambda$setCallback$0$NightDisplayListener(callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setCallbackInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$setCallback$0$NightDisplayListener(Callback callback) {
        Callback callback2 = this.mCallback;
        if (callback2 != callback) {
            this.mCallback = callback;
            if (callback == null) {
                this.mContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
                return;
            }
            if (callback2 == null) {
                ContentResolver contentResolver = this.mContext.getContentResolver();
                contentResolver.registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.NIGHT_DISPLAY_ACTIVATED), false, this.mContentObserver, this.mUserId);
                contentResolver.registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.NIGHT_DISPLAY_AUTO_MODE), false, this.mContentObserver, this.mUserId);
                contentResolver.registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.NIGHT_DISPLAY_CUSTOM_START_TIME), false, this.mContentObserver, this.mUserId);
                contentResolver.registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.NIGHT_DISPLAY_CUSTOM_END_TIME), false, this.mContentObserver, this.mUserId);
                contentResolver.registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.NIGHT_DISPLAY_COLOR_TEMPERATURE), false, this.mContentObserver, this.mUserId);
            }
        }
    }
}
