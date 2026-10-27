package android.widget;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Process;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings;
import android.provider.Telephony;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.RemotableViewMethod;
import android.view.ViewDebug;
import android.view.ViewHierarchyEncoder;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.RemoteViews;
import com.android.internal.R;
import java.util.Calendar;
import java.util.TimeZone;
import libcore.icu.LocaleData;

/* JADX INFO: loaded from: classes3.dex */
@RemoteViews.RemoteView
public class TextClock extends TextView {

    @Deprecated
    public static final CharSequence DEFAULT_FORMAT_12_HOUR = "h:mm a";

    @Deprecated
    public static final CharSequence DEFAULT_FORMAT_24_HOUR = "H:mm";
    private CharSequence mDescFormat;
    private CharSequence mDescFormat12;
    private CharSequence mDescFormat24;

    @ViewDebug.ExportedProperty
    private CharSequence mFormat;
    private CharSequence mFormat12;
    private CharSequence mFormat24;
    private ContentObserver mFormatChangeObserver;

    @ViewDebug.ExportedProperty
    private boolean mHasSeconds;
    private final BroadcastReceiver mIntentReceiver;
    private boolean mRegistered;
    private boolean mShouldRunTicker;
    private boolean mShowCurrentUserTime;
    private boolean mStopTicking;
    private final Runnable mTicker;
    private Calendar mTime;
    private String mTimeZone;

    private static CharSequence abc(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return charSequence == null ? charSequence2 == null ? charSequence3 : charSequence2 : charSequence;
    }

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<TextClock> {
        private int mFormat12HourId;
        private int mFormat24HourId;
        private int mIs24HourModeEnabledId;
        private boolean mPropertiesMapped = false;
        private int mTimeZoneId;

        @Override // android.view.inspector.InspectionCompanion
        public void mapProperties(PropertyMapper propertyMapper) {
            this.mFormat12HourId = propertyMapper.mapObject("format12Hour", 16843722);
            this.mFormat24HourId = propertyMapper.mapObject("format24Hour", 16843723);
            this.mIs24HourModeEnabledId = propertyMapper.mapBoolean("is24HourModeEnabled", 0);
            this.mTimeZoneId = propertyMapper.mapObject("timeZone", 16843724);
            this.mPropertiesMapped = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public void readProperties(TextClock textClock, PropertyReader propertyReader) {
            if (!this.mPropertiesMapped) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readObject(this.mFormat12HourId, textClock.getFormat12Hour());
            propertyReader.readObject(this.mFormat24HourId, textClock.getFormat24Hour());
            propertyReader.readBoolean(this.mIs24HourModeEnabledId, textClock.is24HourModeEnabled());
            propertyReader.readObject(this.mTimeZoneId, textClock.getTimeZone());
        }
    }

    private class FormatChangeObserver extends ContentObserver {
        public FormatChangeObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            TextClock.this.chooseFormat();
            TextClock.this.onTimeChanged();
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            TextClock.this.chooseFormat();
            TextClock.this.onTimeChanged();
        }
    }

