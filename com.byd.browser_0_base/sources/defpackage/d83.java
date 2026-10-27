package defpackage;

/* JADX INFO: compiled from: TextFormatEscaper.java */
/* JADX INFO: loaded from: classes.dex */
public final class d83 {

    /* JADX INFO: compiled from: TextFormatEscaper.java */
    public static class a implements b {
        public final /* synthetic */ lk a;

        public a(lk lkVar) {
            this.a = lkVar;
        }

        @Override // d83.b
        public final byte a(int i) {
            return this.a.a(i);
        }

        @Override // d83.b
        public final int size() {
            return this.a.size();
        }
    }

    /* JADX INFO: compiled from: TextFormatEscaper.java */
    public interface b {
        byte a(int i);

        int size();
    }

    public static String a(lk lkVar) {
        return b(new a(lkVar));
    }

    public static String b(b bVar) {
        StringBuilder sb = new StringBuilder(bVar.size());
        for (int i = 0; i < bVar.size(); i++) {
            byte bA = bVar.a(i);
            if (bA == 34) {
                sb.append("\\\"");
            } else if (bA == 39) {
                sb.append("\\'");
            } else if (bA != 92) {
                switch (bA) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (bA < 32 || bA > 126) {
                            sb.append('\\');
                            sb.append((char) (((bA >>> 6) & 3) + 48));
                            sb.append((char) (((bA >>> 3) & 7) + 48));
                            sb.append((char) ((bA & 7) + 48));
                        } else {
                            sb.append((char) bA);
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }
}
