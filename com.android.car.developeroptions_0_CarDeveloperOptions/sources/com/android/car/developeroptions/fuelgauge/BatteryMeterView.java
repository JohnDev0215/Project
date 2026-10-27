package com.android.car.developeroptions.fuelgauge;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.car.developeroptions.R;
import com.android.settingslib.Utils;
import com.android.settingslib.graph.ThemedBatteryDrawable;

/* JADX INFO: loaded from: classes.dex */
public class BatteryMeterView extends ImageView {
    ColorFilter mAccentColorFilter;
    BatteryMeterDrawable mDrawable;
    ColorFilter mErrorColorFilter;
    private boolean mPowerSaveEnabled;

    public BatteryMeterView(Context context) {
        this(context, null, 0);
    }

    public BatteryMeterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BatteryMeterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int color = context.getColor(R.color.meter_background_color);
        this.mAccentColorFilter = new PorterDuffColorFilter(Utils.getColorAttrDefaultColor(context, android.R.attr.colorAccent), PorterDuff.Mode.SRC);
        this.mErrorColorFilter = new PorterDuffColorFilter(context.getColor(R.color.battery_icon_color_error), PorterDuff.Mode.SRC_IN);
        BatteryMeterDrawable batteryMeterDrawable = new BatteryMeterDrawable(context, color);
        this.mDrawable = batteryMeterDrawable;
        batteryMeterDrawable.setColorFilter(this.mAccentColorFilter);
        setImageDrawable(this.mDrawable);
        setLayerType(1, null);
    }

    public void setBatteryLevel(int i) {
        this.mDrawable.setBatteryLevel(i);
        if (i < this.mDrawable.getCriticalLevel()) {
            this.mDrawable.setColorFilter(this.mErrorColorFilter);
        } else {
            this.mDrawable.setColorFilter(this.mAccentColorFilter);
        }
    }

    public void setPowerSave(boolean z) {
        this.mDrawable.setPowerSaveEnabled(z);
        this.mPowerSaveEnabled = z;
    }

    public boolean getPowerSave() {
        return this.mPowerSaveEnabled;
    }

    public int getBatteryLevel() {
        return this.mDrawable.getLevel();
    }

    public void setCharging(boolean z) {
        this.mDrawable.setCharging(z);
        postInvalidate();
    }

    public boolean getCharging() {
        return this.mDrawable.getCharging();
    }

    public static class BatteryMeterDrawable extends ThemedBatteryDrawable {
        private final int mIntrinsicHeight;
        private final int mIntrinsicWidth;

        public BatteryMeterDrawable(Context context, int i) {
            super(context, i);
            this.mIntrinsicWidth = context.getResources().getDimensionPixelSize(R.dimen.battery_meter_width);
            this.mIntrinsicHeight = context.getResources().getDimensionPixelSize(R.dimen.battery_meter_height);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.mIntrinsicWidth;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.mIntrinsicHeight;
        }
    }
}
