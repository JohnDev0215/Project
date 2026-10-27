package com.byd.autoservice.study;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.byd.autoservice.R;
import com.bydux.api.common.tools.cmd.BydAdbHelper;

/* JADX INFO: loaded from: classes.dex */
public class AutoStudyActivity extends Activity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1860b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1861c = 1001;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @SuppressLint({"HandlerLeak"})
    public final Handler f1862d = new a();

    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1001) {
                return;
            }
            TextView textView = AutoStudyActivity.this.f1860b;
            AutoStudyActivity autoStudyActivity = AutoStudyActivity.this;
            textView.setText(autoStudyActivity.b(autoStudyActivity.getApplicationContext()));
        }
    }

    public StringBuffer b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("study_auto", 0);
        StringBuffer stringBuffer = new StringBuffer();
        String string = sharedPreferences.getString("study_auto_content", "");
        if (string.equals("")) {
            stringBuffer.append("no change");
        } else {
            String[] strArrSplit = string.split("#");
            int i3 = 0;
            while (i3 < strArrSplit.length) {
                String[] strArrSplit2 = strArrSplit[i3].split(";");
                stringBuffer.append(context.getString(R.string.auto_study_title));
                StringBuilder sb = new StringBuilder();
                i3++;
                sb.append(i3);
                sb.append(":\n");
                stringBuffer.append(sb.toString());
                stringBuffer.append(context.getString(R.string.auto_study_time));
                stringBuffer.append(strArrSplit2[0]);
                stringBuffer.append(BydAdbHelper.COMMAND_LINE_END);
                for (int i4 = 1; i4 < strArrSplit2.length; i4++) {
                    stringBuffer.append(strArrSplit2[i4]);
                    stringBuffer.append(BydAdbHelper.COMMAND_LINE_END);
                }
                stringBuffer.append(BydAdbHelper.COMMAND_LINE_END);
            }
        }
        return stringBuffer;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_auto_study);
        this.f1860b = (TextView) findViewById(R.id.auto_study_text_id);
        this.f1862d.sendEmptyMessageDelayed(1001, 1000L);
    }
}
