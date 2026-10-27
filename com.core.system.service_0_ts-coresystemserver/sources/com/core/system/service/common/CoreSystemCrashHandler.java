package com.core.system.service.common;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.ts.car.sdk.core.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class CoreSystemCrashHandler {
    public static final String EXCEPTION_FILE_PATH = "/sdcard/trace";
    private static final String FILE_NAME_SUFFIX = ".trace";
    public static final int MSG_CRASH_SERVICE = 2000;
    private static final String TAG = "CoreSystemCrashHandler";
    private static CoreSystemCrashHandler sInstance = new CoreSystemCrashHandler();
    private ExceptionCallBack mCallBack;
    StackTraceElement[] mElements;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    public interface ExceptionCallBack {
        void onServiceException(String[] strArr);
    }

    private CoreSystemCrashHandler() {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() { // from class: com.core.system.service.common.CoreSystemCrashHandler.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable ex) {
                Logger.error(CoreSystemCrashHandler.TAG, "[" + Process.myTid() + "]uncaughtException: ", ex);
                StackTraceElement[] elements = ex.getStackTrace();
                if (elements != null) {
                    Message message = Message.obtain();
                    message.what = CoreSystemCrashHandler.MSG_CRASH_SERVICE;
                    message.obj = elements;
                    CoreSystemCrashHandler.this.mHandler.sendMessage(message);
                }
                CoreSystemCrashHandler.this.loopHandle();
            }
        };
        this.mUncaughtExceptionHandler = uncaughtExceptionHandler;
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.core.system.service.common.CoreSystemCrashHandler.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                CoreSystemCrashHandler.this.asyncHandleMessage(msg);
            }
        };
    }

    public void asyncHandleMessage(Message msg) {
        if (msg.what == 2000) {
            StackTraceElement[] elements = (StackTraceElement[]) msg.obj;
            try {
                exportExceptionToFile(elements);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String[] stackStr = new String[elements.length];
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] != null) {
                    stackStr[i] = elements[i].getClassName();
                }
                Logger.debug(TAG, "uncaughtException() stackStr" + stackStr[i]);
            }
            ExceptionCallBack exceptionCallBack = this.mCallBack;
            if (exceptionCallBack != null) {
                exceptionCallBack.onServiceException(stackStr);
            } else {
                Logger.debug(TAG, "uncaughtException() mCallBack == null");
            }
        }
    }

    public static synchronized CoreSystemCrashHandler getInstance() {
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loopHandle() {
        while (true) {
            try {
                Logger.debug(TAG, "uncaughtException Looper.getMainLooper() ！");
                if (Looper.myLooper() == null) {
                    Looper.prepare();
                }
                Looper.loop();
            } catch (Throwable ex) {
                Logger.error(TAG, "loopHandle uncaughtException", ex);
            }
        }
    }

    public void registerCallBack(ExceptionCallBack cb) {
        synchronized (this) {
            this.mCallBack = cb;
            Logger.debug(TAG, "registerCallBack() mCallBack" + this.mCallBack);
        }
    }

    public void unregisterCallBack() {
        synchronized (this) {
            this.mCallBack = null;
            if (this.mHandlerThread != null) {
                this.mHandlerThread.quit();
                this.mHandlerThread = null;
            }
        }
    }

    private void exportExceptionToFile(StackTraceElement[] elements) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            Logger.debug(TAG, "exportExceptionToFile() SD card dose mount.");
            return;
        }
        String date = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss").format(new Date());
        FileOutputStream fos = null;
        try {
            try {
                try {
                    File dir = new File(EXCEPTION_FILE_PATH);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File file = new File(EXCEPTION_FILE_PATH + File.separator + date + FILE_NAME_SUFFIX);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    StringBuffer exceptionStr = new StringBuffer();
                    if (elements != null) {
                        for (StackTraceElement stackTraceElement : elements) {
                            exceptionStr.append(stackTraceElement.toString() + "\n");
                        }
                    }
                    fos = new FileOutputStream(file);
                    fos.write(exceptionStr.toString().getBytes(StandardCharsets.UTF_8));
                    fos.close();
                } catch (IOException ex) {
                    Logger.error(TAG, "exportExceptionToFile() write file error.");
                    ex.printStackTrace();
                    if (fos != null) {
                        fos.close();
                    }
                }
            } catch (Throwable th) {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException ex2) {
                        Logger.warning(TAG, ex2);
                    }
                }
                throw th;
            }
        } catch (IOException ex3) {
            Logger.warning(TAG, ex3);
        }
    }
}
