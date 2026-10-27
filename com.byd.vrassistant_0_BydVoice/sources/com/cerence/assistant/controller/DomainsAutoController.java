package com.cerence.assistant.controller;

import cerence.ark.assistant.common.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class DomainsAutoController {
    private static final String TAG = "DomainsAutoController";

    /* JADX WARN: Can't wrap try/catch for region: R(11:7|8|(2:42|43)(8:13|14|(3:16|17|(1:19)(2:20|(1:22)(2:23|(1:25)(1:26))))(2:27|(1:29)(5:31|74|32|(8:37|67|45|69|59|(1:61)|65|66)|38))|30|74|32|(9:34|37|67|45|69|59|(0)|65|66)|38)|44|67|45|69|59|(0)|65|66) */
    /* JADX WARN: Can't wrap try/catch for region: R(12:0|2|71|3|73|(8:5|(11:7|8|(2:42|43)(8:13|14|(3:16|17|(1:19)(2:20|(1:22)(2:23|(1:25)(1:26))))(2:27|(1:29)(5:31|74|32|(8:37|67|45|69|59|(1:61)|65|66)|38))|30|74|32|(9:34|37|67|45|69|59|(0)|65|66)|38)|44|67|45|69|59|(0)|65|66)(2:49|50)|58|69|59|(0)|65|66)(1:52)|51|69|59|(0)|65|66) */
    /* JADX WARN: Can't wrap try/catch for region: R(8:13|14|(3:16|17|(1:19)(2:20|(1:22)(2:23|(1:25)(1:26))))(2:27|(1:29)(5:31|74|32|(8:37|67|45|69|59|(1:61)|65|66)|38))|30|74|32|(9:34|37|67|45|69|59|(0)|65|66)|38) */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0144, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0145, code lost:
    
        r10 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x016e, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01ba, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01bb, code lost:
    
        cerence.ark.assistant.common.Logger.error(com.cerence.assistant.controller.DomainsAutoController.TAG, java.lang.String.format("%s: ", "getVrVersion"), r0);
     */
    /* JADX WARN: Not initialized variable reg: 16, insn: 0x0197: MOVE (r10 I:??[OBJECT, ARRAY]) = (r16 I:??[OBJECT, ARRAY]), block:B:55:0x0197 */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01b6 A[Catch: Exception -> 0x01ba, TRY_LEAVE, TryCatch #1 {Exception -> 0x01ba, blocks: (B:59:0x01ac, B:61:0x01b6), top: B:69:0x01ac }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getVrVersion(java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 463
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cerence.assistant.controller.DomainsAutoController.getVrVersion(java.lang.String):java.lang.String");
    }

    public static int deleteSession(String str, String str2, String str3) {
        try {
            Logger.debug(TAG, String.format("**GRPC_deleteSession** dataPath = %s, userId = %s, deviceId = %s", str, str2, str3));
            return new DeleteSessionClient(new GrpcClient(str), str2, str3).execute();
        } catch (Exception e) {
            Logger.error(TAG, "**GRPC_deleteSession** deleteSession Exception ", e);
            return -12;
        }
    }
}
