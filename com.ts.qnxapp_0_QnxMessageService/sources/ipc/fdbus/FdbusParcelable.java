package ipc.fdbus;

/* JADX INFO: loaded from: classes.dex */
public class FdbusParcelable {

    public static class TextFormatter {
        private static final int FDB_BYTEARRAY_PRINT_SIZE = 16;
        private String mStream;

        public TextFormatter() {
            this.mStream = new String();
        }

        public TextFormatter(String stream) {
            this.mStream = stream;
        }

        public void append(String data) {
            this.mStream += data;
        }

        public String stream() {
            return this.mStream;
        }

        private void formatOne(Object value) {
            if (value instanceof FdbusParcelable) {
                FdbusParcelable op = (FdbusParcelable) value;
                this.mStream += "{";
                op.toString(this);
                this.mStream += "}";
                return;
            }
            if (value instanceof String) {
                String os = (String) value;
                this.mStream += os;
                return;
            }
            if (value instanceof Integer) {
                Integer oi = (Integer) value;
                this.mStream += oi.toString();
                return;
            }
            if (value instanceof byte[]) {
                byte[] ob = (byte[]) value;
                int len = ob.length;
                if (len > 16) {
                    Integer l = Integer.valueOf(len);
                    this.mStream += l.toString() + "[";
                    len = 16;
                } else {
                    this.mStream += "[";
                }
                for (int i = 0; i < len; i++) {
                    Byte data = Byte.valueOf(ob[i]);
                    this.mStream += data.toString() + ",";
                }
                this.mStream += "]";
                return;
            }
            if (value instanceof byte[][]) {
                this.mStream += "[";
                for (byte[] bArr : (byte[][]) value) {
                    formatOne(bArr);
                    this.mStream += ",";
                }
                this.mStream += "]";
                return;
            }
            this.mStream += "error!";
        }

        public void format(Object value) {
            if (value instanceof Object[]) {
                this.mStream += "[";
                Object[] oa = (Object[]) value;
                for (Object obj : oa) {
                    formatOne(obj);
                    this.mStream += ",";
                }
                this.mStream += "]";
                return;
            }
            formatOne(value);
        }

        public void format(String key, Object value) {
            this.mStream += key + ":";
            format(value);
            this.mStream += ",";
        }
    }

    public void serialize(FdbusSerializer serializer) {
    }

    public void deserialize(FdbusDeserializer deserializer) {
    }

    public void toString(TextFormatter fmter) {
        fmter.format("null");
    }

    public String toString() {
        TextFormatter fmter = new TextFormatter();
        fmter.format(this);
        return fmter.stream();
    }
}
