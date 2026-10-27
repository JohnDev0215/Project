package com.neusoft.na.dltdroid;

import android.content.Context;
import android.content.UriPermission;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.system.Os;
import android.system.StructStatVfs;
import android.util.Log;
import com.neusoft.na.ocservice.OneCoreService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Util.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0010\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t\u001a\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t\u001a\u001d\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0010\u001a\b\u0010\u0011\u001a\u0004\u0018\u00010\t\u001a\u0016\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a \u0010\u0013\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\t¨\u0006\u0015"}, d2 = {"checkFilePermissions", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "createDirectory", "", "fileDirectory", "", "createFile", "Ljava/io/File;", OneCoreService.PAYLOAD_KEY_PATH, "name", "getFreeSpace", "", "(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/Long;", "getIpAsString", "isPathAccessible", "writeToTxt", "content", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class UtilKt {
    public static final String getIpAsString() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Intrinsics.checkNotNullExpressionValue(networkInterfaces, "getNetworkInterfaces()");
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                Intrinsics.checkNotNullExpressionValue(networkInterfaceNextElement, "en.nextElement()");
                Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                Intrinsics.checkNotNullExpressionValue(inetAddresses, "intf.getInetAddresses()");
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    Intrinsics.checkNotNullExpressionValue(inetAddressNextElement, "enumIpAddr.nextElement()");
                    InetAddress inetAddress = inetAddressNextElement;
                    if (!inetAddress.isLoopbackAddress() && inetAddress.isSiteLocalAddress() && (inetAddress instanceof Inet4Address)) {
                        String hostAddress = ((Inet4Address) inetAddress).getHostAddress();
                        Intrinsics.checkNotNullExpressionValue(hostAddress, "inetAddress.hostAddress");
                        return hostAddress;
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            Log.e(MainActivity.INSTANCE.getLOGTAG(), e.toString());
            return null;
        }
    }

    public static final boolean checkFilePermissions(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (Intrinsics.areEqual(uri.getScheme(), "file")) {
            return true;
        }
        List<UriPermission> persistedUriPermissions = context.getContentResolver().getPersistedUriPermissions();
        Intrinsics.checkNotNullExpressionValue(persistedUriPermissions, "context.contentResolver.persistedUriPermissions");
        List<UriPermission> list = persistedUriPermissions;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (UriPermission uriPermission : list) {
                if (Intrinsics.areEqual(uriPermission.getUri(), uri) && uriPermission.isWritePermission() && uriPermission.isReadPermission()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean isPathAccessible(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (Intrinsics.areEqual(uri.getScheme(), "file")) {
            String path = uri.getPath();
            if (path == null) {
                return false;
            }
            new File(path).canWrite();
            return false;
        }
        try {
            Intrinsics.checkNotNull(context.getContentResolver().openFileDescriptor(DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri)), "r"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static final Long getFreeSpace(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (Intrinsics.areEqual(uri.getScheme(), "file")) {
            String path = uri.getPath();
            if (path != null) {
                return Long.valueOf(new File(path).getFreeSpace());
            }
        } else {
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri)), "r");
                Intrinsics.checkNotNull(parcelFileDescriptorOpenFileDescriptor);
                StructStatVfs structStatVfsFstatvfs = Os.fstatvfs(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                return Long.valueOf(structStatVfsFstatvfs.f_bavail * structStatVfsFstatvfs.f_bsize);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static final boolean writeToTxt(String path, String name, String str) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(name, "name");
        createDirectory(path);
        File file = new File(path + name);
        try {
            file.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            bufferedWriter.write(str);
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static final File createFile(String path, String name) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(name, "name");
        createDirectory(path);
        File file = new File(path, name);
        file.createNewFile();
        return file;
    }

    public static final void createDirectory(String str) {
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        Log.d(LogService.INSTANCE.getLOGTAG(), "mkdir :" + str);
        file.mkdirs();
    }
}
