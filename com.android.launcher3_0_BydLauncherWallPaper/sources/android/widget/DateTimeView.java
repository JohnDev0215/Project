package android.widget;

import android.app.ActivityThread;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.RemotableViewMethod;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.RemoteViews;
import com.android.internal.R;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
@RemoteViews.RemoteView
public class DateTimeView extends TextView {
    private static final int SHOW_MONTH_DAY_YEAR = 1;
    private static final int SHOW_TIME = 0;
    private static final ThreadLocal<ReceiverInfo> sReceiverInfo = new ThreadLocal<>();
    int mLastDisplay;
    DateFormat mLastFormat;
    private LocalDateTime mLocalTime;
    private String mNowText;
    private boolean mShowRelativeTime;
    private long mTimeMillis;
    private long mUpdateTimeMillis;

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<DateTimeView> {
        private boolean mPropertiesMapped = false;
        private int mShowReleativeId;

        @Override // android.view.inspector.InspectionCompanion
        public void mapProperties(PropertyMapper propertyMapper) {
            this.mShowReleativeId = propertyMapper.mapBoolean("showReleative", 0);
            this.mPropertiesMapped = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public void readProperties(DateTimeView dateTimeView, PropertyReader propertyReader) {
            if (!this.mPropertiesMapped) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readBoolean(this.mShowReleativeId, dateTimeView.isShowRelativeTime());
        }
    }

    public DateTimeView(Context context) {
        this(context, null);
    }

    public DateTimeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLastDisplay = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DateTimeView, 0, 0);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            if (typedArrayObtainStyledAttributes.getIndex(i) == 0) {
                setShowRelativeTime(typedArrayObtainStyledAttributes.getBoolean(i, false));
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ThreadLocal<ReceiverInfo> threadLocal = sReceiverInfo;
        ReceiverInfo receiverInfo = threadLocal.get();
        if (receiverInfo == null) {
            receiverInfo = new ReceiverInfo();
            threadLocal.set(receiverInfo);
        }
        receiverInfo.addView(this);
        if (this.mShowRelativeTime) {
            update();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ReceiverInfo receiverInfo = sReceiverInfo.get();
        if (receiverInfo != null) {
            receiverInfo.removeView(this);
        }
    }

    @RemotableViewMethod
    public void setTime(long j) {
        this.mTimeMillis = j;
        this.mLocalTime = toLocalDateTime(j, ZoneId.systemDefault()).withSecond(0);
        update();
    }

    @RemotableViewMethod
    public void setShowRelativeTime(boolean z) {
        this.mShowRelativeTime = z;
        updateNowText();
        update();
    }

    public boolean isShowRelativeTime() {
        return this.mShowRelativeTime;
    }

    @Override // android.view.View
    @RemotableViewMethod
    public void setVisibility(int i) {
        boolean z = i != 8 && getVisibility() == 8;
        super.setVisibility(i);
        if (z) {
            update();
        }
    }

    void update() {
        DateFormat timeFormat;
        if (this.mLocalTime == null || getVisibility() == 8) {
            return;
        }
        if (this.mShowRelativeTime) {
            updateRelativeTime();
            return;
        }
        ZoneId zoneIdSystemDefault = ZoneId.systemDefault();
        LocalDateTime localDateTime = this.mLocalTime;
        LocalDateTime localDateTimeOf = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIDNIGHT);
        LocalDateTime localDateTimePlusDays = localDateTimeOf.plusDays(1L);
        int i = 0;
        LocalDateTime localDateTimeWithSecond = LocalDateTime.now(zoneIdSystemDefault).withSecond(0);
        long epochMillis = toEpochMillis(localDateTime.minusHours(12L), zoneIdSystemDefault);
        long epochMillis2 = toEpochMillis(localDateTime.plusHours(12L), zoneIdSystemDefault);
        long epochMillis3 = toEpochMillis(localDateTimeOf, zoneIdSystemDefault);
        long epochMillis4 = toEpochMillis(localDateTimePlusDays, zoneIdSystemDefault);
        long epochMillis5 = toEpochMillis(localDateTime, zoneIdSystemDefault);
        long epochMillis6 = toEpochMillis(localDateTimeWithSecond, zoneIdSystemDefault);
        if ((epochMillis6 < epochMillis3 || epochMillis6 >= epochMillis4) && (epochMillis6 < epochMillis || epochMillis6 >= epochMillis2)) {
            i = 1;
        }
        if (i != this.mLastDisplay || (timeFormat = this.mLastFormat) == null) {
            if (i == 0) {
                timeFormat = getTimeFormat();
            } else if (i == 1) {
                timeFormat = DateFormat.getDateInstance(3);
            } else {
                throw new RuntimeException("unknown display value: " + i);
            }
            this.mLastFormat = timeFormat;
        }
        setText(timeFormat.format(new Date(epochMillis5)));
        if (i == 0) {
            if (epochMillis2 <= epochMillis4) {
                epochMillis2 = epochMillis4;
            }
            this.mUpdateTimeMillis = epochMillis2;
        } else {
            if (this.mTimeMillis < epochMillis6) {
                this.mUpdateTimeMillis = 0L;
                return;
            }
            if (epochMillis >= epochMillis3) {
                epochMillis = epochMillis3;
            }
            this.mUpdateTimeMillis = epochMillis;
        }
    }

