package androidx.constraintlayout.core.parser;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class CLKey extends CLContainer {
    private static ArrayList<String> sections;

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        sections = arrayList;
        arrayList.add("ConstraintSets");
        sections.add("Variables");
        sections.add("Generate");
        sections.add(TypedValues.TransitionType.NAME);
        sections.add("KeyFrames");
        sections.add(TypedValues.AttributesType.NAME);
        sections.add("KeyPositions");
        sections.add("KeyCycles");
    }

    public CLKey(char[] cArr) {
        super(cArr);
    }

    public static CLElement allocate(String str, CLElement cLElement) {
        CLKey cLKey = new CLKey(str.toCharArray());
        cLKey.setStart(0L);
        cLKey.setEnd(str.length() - 1);
        cLKey.set(cLElement);
        return cLKey;
    }

    public static CLElement allocate(char[] cArr) {
        return new CLKey(cArr);
    }

    public String getName() {
        return content();
    }

    public CLElement getValue() {
        if (this.mElements.size() > 0) {
            return this.mElements.get(0);
        }
        return null;
    }

    public void set(CLElement cLElement) {
        if (this.mElements.size() > 0) {
            this.mElements.set(0, cLElement);
        } else {
            this.mElements.add(cLElement);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0046  */
    @Override // androidx.constraintlayout.core.parser.CLElement
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toFormattedJSON(int r6, int r7) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = r5.getDebugName()
            r0.<init>(r1)
            r5.addIndent(r0, r6)
            java.lang.String r1 = r5.content()
            java.util.ArrayList<androidx.constraintlayout.core.parser.CLElement> r2 = r5.mElements
            int r2 = r2.size()
            if (r2 <= 0) goto L5c
            r0.append(r1)
            java.lang.String r2 = ": "
            r0.append(r2)
            java.util.ArrayList<java.lang.String> r2 = androidx.constraintlayout.core.parser.CLKey.sections
            boolean r1 = r2.contains(r1)
            if (r1 == 0) goto L29
            r7 = 3
        L29:
            r1 = 0
            if (r7 <= 0) goto L2d
            goto L46
        L2d:
            java.util.ArrayList<androidx.constraintlayout.core.parser.CLElement> r2 = r5.mElements
            java.lang.Object r2 = r2.get(r1)
            androidx.constraintlayout.core.parser.CLElement r2 = (androidx.constraintlayout.core.parser.CLElement) r2
            java.lang.String r2 = r2.toJSON()
            int r3 = r2.length()
            int r3 = r3 + r6
            int r4 = androidx.constraintlayout.core.parser.CLElement.MAX_LINE
            if (r3 >= r4) goto L46
            r0.append(r2)
            goto L57
        L46:
            java.util.ArrayList<androidx.constraintlayout.core.parser.CLElement> r2 = r5.mElements
            java.lang.Object r1 = r2.get(r1)
            androidx.constraintlayout.core.parser.CLElement r1 = (androidx.constraintlayout.core.parser.CLElement) r1
            int r7 = r7 + (-1)
            java.lang.String r6 = r1.toFormattedJSON(r6, r7)
            r0.append(r6)
        L57:
            java.lang.String r6 = r0.toString()
            return r6
        L5c:
            java.lang.String r6 = ": <> "
            java.lang.String r6 = spi.d.f(r1, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.parser.CLKey.toFormattedJSON(int, int):java.lang.String");
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        StringBuilder sb;
        String json;
        if (this.mElements.size() > 0) {
            sb = new StringBuilder();
            sb.append(getDebugName());
            sb.append(content());
            sb.append(": ");
            json = this.mElements.get(0).toJSON();
        } else {
            sb = new StringBuilder();
            sb.append(getDebugName());
            sb.append(content());
            json = ": <> ";
        }
        sb.append(json);
        return sb.toString();
    }
}
