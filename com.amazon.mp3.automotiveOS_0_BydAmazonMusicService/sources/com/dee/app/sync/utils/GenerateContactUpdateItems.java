package com.dee.app.sync.utils;

import android.annotation.TargetApi;
import com.dee.app.contacts.common.constants.ContactConfig;
import com.dee.app.contacts.common.models.server.GetContactsV2ServerResponse;
import com.dee.app.contacts.common.network.gateway.ContactsSyncGatewayService;
import com.dee.app.contacts.common.utils.ContactsConverterUtil;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2QueryConfig;
import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2Request;
import com.dee.app.contacts.interfaces.models.data.ContactForSync;
import com.dee.app.contacts.interfaces.models.data.ContactUploadInfo;
import com.dee.app.contacts.interfaces.models.data.enums.ContactSourceType;
import com.dee.app.contacts.interfaces.models.data.enums.OwnerIdType;
import com.dee.app.contacts.interfaces.models.data.enums.SyncOperationType;
import com.dee.app.contacts.interfaces.models.data.server.ServerContact;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes5.dex */
public class GenerateContactUpdateItems {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", GenerateContactUpdateItems.class);
    private final ContactConfig mContactConfig;
    private final ContactsSyncGatewayService mContactsSyncGatewayService;
    private final DeviceContactsFetcher mDeviceContactsFetcher;

    public GenerateContactUpdateItems(ContactConfig contactConfig, ContactsSyncGatewayService contactsSyncGatewayService, DeviceContactsFetcher deviceContactsFetcher) {
        this.mContactConfig = contactConfig;
        this.mContactsSyncGatewayService = contactsSyncGatewayService;
        this.mDeviceContactsFetcher = deviceContactsFetcher;
    }

