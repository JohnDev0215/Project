package com.amazon.music.views.library.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EmptyStateModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "Lcom/amazon/music/views/library/models/EmptyStateButtonProvider;", "()V", "ADDSONGS", "BUY", "EXPLORE", "RESET", "Lcom/amazon/music/views/library/models/EmptyStateButtonType$EXPLORE;", "Lcom/amazon/music/views/library/models/EmptyStateButtonType$RESET;", "Lcom/amazon/music/views/library/models/EmptyStateButtonType$BUY;", "Lcom/amazon/music/views/library/models/EmptyStateButtonType$ADDSONGS;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class EmptyStateButtonType implements EmptyStateButtonProvider {
    public /* synthetic */ EmptyStateButtonType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: EmptyStateModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/views/library/models/EmptyStateButtonType$EXPLORE;", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "", "buttonString", "Ljava/lang/String;", "getButtonString", "()Ljava/lang/String;", "getDisplayString", "displayString", "<init>", "(Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
    public static final class EXPLORE extends EmptyStateButtonType {
        private final String buttonString;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EXPLORE(String buttonString) {
            super(null);
            Intrinsics.checkNotNullParameter(buttonString, "buttonString");
            this.buttonString = buttonString;
        }

        @Override // com.amazon.music.views.library.models.EmptyStateButtonProvider
        /* JADX INFO: renamed from: getDisplayString, reason: from getter */
        public String getButtonString() {
            return this.buttonString;
        }
    }

    private EmptyStateButtonType() {
    }

    /* JADX INFO: compiled from: EmptyStateModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/views/library/models/EmptyStateButtonType$RESET;", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "", "buttonString", "Ljava/lang/String;", "getButtonString", "()Ljava/lang/String;", "getDisplayString", "displayString", "<init>", "(Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
    public static final class RESET extends EmptyStateButtonType {
        private final String buttonString;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RESET(String buttonString) {
            super(null);
            Intrinsics.checkNotNullParameter(buttonString, "buttonString");
            this.buttonString = buttonString;
        }

        @Override // com.amazon.music.views.library.models.EmptyStateButtonProvider
        /* JADX INFO: renamed from: getDisplayString, reason: from getter */
        public String getButtonString() {
            return this.buttonString;
        }
    }

    /* JADX INFO: compiled from: EmptyStateModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/views/library/models/EmptyStateButtonType$BUY;", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "", "buttonString", "Ljava/lang/String;", "getButtonString", "()Ljava/lang/String;", "getDisplayString", "displayString", "<init>", "(Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
    public static final class BUY extends EmptyStateButtonType {
        private final String buttonString;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BUY(String buttonString) {
            super(null);
            Intrinsics.checkNotNullParameter(buttonString, "buttonString");
            this.buttonString = buttonString;
        }

        @Override // com.amazon.music.views.library.models.EmptyStateButtonProvider
        /* JADX INFO: renamed from: getDisplayString, reason: from getter */
        public String getButtonString() {
            return this.buttonString;
        }
    }

    /* JADX INFO: compiled from: EmptyStateModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/views/library/models/EmptyStateButtonType$ADDSONGS;", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "", "buttonString", "Ljava/lang/String;", "getButtonString", "()Ljava/lang/String;", "getDisplayString", "displayString", "<init>", "(Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
    public static final class ADDSONGS extends EmptyStateButtonType {
        private final String buttonString;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ADDSONGS(String buttonString) {
            super(null);
            Intrinsics.checkNotNullParameter(buttonString, "buttonString");
            this.buttonString = buttonString;
        }

        @Override // com.amazon.music.views.library.models.EmptyStateButtonProvider
        /* JADX INFO: renamed from: getDisplayString, reason: from getter */
        public String getButtonString() {
            return this.buttonString;
        }
    }
}
