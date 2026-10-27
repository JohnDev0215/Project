package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.Preference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class SeekBarPreference extends Preference {
    int P;
    int Q;
    private int R;
    private int S;
    boolean T;
    SeekBar U;
    private TextView V;
    boolean W;
    private boolean X;
    boolean Y;
    private SeekBar.OnSeekBarChangeListener Z;
    private View.OnKeyListener a0;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements SeekBar.OnSeekBarChangeListener {
        a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                SeekBarPreference seekBarPreference = SeekBarPreference.this;
                if (seekBarPreference.Y || !seekBarPreference.T) {
                    SeekBarPreference.this.H0(seekBar);
                    return;
                }
            }
            SeekBarPreference seekBarPreference2 = SeekBarPreference.this;
            seekBarPreference2.I0(i + seekBarPreference2.Q);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            SeekBarPreference.this.T = true;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            SeekBarPreference.this.T = false;
            int progress = seekBar.getProgress();
            SeekBarPreference seekBarPreference = SeekBarPreference.this;
            if (progress + seekBarPreference.Q != seekBarPreference.P) {
                seekBarPreference.H0(seekBar);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements View.OnKeyListener {
        b() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 0) {
                return false;
            }
            if ((!SeekBarPreference.this.W && (i == 21 || i == 22)) || i == 23 || i == 66) {
                return false;
            }
            SeekBar seekBar = SeekBarPreference.this.U;
            if (seekBar != null) {
                return seekBar.onKeyDown(i, keyEvent);
            }
            Log.e("SeekBarPreference", "SeekBar view is null and hence cannot be adjusted.");
            return false;
        }
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.Z = new a();
        this.a0 = new b();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SeekBarPreference, i, i2);
        this.Q = typedArrayObtainStyledAttributes.getInt(R$styleable.SeekBarPreference_min, 0);
        E0(typedArrayObtainStyledAttributes.getInt(R$styleable.SeekBarPreference_android_max, 100));
        F0(typedArrayObtainStyledAttributes.getInt(R$styleable.SeekBarPreference_seekBarIncrement, 0));
        this.W = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SeekBarPreference_adjustable, true);
        this.X = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SeekBarPreference_showSeekBarValue, false);
        this.Y = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SeekBarPreference_updatesContinuously, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void G0(int i, boolean z) {
        int i2 = this.Q;
        if (i < i2) {
            i = i2;
        }
        int i3 = this.R;
        if (i > i3) {
            i = i3;
        }
        if (i != this.P) {
            this.P = i;
            I0(i);
            Y(i);
            if (z) {
                I();
            }
        }
    }

    public final void E0(int i) {
        int i2 = this.Q;
        if (i < i2) {
            i = i2;
        }
        if (i != this.R) {
            this.R = i;
            I();
        }
    }

    public final void F0(int i) {
        if (i != this.S) {
            this.S = Math.min(this.R - this.Q, Math.abs(i));
            I();
        }
    }

    void H0(SeekBar seekBar) {
        int progress = this.Q + seekBar.getProgress();
        if (progress != this.P) {
            if (d(Integer.valueOf(progress))) {
                G0(progress, false);
            } else {
                seekBar.setProgress(this.P - this.Q);
                I0(this.P);
            }
        }
    }

    void I0(int i) {
        TextView textView = this.V;
        if (textView != null) {
            textView.setText(String.valueOf(i));
        }
    }

    @Override // androidx.preference.Preference
    public void M(f fVar) {
        super.M(fVar);
        fVar.f1092a.setOnKeyListener(this.a0);
        this.U = (SeekBar) fVar.M(R$id.seekbar);
        TextView textView = (TextView) fVar.M(R$id.seekbar_value);
        this.V = textView;
        if (this.X) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
            this.V = null;
        }
        SeekBar seekBar = this.U;
        if (seekBar == null) {
            Log.e("SeekBarPreference", "SeekBar view is null in onBindViewHolder.");
            return;
        }
        seekBar.setOnSeekBarChangeListener(this.Z);
        this.U.setMax(this.R - this.Q);
        int i = this.S;
        if (i != 0) {
            this.U.setKeyProgressIncrement(i);
        } else {
            this.S = this.U.getKeyProgressIncrement();
        }
        this.U.setProgress(this.P - this.Q);
        I0(this.P);
        this.U.setEnabled(E());
    }

    @Override // androidx.preference.Preference
    protected Object Q(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, 0));
    }

    @Override // androidx.preference.Preference
    protected void T(Parcelable parcelable) {
        if (!parcelable.getClass().equals(SavedState.class)) {
            super.T(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.T(savedState.getSuperState());
        this.P = savedState.f1050c;
        this.Q = savedState.d;
        this.R = savedState.e;
        I();
    }

    @Override // androidx.preference.Preference
    protected Parcelable U() {
        Parcelable parcelableU = super.U();
        if (F()) {
            return parcelableU;
        }
        SavedState savedState = new SavedState(parcelableU);
        savedState.f1050c = this.P;
        savedState.d = this.Q;
        savedState.e = this.R;
        return savedState;
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1050c;
        int d;
        int e;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Parcelable.Creator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.f1050c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1050c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.seekBarPreferenceStyle);
    }
}
