package com.byd.music.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public class ViewWarn extends LinearLayout {
    View view;

    public interface OnClick {
        void onClick();
    }

    public ViewWarn(Context context) {
        super(context);
        this.view = inflate(context, R.layout.view_online, this);
    }

    public ViewWarn(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.view = inflate(context, R.layout.view_online, this);
    }

    public ViewWarn(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.view = inflate(context, R.layout.view_online, this);
    }

    public void initNoneNetView(int i, String str, final OnClick onClick) {
        this.view.findViewById(R.id.ctl_warn_net).setVisibility(0);
        TextView textView = (TextView) this.view.findViewById(R.id.tv_warn_online_title);
        textView.setText(str);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.ViewWarn.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                onClick.onClick();
            }
        });
        findViewById(R.id.btn_warn_online_update).setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.ViewWarn.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                onClick.onClick();
            }
        });
    }
}
