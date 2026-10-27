package com.amazon.music.identity.profiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.music.identity.profiles.view.util.NavigationHelper;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: ProfileIdentityMainActivity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u0014\u0010\u0005\u001a\u00020\u00042\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u0006\u001a\u00020\u0004H\u0014R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/identity/profiles/ProfileIdentityMainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/os/Bundle;", "savedInstanceState", "", "onCreate", "onDestroy", "Lcom/amazon/music/views/library/styles/StyleSheet;", "mStyleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getMStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setMStyleSheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Lrx/Subscription;", "styleSheetSubscription", "Lrx/Subscription;", "<init>", "()V", "Companion", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0})
public final class ProfileIdentityMainActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private StyleSheet mStyleSheet;
    private Subscription styleSheetSubscription;

    public final StyleSheet getMStyleSheet() {
        return this.mStyleSheet;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            setContentView(R$layout.profile_identity_main_activity);
            Observable<StyleSheet> styleSheetObservable = DataProvider.INSTANCE.getStyleSheetObservable();
            this.styleSheetSubscription = styleSheetObservable == null ? null : styleSheetObservable.subscribe(new Action1() { // from class: com.amazon.music.identity.profiles.ProfileIdentityMainActivity$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    ProfileIdentityMainActivity.m2125onCreate$lambda0(this.f$0, (StyleSheet) obj);
                }
            });
            String stringExtra = getIntent().getStringExtra("navigation_tag");
            if (stringExtra == null) {
                return;
            }
            new NavigationHelper(this).navigateTo(R$id.profile_fragment_container, stringExtra);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m2125onCreate$lambda0(ProfileIdentityMainActivity this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mStyleSheet = styleSheet;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Subscription subscription = this.styleSheetSubscription;
        if (subscription == null) {
            return;
        }
        subscription.unsubscribe();
    }

    /* JADX INFO: compiled from: ProfileIdentityMainActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/music/identity/profiles/ProfileIdentityMainActivity$Companion;", "", "()V", "getStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "navigateTo", "", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Intent getStartIntent(Context context, String navigateTo) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(navigateTo, "navigateTo");
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClass(context, ProfileIdentityMainActivity.class);
            intent.addFlags(131072);
            intent.putExtra("navigation_tag", navigateTo);
            return intent;
        }
    }
}
