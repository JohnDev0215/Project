package org.xwalk.core;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

/* JADX INFO: loaded from: classes4.dex */
class XWalkMixedResources extends Resources {
    private Resources mLibraryResource;

    private boolean isCalledInLibrary() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            if (className.startsWith("org.chromium") || className.startsWith("org.xwalk.core.internal")) {
                return true;
            }
            if (className.startsWith(XWalkLibraryInterface.XWALK_CORE_PACKAGE) && !className.endsWith("XWalkMixedResources")) {
                return false;
            }
        }
        return false;
    }

    XWalkMixedResources(Resources resources, Resources resources2) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.mLibraryResource = resources2;
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i) throws Resources.NotFoundException {
        boolean zIsCalledInLibrary = isCalledInLibrary();
        try {
            if (zIsCalledInLibrary) {
                return this.mLibraryResource.getText(i);
            }
            return super.getText(i);
        } catch (Resources.NotFoundException unused) {
            if (zIsCalledInLibrary) {
                return super.getText(i);
            }
            return this.mLibraryResource.getText(i);
        }
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getLayout(int i) throws Resources.NotFoundException {
        boolean zIsCalledInLibrary = isCalledInLibrary();
        try {
            if (zIsCalledInLibrary) {
                return this.mLibraryResource.getLayout(i);
            }
            return super.getLayout(i);
        } catch (Resources.NotFoundException unused) {
            if (zIsCalledInLibrary) {
                return super.getLayout(i);
            }
            return this.mLibraryResource.getLayout(i);
        }
    }

    @Override // android.content.res.Resources
    public void getValue(int i, TypedValue typedValue, boolean z) {
        boolean zIsCalledInLibrary = isCalledInLibrary();
        try {
            if (zIsCalledInLibrary) {
                this.mLibraryResource.getValue(i, typedValue, z);
            } else {
                super.getValue(i, typedValue, z);
            }
        } catch (Resources.NotFoundException unused) {
            if (zIsCalledInLibrary) {
                super.getValue(i, typedValue, z);
            } else {
                this.mLibraryResource.getValue(i, typedValue, z);
            }
        }
    }

    @Override // android.content.res.Resources
    public void getValueForDensity(int i, int i2, TypedValue typedValue, boolean z) {
        boolean zIsCalledInLibrary = isCalledInLibrary();
        try {
            if (zIsCalledInLibrary) {
                this.mLibraryResource.getValueForDensity(i, i2, typedValue, z);
            } else {
                super.getValueForDensity(i, i2, typedValue, z);
            }
        } catch (Resources.NotFoundException unused) {
            if (zIsCalledInLibrary) {
                super.getValueForDensity(i, i2, typedValue, z);
            } else {
                this.mLibraryResource.getValueForDensity(i, i2, typedValue, z);
            }
        }
    }

    @Override // android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        if (isCalledInLibrary()) {
            int identifier = this.mLibraryResource.getIdentifier(str, str2, str3);
            return identifier != 0 ? identifier : super.getIdentifier(str, str2, str3);
        }
        int identifier2 = super.getIdentifier(str, str2, str3);
        return identifier2 != 0 ? identifier2 : this.mLibraryResource.getIdentifier(str, str2, str3);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) {
        boolean zIsCalledInLibrary = isCalledInLibrary();
        try {
            if (zIsCalledInLibrary) {
                return this.mLibraryResource.getDrawable(i);
            }
            return super.getDrawable(i);
        } catch (Resources.NotFoundException unused) {
            if (zIsCalledInLibrary) {
                return super.getDrawable(i);
            }
            return this.mLibraryResource.getDrawable(i);
        }
    }
}
