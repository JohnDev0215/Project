package com.android.phone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.text.method.DigitsKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.internal.telephony.CommandException;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.phone.settings.fdn.EditPinPreference;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class CallBarringEditPreference extends EditPinPreference {
    private int mButtonClicked;
    private CharSequence mDisableText;
    private CharSequence mEnableText;
    private String mFacility;
    private final MyHandler mHandler;
    boolean mIsActivated;
    private Phone mPhone;
    private CharSequence mSummaryOff;
    private CharSequence mSummaryOn;
    private TimeConsumingPreferenceListener mTcpListener;

    public CallBarringEditPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsActivated = false;
        this.mHandler = new MyHandler();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, android.R.styleable.CheckBoxPreference, 0, 0);
        this.mSummaryOn = typedArrayObtainStyledAttributes.getString(0);
        this.mSummaryOff = typedArrayObtainStyledAttributes.getString(1);
        this.mDisableText = context.getText(R.string.disable);
        this.mEnableText = context.getText(R.string.enable);
        typedArrayObtainStyledAttributes.recycle();
        this.mPhone = PhoneFactory.getDefaultPhone();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.CallBarringEditPreference, 0, R.style.EditPhoneNumberPreference);
        this.mFacility = typedArrayObtainStyledAttributes2.getString(2);
        typedArrayObtainStyledAttributes2.recycle();
    }

    public CallBarringEditPreference(Context context) {
        this(context, null);
    }

    void init(TimeConsumingPreferenceListener timeConsumingPreferenceListener, boolean z, Phone phone) {
        this.mPhone = phone;
        this.mTcpListener = timeConsumingPreferenceListener;
        if (z) {
            return;
        }
        phone.getCallBarring(this.mFacility, "", this.mHandler.obtainMessage(0), getServiceClassForCallBarring(this.mPhone));
        TimeConsumingPreferenceListener timeConsumingPreferenceListener2 = this.mTcpListener;
        if (timeConsumingPreferenceListener2 != null) {
            timeConsumingPreferenceListener2.onStarted(this, true);
        }
    }

    @Override // android.preference.DialogPreference, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        super.onClick(dialogInterface, i);
        this.mButtonClicked = i;
    }

    @Override // android.preference.EditTextPreference, android.preference.DialogPreference
    protected void showDialog(Bundle bundle) {
        setDialogMessage(getContext().getString(R.string.messageCallBarring));
        super.showDialog(bundle);
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        CharSequence summary;
        int i;
        super.onBindView(view);
        TextView textView = (TextView) view.findViewById(android.R.id.summary);
        if (textView != null) {
            if (this.mIsActivated) {
                summary = this.mSummaryOn;
                if (summary == null) {
                    summary = getSummary();
                }
            } else {
                summary = this.mSummaryOff;
                if (summary == null) {
                    summary = getSummary();
                }
            }
            if (summary != null) {
                textView.setText(summary);
                i = 0;
            } else {
                i = 8;
            }
            if (i != textView.getVisibility()) {
                textView.setVisibility(i);
            }
        }
    }

    @Override // com.android.phone.settings.fdn.EditPinPreference, android.preference.DialogPreference
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        builder.setPositiveButton((CharSequence) null, (DialogInterface.OnClickListener) null);
        builder.setNeutralButton(this.mIsActivated ? this.mDisableText : this.mEnableText, this);
    }

    @Override // com.android.phone.settings.fdn.EditPinPreference, android.preference.EditTextPreference, android.preference.DialogPreference
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        this.mButtonClicked = -2;
        EditText editText = (EditText) view.findViewById(android.R.id.edit);
        if (editText != null) {
            editText.setSingleLine(true);
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.setKeyListener(DigitsKeyListener.getInstance());
            editText.setVisibility(0);
        }
    }

    @Override // com.android.phone.settings.fdn.EditPinPreference, android.preference.EditTextPreference, android.preference.DialogPreference
    protected void onDialogClosed(boolean z) {
        super.onDialogClosed(z);
        if (this.mButtonClicked != -2) {
            String string = getEditText().getText().toString();
            if (string == null || string.length() != 4) {
                Toast.makeText(getContext(), getContext().getString(R.string.call_barring_right_pwd_number), 0).show();
                return;
            }
            this.mPhone.setCallBarring(this.mFacility, !this.mIsActivated, string, this.mHandler.obtainMessage(1), getServiceClassForCallBarring(this.mPhone));
            TimeConsumingPreferenceListener timeConsumingPreferenceListener = this.mTcpListener;
            if (timeConsumingPreferenceListener != null) {
                timeConsumingPreferenceListener.onStarted(this, false);
            }
        }
    }

    void handleCallBarringResult(boolean z) {
        this.mIsActivated = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getServiceClassForCallBarring(Phone phone) {
        PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(phone.getSubId());
        if (carrierConfigForSubId != null) {
            return carrierConfigForSubId.getInt("call_barring_default_service_class_int", 1);
        }
        return 1;
    }

    void updateSummaryText() {
        notifyChanged();
        notifyDependencyChange(shouldDisableDependents());
    }

    @Override // android.preference.EditTextPreference, android.preference.Preference
    public boolean shouldDisableDependents() {
        return this.mIsActivated;
    }

    private static class MyHandler extends Handler {
        private final WeakReference<CallBarringEditPreference> mCallBarringEditPreference;

        private MyHandler(CallBarringEditPreference callBarringEditPreference) {
            this.mCallBarringEditPreference = new WeakReference<>(callBarringEditPreference);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                handleGetCallBarringResponse(message);
            } else {
                if (i != 1) {
                    return;
                }
                handleSetCallBarringResponse(message);
            }
        }

        private void handleGetCallBarringResponse(Message message) {
            CallBarringEditPreference callBarringEditPreference = this.mCallBarringEditPreference.get();
            if (callBarringEditPreference == null) {
                return;
            }
            AsyncResult asyncResult = (AsyncResult) message.obj;
            if (message.arg2 == 1) {
                callBarringEditPreference.mTcpListener.onFinished(callBarringEditPreference, false);
            } else {
                callBarringEditPreference.mTcpListener.onFinished(callBarringEditPreference, true);
            }
            if (asyncResult.exception != null) {
                callBarringEditPreference.mTcpListener.onException(callBarringEditPreference, (CommandException) asyncResult.exception);
            } else {
                if (asyncResult.userObj instanceof Throwable) {
                    callBarringEditPreference.mTcpListener.onError(callBarringEditPreference, 400);
                }
                int[] iArr = (int[]) asyncResult.result;
                if (iArr.length == 0) {
                    callBarringEditPreference.setEnabled(false);
                    callBarringEditPreference.mTcpListener.onError(callBarringEditPreference, 400);
                } else {
                    callBarringEditPreference.handleCallBarringResult(iArr[0] != 0);
                }
            }
            callBarringEditPreference.updateSummaryText();
        }

        private void handleSetCallBarringResponse(Message message) {
            CallBarringEditPreference callBarringEditPreference = this.mCallBarringEditPreference.get();
            if (callBarringEditPreference == null) {
                return;
            }
            AsyncResult asyncResult = (AsyncResult) message.obj;
            if (asyncResult.exception == null) {
                boolean z = asyncResult.userObj instanceof Throwable;
            }
            callBarringEditPreference.mPhone.getCallBarring(callBarringEditPreference.mFacility, "", obtainMessage(0, 0, 1, asyncResult.exception), CallBarringEditPreference.getServiceClassForCallBarring(callBarringEditPreference.mPhone));
        }
    }
}
