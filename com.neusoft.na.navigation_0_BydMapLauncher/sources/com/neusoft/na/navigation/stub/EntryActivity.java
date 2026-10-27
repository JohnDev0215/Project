package com.neusoft.na.navigation.stub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import cerence.ark.assistant.library.JsonKey;
import com.ecarx.sdk.oauth2.IEventHandler;
import com.ecarx.sdk.oauth2.OAuth2API;
import com.ecarx.sdk.oauth2.base.BaseReq;
import com.ecarx.sdk.oauth2.base.BaseResp;
import com.ecarx.sdk.oauth2.event.SendAuth;
import com.neusoft.na.navigation.R;

/* JADX INFO: loaded from: classes2.dex */
public class EntryActivity extends Activity implements IEventHandler {
    private static final String TAG = "EntryActivity";

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_entry);
        handleIntent();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent();
    }

    private void handleIntent() {
        OAuth2API.getInstance().handleIntent(getIntent(), this);
    }

    @Override // com.ecarx.sdk.oauth2.IEventHandler
    public void onReq(BaseReq baseReq) {
        Log.e(TAG, "onReq: type = " + baseReq.getType());
        Toast.makeText(this, "开始授权 onReq: type =" + baseReq.getType(), 0).show();
    }

    @Override // com.ecarx.sdk.oauth2.IEventHandler
    public void onResp(BaseResp baseResp) {
        String str = TAG;
        Log.e(str, "onResp: type = " + baseResp.getType());
        if (baseResp.getType() == 1) {
            authResp(baseResp);
        } else {
            Log.e(str, "TYPE ERROR");
            Toast.makeText(this, "TYPE ERROR", 0).show();
        }
    }

    private void authResp(BaseResp baseResp) {
        String str = TAG;
        Log.e(str, "onResp: errorCode = " + baseResp.errCode);
        if (baseResp.errCode == 0) {
            String str2 = ((SendAuth.Resp) baseResp).code + "";
            Log.e(str, "onResp: token = " + str2);
            Toast.makeText(this, "Success onResp: token = " + str2, 0).show();
            Intent intent = new Intent();
            intent.setAction("AuthCodeAction");
            intent.putExtra(JsonKey.RESULTS_DATA, str2);
            sendBroadcast(intent);
            return;
        }
        Log.e(str, "CODE ERROR");
        Toast.makeText(this, "CODE ERROR:" + baseResp.errCode, 0).show();
    }
}
