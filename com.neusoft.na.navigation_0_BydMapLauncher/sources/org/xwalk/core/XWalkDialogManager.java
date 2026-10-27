package org.xwalk.core;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkDialogManager {
    public static final int DIALOG_ARCHITECTURE_MISMATCH = 4;
    public static final int DIALOG_DECOMPRESSING = 11;
    public static final int DIALOG_DOWNLOADING = 12;
    public static final int DIALOG_DOWNLOAD_ERROR = 6;
    public static final int DIALOG_NEWER_VERSION = 3;
    public static final int DIALOG_NOT_FOUND = 1;
    public static final int DIALOG_OLDER_VERSION = 2;
    public static final int DIALOG_SELECT_STORE = 7;
    public static final int DIALOG_SIGNATURE_CHECK_ERROR = 5;
    public static final int DIALOG_UNSUPPORTED_STORE = 8;
    private static final String TAG = "XWalkLib";
    private Dialog mActiveDialog;
    private AlertDialog mArchitectureMismatchDialog;
    private Context mContext;
    private ProgressDialog mDecompressingDialog;
    private AlertDialog mDownloadErrorDialog;
    private ProgressDialog mDownloadingDialog;
    private AlertDialog mNewerVersionDialog;
    private AlertDialog mNotFoundDialog;
    private AlertDialog mOlderVersionDialog;
    private AlertDialog mSelectStoreDialog;
    private AlertDialog mSignatureCheckErrorDialog;
    private AlertDialog mUnsupportedStoreDialog;

    private static class ButtonAction {
        Runnable mClickAction;
        boolean mMandatory;
        int mWhich;

        ButtonAction(int i, Runnable runnable, boolean z) {
            this.mWhich = i;
            this.mClickAction = runnable;
            this.mMandatory = z;
        }
    }

    public XWalkDialogManager(Context context) {
        this.mContext = context;
    }

    public void setAlertDialog(int i, AlertDialog alertDialog) {
        if ((alertDialog instanceof ProgressDialog) || (alertDialog instanceof DatePickerDialog) || (alertDialog instanceof TimePickerDialog)) {
            throw new IllegalArgumentException("The type of dialog must be AlertDialog");
        }
        if (i == 1) {
            this.mNotFoundDialog = alertDialog;
            return;
        }
        if (i == 2) {
            this.mOlderVersionDialog = alertDialog;
            return;
        }
        if (i == 3) {
            this.mNewerVersionDialog = alertDialog;
            return;
        }
        if (i == 4) {
            this.mArchitectureMismatchDialog = alertDialog;
            return;
        }
        if (i == 5) {
            this.mSignatureCheckErrorDialog = alertDialog;
            return;
        }
        if (i == 6) {
            this.mDownloadErrorDialog = alertDialog;
        } else if (i == 7) {
            this.mSelectStoreDialog = alertDialog;
        } else {
            if (i == 8) {
                this.mUnsupportedStoreDialog = alertDialog;
                return;
            }
            throw new IllegalArgumentException("Invalid dialog id " + i);
        }
    }

    public void setProgressDialog(int i, ProgressDialog progressDialog) {
        if (i == 11) {
            this.mDecompressingDialog = progressDialog;
        } else {
            if (i == 12) {
                this.mDownloadingDialog = progressDialog;
                return;
            }
            throw new IllegalArgumentException("Invalid dialog id " + i);
        }
    }

    public AlertDialog getAlertDialog(int i) {
        if (i == 1) {
            if (this.mNotFoundDialog == null) {
                AlertDialog alertDialogBuildAlertDialog = buildAlertDialog();
                this.mNotFoundDialog = alertDialogBuildAlertDialog;
                setTitle(alertDialogBuildAlertDialog, R.string.startup_not_found_title);
                setMessage(this.mNotFoundDialog, R.string.startup_not_found_message);
                setPositiveButton(this.mNotFoundDialog, R.string.xwalk_get_crosswalk);
                setNegativeButton(this.mNotFoundDialog, R.string.xwalk_close);
            }
            return this.mNotFoundDialog;
        }
        if (i == 2) {
            if (this.mOlderVersionDialog == null) {
                AlertDialog alertDialogBuildAlertDialog2 = buildAlertDialog();
                this.mOlderVersionDialog = alertDialogBuildAlertDialog2;
                setTitle(alertDialogBuildAlertDialog2, R.string.startup_older_version_title);
                setMessage(this.mOlderVersionDialog, R.string.startup_older_version_message);
                setPositiveButton(this.mOlderVersionDialog, R.string.xwalk_get_crosswalk);
                setNegativeButton(this.mOlderVersionDialog, R.string.xwalk_close);
            }
            return this.mOlderVersionDialog;
        }
        if (i == 3) {
            if (this.mNewerVersionDialog == null) {
                AlertDialog alertDialogBuildAlertDialog3 = buildAlertDialog();
                this.mNewerVersionDialog = alertDialogBuildAlertDialog3;
                setTitle(alertDialogBuildAlertDialog3, R.string.startup_newer_version_title);
                setMessage(this.mNewerVersionDialog, R.string.startup_newer_version_message);
                setNegativeButton(this.mNewerVersionDialog, R.string.xwalk_close);
            }
            return this.mNewerVersionDialog;
        }
        if (i == 4) {
            if (this.mArchitectureMismatchDialog == null) {
                AlertDialog alertDialogBuildAlertDialog4 = buildAlertDialog();
                this.mArchitectureMismatchDialog = alertDialogBuildAlertDialog4;
                setTitle(alertDialogBuildAlertDialog4, R.string.startup_architecture_mismatch_title);
                setMessage(this.mArchitectureMismatchDialog, R.string.startup_architecture_mismatch_message);
                setPositiveButton(this.mArchitectureMismatchDialog, R.string.xwalk_get_crosswalk);
                setNegativeButton(this.mArchitectureMismatchDialog, R.string.xwalk_close);
            }
            return this.mArchitectureMismatchDialog;
        }
        if (i == 5) {
            if (this.mSignatureCheckErrorDialog == null) {
                AlertDialog alertDialogBuildAlertDialog5 = buildAlertDialog();
                this.mSignatureCheckErrorDialog = alertDialogBuildAlertDialog5;
                setTitle(alertDialogBuildAlertDialog5, R.string.startup_signature_check_error_title);
                setMessage(this.mSignatureCheckErrorDialog, R.string.startup_signature_check_error_message);
                setNegativeButton(this.mSignatureCheckErrorDialog, R.string.xwalk_close);
            }
            return this.mSignatureCheckErrorDialog;
        }
        if (i == 6) {
            if (this.mDownloadErrorDialog == null) {
                AlertDialog alertDialogBuildAlertDialog6 = buildAlertDialog();
                this.mDownloadErrorDialog = alertDialogBuildAlertDialog6;
                setTitle(alertDialogBuildAlertDialog6, R.string.crosswalk_install_title);
                setMessage(this.mDownloadErrorDialog, R.string.download_failed_message);
                setPositiveButton(this.mDownloadErrorDialog, R.string.xwalk_retry);
                setNegativeButton(this.mDownloadErrorDialog, R.string.xwalk_cancel);
            }
            return this.mDownloadErrorDialog;
        }
        if (i == 7) {
            if (this.mSelectStoreDialog == null) {
                AlertDialog alertDialogBuildAlertDialog7 = buildAlertDialog();
                this.mSelectStoreDialog = alertDialogBuildAlertDialog7;
                setTitle(alertDialogBuildAlertDialog7, R.string.crosswalk_install_title);
                setPositiveButton(this.mSelectStoreDialog, R.string.xwalk_continue);
            }
            return this.mSelectStoreDialog;
        }
        if (i == 8) {
            if (this.mUnsupportedStoreDialog == null) {
                AlertDialog alertDialogBuildAlertDialog8 = buildAlertDialog();
                this.mUnsupportedStoreDialog = alertDialogBuildAlertDialog8;
                setTitle(alertDialogBuildAlertDialog8, R.string.crosswalk_install_title);
                setMessage(this.mUnsupportedStoreDialog, R.string.unsupported_store_message);
                setNegativeButton(this.mUnsupportedStoreDialog, R.string.xwalk_close);
            }
            return this.mUnsupportedStoreDialog;
        }
        throw new IllegalArgumentException("Invalid dialog id " + i);
    }

    public ProgressDialog getProgressDialog(int i) {
        if (i == 11) {
            if (this.mDecompressingDialog == null) {
                ProgressDialog progressDialogBuildProgressDialog = buildProgressDialog();
                this.mDecompressingDialog = progressDialogBuildProgressDialog;
                setTitle(progressDialogBuildProgressDialog, R.string.crosswalk_install_title);
                setMessage(this.mDecompressingDialog, R.string.decompression_progress_message);
                setNegativeButton(this.mDecompressingDialog, R.string.xwalk_cancel);
                this.mDecompressingDialog.setProgressStyle(0);
            }
            return this.mDecompressingDialog;
        }
        if (i == 12) {
            if (this.mDownloadingDialog == null) {
                ProgressDialog progressDialogBuildProgressDialog2 = buildProgressDialog();
                this.mDownloadingDialog = progressDialogBuildProgressDialog2;
                setTitle(progressDialogBuildProgressDialog2, R.string.crosswalk_install_title);
                setMessage(this.mDownloadingDialog, R.string.download_progress_message);
                setNegativeButton(this.mDownloadingDialog, R.string.xwalk_cancel);
                this.mDownloadingDialog.setProgressStyle(1);
            }
            return this.mDownloadingDialog;
        }
        throw new IllegalArgumentException("Invalid dialog id " + i);
    }

    void showInitializationError(int i, Runnable runnable, Runnable runnable2) {
        AlertDialog alertDialog;
        ArrayList<ButtonAction> arrayList = new ArrayList<>();
        if (i == 2) {
            alertDialog = getAlertDialog(1);
            arrayList.add(new ButtonAction(-1, runnable2, true));
            arrayList.add(new ButtonAction(-2, runnable, false));
        } else if (i == 3) {
            alertDialog = getAlertDialog(2);
            arrayList.add(new ButtonAction(-1, runnable2, true));
            arrayList.add(new ButtonAction(-2, runnable, false));
        } else if (i == 4) {
            alertDialog = getAlertDialog(3);
            arrayList.add(new ButtonAction(-2, runnable, true));
        } else if (i == 6) {
            alertDialog = getAlertDialog(4);
            arrayList.add(new ButtonAction(-1, runnable2, true));
            arrayList.add(new ButtonAction(-2, runnable, false));
        } else if (i == 7) {
            alertDialog = getAlertDialog(5);
            arrayList.add(new ButtonAction(-2, runnable, true));
        } else {
            throw new IllegalArgumentException("Invalid status " + i);
        }
        showDialog(alertDialog, arrayList);
    }

    void showDownloadError(Runnable runnable, Runnable runnable2) {
        AlertDialog alertDialog = getAlertDialog(6);
        ArrayList<ButtonAction> arrayList = new ArrayList<>();
        arrayList.add(new ButtonAction(-1, runnable2, true));
        arrayList.add(new ButtonAction(-2, runnable, false));
        showDialog(alertDialog, arrayList);
    }

    void showSelectStore(Runnable runnable, String str) {
        AlertDialog alertDialog = getAlertDialog(7);
        setMessage(alertDialog, this.mContext.getString(R.string.select_store_message).replace("STORE_NAME", str));
        ArrayList<ButtonAction> arrayList = new ArrayList<>();
        arrayList.add(new ButtonAction(-1, runnable, true));
        showDialog(alertDialog, arrayList);
    }

    void showUnsupportedStore(Runnable runnable) {
        AlertDialog alertDialog = getAlertDialog(8);
        ArrayList<ButtonAction> arrayList = new ArrayList<>();
        arrayList.add(new ButtonAction(-2, runnable, true));
        showDialog(alertDialog, arrayList);
    }

    void showDecompressProgress(Runnable runnable) {
        ProgressDialog progressDialog = getProgressDialog(11);
        ArrayList<ButtonAction> arrayList = new ArrayList<>();
        arrayList.add(new ButtonAction(-2, runnable, false));
        showDialog(progressDialog, arrayList);
    }

    void showDownloadProgress(Runnable runnable) {
        ProgressDialog progressDialog = getProgressDialog(12);
        ArrayList<ButtonAction> arrayList = new ArrayList<>();
        arrayList.add(new ButtonAction(-2, runnable, false));
        showDialog(progressDialog, arrayList);
    }

    void dismissDialog() {
        this.mActiveDialog.dismiss();
        this.mActiveDialog = null;
    }

    void setProgress(int i, int i2) {
        ProgressDialog progressDialog = (ProgressDialog) this.mActiveDialog;
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(i2);
        progressDialog.setProgress(i);
    }

    boolean isShowingDialog() {
        Dialog dialog = this.mActiveDialog;
        return dialog != null && dialog.isShowing();
    }

    private AlertDialog buildAlertDialog() {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this.mContext).create();
        alertDialogCreate.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.setCanceledOnTouchOutside(false);
        return alertDialogCreate;
    }

    private ProgressDialog buildProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this.mContext);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    private void setTitle(AlertDialog alertDialog, int i) {
        alertDialog.setTitle(this.mContext.getString(i));
    }

    private void setMessage(AlertDialog alertDialog, int i) {
        setMessage(alertDialog, this.mContext.getString(i));
    }

    private void setMessage(AlertDialog alertDialog, String str) {
        String strReplaceAll = str.replaceAll("APP_NAME", XWalkEnvironment.getApplicationName());
        if (strReplaceAll.startsWith("this")) {
            strReplaceAll = strReplaceAll.replaceFirst("this", "This");
        }
        alertDialog.setMessage(strReplaceAll);
    }

    private void setPositiveButton(AlertDialog alertDialog, int i) {
        alertDialog.setButton(-1, this.mContext.getString(i), (DialogInterface.OnClickListener) null);
    }

    private void setNegativeButton(AlertDialog alertDialog, int i) {
        alertDialog.setButton(-2, this.mContext.getString(i), (DialogInterface.OnClickListener) null);
    }

    private void showDialog(final AlertDialog alertDialog, final ArrayList<ButtonAction> arrayList) {
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: org.xwalk.core.XWalkDialogManager.1
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                for (ButtonAction buttonAction : arrayList) {
                    Button button = alertDialog.getButton(buttonAction.mWhich);
                    if (button == null) {
                        if (buttonAction.mMandatory) {
                            throw new RuntimeException("Button " + buttonAction.mWhich + " is mandatory");
                        }
                    } else if (buttonAction.mClickAction != null) {
                        final Runnable runnable = buttonAction.mClickAction;
                        button.setOnClickListener(new View.OnClickListener() { // from class: org.xwalk.core.XWalkDialogManager.1.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                XWalkDialogManager.this.dismissDialog();
                                runnable.run();
                            }
                        });
                    }
                }
            }
        });
        this.mActiveDialog = alertDialog;
        alertDialog.show();
    }
}
