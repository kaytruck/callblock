package kaytruck.spring.callblock.configuration;

/**
 * 閉塞制御のステータス
 */
public enum BlockStatusCode {
    OPEN("OPEN"), CLOSE("CLOSE");

    private String str;

    private BlockStatusCode(String s) {
        this.str = s;
    }

    public String getString() {
        return str;
    }

    public static BlockStatusCode getCode(String s) {
        for (BlockStatusCode code : values()) {
            if (code.getString().equalsIgnoreCase(s)) {
                return code;
            }
        }
        throw new IllegalArgumentException("定義されていない型です=" + s);
    }
}