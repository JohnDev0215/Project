package com.android.services.telephony.sip;

import android.content.Context;
import android.net.sip.SipProfile;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class SipProfileDb {
    private Context mContext;
    private int mProfilesCount = -1;
    private String mProfilesDirectory;
    private SipPreferences mSipPreferences;

    public SipProfileDb(Context context) {
        this.mContext = context.createCredentialProtectedStorageContext();
        setupDatabase();
    }

    public void accessDEStorageForMigration() {
        this.mContext = this.mContext.createDeviceProtectedStorageContext();
        setupDatabase();
    }

    private void setupDatabase() {
        this.mProfilesDirectory = this.mContext.getFilesDir().getAbsolutePath() + "/profiles/";
        this.mSipPreferences = new SipPreferences(this.mContext);
    }

    public void deleteProfile(SipProfile sipProfile) throws IOException {
        synchronized (SipProfileDb.class) {
            File file = new File(this.mProfilesDirectory, sipProfile.getProfileName());
            if (!isChild(new File(this.mProfilesDirectory), file)) {
                throw new IOException("Invalid Profile Credentials!");
            }
            deleteProfile(file);
            if (this.mProfilesCount < 0) {
                retrieveSipProfileListInternal();
            }
        }
    }

    private void deleteProfile(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                deleteProfile(file2);
            }
        }
        file.delete();
    }

    public void cleanupUponMigration() {
        File file = new File(this.mProfilesDirectory);
        if (file.isDirectory()) {
            file.delete();
        }
        this.mSipPreferences.clearSharedPreferences();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0061 A[Catch: all -> 0x006d, TRY_ENTER, TryCatch #5 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x000a, B:9:0x0022, B:11:0x0028, B:12:0x002b, B:17:0x004a, B:18:0x004d, B:34:0x0061, B:35:0x0064, B:36:0x0065, B:37:0x006c), top: B:48:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void saveProfile(android.net.sip.SipProfile r5) throws java.io.IOException {
        /*
            r4 = this;
            java.lang.Class<com.android.services.telephony.sip.SipProfileDb> r0 = com.android.services.telephony.sip.SipProfileDb.class
            monitor-enter(r0)
            int r1 = r4.mProfilesCount     // Catch: java.lang.Throwable -> L6d
            if (r1 >= 0) goto La
            r4.retrieveSipProfileListInternal()     // Catch: java.lang.Throwable -> L6d
        La:
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L6d
            java.lang.String r2 = r4.mProfilesDirectory     // Catch: java.lang.Throwable -> L6d
            java.lang.String r3 = r5.getProfileName()     // Catch: java.lang.Throwable -> L6d
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L6d
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L6d
            java.lang.String r3 = r4.mProfilesDirectory     // Catch: java.lang.Throwable -> L6d
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L6d
            boolean r4 = r4.isChild(r2, r1)     // Catch: java.lang.Throwable -> L6d
            if (r4 == 0) goto L65
            boolean r4 = r1.exists()     // Catch: java.lang.Throwable -> L6d
            if (r4 != 0) goto L2b
            r1.mkdirs()     // Catch: java.lang.Throwable -> L6d
        L2b:
            android.util.AtomicFile r4 = new android.util.AtomicFile     // Catch: java.lang.Throwable -> L6d
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L6d
            java.lang.String r3 = ".pobj"
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L6d
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L6d
            r1 = 0
            java.io.FileOutputStream r2 = r4.startWrite()     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            java.io.ObjectOutputStream r3 = new java.io.ObjectOutputStream     // Catch: java.io.IOException -> L51 java.lang.Throwable -> L55
            r3.<init>(r2)     // Catch: java.io.IOException -> L51 java.lang.Throwable -> L55
            r3.writeObject(r5)     // Catch: java.io.IOException -> L4f java.lang.Throwable -> L5d
            r3.flush()     // Catch: java.io.IOException -> L4f java.lang.Throwable -> L5d
            r4.finishWrite(r2)     // Catch: java.io.IOException -> L4f java.lang.Throwable -> L5d
            r3.close()     // Catch: java.lang.Throwable -> L6d
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6d
            return
        L4f:
            r5 = move-exception
            goto L53
        L51:
            r5 = move-exception
            r3 = r1
        L53:
            r1 = r2
            goto L59
        L55:
            r4 = move-exception
            goto L5f
        L57:
            r5 = move-exception
            r3 = r1
        L59:
            r4.failWrite(r1)     // Catch: java.lang.Throwable -> L5d
            throw r5     // Catch: java.lang.Throwable -> L5d
        L5d:
            r4 = move-exception
            r1 = r3
        L5f:
            if (r1 == 0) goto L64
            r1.close()     // Catch: java.lang.Throwable -> L6d
        L64:
            throw r4     // Catch: java.lang.Throwable -> L6d
        L65:
            java.io.IOException r4 = new java.io.IOException     // Catch: java.lang.Throwable -> L6d
            java.lang.String r5 = "Invalid Profile Credentials!"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L6d
            throw r4     // Catch: java.lang.Throwable -> L6d
        L6d:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6d
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.services.telephony.sip.SipProfileDb.saveProfile(android.net.sip.SipProfile):void");
    }

    public List<SipProfile> retrieveSipProfileList() {
        List<SipProfile> listRetrieveSipProfileListInternal;
        synchronized (SipProfileDb.class) {
            listRetrieveSipProfileListInternal = retrieveSipProfileListInternal();
        }
        return listRetrieveSipProfileListInternal;
    }

    private List<SipProfile> retrieveSipProfileListInternal() throws Throwable {
        List<SipProfile> listSynchronizedList = Collections.synchronizedList(new ArrayList());
        String[] list = new File(this.mProfilesDirectory).list();
        if (list == null) {
            return listSynchronizedList;
        }
        for (String str : list) {
            SipProfile sipProfileRetrieveSipProfileFromName = retrieveSipProfileFromName(str);
            if (sipProfileRetrieveSipProfileFromName != null) {
                listSynchronizedList.add(sipProfileRetrieveSipProfileFromName);
            }
        }
        this.mProfilesCount = listSynchronizedList.size();
        return listSynchronizedList;
    }

    public SipProfile retrieveSipProfileFromName(String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(new File(new File(this.mProfilesDirectory), str), ".pobj");
        if (file.exists()) {
            try {
                SipProfile sipProfileDeserialize = deserialize(file);
                if (sipProfileDeserialize != null) {
                    if (str.equals(sipProfileDeserialize.getProfileName())) {
                        return sipProfileDeserialize;
                    }
                }
            } catch (IOException e) {
                log("retrieveSipProfileListInternal, exception: " + e);
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.net.sip.SipProfile deserialize(java.io.File r4) throws java.lang.Throwable {
        /*
            r3 = this;
            android.util.AtomicFile r3 = new android.util.AtomicFile
            r3.<init>(r4)
            r4 = 0
            java.io.ObjectInputStream r0 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L1b java.lang.ClassNotFoundException -> L1d
            java.io.FileInputStream r3 = r3.openRead()     // Catch: java.lang.Throwable -> L1b java.lang.ClassNotFoundException -> L1d
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L1b java.lang.ClassNotFoundException -> L1d
            java.lang.Object r3 = r0.readObject()     // Catch: java.lang.ClassNotFoundException -> L19 java.lang.Throwable -> L39
            android.net.sip.SipProfile r3 = (android.net.sip.SipProfile) r3     // Catch: java.lang.ClassNotFoundException -> L19 java.lang.Throwable -> L39
            r0.close()
            return r3
        L19:
            r3 = move-exception
            goto L1f
        L1b:
            r3 = move-exception
            goto L3b
        L1d:
            r3 = move-exception
            r0 = r4
        L1f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L39
            r1.<init>()     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = "deserialize, exception: "
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            r1.append(r3)     // Catch: java.lang.Throwable -> L39
            java.lang.String r3 = r1.toString()     // Catch: java.lang.Throwable -> L39
            log(r3)     // Catch: java.lang.Throwable -> L39
            if (r0 == 0) goto L38
            r0.close()
        L38:
            return r4
        L39:
            r3 = move-exception
            r4 = r0
        L3b:
            if (r4 == 0) goto L40
            r4.close()
        L40:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.services.telephony.sip.SipProfileDb.deserialize(java.io.File):android.net.sip.SipProfile");
    }

    private static void log(String str) {
        Log.d("SIP", "[SipProfileDb] " + str);
    }

    private boolean isChild(File file, File file2) {
        if (file == null || file2 == null) {
            return false;
        }
        if (file.equals(file2.getAbsoluteFile().getParentFile())) {
            return true;
        }
        Log.w("SIP", "isChild, file is not a child of the base dir.");
        EventLog.writeEvent(1397638484, "31530456", -1, "");
        return false;
    }
}
