package com.byd.globalnotification.notification;

import a.b.k.l;
import a.k.k;
import a.k.l;
import a.k.m;
import android.app.PendingIntent;
import android.service.notification.StatusBarNotification;
import android.widget.RemoteViews;
import androidx.annotation.Keep;
import b.a.a.a.a;
import b.c.a.h.b;
import com.byd.globalnotification.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class NotifyMsgBean {
    public static final int TYPE_CUSTOM = 2;
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_PROGRESS = 1;
    public String iconUrl;
    public boolean isAutoCancel;
    public String key;
    public PendingIntent pendingIntent;
    public String pkgName;
    public StatusBarNotification sbn;
    public long timeMills;
    public l<String> timeString;
    public int type;
    public l<String> title = new l<>();
    public l<String> content = new l<>();
    public l<RemoteViews> contentView = new l<>();
    public l<RemoteViews> bigContentView = new l<>();
    public m progress = new m(0);
    public m progressMax = new m(0);
    public k multiple = new k(false);
    public k isExpand = new k(false);

    public NotifyMsgBean() {
    }

    public NotifyMsgBean(StatusBarNotification statusBarNotification) {
        this.sbn = statusBarNotification;
        this.key = statusBarNotification.getKey();
        this.pkgName = statusBarNotification.getPackageName();
        this.title.j(statusBarNotification.getNotification().extras.getString("android.title", ""));
        this.content.j(statusBarNotification.getNotification().extras.getString("android.text", ""));
        this.progress.j(statusBarNotification.getNotification().extras.getInt("android.progress", 0));
        this.progressMax.j(statusBarNotification.getNotification().extras.getInt("android.progressMax", 0));
        this.pendingIntent = statusBarNotification.getNotification().contentIntent;
        this.contentView.j(statusBarNotification.getNotification().contentView);
        this.bigContentView.j(statusBarNotification.getNotification().bigContentView);
        this.isAutoCancel = (statusBarNotification.getNotification().flags & 16) != 0;
        this.timeString = new l<>();
        this.timeMills = statusBarNotification.getPostTime() < 1483286400000L ? System.currentTimeMillis() : statusBarNotification.getPostTime();
        StringBuilder sbD = a.d("NotifyMsgBean, notify time = ");
        sbD.append(formatDayDate(this.timeMills));
        b.b(sbD.toString());
        refreshTimeStringForSysMsg();
        if (this.contentView.f925b != null || this.bigContentView.f925b != null) {
            this.type = 2;
        } else if (this.progress.f926b <= 0 || this.progressMax.f926b <= 0) {
            this.type = 0;
        } else {
            this.type = 1;
        }
    }

    public static String formatDayDate(long j) {
        Locale locale;
        String str;
        if (b.c.a.h.d.b.a().getResources().getConfiguration().locale.getLanguage().endsWith("zh")) {
            locale = Locale.SIMPLIFIED_CHINESE;
            str = "yyyy年MM月dd日";
        } else {
            locale = Locale.ENGLISH;
            str = "yyyy MMM dd";
        }
        return new SimpleDateFormat(str, locale).format(new Date(j));
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public String getKey() {
        return this.key;
    }

    public PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public m getProgress() {
        return this.progress;
    }

    public m getProgressMax() {
        return this.progressMax;
    }

    public StatusBarNotification getSbn() {
        return this.sbn;
    }

    public long getTimeMills() {
        return this.timeMills;
    }

    public int getType() {
        return this.type;
    }

    public boolean isAutoCancel() {
        return this.isAutoCancel;
    }

    public k isMultiple() {
        return this.multiple;
    }

    public void refreshTimeStringForSysMsg() {
        String dayDate;
        int i;
        int iCurrentTimeMillis = ((int) (System.currentTimeMillis() - this.timeMills)) / 1000;
        if (iCurrentTimeMillis < 0 || iCurrentTimeMillis > 30) {
            if (iCurrentTimeMillis <= 30 || iCurrentTimeMillis > 3600) {
                if (iCurrentTimeMillis <= 3600 || iCurrentTimeMillis > 86400) {
                    if (iCurrentTimeMillis <= 86400 || iCurrentTimeMillis > 172800) {
                        if (iCurrentTimeMillis > 172800) {
                        }
                        dayDate = formatDayDate(this.timeMills);
                    } else {
                        i = R.string.notify_time_yesterday;
                    }
                } else if (iCurrentTimeMillis < 7200) {
                    i = R.string.notify_time_one_h;
                } else {
                    dayDate = String.format(l.i.F(R.string.notify_time_h_s), Integer.valueOf(iCurrentTimeMillis / 3600));
                }
            } else if (iCurrentTimeMillis <= 60) {
                i = R.string.notify_time_one_min;
            } else {
                dayDate = String.format(l.i.F(R.string.notify_time_mins), Integer.valueOf(iCurrentTimeMillis / 60));
            }
            this.timeString.j(dayDate);
        }
        i = R.string.notify_time_just_now;
        dayDate = l.i.F(i);
        this.timeString.j(dayDate);
    }

    public void setMultiple(boolean z) {
        k kVar = this.multiple;
        if (z != kVar.f924b) {
            kVar.f924b = z;
            kVar.h();
        }
    }

    public void setProgress(int i) {
        m mVar = this.progress;
        if (i != mVar.f926b) {
            mVar.f926b = i;
            mVar.h();
        }
    }

    public void setProgressMax(int i) {
        m mVar = this.progressMax;
        if (i != mVar.f926b) {
            mVar.f926b = i;
            mVar.h();
        }
    }

    public void setType(int i) {
        this.type = i;
    }
}