    public TextClock(Context context) {
        super(context);
        this.mIntentReceiver = new BroadcastReceiver() { // from class: android.widget.TextClock.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (TextClock.this.mStopTicking) {
                    return;
                }
                if (TextClock.this.mTimeZone != null || !"android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction())) {
                    if (!TextClock.this.mShouldRunTicker && ("android.intent.action.TIME_TICK".equals(intent.getAction()) || "android.intent.action.TIME_SET".equals(intent.getAction()))) {
                        return;
                    }
                } else {
                    TextClock.this.createTime(intent.getStringExtra(Intent.EXTRA_TIMEZONE));
                }
                TextClock.this.onTimeChanged();
            }
        };
        this.mTicker = new Runnable() { // from class: android.widget.TextClock.2
            @Override // java.lang.Runnable
            public void run() {
                if (TextClock.this.mStopTicking) {
                    return;
                }
                TextClock.this.onTimeChanged();
                long jUptimeMillis = SystemClock.uptimeMillis();
                long j = jUptimeMillis + (1000 - (jUptimeMillis % 1000));
                Handler handler = TextClock.this.getHandler();
                if (handler != null) {
                    handler.postAtTime(TextClock.this.mTicker, j);
                }
            }
        };
        init();
    }

    public TextClock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextClock(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public TextClock(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mIntentReceiver = new BroadcastReceiver() { // from class: android.widget.TextClock.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (TextClock.this.mStopTicking) {
                    return;
                }
                if (TextClock.this.mTimeZone != null || !"android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction())) {
                    if (!TextClock.this.mShouldRunTicker && ("android.intent.action.TIME_TICK".equals(intent.getAction()) || "android.intent.action.TIME_SET".equals(intent.getAction()))) {
                        return;
                    }
                } else {
                    TextClock.this.createTime(intent.getStringExtra(Intent.EXTRA_TIMEZONE));
                }
                TextClock.this.onTimeChanged();
            }
        };
        this.mTicker = new Runnable() { // from class: android.widget.TextClock.2
            @Override // java.lang.Runnable
            public void run() {
                if (TextClock.this.mStopTicking) {
                    return;
                }
                TextClock.this.onTimeChanged();
                long jUptimeMillis = SystemClock.uptimeMillis();
                long j = jUptimeMillis + (1000 - (jUptimeMillis % 1000));
                Handler handler = TextClock.this.getHandler();
                if (handler != null) {
                    handler.postAtTime(TextClock.this.mTicker, j);
                }
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TextClock, i, i2);
        saveAttributeDataForStyleable(context, R.styleable.TextClock, attributeSet, typedArrayObtainStyledAttributes, i, i2);
        try {
            this.mFormat12 = typedArrayObtainStyledAttributes.getText(0);
            this.mFormat24 = typedArrayObtainStyledAttributes.getText(1);
            this.mTimeZone = typedArrayObtainStyledAttributes.getString(2);
            typedArrayObtainStyledAttributes.recycle();
            init();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void init() {
        if (this.mFormat12 == null || this.mFormat24 == null) {
            LocaleData localeData = LocaleData.get(getContext().getResources().getConfiguration().locale);
            if (this.mFormat12 == null) {
                this.mFormat12 = localeData.timeFormat_hm;
            }
            if (this.mFormat24 == null) {
                this.mFormat24 = localeData.timeFormat_Hm;
            }
        }
        createTime(this.mTimeZone);
        chooseFormat();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createTime(String str) {
        if (str != null) {
            this.mTime = Calendar.getInstance(TimeZone.getTimeZone(str));
        } else {
            this.mTime = Calendar.getInstance();
        }
    }

    @ViewDebug.ExportedProperty
    public CharSequence getFormat12Hour() {
        return this.mFormat12;
    }

    @RemotableViewMethod
    public void setFormat12Hour(CharSequence charSequence) {
        this.mFormat12 = charSequence;
        chooseFormat();
        onTimeChanged();
    }

    public void setContentDescriptionFormat12Hour(CharSequence charSequence) {
        this.mDescFormat12 = charSequence;
        chooseFormat();
        onTimeChanged();
    }

    @ViewDebug.ExportedProperty
    public CharSequence getFormat24Hour() {
        return this.mFormat24;
    }

    @RemotableViewMethod
    public void setFormat24Hour(CharSequence charSequence) {
        this.mFormat24 = charSequence;
        chooseFormat();
        onTimeChanged();
    }

    public void setContentDescriptionFormat24Hour(CharSequence charSequence) {
        this.mDescFormat24 = charSequence;
        chooseFormat();
        onTimeChanged();
    }

    public void setShowCurrentUserTime(boolean z) {
        this.mShowCurrentUserTime = z;
        chooseFormat();
        onTimeChanged();
        unregisterObserver();
        registerObserver();
    }

    public void refreshTime() {
        onTimeChanged();
        invalidate();
    }

    public boolean is24HourModeEnabled() {
        if (this.mShowCurrentUserTime) {
            return DateFormat.is24HourFormat(getContext(), ActivityManager.getCurrentUser());
        }
        return DateFormat.is24HourFormat(getContext());
    }

    public String getTimeZone() {
        return this.mTimeZone;
    }

    @RemotableViewMethod
    public void setTimeZone(String str) {
        this.mTimeZone = str;
        createTime(str);
        onTimeChanged();
    }

    public CharSequence getFormat() {
        return this.mFormat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void chooseFormat() {
        boolean zIs24HourModeEnabled = is24HourModeEnabled();
        LocaleData localeData = LocaleData.get(getContext().getResources().getConfiguration().locale);
        if (zIs24HourModeEnabled) {
            CharSequence charSequenceAbc = abc(this.mFormat24, this.mFormat12, localeData.timeFormat_Hm);
            this.mFormat = charSequenceAbc;
            this.mDescFormat = abc(this.mDescFormat24, this.mDescFormat12, charSequenceAbc);
        } else {
            CharSequence charSequenceAbc2 = abc(this.mFormat12, this.mFormat24, localeData.timeFormat_hm);
            this.mFormat = charSequenceAbc2;
            this.mDescFormat = abc(this.mDescFormat12, this.mDescFormat24, charSequenceAbc2);
        }
        boolean z = this.mHasSeconds;
        boolean zHasSeconds = DateFormat.hasSeconds(this.mFormat);
        this.mHasSeconds = zHasSeconds;
        if (!this.mShouldRunTicker || z == zHasSeconds) {
            return;
        }
        if (z) {
            getHandler().removeCallbacks(this.mTicker);
        } else {
            this.mTicker.run();
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRegistered) {
            return;
        }
        this.mRegistered = true;
        registerReceiver();
        registerObserver();
        createTime(this.mTimeZone);
    }

    @Override // android.view.View
    public void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        boolean z2 = this.mShouldRunTicker;
        if (z2 || !z) {
            if (!z2 || z) {
                return;
            }
            this.mShouldRunTicker = false;
            getHandler().removeCallbacks(this.mTicker);
            return;
        }
        this.mShouldRunTicker = true;
        if (this.mHasSeconds) {
            this.mTicker.run();
        } else {
            onTimeChanged();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mRegistered) {
            unregisterReceiver();
            unregisterObserver();
            this.mRegistered = false;
        }
    }

    public void disableClockTick() {
        this.mStopTicking = true;
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        getContext().registerReceiverAsUser(this.mIntentReceiver, Process.myUserHandle(), intentFilter, null, getHandler());
    }

    private void registerObserver() {
        if (this.mRegistered) {
            if (this.mFormatChangeObserver == null) {
                this.mFormatChangeObserver = new FormatChangeObserver(getHandler());
            }
            ContentResolver contentResolver = getContext().getContentResolver();
            Uri uriFor = Settings.System.getUriFor("time_12_24");
            if (this.mShowCurrentUserTime) {
                contentResolver.registerContentObserver(uriFor, true, this.mFormatChangeObserver, -1);
            } else {
                contentResolver.registerContentObserver(uriFor, true, this.mFormatChangeObserver, UserHandle.myUserId());
            }
        }
    }

    private void unregisterReceiver() {
        getContext().unregisterReceiver(this.mIntentReceiver);
    }

    private void unregisterObserver() {
        if (this.mFormatChangeObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(this.mFormatChangeObserver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTimeChanged() {
        this.mTime.setTimeInMillis(System.currentTimeMillis());
        setText(DateFormat.format(this.mFormat, this.mTime));
        setContentDescription(DateFormat.format(this.mDescFormat, this.mTime));
    }

    @Override // android.widget.TextView, android.view.View
    protected void encodeProperties(ViewHierarchyEncoder viewHierarchyEncoder) {
        super.encodeProperties(viewHierarchyEncoder);
        CharSequence format12Hour = getFormat12Hour();
        viewHierarchyEncoder.addProperty("format12Hour", format12Hour == null ? null : format12Hour.toString());
        CharSequence format24Hour = getFormat24Hour();
        viewHierarchyEncoder.addProperty("format24Hour", format24Hour == null ? null : format24Hour.toString());
        CharSequence charSequence = this.mFormat;
        viewHierarchyEncoder.addProperty(Telephony.CellBroadcasts.MESSAGE_FORMAT, charSequence != null ? charSequence.toString() : null);
        viewHierarchyEncoder.addProperty("hasSeconds", this.mHasSeconds);
    }
}
