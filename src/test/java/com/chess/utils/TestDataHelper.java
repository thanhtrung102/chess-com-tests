package com.chess.utils;

import com.zebrunner.carina.utils.R;

/**
 * Helper class for test data management.
 * Provides access to test configuration data.
 */
public class TestDataHelper {

    /**
     * Get test username from config
     * @return test username (Gmail account)
     */
    public static String getTestUsername() {
        String username = R.CONFIG.get("test_user_username");
        return (username != null && !username.isEmpty()) ? username : "thanhtrungnsl2003@gmail.com";
    }

    /**
     * Get test email from config
     * @return test email
     */
    public static String getTestEmail() {
        String email = R.CONFIG.get("test_user_email");
        return (email != null && !email.isEmpty()) ? email : "thanhtrungnsl2003@gmail.com";
    }

    /**
     * Get test password from config
     * @return test password
     */
    public static String getTestPassword() {
        String password = R.CONFIG.get("test_user_password");
        return (password != null && !password.isEmpty()) ? password : "13122003@Abc";
    }
}
