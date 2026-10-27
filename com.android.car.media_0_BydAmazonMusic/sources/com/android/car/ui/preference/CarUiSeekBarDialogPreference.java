package com.android.car.ui.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$string;
import spi.b2;
import spi.fc;
import spi.i3;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class CarUiSeekBarDialogPreference extends DialogPreference implements i3, fc {
    public int a;
    public SeekBar b;
    public int c;
    public TextView d;
    public String e;
    public int f;
    public TextView g;
    public String h;
    public int i;
    public TextView j;
    public String k;
    public SeekBar.OnSeekBarChangeListener l;
    public int m;

    public CarUiSeekBarDialogPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.m = 100;
        b();
    }

    public final void b() {
        setDialogLayoutResource(R$layout.car_ui_seekbar_dialog);
        setPositiveButtonText(R$string.car_ui_dialog_preference_positive);
        setNegativeButtonText(R$string.car_ui_dialog_preference_negative);
    }

    @Override // androidx.preference.Preference
    public final void onAttached() {
        super.onAttached();
        this.a = getPersistedInt(0);
    }

    @Override // spi.i3
    public final void onBindDialogView(@NonNull View view) {
        this.b = (SeekBar) b2.d(view, R$id.seek_bar);
        this.d = (TextView) b2.d(view, R$id.seek_bar_text_top);
        this.g = (TextView) b2.d(view, R$id.seek_bar_text_left);
        this.j = (TextView) b2.d(view, R$id.seek_bar_text_right);
        int i = this.a;
        SeekBar seekBar = this.b;
        if (seekBar != null) {
            seekBar.setProgress(i);
        }
        this.a = i;
        int i2 = this.c;
        TextView textView = this.d;
        if (textView != null) {
            textView.setVisibility(i2);
        }
        this.c = i2;
        String str = this.e;
        TextView textView2 = this.d;
        if (textView2 != null) {
            textView2.setText(str);
        }
        this.e = str;
        int i3 = this.f;
        TextView textView3 = this.g;
        if (textView3 != null) {
            textView3.setVisibility(i3);
        }
        this.f = i3;
        String str2 = this.h;
        TextView textView4 = this.g;
        if (textView4 != null) {
            textView4.setText(str2);
        }
        this.h = str2;
        int i4 = this.i;
        TextView textView5 = this.j;
        if (textView5 != null) {
            textView5.setVisibility(i4);
        }
        this.i = i4;
        String str3 = this.k;
        TextView textView6 = this.j;
        if (textView6 != null) {
            textView6.setText(str3);
        }
        this.k = str3;
        int i5 = this.m;
        SeekBar seekBar2 = this.b;
        if (seekBar2 != null) {
            seekBar2.setMax(i5);
        }
        this.m = i5;
        setOnSeekBarChangeListener(this.l);
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        b2.l(preferenceViewHolder.itemView, false);
    }

    @Override // spi.i3
    public final void onDialogClosed(boolean z) {
        if (z) {
            int progress = this.b.getProgress();
            this.a = progress;
            persistInt(progress);
            notifyChanged();
        }
        this.d = null;
        this.j = null;
        this.g = null;
        this.b = null;
    }

    @Override // androidx.preference.Preference
    public final void performClick() {
        if (!isEnabled()) {
            isSelectable();
        }
        super.performClick();
    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        SeekBar seekBar = this.b;
        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        }
        this.l = onSeekBarChangeListener;
    }

    public CarUiSeekBarDialogPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = 100;
        b();
    }

    public CarUiSeekBarDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = 100;
        b();
    }

    public CarUiSeekBarDialogPreference(Context context) {
        super(context);
        this.m = 100;
        b();
    }
}
