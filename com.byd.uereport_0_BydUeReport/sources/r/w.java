package r;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ResourcesWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class w extends Resources {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Resources f7657a;

    public w(Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f7657a = resources;
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getAnimation(int i8) throws Resources.NotFoundException {
        return this.f7657a.getAnimation(i8);
    }

    @Override // android.content.res.Resources
    public boolean getBoolean(int i8) throws Resources.NotFoundException {
        return this.f7657a.getBoolean(i8);
    }

    @Override // android.content.res.Resources
    public int getColor(int i8) throws Resources.NotFoundException {
        return this.f7657a.getColor(i8);
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int i8) throws Resources.NotFoundException {
        return this.f7657a.getColorStateList(i8);
    }

    @Override // android.content.res.Resources
    public Configuration getConfiguration() {
        return this.f7657a.getConfiguration();
    }

    @Override // android.content.res.Resources
    public float getDimension(int i8) throws Resources.NotFoundException {
        return this.f7657a.getDimension(i8);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelOffset(int i8) throws Resources.NotFoundException {
        return this.f7657a.getDimensionPixelOffset(i8);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelSize(int i8) throws Resources.NotFoundException {
        return this.f7657a.getDimensionPixelSize(i8);
    }

    @Override // android.content.res.Resources
    public DisplayMetrics getDisplayMetrics() {
        return this.f7657a.getDisplayMetrics();
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i8) throws Resources.NotFoundException {
        return this.f7657a.getDrawable(i8);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i8, int i9) throws Resources.NotFoundException {
        return this.f7657a.getDrawableForDensity(i8, i9);
    }

    @Override // android.content.res.Resources
    public float getFraction(int i8, int i9, int i10) {
        return this.f7657a.getFraction(i8, i9, i10);
    }

    @Override // android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        return this.f7657a.getIdentifier(str, str2, str3);
    }

    @Override // android.content.res.Resources
    public int[] getIntArray(int i8) throws Resources.NotFoundException {
        return this.f7657a.getIntArray(i8);
    }

    @Override // android.content.res.Resources
    public int getInteger(int i8) throws Resources.NotFoundException {
        return this.f7657a.getInteger(i8);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getLayout(int i8) throws Resources.NotFoundException {
        return this.f7657a.getLayout(i8);
    }

    @Override // android.content.res.Resources
    public Movie getMovie(int i8) throws Resources.NotFoundException {
        return this.f7657a.getMovie(i8);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i8, int i9, Object... objArr) throws Resources.NotFoundException {
        return this.f7657a.getQuantityString(i8, i9, objArr);
    }

    @Override // android.content.res.Resources
    public CharSequence getQuantityText(int i8, int i9) throws Resources.NotFoundException {
        return this.f7657a.getQuantityText(i8, i9);
    }

    @Override // android.content.res.Resources
    public String getResourceEntryName(int i8) throws Resources.NotFoundException {
        return this.f7657a.getResourceEntryName(i8);
    }

    @Override // android.content.res.Resources
    public String getResourceName(int i8) throws Resources.NotFoundException {
        return this.f7657a.getResourceName(i8);
    }

    @Override // android.content.res.Resources
    public String getResourcePackageName(int i8) throws Resources.NotFoundException {
        return this.f7657a.getResourcePackageName(i8);
    }

    @Override // android.content.res.Resources
    public String getResourceTypeName(int i8) throws Resources.NotFoundException {
        return this.f7657a.getResourceTypeName(i8);
    }

    @Override // android.content.res.Resources
    public String getString(int i8) throws Resources.NotFoundException {
        return this.f7657a.getString(i8);
    }

    @Override // android.content.res.Resources
    public String[] getStringArray(int i8) throws Resources.NotFoundException {
        return this.f7657a.getStringArray(i8);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i8) throws Resources.NotFoundException {
        return this.f7657a.getText(i8);
    }

    @Override // android.content.res.Resources
    public CharSequence[] getTextArray(int i8) throws Resources.NotFoundException {
        return this.f7657a.getTextArray(i8);
    }

    @Override // android.content.res.Resources
    public void getValue(int i8, TypedValue typedValue, boolean z7) throws Resources.NotFoundException {
        this.f7657a.getValue(i8, typedValue, z7);
    }

    @Override // android.content.res.Resources
    public void getValueForDensity(int i8, int i9, TypedValue typedValue, boolean z7) throws Resources.NotFoundException {
        this.f7657a.getValueForDensity(i8, i9, typedValue, z7);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getXml(int i8) throws Resources.NotFoundException {
        return this.f7657a.getXml(i8);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainAttributes(AttributeSet attributeSet, int[] iArr) {
        return this.f7657a.obtainAttributes(attributeSet, iArr);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainTypedArray(int i8) throws Resources.NotFoundException {
        return this.f7657a.obtainTypedArray(i8);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i8) throws Resources.NotFoundException {
        return this.f7657a.openRawResource(i8);
    }

    @Override // android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int i8) throws Resources.NotFoundException {
        return this.f7657a.openRawResourceFd(i8);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtra(String str, AttributeSet attributeSet, Bundle bundle) throws XmlPullParserException {
        this.f7657a.parseBundleExtra(str, attributeSet, bundle);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtras(XmlResourceParser xmlResourceParser, Bundle bundle) throws XmlPullParserException, IOException {
        this.f7657a.parseBundleExtras(xmlResourceParser, bundle);
    }

    @Override // android.content.res.Resources
    public void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics) {
        super.updateConfiguration(configuration, displayMetrics);
        Resources resources = this.f7657a;
        if (resources != null) {
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i8, Resources.Theme theme) throws Resources.NotFoundException {
        return this.f7657a.getDrawable(i8, theme);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i8, int i9, Resources.Theme theme) {
        return this.f7657a.getDrawableForDensity(i8, i9, theme);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i8, int i9) throws Resources.NotFoundException {
        return this.f7657a.getQuantityString(i8, i9);
    }

    @Override // android.content.res.Resources
    public String getString(int i8, Object... objArr) throws Resources.NotFoundException {
        return this.f7657a.getString(i8, objArr);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i8, CharSequence charSequence) {
        return this.f7657a.getText(i8, charSequence);
    }

    @Override // android.content.res.Resources
    public void getValue(String str, TypedValue typedValue, boolean z7) throws Resources.NotFoundException {
        this.f7657a.getValue(str, typedValue, z7);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i8, TypedValue typedValue) throws Resources.NotFoundException {
        return this.f7657a.openRawResource(i8, typedValue);
    }
}
