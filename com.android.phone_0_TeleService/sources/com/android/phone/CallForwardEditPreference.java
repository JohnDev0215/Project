package com.android.phone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.AsyncResult;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.BidiFormatter;
import android.text.SpannableString;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.android.internal.telephony.CallForwardInfo;
import com.android.internal.telephony.CommandException;
import com.android.internal.telephony.Phone;
import com.android.phone.CarrierXmlParser;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class CallForwardEditPreference extends EditPhoneNumberPreference {
    private static final String[] SRC_TAGS = {"{0}"};
    CallForwardInfo callForwardInfo;
    private int mAction;
    private int mButtonClicked;
    private boolean mCallForwardByUssd;
    private CarrierXmlParser mCarrierXmlParser;
    private HashMap<String, String> mCfInfo;
    private Object mCommandException;
    private MyHandler mHandler;
    private Phone mPhone;
    private int mPreviousCommand;
    private boolean mReplaceInvalidCFNumber;
    private int mServiceClass;
    private CarrierXmlParser.SsEntry.SSAction mSsAction;
    private CharSequence mSummaryOnTemplate;
    private TimeConsumingPreferenceListener mTcpListener;
    int reason;

    public CallForwardEditPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new MyHandler();
        this.mReplaceInvalidCFNumber = false;
        this.mCallForwardByUssd = false;
        this.mPreviousCommand = 0;
        this.mSsAction = CarrierXmlParser.SsEntry.SSAction.UNKNOWN;
        this.mSummaryOnTemplate = getSummaryOn();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CallForwardEditPreference, 0, R.style.EditPhoneNumberPreference);
        this.mServiceClass = typedArrayObtainStyledAttributes.getInt(1, 1);
        this.reason = typedArrayObtainStyledAttributes.getInt(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        Log.d("CallForwardEditPreference", "mServiceClass=" + this.mServiceClass + ", reason=" + this.reason);
    }

    public CallForwardEditPreference(Context context) {
        this(context, null);
    }

    void init(TimeConsumingPreferenceListener timeConsumingPreferenceListener, Phone phone, boolean z, boolean z2) {
        this.mPhone = phone;
        this.mTcpListener = timeConsumingPreferenceListener;
        this.mReplaceInvalidCFNumber = z;
        this.mCallForwardByUssd = z2;
        Log.d("CallForwardEditPreference", "init :mReplaceInvalidCFNumber " + this.mReplaceInvalidCFNumber + ", mCallForwardByUssd " + this.mCallForwardByUssd);
        if (this.mCallForwardByUssd) {
            this.mCfInfo = new HashMap<>();
            this.mCarrierXmlParser = new CarrierXmlParser(getContext(), new TelephonyManager(getContext(), phone.getSubId()).getSimCarrierId());
        }
    }

    void restoreCallForwardInfo(CallForwardInfo callForwardInfo) {
        handleCallForwardResult(callForwardInfo);
        updateSummaryText();
    }

    @Override // com.android.phone.EditPhoneNumberPreference, android.preference.EditTextPreference, android.preference.DialogPreference
    protected void onBindDialogView(View view) {
        this.mButtonClicked = -2;
        super.onBindDialogView(view);
    }

    @Override // com.android.phone.EditPhoneNumberPreference, android.preference.DialogPreference, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        super.onClick(dialogInterface, i);
        this.mButtonClicked = i;
    }

    @Override // com.android.phone.EditPhoneNumberPreference, android.preference.EditTextPreference, android.preference.DialogPreference
    protected void onDialogClosed(boolean z) {
        CallForwardInfo callForwardInfo;
        super.onDialogClosed(z);
        Log.d("CallForwardEditPreference", "mButtonClicked=" + this.mButtonClicked + ", positiveResult=" + z);
        if (this.mButtonClicked != -2) {
            int i = (isToggled() || this.mButtonClicked == -1) ? 3 : 0;
            int i2 = (this.reason == 2 && PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId()).getBoolean("support_no_reply_timer_for_cfnry_bool", true)) ? 20 : 0;
            String phoneNumber = getPhoneNumber();
            Log.d("CallForwardEditPreference", "callForwardInfo=" + this.callForwardInfo);
            if (i == 3 && (callForwardInfo = this.callForwardInfo) != null && callForwardInfo.status == 1 && phoneNumber.equals(callForwardInfo.number)) {
                Log.d("CallForwardEditPreference", "no change, do nothing");
                return;
            }
            Log.d("CallForwardEditPreference", "reason=" + this.reason + ", action=" + i + ", number=" + phoneNumber);
            setSummaryOn("");
            if (!this.mCallForwardByUssd) {
                this.mPhone.setCallForwardingOption(i, this.reason, phoneNumber, this.mServiceClass, i2, this.mHandler.obtainMessage(1, i, 1));
            } else {
                if (i == 3) {
                    this.mCfInfo.put("tag_number", phoneNumber);
                    this.mCfInfo.put("tag_time", Integer.toString(i2));
                } else {
                    this.mCfInfo.clear();
                }
                MyHandler myHandler = this.mHandler;
                myHandler.sendMessage(myHandler.obtainMessage(3, i, 1));
            }
            TimeConsumingPreferenceListener timeConsumingPreferenceListener = this.mTcpListener;
            if (timeConsumingPreferenceListener != null) {
                timeConsumingPreferenceListener.onStarted(this, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCallForwardResult(CallForwardInfo callForwardInfo) {
        PersistableBundle carrierConfigForSubId;
        this.callForwardInfo = callForwardInfo;
        Log.d("CallForwardEditPreference", "handleGetCFResponse done, callForwardInfo=" + this.callForwardInfo);
        if (this.mReplaceInvalidCFNumber && PhoneNumberUtils.formatNumber(this.callForwardInfo.number, getCurrentCountryIso()) == null) {
            this.callForwardInfo.number = getContext().getString(R.string.voicemail);
            Log.i("CallForwardEditPreference", "handleGetCFResponse: Overridding CF number");
        }
        boolean z = false;
        setToggled(this.callForwardInfo.status == 1);
        if (TextUtils.isEmpty(this.callForwardInfo.number) && (carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId())) != null) {
            z = carrierConfigForSubId.getBoolean("display_voicemail_number_as_default_call_forwarding_number");
            Log.d("CallForwardEditPreference", "display voicemail number as default");
        }
        String voiceMailNumber = this.mPhone.getVoiceMailNumber();
        if (!z) {
            voiceMailNumber = this.callForwardInfo.number;
        }
        setPhoneNumber(voiceMailNumber);
    }

    void startCallForwardOptionsQuery() {
        if (!this.mCallForwardByUssd) {
            this.mPhone.getCallForwardingOption(this.reason, this.mServiceClass, this.mHandler.obtainMessage(0, 0, 0, null));
        } else {
            MyHandler myHandler = this.mHandler;
            myHandler.sendMessage(myHandler.obtainMessage(2, 0, 0, null));
        }
        TimeConsumingPreferenceListener timeConsumingPreferenceListener = this.mTcpListener;
        if (timeConsumingPreferenceListener != null) {
            timeConsumingPreferenceListener.onStarted(this, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSummaryText() {
        if (isToggled()) {
            String rawPhoneNumber = getRawPhoneNumber();
            if (rawPhoneNumber != null && rawPhoneNumber.length() > 0) {
                String strUnicodeWrap = BidiFormatter.getInstance().unicodeWrap(rawPhoneNumber, TextDirectionHeuristics.LTR);
                String strValueOf = String.valueOf(TextUtils.replace(this.mSummaryOnTemplate, SRC_TAGS, new String[]{strUnicodeWrap}));
                int iIndexOf = strValueOf.indexOf(strUnicodeWrap);
                SpannableString spannableString = new SpannableString(strValueOf);
                PhoneNumberUtils.addTtsSpan(spannableString, iIndexOf, strUnicodeWrap.length() + iIndexOf);
                setSummaryOn(spannableString);
                return;
            }
            setSummaryOn(getContext().getString(R.string.sum_cfu_enabled_no_number));
        }
    }

    private String getCurrentCountryIso() {
        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService("phone");
        return telephonyManager == null ? "" : telephonyManager.getNetworkCountryIso().toUpperCase();
    }

    private class MyHandler extends Handler {
        TelephonyManager.UssdResponseCallback mUssdCallback;

        private MyHandler() {
            this.mUssdCallback = new TelephonyManager.UssdResponseCallback() { // from class: com.android.phone.CallForwardEditPreference.MyHandler.1
                @Override // android.telephony.TelephonyManager.UssdResponseCallback
                public void onReceiveUssdResponse(TelephonyManager telephonyManager, String str, CharSequence charSequence) {
                    if (CallForwardEditPreference.this.mSsAction == CarrierXmlParser.SsEntry.SSAction.UNKNOWN) {
                        return;
                    }
                    HashMap<String, String> responseSet = CallForwardEditPreference.this.mCarrierXmlParser.getFeature("callforwarding").getResponseSet(CallForwardEditPreference.this.mSsAction, charSequence.toString());
                    MyHandler.this.sendCfMessage(CallForwardEditPreference.this.mSsAction == CarrierXmlParser.SsEntry.SSAction.QUERY ? MyHandler.this.makeCallForwardInfo(responseSet) : null, responseSet.get("RESPONSE_ERROR") != null ? new CommandException(CommandException.Error.GENERIC_FAILURE) : null);
                }

                @Override // android.telephony.TelephonyManager.UssdResponseCallback
                public void onReceiveUssdResponseFailed(TelephonyManager telephonyManager, String str, int i) {
                    Log.d("CallForwardEditPreference", "receive the ussd result failed");
                    MyHandler.this.sendCfMessage(null, new CommandException(CommandException.Error.GENERIC_FAILURE));
                }
            };
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                handleGetCFResponse(message);
                return;
            }
            if (i == 1) {
                handleSetCFResponse(message);
            } else if (i == 2) {
                prepareUssdCommand(message, CarrierXmlParser.SsEntry.SSAction.QUERY);
            } else {
                if (i != 3) {
                    return;
                }
                prepareUssdCommand(message, CarrierXmlParser.SsEntry.SSAction.UNKNOWN);
            }
        }

        private void handleGetCFResponse(Message message) {
            CharSequence text;
            Log.d("CallForwardEditPreference", "handleGetCFResponse: done");
            CallForwardEditPreference.this.mTcpListener.onFinished(CallForwardEditPreference.this, message.arg2 != 1);
            AsyncResult asyncResult = (AsyncResult) message.obj;
            CallForwardEditPreference callForwardEditPreference = CallForwardEditPreference.this;
            callForwardEditPreference.callForwardInfo = null;
            if (asyncResult.exception != null) {
                Log.d("CallForwardEditPreference", "handleGetCFResponse: ar.exception=" + asyncResult.exception);
                if (asyncResult.exception instanceof CommandException) {
                    CallForwardEditPreference.this.mTcpListener.onException(CallForwardEditPreference.this, (CommandException) asyncResult.exception);
                } else {
                    CallForwardEditPreference.this.mTcpListener.onError(CallForwardEditPreference.this, 300);
                }
            } else {
                if (asyncResult.userObj instanceof Throwable) {
                    callForwardEditPreference.mTcpListener.onError(CallForwardEditPreference.this, 400);
                }
                CallForwardInfo[] callForwardInfoArr = (CallForwardInfo[]) asyncResult.result;
                if (callForwardInfoArr == null || callForwardInfoArr.length == 0) {
                    Log.d("CallForwardEditPreference", "handleGetCFResponse: cfInfoArray.length==0");
                    CallForwardEditPreference.this.mTcpListener.onError(CallForwardEditPreference.this, 400);
                } else {
                    int length = callForwardInfoArr.length;
                    for (int i = 0; i < length; i++) {
                        Log.d("CallForwardEditPreference", "handleGetCFResponse, cfInfoArray[" + i + "]=" + callForwardInfoArr[i]);
                        if ((CallForwardEditPreference.this.mServiceClass & callForwardInfoArr[i].serviceClass) != 0) {
                            CallForwardInfo callForwardInfo = callForwardInfoArr[i];
                            CallForwardEditPreference.this.handleCallForwardResult(callForwardInfo);
                            if (asyncResult.userObj instanceof Throwable) {
                                Log.d("CallForwardEditPreference", "Skipped duplicated error dialog");
                            } else if (message.arg2 == 1 && message.arg1 == 0 && callForwardInfo.status == 1) {
                                if (CallForwardEditPreference.this.isSkipCFFailToDisableDialog()) {
                                    Log.d("CallForwardEditPreference", "Skipped Callforwarding fail-to-disable dialog");
                                } else {
                                    CallForwardEditPreference callForwardEditPreference2 = CallForwardEditPreference.this;
                                    int i2 = callForwardEditPreference2.reason;
                                    if (i2 == 1) {
                                        text = callForwardEditPreference2.getContext().getText(R.string.disable_cfb_forbidden);
                                    } else if (i2 == 2) {
                                        text = callForwardEditPreference2.getContext().getText(R.string.disable_cfnry_forbidden);
                                    } else {
                                        text = callForwardEditPreference2.getContext().getText(R.string.disable_cfnrc_forbidden);
                                    }
                                    AlertDialog.Builder builder = new AlertDialog.Builder(CallForwardEditPreference.this.getContext());
                                    builder.setNeutralButton(R.string.close_dialog, (DialogInterface.OnClickListener) null);
                                    builder.setTitle(CallForwardEditPreference.this.getContext().getText(R.string.error_updating_title));
                                    builder.setMessage(text);
                                    builder.setCancelable(true);
                                    builder.create().show();
                                }
                            }
                        }
                    }
                }
            }
            CallForwardEditPreference.this.updateSummaryText();
        }

        private void handleSetCFResponse(Message message) {
            AsyncResult asyncResult = (AsyncResult) message.obj;
            if (asyncResult.exception != null) {
                Log.d("CallForwardEditPreference", "handleSetCFResponse: ar.exception=" + asyncResult.exception);
            }
            Log.d("CallForwardEditPreference", "handleSetCFResponse: re get");
            if (!CallForwardEditPreference.this.mCallForwardByUssd) {
                Phone phone = CallForwardEditPreference.this.mPhone;
                CallForwardEditPreference callForwardEditPreference = CallForwardEditPreference.this;
                phone.getCallForwardingOption(callForwardEditPreference.reason, callForwardEditPreference.mServiceClass, obtainMessage(0, message.arg1, 1, asyncResult.exception));
            } else {
                MyHandler myHandler = CallForwardEditPreference.this.mHandler;
                MyHandler myHandler2 = CallForwardEditPreference.this.mHandler;
                MyHandler unused = CallForwardEditPreference.this.mHandler;
                myHandler.sendMessage(myHandler2.obtainMessage(2, message.arg1, 1, asyncResult.exception));
            }
        }

        private void prepareUssdCommand(Message message, CarrierXmlParser.SsEntry.SSAction sSAction) {
            CallForwardEditPreference.this.mAction = message.arg1;
            CallForwardEditPreference.this.mPreviousCommand = message.arg2;
            CallForwardEditPreference.this.mCommandException = message.obj;
            CallForwardEditPreference.this.mSsAction = sSAction;
            if (CallForwardEditPreference.this.mSsAction != CarrierXmlParser.SsEntry.SSAction.QUERY) {
                if (CallForwardEditPreference.this.mAction == 3) {
                    CallForwardEditPreference.this.mSsAction = CarrierXmlParser.SsEntry.SSAction.UPDATE_ACTIVATE;
                } else {
                    CallForwardEditPreference.this.mSsAction = CarrierXmlParser.SsEntry.SSAction.UPDATE_DEACTIVATE;
                }
            }
            new Thread(new Runnable() { // from class: com.android.phone.CallForwardEditPreference.MyHandler.2
                @Override // java.lang.Runnable
                public void run() {
                    MyHandler myHandler = MyHandler.this;
                    myHandler.sendUssdCommand(myHandler.mUssdCallback, CallForwardEditPreference.this.mSsAction, CallForwardEditPreference.this.mCfInfo.isEmpty() ? null : CallForwardEditPreference.this.mCfInfo);
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendUssdCommand(TelephonyManager.UssdResponseCallback ussdResponseCallback, CarrierXmlParser.SsEntry.SSAction sSAction, HashMap<String, String> map) {
            ((TelephonyManager) CallForwardEditPreference.this.getContext().getSystemService("phone")).sendUssdRequest(CallForwardEditPreference.this.mCarrierXmlParser.getFeature("callforwarding").makeCommand(sSAction, map), ussdResponseCallback, CallForwardEditPreference.this.mHandler);
        }

        private Message makeGetCfMessage(int i, int i2, Object obj) {
            return CallForwardEditPreference.this.mHandler.obtainMessage(i, CallForwardEditPreference.this.mAction, i2, obj);
        }

        private Message makeSetCfMessage(int i, int i2) {
            return CallForwardEditPreference.this.mHandler.obtainMessage(i, CallForwardEditPreference.this.mAction, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendCfMessage(Object obj, Throwable th) {
            Message messageMakeSetCfMessage;
            if (CallForwardEditPreference.this.mSsAction == CarrierXmlParser.SsEntry.SSAction.UNKNOWN) {
                return;
            }
            if (CallForwardEditPreference.this.mSsAction == CarrierXmlParser.SsEntry.SSAction.QUERY) {
                messageMakeSetCfMessage = makeGetCfMessage(0, CallForwardEditPreference.this.mPreviousCommand, CallForwardEditPreference.this.mCommandException);
            } else {
                messageMakeSetCfMessage = makeSetCfMessage(1, 1);
            }
            AsyncResult.forMessage(messageMakeSetCfMessage, obj, th);
            messageMakeSetCfMessage.sendToTarget();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0056  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.android.internal.telephony.CallForwardInfo[] makeCallForwardInfo(java.util.HashMap<java.lang.String, java.lang.String> r7) {
            /*
                r6 = this;
                r0 = 1
                r1 = 0
                if (r7 == 0) goto L58
                int r2 = r7.size()
                if (r2 == 0) goto L58
                java.lang.String r2 = "status_code"
                java.lang.Object r2 = r7.get(r2)
                java.lang.String r2 = (java.lang.String) r2
                java.lang.String r3 = "time"
                java.lang.Object r4 = r7.get(r3)
                java.lang.String r4 = (java.lang.String) r4
                boolean r5 = android.text.TextUtils.isEmpty(r2)
                if (r5 != 0) goto L38
                java.lang.String r5 = "activate"
                boolean r5 = r2.equals(r5)
                if (r5 == 0) goto L2a
                r2 = r0
                goto L39
            L2a:
                java.lang.String r5 = "deactivate"
                boolean r5 = r2.equals(r5)
                if (r5 != 0) goto L38
                java.lang.String r5 = "unregister"
                boolean r2 = r2.equals(r5)
            L38:
                r2 = r1
            L39:
                java.lang.String r5 = "number"
                java.lang.Object r5 = r7.get(r5)
                java.lang.String r5 = (java.lang.String) r5
                boolean r4 = android.text.TextUtils.isEmpty(r4)
                if (r4 != 0) goto L56
                java.lang.Object r7 = r7.get(r3)
                java.lang.String r7 = (java.lang.String) r7
                java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
                int r7 = r7.intValue()
                goto L5c
            L56:
                r7 = r1
                goto L5c
            L58:
                java.lang.String r5 = ""
                r7 = r1
                r2 = r7
            L5c:
                com.android.internal.telephony.CallForwardInfo[] r0 = new com.android.internal.telephony.CallForwardInfo[r0]
                com.android.internal.telephony.CallForwardInfo r3 = new com.android.internal.telephony.CallForwardInfo
                r3.<init>()
                r0[r1] = r3
                r3 = r0[r1]
                r3.status = r2
                r2 = r0[r1]
                com.android.phone.CallForwardEditPreference r6 = com.android.phone.CallForwardEditPreference.this
                int r3 = r6.reason
                r2.reason = r3
                r2 = r0[r1]
                int r6 = com.android.phone.CallForwardEditPreference.access$600(r6)
                r2.serviceClass = r6
                r6 = r0[r1]
                r6.number = r5
                r6 = r0[r1]
                r6.timeSeconds = r7
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.phone.CallForwardEditPreference.MyHandler.makeCallForwardInfo(java.util.HashMap):com.android.internal.telephony.CallForwardInfo[]");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSkipCFFailToDisableDialog() {
        PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
        if (carrierConfigForSubId != null) {
            return carrierConfigForSubId.getBoolean("skip_cf_fail_to_disable_dialog_bool");
        }
        return false;
    }
}
