package io.branch.referral.network;

import android.content.Context;
import io.branch.referral.PrefHelper;
import io.branch.referral.network.BranchRemoteInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
public class BranchRemoteInterfaceUrlConnection extends BranchRemoteInterface {
    private PrefHelper prefHelper;

    BranchRemoteInterfaceUrlConnection(Context context) {
        this.prefHelper = PrefHelper.getInstance(context);
    }

    @Override // io.branch.referral.network.BranchRemoteInterface
    public BranchRemoteInterface.BranchResponse doRestfulGet(String str) throws BranchRemoteInterface.BranchRemoteException {
        return doRestfulGet(str, 0);
    }

    @Override // io.branch.referral.network.BranchRemoteInterface
    public BranchRemoteInterface.BranchResponse doRestfulPost(String str, JSONObject jSONObject) throws BranchRemoteInterface.BranchRemoteException {
        return doRestfulPost(str, jSONObject, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0135  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private io.branch.referral.network.BranchRemoteInterface.BranchResponse doRestfulGet(java.lang.String r7, int r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.network.BranchRemoteInterfaceUrlConnection.doRestfulGet(java.lang.String, int):io.branch.referral.network.BranchRemoteInterface$BranchResponse");
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0123: MOVE (r4 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:71:0x0123 */
    /* JADX WARN: Removed duplicated region for block: B:104:0x00e1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0098 A[Catch: all -> 0x00b3, FileNotFoundException -> 0x00b6, TRY_LEAVE, TryCatch #17 {FileNotFoundException -> 0x00b6, all -> 0x00b3, blocks: (B:24:0x008d, B:26:0x0093, B:27:0x0098), top: B:111:0x008d }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00d2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private io.branch.referral.network.BranchRemoteInterface.BranchResponse doRestfulPost(java.lang.String r9, org.json.JSONObject r10, int r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.network.BranchRemoteInterfaceUrlConnection.doRestfulPost(java.lang.String, org.json.JSONObject, int):io.branch.referral.network.BranchRemoteInterface$BranchResponse");
    }

    private String getResponseString(InputStream inputStream) {
        if (inputStream != null) {
            try {
                return new BufferedReader(new InputStreamReader(inputStream)).readLine();
            } catch (IOException unused) {
            }
        }
        return null;
    }
}
