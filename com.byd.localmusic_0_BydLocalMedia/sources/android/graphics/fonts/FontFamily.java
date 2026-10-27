package android.graphics.fonts;

import com.android.internal.util.Preconditions;
import dalvik.annotation.optimization.CriticalNative;
import java.util.ArrayList;
import java.util.HashSet;
import libcore.util.NativeAllocationRegistry;

/* JADX INFO: loaded from: classes.dex */
public final class FontFamily {
    private static final String TAG = "FontFamily";
    private final ArrayList<Font> mFonts;
    private final long mNativePtr;

    public static final class Builder {
        private static final NativeAllocationRegistry sFamilyRegistory = NativeAllocationRegistry.createMalloced(FontFamily.class.getClassLoader(), nGetReleaseNativeFamily());
        private final ArrayList<Font> mFonts = new ArrayList<>();
        private final HashSet<Integer> mStyleHashSet = new HashSet<>();

        @CriticalNative
        private static native void nAddFont(long j, long j2);

        private static native long nBuild(long j, String str, int i, boolean z);

        @CriticalNative
        private static native long nGetReleaseNativeFamily();

        private static native long nInitBuilder();

        public Builder(Font font) {
            Preconditions.checkNotNull(font, "font can not be null");
            this.mStyleHashSet.add(Integer.valueOf(makeStyleIdentifier(font)));
            this.mFonts.add(font);
        }

        public Builder addFont(Font font) {
            Preconditions.checkNotNull(font, "font can not be null");
            if (!this.mStyleHashSet.add(Integer.valueOf(makeStyleIdentifier(font)))) {
                throw new IllegalArgumentException(font + " has already been added");
            }
            this.mFonts.add(font);
            return this;
        }

        public FontFamily build() {
            return build("", 0, true);
        }

        public FontFamily build(String str, int i, boolean z) {
            long jNInitBuilder = nInitBuilder();
            for (int i2 = 0; i2 < this.mFonts.size(); i2++) {
                nAddFont(jNInitBuilder, this.mFonts.get(i2).getNativePtr());
            }
            long jNBuild = nBuild(jNInitBuilder, str, i, z);
            FontFamily fontFamily = new FontFamily(this.mFonts, jNBuild);
            sFamilyRegistory.registerNativeAllocation(fontFamily, jNBuild);
            return fontFamily;
        }

        private static int makeStyleIdentifier(Font font) {
            return (font.getStyle().getSlant() << 16) | font.getStyle().getWeight();
        }
    }

    private FontFamily(ArrayList<Font> arrayList, long j) {
        this.mFonts = arrayList;
        this.mNativePtr = j;
    }

    public Font getFont(int i) {
        return this.mFonts.get(i);
    }

    public int getSize() {
        return this.mFonts.size();
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }
}
