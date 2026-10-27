package p;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Xml;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import androidx.fragment.app.SpecialEffectsController$Operation$State;
import androidx.work.BackoffPolicy;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import com.google.protobuf.InvalidProtocolBufferException;
import com.spotify.base.java.logging.Logger;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.builders.ListBuilder;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public abstract class p02 {
    public static final ke2 a = new ke2(2);
    public static final vk0 b = new vk0(3);
    public static final ke2 c = new ke2(10);
    public static final vd2 d = new vd2();
    public static final wd2 e = new wd2();
    public static final z90 f = new z90(13);

    public static SpecialEffectsController$Operation$State a(View view) {
        float alpha = view.getAlpha();
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State = SpecialEffectsController$Operation$State.l;
        if (alpha == 0.0f && view.getVisibility() == 0) {
            return specialEffectsController$Operation$State;
        }
        int visibility = view.getVisibility();
        if (visibility == 0) {
            return SpecialEffectsController$Operation$State.e;
        }
        if (visibility == 4) {
            return specialEffectsController$Operation$State;
        }
        if (visibility == 8) {
            return SpecialEffectsController$Operation$State.k;
        }
        throw new IllegalArgumentException(u4.e("Unknown visibility ", visibility));
    }

    public static final LinkedHashSet b(byte[] bArr) {
        v03.j(bArr, "bytes");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (bArr.length == 0) {
            return linkedHashSet;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                try {
                    int i = objectInputStream.readInt();
                    for (int i2 = 0; i2 < i; i2++) {
                        Uri uri = Uri.parse(objectInputStream.readUTF());
                        boolean z = objectInputStream.readBoolean();
                        v03.i(uri, "uri");
                        linkedHashSet.add(new vy(uri, z));
                    }
                    ux2.e(objectInputStream, null);
                } finally {
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            ux2.e(byteArrayInputStream, null);
            return linkedHashSet;
        } finally {
        }
    }

    public static void c(String str, boolean z) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void d(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void e(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static String f(ByteBuffer byteBuffer, int i, int i2) throws InvalidProtocolBufferException {
        if ((i | i2 | ((byteBuffer.limit() - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        int i3 = i + i2;
        char[] cArr = new char[i2];
        int i4 = 0;
        while (i < i3) {
            byte b2 = byteBuffer.get(i);
            if (!zu.c(b2)) {
                break;
            }
            i++;
            cArr[i4] = (char) b2;
            i4++;
        }
        int i5 = i4;
        while (i < i3) {
            int i6 = i + 1;
            byte b3 = byteBuffer.get(i);
            if (zu.c(b3)) {
                int i7 = i5 + 1;
                cArr[i5] = (char) b3;
                while (i6 < i3) {
                    byte b4 = byteBuffer.get(i6);
                    if (!zu.c(b4)) {
                        break;
                    }
                    i6++;
                    cArr[i7] = (char) b4;
                    i7++;
                }
                i5 = i7;
                i = i6;
            } else if (b3 < -32) {
                if (i6 >= i3) {
                    throw InvalidProtocolBufferException.c();
                }
                i += 2;
                zu.d(b3, byteBuffer.get(i6), cArr, i5);
                i5++;
            } else if (b3 < -16) {
                if (i6 >= i3 - 1) {
                    throw InvalidProtocolBufferException.c();
                }
                int i8 = i + 2;
                i += 3;
                zu.e(b3, byteBuffer.get(i6), byteBuffer.get(i8), cArr, i5);
                i5++;
            } else {
                if (i6 >= i3 - 2) {
                    throw InvalidProtocolBufferException.c();
                }
                byte b5 = byteBuffer.get(i6);
                int i9 = i + 3;
                byte b6 = byteBuffer.get(i + 2);
                i += 4;
                zu.b(b3, b5, b6, byteBuffer.get(i9), cArr, i5);
                i5 += 2;
            }
        }
        return new String(cArr, 0, i5);
    }

    public static final void g(androidx.sqlite.db.framework.b bVar) {
        ListBuilder listBuilder = new ListBuilder();
        Cursor cursorC = bVar.c("SELECT name FROM sqlite_master WHERE type = 'trigger'");
        while (cursorC.moveToNext()) {
            try {
                listBuilder.add(cursorC.getString(0));
            } finally {
            }
        }
        ux2.e(cursorC, null);
        listBuilder.m();
        Iterator it = listBuilder.iterator();
        while (true) {
            va1 va1Var = (va1) it;
            if (!va1Var.hasNext()) {
                return;
            }
            String str = (String) va1Var.next();
            v03.i(str, "triggerName");
            if (ng2.g0(str, "room_fts_content_sync_", false)) {
                bVar.w("DROP TRIGGER IF EXISTS ".concat(str));
            }
        }
    }

    public static int h(int i, int i2) {
        if (i2 < 0) {
            throw new AssertionError("cannot store more than MAX_VALUE elements");
        }
        int iHighestOneBit = i + (i >> 1) + 1;
        if (iHighestOneBit < i2) {
            iHighestOneBit = Integer.highestOneBit(i2 - 1) << 1;
        }
        if (iHighestOneBit < 0) {
            return Integer.MAX_VALUE;
        }
        return iHighestOneBit;
    }

    public static final BackoffPolicy i(int i) {
        if (i == 0) {
            return BackoffPolicy.c;
        }
        if (i == 1) {
            return BackoffPolicy.e;
        }
        throw new IllegalArgumentException(u4.f("Could not convert ", i, " to BackoffPolicy"));
    }

    public static final NetworkType j(int i) {
        if (i == 0) {
            return NetworkType.c;
        }
        if (i == 1) {
            return NetworkType.e;
        }
        if (i == 2) {
            return NetworkType.k;
        }
        if (i == 3) {
            return NetworkType.l;
        }
        if (i == 4) {
            return NetworkType.m;
        }
        if (Build.VERSION.SDK_INT < 30 || i != 5) {
            throw new IllegalArgumentException(u4.f("Could not convert ", i, " to NetworkType"));
        }
        return NetworkType.n;
    }

    public static final OutOfQuotaPolicy k(int i) {
        if (i == 0) {
            return OutOfQuotaPolicy.c;
        }
        if (i == 1) {
            return OutOfQuotaPolicy.e;
        }
        throw new IllegalArgumentException(u4.f("Could not convert ", i, " to OutOfQuotaPolicy"));
    }

    public static final WorkInfo$State l(int i) {
        if (i == 0) {
            return WorkInfo$State.c;
        }
        if (i == 1) {
            return WorkInfo$State.e;
        }
        if (i == 2) {
            return WorkInfo$State.k;
        }
        if (i == 3) {
            return WorkInfo$State.l;
        }
        if (i == 4) {
            return WorkInfo$State.m;
        }
        if (i == 5) {
            return WorkInfo$State.n;
        }
        throw new IllegalArgumentException(u4.f("Could not convert ", i, " to State"));
    }

    public static final boolean m(Type type) {
        if (type == null) {
            return false;
        }
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            v03.i(actualTypeArguments, "type.actualTypeArguments");
            for (Type type2 : actualTypeArguments) {
                if (m(type2)) {
                    return true;
                }
            }
            return false;
        }
        if (!(type instanceof Class)) {
            return false;
        }
        try {
        } catch (NoClassDefFoundError e2) {
            Logger.c(e2, "Error reading annotation", new Object[0]);
        }
        if (!((Class) type).isAnnotationPresent(c71.class)) {
            if (!((Class) type).isAnnotationPresent(rr2.class)) {
                if (!pr2.class.isAssignableFrom((Class) type)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void n(Context context, String str) {
        if (str.equals("")) {
            context.deleteFile("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file");
            return;
        }
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file", 0);
            XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
            try {
                xmlSerializerNewSerializer.setOutput(fileOutputStreamOpenFileOutput, null);
                xmlSerializerNewSerializer.startDocument("UTF-8", Boolean.TRUE);
                xmlSerializerNewSerializer.startTag(null, "locales");
                xmlSerializerNewSerializer.attribute(null, "application_locales", str);
                xmlSerializerNewSerializer.endTag(null, "locales");
                xmlSerializerNewSerializer.endDocument();
                if (fileOutputStreamOpenFileOutput == null) {
                    return;
                }
            } catch (Exception unused) {
                if (fileOutputStreamOpenFileOutput == null) {
                    return;
                }
            } catch (Throwable th) {
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
            try {
                fileOutputStreamOpenFileOutput.close();
            } catch (IOException unused3) {
            }
        } catch (FileNotFoundException unused4) {
            String.format("Storing App Locales : FileNotFoundException: Cannot open file %s for writing ", "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file");
        }
    }

    public static final Cursor o(i52 i52Var, m52 m52Var) {
        v03.j(i52Var, "db");
        return i52Var.m(m52Var, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003a, code lost:
    
        r1 = r3.getAttributeValue(null, "application_locales");
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0043 A[EXC_TOP_SPLITTER, PHI: r1
  0x0043: PHI (r1v3 java.lang.String) = (r1v0 java.lang.String), (r1v5 java.lang.String) binds: [B:27:0x004d, B:21:0x0041] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String p(android.content.Context r8) {
        /*
            java.lang.String r0 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.lang.String r1 = ""
            java.io.FileInputStream r2 = r8.openFileInput(r0)     // Catch: java.io.FileNotFoundException -> L5a
            org.xmlpull.v1.XmlPullParser r3 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
            java.lang.String r4 = "UTF-8"
            r3.setInput(r2, r4)     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
            int r4 = r3.getDepth()     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
        L15:
            int r5 = r3.next()     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
            r6 = 1
            if (r5 == r6) goto L41
            r6 = 3
            if (r5 != r6) goto L28
            int r7 = r3.getDepth()     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
            if (r7 <= r4) goto L41
            goto L28
        L26:
            r8 = move-exception
            goto L47
        L28:
            if (r5 == r6) goto L15
            r6 = 4
            if (r5 != r6) goto L2e
            goto L15
        L2e:
            java.lang.String r5 = r3.getName()     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
            java.lang.String r6 = "locales"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
            if (r5 == 0) goto L15
            java.lang.String r4 = "application_locales"
            r5 = 0
            java.lang.String r1 = r3.getAttributeValue(r5, r4)     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L4d
        L41:
            if (r2 == 0) goto L50
        L43:
            r2.close()     // Catch: java.io.IOException -> L50
            goto L50
        L47:
            if (r2 == 0) goto L4c
            r2.close()     // Catch: java.io.IOException -> L4c
        L4c:
            throw r8
        L4d:
            if (r2 == 0) goto L50
            goto L43
        L50:
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L57
            goto L5a
        L57:
            r8.deleteFile(r0)
        L5a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p.p02.p(android.content.Context):java.lang.String");
    }

    public static void q(EditorInfo editorInfo, CharSequence charSequence, int i, int i2) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT", charSequence != null ? new SpannableStringBuilder(charSequence) : null);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD", i);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END", i2);
    }

    public static final int r(WorkInfo$State workInfo$State) {
        v03.j(workInfo$State, "state");
        int iOrdinal = workInfo$State.ordinal();
        if (iOrdinal == 0) {
            return 0;
        }
        if (iOrdinal == 1) {
            return 1;
        }
        if (iOrdinal == 2) {
            return 2;
        }
        if (iOrdinal == 3) {
            return 3;
        }
        if (iOrdinal == 4) {
            return 4;
        }
        if (iOrdinal == 5) {
            return 5;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final com.apollographql.apollo3.api.c s(com.apollographql.apollo3.api.c cVar, Set set) {
        v03.j(cVar, "<this>");
        v03.j(set, "deferredFragmentIds");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(cVar.c);
        return new com.apollographql.apollo3.api.c(linkedHashMap, new z4(cVar.b.a, set));
    }

    public abstract void t(byte[] bArr, int i, int i2);
}
