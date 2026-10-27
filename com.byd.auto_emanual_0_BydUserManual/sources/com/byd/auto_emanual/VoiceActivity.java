package com.byd.auto_emanual;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.byd.auto_emanual.fragment.ClassifyData;
import com.byd.auto_emanual.fragment.NormalDataAdapter;
import com.byd.auto_emanual.fragment.Page;
import com.byd.auto_emanual.fragment.Presenter;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.SearchUtil;
import com.byd.auto_emanual.util.SharedPrefUtil;
import com.byd.auto_emanual.util.Utils;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class VoiceActivity extends Activity {
    private static int MAX_TRY_COUNT = 10;
    private static int MSG_START_MEDIA_ACTIVITY = 1;
    private static final String TAG = "VoiceActivity";
    private static int TIME_DELAY_START_MEDIA_ACTIVITY = 100;
    private static int TRY_COUNT;
    private MyHandler myHandler = new MyHandler(this);

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.d(TAG, "onCreate " + getIntent().toString());
        getWindow().setNavigationBarColor(0);
        getWindow().setStatusBarColor(0);
        int iCheckSelfPermission = checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        int iCheckSelfPermission2 = checkSelfPermission("android.permission.BYDAUTO_BODYWORK_COMMON");
        int iCheckSelfPermission3 = checkSelfPermission("android.permission.BYDAUTO_SETTING_COMMON");
        if (iCheckSelfPermission != 0 || iCheckSelfPermission2 != 0 || iCheckSelfPermission3 != 0) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.BYDAUTO_BODYWORK_COMMON", "android.permission.BYDAUTO_SETTING_COMMON"}, 2);
        } else {
            startMediaActivity();
            finish();
        }
    }

    private void startMediaActivity() {
        new Presenter(null).loadData(Page.JSON_DATA);
        if (!Utils.notShowVideo(getApplicationContext())) {
            new Presenter(null).loadData(Page.RECOMMEND);
            new Presenter(null).loadData(Page.START);
        }
        if (getIntent() == null || !Parameters.ACTION_START_FROM_VOICE.equals(getIntent().getAction())) {
            return;
        }
        startMediaActivity(getIntent());
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        String str = TAG;
        MLog.d(str, "onRequestPermissionsResult " + grantResults.length);
        if (requestCode == 2 && grantResults.length >= 3) {
            if (grantResults[0] == 0 && grantResults[1] == 0 && grantResults[2] == 0) {
                MLog.i(str, "permission granted.");
                SharedPrefUtil.save(getApplicationContext(), MainActivity.HAS_PERMISSION, "1");
                startMediaActivity();
            } else {
                MLog.i(str, "permission not granted.");
            }
        }
        finish();
    }

    private void startMediaActivity(Intent intent) {
        TRY_COUNT++;
        Message messageObtainMessage = this.myHandler.obtainMessage();
        messageObtainMessage.what = MSG_START_MEDIA_ACTIVITY;
        messageObtainMessage.obj = intent;
        this.myHandler.removeMessages(messageObtainMessage.what);
        this.myHandler.sendMessageDelayed(messageObtainMessage, TIME_DELAY_START_MEDIA_ACTIVITY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealIntentFromVoice(Intent intent) {
        String stringExtra = intent.getStringExtra(Parameters.KEY_FROM_VOICE);
        String stringExtra2 = intent.getStringExtra(Parameters.TITLE_FROM_VOICE);
        String stringExtra3 = intent.getStringExtra(Parameters.TYPE_FROM_VOICE);
        String stringExtra4 = intent.getStringExtra(Parameters.ID_FROM_VOICE);
        String str = TAG;
        MLog.d(str, "ACTION_START_FROM_VOICE " + stringExtra + ", " + stringExtra2 + ", " + stringExtra3 + ", " + stringExtra4);
        ClassifyData classifyDataSearchByVoidId = SearchUtil.searchByVoidId(getApplicationContext(), stringExtra4);
        if (classifyDataSearchByVoidId.getContentItemSize() <= 0 && TRY_COUNT < MAX_TRY_COUNT) {
            MLog.i(str, "no result from voice. " + stringExtra4);
            startMediaActivity(intent);
        } else {
            if (TRY_COUNT >= MAX_TRY_COUNT) {
                return;
            }
            TRY_COUNT = 0;
            NormalDataAdapter.getSearchData(classifyDataSearchByVoidId);
            int indexByHeader = classifyDataSearchByVoidId.getIndexByHeader(stringExtra4);
            ContentItem byPosition = classifyDataSearchByVoidId.getByPosition(indexByHeader);
            NormalDataAdapter.startMediaActivity(classifyDataSearchByVoidId.getClassify(), byPosition.getHeader(), byPosition.getCoverPath(), byPosition.getPath(), byPosition.getContentItemType(), indexByHeader, classifyDataSearchByVoidId.getModelTypeName());
        }
    }

    private static class MyHandler extends Handler {
        private WeakReference<VoiceActivity> handler;

        public MyHandler(VoiceActivity voiceActivity) {
            this.handler = new WeakReference<>(voiceActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == VoiceActivity.MSG_START_MEDIA_ACTIVITY) {
                this.handler.get().dealIntentFromVoice((Intent) msg.obj);
            }
        }
    }
}
