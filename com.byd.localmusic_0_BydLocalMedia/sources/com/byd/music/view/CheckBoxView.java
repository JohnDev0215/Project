package com.byd.music.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import com.byd.music.R;
import com.byd.music.utils.binding.BindingUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public class CheckBoxView extends LinearLayout {
    Context mContext;

    public CheckBoxView(Context context) {
        this(context, null);
    }

    public CheckBoxView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CheckBoxView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void setParam(String str, boolean z, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, Object obj, boolean z2) {
        View viewInflate = ((LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_check_box, (ViewGroup) this, true);
        SwitchCompat switchCompat = (SwitchCompat) viewInflate.findViewById(R.id.settings_switch);
        TextView textView = (TextView) viewInflate.findViewById(R.id.settings_switch_text);
        if (z2) {
            BindingUtils.setConstraintCustomRTL(switchCompat, 0, textView.getId(), 0, 1);
            BindingUtils.setConstraintCustomRTL(textView, 1, 0, switchCompat.getId(), 0);
        } else if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
            BindingUtils.setConstraintCustomAndChain(switchCompat, 1, 0, textView.getId(), 0, 1);
            BindingUtils.setConstraintCustomAndChain(textView, 0, switchCompat.getId(), 0, 1, 1);
        } else {
            BindingUtils.setConstraintCustomAndChain(switchCompat, 1, 0, textView.getId(), 0, 1);
            BindingUtils.setConstraintCustomAndChain(textView, 0, switchCompat.getId(), 0, 0, 1);
        }
        textView.setText(str);
        switchCompat.setChecked(z);
        switchCompat.setOnCheckedChangeListener(onCheckedChangeListener);
        switchCompat.setTag(obj);
    }

    private void init(Context context) {
        this.mContext = context;
    }

    public boolean isCheck() {
        return ((SwitchCompat) findViewById(R.id.settings_switch)).isChecked();
    }

    public void setCheck(boolean z) {
        ((SwitchCompat) findViewById(R.id.settings_switch)).setChecked(z);
    }
}
