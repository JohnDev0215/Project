package com.dee.app.contacts.common.utils;

import com.dee.app.contacts.common.exceptions.ClientNotSupportedException;
import com.dee.app.contacts.interfaces.models.data.enums.ContactSourceType;

/* JADX INFO: loaded from: classes5.dex */
public class SyncUtils {
    private final String appName;

    public SyncUtils(String str) {
        this.appName = str;
    }

    public Client getClient() {
        return Client.get(this.appName);
    }

    public Boolean is2pClient() {
        if (!this.appName.isEmpty() && Client.get(this.appName) != Client.AlexaApp) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public ContactSourceType getContactSourceTypeForClient() throws ClientNotSupportedException {
        int i = AnonymousClass1.$SwitchMap$com$dee$app$contacts$common$utils$Client[Client.get(this.appName).ordinal()];
        if (i == 1) {
            return ContactSourceType.AmazonPhotos;
        }
        if (i == 2) {
            return ContactSourceType.AmazonMusic;
        }
        throw new ClientNotSupportedException(this.appName);
    }

    /* JADX INFO: renamed from: com.dee.app.contacts.common.utils.SyncUtils$1, reason: invalid class name */
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
        }
    }
}
