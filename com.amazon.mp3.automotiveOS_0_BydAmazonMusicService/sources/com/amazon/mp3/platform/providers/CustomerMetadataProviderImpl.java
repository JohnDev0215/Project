package com.amazon.mp3.platform.providers;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.music.authentication.AuthDataType;
import com.amazon.music.authentication.AuthInfo;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.platform.data.CustomerMetadata;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CustomerMetadataProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u001c\u0010\u000b\u001a\n \n*\u0004\u0018\u00010\t0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\u0010\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/platform/providers/CustomerMetadataProviderImpl;", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lkotlin/Function0;", "Lcom/amazon/music/authentication/AuthInfo;", "authInfoProvider", "Lkotlin/jvm/functions/Function0;", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "Lcom/amazon/music/platform/data/CustomerMetadata;", "getCustomerMetadata", "()Lcom/amazon/music/platform/data/CustomerMetadata;", "customerMetadata", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CustomerMetadataProviderImpl implements CustomerMetadataProvider {
    private final Function0<AuthInfo> authInfoProvider;
    private final Context context;
    private final Logger logger;

    /* JADX WARN: Multi-variable type inference failed */
    public CustomerMetadataProviderImpl(Context context, Function0<? extends AuthInfo> authInfoProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(authInfoProvider, "authInfoProvider");
        this.context = context;
        this.authInfoProvider = authInfoProvider;
        this.logger = LoggerFactory.getLogger(CustomerMetadataProviderImpl.class.getSimpleName());
    }

    public /* synthetic */ CustomerMetadataProviderImpl(Context context, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new Function0<AuthInfo>() { // from class: com.amazon.mp3.platform.providers.CustomerMetadataProviderImpl.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AuthInfo invoke() {
                AuthInfo authInfoMAPR5 = AmazonApplication.getAuthInfoMAPR5();
                Intrinsics.checkNotNullExpressionValue(authInfoMAPR5, "getAuthInfoMAPR5()");
                return authInfoMAPR5;
            }
        } : function0);
    }

    @Override // com.amazon.music.platform.providers.CustomerMetadataProvider
    public CustomerMetadata getCustomerMetadata() throws AuthenticationException {
        String username;
        String strFind;
        String str;
        String strFind2;
        AuthInfo authInfoInvoke;
        String str2 = "";
        try {
            String firstName = AccountCredentialUtil.get(this.context).getFirstName();
            Intrinsics.checkNotNullExpressionValue(firstName, "get(context).firstName");
            try {
                username = AccountCredentialStorage.get(this.context).getUsername();
                Intrinsics.checkNotNullExpressionValue(username, "get(context).username");
            } catch (NullPointerException unused) {
                username = "";
            }
            try {
                authInfoInvoke = this.authInfoProvider.invoke();
                strFind = authInfoInvoke.find(AuthDataType.DIRECTED_ID);
            } catch (NullPointerException unused2) {
                strFind = null;
                str2 = firstName;
                this.logger.error("Failed to retrieve CustomerMetadata");
                str = str2;
                strFind2 = null;
                return new CustomerMetadata(str, username, strFind, strFind2, AccountDetailUtil.getMusicTerritoryOfResidence());
            }
            try {
                strFind2 = authInfoInvoke.find(AuthDataType.SESSION_ID);
                str = firstName;
            } catch (NullPointerException unused3) {
                str2 = firstName;
                this.logger.error("Failed to retrieve CustomerMetadata");
                str = str2;
                strFind2 = null;
            }
        } catch (NullPointerException unused4) {
            username = "";
            strFind = null;
        }
        return new CustomerMetadata(str, username, strFind, strFind2, AccountDetailUtil.getMusicTerritoryOfResidence());
    }
}
