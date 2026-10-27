package com.dee.app.contacts.common.utils;

import com.dee.app.contacts.common.exceptions.ClientNotSupportedException;
import com.dee.app.contacts.interfaces.models.data.enums.ContactsOwnerPreferenceTarget;
import com.dee.app.contacts.interfaces.models.data.enums.PreferenceNameSpace;

/* JADX INFO: loaded from: classes5.dex */
public class PreferenceUtils {
    private final String appName;

    public PreferenceUtils(String str) {
        this.appName = str;
    }

    public PreferenceNameSpace getPreferenceNameSpaceForClient() throws ClientNotSupportedException {
        int i = AnonymousClass1.$SwitchMap$com$dee$app$contacts$common$utils$Client[Client.get(this.appName).ordinal()];
        if (i == 1) {
            return PreferenceNameSpace.PHOTOS;
        }
        if (i == 2) {
            return PreferenceNameSpace.MUSIC;
        }
        if (i == 3) {
            return PreferenceNameSpace.COMMS;
        }
        throw new ClientNotSupportedException(this.appName);
    }

    /* JADX INFO: renamed from: com.dee.app.contacts.common.utils.PreferenceUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$dee$app$contacts$common$utils$Client;

        static {
            int[] iArr = new int[Client.values().length];
            $SwitchMap$com$dee$app$contacts$common$utils$Client = iArr;
            try {
                iArr[Client.PhotosApp.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$dee$app$contacts$common$utils$Client[Client.MusicApp.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$dee$app$contacts$common$utils$Client[Client.AlexaApp.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public ContactsOwnerPreferenceTarget getContactsOwnerPreferenceTargetForClient() throws ClientNotSupportedException {
        int i = AnonymousClass1.$SwitchMap$com$dee$app$contacts$common$utils$Client[Client.get(this.appName).ordinal()];
        if (i == 1) {
            return ContactsOwnerPreferenceTarget.AMAZON_PHOTOS;
        }
        if (i == 2) {
            return ContactsOwnerPreferenceTarget.AMAZON_MUSIC;
        }
        if (i == 3) {
            return ContactsOwnerPreferenceTarget.ALEXA_COMMS;
        }
        throw new ClientNotSupportedException(this.appName);
    }
}
