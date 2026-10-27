package com.android.bluetooth.opp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppBatch {
    private static final String TAG = "BtOppBatch";
    private static final boolean V = false;
    private final Context mContext;
    public final BluetoothDevice mDestination;
    public final int mDirection;
    public int mId;
    private BluetoothOppBatchListener mListener;
    private final ArrayList<BluetoothOppShareInfo> mShares;
    public int mStatus;
    public final long mTimestamp;

    public interface BluetoothOppBatchListener {
        void onBatchCanceled();

        void onShareAdded(int i);

        void onShareDeleted(int i);
    }

    public BluetoothOppBatch(Context context, BluetoothOppShareInfo info) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        this.mContext = context;
        this.mShares = new ArrayList<>();
        this.mTimestamp = info.mTimestamp;
        this.mDirection = info.mDirection;
        this.mDestination = adapter.getRemoteDevice(info.mDestination);
        this.mStatus = 0;
        this.mShares.add(info);
    }

    public void addShare(BluetoothOppShareInfo info) {
        this.mShares.add(info);
        BluetoothOppBatchListener bluetoothOppBatchListener = this.mListener;
        if (bluetoothOppBatchListener != null) {
            bluetoothOppBatchListener.onShareAdded(info.mId);
        }
    }

    public void cancelBatch() {
        BluetoothOppBatchListener bluetoothOppBatchListener = this.mListener;
        if (bluetoothOppBatchListener != null) {
            bluetoothOppBatchListener.onBatchCanceled();
        }
        for (int i = this.mShares.size() - 1; i >= 0; i--) {
            BluetoothOppShareInfo info = this.mShares.get(i);
            if (info.mStatus < 200) {
                if (info.mDirection == 1 && info.mUri != null) {
                    this.mContext.getContentResolver().delete(info.mUri, null, null);
                }
                Constants.updateShareStatus(this.mContext, info.mId, BluetoothShare.STATUS_CANCELED);
            }
        }
        this.mShares.clear();
    }

    public boolean hasShare(BluetoothOppShareInfo info) {
        return this.mShares.contains(info);
    }

    public boolean isEmpty() {
        return this.mShares.size() == 0;
    }

    public int getNumShares() {
        return this.mShares.size();
    }

    public void registerListern(BluetoothOppBatchListener listener) {
        this.mListener = listener;
    }

    public BluetoothOppShareInfo getPendingShare() {
        for (int i = 0; i < this.mShares.size(); i++) {
            BluetoothOppShareInfo share = this.mShares.get(i);
            if (share.mStatus == 190) {
                return share;
            }
        }
        return null;
    }
}
