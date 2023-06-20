package hr.algebra.bookify.webapp.security;

import java.util.Arrays;

public class StringSecurity {

    public final static String[] STRING_BLACK_LIST = {
            "SELECT",
            "INSERT",
            "ADD",
            "ALTER",
            "CREATE",
            "DROP",
            "DELETE",
            "OR"
    };

    public final static String[] CHAR_BLACK_LIST = {
            ";",
            "<",
            ">",
            "/",
            "\\"
    };

    public static String checkRegex() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^.*(");
        for(String string : STRING_BLACK_LIST) {
            stringBuilder.append("[; ]").append(string).append(" ").append("|");
        }
        for(String string : CHAR_BLACK_LIST) {
            stringBuilder.append("\\").append(string).append("|");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(").*$");
        return stringBuilder.toString();
    }

    public static boolean isSafe(String stringToTest) {
        return !stringToTest.toUpperCase().matches(checkRegex());
    }

    public static void main(String[] args) {
        System.out.println(checkRegex());
    }

}
