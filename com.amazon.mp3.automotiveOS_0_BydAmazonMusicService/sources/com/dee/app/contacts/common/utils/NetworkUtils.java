package com.dee.app.contacts.common.utils;

import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import java.text.MessageFormat;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class NetworkUtils {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", NetworkUtils.class);
    private final AlexaCommsCoreIdentityService mCommsIdentityManager;

    public String getAccountsURL() {
        return "/commsidentity/accounts";
    }

    public String getPhoneNumberVerificationURL() {
        return "/commsidentity/cvf/aao";
    }

    public NetworkUtils(AlexaCommsCoreIdentityService alexaCommsCoreIdentityService) {
        this.mCommsIdentityManager = alexaCommsCoreIdentityService;
    }

    public String getContactsV2URL(String str) {
        String ownerId = getOwnerId(str);
        if (StringUtils.isBlank(ownerId)) {
            return null;
        }
        return MessageFormat.format("/users/{0}/paginatedContacts", ownerId);
    }

    public String batchUpdateContactsURL(String str) {
        String ownerId = getOwnerId(str);
        if (StringUtils.isBlank(ownerId)) {
            return null;
        }
        return MessageFormat.format("/users/{0}/contacts", ownerId);
    }

    public String canSyncContactsURL(String str) {
        String ownerId = getOwnerId(str);
        if (StringUtils.isBlank(ownerId)) {
            return null;
        }
        return MessageFormat.format("/contacts/users/{0}/canSyncContacts", ownerId);
    }

    public String getMasterDeviceIdURL(String str) {
        String ownerId = getOwnerId(str);
        if (StringUtils.isBlank(ownerId)) {
            return null;
        }
        return MessageFormat.format("/users/{0}/devices", ownerId);
    }

    public String getContactByContactIdURL(String str, String str2) {
        if (StringUtils.isBlank(str2)) {
            return null;
        }
        String ownerId = getOwnerId(str);
        if (StringUtils.isBlank(ownerId)) {
            return null;
        }
        return MessageFormat.format("/users/{0}/contact/{1}", ownerId, str2);
    }

    public String getSetContactPreferenceURL(String str, String str2) {
        if (StringUtils.isBlank(str2)) {
            return null;
        }
        String ownerId = getOwnerId(str);
        if (StringUtils.isBlank(ownerId)) {
            return null;
        }
        return MessageFormat.format("/users/{0}/contacts/{1}/preferences", ownerId, str2);
    }

    public String getContactPreferenceURL(String str, String str2, String str3) {
        if (StringUtils.isBlank(str2) || StringUtils.isBlank(str3)) {
            return null;
        }
        String ownerHgId = getOwnerHgId(str);
        if (StringUtils.isBlank(ownerHgId)) {
            return null;
        }
        return MessageFormat.format("/users/{0}/contacts/{1}/preferences/{2}", ownerHgId, str2, str3);
    }

    public String getIdentityPreferenceURL(String str, String str2) {
        if (StringUtils.isBlank(str2)) {
            return null;
        }
        String ownerId = getOwnerId(str);
        if (StringUtils.isBlank(ownerId)) {
            return null;
        }
        return MessageFormat.format("/users/{0}/preferences/{1}", ownerId, str2);
    }

    public String getSetIdentityPreferenceURL(String str, String str2) {
        if (StringUtils.isBlank(str2)) {
            return null;
        }
        String ownerId = getOwnerId(str);
        if (StringUtils.isBlank(ownerId)) {
            return null;
        }
        return MessageFormat.format("/users/{0}/preferences/{1}", ownerId, str2);
    }

    public String getPreferenceForOwnerURL(String str, String str2) {
        if (StringUtils.isBlank(str2)) {
            return null;
        }
        String ownerId = getOwnerId(str);
        if (StringUtils.isBlank(ownerId)) {
            return null;
        }
        return MessageFormat.format("/contacts/users/{0}/preferences/{1}", ownerId, str2);
    }

    public String setPreferenceForOwnerURL(String str) {
        String ownerId = getOwnerId(str);
        if (StringUtils.isBlank(ownerId)) {
            return null;
        }
        return MessageFormat.format("/contacts/users/{0}/preferences", ownerId);
    }

    private String getOwnerId(String str) {
        String commsId = getCommsId();
        if (StringUtils.isBlank(str) && StringUtils.isBlank(commsId)) {
            return null;
        }
        return StringUtils.isBlank(str) ? commsId : str;
    }

    private String getOwnerHgId(String str) {
        String homeGroupId = getHomeGroupId();
        if (StringUtils.isBlank(str) && StringUtils.isBlank(homeGroupId)) {
            return null;
        }
        return StringUtils.isBlank(str) ? homeGroupId : str;
    }

    public String getCommsId() {
        AlexaCommsCoreIdentityService alexaCommsCoreIdentityService = this.mCommsIdentityManager;
        if (alexaCommsCoreIdentityService == null || alexaCommsCoreIdentityService.getIdentity() == null) {
            return null;
        }
        return this.mCommsIdentityManager.getIdentity().getCommsId();
    }

    public String getHomeGroupId() {
        AlexaCommsCoreIdentityService alexaCommsCoreIdentityService = this.mCommsIdentityManager;
        if (alexaCommsCoreIdentityService == null || alexaCommsCoreIdentityService.getIdentity() == null) {
            return null;
        }
        return this.mCommsIdentityManager.getIdentity().getHomegroupId();
    }
}
