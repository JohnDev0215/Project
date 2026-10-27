package com.amazon.music.connect.contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.music.connect.R$id;
import com.amazon.music.connect.R$layout;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.router.UnknownDestinationException;
import com.google.firebase.perf.plugin.util.AsmString;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: ContactImportWebViewActivity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000bH\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000bH\u0002J\u0012\u0010\u0015\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0010H\u0014J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J:\u0010\u001d\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\u00042\b\u0010 \u001a\u0004\u0018\u00010\u00042\b\u0010!\u001a\u0004\u0018\u00010\u00042\b\u0010\"\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010#\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R2\u0010\t\u001a&\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b \f*\u0012\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/music/connect/contacts/ContactImportWebViewActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "BROWSE_HOME_DEEPLINK", "", "TAG", "TAG_WEB_FRAGMENT", "fragment", "Lcom/amazon/music/connect/contacts/ContactImportWebViewFragment;", "webEventPublisher", "Lrx/subjects/PublishSubject;", "Lcom/amazon/music/connect/contacts/ContactImportEvent;", "kotlin.jvm.PlatformType", "webEventSubscription", "Lrx/Subscription;", "handleError", "", "handleWebEvent", "webEvent", "init", "onContactImportCompletion", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "onReceivedIntent", "setupNewFragment", "customerId", "musicDomain", Splash.PARAMS_MUSIC_TERRITORY, "refMarker", "path", "subscribeEvents", "Companion", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContactImportWebViewActivity extends AppCompatActivity {
    private final String BROWSE_HOME_DEEPLINK;
    private final String TAG;
    private final String TAG_WEB_FRAGMENT;
    private ContactImportWebViewFragment fragment;
    private final PublishSubject<ContactImportEvent> webEventPublisher;
    private Subscription webEventSubscription;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String CUSTOMER_COMPLETED_CONTACT_IMPORT = "CUSTOMER_COMPLETED_CONTACT_IMPORT";
    private static final String CONTACT_IMPORT_LOAD_TIMED_OUT = "CONTACT_IMPORT_LOAD_TIMED_OUT";
    private static final String CONTACT_IMPORT_ON_RETRY_CONNECTION = "CONTACT_IMPORT_ON_RETRY_CONNECTION";
    private static final String CONTACT_IMPORT_ON_DIALOG_DISMISS = "CONTACT_IMPORT_ON_DIALOG_DISMISS";
    private static final String CONTACT_IMPORT_ON_CONNECTION_ERROR_DIALOG_CANCEL = "CONTACT_IMPORT_ON_CONNECTION_ERROR_DIALOG_CANCEL";
    private static final String CONTACT_IMPORT_ON_ERROR = "CONTACT_IMPORT_ON_ERROR";
    private static final int RESULT_ERROR = 2;
    private static final String EXTRA_REF_MARKER = "com.amazon.mp3.activity.EXTRA_REF_MARKER";
    private static final String EXTRA_PATH = "com.amazon.mp3.activity.EXTRA_PATH";
    private static final String EXTRA_MUSIC_DOMAIN = "com.amazon.mp3.activity.EXTRA_MUSIC_DOMAIN";
    private static final String EXTRA_CUSTOMER_ID = "com.amazon.mp3.activity.EXTRA_CUSTOMER_ID";
    private static final String EXTRA_MUSIC_TERRITORY = "com.amazon.mp3.activity.EXTRA_MUSIC_TERRITORY";

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeEvents$lambda-1, reason: not valid java name */
    public static final void m1893subscribeEvents$lambda1(Throwable th) {
    }

    public ContactImportWebViewActivity() {
        String simpleName = ContactImportWebViewActivity.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "ContactImportWebViewActi…lass.java.getSimpleName()");
        this.TAG = simpleName;
        this.BROWSE_HOME_DEEPLINK = "/";
        this.TAG_WEB_FRAGMENT = "ContactImportWebViewActivity.TAG_WEB_FRAGMENT";
        this.webEventPublisher = PublishSubject.create();
    }

    /* JADX INFO: compiled from: ContactImportWebViewActivity.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ:\u0010\u000b\u001a\u00020\n2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0004R\u001a\u0010\f\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0012\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000fR\u001a\u0010\u0015\u001a\u00020\u00148\u0006X\u0086D¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0019\u0010\rR\u0014\u0010\u001a\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001a\u0010\rR\u0014\u0010\u001b\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001b\u0010\rR\u0014\u0010\u001c\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001c\u0010\rR\u0014\u0010\u001d\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001d\u0010\r¨\u0006 "}, d2 = {"Lcom/amazon/music/connect/contacts/ContactImportWebViewActivity$Companion;", "", "Landroid/content/Context;", "context", "", "customerId", "musicDomain", Splash.PARAMS_MUSIC_TERRITORY, "path", "ref", "Landroid/content/Intent;", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "CUSTOMER_COMPLETED_CONTACT_IMPORT", "Ljava/lang/String;", "getCUSTOMER_COMPLETED_CONTACT_IMPORT", "()Ljava/lang/String;", "CONTACT_IMPORT_LOAD_TIMED_OUT", "getCONTACT_IMPORT_LOAD_TIMED_OUT", "CONTACT_IMPORT_ON_ERROR", "getCONTACT_IMPORT_ON_ERROR", "", "RESULT_ERROR", "I", "getRESULT_ERROR", "()I", "EXTRA_CUSTOMER_ID", "EXTRA_MUSIC_DOMAIN", "EXTRA_MUSIC_TERRITORY", "EXTRA_PATH", "EXTRA_REF_MARKER", "<init>", "()V", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getCUSTOMER_COMPLETED_CONTACT_IMPORT() {
            return ContactImportWebViewActivity.CUSTOMER_COMPLETED_CONTACT_IMPORT;
        }

        public final String getCONTACT_IMPORT_LOAD_TIMED_OUT() {
            return ContactImportWebViewActivity.CONTACT_IMPORT_LOAD_TIMED_OUT;
        }

        public final String getCONTACT_IMPORT_ON_ERROR() {
            return ContactImportWebViewActivity.CONTACT_IMPORT_ON_ERROR;
        }

        public final int getRESULT_ERROR() {
            return ContactImportWebViewActivity.RESULT_ERROR;
        }

        public final Intent start(Context context, String customerId, String musicDomain, String musicTerritory, String path, String ref) {
            Intrinsics.checkNotNullParameter(customerId, "customerId");
            Intrinsics.checkNotNullParameter(musicDomain, "musicDomain");
            Intrinsics.checkNotNullParameter(musicTerritory, "musicTerritory");
            Intrinsics.checkNotNullParameter(path, "path");
            Intent intent = new Intent(context, (Class<?>) ContactImportWebViewActivity.class);
            intent.putExtra(ContactImportWebViewActivity.EXTRA_CUSTOMER_ID, customerId);
            intent.putExtra(ContactImportWebViewActivity.EXTRA_MUSIC_DOMAIN, musicDomain);
            intent.putExtra(ContactImportWebViewActivity.EXTRA_MUSIC_TERRITORY, musicTerritory);
            intent.putExtra(ContactImportWebViewActivity.EXTRA_PATH, path);
            intent.putExtra(ContactImportWebViewActivity.EXTRA_REF_MARKER, ref);
            return intent;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R$layout.contact_import_webview_layout);
        subscribeEvents();
        init();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        onReceivedIntent(intent);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Subscription subscription = this.webEventSubscription;
        if (subscription == null) {
            return;
        }
        subscription.unsubscribe();
    }

    private final void init() {
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        onReceivedIntent(intent);
    }

    private final void onReceivedIntent(Intent intent) {
        setupNewFragment(intent.getStringExtra(EXTRA_CUSTOMER_ID), intent.getStringExtra(EXTRA_MUSIC_DOMAIN), intent.getStringExtra(EXTRA_MUSIC_TERRITORY), intent.getStringExtra(EXTRA_REF_MARKER), intent.getStringExtra(EXTRA_PATH));
    }

    private final void setupNewFragment(String customerId, String musicDomain, String musicTerritory, String refMarker, String path) {
        PublishSubject<ContactImportEvent> webEventPublisher = this.webEventPublisher;
        Intrinsics.checkNotNullExpressionValue(webEventPublisher, "webEventPublisher");
        this.fragment = new ContactImportWebViewFragment(customerId, musicDomain, musicTerritory, path, refMarker, webEventPublisher);
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R$id.FragmentContainer;
        ContactImportWebViewFragment contactImportWebViewFragment = this.fragment;
        if (contactImportWebViewFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            contactImportWebViewFragment = null;
        }
        fragmentTransactionBeginTransaction.replace(i, contactImportWebViewFragment, this.TAG_WEB_FRAGMENT).commit();
    }

    private final void subscribeEvents() {
        this.webEventSubscription = this.webEventPublisher.subscribe(new Action1() { // from class: com.amazon.music.connect.contacts.ContactImportWebViewActivity$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
                ContactImportWebViewActivity.m1892subscribeEvents$lambda0(this.f$0, (ContactImportEvent) obj);
            }
        }, new Action1() { // from class: com.amazon.music.connect.contacts.ContactImportWebViewActivity$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ContactImportWebViewActivity.m1893subscribeEvents$lambda1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeEvents$lambda-0, reason: not valid java name */
    public static final void m1892subscribeEvents$lambda0(ContactImportWebViewActivity this$0, ContactImportEvent webEvent) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(webEvent, "webEvent");
        this$0.handleWebEvent(webEvent);
    }

    private final void handleWebEvent(ContactImportEvent webEvent) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        if (Intrinsics.areEqual(CUSTOMER_COMPLETED_CONTACT_IMPORT, webEvent.getId())) {
            onContactImportCompletion(webEvent);
            return;
        }
        if (Intrinsics.areEqual(CONTACT_IMPORT_ON_RETRY_CONNECTION, webEvent.getId())) {
            init();
        } else if (Intrinsics.areEqual(CONTACT_IMPORT_ON_DIALOG_DISMISS, webEvent.getId()) || Intrinsics.areEqual(CONTACT_IMPORT_ON_CONNECTION_ERROR_DIALOG_CANCEL, webEvent.getId()) || Intrinsics.areEqual(CONTACT_IMPORT_ON_ERROR, webEvent.getId()) || Intrinsics.areEqual(CONTACT_IMPORT_LOAD_TIMED_OUT, webEvent.getId())) {
            handleError();
        }
    }

    private final void handleError() {
        Log.w(this.TAG, "Received an error while loading Contact Import Webview");
        setResult(RESULT_ERROR);
        finish();
    }

    private final void onContactImportCompletion(ContactImportEvent webEvent) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        String completionUrl = webEvent.getCompletionUrl();
        if (completionUrl == null) {
            completionUrl = this.BROWSE_HOME_DEEPLINK;
        }
        DeeplinksManager.get(this).handle(Uri.parse(completionUrl));
        finish();
    }
}
