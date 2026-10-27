package android.app.servertransaction;

import android.app.ActivityThread;
import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.util.IntArray;
import android.util.Slog;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class TransactionExecutor {
    private static final boolean DEBUG_RESOLVER = false;
    private static final String TAG = "TransactionExecutor";
    private ClientTransactionHandler mTransactionHandler;
    private PendingTransactionActions mPendingActions = new PendingTransactionActions();
    private TransactionExecutorHelper mHelper = new TransactionExecutorHelper();

    public TransactionExecutor(ClientTransactionHandler clientTransactionHandler) {
        this.mTransactionHandler = clientTransactionHandler;
    }

    public void execute(ClientTransaction clientTransaction) {
        Map<IBinder, ClientTransactionItem> activitiesToBeDestroyed;
        ClientTransactionItem clientTransactionItem;
        IBinder activityToken = clientTransaction.getActivityToken();
        if (activityToken != null && (clientTransactionItem = (activitiesToBeDestroyed = this.mTransactionHandler.getActivitiesToBeDestroyed()).get(activityToken)) != null) {
            if (clientTransaction.getLifecycleStateRequest() == clientTransactionItem) {
                activitiesToBeDestroyed.remove(activityToken);
            }
            if (this.mTransactionHandler.getActivityClient(activityToken) == null) {
                Slog.w(TAG, TransactionExecutorHelper.tId(clientTransaction) + "Skip pre-destroyed transaction:\n" + TransactionExecutorHelper.transactionToString(clientTransaction, this.mTransactionHandler));
                return;
            }
        }
        executeCallbacks(clientTransaction);
        executeLifecycleState(clientTransaction);
        this.mPendingActions.clear();
    }

    public void executeCallbacks(ClientTransaction clientTransaction) {
        List<ClientTransactionItem> callbacks = clientTransaction.getCallbacks();
        if (callbacks == null || callbacks.isEmpty()) {
            return;
        }
        IBinder activityToken = clientTransaction.getActivityToken();
        ActivityThread.ActivityClientRecord activityClient = this.mTransactionHandler.getActivityClient(activityToken);
        ActivityLifecycleItem lifecycleStateRequest = clientTransaction.getLifecycleStateRequest();
        int targetState = lifecycleStateRequest != null ? lifecycleStateRequest.getTargetState() : -1;
        int iLastCallbackRequestingState = TransactionExecutorHelper.lastCallbackRequestingState(clientTransaction);
        int size = callbacks.size();
        int i = 0;
        while (i < size) {
            ClientTransactionItem clientTransactionItem = callbacks.get(i);
            int postExecutionState = clientTransactionItem.getPostExecutionState();
            int closestPreExecutionState = this.mHelper.getClosestPreExecutionState(activityClient, clientTransactionItem.getPostExecutionState());
            if (closestPreExecutionState != -1) {
                cycleToPath(activityClient, closestPreExecutionState, clientTransaction);
            }
            clientTransactionItem.execute(this.mTransactionHandler, activityToken, this.mPendingActions);
            clientTransactionItem.postExecute(this.mTransactionHandler, activityToken, this.mPendingActions);
            if (activityClient == null) {
                activityClient = this.mTransactionHandler.getActivityClient(activityToken);
            }
            if (postExecutionState != -1 && activityClient != null) {
                cycleToPath(activityClient, postExecutionState, i == iLastCallbackRequestingState && targetState == postExecutionState, clientTransaction);
            }
            i++;
        }
    }

    private void executeLifecycleState(ClientTransaction clientTransaction) {
        IBinder activityToken;
        ActivityThread.ActivityClientRecord activityClient;
        ActivityLifecycleItem lifecycleStateRequest = clientTransaction.getLifecycleStateRequest();
        if (lifecycleStateRequest == null || (activityClient = this.mTransactionHandler.getActivityClient((activityToken = clientTransaction.getActivityToken()))) == null) {
            return;
        }
        cycleToPath(activityClient, lifecycleStateRequest.getTargetState(), true, clientTransaction);
        lifecycleStateRequest.execute(this.mTransactionHandler, activityToken, this.mPendingActions);
        lifecycleStateRequest.postExecute(this.mTransactionHandler, activityToken, this.mPendingActions);
    }

    public void cycleToPath(ActivityThread.ActivityClientRecord activityClientRecord, int i, ClientTransaction clientTransaction) {
        cycleToPath(activityClientRecord, i, false, clientTransaction);
    }

    private void cycleToPath(ActivityThread.ActivityClientRecord activityClientRecord, int i, boolean z, ClientTransaction clientTransaction) {
        performLifecycleSequence(activityClientRecord, this.mHelper.getLifecyclePath(activityClientRecord.getLifecycleState(), i, z), clientTransaction);
    }

    private void performLifecycleSequence(ActivityThread.ActivityClientRecord activityClientRecord, IntArray intArray, ClientTransaction clientTransaction) {
        int size = intArray.size();
        for (int i = 0; i < size; i++) {
            int i2 = intArray.get(i);
            switch (i2) {
                case 1:
                    this.mTransactionHandler.handleLaunchActivity(activityClientRecord, this.mPendingActions, null);
                    break;
                case 2:
                    this.mTransactionHandler.handleStartActivity(activityClientRecord.token, this.mPendingActions);
                    break;
                case 3:
                    this.mTransactionHandler.handleResumeActivity(activityClientRecord.token, false, activityClientRecord.isForward, "LIFECYCLER_RESUME_ACTIVITY");
                    break;
                case 4:
                    this.mTransactionHandler.handlePauseActivity(activityClientRecord.token, false, false, 0, this.mPendingActions, "LIFECYCLER_PAUSE_ACTIVITY");
                    break;
                case 5:
                    this.mTransactionHandler.handleStopActivity(activityClientRecord.token, 0, this.mPendingActions, false, "LIFECYCLER_STOP_ACTIVITY");
                    break;
                case 6:
                    this.mTransactionHandler.handleDestroyActivity(activityClientRecord.token, false, 0, false, "performLifecycleSequence. cycling to:" + intArray.get(size - 1));
                    break;
                case 7:
                    this.mTransactionHandler.performRestartActivity(activityClientRecord.token, false);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected lifecycle state: " + i2);
            }
        }
    }
}
