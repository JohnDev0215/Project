package com.byd.music.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.ThreadUtils;
import com.byd.music.R;
import com.byd.music.common.AppExecutors;

/* JADX INFO: loaded from: classes5.dex */
public class MusicToastUtils1 {
    public static final int SHOW_TYPE_FAILED = 4;
    public static final int SHOW_TYPE_LONG = 2;
    public static final int SHOW_TYPE_SHORT = 1;
    public static final int SHOW_TYPE_SUCCESS = 3;
    private static String mText;
    private static long startMills;

    public static void showToast(final Context context, final String str, final int i) {
        LogUtil.e("MusicToastUtils1", "showToast context = " + context + " ; text = " + str + " ; showType = " + i);
        if (TextUtils.equals(mText, str) && Math.abs(System.currentTimeMillis() - startMills) < 2000) {
            LogUtil.i(" MusicToastUtils1 --> showToast: show in 2s and return");
            return;
        }
        mText = str;
        if (!ThreadUtils.isMainThread()) {
            AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.utils.MusicToastUtils1.1
                @Override // java.lang.Runnable
                public void run() {
                    MusicToastUtils1.makeText(context, str, i);
                }
            });
        } else {
            makeText(context, str, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void makeText(Context context, String str, int i) {
        Toast toastMakeText = Toast.makeText(context, str, i == 1 ? 0 : 1);
        LogUtil.e("MusicToastUtils1", "makeText = " + context + " ; text = " + str + " ; showType = " + i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.success_toast_layout, (ViewGroup) null, false);
        ((TextView) viewInflate.findViewById(R.id.tv_toast)).setText(str);
        if (i == 3 || i == 4) {
            ((ImageView) viewInflate.findViewById(R.id.iv_toast)).setImageResource(i == 3 ? R.drawable.icon_toast_ok : R.drawable.icon_toast_failed);
            ((ImageView) viewInflate.findViewById(R.id.iv_toast)).setVisibility(0);
        }
        toastMakeText.setView(viewInflate);
        toastMakeText.setGravity(48, 0, 100);
        toastMakeText.show();
        startMills = System.currentTimeMillis();
    }
}
