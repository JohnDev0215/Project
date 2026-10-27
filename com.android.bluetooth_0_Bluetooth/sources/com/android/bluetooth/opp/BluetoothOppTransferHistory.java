package com.android.bluetooth.opp;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.StaleDataException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.bluetooth.R;
import com.android.vcard.VCardConfig;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppTransferHistory extends Activity implements View.OnCreateContextMenuListener, AdapterView.OnItemClickListener {
    private static final String TAG = "BluetoothOppTransferHistory";
    private static final boolean V = false;
    private boolean mContextMenu = false;
    private int mContextMenuPosition;
    private int mIdColumnId;
    private ListView mListView;
    private BluetoothOppNotification mNotifier;
    private boolean mShowAllIncoming;
    private BluetoothOppTransferAdapter mTransferAdapter;
    private Cursor mTransferCursor;

    @Override // android.app.Activity
    public void onCreate(Bundle icicle) {
        String direction;
        super.onCreate(icicle);
        setContentView(R.layout.bluetooth_transfers_page);
        ListView listView = (ListView) findViewById(R.id.list);
        this.mListView = listView;
        listView.setEmptyView(findViewById(R.id.empty));
        this.mShowAllIncoming = getIntent().getBooleanExtra("android.btopp.intent.extra.SHOW_ALL", false);
        int dir = getIntent().getIntExtra(BluetoothShare.DIRECTION, 0);
        if (dir == 0) {
            setTitle(getText(R.string.outbound_history_title));
            direction = "(direction == 0)";
        } else {
            if (this.mShowAllIncoming) {
                setTitle(getText(R.string.btopp_live_folder));
            } else {
                setTitle(getText(R.string.inbound_history_title));
            }
            direction = "(direction == 1)";
        }
        String selection = "status >= '200' AND " + direction;
        if (!this.mShowAllIncoming) {
            selection = selection + " AND (" + BluetoothShare.VISIBILITY + " IS NULL OR " + BluetoothShare.VISIBILITY + " == '0')";
        }
        Cursor cursorQuery = getContentResolver().query(BluetoothShare.CONTENT_URI, new String[]{"_id", BluetoothShare.FILENAME_HINT, "status", BluetoothShare.TOTAL_BYTES, BluetoothShare._DATA, "timestamp", BluetoothShare.VISIBILITY, BluetoothShare.DESTINATION, BluetoothShare.DIRECTION}, selection, null, "timestamp DESC");
        this.mTransferCursor = cursorQuery;
        if (cursorQuery != null) {
            this.mIdColumnId = cursorQuery.getColumnIndexOrThrow("_id");
            BluetoothOppTransferAdapter bluetoothOppTransferAdapter = new BluetoothOppTransferAdapter(this, R.layout.bluetooth_transfer_item, this.mTransferCursor);
            this.mTransferAdapter = bluetoothOppTransferAdapter;
            this.mListView.setAdapter((ListAdapter) bluetoothOppTransferAdapter);
            this.mListView.setScrollBarStyle(16777216);
            this.mListView.setOnCreateContextMenuListener(this);
            this.mListView.setOnItemClickListener(this);
        }
        this.mNotifier = new BluetoothOppNotification(this);
        this.mContextMenu = false;
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.mTransferCursor != null && !this.mShowAllIncoming) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.transferhistory, menu);
            return true;
        }
        return true;
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!this.mShowAllIncoming) {
            menu.findItem(R.id.transfer_menu_clear_all).setEnabled(isTransferComplete());
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.transfer_menu_clear_all) {
            promptClearList();
            return true;
        }
        return false;
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem item) {
        if (this.mTransferCursor.getCount() == 0) {
            Log.i(TAG, "History is already cleared, not clearing again");
            return true;
        }
        this.mTransferCursor.moveToPosition(this.mContextMenuPosition);
        int itemId = item.getItemId();
        if (itemId != R.id.transfer_menu_clear) {
            if (itemId == R.id.transfer_menu_open) {
                openCompleteTransfer();
                updateNotificationWhenBtDisabled();
                return true;
            }
            return false;
        }
        int sessionId = this.mTransferCursor.getInt(this.mIdColumnId);
        Uri contentUri = Uri.parse(BluetoothShare.CONTENT_URI + "/" + sessionId);
        BluetoothOppUtility.updateVisibilityToHidden(this, contentUri);
        updateNotificationWhenBtDisabled();
        return true;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        Cursor cursor = this.mTransferCursor;
        if (cursor != null) {
            cursor.close();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Cursor cursor = this.mTransferCursor;
        if (cursor != null) {
            this.mContextMenu = true;
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            cursor.moveToPosition(info.position);
            this.mContextMenuPosition = info.position;
            Cursor cursor2 = this.mTransferCursor;
            String fileName = cursor2.getString(cursor2.getColumnIndexOrThrow(BluetoothShare.FILENAME_HINT));
            if (fileName == null) {
                fileName = getString(R.string.unknown_file);
            }
            menu.setHeaderTitle(fileName);
            MenuInflater inflater = getMenuInflater();
            if (this.mShowAllIncoming) {
                inflater.inflate(R.menu.receivedfilescontextfinished, menu);
            } else {
                inflater.inflate(R.menu.transferhistorycontextfinished, menu);
            }
        }
    }

    private void promptClearList() {
        new AlertDialog.Builder(this).setTitle(R.string.transfer_clear_dlg_title).setMessage(R.string.transfer_clear_dlg_msg).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.bluetooth.opp.BluetoothOppTransferHistory.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int whichButton) {
                BluetoothOppTransferHistory.this.clearAllDownloads();
            }
        }).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).show();
    }

    private boolean isTransferComplete() {
        try {
            if (this.mTransferCursor.moveToFirst()) {
                while (!this.mTransferCursor.isAfterLast()) {
                    int statusColumnId = this.mTransferCursor.getColumnIndexOrThrow("status");
                    int status = this.mTransferCursor.getInt(statusColumnId);
                    if (BluetoothShare.isStatusCompleted(status)) {
                        return true;
                    }
                    this.mTransferCursor.moveToNext();
                }
                return false;
            }
            return false;
        } catch (StaleDataException e) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAllDownloads() {
        if (this.mTransferCursor.moveToFirst()) {
            while (!this.mTransferCursor.isAfterLast()) {
                int sessionId = this.mTransferCursor.getInt(this.mIdColumnId);
                Uri contentUri = Uri.parse(BluetoothShare.CONTENT_URI + "/" + sessionId);
                BluetoothOppUtility.updateVisibilityToHidden(this, contentUri);
                this.mTransferCursor.moveToNext();
            }
            updateNotificationWhenBtDisabled();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!this.mContextMenu) {
            this.mTransferCursor.moveToPosition(position);
            openCompleteTransfer();
            updateNotificationWhenBtDisabled();
        }
        this.mContextMenu = false;
    }

    private void openCompleteTransfer() {
        int sessionId = this.mTransferCursor.getInt(this.mIdColumnId);
        Uri contentUri = Uri.parse(BluetoothShare.CONTENT_URI + "/" + sessionId);
        BluetoothOppTransferInfo transInfo = BluetoothOppUtility.queryRecord(this, contentUri);
        if (transInfo == null) {
            Log.e(TAG, "Error: Can not get data from db");
            return;
        }
        if (transInfo.mDirection == 1 && BluetoothShare.isStatusSuccess(transInfo.mStatus)) {
            BluetoothOppUtility.updateVisibilityToHidden(this, contentUri);
            BluetoothOppUtility.openReceivedFile(this, transInfo.mFileName, transInfo.mFileType, transInfo.mTimeStamp, contentUri);
        } else {
            Intent in = new Intent(this, (Class<?>) BluetoothOppTransferActivity.class);
            in.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
            in.setDataAndNormalize(contentUri);
            startActivity(in);
        }
    }

    private void updateNotificationWhenBtDisabled() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (!adapter.isEnabled()) {
            this.mNotifier.updateNotification();
        }
    }
}