    private void updateRelativeTime() {
        int i;
        String str;
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jAbs = Math.abs(jCurrentTimeMillis - this.mTimeMillis);
        boolean z = jCurrentTimeMillis >= this.mTimeMillis;
        long j = 60000;
        if (jAbs < 60000) {
            setText(this.mNowText);
            this.mUpdateTimeMillis = this.mTimeMillis + 60000 + 1;
            return;
        }
        if (jAbs < 3600000) {
            i = (int) (jAbs / 60000);
            str = String.format(getContext().getResources().getQuantityString(z ? R.plurals.duration_minutes_shortest : R.plurals.duration_minutes_shortest_future, i), Integer.valueOf(i));
        } else {
            j = 86400000;
            if (jAbs < 86400000) {
                i = (int) (jAbs / 3600000);
                str = String.format(getContext().getResources().getQuantityString(z ? R.plurals.duration_hours_shortest : R.plurals.duration_hours_shortest_future, i), Integer.valueOf(i));
                j = 3600000;
            } else if (jAbs < DateUtils.YEAR_IN_MILLIS) {
                LocalDateTime localDateTime = this.mLocalTime;
                ZoneId zoneIdSystemDefault = ZoneId.systemDefault();
                LocalDateTime localDateTime2 = toLocalDateTime(jCurrentTimeMillis, zoneIdSystemDefault);
                int iMax = Math.max(Math.abs(dayDistance(localDateTime, localDateTime2)), 1);
                String str2 = String.format(getContext().getResources().getQuantityString(z ? R.plurals.duration_days_shortest : R.plurals.duration_days_shortest_future, iMax), Integer.valueOf(iMax));
                if (z || iMax != 1) {
                    this.mUpdateTimeMillis = computeNextMidnight(localDateTime2, zoneIdSystemDefault);
                    j = -1;
                }
                i = iMax;
                str = str2;
            } else {
                i = (int) (jAbs / DateUtils.YEAR_IN_MILLIS);
                str = String.format(getContext().getResources().getQuantityString(z ? R.plurals.duration_years_shortest : R.plurals.duration_years_shortest_future, i), Integer.valueOf(i));
                j = 31449600000L;
            }
        }
        if (j != -1) {
            if (z) {
                this.mUpdateTimeMillis = this.mTimeMillis + (j * ((long) (i + 1))) + 1;
            } else {
                this.mUpdateTimeMillis = (this.mTimeMillis - (j * ((long) i))) + 1;
            }
        }
        setText(str);
    }

    private static long computeNextMidnight(LocalDateTime localDateTime, ZoneId zoneId) {
        return toEpochMillis(LocalDateTime.of(localDateTime.toLocalDate().plusDays(1L), LocalTime.MIDNIGHT), zoneId);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateNowText();
        update();
    }

    private void updateNowText() {
        if (this.mShowRelativeTime) {
            this.mNowText = getContext().getResources().getString(R.string.now_string_shortest);
        }
    }

    private static int dayDistance(LocalDateTime localDateTime, LocalDateTime localDateTime2) {
        return (int) (localDateTime2.getLong(JulianFields.JULIAN_DAY) - localDateTime.getLong(JulianFields.JULIAN_DAY));
    }

