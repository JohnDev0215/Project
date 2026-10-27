package com.amazon.mp3.dialog.fragment;

import android.app.Dialog;
import android.content.Context;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.util.ConnectivityUtil;

/* JADX INFO: loaded from: classes3.dex */
public class NetworkErrorDialogHandler implements NetworkErrorDialog.INetworkErrorDialogHandler {
    private Context mContext;
    private Dialog mNetworkErrorDialog;

    @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onConnectionErrorDialogCancel() {
    }

    @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onDialogDismiss() {
    }

    public NetworkErrorDialogHandler(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onRetryConnection() {
        if (ConnectivityUtil.hasAnyInternetConnection()) {
            return;
        }
        showNetworkErrorDialog();
    }

    public void showNetworkErrorDialog() {
        Dialog dialogCreate = NetworkErrorDialog.create(this.mContext, this);
        this.mNetworkErrorDialog = dialogCreate;
        dialogCreate.show();
    }
}
