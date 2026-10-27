package com.android.car;

import android.car.input.ICarInputCallback;
import android.car.input.RotaryEvent;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import androidx.mediarouter.media.MediaRouter;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class InputCaptureClientController {
    private static final boolean DBG_CALLS = false;
    private static final boolean DBG_DISPATCH = false;
    private static final boolean DBG_STACK = false;
    private static final String TAG = "CAR.INPUT";
    private final Context mContext;
    private int mNumKeyEventsDispatched;
    private int mNumRotaryEventsDispatched;
    private static final Map<Integer, Integer> KEY_EVENT_TO_INPUT_TYPE = Map.ofEntries(Map.entry(23, 100), Map.entry(20, 100), Map.entry(19, 100), Map.entry(21, 100), Map.entry(22, 100), Map.entry(269, 100), Map.entry(271, 100), Map.entry(268, 100), Map.entry(270, 100), Map.entry(Integer.valueOf(MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED), 101), Map.entry(263, 101), Map.entry(Integer.valueOf(MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED), 101), Map.entry(Integer.valueOf(MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED), 101), Map.entry(281, 102), Map.entry(280, 102), Map.entry(282, 102), Map.entry(Integer.valueOf(CarStatsLog.CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED), 102));
    private static final Set<Integer> VALID_INPUT_TYPES = Set.of(1, 10, 100, 101, 102);
    private static final Set<Integer> VALID_ROTARY_TYPES = Set.of(10);
    private static final List<Integer> SUPPORTED_DISPLAY_TYPES = List.of(0);
    private static final int[] EMPTY_INPUT_TYPES = new int[0];
    private final Object mLock = new Object();
    private final SparseArray<LinkedList<ClientInfoForDisplay>> mFullDisplayEventCapturers = new SparseArray<>(2);
    private final SparseArray<SparseArray<LinkedList<ClientInfoForDisplay>>> mPerInputTypeCapturers = new SparseArray<>(2);
    private final SparseArray<HashMap<IBinder, ClientInfoForDisplay>> mAllClients = new SparseArray<>(1);
    private final LinkedList<ClientsToDispatch> mClientDispatchQueue = new LinkedList<>();
    private final ArrayList<KeyEvent> mKeyEventDispatchScratchList = new ArrayList<>(1);
    private final ArrayList<RotaryEvent> mRotaryEventDispatchScratchList = new ArrayList<>(1);

    private final class ClientInfoForDisplay implements IBinder.DeathRecipient {
        private final ICarInputCallback mCallback;
        private final int mFlags;
        private final ArrayList<Integer> mGrantedTypes;
        private final int[] mInputTypes;
        private final int mPid;
        private final int mTargetDisplayType;
        private final int mUid;

        private ClientInfoForDisplay(int uid, int pid, ICarInputCallback callback, int targetDisplayType, int[] inputTypes, int flags) {
            this.mUid = uid;
            this.mPid = pid;
            this.mCallback = callback;
            this.mTargetDisplayType = targetDisplayType;
            this.mInputTypes = inputTypes;
            this.mFlags = flags;
            this.mGrantedTypes = new ArrayList<>(inputTypes.length);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void linkToDeath() throws RemoteException {
            this.mCallback.asBinder().linkToDeath(this, 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void unlinkToDeath() {
            this.mCallback.asBinder().unlinkToDeath(this, 0);
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            InputCaptureClientController.this.onClientDeath(this);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Client{");
            sb.append("uid:");
            sb.append(this.mUid);
            sb.append(",pid:");
            sb.append(this.mPid);
            sb.append(",callback:");
            sb.append(this.mCallback);
            sb.append(",inputTypes:");
            sb.append(this.mInputTypes);
            sb.append(",flags:");
            sb.append(Integer.toHexString(this.mFlags));
            sb.append(",grantedTypes:");
            sb.append(this.mGrantedTypes);
            sb.append("}");
            return sb.toString();
        }
    }

    private static final class ClientsToDispatch {
        private final ArrayMap<ICarInputCallback, int[]> mClientsToDispatch;
        private final int mDisplayType;

        private ClientsToDispatch(int displayType) {
            this.mClientsToDispatch = new ArrayMap<>();
            this.mDisplayType = displayType;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void add(ClientInfoForDisplay client) {
            int[] inputTypesToDispatch;
            if (client.mGrantedTypes.isEmpty()) {
                inputTypesToDispatch = InputCaptureClientController.EMPTY_INPUT_TYPES;
            } else {
                inputTypesToDispatch = ArrayUtils.convertToIntArray(client.mGrantedTypes);
            }
            this.mClientsToDispatch.put(client.mCallback, inputTypesToDispatch);
        }
    }

    public InputCaptureClientController(Context context) {
        this.mContext = context;
    }

    public int requestInputEventCapture(ICarInputCallback callback, int targetDisplayType, int[] inputTypes, int requestFlags) throws RemoteException {
        Object obj;
        HashMap<IBinder, ClientInfoForDisplay> allClientsForDisplay;
        LinkedList<ClientInfoForDisplay> fullCapturersStack;
        ClientInfoForDisplay topStackClient;
        LinkedList<ClientInfoForDisplay> fullCapturersStack2;
        int i;
        LinkedList<ClientInfoForDisplay> fullCapturersStack3;
        ICarImpl.assertPermission(this.mContext, "android.permission.MONITOR_INPUT");
        Preconditions.checkArgument(SUPPORTED_DISPLAY_TYPES.contains(Integer.valueOf(targetDisplayType)), "Display not supported yet:" + targetDisplayType);
        boolean isRequestingAllEvents = (requestFlags & 2) != 0;
        if (isRequestingAllEvents) {
            ICarImpl.assertCallingFromSystemProcessOrSelf();
            if (inputTypes.length != 1 || inputTypes[0] != 1) {
                throw new IllegalArgumentException("Input type should be INPUT_TYPE_ALL_INPUTS for CAPTURE_REQ_FLAGS_TAKE_ALL_EVENTS_FOR_DISPLAY");
            }
        }
        if (targetDisplayType != 1 && targetDisplayType != 0) {
            throw new IllegalArgumentException("Unrecognized display type:" + targetDisplayType);
        }
        if (inputTypes == null) {
            throw new IllegalArgumentException("inputTypes cannot be null");
        }
        assertInputTypeValid(inputTypes);
        Arrays.sort(inputTypes);
        IBinder clientBinder = callback.asBinder();
        boolean allowsDelayedGrant = (requestFlags & 1) != 0;
        int ret = 0;
        ClientsToDispatch clientsToDispatch = new ClientsToDispatch(targetDisplayType);
        Object obj2 = this.mLock;
        synchronized (obj2) {
            try {
                HashMap<IBinder, ClientInfoForDisplay> allClientsForDisplay2 = this.mAllClients.get(targetDisplayType);
                if (allClientsForDisplay2 == null) {
                    try {
                        HashMap<IBinder, ClientInfoForDisplay> allClientsForDisplay3 = new HashMap<>();
                        this.mAllClients.put(targetDisplayType, allClientsForDisplay3);
                        allClientsForDisplay = allClientsForDisplay3;
                    } catch (Throwable th) {
                        e = th;
                        obj = obj2;
                    }
                } else {
                    allClientsForDisplay = allClientsForDisplay2;
                }
                ClientInfoForDisplay oldClientInfo = allClientsForDisplay.remove(clientBinder);
                LinkedList<ClientInfoForDisplay> fullCapturersStack4 = this.mFullDisplayEventCapturers.get(targetDisplayType);
                if (fullCapturersStack4 == null) {
                    LinkedList<ClientInfoForDisplay> fullCapturersStack5 = new LinkedList<>();
                    this.mFullDisplayEventCapturers.put(targetDisplayType, fullCapturersStack5);
                    fullCapturersStack = fullCapturersStack5;
                } else {
                    fullCapturersStack = fullCapturersStack4;
                }
                if (isRequestingAllEvents || fullCapturersStack.size() <= 0 || fullCapturersStack.getFirst() == oldClientInfo || allowsDelayedGrant) {
                    LinkedList<ClientInfoForDisplay> fullCapturersStack6 = fullCapturersStack;
                    HashMap<IBinder, ClientInfoForDisplay> allClientsForDisplay4 = allClientsForDisplay;
                    obj = obj2;
                    try {
                        ClientInfoForDisplay newClient = new ClientInfoForDisplay(Binder.getCallingUid(), Binder.getCallingPid(), callback, targetDisplayType, inputTypes, requestFlags);
                        try {
                            try {
                                newClient.linkToDeath();
                                SparseArray<LinkedList<ClientInfoForDisplay>> perInputStacks = this.mPerInputTypeCapturers.get(targetDisplayType);
                                if (perInputStacks == null) {
                                    try {
                                        perInputStacks = new SparseArray<>();
                                        this.mPerInputTypeCapturers.put(targetDisplayType, perInputStacks);
                                    } catch (Throwable th2) {
                                        e = th2;
                                    }
                                }
                                if (isRequestingAllEvents) {
                                    if (fullCapturersStack6.isEmpty()) {
                                        fullCapturersStack3 = fullCapturersStack6;
                                        for (int i2 = 0; i2 < perInputStacks.size(); i2++) {
                                            LinkedList<ClientInfoForDisplay> perTypeStack = perInputStacks.valueAt(i2);
                                            if (!perTypeStack.isEmpty()) {
                                                ClientInfoForDisplay topClient = perTypeStack.getFirst();
                                                if (topClient != oldClientInfo) {
                                                    topClient.mGrantedTypes.clear();
                                                    clientsToDispatch.add(topClient);
                                                }
                                                perTypeStack.remove(oldClientInfo);
                                            }
                                        }
                                    } else {
                                        ClientInfoForDisplay oldCapturer = fullCapturersStack6.getFirst();
                                        if (oldCapturer != oldClientInfo) {
                                            oldCapturer.mGrantedTypes.clear();
                                            clientsToDispatch.add(oldCapturer);
                                        }
                                        fullCapturersStack3 = fullCapturersStack6;
                                        fullCapturersStack3.remove(oldClientInfo);
                                    }
                                    fullCapturersStack3.addFirst(newClient);
                                } else {
                                    LinkedList<ClientInfoForDisplay> fullCapturersStack7 = fullCapturersStack6;
                                    boolean hadFullCapture = false;
                                    boolean fullCaptureActive = false;
                                    if (fullCapturersStack7.size() > 0) {
                                        if (fullCapturersStack7.getFirst() == oldClientInfo) {
                                            fullCapturersStack7.remove(oldClientInfo);
                                            if (fullCapturersStack7.size() > 0) {
                                                fullCaptureActive = true;
                                                ret = 2;
                                                ClientInfoForDisplay topClient2 = fullCapturersStack7.getFirst();
                                                topClient2.mGrantedTypes.clear();
                                                topClient2.mGrantedTypes.add(1);
                                                clientsToDispatch.add(topClient2);
                                            } else {
                                                hadFullCapture = true;
                                            }
                                        } else {
                                            fullCaptureActive = true;
                                            ret = 2;
                                        }
                                    }
                                    for (int i3 = 0; i3 < perInputStacks.size(); i3++) {
                                        perInputStacks.valueAt(i3).remove(oldClientInfo);
                                    }
                                    int i4 = inputTypes.length;
                                    int i5 = 0;
                                    while (i5 < i4) {
                                        int inputType = inputTypes[i5];
                                        LinkedList<ClientInfoForDisplay> perInputStack = perInputStacks.get(inputType);
                                        if (perInputStack == null) {
                                            perInputStack = new LinkedList<>();
                                            perInputStacks.put(inputType, perInputStack);
                                        }
                                        if (perInputStack.size() > 0) {
                                            ClientInfoForDisplay oldTopClient = perInputStack.getFirst();
                                            fullCapturersStack2 = fullCapturersStack7;
                                            i = i4;
                                            if (oldTopClient.mGrantedTypes.remove(Integer.valueOf(inputType))) {
                                                clientsToDispatch.add(oldTopClient);
                                            }
                                        } else {
                                            fullCapturersStack2 = fullCapturersStack7;
                                            i = i4;
                                        }
                                        if (!fullCaptureActive) {
                                            newClient.mGrantedTypes.add(Integer.valueOf(inputType));
                                        }
                                        perInputStack.addFirst(newClient);
                                        i5++;
                                        i4 = i;
                                        fullCapturersStack7 = fullCapturersStack2;
                                    }
                                    if (!fullCaptureActive && hadFullCapture) {
                                        for (int i6 = 0; i6 < perInputStacks.size(); i6++) {
                                            int inputType2 = perInputStacks.keyAt(i6);
                                            LinkedList<ClientInfoForDisplay> perInputStack2 = perInputStacks.valueAt(i6);
                                            if (perInputStack2.size() > 0 && (topStackClient = perInputStack2.getFirst()) != newClient && !topStackClient.mGrantedTypes.contains(Integer.valueOf(inputType2))) {
                                                topStackClient.mGrantedTypes.add(Integer.valueOf(inputType2));
                                                clientsToDispatch.add(topStackClient);
                                            }
                                        }
                                    }
                                }
                                allClientsForDisplay4.put(clientBinder, newClient);
                                dispatchClientCallbackLocked(clientsToDispatch);
                                return ret;
                            } catch (Throwable th3) {
                                e = th3;
                            }
                        } catch (RemoteException e) {
                            Log.i("CAR.INPUT", "requestInputEventCapture, cannot linkToDeath to client, pid:" + Binder.getCallingUid());
                            return 1;
                        }
                    } catch (Throwable th4) {
                        e = th4;
                    }
                }
            } catch (Throwable th5) {
                e = th5;
                obj = obj2;
            }
            throw e;
        }
        return 1;
    }

    public void releaseInputEventCapture(ICarInputCallback callback, int targetDisplayType) {
        Objects.requireNonNull(callback);
        Preconditions.checkArgument(SUPPORTED_DISPLAY_TYPES.contains(Integer.valueOf(targetDisplayType)), "Display not supported yet:" + targetDisplayType);
        ClientsToDispatch clientsToDispatch = new ClientsToDispatch(targetDisplayType);
        synchronized (this.mLock) {
            HashMap<IBinder, ClientInfoForDisplay> allClientsForDisplay = this.mAllClients.get(targetDisplayType);
            ClientInfoForDisplay clientInfo = allClientsForDisplay.remove(callback.asBinder());
            if (clientInfo != null) {
                clientInfo.unlinkToDeath();
                LinkedList<ClientInfoForDisplay> fullCapturersStack = this.mFullDisplayEventCapturers.get(targetDisplayType);
                boolean fullCaptureActive = false;
                if (fullCapturersStack.size() > 0) {
                    if (fullCapturersStack.getFirst() == clientInfo) {
                        fullCapturersStack.remove(clientInfo);
                        if (fullCapturersStack.size() > 0) {
                            ClientInfoForDisplay newStopStackClient = fullCapturersStack.getFirst();
                            newStopStackClient.mGrantedTypes.clear();
                            newStopStackClient.mGrantedTypes.add(1);
                            clientsToDispatch.add(newStopStackClient);
                            fullCaptureActive = true;
                        }
                    } else {
                        fullCaptureActive = true;
                    }
                    fullCapturersStack.remove(clientInfo);
                }
                SparseArray<LinkedList<ClientInfoForDisplay>> perInputStacks = this.mPerInputTypeCapturers.get(targetDisplayType);
                if (perInputStacks != null) {
                    for (int i = 0; i < perInputStacks.size(); i++) {
                        int inputType = perInputStacks.keyAt(i);
                        LinkedList<ClientInfoForDisplay> perInputStack = perInputStacks.valueAt(i);
                        if (perInputStack.size() > 0) {
                            if (perInputStack.getFirst() == clientInfo) {
                                perInputStack.removeFirst();
                                if (perInputStack.size() > 0) {
                                    ClientInfoForDisplay newTopClient = perInputStack.getFirst();
                                    if (!fullCaptureActive) {
                                        newTopClient.mGrantedTypes.add(Integer.valueOf(inputType));
                                        clientsToDispatch.add(newTopClient);
                                    }
                                }
                            } else {
                                if (!fullCaptureActive) {
                                    ClientInfoForDisplay topClient = perInputStack.getFirst();
                                    if (!topClient.mGrantedTypes.contains(Integer.valueOf(inputType))) {
                                        topClient.mGrantedTypes.add(Integer.valueOf(inputType));
                                        clientsToDispatch.add(topClient);
                                    }
                                }
                                perInputStack.remove(clientInfo);
                            }
                        }
                    }
                }
                dispatchClientCallbackLocked(clientsToDispatch);
                return;
            }
            Log.w("CAR.INPUT", "Cannot find client for releaseInputEventCapture:" + callback);
        }
    }

    public boolean onKeyEvent(int displayType, KeyEvent event) {
        Integer inputType;
        if (!SUPPORTED_DISPLAY_TYPES.contains(Integer.valueOf(displayType)) || (inputType = KEY_EVENT_TO_INPUT_TYPE.get(Integer.valueOf(event.getKeyCode()))) == null) {
            return false;
        }
        synchronized (this.mLock) {
            ICarInputCallback callback = getClientForInputTypeLocked(displayType, inputType.intValue());
            if (callback == null) {
                return false;
            }
            this.mNumKeyEventsDispatched++;
            dispatchKeyEvent(displayType, event, callback);
            return true;
        }
    }

    public boolean onRotaryEvent(int displayType, RotaryEvent event) {
        if (!SUPPORTED_DISPLAY_TYPES.contains(Integer.valueOf(displayType))) {
            Log.w("CAR.INPUT", "onRotaryEvent for not supported display:" + displayType);
            return false;
        }
        int inputType = event.getInputType();
        if (!VALID_ROTARY_TYPES.contains(Integer.valueOf(inputType))) {
            Log.w("CAR.INPUT", "onRotaryEvent for not supported input type:" + inputType);
            return false;
        }
        synchronized (this.mLock) {
            ICarInputCallback callback = getClientForInputTypeLocked(displayType, inputType);
            if (callback == null) {
                return false;
            }
            this.mNumRotaryEventsDispatched++;
            dispatchRotaryEvent(displayType, event, callback);
            return true;
        }
    }

    ICarInputCallback getClientForInputTypeLocked(int targetDisplayType, int inputType) {
        LinkedList<ClientInfoForDisplay> perInputStack;
        LinkedList<ClientInfoForDisplay> fullCapturersStack = this.mFullDisplayEventCapturers.get(targetDisplayType);
        if (fullCapturersStack != null && fullCapturersStack.size() > 0) {
            return fullCapturersStack.getFirst().mCallback;
        }
        SparseArray<LinkedList<ClientInfoForDisplay>> perInputStacks = this.mPerInputTypeCapturers.get(targetDisplayType);
        if (perInputStacks != null && (perInputStack = perInputStacks.get(inputType)) != null && perInputStack.size() > 0) {
            return perInputStack.getFirst().mCallback;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClientDeath(ClientInfoForDisplay client) {
        releaseInputEventCapture(client.mCallback, client.mTargetDisplayType);
    }

    public void dump(PrintWriter writer) {
        writer.println("**InputCaptureClientController**");
        synchronized (this.mLock) {
            Iterator<Integer> it = SUPPORTED_DISPLAY_TYPES.iterator();
            while (it.hasNext()) {
                int display = it.next().intValue();
                writer.println("***Display:" + display);
                HashMap<IBinder, ClientInfoForDisplay> allClientsForDisplay = this.mAllClients.get(display);
                if (allClientsForDisplay != null) {
                    writer.println("****All clients:");
                    for (ClientInfoForDisplay client : allClientsForDisplay.values()) {
                        writer.println(client);
                    }
                }
                LinkedList<ClientInfoForDisplay> fullCapturersStack = this.mFullDisplayEventCapturers.get(display);
                if (fullCapturersStack != null) {
                    writer.println("****Full capture stack");
                    for (ClientInfoForDisplay client2 : fullCapturersStack) {
                        writer.println(client2);
                    }
                }
                SparseArray<LinkedList<ClientInfoForDisplay>> perInputStacks = this.mPerInputTypeCapturers.get(display);
                if (perInputStacks != null) {
                    for (int i = 0; i < perInputStacks.size(); i++) {
                        int inputType = perInputStacks.keyAt(i);
                        LinkedList<ClientInfoForDisplay> perInputStack = perInputStacks.valueAt(i);
                        if (perInputStack.size() > 0) {
                            writer.println("**** Per Input stack, input type:" + inputType);
                            for (ClientInfoForDisplay client3 : perInputStack) {
                                writer.println(client3);
                            }
                        }
                    }
                }
            }
            writer.println("mNumKeyEventsDispatched:" + this.mNumKeyEventsDispatched + ",mNumRotaryEventsDispatched:" + this.mNumRotaryEventsDispatched);
        }
    }

    private void dispatchClientCallbackLocked(ClientsToDispatch clientsToDispatch) {
        if (clientsToDispatch.mClientsToDispatch.isEmpty()) {
            return;
        }
        this.mClientDispatchQueue.add(clientsToDispatch);
        CarServiceUtils.runOnMain(new Runnable() { // from class: com.android.car.-$$Lambda$InputCaptureClientController$EiGnof2ftzazIp61r7W8Ykjg0L0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dispatchClientCallbackLocked$0$InputCaptureClientController();
            }
        });
    }

    public /* synthetic */ void lambda$dispatchClientCallbackLocked$0$InputCaptureClientController() {
        synchronized (this.mLock) {
            if (this.mClientDispatchQueue.isEmpty()) {
                return;
            }
            ClientsToDispatch clients = this.mClientDispatchQueue.pop();
            for (int i = 0; i < clients.mClientsToDispatch.size(); i++) {
                ICarInputCallback callback = (ICarInputCallback) clients.mClientsToDispatch.keyAt(i);
                int[] inputTypes = (int[]) clients.mClientsToDispatch.valueAt(i);
                Arrays.sort(inputTypes);
                try {
                    callback.onCaptureStateChanged(clients.mDisplayType, inputTypes);
                } catch (RemoteException e) {
                }
            }
        }
    }

    private void dispatchKeyEvent(final int targetDisplayType, final KeyEvent event, final ICarInputCallback callback) {
        CarServiceUtils.runOnMain(new Runnable() { // from class: com.android.car.-$$Lambda$InputCaptureClientController$Py4JG5bjuGSxYBbR9OhGwOP-Id0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dispatchKeyEvent$1$InputCaptureClientController(event, callback, targetDisplayType);
            }
        });
    }

    public /* synthetic */ void lambda$dispatchKeyEvent$1$InputCaptureClientController(KeyEvent event, ICarInputCallback callback, int targetDisplayType) {
        this.mKeyEventDispatchScratchList.clear();
        this.mKeyEventDispatchScratchList.add(event);
        try {
            callback.onKeyEvents(targetDisplayType, this.mKeyEventDispatchScratchList);
        } catch (RemoteException e) {
        }
    }

    private void dispatchRotaryEvent(final int targetDisplayType, final RotaryEvent event, final ICarInputCallback callback) {
        CarServiceUtils.runOnMain(new Runnable() { // from class: com.android.car.-$$Lambda$InputCaptureClientController$N0FMsGlXMmhGvG126VnAPa3d1aA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dispatchRotaryEvent$2$InputCaptureClientController(event, callback, targetDisplayType);
            }
        });
    }

    public /* synthetic */ void lambda$dispatchRotaryEvent$2$InputCaptureClientController(RotaryEvent event, ICarInputCallback callback, int targetDisplayType) {
        this.mRotaryEventDispatchScratchList.clear();
        this.mRotaryEventDispatchScratchList.add(event);
        try {
            callback.onRotaryEvents(targetDisplayType, this.mRotaryEventDispatchScratchList);
        } catch (RemoteException e) {
        }
    }

    private static void assertInputTypeValid(int[] inputTypes) {
        for (int inputType : inputTypes) {
            if (!VALID_INPUT_TYPES.contains(Integer.valueOf(inputType))) {
                throw new IllegalArgumentException("Invalid input type:" + inputType + ", inputTypes:" + Arrays.toString(inputTypes));
            }
        }
    }
}