    private DateFormat getTimeFormat() {
        return android.text.format.DateFormat.getTimeFormat(getContext());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearFormatAndUpdate() {
        this.mLastFormat = null;
        update();
    }

    @Override // android.widget.TextView, android.view.View
    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        String str;
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        if (this.mShowRelativeTime) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jAbs = Math.abs(jCurrentTimeMillis - this.mTimeMillis);
            boolean z = jCurrentTimeMillis >= this.mTimeMillis;
            if (jAbs < 60000) {
                str = this.mNowText;
            } else if (jAbs < 3600000) {
                int i = (int) (jAbs / 60000);
                str = String.format(getContext().getResources().getQuantityString(z ? R.plurals.duration_minutes_relative : R.plurals.duration_minutes_relative_future, i), Integer.valueOf(i));
            } else if (jAbs < 86400000) {
                int i2 = (int) (jAbs / 3600000);
                str = String.format(getContext().getResources().getQuantityString(z ? R.plurals.duration_hours_relative : R.plurals.duration_hours_relative_future, i2), Integer.valueOf(i2));
            } else if (jAbs < DateUtils.YEAR_IN_MILLIS) {
                int iMax = Math.max(Math.abs(dayDistance(this.mLocalTime, toLocalDateTime(jCurrentTimeMillis, ZoneId.systemDefault()))), 1);
                str = String.format(getContext().getResources().getQuantityString(z ? R.plurals.duration_days_relative : R.plurals.duration_days_relative_future, iMax), Integer.valueOf(iMax));
            } else {
                int i3 = (int) (jAbs / DateUtils.YEAR_IN_MILLIS);
                str = String.format(getContext().getResources().getQuantityString(z ? R.plurals.duration_years_relative : R.plurals.duration_years_relative_future, i3), Integer.valueOf(i3));
            }
            accessibilityNodeInfo.setText(str);
        }
    }

    public static void setReceiverHandler(Handler handler) {
        ThreadLocal<ReceiverInfo> threadLocal = sReceiverInfo;
        ReceiverInfo receiverInfo = threadLocal.get();
        if (receiverInfo == null) {
            receiverInfo = new ReceiverInfo();
            threadLocal.set(receiverInfo);
        }
        receiverInfo.setHandler(handler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class ReceiverInfo {
        private final ArrayList<DateTimeView> mAttachedViews;
        private Handler mHandler;
        private final ContentObserver mObserver;
        private final BroadcastReceiver mReceiver;

        private ReceiverInfo() {
            this.mAttachedViews = new ArrayList<>();
            this.mReceiver = new BroadcastReceiver() { // from class: android.widget.DateTimeView.ReceiverInfo.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (!"android.intent.action.TIME_TICK".equals(intent.getAction()) || System.currentTimeMillis() >= ReceiverInfo.this.getSoonestUpdateTime()) {
                        ReceiverInfo.this.updateAll();
                    }
                }
            };
            this.mObserver = new ContentObserver(new Handler()) { // from class: android.widget.DateTimeView.ReceiverInfo.2
                @Override // android.database.ContentObserver
                public void onChange(boolean z) {
                    ReceiverInfo.this.updateAll();
                }
            };
            this.mHandler = new Handler();
        }

        public void addView(DateTimeView dateTimeView) {
            synchronized (this.mAttachedViews) {
                boolean zIsEmpty = this.mAttachedViews.isEmpty();
                this.mAttachedViews.add(dateTimeView);
                if (zIsEmpty) {
                    register(getApplicationContextIfAvailable(dateTimeView.getContext()));
                }
            }
        }

        public void removeView(DateTimeView dateTimeView) {
            synchronized (this.mAttachedViews) {
                if (this.mAttachedViews.remove(dateTimeView) && this.mAttachedViews.isEmpty()) {
                    unregister(getApplicationContextIfAvailable(dateTimeView.getContext()));
                }
            }
        }

        void updateAll() {
            synchronized (this.mAttachedViews) {
                int size = this.mAttachedViews.size();
                for (int i = 0; i < size; i++) {
                    final DateTimeView dateTimeView = this.mAttachedViews.get(i);
                    dateTimeView.post(new Runnable() { // from class: android.widget.-$$Lambda$DateTimeView$ReceiverInfo$5WeIQ6PZQOQ8n1oTVyvbaESfvIc
                        @Override // java.lang.Runnable
                        public final void run() {
                            dateTimeView.clearFormatAndUpdate();
                        }
                    });
                }
            }
        }

        long getSoonestUpdateTime() {
            long j;
            synchronized (this.mAttachedViews) {
                int size = this.mAttachedViews.size();
                j = Long.MAX_VALUE;
                for (int i = 0; i < size; i++) {
                    long j2 = this.mAttachedViews.get(i).mUpdateTimeMillis;
                    if (j2 < j) {
                        j = j2;
                    }
                }
            }
            return j;
        }

        static final Context getApplicationContextIfAvailable(Context context) {
            Context applicationContext = context.getApplicationContext();
            return applicationContext != null ? applicationContext : ActivityThread.currentApplication().getApplicationContext();
        }

        void register(Context context) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_TICK");
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction(Intent.ACTION_CONFIGURATION_CHANGED);
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            context.registerReceiver(this.mReceiver, intentFilter, null, this.mHandler);
        }

        void unregister(Context context) {
            context.unregisterReceiver(this.mReceiver);
        }

        public void setHandler(Handler handler) {
            this.mHandler = handler;
            synchronized (this.mAttachedViews) {
                if (!this.mAttachedViews.isEmpty()) {
                    unregister(this.mAttachedViews.get(0).getContext());
                    register(this.mAttachedViews.get(0).getContext());
                }
            }
        }
    }

    private static LocalDateTime toLocalDateTime(long j, ZoneId zoneId) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(j), zoneId);
    }

    private static long toEpochMillis(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.toInstant(zoneId.getRules().getOffset(localDateTime)).toEpochMilli();
    }
}
