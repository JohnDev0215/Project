package com.byd.vrassistant.utils;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class LoggerUtil {
    private static final long MAX_SIZE = 10485760;
    private static final String SUFFIX = "data-operate.txt";
    private static final String TAG = TagUtil.getTagName(LoggerUtil.class);
    private static Handler handler;
    private static HandlerThread handlerThread;

    /* JADX INFO: Access modifiers changed from: private */
    public static long getCurrentTimeLong() {
        return System.currentTimeMillis();
    }

    private static class OperateDataRunnable implements Runnable {
        private final String filePath;
        private final String msg;

        public OperateDataRunnable(String str, String str2) {
            this.filePath = str;
            this.msg = str2;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            String str;
            Log.d(LoggerUtil.TAG, "run() called with: filePath = [" + this.filePath + "], msg = [" + this.msg + "]");
            Log.i(LoggerUtil.TAG, "run: threadName = " + Thread.currentThread().getName() + " id = " + Thread.currentThread().getId());
            BufferedWriter bufferedWriter = null;
            try {
                try {
                    try {
                        File file = new File(this.filePath);
                        if (!file.exists()) {
                            boolean zMkdir = file.mkdir();
                            Log.i(LoggerUtil.TAG, "writerLog: dir = " + zMkdir);
                            if (!zMkdir) {
                                return;
                            }
                        }
                        long currentTimeLong = LoggerUtil.getCurrentTimeLong();
                        String[] list = file.list();
                        if (list == null || list.length <= 0) {
                            Log.i(LoggerUtil.TAG, "writerLog: list = null");
                            str = LoggerUtil.getCurrentTime() + "-data-operate.txt";
                        } else {
                            Log.i(LoggerUtil.TAG, "writerLog: list.length = " + list.length);
                            long j = 1704936628000L;
                            str = LoggerUtil.SUFFIX;
                            for (String str2 : list) {
                                long jLastModified = currentTimeLong - new File(file, str2).lastModified();
                                if (jLastModified < j) {
                                    str = str2;
                                    j = jLastModified;
                                }
                            }
                        }
                        Log.i(LoggerUtil.TAG, "writerLog: fileName = " + str);
                        File file2 = new File(file, str);
                        if (!file2.exists()) {
                            boolean zCreateNewFile = file2.createNewFile();
                            Log.i(LoggerUtil.TAG, "writerLog: newFile = " + zCreateNewFile);
                            if (!zCreateNewFile) {
                                return;
                            }
                        }
                        if (file2.length() > LoggerUtil.MAX_SIZE) {
                            file2 = new File(file, LoggerUtil.getCurrentTime() + "-" + LoggerUtil.SUFFIX);
                            boolean zCreateNewFile2 = file2.createNewFile();
                            Log.i(LoggerUtil.TAG, "writerLog: exceed max size newFile = " + zCreateNewFile2);
                            if (!zCreateNewFile2) {
                                return;
                            }
                        }
                        BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2, true));
                        try {
                            bufferedWriter2.write("[" + LoggerUtil.getCurrentTime() + "] - " + this.msg + "\n");
                            bufferedWriter2.close();
                            bufferedWriter2.close();
                        } catch (Exception e) {
                            bufferedWriter = bufferedWriter2;
                            e = e;
                            e.printStackTrace();
                            if (bufferedWriter != null) {
                                bufferedWriter.close();
                            }
                        } catch (Throwable th) {
                            bufferedWriter = bufferedWriter2;
                            th = th;
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    public static void writerLog(String str, String str2) {
        if (handlerThread == null) {
            HandlerThread handlerThread2 = new HandlerThread("data-operate");
            handlerThread = handlerThread2;
            handlerThread2.start();
        }
        if (handler == null) {
            handler = new Handler(handlerThread.getLooper());
        }
        handler.post(new OperateDataRunnable(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}
