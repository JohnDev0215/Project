package com.android.bluetooth.map;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.Telephony;
import android.util.Log;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.PduComposer;
import com.google.android.mms.pdu.PduPersister;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class MmsFileProvider extends ContentProvider {
    static final Uri CONTENT_URI = Uri.parse("content://com.android.bluetooth.map.MmsFileProvider");
    static final String TAG = "BluetoothMmsFileProvider";
    private PipeWriter mPipeWriter = new PipeWriter();

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String fileMode) throws FileNotFoundException {
        String idStr = uri.getLastPathSegment();
        if (idStr == null) {
            throw new FileNotFoundException("Unable to extract message handle from: " + uri);
        }
        try {
            Long.parseLong(idStr);
            Uri messageUri = Telephony.Mms.CONTENT_URI.buildUpon().appendEncodedPath(idStr).build();
            return openPipeHelper(messageUri, null, null, null, this.mPipeWriter);
        } catch (NumberFormatException e) {
            Log.w(TAG, e);
            throw new FileNotFoundException("Unable to extract message handle from: " + uri);
        }
    }

    public class PipeWriter implements ContentProvider.PipeDataWriter<Cursor> {
        public PipeWriter() {
        }

        @Override // android.content.ContentProvider.PipeDataWriter
        public void writeDataToPipe(ParcelFileDescriptor output, Uri uri, String mimeType, Bundle opts, Cursor c) {
            FileOutputStream fout = null;
            PduPersister pduPersister = null;
            try {
                try {
                    try {
                        try {
                            fout = new FileOutputStream(output.getFileDescriptor());
                            pduPersister = PduPersister.getPduPersister(MmsFileProvider.this.getContext());
                            GenericPdu pdu = pduPersister.load(uri);
                            byte[] bytes = new PduComposer(MmsFileProvider.this.getContext(), pdu).make();
                            fout.write(bytes);
                            if (pduPersister != null) {
                                pduPersister.release();
                            }
                            try {
                                fout.flush();
                            } catch (IOException e) {
                                Log.w(MmsFileProvider.TAG, "IOException: ", e);
                            }
                            fout.close();
                        } finally {
                        }
                    } catch (IOException e2) {
                        Log.w(MmsFileProvider.TAG, e2);
                        if (pduPersister != null) {
                            pduPersister.release();
                        }
                        try {
                            fout.flush();
                        } catch (IOException e3) {
                            Log.w(MmsFileProvider.TAG, "IOException: ", e3);
                        }
                        fout.close();
                    }
                } catch (MmsException e4) {
                    Log.w(MmsFileProvider.TAG, (Throwable) e4);
                    if (pduPersister != null) {
                        pduPersister.release();
                    }
                    try {
                        fout.flush();
                    } catch (IOException e5) {
                        Log.w(MmsFileProvider.TAG, "IOException: ", e5);
                    }
                    fout.close();
                }
            } catch (IOException e6) {
                Log.w(MmsFileProvider.TAG, "IOException: ", e6);
            }
        }
    }
}
