package com.android.server.telecom.settings;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BlockedNumberContract;

/* JADX INFO: loaded from: classes.dex */
public class BlockNumberTaskFragment extends Fragment {
    Listener mListener;
    private BlockNumberTask mTask;

    public interface Listener {
        void onBlocked(String str, boolean z);
    }

    private class BlockNumberTask extends AsyncTask<String, Void, Boolean> {
        private String mNumber;

        private BlockNumberTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(String... strArr) {
            this.mNumber = strArr[0];
            if (BlockedNumberContract.isBlocked(BlockNumberTaskFragment.this.getContext(), this.mNumber)) {
                return Boolean.FALSE;
            }
            ContentResolver contentResolver = BlockNumberTaskFragment.this.getContext().getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put("original_number", this.mNumber);
            contentResolver.insert(BlockedNumberContract.BlockedNumbers.CONTENT_URI, contentValues);
            return Boolean.TRUE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            BlockNumberTaskFragment.this.mTask = null;
            Listener listener = BlockNumberTaskFragment.this.mListener;
            if (listener != null) {
                listener.onBlocked(this.mNumber, !bool.booleanValue());
            }
            BlockNumberTaskFragment.this.mListener = null;
        }
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        BlockNumberTask blockNumberTask = this.mTask;
        if (blockNumberTask != null) {
            blockNumberTask.cancel(true);
        }
        super.onDestroy();
    }

    public void blockIfNotAlreadyBlocked(String str, Listener listener) {
        this.mListener = listener;
        BlockNumberTask blockNumberTask = new BlockNumberTask();
        this.mTask = blockNumberTask;
        blockNumberTask.execute(str);
    }
}
