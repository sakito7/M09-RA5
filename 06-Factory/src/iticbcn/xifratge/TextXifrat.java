package iticbcn.xifratge;

import java.util.Base64;

public class TextXifrat {
    private final byte[] bytes;

    public TextXifrat(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public byte[] getBytes() {
        return bytes;
    }
}