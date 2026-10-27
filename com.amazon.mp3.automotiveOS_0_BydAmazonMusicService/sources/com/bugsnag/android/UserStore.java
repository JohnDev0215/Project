package com.bugsnag.android;

import android.util.JsonReader;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.bugsnag.android.StateEvent;
import com.bugsnag.android.User;
import com.bugsnag.android.internal.ImmutableConfig;
import com.bugsnag.android.internal.StateObserver;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX INFO: compiled from: UserStore.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B3\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0011J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0011H\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0011J\u0010\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/bugsnag/android/UserStore;", "", "config", "Lcom/bugsnag/android/internal/ImmutableConfig;", Splash.PARAMS_DEVICE_ID, "", "file", "Ljava/io/File;", "sharedPrefMigrator", "Lcom/bugsnag/android/SharedPrefMigrator;", "logger", "Lcom/bugsnag/android/Logger;", "(Lcom/bugsnag/android/internal/ImmutableConfig;Ljava/lang/String;Ljava/io/File;Lcom/bugsnag/android/SharedPrefMigrator;Lcom/bugsnag/android/Logger;)V", "persist", "", "previousUser", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/bugsnag/android/User;", "synchronizedStreamableStore", "Lcom/bugsnag/android/SynchronizedStreamableStore;", "load", "Lcom/bugsnag/android/UserState;", "initialUser", "loadPersistedUser", "save", "", "user", "validUser", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class UserStore {
    private final ImmutableConfig config;
    private final String deviceId;
    private final Logger logger;
    private final boolean persist;
    private final AtomicReference<User> previousUser;
    private final SharedPrefMigrator sharedPrefMigrator;
    private final SynchronizedStreamableStore<User> synchronizedStreamableStore;

    @JvmOverloads
    public UserStore(ImmutableConfig immutableConfig, String str, File file, SharedPrefMigrator sharedPrefMigrator, Logger logger) {
        this.config = immutableConfig;
        this.deviceId = str;
        this.sharedPrefMigrator = sharedPrefMigrator;
        this.logger = logger;
        this.persist = immutableConfig.getPersistUser();
        this.previousUser = new AtomicReference<>(null);
        try {
            file.createNewFile();
        } catch (IOException e) {
            this.logger.w("Failed to created device ID file", e);
        }
        this.synchronizedStreamableStore = new SynchronizedStreamableStore<>(file);
    }

    public /* synthetic */ UserStore(ImmutableConfig immutableConfig, String str, File file, SharedPrefMigrator sharedPrefMigrator, Logger logger, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(immutableConfig, str, (i & 4) != 0 ? new File(immutableConfig.getPersistenceDirectory().getValue(), "user-info") : file, sharedPrefMigrator, logger);
    }

    public final UserState load(User initialUser) {
        if (!validUser(initialUser)) {
            initialUser = this.persist ? loadPersistedUser() : null;
        }
        UserState userState = (initialUser == null || !validUser(initialUser)) ? new UserState(new User(this.deviceId, null, null)) : new UserState(initialUser);
        userState.addObserver(new StateObserver() { // from class: com.bugsnag.android.UserStore.load.1
            @Override // com.bugsnag.android.internal.StateObserver
            public final void onStateChange(StateEvent stateEvent) {
                if (stateEvent instanceof StateEvent.UpdateUser) {
                    UserStore.this.save(((StateEvent.UpdateUser) stateEvent).user);
                }
            }
        });
        return userState;
    }

    public final void save(User user) {
        if (this.persist && (!kotlin.jvm.internal.Intrinsics.areEqual(user, this.previousUser.getAndSet(user)))) {
            try {
                this.synchronizedStreamableStore.persist(user);
            } catch (Exception e) {
                this.logger.w("Failed to persist user info", e);
            }
        }
    }

    private final boolean validUser(User user) {
        return (user.getId() == null && user.getName() == null && user.getEmail() == null) ? false : true;
    }

    private final User loadPersistedUser() {
        if (this.sharedPrefMigrator.hasPrefs()) {
            User userLoadUser = this.sharedPrefMigrator.loadUser(this.deviceId);
            save(userLoadUser);
            return userLoadUser;
        }
        try {
            return (User) this.synchronizedStreamableStore.load(new C04631(User.INSTANCE));
        } catch (Exception e) {
            this.logger.w("Failed to load user info", e);
            return null;
        }
    }

    /* JADX INFO: renamed from: com.bugsnag.android.UserStore$loadPersistedUser$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UserStore.kt */
    @kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/bugsnag/android/User;", "p1", "Landroid/util/JsonReader;", "invoke"}, k = 3, mv = {1, 4, 2})
    static final /* synthetic */ class C04631 extends FunctionReference implements Function1<JsonReader, User> {
        C04631(User.Companion companion) {
            super(1, companion);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "fromReader";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(User.Companion.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "fromReader(Landroid/util/JsonReader;)Lcom/bugsnag/android/User;";
        }

        @Override // kotlin.jvm.functions.Function1
        public final User invoke(JsonReader jsonReader) {
            return ((User.Companion) this.receiver).fromReader(jsonReader);
        }
    }
}
