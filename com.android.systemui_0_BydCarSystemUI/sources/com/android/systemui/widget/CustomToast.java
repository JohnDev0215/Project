package com.android.systemui.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.TextView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.base.BaseToast;

/* JADX INFO: loaded from: classes.dex */
public class CustomToast extends BaseToast implements DialogInterface.OnShowListener {
    private String text;
    private TextView textView;

    @Override // com.android.systemui.base.BaseToast
    protected int getLayoutId() {
        return R.layout.view_custom_toast;
    }

    public CustomToast(Context context) {
        super(context);
    }

    public CustomToast(Context context, String text) {
        super(context);
        this.text = text;
    }

    @Override // com.android.systemui.base.BaseToast
    protected void initSize() {
        this.mGravity = 49;
        if (isLand(this.mContext)) {
            this.mLocationY = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_128);
        } else {
            this.mLocationY = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_246);
        }
    }

    @Override // com.android.systemui.base.BaseToast
    protected void initData() {
        this.textView = (TextView) findViewById(R.id.tv_content);
        setOnShowListener(this);
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialog) {
        if (!TextUtils.isEmpty(this.text)) {
            this.textView.setText(this.text);
        }
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.widget.CustomToast.1
            @Override // java.lang.Runnable
            public void run() {
                CustomToast.this.dismiss();
            }
        }, 2000L);
    }

    public boolean isLand(Context context) {
        return context.getResources().getConfiguration().orientation != 1;
    }
}
