package com.byd.sing.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.sing.R;
import com.byd.sing.h;
import com.byd.sing.j;

/* JADX INFO: loaded from: classes.dex */
public class SettingItem extends ConstraintLayout {
    private SeekBar A;
    private int B;
    private int C;
    private c D;
    private boolean E;
    private Toast F;
    private String y;
    private TextView z;

    class a implements SeekBar.OnSeekBarChangeListener {
        a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                SettingItem.this.B = i;
                SettingItem.this.z.setText(String.valueOf(i));
            }
            if (SettingItem.this.D != null) {
                SettingItem.this.D.onProgressChanged(seekBar, i, z);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    class b implements View.OnTouchListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f3301b;

        b(Context context) {
            this.f3301b = context;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (SettingItem.this.F != null) {
                SettingItem.this.F.cancel();
                SettingItem.this.F = null;
            }
            if (!SettingItem.this.E) {
                return false;
            }
            SettingItem settingItem = SettingItem.this;
            settingItem.F = Toast.makeText(this.f3301b, settingItem.getResources().getString(R.string.nonsupport_reverb), 0);
            SettingItem.this.F.show();
            return true;
        }
    }

    public static class c implements SeekBar.OnSeekBarChangeListener {
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    public SettingItem(Context context) {
        this(context, null);
    }

    private void E(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, h.U0);
        this.y = typedArrayObtainStyledAttributes.getString(1);
        this.B = typedArrayObtainStyledAttributes.getInt(2, 0);
        this.C = typedArrayObtainStyledAttributes.getInt(0, 10);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void F(Context context) {
        if (j.f().q()) {
            LayoutInflater.from(context).inflate(R.layout.layout_setting_item, (ViewGroup) this, true);
        } else {
            LayoutInflater.from(context).inflate(R.layout.layout_setting_item_denza, (ViewGroup) this, true);
        }
        LineTextView lineTextView = (LineTextView) findViewById(R.id.tv_name_zh);
        TextView textView = (TextView) findViewById(R.id.tv_name);
        if (G()) {
            lineTextView.setVisibility(0);
            textView.setVisibility(4);
            lineTextView.setText(this.y);
        } else {
            lineTextView.setVisibility(4);
            textView.setVisibility(0);
            textView.setText(this.y);
        }
        this.z = (TextView) findViewById(R.id.tv_value);
        this.A = (SeekBar) findViewById(R.id.sb_value);
        this.z.setText(String.valueOf(this.B));
        this.A.setProgress(this.B);
        this.A.setMax(this.C);
        this.A.setOnSeekBarChangeListener(new a());
        this.A.setOnTouchListener(new b(context));
    }

    private boolean G() {
        String language = getResources().getConfiguration().locale.getLanguage();
        com.byd.sing.a.a("language = " + language);
        return language.endsWith("zh");
    }

    public int getValue() {
        return this.B;
    }

    public void setMax(int i) {
        if (i >= 0) {
            this.C = i;
            this.A.setMax(i);
        }
    }

    public void setNotSupport(boolean z) {
        this.E = z;
    }

    public void setOnVolumeChangeListener(c cVar) {
        this.D = cVar;
    }

    public void setValue(int i) {
        com.byd.sing.a.a("value = " + i);
        if (i < 0 || i > this.C) {
            return;
        }
        this.B = i;
        this.z.setText(String.valueOf(i));
        this.A.setProgress(this.B);
    }

    public SettingItem(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SettingItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        E(context, attributeSet);
        F(context);
    }
}
