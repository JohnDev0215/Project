package com.amazon.mp3.net.cirrus;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.util.Log;
import org.json.JSONObject;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'ListStreamingDevices' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes3.dex */
public final class LibraryRequest implements CirrusRequest {
    private static final /* synthetic */ LibraryRequest[] $VALUES;
    public static final LibraryRequest GenerateNonce;
    public static final LibraryRequest ListStreamingDevices;
    private static final boolean RETRY_ON_ACCOUNT_NOT_FOUND = true;
    public static final LibraryRequest RevokeStreamingDevice;
    public static final String TAG;
    private static volatile CirrusRequestDispatcher sBluemoonDispatcher;
    private static volatile CirrusRequestDispatcher sDispatcher;
    private CirrusRequest mDelegate;

    public static LibraryRequest valueOf(String name) {
        return (LibraryRequest) Enum.valueOf(LibraryRequest.class, name);
    }

    public static LibraryRequest[] values() {
        return (LibraryRequest[]) $VALUES.clone();
    }

    static {
        CirrusApi cirrusApi = CirrusApi.Version2;
        LibraryRequest libraryRequest = new LibraryRequest("ListStreamingDevices", 0, "listStreamingDevices", cirrusApi);
        ListStreamingDevices = libraryRequest;
        LibraryRequest libraryRequest2 = new LibraryRequest("RevokeStreamingDevice", 1, "revokeStreamingDevice", cirrusApi);
        RevokeStreamingDevice = libraryRequest2;
        LibraryRequest libraryRequest3 = new LibraryRequest("GenerateNonce", 2, "generateNonce", cirrusApi);
        GenerateNonce = libraryRequest3;
        $VALUES = new LibraryRequest[]{libraryRequest, libraryRequest2, libraryRequest3};
        TAG = LibraryRequest.class.getSimpleName();
    }

    private static CirrusRequestDispatcher getLibraryDispatcher() {
        if (sDispatcher == null) {
            sDispatcher = new LibraryRequestDispatcher(false);
        }
        return sDispatcher;
    }

    private static CirrusRequestDispatcher getBluemoonLibraryDispatcher() {
        if (sBluemoonDispatcher == null) {
            sBluemoonDispatcher = new LibraryRequestDispatcher(true);
        }
        return sBluemoonDispatcher;
    }

    private LibraryRequest(String $enum$name, int $enum$ordinal, final String name, final CirrusApi apiVersion) {
        this($enum$name, $enum$ordinal, name, apiVersion, false);
    }

    private LibraryRequest(String $enum$name, int $enum$ordinal, final String name, final CirrusApi apiVersion, boolean ignoreBluemoonExceptions) {
        this.mDelegate = new CirrusRequestDelegate(name, apiVersion, ignoreBluemoonExceptions ? getBluemoonLibraryDispatcher() : getLibraryDispatcher());
    }

    @Override // com.amazon.mp3.net.cirrus.CirrusRequest, com.amazon.mp3.net.service.ServiceRequest
    public String getRequestName() {
        return this.mDelegate.getRequestName();
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public CirrusApi getApiVersion() {
        return this.mDelegate.getApiVersion();
    }

    @Override // com.amazon.mp3.net.cirrus.CirrusRequest
    public CirrusRequestDispatcher getDispatcher() {
        return this.mDelegate.getDispatcher();
    }

    @Override // com.amazon.mp3.net.cirrus.CirrusRequest
    public JSONObject execute() throws AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException, CirrusExceptions.CirrusException {
        return this.mDelegate.execute();
    }

    @Override // com.amazon.mp3.net.cirrus.CirrusRequest, com.amazon.mp3.net.service.ServiceRequest
    public JSONObject execute(final JSONObject requestParams) throws AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException, CirrusExceptions.CirrusException {
        return doExecute(requestParams, true);
    }

    private JSONObject doExecute(final JSONObject requestParams, boolean retry) throws AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException, CirrusExceptions.CirrusException {
        try {
            return this.mDelegate.execute(requestParams);
        } catch (CirrusExceptions.AccountNotFoundException e) {
            String str = TAG;
            Log.debug(str, "%s attempt failed: AccountNotFoundException", getRequestName());
            if (retry && isDiscoverCustomerHomeUpdated()) {
                Log.debug(str, "Cirrus end point identified. %s will retry...", getRequestName());
                return doExecute(requestParams, false);
            }
            Log.debug(str, retry ? "%s retry aborded because Cloud player account does not exist" : "%s retry aborded because retry limit reached", getRequestName());
            throw e;
        }
    }

    private boolean isDiscoverCustomerHomeUpdated() {
        try {
            Log.debug(TAG, "Cirrus end point discovery required before retrying.");
            return AccountRequestUtil.getCustomerHome(AmazonApplication.getContext()).getCloudPlayerAccountExists();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
