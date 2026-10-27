package com.android.car.hal;

import android.car.userlib.HalCallback;
import android.car.userlib.UserHalHelper;
import android.car.userlib.UserHelper;
import android.hardware.automotive.vehicle.V2_0.CreateUserRequest;
import android.hardware.automotive.vehicle.V2_0.CreateUserResponse;
import android.hardware.automotive.vehicle.V2_0.InitialUserInfoResponse;
import android.hardware.automotive.vehicle.V2_0.RemoveUserRequest;
import android.hardware.automotive.vehicle.V2_0.SwitchUserRequest;
import android.hardware.automotive.vehicle.V2_0.SwitchUserResponse;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationAssociation;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationAssociationType;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationGetRequest;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationResponse;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationSetAssociation;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationSetRequest;
import android.hardware.automotive.vehicle.V2_0.UsersInfo;
import android.hardware.automotive.vehicle.V2_0.VehiclePropConfig;
import android.hardware.automotive.vehicle.V2_0.VehiclePropValue;
import android.os.Handler;
import android.os.Looper;
import android.os.ServiceSpecificException;
import android.sysprop.CarProperties;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.android.car.CarLocalServices;
import com.android.car.CarStatsLog;
import com.android.car.user.CarUserService;
import com.android.internal.util.FunctionalUtils;
import com.android.internal.util.Preconditions;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
public final class UserHalService extends HalServiceBase {
    private static final boolean DBG = false;
    private static final String UNSUPPORTED_MSG = "Vehicle HAL does not support user management";
    private static final String USER_ASSOCIATION_UNSUPPORTED_MSG = "Vehicle HAL does not support user association";
    private final int mBaseRequestId;
    private final VehicleHal mHal;
    private final Handler mHandler;
    private final Object mLock;
    private int mNextRequestId;
    private final SparseArray<PendingRequest<?, ?>> mPendingRequests;
    private SparseArray<VehiclePropConfig> mProperties;
    private static final String TAG = UserHalService.class.getSimpleName();
    private static final int[] SUPPORTED_PROPERTIES = {299896585, 299896583, 299896586, 299896584, 299896587};
    private static final int[] CORE_PROPERTIES = {299896585, 299896583, 299896586, 299896584};

    public UserHalService(VehicleHal hal) {
        this(hal, new Handler(Looper.getMainLooper()));
    }

    UserHalService(VehicleHal hal, Handler handler) {
        this.mLock = new Object();
        this.mNextRequestId = 1;
        this.mPendingRequests = new SparseArray<>();
        this.mHal = hal;
        this.mHandler = handler;
        this.mBaseRequestId = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
    }

