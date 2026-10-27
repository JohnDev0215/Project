package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.net.Uri;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.internal.os.BackgroundThread;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.statusbar.policy.ClockController;
import com.byd.card.bean.Constant;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class ClockControllerImpl extends BroadcastReceiver implements ClockController {
    public static String SPLIT = "-";
    private String mClockDate;
    private String mClockTime;
    private Context mContext;
    private int mDate;
    private FormatChangeObserver mFormatChangeObserver;
    private int mHour;
    private Locale mLocale;
    private int mMin;
    private int mMonth;
    private int mSecond;
    private int mYear;
    private final ArrayList<ClockController.Callback> mCallbacks = new ArrayList<>();
    private Calendar mCalendar = Calendar.getInstance();
    private SimpleDateFormat mClockFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");

    @Override // com.android.systemui.Dumpable
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    public ClockControllerImpl(Context context) {
        this.mContext = context;
        registerReceicer();
        updateClock();
    }

    public final void registerReceicer() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        intentFilter.addAction("android.intent.action.USER_SWITCHED");
        this.mContext.registerReceiverAsUser(this, UserHandle.ALL, intentFilter, null, (Handler) Dependency.get(Dependency.TIME_TICK_HANDLER));
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BydLogHelper.logD("onReceive action = " + action);
        if (action.equals("android.intent.action.TIMEZONE_CHANGED")) {
            final String stringExtra = intent.getStringExtra("time-zone");
            BackgroundThread.getHandler().post(new Runnable() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$ClockControllerImpl$-9oVFctM6M-ROBn-GlidfdiTOiQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onReceive$0$ClockControllerImpl(stringExtra);
                }
            });
        } else if (action.equals("android.intent.action.CONFIGURATION_CHANGED")) {
            final Locale locale = this.mContext.getResources().getConfiguration().locale;
            BackgroundThread.getHandler().post(new Runnable() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$ClockControllerImpl$LGCCqguqkpS7VDsJ5jPCjW908kY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onReceive$1$ClockControllerImpl(locale);
                }
            });
        }
        BackgroundThread.getHandler().post(new Runnable() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$ClockControllerImpl$IK7l4AExZ8PE8_nXRvmfzeWtKYE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.updateClock();
            }
        });
    }

    public /* synthetic */ void lambda$onReceive$0$ClockControllerImpl(String str) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(str));
        this.mCalendar = calendar;
        SimpleDateFormat simpleDateFormat = this.mClockFormat;
        if (simpleDateFormat != null) {
            simpleDateFormat.setTimeZone(calendar.getTimeZone());
        }
    }

    public /* synthetic */ void lambda$onReceive$1$ClockControllerImpl(Locale locale) {
        if (locale.equals(this.mLocale)) {
            return;
        }
        this.mLocale = locale;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateClock() {
        String string = Settings.System.getString(this.mContext.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24);
        BydLogHelper.logD("strTimeFormat:" + string);
        Date date = new Date(System.currentTimeMillis());
        BydLogHelper.logD("updateClock curr: " + date.toString());
        if (TextUtils.equals(TimeManagerImpl.TIME_FORMAT_TWELVE, string)) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            int i = gregorianCalendar.get(9);
            String strSubstring = new SimpleDateFormat(this.mContext.getString(R.string.formatTime12), Locale.ENGLISH).format(date);
            if (strSubstring.startsWith(Constant.CarType.CAR_TYPE_FUEL)) {
                strSubstring = strSubstring.substring(1);
            }
            this.mClockTime = this.mContext.getString(i == 0 ? R.string.sys_ui_time_am : R.string.sys_ui_time_pm) + SPLIT + strSubstring;
        } else {
            this.mClockTime = new SimpleDateFormat(this.mContext.getString(R.string.formatTime24), Locale.ENGLISH).format(date);
        }
        BydLogHelper.logD("mClockTime:" + this.mClockTime);
        synchronized (this.mCallbacks) {
            Iterator<ClockController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onClockTime(this.mClockTime);
            }
        }
    }

    private synchronized void getTime() {
        this.mYear = this.mCalendar.get(1);
        this.mMonth = this.mCalendar.get(2);
        this.mDate = this.mCalendar.get(5);
        this.mHour = this.mCalendar.get(11);
        this.mMin = this.mCalendar.get(12);
        this.mSecond = this.mCalendar.get(13);
    }

    @Override // com.android.systemui.statusbar.policy.ClockController
    public String getClockDate() {
        updateClock();
        return this.mClockDate;
    }

    @Override // com.android.systemui.statusbar.policy.ClockController
    public String getClockTime() {
        updateClock();
        return this.mClockTime;
    }

    private class FormatChangeObserver extends ContentObserver {
        public FormatChangeObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            ClockControllerImpl.this.updateClock();
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            ClockControllerImpl.this.updateClock();
        }
    }

    private void registerObserver() {
        if (this.mFormatChangeObserver == null) {
            this.mFormatChangeObserver = new FormatChangeObserver(BackgroundThread.getHandler());
        }
        this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor(BYDAutoSysPropertiesDevice.TIME_12_24), true, this.mFormatChangeObserver);
    }

    private void unregisterObserver() {
        if (this.mFormatChangeObserver != null) {
            this.mContext.getContentResolver().unregisterContentObserver(this.mFormatChangeObserver);
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void addCallback(ClockController.Callback callback) {
        synchronized (this.mCallbacks) {
            if (callback != null) {
                if (!this.mCallbacks.contains(callback)) {
                    registerObserver();
                    this.mCallbacks.add(callback);
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void removeCallback(ClockController.Callback callback) {
        synchronized (this.mCallbacks) {
            if (callback == null) {
                return;
            }
            unregisterObserver();
            this.mCallbacks.remove(callback);
        }
    }
}
