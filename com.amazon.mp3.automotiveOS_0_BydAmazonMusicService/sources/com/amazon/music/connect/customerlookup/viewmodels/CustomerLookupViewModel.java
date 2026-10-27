package com.amazon.music.connect.customerlookup.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.amazon.music.connect.IdentityProvider;
import com.amazon.music.connect.customerlookup.CustomerLookupEvent;
import com.amazon.music.connect.customerlookup.CustomerLookupResult;
import com.amazon.music.connect.customerlookup.service.CustomerLookupService;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CustomerLookupViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010\tJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\bJ\u000e\u0010\u001f\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\bJ\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00130\fJ\u0012\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\r0\fJ\u0012\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\r0\fJ\u0016\u0010#\u001a\u0012\u0012\u0004\u0012\u00020\u00150$j\b\u0012\u0004\u0012\u00020\u0015`%J\u001a\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020(2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\bR\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/music/connect/customerlookup/viewmodels/CustomerLookupViewModel;", "Landroidx/lifecycle/ViewModel;", "identityProvider", "Lcom/amazon/music/connect/IdentityProvider;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "endpoints", "", "", "(Lcom/amazon/music/connect/IdentityProvider;Lcom/amazon/music/views/library/styles/StyleSheet;Ljava/util/Map;)V", "TAG", "customerSuggestions", "Landroidx/lifecycle/LiveData;", "", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "lookupEventLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/connect/customerlookup/CustomerLookupEvent;", "lookupResults", "Lcom/amazon/music/connect/customerlookup/CustomerLookupResult;", "lookupService", "Lcom/amazon/music/connect/customerlookup/service/CustomerLookupService;", "results", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "suggestions", "fetchCustomerResults", "", "input", "fetchCustomerSuggestions", "getCustomerLookupEventLiveData", "getCustomerSuggestions", "getLookupResults", "getLookupResultsValue", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "postEvent", "eventType", "Lcom/amazon/music/connect/customerlookup/CustomerLookupEvent$FragmentAction;", "profileId", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerLookupViewModel extends ViewModel {
    private final String TAG;
    private LiveData<List<String>> customerSuggestions;
    private final Map<String, String> endpoints;
    private final IdentityProvider identityProvider;
    private final Logger logger;
    private MutableLiveData<CustomerLookupEvent> lookupEventLiveData;
    private LiveData<List<CustomerLookupResult>> lookupResults;
    private CustomerLookupService lookupService;
    private List<CustomerLookupResult> results;
    private final StyleSheet styleSheet;
    private List<String> suggestions;

    public CustomerLookupViewModel(IdentityProvider identityProvider, StyleSheet styleSheet, Map<String, String> endpoints) {
        Intrinsics.checkNotNullParameter(identityProvider, "identityProvider");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(endpoints, "endpoints");
        this.identityProvider = identityProvider;
        this.styleSheet = styleSheet;
        this.endpoints = endpoints;
        String simpleName = Reflection.getOrCreateKotlinClass(CustomerLookupViewModel.class).getSimpleName();
        this.TAG = simpleName;
        this.logger = LoggerFactory.getLogger(simpleName);
        this.lookupService = new CustomerLookupService(identityProvider, endpoints);
        this.lookupEventLiveData = new MutableLiveData<>();
        this.lookupResults = new MutableLiveData();
        this.customerSuggestions = new MutableLiveData();
        this.suggestions = new ArrayList();
        this.results = new ArrayList();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    /* JADX INFO: renamed from: com.amazon.music.connect.customerlookup.viewmodels.CustomerLookupViewModel$fetchCustomerResults$1, reason: invalid class name */
    /* JADX INFO: compiled from: CustomerLookupViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.connect.customerlookup.viewmodels.CustomerLookupViewModel$fetchCustomerResults$1", f = "CustomerLookupViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $input;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$input = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CustomerLookupViewModel.this.new AnonymousClass1(this.$input, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CustomerLookupViewModel customerLookupViewModel = CustomerLookupViewModel.this;
            customerLookupViewModel.results = customerLookupViewModel.lookupService.getCustomerResults(this.$input);
            ((MutableLiveData) CustomerLookupViewModel.this.lookupResults).postValue(CustomerLookupViewModel.this.results);
            return Unit.INSTANCE;
        }
    }

    public final void fetchCustomerResults(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(input, null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.music.connect.customerlookup.viewmodels.CustomerLookupViewModel$fetchCustomerSuggestions$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CustomerLookupViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.connect.customerlookup.viewmodels.CustomerLookupViewModel$fetchCustomerSuggestions$1", f = "CustomerLookupViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04261 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $input;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04261(String str, Continuation<? super C04261> continuation) {
            super(2, continuation);
            this.$input = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CustomerLookupViewModel.this.new C04261(this.$input, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04261) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CustomerLookupViewModel customerLookupViewModel = CustomerLookupViewModel.this;
            customerLookupViewModel.suggestions = customerLookupViewModel.lookupService.getCustomerSuggestions(this.$input);
            ((MutableLiveData) CustomerLookupViewModel.this.customerSuggestions).postValue(CustomerLookupViewModel.this.suggestions);
            return Unit.INSTANCE;
        }
    }

    public final void fetchCustomerSuggestions(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C04261(input, null), 3, null);
    }

    public final LiveData<CustomerLookupEvent> getCustomerLookupEventLiveData() {
        return this.lookupEventLiveData;
    }

    public static /* synthetic */ void postEvent$default(CustomerLookupViewModel customerLookupViewModel, CustomerLookupEvent.FragmentAction fragmentAction, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        customerLookupViewModel.postEvent(fragmentAction, str);
    }

    public final void postEvent(CustomerLookupEvent.FragmentAction eventType, String profileId) {
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        this.lookupEventLiveData.postValue(new CustomerLookupEvent(eventType, profileId));
    }

    public final LiveData<List<CustomerLookupResult>> getLookupResults() {
        return this.lookupResults;
    }

    public final LiveData<List<String>> getCustomerSuggestions() {
        return this.customerSuggestions;
    }

    public final ArrayList<CustomerLookupResult> getLookupResultsValue() {
        if (this.lookupResults.getValue() != null) {
            List<CustomerLookupResult> value = this.lookupResults.getValue();
            if (value != null) {
                return (ArrayList) value;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<com.amazon.music.connect.customerlookup.CustomerLookupResult>");
        }
        return new ArrayList<>();
    }
}
