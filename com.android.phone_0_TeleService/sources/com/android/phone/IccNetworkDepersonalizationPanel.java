package com.android.phone;

import android.content.Context;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DialerKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.uicc.IccCardApplicationStatus;

/* JADX INFO: loaded from: classes.dex */
public class IccNetworkDepersonalizationPanel extends IccPanel {
    private Button mDismissButton;
    View.OnClickListener mDismissListener;
    private LinearLayout mEntryPanel;
    private Handler mHandler;
    private IccCardApplicationStatus.PersoSubState mPersoSubState;
    private int mPersoSubtype;
    private TextView mPersoSubtypeText;
    private Phone mPhone;
    private EditText mPinEntry;
    private TextWatcher mPinEntryWatcher;
    private LinearLayout mStatusPanel;
    private TextView mStatusText;
    private Button mUnlockButton;
    View.OnClickListener mUnlockListener;
    private static boolean[] sShowingDialog = new boolean[TelephonyManager.getDefault().getSupportedModemCount()];
    private static IccNetworkDepersonalizationPanel[] sNdpPanel = new IccNetworkDepersonalizationPanel[TelephonyManager.getDefault().getSupportedModemCount()];

    enum statusType {
        ENTRY,
        IN_PROGRESS,
        ERROR,
        SUCCESS
    }

    public static void showDialog(Phone phone, int i) {
        int phoneId = phone == null ? 0 : phone.getPhoneId();
        boolean[] zArr = sShowingDialog;
        if (phoneId >= zArr.length) {
            Log.e("PhoneGlobals", "[IccNetworkDepersonalizationPanel] showDialog; invalid phoneId" + phoneId);
            return;
        }
        if (zArr[phoneId]) {
            Log.i("PhoneGlobals", "[IccNetworkDepersonalizationPanel] - showDialog; skipped already shown.");
            return;
        }
        Log.i("PhoneGlobals", "[IccNetworkDepersonalizationPanel] - showDialog; showing dialog.");
        sShowingDialog[phoneId] = true;
        sNdpPanel[phoneId] = new IccNetworkDepersonalizationPanel(PhoneGlobals.getInstance(), phone, i);
        sNdpPanel[phoneId].show();
    }

    public static void dialogDismiss(int i) {
        boolean[] zArr = sShowingDialog;
        if (i >= zArr.length) {
            Log.e("PhoneGlobals", "[IccNetworkDepersonalizationPanel] - dismiss; invalid phoneId " + i);
            return;
        }
        IccNetworkDepersonalizationPanel[] iccNetworkDepersonalizationPanelArr = sNdpPanel;
        if (iccNetworkDepersonalizationPanelArr[i] == null || !zArr[i]) {
            return;
        }
        iccNetworkDepersonalizationPanelArr[i].dismiss();
    }

