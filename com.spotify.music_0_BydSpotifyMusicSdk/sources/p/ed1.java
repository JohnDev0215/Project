package p;

import com.google.common.base.Optional;
import com.spotify.automotive.baseui.login.AAMFullscreenDialogActivity;
import com.spotify.automotive.httpwebgategson.TokenResponse;
import com.spotify.base.java.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ed1 implements io.reactivex.rxjava3.functions.o, io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;

    public /* synthetic */ ed1(int i) {
        this.c = i;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public void accept(Object obj) {
        switch (this.c) {
            case 0:
                Logger.g("Session logged out.", new Object[0]);
                break;
            case 1:
                Logger.c((Throwable) obj, "Failed to log out.", new Object[0]);
                break;
            case 2:
                Logger.c((Throwable) obj, "Couldn't subscribe to get username", new Object[0]);
                break;
            case 3:
                Logger.c((Throwable) obj, "Failed to subscribe to product state.", new Object[0]);
                break;
            case 4:
                Logger.c((Throwable) obj, "unable to prefetch images", new Object[0]);
                break;
            case 5:
                Logger.c((Throwable) obj, "Couldn't add Account", new Object[0]);
                break;
            case 6:
                Logger.c((Throwable) obj, "Failed to subscribe to playerState", new Object[0]);
                break;
            case 7:
                Logger.c((Throwable) obj, "Exception setting liked state of track.", new Object[0]);
                break;
            case 8:
                Logger.c((Throwable) obj, "Exception subscribing to player state.", new Object[0]);
                break;
            case 9:
                int i = AAMFullscreenDialogActivity.H;
                Logger.b("Failed to subscribe to UX restrictions.", new Object[0]);
                break;
            case 10:
                Logger.b("Failed to subscribe to UX restrictions.", new Object[0]);
                break;
            case 11:
                Logger.b("Failed to subscribe to UX restrictions.", new Object[0]);
                break;
            case 12:
                Logger.c((Throwable) obj, "Error setting streaming bitrate.", new Object[0]);
                break;
            case 13:
                Logger.c((Throwable) obj, "Failed loading login state.", new Object[0]);
                break;
            case 14:
                Logger.c((Throwable) obj, "Failed loading user profile from backend.", new Object[0]);
                break;
            case 15:
                Logger.c((Throwable) obj, "Failed loading current social session.", new Object[0]);
                break;
            case 16:
                Logger.c((Throwable) obj, "Failed to leave social connect session", new Object[0]);
                break;
            case 17:
                Throwable th = (Throwable) obj;
                Logger.c(th, "Error logging out.", new Object[0]);
                lc.n("Failed to log out: " + th);
                break;
            case 18:
                Logger.c((Throwable) obj, "Exception getting liked state of item.", new Object[0]);
                break;
            case 19:
                Logger.c((Throwable) obj, "Error subscribing to connection type observable.", new Object[0]);
                break;
            case 20:
                Optional optional = (Optional) obj;
                if (optional.d() && ((TokenResponse) optional.c()).b != null) {
                    String str = ((TokenResponse) optional.c()).b;
                    break;
                }
                break;
            case 21:
                Logger.c((Throwable) obj, "Error fetching on-demand list", new Object[0]);
                break;
            case 22:
                Logger.c((Throwable) obj, "Exception while calling pause.", new Object[0]);
                break;
            case 23:
                Logger.c((Throwable) obj, "Exception while calling playUri.", new Object[0]);
                break;
            case 24:
                Logger.c((Throwable) obj, "Exception while calling pause.", new Object[0]);
                break;
            case 25:
                Logger.c((Throwable) obj, "Exception while calling skipToNext.", new Object[0]);
                break;
            case 26:
                break;
            case 27:
                Logger.c((Throwable) obj, "Exception while setting repeat.", new Object[0]);
                break;
            case 28:
                Logger.c((Throwable) obj, "Error subscribing to player state.", new Object[0]);
                break;
            default:
                Logger.c((Throwable) obj, "Exception while calling skipToQueueItem.", new Object[0]);
                break;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:118:?, code lost:
    
        r1 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:?, code lost:
    
        r1 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:?, code lost:
    
        r1 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:?, code lost:
    
        r1 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:?, code lost:
    
        r1 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:?, code lost:
    
        r1 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00b9, code lost:
    
        r1 = -1;
     */
    /* JADX WARN: Failed to clean up code after switch over string restore
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r1v0 int, still in use, count: 2, list:
  (r1v0 int) from 0x00fa: PHI (r1v14 int) = 
  (r1v3 int)
  (r1v4 int)
  (r1v5 int)
  (r1v6 int)
  (r1v0 int)
  (r1v7 int)
  (r1v8 int)
  (r1v9 int)
  (r1v10 int)
  (r1v11 int)
  (r1v12 int)
  (r1v13 int)
  (r1v15 int)
 binds: [B:123:?, B:53:0x00f9, B:122:?, B:49:0x00ee, B:44:0x00e2, B:121:?, B:120:?, B:42:0x00da, B:119:?, B:38:0x00cf, B:118:?, B:34:0x00c4, B:30:0x00b9] A[DONT_GENERATE, DONT_INLINE, REMOVE]
  (r1v0 int A[DONT_INLINE]) from 0x016a: CONSTRUCTOR (r1v0 int A[DONT_INLINE]), (r14v30 p.ji1 A[DONT_INLINE]) A[MD:(int, java.lang.Object):void (m), REMOVE, WRAPPED] call: p.i0.<init>(int, java.lang.Object):void type: CONSTRUCTOR
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
    	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
    	at jadx.core.utils.InsnRemover.removeAllMarked(InsnRemover.java:271)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.markCodeForRemoval(SwitchOverStringVisitor.java:160)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:124)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:71)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    @Override // io.reactivex.rxjava3.functions.o
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object apply(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 606
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ed1.apply(java.lang.Object):java.lang.Object");
    }
}
