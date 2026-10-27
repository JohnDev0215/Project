package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.AbsSavedState;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.Preference;
import defpackage.n32;

/* JADX INFO: loaded from: classes.dex */
public class SeekBarPreference extends Preference {
    public int S;
    public int T;
    public int U;
    public int V;
    public boolean W;
    public SeekBar X;
    public TextView Y;
    public final boolean Z;
    public final boolean a0;
    public final boolean b0;
    public final a c0;
    public final b d0;

    public class a implements SeekBar.OnSeekBarChangeListener {
        public a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            SeekBarPreference seekBarPreference = SeekBarPreference.this;
            if (z && (seekBarPreference.b0 || !seekBarPreference.W)) {
                seekBarPreference.Z(seekBar);
                return;
            }
            int i2 = i + seekBarPreference.T;
            TextView textView = seekBarPreference.Y;
            if (textView != null) {
                textView.setText(String.valueOf(i2));
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStartTrackingTouch(SeekBar seekBar) {
            SeekBarPreference.this.W = true;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStopTrackingTouch(SeekBar seekBar) {
            SeekBarPreference seekBarPreference = SeekBarPreference.this;
            seekBarPreference.W = false;
            if (seekBar.getProgress() + seekBarPreference.T != seekBarPreference.S) {
                seekBarPreference.Z(seekBar);
            }
        }
    }

    public class b implements View.OnKeyListener {
        public b() {
        }

        @Override // android.view.View.OnKeyListener
        public final boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 0) {
                return false;
            }
            SeekBarPreference seekBarPreference = SeekBarPreference.this;
            if ((!seekBarPreference.Z && (i == 21 || i == 22)) || i == 23 || i == 66) {
                return false;
            }
            SeekBar seekBar = seekBarPreference.X;
            if (seekBar != null) {
                return seekBar.onKeyDown(i, keyEvent);
            }
            Log.e("SeekBarPreference", "SeekBar view is null and hence cannot be adjusted.");
            return false;
        }
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, 0);
        this.c0 = new a();
        this.d0 = new b();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SeekBarPreference, i, 0);
        this.T = typedArrayObtainStyledAttributes.getInt(R$styleable.SeekBarPreference_min, 0);
        int i3 = typedArrayObtainStyledAttributes.getInt(R$styleable.SeekBarPreference_android_max, 100);
        int i4 = this.T;
        i3 = i3 < i4 ? i4 : i3;
        if (i3 != this.U) {
            this.U = i3;
            s();
        }
        int i5 = typedArrayObtainStyledAttributes.getInt(R$styleable.SeekBarPreference_seekBarIncrement, 0);
        if (i5 != this.V) {
            this.V = Math.min(this.U - this.T, Math.abs(i5));
            s();
        }
        this.Z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SeekBarPreference_adjustable, true);
        this.a0 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SeekBarPreference_showSeekBarValue, false);
        this.b0 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SeekBarPreference_updatesContinuously, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public final Object C(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, 0));
    }

    @Override // androidx.preference.Preference
    public final void D(Parcelable parcelable) {
        if (!parcelable.getClass().equals(SavedState.class)) {
            super.D(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.D(savedState.getSuperState());
        this.S = savedState.e;
        this.T = savedState.f;
        this.U = savedState.g;
        s();
    }

    @Override // androidx.preference.Preference
    public final Parcelable E() {
        this.O = true;
        AbsSavedState absSavedState = AbsSavedState.EMPTY_STATE;
        if (this.w) {
            return absSavedState;
        }
        SavedState savedState = new SavedState(absSavedState);
        savedState.e = this.S;
        savedState.f = this.T;
        savedState.g = this.U;
        return savedState;
    }

    @Override // androidx.preference.Preference
    public final void F(Object obj) {
        if (obj == null) {
            obj = 0;
        }
        int iIntValue = ((Integer) obj).intValue();
        if (W()) {
            iIntValue = this.f.c().getInt(this.p, iIntValue);
        }
        Y(iIntValue, true);
    }

    public final void Y(int i, boolean z) {
        int i2 = this.T;
        if (i < i2) {
            i = i2;
        }
        int i3 = this.U;
        if (i > i3) {
            i = i3;
        }
        if (i != this.S) {
            this.S = i;
            TextView textView = this.Y;
            if (textView != null) {
                textView.setText(String.valueOf(i));
            }
            if (W()) {
                int i4 = ~i;
                if (W()) {
                    i4 = this.f.c().getInt(this.p, i4);
                }
                if (i != i4) {
                    SharedPreferences.Editor editorB = this.f.b();
                    editorB.putInt(this.p, i);
                    if (!this.f.e) {
                        editorB.apply();
                    }
                }
            }
            if (z) {
                s();
            }
        }
    }

    public final void Z(SeekBar seekBar) {
        int progress = seekBar.getProgress() + this.T;
        if (progress != this.S) {
            if (h(Integer.valueOf(progress))) {
                Y(progress, false);
                return;
            }
            seekBar.setProgress(this.S - this.T);
            int i = this.S;
            TextView textView = this.Y;
            if (textView != null) {
                textView.setText(String.valueOf(i));
            }
        }
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        n32Var.e.setOnKeyListener(this.d0);
        this.X = (SeekBar) n32Var.u(R$id.seekbar);
        TextView textView = (TextView) n32Var.u(R$id.seekbar_value);
        this.Y = textView;
        if (this.a0) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
            this.Y = null;
        }
        SeekBar seekBar = this.X;
        if (seekBar == null) {
            Log.e("SeekBarPreference", "SeekBar view is null in onBindViewHolder.");
            return;
        }
        seekBar.setOnSeekBarChangeListener(this.c0);
        this.X.setMax(this.U - this.T);
        int i = this.V;
        if (i != 0) {
            this.X.setKeyProgressIncrement(i);
        } else {
            this.V = this.X.getKeyProgressIncrement();
        }
        this.X.setProgress(this.S - this.T);
        int i2 = this.S;
        TextView textView2 = this.Y;
        if (textView2 != null) {
            textView2.setText(String.valueOf(i2));
        }
        this.X.setEnabled(q());
    }

    public static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int e;
        public int f;
        public int g;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            public final SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
        }

        public SavedState(AbsSavedState absSavedState) {
            super(absSavedState);
        }
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.seekBarPreferenceStyle);
    }

    public SeekBarPreference(Context context) {
        this(context, null);
    }
}
