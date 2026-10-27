package androidx.leanback.widget;

import android.content.Context;
import android.os.Bundle;
import androidx.leanback.widget.GuidedAction;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class GuidedDatePickerAction extends GuidedAction {
    long mDate;
    String mDatePickerFormat;
    long mMinDate = Long.MIN_VALUE;
    long mMaxDate = org.tukaani.xz.common.Util.VLI_MAX;

    public static abstract class BuilderBase<B extends BuilderBase> extends GuidedAction.BuilderBase<B> {
        private long mDate;
        private String mDatePickerFormat;
        private long mMaxDate;
        private long mMinDate;

        public BuilderBase(Context context) {
            super(context);
            this.mMinDate = Long.MIN_VALUE;
            this.mMaxDate = org.tukaani.xz.common.Util.VLI_MAX;
            Calendar c = Calendar.getInstance();
            this.mDate = c.getTimeInMillis();
            hasEditableActivatorView(true);
        }

        public B datePickerFormat(String format) {
            this.mDatePickerFormat = format;
            return this;
        }

        public B date(long date) {
            this.mDate = date;
            return this;
        }

        public B minDate(long minDate) {
            this.mMinDate = minDate;
            return this;
        }

        public B maxDate(long maxDate) {
            this.mMaxDate = maxDate;
            return this;
        }

        protected final void applyDatePickerValues(GuidedDatePickerAction action) {
            super.applyValues(action);
            action.mDatePickerFormat = this.mDatePickerFormat;
            action.mDate = this.mDate;
            long j = this.mMinDate;
            if (j > this.mMaxDate) {
                throw new IllegalArgumentException("MinDate cannot be larger than MaxDate");
            }
            action.mMinDate = j;
            action.mMaxDate = this.mMaxDate;
        }
    }

    public static final class Builder extends BuilderBase<Builder> {
        public Builder(Context context) {
            super(context);
        }

        public GuidedDatePickerAction build() {
            GuidedDatePickerAction action = new GuidedDatePickerAction();
            applyDatePickerValues(action);
            return action;
        }
    }

    public String getDatePickerFormat() {
        return this.mDatePickerFormat;
    }

    public long getDate() {
        return this.mDate;
    }

    public void setDate(long date) {
        this.mDate = date;
    }

    public long getMinDate() {
        return this.mMinDate;
    }

    public long getMaxDate() {
        return this.mMaxDate;
    }

    @Override // androidx.leanback.widget.GuidedAction
    public void onSaveInstanceState(Bundle bundle, String key) {
        bundle.putLong(key, getDate());
    }

    @Override // androidx.leanback.widget.GuidedAction
    public void onRestoreInstanceState(Bundle bundle, String key) {
        setDate(bundle.getLong(key, getDate()));
    }
}
