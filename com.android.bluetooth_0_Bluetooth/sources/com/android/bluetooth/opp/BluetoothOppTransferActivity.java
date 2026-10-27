package com.android.bluetooth.opp;

import android.app.NotificationManager;
import android.bluetooth.AlertActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.INetd;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.bluetooth.R;
import com.android.bluetooth.mapapi.BluetoothMapContract;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppTransferActivity extends AlertActivity implements DialogInterface.OnClickListener {
    private static final boolean D = false;
    public static final int DIALOG_RECEIVE_COMPLETE_FAIL = 2;
    public static final int DIALOG_RECEIVE_COMPLETE_SUCCESS = 1;
    public static final int DIALOG_RECEIVE_ONGOING = 0;
    public static final int DIALOG_SEND_COMPLETE_FAIL = 5;
    public static final int DIALOG_SEND_COMPLETE_SUCCESS = 4;
    public static final int DIALOG_SEND_ONGOING = 3;
    private static final String TAG = "BluetoothOppTransferActivity";
    private static final boolean V = false;
    private BluetoothAdapter mAdapter;
    boolean mIsComplete;
    private TextView mLine1View;
    private TextView mLine2View;
    private TextView mLine3View;
    private TextView mLine5View;
    private BluetoothTransferContentObserver mObserver;
    private TextView mPercentView;
    private ProgressBar mProgressTransfer;
    private BluetoothOppTransferInfo mTransInfo;
    private Uri mUri;
    private int mWhichDialog;
    private View mView = null;
    private boolean mNeedUpdateButton = false;

    private class BluetoothTransferContentObserver extends ContentObserver {
        BluetoothTransferContentObserver() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            BluetoothOppTransferActivity.this.mNeedUpdateButton = true;
            BluetoothOppTransferActivity.this.updateProgressbar();
        }
    }

    @Override // android.bluetooth.AlertActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addPrivateFlags(524288);
        Intent intent = getIntent();
        this.mUri = intent.getData();
        this.mTransInfo = new BluetoothOppTransferInfo();
        BluetoothOppTransferInfo bluetoothOppTransferInfoQueryRecord = BluetoothOppUtility.queryRecord(this, this.mUri);
        this.mTransInfo = bluetoothOppTransferInfoQueryRecord;
        if (bluetoothOppTransferInfoQueryRecord == null) {
            finish();
            return;
        }
        this.mIsComplete = BluetoothShare.isStatusCompleted(bluetoothOppTransferInfoQueryRecord.mStatus);
        displayWhichDialog();
        if (!this.mIsComplete) {
            this.mObserver = new BluetoothTransferContentObserver();
            getContentResolver().registerContentObserver(BluetoothShare.CONTENT_URI, true, this.mObserver);
        }
        int i = this.mWhichDialog;
        if (i != 3 && i != 0) {
            BluetoothOppUtility.updateVisibilityToHidden(this, this.mUri);
        }
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        setUpDialog();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (this.mObserver != null) {
            getContentResolver().unregisterContentObserver(this.mObserver);
        }
        super.onDestroy();
    }

    private void displayWhichDialog() {
        int direction = this.mTransInfo.mDirection;
        boolean isSuccess = BluetoothShare.isStatusSuccess(this.mTransInfo.mStatus);
        boolean isComplete = BluetoothShare.isStatusCompleted(this.mTransInfo.mStatus);
        if (direction == 1) {
            if (isComplete) {
                if (isSuccess) {
                    this.mWhichDialog = 1;
                    return;
                } else {
                    if (!isSuccess) {
                        this.mWhichDialog = 2;
                        return;
                    }
                    return;
                }
            }
            if (!isComplete) {
                this.mWhichDialog = 0;
                return;
            }
            return;
        }
        if (direction == 0) {
            if (isComplete) {
                if (isSuccess) {
                    this.mWhichDialog = 4;
                    return;
                } else {
                    if (!isSuccess) {
                        this.mWhichDialog = 5;
                        return;
                    }
                    return;
                }
            }
            if (!isComplete) {
                this.mWhichDialog = 3;
            }
        }
    }

    private void setUpDialog() {
        this.mAlertBuilder.setTitle(getString(R.string.download_title));
        int i = this.mWhichDialog;
        if (i == 0 || i == 3) {
            this.mAlertBuilder.setPositiveButton(R.string.download_ok, this);
            this.mAlertBuilder.setNegativeButton(R.string.download_cancel, this);
        } else if (i == 1) {
            this.mAlertBuilder.setPositiveButton(R.string.download_succ_ok, this);
        } else if (i == 2) {
            this.mAlertBuilder.setIconAttribute(android.R.attr.alertDialogIcon);
            this.mAlertBuilder.setPositiveButton(R.string.download_fail_ok, this);
        } else if (i == 4) {
            this.mAlertBuilder.setPositiveButton(R.string.upload_succ_ok, this);
        } else if (i == 5) {
            this.mAlertBuilder.setIconAttribute(android.R.attr.alertDialogIcon);
            this.mAlertBuilder.setNegativeButton(R.string.upload_fail_cancel, this);
        }
        this.mAlertBuilder.setView(createView());
        setupAlert();
    }

    private View createView() {
        View viewInflate = getLayoutInflater().inflate(R.layout.file_transfer, (ViewGroup) null);
        this.mView = viewInflate;
        this.mProgressTransfer = (ProgressBar) viewInflate.findViewById(R.id.progress_transfer);
        this.mPercentView = (TextView) this.mView.findViewById(R.id.progress_percent);
        customizeViewContent();
        this.mNeedUpdateButton = false;
        updateProgressbar();
        return this.mView;
    }

    private void customizeViewContent() {
        int id;
        int i = this.mWhichDialog;
        if (i == 0 || i == 1) {
            this.mLine1View = (TextView) this.mView.findViewById(R.id.line1_view);
            String tmp = getString(R.string.download_line1, new Object[]{this.mTransInfo.mDeviceName});
            this.mLine1View.setText(tmp);
            this.mLine2View = (TextView) this.mView.findViewById(R.id.line2_view);
            String tmp2 = getString(R.string.download_line2, new Object[]{this.mTransInfo.mFileName});
            this.mLine2View.setText(tmp2);
            this.mLine3View = (TextView) this.mView.findViewById(R.id.line3_view);
            String tmp3 = getString(R.string.download_line3, new Object[]{Formatter.formatFileSize(this, this.mTransInfo.mTotalBytes)});
            this.mLine3View.setText(tmp3);
            this.mLine5View = (TextView) this.mView.findViewById(R.id.line5_view);
            int i2 = this.mWhichDialog;
            if (i2 == 0) {
                tmp3 = getString(R.string.download_line5);
            } else if (i2 == 1) {
                tmp3 = getString(R.string.download_succ_line5);
            }
            this.mLine5View.setText(tmp3);
        } else if (i == 3 || i == 4) {
            this.mLine1View = (TextView) this.mView.findViewById(R.id.line1_view);
            String tmp4 = getString(R.string.upload_line1, new Object[]{this.mTransInfo.mDeviceName});
            this.mLine1View.setText(tmp4);
            this.mLine2View = (TextView) this.mView.findViewById(R.id.line2_view);
            String tmp5 = getString(R.string.download_line2, new Object[]{this.mTransInfo.mFileName});
            this.mLine2View.setText(tmp5);
            this.mLine3View = (TextView) this.mView.findViewById(R.id.line3_view);
            String tmp6 = getString(R.string.upload_line3, new Object[]{this.mTransInfo.mFileType, Formatter.formatFileSize(this, this.mTransInfo.mTotalBytes)});
            this.mLine3View.setText(tmp6);
            this.mLine5View = (TextView) this.mView.findViewById(R.id.line5_view);
            int i3 = this.mWhichDialog;
            if (i3 == 3) {
                tmp6 = getString(R.string.upload_line5);
            } else if (i3 == 4) {
                tmp6 = getString(R.string.upload_succ_line5);
            }
            this.mLine5View.setText(tmp6);
        } else if (i == 2) {
            if (this.mTransInfo.mStatus == 494) {
                this.mLine1View = (TextView) this.mView.findViewById(R.id.line1_view);
                if (BluetoothOppUtility.deviceHasNoSdCard()) {
                    id = R.string.bt_sm_2_1_nosdcard;
                } else {
                    id = R.string.bt_sm_2_1_default;
                }
                String tmp7 = getString(id);
                this.mLine1View.setText(tmp7);
                this.mLine2View = (TextView) this.mView.findViewById(R.id.line2_view);
                String tmp8 = getString(R.string.download_fail_line2, new Object[]{this.mTransInfo.mFileName});
                this.mLine2View.setText(tmp8);
                this.mLine3View = (TextView) this.mView.findViewById(R.id.line3_view);
                String tmp9 = getString(R.string.bt_sm_2_2, new Object[]{Formatter.formatFileSize(this, this.mTransInfo.mTotalBytes)});
                this.mLine3View.setText(tmp9);
            } else {
                this.mLine1View = (TextView) this.mView.findViewById(R.id.line1_view);
                String tmp10 = getString(R.string.download_fail_line1);
                this.mLine1View.setText(tmp10);
                this.mLine2View = (TextView) this.mView.findViewById(R.id.line2_view);
                String tmp11 = getString(R.string.download_fail_line2, new Object[]{this.mTransInfo.mFileName});
                this.mLine2View.setText(tmp11);
                this.mLine3View = (TextView) this.mView.findViewById(R.id.line3_view);
                String tmp12 = getString(R.string.download_fail_line3, new Object[]{BluetoothOppUtility.getStatusDescription(this, this.mTransInfo.mStatus, this.mTransInfo.mDeviceName)});
                this.mLine3View.setText(tmp12);
            }
            TextView textView = (TextView) this.mView.findViewById(R.id.line5_view);
            this.mLine5View = textView;
            textView.setVisibility(8);
        } else if (i == 5) {
            this.mLine1View = (TextView) this.mView.findViewById(R.id.line1_view);
            String tmp13 = getString(R.string.upload_fail_line1, new Object[]{this.mTransInfo.mDeviceName});
            this.mLine1View.setText(tmp13);
            this.mLine2View = (TextView) this.mView.findViewById(R.id.line2_view);
            String tmp14 = getString(R.string.upload_fail_line1_2, new Object[]{this.mTransInfo.mFileName});
            this.mLine2View.setText(tmp14);
            this.mLine3View = (TextView) this.mView.findViewById(R.id.line3_view);
            String tmp15 = getString(R.string.download_fail_line3, new Object[]{BluetoothOppUtility.getStatusDescription(this, this.mTransInfo.mStatus, this.mTransInfo.mDeviceName)});
            this.mLine3View.setText(tmp15);
            TextView textView2 = (TextView) this.mView.findViewById(R.id.line5_view);
            this.mLine5View = textView2;
            textView2.setVisibility(8);
        }
        if (BluetoothShare.isStatusError(this.mTransInfo.mStatus)) {
            this.mProgressTransfer.setVisibility(8);
            this.mPercentView.setVisibility(8);
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        if (which == -2) {
            int i = this.mWhichDialog;
            if (i == 0 || i == 3) {
                getContentResolver().delete(this.mUri, null, null);
                String msg = INetd.NEXTHOP_NONE;
                int i2 = this.mWhichDialog;
                if (i2 == 0) {
                    msg = getString(R.string.bt_toast_3, new Object[]{this.mTransInfo.mDeviceName});
                } else if (i2 == 3) {
                    msg = getString(R.string.bt_toast_6, new Object[]{this.mTransInfo.mDeviceName});
                }
                Toast.makeText(this, msg, 0).show();
                ((NotificationManager) getSystemService(BluetoothMapContract.RECEPTION_STATE_NOTIFICATION)).cancel(this.mTransInfo.mID);
            } else if (i == 5) {
                BluetoothOppUtility.updateVisibilityToHidden(this, this.mUri);
            }
        } else if (which == -1) {
            int i3 = this.mWhichDialog;
            if (i3 == 1) {
                BluetoothOppUtility.openReceivedFile(this, this.mTransInfo.mFileName, this.mTransInfo.mFileType, this.mTransInfo.mTimeStamp, this.mUri);
                BluetoothOppUtility.updateVisibilityToHidden(this, this.mUri);
                ((NotificationManager) getSystemService(BluetoothMapContract.RECEPTION_STATE_NOTIFICATION)).cancel(this.mTransInfo.mID);
            } else if (i3 == 4) {
                BluetoothOppUtility.updateVisibilityToHidden(this, this.mUri);
                ((NotificationManager) getSystemService(BluetoothMapContract.RECEPTION_STATE_NOTIFICATION)).cancel(this.mTransInfo.mID);
            }
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgressbar() {
        BluetoothOppTransferInfo bluetoothOppTransferInfoQueryRecord = BluetoothOppUtility.queryRecord(this, this.mUri);
        this.mTransInfo = bluetoothOppTransferInfoQueryRecord;
        if (bluetoothOppTransferInfoQueryRecord == null) {
            return;
        }
        this.mProgressTransfer.setMax(100);
        if (this.mTransInfo.mTotalBytes != 0) {
            this.mProgressTransfer.setProgress((int) ((this.mTransInfo.mCurrentBytes * 100) / this.mTransInfo.mTotalBytes));
        } else {
            this.mProgressTransfer.setProgress(100);
        }
        this.mPercentView.setText(BluetoothOppUtility.formatProgressText(this.mTransInfo.mTotalBytes, this.mTransInfo.mCurrentBytes));
        if (!this.mIsComplete && BluetoothShare.isStatusCompleted(this.mTransInfo.mStatus) && this.mNeedUpdateButton) {
            if (this.mObserver != null) {
                getContentResolver().unregisterContentObserver(this.mObserver);
                this.mObserver = null;
            }
            displayWhichDialog();
            updateButton();
            customizeViewContent();
        }
    }

    private void updateButton() {
        int i = this.mWhichDialog;
        if (i == 1) {
            changeButtonVisibility(-2, 8);
            changeButtonText(-1, getString(R.string.download_succ_ok));
            return;
        }
        if (i == 2) {
            changeIconAttribute(android.R.attr.alertDialogIcon);
            changeButtonVisibility(-2, 8);
            changeButtonText(-1, getString(R.string.download_fail_ok));
        } else if (i == 4) {
            changeButtonVisibility(-2, 8);
            changeButtonText(-1, getString(R.string.upload_succ_ok));
        } else if (i == 5) {
            changeIconAttribute(android.R.attr.alertDialogIcon);
            changeButtonText(-2, getString(R.string.upload_fail_cancel));
        }
    }
}
