package com.byd.music.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.util.Utils;
import com.byd.music.R;
import com.byd.music.common.AppExecutors;

/* JADX INFO: loaded from: classes5.dex */
public class ToastUtil {
    private static Toast toast;

    public static void showCenter(final String str) {
        AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.utils.-$$Lambda$ToastUtil$vvAhmTtBiGklNQ_HPo918Yt1SRU
            @Override // java.lang.Runnable
            public final void run() {
                ToastUtil.lambda$showCenter$0(str);
            }
        });
    }

    static /* synthetic */ void lambda$showCenter$0(String str) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
            toast = null;
        }
        Toast toastMakeText = Toast.makeText(Utils.getApp(), str, 0);
        toast = toastMakeText;
        toastMakeText.show();
    }

    public static void showCenterToast(final Context context, final String str, final boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.utils.-$$Lambda$ToastUtil$a7lBuHgY9_qvWEcU9FE48pfCNCY
            @Override // java.lang.Runnable
            public final void run() {
                ToastUtil.lambda$showCenterToast$1(context, str, z);
            }
        });
    }

    static /* synthetic */ void lambda$showCenterToast$1(Context context, String str, boolean z) {
        Toast toast2 = new Toast(context);
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.music_toast_layout, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) constraintLayout.findViewById(R.id.ll_toast);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.bottomMargin = 0;
        layoutParams.topMargin = 0;
        linearLayout.setLayoutParams(layoutParams);
        TextView textView = (TextView) constraintLayout.findViewById(R.id.tv_toast);
        textView.setText(str);
        textView.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.color_text_white_front));
        ImageView imageView = (ImageView) constraintLayout.findViewById(R.id.iv_toast);
        imageView.setVisibility(z ? 0 : 8);
        if (!z) {
            textView.setPadding((int) context.getResources().getDimension(R.dimen.dp_24), 0, (int) context.getResources().getDimension(R.dimen.dp_24), 0);
        } else {
            textView.setPadding(0, 0, (int) context.getResources().getDimension(R.dimen.dp_24), 0);
            imageView.setPadding((int) context.getResources().getDimension(R.dimen.dp_13), 0, 0, 0);
        }
        toast2.setView(constraintLayout);
        toast2.setGravity(17, 0, 0);
        toast2.show();
        if (Build.VERSION.SDK_INT >= 30) {
            toast2.addCallback(new Toast.Callback() { // from class: com.byd.music.utils.ToastUtil.1
                @Override // android.widget.Toast.Callback
                public void onToastShown() {
                    super.onToastShown();
                }

                @Override // android.widget.Toast.Callback
                public void onToastHidden() {
                    super.onToastHidden();
                }
            });
        }
    }
}
