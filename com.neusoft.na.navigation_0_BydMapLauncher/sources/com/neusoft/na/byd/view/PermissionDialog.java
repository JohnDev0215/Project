package com.neusoft.na.byd.view;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.neusoft.na.byd.R;
import com.neusoft.na.common.BasicPrivacyView;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PermissionDialog extends BasicPrivacyView {
    private boolean mOnlyCall;
    private List<String> permissionList;

    public PermissionDialog(Context context) {
        this(context, null);
    }

    public PermissionDialog(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.my_dialog, this);
    }

    @Override // com.neusoft.na.common.BasicPrivacyView
    public void setPermissionList(List<String> list, boolean z) {
        super.setPermissionList(list, z);
        this.permissionList = list;
        this.mOnlyCall = z;
        onDialogViewCreated();
    }

    private void onDialogViewCreated() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_necessary);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.ll_phone);
        TextView textView = (TextView) findViewById(R.id.tv_exit);
        TextView textView2 = (TextView) findViewById(R.id.tv_setting);
        if (isCallPhone()) {
            linearLayout.setVisibility(8);
            linearLayout2.setVisibility(0);
            if (this.mOnlyCall) {
                textView.setText(R.string.permission_deny);
            } else {
                textView.setText(R.string.permission_continue);
            }
        } else {
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(8);
            LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.ll_necessary_child);
            textView.setText(R.string.permission_exit);
            List<String> list = this.permissionList;
            if (list != null) {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    String hint = getHint(it.next());
                    if (!TextUtils.isEmpty(hint)) {
                        TextView textView3 = new TextView(getContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                        textView3.setTextColor(ContextCompat.getColor(getContext(), R.color.permission_hint_text));
                        textView3.setTextSize(1, 16.0f);
                        textView3.setText(hint);
                        linearLayout3.addView(textView3, layoutParams);
                    }
                }
            }
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.byd.view.PermissionDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m153x259d838f(view);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.byd.view.PermissionDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m154x2d02b8ae(view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onDialogViewCreated$0$com-neusoft-na-byd-view-PermissionDialog, reason: not valid java name */
    /* synthetic */ void m153x259d838f(View view) {
        if (this.mListener != null) {
            this.mListener.onClickAgree();
        }
    }

    /* JADX INFO: renamed from: lambda$onDialogViewCreated$1$com-neusoft-na-byd-view-PermissionDialog, reason: not valid java name */
    /* synthetic */ void m154x2d02b8ae(View view) {
        if (this.mListener != null) {
            if (isCallPhone()) {
                this.mListener.onClickChange();
            } else {
                this.mListener.onClickCancel();
            }
        }
    }

    private boolean isCallPhone() {
        List<String> list = this.permissionList;
        return list != null && list.size() == 1 && this.permissionList.get(0).equals("android.permission.CALL_PHONE");
    }

    private String getHint(String str) {
        str.hashCode();
        switch (str) {
            case "android.permission.ACCESS_FINE_LOCATION":
                return getString(R.string.permission_location);
            case "android.permission.BYDAUTO_AUDIO_COMMON":
                return getString(R.string.permission_audio);
            case "android.permission.BYDAUTO_BODYWORK_COMMON":
                return getString(R.string.permission_bodywork);
            case "android.permission.BYDAUTO_INSTRUMENT_COMMON":
                return getString(R.string.permission_instrument);
            case "android.permission.BYDAUTO_YUN_COMMON":
                return getString(R.string.permission_yun);
            case "android.permission.BYDAUTO_STATISTIC_COMMON":
                return getString(R.string.permission_statistic);
            case "android.permission.WRITE_EXTERNAL_STORAGE":
                return getString(R.string.permission_write_storage);
            case "android.permission.BYDAUTO_GEARBOX_COMMON":
                return getString(R.string.permission_gear);
            default:
                return null;
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_root);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.ll_necessary_child);
        TextView textView = (TextView) findViewById(R.id.tv_title);
        TextView textView2 = (TextView) findViewById(R.id.tv_title_phone);
        TextView textView3 = (TextView) findViewById(R.id.tv_setting);
        TextView textView4 = (TextView) findViewById(R.id.tv_exit);
        linearLayout.setBackgroundColor(getContext().getColor(R.color.permission_top_bg));
        linearLayout.setBackgroundResource(R.drawable.permission_top_bg);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.permission_hint_text));
        textView.setText(getString(R.string.permission_necessary));
        textView2.setTextColor(ContextCompat.getColor(getContext(), R.color.permission_hint_text));
        textView2.setText(getString(R.string.permission_phone));
        textView3.setBackgroundColor(getContext().getColor(R.color.permission_agree_bg_start));
        textView3.setBackgroundResource(R.drawable.permission_agree_bg);
        textView3.setTextColor(ContextCompat.getColor(getContext(), R.color.permission_agree_text));
        textView3.setText(getString(R.string.permission_setting));
        textView4.setBackgroundColor(getContext().getColor(R.color.permission_exit));
        textView4.setBackgroundResource(R.drawable.permission_exit_bg);
        textView4.setTextColor(ContextCompat.getColor(getContext(), R.color.permission_exit_text));
        textView4.setText(getString(R.string.permission_exit));
        List<String> list = this.permissionList;
        if (list != null) {
            Iterator<String> it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                String hint = getHint(it.next());
                if (!TextUtils.isEmpty(hint) && i < linearLayout2.getChildCount()) {
                    TextView textView5 = (TextView) linearLayout2.getChildAt(i);
                    textView5.setTextColor(ContextCompat.getColor(getContext(), R.color.permission_hint_text));
                    textView5.setText(hint);
                    i++;
                }
            }
        }
    }

    private String getString(int i) {
        return getContext().getString(i);
    }
}