    @Override // com.android.car.hal.HalServiceBase
    public void init() {
        SparseArray<VehiclePropConfig> sparseArray = this.mProperties;
        if (sparseArray == null) {
            return;
        }
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            VehiclePropConfig config = this.mProperties.valueAt(i);
            if (VehicleHal.isPropertySubscribable(config)) {
                this.mHal.subscribeProperty(this, config.prop);
            }
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void release() {
    }

    @Override // com.android.car.hal.HalServiceBase
    public void onHalEvents(List<VehiclePropValue> values) {
        for (int i = 0; i < values.size(); i++) {
            VehiclePropValue value = values.get(i);
            switch (value.prop) {
                case 299896583:
                    this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: com.android.car.hal.-$$Lambda$UserHalService$AyTj9VUX2m1Qn9v_nTI5SCeOyd4
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            ((UserHalService) obj).handleOnInitialUserInfoResponse((VehiclePropValue) obj2);
                        }
                    }, this, value));
                    break;
                case 299896584:
                    this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: com.android.car.hal.-$$Lambda$UserHalService$oLM7Ctl9igphGKGdB5iFKuZfvbo
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            ((UserHalService) obj).handleOnSwitchUserResponse((VehiclePropValue) obj2);
                        }
                    }, this, value));
                    break;
                case 299896585:
                    this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: com.android.car.hal.-$$Lambda$UserHalService$wv9uz9JyHgG4aVSwLANatKurxQ4
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            ((UserHalService) obj).handleOnCreateUserResponse((VehiclePropValue) obj2);
                        }
                    }, this, value));
                    break;
                case 299896586:
                    Log.w(TAG, "Received REMOVE_USER HAL event: " + value);
                    break;
                case 299896587:
                    this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: com.android.car.hal.-$$Lambda$UserHalService$acpbPov4pj9R3zrlq38-GEJAk08
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            ((UserHalService) obj).handleOnUserIdentificationAssociation((VehiclePropValue) obj2);
                        }
                    }, this, value));
                    break;
                default:
                    Log.w(TAG, "received unsupported event from HAL: " + value);
                    break;
            }
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void onPropertySetError(int property, int area, int errorCode) {
    }

    @Override // com.android.car.hal.HalServiceBase
    public int[] getAllSupportedProperties() {
        return SUPPORTED_PROPERTIES;
    }

    @Override // com.android.car.hal.HalServiceBase
    public void takeProperties(Collection<VehiclePropConfig> properties) {
        if (properties.isEmpty()) {
            Log.w(TAG, UNSUPPORTED_MSG);
            return;
        }
        SparseArray<VehiclePropConfig> supportedProperties = new SparseArray<>(5);
        for (VehiclePropConfig config : properties) {
            supportedProperties.put(config.prop, config);
        }
        synchronized (this.mLock) {
            this.mProperties = supportedProperties;
        }
    }

    public boolean isSupported() {
        synchronized (this.mLock) {
            if (this.mProperties == null) {
                return false;
            }
            for (int i = 0; i < CORE_PROPERTIES.length; i++) {
                if (this.mProperties.get(CORE_PROPERTIES[i]) == null) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean isUserAssociationSupported() {
        synchronized (this.mLock) {
            if (this.mProperties == null) {
                return false;
            }
            return this.mProperties.get(299896587) != null;
        }
    }

    private void checkSupported() {
        Preconditions.checkState(isSupported(), UNSUPPORTED_MSG);
    }

    private void checkUserAssociationSupported() {
        Preconditions.checkState(isUserAssociationSupported(), USER_ASSOCIATION_UNSUPPORTED_MSG);
    }

    private int getRequestIdForStatsLog(int originalRequestId) {
        int i = this.mBaseRequestId;
        if (Integer.MAX_VALUE - i < originalRequestId) {
            return (i - Integer.MAX_VALUE) + originalRequestId;
        }
        return i + originalRequestId;
    }

    public void getInitialUserInfo(int requestType, int timeoutMs, UsersInfo usersInfo, HalCallback<InitialUserInfoResponse> callback) {
        Preconditions.checkArgumentPositive(timeoutMs, "timeout must be positive");
        Objects.requireNonNull(usersInfo);
        UserHalHelper.checkValid(usersInfo);
        Objects.requireNonNull(callback);
        checkSupported();
        int requestId = getNextRequestId();
        VehiclePropValue propRequest = UserHalHelper.createPropRequest(299896583, requestId, requestType);
        UserHalHelper.addUsersInfo(propRequest, usersInfo);
        synchronized (this.mLock) {
            if (hasPendingRequestLocked(InitialUserInfoResponse.class, callback)) {
                return;
            }
            addPendingRequestLocked(requestId, InitialUserInfoResponse.class, callback);
            EventLog.writeEvent(150140, Integer.valueOf(requestId), Integer.valueOf(requestType), Integer.valueOf(timeoutMs));
            CarStatsLog.write(CarStatsLog.CAR_USER_HAL_INITIAL_USER_INFO_REQUEST_REPORTED, getRequestIdForStatsLog(requestId), getInitialUserInfoRequestTypeForStatsd(requestType), timeoutMs);
            sendHalRequest(requestId, timeoutMs, propRequest, callback);
        }
    }

    private static int getInitialUserInfoRequestTypeForStatsd(int requestType) {
        if (requestType == 1) {
            return 1;
        }
        if (requestType == 2) {
            return 2;
        }
        if (requestType != 3) {
            return requestType != 4 ? 0 : 4;
        }
        return 3;
    }

    private void sendHalRequest(int requestId, int timeoutMs, VehiclePropValue request, HalCallback<?> callback) {
        this.mHandler.sendMessageDelayed(PooledLambda.obtainMessage(new BiConsumer() { // from class: com.android.car.hal.-$$Lambda$UserHalService$YcJqbkHnp9Ln5WfE2vQoFmUD0Uk
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((UserHalService) obj).handleCheckIfRequestTimedOut(((Integer) obj2).intValue());
            }
        }, this, Integer.valueOf(requestId)).setWhat(requestId), timeoutMs);
        try {
            this.mHal.set(request);
        } catch (ServiceSpecificException e) {
            handleRemovePendingRequest(requestId);
            Log.w(TAG, "Failed to set " + request, e);
            callback.onResponse(2, null);
        }
    }

    public void switchUser(SwitchUserRequest request, int timeoutMs, HalCallback<SwitchUserResponse> callback) {
        Preconditions.checkArgumentPositive(timeoutMs, "timeout must be positive");
        Objects.requireNonNull(callback, "callback cannot be null");
        Objects.requireNonNull(request, "request cannot be null");
        checkSupported();
        request.requestId = getNextRequestId();
        request.messageType = 2;
        VehiclePropValue propRequest = UserHalHelper.toVehiclePropValue(request);
        synchronized (this.mLock) {
            if (hasPendingRequestLocked(SwitchUserResponse.class, callback)) {
                return;
            }
            addPendingRequestLocked(request.requestId, SwitchUserResponse.class, callback);
            EventLog.writeEvent(150142, Integer.valueOf(request.requestId), Integer.valueOf(request.targetUser.userId), Integer.valueOf(timeoutMs));
            CarStatsLog.write(CarStatsLog.CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED, getRequestIdForStatsLog(request.requestId), 1, request.usersInfo.currentUser.userId, request.usersInfo.currentUser.flags, request.targetUser.userId, request.targetUser.flags, timeoutMs);
            sendHalRequest(request.requestId, timeoutMs, propRequest, callback);
        }
    }

    public void removeUser(RemoveUserRequest request) {
        Objects.requireNonNull(request, "request cannot be null");
        checkSupported();
        request.requestId = getNextRequestId();
        VehiclePropValue propRequest = UserHalHelper.toVehiclePropValue(request);
        EventLog.writeEvent(150153, Integer.valueOf(request.removedUserInfo.userId), Integer.valueOf(request.usersInfo.currentUser.userId));
        CarStatsLog.write(CarStatsLog.CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED, getRequestIdForStatsLog(request.requestId), 5, request.usersInfo.currentUser.userId, request.usersInfo.currentUser.flags, request.removedUserInfo.userId, request.removedUserInfo.flags, -1);
        try {
            this.mHal.set(propRequest);
        } catch (ServiceSpecificException e) {
            Log.w(TAG, "Failed to set REMOVE USER", e);
        }
    }

    public void createUser(CreateUserRequest request, int timeoutMs, HalCallback<CreateUserResponse> callback) {
        Objects.requireNonNull(request);
        Preconditions.checkArgumentPositive(timeoutMs, "timeout must be positive");
        Objects.requireNonNull(callback);
        checkSupported();
        request.requestId = getNextRequestId();
        VehiclePropValue propRequest = UserHalHelper.toVehiclePropValue(request);
        synchronized (this.mLock) {
            if (hasPendingRequestLocked(CreateUserResponse.class, callback)) {
                return;
            }
            addPendingRequestLocked(request.requestId, CreateUserResponse.class, callback);
            EventLog.writeEvent(150151, Integer.valueOf(request.requestId), UserHelper.safeName(request.newUserName), Integer.valueOf(request.newUserInfo.flags), Integer.valueOf(timeoutMs));
            CarStatsLog.write(CarStatsLog.CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED, getRequestIdForStatsLog(request.requestId), 4, request.usersInfo.currentUser.userId, request.usersInfo.currentUser.flags, request.newUserInfo.userId, request.newUserInfo.flags, timeoutMs);
            sendHalRequest(request.requestId, timeoutMs, propRequest, callback);
        }
    }

    public void postSwitchResponse(SwitchUserRequest request) {
        Objects.requireNonNull(request, "request cannot be null");
        checkSupported();
        request.messageType = 5;
        VehiclePropValue propRequest = UserHalHelper.toVehiclePropValue(request);
        EventLog.writeEvent(150144, Integer.valueOf(request.requestId), Integer.valueOf(request.targetUser.userId), Integer.valueOf(request.usersInfo.currentUser.userId));
        CarStatsLog.write(CarStatsLog.CAR_USER_HAL_POST_SWITCH_RESPONSE_REPORTED, getRequestIdForStatsLog(request.requestId), request.targetUser.userId != request.usersInfo.currentUser.userId ? 2 : 1);
        try {
            this.mHal.set(propRequest);
        } catch (ServiceSpecificException e) {
            Log.w(TAG, "Failed to set ANDROID POST SWITCH", e);
        }
    }

    public void legacyUserSwitch(SwitchUserRequest request) {
        Objects.requireNonNull(request, "request cannot be null");
        checkSupported();
        request.requestId = getNextRequestId();
        request.messageType = 1;
        VehiclePropValue propRequest = UserHalHelper.toVehiclePropValue(request);
        EventLog.writeEvent(150147, Integer.valueOf(request.requestId), Integer.valueOf(request.targetUser.userId), Integer.valueOf(request.usersInfo.currentUser.userId));
        CarStatsLog.write(CarStatsLog.CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED, getRequestIdForStatsLog(request.requestId), 3, request.usersInfo.currentUser.userId, request.usersInfo.currentUser.flags, request.targetUser.userId, request.targetUser.flags, -1);
        try {
            this.mHal.set(propRequest);
        } catch (ServiceSpecificException e) {
            Log.w(TAG, "Failed to set LEGACY ANDROID SWITCH", e);
        }
    }

    public UserIdentificationResponse getUserAssociation(UserIdentificationGetRequest request) {
        Objects.requireNonNull(request, "request cannot be null");
        checkUserAssociationSupported();
        SparseBooleanArray types = new SparseBooleanArray(request.numberAssociationTypes);
        for (int i = 0; i < request.numberAssociationTypes; i++) {
            int type = request.associationTypes.get(i).intValue();
            Preconditions.checkArgument(!types.get(type), "type %s found more than once on %s", new Object[]{UserIdentificationAssociationType.toString(type), request});
            types.put(type, true);
        }
        int i2 = getNextRequestId();
        request.requestId = i2;
        VehiclePropValue requestAsPropValue = UserHalHelper.toVehiclePropValue(request);
        EventLog.writeEvent(150145, requestAsPropValue.value.int32Values.toArray());
        VehiclePropValue responseAsPropValue = this.mHal.get(requestAsPropValue);
        if (responseAsPropValue == null) {
            Log.w(TAG, "HAL returned null for request " + requestAsPropValue);
            return null;
        }
        logEventWithErrorMessage(150146, responseAsPropValue);
        try {
            UserIdentificationResponse response = UserHalHelper.toUserIdentificationResponse(responseAsPropValue);
            if (response.requestId != request.requestId) {
                Log.w(TAG, "invalid request id (should be " + request.requestId + ") on HAL response: " + response);
                return null;
            }
            if (response.numberAssociation != request.numberAssociationTypes) {
                Log.w(TAG, "Wrong number of association types on HAL response (expected " + request.numberAssociationTypes + ") for request " + requestAsPropValue + ": " + response);
                return null;
            }
            for (int i3 = 0; i3 < request.numberAssociationTypes; i3++) {
                int expectedType = request.associationTypes.get(i3).intValue();
                int actualType = response.associations.get(i3).type;
                if (actualType != expectedType) {
                    Log.w(TAG, "Wrong type on index " + i3 + " of HAL response (" + response + ") for request " + requestAsPropValue + " : expected " + UserIdentificationAssociationType.toString(expectedType) + ", got " + UserIdentificationAssociationType.toString(actualType));
                    return null;
                }
            }
            int[] associationTypes = new int[response.numberAssociation];
            int[] associationValues = new int[response.numberAssociation];
            for (int i4 = 0; i4 < response.numberAssociation; i4++) {
                UserIdentificationAssociation association = response.associations.get(i4);
                associationTypes[i4] = association.type;
                associationValues[i4] = association.value;
            }
            CarStatsLog.write(CarStatsLog.CAR_USER_HAL_USER_ASSOCIATION_REQUEST_REPORTED, getRequestIdForStatsLog(request.requestId), 2, request.userInfo.userId, request.userInfo.flags, request.numberAssociationTypes, Arrays.toString(associationTypes), Arrays.toString(associationValues));
            return response;
        } catch (IllegalArgumentException e) {
            Log.w(TAG, "invalid response from HAL for " + requestAsPropValue, e);
            return null;
        }
    }

    public void setUserAssociation(int timeoutMs, UserIdentificationSetRequest request, HalCallback<UserIdentificationResponse> callback) {
        Preconditions.checkArgumentPositive(timeoutMs, "timeout must be positive");
        Objects.requireNonNull(request, "request cannot be null");
        Objects.requireNonNull(callback, "callback cannot be null");
        SparseBooleanArray types = new SparseBooleanArray(request.numberAssociations);
        for (int i = 0; i < request.numberAssociations; i++) {
            int type = request.associations.get(i).type;
            Preconditions.checkArgument(!types.get(type), "type %s found more than once on %s", new Object[]{UserIdentificationAssociationType.toString(type), request});
            types.put(type, true);
        }
        checkUserAssociationSupported();
        request.requestId = getNextRequestId();
        VehiclePropValue propRequest = UserHalHelper.toVehiclePropValue(request);
        synchronized (this.mLock) {
            if (hasPendingRequestLocked(UserIdentificationResponse.class, callback)) {
                return;
            }
            addPendingRequestLocked(request.requestId, UserIdentificationResponse.class, request, callback);
            EventLog.writeEvent(150148, propRequest.value.int32Values.toArray());
            int[] associationTypes = new int[request.numberAssociations];
            int[] associationValues = new int[request.numberAssociations];
            for (int i2 = 0; i2 < request.numberAssociations; i2++) {
                UserIdentificationSetAssociation association = request.associations.get(i2);
                associationTypes[i2] = association.type;
                associationValues[i2] = association.value;
            }
            CarStatsLog.write(CarStatsLog.CAR_USER_HAL_USER_ASSOCIATION_REQUEST_REPORTED, getRequestIdForStatsLog(request.requestId), 1, request.userInfo.userId, request.userInfo.flags, request.numberAssociations, Arrays.toString(associationTypes), Arrays.toString(associationValues));
            sendHalRequest(request.requestId, timeoutMs, propRequest, callback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnUserIdentificationAssociation(VehiclePropValue value) {
        logEventWithErrorMessage(150149, value);
        int requestId = value.value.int32Values.get(0).intValue();
        HalCallback<UserIdentificationResponse> callback = handleGetPendingCallback(requestId, UserIdentificationResponse.class);
        if (callback == null) {
            Log.w(TAG, "no callback for requestId " + requestId + ": " + value);
            return;
        }
        PendingRequest<?, ?> pendingRequest = handleRemovePendingRequest(requestId);
        try {
            UserIdentificationResponse response = UserHalHelper.toUserIdentificationResponse(value);
            UserIdentificationSetRequest request = (UserIdentificationSetRequest) PendingRequest.getRequest(pendingRequest, UserIdentificationSetRequest.class, requestId);
            if (request == null) {
                callback.onResponse(4, null);
                logSetUserAssociationResponse(requestId, response, 4);
                return;
            }
            if (response.numberAssociation != request.numberAssociations) {
                Log.w(TAG, "Wrong number of association types on HAL response (expected " + request.numberAssociations + ") for request " + request + ": " + response);
                callback.onResponse(4, null);
                logSetUserAssociationResponse(requestId, response, 4);
                return;
            }
            for (int i = 0; i < request.numberAssociations; i++) {
                int expectedType = request.associations.get(i).type;
                int actualType = response.associations.get(i).type;
                if (actualType != expectedType) {
                    Log.w(TAG, "Wrong type on index " + i + " of HAL response (" + response + ") for request " + value + " : expected " + UserIdentificationAssociationType.toString(expectedType) + ", got " + UserIdentificationAssociationType.toString(actualType));
                    callback.onResponse(4, null);
                    logSetUserAssociationResponse(requestId, response, 4);
                    return;
                }
            }
            callback.onResponse(1, response);
            logSetUserAssociationResponse(requestId, response, 1);
        } catch (RuntimeException e) {
            Log.w(TAG, "error parsing UserIdentificationResponse (" + value + ")", e);
            callback.onResponse(4, null);
            CarStatsLog.write(CarStatsLog.CAR_USER_HAL_SET_USER_ASSOCIATION_RESPONSE_REPORTED, getRequestIdForStatsLog(requestId), getHalCallbackStatusForStatsd(4), 0, "", "");
        }
    }

    private void logSetUserAssociationResponse(int requestId, UserIdentificationResponse response, int halCallbackStatus) {
        int[] associationTypes = new int[response.numberAssociation];
        int[] associationValues = new int[response.numberAssociation];
        for (int i = 0; i < response.numberAssociation; i++) {
            UserIdentificationAssociation association = response.associations.get(i);
            associationTypes[i] = association.type;
            associationValues[i] = association.value;
        }
        CarStatsLog.write(CarStatsLog.CAR_USER_HAL_SET_USER_ASSOCIATION_RESPONSE_REPORTED, getRequestIdForStatsLog(requestId), getHalCallbackStatusForStatsd(halCallbackStatus), response.numberAssociation, Arrays.toString(associationTypes), Arrays.toString(associationValues));
    }

    private static void logEventWithErrorMessage(int eventTag, VehiclePropValue value) {
        if (TextUtils.isEmpty(value.value.stringValue)) {
            EventLog.writeEvent(eventTag, value.value.int32Values.toArray());
            return;
        }
        int size = value.value.int32Values.size();
        Object[] list = new Object[size + 1];
        value.value.int32Values.toArray(list);
        list[list.length - 1] = value.value.stringValue;
        EventLog.writeEvent(eventTag, list);
    }

    int getNextRequestId() {
        int i;
        synchronized (this.mLock) {
            i = this.mNextRequestId;
            this.mNextRequestId = i + 1;
        }
        return i;
    }

    private <REQ, RESP> void addPendingRequestLocked(int requestId, Class<RESP> responseClass, REQ request, HalCallback<RESP> callback) {
        this.mPendingRequests.put(requestId, new PendingRequest<>(responseClass, request, callback));
    }

    private <RESP> void addPendingRequestLocked(int requestId, Class<RESP> responseClass, HalCallback<RESP> callback) {
        addPendingRequestLocked(requestId, responseClass, null, callback);
    }

    private boolean hasPendingRequestLocked(Class<?> responseClass, HalCallback<?> callback) {
        for (int i = 0; i < this.mPendingRequests.size(); i++) {
            PendingRequest<?, ?> pendingRequest = this.mPendingRequests.valueAt(i);
            if (pendingRequest.responseClass == responseClass) {
                Log.w(TAG, "Already have pending request of type " + responseClass);
                callback.onResponse(5, null);
                return true;
            }
        }
        return false;
    }

    private PendingRequest<?, ?> handleRemovePendingRequest(int requestId) {
        PendingRequest<?, ?> pendingRequest;
        this.mHandler.removeMessages(requestId);
        synchronized (this.mLock) {
            pendingRequest = this.mPendingRequests.get(requestId);
            this.mPendingRequests.remove(requestId);
        }
        return pendingRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCheckIfRequestTimedOut(int requestId) {
        PendingRequest<?, ?> pendingRequest = getPendingRequest(requestId);
        if (pendingRequest == null) {
            return;
        }
        Log.w(TAG, "Request #" + requestId + " timed out");
        handleRemovePendingRequest(requestId);
        pendingRequest.callback.onResponse(3, null);
    }

    private PendingRequest<?, ?> getPendingRequest(int requestId) {
        PendingRequest<?, ?> pendingRequest;
        synchronized (this.mLock) {
            pendingRequest = this.mPendingRequests.get(requestId);
        }
        return pendingRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnInitialUserInfoResponse(VehiclePropValue value) {
        int requestId = value.value.int32Values.get(0).intValue();
        HalCallback<InitialUserInfoResponse> callback = handleGetPendingCallback(requestId, InitialUserInfoResponse.class);
        if (callback == null) {
            EventLog.writeEvent(150141, Integer.valueOf(requestId), -1);
            CarStatsLog.write(CarStatsLog.CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED, getRequestIdForStatsLog(requestId), getHalCallbackStatusForStatsd(-1), getInitialUserInfoResponseActionForStatsd(0), -1, -1, "");
            Log.w(TAG, "no callback for requestId " + requestId + ": " + value);
            return;
        }
        handleRemovePendingRequest(requestId);
        try {
            InitialUserInfoResponse response = UserHalHelper.toInitialUserInfoResponse(value);
            EventLog.writeEvent(150141, Integer.valueOf(requestId), 1, Integer.valueOf(response.action), Integer.valueOf(response.userToSwitchOrCreate.userId), Integer.valueOf(response.userToSwitchOrCreate.flags), response.userNameToCreate, response.userLocales);
            CarStatsLog.write(CarStatsLog.CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED, getRequestIdForStatsLog(requestId), getHalCallbackStatusForStatsd(1), getInitialUserInfoResponseActionForStatsd(response.action), response.userToSwitchOrCreate.userId, response.userToSwitchOrCreate.flags, response.userLocales);
            callback.onResponse(1, response);
        } catch (RuntimeException e) {
            Log.e(TAG, "invalid response (" + value + ") from HAL", e);
            EventLog.writeEvent(150141, Integer.valueOf(requestId), 4);
            CarStatsLog.write(CarStatsLog.CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED, getRequestIdForStatsLog(requestId), getHalCallbackStatusForStatsd(4), getInitialUserInfoResponseActionForStatsd(0), -1, -1, "");
            callback.onResponse(4, null);
        }
    }

    private static int getInitialUserInfoResponseActionForStatsd(int action) {
        if (action != 1) {
            return action != 2 ? 1 : 3;
        }
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnSwitchUserResponse(VehiclePropValue value) {
        int requestId = value.value.int32Values.get(0).intValue();
        int messageType = value.value.int32Values.get(1).intValue();
        if (messageType == 3) {
            handleOnSwitchUserVehicleResponse(value);
            return;
        }
        if (messageType == 4) {
            handleOnSwitchUserVehicleRequest(value);
            return;
        }
        Log.e(TAG, "handleOnSwitchUserResponse invalid message type (" + messageType + ") from HAL: " + value);
        HalCallback<SwitchUserResponse> callback = handleGetPendingCallback(requestId, SwitchUserResponse.class);
        if (callback != null) {
            handleRemovePendingRequest(requestId);
            EventLog.writeEvent(150143, Integer.valueOf(requestId), 4);
            callback.onResponse(4, null);
        }
    }

    private void handleOnSwitchUserVehicleRequest(VehiclePropValue value) {
        int requestId = value.value.int32Values.get(0).intValue();
        int targetUserId = value.value.int32Values.get(2).intValue();
        EventLog.writeEvent(150150, Integer.valueOf(requestId), Integer.valueOf(targetUserId));
        CarStatsLog.write(CarStatsLog.CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED, getRequestIdForStatsLog(requestId), 2, -1, -1, targetUserId, -1, -1);
        if (requestId >= 0) {
            Log.e(TAG, "handleVehicleRequest invalid requestId (" + requestId + ") from HAL: " + value);
            return;
        }
        CarUserService userService = (CarUserService) CarLocalServices.getService(CarUserService.class);
        userService.switchAndroidUserFromHal(requestId, targetUserId);
    }

    private void handleOnSwitchUserVehicleResponse(VehiclePropValue value) {
        int requestId = value.value.int32Values.get(0).intValue();
        HalCallback<SwitchUserResponse> callback = handleGetPendingCallback(requestId, SwitchUserResponse.class);
        if (callback == null) {
            EventLog.writeEvent(150143, Integer.valueOf(requestId), -1);
            Log.w(TAG, "no callback for requestId " + requestId + ": " + value);
            logHalSwitchUserResponse(requestId, 4);
            return;
        }
        handleRemovePendingRequest(requestId);
        SwitchUserResponse response = new SwitchUserResponse();
        response.requestId = requestId;
        response.messageType = value.value.int32Values.get(1).intValue();
        response.status = value.value.int32Values.get(2).intValue();
        response.errorMessage = value.value.stringValue;
        if (response.status == 1 || response.status == 2) {
            EventLog.writeEvent(150143, Integer.valueOf(requestId), 1, Integer.valueOf(response.status), response.errorMessage);
            callback.onResponse(1, response);
            logHalSwitchUserResponse(requestId, 1, response.status);
            return;
        }
        EventLog.writeEvent(150143, Integer.valueOf(requestId), 4, Integer.valueOf(response.status), response.errorMessage);
        Log.e(TAG, "invalid status (" + response.status + ") from HAL: " + value);
        callback.onResponse(4, null);
        logHalSwitchUserResponse(requestId, 4, response.status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnCreateUserResponse(VehiclePropValue value) {
        int requestId = value.value.int32Values.get(0).intValue();
        HalCallback<CreateUserResponse> callback = handleGetPendingCallback(requestId, CreateUserResponse.class);
        if (callback == null) {
            EventLog.writeEvent(150152, Integer.valueOf(requestId), -1);
            Log.w(TAG, "no callback for requestId " + requestId + ": " + value);
            return;
        }
        handleRemovePendingRequest(requestId);
        CreateUserResponse response = new CreateUserResponse();
        response.requestId = requestId;
        response.status = value.value.int32Values.get(1).intValue();
        response.errorMessage = value.value.stringValue;
        if (response.status == 1 || response.status == 2) {
            EventLog.writeEvent(150152, Integer.valueOf(requestId), 1, Integer.valueOf(response.status), response.errorMessage);
            callback.onResponse(1, response);
            logHalCreateUserResponse(requestId, 1, response.status);
            return;
        }
        EventLog.writeEvent(150152, Integer.valueOf(requestId), 4, Integer.valueOf(response.status), response.errorMessage);
        Log.e(TAG, "invalid status (" + response.status + ") from HAL: " + value);
        callback.onResponse(4, null);
        logHalCreateUserResponse(requestId, 4);
    }

    private void logHalSwitchUserResponse(int requestId, int halCallbackStatus) {
        CarStatsLog.write(CarStatsLog.CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED, getRequestIdForStatsLog(requestId), getHalCallbackStatusForStatsd(halCallbackStatus), 0);
    }

    private void logHalSwitchUserResponse(int requestId, int halCallbackStatus, int userSwitchstatus) {
        int userSwitchstatusForStatsd = 1;
        if (userSwitchstatus != 1) {
            userSwitchstatusForStatsd = 2;
        }
        CarStatsLog.write(CarStatsLog.CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED, getRequestIdForStatsLog(requestId), getHalCallbackStatusForStatsd(halCallbackStatus), userSwitchstatusForStatsd);
    }

    private void logHalCreateUserResponse(int requestId, int halCallbackStatus) {
        CarStatsLog.write(CarStatsLog.CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED, getRequestIdForStatsLog(requestId), getHalCallbackStatusForStatsd(halCallbackStatus), 0);
    }

    private void logHalCreateUserResponse(int requestId, int halCallbackStatus, int userCreatestatus) {
        int userCreatestatusForStatsd = 1;
        if (userCreatestatus != 1) {
            userCreatestatusForStatsd = 2;
        }
        CarStatsLog.write(CarStatsLog.CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED, getRequestIdForStatsLog(requestId), getHalCallbackStatusForStatsd(halCallbackStatus), userCreatestatusForStatsd);
    }

    private int getHalCallbackStatusForStatsd(int halCallbackStatus) {
        if (halCallbackStatus != 1) {
            if (halCallbackStatus == 2) {
                return 3;
            }
            if (halCallbackStatus == 3) {
                return 4;
            }
            if (halCallbackStatus != 4) {
                return halCallbackStatus != 5 ? 1 : 6;
            }
            return 5;
        }
        return 2;
    }

    private <T> HalCallback<T> handleGetPendingCallback(int i, Class<T> cls) {
        PendingRequest<?, ?> pendingRequest = getPendingRequest(i);
        if (pendingRequest == null) {
            return null;
        }
        if (pendingRequest.responseClass != cls) {
            Log.e(TAG, "Invalid callback class for request " + i + ": expected" + cls + ", but got is " + pendingRequest.responseClass);
            return null;
        }
        return (HalCallback<T>) pendingRequest.callback;
    }

    @Override // com.android.car.hal.HalServiceBase
    public void dump(PrintWriter writer) {
        writer.printf("*User HAL*\n", new Object[0]);
        writer.printf("Relevant CarProperties\n", new Object[0]);
        dumpSystemProperty(writer, "  ", "user_hal_enabled", CarProperties.user_hal_enabled());
        dumpSystemProperty(writer, "  ", "user_hal_timeout", CarProperties.user_hal_timeout());
        synchronized (this.mLock) {
            if (!isSupported()) {
                writer.println(UNSUPPORTED_MSG);
                return;
            }
            int numberProperties = this.mProperties.size();
            writer.printf("%d supported properties\n", Integer.valueOf(numberProperties));
            for (int i = 0; i < numberProperties; i++) {
                writer.printf("%s%s\n", "  ", this.mProperties.valueAt(i));
            }
            writer.printf("Base request id: %d\n", Integer.valueOf(this.mBaseRequestId));
            writer.printf("next request id: %d\n", Integer.valueOf(this.mNextRequestId));
            int numberPendingCallbacks = this.mPendingRequests.size();
            if (numberPendingCallbacks == 0) {
                writer.println("no pending callbacks");
            } else {
                writer.printf("%d pending callbacks: %s\n", Integer.valueOf(numberPendingCallbacks));
                for (int i2 = 0; i2 < numberPendingCallbacks; i2++) {
                    writer.print("  ");
                    this.mPendingRequests.valueAt(i2).dump(writer);
                    writer.println();
                }
            }
        }
    }

    private static void dumpSystemProperty(PrintWriter writer, String indent, String name, Optional<?> prop) {
        String value = prop.isPresent() ? prop.get().toString() : "<NOT SET>";
        writer.printf("%s%s=%s\n", indent, name, value);
    }

    private static final class PendingRequest<REQ, RESP> {
        public final HalCallback<RESP> callback;
        public final REQ request;
        public final Class<RESP> responseClass;

        PendingRequest(Class<RESP> responseClass, REQ request, HalCallback<RESP> callback) {
            this.responseClass = responseClass;
            this.request = request;
            this.callback = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T getRequest(PendingRequest<?, ?> pendingRequest, Class<T> clazz, int requestId) {
            if (pendingRequest == null) {
                Log.e(UserHalService.TAG, "No pending request for id " + requestId);
                return null;
            }
            Object request = pendingRequest.request;
            if (!clazz.isInstance(request)) {
                Log.e(UserHalService.TAG, "Wrong pending request for id " + requestId + ": " + pendingRequest);
                return null;
            }
            return clazz.cast(request);
        }

        public void dump(PrintWriter pw) {
            pw.printf("Class: %s Callback: %s", this.responseClass.getSimpleName(), FunctionalUtils.getLambdaName(this.callback));
            REQ req = this.request;
            if (req != null) {
                pw.printf(" Request: %s", req);
            }
        }

        public String toString() {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.print("[PendingRequest: ");
            dump(pw);
            pw.print("]");
            pw.flush();
            return sw.toString();
        }
    }
}
