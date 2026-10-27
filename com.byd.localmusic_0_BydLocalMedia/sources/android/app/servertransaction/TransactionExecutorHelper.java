package android.app.servertransaction;

import android.app.Activity;
import android.app.ActivityThread;
import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.util.IntArray;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TransactionExecutorHelper {
    private static final int DESTRUCTION_PENALTY = 10;
    private static final int[] ON_RESUME_PRE_EXCUTION_STATES = {2, 4};
    private IntArray mLifecycleSequence = new IntArray(6);

    public IntArray getLifecyclePath(int i, int i2, boolean z) {
        if (i == -1 || i2 == -1) {
            throw new IllegalArgumentException("Can't resolve lifecycle path for undefined state");
        }
        if (i == 7 || i2 == 7) {
            throw new IllegalArgumentException("Can't start or finish in intermittent RESTART state");
        }
        if (i2 == 0 && i != i2) {
            throw new IllegalArgumentException("Can only start in pre-onCreate state");
        }
        this.mLifecycleSequence.clear();
        if (i2 >= i) {
            if (i == 2 && i2 == 5) {
                this.mLifecycleSequence.add(5);
            } else {
                for (int i3 = i + 1; i3 <= i2; i3++) {
                    this.mLifecycleSequence.add(i3);
                }
            }
        } else if (i == 4 && i2 == 3) {
            this.mLifecycleSequence.add(3);
        } else if (i > 5 || i2 < 2) {
            for (int i4 = i + 1; i4 <= 6; i4++) {
                this.mLifecycleSequence.add(i4);
            }
            for (int i5 = 1; i5 <= i2; i5++) {
                this.mLifecycleSequence.add(i5);
            }
        } else {
            for (int i6 = i + 1; i6 <= 5; i6++) {
                this.mLifecycleSequence.add(i6);
            }
            this.mLifecycleSequence.add(7);
            for (int i7 = 2; i7 <= i2; i7++) {
                this.mLifecycleSequence.add(i7);
            }
        }
        if (z && this.mLifecycleSequence.size() != 0) {
            IntArray intArray = this.mLifecycleSequence;
            intArray.remove(intArray.size() - 1);
        }
        return this.mLifecycleSequence;
    }

    public int getClosestPreExecutionState(ActivityThread.ActivityClientRecord activityClientRecord, int i) {
        if (i == -1) {
            return -1;
        }
        if (i == 3) {
            return getClosestOfStates(activityClientRecord, ON_RESUME_PRE_EXCUTION_STATES);
        }
        throw new UnsupportedOperationException("Pre-execution states for state: " + i + " is not supported.");
    }

    public int getClosestOfStates(ActivityThread.ActivityClientRecord activityClientRecord, int[] iArr) {
        int i = -1;
        if (iArr != null && iArr.length != 0) {
            int lifecycleState = activityClientRecord.getLifecycleState();
            int i2 = Integer.MAX_VALUE;
            for (int i3 = 0; i3 < iArr.length; i3++) {
                getLifecyclePath(lifecycleState, iArr[i3], false);
                int size = this.mLifecycleSequence.size();
                if (pathInvolvesDestruction(this.mLifecycleSequence)) {
                    size += 10;
                }
                if (i2 > size) {
                    i = iArr[i3];
                    i2 = size;
                }
            }
        }
        return i;
    }

    public static ActivityLifecycleItem getLifecycleRequestForCurrentState(ActivityThread.ActivityClientRecord activityClientRecord) {
        int lifecycleState = activityClientRecord.getLifecycleState();
        if (lifecycleState == 4) {
            return PauseActivityItem.obtain();
        }
        if (lifecycleState == 5) {
            return StopActivityItem.obtain(0);
        }
        return ResumeActivityItem.obtain(false);
    }

    private static boolean pathInvolvesDestruction(IntArray intArray) {
        int size = intArray.size();
        for (int i = 0; i < size; i++) {
            if (intArray.get(i) == 6) {
                return true;
            }
        }
        return false;
    }

    static int lastCallbackRequestingState(ClientTransaction clientTransaction) {
        List<ClientTransactionItem> callbacks = clientTransaction.getCallbacks();
        if (callbacks == null || callbacks.size() == 0) {
            return -1;
        }
        int i = -1;
        int i2 = -1;
        for (int size = callbacks.size() - 1; size >= 0; size--) {
            int postExecutionState = callbacks.get(size).getPostExecutionState();
            if (postExecutionState != -1) {
                if (i != -1 && i != postExecutionState) {
                    break;
                }
                i2 = size;
                i = postExecutionState;
            }
        }
        return i2;
    }

    static String transactionToString(ClientTransaction clientTransaction, ClientTransactionHandler clientTransactionHandler) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        String strTId = tId(clientTransaction);
        clientTransaction.dump(strTId, printWriter);
        printWriter.append((CharSequence) (strTId + "Target activity: ")).println(getActivityName(clientTransaction.getActivityToken(), clientTransactionHandler));
        return stringWriter.toString();
    }

    static String tId(ClientTransaction clientTransaction) {
        return "tId:" + clientTransaction.hashCode() + " ";
    }

    static String getActivityName(IBinder iBinder, ClientTransactionHandler clientTransactionHandler) {
        Activity activityForToken = getActivityForToken(iBinder, clientTransactionHandler);
        if (activityForToken != null) {
            return activityForToken.getComponentName().getClassName();
        }
        return "Not found for token: " + iBinder;
    }

    static String getShortActivityName(IBinder iBinder, ClientTransactionHandler clientTransactionHandler) {
        Activity activityForToken = getActivityForToken(iBinder, clientTransactionHandler);
        if (activityForToken != null) {
            return activityForToken.getComponentName().getShortClassName();
        }
        return "Not found for token: " + iBinder;
    }

    private static Activity getActivityForToken(IBinder iBinder, ClientTransactionHandler clientTransactionHandler) {
        if (iBinder == null) {
            return null;
        }
        return clientTransactionHandler.getActivity(iBinder);
    }

    static String getStateName(int i) {
        switch (i) {
            case -1:
                return "UNDEFINED";
            case 0:
                return "PRE_ON_CREATE";
            case 1:
                return "ON_CREATE";
            case 2:
                return "ON_START";
            case 3:
                return "ON_RESUME";
            case 4:
                return "ON_PAUSE";
            case 5:
                return "ON_STOP";
            case 6:
                return "ON_DESTROY";
            case 7:
                return "ON_RESTART";
            default:
                throw new IllegalArgumentException("Unexpected lifecycle state: " + i);
        }
    }
}
