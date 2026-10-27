package com.dee.app.sync.api.sync;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import com.amazonaws.services.machinelearning.model.InvalidInputException;
import com.dee.app.contacts.common.errors.ContactsError;
import com.dee.app.contacts.common.exceptions.ClientNotSupportedException;
import com.dee.app.contacts.common.exceptions.EmptyBatchException;
import com.dee.app.contacts.common.models.server.CanSyncContactsServerResponse;
import com.dee.app.contacts.common.network.gateway.ContactsSyncGatewayService;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.common.utils.ContactsMetricsRecorder;
import com.dee.app.contacts.common.utils.PermissionsUtils;
import com.dee.app.contacts.common.utils.SyncUtils;
import com.dee.app.contacts.interfaces.models.apis.batchupdatecontacts.BatchUpdateLABContactsBody;
import com.dee.app.contacts.interfaces.models.apis.batchupdatecontacts.BatchUpdateLABContactsQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.batchupdatecontacts.BatchUpdateLABContactsRequest;
import com.dee.app.contacts.interfaces.models.apis.batchupdatecontacts.BatchUpdateLABContactsResponse;
import com.dee.app.contacts.interfaces.models.apis.cansync.CanSyncContactsQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.cansync.CanSyncContactsRequest;
import com.dee.app.contacts.interfaces.models.apis.getmasterdeviceid.GetMasterDeviceIdQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.getmasterdeviceid.GetMasterDeviceIdRequest;
import com.dee.app.contacts.interfaces.models.apis.getmasterdeviceid.GetMasterDeviceIdResponse;
import com.dee.app.contacts.interfaces.models.apis.synccontacts.ContactUpdateItemsResponse;
import com.dee.app.contacts.interfaces.models.apis.synccontacts.ContactsUpSyncRequest;
import com.dee.app.contacts.interfaces.models.apis.synccontacts.ContactsUpSyncResponse;
import com.dee.app.contacts.interfaces.models.apis.synccontacts.SplitBatchesResponse;
import com.dee.app.contacts.interfaces.models.data.ContactForSync;
import com.dee.app.contacts.interfaces.models.data.enums.CanSyncContactsStatus;
import com.dee.app.contacts.interfaces.models.data.enums.ContactSourceType;
import com.dee.app.contacts.interfaces.models.data.enums.PermissionAuthStatus;
import com.dee.app.contacts.interfaces.models.data.enums.PermissionRequestInfo;
import com.dee.app.contacts.interfaces.models.data.enums.SyncOperationType;
import com.dee.app.sync.api.AbstractContactApiAction;
import com.dee.app.sync.utils.GenerateContactUpdateItems;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.FuncN;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class ContactsUpSyncApi extends AbstractContactApiAction<ContactsUpSyncRequest, ContactsUpSyncResponse> {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", ContactsUpSyncApi.class);
    private final ContactsSyncGatewayService mContactsSyncGatewayService;
    private final String mDeviceId;
    private final GenerateContactUpdateItems mGenerateContactUpdateItems;
    private final ContactsMetricsRecorder mMetricsRecorder;
    private final PermissionsUtils mPermissionsUtils;
    private final SyncUtils mSyncUtils;

    public ContactsUpSyncApi(SharedPreferences sharedPreferences, PermissionsUtils permissionsUtils, ContactsSyncGatewayService contactsSyncGatewayService, SyncUtils syncUtils, GenerateContactUpdateItems generateContactUpdateItems, String str, ContactsMetricsRecorder contactsMetricsRecorder) {
        super(sharedPreferences);
        this.mPermissionsUtils = permissionsUtils;
        this.mContactsSyncGatewayService = contactsSyncGatewayService;
        this.mSyncUtils = syncUtils;
        this.mGenerateContactUpdateItems = generateContactUpdateItems;
        this.mDeviceId = str;
        this.mMetricsRecorder = contactsMetricsRecorder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.dee.app.sync.api.AbstractContactApiAction
    public Observable<ContactsUpSyncResponse> handleAction(ContactsUpSyncRequest contactsUpSyncRequest) {
        return handleUpSyncContactsAction(contactsUpSyncRequest).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    private Observable<ContactsUpSyncResponse> handleUpSyncContactsAction(final ContactsUpSyncRequest contactsUpSyncRequest) {
        if (this.mPermissionsUtils.getPermissionAuthStatus(PermissionRequestInfo.READ_CONTACTS) != PermissionAuthStatus.GRANTED) {
            LOG.i("Can't sync, no contact read permission.");
            return Observable.just(new ContactsUpSyncResponse(Boolean.FALSE, "Can't sync, no contact read permission."));
        }
        CanSyncContactsRequest canSyncContactsRequest = new CanSyncContactsRequest(contactsUpSyncRequest.getOwnerId(), contactsUpSyncRequest.getOwnerIdType() != null ? new CanSyncContactsQueryConfig(this.mDeviceId, contactsUpSyncRequest.getOwnerIdType()) : new CanSyncContactsQueryConfig(this.mDeviceId));
        final AtomicReference atomicReference = new AtomicReference();
        return canSync(canSyncContactsRequest).flatMap(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$handleUpSyncContactsAction$0(contactsUpSyncRequest, (Boolean) obj);
            }
        }).flatMap(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda1
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$handleUpSyncContactsAction$1(contactsUpSyncRequest, (ContactSourceType) obj);
            }
        }).flatMap(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda2
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$handleUpSyncContactsAction$2(atomicReference, contactsUpSyncRequest, (ContactUpdateItemsResponse) obj);
            }
        }).flatMap(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda3
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$handleUpSyncContactsAction$3(atomicReference, contactsUpSyncRequest, (Boolean) obj);
            }
        }).map(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda4
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$handleUpSyncContactsAction$4((Boolean) obj);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$handleUpSyncContactsAction$5((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Observable lambda$handleUpSyncContactsAction$0(ContactsUpSyncRequest contactsUpSyncRequest, Boolean bool) {
        return onSyncAllowed(contactsUpSyncRequest, this.mDeviceId, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Observable lambda$handleUpSyncContactsAction$1(ContactsUpSyncRequest contactsUpSyncRequest, ContactSourceType contactSourceType) {
        return performUpSync(contactsUpSyncRequest, contactSourceType, this.mDeviceId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Observable lambda$handleUpSyncContactsAction$2(AtomicReference atomicReference, ContactsUpSyncRequest contactsUpSyncRequest, ContactUpdateItemsResponse contactUpdateItemsResponse) {
        atomicReference.set(contactUpdateItemsResponse);
        return splitAndPerformBatchUpdates(contactUpdateItemsResponse.getDeleteOnlyItems(), contactUpdateItemsResponse.getContactSourceType(), contactsUpSyncRequest, this.mDeviceId, Boolean.TRUE, SyncOperationType.DELETE.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Observable lambda$handleUpSyncContactsAction$3(AtomicReference atomicReference, ContactsUpSyncRequest contactsUpSyncRequest, Boolean bool) {
        return splitAndPerformBatchUpdates(((ContactUpdateItemsResponse) atomicReference.get()).getNonDeleteItems(), ((ContactUpdateItemsResponse) atomicReference.get()).getContactSourceType(), contactsUpSyncRequest, this.mDeviceId, bool, SyncOperationType.ADD.toString() + "/" + SyncOperationType.UPDATE.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ContactsUpSyncResponse lambda$handleUpSyncContactsAction$4(Boolean bool) {
        recordMetrics("CommsContacts.ContactsUpSync.Success");
        return new ContactsUpSyncResponse(bool, "Completed up-sync operation");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleUpSyncContactsAction$5(Throwable th) {
        recordMetrics("CommsContacts.ContactsUpSync.Failure");
        LOG.e("Contacts up-sync failed. ", th);
    }

    private Observable<Boolean> splitAndPerformBatchUpdates(List<ContactForSync> list, final ContactSourceType contactSourceType, final ContactsUpSyncRequest contactsUpSyncRequest, final String str, Boolean bool, final String str2) {
        if (!bool.booleanValue()) {
            throw new ContactsError("Previous batch failed. Hence aborting up-sync");
        }
        final AtomicReference atomicReference = new AtomicReference();
        return splitBatchUpdates(list, contactSourceType, str2).flatMap(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda8
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$splitAndPerformBatchUpdates$6(atomicReference, contactsUpSyncRequest, str, contactSourceType, str2, (SplitBatchesResponse) obj);
            }
        }).flatMap(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda9
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$splitAndPerformBatchUpdates$7(contactsUpSyncRequest, str, contactSourceType, atomicReference, str2, (Boolean) obj);
            }
        }).map(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda10
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ContactsUpSyncApi.lambda$splitAndPerformBatchUpdates$8((Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Observable lambda$splitAndPerformBatchUpdates$6(AtomicReference atomicReference, ContactsUpSyncRequest contactsUpSyncRequest, String str, ContactSourceType contactSourceType, String str2, SplitBatchesResponse splitBatchesResponse) {
        atomicReference.set(splitBatchesResponse);
        return lambda$performBatchUpdateContacts$18(contactsUpSyncRequest, str, contactSourceType, Boolean.FALSE, splitBatchesResponse.getInitialBatches(), Boolean.TRUE, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Observable lambda$splitAndPerformBatchUpdates$7(ContactsUpSyncRequest contactsUpSyncRequest, String str, ContactSourceType contactSourceType, AtomicReference atomicReference, String str2, Boolean bool) {
        return lambda$performBatchUpdateContacts$18(contactsUpSyncRequest, str, contactSourceType, Boolean.TRUE, ((SplitBatchesResponse) atomicReference.get()).getLastBatch(), bool, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$splitAndPerformBatchUpdates$8(Boolean bool) {
        Boolean bool2 = Boolean.TRUE;
        return bool == bool2 ? bool2 : Boolean.FALSE;
    }

    private Observable<Boolean> canSync(CanSyncContactsRequest canSyncContactsRequest) {
        return this.mContactsSyncGatewayService.canSync(canSyncContactsRequest).map(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda13
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ContactsUpSyncApi.lambda$canSync$9((CanSyncContactsServerResponse) obj);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda14
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ContactsUpSyncApi.lambda$canSync$10((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$canSync$9(CanSyncContactsServerResponse canSyncContactsServerResponse) {
        ContactsLogger contactsLogger = LOG;
        contactsLogger.i("can sync status = " + canSyncContactsServerResponse.getCanSyncStatus());
        if (CanSyncContactsStatus.ALLOWED.getStatus().equals(canSyncContactsServerResponse.getCanSyncStatus())) {
            return Boolean.TRUE;
        }
        contactsLogger.i("can sync contacts is not allowed - " + canSyncContactsServerResponse.getCanSyncStatus());
        return Boolean.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$canSync$10(Throwable th) {
        LOG.e("Error occurred during CanSync contacts", th);
    }

    private Observable<ContactSourceType> onSyncAllowed(ContactsUpSyncRequest contactsUpSyncRequest, final String str, Boolean bool) {
        ContactsLogger contactsLogger = LOG;
        contactsLogger.i("isSyncAllowed = " + bool);
        if (!bool.booleanValue()) {
            return Observable.just(ContactSourceType.UnsetContact);
        }
        if (!this.mSyncUtils.is2pClient().booleanValue()) {
            contactsLogger.i("Request is from Alexa client");
            return this.mContactsSyncGatewayService.getMasterDeviceId(new GetMasterDeviceIdRequest(contactsUpSyncRequest.getOwnerId(), contactsUpSyncRequest.getOwnerIdType() != null ? new GetMasterDeviceIdQueryConfig(contactsUpSyncRequest.getOwnerIdType()) : new GetMasterDeviceIdQueryConfig())).map(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda11
                @Override // rx.functions.Func1
                public final Object call(Object obj) {
                    return ContactsUpSyncApi.lambda$onSyncAllowed$11(str, (GetMasterDeviceIdResponse) obj);
                }
            }).doOnError(new Action1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda12
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    ContactsUpSyncApi.lambda$onSyncAllowed$12((Throwable) obj);
                }
            });
        }
        contactsLogger.i("Request is from 2P client: " + this.mSyncUtils.getClient().toString());
        try {
            return Observable.just(this.mSyncUtils.getContactSourceTypeForClient());
        } catch (ClientNotSupportedException e) {
            return Observable.error(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.amazonaws.services.machinelearning.model.InvalidInputException */
    public static /* synthetic */ ContactSourceType lambda$onSyncAllowed$11(String str, GetMasterDeviceIdResponse getMasterDeviceIdResponse) throws InvalidInputException {
        LOG.i("Successfully fetched masterDeviceId");
        if (getMasterDeviceIdResponse.getMasterDeviceId().equals(str)) {
            return ContactSourceType.SyncedContact;
        }
        throw new InvalidInputException("deviceId and masterDeviceId didn't match");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onSyncAllowed$12(Throwable th) {
        LOG.e("GetMasterDeviceId API failed ", th);
    }

    private Observable<ContactUpdateItemsResponse> performUpSync(ContactsUpSyncRequest contactsUpSyncRequest, final ContactSourceType contactSourceType, String str) {
        final ArrayList arrayList = new ArrayList();
        return this.mGenerateContactUpdateItems.getContactDifference(str, contactsUpSyncRequest.getOwnerId(), contactsUpSyncRequest.getOwnerIdType()).map(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda6
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ContactsUpSyncApi.lambda$performUpSync$13(arrayList, contactSourceType, (List) obj);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda7
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ContactsUpSyncApi.lambda$performUpSync$14((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ContactUpdateItemsResponse lambda$performUpSync$13(List list, ContactSourceType contactSourceType, List list2) {
        list.addAll(list2);
        ContactsLogger contactsLogger = LOG;
        contactsLogger.i("ContactUpdateItems size = " + list.size());
        if (list.size() == 0) {
            contactsLogger.i("ContactUpdateItems length = 0");
            List list3 = Collections.EMPTY_LIST;
            return new ContactUpdateItemsResponse(list3, list3, contactSourceType);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ContactForSync contactForSync = (ContactForSync) it.next();
            if (contactForSync.getSyncOperationType().equals(SyncOperationType.DELETE)) {
                arrayList.add(contactForSync);
            } else {
                arrayList2.add(contactForSync);
            }
        }
        ContactsLogger contactsLogger2 = LOG;
        contactsLogger2.i("deleteOnlyItems length = " + arrayList.size());
        contactsLogger2.i("nonDeleteItems length = " + arrayList2.size());
        return new ContactUpdateItemsResponse(arrayList, arrayList2, contactSourceType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$performUpSync$14(Throwable th) {
        LOG.e("performUpSync failed ", th);
    }

    private Observable<SplitBatchesResponse> splitBatchUpdates(List<ContactForSync> list, ContactSourceType contactSourceType, String str) {
        new ArrayList();
        try {
            List<List<ContactForSync>> listSplitUpdatesIntoBatches = splitUpdatesIntoBatches(list);
            ArrayList arrayList = new ArrayList();
            arrayList.add(listSplitUpdatesIntoBatches.get(listSplitUpdatesIntoBatches.size() - 1));
            return Observable.just(new SplitBatchesResponse(listSplitUpdatesIntoBatches.subList(0, listSplitUpdatesIntoBatches.size() - 1), arrayList, contactSourceType));
        } catch (EmptyBatchException unused) {
            LOG.i("No contact updates to process for " + str);
            return Observable.just(new SplitBatchesResponse(Collections.EMPTY_LIST, Collections.EMPTY_LIST, contactSourceType));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(24)
    /* JADX INFO: renamed from: performBatchUpdateContacts, reason: merged with bridge method [inline-methods] */
    public Observable<Boolean> lambda$performBatchUpdateContacts$18(final ContactsUpSyncRequest contactsUpSyncRequest, final String str, final ContactSourceType contactSourceType, final Boolean bool, List<List<ContactForSync>> list, Boolean bool2, final String str2) {
        final List<List<ContactForSync>> listSubList;
        List<List<ContactForSync>> list2 = list;
        if (!bool2.booleanValue()) {
            throw new ContactsError("Previous batches failed. Aborting contacts up-sync");
        }
        ContactsLogger contactsLogger = LOG;
        contactsLogger.i("Processing last batch = " + bool);
        if (list.size() == 0) {
            contactsLogger.i("No batches present to perform up-sync for " + str2);
            return Observable.just(Boolean.TRUE);
        }
        Integer num = 2;
        if (list.size() > num.intValue()) {
            List<List<ContactForSync>> listSubList2 = list2.subList(0, num.intValue());
            listSubList = list2.subList(num.intValue(), list.size());
            list2 = listSubList2;
        } else {
            listSubList = Collections.EMPTY_LIST;
        }
        contactsLogger.i("Current batch size = " + list2.size());
        contactsLogger.i("Remaining batch size = " + listSubList.size());
        ArrayList arrayList = new ArrayList();
        Iterator<List<ContactForSync>> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(getBatchUpdateLABContactsObservable(new BatchUpdateLABContactsRequest(contactsUpSyncRequest.getOwnerId(), new BatchUpdateLABContactsQueryConfig(contactsUpSyncRequest.getOwnerIdType()), new BatchUpdateLABContactsBody(str, contactsUpSyncRequest.getSourceDeviceName(), bool, contactSourceType.getName(), it.next()))));
        }
        return Observable.zip(arrayList, new FuncN() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda15
            @Override // rx.functions.FuncN
            public final Object call(Object[] objArr) {
                return ContactsUpSyncApi.lambda$performBatchUpdateContacts$16(objArr);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda16
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ContactsUpSyncApi.lambda$performBatchUpdateContacts$17((Throwable) obj);
            }
        }).flatMap(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda17
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$performBatchUpdateContacts$18(contactsUpSyncRequest, str, contactSourceType, bool, listSubList, str2, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$performBatchUpdateContacts$16(Object[] objArr) {
        List list = (List) Arrays.stream(objArr).map(new Function() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda18
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ContactsUpSyncApi.lambda$null$15(obj);
            }
        }).collect(Collectors.toList());
        Boolean boolValueOf = Boolean.TRUE;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            boolValueOf = Boolean.valueOf(boolValueOf.booleanValue() && ((Boolean) it.next()).booleanValue());
        }
        return boolValueOf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$null$15(Object obj) {
        return (Boolean) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$performBatchUpdateContacts$17(Throwable th) {
        LOG.e("Error while performing async batch updates");
    }

    private Observable<Boolean> getBatchUpdateLABContactsObservable(BatchUpdateLABContactsRequest batchUpdateLABContactsRequest) {
        return this.mContactsSyncGatewayService.batchUpdateContacts(batchUpdateLABContactsRequest).map(new Func1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda19
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ContactsUpSyncApi.lambda$getBatchUpdateLABContactsObservable$19((BatchUpdateLABContactsResponse) obj);
            }
        }).doOnError(new Action1() { // from class: com.dee.app.sync.api.sync.ContactsUpSyncApi$$ExternalSyntheticLambda20
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ContactsUpSyncApi.lambda$getBatchUpdateLABContactsObservable$20((Throwable) obj);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$getBatchUpdateLABContactsObservable$19(BatchUpdateLABContactsResponse batchUpdateLABContactsResponse) {
        if (batchUpdateLABContactsResponse.getFailureList() != null && batchUpdateLABContactsResponse.getFailureList().isEmpty() && batchUpdateLABContactsResponse.getSuccessList() != null && batchUpdateLABContactsResponse.getSuccessList().size() > 0) {
            LOG.i("Successfully synced " + batchUpdateLABContactsResponse.getSuccessList().size() + " contacts.");
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getBatchUpdateLABContactsObservable$20(Throwable th) {
        LOG.e("batchUpdateLABContacts failed, contacts up sync aborted. " + th);
    }

    private List<List<ContactForSync>> splitUpdatesIntoBatches(List<ContactForSync> list) throws EmptyBatchException {
        Integer num = 100;
        List<List<ContactForSync>> listPartition = Lists.partition(list, num.intValue());
        if (listPartition.size() != 0) {
            return listPartition;
        }
        throw new EmptyBatchException("Batches count is zero");
    }

    private void recordMetrics(String str) {
        this.mMetricsRecorder.recordCounter(str, ContactsUpSyncApi.class);
    }
}
