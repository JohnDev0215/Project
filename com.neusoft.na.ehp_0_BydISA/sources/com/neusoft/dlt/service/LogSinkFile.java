package com.neusoft.dlt.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.material.timepicker.TimeModel;
import com.neusoft.dlt.LogService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.channels.WritableByteChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class LogSinkFile implements ILogSink {
    public static final String LOGTAG = "dltLog";
    private String ecu;
    private boolean enableSplit;
    private Context mContext;
    private byte[] mEcu;
    private String mFileNamePattern;
    private String mLogPath;
    private long mMaxFileSize;
    private int mMaxFilesNum;
    private String mPickedDir;
    private OpenFile mCurrentFile = null;
    private long mCurrentFileSize = 0;
    private boolean mRunning = false;
    private byte[] DLT_MAGIC = {68, 76, 84, 1};

    @Override // com.neusoft.dlt.service.ILogSink
    public void register(Selector selector) {
    }

    public LogSinkFile(Context context, String str, String str2, String str3, long j, int i, boolean z) {
        this.mContext = context;
        this.mLogPath = str;
        this.mFileNamePattern = str2;
        this.ecu = str3;
        this.mMaxFileSize = j;
        this.mMaxFilesNum = i;
        this.enableSplit = z;
        this.mPickedDir = str;
        this.mEcu = makeEcu(str3);
    }

    public String getLogPath() {
        return this.mLogPath;
    }

    @Override // com.neusoft.dlt.service.ILogSink
    public void logMessage(DltMessage dltMessage) {
        if (this.mRunning) {
            ByteBuffer storageHeader = getStorageHeader();
            ByteBuffer byteBuffer = dltMessage.toByteBuffer();
            int iRemaining = storageHeader.remaining() + byteBuffer.remaining();
            OpenFile openFileCreateNewLog = this.mCurrentFile;
            if (openFileCreateNewLog == null) {
                openFileCreateNewLog = createNewLog(this.enableSplit);
            } else if (this.mCurrentFileSize + ((long) iRemaining) > this.mMaxFileSize) {
                Log.i(LOGTAG, "file size limit reached: " + this.mCurrentFileSize);
                openFileCreateNewLog = createNewLog(this.enableSplit);
            }
            try {
                openFileCreateNewLog.channel.write(storageHeader);
                openFileCreateNewLog.channel.write(byteBuffer);
                this.mCurrentFileSize += (long) iRemaining;
            } catch (IOException e) {
                Log.e(LOGTAG, "Exception when writing message", e);
                closeStream();
            }
        }
    }

    @Override // com.neusoft.dlt.service.ILogSink
    public boolean start() {
        if (this.mRunning) {
            return true;
        }
        Log.i(LOGTAG, "Starting LogFileSink");
        this.mRunning = true;
        return true;
    }

    @Override // com.neusoft.dlt.service.ILogSink
    public boolean stop() {
        if (!this.mRunning) {
            return true;
        }
        Log.i(LOGTAG, "Stopping LogFileSink");
        closeStream();
        this.mRunning = false;
        return true;
    }

    private OpenFile createNewLog(boolean z) {
        String strCreateFileName;
        File fileCreateFile;
        closeStream();
        if (!z) {
            strCreateFileName = createFileName();
        } else {
            int availableLogFileID = getAvailableLogFileID();
            String strCreateFileNameForSplit = createFileNameForSplit(availableLogFileID);
            if (this.mPickedDir != null) {
                Log.d(LOGTAG, "Directory for log output:" + this.mPickedDir);
                removeOldestLogFile(this.mPickedDir, availableLogFileID);
            }
            strCreateFileName = strCreateFileNameForSplit;
        }
        String str = this.mPickedDir;
        if (str != null) {
            fileCreateFile = createFile(str, strCreateFileName);
        } else {
            Log.d(LOGTAG, "Directory for log output is null.");
            fileCreateFile = null;
        }
        try {
            if (fileCreateFile != null) {
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateFile);
                FileChannel channel = fileOutputStream.getChannel();
                Log.i(LOGTAG, "New log file started: '" + fileCreateFile.getName() + "'");
                OpenFile openFile = new OpenFile(fileOutputStream, channel);
                this.mCurrentFile = openFile;
                return openFile;
            }
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            String str2 = "Unable to open output stream for " + fileCreateFile.getName();
            Log.e(LOGTAG, str2);
            throw new RuntimeException(str2, e);
        }
    }

    private String createFileName() {
        return this.mFileNamePattern.replace(TimeModel.NUMBER_FORMAT, new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss", Locale.ENGLISH).format(Calendar.getInstance().getTime()));
    }

    private String createFileNameForSplit(int i) {
        String str = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss", Locale.ENGLISH).format(Calendar.getInstance().getTime()) + "." + i;
        Log.d(LOGTAG, "File name is " + str);
        return this.mFileNamePattern.replace(TimeModel.NUMBER_FORMAT, str);
    }

    private void closeStream() {
        try {
            try {
                OpenFile openFile = this.mCurrentFile;
                if (openFile != null) {
                    openFile.channel.close();
                    this.mCurrentFile.stream.close();
                }
            } catch (IOException e) {
                Log.e(LOGTAG, "Failed to close logfile output stream ", e);
            }
        } finally {
            this.mCurrentFile = null;
            this.mCurrentFileSize = 0L;
        }
    }

    private ByteBuffer getStorageHeader() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(16);
        byteBufferAllocateDirect.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocateDirect.put(this.DLT_MAGIC);
        byteBufferAllocateDirect.putInt((int) (jCurrentTimeMillis / 1000));
        byteBufferAllocateDirect.putInt((int) ((jCurrentTimeMillis % 1000) * 1000));
        byteBufferAllocateDirect.put(this.mEcu);
        byteBufferAllocateDirect.flip();
        return byteBufferAllocateDirect;
    }

    private int getAvailableLogFileID() {
        int i = this.mContext.getSharedPreferences("LogFileID", 0).getInt("fileID", 1);
        SharedPreferences.Editor editorEdit = this.mContext.getSharedPreferences("LogFileID", 0).edit();
        int i2 = i + 1;
        int i3 = i2 <= this.mMaxFilesNum ? i2 : 1;
        editorEdit.putInt("fileID", i3);
        editorEdit.apply();
        Log.d(LOGTAG, "getAvailableLogFileID:" + i3);
        return i3;
    }

    private void removeOldestLogFile(String str, int i) {
        ArrayList<String> fileName = getFileName(str, ".dlt");
        new ArrayList();
        if (fileName.size() == 0) {
            return;
        }
        Log.d(LOGTAG, "remove oldest log file ID:" + i);
        if (fileName != null) {
            for (int i2 = 0; i2 < fileName.size(); i2++) {
                Log.d(LOGTAG, "Got dlt file:" + fileName.get(i2));
                try {
                    List listAsList = Arrays.asList(fileName.get(i2).split("\\."));
                    if (listAsList != null && listAsList.size() > 0 && ((String) listAsList.get(listAsList.size() - 2)).equals(String.valueOf(i))) {
                        String str2 = str + fileName.get(i2);
                        Log.d(LOGTAG, "Remove the oldest log file: " + str2);
                        if (!new File(str2).delete()) {
                            Log.d(LOGTAG, "Delete file failed.");
                        }
                    }
                } catch (Exception e) {
                    Log.d(LOGTAG, e.toString());
                }
            }
        }
    }

    private ArrayList<String> getFileName(String str, String str2) {
        ArrayList<String> arrayList = new ArrayList<>();
        File file = new File(str);
        if (!file.exists()) {
            return arrayList;
        }
        File[] fileArrListFiles = file.listFiles();
        for (int i = 0; i < fileArrListFiles.length; i++) {
            if (!fileArrListFiles[i].isDirectory()) {
                String name = fileArrListFiles[i].getName();
                if (name.trim().toLowerCase().endsWith(str2)) {
                    arrayList.add(name);
                }
            }
        }
        return arrayList;
    }

    public File createFile(String str, String str2) {
        createDirectory(str);
        File file = new File(str, str2);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void createDirectory(String str) {
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        Log.d(LogService.LOGTAG, "mkdir :" + str);
        file.mkdirs();
    }

    class OpenFile {
        public WritableByteChannel channel;
        public FileOutputStream stream;

        public OpenFile(FileOutputStream fileOutputStream, WritableByteChannel writableByteChannel) {
            this.stream = fileOutputStream;
            this.channel = writableByteChannel;
        }
    }

    private byte[] makeEcu(String str) {
        byte[] bArr = new byte[4];
        byte[] bytes = str.getBytes();
        System.arraycopy(bytes, 0, bArr, 0, Math.min(4, bytes.length));
        return bArr;
    }
}
