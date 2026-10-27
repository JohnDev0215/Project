package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

/* JADX INFO: loaded from: classes3.dex */
public enum Visibility implements ModifierContributor.ForType, ModifierContributor.ForMethod, ModifierContributor.ForField {
    PUBLIC(1),
    PACKAGE_PRIVATE(0),
    PROTECTED(4),
    PRIVATE(2);

    private final int mask;

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public int getRange() {
        return 7;
    }

    Visibility(int i) {
        this.mask = i;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public int getMask() {
        return this.mask;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public boolean isDefault() {
        return this == PACKAGE_PRIVATE;
    }

    public boolean isPublic() {
        return (this.mask & 1) != 0;
    }

    public boolean isProtected() {
        return (this.mask & 4) != 0;
    }

    public boolean isPackagePrivate() {
        return (isPublic() || isPrivate() || isProtected()) ? false : true;
    }

    public boolean isPrivate() {
        return (this.mask & 2) != 0;
    }

    /* JADX INFO: renamed from: net.bytebuddy.description.modifier.Visibility$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$bytebuddy$description$modifier$Visibility;

        static {
            int[] iArr = new int[Visibility.values().length];
            $SwitchMap$net$bytebuddy$description$modifier$Visibility = iArr;
            try {
                iArr[Visibility.PUBLIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$bytebuddy$description$modifier$Visibility[Visibility.PROTECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$bytebuddy$description$modifier$Visibility[Visibility.PACKAGE_PRIVATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$net$bytebuddy$description$modifier$Visibility[Visibility.PRIVATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public Visibility expandTo(Visibility visibility) {
        int i = AnonymousClass1.$SwitchMap$net$bytebuddy$description$modifier$Visibility[visibility.ordinal()];
        if (i == 1) {
            return PUBLIC;
        }
        if (i == 2) {
            Visibility visibility2 = PUBLIC;
            return this == visibility2 ? visibility2 : visibility;
        }
        if (i == 3) {
            return this == PRIVATE ? PACKAGE_PRIVATE : this;
        }
        if (i == 4) {
            return this;
        }
        throw new IllegalStateException("Unexpected visibility: " + visibility);
    }
}
