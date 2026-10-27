package androidx.slice;

import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Pair;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SliceItem extends CustomVersionedParcelable {
    String mFormat;
    protected String[] mHints;
    SliceItemHolder mHolder;
    Object mObj;
    CharSequence mSanitizedText;
    String mSubType;

    public interface ActionHandler {
        void onAction(SliceItem sliceItem, Context context, Intent intent);
    }

    public SliceItem(Object obj, String str, String str2, String[] strArr) {
        this.mHints = Slice.NO_HINTS;
        this.mFormat = "text";
        this.mSubType = null;
        this.mHints = strArr;
        this.mFormat = str;
        this.mSubType = str2;
        this.mObj = obj;
    }

    public SliceItem(Object obj, String str, String str2, List<String> list) {
        this(obj, str, str2, (String[]) list.toArray(new String[list.size()]));
    }

    public SliceItem() {
        this.mHints = Slice.NO_HINTS;
        this.mFormat = "text";
        this.mSubType = null;
    }

    public SliceItem(PendingIntent pendingIntent, Slice slice, String str, String str2, String[] strArr) {
        this(new Pair(pendingIntent, slice), str, str2, strArr);
    }

    public String getFormat() {
        return this.mFormat;
    }

    public String getSubType() {
        return this.mSubType;
    }

    public CharSequence getText() {
        return (CharSequence) this.mObj;
    }

    public CharSequence getSanitizedText() {
        if (this.mSanitizedText == null) {
            this.mSanitizedText = sanitizeText(getText());
        }
        return this.mSanitizedText;
    }

    public IconCompat getIcon() {
        return (IconCompat) this.mObj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PendingIntent getAction() {
        F f = ((Pair) this.mObj).first;
        if (f instanceof PendingIntent) {
            return (PendingIntent) f;
        }
        return null;
    }

    public void fireAction(Context context, Intent intent) throws PendingIntent.CanceledException {
        fireActionInternal(context, intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean fireActionInternal(Context context, Intent intent) throws PendingIntent.CanceledException {
        F f = ((Pair) this.mObj).first;
        if (f instanceof PendingIntent) {
            ((PendingIntent) f).send(context, 0, intent, null, null);
            return false;
        }
        ((ActionHandler) f).onAction(this, context, intent);
        return true;
    }

    public RemoteInput getRemoteInput() {
        return (RemoteInput) this.mObj;
    }

    public int getInt() {
        return ((Integer) this.mObj).intValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Slice getSlice() {
        if ("action".equals(getFormat())) {
            return (Slice) ((Pair) this.mObj).second;
        }
        return (Slice) this.mObj;
    }

    public long getLong() {
        return ((Long) this.mObj).longValue();
    }

    public boolean hasHint(String str) {
        return ArrayUtils.contains(this.mHints, str);
    }

    public SliceItem(Bundle bundle) {
        this.mHints = Slice.NO_HINTS;
        this.mFormat = "text";
        this.mSubType = null;
        this.mHints = bundle.getStringArray("hints");
        this.mFormat = bundle.getString("format");
        this.mSubType = bundle.getString("subtype");
        this.mObj = readObj(this.mFormat, bundle);
    }

    public boolean hasAnyHints(String... strArr) {
        if (strArr == null) {
            return false;
        }
        for (String str : strArr) {
            if (ArrayUtils.contains(this.mHints, str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.Object readObj(java.lang.String r3, android.os.Bundle r4) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.SliceItem.readObj(java.lang.String, android.os.Bundle):java.lang.Object");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String typeToString(java.lang.String r2) {
        /*
            int r0 = r2.hashCode()
            switch(r0) {
                case -1422950858: goto L44;
                case 104431: goto L3a;
                case 3327612: goto L30;
                case 3556653: goto L26;
                case 100313435: goto L1c;
                case 100358090: goto L12;
                case 109526418: goto L8;
                default: goto L7;
            }
        L7:
            goto L4e
        L8:
            java.lang.String r0 = "slice"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 0
            goto L4f
        L12:
            java.lang.String r0 = "input"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 6
            goto L4f
        L1c:
            java.lang.String r0 = "image"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 2
            goto L4f
        L26:
            java.lang.String r0 = "text"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 1
            goto L4f
        L30:
            java.lang.String r0 = "long"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 5
            goto L4f
        L3a:
            java.lang.String r0 = "int"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 4
            goto L4f
        L44:
            java.lang.String r0 = "action"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 3
            goto L4f
        L4e:
            r0 = -1
        L4f:
            switch(r0) {
                case 0: goto L76;
                case 1: goto L73;
                case 2: goto L70;
                case 3: goto L6d;
                case 4: goto L6a;
                case 5: goto L67;
                case 6: goto L64;
                default: goto L52;
            }
        L52:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unrecognized format: "
            r0.append(r1)
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            return r2
        L64:
            java.lang.String r2 = "RemoteInput"
            return r2
        L67:
            java.lang.String r2 = "Long"
            return r2
        L6a:
            java.lang.String r2 = "Int"
            return r2
        L6d:
            java.lang.String r2 = "Action"
            return r2
        L70:
            java.lang.String r2 = "Image"
            return r2
        L73:
            java.lang.String r2 = "Text"
            return r2
        L76:
            java.lang.String r2 = "Slice"
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.SliceItem.typeToString(java.lang.String):java.lang.String");
    }

    public String toString() {
        return toString("");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public String toString(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(getFormat());
        if (getSubType() != null) {
            sb.append('<');
            sb.append(getSubType());
            sb.append('>');
        }
        sb.append(' ');
        String[] strArr = this.mHints;
        if (strArr.length > 0) {
            Slice.appendHints(sb, strArr);
            sb.append(' ');
        }
        String str2 = str + "  ";
        String format = getFormat();
        byte b = -1;
        switch (format.hashCode()) {
            case -1422950858:
                if (format.equals("action")) {
                    b = 1;
                }
                break;
            case 104431:
                if (format.equals("int")) {
                    b = 4;
                }
                break;
            case 3327612:
                if (format.equals("long")) {
                    b = 5;
                }
                break;
            case 3556653:
                if (format.equals("text")) {
                    b = 2;
                }
                break;
            case 100313435:
                if (format.equals("image")) {
                    b = 3;
                }
                break;
            case 109526418:
                if (format.equals("slice")) {
                    b = 0;
                }
                break;
        }
        if (b == 0) {
            sb.append("{\n");
            sb.append(getSlice().toString(str2));
            sb.append('\n');
            sb.append(str);
            sb.append('}');
        } else if (b == 1) {
            Object obj = ((Pair) this.mObj).first;
            sb.append('[');
            sb.append(obj);
            sb.append("] ");
            sb.append("{\n");
            sb.append(getSlice().toString(str2));
            sb.append('\n');
            sb.append(str);
            sb.append('}');
        } else if (b == 2) {
            sb.append('\"');
            sb.append(getText());
            sb.append('\"');
        } else if (b == 3) {
            sb.append(getIcon());
        } else if (b != 4) {
            if (b == 5) {
                if (!"millis".equals(getSubType())) {
                    sb.append(getLong());
                    sb.append('L');
                } else if (getLong() == -1) {
                    sb.append("INFINITY");
                } else {
                    sb.append(DateUtils.getRelativeTimeSpanString(getLong(), Calendar.getInstance().getTimeInMillis(), 1000L, 262144));
                }
            } else {
                sb.append(typeToString(getFormat()));
            }
        } else if ("color".equals(getSubType())) {
            int i = getInt();
            sb.append(String.format("a=0x%02x r=0x%02x g=0x%02x b=0x%02x", Integer.valueOf(Color.alpha(i)), Integer.valueOf(Color.red(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.blue(i))));
        } else if ("layout_direction".equals(getSubType())) {
            sb.append(layoutDirectionToString(getInt()));
        } else {
            sb.append(getInt());
        }
        sb.append("\n");
        return sb.toString();
    }

    public void onPreParceling(boolean z) {
        this.mHolder = new SliceItemHolder(this.mFormat, this.mObj, z);
    }

    public void onPostParceling() {
        SliceItemHolder sliceItemHolder = this.mHolder;
        if (sliceItemHolder != null) {
            this.mObj = sliceItemHolder.getObj(this.mFormat);
            this.mHolder.release();
        } else {
            this.mObj = null;
        }
        this.mHolder = null;
    }

    private static String layoutDirectionToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? Integer.toString(i) : "LOCALE" : "INHERIT" : "RTL" : "LTR";
    }

    private static CharSequence sanitizeText(CharSequence charSequence) {
        if (charSequence instanceof Spannable) {
            fixSpannableText((Spannable) charSequence);
            return charSequence;
        }
        if (!(charSequence instanceof Spanned) || checkSpannedText((Spanned) charSequence)) {
            return charSequence;
        }
        SpannableString spannableString = new SpannableString(charSequence);
        fixSpannableText(spannableString);
        return spannableString;
    }

    private static boolean checkSpannedText(Spanned spanned) {
        for (Object obj : spanned.getSpans(0, spanned.length(), Object.class)) {
            if (!checkSpan(obj)) {
                return false;
            }
        }
        return true;
    }

    private static void fixSpannableText(Spannable spannable) {
        for (Object obj : spannable.getSpans(0, spannable.length(), Object.class)) {
            Object objFixSpan = fixSpan(obj);
            if (objFixSpan != obj) {
                if (objFixSpan != null) {
                    spannable.setSpan(objFixSpan, spannable.getSpanStart(obj), spannable.getSpanEnd(obj), spannable.getSpanFlags(obj));
                }
                spannable.removeSpan(obj);
            }
        }
    }

    private static boolean checkSpan(Object obj) {
        return (obj instanceof AlignmentSpan) || (obj instanceof ForegroundColorSpan) || (obj instanceof RelativeSizeSpan) || (obj instanceof StyleSpan);
    }

    private static Object fixSpan(Object obj) {
        if (checkSpan(obj)) {
            return obj;
        }
        return null;
    }
}
