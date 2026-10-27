package androidx.navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class NavArgument {

    @Nullable
    private final Object mDefaultValue;
    private final boolean mDefaultValuePresent;
    private final boolean mIsNullable;

    @NonNull
    private final NavType mType;

    NavArgument(@NonNull NavType<?> navType, boolean z, @Nullable Object obj, boolean z2) {
        if (!navType.isNullableAllowed() && z) {
            throw new IllegalArgumentException(navType.getName() + " does not allow nullable values");
        }
        if (!z && z2 && obj == null) {
            throw new IllegalArgumentException("Argument with type " + navType.getName() + " has null value but is not nullable.");
        }
        this.mType = navType;
        this.mIsNullable = z;
        this.mDefaultValue = obj;
        this.mDefaultValuePresent = z2;
    }

    public boolean isDefaultValuePresent() {
        return this.mDefaultValuePresent;
    }

    @NonNull
    public NavType<?> getType() {
        return this.mType;
    }

    public boolean isNullable() {
        return this.mIsNullable;
    }

    @Nullable
    public Object getDefaultValue() {
        return this.mDefaultValue;
    }

    void putDefaultValue(@NonNull String str, @NonNull Bundle bundle) {
        if (this.mDefaultValuePresent) {
            this.mType.put(bundle, str, this.mDefaultValue);
        }
    }

    boolean verify(@NonNull String str, @NonNull Bundle bundle) {
        if (!this.mIsNullable && bundle.containsKey(str) && bundle.get(str) == null) {
            return false;
        }
        try {
            this.mType.get(bundle, str);
            return true;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NavArgument.class != obj.getClass()) {
            return false;
        }
        NavArgument navArgument = (NavArgument) obj;
        if (this.mIsNullable != navArgument.mIsNullable || this.mDefaultValuePresent != navArgument.mDefaultValuePresent || !this.mType.equals(navArgument.mType)) {
            return false;
        }
        Object obj2 = this.mDefaultValue;
        return obj2 != null ? obj2.equals(navArgument.mDefaultValue) : navArgument.mDefaultValue == null;
    }

    public int hashCode() {
        int iHashCode = ((((this.mType.hashCode() * 31) + (this.mIsNullable ? 1 : 0)) * 31) + (this.mDefaultValuePresent ? 1 : 0)) * 31;
        Object obj = this.mDefaultValue;
        return iHashCode + (obj != null ? obj.hashCode() : 0);
    }

    public static final class Builder {

        @Nullable
        private Object mDefaultValue;

        @Nullable
        private NavType<?> mType;
        private boolean mIsNullable = false;
        private boolean mDefaultValuePresent = false;

        @NonNull
        public Builder setType(@NonNull NavType<?> navType) {
            this.mType = navType;
            return this;
        }

        @NonNull
        public Builder setIsNullable(boolean z) {
            this.mIsNullable = z;
            return this;
        }

        @NonNull
        public Builder setDefaultValue(@Nullable Object obj) {
            this.mDefaultValue = obj;
            this.mDefaultValuePresent = true;
            return this;
        }

        @NonNull
        public NavArgument build() {
            if (this.mType == null) {
                this.mType = NavType.inferFromValueType(this.mDefaultValue);
            }
            return new NavArgument(this.mType, this.mIsNullable, this.mDefaultValue, this.mDefaultValuePresent);
        }
    }
}
