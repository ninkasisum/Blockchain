package org.hyperskill.models;

import com.sun.istack.internal.NotNull;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Hash {
    private static final int cost = 10;
    private static String empty = "0x00000000";
    public static boolean isValidHash(@NotNull String data,@NotNull String hash)
    {
        return BCrypt.checkpw(data, hash);
    }

    public static String calculateHash(@NotNull String data)
    {
        return BCrypt.hashpw(data, BCrypt.gensalt(cost));
    }

    public static String empty()
    {
        return BCrypt.hashpw(empty, BCrypt.gensalt(cost));
    }
}