    public IccNetworkDepersonalizationPanel(Context context, Phone phone, int i) {
        super(context);
        this.mPinEntryWatcher = new TextWatcher() { // from class: com.android.phone.IccNetworkDepersonalizationPanel.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (SpecialCharSequenceMgr.handleChars(IccNetworkDepersonalizationPanel.this.getContext(), editable.toString())) {
                    IccNetworkDepersonalizationPanel.this.mPinEntry.getText().clear();
                }
            }
        };
        this.mHandler = new Handler() { // from class: com.android.phone.IccNetworkDepersonalizationPanel.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 100) {
                    if (((AsyncResult) message.obj).exception != null) {
                        IccNetworkDepersonalizationPanel.this.displayStatus(statusType.ERROR.name());
                        postDelayed(new Runnable() { // from class: com.android.phone.IccNetworkDepersonalizationPanel.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                IccNetworkDepersonalizationPanel.this.hideAlert();
                                IccNetworkDepersonalizationPanel.this.mPinEntry.getText().clear();
                                IccNetworkDepersonalizationPanel.this.mPinEntry.requestFocus();
                            }
                        }, 3000L);
                    } else {
                        IccNetworkDepersonalizationPanel.this.displayStatus(statusType.SUCCESS.name());
                        postDelayed(new Runnable() { // from class: com.android.phone.IccNetworkDepersonalizationPanel.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                IccNetworkDepersonalizationPanel.this.dismiss();
                            }
                        }, 3000L);
                    }
                }
            }
        };
        this.mUnlockListener = new View.OnClickListener() { // from class: com.android.phone.IccNetworkDepersonalizationPanel.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String string = IccNetworkDepersonalizationPanel.this.mPinEntry.getText().toString();
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                IccNetworkDepersonalizationPanel.this.log("Requesting De-Personalization for subtype " + IccNetworkDepersonalizationPanel.this.mPersoSubtype);
                try {
                    IccNetworkDepersonalizationPanel.this.mPhone.getIccCard().supplySimDepersonalization(IccNetworkDepersonalizationPanel.this.mPersoSubState, string, Message.obtain(IccNetworkDepersonalizationPanel.this.mHandler, 100));
                } catch (NullPointerException e) {
                    IccNetworkDepersonalizationPanel.this.log("NullPointerException @supplySimDepersonalization" + e);
                }
                IccNetworkDepersonalizationPanel.this.displayStatus(statusType.IN_PROGRESS.name());
            }
        };
        this.mDismissListener = new View.OnClickListener() { // from class: com.android.phone.IccNetworkDepersonalizationPanel.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IccNetworkDepersonalizationPanel.this.dismiss();
            }
        };
        this.mPhone = phone == null ? PhoneGlobals.getPhone() : phone;
        this.mPersoSubtype = i;
    }

    @Override // com.android.phone.IccPanel, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.sim_ndp);
        EditText editText = (EditText) findViewById(R.id.pin_entry);
        this.mPinEntry = editText;
        editText.setKeyListener(DialerKeyListener.getInstance());
        this.mPinEntry.setOnClickListener(this.mUnlockListener);
        Editable text = this.mPinEntry.getText();
        text.setSpan(this.mPinEntryWatcher, 0, text.length(), 18);
        this.mEntryPanel = (LinearLayout) findViewById(R.id.entry_panel);
        this.mPersoSubtypeText = (TextView) findViewById(R.id.perso_subtype_text);
        displayStatus(statusType.ENTRY.name());
        Button button = (Button) findViewById(R.id.ndp_unlock);
        this.mUnlockButton = button;
        button.setOnClickListener(this.mUnlockListener);
        this.mDismissButton = (Button) findViewById(R.id.ndp_dismiss);
        if (PhoneGlobals.getInstance().getCarrierConfig().getBoolean("sim_network_unlock_allow_dismiss_bool")) {
            this.mDismissButton.setVisibility(0);
            this.mDismissButton.setOnClickListener(this.mDismissListener);
        } else {
            this.mDismissButton.setVisibility(8);
        }
        this.mStatusPanel = (LinearLayout) findViewById(R.id.status_panel);
        this.mStatusText = (TextView) findViewById(R.id.status_text);
    }

    @Override // com.android.phone.IccPanel, android.app.Dialog
    protected void onStart() {
        super.onStart();
    }

    @Override // com.android.phone.IccPanel, android.app.Dialog
    public void onStop() {
        super.onStop();
        Log.i("PhoneGlobals", "[IccNetworkDepersonalizationPanel] - showDialog; hiding dialog.");
        Phone phone = this.mPhone;
        int phoneId = phone == null ? 0 : phone.getPhoneId();
        boolean[] zArr = sShowingDialog;
        if (phoneId >= zArr.length) {
            Log.e("PhoneGlobals", "[IccNetworkDepersonalizationPanel] - onStop; invalid phoneId " + phoneId);
            return;
        }
        zArr[phoneId] = false;
    }

    @Override // com.android.phone.IccPanel, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayStatus(String str) {
        this.mPersoSubState = IccCardApplicationStatus.PersoSubState.values()[this.mPersoSubtype];
        log("displayStatus mPersoSubState: " + this.mPersoSubState.name() + "type: " + str);
        int identifier = getContext().getResources().getIdentifier(this.mPersoSubState.name() + "_" + str, "string", "android");
        if (identifier == 0) {
            log("Unable to get the PersoSubType string");
            return;
        }
        if (!IccCardApplicationStatus.PersoSubState.isPersoLocked(this.mPersoSubState)) {
            log("Unsupported Perso Subtype :" + this.mPersoSubState.name());
            return;
        }
        if (str == statusType.ENTRY.name()) {
            this.mPersoSubtypeText.setText(getContext().getString(identifier));
        } else {
            this.mStatusText.setText(identifier);
            this.mEntryPanel.setVisibility(8);
            this.mStatusPanel.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideAlert() {
        this.mEntryPanel.setVisibility(0);
        this.mStatusPanel.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        Log.d("PhoneGlobals", "[IccNetworkDepersonalizationPanel] " + str);
    }
}
