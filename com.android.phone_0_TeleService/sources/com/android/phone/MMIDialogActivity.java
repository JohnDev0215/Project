package com.android.phone;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.internal.telephony.CallManager;
import com.android.internal.telephony.MmiCode;
import com.android.internal.telephony.Phone;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MMIDialogActivity extends Activity {
    private static final String TAG = MMIDialogActivity.class.getSimpleName();
    private CallManager mCM = PhoneGlobals.getInstance().getCallManager();
    private Handler mHandler;
    private Dialog mMMIDialog;
    private Phone mPhone;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        String str = TAG;
        super.onCreate(bundle);
        int intExtra = getIntent().getIntExtra("subscription", Integer.MAX_VALUE);
        Phone phone = PhoneGlobals.getPhone(intExtra);
        this.mPhone = phone;
        if (phone == null) {
            Log.w(str, "onCreate: invalid subscription id (" + intExtra + ") lead to null Phone.");
            finish();
        }
        this.mHandler = new Handler() { // from class: com.android.phone.MMIDialogActivity.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 52) {
                    MMIDialogActivity.this.onMMIComplete((MmiCode) ((AsyncResult) message.obj).result);
                } else {
                    if (i != 53) {
                        return;
                    }
                    MMIDialogActivity.this.onMMICancel();
                }
            }
        };
        Log.d(str, "onCreate; registering for mmi complete.");
        this.mCM.registerForMmiComplete(this.mHandler, 52, (Object) null);
        showMMIDialog();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Dialog dialog = this.mMMIDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mMMIDialog = null;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            this.mCM.unregisterForMmiComplete(handler);
            this.mHandler = null;
        }
    }

    private void showMMIDialog() {
        String str = TAG;
        ArrayList arrayList = new ArrayList(this.mPhone.getPendingMmiCodes());
        if (this.mPhone.getImsPhone() != null) {
            arrayList.addAll(this.mPhone.getImsPhone().getPendingMmiCodes());
        }
        if (arrayList.size() > 0) {
            MmiCode mmiCode = (MmiCode) arrayList.get(0);
            Message messageObtain = Message.obtain(this.mHandler, 53);
            Log.d(str, "showMMIDialog: mmiCode = " + mmiCode);
            this.mMMIDialog = PhoneUtils.displayMMIInitiate(this, mmiCode, messageObtain, this.mMMIDialog);
            return;
        }
        Log.d(str, "showMMIDialog: no pending MMIs; finishing");
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMMIComplete(MmiCode mmiCode) {
        String str = TAG;
        Log.d(str, "onMMIComplete: mmi=" + mmiCode);
        int phoneType = this.mPhone.getPhoneType();
        if (phoneType == 2) {
            PhoneUtils.displayMMIComplete(this.mPhone, this, mmiCode, null, null);
            return;
        }
        if (phoneType == 1) {
            if (mmiCode.getState() != MmiCode.State.PENDING) {
                Log.d(str, "onMMIComplete: Got MMI_COMPLETE, finishing dialog activity...");
                dismissDialogsAndFinish();
            } else {
                Log.d(str, "onMMIComplete: still pending.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMMICancel() {
        String str = TAG;
        Log.v(str, "onMMICancel()...");
        PhoneUtils.cancelMmiCode(this.mPhone);
        Log.d(str, "onMMICancel: finishing MMI dialog...");
        dismissDialogsAndFinish();
    }

    private void dismissDialogsAndFinish() {
        Dialog dialog = this.mMMIDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mMMIDialog = null;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            this.mCM.unregisterForMmiComplete(handler);
            this.mHandler = null;
        }
        Log.v(TAG, "dismissDialogsAndFinish");
        finish();
    }
}