    public Observable<List<ContactForSync>> getContactDifference(final String str, String str2, OwnerIdType ownerIdType) {
        return Observable.zip(getServerContactsObservable(str2, ownerIdType).subscribeOn(Schedulers.computation()), getAddressBookContactsObservable().subscribeOn(Schedulers.computation()), new Func2() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda0
            @Override // rx.functions.Func2
            public final Object call(Object obj, Object obj2) {
                return this.f$0.lambda$getContactDifference$0(str, (List) obj, (List) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$getContactDifference$0(String str, List list, List list2) {
        ContactsLogger contactsLogger = LOG;
        contactsLogger.i("serverContacts = " + list.size());
        contactsLogger.i("addressBookContacts = " + list2.size());
        return getContactUpdateItems(list, list2, str);
    }

    private Observable<List<ServerContact>> getServerContactsObservable(String str, OwnerIdType ownerIdType) {
        GetContactsV2QueryConfig getContactsV2QueryConfig = new GetContactsV2QueryConfig();
        if (ownerIdType != null) {
            getContactsV2QueryConfig.setOwnerIdType(ownerIdType);
        }
        return this.mContactsSyncGatewayService.getContactsV2(new GetContactsV2Request(str, getContactsV2QueryConfig)).map(new Func1() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda5
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ((GetContactsV2ServerResponse) obj).getContactList();
            }
        }).doOnError(new Action1() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda6
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GenerateContactUpdateItems.lambda$getServerContactsObservable$1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getServerContactsObservable$1(Throwable th) {
        LOG.i("GetContactV2 API failed, contacts up sync aborted" + th);
    }

    private Observable<List<ContactUploadInfo>> getAddressBookContactsObservable() {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$getAddressBookContactsObservable$2((Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getAddressBookContactsObservable$2(Subscriber subscriber) {
        try {
            subscriber.onNext(this.mDeviceContactsFetcher.getDeviceContacts());
        } catch (Exception e) {
            LOG.e("Unknown exception while fetching address book contacts");
            subscriber.onError(e);
        }
    }

    @TargetApi(24)
    public List<ContactForSync> getContactUpdateItems(List<ServerContact> list, List<ContactUploadInfo> list2, final String str) throws Exception {
        try {
            final List listAsList = Arrays.asList(ContactSourceType.BulkImportedContact.getName(), ContactSourceType.SyncedContact.getName());
            List list3 = (List) list.stream().filter(new Predicate() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return GenerateContactUpdateItems.lambda$getContactUpdateItems$3(str, listAsList, (ServerContact) obj);
                }
            }).collect(Collectors.toList());
            LOG.i("filteredServerContacts size = " + list3.size());
            final Map<String, ContactUploadInfo> mapBuildContactMap = buildContactMap(new ArrayList(Sets.newHashSet(list2)));
            final ArrayList arrayList = new ArrayList();
            list3.forEach(new Consumer() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$getContactUpdateItems$4(mapBuildContactMap, str, arrayList, (ServerContact) obj);
                }
            });
            mapBuildContactMap.forEach(new BiConsumer() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda4
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GenerateContactUpdateItems.lambda$getContactUpdateItems$5(arrayList, (String) obj, (ContactUploadInfo) obj2);
                }
            });
            return arrayList;
        } catch (Exception e) {
            LOG.i("Exception in getContactUpdateItems " + e);
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getContactUpdateItems$3(String str, List list, ServerContact serverContact) {
        return Boolean.valueOf(!serverContact.isHomeGroup() && serverContact.getSourceDeviceId() != null && serverContact.getSourceDeviceId().equals(str) && list.contains(serverContact.getContactSourceType())).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getContactUpdateItems$4(Map map, String str, List list, ServerContact serverContact) {
        ContactUploadInfo contactUploadInfo = (ContactUploadInfo) map.get(!StringUtils.isBlank(serverContact.getDeviceContactId()) ? serverContact.getDeviceContactId() : "invalid");
        if (contactUploadInfo != null) {
            if (serverContact.getSourceDeviceId().equals(str)) {
                if (!isContactMatch(contactUploadInfo, standardizeServerContact(serverContact)).booleanValue()) {
                    list.add(ContactsConverterUtil.convertContactToContactForSync(contactUploadInfo, SyncOperationType.UPDATE));
                }
                map.remove(serverContact.getDeviceContactId());
                return;
            }
            list.add(ContactsConverterUtil.convertContactToContactForSync(contactUploadInfo, SyncOperationType.DELETE));
            return;
        }
        list.add(ContactsConverterUtil.convertContactToContactForSync(standardizeServerContact(serverContact), SyncOperationType.DELETE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getContactUpdateItems$5(List list, String str, ContactUploadInfo contactUploadInfo) {
        list.add(ContactsConverterUtil.convertContactToContactForSync(contactUploadInfo, SyncOperationType.ADD));
    }

    public ContactUploadInfo standardizeServerContact(ServerContact serverContact) {
        return standardizeContact(ContactsConverterUtil.convertServerContactToContactUploadInfo(serverContact));
    }

    public ContactUploadInfo standardizeContact(ContactUploadInfo contactUploadInfo) {
        List<ContactUploadInfo.PhoneNumber> listEmptyList;
        contactUploadInfo.setContactName(standardizeName(contactUploadInfo.getName().getFirstName()), standardizeName(contactUploadInfo.getName().getLastName()), standardizeName(contactUploadInfo.getName().getPhoneticFirstName()), standardizeName(contactUploadInfo.getName().getPhoneticLastName()), standardizeName(contactUploadInfo.getName().getNickName()));
        String company = contactUploadInfo.getCompany();
        if (!StringUtils.isBlank(company)) {
            contactUploadInfo.setCompany(getSubstring(company, this.mContactConfig.companyNameMaxLength));
        }
        if (!isNullOrEmpty(contactUploadInfo.getNumbers()).booleanValue()) {
            listEmptyList = sortAndTruncatePhoneNumbers(contactUploadInfo.getNumbers(), this.mContactConfig.phoneNumberMaxLength);
        } else {
            listEmptyList = Collections.emptyList();
        }
        List<ContactUploadInfo.EmailAddress> listSortAndTruncateEmails = !isNullOrEmpty(contactUploadInfo.getEmails()).booleanValue() ? sortAndTruncateEmails(contactUploadInfo.getEmails(), this.mContactConfig.maxEmailAddressLength) : null;
        contactUploadInfo.setPhoneNumbers(listEmptyList);
        contactUploadInfo.setEmailAddresses(listSortAndTruncateEmails);
        return contactUploadInfo;
    }

    @TargetApi(24)
    private List<ContactUploadInfo.PhoneNumber> sortAndTruncatePhoneNumbers(List<ContactUploadInfo.PhoneNumber> list, final Integer num) {
        return (List) list.stream().filter(new Predicate() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda11
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return GenerateContactUpdateItems.lambda$sortAndTruncatePhoneNumbers$6((ContactUploadInfo.PhoneNumber) obj);
            }
        }).sorted(new Comparator() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda12
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return GenerateContactUpdateItems.lambda$sortAndTruncatePhoneNumbers$7((ContactUploadInfo.PhoneNumber) obj, (ContactUploadInfo.PhoneNumber) obj2);
            }
        }).map(new Function() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda13
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$sortAndTruncatePhoneNumbers$8(num, (ContactUploadInfo.PhoneNumber) obj);
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$sortAndTruncatePhoneNumbers$6(ContactUploadInfo.PhoneNumber phoneNumber) {
        return phoneNumber.getNumber() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$sortAndTruncatePhoneNumbers$7(ContactUploadInfo.PhoneNumber phoneNumber, ContactUploadInfo.PhoneNumber phoneNumber2) {
        return phoneNumber.getNumber().compareTo(phoneNumber2.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ContactUploadInfo.PhoneNumber lambda$sortAndTruncatePhoneNumbers$8(Integer num, ContactUploadInfo.PhoneNumber phoneNumber) {
        phoneNumber.setNumber(getSubstring(phoneNumber.getNumber(), num));
        return phoneNumber;
    }

    @TargetApi(24)
    private List<ContactUploadInfo.EmailAddress> sortAndTruncateEmails(List<ContactUploadInfo.EmailAddress> list, final Integer num) {
        return (List) list.stream().filter(new Predicate() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return GenerateContactUpdateItems.lambda$sortAndTruncateEmails$9((ContactUploadInfo.EmailAddress) obj);
            }
        }).sorted(new Comparator() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda9
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return GenerateContactUpdateItems.lambda$sortAndTruncateEmails$10((ContactUploadInfo.EmailAddress) obj, (ContactUploadInfo.EmailAddress) obj2);
            }
        }).map(new Function() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$sortAndTruncateEmails$11(num, (ContactUploadInfo.EmailAddress) obj);
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$sortAndTruncateEmails$9(ContactUploadInfo.EmailAddress emailAddress) {
        return emailAddress.getEmailAddress() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$sortAndTruncateEmails$10(ContactUploadInfo.EmailAddress emailAddress, ContactUploadInfo.EmailAddress emailAddress2) {
        return emailAddress.getEmailAddress().compareTo(emailAddress2.getEmailAddress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ContactUploadInfo.EmailAddress lambda$sortAndTruncateEmails$11(Integer num, ContactUploadInfo.EmailAddress emailAddress) {
        emailAddress.setEmailAddress(getSubstring(emailAddress.getEmailAddress(), num));
        return emailAddress;
    }

    @TargetApi(24)
    public Map<String, ContactUploadInfo> buildContactMap(List<ContactUploadInfo> list) {
        final HashMap map = new HashMap();
        list.forEach(new Consumer() { // from class: com.dee.app.sync.utils.GenerateContactUpdateItems$$ExternalSyntheticLambda7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$buildContactMap$12(map, (ContactUploadInfo) obj);
            }
        });
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildContactMap$12(Map map, ContactUploadInfo contactUploadInfo) {
        if (isAddressBookContactEmpty(contactUploadInfo).booleanValue()) {
            return;
        }
        map.put(contactUploadInfo.getDeviceContactId(), contactUploadInfo);
    }

    public Boolean isAddressBookContactEmpty(ContactUploadInfo contactUploadInfo) {
        ContactUploadInfo.Name name = contactUploadInfo.getName();
        if (!StringUtils.isBlank(name.getFirstName()) || !StringUtils.isBlank(name.getLastName()) || !StringUtils.isBlank(name.getNickName()) || !StringUtils.isBlank(name.getPhoneticFirstName()) || !StringUtils.isBlank(name.getPhoneticLastName()) || !ArrayUtils.isEmpty(contactUploadInfo.getNumbers().toArray()) || !ArrayUtils.isEmpty(contactUploadInfo.getEmails().toArray()) || !StringUtils.isBlank(contactUploadInfo.getCompany())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private Boolean isContactMatch(ContactUploadInfo contactUploadInfo, ContactUploadInfo contactUploadInfo2) {
        return Boolean.valueOf(compareName(contactUploadInfo.getName(), contactUploadInfo2.getName()).booleanValue() && comparePhoneNumbers(contactUploadInfo.getNumbers(), contactUploadInfo2.getNumbers()).booleanValue() && compareEmails(contactUploadInfo.getEmails(), contactUploadInfo2.getEmails()).booleanValue() && isEqual(contactUploadInfo.getCompany(), contactUploadInfo2.getCompany()).booleanValue());
    }

    private Boolean compareName(ContactUploadInfo.Name name, ContactUploadInfo.Name name2) {
        return Boolean.valueOf(isEqual(name.getFirstName(), name2.getFirstName()).booleanValue() && isEqual(name.getLastName(), name2.getLastName()).booleanValue() && isEqual(name.getPhoneticFirstName(), name2.getPhoneticFirstName()).booleanValue() && isEqual(name.getPhoneticLastName(), name2.getPhoneticLastName()).booleanValue() && isEqual(name.getPronunciationFirstName(), name2.getPronunciationFirstName()).booleanValue() && isEqual(name.getPronunciationLastName(), name2.getPronunciationLastName()).booleanValue() && isEqual(name.getNickName(), name2.getNickName()).booleanValue());
    }

    private Boolean comparePhoneNumbers(List<ContactUploadInfo.PhoneNumber> list, List<ContactUploadInfo.PhoneNumber> list2) {
        if (isNullOrEmpty(list).booleanValue() && isNullOrEmpty(list2).booleanValue()) {
            return Boolean.TRUE;
        }
        if (list.size() != list2.size()) {
            return Boolean.FALSE;
        }
        HashMap map = new HashMap();
        for (ContactUploadInfo.PhoneNumber phoneNumber : list) {
            List arrayList = (List) map.get(phoneNumber.getNumber());
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(phoneNumber.getRawType());
            map.put(phoneNumber.getNumber(), arrayList);
        }
        for (ContactUploadInfo.PhoneNumber phoneNumber2 : list2) {
            List list3 = (List) map.get(phoneNumber2.getNumber());
            int iIndexOf = list3 != null ? list3.indexOf(phoneNumber2.getRawType()) : -1;
            if (iIndexOf < 0) {
                return Boolean.TRUE;
            }
            list3.subList(iIndexOf, iIndexOf + 1).clear();
            if (list3.isEmpty()) {
                map.remove(phoneNumber2.getNumber());
            }
        }
        return Boolean.valueOf(map.size() == 0);
    }

    private Boolean compareEmails(List<ContactUploadInfo.EmailAddress> list, List<ContactUploadInfo.EmailAddress> list2) {
        if (isNullOrEmpty(list).booleanValue() && isNullOrEmpty(list2).booleanValue()) {
            return Boolean.TRUE;
        }
        if (list.size() != list2.size()) {
            return Boolean.FALSE;
        }
        HashMap map = new HashMap();
        for (ContactUploadInfo.EmailAddress emailAddress : list) {
            List arrayList = (List) map.get(emailAddress.getEmailAddress());
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(emailAddress.getRawType());
            map.put(emailAddress.getEmailAddress(), arrayList);
        }
        for (ContactUploadInfo.EmailAddress emailAddress2 : list2) {
            List list3 = (List) map.get(emailAddress2.getEmailAddress());
            int iIndexOf = list3 != null ? list3.indexOf(emailAddress2.getRawType()) : -1;
            if (iIndexOf < 0) {
                return Boolean.FALSE;
            }
            list3.subList(iIndexOf, iIndexOf + 1).clear();
            if (list3.isEmpty()) {
                map.remove(emailAddress2.getEmailAddress());
            }
        }
        return Boolean.valueOf(map.size() == 0);
    }

    private String standardizeName(String str) {
        return !StringUtils.isBlank(str) ? getSubstring(str, this.mContactConfig.nameMaxLength) : "";
    }

    private String getSubstring(String str, Integer num) {
        return str.substring(0, Math.min(str.length(), num.intValue() + 1));
    }

    private <T> Boolean isNullOrEmpty(List<T> list) {
        return Boolean.valueOf(list == null || list.isEmpty());
    }

    private <T> Boolean isNullOrEmpty(String str) {
        return (str == null || str.isEmpty()) ? Boolean.TRUE : Boolean.FALSE;
    }

    private Boolean isEqual(String str, String str2) {
        if (isNullOrEmpty(str).booleanValue() && isNullOrEmpty(str2).booleanValue()) {
            return Boolean.TRUE;
        }
        return Boolean.valueOf(StringUtils.equals(str, str2));
    }
}
