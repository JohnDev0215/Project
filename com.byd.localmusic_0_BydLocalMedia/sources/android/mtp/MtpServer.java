package android.mtp;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.internal.util.Preconditions;
import java.io.FileDescriptor;
import java.util.Random;
import libcore.util.HexEncoding;

/* JADX INFO: loaded from: classes2.dex */
public class MtpServer implements Runnable {
    private static final int sID_LEN_BYTES = 16;
    private static final int sID_LEN_STR = 32;
    private final Context mContext;
    private final MtpDatabase mDatabase;
    private long mNativeContext;
    private final Runnable mOnTerminate;

    private final native void native_add_storage(MtpStorage mtpStorage);

    private final native void native_cleanup();

    public static final native void native_configure(boolean z);

    private final native void native_remove_storage(int i);

    private final native void native_run();

    private final native void native_send_device_property_changed(int i);

    private final native void native_send_object_added(int i);

    private final native void native_send_object_info_changed(int i);

    private final native void native_send_object_removed(int i);

    private final native void native_setup(MtpDatabase mtpDatabase, FileDescriptor fileDescriptor, boolean z, String str, String str2, String str3, String str4);

    static {
        System.loadLibrary("media_jni");
    }

    public MtpServer(MtpDatabase mtpDatabase, FileDescriptor fileDescriptor, boolean z, Runnable runnable, String str, String str2, String str3) {
        String str4;
        this.mDatabase = (MtpDatabase) Preconditions.checkNotNull(mtpDatabase);
        this.mOnTerminate = (Runnable) Preconditions.checkNotNull(runnable);
        Context context = this.mDatabase.getContext();
        this.mContext = context;
        int i = 0;
        SharedPreferences sharedPreferences = context.getSharedPreferences("mtp-cfg", 0);
        String str5 = null;
        if (sharedPreferences.contains("mtp-id")) {
            String string = sharedPreferences.getString("mtp-id", null);
            if (string.length() == 32) {
                while (true) {
                    if (i >= string.length()) {
                        str5 = string;
                        break;
                    } else if (Character.digit(string.charAt(i), 16) == -1) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        if (str5 == null) {
            String randId = getRandId();
            sharedPreferences.edit().putString("mtp-id", randId).apply();
            str4 = randId;
        } else {
            str4 = str5;
        }
        native_setup(mtpDatabase, fileDescriptor, z, str, str2, str3, str4);
        mtpDatabase.setServer(this);
    }

    private String getRandId() {
        byte[] bArr = new byte[16];
        new Random().nextBytes(bArr);
        return HexEncoding.encodeToString(bArr);
    }

    public void start() {
        new Thread(this, "MtpServer").start();
    }

    @Override // java.lang.Runnable
    public void run() {
        native_run();
        native_cleanup();
        this.mDatabase.close();
        this.mOnTerminate.run();
    }

    public void sendObjectAdded(int i) {
        native_send_object_added(i);
    }

    public void sendObjectRemoved(int i) {
        native_send_object_removed(i);
    }

    public void sendObjectInfoChanged(int i) {
        native_send_object_info_changed(i);
    }

    public void sendDevicePropertyChanged(int i) {
        native_send_device_property_changed(i);
    }

    public void addStorage(MtpStorage mtpStorage) {
        native_add_storage(mtpStorage);
    }

    public void removeStorage(MtpStorage mtpStorage) {
        native_remove_storage(mtpStorage.getStorageId());
    }

    public static void configure(boolean z) {
        native_configure(z);
    }
}
