package com.android.bluetooth.pbap;

import android.bluetooth.AlertActivity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.INetd;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.android.bluetooth.R;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPbapActivity extends AlertActivity implements DialogInterface.OnClickListener, Preference.OnPreferenceChangeListener, TextWatcher {
    private static final int BLUETOOTH_OBEX_AUTHKEY_MAX_LENGTH = 16;
    private static final int DIALOG_YES_NO_AUTH = 1;
    private static final int DISMISS_TIMEOUT_DIALOG = 0;
    private static final int DISMISS_TIMEOUT_DIALOG_VALUE = 2000;
    private static final String KEY_USER_TIMEOUT = "user_timeout";
    private static final String TAG = "BluetoothPbapActivity";
    private static final boolean V = false;
    private int mCurrentDialog;
    private BluetoothDevice mDevice;
    private EditText mKeyView;
    private TextView mMessageView;
    private View mView;
    private String mSessionKey = INetd.NEXTHOP_NONE;
    private boolean mTimeout = false;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.pbap.BluetoothPbapActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.android.bluetooth.pbap.userconfirmtimeout".equals(intent.getAction())) {
                BluetoothPbapActivity.this.onTimeout();
            }
        }
    };
    private final Handler mTimeoutHandler = new Handler() { // from class: com.android.bluetooth.pbap.BluetoothPbapActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                BluetoothPbapActivity.this.finish();
            }
        }
    };

    @Override // android.bluetooth.AlertActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addPrivateFlags(524288);
        Intent i = getIntent();
        String action = i.getAction();
        this.mDevice = (BluetoothDevice) i.getParcelableExtra("com.android.bluetooth.pbap.device");
        if (action != null && action.equals("com.android.bluetooth.pbap.authchall")) {
            showPbapDialog(1);
            this.mCurrentDialog = 1;
        } else {
            Log.e(TAG, "Error: this activity may be started only with intent PBAP_ACCESS_REQUEST or PBAP_AUTH_CHALL ");
            finish();
        }
        registerReceiver(this.mReceiver, new IntentFilter("com.android.bluetooth.pbap.userconfirmtimeout"));
    }

    private void showPbapDialog(int id) {
        if (id == 1) {
            this.mAlertBuilder.setTitle(getString(R.string.pbap_session_key_dialog_header));
            this.mAlertBuilder.setView(createView(1));
            this.mAlertBuilder.setPositiveButton(android.R.string.ok, this);
            this.mAlertBuilder.setNegativeButton(android.R.string.cancel, this);
            setupAlert();
            changeButtonEnabled(-1, false);
        }
    }

    private String createDisplayText(int id) {
        if (id == 1) {
            String mMessage2 = getString(R.string.pbap_session_key_dialog_title, new Object[]{this.mDevice});
            return mMessage2;
        }
        return null;
    }

    private View createView(int id) {
        if (id != 1) {
            return null;
        }
        View viewInflate = getLayoutInflater().inflate(R.layout.auth, (ViewGroup) null);
        this.mView = viewInflate;
        TextView textView = (TextView) viewInflate.findViewById(R.id.message);
        this.mMessageView = textView;
        textView.setText(createDisplayText(id));
        EditText editText = (EditText) this.mView.findViewById(R.id.text);
        this.mKeyView = editText;
        editText.addTextChangedListener(this);
        this.mKeyView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        return this.mView;
    }

    private void onPositive() {
        if (!this.mTimeout && this.mCurrentDialog == 1) {
            sendIntentToReceiver("com.android.bluetooth.pbap.authresponse", "com.android.bluetooth.pbap.sessionkey", this.mSessionKey);
            this.mKeyView.removeTextChangedListener(this);
        }
        this.mTimeout = false;
        finish();
    }

    private void onNegative() {
        if (this.mCurrentDialog == 1) {
            sendIntentToReceiver("com.android.bluetooth.pbap.authcancelled", null, null);
            this.mKeyView.removeTextChangedListener(this);
        }
        finish();
    }

    private void sendIntentToReceiver(String intentName, String extraName, String extraValue) {
        Intent intent = new Intent(intentName);
        intent.setPackage("com.android.bluetooth");
        intent.putExtra("com.android.bluetooth.pbap.device", this.mDevice);
        if (extraName != null) {
            intent.putExtra(extraName, extraValue);
        }
        sendBroadcast(intent);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        if (which == -2) {
            onNegative();
        } else if (which == -1) {
            if (this.mCurrentDialog == 1) {
                this.mSessionKey = this.mKeyView.getText().toString();
            }
            onPositive();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTimeout() {
        this.mTimeout = true;
        if (this.mCurrentDialog == 1) {
            this.mMessageView.setText(getString(R.string.pbap_authentication_timeout_message, new Object[]{this.mDevice}));
            this.mKeyView.setVisibility(8);
            this.mKeyView.clearFocus();
            this.mKeyView.removeTextChangedListener(this);
            changeButtonEnabled(-1, true);
            changeButtonVisibility(-2, 8);
        }
        Handler handler = this.mTimeoutHandler;
        handler.sendMessageDelayed(handler.obtainMessage(0), 2000L);
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boolean z = savedInstanceState.getBoolean(KEY_USER_TIMEOUT);
        this.mTimeout = z;
        if (z) {
            onTimeout();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_USER_TIMEOUT, this.mTimeout);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
    }

    @Override // android.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return true;
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int before, int after) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            changeButtonEnabled(-1, true);
        }
    }
}
