package com.neusoft.na.dltdroid.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.neusoft.na.dltdroid.UtilKt;
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
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LogSinkFile.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 12\u00020\u0001:\u000212B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u000bH\u0002J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u001f\u001a\u00020\u000bH\u0002J0\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010!j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\"2\b\u0010#\u001a\u0004\u0018\u00010\u00052\b\u0010$\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020,H\u0016J\u001a\u0010-\u001a\u00020\u001a2\b\u0010#\u001a\u0004\u0018\u00010\u00052\u0006\u0010.\u001a\u00020\u000bH\u0002J\b\u0010/\u001a\u00020\rH\u0016J\b\u00100\u001a\u00020\rH\u0016R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/neusoft/na/dltdroid/service/LogSinkFile;", "Lcom/neusoft/na/dltdroid/service/ILogSink;", "mContext", "Landroid/content/Context;", "mLogPath", "", "mFileNamePattern", "ecu", "mMaxFileSize", "", "mMaxFilesNum", "", "enableSplit", "", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JIZ)V", "logPath", "getLogPath", "()Ljava/lang/String;", "mCurrentFile", "Lcom/neusoft/na/dltdroid/service/LogSinkFile$OpenFile;", "mCurrentFileSize", "mEcu", "", "mPickedDir", "mRunning", "closeStream", "", "createFileName", "createFileNameForSplit", "id", "createNewLog", "getAvailableLogFileID", "getFileName", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "fileAbsolutePath", "type", "getStorageHeader", "Ljava/nio/ByteBuffer;", "logMessage", NotificationCompat.CATEGORY_MESSAGE, "Lcom/neusoft/na/dltdroid/service/DltMessage;", "register", "selector", "Ljava/nio/channels/Selector;", "removeOldestLogFile", "logFileID", "start", "stop", "Companion", "OpenFile", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LogSinkFile implements ILogSink {
    private final boolean enableSplit;
    private final Context mContext;
    private OpenFile mCurrentFile;
    private long mCurrentFileSize;
    private final byte[] mEcu;
    private final String mFileNamePattern;
    private final String mLogPath;
    private final long mMaxFileSize;
    private final int mMaxFilesNum;
    private final String mPickedDir;
    private boolean mRunning;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String LOGTAG = "dltLog";
    private static final byte[] DLT_MAGIC = {68, 76, 84, 1};

    @Override // com.neusoft.na.dltdroid.service.ILogSink
    public void register(Selector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
    }

    /* JADX INFO: compiled from: LogSinkFile.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/neusoft/na/dltdroid/service/LogSinkFile$OpenFile;", "", "stream", "Ljava/io/FileOutputStream;", "channel", "Ljava/nio/channels/WritableByteChannel;", "(Ljava/io/FileOutputStream;Ljava/nio/channels/WritableByteChannel;)V", "getChannel", "()Ljava/nio/channels/WritableByteChannel;", "getStream", "()Ljava/io/FileOutputStream;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class OpenFile {
        private final WritableByteChannel channel;
        private final FileOutputStream stream;

        public static /* synthetic */ OpenFile copy$default(OpenFile openFile, FileOutputStream fileOutputStream, WritableByteChannel writableByteChannel, int i, Object obj) {
            if ((i & 1) != 0) {
                fileOutputStream = openFile.stream;
            }
            if ((i & 2) != 0) {
                writableByteChannel = openFile.channel;
            }
            return openFile.copy(fileOutputStream, writableByteChannel);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FileOutputStream getStream() {
            return this.stream;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final WritableByteChannel getChannel() {
            return this.channel;
        }

        public final OpenFile copy(FileOutputStream stream, WritableByteChannel channel) {
            Intrinsics.checkNotNullParameter(stream, "stream");
            Intrinsics.checkNotNullParameter(channel, "channel");
            return new OpenFile(stream, channel);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OpenFile)) {
                return false;
            }
            OpenFile openFile = (OpenFile) other;
            return Intrinsics.areEqual(this.stream, openFile.stream) && Intrinsics.areEqual(this.channel, openFile.channel);
        }

        public int hashCode() {
            return (this.stream.hashCode() * 31) + this.channel.hashCode();
        }

        public String toString() {
            return "OpenFile(stream=" + this.stream + ", channel=" + this.channel + ')';
        }

        public OpenFile(FileOutputStream stream, WritableByteChannel channel) {
            Intrinsics.checkNotNullParameter(stream, "stream");
            Intrinsics.checkNotNullParameter(channel, "channel");
            this.stream = stream;
            this.channel = channel;
        }

        public final WritableByteChannel getChannel() {
            return this.channel;
        }

        public final FileOutputStream getStream() {
            return this.stream;
        }
    }

    public LogSinkFile(Context mContext, String str, String mFileNamePattern, String ecu, long j, int i, boolean z) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(mFileNamePattern, "mFileNamePattern");
        Intrinsics.checkNotNullParameter(ecu, "ecu");
        this.mContext = mContext;
        this.mLogPath = str;
        this.mFileNamePattern = mFileNamePattern;
        this.mMaxFileSize = j;
        this.mMaxFilesNum = i;
        this.enableSplit = z;
        this.mPickedDir = str;
        this.mEcu = INSTANCE.makeEcu(ecu);
    }

    public final String getLogPath() {
        return String.valueOf(this.mLogPath);
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSink
    public boolean start() {
        if (this.mRunning) {
            return true;
        }
        Log.i(LOGTAG, "Starting LogFileSink");
        this.mRunning = true;
        return true;
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSink
    public boolean stop() {
        if (!this.mRunning) {
            return true;
        }
        Log.i(LOGTAG, "Stopping LogFileSink");
        closeStream();
        this.mRunning = false;
        return true;
    }

    @Override // com.neusoft.na.dltdroid.service.ILogSink
    public synchronized void logMessage(DltMessage msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (this.mRunning) {
            ByteBuffer storageHeader = getStorageHeader();
            ByteBuffer byteBuffer = msg.toByteBuffer();
            int iRemaining = storageHeader.remaining() + byteBuffer.remaining();
            OpenFile openFileCreateNewLog = this.mCurrentFile;
            if (openFileCreateNewLog == null) {
                openFileCreateNewLog = createNewLog(this.enableSplit);
            } else if (this.mCurrentFileSize + ((long) iRemaining) > this.mMaxFileSize) {
                Log.i(LOGTAG, "file size limit reached: " + this.mCurrentFileSize);
                openFileCreateNewLog = createNewLog(this.enableSplit);
            } else {
                Intrinsics.checkNotNull(openFileCreateNewLog);
            }
            try {
                openFileCreateNewLog.getChannel().write(storageHeader);
                openFileCreateNewLog.getChannel().write(byteBuffer);
                this.mCurrentFileSize += (long) iRemaining;
            } catch (IOException e) {
                Log.e(LOGTAG, "Exception when writing message", e);
                closeStream();
            }
        }
    }

    private final OpenFile createNewLog(boolean enableSplit) {
        String strCreateFileName;
        File fileCreateFile;
        closeStream();
        if (!enableSplit) {
            strCreateFileName = createFileName();
        } else {
            int availableLogFileID = getAvailableLogFileID();
            String strCreateFileNameForSplit = createFileNameForSplit(availableLogFileID);
            if (this.mPickedDir != null) {
                Log.i(LOGTAG, "Directory for log output:" + this.mPickedDir);
                removeOldestLogFile(this.mPickedDir, availableLogFileID);
            }
            strCreateFileName = strCreateFileNameForSplit;
        }
        String str = this.mPickedDir;
        if (str != null) {
            fileCreateFile = UtilKt.createFile(str, strCreateFileName);
        } else {
            Log.i(LOGTAG, "Directory for log output is null.");
            fileCreateFile = null;
        }
        try {
            if (fileCreateFile != null) {
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateFile);
                FileChannel currentChannel = fileOutputStream.getChannel();
                Log.i(LOGTAG, "New log file started: '" + fileCreateFile.getName() + '\'');
                Intrinsics.checkNotNullExpressionValue(currentChannel, "currentChannel");
                OpenFile openFile = new OpenFile(fileOutputStream, currentChannel);
                this.mCurrentFile = openFile;
                return openFile;
            }
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            StringBuilder sb = new StringBuilder("Unable to open output stream for ");
            String name = fileCreateFile != null ? fileCreateFile.getName() : null;
            Intrinsics.checkNotNull(name);
            String string = sb.append(name).toString();
            Log.e(LOGTAG, string);
            throw new RuntimeException(string, e);
        }
    }

    private final String createFileName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss", Locale.ENGLISH);
        String str = this.mFileNamePattern;
        Regex regex = new Regex("%d");
        String str2 = simpleDateFormat.format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(str2, "fmt.format(Calendar.getInstance().time)");
        return regex.replace(str, str2);
    }

    private final String createFileNameForSplit(int id) {
        String str = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss", Locale.ENGLISH).format(Calendar.getInstance().getTime()) + '.' + id;
        Log.i(LOGTAG, "File name is " + str);
        return new Regex("%d").replace(this.mFileNamePattern, str);
    }

    private final void closeStream() {
        try {
            try {
                OpenFile openFile = this.mCurrentFile;
                if (openFile != null) {
                    openFile.getChannel().close();
                    openFile.getStream().close();
                }
            } catch (IOException e) {
                Log.e(LOGTAG, "Failed to close logfile output stream ", e);
            }
        } finally {
            this.mCurrentFile = null;
            this.mCurrentFileSize = 0L;
        }
    }

    private final ByteBuffer getStorageHeader() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = 1000;
        ByteBuffer bb = ByteBuffer.allocateDirect(16);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.put(DLT_MAGIC);
        bb.putInt((int) (jCurrentTimeMillis / j));
        bb.putInt(((int) (jCurrentTimeMillis % j)) * 1000);
        bb.put(this.mEcu);
        bb.flip();
        Intrinsics.checkNotNullExpressionValue(bb, "bb");
        return bb;
    }

    private final int getAvailableLogFileID() {
        String str = LOGTAG;
        Log.i(str, "[getAvailableLogFileID] --> mMaxFilesNum == " + this.mMaxFilesNum);
        int i = this.mContext.getSharedPreferences("LogFileID", 0).getInt("fileID", 1);
        Log.i(str, "getInt logFileID == " + i);
        SharedPreferences.Editor editorEdit = this.mContext.getSharedPreferences("LogFileID", 0).edit();
        Intrinsics.checkNotNullExpressionValue(editorEdit, "this.mContext.getSharedP…text.MODE_PRIVATE).edit()");
        int i2 = i + 1;
        int i3 = i2 <= this.mMaxFilesNum ? i2 : 1;
        editorEdit.putInt("fileID", i3);
        Log.i(str, "putInt logFileID == " + i3);
        editorEdit.apply();
        Log.i(str, "getAvailableLogFileID:" + i3);
        return i3;
    }

    private final void removeOldestLogFile(String fileAbsolutePath, int logFileID) {
        ArrayList<String> fileName = getFileName(fileAbsolutePath, ".dlt");
        new ArrayList();
        Integer numValueOf = fileName != null ? Integer.valueOf(fileName.size()) : null;
        Intrinsics.checkNotNull(numValueOf);
        if (numValueOf.intValue() == 0) {
            return;
        }
        Log.i(LOGTAG, "remove oldest log file ID:" + logFileID);
        if (fileName == null || !(!fileName.isEmpty())) {
            return;
        }
        int size = fileName.size();
        for (int i = 0; i < size; i++) {
            String str = LOGTAG;
            Log.i(str, "Got dlt file:" + fileName.get(i));
            try {
                String str2 = fileName.get(i);
                Intrinsics.checkNotNullExpressionValue(str2, "logFileList[i]");
                List listSplit$default = StringsKt.split$default((CharSequence) str2, new String[]{"."}, false, 0, 6, (Object) null);
                Intrinsics.checkNotNull(listSplit$default, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.String> }");
                ArrayList arrayList = (ArrayList) listSplit$default;
                if ((!arrayList.isEmpty()) && Intrinsics.areEqual(arrayList.get(arrayList.size() - 2), String.valueOf(logFileID))) {
                    String str3 = fileAbsolutePath + fileName.get(i);
                    Log.i(str, "Remove the oldest log file: " + str3);
                    if (!new File(str3).delete()) {
                        Log.i(str, "Delete file failed.");
                    }
                }
            } catch (Exception e) {
                Log.i(LOGTAG, String.valueOf(e));
            }
        }
    }

    private final ArrayList<String> getFileName(String fileAbsolutePath, String type) {
        ArrayList<String> arrayList = new ArrayList<>();
        File file = new File(fileAbsolutePath);
        if (!file.exists()) {
            return arrayList;
        }
        File[] fileArrListFiles = file.listFiles();
        int length = fileArrListFiles.length;
        for (int i = 0; i < length; i++) {
            if (!fileArrListFiles[i].isDirectory()) {
                String fileName = fileArrListFiles[i].getName();
                Intrinsics.checkNotNullExpressionValue(fileName, "fileName");
                String str = fileName;
                int length2 = str.length() - 1;
                int i2 = 0;
                boolean z = false;
                while (i2 <= length2) {
                    boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i2 : length2), 32) <= 0;
                    if (z) {
                        if (!z2) {
                            break;
                        }
                        length2--;
                    } else if (z2) {
                        i2++;
                    } else {
                        z = true;
                    }
                }
                String lowerCase = str.subSequence(i2, length2 + 1).toString().toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                Intrinsics.checkNotNull(type);
                if (StringsKt.endsWith$default(lowerCase, type, false, 2, (Object) null)) {
                    arrayList.add(fileName);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: LogSinkFile.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/neusoft/na/dltdroid/service/LogSinkFile$Companion;", "", "()V", "DLT_MAGIC", "", "LOGTAG", "", "getLOGTAG", "()Ljava/lang/String;", "makeEcu", "ecuId", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getLOGTAG() {
            return LogSinkFile.LOGTAG;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final byte[] makeEcu(String ecuId) {
            byte[] bArr = new byte[4];
            byte[] bytes = ecuId.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            System.arraycopy(bytes, 0, bArr, 0, Math.min(4, bytes.length));
            return bArr;
        }
    }
}
