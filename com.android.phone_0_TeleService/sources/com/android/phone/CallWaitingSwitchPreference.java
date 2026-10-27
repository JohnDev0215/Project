package com.android.phone;

import android.content.Context;
import android.os.AsyncResult;
import android.os.Handler;
import android.os.Message;
import android.preference.SwitchPreference;
import android.util.AttributeSet;
import android.util.Log;
import com.android.internal.telephony.CommandException;
import com.android.internal.telephony.Phone;

/* JADX INFO: loaded from: classes.dex */
public class CallWaitingSwitchPreference extends SwitchPreference {
    private final MyHandler mHandler;
    private Phone mPhone;
    private TimeConsumingPreferenceListener mTcpListener;

    public CallWaitingSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHandler = new MyHandler();
    }

    public CallWaitingSwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, android.R.attr.switchPreferenceStyle);
    }

    public CallWaitingSwitchPreference(Context context) {
        this(context, null);
    }

    void init(TimeConsumingPreferenceListener timeConsumingPreferenceListener, boolean z, Phone phone) {
        this.mPhone = phone;
        this.mTcpListener = timeConsumingPreferenceListener;
        if (z) {
            return;
        }
        phone.getCallWaiting(this.mHandler.obtainMessage(0, 0, 0));
        TimeConsumingPreferenceListener timeConsumingPreferenceListener2 = this.mTcpListener;
        if (timeConsumingPreferenceListener2 != null) {
            timeConsumingPreferenceListener2.onStarted(this, true);
        }
    }

    @Override // android.preference.TwoStatePreference, android.preference.Preference
    protected void onClick() {
        super.onClick();
        this.mPhone.setCallWaiting(isChecked(), this.mHandler.obtainMessage(1));
        TimeConsumingPreferenceListener timeConsumingPreferenceListener = this.mTcpListener;
        if (timeConsumingPreferenceListener != null) {
            timeConsumingPreferenceListener.onStarted(this, false);
        }
    }

    private class MyHandler extends Handler {
        private MyHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                handleGetCallWaitingResponse(message);
            } else {
                if (i != 1) {
                    return;
                }
                handleSetCallWaitingResponse(message);
            }
        }

        private void handleGetCallWaitingResponse(Message message) {
            AsyncResult asyncResult = (AsyncResult) message.obj;
            boolean z = false;
            if (CallWaitingSwitchPreference.this.mTcpListener != null) {
                if (message.arg2 == 1) {
                    CallWaitingSwitchPreference.this.mTcpListener.onFinished(CallWaitingSwitchPreference.this, false);
                } else {
                    CallWaitingSwitchPreference.this.mTcpListener.onFinished(CallWaitingSwitchPreference.this, true);
                }
            }
            Throwable th = asyncResult.exception;
            if (th instanceof CommandException) {
                if (CallWaitingSwitchPreference.this.mTcpListener != null) {
                    CallWaitingSwitchPreference.this.mTcpListener.onException(CallWaitingSwitchPreference.this, (CommandException) asyncResult.exception);
                    return;
                }
                return;
            }
            if ((asyncResult.userObj instanceof Throwable) || th != null) {
                if (CallWaitingSwitchPreference.this.mTcpListener != null) {
                    CallWaitingSwitchPreference.this.mTcpListener.onError(CallWaitingSwitchPreference.this, 400);
                    return;
                }
                return;
            }
            int[] iArr = (int[]) asyncResult.result;
            try {
                CallWaitingSwitchPreference callWaitingSwitchPreference = CallWaitingSwitchPreference.this;
                if (iArr[0] == 1 && (iArr[1] & 1) == 1) {
                    z = true;
                }
                callWaitingSwitchPreference.setChecked(z);
            } catch (ArrayIndexOutOfBoundsException e) {
                Log.e("CallWaitingSwitchPreference", "handleGetCallWaitingResponse: improper result: err =" + e.getMessage());
            }
        }

        private void handleSetCallWaitingResponse(Message message) {
            AsyncResult asyncResult = (AsyncResult) message.obj;
            Throwable th = asyncResult.exception;
            CallWaitingSwitchPreference.this.mPhone.getCallWaiting(obtainMessage(0, 1, 1, asyncResult.exception));
        }
    }
}
